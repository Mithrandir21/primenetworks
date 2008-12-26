package servers;


import java.io.Serializable;

import managment.ArrayManagment;
import objects.Object;
import objects.Servers;
import objects.Software;
import software.Backup;
import exceptions.StringNotFoundInArrayException;


/**
 * A representation of a backup server. This server will backup different
 * information from one or multiple parts of the network. <b>The functionality
 * of the device is yet to be implemented.</b>
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class BackupServer extends Servers implements Serializable
{

	// Supports on-site-access, hence it has a keyboard, mouse and screen
	private boolean supportsOnSiteAccess;

	// Supports remote access
	private boolean supportsRemoteAccess;

	// Supported remote access protocols
	private String[] supportedRemoteAccessProtocols;


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
	 *            The description of the application that the server is set to
	 *            run.
	 * @param BackupSWversion
	 *            The version of the application that the server is set to run.
	 */
	public BackupServer(String Name, String Desc, String BackupSWname,
			String BackupSWdesc, String BackupSWversion)
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
	 *            The description of the application that the server is set to
	 *            run.
	 * @param BackupSWversion
	 *            The version of the application that the server is set to run.
	 */
	public BackupServer(String Name, String Desc, String[] SupConInt,
			String BackupSWname, String BackupSWdesc, String BackupSWversion)
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
	 *            The description of the application that the server is set to
	 *            run.
	 * @param BackupSWversion
	 *            The version of the application that the server is set to run.
	 */
	public BackupServer(String Name, String Desc, Object[] DesktopComponents,
			String BackupSWname, String BackupSWdesc, String BackupSWversion)
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
	 *            The description of the application that the server is set to
	 *            run.
	 * @param BackupSWversion
	 *            The version of the application that the server is set to run.
	 */
	public BackupServer(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents, String BackupSWname,
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
	 * Get the protocols this device supports for access from a remote location.
	 */
	public String[] getSupportedRemoteAccessProtocols()
	{

		return supportedRemoteAccessProtocols;
	}





	/**
	 * Get a boolean saying if this device supports on-site-access, which
	 * implies that the device is atleast connected to a mouse, keyboard and a
	 * monitor.
	 */
	public boolean SupportsOnSiteAccess()
	{

		return supportsOnSiteAccess;
	}





	/**
	 * Get a boolean saying if the device supports remote access.
	 */
	public boolean SupportsRemoteAccess()
	{

		return supportsRemoteAccess;
	}





	/**
	 * Sets the application that is to run on the device.
	 */
	public void setBackupApplication(Backup Backup)
	{

		this.Backup = Backup;
	}





	/**
	 * Set an array of string with the protocols the device supports for remote
	 * access.
	 */
	public void setSupportedRemoteAccessProtocols(
			String[] supportedRemoteAccessProtocols)
	{

		this.supportedRemoteAccessProtocols = supportedRemoteAccessProtocols;
	}





	/**
	 * Set the support of the device for On-Site-Access.
	 */
	public void setSupportsOnSiteAccess(boolean supportsOnSiteAccess)
	{

		this.supportsOnSiteAccess = supportsOnSiteAccess;
	}





	/**
	 * Set the support of the device for remote access.
	 */
	public void setSupportsRemoteAccess(boolean supportsRemoteAccess)
	{

		this.supportsRemoteAccess = supportsRemoteAccess;
	}




	// CLASS METHODES
	/**
	 * Function to add protocols to the the remote access protocols list.
	 * 
	 * @param NewProtocols
	 *            An array of new remote access protocols.
	 */
	public void addRemoteAccessProtocols(String[] NewProtocols)
			throws Exception
	{
		supportedRemoteAccessProtocols = ArrayManagment.addItems(NewProtocols,
				supportedRemoteAccessProtocols);

	}



	/**
	 * Function to remove remote access protocols from the array of remote
	 * access protocols.
	 * 
	 * @param ToBeRemoved
	 *            Remote access protocol to be removed.
	 */
	public void removeRemoteAccessProtocols(String[] ToBeRemoved)
			throws StringNotFoundInArrayException
	{
		supportedRemoteAccessProtocols = ArrayManagment.removeItems(
				ToBeRemoved, supportedRemoteAccessProtocols);
	}
}
