package model;

import bo.User;
import dal.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;

public class UserBean implements Serializable {
	
	private static final String FORM_FIELD_LOGIN = "form-username";
	private static final String FORM_FIELD_PWD = "form-password";
	public static final String ATT_SESSION_CONNECTED_USER = "connectedUser";
	
	private User user;
	private String authResult;
	
	public UserBean() {}
	
	public User getUser() {
		return user;
	}
	
	public void setUser( User user ) {
		this.user = user;
	}
	
	public String getAuthResult() {
		return authResult;
	}
	
	public void setAuthResult( String authResult ) {
		this.authResult = authResult;
	}
	
	public boolean isConnected( HttpServletRequest request ) {
		HttpSession session = request.getSession();
		User connectedUser = ( User ) session.getAttribute( ATT_SESSION_CONNECTED_USER );
		return connectedUser != null;
	}
	
	public void authenticate( HttpServletRequest request )  {
		
		String login = request.getParameter( FORM_FIELD_LOGIN );
		System.out.println(login + "////////////////");
		String password = request.getParameter( FORM_FIELD_PWD );
		System.out.println(password + "////////////////");
		User user = null;
		try {
			user = DAOFactory.getUserDAO().authenticate( login, password );
			System.out.println(user.getLogin());
			if ( null != user ) {
				request.getSession().setAttribute( ATT_SESSION_CONNECTED_USER, user );
				authResult = "Bienvenue " + login + "!";
			} else {
				user = new User(login, password);
				authResult = "Identification échouée, merci de recommencer...";
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
			authResult = "Identification échouée : Pb de connexions à la base de données !";
		}
	}
}
