package exceptions;


import objects.Object;


/**
 * This is exception class extends the super class, {@link java.lang.Exception
 * Exception} class. It is an exception made to handle situations that occur
 * when a object, such as a {@link objects.hardwareObjects.CPU CPU} or {@link objects.hardwareObjects.HDD
 * motherboard}, is searched for in an array and not found. It also contains a
 * datafield for the object that was not found.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ObjectNotFoundInArrayException extends Exception
{
	// A container for the object that was not found.
	Object object;


	/**
	 * Constructs a new exception with the specified detail message, along with
	 * the object that is not found. It also stores the object that was not
	 * found, which can be accessed by the {@link #getObject() getObject()}
	 * function.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later
	 *            retrieval by the {@link #getMessage()} method.
	 * @param notFoundObject
	 *            The object that was not found within an array
	 */
	public ObjectNotFoundInArrayException(String message, Object notFoundObject)
	{
		super(message);

		object = notFoundObject;
	}



	/**
	 * Retrieves the object that was not found.
	 */
	public Object getObject()
	{

		return object;
	}
}
