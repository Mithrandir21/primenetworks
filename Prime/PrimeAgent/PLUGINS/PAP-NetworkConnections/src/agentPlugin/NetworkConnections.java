/**
 * 
 */
package agentPlugin;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.regex.Pattern;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.events.Init;
import utils.DeviceType;
import utils.ImplementationType;
import agentPluginInterface.PrimeAgentPluginInterface;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
@PluginImplementation
public class NetworkConnections implements PrimeAgentPluginInterface
{
	// The type of computer
	static DeviceType devType;

	private String output = "";

	private boolean finished = false;

	/*
	 * (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginName()
	 */
	@Override
	public String getPluginName()
	{
		return "Basic Network Connections";
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * agentPluginInterface.PrimeAgentPluginInterface#getPluginDescription()
	 */
	@Override
	public String getPluginDescription()
	{
		return "The devices basic network connection information.";
	}

	/*
	 * (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginAuthor()
	 */
	@Override
	public String getPluginAuthor()
	{
		return "Bahram Malaekeh";
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * agentPluginInterface.PrimeAgentPluginInterface#getImplementationType()
	 */
	@Override
	public ImplementationType getImplementationType()
	{
		return ImplementationType.MIXED;
	}

	/*
	 * (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginVersion()
	 */
	@Override
	public String getPluginVersion()
	{
		return "1.0";
	}

	/*
	 * (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#initiatePluging()
	 */
	@Init
	@Override
	public void initiatePluging()
	{
		devType = DeviceType.getLocalHostType();
	}

	/*
	 * (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#destroy()
	 */
	@Override
	public void destroy()
	{

	}

	/*
	 * (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginOutput()
	 */
	@Override
	public String getPluginOutput()
	{
		return output;
	}

	/*
	 * (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#setFinished()
	 */
	@Override
	public void setFinished()
	{
		finished = true;
	}

	/*
	 * (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#isFinished()
	 */
	@Override
	public boolean isFinished()
	{
		return finished;
	}

	/*
	 * (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#run()
	 */
	@Override
	public void run()
	{
		/**
		 * Steps in determining NIC validity: 1. Step: Is loopback? 2. Step: NIC
		 * has name? -- FIXME - IPv4 only MAC 3. Step: Has MAC (IPv4 only for
		 * now).
		 */
		try
		{
			// Gets all the hosts NICs
			Enumeration<NetworkInterface> NICs = NetworkInterface
					.getNetworkInterfaces();

			// Goes through all found NICs
			for ( NetworkInterface singleNIC : Collections.list(NICs) )
			{
				String devName = null;
				String devMAC = null;
				String devIP = null;
				String devDG = null;

				if ( singleNIC != null )
				{
					// Exclude loopbacks
					if ( !singleNIC.isLoopback() )
					{
						// Gets the name of the device
						devName = singleNIC.getDisplayName();

						if ( devName != null && devName != "" )
						{
							// Gets the MAC of the NIC (IPv4)
							devMAC = getMAC(singleNIC.getHardwareAddress());

							if ( devMAC != null && devMAC != "" )
							{
								boolean IPfound = false;

								Enumeration<InetAddress> tempAdr = singleNIC
										.getInetAddresses();

								// Gets the a single IP to the NIC
								while ( !IPfound && tempAdr.hasMoreElements() )
								{
									String tempIP = tempAdr.nextElement()
											.getHostAddress();
									// If it is a valid IPv4 IP
									if ( isIPvalid(tempIP) )
									{
										devIP = tempIP;
										IPfound = true;
									}
								}

								if ( devIP != null && devIP != "" )
								{
									devDG = getDefaultGateway(singleNIC);

									if ( devName != null && devMAC != null
											&& devIP != null && devDG != null )
									{
										output += "Device Name:'" + devName
												+ "' ";
										output += "Device MAC:'" + devMAC
												+ "' ";
										output += "Device IP:'" + devIP + "' ";

										output += "Default Gateway:'" + devDG
												+ "'";
									}
								}
							}
						}
					}
				}
			}
		}
		catch ( SocketException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * This function formats the given bytes into a MAC address. Validates
	 * before return.
	 */
	private static String getMAC(byte[] macBytes)
	{
		String mac = "";

		if ( macBytes != null )
		{
			// MAC IPv4
			if ( macBytes.length == 6 )
			{
				/*
				 * Extract each array of mac address and convert it to hexa with
				 * the following format 08-00-27-DC-4A-9E.
				 */
				for ( int i = 0; i < macBytes.length; i++ )
				{
					Formatter format = new Formatter();
					mac += format.format("%02X%s", macBytes[i],
							(i < macBytes.length - 1) ? "-" : "");

				}

				// If the gotten string is a valid mac adr.
				if ( isMACvalid(mac) )
				{
					return mac;
				}
			}
		}

		return null;
	}


	/**
	 * This function uses the output for the command "config /all" to find the
	 * Default Gateway for the given {@link NetworkInterface}.
	 */
	private static String getDefaultGateway(NetworkInterface nic)
	{
		String dg = null;

		// IF WINDOWS
		if ( devType == DeviceType.WINDOWS )
		{
			try
			{
				String line;
				Process p = Runtime.getRuntime().exec("ipconfig /all");
				BufferedReader input = new BufferedReader(
						new InputStreamReader(p.getInputStream()));

				String foundNICname = "";
				// Reads each line
				while ( (line = input.readLine()) != null )
				{
					// If the line is no empty
					if ( line != "" )
					{
						// Find the name of the current NIC
						if ( line.trim().startsWith("Description") )
						{
							// Sets the name of the current NIC
							foundNICname = line.substring(
									line.indexOf(". :") + 4, line.length());
						}
						// If the pointer has moved down to the IP section
						else if ( line.trim().startsWith("Default Gateway") )
						{
							if ( !foundNICname.contains("Virtual") )
							{
								if ( nic.getDisplayName().equals(foundNICname) )
								{
									dg = getIpstring(line.trim());
								}
							}
						}
					}
				}
				input.close();
			}
			catch ( Exception err )
			{
				err.printStackTrace();
			}
		}
		// IF MAC
		else if ( devType == DeviceType.MAC )
		{
		}
		// IF UNIX/LINUX
		else if ( devType == DeviceType.UNIX )
		{
		}

		return dg;
	}


	/**
	 * Attempts to get only the IP part of the given string.
	 */
	private static String getIpstring(String line)
	{
		String ip = null;

		// If the line starts with IPv5 Address
		if ( line.trim().startsWith("Default Gateway") )
		{
			// If the line ends with the given string
			if ( line.endsWith("(Preferred)") )
			{
				// Removes the string
				line = line.replaceAll("\\(Preferred\\)", "");
			}

			// Gets only the end of the String
			ip = line.substring(line.indexOf(". :") + 4, line.length());
		}

		return ip;
	}




	/**
	 * This is the pattern used by the system to verify and validate MAC
	 * addresses.
	 * 
	 * @return The pattern for MAC addresses in the system.
	 */
	public static boolean isMACvalid(String text)
	{
		boolean valid = false;

		if ( text != null && text != "" )
		{
			// The pattern for MAC addresses
			Pattern MAC_PATTERN = Pattern
					.compile("((([0-9a-fA-F]){1,2}[-:]){5}([0-9a-fA-F]){1,2})");

			valid = MAC_PATTERN.matcher(text).matches();
		}

		return valid;
	}


	/**
	 * This is the pattern used by the system to verify and validate IP
	 * addresses.
	 * 
	 * @return The pattern for IP addresses in the system.
	 */
	public static boolean isIPvalid(String text)
	{
		boolean valid = false;

		if ( text != null && text != "" )
		{
			String _255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

			// The pattern for IP addresses
			Pattern IP_PATTERN = Pattern.compile("^(?:" + _255 + "\\.){3}"
					+ _255 + "$");

			valid = IP_PATTERN.matcher(text).matches();
		}

		return valid;
	}
}
