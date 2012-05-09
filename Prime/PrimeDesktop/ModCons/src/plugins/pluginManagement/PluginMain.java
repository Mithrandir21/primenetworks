/**
 * 
 */
package plugins.pluginManagement;


import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import localPluginInterface.PrimeLocalPluginInterface;
import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import net.xeoh.plugins.base.util.PluginManagerUtil;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PluginMain
{
	public static PluginManager pm = null;

	public static PluginManagerUtil pmu = null;

	public static boolean endQueue = false;

	public static Thread pluginExeThread;

	private static PluginExecution pluginExe = new PluginExecution();


	/**
	 * This constructor initiates the variables in the class and creates the
	 * plugin queue.
	 */
	public PluginMain()
	{
		loadPrimePlugins();
	}


	/**
	 * This function loads all the plugins in the plugins folder.
	 */
	private static boolean loadPrimePlugins()
	{
		pm = PluginManagerFactory.createPluginManager();

		// Loads all plugins in the plugins folder
		pm.addPluginsFrom(new File("resource/plugins/").toURI());

		// Creates a plugin manager "helper"
		pmu = new PluginManagerUtil(pm);

		if ( pm != null && pmu != null )
		{
			initPluginQueue();

			loadIndividualPlugins();

			return true;
		}

		// Something went wrong
		return false;
	}



	/**
	 * This function initiates the local plugin queue, so that an plugin that
	 * wants to run has queue to place itself in for execution.
	 */
	private static void initPluginQueue()
	{
		pluginExe = new PluginExecution();

		// Creates the new PluginExecution inside a Thread
		pluginExeThread = new Thread(pluginExe);
	}



	private static void loadIndividualPlugins()
	{
		/**
		 * Gets a Collection of all plugins that implement the
		 * PrimeLocalPluginInterface, which also implements Runnable (where the
		 * data gathering will take place).
		 */
		Collection<PrimeLocalPluginInterface> plugins = pmu
				.getPlugins(PrimeLocalPluginInterface.class);

		// Get an Iterator for the Plugin Collection
		Iterator<PrimeLocalPluginInterface> itr = plugins.iterator();

		while ( itr.hasNext() )
		{
			// Gets a single plugin
			PrimeLocalPluginInterface plugin = (PrimeLocalPluginInterface) itr
					.next();

			// Add the Runnable plugin
			pluginExe.addPluginJob(plugin);
		}
	}


	/**
	 * Starts the plugin queue.
	 */
	public static void startPluginQueue()
	{
		pluginExeThread.start();
	}


	/**
	 * Stops the plugin queue from receiving new plugins jobs.
	 */
	public static void stopPluginQueue()
	{
		endQueue = true;
	}


	public static boolean arePluginsFinished()
	{
		return pluginExe.arePluginsFinished();
	}
}
