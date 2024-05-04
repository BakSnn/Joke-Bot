package ru.baksnn.project.JokeBot.JokeBot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.baksnn.project.JokeBot.model.ClientsAuthority;

@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .requestMatchers("/registration", "/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/jokes").permitAll()
                                .requestMatchers(HttpMethod.POST, "/jokes").hasAuthority(ClientsAuthority.MODERATOR.getAuthority())
                                .requestMatchers(HttpMethod.GET, "/jokes/**").hasAuthority(ClientsAuthority.ADMIN.getAuthority())
                                .requestMatchers(HttpMethod.POST, "/jokes").hasAuthority(ClientsAuthority.ADMIN.getAuthority())
                                .requestMatchers(HttpMethod.PUT, "/jokes/**").hasAuthority(ClientsAuthority.ADMIN.getAuthority())
                                .requestMatchers(HttpMethod.DELETE, "/jokes/**").hasAuthority(ClientsAuthority.ADMIN.getAuthority())
                                .anyRequest().hasAuthority(ClientsAuthority.ADMIN.getAuthority()))
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}