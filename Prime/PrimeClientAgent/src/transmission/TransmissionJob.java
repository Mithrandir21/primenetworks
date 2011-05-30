/**
 * 
 */
package transmission;


import java.io.IOException;
import java.io.PrintWriter;

import utils.ServerConUtils;
import agentPluginInterface.PrimeAgentPluginInterface;


/**
 * This class is a {@link Runnable} that when run will attempt to send
 * information from the plugin object in this class to a PrimeAgentListener on a
 * PrimeDesktop server.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class TransmissionJob implements Runnable
{
	/**
	 * The plugin that will contain information sent to the server.
	 */
	private PrimeAgentPluginInterface plugin;

	/**
	 * A constructor that sets the plugin for this class.
	 */
	public TransmissionJob(PrimeAgentPluginInterface plugin)
	{
		this.plugin = plugin;
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		synchronized (this)
		{
			try
			{
				if ( ServerConUtils.socket != null
						&& ServerConUtils.socket.isConnected() )
				{
					// A boolean on whether or not the server has received the
					// "--END" text.
					boolean comfirmationReceived = false;

					boolean errorReceived = false;

					// Sends the plugin information
					if ( sendPluginInfo(ServerConUtils.printwriter) )
					{
						String lineread = "";
						// Waits for confirmation from server
						while ( (!errorReceived)
								&& (!comfirmationReceived)
								&& (lineread = ServerConUtils.bufferedreader
										.readLine()) != null )
						{
							// If the received text is NOT an error
							if ( lineread
									.compareToIgnoreCase("--ERROR-RECEIVING") != 0 )
							{
								System.out.println("Received from Server: "
										+ lineread);
								if ( lineread.compareToIgnoreCase("--Received") == 0 )
								{
									comfirmationReceived = true;
								}
							}
							else
							{
								errorReceived = true;
							}
						}
					}
				}

			}
			catch ( IOException ioe )
			{
				System.out.println("IOException: " + ioe.getMessage());
			}
			finally
			{
				// Notifies PluginOutputTransmitter.run().
				this.notify();
			}
		}
	}


	/**
	 * This function is a helper class that does the actual sending of plugin
	 * name, version and output (in that order).
	 * 
	 * Will return true if all necessary data exists in plugin and false if not.
	 */
	private boolean sendPluginInfo(PrintWriter send)
	{
		String pluginName = plugin.getPluginName();

		String pluginVersion = plugin.getPluginVersion();

		String pluginOutput = plugin.getPluginOutput();

		// Puts the name and version of the plugin together with the plugin
		// output.
		if ( pluginName != null || pluginVersion != null
				|| pluginOutput != null )
		{
			send.println("START--");

			send.println("Plugin Name:'" + pluginName + "'");

			send.println("Plugin Version:'" + pluginVersion + "'");

			send.println(pluginOutput);

			// Sends "--END" to notify plugin info end
			send.println("--END");

			return true;
		}

		return false;
	}

}
