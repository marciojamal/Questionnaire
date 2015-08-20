package br.com.softbox.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
@WebFilter("/*")
public class EncodingFilter implements  Filter {

	private String encoding = "utf-8";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		try{
			filterChain.doFilter(request, response);
		}catch(Exception e){
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		String encodingParam = filterConfig.getInitParameter("encoding");
		if (encodingParam != null) {
			encoding = encodingParam;
		}
	}

	public void destroy() {
		// nothing todo
	}

}