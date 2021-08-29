package com.j2kb.minipetpee.security.jwt;

import com.j2kb.minipetpee.global.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class JwtFilter extends GenericFilterBean {
    private final JwtResolver jwtResolver;

    public JwtFilter(JwtResolver jwtResolver) {
        this.jwtResolver = jwtResolver;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            String token = jwtResolver.parseToken((HttpServletRequest) request);
            if (StringUtils.hasText(token) && jwtResolver.validateToken(token)) {
                JwtAuthenticationToken authentication = jwtResolver.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.debug(ErrorCode.EMP1009.getMessage());
            }
        } else {
            SecurityContextHolder.getContext().getAuthentication();
        }
        filterChain.doFilter(request, response);
    }
}
