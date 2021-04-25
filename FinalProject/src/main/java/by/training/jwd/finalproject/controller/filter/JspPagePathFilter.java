package by.training.jwd.finalproject.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.jwd.finalproject.controller.PagePath;

/**
 * The {@code JspPagePathFilter} class represents jsp page path filter.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
@WebFilter(urlPatterns = { "/jsp/*" })
public class JspPagePathFilter implements Filter {
	@Override
	public void init(FilterConfig config) {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.INDEX);
	}

	@Override
	public void destroy() {
	}
}