package br.com.cineshare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desativa proteção CSRF (para facilitar testes)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // Permite acesso livre às APIs
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable()) // Desativa login via formulário
                .httpBasic(basic -> basic.disable()); // Desativa autenticação básica

        return http.build();
    }
}
