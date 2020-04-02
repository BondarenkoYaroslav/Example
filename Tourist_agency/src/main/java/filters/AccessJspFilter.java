package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/AccessJspFilter")
public class AccessJspFilter implements Filter {

	public AccessJspFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("");  
	    rd.include(request, response);  
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
