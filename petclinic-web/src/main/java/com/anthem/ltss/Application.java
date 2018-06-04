/**
 * 
 */
package com.anthem.ltss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.anthem.ltss.login.LoginController;
import com.antheminc.oss.nimbus.domain.session.HttpSessionProvider;
import com.antheminc.oss.nimbus.domain.session.SessionProvider;

/**
 * @author Jayant Chaudhuri
 *
 */
@Configuration
@SpringBootApplication(scanBasePackageClasses=LoginController.class)
@ComponentScan(basePackages={"com.anthem.ltss","com.anthem.cm.ltss.extension.conf"})
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){ 
		return application.sources(Application.class);
	} 
	public static void main(String[] args) throws Exception { 
		SpringApplication.run(Application.class, args);
	} 
	
	@Bean
	public SessionProvider sessionProvider() { 
		return new HttpSessionProvider();
	} 

}