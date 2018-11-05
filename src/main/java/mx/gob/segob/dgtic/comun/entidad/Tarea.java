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
@Table(name = "D_TAREA")
public class Tarea implements Serializable {

	private static final long serialVersionUID = 1008L;

	@Id
    //@SequenceGenerator(name = "SEQ_PROYECTO", sequenceName = "SEQ_PROYECTO_01", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROYECTO")
    @Column(name = "ID_TAREA")
    private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Getter(AccessLevel.NONE)
    @Excluir
    @JoinColumn(name = "ID_ACTIVIDAD")
	private Actividad actividad;
	
	@Column(name = "DES_TAREA")
    private String descripcion;
	
	@Lob
	@Column(name = "ARC_TAREA")
    private byte[] archivo;
	
	@Column(name = "FEC_INITAR")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FEC_FINTAR")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaFin;
    
    @OneToMany(mappedBy="tarea", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<SubTarea> subTareas;
    
    public Tarea() {
    	subTareas = new HashSet<>();
    }
    
    public Actividad getActividad() {
    	return null;
    }
    
    @Override
    public String toString() {
        return "Tarea [id=" + id + ", descripcion=" + descripcion + "]";
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Tarea)) {
			return false;
		}
		Tarea other = (Tarea) obj;
		//Estas líneas pueden estar causando un ciclo infinito en JPA
//		if (actividad == null) {
//			if (other.actividad != null) {
//				return false;
//			}
//		} else if (!actividad.equals(other.actividad)) {
//			return false;
//		}
		if (descripcion == null) {
			if (other.descripcion != null) {
				return false;
			}
		} else if (!descripcion.equals(other.descripcion)) {
			return false;
		}
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
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return SetUtil.equals(subTareas, other.subTareas);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//Estas líneas pueden estar causando un ciclo infinito en JPA
//		result = prime * result
//				+ ((actividad == null) ? 0 : actividad.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result
				+ ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((subTareas == null) ? 0 : subTareas.hashCode());
		return result;
	}
    
}

