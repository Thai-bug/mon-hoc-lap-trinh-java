/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.configs;

import java.util.Properties;
import javax.ejb.TransactionManagement;
import javax.sql.DataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 *
 * @author Admin
 */
@Configuration
@PropertySource("classpath:database.properties")
public class HibernateConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setHibernateProperties(getProps());
        factory.setPackagesToScan("com.pojos");

        return factory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("hibernate.connection.driverClass"));
        dataSource.setUrl(env.getProperty("hibernate.connection.url"));
        dataSource.setUsername(
                env.getProperty("hibernate.connection.username"));
        dataSource.setPassword(
                env.getProperty("hibernate.connection.password"));
        
        return dataSource;
    }

    public Properties getProps() {
        Properties props = new Properties();
        props.setProperty(AvailableSettings.DIALECT, env.getProperty("hibernate.dialect"));
        props.setProperty(AvailableSettings.SHOW_SQL, env.getProperty("hibernate.showSql"));
        props.setProperty(AvailableSettings.HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        return props;
    }
    
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager trans = new HibernateTransactionManager();
        trans.setSessionFactory(this.getSessionFactory().getObject());
        
        return trans;
    }
}
