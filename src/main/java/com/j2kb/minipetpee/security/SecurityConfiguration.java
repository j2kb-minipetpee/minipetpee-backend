package com.j2kb.minipetpee.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .mvcMatchers("/").permitAll()
            .mvcMatchers("/aws/health").permitAll()
            .anyRequest().permitAll();

        httpSecurity.csrf()
            .disable();

        httpSecurity.headers()
            .frameOptions().disable();

        httpSecurity.formLogin();
        httpSecurity.httpBasic();
    }
}
