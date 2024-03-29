package controller;

import model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet( urlPatterns = {"/login", "/signup", "/admin"} )
public class LoginController extends HttpServlet {
	
	private static final String PAGE_LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
	private static final String PAGE_ADMIN_JSP = "/users";
	private static final String PAGE_HOME_JSP = "/game";

	
	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		request.setAttribute( "today", new Date() );
		UserBean bean = ( UserBean ) request.getAttribute( "userBean" );
		if ( bean == null ) {
			bean = new UserBean();
			request.setAttribute( "userBean", bean );
		}
		if ( bean.isConnected( request ) ) {
			System.out.println("On est connecté");
			if(request.getServletPath().equals("/login")) {
				response.sendRedirect(request.getContextPath() + PAGE_HOME_JSP);
			}
			else if(request.getServletPath().equals("/admin")){
				System.out.println("je suis là");
				request.getServletContext().getRequestDispatcher( PAGE_ADMIN_JSP ).forward( request, response );
			}
		} else {
			request.getServletContext().getRequestDispatcher( PAGE_LOGIN_JSP ).forward( request, response );
		}
	}
	
	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		//if selon le form rempli
		UserBean bean = new UserBean();
		//if form login
		if(request.getServletPath().equals("/login")){
			bean.authenticate( request );
			request.setAttribute( "userBean", bean );
			doGet( request, response );
		}
		else if(request.getServletPath().equals("/signup")){
			//Est ce que on peut
			if (bean.setUserFromForm(request)) {
				response.sendRedirect(request.getContextPath() + "/login");
			} else {
				doGet(request,response);
			}
		}
		else if(request.getServletPath().equals("/admin")){
			//cas du bouton admin
			bean.authenticate( request );
			request.setAttribute( "userBean", bean );
			doGet( request, response );
		}
		else{
			request.getServletContext().getRequestDispatcher( PAGE_LOGIN_JSP ).forward( request, response );
		}
	}
}
