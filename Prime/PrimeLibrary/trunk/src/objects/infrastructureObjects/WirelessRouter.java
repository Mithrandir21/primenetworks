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
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param Name
	 * @param Desc
	 * @param SupConInt
	 * @param objectMB
	 * @param outPorts
	 * @param inPorts
	 * @param DuplexSupport
	 * @param wifi
	 */
	public WirelessRouter(String Name, String Desc, String[] SupConInt,
			Motherboard objectMB, int outPorts, int inPorts,
			String[] DuplexSupport, boolean wifi)
	{
		super(Name, Desc, SupConInt, objectMB, outPorts, inPorts, DuplexSupport);

		this.wireless = wifi;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the wireless
	 */
	public boolean isWireless()
	{
		return this.wireless;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param wireless
	 *            the wireless to set
	 */
	public void setWireless(boolean wireless)
	{
		this.wireless = wireless;
	}

}
