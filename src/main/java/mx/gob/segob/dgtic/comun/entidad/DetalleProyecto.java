package mx.gob.segob.dgtic.comun.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

import lombok.Data;
import lombok.Getter;
import lombok.AccessLevel;
import mx.gob.segob.dgtic.comun.util.SetUtil;
import mx.gob.segob.dgtic.webservices.util.Excluir;

import org.eclipse.persistence.annotations.Cache;

@Entity
@Cache(alwaysRefresh = true)
@Data
@Table(name = "D_PROYECTO")
public class DetalleProyecto implements Serializable{
	
	private static final long serialVersionUID = 1005L;
	
    @Id
  //@SequenceGenerator(name = "SEQ_PROYECTO", sequenceName = "SEQ_PROYECTO_01", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROYECTO")
    @Column(name = "ID_DPROYECTO")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="CVE_PROYECTO")
    @Getter(AccessLevel.NONE)
    @Excluir
    private Proyecto proyecto;
    @Column(name = "ID_PROYECTO")
    private Integer idProyecto;
    
    @ManyToOne
    @JoinColumn(name="ID_CLASPROY")
    private Clasificacion clasificacion;
           
    @Column(name = "NOM_OAD")
    private String nombreOAD;
    
    @ManyToOne
    @JoinColumn(name = "CVE_AREA")
    private Area area;
    
    @Column(name = "DES_PROYECTO")
    private String desProyecto;
    
    @Column(name = "OBJ_GENERAL")
    private String objetivoProyecto;
    
    @Column(name = "FEC_INICIO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FEC_FIN")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaFin;
    
    @Column(name = "DUR_PLAN")
    private Integer durPlan;
    
    @Column(name = "PRE_PLAN")
    private Integer prePlan;
    
    @Column(name = "RES_PROYECTO")
    private String resProyecto;
    @Column(name = "NOM_GESTOR")
    private String nombreGestor;
    @Column(name = "NOM_LIDER")
    private String nombreLider;
    @Column(name = "NOM_INVOLUCRADO")
    private String nombreInvolucrado;
    @Column(name = "OBS_PROYECTO")
    private String obsProyecto;
    
    @Column(name = "FEC_REGISTRO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FEC_CIERRE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaCierre;
    
    @OneToMany(mappedBy="detalleProyecto", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Objetivo> objetivos;
    
    //FIXME: Se debe hacer referencia a DetalleProyecto en Presupuesto (bidireccional). Falla al no encontrar la relación.
//    @OneToMany(cascade=CascadeType.ALL)
//    private Set<Presupuesto> presupuestos = new HashSet<>();
    
    public DetalleProyecto() {
    	objetivos = new HashSet<>();
    }
    
    public Proyecto getProyecto() {
    	return null;
    }
    
    @Override
    public String toString() {
        return "DetalleProyecto [id=" + id + ", descripcion=" + desProyecto + "]";
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DetalleProyecto)) {
			return false;
		}
		DetalleProyecto other = (DetalleProyecto) obj;
		if (desProyecto == null) {
			if (other.desProyecto != null) {
				return false;
			}
		} else if (!desProyecto.equals(other.desProyecto)) {
			return false;
		}
		if (durPlan == null) {
			if (other.durPlan != null) {
				return false;
			}
		} else if (!durPlan.equals(other.durPlan)) {
			return false;
		}
		if (fechaCierre == null) {
			if (other.fechaCierre != null) {
				return false;
			}
		} else if (!fechaCierre.equals(other.fechaCierre)) {
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
		if (fechaRegistro == null) {
			if (other.fechaRegistro != null) {
				return false;
			}
		} else if (!fechaRegistro.equals(other.fechaRegistro)) {
			return false;
		}
		if (idProyecto == null) {
			if (other.idProyecto != null) {
				return false;
			}
		} else if (!idProyecto.equals(other.idProyecto)) {
			return false;
		}
		if (nombreGestor == null) {
			if (other.nombreGestor != null) {
				return false;
			}
		} else if (!nombreGestor.equals(other.nombreGestor)) {
			return false;
		}
		if (nombreInvolucrado == null) {
			if (other.nombreInvolucrado != null) {
				return false;
			}
		} else if (!nombreInvolucrado.equals(other.nombreInvolucrado)) {
			return false;
		}
		if (nombreLider == null) {
			if (other.nombreLider != null) {
				return false;
			}
		} else if (!nombreLider.equals(other.nombreLider)) {
			return false;
		}
		if (nombreOAD == null) {
			if (other.nombreOAD != null) {
				return false;
			}
		} else if (!nombreOAD.equals(other.nombreOAD)) {
			return false;
		}
		if (objetivoProyecto == null) {
			if (other.objetivoProyecto != null) {
				return false;
			}
		} else if (!objetivoProyecto.equals(other.objetivoProyecto)) {
			return false;
		}
		if (obsProyecto == null) {
			if (other.obsProyecto != null) {
				return false;
			}
		} else if (!obsProyecto.equals(other.obsProyecto)) {
			return false;
		}
		if (prePlan == null) {
			if (other.prePlan != null) {
				return false;
			}
		} else if (!prePlan.equals(other.prePlan)) {
			return false;
		}
		if (resProyecto == null) {
			if (other.resProyecto != null) {
				return false;
			}
		} else if (!resProyecto.equals(other.resProyecto)) {
			return false;
		}
		return SetUtil.equals(objetivos, other.objetivos);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((desProyecto == null) ? 0 : desProyecto.hashCode());
		result = prime * result + ((durPlan == null) ? 0 : durPlan.hashCode());
		result = prime * result
				+ ((fechaCierre == null) ? 0 : fechaCierre.hashCode());
		result = prime * result
				+ ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result
				+ ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result
				+ ((fechaRegistro == null) ? 0 : fechaRegistro.hashCode());
		result = prime * result
				+ ((idProyecto == null) ? 0 : idProyecto.hashCode());
		result = prime * result
				+ ((nombreGestor == null) ? 0 : nombreGestor.hashCode());
		result = prime
				* result
				+ ((nombreInvolucrado == null) ? 0 : nombreInvolucrado
						.hashCode());
		result = prime * result
				+ ((nombreLider == null) ? 0 : nombreLider.hashCode());
		result = prime * result
				+ ((nombreOAD == null) ? 0 : nombreOAD.hashCode());
		result = prime
				* result
				+ ((objetivoProyecto == null) ? 0 : objetivoProyecto.hashCode());
		result = prime * result
				+ ((obsProyecto == null) ? 0 : obsProyecto.hashCode());
		result = prime * result + ((prePlan == null) ? 0 : prePlan.hashCode());
		result = prime * result
				+ ((resProyecto == null) ? 0 : resProyecto.hashCode());
		result = prime * result + ((objetivos == null) ? 0 : objetivos.hashCode());
		return result;
	}
    
    
}