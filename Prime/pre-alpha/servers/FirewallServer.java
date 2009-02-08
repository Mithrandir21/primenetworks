package servers;


import java.io.Serializable;

import managment.ArrayManagment;
import objects.Object;
import objects.Servers;
import objects.Software;
import software.Firewall;
import exceptions.StringNotFoundInArrayException;


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
	public FirewallServer(String Name, String Desc, String FirewallSWname,
			String FirewallSWdesc, String FirewallSWversion)
	{
		super(Name, Desc);

		// Creates a firewall software object
		firewall = new Firewall(FirewallSWname, FirewallSWdesc,
				FirewallSWversion);
		Software[] sw = { firewall };
		super.setSoftware(sw);
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
	public FirewallServer(String Name, String Desc, String[] SupConInt,
			String FirewallSWname, String FirewallSWdesc,
			String FirewallSWversion)
	{
		super(Name, Desc, SupConInt);

		// Creates a firewall software object
		firewall = new Firewall(FirewallSWname, FirewallSWdesc,
				FirewallSWversion);
		Software[] sw = { firewall };
		super.setSoftware(sw);
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
	public FirewallServer(String Name, String Desc, Object[] DesktopComponents,
			String FirewallSWname, String FirewallSWdesc,
			String FirewallSWversion)
	{
		super(Name, Desc, DesktopComponents);

		// Creates a firewall software object
		firewall = new Firewall(FirewallSWname, FirewallSWdesc,
				FirewallSWversion);
		Software[] sw = { firewall };
		super.setSoftware(sw);
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
	public FirewallServer(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents, String FirewallSWname,
			String FirewallSWdesc, String FirewallSWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// Creates a firewall software object
		firewall = new Firewall(FirewallSWname, FirewallSWdesc,
				FirewallSWversion);
		Software[] sw = { firewall };
		super.setSoftware(sw);
	}


	/**
	 * Get the software that is set to run on this device.
	 */
	public Firewall getFirewallApplication()
	{

		return firewall;
	}


	/**
	 * Sets the application that is to run on the device.
	 */
	public void setFirewallApplication(Firewall firewall)
	{

		this.firewall = firewall;
	}
}
