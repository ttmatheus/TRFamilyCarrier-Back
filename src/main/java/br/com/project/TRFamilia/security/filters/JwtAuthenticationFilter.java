package br.com.project.TRFamilia.security.filters;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.project.TRFamilia.security.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter implements Filter {

	@Autowired
	public JwtUtil jwtUtil;

	@Override
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain
	) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;


		String authHeader = httpRequest.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			httpResponse.getWriter().write("Unauthorized: Missing or invalid token");
			return;
		}

		String token = authHeader.replace("Bearer ", "");

		System.out.println("Claims: " + token);
		

		try {
			Map<String, Object> claims = jwtUtil.extractUserClaim(token);
			

			request.setAttribute("userEmail", claims.get("userEmail"));
			request.setAttribute("userId", claims.get("userId"));
			request.setAttribute("userRole", claims.get("userRole"));

			chain.doFilter(request, response);
		} catch (JwtException | IllegalArgumentException e) {
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			httpResponse.getWriter().write("Unauthorized: Invalid token");
		}
	}
}
