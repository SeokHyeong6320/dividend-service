package com.project.security;

import com.project.service.impl.MemberServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements TokenProvider{

    @Value("${token-provider.jwt.key.roles}")
    public String KEY_ROLES;
    @Value("${token-provider.jwt.token.expire-time}")
    public Long TOKEN_EXPIRE_TIME;

    private final SecretKey secretKey;
    private final MemberServiceImpl memberServiceImpl;


   /* public JwtTokenProvider(
            @Value("${spring.jwt.secret}") String key,
            MemberServiceImpl memberServiceImpl
    ) {

        this.secretKey =
                new SecretKeySpec(
                        key.getBytes(StandardCharsets.UTF_8),
                        Jwts.SIG.HS256.key().build().getAlgorithm()
                );
        this.memberServiceImpl = memberServiceImpl;
    }*/


    @Override
    public String generateToken(String username, List<String> roles) {

        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        return Jwts.builder().subject(username)
                .claim(KEY_ROLES, roles)
                .signWith(secretKey)
                .issuedAt(now)
                .expiration(expiredDate)
                .compact();
    }

    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) return false;

        Claims claims = parseClaims(token);
        return !claims.getExpiration().before(new Date());
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails =
                memberServiceImpl.loadByUsername(getUsername(token));

        UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken
                (userDetails, "", userDetails.getAuthorities());
        return token1;
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey).build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private String getUsername(String token) {
        return parseClaims(token).getSubject();
    }
}
