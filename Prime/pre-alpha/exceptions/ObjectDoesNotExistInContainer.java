package exceptions;


import objects.Object;


;

/**
 * This extension of the {@link java.lang.Exception Exception} class is created
 * and thrown when an object that is searched for is not found in a specific
 * container.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ObjectDoesNotExistInContainer extends Exception
{
	// The object that was attempted removed to a container
	Object object;


	/**
	 * Constructor
	 * 
	 * @param message
	 * @param notFoundObject
	 */
	public ObjectDoesNotExistInContainer(String message, Object notFoundObject)
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
