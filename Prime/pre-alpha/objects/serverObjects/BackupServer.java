package objects.serverObjects;


import java.io.Serializable;

import objects.Object;
import objects.Servers;
import objects.Software;
import objects.softwareObjects.Backup;


/**
 * A representation of a backup server. This server will backup different information from one or multiple parts of the
 * network. <b>The functionality of the device is yet to be implemented.</b>
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class BackupServer extends Servers implements Serializable
{



	// Connection to the exact Backup software that is going to be run on this
	// server
	private Backup Backup;



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param BackupSWname
	 *            The name of the application that the server is set to run.
	 * @param BackupSWdesc
	 *            The description of the application that the server is set to run.
	 * @param BackupSWversion
	 *            The version of the application that the server is set to run.
	 */
	public BackupServer(String Name, String Desc, String BackupSWname, String BackupSWdesc, String BackupSWversion)
	{
		super(Name, Desc);

		// Creates a Backup software object
		Backup = new Backup(BackupSWname, BackupSWdesc, BackupSWversion);
		Software[] sw = { Backup };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param BackupSWname
	 *            The name of the application that the server is set to run.
	 * @param BackupSWdesc
	 *            The description of the application that the server is set to run.
	 * @param BackupSWversion
	 *            The version of the application that the server is set to run.
	 */
	public BackupServer(String Name, String Desc, String[] SupConInt, String BackupSWname, String BackupSWdesc,
			String BackupSWversion)
	{
		super(Name, Desc, SupConInt);

		// Creates a Backup software object
		Backup = new Backup(BackupSWname, BackupSWdesc, BackupSWversion);
		Software[] sw = { Backup };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param BackupSWname
	 *            The name of the application that the server is set to run.
	 * @param BackupSWdesc
	 *            The description of the application that the server is set to run.
	 * @param BackupSWversion
	 *            The version of the application that the server is set to run.
	 */
	public BackupServer(String Name, String Desc, Object[] DesktopComponents, String BackupSWname, String BackupSWdesc,
			String BackupSWversion)
	{
		super(Name, Desc, DesktopComponents);

		// Creates a Backup software object
		Backup = new Backup(BackupSWname, BackupSWdesc, BackupSWversion);
		Software[] sw = { Backup };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param BackupSWname
	 *            The name of the application that the server is set to run.
	 * @param BackupSWdesc
	 *            The description of the application that the server is set to run.
	 * @param BackupSWversion
	 *            The version of the application that the server is set to run.
	 */
	public BackupServer(String Name, String Desc, String[] SupConInt, Object[] DesktopComponents, String BackupSWname,
			String BackupSWdesc, String BackupSWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// Creates a Backup software object
		Backup = new Backup(BackupSWname, BackupSWdesc, BackupSWversion);
		Software[] sw = { Backup };
		super.setSoftware(sw);
	}





	/**
	 * Get the software that is set to run on this device.
	 */
	public Backup getBackupApplication()
	{

		return Backup;
	}


	/**
	 * Sets the application that is to run on the device.
	 */
	public void setBackupApplication(Backup Backup)
	{

		this.Backup = Backup;
	}

}
