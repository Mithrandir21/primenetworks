package software;


import java.io.Serializable;

import objects.Software;


public class Backup extends Software implements Serializable
{
	/*
	 * Datafields for an abstract webserver These will contain the values of any
	 * webserver object
	 */

	// FIXME - Fix backup.
	public Backup(String Name, String Desc, String Version)
	{
		super(Name, Desc, Version);
	}
}
