package mx.gob.segob.dgtic.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.dgtic.business.service.ParametroService;
import mx.gob.segob.dgtic.comun.entidad.Parametro;
import mx.gob.segob.dgtic.persistence.repository.ParametroRepository;

@Service
public class ParametroServiceImpl implements ParametroService {
	
	@Autowired
	private ParametroRepository parametroRepository;
	
	@Override
	public List<Parametro> obtieneParametros() {
		return parametroRepository.findAll();
	}

	@Override
	public Parametro obtieneParametroPorNombre(String nombre) {
		return parametroRepository.findByParametro(nombre);
	}

	@Override
	public Map<String, String> obtieneParametrosEspecificos(String prefijo) {
		Map<String, String> parametros = new HashMap<>();
		List<Parametro> listaParametros = parametroRepository.findByParametroLike(prefijo+"%");
		for(Parametro p : listaParametros) {
			parametros.put(p.getParametro().substring(prefijo.length()+1), p.getValParametro());
		}
		return parametros;
	}
}
