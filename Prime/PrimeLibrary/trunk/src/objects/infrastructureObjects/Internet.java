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
package objects.infrastructureObjects;


import objects.Infrastructure;
import objects.Object;
import objects.hardwareObjects.Motherboard;


/**
 * This class represents a Internet connection point. The point of this object is to give a object which a user can
 * assume is the connection out to the World Wide Web. The user should assume that this object is where the Internet can
 * be found.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class Internet extends Infrastructure
{
	/**
	 * The name of the network
	 */
	private String netName;


	/**
	 * The description of the network
	 */
	private String netDescription;


	/**
	 * The device that is connected to this network object
	 */
	private Object connectedDevice;



	/**
	 * A constructor for an Internet object with the name of the Network the Internet object represent a connection to.
	 * 
	 * @param name
	 *            The name of the object.
	 * @param desc
	 *            The description of the object.
	 * @param net
	 *            The name of the Network the Internet object represent a connection to.
	 */
	public Internet(String name, String desc, String net)
	{
		super(name, desc);
		this.netName = net;
		this.netDescription = desc;
	}



	/**
	 * A constructor for an Internet object with the connected {@link Object Objects} as a given argument.
	 * 
	 * @param name
	 *            The name of the object.
	 * @param desc
	 *            The description of the object.
	 * @param net
	 *            The name of the Network the Internet object represent a connection to.
	 * @param device
	 *            The connected {@link Object Objects}.
	 */
	public Internet(String name, String desc, String net, Object device)
	{
		super(name, desc);
		this.netName = net;
		this.netDescription = desc;
		this.connectedDevice = device;
	}


	/**
	 * A constructor for an Internet object with a String array of supported connection types(which normally would only
	 * be RJ-45) and a {@link Motherboard}.
	 * 
	 * @param name
	 *            The name of the object.
	 * @param desc
	 *            The description of the object.
	 * @param net
	 *            The name of the Network the Internet object represent a connection to.
	 * @param supCon
	 *            The supported connection types of the object.
	 * @param initObjects
	 *            The {@link Motherboard} of the Internet object(which is only used so that the system can connect a
	 *            RJ-45 cable to).
	 */
	public Internet(String name, String desc, String net, String[] supCon,
			Motherboard initObjects)
	{
		super(name, desc, supCon, initObjects);
		this.netName = net;
		this.netDescription = desc;
	}



	// GETTERS

	/**
	 * Gets the name of the Network the Internet object represent a connection to.
	 */
	public String getNetName()
	{
		return this.netName;
	}



	/**
	 * Gets the description of the Internet object.
	 */
	public String getNetDescription()
	{
		return this.netDescription;
	}



	/**
	 * Gets the object connected to this Internet object.
	 */
	public Object getConnectedDevice()
	{
		return this.connectedDevice;
	}



	// SETTERS

	/**
	 * Sets the name of the Network the Internet object represent a connection to.
	 */
	public void setNetName(String netName)
	{
		this.netName = netName;
	}



	/**
	 * Sets the description of the Internet object.
	 */
	public void setNetDescription(String netDescription)
	{
		this.netDescription = netDescription;
	}



	/**
	 * Sets the object connected to this Internet object.
	 */
	public void setConnectedDevice(Object connectedDevice)
	{
		this.connectedDevice = connectedDevice;
	}


}
