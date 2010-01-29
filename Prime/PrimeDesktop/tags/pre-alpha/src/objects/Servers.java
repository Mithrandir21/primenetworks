package objects;


import java.io.Serializable;

import managment.ArrayManagment;
import exceptions.StringNotFoundInArrayException;



/**
 * An abstract super class for all servers objects in the system, including {@link objects.clientObjects.Desktop MUST
 * CHANGE} and {@link objects.clientObjects.Laptop MUST CHANGE}. MUST ADD INFO!
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public abstract class Servers extends Object implements Serializable
{


	// NETWORK INFORMATION FIELDS


	// Supports on-site-access, hence it has a keyboard, mouse and screen
	private boolean supportsOnSiteAccess;

	// Supports remote access
	private boolean supportsRemoteAccess;

	// Supported remote access protocols
	private String[] supportedRemoteAccessProtocols;

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
	public Servers(String Name, String Desc, String[] SupConInt, Object[] DesktopComponents)
	{
		super(Name, Desc, SupConInt, DesktopComponents);
	}





	/**
	 * Get a boolean saying if this device supports on-site-access, which implies that the device is at least connected
	 * to a mouse, keyboard and a monitor.
	 */
	public boolean supportsOnSiteAccess()
	{
		return supportsOnSiteAccess;
	}


	/**
	 * Get a boolean saying if the device supports remote access.
	 */
	public boolean supportsRemoteAccess()
	{
		return supportsRemoteAccess;
	}



	/**
	 * Get the protocols this device supports for access from a remote location.
	 */
	public String[] getSupportedRemoteAccessProtocols()
	{
		return supportedRemoteAccessProtocols;
	}



	/**
	 * Set the support of the device for On-Site-Access.
	 */
	public void setSupportsOnSiteAccess(boolean supportsOnSiteAccess)
	{
		this.supportsOnSiteAccess = supportsOnSiteAccess;
	}



	/**
	 * Set the support of the device for remote access.
	 */
	public void setSupportsRemoteAccess(boolean supportsRemoteAccess)
	{
		this.supportsRemoteAccess = supportsRemoteAccess;
	}


	/**
	 * Set an array of string with the protocols the device supports for remote access.
	 */
	public void setSupportedRemoteAccessProtocols(String[] supportedRemoteAccessProtocols)
	{

		this.supportedRemoteAccessProtocols = supportedRemoteAccessProtocols;
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
	 * Function to add protocols to the the remote access protocols list.
	 * 
	 * @param NewProtocols
	 *            An array of new remote access protocols.
	 */
	public void addRemoteAccessProtocols(String[] NewProtocols) throws Exception
	{
		supportedRemoteAccessProtocols = ArrayManagment.addItems(NewProtocols, supportedRemoteAccessProtocols);

	}



	/**
	 * Function to remove remote access protocols from the array of remote access protocols.
	 * 
	 * @param ToBeRemoved
	 *            Remote access protocol to be removed.
	 */
	public void removeRemoteAccessProtocols(String[] ToBeRemoved) throws StringNotFoundInArrayException
	{
		supportedRemoteAccessProtocols = ArrayManagment.removeItems(ToBeRemoved, supportedRemoteAccessProtocols);
	}

}
