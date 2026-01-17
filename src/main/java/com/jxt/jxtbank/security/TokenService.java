package com.jxt.jxtbank.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class TokenService {


    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.expiration.time}")
    private String EXPIRATION_TIME;

    private SecretKey secretKey;


    //Spring bean olsuturulduktan hemen sonra bir kez cagrilir
    @PostConstruct
    private void init(){
        byte[] keyByte = JWT_SECRET.getBytes(StandardCharsets.UTF_8);
        this.secretKey = new SecretKeySpec(keyByte, "HmacSHA256");

    }

    public String generateToken(String email){
        return Jwts.builder().subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(EXPIRATION_TIME)))
                .signWith(secretKey)
                .compact();

    }
    public String getUsernameFromToken(String token){
        return extracClaims(token, Claims::getSubject);
    }
    public <T> T extracClaims(String token, Function<Claims, T> function){
        return function.apply(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload());

    }
    public boolean validateToken(String token, UserDetails userDetails){
        final String userName = getUsernameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }
    private boolean isTokenExpired(String token){
        return extracClaims(token, Claims::getExpiration).before(new Date());
    }

}
