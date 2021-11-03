package com.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
public class HibernateConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan("com.pojos");
        factoryBean.setDataSource(dataSource());
        factoryBean.setHibernateProperties(getHibernateProperties());
        return factoryBean;
    }

    private Properties getHibernateProperties() {
        Properties props = new Properties();
        props.put(DIALECT, env.getRequiredProperty("hibernate.dialect"));
        props.put(SHOW_SQL, env.getRequiredProperty("hibernate.showSql"));
        props.put(HBM2DDL_AUTO, env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        props.put(FORMAT_SQL, env.getRequiredProperty("hibernate.formatSql"));
        return props;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getRequiredProperty("hibernate.connection.driverClass"));
        dataSource.setUrl(env.getRequiredProperty("hibernate.connection.url"));
        dataSource.setUsername(
                env.getRequiredProperty("hibernate.connection.username"));
        dataSource.setPassword(
                env.getRequiredProperty("hibernate.connection.password"));
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager =
                new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}