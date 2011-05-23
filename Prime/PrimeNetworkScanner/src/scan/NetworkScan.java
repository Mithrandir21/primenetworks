package scan;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import scan.status.PingMethod;
import util.Tuple;


/**
 * Scans a Subnet for (alive) Hosts
 * 
 * @author rakudave
 */
public class NetworkScan implements ChangeListener
{
	// The Subnet that will be scanned.
	private Subnet subnet;

	// The Map that will hold the PingMethod results for each IP in the subnet
	private java.util.Map<InetAddress, PingMethod> found;

	private ExecutorService ex;


	public NetworkScan(Subnet subnet)
	{
		this.subnet = subnet;
		found = new HashMap<InetAddress, PingMethod>();
	}

	public void start(boolean doPortScan)
	{
		if ( ex != null )
		{
			return;
		}

		ex = Executors.newFixedThreadPool(50);


		String[] addresses = subnet.getInfo().getAllAddresses();

		for ( int i = 0; i < addresses.length; i++ )
		{
			try
			{
				// The IP address of the host is given for IP validation.
				InetAddress address = InetAddress.getByName(addresses[i]);

				ex.submit(new HostCheck(address, this, doPortScan));
			}
			catch ( UnknownHostException uhe )
			{
				// TODO - Logger.error("Invalid address: " + addresses[i], uhe);
			}
			catch ( RejectedExecutionException ree )
			{
				// TODO - Logger.debug("Scan interrupted: " + addresses[i],
				// ree);
			}
		}

		ex.shutdown();
	}

	public boolean isDone()
	{
		return ex.isTerminated();
	}

	public void cancel()
	{
		ex.shutdownNow();
	}


	/**
	 * TODO - Description
	 * 
	 */
	public java.util.Map<InetAddress, PingMethod> getFoundHosts()
	{
		return found;
	}



	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void stateChanged(ChangeEvent e)
	{
		Tuple<InetAddress, PingMethod> tuple = (Tuple<InetAddress, PingMethod>) e
				.getSource();
		found.put(tuple.getFirst(), tuple.getSecond());
	}


	/**
	 * TODO - Description
	 * 
	 */
	public static String getLocalAddress()
	{
		Enumeration<NetworkInterface> nets;
		try
		{
			nets = NetworkInterface.getNetworkInterfaces();
		}
		catch ( SocketException e )
		{
			// TODO - Logger.debug("Unable to get local interfaces", e);
			return null;
		}
		for ( NetworkInterface netint : Collections.list(nets) )
		{
			for ( InetAddress inetAddress : Collections.list(netint
					.getInetAddresses()) )
			{
				if ( !inetAddress.isLoopbackAddress()
						&& inetAddress
								.getHostAddress()
								.matches(
										"(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)") )
				{
					return inetAddress.getHostAddress();
				}
			}
		}
		return null;
	}
}
