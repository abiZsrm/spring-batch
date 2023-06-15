package com.customerBatch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db/datasource.properties")
public class JdbcConfig
{
    @Value("${db.driverClassName}")
    private String m_driverClassName;

    @Value("${db.url}")
    private String m_url;

    @Value("${db.username}")
    private String m_username;

    @Value("${db.password}")
    private String m_password;

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(m_driverClassName);
        dataSource.setUrl(m_url);
        dataSource.setUsername(m_username);
        dataSource.setPassword(m_password);

        return dataSource;
    }

    @Bean
    public TransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}