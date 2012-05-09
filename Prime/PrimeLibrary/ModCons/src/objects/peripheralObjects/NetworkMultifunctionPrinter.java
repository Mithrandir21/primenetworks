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
package objects.peripheralObjects;


import objects.hardwareObjects.Motherboard;
import widgetManipulation.WidgetNetworkInfo;


/**
 * This extension of the {@link MultifunctionPrinter} represents a
 * {@link MultifunctionPrinter} that is network capable.
 * 
 * @author Bahram Malaekeh
 */
public class NetworkMultifunctionPrinter extends MultifunctionPrinter
{
	private WidgetNetworkInfo info = new WidgetNetworkInfo();



	/**
	 * Constructor of the MFP class.
	 * 
	 * @param Name
	 *            The name of the MFP.
	 * @param Desc
	 *            The description of the MFP.
	 * @param MFPConnectionInterfaces
	 *            Connection interfaces supported by a MFP. A array of Strings.
	 */
	public NetworkMultifunctionPrinter(String Name, String Desc,
			String[] MFPConnectionInterfaces)
	{
		super(Name, Desc, MFPConnectionInterfaces);
	}



	/**
	 * Constructor of the MFP class.
	 * 
	 * @param Name
	 *            The name of the MFP.
	 * @param Desc
	 *            The description of the MFP.
	 * @param MFPConnectionInterfaces
	 *            Connection interfaces supported by a MFP. A array of Strings.
	 * @param objectMB
	 *            The {@link Motherboard} of the object.
	 */
	public NetworkMultifunctionPrinter(String Name, String Desc,
			String[] MFPConnectionInterfaces, Motherboard objectMB)
	{
		super(Name, Desc, MFPConnectionInterfaces, objectMB);
	}




	/**
	 * Gets the {@link WidgetNetworkInfo} object containing the network
	 * information about this MFP.
	 */
	public WidgetNetworkInfo getInfo()
	{
		return this.info;
	}


	/**
	 * Sets the {@link WidgetNetworkInfo} object containing the network
	 * information about this MFP.
	 */
	public void setInfo(WidgetNetworkInfo info)
	{
		this.info = info;
	}
}
