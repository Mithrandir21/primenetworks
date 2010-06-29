/**
 * 
 */
package exceptions;


/**
 * RangeIsNotValidException exception will be thrown when two IP address does not make a valid IP rang. (This exception
 * should be used when trying to validate and verify IP ranges.)
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class RangeIsNotValidException extends Exception
{
	// The two different IPs
	String from = null;

	String to = null;



	/**
	 * Constructs a new exception with parameters for two {@link Object Objects} . The two parameters will be the two
	 * different IP addresses that can create a valid IP range.s
	 * 
	 * @param from
	 *            Start IP address.
	 * @param to
	 *            End IP address.
	 */
	public RangeIsNotValidException(String from, String to)
	{
		this.from = from;
		this.to = to;
	}



	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "There can not be created a valid IP range between these two IP address, "
				+ from + " and " + to + ".";

		return output;
	}
}
