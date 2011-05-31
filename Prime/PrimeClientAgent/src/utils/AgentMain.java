/**
 * 
 */
package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import transmission.PluginOutputTransmitter;


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

	public static PluginOutputTransmitter pluginOutTrans;

	// /**
	// * TODO - Description
	// */
	// public static void main(String[] args)
	// {
	// // Creates an Executor schedule for all the runnable plugins
	// scheduler = new ScheduledThreadPoolExecutor(25);
	//
	// PluginManager pm = PluginManagerFactory.createPluginManager();
	//
	// // Loads all plugins in the plugins folder
	// pm.addPluginsFrom(new File("plugins/").toURI());
	//
	// // Creates a plugin manager "helper"
	// PluginManagerUtil pmu = new PluginManagerUtil(pm);
	//
	// /**
	// * Gets a Collection of all plugins that implement the
	// * PrimeAgentPluginInterface, which also implements Runnable (where the
	// * data gathering will take place).
	// */
	// Collection<PrimeAgentPluginInterface> plugins = pmu
	// .getPlugins(PrimeAgentPluginInterface.class);
	//
	// // Get an Iterator for the Plugin Collection
	// Iterator<PrimeAgentPluginInterface> itr = plugins.iterator();
	//
	// while ( itr.hasNext() )
	// {
	// // Gets a single plugin
	// PrimeAgentPluginInterface plugin = (PrimeAgentPluginInterface) itr
	// .next();
	//
	// // Add the Runnable plugin to the Executor schedule
	// scheduler.schedule(plugin, 2, SECONDS);
	// }
	//
	// // Calls an orderly 'shutdown' of all the tasks running. (Will NOT
	// // terminate, but will disallow additional tasks from being added.)
	// scheduler.shutdown();
	//
	//
	// while ( !scheduler.isTerminated() )
	// {
	// // wait...
	// }
	//
	//
	// pluginOutTrans = new PluginOutputTransmitter();
	//
	//
	// // Get an Iterator for the Plugin Collection
	// Iterator<PrimeAgentPluginInterface> itr2 = plugins.iterator();
	//
	// while ( itr2.hasNext() )
	// {
	// // Gets a single plugin
	// PrimeAgentPluginInterface plugin = (PrimeAgentPluginInterface) itr2
	// .next();
	//
	// System.out.println("Plugin " + plugin.getPluginName()
	// + " is finished = " + plugin.isFinished());
	//
	// pluginOutTrans.addTransmissionJob(plugin);
	// }
	//
	// System.out
	// .println("All plugins are done = " + scheduler.isTerminated());
	//
	//
	// // pluginOutTrans.addTransmissionCompleteJob();
	//
	//
	// // Starts the transmission jobs
	// new Thread(pluginOutTrans).start();
	// }


	public static void main(String[] args)
	{
		ResultSet rs;
		Connection conn;

		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			Statement stat = conn.createStatement();
			stat.executeUpdate("drop table if exists people;");
			stat.executeUpdate("create table people (name, occupation);");
			PreparedStatement prep = conn
					.prepareStatement("insert into people values (?, ?);");

			prep.setString(1, "Gandhi");
			prep.setString(2, "politics");
			prep.addBatch();
			prep.setString(1, "Turing");
			prep.setString(2, "computers");
			prep.addBatch();
			prep.setString(1, "Wittgenstein");
			prep.setString(2, "smartypants");
			prep.addBatch();

			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);

			rs = stat.executeQuery("select * from people;");
			while ( rs.next() )
			{
				System.out.println("name = " + rs.getString("name"));
				System.out.println("job = " + rs.getString("occupation"));
			}

			rs.close();
			conn.close();
		}
		catch ( ClassNotFoundException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( SQLException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
