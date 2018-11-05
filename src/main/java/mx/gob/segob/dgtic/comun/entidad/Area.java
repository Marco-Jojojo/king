package mx.gob.segob.dgtic.comun.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "C_AREA")
public class Area implements Serializable {

	private static final long serialVersionUID = 1003L;
	
    @Id
    @Column(name = "CVE_AREA")
    private String clave;
    
    @Column(name = "DES_AREA")
    private String descripcion;
    
    @Column(name = "ESTATUS")
    private char estatus;
	
}
