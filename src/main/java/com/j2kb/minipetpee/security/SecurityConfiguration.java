package com.j2kb.minipetpee.security;

import com.j2kb.minipetpee.security.jwt.JwtAccessDeniedHandler;
import com.j2kb.minipetpee.security.jwt.JwtAuthenticationEntryPointHandler;
import com.j2kb.minipetpee.security.jwt.JwtFilter;
import com.j2kb.minipetpee.security.jwt.JwtResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token-expiry-seconds}")
    private long accessTokenExpirySeconds;

    @Value("${jwt.refresh-token-expiry-seconds}")
    private long refreshTokenExpirySeconds;

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                .antMatchers("/favicon.ico")
                .antMatchers("/h2-console/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/swagger-ui/**")
                .antMatchers("/aws/health")
                .antMatchers("/error");
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic()

                .and()
                .formLogin()

                .and()
                .csrf().disable()

                .headers()
                .frameOptions().disable()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
                .mvcMatchers("/apis/validate-email").permitAll()
                .mvcMatchers("/apis/members").permitAll()
                .mvcMatchers("/apis/login").permitAll()
                .anyRequest().permitAll()

                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPointHandler())
                .accessDeniedHandler(jwtAccessDeniedHandler());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtResolver jwtResolver() {
        return new JwtResolver(secretKey, accessTokenExpirySeconds, refreshTokenExpirySeconds);
    }

    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtResolver());
    }

    @Bean
    public JwtAuthenticationEntryPointHandler jwtAuthenticationEntryPointHandler() {
        return new JwtAuthenticationEntryPointHandler();
    }

    @Bean
    public JwtAccessDeniedHandler jwtAccessDeniedHandler() {
        return new JwtAccessDeniedHandler();
    }
}
