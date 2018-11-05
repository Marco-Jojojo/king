/*
* ****************************************************
* * Aplicaci&oacute;n Base
* * Versi&oacute;n 1.0.0
* * Secretar&iacute;a de Gobernaci&oacute;n - DGTIC
* ****************************************************
 */
package mx.gob.segob.dgtic.persistence.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Configura y establece la capa de persistencia dentro del contexto de SPRING,
 * delimitando las clases de esta capa al paquete
 * <em>{@code mx.gob.segob.dgtic.persistence}</em>
 *
 * <p>
 * La clase de inicializaci&oacute;n {@link mx.gob.segob.dgtic.ApplicationInit}
 * detectara este componente como una clase de configuraci&oacute;n mediante la
 * anotaci&oacute;n
 * {@link org.springframework.context.annotation.Configuration @Configuration} y
 * por medio de la anotaci&oacute;n
 * {@link org.springframework.context.annotation.ComponentScan @ComponentScan}
 * se establece la ubicaci&oacute;n del paquete que contendr&aacute; las clases
 * a detectar para la capa de persistencia.
 *
 * <p>
 * Se activa el control transaccional para el proceso de persistencia de
 * informaci&oacute;n por medio de SPRING y del uso de la anotaci&oacute;n
 * {@link org.springframework.transaction.annotation.Transactional @Transactional}
 * <p>
 * El acceso a la base de datos se controla por medio de un pool de conexiones
 * configurado desde el servidor de aplicaciones y se accede desde un JNDI
 * Datasource. El nombre del JNDI se puede modifica desde la variable de
 * propiedades de configuraci&oacute;n de la aplicaci&oacute;n
 * ({@code aplicacion.properties} : {@code jndi.datasource})
 *
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.transaction.annotation.EnableTransactionManagement
 */
@Configuration
@ComponentScan(basePackages = {"mx.gob.segob.dgtic.persistence","mx.gob.segob.dgtic.comun.transport.dto"})
@PropertySource("classpath:config/properties/aplicacion.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "mx.gob.segob.dgtic.persistence.repository")
public class PersistenceConfig {

    /**
     * JNDI del pool de conexiones configurado en el servidor de aplicaciones.
     * El nombre de la variable se puede alterar desde las propiedades de
     * configuraci&oacute;n de la aplicaci&oacute;n
     * ({@code aplicacion.properties} : {@code jndi.datasource})
     */
    @Value("${jndi.datasource}")
    private String jndiDataSource;

    @Bean
    public DataSource dataSource() throws NamingException {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        return dsLookup.getDataSource(jndiDataSource);
    }

    @Bean
    public EclipseLinkJpaVendorAdapter jpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter eljva = new EclipseLinkJpaVendorAdapter();
        eljva.setDatabasePlatform("org.eclipse.persistence.platform.database.OraclePlatform");
        eljva.setGenerateDdl(false);
        eljva.setShowSql(true);

        return eljva;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("mx.gob.segob.dgtic.comun.entidad");
        em.setJpaVendorAdapter(jpaVendorAdapter());
        Map<String, String> map = new HashMap<>();
        map.put("eclipselink.weaving", "false");

        em.setJpaPropertyMap(map);
        return em;
    }


    @Bean
    public PlatformTransactionManager transactionManager() throws NamingException {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory().getObject());
        return transactionManager;
    }

    /**
     * Bean requerido para la inspecci&oacute;n de una propiedad mediante la
     * anotacion @Value del archivo properties cargado mediante @PropertySource
     *
     * @return PropertySourcesPlaceholderConfigurer
     *
     * @see org.springframework.context.annotation.Bean
     */
    @Bean("propertiesPersistence")
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
  
}
