package com.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JdbcConfig {
   // @Bean
    public DriverManagerDataSource getDriverManager(){
        DriverManagerDataSource dm =new DriverManagerDataSource();
        dm.setPassword("root");
        dm.setUsername("root");
        dm.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dm.setUrl("jdbc:mysql://localhost:3306/employee");
        return dm;
    }
    //@Bean
    public JdbcTemplate getTemplate(){
        JdbcTemplate template =new JdbcTemplate();
        template.setDataSource(getDriverManager());
        return template;
    }

}
