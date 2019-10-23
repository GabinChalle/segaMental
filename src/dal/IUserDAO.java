package dal;

import bo.User;

import java.sql.SQLException;

public interface IUserDAO<ID, E> extends IDAO<ID, E> {
	
	public User authenticate( String login, String password ) throws SQLException;
}
