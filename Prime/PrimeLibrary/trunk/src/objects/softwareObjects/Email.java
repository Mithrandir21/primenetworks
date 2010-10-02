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

import objects.Software;


public class Email extends Software implements Serializable
{
	/*
	 * Datafields for an abstract webserver These will contain the values of any
	 * webserver object
	 */
	// Supported Operating systems
	private String[] supportedOperatingSystems;

	// Supports POP3
	private boolean supportsPOP3;


	// Supports SMTP
	private boolean supportsSMTP;


	// Supports IMAP
	private boolean supportsIMAP;


	// Supports NNTP
	private boolean supportsNNTP;


	// Supports SSL
	private boolean supportsSSL;


	// Supports webmail
	private boolean supportsWebmail;


	/**
	 * A constructor for the class.
	 * 
	 * @param Name
	 * @param Desc
	 * @param Version
	 */
	public Email(String Name, String Desc, String Version)
	{
		super(Name, Desc, Version);
	}



	/**
	 * Gets the supported {@link OperatingSystem OperatingSystems}.
	 */
	public String[] getSupportedOperatingSystems()
	{

		return this.supportedOperatingSystems;
	}



	/**
	 * Gets whether or not the software supports IMAP.
	 */
	public boolean SupportsIMAP()
	{

		return this.supportsIMAP;
	}



	/**
	 * Gets whether or not the software supports NNTP.
	 */
	public boolean SupportsNNTP()
	{

		return this.supportsNNTP;
	}



	/**
	 * Gets whether or not the software supports POP3.
	 */
	public boolean SupportsPOP3()
	{

		return this.supportsPOP3;
	}



	/**
	 * Gets whether or not the software supports SMTP.
	 */
	public boolean SupportsSMTP()
	{

		return this.supportsSMTP;
	}



	/**
	 * Gets whether or not the software supports SSL.
	 */
	public boolean SupportsSSL()
	{

		return this.supportsSSL;
	}



	/**
	 * Gets whether or not the software supports webmail.
	 */
	public boolean SupportsWebmail()
	{

		return this.supportsWebmail;
	}



	/**
	 * Sets the supported {@link OperatingSystem OperatingSystems}.
	 */
	public void setSupportedOperatingSystems(String[] supportedOperatingSystems)
	{

		this.supportedOperatingSystems = supportedOperatingSystems;
	}



	/**
	 * Sets whether or not the software supports IMAP.
	 */
	public void setSupportsIMAP(boolean supportsIMAP)
	{

		this.supportsIMAP = supportsIMAP;
	}



	/**
	 * Sets whether or not the software supports NNTP.
	 */
	public void setSupportsNNTP(boolean supportsNNTP)
	{

		this.supportsNNTP = supportsNNTP;
	}



	/**
	 * Sets whether or not the software supports POP3.
	 */
	public void setSupportsPOP3(boolean supportsPOP3)
	{

		this.supportsPOP3 = supportsPOP3;
	}



	/**
	 * Sets whether or not the software supports SMTP.
	 */
	public void setSupportsSMTP(boolean supportsSMTP)
	{

		this.supportsSMTP = supportsSMTP;
	}



	/**
	 * Sets whether or not the software supports SSL.
	 */
	public void setSupportsSSL(boolean supportsSSL)
	{

		this.supportsSSL = supportsSSL;
	}



	/**
	 * Sets whether or not the software supports webmail.
	 */
	public void setSupportsWebmail(boolean supportsWebmail)
	{

		this.supportsWebmail = supportsWebmail;
	}
}
