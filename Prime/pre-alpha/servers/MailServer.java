package servers;


import java.io.Serializable;

import managment.ArrayManagment;
import objects.Object;
import objects.Servers;
import software.Email;
import exceptions.StringNotFoundInArrayException;


/**
 * A representation of a email server. This server sends and recieves email,
 * depending on the application running on the server.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class MailServer extends Servers implements Serializable
{

	// Supports on-site-access, hence it has a keyboard, mouse and screen
	private boolean supportsOnSiteAccess;

	// Supports remote access
	private boolean supportsRemoteAccess;

	// Supported remote access protocols
	private String[] supportedRemoteAccessProtocols;


	// Connection to the exact Email software that is going to be run on this
	// server
	private Email Email;



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param EmailSWname
	 *            The name of the application that the server is set to run.
	 * @param EmailSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param EmailSWversion
	 *            The version of the application that the server is set to run.
	 */
	public MailServer(String Name, String Desc, String EmailSWname, String EmailSWdesc,
			String EmailSWversion)
	{
		super(Name, Desc);

		// Creates a Email software object
		Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
	}


	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param EmailSWname
	 *            The name of the application that the server is set to run.
	 * @param EmailSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param EmailSWversion
	 *            The version of the application that the server is set to run.
	 */
	public MailServer(String Name, String Desc, String[] SupConInt, String EmailSWname,
			String EmailSWdesc, String EmailSWversion)
	{
		super(Name, Desc, SupConInt);

		// Creates a Email software object
		Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param EmailSWname
	 *            The name of the application that the server is set to run.
	 * @param EmailSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param EmailSWversion
	 *            The version of the application that the server is set to run.
	 */
	public MailServer(String Name, String Desc, Object[] DesktopComponents, String EmailSWname,
			String EmailSWdesc, String EmailSWversion)
	{
		super(Name, Desc, DesktopComponents);

		// Creates a Email software object
		Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param EmailSWname
	 *            The name of the application that the server is set to run.
	 * @param EmailSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param EmailSWversion
	 *            The version of the application that the server is set to run.
	 */
	public MailServer(String Name, String Desc, String[] SupConInt, Object[] DesktopComponents,
			String EmailSWname, String EmailSWdesc, String EmailSWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// Creates a Email software object
		Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
	}





	/**
	 * Get the software that is set to run on this device.
	 */
	public Email getEmailApplication()
	{

		return Email;
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
	public void setEmailApplication(Email Email)
	{

		this.Email = Email;
	}





	/**
	 * Set an array of string with the protocols the device supports for remote
	 * access.
	 */
	public void setSupportedRemoteAccessProtocols(String[] supportedRemoteAccessProtocols)
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
	public void addRemoteAccessProtocols(String[] NewProtocols) throws Exception
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
		supportedRemoteAccessProtocols = ArrayManagment.removeItems(ToBeRemoved,
				supportedRemoteAccessProtocols);
	}
}
