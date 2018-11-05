package mx.gob.segob.dgtic.comun.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "D_PRESUPUESTO")
public class Presupuesto implements Serializable {

	private static final long serialVersionUID = 1010L;

	//FIXME: Hace falta un ID para esta tabla
//	@Id
//    //@SequenceGenerator(name = "SEQ_PROYECTO", sequenceName = "SEQ_PROYECTO_01", allocationSize = 1)
//    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROYECTO")
//    @Column(name = "CVE_PRESUPUESTO")
//    private Integer clave;
	
//	@ManyToOne
//  @JoinColumn(name="CVE_PROYECTO")
	@Id
	@Column(name = "CVE_PROYECTO")
    private String claveProyecto; //FIXME: Checar si ésta debe ser referencia a Proyecto
	
	//FIXME: ¿Tendrá relación muchos a uno con el D_PROYECTO?
	
	@Column(name = "PRY_PRESUPUESTO")
    private String proyecto;
	
	@Column(name = "DES_PRESUPUESTO")
    private String descripcion;
	
	@Column(name = "AUT_PRESUPUESTO")
    private String aut;
	
	@Lob
	@Column(name = "PRY_EVIDENCIA")
    private byte[] evidencia;
}
