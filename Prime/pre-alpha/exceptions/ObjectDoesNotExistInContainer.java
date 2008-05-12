package exceptions;

import objects.Object;;

/**
 * Description NEEDED!
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ObjectDoesNotExistInContainer extends Exception
{
	// The object that was attempted removed to a container
	Object object;
	
	
    /**
     * Description NEEDED!
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
	 *
	 */
	public Object getObject()
	{
	
		return object;
	}
}
