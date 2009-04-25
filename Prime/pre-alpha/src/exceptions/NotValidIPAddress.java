/**
 * 
 */
package exceptions;


/**
 * This exception is thrown when a given string is not a valid IP address.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class NotValidIPAddress extends Exception
{
	// The text that is not a valid IP address
	String text;



	/**
	 * A constructor that set the given string as the exceptions field of text. The given String is the invalid IP
	 * address string.
	 * 
	 * @param IPtext The invalid IP address string.
	 */
	public NotValidIPAddress(String IPtext)
	{
		text = IPtext;
	}
	
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "This, " + text + ", is not a valid IP address.";
		
		return output;
	}
	
}
