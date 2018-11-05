package mx.gob.segob.dgtic.comun.transport.dto.proyectos;


import java.util.Date;

import lombok.Data;
import mx.gob.segob.dgtic.webservices.recursos.GestionProyectosRecurso;

@Data
public class ProyectoDto {
	private String clave;
	private String claveUsuario;
	private String nombre;
	private Date fechaRegistro;
	private String claveArea;
	private Integer idEstatus;
	private Double porcentajeAvance;
	
	
	
}
