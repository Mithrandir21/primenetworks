/**
 * 
 */
package utils;


import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;

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

	public static PluginOutputTransmitter pluginOutTrans;

	/**
	 * TODO - Description
	 */
	public static void main(String[] args)
	{
		// Creates an Executor schedule for all the runnable plugins
		scheduler = new ScheduledThreadPoolExecutor(25);

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
			scheduler.schedule(plugin, 2, SECONDS);
		}

		// Calls an orderly 'shutdown' of all the tasks running. (Will NOT
		// terminate, but will disallow additional tasks from being added.)
		scheduler.shutdown();


		while ( !scheduler.isTerminated() )
		{
			// wait...
		}


		pluginOutTrans = new PluginOutputTransmitter();


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


		// pluginOutTrans.addTransmissionCompleteJob();


		// Starts the transmission jobs
		new Thread(pluginOutTrans).start();
	}
}
