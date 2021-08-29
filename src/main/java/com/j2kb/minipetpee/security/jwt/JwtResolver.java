package com.j2kb.minipetpee.security.jwt;

import com.j2kb.minipetpee.api.member.domain.Role;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class JwtResolver implements InitializingBean {
    private final String secretKey;
    private final long accessTokenExpirySeconds;
    private final long refreshTokenExpirySeconds;
    private final Map<String, Object> headers;

    private Key key;

    public JwtResolver(final String secretKey, final long accessTokenExpirySeconds, final long refreshTokenExpirySeconds) {
        this.secretKey = secretKey;
        this.accessTokenExpirySeconds = accessTokenExpirySeconds;
        this.refreshTokenExpirySeconds = refreshTokenExpirySeconds;
        this.headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", SignatureAlgorithm.HS256.name());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(decodedKey);
    }

    public String issueAccessToken(JwtAuthenticationPrincipal principal) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", principal.getId());
        claims.put("name", principal.getName());
        claims.put("email", principal.getEmail());
        claims.put("role", principal.getRole());

        return Jwts.builder()
                .setHeader(headers)
                .setClaims(claims)
                .setExpiration(Date.from(ZonedDateTime.now().plusSeconds(accessTokenExpirySeconds).toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String issueRefreshToken(JwtAuthenticationPrincipal principal) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", principal.getId());
        claims.put("isReaccessable", true);

        return Jwts.builder()
                .setHeader(headers)
                .setClaims(claims)
                .setExpiration(Date.from(ZonedDateTime.now().plusSeconds(refreshTokenExpirySeconds).toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public JwtAuthenticationToken getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(key)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();

        if (Objects.isNull(claims.get("id", Long.class))) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, ErrorCode.EMP1004);
        }
        if (Objects.isNull(claims.get("name", String.class))) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, ErrorCode.EMP1005);
        }
        if (Objects.isNull(claims.get("email", String.class))) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, ErrorCode.EMP1006);
        }
        if (Objects.isNull(claims.get("role", String.class))) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, ErrorCode.EMP1007);
        }

        List<GrantedAuthority> authorities =
                Role.valueOf(claims.get("role", String.class)).getAuthorities().stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.name()))
                        .collect(Collectors.toList());

        JwtAuthenticationPrincipal principal = new JwtAuthenticationPrincipal(claims.get("id", Long.class), claims.get("name", String.class), claims.get("email", String.class));

        return new JwtAuthenticationToken(authorities, principal, null);
    }

    public String parseToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(authorization)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP1008);
        }
        if (!authorization.startsWith("Bearer")) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, ErrorCode.EMP1000);
        }
        return authorization.substring(7);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, ErrorCode.EMP1002);
        } catch (ExpiredJwtException e) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, ErrorCode.EMP1001);
        } catch (UnsupportedJwtException e) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, ErrorCode.EMP1003);
        } catch (IllegalArgumentException e) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, ErrorCode.EMP1000);
        }
    }
}
