package exceptions;


/**
 * Description NEEDED!
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ConnectionDoesNotExist extends Exception
{
	// Container for names of the two objects that have no connection between them
	String a = null;
	String b = null;
	
	
	
	/**
	 * Description NEEDED!
	 *
	 */
	public ConnectionDoesNotExist(String a, String b)
	{
		// Sets the names of the objects that have connection between the
		this.a = a;
		this.b = b;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage()
	{
		String output = "The exists no connection between object a, " + a
		+ " and object b, " + b + ".";
		
		return output;
	}
	
}
