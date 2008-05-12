package exceptions;


/**
 * This is exception class extends the super class, {@link  java.lang.Exception  Exception} class.
 * It is an exception made to handle situations that occur when a string is 
 * searched for in an array and not found.
 * It also contains a datafield for the string that was not found.
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class StringNotFoundInArrayException extends Exception
{
	// A container for the string that was not found.
	String string;
	
	
    /**
     * Constructs a new exception with the specified detail message, along with the
     * string that is not found. It also stores the string that was not found, which can be
     * accessed by the {@link  #getString()  getString()} function. 
     *
     * @param   message   the detail message. The detail message is saved for 
     *          later retrieval by the {@link #getMessage()} method.
     * @param   notFoundString  The string that was not found within an array
     */
    public StringNotFoundInArrayException(String message, String notFoundString) 
    {
    	super(message);
    	
    	string = notFoundString;
    }


	
	/**
	 * Retrieves the object that was not found.
	 *
	 */
	public String getString()
	{
	
		return string;
	}
}
    