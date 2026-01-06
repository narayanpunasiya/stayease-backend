// package com.takehome.stayease.config;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;
// import com.takehome.stayease.entity.Role;

// import java.util.Date;
// import java.util.List;

// @Component
// public class JwtUtil {

//     @Value("${jwt.secret}")
//     private static String secret;

//     public String generateToken(String email, Role role) {
//         return Jwts.builder()
//             .setSubject(email)
//             .claim("roles",List.of("Role" + role.name())) // USER, ADMIN, HOTEL_MANAGER
//             .setIssuedAt(new Date())
//             .setExpiration(new Date(System.currentTimeMillis() + 86400000))
//             .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
//             .compact();
//     }

//     public String extractUsername(String token) {
//         return extractClaims(token).getSubject();
//     }

//     public static Claims extractClaims(String token) {
//         return Jwts.parserBuilder()
//             .setSigningKey(secret.getBytes())
//             .build()
//             .parseClaimsJws(token)
//             .getBody();
//     }
// }
