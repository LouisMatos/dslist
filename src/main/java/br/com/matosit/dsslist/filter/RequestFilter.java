package br.com.matosit.dsslist.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RequestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// do nothing.
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		try {
			MDC.put("requestId", getCorrelationId());
			log.info("Request IP address is {}", servletRequest.getRemoteAddr());
			log.info("Request content type is {}", servletRequest.getContentType());
			filterChain.doFilter(servletRequest, servletResponse);
		} finally {
			MDC.remove("requestId");
		}

//		UUID uniqueId = UUID.randomUUID();
//		MDC.put("requestId", uniqueId.toString());
//		log.info("Request IP address is {}", servletRequest.getRemoteAddr());
//		log.info("Request content type is {}", servletRequest.getContentType());
//
//		filterChain.doFilter(servletRequest, servletResponse);

	}

	private String getCorrelationId() {
		return UUID.randomUUID().toString();
	}

	@Override
	public void destroy() {
		// do nothing.
	}

}
