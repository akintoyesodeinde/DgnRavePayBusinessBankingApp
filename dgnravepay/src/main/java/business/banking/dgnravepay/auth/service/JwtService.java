package business.banking.dgnravepay.auth.service;

import business.banking.dgnravepay.auth.entity.UserEntity;
import business.banking.dgnravepay.auth.config.AppProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final AppProperties props;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(props.getJwtSecret().getBytes());
    }

    public String generateAccessToken(UserEntity user) {
        long expMillis = System.currentTimeMillis() + props.getAccessTokenExpirationSec() * 1000L;
        return Jwts.builder()
                .setSubject(user.getPasswordHash()) // Using hash as subject to identify user
                .claim("uid", user.getId().toString())
                // Roles/Permissions removed here
                .setIssuedAt(new Date())
                .setExpiration(new Date(expMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(UserEntity user) {
        long expMillis = System.currentTimeMillis() + props.getRefreshTokenExpirationSec() * 1000L;
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setExpiration(new Date(expMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}












//package business.banking.dgnravepay.auth.service;
//
//
//import business.banking.dgnravepay.auth.entity.UserEntity;
//import business.banking.dgnravepay.auth.config.AppProperties;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Date;
//
//@Service
//@RequiredArgsConstructor
//public class JwtService {
//
//    private final AppProperties props; // wrapper for properties (below)
//
//    private Key getSigningKey() {
//        return Keys.hmacShaKeyFor(props.getJwtSecret().getBytes());
//    }
//
//    public String generateAccessToken(UserEntity user) {
//        long expMillis = System.currentTimeMillis() + props.getAccessTokenExpirationSec() * 1000L;
//        Date exp = new Date(expMillis);
//        return Jwts.builder()
//                .setSubject(user.getPhoneNumber())
//                .claim("uid", user.getId().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(exp)
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String generateRefreshToken(UserEntity user) {
//        long expMillis = System.currentTimeMillis() + props.getRefreshTokenExpirationSec() * 1000L;
//        Date exp = new Date(expMillis);
//        return Jwts.builder()
//                .setSubject(user.getId().toString())
//                        .setExpiration(exp)
//                        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                        .compact();
//    }
//
//    public Claims parse(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}
