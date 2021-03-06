package mx.gob.segob.dgtic.business.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.bytecode.ByteArray;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mx.gob.segob.dgtic.business.service.NotificacionesService;
import mx.gob.segob.dgtic.business.service.GestionProyectosService;
import mx.gob.segob.dgtic.business.service.base.ServiceBase;
import mx.gob.segob.dgtic.comun.entidad.Actividad;
import mx.gob.segob.dgtic.comun.entidad.DetalleProyecto;
import mx.gob.segob.dgtic.comun.entidad.Objetivo;
import mx.gob.segob.dgtic.comun.entidad.Proyecto;
import mx.gob.segob.dgtic.comun.entidad.SubTarea;
import mx.gob.segob.dgtic.comun.entidad.Tarea;
import mx.gob.segob.dgtic.comun.transport.dto.proyectos.ProyectoDto;
import mx.gob.segob.dgtic.persistence.repository.DetalleProyectoRepository;
import mx.gob.segob.dgtic.persistence.repository.ProyectoRepository;


@Service
@Slf4j
public class GestionProyectosServiceImpl extends ServiceBase implements GestionProyectosService {

	@Autowired
	private NotificacionesService notificacionesService;
	
	@Autowired
	private ProyectoRepository proyectoRepository;
	
	@Autowired
	private DetalleProyectoRepository detalleProyectoRepository;
	
	@Value("${ruta.archivos}")
	public String FILE_PATH;
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<Proyecto> obtenerProyectos() {
		return proyectoRepository.findAll();
	}

	@Override
	public Proyecto obtenerProyectoPorID(String clave) {
		return proyectoRepository.findOne(clave);
	}
	
	@Override
	@Transactional
	public Proyecto registrarProyecto(Proyecto proyecto, Map<String, Byte[]> archivos) {
		asignaciones(proyecto, archivos);
		return persisteProyecto(proyecto);
	}

	@Override
	public Proyecto actualizarProyecto(Proyecto proyecto) {
		return persisteProyecto(proyecto);
	}

	private Proyecto persisteProyecto(Proyecto proyecto) {
		Proyecto proyectoGuardado = null;
		try{
			proyectoGuardado = proyectoRepository.save(proyecto);	
			//Usuario usuario = usuarioService.buscaUsuarioPorId(id);
			String destinatario = "@mozcalti.com"; //usuario.getCorreo();
			String texto = "Se ha registrado un nuevo proyecto con ID";
			String asunto = "Registro de nuevo proyecto";
			notificacionesService.enviaCorreo(destinatario, asunto, texto);
			
		}catch(Exception ex){
			log.error(ex.getMessage());
		}
		return proyectoGuardado;
	}

	@Override
	public void copiarValoresProyecto(Proyecto viejo, Proyecto nuevo, Map<String, Byte[]> archivos) {
		if(nuevo.getId() != null)
			viejo.setId(nuevo.getId());
		if(nuevo.getUsuario() != null)
			viejo.setUsuario(nuevo.getUsuario()); // FIXME: Corregir cuando se haga la relación con la entidad Usuario.
		if(nuevo.getNombre() != null)
			viejo.setNombre(nuevo.getNombre());
		if(nuevo.getFechaRegistro() != null)
			viejo.setFechaRegistro(nuevo.getFechaRegistro());
		if(nuevo.getArea() != null)
			viejo.setArea(nuevo.getArea());
		if(nuevo.getEstatus() != null)
			viejo.setEstatus(nuevo.getEstatus());
		if(nuevo.getPorcentajeAvance() != null)
			viejo.setPorcentajeAvance(nuevo.getPorcentajeAvance());
		copiarValores(viejo.getDetallesProyecto(), nuevo.getDetallesProyecto());
		asignaciones(viejo, archivos);
	}

	/**
	 * Guarda el valor bidireccional en las colecciones de la entidad (ya que el Json no contiene este valor, debido a que es excluído)
	 * y guarda el archivo correspondiente siguiendo una convención de nombre del parámetro en la forma "archivo-<entidad>-<id>.
	 * @param proyectoEnBD
	 */
	private void asignaciones(Proyecto proyectoEnBD, Map<String, Byte[]> archivos) {
		for(DetalleProyecto detalle : proyectoEnBD.getDetallesProyecto()) {
			detalle.setProyecto(proyectoEnBD);
			for(Objetivo objetivo : detalle.getObjetivos()) {
				objetivo.setDetalleProyecto(detalle);
				Byte[] archivoObjetivo = archivos.get("archivo-objetivo-"+objetivo.getClave());
				if(archivoObjetivo == null) {
					archivoObjetivo = new Byte[0];
				}
				objetivo.setArchivo(ArrayUtils.toPrimitive(archivoObjetivo));
				for(Actividad actividad : objetivo.getActividades()) {
					actividad.setObjetivo(objetivo);
					Byte[] archivoActividad = archivos.get("archivo-actividad-"+actividad.getId());
					if(archivoActividad == null) {
						archivoActividad = new Byte[0];
					}
					actividad.setArchivo(ArrayUtils.toPrimitive(archivoActividad));
					for(Tarea tarea : actividad.getTareas()) {
						tarea.setActividad(actividad);
						Byte[] archivoTarea = archivos.get("archivo-tarea-"+tarea.getId());
						if(archivoTarea == null) {
							archivoTarea = new Byte[0];
						}
						tarea.setArchivo(ArrayUtils.toPrimitive(archivoTarea));
						for(SubTarea subTarea : tarea.getSubTareas()) {
							subTarea.setTarea(tarea);
							Byte[] archivoSubTarea = archivos.get("archivo-subTarea-"+subTarea.getId());
							if(archivoSubTarea == null) {
								archivoSubTarea = new Byte[0];
							}
							subTarea.setArchivo(ArrayUtils.toPrimitive(archivoSubTarea));
						}
					}
				}
			}
		}
	}
}
