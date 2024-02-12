package ua.foxminded.university_cms.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import ua.foxminded.university_cms.model.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry ->
                        registry
                                .requestMatchers(permitAllMatchers())
                                .permitAll()
                                .requestMatchers(adminMatchers())
                                .hasRole(Role.ADMIN.name())
                                .anyRequest()
                                .authenticated())
                .formLogin(
                        httpSecurityFormLoginConfigurer ->
                                httpSecurityFormLoginConfigurer.loginPage("/login")
                                        .defaultSuccessUrl("/course", true))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
    Without this setting, Spring Security may treat requests for static resources just like any other
     requests and apply security rules to them, which may result in access restrictions.
     **/
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/style/**", "/js/**", "/webjars/**");
    }

    private RequestMatcher adminMatchers() {
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/course/create"),
                new AntPathRequestMatcher("/course/delete/*"),
                new AntPathRequestMatcher("/course/update/*")
        );
    }

    private RequestMatcher permitAllMatchers() {
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/login")
        );
    }
}


