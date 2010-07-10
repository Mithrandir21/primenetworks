/**
 * 
 */
package exceptions;


import objects.Object;
import objects.hardwareObjects.Motherboard;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class MotherboardNotFound extends Exception
{
	/**
	 * The object that does not contain a {@link Motherboard}.
	 */
	Object object = null;


	/**
	 * A constructor with a given {@link Object}.
	 * 
	 * @param obj
	 *            The object that does not contain a {@link Motherboard}.
	 */
	public MotherboardNotFound(Object obj)
	{
		object = obj;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "No Motherboard was found for object: "
				+ object.getObjectName();

		return output;
	}

}
