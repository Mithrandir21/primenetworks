/**
 * 
 */
package plugins.pluginManagement;


import localPluginInterface.PrimeLocalPluginInterface;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PluginJob implements Runnable
{

	/**
	 * The plugin that will contain information sent to the server.
	 */
	private PrimeLocalPluginInterface plugin;

	/**
	 * A constructor that sets the plugin for this class.
	 */
	public PluginJob(PrimeLocalPluginInterface plugin)
	{
		this.plugin = plugin;
	}



	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		synchronized (this)
		{

		}
	}

}
