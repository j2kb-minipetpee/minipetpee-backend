package com.j2kb.minipetpee.security.jwt;

import com.j2kb.minipetpee.api.member.domain.Role;
import com.j2kb.minipetpee.global.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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
        claims.put("homepeeId", principal.getHomepeeId());
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
            log.error(ErrorCode.EMP1004.getMessage());
        }
        if (Objects.isNull(claims.get("homepeeId", Long.class))) {
            log.error(ErrorCode.EMP1016.getMessage());
        }
        if (Objects.isNull(claims.get("name", String.class))) {
            log.error(ErrorCode.EMP1005.getMessage());
        }
        if (Objects.isNull(claims.get("email", String.class))) {
            log.error(ErrorCode.EMP1006.getMessage());
        }
        if (Objects.isNull(claims.get("role", String.class))) {
            log.error(ErrorCode.EMP1007.getMessage());
        }

        List<GrantedAuthority> authorities =
                Role.valueOf(claims.get("role", String.class)).getAuthorities().stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.name()))
                        .collect(Collectors.toList());

        JwtAuthenticationPrincipal principal = new JwtAuthenticationPrincipal(
                claims.get("id", Long.class),
                claims.get("homepeeId", Long.class),
                claims.get("name", String.class),
                claims.get("email", String.class)
        );

        return new JwtAuthenticationToken(authorities, principal, null);
    }

    public String parseToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!Objects.isNull(authorization) && authorization.startsWith("Bearer")) {
            return authorization.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error(ErrorCode.EMP1002.getMessage());
        } catch (ExpiredJwtException e) {
            log.error(ErrorCode.EMP1001.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error(ErrorCode.EMP1003.getMessage());
        } catch (IllegalArgumentException e) {
            log.error(ErrorCode.EMP1000.getMessage());
        }
        return false;
    }
}
