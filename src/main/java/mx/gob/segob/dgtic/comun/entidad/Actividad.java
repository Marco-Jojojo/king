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
@Table(name = "D_ACTIVIDAD")
public class Actividad implements Serializable {

	private static final long serialVersionUID = 1007L;

	@Id
    //@SequenceGenerator(name = "SEQ_PROYECTO", sequenceName = "SEQ_PROYECTO_01", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROYECTO")
    @Column(name = "ID_ACTIVIDAD")
    private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Getter(AccessLevel.NONE)
    @Excluir
    @JoinColumn(name = "CVE_OBJESP")
	private Objetivo objetivo;
	
	@Column(name = "ACT_DESCRIPCION")
    private String descripcion;
	
	@Lob
	@Column(name = "ARC_ACTIVIDAD")
	@Excluir
    private byte[] archivo;
	
	@Column(name = "FEC_INIACT")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FEC_FINACT")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaFin;
    
    @OneToMany(mappedBy="actividad", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Tarea> tareas;
    
    public Actividad() {
    	tareas = new HashSet<>();
    }
    
    public Objetivo getObjetivo() {
    	return null;
    }
    
    @Override
    public String toString() {
        return "Actividad [id=" + id + ", descripcion=" + descripcion + "]";
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Actividad)) {
			return false;
		}
		Actividad other = (Actividad) obj;
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
		//Estas líneas pueden estar causando un ciclo infinito en JPA
//		if (objetivo == null) {
//			if (other.objetivo != null) {
//				return false;
//			}
//		} else if (!objetivo.equals(other.objetivo)) {
//			return false;
//		}
		return SetUtil.equals(tareas, other.tareas);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result
				+ ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		//Estas líneas pueden estar causando un ciclo infinito en JPA
//		result = prime * result
//				+ ((objetivo == null) ? 0 : objetivo.hashCode());
		result = prime * result + ((tareas == null) ? 0 : tareas.hashCode());
		return result;
	}
    
}
