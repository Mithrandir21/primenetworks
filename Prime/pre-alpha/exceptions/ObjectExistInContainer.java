package exceptions;

import objects.Object;;

/**
 * Description NEEDED!
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ObjectExistInContainer extends Exception
{
	// The object that was attempted added to a container
	Object object;
	
	
    /**
     * Description NEEDED!
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
	 *
	 */
	public Object getObject()
	{
	
		return object;
	}
}
