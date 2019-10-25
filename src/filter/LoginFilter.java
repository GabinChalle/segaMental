package filter;

import model.UserBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns = {"/users", "/users/*"} )
public class LoginFilter implements Filter {
	
	private static final String PAGE_LOGIN_SVLT = "/login";
	
	@Override
	public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
		
		UserBean bean = new UserBean();
		if ( !bean.isConnected( ( HttpServletRequest ) servletRequest ) ) {
			(( HttpServletResponse ) servletResponse)
					.sendRedirect( (( HttpServletRequest ) servletRequest).getContextPath() + PAGE_LOGIN_SVLT );
		} else {
			filterChain.doFilter( servletRequest, servletResponse );
		}
	}
}
