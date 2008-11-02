package servers;


import java.io.Serializable;

import managment.ArrayManagment;
import objects.Object;
import objects.Servers;
import objects.Software;
import software.Webserver;
import exceptions.StringNotFoundInArrayException;


/**
 * A representation of a web server. This server serves, whoever has access to
 * connect to it with, with websites. The content of the sites depend on the
 * purpose decided by the network administrator.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class HTTPServer extends Servers implements Serializable
{

	// Supports on-site-access, hence it has a keyboard, mouse and screen
	private boolean supportsOnSiteAccess;

	// Supports remote access
	private boolean supportsRemoteAccess;

	// Supported remote access protocols
	private String[] supportedRemoteAccessProtocols;


	// Connection to the exact Webserver software that is going to be run on
	// this server
	private Webserver Webserver;



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param WebserverSWname
	 *            The name of the application that the server is set to run.
	 * @param WebserverSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param WebserverSWversion
	 *            The version of the application that the server is set to run.
	 */
	public HTTPServer(String Name, String Desc, String WebserverSWname, String WebserverSWdesc,
			String WebserverSWversion)
	{
		super(Name, Desc);

		// Creates a Webserver software object
		Webserver = new Webserver(WebserverSWname, WebserverSWdesc, WebserverSWversion);
		Software[] sw = {Webserver};
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param WebserverSWname
	 *            The name of the application that the server is set to run.
	 * @param WebserverSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param WebserverSWversion
	 *            The version of the application that the server is set to run.
	 */
	public HTTPServer(String Name, String Desc, String[] SupConInt, String WebserverSWname,
			String WebserverSWdesc, String WebserverSWversion)
	{
		super(Name, Desc, SupConInt);

		// Creates a Webserver software object
		Webserver = new Webserver(WebserverSWname, WebserverSWdesc, WebserverSWversion);
		Software[] sw = {Webserver};
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param WebserverSWname
	 *            The name of the application that the server is set to run.
	 * @param WebserverSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param WebserverSWversion
	 *            The version of the application that the server is set to run.
	 */
	public HTTPServer(String Name, String Desc, Object[] DesktopComponents, String WebserverSWname,
			String WebserverSWdesc, String WebserverSWversion)
	{
		super(Name, Desc, DesktopComponents);

		// Creates a Webserver software object
		Webserver = new Webserver(WebserverSWname, WebserverSWdesc, WebserverSWversion);
		Software[] sw = {Webserver};
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param WebserverSWname
	 *            The name of the application that the server is set to run.
	 * @param WebserverSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param WebserverSWversion
	 *            The version of the application that the server is set to run.
	 */
	public HTTPServer(String Name, String Desc, String[] SupConInt, Object[] DesktopComponents,
			String WebserverSWname, String WebserverSWdesc, String WebserverSWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// Creates a Webserver software object
		Webserver = new Webserver(WebserverSWname, WebserverSWdesc, WebserverSWversion);
		Software[] sw = {Webserver};
		super.setSoftware(sw);
	}





	/**
	 * Get the software that is set to run on this device.
	 * 
	 */
	public Webserver getWebserverApplication()
	{

		return Webserver;
	}





	/**
	 * Get the protocols this device supports for access from a remote location.
	 * 
	 */
	public String[] getSupportedRemoteAccessProtocols()
	{

		return supportedRemoteAccessProtocols;
	}





	/**
	 * Get a boolean saying if this device supports on-site-access, which
	 * implies that the device is atleast connected to a mouse, keyboard and a
	 * monitor.
	 * 
	 */
	public boolean SupportsOnSiteAccess()
	{

		return supportsOnSiteAccess;
	}





	/**
	 * Get a boolean saying if the device supports remote access.
	 * 
	 */
	public boolean SupportsRemoteAccess()
	{

		return supportsRemoteAccess;
	}





	/**
	 * Sets the application that is to run on the device.
	 * 
	 */
	public void setWebserverApplication(Webserver Webserver)
	{

		this.Webserver = Webserver;
	}





	/**
	 * Set an array of string with the protocols the device supports for remote
	 * access.
	 * 
	 */
	public void setSupportedRemoteAccessProtocols(String[] supportedRemoteAccessProtocols)
	{

		this.supportedRemoteAccessProtocols = supportedRemoteAccessProtocols;
	}





	/**
	 * Set the support of the device for On-Site-Access.
	 * 
	 */
	public void setSupportsOnSiteAccess(boolean supportsOnSiteAccess)
	{

		this.supportsOnSiteAccess = supportsOnSiteAccess;
	}





	/**
	 * Set the support of the device for remote access.
	 * 
	 */
	public void setSupportsRemoteAccess(boolean supportsRemoteAccess)
	{

		this.supportsRemoteAccess = supportsRemoteAccess;
	}



	// CLASS METHODES
	/**
	 * Function to add protocols to the the remote access protocols list.
	 * 
	 * @param NewProtocols
	 *            An array of new remote access protocols.
	 */
	public void addRemoteAccessProtocols(String[] NewProtocols) throws Exception
	{
		supportedRemoteAccessProtocols = ArrayManagment.addItems(NewProtocols,
				supportedRemoteAccessProtocols);

	}



	/**
	 * Function to remove remote access protocols from the array of remote
	 * access protocols.
	 * 
	 * @param ToBeRemoved
	 *            Remote access protocol to be removed.
	 */
	public void removeRemoteAccessProtocols(String[] ToBeRemoved)
			throws StringNotFoundInArrayException
	{
		supportedRemoteAccessProtocols = ArrayManagment.removeItems(ToBeRemoved,
				supportedRemoteAccessProtocols);
	}
}
