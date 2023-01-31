package com.thdtraining.todoserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

/* 1. This is the first file to configure se security */
@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    /* 17. Inject  UserDetailService and AuthorizationFilter to WebSecurityConfig*/
    private final UserDetailServiceImp userDetailService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    
    /* 2. This is the first method that is build */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{

        /* 18. Add AuthenticationFilter and set AuthenticationManager  */

        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return http.csrf().disable()
            .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/user")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/user")
                .hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/user/id/**")
                .hasAuthority("ADMIN")
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(jwtAuthenticationFilter)
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    /* 3. Create a bean to load users in memory  */
    /* @Bean
    public UserDetailsService userDetailService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin2").password(passwordEncoder().encode("admin2")).roles().build());
        return manager;
    }  */

    /* 4. Implementacion of password encoder */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/* 5. Create an Authentication manager */
    @Bean
    AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailService)
        .passwordEncoder(passwordEncoder())
        .and()
        .build();
    }
}
