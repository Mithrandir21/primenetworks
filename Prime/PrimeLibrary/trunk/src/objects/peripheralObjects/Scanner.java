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



import java.io.Serializable;

import objects.ExternalHardware;
import objects.hardwareObjects.Motherboard;


/**
 * This class represents a scanner. This device can be connected to any device that supports the conncetion type of this
 * device. It contains information on what kind of capability the printer machine has and what kind of connection
 * interface is has.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Scanner extends ExternalHardware implements Serializable
{

	// Maximum resolution of a scanner
	private String resolution;

	/**
	 * Constructor of the scanner class.
	 * 
	 * @param Name
	 *            The name of the scanner.
	 * @param Desc
	 *            The description of the scanner.
	 * @param Sresolution
	 *            Maximum resolution of a scanner. <br>
	 *            This can be a NULL pointer. The value will then be a NULL pointer.
	 * @param SconnectionInterfaces
	 *            Connection interfaces supported by the scanner. An array of Strings.
	 */
	public Scanner(String Name, String Desc, String Sresolution,
			String[] SconnectionInterfaces)
	{
		super(Name, Desc, SconnectionInterfaces);

		if ( Sresolution != null )
		{
			resolution = Sresolution;
		}
	}


	/**
	 * Constructor of the scanner class.
	 * 
	 * @param Name
	 *            The name of the scanner.
	 * @param Desc
	 *            The description of the scanner.
	 * @param Sresolution
	 *            Maximum resolution of a scanner. <br>
	 *            This can be a NULL pointer. The value will then be a NULL pointer.
	 * @param SconnectionInterfaces
	 *            Connection interfaces supported by the scanner. An array of Strings.
	 */
	public Scanner(String Name, String Desc, String Sresolution,
			String[] SconnectionInterfaces, Motherboard objectMB)
	{
		super(Name, Desc, SconnectionInterfaces, objectMB);

		if ( Sresolution != null )
		{
			resolution = Sresolution;
		}
	}


	/**
	 * Constructor of the scanner class.
	 * 
	 * @param Name
	 *            The name of the scanner.
	 * @param Desc
	 *            The description of the scanner.
	 * @param Sresolution
	 *            Maximum resolution of a scanner. <br>
	 *            This can be a NULL pointer. The value will then be a NULL pointer.
	 */
	public Scanner(String Name, String Desc, String Sresolution,
			Motherboard objectMB)
	{
		super(Name, Desc, objectMB);

		if ( Sresolution != null )
		{
			resolution = Sresolution;
		}
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get the resolution of the scanner.
	 */
	public String getScannerResolution()
	{
		return resolution;
	}


	// SET METHODES

	/**
	 * Sets the resolution of the scanner.
	 */
	public void setScannerResolution(String Sresolution)
	{
		resolution = Sresolution;
	}

}