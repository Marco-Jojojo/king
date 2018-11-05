package mx.gob.segob.dgtic.comun.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "C_PARAMETRO")
public class Parametro implements Serializable {

	private static final long serialVersionUID = 133L;
	
	@Id
    @SequenceGenerator(name = "SEQ_PARAMETRO_01", sequenceName = "SEQ_PARAMETRO_01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PARAMETRO_01")
    @Column(name = "ID_PARAMETRO")
    private Integer idParametro;
	
	@Column(name = "DES_PARAMETRO")
	private String parametro;
	
	@Column(name = "VAL_PARAMETRO")
	private String valParametro;
	
}
