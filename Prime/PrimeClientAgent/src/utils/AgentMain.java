/**
 * 
 */
package utils;


import java.io.File;
import java.sql.Connection;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import net.xeoh.plugins.base.util.PluginManagerUtil;
import transmission.PluginOutputTransmitter;
import agentPluginInterface.PrimeAgentPluginInterface;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class AgentMain
{
	public static ScheduledThreadPoolExecutor scheduler;

	public static String serverurl = "127.0.0.1";

	public static int serverport = 58468;

	public static boolean canConnectToServer = false;

	public static PluginOutputTransmitter pluginOutTrans;

	public static UUID agentID;

	/**
	 * TODO - Description
	 */
	public static void main(String[] args)
	{
		// If the initiation of the database is false
		if ( !initDatabase() )
		{
			System.out.println("DB initiation went wrong. Fatal!");
			System.exit(1);
		}

		// Creates the transmitter, that initiates the server connection
		pluginOutTrans = new PluginOutputTransmitter();

		if ( !canConnectToServer )
		{
			System.out.println("Could not connect to server: " + serverurl
					+ " port: " + serverport + ". Fatal!");
			System.exit(2);
		}

		// Creates an Executor schedule for all the runnable plugins
		scheduler = new ScheduledThreadPoolExecutor(2);

		PluginManager pm = PluginManagerFactory.createPluginManager();

		// Loads all plugins in the plugins folder
		pm.addPluginsFrom(new File("plugins/").toURI());

		// Creates a plugin manager "helper"
		PluginManagerUtil pmu = new PluginManagerUtil(pm);

		/**
		 * Gets a Collection of all plugins that implement the
		 * PrimeAgentPluginInterface, which also implements Runnable (where the
		 * data gathering will take place).
		 */
		Collection<PrimeAgentPluginInterface> plugins = pmu
				.getPlugins(PrimeAgentPluginInterface.class);

		// Get an Iterator for the Plugin Collection
		Iterator<PrimeAgentPluginInterface> itr = plugins.iterator();

		while ( itr.hasNext() )
		{
			// Gets a single plugin
			PrimeAgentPluginInterface plugin = (PrimeAgentPluginInterface) itr
					.next();

			// Add the Runnable plugin to the Executor schedule
			scheduler.schedule(plugin, 2, TimeUnit.SECONDS);
		}

		// Calls an orderly 'shutdown' of all the tasks running. (Will NOT
		// terminate, but will disallow additional tasks from being added.)
		scheduler.shutdown();


		while ( !scheduler.isTerminated() )
		{
			// wait...
		}


		// Get an Iterator for the Plugin Collection
		Iterator<PrimeAgentPluginInterface> itr2 = plugins.iterator();

		while ( itr2.hasNext() )
		{
			// Gets a single plugin
			PrimeAgentPluginInterface plugin = (PrimeAgentPluginInterface) itr2
					.next();

			System.out.println("Plugin " + plugin.getPluginName()
					+ " is finished = " + plugin.isFinished());

			pluginOutTrans.addTransmissionJob(plugin);
		}

		System.out
				.println("All plugins are done = " + scheduler.isTerminated());


		pluginOutTrans.addTransmissionCompleteJob();


		// Starts the transmission jobs
		new Thread(pluginOutTrans).start();
	}


	/**
	 * This function initiates the database and retrieves (creates) all
	 * necessary fields.
	 */
	private static boolean initDatabase()
	{
		// Creates
		Connection con = SqlLiteFunctions.createConnection();

		if ( con != null )
		{
			// If the SQL table does not exist
			if ( !SqlLiteFunctions.generalInfoTableExists(con) )
			{
				// Creates the SQL table
				System.out.println(SqlLiteFunctions.createTable(con, false));
			}

			// Gets (creates) the agents UUID.
			System.out.println(SqlLiteFunctions.createAndStoreAgentUID(con,
					false));

			// If the UUID was not set
			if ( agentID == null )
			{
				return false;
			}

			// Table exists and UUID exists and is set.
			return true;
		}

		// Connection was not made.
		return false;
	}
}
