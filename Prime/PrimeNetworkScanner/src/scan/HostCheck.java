/**
 * 
 */
package scan;


import java.net.InetAddress;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import scan.status.IsReachable;
import scan.status.NativePing;
import scan.status.PingMethod;
import scan.status.Status;
import util.Tuple;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class HostCheck implements Runnable
{
	private InetAddress address;

	private ChangeListener l;

	private boolean doPortScan;

	public HostCheck(InetAddress address, ChangeListener l, boolean doPortScan)
	{
		this.address = address;
		this.l = l;
		this.doPortScan = doPortScan;
	}

	@Override
	public void run()
	{
		// The result that will be delivered to the ChangeListener.
		Tuple<InetAddress, PingMethod> result = null;

		// ignore A/B/C-Broadcast addresses
		if ( new Subnet(address.getHostAddress() + "/24").getInfo()
				.getBroadcastAddress().equals(address.getHostAddress())
				|| new Subnet(address.getHostAddress() + "/16").getInfo()
						.getBroadcastAddress().equals(address.getHostAddress())
				|| new Subnet(address.getHostAddress() + "/8").getInfo()
						.getBroadcastAddress().equals(address.getHostAddress()) )
		{
			return;
		}



		if ( IsReachable.getInstance().getStatus(address) == Status.UP )
		{
			result = new Tuple<InetAddress, PingMethod>(address,
					IsReachable.getInstance());
		}
		else if ( NativePing.getInstance().getStatus(address) == Status.UP )
		{
			result = new Tuple<InetAddress, PingMethod>(address,
					NativePing.getInstance());
		}
		else if ( doPortScan )
		{
			// FIXME - DOPORTSCANN
			// int port = PortScan.sweepCommon(address);
			// result = (port != -1) ? new Tuple<InetAddress, PingMethod>(
			// address, new OpenSocket(port)) : null;
		}

		// Adds the results to the foundHosts list if the result is not null
		if ( result != null )
		{
			l.stateChanged(new ChangeEvent(result));
		}
	}
}
