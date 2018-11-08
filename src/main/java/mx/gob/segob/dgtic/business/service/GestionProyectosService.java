package mx.gob.segob.dgtic.business.service;

import java.util.List;
import java.util.Map;

import mx.gob.segob.dgtic.comun.entidad.Proyecto;
import mx.gob.segob.dgtic.comun.transport.dto.proyectos.ProyectoDto;

public interface GestionProyectosService {

	/**
	 * Registra un nuevo proyecto.
	 * @param proyecto
	 * @return Indica si ha sido guardado con éxito o no.
	 */
	Proyecto registrarProyecto(Proyecto proyecto, Map<String, Byte[]> archivos);
	
	/**
	 * Actualiza un proyecto existente.
	 * @param proyecto
	 * @return Indica si ha sido guardado con éxito o no.
	 */
	Proyecto actualizarProyecto(Proyecto proyecto);
	
	/**
	 * Obtiene una lista de todos los proyectos registrados
	 * @return
	 */
	List<Proyecto> obtenerProyectos();
	
	
	/**
	 * Obtiene un proyecto por ID
	 * @return
	 */
	Proyecto obtenerProyectoPorID(String claveProyecto);
	
	/**
	 * Copia valores nuevo al ya existente en base de datos.
	 * @param viejo
	 * @param nuevo
	 */
	void copiarValoresProyecto(Proyecto viejo, Proyecto nuevo, Map<String, Byte[]> archivos);
}
