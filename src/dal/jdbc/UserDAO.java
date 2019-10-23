package dal.jdbc;

import bo.User;
import dal.DAOFactory;
import dal.IUserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements IUserDAO<Long, User> {
	
	private static final String AUTHENT_QUERY = "SELECT * FROM user WHERE login = ? AND password = ?";
	
	@Override
	public User authenticate( String login, String password ) throws SQLException {
		Connection connection = DAOFactory.getJDBCConnection();
		User user = null;
		if ( null != connection ) {
			try ( PreparedStatement ps = connection
					.prepareStatement( AUTHENT_QUERY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE ) ) {
				ps.setString( 1, login );
				ps.setString( 2, password );
				try ( ResultSet rs = ps.executeQuery() ) {
					if ( rs.next() ) {
						int nbConnections = rs.getInt( "connections" ) + 1;
						rs.updateInt( "connections", nbConnections );
						rs.updateRow();
						user = new User();
						user.setLogin( rs.getString( "login" ) );
						user.setPassword( rs.getString( "password" ) );
						user.setNbConnections( nbConnections );
					}
				}
			}
		}
		return user;
	}
	
	@Override
	public void create( User object ) throws SQLException {
	
	}
	
	@Override
	public void update( User object ) throws SQLException {
	
	}
	
	@Override
	public void delete( User object ) throws SQLException {
	
	}
	
	@Override
	public User findById( Long aLong ) throws SQLException {
		return null;
	}
	
	@Override
	public List<User> findByAll() throws SQLException {
		return null;
	}
}
