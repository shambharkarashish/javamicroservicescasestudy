package com.virtusa.ecommerce.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
@EnableConfigurationProperties(VaultConfiguration.class)
public class DbConfiguration {
	
	@Value("${inventory_db_url}")	
	private String dbUrl;
	@Value("${db_driver}")
	private String driverName;
	
	private VaultConfiguration vaultConfiguration;
	
	private DataSourceBuilder dataSourceBuilder;
	
	public DbConfiguration(VaultConfiguration vaultConfiguration)
	{
		this.vaultConfiguration=vaultConfiguration;
	}
	
	@Bean
	public DataSource getDataSource()
	{
		dataSourceBuilder=DataSourceBuilder.create();
		dataSourceBuilder.url(dbUrl);
		dataSourceBuilder.driverClassName(driverName);
		dataSourceBuilder.username(this.vaultConfiguration.getUsername());
		dataSourceBuilder.password(this.vaultConfiguration.getPassword());
		return dataSourceBuilder.build();
		
	}
	
	

}
