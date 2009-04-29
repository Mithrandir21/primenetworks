/**
 * 
 */
package managment;


import java.util.regex.Pattern;

import exceptions.NotValidIPAddress;
import exceptions.RangeIsNotValidException;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class NetworkManagment
{

	/**
	 * This is the pattern used by the system to verify and validate MAC addresses.
	 * 
	 * @return The pattern for MAC addresses in the system.
	 */
	public static Pattern getMACpattern()
	{
		// The pattern for MAC addresses
		Pattern MAC_PATTERN = Pattern.compile("((([0-9a-fA-F]){1,2}[-:]){5}([0-9a-fA-F]){1,2})");


		return MAC_PATTERN;
	}


	/**
	 * This is the pattern used by the system to verify and validate IP addresses.
	 * 
	 * @return The pattern for IP addresses in the system.
	 */
	public static Pattern getIPpattern()
	{
		String _255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

		// The pattern for IP addresses
		Pattern IP_PATTERN = Pattern.compile("^(?:" + _255 + "\\.){3}" + _255 + "$");


		return IP_PATTERN;
	}




	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static boolean processRange(String fromIP, String toIP) throws RangeIsNotValidException, NotValidIPAddress,
			RangeNotBigEnoughException
	{
		// Splits the fromIP into strings on the symbol "."
		String[] fromString = fromIP.split("\\.");

		// Splits the toIP into strings on the symbol "."
		String[] toString = toIP.split("\\.");

		
		// If the IP does not match the IP pattern(valid IP)
		if ( fromString.length != 4 || (!(getIPpattern().matcher(fromIP).matches())))
		{
			throw new NotValidIPAddress(fromIP);
		}


		// If the IP does not match the IP pattern(valid IP)
		if ( toString.length != 4 || (!(getIPpattern().matcher(toIP).matches())) )
		{
			throw new NotValidIPAddress(toIP);
		}



		int[] from = new int[4];
		int[] to = new int[4];


		// Converts the strings to int
		for ( int i = 0; i < 4; i++ )
		{
			from[i] = Integer.parseInt(fromString[i]);
			to[i] = Integer.parseInt(toString[i]);
		}



		// Check the first numbers in the IP, before the first "."
		// Eks:
		// 1. 192 < 193
		if ( from[0] < to[0] )
		{
			return true;
		}
		// 1. 192 == 192
		else if ( from[0] == to[0] )
		{
			// 2. 40 < 41
			if ( from[1] < to[1] )
			{
				return true;
			}
			// 2. 40 == 40
			else if ( from[1] == to[1] )
			{
				// 3. 90 < 91
				if ( from[2] < to[2] )
				{
					return true;
				}
				// 3. 90 == 90
				else if ( from[2] == to[2] )
				{
					// 4. 1 < 2
					if ( from[3] < to[3] )
					{
						return true;
					}
					// 4. 1 == 1
					else if ( from[3] == to[3] )
					{
						// Does not matter what IP is chosen because both IPs are the same
						throw new RangeNotBigEnoughException(fromIP);
					}
				}
			}
		}

		// If the method gets to this point the fromIP is larger then the toIP
		throw new RangeIsNotValidException(fromIP, toIP);
	}
	
	
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param fromIP
	 * @param toIP
	 * @return
	 * @throws NotValidIPAddress
	 * @throws RangeNotBigEnoughException
	 */
	public static boolean isIPhigherThenIP(String fromIP, String toIP) throws NotValidIPAddress, RangeNotBigEnoughException
	{
		// Splits the fromIP into strings on the symbol "."
		String[] fromString = fromIP.split("\\.");

		// Splits the toIP into strings on the symbol "."
		String[] toString = toIP.split("\\.");
		
		
		// If the IP does not match the IP pattern(valid IP)
		if ( fromString.length != 4 || (!(getIPpattern().matcher(fromIP).matches())))
		{
			throw new NotValidIPAddress(fromIP);
		}


		// If the IP does not match the IP pattern(valid IP)
		if ( toString.length != 4 || (!(getIPpattern().matcher(toIP).matches())) )
		{
			throw new NotValidIPAddress(toIP);
		}



		int[] from = new int[4];
		int[] to = new int[4];


		// Converts the strings to int
		for ( int i = 0; i < 4; i++ )
		{
			from[i] = Integer.parseInt(fromString[i]);
			to[i] = Integer.parseInt(toString[i]);
		}



		// Check the first numbers in the IP, before the first "."
		// Eks:
		// 1. 192 < 193
		if ( from[0] < to[0] )
		{
			return true;
		}
		// 1. 192 == 192
		else if ( from[0] == to[0] )
		{
			// 2. 40 < 41
			if ( from[1] < to[1] )
			{
				return true;
			}
			// 2. 40 == 40
			else if ( from[1] == to[1] )
			{
				// 3. 90 < 91
				if ( from[2] < to[2] )
				{
					return true;
				}
				// 3. 90 == 90
				else if ( from[2] == to[2] )
				{
					// 4. 1 < 2
					if ( from[3] < to[3] )
					{
						return true;
					}
					// 4. 1 == 1
					else if ( from[3] == to[3] )
					{
						// Does not matter what IP is chosen because both IPs are the same
						throw new RangeNotBigEnoughException(fromIP);
					}
				}
			}
		}
		
		
		return false;
	}


}
