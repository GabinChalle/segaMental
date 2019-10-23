package listener;

import dal.DAOFactory;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class AppListener implements ServletContextListener {
	
	private static final Logger LOGGER  = Logger.getLogger( AppListener.class.getName() );
	
	@Override
	public void contextInitialized( ServletContextEvent sce ) {
		LOGGER.log( Level.INFO, "Chargement de l'application" );
		try {
			DAOFactory.init( sce.getServletContext() );
		} catch ( NamingException e ) {
			e.printStackTrace();
		}
	}
}
