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
package objects;



import java.io.Serializable;

import objects.hardwareObjects.Motherboard;


/**
 * An abstract super class for all external hardware objects in the system, including
 * {@link objects.peripheralObjects.ExternalHDD ExternalHDD}, {@link objects.peripheralObjects.Printer Printer} and
 * {@link objects.peripheralObjects.Monitor Monitor}. The class contains different constructors so the system can easily
 * create different object with the available information. There are constructors that create object only with a name
 * and a description, and there are constructors that create an object with all the internal components or just a
 * motherboard.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public abstract class ExternalHardware extends Hardware implements Serializable
{

	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware device.
	 */
	public ExternalHardware(String Name, String Desc)
	{
		super(Name, Desc);
	}


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware device.
	 * @param SupConInt
	 *            An array of strings that describes the supported connection interfaces.
	 */
	public ExternalHardware(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware device.
	 * @param DesktopComponents
	 *            The initial components an instance of a desktop has.
	 */
	public ExternalHardware(String Name, String Desc, Object[] DesktopComponents)
	{
		super(Name, Desc, DesktopComponents);
	}


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware device.
	 * @param SupConInt
	 *            An array of strings that describes the supported connection interfaces.
	 * @param DesktopComponents
	 *            The initial components an instance of a desktop has.
	 */
	public ExternalHardware(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents)
	{
		super(Name, Desc, SupConInt, DesktopComponents);
	}


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware device.
	 * @param SupConInt
	 *            An array of strings that describes the supported connection interfaces.
	 * @param deviceMB
	 *            The initial {@link objects.hardwareObjects.Motherboard motherboard} the hardware has.
	 */
	public ExternalHardware(String Name, String Desc, String[] SupConInt,
			Motherboard deviceMB)
	{
		super(Name, Desc, SupConInt, deviceMB);
	}


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware device.
	 * @param deviceMB
	 *            The initial {@link objects.hardwareObjects.Motherboard motherboard} the hardware has.
	 */
	public ExternalHardware(String Name, String Desc, Motherboard deviceMB)
	{
		super(Name, Desc, deviceMB);
	}
}
