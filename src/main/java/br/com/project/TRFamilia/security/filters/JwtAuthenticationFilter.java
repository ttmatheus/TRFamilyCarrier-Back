package br.com.project.TRFamilia.security.filters;

import java.io.IOException;
import java.util.Map;

import br.com.project.TRFamilia.security.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return; // deixa passar para o próximo filtro, pode ser rota pública
        }

        String token = authHeader.substring(7);

        try {
            Map<String, Object> claims = jwtUtil.extractUserClaim(token);

            String userEmail = (String) claims.get("email");
            String userType = (String) claims.get("userType");
			Integer userId = (Integer) claims.get("id");

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userEmail,
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority(userType))
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            request.setAttribute("userEmail", userEmail);
            request.setAttribute("userType", userType);
			request.setAttribute("userId", userId);

        } catch (JwtException | IllegalArgumentException e) {
			e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
