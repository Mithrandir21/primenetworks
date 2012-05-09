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
import objects.softwareObjects.Proxy;


/**
 * A representation of a proxy server. This server serves the rest of network with information depending on the layout
 * of the network itself and the purpose giving to the device by the network administrator.
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
	 *            The description of the application that the server is set to run.
	 * @param ProxySWversion
	 *            The version of the application that the server is set to run.
	 */
	public ProxyServer(String Name, String Desc, String ProxySWname,
			String ProxySWdesc, String ProxySWversion)
	{
		super(Name, Desc);

		// Creates a Proxy software object
		this.Proxy = new Proxy(ProxySWname, ProxySWdesc, ProxySWversion);
		Software[] sw = { this.Proxy };
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
	 *            The description of the application that the server is set to run.
	 * @param ProxySWversion
	 *            The version of the application that the server is set to run.
	 */
	public ProxyServer(String Name, String Desc, String[] SupConInt,
			String ProxySWname, String ProxySWdesc, String ProxySWversion)
	{
		super(Name, Desc, SupConInt);

		// Creates a Proxy software object
		this.Proxy = new Proxy(ProxySWname, ProxySWdesc, ProxySWversion);
		Software[] sw = { this.Proxy };
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
	 *            The description of the application that the server is set to run.
	 * @param ProxySWversion
	 *            The version of the application that the server is set to run.
	 */
	public ProxyServer(String Name, String Desc, Object[] DesktopComponents,
			String ProxySWname, String ProxySWdesc, String ProxySWversion)
	{
		super(Name, Desc, DesktopComponents);

		// Creates a Proxy software object
		this.Proxy = new Proxy(ProxySWname, ProxySWdesc, ProxySWversion);
		Software[] sw = { this.Proxy };
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
	 *            The description of the application that the server is set to run.
	 * @param ProxySWversion
	 *            The version of the application that the server is set to run.
	 */
	public ProxyServer(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents, String ProxySWname, String ProxySWdesc,
			String ProxySWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// Creates a Proxy software object
		this.Proxy = new Proxy(ProxySWname, ProxySWdesc, ProxySWversion);
		Software[] sw = { this.Proxy };
		super.setSoftware(sw);
	}




	/**
	 * Get the software that is set to run on this device.
	 */
	public Proxy getProxyApplication()
	{

		return this.Proxy;
	}

	/**
	 * Sets the application that is to run on the device.
	 */
	public void setProxyApplication(Proxy Proxy)
	{

		this.Proxy = Proxy;
	}
}
