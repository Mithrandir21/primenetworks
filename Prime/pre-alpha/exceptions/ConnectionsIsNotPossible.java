/**
 * 
 */
package exceptions;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class ConnectionsIsNotPossible extends Exception
{
	// Container for names of the two objects that have a connection between them
	String a = null;
	String b = null;
	String reason = null;
	
	
	/**
	 * Description NEEDED!
	 *
	 */
	public ConnectionsIsNotPossible(String a, String b, String reason)
	{
		// Sets the names of the objects that have a connection between them
		this.a = a;
		this.b = b;
		this.reason = reason;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage()
	{
		String output = "A connection between object a, " + a
		+ " and object b, " + b + ", is no possible because " + reason;
		
		return output;
	}
}
