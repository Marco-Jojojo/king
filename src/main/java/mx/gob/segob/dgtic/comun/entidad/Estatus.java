package mx.gob.segob.dgtic.comun.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "C_ESTATUS")
public class Estatus implements Serializable {

	private static final long serialVersionUID = 1001L;
	
    @Id
    @Column(name = "ID_ESTATUS")
    private Integer id;
    
    @Column(name = "DES_ESTATUS")
    private String descripcion;
    
    @Column(name = "ESTATUS")
    private char estatus;
	
}
