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
 * An abstract super class for all hardware objects in the system, including {@link objects.Servers Servers},
 * {@link objects.Clients Clients} and Peripherals. MUST ADD INFO
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public abstract class Hardware extends Object implements Serializable
{


	// FIXME - Create a component rating system.


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 */
	protected Hardware(String Name, String Desc)
	{
		super(Name, Desc);
	}


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 */
	protected Hardware(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 */
	protected Hardware(String Name, String Desc, Object[] DesktopComponents)
	{
		super(Name, Desc, DesktopComponents);
	}


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 */
	protected Hardware(String Name, String Desc, String[] SupConInt,
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
	 *            The description of the hardware decvice.
	 */
	protected Hardware(String Name, String Desc, String[] SupConInt,
			Motherboard objectMB)
	{
		super(Name, Desc, SupConInt, objectMB);
	}


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 */
	protected Hardware(String Name, String Desc, Motherboard objectMB)
	{
		super(Name, Desc, null, objectMB);
	}
}
