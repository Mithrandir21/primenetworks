package objects;



import java.io.Serializable;

import objects.hardwareObjects.Motherboard;


/**
 * An abstract super class for all hardware objects in the system, including
 * {@link objects.Servers Servers}, {@link objects.Clients Clients} and
 * Peripherals. MUST ADD INFO
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public abstract class Hardware extends Object implements Serializable
{


	// FIXME - Create a component rating system.


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 */
	public Hardware(String Name, String Desc)
	{
		super(Name, Desc);
	}


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 */
	public Hardware(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 */
	public Hardware(String Name, String Desc, Object[] DesktopComponents)
	{
		super(Name, Desc, DesktopComponents);
	}


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 */
	public Hardware(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents)
	{
		super(Name, Desc, SupConInt, DesktopComponents);
	}


	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 */
	public Hardware(String Name, String Desc, String[] SupConInt,
			Motherboard objectMB)
	{
		super(Name, Desc, SupConInt, objectMB);
	}
}
