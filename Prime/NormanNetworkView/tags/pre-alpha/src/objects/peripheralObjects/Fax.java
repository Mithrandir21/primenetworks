package objects.peripheralObjects;


import java.io.Serializable;

import objects.ExternalHardware;


/**
 * This class represents a fax machine. This device can be connected to any device that supports the conncetion type of
 * this device. It contains information on what kind of capability the fax machine has and what kind of connection
 * interface is has.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Fax extends ExternalHardware implements Serializable
{

	// Type of fax. ink or laser
	private String faxType;


	// Pages per minute(up to)
	private int ppm;



	/**
	 * Constructor of the fax class.
	 * 
	 * @param Name
	 *            The name of the fax.
	 * @param Desc
	 *            The description of the fax.
	 * @param FfaxType
	 *            Type of fax. ink or laser.
	 * @param FconnectionInterfaces
	 *            Connection interfaces supported by the fax. An array of Strings.
	 */
	public Fax(String Name, String Desc, String FfaxType, String[] FconnectionInterfaces)
	{
		super(Name, Desc, FconnectionInterfaces);

		faxType = FfaxType;
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get type of fax. ink or laser.
	 */
	public String getFaxType()
	{
		return faxType;
	}


	/**
	 * Get print outs per minute.
	 */
	public int getPagesPerMinute()
	{
		return ppm;
	}


	// SET METHODES

	/**
	 * Sets the type of fax. Ink or laser.
	 */
	public void setFaxType(String FfaxType)
	{
		faxType = FfaxType;
	}


	/**
	 * Sets pages per minute for a fax.
	 */
	public void setPagesPerMinute(int Pppm)
	{
		ppm = Pppm;
	}
}