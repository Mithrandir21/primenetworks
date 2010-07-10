/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package objects.serverObjects;


import java.io.Serializable;

import objects.Object;
import objects.Servers;
import objects.Software;
import objects.softwareObjects.Webserver;


/**
 * A representation of a web server. This server serves, whoever has access to connect to it with, with websites. The
 * content of the sites depend on the purpose decided by the network administrator.
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
	 *            The description of the application that the server is set to run.
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
	 *            The description of the application that the server is set to run.
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
	 *            The description of the application that the server is set to run.
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
	 *            The description of the application that the server is set to run.
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
