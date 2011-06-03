/**
 * 
 */
package utils;


import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.ListenerMain;
import serverPluginInterface.PrimeServerPluginInterface;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class AgentInputProcessing
{
	/**
	 * This function attempts to convert the String given to a UUID. Will return
	 * null if text is null, empty or not UUID format.
	 */
	public static UUID verifyStringAsUUID(String text)
	{
		if ( text != null && text != "" )
		{
			try
			{
				return UUID.fromString(text);
			}
			catch ( IllegalArgumentException e )
			{
			}
		}

		return null;
	}


	/**
	 * This function uses Regular Expressions to verify the syntax of the given
	 * text to match " AgentID:'[anything]' ".
	 */
	public static boolean verifyPluginAgentIDSyntax(String text)
	{
		// Create a pattern to match "Plugin Name:'[anything]'"
		Pattern p = Pattern.compile("AgentID:'.*?'");

		// Create a matcher with an input string
		Matcher m = p.matcher(text);

		return m.find();
	}


	/**
	 * This function uses Regular Expressions to verify the syntax of the given
	 * text to match " Plugin Name:'[anything]' ".
	 */
	public static boolean verifyPluginNameSyntax(String text)
	{
		// Create a pattern to match "Plugin Name:'[anything]'"
		Pattern p = Pattern.compile("Plugin Name:'.*?'");

		// Create a matcher with an input string
		Matcher m = p.matcher(text);

		return m.find();
	}

	/**
	 * This function uses Regular Expressions to verify the syntax of the given
	 * text to match " Plugin Version:'[anything]' ".
	 */
	public static boolean verifyPluginVersionSyntax(String text)
	{
		// Create a pattern to match "Plugin Version:'[anything]'"
		Pattern p = Pattern.compile("Plugin Version:'.*?'");

		// Create a matcher with an input string
		Matcher m = p.matcher(text);

		return m.find();
	}


	/**
	 * TODO - Description
	 */
	public static String getPluginMetaInfo(String input)
	{
		return input
				.substring(input.indexOf('\'') + 1, input.lastIndexOf('\''));
	}




	/**
	 * TODO - Description
	 */
	public static PrimeServerPluginInterface findCorrectPlugin(
			String pluginName, String pluginVersion)
	{
		/**
		 * Gets a Collection of all plugins that implement the
		 * PrimeAgentPluginInterface, which also implements Runnable (where the
		 * data gathering will take place).
		 */
		Collection<PrimeServerPluginInterface> plugins = ListenerMain.pmu
				.getPlugins(PrimeServerPluginInterface.class);

		// Get an Iterator for the Plugin Collection
		Iterator<PrimeServerPluginInterface> itr = plugins.iterator();

		while ( itr.hasNext() )
		{
			// Gets a single plugin
			PrimeServerPluginInterface plugin = (PrimeServerPluginInterface) itr
					.next();

			if ( plugin.getPluginName().equals(pluginName) )
			{
				if ( plugin.getPluginVersion().equals(pluginVersion) )
				{
					System.out.println("Plugin found!");
					return plugin;
				}
			}
		}


		return null;
	}
}
