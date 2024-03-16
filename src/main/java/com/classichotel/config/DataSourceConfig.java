package com.classichotel.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

  @Value("${spring.datasource.url}")
  private String url;

  @Value("${spring.datasource.username}")
  private String userName;

  @Value("${spring.datasource.password}")
  private String password;

  @Value("${spring.datasource.driver-class-name}")
  private String driverClassName;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl(url);
    dataSource.setUsername(userName);
    dataSource.setPassword(password);
    dataSource.setDriverClassName(driverClassName);
    return dataSource;
  }
}
