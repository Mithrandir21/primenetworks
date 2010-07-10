/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package logistical;


import java.util.regex.Pattern;

import managment.NetworkManagment;
import connections.ConnectionUtils;
import connections.DeviceConnection;
import connections.NetworkConnection;


/**
 * This class contains logic checking tests for validating names used through
 * out the system and validation for IP addresses.
 * 
 * @author Bahram Malaekeh
 */
public class checkLogic
{
	/**
	 * Validates the given string against the systems internal string pattern.
	 * 
	 * @param text
	 *            The string that is to be validated.
	 * @return True or false depending on the string matching the system name
	 *         pattern.
	 */
	public static boolean validateName(String text)
	{
		if ( text.equals("") )
		{
			return false;
		}

		// This pattern will allow letters, numbers, spaces, underscore and the
		// letters Ø,Æ and Å.
		String pat = "([\\w\\-_øæåØÆÅ\\d\\s]+)";

		Pattern test = Pattern.compile(pat);

		return test.matcher(text).matches();

	}


	/**
	 * Validates the given String, that should represent an IP address. Matches
	 * the given IP against the NetworkManagment.getIPpattern().
	 * 
	 * @param IP
	 *            The IP string to be validated.
	 * @return True or false depending on the IP matching the system name
	 *         pattern.
	 */
	public static boolean validateIP(String IP)
	{
		return NetworkManagment.getIPpattern().matcher(IP).matches();
	}



	// FIXME - Add user-added connection types
	/**
	 * Returns a connection class depending on the given string. MUST BE WORKED
	 * ON.
	 * 
	 * @param type
	 */
	public static Class<?> getConClass(String type)
	{
		if ( type.equals(ConnectionUtils.RJ45) )
		{
			return NetworkConnection.class;
		}
		else if ( type.equals(ConnectionUtils.Wireless) )
		{
			return NetworkConnection.class;
		}
		else if ( type.equals(ConnectionUtils.Coax) )
		{
			return NetworkConnection.class;
		}
		else if ( type.equals(ConnectionUtils.Fiber) )
		{
			return NetworkConnection.class;
		}
		else if ( type.equals(ConnectionUtils.USB) )
		{
			return DeviceConnection.class;
		}
		else if ( type.equals(ConnectionUtils.FireWire) )
		{
			return DeviceConnection.class;
		}


		return null;
	}
}
