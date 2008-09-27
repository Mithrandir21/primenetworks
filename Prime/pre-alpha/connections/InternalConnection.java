package connections;


import java.io.Serializable;

import objects.Object;


/**
 * A representation of a internal connection between two devices. This can be
 * between a hdd and a motherboard, a CPU and a motherboard, hot-swap HDD and a
 * server and so on. So any connection within a computer is representanted by an
 * internalConnection object.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class InternalConnection extends Connection implements Serializable
{


	/**
	 * A class constructor for the internal connection.
	 * 
	 * @param From
	 *            The object the connection emanates from.
	 * @param To
	 *            The object which is to be connected to.
	 */
	public InternalConnection(String Name, String Desc, Object From, Object To, String connection)
	{
		super(Name, Desc, From, To, connection);
	}
}
