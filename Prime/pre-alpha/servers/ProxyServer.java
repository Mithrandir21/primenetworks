package servers;


import java.io.Serializable;

import managment.ArrayManagment;
import objects.Object;
import objects.Servers;
import objects.Software;
import software.Proxy;
import exceptions.StringNotFoundInArrayException;


/**
 * A representation of a proxy server. This server serves the rest of network
 * with information depending on the layout of the network itself and the
 * purpose giving to the device by the network administrator.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ProxyServer extends Servers implements Serializable
{
	// Connection to the exact Proxy software that is going to be run on this
	// server
	private Proxy Proxy;



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param ProxySWname
	 *            The name of the application that the server is set to run.
	 * @param ProxySWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param ProxySWversion
	 *            The version of the application that the server is set to run.
	 */
	public ProxyServer(String Name, String Desc, String ProxySWname,
			String ProxySWdesc, String ProxySWversion)
	{
		super(Name, Desc);

		// Creates a Proxy software object
		Proxy = new Proxy(ProxySWname, ProxySWdesc, ProxySWversion);
		Software[] sw = { Proxy };
		super.setSoftware(sw);
	}


	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param ProxySWname
	 *            The name of the application that the server is set to run.
	 * @param ProxySWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param ProxySWversion
	 *            The version of the application that the server is set to run.
	 */
	public ProxyServer(String Name, String Desc, String[] SupConInt,
			String ProxySWname, String ProxySWdesc, String ProxySWversion)
	{
		super(Name, Desc, SupConInt);

		// Creates a Proxy software object
		Proxy = new Proxy(ProxySWname, ProxySWdesc, ProxySWversion);
		Software[] sw = { Proxy };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param ProxySWname
	 *            The name of the application that the server is set to run.
	 * @param ProxySWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param ProxySWversion
	 *            The version of the application that the server is set to run.
	 */
	public ProxyServer(String Name, String Desc, Object[] DesktopComponents,
			String ProxySWname, String ProxySWdesc, String ProxySWversion)
	{
		super(Name, Desc, DesktopComponents);

		// Creates a Proxy software object
		Proxy = new Proxy(ProxySWname, ProxySWdesc, ProxySWversion);
		Software[] sw = { Proxy };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param ProxySWname
	 *            The name of the application that the server is set to run.
	 * @param ProxySWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param ProxySWversion
	 *            The version of the application that the server is set to run.
	 */
	public ProxyServer(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents, String ProxySWname, String ProxySWdesc,
			String ProxySWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// Creates a Proxy software object
		Proxy = new Proxy(ProxySWname, ProxySWdesc, ProxySWversion);
		Software[] sw = { Proxy };
		super.setSoftware(sw);
	}




	/**
	 * Get the software that is set to run on this device.
	 */
	public Proxy getProxyApplication()
	{

		return Proxy;
	}

	/**
	 * Sets the application that is to run on the device.
	 */
	public void setProxyApplication(Proxy Proxy)
	{

		this.Proxy = Proxy;
	}
}
