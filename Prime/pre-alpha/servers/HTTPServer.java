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
	public HTTPServer(String Name, String Desc, String WebserverSWname,
			String WebserverSWdesc, String WebserverSWversion)
	{
		super(Name, Desc);

		// Creates a Webserver software object
		Webserver = new Webserver(WebserverSWname, WebserverSWdesc,
				WebserverSWversion);
		Software[] sw = { Webserver };
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
	public HTTPServer(String Name, String Desc, String[] SupConInt,
			String WebserverSWname, String WebserverSWdesc,
			String WebserverSWversion)
	{
		super(Name, Desc, SupConInt);

		// Creates a Webserver software object
		Webserver = new Webserver(WebserverSWname, WebserverSWdesc,
				WebserverSWversion);
		Software[] sw = { Webserver };
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
	public HTTPServer(String Name, String Desc, Object[] DesktopComponents,
			String WebserverSWname, String WebserverSWdesc,
			String WebserverSWversion)
	{
		super(Name, Desc, DesktopComponents);

		// Creates a Webserver software object
		Webserver = new Webserver(WebserverSWname, WebserverSWdesc,
				WebserverSWversion);
		Software[] sw = { Webserver };
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
	public HTTPServer(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents, String WebserverSWname,
			String WebserverSWdesc, String WebserverSWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// Creates a Webserver software object
		Webserver = new Webserver(WebserverSWname, WebserverSWdesc,
				WebserverSWversion);
		Software[] sw = { Webserver };
		super.setSoftware(sw);
	}





	/**
	 * Get the software that is set to run on this device.
	 */
	public Webserver getWebserverApplication()
	{

		return Webserver;
	}

	/**
	 * Sets the application that is to run on the device.
	 */
	public void setWebserverApplication(Webserver Webserver)
	{

		this.Webserver = Webserver;
	}
}
