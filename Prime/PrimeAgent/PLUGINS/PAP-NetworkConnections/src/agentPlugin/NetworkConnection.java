/**
 * 
 */
package agentPlugin;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

import utils.ImplementationType;
import agentPluginInterface.PrimeAgentPluginInterface;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class NetworkConnection implements PrimeAgentPluginInterface
{

	private boolean finished = false;

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginName()
	 */
	@Override
	public String getPluginName()
	{
		return "Basic Network Connections";
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginDescription()
	 */
	@Override
	public String getPluginDescription()
	{
		return "The devices basic network connection information.";
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginAuthor()
	 */
	@Override
	public String getPluginAuthor()
	{
		return "Bahram Malaekeh";
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getImplementationType()
	 */
	@Override
	public ImplementationType getImplementationType()
	{
		return ImplementationType.MIXED;
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginVersion()
	 */
	@Override
	public String getPluginVersion()
	{
		return "1.0";
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#initiatePluging()
	 */
	@Override
	public void initiatePluging()
	{

	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#destroy()
	 */
	@Override
	public void destroy()
	{

	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginOutput()
	 */
	@Override
	public String getPluginOutput()
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#setFinished()
	 */
	@Override
	public void setFinished()
	{
		finished = true;
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#isFinished()
	 */
	@Override
	public boolean isFinished()
	{
		return finished;
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#run()
	 */
	@Override
	public void run()
	{
		Enumeration<NetworkInterface> nets;
		try
		{
			nets = NetworkInterface.getNetworkInterfaces();
			for ( NetworkInterface netint : Collections.list(nets) )
				displayInterfaceInformation(netint);
		}
		catch ( SocketException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void displayInterfaceInformation(NetworkInterface netint)
			throws SocketException
	{
		java.lang.System.out.printf("Display name: %s\n",
				netint.getDisplayName());
		java.lang.System.out.printf("Name: %s\n", netint.getName());
		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
		for ( InetAddress inetAddress : Collections.list(inetAddresses) )
		{
			java.lang.System.out.printf("InetAddress: %s\n", inetAddress);
		}
		java.lang.System.out.printf("\n");
	}
}
