package exceptions;


import objects.Object;


;

/**
 * This extension of the {@link Exception Exception} class is created and thrown when an object that is searched for is
 * found in a specific container(when it should not be found in that container).
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ObjectExistInContainer extends Exception
{
	// The object that was attempted added to a container
	Object object;


	/**
	 * Constructor for this exception including the {@link Object} found.
	 * 
	 * @param message
	 * @param FoundObject
	 */
	public ObjectExistInContainer(String message, Object FoundObject)
	{
		super(message);

		object = FoundObject;
	}



	/**
	 * Retrieves the object that was found.
	 */
	public Object getObject()
	{

		return object;
	}
}
