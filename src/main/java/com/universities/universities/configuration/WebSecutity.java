package com.universities.universities.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableMethodSecurity
public class WebSecutity implements WebMvcConfigurer {

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    
    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) 
        throws Exception {       
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults());
        
        http.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((authorize) ->
                authorize
                    // Endpoints públicos
                    .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/excel/datas").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/ranking/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/news/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/ranking/**").permitAll()
                    // Endpoints protegidos por admin
                    .requestMatchers(HttpMethod.POST, "/universities/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/universities/**").hasRole("ADMIN")
                    /* .requestMatchers(HttpMethod.POST, "/ranking/**").hasRole("ADMIN") */
                    .requestMatchers("/api/excel/**").hasRole("ADMIN")
                    .requestMatchers("/file/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/news/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/news/**").permitAll()
                    
                    .requestMatchers(HttpMethod.PUT, "/api/news/**").hasRole("ADMIN")
                    // Endpoints que requieren autenticación
                    .requestMatchers("/country/**").authenticated()
                    .requestMatchers("/universities/**").authenticated()
                    /* .requestMatchers("/ranking/**").authenticated() */
                    
                    .anyRequest().authenticated()
            )
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint)
            );
        
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build(); 
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*");
    }    
}
