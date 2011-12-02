/**
 * 
 */
package managment;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;


/**
 * This
 * 
 * @author Bahram Malaekeh
 * 
 */
public class DesktopCustomLoggerFormatter extends SimpleFormatter
{
	//
	// Create a DateFormat to format the logger timestamp.
	//
	private static final DateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy hh:mm:ss.SSS");

	// Line separator string. This is the value of the line.separator
	// property at the moment that the SimpleFormatter was created.
	private String lineSeparator = System.getProperty("line.separator");


	/*
	 * (non-Javadoc)
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 */
	@Override
	public String format(LogRecord record)
	{
		StringBuilder builder = new StringBuilder(1000);
		builder.append(dateFormat.format(new Date(record.getMillis()))).append(
				" - ");
		builder.append("[" + getSourceName(record) + "] - ");
		builder.append("[").append(record.getLevel()).append("] - ");
		builder.append("\t");
		builder.append("\t");
		builder.append(formatMessage(record));
		builder.append(lineSeparator);
		return builder.toString();
	}



	/**
	 * Returns a text containing a string with maximum and minimum length of 40
	 * characters.
	 */
	private String getSourceName(LogRecord record)
	{
		String sourceText = record.getSourceClassName() + "."
				+ record.getSourceMethodName();

		// If the text is longer then 40 chars.
		if ( sourceText.length() > 40 )
		{
			sourceText = "..." + sourceText.substring(sourceText.length() - 37);
		}
		else if ( sourceText.length() < 40 )
		{
			while ( sourceText.length() < 40 )
			{
				sourceText = sourceText + " ";
			}
		}

		return sourceText;
	}
}
