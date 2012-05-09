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
package widgetManipulation;


import java.io.Serializable;

import managment.NetworkManagment;


/**
 * This class contains the information about a widget in a network. Information like IP, netmask and default gateway
 * address.
 * There are also patterns for IP and MAC addresses that can be used to verify strings.
 * When setting the different fields, the string is checked against the patterns so the the information saved will be
 * valid.
 * 
 * @author Bahram Malaekeh
 */
public class WidgetNetworkInfo implements Serializable
{
	// The widgets IP in the network
	private String ip = null;


	// The widgets netmask
	private String netmask = null;


	// The widgets MAC address
	private String MAC = null;


	// The widgets default gateway
	private String defaultGateway = null;


	// The widgets network name
	private String networkName = null;


	// The widgets notes
	private String widgetNotes = null;



	/**
	 * Empty constructor for the class.
	 */
	public WidgetNetworkInfo()
	{
	}



	// GETTERS


	/**
	 * @return The IP of the widget in the network.
	 */
	public String getIp()
	{
		// If the string variable is null
		if ( this.ip == null )
		{
			return "";
		}
		return this.ip;
	}




	/**
	 * @return The netmask of the widget in the network.
	 */
	public String getNetmask()
	{
		// If the string variable is null
		if ( this.netmask == null )
		{
			return "";
		}
		return this.netmask;
	}




	/**
	 * @return The Main MAC addresses of the widget in the network.
	 */
	public String getMAC()
	{
		// If the string variable is null
		if ( this.MAC == null )
		{
			return "";
		}
		return this.MAC;
	}




	/**
	 * @return The widgets default gateway on the network.
	 */
	public String getDefaultGateway()
	{
		// If the string variable is null
		if ( this.defaultGateway == null )
		{
			return "";
		}
		return this.defaultGateway;
	}




	/**
	 * @return The name given to the widget in the network.
	 */
	public String getNetworkName()
	{
		// If the string variable is null
		if ( this.networkName == null )
		{
			return "";
		}
		return this.networkName;
	}



	/**
	 * This the notes regarding the widget in the network. It can be anything from what is the widgets access rights in
	 * the network or the uses of the widget in the network.
	 * 
	 * @return The widget notes.
	 */
	public String getWidgetNotes()
	{
		// If the string variable is null
		if ( this.widgetNotes == null )
		{
			return "";
		}
		return this.widgetNotes;
	}


	// SETTERS




	/**
	 * Sets the IP of the widget in the network. The given string is validated before it is set. True is returned if it
	 * passes validation and is set, false if it does not pass.
	 * 
	 * @param ip
	 *            The IP of the widget in the network.
	 */
	public boolean setIp(String ip)
	{
		// If the string is empty
		if ( ip.equals("") )
		{
			this.ip = ip;
			return true;
		}

		// If the string given matches the given pattern.
		if ( NetworkManagment.getIPpattern().matcher(ip).matches() )
		{
			this.ip = ip;
			return true;
		}

		return false;
	}




	/**
	 * Sets the netmask of the widget in the network. The given string is validated before it is set. True is returned
	 * if it passes validation and is set, false if it does not pass.
	 * 
	 * @param netmask
	 *            The netmask of the widget in the network.
	 */
	public boolean setNetmask(String netmask)
	{
		// If the string is empty
		if ( netmask.equals("") )
		{
			this.netmask = netmask;
			return true;
		}

		// If the string given matches the given pattern.
		if ( NetworkManagment.getIPpattern().matcher(netmask).matches() )
		{
			this.netmask = netmask;
			return true;
		}

		return false;
	}




	/**
	 * Sets the Main MAC of the widget in the network. The given string is validated before it is set. True is returned
	 * if it passes validation and is set, false if it does not pass.
	 * 
	 * @param MAC
	 *            The Main MAC addresses for the widget in the network.
	 */
	public boolean setMAC(String MAC)
	{
		// If the string is empty
		if ( MAC.equals("") )
		{
			this.MAC = MAC;
			return true;
		}

		// If the string given matches the given pattern.
		if ( NetworkManagment.getMACpattern().matcher(MAC).matches() )
		{
			this.MAC = MAC;
			return true;
		}

		return false;
	}




	/**
	 * Sets the defaultGateway of the widget in the network. This will be closest infrastructure widget connected to
	 * this widget. The given string is validated before it is set. True is returned if it passes validation and is set,
	 * false if it does not pass.
	 * 
	 * @param defaultGateway
	 *            The default gateway of the widget in the network.
	 */
	public boolean setDefaultGateway(String defaultGateway)
	{
		// If the string is empty
		if ( defaultGateway.equals("") )
		{
			this.defaultGateway = defaultGateway;
			return true;
		}

		// If the string given matches the given pattern.
		if ( NetworkManagment.getIPpattern().matcher(defaultGateway).matches() )
		{
			this.defaultGateway = defaultGateway;
			return true;
		}

		return false;
	}




	/**
	 * The name of widget in the network.
	 * 
	 * @param networkName
	 *            The name of the widget in the network.
	 */
	public void setNetworkName(String networkName)
	{
		this.networkName = networkName;
	}



	/**
	 * This the notes regarding the widget in the network. It can be anything from what is the widgets access rights in
	 * the network or the uses of the widget in the network.
	 * 
	 * @param widgetNotes
	 *            the widgetNotes to set
	 */
	public void setWidgetNotes(String widgetNotes)
	{
		this.widgetNotes = widgetNotes;
	}
}
