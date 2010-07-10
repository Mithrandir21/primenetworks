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
 * This class represents a printer. This device can be connected to any device that supports the conncetion type of this
 * device. It contains information on what kind of capability the printer has and what kind of connection interface is
 * has.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Printer extends ExternalHardware implements Serializable
{

	// Type of printer. ink or laser
	private String printerType;

	// Pages per minute(up to)
	private int ppm;



	/**
	 * Constructor of the printer class.
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
	public Printer(String Name, String Desc, String PprinterType,
			String[] PconnectionInterfaces)
	{
		super(Name, Desc, PconnectionInterfaces);

		printerType = PprinterType;
	}


	/**
	 * Constructor of the printer class.
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
	 *            The {@link Motherboard} of the object.
	 */
	public Printer(String Name, String Desc, String PprinterType,
			String[] PconnectionInterfaces, Motherboard objectMB)
	{
		super(Name, Desc, PconnectionInterfaces, objectMB);

		printerType = PprinterType;
	}



	/**
	 * Constructor of the printer class.
	 * 
	 * @param Name
	 *            The name of the printer.
	 * @param Desc
	 *            The description of the printer.
	 * @param PprinterType
	 *            Type of printer. ink or laser.
	 * @param objectMB
	 *            The {@link Motherboard} of the object.
	 */
	public Printer(String Name, String Desc, String PprinterType,
			Motherboard objectMB)
	{
		super(Name, Desc, objectMB);

		printerType = PprinterType;
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get type of printer. ink or laser.
	 */
	public String getPrinterType()
	{
		return printerType;
	}


	/**
	 * Get print outs per minute.
	 */
	public int getPagesPerMinute()
	{
		return ppm;
	}


	// SET METHODES

	/**
	 * Sets the type of printer. Ink or laser.
	 */
	public void setPrinterType(String PprinterType)
	{
		printerType = PprinterType;
	}



	/**
	 * Sets pages per minute for a printer.
	 */
	public void setPagesPerMinute(int Pppm)
	{
		ppm = Pppm;
	}
}
