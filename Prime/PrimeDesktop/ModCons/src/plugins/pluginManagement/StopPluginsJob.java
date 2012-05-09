/**
 * 
 */
package plugins.pluginManagement;




/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class StopPluginsJob implements Runnable
{
	/**
	 * A boolean on whether or not to force close are plugins.
	 */
	private boolean forceClose = false;


	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		try
		{
			// Stops the plugin queue from receiving new plugins
			PluginMain.stopPluginQueue();

			// Iterations waited
			int waitedSeconds = 0;

			while ( !PluginMain.arePluginsFinished() && !forceClose )
			{
				// If the loop has waited 10 seconds or mod 60 seconds
				if ( (waitedSeconds == 10) || ((waitedSeconds % 60) != 0) )
				{
					System.out
							.println("You have now WAITED too long for plugins to close.");

					waitedSeconds++;
				}
				else
				{
					// Ask the Thread to wait one second
					System.out.println("Plugins are not finished.");
					System.out.println("Started sleep...");
					Thread.sleep(1000);
					System.out.println("Finished sleep.");

					waitedSeconds++;
				}
			}

			System.out.println("Plugins ARE finished.");
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
		}
	}
}
