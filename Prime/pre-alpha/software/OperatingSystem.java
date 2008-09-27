package software;


import java.io.Serializable;

import objects.Software;


public class OperatingSystem extends Software implements Serializable
{
	/*
	 * Datafields for an abstract webserver These will contain the values of any
	 * webserver object
	 */


	// Supported File systems
	private String[] supportedFS;


	// Has encrypted filesystem
	private boolean encryptedFileSystem;


	// Has GUI
	private boolean hasGUI;



	public OperatingSystem(String Name, String Desc, String Version)
	{
		super(Name, Desc, Version);
	}
}
