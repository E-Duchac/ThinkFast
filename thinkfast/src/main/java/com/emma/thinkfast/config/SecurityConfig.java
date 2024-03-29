package com.emma.thinkfast.config;

//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //This bean is specifically to get around auth requirements for the login and anonymous landing page.
    // @Bean
    // public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    //     http.authorizeHttpRequests(requests -> 
    //         requests
    //             .requestMatchers("/user/login").permitAll()
    //             .anyRequest().authenticated()
    //         );
    //     return http.build();
    // }
}
