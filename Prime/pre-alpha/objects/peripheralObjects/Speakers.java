package objects.peripheralObjects;


import java.io.Serializable;

import objects.ExternalHardware;


/**
 * This class represents computer speakers. This device can be connected to any
 * device that supports the conncetion type of this device. It contains
 * information on what kind of capability the speakers have and what kind of
 * connection interface they have.
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