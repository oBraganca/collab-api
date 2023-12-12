package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;


@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ApiGatewayApplication.class)
				.web(WebApplicationType.REACTIVE)
				.run(args);;
	}

	@Bean
	public RouteLocator userServiceRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("user-service", r -> r
						.path("/api/**")
						.uri("http://localhost:9002/")
				)
				.build();
	}
}
