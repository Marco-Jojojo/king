/*
* ****************************************************
* * Aplicaci&oacute;n Base
* * Versi&oacute;n 1.0.0
* * Secretar&iacute;a de Gobernaci&oacute;n - DGTIC
* ****************************************************
*/
package mx.gob.segob.dgtic.business.service.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Clase base para los componentes service de la capa de negocio.
 */
public abstract class ServiceBase {
    
    /** Intancia para realizar log  */
    protected final Logger logger = LoggerFactory.getLogger(ServiceBase.class);

    /**
     * "Modifica" colecciones en base de datos antes de llamar la persistencia de JPA.
     * @param existenteModelo
     * @param nuevoModelo
     */
    public <T> void copiarValores(Set<T> existenteModelo, Set<T> nuevoModelo) {
    	List<T> copiaExistenteModelo = new ArrayList<>(existenteModelo);
    	for(T bdModelo : copiaExistenteModelo) {
    		boolean existe = false;
    		for(T nuevo : nuevoModelo) {
    			if(bdModelo.equals(nuevo)) {
    				existe = true;
    				break;
    			}
    		}
    		if(!existe) {
    			existenteModelo.remove(bdModelo);
    		}
    	}
    	
    	for(T nuevo : nuevoModelo) {
    		boolean coincide = false;
    		for(T bdModelo : copiaExistenteModelo) {
    			if(nuevo.equals(bdModelo)) {
    				coincide = true;
    				break;
    			}
    		}
    		if(!coincide) {
    			existenteModelo.add(nuevo);
    		}
    	}
    }
}
