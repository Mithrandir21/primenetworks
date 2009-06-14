package logistical;


import java.util.regex.Pattern;

import managment.NetworkManagment;


/**
 * This class contains logic checking tests for validating names used through out the system and validation for IP
 * addresses.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class checkLogic
{
	/**
	 * Validates the given string against the systems internal string pattern.
	 * 
	 * @param text
	 *            The string that is to be validated.
	 * @return True or false depending on the string matching the system name pattern.
	 */
	public static boolean validateName(String text)
	{
		if ( text.equals("") )
		{
			return false;
		}

		// This pattern will allow letters, numbers, spaces, underscore and the letters �,� and �.
		String pat = "([\\w\\-_������\\d\\s]+)";

		Pattern test = Pattern.compile(pat);

		return test.matcher(text).matches();

	}


	/**
	 * Validates the given String, that should represent an IP address. Matches the given IP against the
	 * {@link NetworkManagment.getIPpattern}.
	 * 
	 * @param IP
	 *            The IP string to be validated.
	 * @return True or false depending on the IP matching the system name pattern.
	 */
	public static boolean validateIP(String IP)
	{
		return NetworkManagment.getIPpattern().matcher(IP).matches();
	}
}
