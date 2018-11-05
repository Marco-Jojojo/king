package mx.gob.segob.dgtic.comun.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "M_USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1002L;
	
	@Id
    //@SequenceGenerator(name = "SEQ_PROYECTO", sequenceName = "SEQ_PROYECTO_01", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROYECTO")
    @Column(name = "CVE_M_USUARIO")
    private String clave;
	
	@Column(name = "PASSWORD")
	private String password;
}
