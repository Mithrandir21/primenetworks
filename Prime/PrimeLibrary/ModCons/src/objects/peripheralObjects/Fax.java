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
 * This class represents a fax machine. This device can be connected to any device that supports the conncetion type of
 * this device. It contains information on what kind of capability the fax machine has and what kind of connection
 * interface is has.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Fax extends ExternalHardware implements Serializable
{

	// Type of fax. ink or laser
	private String faxType;


	// Pages per minute(up to)
	private int ppm;



	/**
	 * Constructor of the fax class.
	 * 
	 * @param Name
	 *            The name of the fax.
	 * @param Desc
	 *            The description of the fax.
	 * @param FfaxType
	 *            Type of fax. ink or laser.
	 * @param FconnectionInterfaces
	 *            Connection interfaces supported by the fax. An array of Strings.
	 */
	public Fax(String Name, String Desc, String FfaxType,
			String[] FconnectionInterfaces)
	{
		super(Name, Desc, FconnectionInterfaces);

		this.faxType = FfaxType;
	}


	/**
	 * Constructor of the fax class.
	 * 
	 * @param Name
	 *            The name of the fax.
	 * @param Desc
	 *            The description of the fax.
	 * @param FfaxType
	 *            Type of fax. ink or laser.
	 * @param FconnectionInterfaces
	 *            Connection interfaces supported by the fax. An array of Strings.
	 * @param objectMB
	 *            The {@link Motherboard} of the object.
	 */
	public Fax(String Name, String Desc, String FfaxType,
			String[] FconnectionInterfaces, Motherboard objectMB)
	{
		super(Name, Desc, FconnectionInterfaces, objectMB);

		this.faxType = FfaxType;
	}



	/**
	 * Constructor of the fax class.
	 * 
	 * @param Name
	 *            The name of the fax.
	 * @param Desc
	 *            The description of the fax.
	 * @param FfaxType
	 *            Type of fax. ink or laser.
	 * @param objectMB
	 *            The {@link Motherboard} of the object.
	 */
	public Fax(String Name, String Desc, String FfaxType, Motherboard objectMB)
	{
		super(Name, Desc, objectMB);

		this.faxType = FfaxType;
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get type of fax. ink or laser.
	 */
	public String getFaxType()
	{
		return this.faxType;
	}


	/**
	 * Get print outs per minute.
	 */
	public int getPagesPerMinute()
	{
		return this.ppm;
	}


	// SET METHODES

	/**
	 * Sets the type of fax. Ink or laser.
	 */
	public void setFaxType(String FfaxType)
	{
		this.faxType = FfaxType;
	}


	/**
	 * Sets pages per minute for a fax.
	 */
	public void setPagesPerMinute(int Pppm)
	{
		this.ppm = Pppm;
	}
}