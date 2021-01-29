package com.boa.counterpartyapi;
/*
 * mutation{
 createCustomer(input:{
  customerId: 21,
  name: {
    firstName: "Parameswari",
    lastName: "BalaS",
    middleName: " "
  },
  email: "param@gmail.com",
  gender: "FEMALE",
  dob: "1970-12-02",
  addresses:[{
    addressId: 11,
      doorNo: "8d",
      street: "Gandhi St",
      city: "Chenani",
      state: "TN"
  }]
  
  
}){
  email
}
}
GET /graphql?query=query%20aTest(%24arg1%3A%20String!)%20%7B%20test(who%3A%20%24arg1)%20%7D&operationName=aTest&variables=me
http://localhost:6060/graphql/schema.json
http://localhost:6060/graphql?query=%7BfindAllCustomers%7BcustomerId%7D%7D
http://localhost:6060/graphql?query=%7BfindAllCustomers%7BcustomerId%20name%7D%7D
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
//@EnableEurekaClient
public class CounterPartyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CounterPartyApiApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
}
