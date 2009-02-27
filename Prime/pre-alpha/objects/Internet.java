/**
 * 
 */
package objects;

import java.io.Serializable;

/**
 * CLASSDesc - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class Internet implements Serializable
{
	/**
	 * The name of the network
	 */
	private String netName;
	
	
	/**
	 * The description of the network
	 */
	private String netDescription;
	
	
	/**
	 * The device that is connected to this network object
	 */
	private Object connectedDevice;
	
	
	
	public Internet(String name, String desc, Object device)
	{
		netName = name;
		netDescription = desc;
		connectedDevice = device;
	}


	
	// GETTERS

	/**
	 * TODO - Description NEEDED!
	 *
	 * @return the netName
	 */
	public String getNetName()
	{
		return netName;
	}



	/**
	 * TODO - Description NEEDED!
	 *
	 * @return the netDescription
	 */
	public String getNetDescription()
	{
		return netDescription;
	}



	/**
	 * TODO - Description NEEDED!
	 *
	 * @return the connectedDevice
	 */
	public Object getConnectedDevice()
	{
		return connectedDevice;
	}


	
	// SETTERS

	/**
	 * TODO - Description NEEDED!
	 *
	 * @param netName the netName to set
	 */
	public void setNetName(String netName)
	{
		this.netName = netName;
	}



	/**
	 * TODO - Description NEEDED!
	 *
	 * @param netDescription the netDescription to set
	 */
	public void setNetDescription(String netDescription)
	{
		this.netDescription = netDescription;
	}



	/**
	 * TODO - Description NEEDED!
	 *
	 * @param connectedDevice the connectedDevice to set
	 */
	public void setConnectedDevice(Object connectedDevice)
	{
		this.connectedDevice = connectedDevice;
	}
	
	
}
