/**
 * 
 */
package objects;

import hardware.Motherboard;

import java.io.Serializable;


/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class Infrastructure extends Object implements Serializable
{

	/**
	 * TODO - Description NEEDED!
	 *
	 * @param Name
	 * @param Desc
	 */
	public Infrastructure(String Name, String Desc)
	{
		super(Name, Desc);
	}
	
	
	/**
	 * TODO - Description NEEDED!
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
	 * TODO - Description NEEDED!
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
	 * TODO - Description NEEDED!
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
	 * TODO - Description NEEDED!
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
