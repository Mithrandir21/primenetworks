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
package objects.softwareObjects;


import java.io.Serializable;
import java.util.Date;

import objects.Software;


/**
 * This class represents an security suite program. It contains information on
 * whether or not the security suite
 * contains a firewall, an antivirus and/or a proxy. It also contains
 * information about the activation of the software,
 * the license of the software and the activation/expiration date for the
 * software.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class SecuritySuite extends Software implements Serializable
{
	/*
	 * Datafields for an Security Suite. These will contain the values of any
	 * Security Suite object.
	 */
	// Supported Operating systems
	private String[] supportedOperatingSystems;

	// Whether or not the security suite has been activated
	private boolean activated;

	// The actual license of the program
	private String license;

	// Whether or not the security suite contains an antivirus
	private boolean hasAntivirus;

	// TO-DO: Set up connection between security suite and antivirus, firewall
	// and proxy

	// Whether or not the security suite contains an firewall
	private boolean hasFirewall;

	// Whether or not the security suite contains an proxy
	private boolean hasProxy;

	// The date of activation
	private Date actDate;

	// The date the license expires
	private Date expDate;


	// Constructor for the class
	/**
	 * Description NEEDED!
	 * 
	 * @param Name
	 *            The name of the security suite software.
	 * @param Desc
	 *            The description of the security suite software.
	 * @param Version
	 *            The version of the security suite software.
	 * @param SShasAntivirus
	 *            Whether or not the security suite contains an antivirus.
	 * @param SShasFirewall
	 *            Whether or not the security suite contains an firewall.
	 * @param SShasProxy
	 *            Whether or not the security suite contains an proxy.
	 */
	public SecuritySuite(String Name, String Desc, String Version,
			boolean SShasAntivirus, boolean SShasFirewall, boolean SShasProxy)
	{
		super(Name, Desc, Version);

		this.hasAntivirus = SShasAntivirus;
		this.hasFirewall = SShasFirewall;
		this.hasProxy = SShasProxy;
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Gets the string containing the names of the supported
	 * {@link OperatingSystem}.
	 */
	public String[] getSupportedOperatingSystems()
	{
		return this.supportedOperatingSystems;
	}


	/**
	 * Get whether or not the suite has been activated.
	 */
	public boolean isActivated()
	{
		return this.activated;
	}


	/**
	 * Get the actual license string of the antivirus software.
	 */
	public String getLicense()
	{
		return this.license;
	}


	/**
	 * Get whether or not the suite has an antivirus.
	 */
	public boolean hasAntivirus()
	{
		return this.hasAntivirus;
	}


	/**
	 * Get whether or not the suite has a firewall.
	 */
	public boolean hasFirewall()
	{
		return this.hasFirewall;
	}


	/**
	 * Get whether or not the suite has a proxy.
	 */
	public boolean hasProxy()
	{
		return this.hasProxy;
	}


	/**
	 * Get the activation date for the suite.
	 */
	public Date getActivationDate()
	{
		return this.actDate;
	}


	/**
	 * Get the expiration date for the suite.
	 */
	public Date getExpirationDate()
	{
		return this.expDate;
	}



	// SET METHODES

	/**
	 * Sets the string containing the names of the supported
	 * {@link OperatingSystem}.
	 */
	public void setSupportedOperatingSystems(String[] supportedOperatingSystems)
	{
		this.supportedOperatingSystems = supportedOperatingSystems;
	}


	/**
	 * Sets the suite to activated.
	 */
	public void setActivated(boolean SSactivated)
	{
		this.activated = SSactivated;
	}


	/**
	 * Set the actual license string of the security suite software.
	 */
	public void setLicense(String AVlicense)
	{
		this.license = AVlicense;
	}


	/**
	 * Get whether or not the suite has an antivirus.
	 */
	public void setHasAntivirus(boolean SShasAntivirus)
	{
		this.hasAntivirus = SShasAntivirus;
	}


	/**
	 * Get whether or not the suite has an firewall.
	 */
	public void sethasFirewall(boolean SShasFirewall)
	{
		this.hasFirewall = SShasFirewall;
	}


	/**
	 * Get whether or not the suite has an proxy.
	 */
	public void sethasProxy(boolean SShasProxy)
	{
		this.hasProxy = SShasProxy;
	}


	/**
	 * Sets the suites activation date.
	 */
	public void setActivationDate(Date SSactivattionDate)
	{
		this.actDate = SSactivattionDate;
	}


	/**
	 * Sets the suites expiration date.
	 */
	public void setExpirationDate(Date SSexpirationDate)
	{
		this.expDate = SSexpirationDate;
	}
}
