package local.uniclog.app.sqlite.config;

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
        basePackages = "local.uniclog.app.sqlite",
        entityManagerFactoryRef = "sQLiteDBEntityManager",
        transactionManagerRef = "sQLiteDBTransactionManager"
)
@RequiredArgsConstructor
public class SQLiteDataSourceConfiguration {
    private final Environment environment;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean sQLiteDBEntityManager() {
        var entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(sQLiteDataSource());
        entityManager.setPackagesToScan("local.uniclog.app.sqlite");

        var vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                environment.getProperty("sqlite.spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect",
                environment.getProperty("sqlite.spring.jpa.database-platform"));
        properties.put("generate-ddl",
                environment.getProperty("sqlite.spring.jpa.generate-ddl"));
        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }

    @Primary
    @Bean
    public DataSource sQLiteDataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                Objects.requireNonNull(
                        environment.getProperty("sqlite.spring.datasource.driver-class-name")));
        dataSource.setUrl(environment.getProperty("sqlite.spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("sqlite.spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("sqlite.spring.datasource.password"));
        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager sQLiteDBTransactionManager() {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(sQLiteDBEntityManager().getObject());
        return transactionManager;
    }
}
