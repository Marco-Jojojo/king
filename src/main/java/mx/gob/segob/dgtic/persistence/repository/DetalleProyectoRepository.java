package mx.gob.segob.dgtic.persistence.repository;

import mx.gob.segob.dgtic.comun.entidad.DetalleProyecto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleProyectoRepository extends JpaRepository<DetalleProyecto, String>{

}
