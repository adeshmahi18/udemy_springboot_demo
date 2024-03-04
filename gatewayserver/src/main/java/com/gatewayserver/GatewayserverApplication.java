package com.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableAutoConfiguration
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator eazyBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/rcs/employee/**")
						.filters( f -> f.rewritePath("/rcs/employee/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								//.retry(retryConfig -> retryConfig.setRetries(2).setMethods(HttpMethod.GET)
										//.setBackoff(Duration.ofSeconds(100), Duration.ofMillis(1000),2, true))
								.circuitBreaker(config -> config.setName("employeeCircuitBreaker"))	)
						.uri("lb://EMPLOYEE"))
				.route(p -> p
						.path("/rcs/department/**")
						.filters( f -> f.rewritePath("/rcs/employee/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.circuitBreaker(config -> config.setName("departmentCircuitBreaker")
										.setFallbackUri("forward:/contactSupport")))
						.uri("lb://DEPARTMENT"))
						.build();


	}
}
