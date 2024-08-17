package com.nocountry.powerfit.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig{

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //Auth
                .antMatchers("/api/v1/auth/**").permitAll()
                //Categories
                .antMatchers("/api/v1/categories/all").permitAll()
                .antMatchers("/api/v1/categories/{name}").permitAll()
                .antMatchers("/api/v1/categories/{id}").permitAll()
                //Products
                .antMatchers("/api/v1/products/all").permitAll()
                .antMatchers("/api/v1/products/category/{categoryName}").permitAll()
                .antMatchers("/api/v1/products/name/{productName}").permitAll()
                .antMatchers("/api/v1/products/{id}").permitAll()
                //Utils
                .antMatchers("/api/v1/categories/").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
        return http.build();
    }
}
