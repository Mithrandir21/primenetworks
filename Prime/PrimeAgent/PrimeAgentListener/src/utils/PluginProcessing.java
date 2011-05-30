/**
 * 
 */
package utils;


import java.io.BufferedReader;
import java.io.IOException;

import listener.AgentListener;
import serverPluginInterface.PrimeServerPluginInterface;


/**
 * This class takes care of the processing for the plugin input sent from the
 * Agent, through the {@link AgentListener#run()}.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PluginProcessing implements Runnable
{
	private BufferedReader bufferedreader = null;

	public boolean success = false;


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param bufferedreader
	 */
	public PluginProcessing(BufferedReader bufferedreader)
	{
		this.bufferedreader = bufferedreader;
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
				if ( bufferedreader != null && bufferedreader.ready() )
				{
					/**
					 * The protocol for information sending from the agents is
					 * as following:
					 * 
					 * 1. Send the plugin name
					 * 
					 * 2. Send the plugin version
					 * 
					 * 3. Send the plugin output
					 */

					String pluginName = "";
					String pluginVersion = "";
					String pluginOutput = "";

					String lineread = "";

					// Verify the syntax of the plugin name text and reads the
					// first input
					if ( (lineread = bufferedreader.readLine()) != null
							&& AgentInputProcessing
									.verifyPluginNameSyntax(lineread) )
					{
						System.out.println("Step1 - " + lineread);
						// If it is not NULL, get plugin name
						pluginName = AgentInputProcessing
								.getPluginMetaInfo(lineread);

						// Verify the syntax of the plugin version text and
						// reads the second input
						if ( (lineread = bufferedreader.readLine()) != null
								&& AgentInputProcessing
										.verifyPluginVersionSyntax(lineread) )
						{
							System.out.println("Step2 - " + lineread);
							// If it is not NULL, get plugin version
							pluginVersion = AgentInputProcessing
									.getPluginMetaInfo(lineread);

							// Reads the third input
							if ( (lineread = bufferedreader.readLine()) != null )
							{
								// Finds the correct plugin by name and version
								PrimeServerPluginInterface plugin = AgentInputProcessing
										.findCorrectPlugin(pluginName,
												pluginVersion);

								// If any plugin was found
								if ( plugin != null )
								{
									System.out.println("Step3 - " + lineread);
									// If it is not NULL, get plugin output
									pluginOutput = lineread;

									// Reads the END input
									if ( (lineread = bufferedreader.readLine()) != null )
									{
										System.out.println("Step4 - "
												+ lineread);
										// Gotten the end of the plugin output
										if ( lineread
												.compareToIgnoreCase("--END") == 0 )
										{
											System.out
													.println("Plugin output received succesfully!\n");
											success = true;

											// Sends the agent plugin output to
											// the server agent input for
											// processing.
											plugin.agentInputProcessing(pluginOutput);

											System.out.println();
										}
									}
								}
							}
						}
					}
				}
			}
			catch ( IOException e )
			{
				e.printStackTrace();
			}

			// Notifies the AgentListener to go on.
			this.notify();
		}
	}
}
