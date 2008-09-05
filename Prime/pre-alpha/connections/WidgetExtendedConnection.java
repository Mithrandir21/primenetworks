/**
 * 
 */
package connections;


import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;

import widgetManipulation.WidgetObject;


/**
 * This class is an extension of the {@link ConnectionWidget ConnectionWidget}
 * class. It is used to represent a connection between two
 * {@link WidgetObject WidgetObjects} on a canvas. It contains a pointer to an
 * actual {@link Connection Connection} which is the actual connection between
 * two objects in the system.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class WidgetExtendedConnection extends ConnectionWidget
{
	private Connection connection = null;

	/**
	 * A constructor which takes a scene and a connection as parameters. The
	 * scene is necessary to create a {@link ConnectionWidget} and the
	 * connection is the actual connection between the two connected objects.
	 * 
	 * @param scene The scene where the visual connection is to be located.
	 * @param con The actual system connection between the two objects.
	 */
	public WidgetExtendedConnection(Scene scene, Connection con)
	{
		super(scene);
		connection = con;
	}




	/**
	 * Gets the actual connection between the two objects.
	 * 
	 * @return the connection
	 */
	public Connection getConnection()
	{
		return connection;
	}

	/**
	 * Sets the actual connection between the two objects.
	 * 
	 * @param connection
	 *            The connection between two system objects.
	 */
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}

}
