package dal;

import bo.User;
import dal.jdbc.UserDAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory {
	
	private static String mode;
	private static String dsName;
	private static DataSource ds;
	
	private DAOFactory() {}
	
	public static void init( ServletContext servletContext ) throws NamingException {
		mode = servletContext.getInitParameter( "DS_MODE" );
		switch ( mode ) {
			case "JDBC":
				dsName = servletContext.getInitParameter( "DS_NAME" );
				// lecture du contexte JDNI de notre servlet
				Context context = new InitialContext();
				// lecture de la datasource définie par requête JNDI
				ds = ( DataSource ) context.lookup( dsName );
				break;
			case "JPA":
			default:
				//TODO
		}
	}
	
	public static Connection getJDBCConnection() throws SQLException {
		Connection connection = null;
		if ( null != ds ) {
			connection = ds.getConnection();
		}
		return connection;
	}
	
	public static IUserDAO getUserDAO() {
		IUserDAO dao;
		switch ( mode ) {
			case "JDBC":
				dao = new UserDAO();
				break;
			case "JPA":
			default:
				//TODO
				dao = null;
		}
		return dao;
	}
}
