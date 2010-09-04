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


import objects.Software;


/**
 * This class will represent a generic software object.
 * This object can be anything the use wants because it does not contain any
 * options.
 * 
 * @author Bahram Malaekeh
 */
public class GenericSoftware extends Software
{

	// Supported Operating systems
	private String[] supportedOperatingSystems;


	/**
	 * A constructor for the values needed for the super class({@link Software}
	 * ).
	 * 
	 * @param Name
	 *            The name of the Generic software.
	 * @param Desc
	 *            The description of the software.
	 * @param SWversion
	 *            The version of the software.
	 */
	public GenericSoftware(String Name, String Desc, String SWversion)
	{
		super(Name, Desc, SWversion);
	}




	// GET METHODES



	/**
	 * Get the supported operating systems.
	 * 
	 * @return A array of strings with names of the Operating Systems.
	 */
	public String[] getSupportedOperatingSystems()
	{

		return supportedOperatingSystems;
	}



	// SET METHODES



	/**
	 * Description NEEDED!
	 * 
	 * @param supportedOperatingSystems
	 *            the supportedOperatingSystems to set
	 */
	public void setSupportedOperatingSystems(String[] supportedOperatingSystems)
	{

		this.supportedOperatingSystems = supportedOperatingSystems;
	}
}
