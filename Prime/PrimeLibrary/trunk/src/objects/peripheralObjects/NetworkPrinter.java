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
package objects.peripheralObjects;


import objects.hardwareObjects.Motherboard;
import widgetManipulation.WidgetNetworkInfo;


/**
 * This class represents a Network printer. This device is almost exactly the same as a {@link Printer}, but contains a
 * {@link WidgetNetworkInfo} object that contains information about the network infor for the device.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class NetworkPrinter extends Printer
{
	private WidgetNetworkInfo info = new WidgetNetworkInfo();


	/**
	 * Constructor of the networkprinter class.
	 * 
	 * @param Name
	 *            The name of the printer.
	 * @param Desc
	 *            The description of the printer.
	 * @param PprinterType
	 *            Type of printer. ink or laser.
	 * @param objectMB
	 */
	public NetworkPrinter(String Name, String Desc, String PprinterType,
			Motherboard objectMB)
	{
		super(Name, Desc, PprinterType, objectMB);
	}

	/**
	 * Constructor of the networkprinter class.
	 * 
	 * @param Name
	 *            The name of the printer.
	 * @param Desc
	 *            The description of the printer.
	 * @param PprinterType
	 *            Type of printer. ink or laser.
	 * @param PconnectionInterfaces
	 *            Connection interfaces supported by the printer. An array of Strings.
	 * @param objectMB
	 */
	public NetworkPrinter(String Name, String Desc, String PprinterType,
			String[] PconnectionInterfaces, Motherboard objectMB)
	{
		super(Name, Desc, PprinterType, PconnectionInterfaces, objectMB);
	}

	/**
	 * Constructor of the networkprinter class.
	 * 
	 * @param Name
	 *            The name of the printer.
	 * @param Desc
	 *            The description of the printer.
	 * @param PprinterType
	 *            Type of printer. ink or laser.
	 * @param PconnectionInterfaces
	 *            Connection interfaces supported by the printer. An array of Strings.
	 */
	public NetworkPrinter(String Name, String Desc, String PprinterType,
			String[] PconnectionInterfaces)
	{
		super(Name, Desc, PprinterType, PconnectionInterfaces);
	}



	// GETTERS

	/**
	 * Gets the {@link WidgetNetworkInfo} object containing the network information about this network printer.
	 */
	public WidgetNetworkInfo getInfo()
	{
		return info;
	}


	// SETTERS

	/**
	 * Sets the {@link WidgetNetworkInfo} object containing the network information about this network printer.
	 */
	public void setInfo(WidgetNetworkInfo info)
	{
		this.info = info;
	}

}
