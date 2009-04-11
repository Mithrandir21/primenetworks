package connections;


import java.io.Serializable;

import objects.Object;


/**
 * A representation of a connection between networking devices. This can be between a computer and a router, a computer
 * and and another computer, a router and another router and so on. In computers the connection is normally maid with
 * the NIC.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class NetworkConnection extends Connection implements Serializable
{
	/**
	 * A class constructor for the network connection.
	 * 
	 * @param From
	 *            The object the connection emanates from.
	 * @param To
	 *            The object which is to be connected to.
	 */
	public NetworkConnection(String Name, String Desc, Object From, Object To, String connection)
	{
		super(Name, Desc, From, To, connection);
	}
}
