package objects;

import java.io.Serializable;


/**
 * An abstract super class for all client objects in the system, 
 * including {@link  clients.Desktop  Desktop} and {@link  clients.Laptop  Laptop}.
 * 
 * MUST ADD INFO!
 *
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public abstract class Clients extends Object implements Serializable
{
	
	// TODO - Create a standalone, singel purpose client class. 
	

	/**
	 * Constructor for an abstract client superclass. 
	 * 
	 * MORE INFO NEEDED!
	 *
	 * @param Name The name of the client.
	 * @param Desc The description of the client.
	 */
	public Clients(String Name, String Desc)
	{
		super(Name,Desc);
	}
	
	
	/**
	 * Constructor for an abstract client superclass. 
	 * 
	 * MORE INFO NEEDED!
	 *
	 * @param Name The name of the client.
	 * @param Desc The description of the client.
	 */
	public Clients(String Name, String Desc, String[] SupConInt)
	{
		super(Name,Desc,SupConInt);
	}
}
