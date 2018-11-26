package com.anthem.client.extension.petclinicgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
//@EnableHystrixDashboard
public class PetclinicGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetclinicGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("vet", p -> p.path("/petclinic/client/org/app/p/vet*/**").or().path("/petclinic/client/org/app/p/petclinicdashboard/**")
						.or()
						.path("/petclinic/login")
						.filters(f -> f.hystrix(c -> c.setFallbackUri("forward:/fallback")))
						.uri("lb://vet"))
				.route("owners", p -> p.path("/petclinic/client/org/app/p/owner*/**").or().path("/petclinic/client/org/app/p/visit*/**")
						.or().path("/petclinic/client/org/app/p/petview*/**")
						.filters(f-> f.hystrix(c-> c.setName("owner").setFallbackUri("forward:/fallback")))
						.uri("lb://owner"))
				.route("eureka-client",p->p.path("/test/**")
						.filters(f -> f.hystrix(c -> c.setFallbackUri("forward:/fallback2")))
						.uri("http://localhost:8086"))				
						//.uri("lb://petclinic-owner"))
				.build();
	}
	
	@RequestMapping(value="/fallback")
	public String fallback() {
		return "Application Nimbus is currently down, please retry later.... ";
	}
	
	@RequestMapping(value="/fallback2")
	public String fallback2() {
		return "Fallback 2 ";
	}
}
