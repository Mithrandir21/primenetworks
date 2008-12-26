package objects;


import java.io.Serializable;



/**
 * An abstract super class for all servers objects in the system, including
 * {@link clients.Desktop MUST CHANGE} and {@link clients.Laptop MUST CHANGE}.
 * MUST ADD INFO!
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public abstract class Servers extends Object implements Serializable
{


	// NETWORK INFORMATION FIELDS

	// Desktop rating
	private int desktopRate;

	// Nodes before it reaches the first router outside of the systems own
	// routers, i.e. the Internet.
	private int numberOfNodes;




	/**
	 * Constructor for an abstract server superclass. MORE INFO NEEDED!
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 */
	public Servers(String Name, String Desc)
	{
		super(Name, Desc);
	}

	/**
	 * Constructor for an abstract server superclass. MORE INFO NEEDED!
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 */
	public Servers(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}



	/**
	 * Constructor for an abstract server superclass. MORE INFO NEEDED!
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 */
	public Servers(String Name, String Desc, Object[] DesktopComponents)
	{
		super(Name, Desc, DesktopComponents);
	}



	/**
	 * Constructor for an abstract server superclass. MORE INFO NEEDED!
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 */
	public Servers(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents)
	{
		super(Name, Desc, SupConInt, DesktopComponents);
	}





	/**
	 * Get the number of nodes between the maskin and the internet.
	 */
	public int getNumberJumps()
	{
		return numberOfNodes;
	}



	/**
	 * Set method for number of jumps to the internet.
	 */
	public void setNumberOfJumps(int jumps)
	{
		numberOfNodes = jumps;
	}

}
