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
package objects.softwareObjects;


import java.io.Serializable;
import java.util.Date;

import objects.Software;


/**
 * This class represents an antivirus program. It contains information on whether or not the license has been activated
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Antivirus extends Software implements Serializable
{
	/*
	 * Datafields for an abstract antivirus These will contain the values of any antivirus object
	 */

	// Supported Operating systems
	private String[] supportedOperatingSystems;

	// Whether or not the antivirus has been activated
	private boolean activated;

	// The actual license of the program
	private String license;

	// The date of activation
	private Date actDate;

	// The date the license expires
	private Date expDate;




	/**
	 * Constructor of the antivirus software class. This will represent a single program with a singel license.
	 * 
	 * @param Name
	 *            The name of the antivirus software.
	 * @param Desc
	 *            The description of the antivirus software.
	 * @param Version
	 *            The version of the antivirus software.
	 */
	public Antivirus(String Name, String Desc, String Version)
	{
		super(Name, Desc, Version);
	}



	// Get and Set methodes for retrieving all datafields.

	// GET METHODES


	/**
	 * Returns an array with the names of the supported Operating Systems.
	 * 
	 * @return the supportedOperatingSystems
	 */
	public String[] getSupportedOperatingSystems()
	{
		return supportedOperatingSystems;
	}

	/**
	 * Get whether or not the antivirus has been activated.
	 */
	public boolean getIsActivated()
	{
		return activated;
	}


	/**
	 * Get the actual license string of the antivirus software.
	 */
	public String getLicense()
	{
		return license;
	}


	/**
	 * Get the activation date for the antivirus.
	 */
	public Date getActivationDate()
	{
		return actDate;
	}


	/**
	 * Get the expiration date for the antivirus.
	 */
	public Date getExpirationDate()
	{
		return expDate;
	}


	// SET METHODES


	/**
	 * Sets an array with the names of the supported Operating Systems.
	 * 
	 * @param supportedOperatingSystems
	 *            the supportedOperatingSystems to set
	 */
	public void setSupportedOperatingSystems(String[] supportedOperatingSystems)
	{
		this.supportedOperatingSystems = supportedOperatingSystems;
	}


	/**
	 * Sets the antivirus to activated.
	 */
	public void setActivated(boolean AVactivated)
	{
		activated = AVactivated;
	}


	/**
	 * Set the actual license string of the antivirus software.
	 */
	public void setLicense(String AVlicense)
	{
		license = AVlicense;
	}


	/**
	 * Sets the antiviruAV activation date.
	 */
	public void setActivationDate(Date AVactivattionDate)
	{
		actDate = AVactivattionDate;
	}


	/**
	 * Sets the antiviruAV expiration date.
	 */
	public void setExpirationDate(Date AVexpirationDate)
	{
		expDate = AVexpirationDate;
	}
}
