package org.firestorm.demosecurities.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(description = "Check user login status and redirect request", urlPatterns = { "/*" }, initParams = {
		@WebInitParam(name = "excluse_url", value = "/Authentication") })
public class AuthenticationFilter implements Filter {
	// Logger for this filter
	Logger LOG = Logger.getLogger(AuthenticationFilter.class);

	/**
	 * Default constructor.
	 */
	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		String uri = req.getRequestURI();
		if (uri.contains("Authentication") || uri.contains("/res/") || uri.contains("/views/")) {
			chain.doFilter(request, response);
		} else {
			if (session == null || session.getAttribute("user") == null) {
				res.sendRedirect("Authentication");
			} else {

				res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
																						// 1.1.
				res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				res.setDateHeader("Expires", 0); // Proxies.
				chain.doFilter(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
