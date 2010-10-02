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
package objects.infrastructureObjects;


import objects.hardwareObjects.Motherboard;


/**
 * This class represents a Wireless {@link Router}. It contains only a boolean
 * on whether or not the Wireless function is turned on or not.
 * 
 * @author Bahram Malaekeh
 */
public class WirelessRouter extends Router
{

	// Wireless feature
	private boolean wireless;


	/**
	 * A constructor for this class that takes as parameters the parameters
	 * needed for the {@link Router} class and a boolean on whether wifi
	 * enabled.
	 */
	public WirelessRouter(String Name, String Desc, String[] SupConInt,
			Motherboard objectMB, int outPorts, int inPorts,
			String[] DuplexSupport, boolean wifi)
	{
		super(Name, Desc, SupConInt, objectMB, outPorts, inPorts, DuplexSupport);

		this.wireless = wifi;
	}


	/**
	 * Gets a boolean on whether Wireless feature is enabled or not.
	 */
	public boolean isWireless()
	{
		return this.wireless;
	}


	/**
	 * Sets a boolean on whether Wireless feature is enabled or not.
	 */
	public void setWireless(boolean wireless)
	{
		this.wireless = wireless;
	}

}
