package mx.gob.segob.dgtic.webservices.recursos;

import java.net.URI;
import java.util.List;

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
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;
import mx.gob.segob.dgtic.business.service.GestionProyectosService;
import mx.gob.segob.dgtic.comun.entidad.Proyecto;
import mx.gob.segob.dgtic.comun.transport.constants.StatusResponse;
import mx.gob.segob.dgtic.comun.transport.dto.proyectos.ProyectoDto;
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
	@Consumes(MediaType.APPLICATION_JSON)
	//@Auditable(modulo="GESTION_PROYECTOS", guardarParametrosEntrada=true)
	@PermitAll
	public Response actualizarProyecto(@RequestBody Proyecto proyecto, @PathParam("clave") String clave) {
		Proyecto proyectoDB = gestionProyectosService.obtenerProyectoPorID(clave);
		if (proyectoDB == null){
			return ResponseJSONGenericoUtil.getRespuestaWarning(StatusResponse.OK, null, "Proyecto no encontrado");
		}
		gestionProyectosService.copiarValoresProyecto(proyectoDB, proyecto);
		proyecto = gestionProyectosService.actualizarProyecto(proyectoDB);

		return ResponseJSONGenericoUtil.getRespuestaExito(StatusResponse.OK, proyecto);
	}

}
