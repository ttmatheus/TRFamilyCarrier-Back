package br.com.project.TRFamilia.security.filters;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class RateLimitingFilter implements Filter {
	
	private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String ipAddress = req.getRemoteAddr();

		Bucket bucket = buckets.computeIfAbsent(ipAddress, this::newBucket);

		if(bucket.tryConsume(1)) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse res = (HttpServletResponse) response;
			res.setStatus(429);
			res.getWriter().write("Too Many Requests");
		}
	}

	@SuppressWarnings("unused")
	private Bucket newBucket(String key) {
		return Bucket.builder()
				.addLimit(Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1))))
				.build();
	}
}
