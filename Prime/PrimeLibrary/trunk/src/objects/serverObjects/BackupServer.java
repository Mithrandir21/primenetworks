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
import objects.softwareObjects.Backup;


/**
 * A representation of a backup server. This server will backup different information from one or multiple parts of the
 * network. <b>The functionality of the device is yet to be implemented.</b>
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class BackupServer extends Servers implements Serializable
{



	// Connection to the exact Backup software that is going to be run on this
	// server
	private Backup Backup;



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param BackupSWname
	 *            The name of the application that the server is set to run.
	 * @param BackupSWdesc
	 *            The description of the application that the server is set to run.
	 * @param BackupSWversion
	 *            The version of the application that the server is set to run.
	 */
	public BackupServer(String Name, String Desc, String BackupSWname,
			String BackupSWdesc, String BackupSWversion)
	{
		super(Name, Desc);

		// Creates a Backup software object
		Backup = new Backup(BackupSWname, BackupSWdesc, BackupSWversion);
		Software[] sw = { Backup };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param BackupSWname
	 *            The name of the application that the server is set to run.
	 * @param BackupSWdesc
	 *            The description of the application that the server is set to run.
	 * @param BackupSWversion
	 *            The version of the application that the server is set to run.
	 */
	public BackupServer(String Name, String Desc, String[] SupConInt,
			String BackupSWname, String BackupSWdesc, String BackupSWversion)
	{
		super(Name, Desc, SupConInt);

		// Creates a Backup software object
		Backup = new Backup(BackupSWname, BackupSWdesc, BackupSWversion);
		Software[] sw = { Backup };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param BackupSWname
	 *            The name of the application that the server is set to run.
	 * @param BackupSWdesc
	 *            The description of the application that the server is set to run.
	 * @param BackupSWversion
	 *            The version of the application that the server is set to run.
	 */
	public BackupServer(String Name, String Desc, Object[] DesktopComponents,
			String BackupSWname, String BackupSWdesc, String BackupSWversion)
	{
		super(Name, Desc, DesktopComponents);

		// Creates a Backup software object
		Backup = new Backup(BackupSWname, BackupSWdesc, BackupSWversion);
		Software[] sw = { Backup };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param BackupSWname
	 *            The name of the application that the server is set to run.
	 * @param BackupSWdesc
	 *            The description of the application that the server is set to run.
	 * @param BackupSWversion
	 *            The version of the application that the server is set to run.
	 */
	public BackupServer(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents, String BackupSWname,
			String BackupSWdesc, String BackupSWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// Creates a Backup software object
		Backup = new Backup(BackupSWname, BackupSWdesc, BackupSWversion);
		Software[] sw = { Backup };
		super.setSoftware(sw);
	}





	/**
	 * Get the software that is set to run on this device.
	 */
	public Backup getBackupApplication()
	{

		return Backup;
	}


	/**
	 * Sets the application that is to run on the device.
	 */
	public void setBackupApplication(Backup Backup)
	{

		this.Backup = Backup;
	}

}
