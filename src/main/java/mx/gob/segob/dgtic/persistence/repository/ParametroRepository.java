package mx.gob.segob.dgtic.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.gob.segob.dgtic.comun.entidad.Parametro;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Integer> {
	Parametro findByParametro(String parametro);
	
	List<Parametro> findByParametroLike(String parametro);
}
