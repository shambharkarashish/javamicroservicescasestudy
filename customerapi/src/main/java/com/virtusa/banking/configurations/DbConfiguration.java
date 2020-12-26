package com.virtusa.banking.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Data
@Slf4j
@EnableConfigurationProperties(VaultConfiguration.class)
public class DbConfiguration {
    @Value("${db_url}")
	private String url;    
    @Value("${db_driver}")
	private String driver;
    
    @Value("${db_username}")
    private String username;
    @Value("${db_password}")
    private String password;
    
    @Value("${message}")
    private String message;
    private DataSourceBuilder dataSourceBuilder;
    private final VaultConfiguration vaultConfiguration;
    
    public DbConfiguration(VaultConfiguration vaultConfig)
    {
    	this.vaultConfiguration=vaultConfig;
    }
    
    @Bean
    //@Profile("prod")
    public DataSource getDataSource()
    {
    	log.info("Message from Property file"+message);
    	//log.info("Entering Production Env.....");
    	log.info("User Name..."+username);
    	log.info("Password..."+password);
    	dataSourceBuilder=DataSourceBuilder.create();
    	dataSourceBuilder.url(url);
    	dataSourceBuilder.username(username);
    	dataSourceBuilder.password(password);
    	dataSourceBuilder.driverClassName(driver);
    	return dataSourceBuilder.build();
   	
    }
    
	
	
}
