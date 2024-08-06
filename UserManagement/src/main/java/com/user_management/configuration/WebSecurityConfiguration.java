package com.user_management.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
import static org.springframework.security.config.Customizer.withDefaults;
 
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {
 
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
 
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
 
//	@Autowired
//	private UserDetailsService jwtService;
 
 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors(withDefaults());
		httpSecurity.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
						.requestMatchers("/authenticate", "/registerNewUser").permitAll()
						.requestMatchers(HttpHeaders.ALLOW).permitAll().anyRequest().authenticated())
				.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/errors/access-denied")
						.authenticationEntryPoint(jwtAuthenticationEntryPoint))
				.sessionManagement((sessionManagement) -> sessionManagement
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

 
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
 
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
//    }
 
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}