/**
 * 
 */
package objects.serverObjects;


import java.io.Serializable;

import objects.Object;
import objects.Servers;


/**
 * @author Bam
 */
public class PrinterServer extends Servers implements Serializable
{



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param printerSWname
	 *            The name of the application that the server is set to run.
	 * @param printerSWdesc
	 *            The description of the application that the server is set to run.
	 * @param printerWversion
	 *            The version of the application that the server is set to run.
	 */
	public PrinterServer(String Name, String Desc, String printerSWname, String printerSWdesc, String printerWversion)
	{
		super(Name, Desc);

		// // Creates a Email software object
		// Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		// Software[] sw = { Email };
		// super.setSoftware(sw);
	}


	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param printerSWname
	 *            The name of the application that the server is set to run.
	 * @param printerSWdesc
	 *            The description of the application that the server is set to run.
	 * @param printerSWversion
	 *            The version of the application that the server is set to run.
	 */
	public PrinterServer(String Name, String Desc, String[] SupConInt, String printerSWname, String printerSWdesc,
			String printerSWversion)
	{
		super(Name, Desc, SupConInt);

		// // Creates a Email software object
		// Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		// Software[] sw = { Email };
		// super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param printerSWname
	 *            The name of the application that the server is set to run.
	 * @param printerSWdesc
	 *            The description of the application that the server is set to run.
	 * @param printerSWversion
	 *            The version of the application that the server is set to run.
	 */
	public PrinterServer(String Name, String Desc, Object[] DesktopComponents, String printerSWname,
			String printerSWdesc, String printerSWversion)
	{
		super(Name, Desc, DesktopComponents);

		// // Creates a Email software object
		// Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		// Software[] sw = { Email };
		// super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param printerSWname
	 *            The name of the application that the server is set to run.
	 * @param printerSWdesc
	 *            The description of the application that the server is set to run.
	 * @param printerSWversion
	 *            The version of the application that the server is set to run.
	 */
	public PrinterServer(String Name, String Desc, String[] SupConInt, Object[] DesktopComponents,
			String printerSWname, String printerSWdesc, String printerSWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// // Creates a Email software object
		// Email = new Email(EmailSWname, EmailSWdesc, EmailSWversion);
		// Software[] sw = { Email };
		// super.setSoftware(sw);
	}
}
