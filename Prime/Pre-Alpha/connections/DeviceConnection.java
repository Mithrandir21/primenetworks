package connections;
import java.io.Serializable;
import objects.Object;


/**
 * A representation of a non-networking connection between different devices.
 * This connection represents any connection that is not a networking connection, like a 
 * connection between a computer and a printer, a computer and a scanner, a printer and
 * a nework router(USB connecetion) and so on.
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class DeviceConnection extends Connection implements Serializable 
{
	

	
	
	/**
	 * A class constructor for the connection between device.
	 *
	 * @param From The object the connection emanates from.
	 * @param To The object which is to be connected to. 
	 * @param connection A string that says what kind of connection there is between the objects.
	 */
	public DeviceConnection(String Name, String Desc,Object From, Object To, String connection)
	{
		super(Name,Desc,From, To,connection);
	}

}
