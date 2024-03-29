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


	public OfficeSuite(String Name, String Desc, String Version, String[] OfficeSupportedFormats)
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
