package com.marwanmakm.boto.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.marwanmakm.boto.dao"})
public class DataSourceConfig {

  @Bean
  @Qualifier("datasource")
  @Profile("prod")
  public DataSource getProdDataSource(
      final @Value("${dbuser}") String user, final @Value("${dbpassword}") String password) {
    return DataSourceBuilder.create()
        .url("jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:5432/postgres")
        .username(user)
        .password(password)
        .build();
  }

  @Bean
  @Qualifier("datasource")
  @Profile("local")
  public DataSource getLocalDataSource(
      final @Value("${spring.datasource.host}") String host,
      final @Value("${spring.datasource.port}") String port,
      final @Value("${spring.datasource.db}") String db,
      final @Value("${spring.datasource.username}") String user,
      final @Value("${spring.datasource.password}") String password) {
    return DataSourceBuilder.create()
        .url(String.format("jdbc:postgresql://%s:%s/%s?serverTimezone=UTC", host, port, db))
        .username(user)
        .password(password)
        .build();
  }

  @Bean
  @Qualifier("datasource")
  @Profile("integration_test")
  public DataSource getTestDataSource() {
    return DataSourceBuilder.create()
        .driverClassName("org.h2.Driver")
        .url("jdbc:h2:mem:testdb")
        .username("sa")
        .build();
  }
}
