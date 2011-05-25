package scan.status;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Utils.DeviceType;


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
			// Gets the device type of the local machine
			DeviceType devType = DeviceType.getLocalHostType();


			String command = "";
			if ( devType == DeviceType.MAC || devType == DeviceType.UNIX )
			{
				command = "ping -c 4 -W 5";
			}
			else
			{
				command = "ping -n 4 -w 1250";
			}

			// Executes the command
			process = Runtime.getRuntime().exec(
					command + " " + address.getHostAddress());

			// Gets the output for the command
			BufferedReader input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			// Places each line from the process into output
			String output = "";
			String line;
			while ( (line = input.readLine()) != null )
			{
				output += line;
			}

			// Checks the output for a certain response and returns a Status
			return (checkPingResponse(output, devType));

			// return (process.waitFor() == 0) ? Status.UP : Status.DOWN;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Native Ping";
	}

	/**
	 * TODO - Description
	 */
	private Status checkPingResponse(String pingOutput, DeviceType devType)
	{
		// The pattern that is to be looked for in the Ping output.
		Pattern allReceived;

		if ( devType == DeviceType.MAC )
		{
			// Applied to Mac
			allReceived = Pattern.compile("4 packets received");
		}
		else if ( devType == DeviceType.UNIX )
		{
			// Applied to Linux/Unix
			allReceived = Pattern.compile("4 received");
		}
		else
		{
			// Applied to Windows
			allReceived = Pattern.compile("Received = 4");
		}

		Matcher matcher = allReceived.matcher(pingOutput);

		if ( matcher.find() )
		{
			return Status.UP;
		}

		return Status.DOWN;
	}
}