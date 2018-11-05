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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

import lombok.Data;

import org.eclipse.persistence.annotations.Cache;




@Entity
@Cache(alwaysRefresh = true)
@Data
@Table(name = "M_PROYECTO")
public class Proyecto implements Serializable{
	
	private static final long serialVersionUID = 102L;
	
    @Id
    //@SequenceGenerator(name = "SEQ_PROYECTO", sequenceName = "SEQ_PROYECTO_01", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROYECTO")
    @Column(name = "CVE_PROYECTO")
    private String clave;
    
    @Column(name = "ID_PROYECTO")
    private Integer id;
    
    //FIXME: Tomar en cuenta hasta que la entidad Usuario esté completa (a quien le haya tocado Usuario). Mientras se dejará como String
//    @OneToOne
//    @JoinColumn(name = "CVE_M_USUARIO")
//    private Usuario usuario;
    @Column(name = "CVE_M_USUARIO")
    private String usuario;
    
    @Column(name = "NOM_PROYECTO")
    private String nombre;
    
    @Column(name = "FEC_REGISTRO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaRegistro;
       
    @ManyToOne
    @JoinColumn(name = "CVE_AREA")
    private Area area;
    
    @ManyToOne
    @JoinColumn(name="ID_ESTATUS")
    private Estatus estatus;
    
    @Column(name = "POR_AVANCE")
    private Double porcentajeAvance;
    
    @OneToMany(mappedBy="proyecto", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<DetalleProyecto> detallesProyecto;
    
    public Proyecto() {
    	detallesProyecto = new HashSet<>();
	}
    
    @Override
    public String toString() {
        return "Proyecto [clave=" + clave + ", nombre=" + nombre + "]";
    }
}
