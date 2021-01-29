package com.boa.counterpartyapi.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Endpoint(id="customer")
@Component
public class BOAEndpoint {

	@Autowired
	private ApplicationContext applicationContext;
	/*@ReadOperation
	@Bean
	public String message()
	{
		return "Critical Message from BOA Service";
	}*/
	
	@ReadOperation
	@Bean
	public String[] getAllRegBeanNames()
	{
		
		return applicationContext.getBeanDefinitionNames();

       
	}
	
}
