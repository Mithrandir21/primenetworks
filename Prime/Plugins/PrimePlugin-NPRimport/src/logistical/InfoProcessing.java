/**
 * 
 */
package logistical;


import objects.Software.base;
import objects.softwareObjects.OperatingSystem;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class InfoProcessing
{
	/**
	 * This function takes a string and look for known words to determine the OS
	 * and then returns an {@link OperatingSystem} object.
	 */
	public static OperatingSystem determineOS(String osText)
	{
		boolean x64bit = false;

		if ( osText.contains("x64") )
		{
			x64bit = true;
		}


		// The type of OS it is, WINDOWS, GNU_LINUX, UNIX, MAC.
		base OSbase = null;

		if ( osText.contains("Microsoft Windows") )
		{
			OSbase = base.WINDOWS;
		}
		else if ( osText.contains("Windows") )
		{
			OSbase = base.WINDOWS;
		}
		else if ( osText.contains("Microsoft") )
		{
			OSbase = base.WINDOWS;
		}
		// Apple
		else if ( osText.contains("Apple") )
		{
			OSbase = base.MAC;
		}
		else if ( osText.contains("OS X") )
		{
			OSbase = base.MAC;
		}
		// UNIX
		else if ( osText.contains("Unix") )
		{
			OSbase = base.UNIX;
		}
		else if ( osText.contains("SCO") )
		{
			OSbase = base.UNIX;
		}
		// Linux
		else if ( osText.contains("Linux") )
		{
			OSbase = base.GNU_LINUX;
		}
		else if ( osText.contains("Novell") )
		{
			OSbase = base.GNU_LINUX;
		}
		else if ( osText.contains("Red Hat") )
		{
			OSbase = base.GNU_LINUX;
		}


		// Determines the OS version (only Windows and Mac).
		String osVersion = "1";

		// Windows
		if ( osText.contains("Windows") && osText.contains("2000") )
		{
			osVersion = "5.0";
		}
		if ( osText.contains("Windows") && osText.contains("2003") )
		{
			osVersion = "5.2";
		}
		if ( osText.contains("Windows") && osText.contains("2008")
				&& osText.contains("R2") )
		{
			osVersion = "6.1";
		}
		if ( osText.contains("Windows") && osText.contains("2008") )
		{
			osVersion = "6.0";
		}
		else if ( osText.contains("XP") && osText.contains("x64") )
		{
			osVersion = "5.2";
		}
		else if ( osText.contains("XP") )
		{
			osVersion = "5.1";
		}
		else if ( osText.contains("Vista") )
		{
			osVersion = "6.0";
		}
		else if ( osText.contains("Windows") && osText.contains("7") )
		{
			osVersion = "6.1";
		}
		// Apple
		else if ( osText.contains("Apple Mac") )
		{
			int index1 = 14;
			int index2 = osText.indexOf(" on Intel");

			try
			{
				Integer.parseInt(osText.substring(index1, index2));
				osVersion = osText.substring(index1, index2);
			}
			catch ( Exception e )
			{
				// osVersion is already set to 1
			}
		}


		// Creates the OS
		OperatingSystem os = new OperatingSystem(osText, osText, osVersion);

		os.setIs64bit(x64bit);

		if ( OSbase != null )
		{
			if ( OSbase == base.WINDOWS || OSbase == base.MAC )
			{
				os.setHasGUI(true);
			}

			os.setBase(OSbase);
		}

		return os;
	}
}
