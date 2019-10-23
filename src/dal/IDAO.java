package dal;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<ID, E> {
	
	public void create( E object ) throws SQLException;
	
	public void update( E object ) throws SQLException;
	
	public void delete( E object ) throws SQLException;
	
	public E findById( ID id ) throws SQLException;
	
	public List<E> findByAll() throws SQLException;
}
