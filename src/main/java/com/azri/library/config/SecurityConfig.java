package com.azri.library.config;


import com.azri.library.security.JwtAuthenticationFilter;
import com.azri.library.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**",
    };
    public static final String API_V_1_BOOK = "/api/v1/book/**";
    public static final String API_V_1_USER = "/api/v1/user/**";
    public static final String API_V_1_BOOK_ACTIVITY_LOG = "/api/v1/book-activity-log";


    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL).permitAll()
                                .requestMatchers(GET, API_V_1_BOOK).hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                                .requestMatchers(PATCH, API_V_1_BOOK).hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                                .requestMatchers(POST, API_V_1_BOOK).hasAnyRole(Role.ADMIN.name())
                                .requestMatchers(PUT, API_V_1_BOOK).hasAnyRole(Role.ADMIN.name())
                                .requestMatchers(DELETE, API_V_1_BOOK).hasAnyRole(Role.ADMIN.name())

                                .requestMatchers(GET, API_V_1_USER).hasAnyRole(Role.ADMIN.name())
                                .requestMatchers(PATCH, API_V_1_USER).hasAnyRole(Role.ADMIN.name())
                                .requestMatchers(POST, API_V_1_USER).hasAnyRole(Role.ADMIN.name())
                                .requestMatchers(PUT, API_V_1_USER).hasAnyRole(Role.ADMIN.name())
                                .requestMatchers(DELETE, API_V_1_USER).hasAnyRole(Role.ADMIN.name())

                                .requestMatchers(API_V_1_BOOK_ACTIVITY_LOG).hasAnyRole(Role.ADMIN.name())
                                .anyRequest().authenticated()


                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(
                        jwtAuthFilter, UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

}
