/**
 * 
 */
package objects;


import hardware.Motherboard;

import java.io.Serializable;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class Infrastructure extends Object implements Serializable
{

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param Name
	 * @param Desc
	 */
	public Infrastructure(String Name, String Desc)
	{
		super(Name, Desc);
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param Name
	 * @param Desc
	 * @param SupConInt
	 */
	public Infrastructure(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param Name
	 * @param Desc
	 * @param objectComponents
	 */
	public Infrastructure(String Name, String Desc, Object[] objectComponents)
	{
		super(Name, Desc, objectComponents);
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param Name
	 * @param Desc
	 * @param SupConInt
	 * @param mb
	 */
	public Infrastructure(String Name, String Desc, String[] SupConInt, Motherboard mb)
	{
		super(Name, Desc, SupConInt, mb);
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param Name
	 * @param Desc
	 * @param SupConInt
	 * @param objectComponents
	 */
	public Infrastructure(String Name, String Desc, String[] SupConInt, Object[] objectComponents)
	{
		super(Name, Desc, SupConInt, objectComponents);
	}

}
