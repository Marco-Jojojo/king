package mx.gob.segob.dgtic.persistence.repository;

import mx.gob.segob.dgtic.comun.entidad.Estatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstatusRepository extends JpaRepository<Estatus, Integer> {

}