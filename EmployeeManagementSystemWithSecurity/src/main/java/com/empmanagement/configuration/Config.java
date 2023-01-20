package com.empmanagement.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableAutoConfiguration
public class Config {
	
	@Bean
	public static PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.csrf().disable()
			.authorizeHttpRequests((auth)->auth.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults());
		
		
		return httpSecurity.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails aftabDetails=User.builder()
				.username("Aftab")
				.password(encoder().encode("9935895648"))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(aftabDetails);
				
	}
}
