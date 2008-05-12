package objects;

import java.io.Serializable;


/**
 * An abstract super class for all hardware objects in the system, 
 * including {@link  objects.Servers  Servers}, {@link  objects.Clients  Clients} and
 * {@link  peripheral  Peripherals}.
 * 
 * MUST ADD INFO
 *
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public abstract class Hardware extends Object implements Serializable
{

	
	// TODO - Create a component rating system.
	
	
	/**
	 * Constructor of an abstract hardware superclass.
	 *
	 * @param Name The name of the hardware device.
	 * @param Desc The description of the hardware decvice.
	 */
	public Hardware(String Name, String Desc)
	{
		super(Name,Desc);
	}
	
	
	/**
	 * Constructor of an abstract hardware superclass.
	 *
	 * @param Name The name of the hardware device.
	 * @param Desc The description of the hardware decvice.
	 */
	public Hardware(String Name, String Desc, String[] SupConInt)
	{
		super(Name,Desc,SupConInt);
	}
	
	
	
}
