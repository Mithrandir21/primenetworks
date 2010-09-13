/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package managment;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import exceptions.NotValidIPAddress;
import exceptions.RangeIsNotValidException;
import exceptions.RangeNotBigEnoughException;


/**
 * This class contains various functions and methods that are used in the
 * management of Network information. This includes for example methods for MAC
 * addresses, IP addresses and processing of IP ranges.
 * 
 * @author Bahram Malaekeh
 */
public class NetworkManagment
{

	/**
	 * This is the pattern used by the system to verify and validate MAC
	 * addresses.
	 * 
	 * @return The pattern for MAC addresses in the system.
	 */
	public static Pattern getMACpattern()
	{
		// The pattern for MAC addresses
		Pattern MAC_PATTERN = Pattern
				.compile("((([0-9a-fA-F]){1,2}[-:]){5}([0-9a-fA-F]){1,2})");


		return MAC_PATTERN;
	}


	/**
	 * This is the pattern used by the system to verify and validate IP
	 * addresses.
	 * 
	 * @return The pattern for IP addresses in the system.
	 */
	public static Pattern getIPpattern()
	{
		String _255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

		// The pattern for IP addresses
		Pattern IP_PATTERN = Pattern.compile("^(?:" + _255 + "\\.){3}" + _255
				+ "$");


		return IP_PATTERN;
	}




	/**
	 * This function process two strings to see if there could be created a
	 * valid range of IP's between these two given IP addresses. A boolean is
	 * returned saying if a valid range is possible. Takes two {@link String
	 * Strings} that should be valid IP addresses. These are checked and
	 * validated.
	 * 
	 * @param fromIP
	 *            The start if the IP range.
	 * @param toIP
	 *            The end of the IP range.
	 * @return A boolean saying if a valid range is possible.
	 */
	public static boolean processRange(String fromIP, String toIP)
			throws RangeIsNotValidException, NotValidIPAddress,
			RangeNotBigEnoughException
	{
		// Splits the fromIP into strings on the symbol "."
		String[] fromString = fromIP.split("\\.");

		// Splits the toIP into strings on the symbol "."
		String[] toString = toIP.split("\\.");


		// If the IP does not match the IP pattern(valid IP)
		if ( fromString.length != 4
				|| (!(getIPpattern().matcher(fromIP).matches())) )
		{
			throw new NotValidIPAddress(fromIP);
		}


		// If the IP does not match the IP pattern(valid IP)
		if ( toString.length != 4
				|| (!(getIPpattern().matcher(toIP).matches())) )
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
						// Does not matter what IP is chosen because both IPs
						// are the same
						throw new RangeNotBigEnoughException(fromIP);
					}
				}
			}
		}

		// If the method gets to this point the fromIP is larger then the toIP
		throw new RangeIsNotValidException(fromIP, toIP);
	}




	/**
	 * This function determines whether or not the first given String
	 * representing an IP is higher then the second given String representing an
	 * IP.
	 * 
	 * @param fromIP
	 *            The first IP(should be the lower IP).
	 * @param toIP
	 *            The second IP(should be the higher IP).
	 * @return A boolean on whether or not the first given IP is higher then the
	 *         second IP.
	 * @throws NotValidIPAddress
	 * @throws RangeNotBigEnoughException
	 */
	public static boolean isIPhigherThenIP(String fromIP, String toIP)
			throws NotValidIPAddress, RangeNotBigEnoughException
	{
		// Splits the fromIP into strings on the symbol "."
		String[] fromString = fromIP.split("\\.");

		// Splits the toIP into strings on the symbol "."
		String[] toString = toIP.split("\\.");


		// If the IP does not match the IP pattern(valid IP)
		if ( fromString.length != 4
				|| (!(getIPpattern().matcher(fromIP).matches())) )
		{
			throw new NotValidIPAddress(fromIP);
		}


		// If the IP does not match the IP pattern(valid IP)
		if ( toString.length != 4
				|| (!(getIPpattern().matcher(toIP).matches())) )
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
						// Does not matter what IP is chosen because both IPs
						// are the same
						throw new RangeNotBigEnoughException(fromIP);
					}
				}
			}
		}


		return false;
	}




	public static boolean hostIsUp(String host) throws UnknownHostException,
			IOException
	{
		int timeOut = 5000; // No hurry

		return InetAddress.getByName(host).isReachable(timeOut);
	}
}
