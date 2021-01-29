package com.boa.counterpartyapi.fetchers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.boa.counterpartyapi.models.Customer;
import com.boa.counterpartyapi.models.FullName;
import com.boa.counterpartyapi.models.Gender;

import graphql.schema.DataFetcher;

@Component
public class GraphQLDataFetcher {
	private final String REST_URL = "http://localhost:6060/";
	@Autowired
	private RestTemplate restTemplate;
	public DataFetcher<List<Customer>> getCustomerList() {
		return dataFetchingEnvironment -> {
			return restTemplate
					.exchange(REST_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {
					}).getBody();
		};
	}
	public DataFetcher<Customer> getCustomerById() {
		return dataFetchingEnvironment -> {
			String id = dataFetchingEnvironment.getArgument("id");
			return restTemplate.getForObject(REST_URL + "/" + id, Customer.class);
		};
	}
	public DataFetcher<String> addCustomer() {
		return dataFetchingEnvironment -> {
			String firstName = dataFetchingEnvironment.getArgument("firstName");
			String lastName = dataFetchingEnvironment.getArgument("lastName");
			String email = dataFetchingEnvironment.getArgument("email");
			long mobileNo = dataFetchingEnvironment.getArgument("mobileNo");
			String dob = dataFetchingEnvironment.getArgument("dob");
			String gender = dataFetchingEnvironment.getArgument("gender");
			Customer customer = new Customer();
			customer.setName(new FullName(firstName,lastName,""));
			customer.setEmail(email);
	        customer.setGender(Gender.valueOf(gender));
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		       
	        //convert String to LocalDate
	       // LocalDate localDate = LocalDate.parse(dob, formatter);
	        customer.setDob(dob);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);
			return restTemplate.postForObject(REST_URL, entity, String.class);
		};
	}
	/*
	 * public DataFetcher<String> updateWebsite() { return dataFetchingEnvironment
	 * -> { String id = dataFetchingEnvironment.getArgument("id"); String name =
	 * dataFetchingEnvironment.getArgument("name"); String url =
	 * dataFetchingEnvironment.getArgument("url"); Website website = new
	 * Website(Integer.parseInt(id), name, url); HttpHeaders headers = new
	 * HttpHeaders(); headers.setContentType(MediaType.APPLICATION_JSON);
	 * HttpEntity<Website> entity = new HttpEntity<>(website, headers); return
	 * restTemplate.exchange(REST_URL, HttpMethod.PUT, entity, new
	 * ParameterizedTypeReference<String>() { }).getBody(); }; } public
	 * DataFetcher<String> deleteWebsite() { return dataFetchingEnvironment -> {
	 * String id = dataFetchingEnvironment.getArgument("id"); Website website = new
	 * Website(); website.setId(Integer.parseInt(id)); HttpHeaders headers = new
	 * HttpHeaders(); headers.setContentType(MediaType.APPLICATION_JSON);
	 * HttpEntity<Website> entity = new HttpEntity<>(website, headers); return
	 * restTemplate.exchange(REST_URL, HttpMethod.DELETE, entity, new
	 * ParameterizedTypeReference<String>() { }).getBody(); }; }
	 */
}
