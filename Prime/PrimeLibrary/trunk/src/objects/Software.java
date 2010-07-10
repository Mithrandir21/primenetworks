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
package objects;


import java.io.Serializable;


/**
 * An abstract super class for all hardware objects in the system, including {@link objects.softwareObjects.Webserver
 * Webserver}, {@link objects.softwareObjects.OperatingSystem Operating System} and {@link objects.softwareObjects.Firewall
 * Firewall}. MUST ADD INFO
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public abstract class Software extends Object implements Serializable
{


	// The software version
	private String version;

	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the software.
	 * @param Desc
	 *            The description of the software.
	 * @param SWversion
	 *            The version of the software. This can contain a NULL pointer, which will then result in the version
	 *            being set to "0.0.1".
	 */
	public Software(String Name, String Desc, String SWversion)
	{
		super(Name, Desc);
		if ( SWversion != null )
		{
			version = SWversion;
		}
		else
		{
			version = "0.0.1";
		}
	}


	/**
	 * Get the version of the software.
	 */
	public String getVersion()
	{

		return version;
	}


	/**
	 * Set the version of the software.
	 */
	public void setVersion(String SWversion)
	{

		version = SWversion;
	}


}
