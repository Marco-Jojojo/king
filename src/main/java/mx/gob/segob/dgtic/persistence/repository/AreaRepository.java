package mx.gob.segob.dgtic.persistence.repository;

import mx.gob.segob.dgtic.comun.entidad.Area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, String> {

}
