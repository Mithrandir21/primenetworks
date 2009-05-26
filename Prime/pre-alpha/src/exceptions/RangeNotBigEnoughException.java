/**
 * 
 */
package exceptions;


/**
 * This exception this thrown when a IP range is attempted create with only one IP.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class RangeNotBigEnoughException extends Exception
{
	// The IP address
	String ip;



	/**
	 * A constructor with the single IP address as the given text string.
	 * 
	 * @param text
	 *            The single IP address.
	 */
	public RangeNotBigEnoughException(String text)
	{
		ip = text;
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "You can not have a IP range with only a single IP, such as the given ip(" + ip + ").";
		
		return output;
	}
}
