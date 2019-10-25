package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class VersionFilter implements Filter {
	
	@Override
	public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
		((HttpServletResponse)servletResponse).addHeader( "x-version", "1.0 Alpha" );
		filterChain.doFilter( servletRequest, servletResponse );
	}
}
