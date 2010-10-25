/**
 * 
 */
package objects.infrastructureObjects;


import objects.hardwareObjects.Motherboard;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class WirelessRouter extends Router
{

	// Wireless feature
	private boolean wireless;


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param Name
	 * @param Desc
	 * @param SupConInt
	 * @param objectMB
	 * @param outPorts
	 * @param inPorts
	 * @param DuplexSupport
	 * @param wifi
	 */
	public WirelessRouter(String Name, String Desc, String[] SupConInt, Motherboard objectMB, int outPorts,
			int inPorts, String[] DuplexSupport, boolean wifi)
	{
		super(Name, Desc, SupConInt, objectMB, outPorts, inPorts, DuplexSupport);

		wireless = wifi;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the wireless
	 */
	public boolean isWireless()
	{
		return wireless;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param wireless
	 *            the wireless to set
	 */
	public void setWireless(boolean wireless)
	{
		this.wireless = wireless;
	}

}
