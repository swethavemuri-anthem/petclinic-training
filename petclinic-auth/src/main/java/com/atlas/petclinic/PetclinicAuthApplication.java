package com.atlas.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.antheminc.oss.nimbus.domain.bpm.activiti.ActivitiExpressionManager;
import com.antheminc.oss.nimbus.domain.session.HttpSessionProvider;
import com.antheminc.oss.nimbus.domain.session.SessionProvider;
import com.atlas.petclininc.auth.utils.JWTAuthTokenUtil;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication(exclude=ActivitiExpressionManager.class)
public class PetclinicAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetclinicAuthApplication.class, args);
	}
	
	@Bean
	public SessionProvider sessionProvider() { 
		return new HttpSessionProvider();
	} 
	
	@Bean
	public JWTAuthTokenUtil tokenUtil() {
		return new JWTAuthTokenUtil();
	}
}
