package objects.peripheralObjects;



import java.io.Serializable;

import objects.ExternalHardware;
import objects.hardwareObjects.Motherboard;


/**
 * This class represents a scanner. This device can be connected to any device that supports the conncetion type of this
 * device. It contains information on what kind of capability the printer machine has and what kind of connection
 * interface is has.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Scanner extends ExternalHardware implements Serializable
{

	// Maximum resolution of a scanner
	private String resolution;

	/**
	 * Constructor of the scanner class.
	 * 
	 * @param Name
	 *            The name of the scanner.
	 * @param Desc
	 *            The description of the scanner.
	 * @param Sresolution
	 *            Maximum resolution of a scanner. <br>
	 *            This can be a NULL pointer. The value will then be a NULL pointer.
	 * @param SconnectionInterfaces
	 *            Connection interfaces supported by the scanner. An array of Strings.
	 */
	public Scanner(String Name, String Desc, String Sresolution, String[] SconnectionInterfaces)
	{
		super(Name, Desc, SconnectionInterfaces);

		if ( Sresolution != null )
		{
			resolution = Sresolution;
		}
	}


	/**
	 * Constructor of the scanner class.
	 * 
	 * @param Name
	 *            The name of the scanner.
	 * @param Desc
	 *            The description of the scanner.
	 * @param Sresolution
	 *            Maximum resolution of a scanner. <br>
	 *            This can be a NULL pointer. The value will then be a NULL pointer.
	 * @param SconnectionInterfaces
	 *            Connection interfaces supported by the scanner. An array of Strings.
	 */
	public Scanner(String Name, String Desc, String Sresolution, String[] SconnectionInterfaces, Motherboard objectMB)
	{
		super(Name, Desc, SconnectionInterfaces, objectMB);

		if ( Sresolution != null )
		{
			resolution = Sresolution;
		}
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get the resolution of the scanner.
	 */
	public String getScannerResolution()
	{
		return resolution;
	}


	// SET METHODES

	/**
	 * Sets the resolution of the scanner.
	 */
	public void setScannerResolution(String Sresolution)
	{
		resolution = Sresolution;
	}

}