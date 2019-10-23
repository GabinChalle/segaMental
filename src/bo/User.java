package bo;

import java.io.Serializable;

public class User implements Serializable {
	
	private int id;
	private String login;
	private String password;
	private int nbConnections;
	
	public User() { }
	
	public User( String login, String password ) {
		this.login = login;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId( int id ) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin( String login ) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword( String password ) {
		this.password = password;
	}
	
	public int getNbConnections() {
		return nbConnections;
	}
	
	public void setNbConnections( int nbConnections ) {
		this.nbConnections = nbConnections;
	}
}
