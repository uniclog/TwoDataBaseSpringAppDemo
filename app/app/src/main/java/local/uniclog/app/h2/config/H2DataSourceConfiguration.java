package local.uniclog.app.h2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@PropertySource({"classpath:multiple-db.properties"})
@EnableJpaRepositories(
        basePackages = "local.uniclog.app.h2",
        entityManagerFactoryRef = "h2DBEntityManager",
        transactionManagerRef = "h2DBTransactionManager"
)
@RequiredArgsConstructor
public class H2DataSourceConfiguration {
    private final Environment environment;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean h2DBEntityManager() {
        var entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(h2DataSource());
        entityManager.setPackagesToScan("local.uniclog.app.h2");

        var vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                environment.getProperty("h2.spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect",
                environment.getProperty("h2.spring.jpa.database-platform"));
        properties.put("generate-ddl",
                environment.getProperty("h2.spring.jpa.generate-ddl"));
        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }

    @Primary
    @Bean
    public DataSource h2DataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                Objects.requireNonNull(
                        environment.getProperty("h2.spring.datasource.driver-class-name")));
        dataSource.setUrl(environment.getProperty("h2.spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("h2.spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("h2.spring.datasource.password"));
        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager h2DBTransactionManager() {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(h2DBEntityManager().getObject());
        return transactionManager;
    }
}
