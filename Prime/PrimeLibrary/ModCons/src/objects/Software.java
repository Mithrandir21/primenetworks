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
package objects;


import java.io.Serializable;

import objects.softwareObjects.OperatingSystem;


/**
 * An abstract super class for all hardware objects in the system, including
 * {@link objects.softwareObjects.Webserver
 * Webserver}, {@link objects.softwareObjects.OperatingSystem Operating System}
 * and {@link objects.softwareObjects.Firewall
 * Firewall}. MUST ADD INFO
 * 
 * @author Bahram Malaekeh
 * @version 0.3
 */
public abstract class Software extends Object implements Serializable
{

	// The base of the OperatingSystem
	private base base;

	// The Softwares File System
	private fileSystems[] fs;

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
	 *            The version of the software. This can contain a NULL pointer,
	 *            which will then result in the version
	 *            being set to "0.0.1".
	 */
	protected Software(String Name, String Desc, String SWversion)
	{
		super(Name, Desc);
		if ( SWversion != null )
		{
			this.version = SWversion;
		}
		else
		{
			this.version = "0.1";
		}
	}

	// GETTERS

	/**
	 * Get the version of the software.
	 */
	public String getVersion()
	{

		return this.version;
	}


	/**
	 * Gets the {@link base} of the {@link Software}.
	 */
	public base getBase()
	{
		return this.base;
	}


	/**
	 * Gets the {@link fileSystems} of the {@link Software}.
	 */
	public fileSystems[] getFs()
	{
		return this.fs;
	}

	// SETTERS


	/**
	 * Set the version of the software.
	 */
	public void setVersion(String SWversion)
	{

		this.version = SWversion;
	}



	/**
	 * Sets the {@link base} of the {@link Software}.
	 */
	public void setBase(base osBase)
	{
		this.base = osBase;
	}




	/**
	 * Sets the {@link fileSystems} of the {@link Software}.
	 */
	public void setFs(fileSystems[] fs)
	{
		this.fs = fs;
	}




	/**
	 * The base of the {@link Software}.
	 * 
	 * @author Bahram Malaekeh
	 */
	public static enum base
	{
		WINDOWS, GNU_LINUX, UNIX, MAC
	}



	/**
	 * The file system of the {@link Software}.
	 * On a {@link OperatingSystem} this will represent the supported File
	 * Systems(FS), but on other {@link Software} objects it
	 * will represent the FS the software supports.
	 * 
	 * @author Bahram Malaekeh
	 * 
	 */
	public enum fileSystems
	{
		FAT16, FAT32, exFAT, NTFS, HFS, HFSplus, HPFS, UFS, EXT2, EXT3, EXT4, XFS, BTRFS, ISO9660, ODS5, JFS, VxFS, ZFS, ReiserFS, SWAP, UDF
	}
}
