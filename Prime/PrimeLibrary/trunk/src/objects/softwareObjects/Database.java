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


public class Database extends Software implements Serializable
{
	// Supported Operating systems
	private String[] supportedOperatingSystems;

	// The max size of the Database(in GB)
	private int maxDBsize;


	public Database(String Name, String Desc, String Version)
	{
		super(Name, Desc, Version);
	}



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
	 * Returns the max size of the Database(in GB).
	 * 
	 * @return the maxDBsize
	 */
	public int getMaxDBsize()
	{
		return maxDBsize;
	}



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
	 * Sets the max size of the Database(in GB).
	 * 
	 * @param maxDBsize
	 *            the maxDBsize to set
	 */
	public void setMaxDBsize(int maxDBsize)
	{
		this.maxDBsize = maxDBsize;
	}



}
