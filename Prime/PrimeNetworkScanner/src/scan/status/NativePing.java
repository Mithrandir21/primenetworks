package scan.status;


import java.net.InetAddress;


/**
 * @author rakudave
 */

// TODO - @XStreamAlias("NativePing")
public class NativePing implements PingMethod
{
	private static NativePing instance;

	private static Object lock = new Object();

	public static NativePing getInstance()
	{
		if ( instance == null )
		{
			synchronized (lock)
			{
				if ( instance == null )
				{
					instance = new NativePing();
				}
			}
		}
		return instance;
	}

	protected NativePing()
	{
	} // changed from private for testing

	@Override
	public Status getStatus(InetAddress address)
	{
		Process process = null;
		try
		{
			boolean isUnix = !System.getProperty("os.name").startsWith(
					"Windows");

			// this uses the exit-status of ping, where 0 is success
			String command = "";
			if ( isUnix )
			{
				command = "ping -c 4 -w 5";
			}
			else
			{
				command = "ping -w 1250";
			}

			process = Runtime.getRuntime().exec(
					command + " " + address.getHostAddress());

			return (process.waitFor() == 0) ? Status.UP : Status.DOWN;
		}
		catch ( Exception e )
		{
			return Status.UNKNOWN;
		}
		finally
		{
			if ( process != null )
				process.destroy();
		}
	}

	@Override
	public String toString()
	{
		return "System Ping";
	}
}