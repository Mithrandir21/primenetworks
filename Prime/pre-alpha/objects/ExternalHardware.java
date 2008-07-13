package objects;

import hardware.Motherboard;

import java.io.Serializable;


/**
 * An abstract super class for all external hardware objects in the system, 
 * including {@link  objects.Servers  Servers}, {@link  objects.Clients  Clients} and
 * {@link  peripheral  Peripherals}.
 * 
 * MUST ADD INFO!!
 * TODO - ADD INFO HERE
 *
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public abstract class ExternalHardware extends Hardware implements Serializable
{

	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 */
	public ExternalHardware(String Name, String Desc)
	{
		super(Name,Desc);
	}
	

	/**
	 * Constructor of an abstract hardware superclass.
	 *
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 * @param SupConInt
	 *            An array of strings that describes the supported connection
	 *            interfaces.
	 */
	public ExternalHardware(String Name, String Desc, String[] SupConInt)
	{
		super(Name,Desc,SupConInt);
	}
	
	
	/**
	 * Constructor of an abstract hardware superclass.
	 * 
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 * @param DesktopComponents
	 *            The initial components an instance of a desktop has.
	 */
	public ExternalHardware(String Name, String Desc, Object[] DesktopComponents)
	{
		super(Name,Desc,DesktopComponents);
	}
	
	
	/**
	 * Constructor of an abstract hardware superclass.
	 *
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 * @param SupConInt
	 *            An array of strings that describes the supported connection
	 *            interfaces.
	 * @param DesktopComponents
	 *            The initial components an instance of a desktop has.
	 */
	public ExternalHardware(String Name, String Desc, String[] SupConInt, Object[] DesktopComponents)
	{
		super(Name,Desc,SupConInt,DesktopComponents);
	}
	
	
	/**
	 * Constructor of an abstract hardware superclass.
	 *
	 * @param Name
	 *            The name of the hardware device.
	 * @param Desc
	 *            The description of the hardware decvice.
	 * @param SupConInt
	 *            An array of strings that describes the supported connection
	 *            interfaces.
	 * @param deviceMB
	 *            The initial {@link hardware.Motherboard motherboard} the hardware has.
	 */
	public ExternalHardware(String Name, String Desc, String[] SupConInt, Motherboard deviceMB)
	{
		super(Name,Desc,SupConInt,deviceMB);
	}
}
