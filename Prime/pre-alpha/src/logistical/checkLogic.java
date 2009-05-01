package logistical;

import java.util.regex.Pattern;

public class checkLogic
{
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param text
	 * @return
	 */
	public static boolean validateWidgetName(String text)
	{
		String pat = "([\\w\\-_øæåØÆÅ\\d\\s]+)";
		
		Pattern test = Pattern.compile(pat);
		
		return test.matcher(text).matches();
	}
}
