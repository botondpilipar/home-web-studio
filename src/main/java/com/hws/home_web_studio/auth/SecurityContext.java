package com.hws.home_web_studio.auth;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.hws.home_web_studio.user.UserRoles;

@Configuration
@EnableWebSecurity
public class SecurityContext {

    @Bean
    public SecurityFilterChain provideFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/users/create").hasRole(UserRoles.ADMINISTRATOR.getName())
                .requestMatchers("/applications/create").hasRole(UserRoles.ADMINISTRATOR.getName())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/v3/api-docs",
                    "/v3/api-docs/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/.well-known/**",
                    "/favicon.ico",
                    "/error",
                    "/error/**").permitAll()
                .requestMatchers("/**/*com.chrome.devtools*").permitAll()
                .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults());

        return http.build();
    }
}