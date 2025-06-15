package br.com.project.TRFamilia.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.project.TRFamilia.security.filters.JwtAuthenticationFilter;

@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilter() {
		FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtAuthenticationFilter());
		registrationBean.addUrlPatterns("/users/*");
		registrationBean.setOrder(1);

		return registrationBean;
	}
}

