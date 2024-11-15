package edu.miu.assignment.securities;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.assignment.exceptions.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtFilter jwtFilter;
    private final ObjectMapper objectMapper;

    private static final String[] whiteLists = {"/swagger-ui/**", "/v3/api-docs/**", "/error"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(whiteLists).permitAll() // for open api documentation
                        .requestMatchers("/api/v1/authenticate").permitAll()
                        .requestMatchers("/api/v1/register").permitAll()
                        .requestMatchers("/api/v1/admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/v1/posts/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/api/v1/comments/**").hasAnyAuthority("USER", "ADMIN")
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("http://localhost:5173"));
                    configuration.setAllowedMethods(List.of("*"));
                    configuration.setAllowedHeaders(List.of("*"));
                    return configuration;
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write(this.objectMapper.writeValueAsString(new ApiError(403, "Access denied")));
                        })
                        .authenticationEntryPoint(authenticationEntryPoint())
                );

        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write(this.objectMapper.writeValueAsString(new ApiError(401, "Unauthorized")));
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
