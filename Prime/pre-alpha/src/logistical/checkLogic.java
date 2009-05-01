package logistical;

import java.util.regex.Pattern;

public class checkLogic
{
	public static boolean validateWidgetName(String text)
	{
		Pattern test = Pattern.compile("\\w");
		
		return test.pattern().matches(text);
	}
}
