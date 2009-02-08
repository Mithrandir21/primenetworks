package servers;


import java.io.Serializable;

import managment.ArrayManagment;
import objects.Object;
import objects.Servers;
import objects.Software;
import software.Database;
import exceptions.StringNotFoundInArrayException;


/**
 * A representation of a database server. This server contains a database, which
 * can be accessed according to rules set up by the network administrator.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class DatabaseServer extends Servers implements Serializable
{

	// Connection to the exact database software that is going to be run on this
	// server
	private Database Database;




	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param DatabaseSWname
	 *            The name of the application that the server is set to run.
	 * @param DatabaseSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param DatabaseSWversion
	 *            The version of the application that the server is set to run.
	 */
	public DatabaseServer(String Name, String Desc, String DatabaseSWname,
			String DatabaseSWdesc, String DatabaseSWversion)
	{
		super(Name, Desc);

		// Creates a database software object
		Database = new Database(DatabaseSWname, DatabaseSWdesc,
				DatabaseSWversion);
		Software[] sw = { Database };
		super.setSoftware(sw);
	}


	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param DatabaseSWname
	 *            The name of the application that the server is set to run.
	 * @param DatabaseSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param DatabaseSWversion
	 *            The version of the application that the server is set to run.
	 */
	public DatabaseServer(String Name, String Desc, String[] SupConInt,
			String DatabaseSWname, String DatabaseSWdesc,
			String DatabaseSWversion)
	{
		super(Name, Desc, SupConInt);

		// Creates a database software object
		Database = new Database(DatabaseSWname, DatabaseSWdesc,
				DatabaseSWversion);
		Software[] sw = { Database };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param DatabaseSWname
	 *            The name of the application that the server is set to run.
	 * @param DatabaseSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param DatabaseSWversion
	 *            The version of the application that the server is set to run.
	 */
	public DatabaseServer(String Name, String Desc, Object[] DesktopComponents,
			String DatabaseSWname, String DatabaseSWdesc,
			String DatabaseSWversion)
	{
		super(Name, Desc, DesktopComponents);

		// Creates a database software object
		Database = new Database(DatabaseSWname, DatabaseSWdesc,
				DatabaseSWversion);
		Software[] sw = { Database };
		super.setSoftware(sw);
	}



	/**
	 * The constructor for the device.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param DatabaseSWname
	 *            The name of the application that the server is set to run.
	 * @param DatabaseSWdesc
	 *            The description of the application that the server is set to
	 *            run.
	 * @param DatabaseSWversion
	 *            The version of the application that the server is set to run.
	 */
	public DatabaseServer(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents, String DatabaseSWname,
			String DatabaseSWdesc, String DatabaseSWversion)
	{
		super(Name, Desc, SupConInt, DesktopComponents);

		// Creates a database software object
		Database = new Database(DatabaseSWname, DatabaseSWdesc,
				DatabaseSWversion);
		Software[] sw = { Database };
		super.setSoftware(sw);
	}



	/**
	 * Get the software that is set to run on this device.
	 */
	public Database getDatabaseApplication()
	{

		return Database;
	}


	/**
	 * Sets the application that is to run on the device.
	 */
	public void setDatabaseApplication(Database Database)
	{

		this.Database = Database;
	}

}
