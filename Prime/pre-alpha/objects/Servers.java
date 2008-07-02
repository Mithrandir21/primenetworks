package objects;
import java.io.Serializable;

import connections.Connection;



/**
 * An abstract super class for all servers objects in the system, 
 * including {@link  clients.Desktop  MUST CHANGE} and {@link  clients.Laptop  MUST CHANGE}.
 * 
 * MUST ADD INFO!
 *
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public abstract class Servers extends Object implements Serializable
{
	// THE MASKIN ITSELF

	// An array that contains pointers to the objects that make up a computer.
	private Object[] components;

	// Counts number of components in the components list
	private int componentCounter;


	// THE DEVICES SURROUNDING IT

	// An array of the objects it is connected to, whether by USB, RJ-45,
	// bluetooth, PS-2 and so on.
	private Object[] connectedDevices;

	// An array of connection object which represent the connection between the
	// outside devices.
	private Connection[] connections;

	// Counts the number of objects it is connected to
	private int connectedDevicesCounter;


	// NETWORK INFORMATION FIELDS

	// Desktop rating
	private int desktopRate;

	// Nodes before it reaches the first router outside of the systems own
	// routers, i.e. the Internet.
	private int numberOfNodes;
	
	
	
	
	/**
	 * Constructor for an abstract server superclass. 
	 * 
	 * MORE INFO NEEDED!
	 *
	 * @param Name The name of the server.
	 * @param Desc The description of the server.
	 */
	public Servers(String Name, String Desc)
	{
		super(Name,Desc);

		componentCounter = 0;

		components = null;

		desktopRate = 0; // Not been rated yet.
	}
	
	/**
	 * Constructor for an abstract server superclass. 
	 * 
	 * MORE INFO NEEDED!
	 *
	 * @param Name The name of the server.
	 * @param Desc The description of the server.
	 */
	public Servers(String Name, String Desc, String[] SupConInt)
	{
		super(Name,Desc,SupConInt);

		componentCounter = 0;

		components = null;

		desktopRate = 0; // Not been rated yet.
	}
	
	
	
	/**
	 * Constructor for an abstract server superclass. 
	 * 
	 * MORE INFO NEEDED!
	 *
	 * @param Name The name of the server.
	 * @param Desc The description of the server.
	 */
	public Servers(String Name, String Desc, Object[] DesktopComponents)
	{
		super(Name,Desc);
		
		componentCounter = DesktopComponents.length;

		components = DesktopComponents;

		desktopRate = 0; // Not been rated yet.		
	}
	
	
	
	/**
	 * Constructor for an abstract server superclass. 
	 * 
	 * MORE INFO NEEDED!
	 *
	 * @param Name The name of the server.
	 * @param Desc The description of the server.
	 */
	public Servers(String Name, String Desc, String[] SupConInt, Object[] DesktopComponents)
	{
		super(Name,Desc,SupConInt);
		
		componentCounter = DesktopComponents.length;

		components = DesktopComponents;

		desktopRate = 0; // Not been rated yet.		
	}
	
}
