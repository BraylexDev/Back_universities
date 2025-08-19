package com.universities.universities.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMethodSecurity
public class WebSecutity implements WebMvcConfigurer{


    /* public WebSecutity(UserService userDetailsService){
        this.userDetailsService = userDetailsService;
    } */

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) 
        throws Exception {       
            return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.cors(Customizer.withDefaults());
        
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) ->
                        //authorize.anyRequest().authenticated()
                authorize.requestMatchers(HttpMethod.POST, "/**").permitAll()
                                .requestMatchers("/api/excel/**").permitAll()
                                .requestMatchers("/file/**").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/user/login").permitAll()
                                //.anyRequest().authenticated()
                );
        return http.build(); 
    }

    /*@Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user = User.withUsername("user")
            .password("userPass")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    } */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
