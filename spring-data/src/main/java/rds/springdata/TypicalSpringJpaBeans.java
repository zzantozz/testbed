package rds.springdata;

import org.h2.Driver;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
* Created with IntelliJ IDEA.
* User: ryan
* Date: 2/25/13
* Time: 8:46 PM
* To change this template use File | Settings | File Templates.
*/
@Configurable
public class TypicalSpringJpaBeans {
    @Bean
    public DataSource dataSource() {
        return new SimpleDriverDataSource(new Driver(), "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1", "sa", "");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPersistenceProvider(new HibernatePersistence());
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("rds.springdata");
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
