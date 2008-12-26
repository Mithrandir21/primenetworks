package connections;


import java.io.Serializable;

import objects.Object;


/**
 * A representation of any connection between any to devices in the entier
 * system. This can be a connection between a computer and a printer, a computer
 * and a router, a router and a server, a router and another router and so on.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public abstract class Connection extends Object implements Serializable
{

	// Object 1
	private Object object1;


	// Object 2
	private Object object2;


	// The actual status of the connection, whether it be enabled or disabled
	private boolean status;


	// Connection rate.
	private int connectionRate;


	// The type of connection between the two objects
	private String connection;




	/**
	 * A class constructor for a connection between 2 objects.
	 * 
	 * @param Name
	 *            The name of the connection.
	 * @param Desc
	 *            The description of the connection.
	 * @param From
	 *            The object the connection eminated from.
	 * @param To
	 *            The object which is to be connected to.
	 */
	public Connection(String Name, String Desc, Object From, Object To,
			String conType)
	{
		super(Name, Desc, new String[0]);
		object1 = From;
		object2 = To;
		connection = conType;
	}




	/**
	 * Get the first object in the connection.
	 */
	public Object getObject1()
	{

		return object1;
	}




	/**
	 * Get the second object in the connection. Normally the object which is to
	 * be connected to.
	 */
	public Object getObject2()
	{

		return object2;
	}





	/**
	 * Get the actual status of the connection, whether it be enabled or
	 * disabled.
	 */
	public boolean getStatus()
	{

		return status;
	}


	/**
	 * Description NEEDED!
	 * 
	 * @return the connection
	 */
	public String getConnectionType()
	{

		return connection;
	}





	/**
	 * Set the first object in the connection.
	 */
	public void setObject1(Object object1)
	{

		this.object1 = object1;
	}




	/**
	 * Set the second object in the connection.
	 */
	public void setObject2(Object object2)
	{

		this.object2 = object2;
	}





	/**
	 * Set the actual status of the connection, whether it be enabled or
	 * disabled.
	 */
	public void setStatus(boolean status)
	{

		this.status = status;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @return the connectionRate
	 */
	public int getConnectionRate()
	{

		return connectionRate;
	}





	/**
	 * Description NEEDED!
	 * 
	 * @param connectionRate
	 *            the connectionRate to set
	 */
	public void setConnectionRate(int connectionRate)
	{

		this.connectionRate = connectionRate;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @param connection
	 *            the connection to set
	 */
	public void setConnection(String connection)
	{

		this.connection = connection;
	}

}
