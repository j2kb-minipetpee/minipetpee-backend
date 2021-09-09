package com.j2kb.minipetpee.security;

import com.j2kb.minipetpee.security.jwt.JwtAccessDeniedHandler;
import com.j2kb.minipetpee.security.jwt.JwtAuthenticationEntryPointHandler;
import com.j2kb.minipetpee.security.jwt.JwtFilter;
import com.j2kb.minipetpee.security.jwt.JwtResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
                .antMatchers("/swagger-ui/**","/v3/api-docs/**")
                .antMatchers("/v3/api-docs/**")
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
                .mvcMatchers("/apis/validate-email").permitAll()    // 이메일 중복 조회
                .mvcMatchers("/apis/members").permitAll()   // 회원가입
                .mvcMatchers("/apis/login").permitAll()     // 로그인
                .mvcMatchers("/apis/popular-posts").permitAll()     // 인기 게시글 조회
                .mvcMatchers("/apis/search-member").permitAll()     // 계정 검색
                .mvcMatchers("/apis/search-post").permitAll()       // 게시글 검색
                .regexMatchers("/apis/[0-9]*").permitAll()      // 홈피 조회
                .regexMatchers(HttpMethod.GET, "/apis/[0-9]*/guest/guest-notes\\?size=[0-9]*&page=[0-9]*").permitAll()    // 방명록 조회
                .regexMatchers(HttpMethod.GET,"/apis/[0-9]*/album/posts\\?size=[0-9]*&page=[0-9]*").permitAll()       // 갤러리 조회
                .regexMatchers(HttpMethod.GET,"/apis/[0-9]*/board/posts\\?size=[0-9]*&page=[0-9]*").permitAll()       // 게시판 조회
                .regexMatchers(HttpMethod.GET,"/apis/[0-9]*/board/posts/[0-9]*").permitAll()        // 게시글 조회
                .regexMatchers(HttpMethod.GET,"/apis/[0-9]*/posts/[0-9]*/comments\\?size=[0-9]*&page=[0-9]*").permitAll()   // 게시글 댓글 조회
                .regexMatchers(HttpMethod.GET, "/apis/[0-9]*/stars").permitAll()    // 스타 목록 조회
                .regexMatchers(HttpMethod.GET, "/apis/[0-9]*/fans").permitAll()    // 팬 목록 조회
                .anyRequest().authenticated()

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
