package com.mycompany.securitydemo.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mycompany.securitydemo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfiguration {

    //set up the variable that holds Environment (properties analog)
    @Autowired
    private Environment environment;

    //setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    //define a bean for viewResolver
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    //define bean for security data
    @Bean
    public DataSource securityDataSource()  {
        //create connection pool
        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        //set JDBC driver
        try {
            securityDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        //log the connection properties
        logger.info(">>>jdbc.url=" + environment.getProperty("jdbc.url"));
        logger.info(">>>jdbc.user=" + environment.getProperty("jdbc.user"));

        //set DB connection properties
        securityDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        securityDataSource.setUser(environment.getProperty("jdbc.user"));
        securityDataSource.setPassword(environment.getProperty("jdbc.password"));

        //set connection pool properties
        securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
        return securityDataSource;

    }

    //helping method
    private int getIntProperty(String s)    {
        String propValue = environment.getProperty(s);
        return Integer.parseInt(propValue);
    }

}
