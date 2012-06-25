/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package objects.hardwareObjects;


import java.io.Serializable;
import java.util.ArrayList;

import objects.Hardware;
import objects.Object;
import connections.Connection;


/**
 * This class represents a external networks interface card connected to
 * {@link objects.Servers server} or {@link objects.Clients
 * client} machine. It can be a server, a desktop or a laptop. It contains
 * information on what
 * kind of capability the networks interface card has. It also contains
 * information on transfere speeds, MACs,
 * connection types and so on. <br>
 * <br>
 * <b>Notation</b>: The networks interface card will be refered to as "NIC" in
 * the remainder of this document.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class ExternalNetworksCard extends Hardware implements Serializable
{

	// The company that produces the NIC. D-Link, Linksys, Cisco and so on.
	private String producer;

	// The port that connects with the computer
	private String port;

	// The MAC address of the NIC
	private String MAC;

	// The connection type of NIC. Wired or wireless
	private String connectionType;

	// The speed of the NIC. 10 Mbps, 100 Mbps or 1000 Mbps
	private int transferSpeed;

	// The NIC protocol. Ethernet, token and so on
	private String protocol;

	// The standards supported by the NIC. IEEE 802.3, IEEE 802.3u, IEEE 802.1x
	// and so on
	private String[] supportedStandards;

	// Whether or not the NIC has support for IP version 6, IPv6
	private boolean supportsIPv6;

	// The Objects this NIC is connected to
	private ArrayList<Object> connectedObject = new ArrayList<Object>();

	// This arraylist will be the connections between the connectedObject.
	private ArrayList<Connection> connections = new ArrayList<Connection>();




	/**
	 * Constructor of a NIC hardware
	 * 
	 * @param Name
	 *            The name of the MB.
	 * @param Desc
	 *            The description of the MB.
	 * @param NICproducer
	 *            The company that produces the NIC. D-Link, Linksys, Cisco and
	 *            so on.
	 * @param NIC_MAC
	 *            The MAC address of the NIC.
	 * @param NICconnectionType
	 *            The connection type of NIC. Wired or wireless.
	 */
	public ExternalNetworksCard(String Name, String Desc, String NICproducer,
			String NIC_MAC, String NICconnectionType, String NICconncectedBy)
	{
		super(Name, Desc);

		this.producer = NICproducer;
		this.MAC = NIC_MAC;
		this.connectionType = NICconnectionType;
		this.port = NICconncectedBy;
	}



	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get producer of the NIC.
	 */
	public String getProducer()
	{
		return this.producer;
	}


	/**
	 * Get the MAC of the NIC.
	 */
	public String getMAC()
	{
		return this.MAC;
	}


	/**
	 * Get the connection type of NIC.
	 */
	public String getConnectionType()
	{
		return this.connectionType;
	}


	/**
	 * Get the transfer speed of the NIC.
	 */
	public int getSpeed()
	{
		return this.transferSpeed;
	}


	/**
	 * Get the protocol of the NIC.
	 */
	public String getProtocol()
	{
		return this.protocol;
	}


	/**
	 * Get the standard(s) of the NIC.
	 */
	public String[] getSupportedStandards()
	{
		return this.supportedStandards;
	}


	/**
	 * Get the protocol of the NIC.
	 */
	public boolean getIPv6support()
	{
		return this.supportsIPv6;
	}


	/**
	 * Get the connection type between the device and the computer.
	 */
	public String getPort()
	{
		return this.port;
	}


	/**
	 * Get the {@link Object} this NIC is connected to.
	 */
	public ArrayList<Object> getConnectedObject()
	{
		return this.connectedObject;
	}


	/**
	 * Get the connections to the connectedObject.
	 */
	public ArrayList<Connection> getConnections()
	{
		return this.connections;
	}


	// SET METHODES

	/**
	 * Set method for producers of the NIC.
	 */
	public void setProducer(String NICProducer)
	{
		this.producer = NICProducer;
	}



	/**
	 * Set method for the type of the NIC.(Wired or Wireless)
	 */
	public void setConnectionType(String type)
	{
		this.connectionType = type;
	}



	/**
	 * Set method for the MAC of the NIC.
	 */
	public void setMAC(String NIC_MAC)
	{
		this.MAC = NIC_MAC;
	}


	/**
	 * Set method for speed of the NIC.
	 */
	public void setSpeed(int NICtransferSpeed)
	{
		this.transferSpeed = NICtransferSpeed;
	}


	/**
	 * Set method for connection type of the NIC.
	 */
	public void setSupportedStandards(String NICsupportedStandards[])
	{
		this.supportedStandards = NICsupportedStandards;
	}


	/**
	 * Set method for speed of the NIC.
	 */
	public void setSupportsIPv6(boolean NICsupportsIPv6)
	{
		this.supportsIPv6 = NICsupportsIPv6;
	}


	/**
	 * Set the connection type between the device and the computer.
	 */
	public void setPortBy(String NICport)
	{
		this.port = NICport;
	}


	/**
	 * Adds the {@link Object} NIC to the connected to arraylist.
	 */
	public void addConnectedObject(Object obj)
	{
		this.connectedObject.add(obj);
	}


	/**
	 * Removes the {@link Object} NIC from the connected to arraylist.
	 */
	public void removeConnectedObject(Object obj)
	{
		this.connectedObject.remove(obj);
	}


	/**
	 * Adds the connection to the connectedObject.
	 */
	public void addConnection(Connection connection)
	{
		this.connections.add(connection);
	}


	/**
	 * Removes the {@link Connection} from the connections arraylist.
	 */
	public void removeConnection(Connection connection)
	{
		this.connections.remove(connection);
	}



	// FUNCTIONS
	/**
	 * This function will traverse the {@link ArrayList} containing the
	 * connected objects and return the object that has a matching serial number
	 * to the one provided. It wil return NULL is no such object is found.
	 */
	public Object getConnectedObjectBySerial(long serial)
	{
		for ( int i = 0; i < connectedObject.size(); i++ )
		{
			if ( connectedObject.get(i).getObjectSerial() == serial )
			{
				return connectedObject.get(i);
			}
		}

		return null;
	}



	/**
	 * This function clears the list of connected devices and connections list.
	 */
	public void removeAllNetworkConnectedDevices()
	{
		connectedObject.clear();
		connections.clear();
	}
}
