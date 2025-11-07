package com.example.EmsBackendApplication;

import com.example.EmsBackendApplication.JuitTesting.VivoEntity;
import com.example.EmsBackendApplication.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Iterator;
import java.util.Map;

@SpringBootApplication
//@EnableScheduling
public class EmsBackendApplication {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(EmsBackendApplication.class, args);
//		jsonData();
	}
	@Bean
	public RestTemplate template()
	{
		return new RestTemplate();
	}
//	public static void jsonData() throws JsonProcessingException {
//		String json = """
//{
//  "id": 101,
//  "name": "Neeraj",
//  "skills": ["Java", "Spring Boot", "MySQL"],
//  "address": { "city": "Gurugram", "state": "Haryana" }
//}
//""";
//
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode rootNode = mapper.readTree(json);
//		System.out.println(rootNode.path("address").path("cit"));
//
//		System.out.println(rootNode);
//		Iterator<Map.Entry<String, JsonNode>> field = rootNode.fields();
//		while (field.hasNext())
//		{
//			Map.Entry<String, JsonNode> entry = field.next();
//			System.out.println(entry.getKey()+":" + entry.getValue());
//		}
//
//		Employee employee = new Employee(190l, "Ravi", "Sharma", "a@gmail.com");
//		JsonNode node = mapper.valueToTree(employee);
//		System.out.println(node);
//
//		Employee employee1 =  mapper.treeToValue(node, Employee.class);
//		System.out.println(employee1);
//
//
//
//	}


//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/api/**")
//						.allowedOrigins("http://localhost:5173")
//						.allowedMethods("GET", "POST", "PUT", "DELETE");
//			}
//		};
//	}
}
