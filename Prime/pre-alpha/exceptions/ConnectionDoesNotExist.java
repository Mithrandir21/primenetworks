package exceptions;


/**
 * ConnectionDoesNotExist exception will be thrown when a connection is searched for between two objects that do not
 * have a connection between them.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ConnectionDoesNotExist extends Exception
{
	// Container for names of the two objects that have no connection between
	// them
	String a = null;

	String b = null;



	/**
	 * Constructs a new exception with parameters for two {@link Object Objects} . The two parameters will be the two
	 * objects that should, but do not, have a connection between them.
	 * 
	 * @param a
	 *            An {@link Object Objects} in the connection.
	 * @param b
	 *            An {@link Object Objects} in the connection.
	 */
	public ConnectionDoesNotExist(String a, String b)
	{
		// Sets the names of the objects that have connection between the
		this.a = a;
		this.b = b;
	}




	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "There exists no connection between object a, " + a + " and object b, " + b + ".";

		return output;
	}

}
