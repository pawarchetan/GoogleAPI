package com.loginext.configuration;

import com.loginext.dao.CoordinateDao;
import com.loginext.dao.CoordinateDaoImpl;
import com.loginext.service.CoordinateService;
import com.loginext.service.CoordinateServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.loginext")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@Log4j2
public class SpringConfigurator {

    private static final String PACKAGE_TO_SCAN = "com.loginext.model";
    private static final String JDBC_DRIVER_NAME = "jdbc.driverClassName";
    private static final String JDBC_DRIVER_URL = "jdbc.url";
    private static final String JDBC_DB_USERNAME = "jdbc.username";
    private static final String JDBC_DB_PASSWORD = "jdbc.password";
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        log.info("Configuring the Session factory..........");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGE_TO_SCAN);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        log.info("Configuring the Data source.........");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty(JDBC_DRIVER_NAME));
        dataSource.setUrl(environment.getRequiredProperty(JDBC_DRIVER_URL));
        dataSource.setUsername(environment.getRequiredProperty(JDBC_DB_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(JDBC_DB_PASSWORD));
        return dataSource;
    }

    private Properties hibernateProperties() {
        log.info("Reading the Hibernate properties file........");
        Properties properties = new Properties();
        properties.setProperty(HIBERNATE_DIALECT, environment.getRequiredProperty(HIBERNATE_DIALECT));
        properties.setProperty(HIBERNATE_SHOW_SQL, environment.getRequiredProperty(HIBERNATE_SHOW_SQL));
        properties.setProperty(HIBERNATE_FORMAT_SQL, environment.getRequiredProperty(HIBERNATE_FORMAT_SQL));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        log.info("Configuring the Transaction manager........");
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        log.info("Configuring the View Resolver............");
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".html");
        resolver.setViewClass(JstlView.class);
        log.info("Configuration Successful.....!!!!!!!!");
        return resolver;
    }

    @Bean
    public CoordinateDao coordinateDao() {
        return new CoordinateDaoImpl();
    }

    @Bean
    public CoordinateService coordinateService() {
        return new CoordinateServiceImpl();
    }

}
