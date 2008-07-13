/**
 * 
 */
package exceptions;


import hardware.Motherboard;
import objects.Object;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class PortIsNotRegisteredOnMotherboard extends Exception
{
	Motherboard mb = null;

	Object obj = null;
	
	String conType = "";


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param aMB
	 * @param aOb
	 */
	public PortIsNotRegisteredOnMotherboard(Motherboard aMB, Object aOb, String type)
	{
		mb = aMB;
		obj = aOb;
		conType = type;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage()
	{
		String output = "The motherboard, " + mb.getObjectName() + ", on the object "
				+ obj.getObjectName() + " has not registered " + conType + " ports in the correct way.";

		return output;
	}
}
