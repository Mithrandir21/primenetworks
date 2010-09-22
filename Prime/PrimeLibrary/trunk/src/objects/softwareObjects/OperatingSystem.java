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

import objects.Clients;
import objects.Servers;
import objects.Software;


/**
 * This class represents a Operating system that will be installed on {@link Clients} and {@link Servers}. Other {@link Software}
 * will be matched to this operating system to check for compatibility. It contains variables that say
 * whether or not the OS supports different file systems and 64-bit.
 * 
 * @author Bahram Malaekeh
 */
public class OperatingSystem extends Software implements Serializable
{
	// Has encrypted file system
	private boolean encryptedFileSystem;


	// Has GUI
	private boolean hasGUI;


	// Is 64-bit
	private boolean is64bit;


	/**
	 * A constructor for the class that takes the name, description and version of the OS.
	 * 
	 * @param Name
	 *            The name of the Operating System.
	 * @param Desc
	 *            The description of the Operating System.
	 * @param Version
	 *            The version of the Operating System.
	 */
	public OperatingSystem(String Name, String Desc, String Version)
	{
		super(Name, Desc, Version);
	}


	// GETTERS

	/**
	 * Gets a boolean on whether or not the system support encryption on the File System.
	 */
	public boolean isEncryptedFileSystem()
	{
		return this.encryptedFileSystem;
	}



	/**
	 * Gets a boolean on whether or not the system has a GUI.
	 */
	public boolean isHasGUI()
	{
		return this.hasGUI;
	}



	/**
	 * Gets a boolean on whether or not the system supports 64-bit architecture.
	 */
	public boolean isIs64bit()
	{
		return this.is64bit;
	}



	// SETTERS


	/**
	 * Sets a boolean on whether or not the system support encryption on the File System.
	 */
	public void setEncryptedFileSystem(boolean encryptedFileSystem)
	{
		this.encryptedFileSystem = encryptedFileSystem;
	}



	/**
	 * Sets a boolean on whether or not the system has a GUI.
	 */
	public void setHasGUI(boolean hasGUI)
	{
		this.hasGUI = hasGUI;
	}



	/**
	 * Sets a boolean on whether or not the system supports 64-bit architecture.
	 */
	public void setIs64bit(boolean is64bit)
	{
		this.is64bit = is64bit;
	}
}
