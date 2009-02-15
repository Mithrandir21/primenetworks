/**
 * 
 */
package objects;


import hardware.Motherboard;

import java.io.Serializable;


/**
 * CLASSDesc - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class Infrastructure extends Object implements Serializable
{

	/**
	 * A constructor for any Infrastructure object.
	 * 
	 * @param Name
	 *            The name of the Object.
	 * @param Desc
	 *            The description of the object.
	 */
	public Infrastructure(String Name, String Desc)
	{
		super(Name, Desc);
	}


	/**
	 * A constructor for any Infrastructure object.
	 * 
	 * @param Name
	 *            The name of the Object.
	 * @param Desc
	 *            The description of the object.
	 * @param SupConInt
	 *            A String array of supported interfaces.
	 */
	public Infrastructure(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}


	/**
	 * A constructor for any Infrastructure object.
	 * 
	 * @param Name
	 *            The name of the Object.
	 * @param Desc
	 *            The description of the object.
	 * @param objectComponents
	 *            An Objects array that contains the objects internal
	 *            components.
	 */
	public Infrastructure(String Name, String Desc, Object[] objectComponents)
	{
		super(Name, Desc, objectComponents);
	}


	/**
	 * A constructor for any Infrastructure object.
	 * 
	 * @param Name
	 *            The name of the Object.
	 * @param Desc
	 *            The description of the object.
	 * @param SupConInt
	 *            A String array of supported interfaces.
	 * @param mb
	 * 			  The objects Motherboard.
	 */
	public Infrastructure(String Name, String Desc, String[] SupConInt,
			Motherboard mb)
	{
		super(Name, Desc, SupConInt, mb);
	}


	/**
	 * A constructor for any Infrastructure object.
	 * 
	 * @param Name
	 *            The name of the Object.
	 * @param Desc
	 *            The description of the object.
	 * @param SupConInt
	 *            A String array of supported interfaces.
	 * @param objectComponents
	 *            An Objects array that contains the objects internal
	 *            components.
	 */
	public Infrastructure(String Name, String Desc, String[] SupConInt,
			Object[] objectComponents)
	{
		super(Name, Desc, SupConInt, objectComponents);
	}

}
