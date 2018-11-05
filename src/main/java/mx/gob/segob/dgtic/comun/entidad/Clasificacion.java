package mx.gob.segob.dgtic.comun.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "C_CLASPROY")
public class Clasificacion implements Serializable {

	private static final long serialVersionUID = 1004L;
	
    @Id
    @Column(name = "ID_CLASPROY")
    private Integer id;
    
    @Column(name = "DES_CLASPROY")
    private String descripcion;
    
    @Column(name = "ESTATUS")
    private char estatus;
	
}