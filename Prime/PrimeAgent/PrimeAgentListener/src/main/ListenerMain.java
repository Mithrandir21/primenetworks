/**
 * 
 */
package main;


import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
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
 */
public class ListenerMain
{
	/**
	 * The {@link ServerSocket} that will take care of providing new sockets on
	 * each connection.
	 */
	public static ServerSocket serversocket = null;

	public static PluginManager pm = null;

	public static PluginManagerUtil pmu = null;

	/**
	 * The URL (IP, hostname, etc) to the Agent.
	 */
	private static String serverurl = "127.0.0.1";

	/**
	 * The port to listen to.
	 */
	private static int serverport = 58468;

	/**
	 * The maximum number connections.
	 */
	private static final int MAXconnections = 100;

	/**
	 * The integer that will keep track of the number of connections.
	 */
	public static int activeCons = 0;


	/**
	 * Main that starts the StartServerPlugins() and then StartListening().
	 */
	public static void main(String[] args)
	{
		if ( startServerPlugins() )
		{
			startListening();
		}
	}


	/**
	 * This function loads all the plugins in the plugins folder.
	 */
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

		// Somethine went wrong
		return false;
	}


	/**
	 * Starts the listening for incoming connections from Clients.
	 */
	private static void startListening()
	{
		try
		{
			// The object that provides Socket objects on connection.
			serversocket = new ServerSocket(serverport, 100,
					InetAddress.getByName(serverurl));

			System.out.println("Listening at " + serverurl + " on port "
					+ serverport);

			while ( activeCons <= MAXconnections )
			{
				// wait indefinitely until a client connects to the socket
				Socket socket = serversocket.accept();

				// The processing Thread
				Thread processing = new Thread(new AgentListener(socket));
				processing.start();

				// Adds a new connection
				activeCons++;
			}
		}
		catch ( IOException ioe )
		{
			System.out.println("startListening() - IOException: "
					+ ioe.getMessage());
		}

	}

	private static void stopListening()
	{
		try
		{
			serversocket.close();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
}
