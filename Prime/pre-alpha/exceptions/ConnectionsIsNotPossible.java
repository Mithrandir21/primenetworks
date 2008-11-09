/**
 * 
 */
package exceptions;


import objects.Object;


/**
 * This exceptions is thrown when a connection is not possible between two
 * {@link Object Objects}. There is also a reason given.
 * 
 * @author Bahram Malaekeh
 */
public class ConnectionsIsNotPossible extends Exception
{
	// Container for names of the two objects that have a connection between
	// them
	String a = null;

	String b = null;

	String reason = null;


	/**
	 * Constructor for this exception including a reason.
	 * 
	 * @param a
	 * @param b
	 * @param reason
	 *            The reason why a connection is not possible between object a
	 *            and object b.
	 */
	public ConnectionsIsNotPossible(String a, String b, String reason)
	{
		// Sets the names of the objects that have a connection between them
		this.a = a;
		this.b = b;
		this.reason = reason;
	}




	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "A connection between object a, " + a
				+ " and object b, " + b + ", is not possible because " + reason;

		return output;
	}
}
