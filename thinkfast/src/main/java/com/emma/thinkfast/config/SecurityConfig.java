package com.emma.thinkfast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // private final UserDetailsService userDetailsService;

    // public SecurityConfig(UserDetailsService userDetailsService) {
    //     this.userDetailsService = userDetailsService;
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
            authorizeRequests
                // Adjust to the following when Login system is finished:
                //.antMatchers("/register").permitAll()     //Registry is any-access
                //.anyRequest().authenticated()             //Other requests require auth
                
                .anyRequest().permitAll()                   //Allows all requests w/out auth
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")    //Custom login
                    .permitAll()                      //Login is any-access
            )
            .logout(LogoutConfigurer::permitAll //Logout is any-access
            );
        //Disable csrf, also but only during development.
        http.csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}