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

import objects.Software;


/**
 * This class represents an officesuite program. It contains information on what kind of formats are supported. <br>
 * <br>
 * FIXME - Work is needed on this class. More functions and information.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class OfficeSuite extends Software implements Serializable
{
	/*
	 * Datafields for an abstract webserver These will contain the values of any webserver object
	 */
	// Supported Operating systems
	private String[] supportedOperatingSystems;

	// The formats supported by the Office suite
	private String[] supportedFormats;


	public OfficeSuite(String Name, String Desc, String Version,
			String[] OfficeSupportedFormats)
	{
		super(Name, Desc, Version);
		if ( OfficeSupportedFormats[0] != null )
		{
			supportedFormats = OfficeSupportedFormats;
		}
		else
		{
			String[] formats = new String[15];
			formats[0] = ".doc";
			formats[1] = ".txt";
			formats[2] = ".xml";
			formats[3] = ".xls";
			formats[4] = ".ppt";
			formats[5] = ".rtf";
			// TODO - Find more supported formats. Based on Microsoft Office

			supportedFormats = formats;
		}
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
	 * Sets an array with the names of the supported Operating Systems.
	 * 
	 * @param supportedOperatingSystems
	 *            the supportedOperatingSystems to set
	 */
	public void setSupportedOperatingSystems(String[] supportedOperatingSystems)
	{
		this.supportedOperatingSystems = supportedOperatingSystems;
	}


	// TODO - Method to add to the supported formats array
}
