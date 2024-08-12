package com.example.demo.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.securityconfig.UserDetailsSeviceImple;
import com.example.demo.service.PatientService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	public AuthenticationSuccessHandler customsuccessHandler;
	@Bean
	public  UserDetailsService getUserdetailservice()
	{return new  UserDetailsSeviceImple();
	}
	@Bean 
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider getDaoAuthPRovider() {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserdetailservice());
		daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
		
		return daoAuthenticationProvider;
	}
	
	@Bean
		public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		 http.authorizeRequests().requestMatchers("/admin/**").hasRole("ADMIN")
		 .requestMatchers("/user/**").hasRole("USER")
		 .requestMatchers("/patient/**").access("hasRole('ROLE_DOCTOR')")
		 .requestMatchers("/**").permitAll().and().formLogin().loginPage("/signin").loginProcessingUrl("/login")
		 .successHandler(customsuccessHandler).and().csrf().disable();
		 
		return http.build();
		}
	
}
