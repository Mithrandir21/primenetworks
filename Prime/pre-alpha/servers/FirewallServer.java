package servers;


import software.*;
import java.io.Serializable;

import exceptions.StringNotFoundInArrayException;
import objects.*;
import objects.Object;
import managment.ArrayManagment;


/**
 * A representation of a firewall server. This firewall, usually standing
 * between the internet and the internal network, will accept, deny or proxy(if
 * function available) connections to the rest of the network.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class FirewallServer extends Servers implements Serializable
{

	// Supports on-site-access, hence it has a keyboard, mouse and screen
	private boolean supportsOnSiteAccess;

	// Supports remote access
	private boolean supportsRemoteAccess;

	// Supported remote access protocols
	private String[] supportedRemoteAccessProtocols;


	// Connection to the exact firewall software that is going to be run on this
	// server
	private Firewall firewall;




	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param FirewallSWname
	 *            The name of the application that the server is set to run.
	 * @param FirewallSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param FirewallSWversion
	 *            The version of the application that the server is set to run.
	 */
	public FirewallServer(String Name, String Desc, String FirewallSWname, String FirewallSWdesc,
			String FirewallSWversion)
	{
		super(Name, Desc);

		// Creates a firewall software object
		firewall = new Firewall(FirewallSWname, FirewallSWdesc, FirewallSWversion);
	}



	/**
	 * The constructor for the device including supported connection interfaces.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param FirewallSWname
	 *            The name of the application that the server is set to run.
	 * @param FirewallSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param FirewallSWversion
	 *            The version of the application that the server is set to run.
	 */
	public FirewallServer(String Name, String Desc, String[] SupConInt, String FirewallSWname,
			String FirewallSWdesc, String FirewallSWversion)
	{
		super(Name, Desc, SupConInt);

		// Creates a firewall software object
		firewall = new Firewall(FirewallSWname, FirewallSWdesc, FirewallSWversion);
	}



	/**
	 * The constructor for the device including supported connection interfaces.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param FirewallSWname
	 *            The name of the application that the server is set to run.
	 * @param FirewallSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param FirewallSWversion
	 *            The version of the application that the server is set to run.
	 */
	public FirewallServer(String Name, String Desc, Object[] DesktopComponents, String FirewallSWname,
			String FirewallSWdesc, String FirewallSWversion)
	{
		super(Name, Desc, DesktopComponents);

		// Creates a firewall software object
		firewall = new Firewall(FirewallSWname, FirewallSWdesc, FirewallSWversion);
	}





	/**
	 * The constructor for the device including supported connection interfaces.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param FirewallSWname
	 *            The name of the application that the server is set to run.
	 * @param FirewallSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param FirewallSWversion
	 *            The version of the application that the server is set to run.
	 */
	public FirewallServer(String Name, String Desc, String[] SupConInt, Object[] DesktopComponents,
			String FirewallSWname, String FirewallSWdesc, String FirewallSWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// Creates a firewall software object
		firewall = new Firewall(FirewallSWname, FirewallSWdesc, FirewallSWversion);
	}


	/**
	 * Get the software that is set to run on this device.
	 */
	public Firewall getFirewallApplication()
	{

		return firewall;
	}





	/**
	 * Get the protocols this device supports for access from a remote location.
	 */
	public String[] getSupportedRemoteAccessProtocols()
	{

		return supportedRemoteAccessProtocols;
	}





	/**
	 * Get a boolean saying if this device supports on-site-access, which
	 * implies that the device is atleast connected to a mouse, keyboard and a
	 * monitor.
	 */
	public boolean SupportsOnSiteAccess()
	{

		return supportsOnSiteAccess;
	}





	/**
	 * Get a boolean saying if the device supports remote access.
	 */
	public boolean SupportsRemoteAccess()
	{

		return supportsRemoteAccess;
	}





	/**
	 * Sets the application that is to run on the device.
	 */
	public void setFirewallApplication(Firewall firewall)
	{

		this.firewall = firewall;
	}





	/**
	 * Set an array of string with the protocols the device supports for remote
	 * access.
	 */
	public void setSupportedRemoteAccessProtocols(String[] supportedRemoteAccessProtocols)
	{

		this.supportedRemoteAccessProtocols = supportedRemoteAccessProtocols;
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
