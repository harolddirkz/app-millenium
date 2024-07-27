package com.devs.demoCours.Auth.Config;

import com.devs.demoCours.Auth.Jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/docente/public/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/curso/public/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/modulo/public/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/session/public/**").permitAll()
                                .requestMatchers("/swagger-ui/**",
                                        "/swagger-resources/*",
                                        "/v3/api-docs/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/docente/list").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }
    @Bean
    CorsConfigurationSource configurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        //config.setAllowedOrigins(List.of("https://taupe-capybara-74e849.netlify.app/"));
        config.setAllowedOrigins(List.of("http://localhost:4200/"));
        config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        config.setAllowedHeaders(List.of("Content-Type", "Authorization"));
        config.setExposedHeaders(Arrays.asList("Content-Type", "Authorization","Another-Header"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
    }
    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter(){
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
                new CorsFilter(configurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
