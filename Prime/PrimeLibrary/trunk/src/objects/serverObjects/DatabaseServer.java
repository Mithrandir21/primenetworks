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
import objects.softwareObjects.Database;


/**
 * A representation of a database server. This server contains a database, which can be accessed according to rules set
 * up by the network administrator.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class DatabaseServer extends Servers implements Serializable
{

	// Connection to the exact database software that is going to be run on this
	// server
	private Database Database;




	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param DatabaseSWname
	 *            The name of the application that the server is set to run.
	 * @param DatabaseSWdesc
	 *            The description of the application that the server is set to run.
	 * @param DatabaseSWversion
	 *            The version of the application that the server is set to run.
	 */
	public DatabaseServer(String Name, String Desc, String DatabaseSWname,
			String DatabaseSWdesc, String DatabaseSWversion)
	{
		super(Name, Desc);

		// Creates a database software object
		this.Database = new Database(DatabaseSWname, DatabaseSWdesc,
				DatabaseSWversion);
		Software[] sw = { this.Database };
		super.setSoftware(sw);
	}


	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param DatabaseSWname
	 *            The name of the application that the server is set to run.
	 * @param DatabaseSWdesc
	 *            The description of the application that the server is set to run.
	 * @param DatabaseSWversion
	 *            The version of the application that the server is set to run.
	 */
	public DatabaseServer(String Name, String Desc, String[] SupConInt,
			String DatabaseSWname, String DatabaseSWdesc,
			String DatabaseSWversion)
	{
		super(Name, Desc, SupConInt);

		// Creates a database software object
		this.Database = new Database(DatabaseSWname, DatabaseSWdesc,
				DatabaseSWversion);
		Software[] sw = { this.Database };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param DatabaseSWname
	 *            The name of the application that the server is set to run.
	 * @param DatabaseSWdesc
	 *            The description of the application that the server is set to run.
	 * @param DatabaseSWversion
	 *            The version of the application that the server is set to run.
	 */
	public DatabaseServer(String Name, String Desc, Object[] DesktopComponents,
			String DatabaseSWname, String DatabaseSWdesc,
			String DatabaseSWversion)
	{
		super(Name, Desc, DesktopComponents);

		// Creates a database software object
		this.Database = new Database(DatabaseSWname, DatabaseSWdesc,
				DatabaseSWversion);
		Software[] sw = { this.Database };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param DatabaseSWname
	 *            The name of the application that the server is set to run.
	 * @param DatabaseSWdesc
	 *            The description of the application that the server is set to run.
	 * @param DatabaseSWversion
	 *            The version of the application that the server is set to run.
	 */
	public DatabaseServer(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents, String DatabaseSWname,
			String DatabaseSWdesc, String DatabaseSWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// Creates a database software object
		this.Database = new Database(DatabaseSWname, DatabaseSWdesc,
				DatabaseSWversion);
		Software[] sw = { this.Database };
		super.setSoftware(sw);
	}



	/**
	 * Get the software that is set to run on this device.
	 */
	public Database getDatabaseApplication()
	{

		return this.Database;
	}


	/**
	 * Sets the application that is to run on the device.
	 */
	public void setDatabaseApplication(Database Database)
	{

		this.Database = Database;
	}

}
