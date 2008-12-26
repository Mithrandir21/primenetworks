package exceptions;


/**
 * ConnectionDoesExist exception will be thrown when a connection is found
 * between two objects. (This expection should be used when trying to validate
 * and verify the system.)
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ConnectionDoesExist extends Exception
{
	// Container for names of the two objects that have a connection between
	// them
	String a = null;

	String b = null;



	/**
	 * Constructs a new exception with parameters for two {@link Object Objects}
	 * . The two parameters will be the two objects that should not, but do,
	 * have a connection between them.
	 * 
	 * @param a
	 *            An {@link Object Objects} in the connection.
	 * @param b
	 *            An {@link Object Objects} in the connection.
	 */
	public ConnectionDoesExist(String a, String b)
	{
		// Sets the names of the objects that have a connection between them
		this.a = a;
		this.b = b;
	}




	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "There exists a connection between object a, " + a
				+ " and object b, " + b + ".";

		return output;
	}

}
