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


/**
 * @author Bam
 */
public class PrinterServer extends Servers implements Serializable
{



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param printerSWname
	 *            The name of the application that the server is set to run.
	 * @param printerSWdesc
	 *            The description of the application that the server is set to run.
	 * @param printerWversion
	 *            The version of the application that the server is set to run.
	 */
	public PrinterServer(String Name, String Desc, String printerSWname,
			String printerSWdesc, String printerWversion)
	{
		super(Name, Desc);

		// // Creates a Email software object
		// Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		// Software[] sw = { Email };
		// super.setSoftware(sw);
	}


	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param printerSWname
	 *            The name of the application that the server is set to run.
	 * @param printerSWdesc
	 *            The description of the application that the server is set to run.
	 * @param printerSWversion
	 *            The version of the application that the server is set to run.
	 */
	public PrinterServer(String Name, String Desc, String[] SupConInt,
			String printerSWname, String printerSWdesc, String printerSWversion)
	{
		super(Name, Desc, SupConInt);

		// // Creates a Email software object
		// Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		// Software[] sw = { Email };
		// super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param printerSWname
	 *            The name of the application that the server is set to run.
	 * @param printerSWdesc
	 *            The description of the application that the server is set to run.
	 * @param printerSWversion
	 *            The version of the application that the server is set to run.
	 */
	public PrinterServer(String Name, String Desc, Object[] DesktopComponents,
			String printerSWname, String printerSWdesc, String printerSWversion)
	{
		super(Name, Desc, DesktopComponents);

		// // Creates a Email software object
		// Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		// Software[] sw = { Email };
		// super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param printerSWname
	 *            The name of the application that the server is set to run.
	 * @param printerSWdesc
	 *            The description of the application that the server is set to run.
	 * @param printerSWversion
	 *            The version of the application that the server is set to run.
	 */
	public PrinterServer(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents, String printerSWname,
			String printerSWdesc, String printerSWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// // Creates a Email software object
		// Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		// Software[] sw = { Email };
		// super.setSoftware(sw);
	}
}
