package logistical;


import java.util.ArrayList;

import objects.Object;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ContainerLogistic
{

	/**
	 * Checks to see if a specific object exist in the container with the same
	 * name as the given object.
	 */
	public static boolean checkObjectNameExistence(ArrayList<Object> container,
			Object obj)
	{
		// The size of the container
		int arraySize = container.size();

		// Goes through all of the container
		for ( int i = 0; i < arraySize; i++ )
		{
			// Checks to see if it is a null pointer
			if ( container.get(i) != null )
			{
				/*
				 * If the name of the container object is the same as the given
				 * object, true is returned.
				 */
				if ( container.get(i).getObjectName().equals(
						obj.getObjectName()) )
				{
					return true;
				}
			}
		}

		// The name does not exist elsewhere in the container
		return false;
	}
}
