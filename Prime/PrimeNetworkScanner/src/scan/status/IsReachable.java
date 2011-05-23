package scan.status;


import java.io.IOException;
import java.net.InetAddress;


/**
 * @author rakudave
 */
public class IsReachable implements PingMethod
{
	private static IsReachable instance;

	private static Object lock = new Object();

	public static IsReachable getInstance()
	{
		if ( instance == null )
		{
			synchronized (lock)
			{
				if ( instance == null )
				{
					instance = new IsReachable();
				}
			}
		}
		return instance;
	}

	public IsReachable()
	{
	} // changed to public for testing

	@Override
	public Status getStatus(InetAddress address)
	{
		try
		{
			// TODO - Custom Timeout Reachable
			if ( address.isReachable(500) )
			{
				// TODO - Logger.trace(address + " is up");
				return Status.UP;
			}
			else
			{
				// TODO - Logger.trace(address + " is down");
				return Status.DOWN;
			}
		}
		catch ( IOException e )
		{
			// TODO - Logger.trace(address + " is unknown");
			return Status.UNKNOWN;
		}
	}

	@Override
	public String toString()
	{
		return "Java Ping";
	}

}
