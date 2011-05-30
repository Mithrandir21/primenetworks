/**
 * 
 */
package main;


import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

import listener.AgentListener;
import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import net.xeoh.plugins.base.util.PluginManagerUtil;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ListenerMain
{
	public static ServerSocket serversocket = null;

	public static Socket socket = null;

	public static PluginManager pm = null;

	public static PluginManagerUtil pmu = null;

	/**
	 * TODO - Description
	 * 
	 */
	public static void main(String[] args)
	{
		if ( startServerPlugins() )
		{
			startListening();
		}
	}


	private static boolean startServerPlugins()
	{
		pm = PluginManagerFactory.createPluginManager();

		// Loads all plugins in the plugins folder
		pm.addPluginsFrom(new File("plugins/").toURI());

		// Creates a plugin manager "helper"
		pmu = new PluginManagerUtil(pm);

		if ( pm != null && pmu != null )
		{
			return true;
		}

		return false;
	}


	/**
	 * TODO - Description
	 * 
	 */
	private static void startListening()
	{
		new Thread(new AgentListener("127.0.0.1", 58468)).start();
	}

	private void stopListening()
	{

	}
}
