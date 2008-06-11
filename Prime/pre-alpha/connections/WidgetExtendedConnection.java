/**
 * 
 */
package connections;

import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class WidgetExtendedConnection extends ConnectionWidget
{
	private Connection connection = null;
	
	public WidgetExtendedConnection(Scene scene, Connection con)
	{
		super(scene);
		connection = con;
	}

	
	
	
	/**
	 * TODO - Description NEEDED!
	 *
	 * @return the connection
	 */
	public Connection getConnection()
	{
		return connection;
	}

	/**
	 * TODO - Description NEEDED!
	 *
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}

}
