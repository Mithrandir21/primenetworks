package software;


import java.io.Serializable;

import objects.Software;


public class Database extends Software implements Serializable
{
	/*
	 * Datafields for an abstract webserver These will contain the values of any
	 * webserver object
	 */
	
	// Supported Operating systems
	private String[] supportedOperatingSystems;
	

	// FIXME - Fix database.
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
	 * Sets an array with the names of the supported Operating Systems.
	 * 
	 * @param supportedOperatingSystems
	 *            the supportedOperatingSystems to set
	 */
	public void setSupportedOperatingSystems(String[] supportedOperatingSystems)
	{
		this.supportedOperatingSystems = supportedOperatingSystems;
	}
	
	
	
}
