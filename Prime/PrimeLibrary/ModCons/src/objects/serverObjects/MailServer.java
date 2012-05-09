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
package objects.serverObjects;


import java.io.Serializable;

import objects.Object;
import objects.Servers;
import objects.Software;
import objects.softwareObjects.Email;


/**
 * A representation of a email server. This server sends and recieves email, depending on the application running on the
 * server.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class MailServer extends Servers implements Serializable
{
	// Connection to the exact Email software that is going to be run on this
	// server
	private Email Email;



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param EmailSWname
	 *            The name of the application that the server is set to run.
	 * @param EmailSWdesc
	 *            The description of the application that the server is set to run.
	 * @param EmailSWversion
	 *            The version of the application that the server is set to run.
	 */
	public MailServer(String Name, String Desc, String EmailSWname,
			String EmailSWdesc, String EmailSWversion)
	{
		super(Name, Desc);

		// Creates a Email software object
		this.Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		Software[] sw = { this.Email };
		super.setSoftware(sw);
	}


	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param EmailSWname
	 *            The name of the application that the server is set to run.
	 * @param EmailSWdesc
	 *            The description of the application that the server is set to run.
	 * @param EmailSWversion
	 *            The version of the application that the server is set to run.
	 */
	public MailServer(String Name, String Desc, String[] SupConInt,
			String EmailSWname, String EmailSWdesc, String EmailSWversion)
	{
		super(Name, Desc, SupConInt);

		// Creates a Email software object
		this.Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		Software[] sw = { this.Email };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param EmailSWname
	 *            The name of the application that the server is set to run.
	 * @param EmailSWdesc
	 *            The description of the application that the server is set to run.
	 * @param EmailSWversion
	 *            The version of the application that the server is set to run.
	 */
	public MailServer(String Name, String Desc, Object[] DesktopComponents,
			String EmailSWname, String EmailSWdesc, String EmailSWversion)
	{
		super(Name, Desc, DesktopComponents);

		// Creates a Email software object
		this.Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		Software[] sw = { this.Email };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param EmailSWname
	 *            The name of the application that the server is set to run.
	 * @param EmailSWdesc
	 *            The description of the application that the server is set to run.
	 * @param EmailSWversion
	 *            The version of the application that the server is set to run.
	 */
	public MailServer(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents, String EmailSWname, String EmailSWdesc,
			String EmailSWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// Creates a Email software object
		this.Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		Software[] sw = { this.Email };
		super.setSoftware(sw);
	}





	/**
	 * Get the software that is set to run on this device.
	 */
	public Email getEmailApplication()
	{

		return this.Email;
	}

	/**
	 * Sets the application that is to run on the device.
	 */
	public void setEmailApplication(Email Email)
	{

		this.Email = Email;
	}

}
