package mx.gob.segob.dgtic.webservices.recursos;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.util.GenericType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;
import mx.gob.segob.dgtic.business.service.GestionProyectosService;
import mx.gob.segob.dgtic.comun.entidad.Proyecto;
import mx.gob.segob.dgtic.comun.transport.constants.StatusResponse;
import mx.gob.segob.dgtic.webservices.recursos.base.RecursoBase;
import mx.gob.segob.dgtic.webservices.util.ResponseJSONGenericoUtil;

@Path("sec")
@Component
@Slf4j
public class GestionProyectosRecurso extends RecursoBase{
	
	@Autowired
	private GestionProyectosService gestionProyectosService;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/proyectos")
    //@Auditable(modulo="GESTION_PROYECTOS", guardarParametrosEntrada=true)
	@PermitAll
    public Response ObtenerProyectos(
    		@QueryParam("claveUsuario") String claveUsuario,
            @HeaderParam("Authorization") String tokenHeaderAcceso
            ) {
    	Response response = null;
    	
    	List<Proyecto> proyectos = gestionProyectosService.obtenerProyectos();
        response = ResponseJSONGenericoUtil.getRespuestaExito(StatusResponse.OK, proyectos);
        return response;
	 }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/proyectos/{clave}")
    //@Auditable(modulo="GESTION_PROYECTOS", guardarParametrosEntrada=true)
	@PermitAll
    public Response ObtenerProyecto(
    		@PathParam("clave") String clave,
    		@QueryParam("claveUsuario") String claveUsuario,
            @HeaderParam("Authorization") String tokenHeaderAcceso
            ) {
    	Proyecto proyecto = gestionProyectosService.obtenerProyectoPorID(clave);
        return ResponseJSONGenericoUtil.getRespuestaExito(StatusResponse.OK, proyecto);
	 }
	
	@POST
	@Path("/proyectos")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Auditable(modulo="GESTION_PROYECTOS", guardarParametrosEntrada=true)
	@PermitAll
	public Response agregarProyecto(@RequestBody Proyecto proyecto) {
		Proyecto nuevoProyecto = gestionProyectosService.registrarProyecto(proyecto);
	    return ResponseJSONGenericoUtil.getRespuestaExito(StatusResponse.OK, nuevoProyecto);
	}
	
	@PUT
	@Path("/proyectos/{clave}")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@PermitAll
	public Response actualizarProyecto(@PathParam("clave") String clave, MultipartFormDataInput input) throws IOException {
		Proyecto proyectoDB = gestionProyectosService.obtenerProyectoPorID(clave);
		if (proyectoDB == null){
			return ResponseJSONGenericoUtil.getRespuestaWarning(StatusResponse.OK, null, "Proyecto no encontrado");
		}
		Proyecto proyecto = input.getFormDataPart("proyecto", new GenericType<Proyecto>(){});
		
		Map<String, List<InputPart>> mapaPartes = input.getFormDataMap();
		Map<String, Byte[]> archivosEnBytes = new HashMap<>();
		for (String llave : mapaPartes.keySet()) {
			if(llave.startsWith("archivo")) {
				Byte[] objetivoArchivo = obtieneBytesDeArchivo(mapaPartes, llave);
				archivosEnBytes.put(llave, objetivoArchivo);
				//FIXME: Lo ideal es tener un campo que guarde el nombre del archivo para poder descargarlo con su extensión adecuada.
			}
		}
		
		gestionProyectosService.copiarValoresProyecto(proyectoDB, proyecto, archivosEnBytes);
		proyecto = gestionProyectosService.actualizarProyecto(proyectoDB);

		return ResponseJSONGenericoUtil.getRespuestaExito(StatusResponse.OK, proyecto);
	}

	private Byte[] obtieneBytesDeArchivo(Map<String, List<InputPart>> mapaPartes, String llave) {
		List<InputPart> inputParts = mapaPartes.get(llave);
		byte[] objetivoArchivo = null;
		for (InputPart inputPart : inputParts) {
			try {
				//convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class,null);
				objetivoArchivo = IOUtils.toByteArray(inputStream);
			  } catch (IOException e) {
				e.printStackTrace();
			  }
		}
		return ArrayUtils.toObject(objetivoArchivo);
	}
	
	/**
	 * TODO: Este método será usado cuando guardemos el nombre del archivo (para extraerlo del formulario).
	 * @param header
	 * @return
	 */
	private String getFileName(MultivaluedMap<String, String> header) {
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String[] name = filename.split("=");
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

}
