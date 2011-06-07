import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Formatter;

import utils.DeviceType;


/**
 * 
 */

/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class MainTesting
{
	// The type of computer
	static DeviceType devType = DeviceType.WINDOWS;


	/**
	 * TODO - Description
	 * 
	 */
	public static void main(String[] args)
	{
		Enumeration<NetworkInterface> nets;
		try
		{
			nets = NetworkInterface.getNetworkInterfaces();
			for ( NetworkInterface netint : Collections.list(nets) )
			{
				getDefaultGateway(netint);
				displayInterfaceInformation(netint);
			}
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
		java.lang.System.out.printf("DisplayName: %s\n",
				netint.getDisplayName() + "\n");
		java.lang.System.out.printf("Name: %s\n", netint.getName());
		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
		for ( InetAddress inetAddress : Collections.list(inetAddresses) )
		{
			java.lang.System.out.printf("InetAddress: %s\n", inetAddress);
		}

		byte[] mac = netint.getHardwareAddress();

		if ( mac != null )
		{
			System.out.println(mac.length);
			/*
			* Extract each array of mac address and convert it to hexa with the
			* following format 08-00-27-DC-4A-9E.
			*/
			for ( int i = 0; i < mac.length; i++ )
			{
				java.lang.System.out.format("%02X%s", mac[i],
						(i < mac.length - 1) ? "-" : "");
			}
		}

		java.lang.System.out.printf("\nLoopback: " + netint.isLoopback());

		java.lang.System.out.printf("\n\n");
	}


	/**
	 * TODO - Description
	 * 
	 */
	private String getValidNICinfo()
	{
		String info = "";


		/**
		 * Steps in determining NIC validity:
		 * 
		 * 1. Step: Is loopback?
		 * 
		 * 2. Step: NIC has name?
		 * 
		 * -- FIXME - IPv4 only MAC
		 * 
		 * 3. Step: Has MAC (IPv4 only for now).
		 */

		try
		{
			// Gets all the hosts NICs
			Enumeration<NetworkInterface> NICs = NetworkInterface
					.getNetworkInterfaces();

			// Goes through all found NICs
			for ( NetworkInterface singleNIC : Collections.list(NICs) )
			{
				getDefaultGateway(singleNIC);
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
						devName = singleNIC.getName();

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
									devIP = tempAdr.nextElement()
											.getHostAddress();
									IPfound = true;
								}

								if ( devIP != null && devIP != "" )
								{

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


		return info;
	}

	/**
	 * TODO - Description
	 * 
	 */
	private String getMAC(byte[] macBytes)
	{
		String mac = "";

		if ( mac != null )
		{
			// MAC IPv4
			if ( mac.length() == 6 )
			{
				/*
				* Extract each array of mac address and convert it to hexa with the
				* following format 08-00-27-DC-4A-9E.
				*/
				for ( int i = 0; i < macBytes.length; i++ )
				{
					Formatter format = new Formatter();
					mac += format.format("%02X%s", macBytes[i],
							(i < macBytes.length - 1) ? "-" : "");
				}
			}
		}

		return mac;
	}


	private static String getDefaultGateway(NetworkInterface nic)
	{
		String dg = null;

		// IF WINDOWS
		/**
		 * Gets all the processes running on the Windows client. Uses
		 * "tasklist.exe /fo csv /nh".
		 */
		if ( devType == DeviceType.WINDOWS )
		{
			try
			{
				ArrayList<String> cmdOutput = new ArrayList<String>();

				String line;
				Process p = Runtime.getRuntime().exec("ipconfig /all");
				BufferedReader input = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				while ( (line = input.readLine()) != null )
				{
					if ( !line.trim().equals("") )
					{
						System.out.println(line);
						// keep only the process name
						// line = line.substring(1);
						// cmdOutput.add(line.substring(0, line.indexOf("\"")));
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

		System.exit(0);
		return dg;
	}
}
