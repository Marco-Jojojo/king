package mx.gob.segob.dgtic.comun.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import mx.gob.segob.dgtic.comun.util.SetUtil;
import mx.gob.segob.dgtic.webservices.util.Excluir;

@Entity
@Data
@Table(name = "D_OBJESP")
public class Objetivo implements Serializable {

	private static final long serialVersionUID = 1006L;
	
	@Id
    //@SequenceGenerator(name = "SEQ_PROYECTO", sequenceName = "SEQ_PROYECTO_01", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROYECTO")
    @Column(name = "CVE_OBJESP")
    private Integer clave;
	
	@Column(name = "ID_DOBJESP")
    private Integer idDObjetivo;
	
//	@ManyToOne
//  @JoinColumn(name="CVE_PROYECTO")
	@Column(name = "CVE_PROYECTO")
    private String claveProyecto; //FIXME: Checar si ésta debe ser referencia a Proyecto
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Getter(AccessLevel.NONE)
    @Excluir
    @JoinColumn(name = "ID_DPROYECTO")
	private DetalleProyecto detalleProyecto;
	
	@Column(name = "DES_OBJESP")
    private String descripcion;
	
	@Lob
	@Column(name = "ARC_OBJESP")
    private byte[] archivo;
	
	@Column(name = "FEC_INIOBJESP")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FEC_FINOBJESP")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaFin;
    
    @OneToMany(mappedBy="objetivo", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Actividad> actividades;
    
    public Objetivo() {
    	actividades = new HashSet<>();
    }
    
    public DetalleProyecto getDetalleProyecto() {
    	return null;
    }
    
    @Override
    public String toString() {
        return "Objetivo [clave=" + clave + ", descripcion=" + descripcion + "]";
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Objetivo)) {
			return false;
		}
		Objetivo other = (Objetivo) obj;
		if (clave == null) {
			if (other.clave != null) {
				return false;
			}
		} else if (!clave.equals(other.clave)) {
			return false;
		}
		if (claveProyecto == null) {
			if (other.claveProyecto != null) {
				return false;
			}
		} else if (!claveProyecto.equals(other.claveProyecto)) {
			return false;
		}
		if (descripcion == null) {
			if (other.descripcion != null) {
				return false;
			}
		} else if (!descripcion.equals(other.descripcion)) {
			return false;
		}
		//Estas líneas pueden estar causando un ciclo infinito en JPA
//		if (detalleProyecto == null) {
//			if (other.detalleProyecto != null) {
//				return false;
//			}
//		} else if (!detalleProyecto.equals(other.detalleProyecto)) {
//			return false;
//		}
		if (fechaFin == null) {
			if (other.fechaFin != null) {
				return false;
			}
		} else if (!fechaFin.equals(other.fechaFin)) {
			return false;
		}
		if (fechaInicio == null) {
			if (other.fechaInicio != null) {
				return false;
			}
		} else if (!fechaInicio.equals(other.fechaInicio)) {
			return false;
		}
		if (idDObjetivo == null) {
			if (other.idDObjetivo != null) {
				return false;
			}
		} else if (!idDObjetivo.equals(other.idDObjetivo)) {
			return false;
		}
		return SetUtil.equals(actividades, other.actividades);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result
				+ ((claveProyecto == null) ? 0 : claveProyecto.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		//Estas líneas pueden estar causando un ciclo infinito en JPA
//		result = prime * result
//				+ ((detalleProyecto == null) ? 0 : detalleProyecto.hashCode());
		result = prime * result
				+ ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result
				+ ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result
				+ ((idDObjetivo == null) ? 0 : idDObjetivo.hashCode());
		result = prime * result + ((actividades == null) ? 0 : actividades.hashCode());
		return result;
	}
    
    
}
