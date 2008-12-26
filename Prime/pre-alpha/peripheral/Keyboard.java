package peripheral;


import java.io.Serializable;

import objects.ExternalHardware;


/**
 * This class represents a keyboard. This device can be connected to any device
 * that supports the conncetion type of this device. It contains information on
 * what kind of capability the keyboard has and what kind of connection
 * interface it has.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class Keyboard extends ExternalHardware implements Serializable
{

	// Boolean to check whether or not a keyboard is a laptop keyboard
	private boolean isLaptopKeyboard;


	// Constructor of the class
	/**
	 * Description NEEDED!
	 * 
	 * @param Name
	 *            The name of the keyboard.
	 * @param Desc
	 *            The description of the keyboard.
	 * @param KeyboardConnectionInterface
	 *            The connection interface supported by a keyboard.
	 */
	public Keyboard(String Name, String Desc,
			String[] KeyboardConnectionInterface)
	{
		super(Name, Desc, KeyboardConnectionInterface);
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES


	/**
	 * Get info on whether or not a keyboard is a laptop keyboard.
	 */
	public boolean isLaptopKeyboard()
	{
		return isLaptopKeyboard;
	}



	// SET METHODES


	/**
	 * Sets info on whether or not a keyboard is a laptop keyboard.
	 */
	public void setIsLaptopKeyboard(boolean info)
	{
		isLaptopKeyboard = info;
	}
}