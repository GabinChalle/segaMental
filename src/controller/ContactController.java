package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( urlPatterns = {"/contacts", "/contacts/add", "/contacts/edit", "/contacts/delete"} )
public class ContactController extends HttpServlet {
	
	private static final String PAGE_CONTACTS_LIST_JSP = "/WEB-INF/jsp/contacts_list.jsp";
	
	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		req.getServletContext().getRequestDispatcher( PAGE_CONTACTS_LIST_JSP ).forward( req, resp );
	}
	
	
}
