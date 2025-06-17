package br.com.project.TRFamilia.security;


import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.project.TRFamilia.dto.UserInfoDTO;
import io.jsonwebtoken.Claims;
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

	public String generateToken(UserInfoDTO userInfo) {
		return Jwts.builder()
				.setSubject(userInfo.getUserEmail())
				.claim(
					"user", Map.of(
						"id", userInfo.getUserId(),
						"userType", userInfo.getUserType(),
						"name", userInfo.getUserName()
					)
				)
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

	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(getKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> extractUserClaim(String token) {
		Claims claims = extractAllClaims(token);
		return claims.get("user", Map.class);
	}
}
