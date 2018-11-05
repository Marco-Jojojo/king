package mx.gob.segob.dgtic.business.service;

import java.util.List;
import java.util.Map;

import mx.gob.segob.dgtic.comun.entidad.Parametro;

public interface ParametroService {
	
	List<Parametro> obtieneParametros();
	
	Parametro obtieneParametroPorNombre(String nombre);
	
	Map<String, String> obtieneParametrosEspecificos(String prefijo);
}
