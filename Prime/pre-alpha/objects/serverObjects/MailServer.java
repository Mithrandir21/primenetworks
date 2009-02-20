package objects.serverObjects;


import java.io.Serializable;

import managment.ArrayManagment;
import objects.Object;
import objects.Servers;
import objects.Software;
import objects.softwareObjects.Email;
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
	public MailServer(String Name, String Desc, String EmailSWname,
			String EmailSWdesc, String EmailSWversion)
	{
		super(Name, Desc);

		// Creates a Email software object
		Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		Software[] sw = { Email };
		super.setSoftware(sw);
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
	public MailServer(String Name, String Desc, String[] SupConInt,
			String EmailSWname, String EmailSWdesc, String EmailSWversion)
	{
		super(Name, Desc, SupConInt);

		// Creates a Email software object
		Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		Software[] sw = { Email };
		super.setSoftware(sw);
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
	public MailServer(String Name, String Desc, Object[] DesktopComponents,
			String EmailSWname, String EmailSWdesc, String EmailSWversion)
	{
		super(Name, Desc, DesktopComponents);

		// Creates a Email software object
		Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		Software[] sw = { Email };
		super.setSoftware(sw);
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
	public MailServer(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents, String EmailSWname, String EmailSWdesc,
			String EmailSWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// Creates a Email software object
		Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		Software[] sw = { Email };
		super.setSoftware(sw);
	}





	/**
	 * Get the software that is set to run on this device.
	 */
	public Email getEmailApplication()
	{

		return Email;
	}

	/**
	 * Sets the application that is to run on the device.
	 */
	public void setEmailApplication(Email Email)
	{

		this.Email = Email;
	}

}
