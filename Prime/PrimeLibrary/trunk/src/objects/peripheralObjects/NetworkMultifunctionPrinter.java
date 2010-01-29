/**
 * 
 */
package objects.peripheralObjects;


import objects.hardwareObjects.Motherboard;
import widgetManipulation.WidgetNetworkInfo;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class NetworkMultifunctionPrinter extends MultifunctionPrinter
{
	private WidgetNetworkInfo info = new WidgetNetworkInfo();



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param Name
	 * @param Desc
	 * @param MFPConnectionInterfaces
	 */
	public NetworkMultifunctionPrinter(String Name, String Desc,
			String[] MFPConnectionInterfaces)
	{
		super(Name, Desc, MFPConnectionInterfaces);
	}



	/**
	 * Constructor of the MFP class.
	 * 
	 * @param Name
	 *            The name of the MFP.
	 * @param Desc
	 *            The description of the MFP.
	 * @param MFPConnectionInterfaces
	 *            Connection interfaces supported by a MFP. A array of Strings.
	 * @param objectMB
	 *            The {@link Motherboard} of the object.
	 */
	public NetworkMultifunctionPrinter(String Name, String Desc,
			String[] MFPConnectionInterfaces, Motherboard objectMB)
	{
		super(Name, Desc, MFPConnectionInterfaces, objectMB);
	}




	/**
	 * Gets the {@link WidgetNetworkInfo} object containing the network information about this MFP.
	 */
	public WidgetNetworkInfo getInfo()
	{
		return info;
	}


	/**
	 * Sets the {@link WidgetNetworkInfo} object containing the network information about this MFP.
	 */
	public void setInfo(WidgetNetworkInfo info)
	{
		this.info = info;
	}
}
