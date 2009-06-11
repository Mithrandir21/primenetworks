/**
 * 
 */
package objects.peripheralObjects;


import widgetManipulation.WidgetNetworkInfo;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
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
	public NetworkMultifunctionPrinter(String Name, String Desc, String[] MFPConnectionInterfaces)
	{
		super(Name, Desc, MFPConnectionInterfaces);
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
