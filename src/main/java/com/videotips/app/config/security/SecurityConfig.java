package com.videotips.app.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//:::::::::::::::::::configure will be used to configure users::::::::::::::::::::
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		InMemoryUserDetailsManager userDetails = new InMemoryUserDetailsManager();
		UserDetails user_1 =User.withUsername("alex").password("123").authorities("admin").build();
		UserDetails user_2 =User.withUsername("alex2").password("123").authorities("read").build();
		userDetails.createUser(user_1);
		userDetails.createUser(user_2);
			
		auth.userDetailsService(userDetails);
	}
	
	@Bean
	@SuppressWarnings("deprecation")
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	// :::::::::::::::::::configure will be used configure endpoint security::::::::::::::::::::
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("v1/email/send").authenticated().anyRequest().authenticated()
			.and().formLogin()
			.and().httpBasic();
	}

	/*
	 * :::::::::::::::::::EXMAPLE SIMPLE VALIDATION ::::::::::::::::::::
	 * 
	 * @Override protected void configure (HttpSecurity http)throws Exception{
	 * http.csrf().disable() .authorizeRequests()
	 * .antMatchers("/email/send").authenticated() .anyRequest().authenticated()
	 * .and().formLogin() .and().httpBasic(); }
	 */
}
