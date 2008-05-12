package exceptions;
import objects.Object;


/**
 * Description NEEDED!
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ConnectionDoesExist extends Exception
{
	// Container for names of the two objects that have a connection between them
	String a = null;
	String b = null;
	
	
	
	/**
	 * Description NEEDED!
	 *
	 */
	public ConnectionDoesExist(String a, String b)
	{
		// Sets the names of the objects that have a connection between them
		this.a = a;
		this.b = b;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage()
	{
		String output = "The already exists a connection between object a, " + a
		+ " and object b, " + b + ".";
		
		return output;
	}
	
}
