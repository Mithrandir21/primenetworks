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
package objects.peripheralObjects;


import java.io.Serializable;

import objects.ExternalHardware;


/**
 * This class represents computer speakers. This device can be connected to any device that supports the conncetion type
 * of this device. It contains information on what kind of capability the speakers have and what kind of connection
 * interface they have.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Speakers extends ExternalHardware implements Serializable
{

	// Number of satellites, actual speakers
	private int satellites;

	// Boolean to check whether or not a speakers are a laptop speakers
	private boolean isLaptopSpeakers;



	/**
	 * Constructor of the speakers class.
	 * 
	 * @param Name
	 *            The name of the speakers.
	 * @param Desc
	 *            The description of the speakers.
	 * @param Speakerssatellites
	 *            The number of satellites, actual speakers.
	 */
	public Speakers(String Name, String Desc, int Speakerssatellites,
			String[] ConnectionInterfaces)
	{
		super(Name, Desc, ConnectionInterfaces);

		satellites = Speakerssatellites;
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get the number of satellites the system has.
	 */
	public int getSpeakersSatellites()
	{
		return satellites;
	}


	/**
	 * Get info on whether or not a speakers are a laptop speakers.
	 */
	public boolean isLaptopSpeakers()
	{
		return isLaptopSpeakers;
	}


	// SET METHODES

	/**
	 * Sets the number of satellites the system has.
	 */
	public void setSpeakersSatellites(int SpeakersSatellites)
	{
		satellites = SpeakersSatellites;
	}


	/**
	 * Sets info on whether or not a speakers are a laptop speakers.
	 */
	public void setIsLaptopSpeakers(boolean info)
	{
		isLaptopSpeakers = info;
	}
}