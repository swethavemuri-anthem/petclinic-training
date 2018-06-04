/**
 * 
 */
package com.anthem.ltss;

import com.anthem.cm.ltss.extension.web.ClientUserDetailsService;
import com.anthem.cm.ltss.extension.web.SiteminderPreAuthFilter;
import com.anthem.ltss.login.LogoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * @author Jayant Chaudhuri
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${serviceUserName}")
	private String serviceUserName;

	@Value("${servicePassword}")
	private String servicePassword;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login", "/*.js", "/*.css", "/*.htm*", "/*.ico*").permitAll()
				.and()
				.authorizeRequests()
				.antMatchers( "**/anthem/services/p/**").authenticated()
				.and()
				.httpBasic()
				.and()
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login")
				.and().logout().invalidateHttpSession(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessHandler(logoutHandler()).logoutSuccessUrl("/login").permitAll().and()
				.addFilterBefore(siteminderFilter(), RequestHeaderAuthenticationFilter.class)
				.csrf().disable();
	}


	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/*.png");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(preauthAuthProvider());

		//For service account auth
		auth.inMemoryAuthentication()
				.withUser(serviceUserName)
				.password(servicePassword)
				.roles("SERVICE");
	}


	public SiteminderPreAuthFilter siteminderFilter() throws Exception {
		SiteminderPreAuthFilter filter = new SiteminderPreAuthFilter();
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}

	@Bean
	public LogoutHandler logoutHandler() {
		return new LogoutHandler();
	}


	@Bean
	public PreAuthenticatedAuthenticationProvider preauthAuthProvider() {
		PreAuthenticatedAuthenticationProvider preauthAuthProvider = new PreAuthenticatedAuthenticationProvider();
		preauthAuthProvider.setPreAuthenticatedUserDetailsService(userDetailsServiceWrapper());
		return preauthAuthProvider;
	}

	@Bean
	public UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsServiceWrapper() {
		UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper =
				new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>();
		wrapper.setUserDetailsService(clientUserDetailsService());
		return wrapper;
	}

	@Bean
	public ClientUserDetailsService clientUserDetailsService() {
		ClientUserDetailsService clientUserDetailsService = new ClientUserDetailsService();
		return clientUserDetailsService;
	}


}