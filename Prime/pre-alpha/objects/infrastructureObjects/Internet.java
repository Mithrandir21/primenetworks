/**
 * 
 */
package objects.infrastructureObjects;


import objects.Object;
import objects.hardwareObjects.Motherboard;


/**
 * CLASSDesc - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class Internet extends Object
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



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param name
	 * @param desc
	 * @param net
	 * @param device
	 */
	public Internet(String name, String desc, String net, Object device)
	{
		super(name, desc);
		netName = net;
		netDescription = desc;
		connectedDevice = device;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param name
	 * @param desc
	 * @param net
	 */
	public Internet(String name, String desc, String net)
	{
		super(name, desc);
		netName = net;
		netDescription = desc;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param name
	 * @param desc
	 * @param net
	 * @param supCon
	 * @param initObjects
	 */
	public Internet(String name, String desc, String net, String[] supCon, Motherboard initObjects)
	{
		super(name, desc, supCon, initObjects);
		netName = net;
		netDescription = desc;
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
	 * @param netName
	 *            the netName to set
	 */
	public void setNetName(String netName)
	{
		this.netName = netName;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param netDescription
	 *            the netDescription to set
	 */
	public void setNetDescription(String netDescription)
	{
		this.netDescription = netDescription;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param connectedDevice
	 *            the connectedDevice to set
	 */
	public void setConnectedDevice(Object connectedDevice)
	{
		this.connectedDevice = connectedDevice;
	}


}
