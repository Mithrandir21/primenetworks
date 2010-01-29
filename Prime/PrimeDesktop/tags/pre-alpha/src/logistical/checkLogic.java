package logistical;


import java.util.regex.Pattern;

import managment.NetworkManagment;


public class checkLogic
{
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param text
	 * @return
	 */
	public static boolean validateName(String text)
	{
		if ( text.equals("") )
		{
			return false;
		}

		// This pattern will allow letters, numbers, spaces, underscore
		// and the letters �,� and �.
		String pat = "([\\w\\-_������\\d\\s]+)";

		Pattern test = Pattern.compile(pat);

		return test.matcher(text).matches();

	}


	/**
	 * @param IP
	 * @return
	 */
	public static boolean validateIP(String IP)
	{
		return NetworkManagment.getIPpattern().matcher(IP).matches();
	}
}
