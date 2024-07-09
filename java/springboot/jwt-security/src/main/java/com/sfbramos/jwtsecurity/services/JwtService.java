package com.sfbramos.jwtsecurity.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${spring.security.jwt.token.secret-key}")
    private String securitySecretKey;

    @Value("${spring.security.jwt.token.expiration-time}")
    private long tokenExpirationTime;

    public String getUserName(final String jwToken) {
        return getClaims(jwToken, Claims::getSubject);
    }

    private Date getExpiration(final String jwToken) {
        return getClaims(jwToken, Claims::getExpiration);
    }

    public String generateToken(final UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public <T> String generateToken(final Map<String, T> extraClaims, final UserDetails userDetails) {
        final Date now = new Date(System.currentTimeMillis());
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + this.tokenExpirationTime))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(final String jwToken, final UserDetails userDetails) {
        final String tokenUserName = getUserName(jwToken);
        return tokenUserName.equals(userDetails.getUsername()) && !isTokenExpired(jwToken);
    }

    private boolean isTokenExpired(final String jwToken) {
        return getExpiration(jwToken).before(new Date(System.currentTimeMillis()));
    }

    private <T> T getClaims(final String jwToken, final Function<Claims, T> claimsFunction) {
        return claimsFunction.apply(getAllClaims(jwToken));
    }

    private Claims getAllClaims(final String jwToken) {
        return getJwtParser().parseSignedClaims(jwToken)
                .getPayload();
    }

    private JwtParser getJwtParser() {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build();
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.securitySecretKey));
    }

}
