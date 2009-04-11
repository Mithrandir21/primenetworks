package objects.peripheralObjects;


import java.io.Serializable;

import objects.ExternalHardware;


/**
 * This class represents a mouse. This device can be connected to any device that supports the conncetion type of this
 * device. It contains information on what kind of capability the mouse has and what kind of connection interface it
 * has.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Mouse extends ExternalHardware implements Serializable
{

	// Boolean to check whether or not a mouse is a laptop mouse
	private boolean isLaptopMouse;


	// Constructor of the class
	/**
	 * Constructor of the mouse class.
	 * 
	 * @param Name
	 *            The name of the mouse.
	 * @param Desc
	 *            The description of the mouse.
	 * @param MouseConnectionInterface
	 *            The connection interface supported by a mouse.
	 */
	public Mouse(String Name, String Desc, String[] MouseConnectionInterface)
	{
		super(Name, Desc, MouseConnectionInterface);

	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get info on whether or not a mouse is a laptop mouse.
	 */
	public boolean isLaptopMouse()
	{
		return isLaptopMouse;
	}


	// SET METHODES

	/**
	 * Sets info on whether or not a mouse is a laptop mouse.
	 */
	public void setIsLaptopMouse(boolean info)
	{
		isLaptopMouse = info;
	}
}