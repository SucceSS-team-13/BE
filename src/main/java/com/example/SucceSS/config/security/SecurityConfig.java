package com.example.SucceSS.config.security;

import com.example.SucceSS.config.CorsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtProvider jwtProvider;


    private final String[] WHITE_LIST = new String[]{
            "/api/auth/sign-up/**",
            "/api/auth/sign-in/**",
            "/error",
            "/api/auth/reissue",
            "/ws-chat/**",
            "/health"
    };
    private static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui/**", "/v3/api-docs/**"
            ,"/swagger-ui.html"
            ,"/swagger-ui/**"
            ,"/api-docs/**",
            "/v3/api-docs/**",
            "v3/api-docs/**",
            "/swagger-ui/**",
            "swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors->cors.configurationSource(CorsConfig.corsConfigurationSource()))
                .sessionManagement((sessionManagement)->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorizeRequests)->
                        authorizeRequests
                                .requestMatchers(WHITE_LIST).permitAll()
                                .requestMatchers(SWAGGER_WHITELIST).permitAll()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .exceptionHandling((exception) ->
                        exception.authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
