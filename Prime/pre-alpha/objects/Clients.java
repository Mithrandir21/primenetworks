package objects;


import java.io.Serializable;


/**
 * An abstract super class for all client objects in the system, including
 * {@link clients.Desktop Desktop} and {@link clients.Laptop Laptop}. MUST ADD
 * INFO!
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public abstract class Clients extends Object implements Serializable
{

	// TODO - Create a standalone, singel purpose client class.


	// NETWORK INFORMATION FIELDS

	// Desktop rating
	private int clientRate;

	// Nodes before it reaches the first router outside of the systems own
	// routers, i.e. the Internet.
	private int numberOfNodes;




	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 */
	public Clients(String Name, String Desc)
	{
		super(Name, Desc);
	}



	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 * @param SupConInt
	 *            The connection interfaces supported by the client.
	 */
	public Clients(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}



	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 * @param DesktopComponents
	 *            The initial components an instance of a desktop has.
	 */
	public Clients(String Name, String Desc, Object[] DesktopComponents)
	{
		super(Name, Desc, DesktopComponents);
	}


	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 * @param SupConInt
	 *            An array of strings that describes the supported connection
	 *            interfaces.
	 * @param DesktopComponents
	 *            The initial components an instance of a desktop has.
	 */
	public Clients(String Name, String Desc, String[] SupConInt,
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



	/**
	 * TODO - Description NEEDED!
	 *
	 * @return the clientRate
	 */
	public int getClientRate()
	{
		return clientRate;
	}



	/**
	 * TODO - Description NEEDED!
	 *
	 * @param clientRate the clientRate to set
	 */
	public void setClientRate(int clientRate)
	{
		this.clientRate = clientRate;
	}

}
