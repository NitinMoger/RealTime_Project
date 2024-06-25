package com.xworkz.finalProject.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:DatabaseProperties.properties")
public class DatabaseConfiguration {

    @Value("${jdbc.DriverClassName}")
    private String databaseDriver;

    @Value("${jdbc.url}")
    private String databaseUrl;

    @Value("${jdbc.userName}")
    private String databaseUserName;

    @Value("${jdbc.password}")
    private String databasePassword;

    public DatabaseConfiguration(){
        System.out.println("DatabaseConfiguration Is work successfully");
    }

    @Bean
    public DataSource dataSource(){
        System.out.println("Datasource is successfully establish database connections");
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(databaseDriver);
        ds.setUrl(databaseUrl);
        ds.setUsername(databaseUserName);
        ds.setPassword(databasePassword);

        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(){

        System.out.println("LocalContainerEntityManagerFactoryBean is used to provide the EntityManagerFactory");
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean=new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.xworkz.finalProject");
        JpaVendorAdapter vendorAdapter=new HibernateJpaVendorAdapter();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        return localContainerEntityManagerFactoryBean;
    }
}

