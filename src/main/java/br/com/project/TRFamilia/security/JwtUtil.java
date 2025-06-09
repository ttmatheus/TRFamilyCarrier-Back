package br.com.project.TRFamilia.security;


import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private final String SECRET_KEY;
	private final long EXPPIRATION_TIME = 86400000;

	public JwtUtil(@Value("${jwt.secret}") String secretKey) {
		this.SECRET_KEY = secretKey;
	}

	private Key getKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPPIRATION_TIME))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public String extractEmail(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token);
			return true;
		} catch(JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
