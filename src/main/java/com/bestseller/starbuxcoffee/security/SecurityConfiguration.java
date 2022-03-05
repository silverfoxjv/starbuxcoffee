package com.bestseller.starbuxcoffee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bestseller.starbuxcoffee.security.repository.UserRepository;
import com.bestseller.starbuxcoffee.security.service.AuthenticationService;
import com.bestseller.starbuxcoffee.security.token.TokenAuthenticationFilter;
import com.bestseller.starbuxcoffee.security.token.TokenService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository repository;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.authenticationService).passwordEncoder(new BCryptPasswordEncoder(10));
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()//
				.antMatchers(HttpMethod.POST, "/auth/**").permitAll()//
				.antMatchers("/auth/test/**").permitAll()//
				.antMatchers(HttpMethod.GET, "/actuator/health").permitAll()//
				.anyRequest().authenticated()//
				.and()//
				.csrf().disable()//
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//
				.and()//
				.addFilterBefore(//
						new TokenAuthenticationFilter(this.tokenService, this.repository),
						UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(final WebSecurity web) throws Exception {
		super.configure(web);
	}

}