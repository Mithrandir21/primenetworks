/**
 * 
 */
package objects.peripheralObjects;


import objects.hardwareObjects.Motherboard;
import widgetManipulation.WidgetNetworkInfo;


/**
 * This class represents a Network printer. This device is almost exactly the same as a {@link Printer}, but contains a
 * {@link WidgetNetworkInfo} object that contains information about the network infor for the device. 
 * 
 * @author Bahram Malaekeh
 * 
 */
public class NetworkPrinter extends Printer
{
	private WidgetNetworkInfo info = new WidgetNetworkInfo();


	/**
	 * Constructor of the networkprinter class.
	 * 
	 * @param Name
	 *            The name of the printer.
	 * @param Desc
	 *            The description of the printer.
	 * @param PprinterType
	 *            Type of printer. ink or laser.
	 * @param objectMB
	 */
	public NetworkPrinter(String Name, String Desc, String PprinterType, Motherboard objectMB)
	{
		super(Name, Desc, PprinterType, objectMB);
	}

	/**
	 * Constructor of the networkprinter class.
	 * 
	 * @param Name
	 *            The name of the printer.
	 * @param Desc
	 *            The description of the printer.
	 * @param PprinterType
	 *            Type of printer. ink or laser.
	 * @param PconnectionInterfaces
	 *            Connection interfaces supported by the printer. An array of Strings.
	 * @param objectMB
	 */
	public NetworkPrinter(String Name, String Desc, String PprinterType, String[] PconnectionInterfaces,
			Motherboard objectMB)
	{
		super(Name, Desc, PprinterType, PconnectionInterfaces, objectMB);
	}

	/**
	 * Constructor of the networkprinter class.
	 * 
	 * @param Name
	 *            The name of the printer.
	 * @param Desc
	 *            The description of the printer.
	 * @param PprinterType
	 *            Type of printer. ink or laser.
	 * @param PconnectionInterfaces
	 *            Connection interfaces supported by the printer. An array of Strings.
	 */
	public NetworkPrinter(String Name, String Desc, String PprinterType, String[] PconnectionInterfaces)
	{
		super(Name, Desc, PprinterType, PconnectionInterfaces);
	}

	
	
	// GETTERS
	
	/**
	 * Gets the {@link WidgetNetworkInfo} object containing the network information about this network printer.
	 */
	public WidgetNetworkInfo getInfo()
	{
		return info;
	}
	
	
	// SETTERS

	/**
	 * Sets the {@link WidgetNetworkInfo} object containing the network information about this network printer.
	 */
	public void setInfo(WidgetNetworkInfo info)
	{
		this.info = info;
	}

}
