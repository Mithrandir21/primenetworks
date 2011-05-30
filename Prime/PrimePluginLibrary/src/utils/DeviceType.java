/**
 * 
 */
package utils;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public enum DeviceType
{

	WINDOWS, UNIX, MAC;

	/**
	 * TODO - Description
	 */
	public static DeviceType getLocalHostType()
	{
		if ( System.getProperty("os.name").contains("Windows") )
		{
			return WINDOWS;
		}
		else if ( System.getProperty("os.name").startsWith("Mac") )
		{
			return MAC;
		}
		else
		{
			return UNIX;
		}
	}
}
