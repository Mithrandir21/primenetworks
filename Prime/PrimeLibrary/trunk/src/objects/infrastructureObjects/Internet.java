/**
 * 
 */
package objects.infrastructureObjects;


import objects.Infrastructure;
import objects.Object;
import objects.hardwareObjects.Motherboard;


/**
 * This class represents a Internet connection point. The point of this object is to give a object which a user can
 * assume is the connection out to the World Wide Web. The user should assume that this object is where the Internet can
 * be found.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class Internet extends Infrastructure
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
	 * A constructor for an Internet object with the name of the Network the Internet object represent a connection to.
	 * 
	 * @param name
	 *            The name of the object.
	 * @param desc
	 *            The description of the object.
	 * @param net
	 *            The name of the Network the Internet object represent a connection to.
	 */
	public Internet(String name, String desc, String net)
	{
		super(name, desc);
		netName = net;
		netDescription = desc;
	}



	/**
	 * A constructor for an Internet object with the connected {@link Object Objects} as a given argument.
	 * 
	 * @param name
	 *            The name of the object.
	 * @param desc
	 *            The description of the object.
	 * @param net
	 *            The name of the Network the Internet object represent a connection to.
	 * @param device
	 *            The connected {@link Object Objects}.
	 */
	public Internet(String name, String desc, String net, Object device)
	{
		super(name, desc);
		netName = net;
		netDescription = desc;
		connectedDevice = device;
	}


	/**
	 * A constructor for an Internet object with a String array of supported connection types(which normally would only
	 * be RJ-45) and a {@link Motherboard}.
	 * 
	 * @param name
	 *            The name of the object.
	 * @param desc
	 *            The description of the object.
	 * @param net
	 *            The name of the Network the Internet object represent a connection to.
	 * @param supCon
	 *            The supported connection types of the object.
	 * @param initObjects
	 *            The {@link Motherboard} of the Internet object(which is only used so that the system can connect a
	 *            RJ-45 cable to).
	 */
	public Internet(String name, String desc, String net, String[] supCon,
			Motherboard initObjects)
	{
		super(name, desc, supCon, initObjects);
		netName = net;
		netDescription = desc;
	}



	// GETTERS

	/**
	 * Gets the name of the Network the Internet object represent a connection to.
	 */
	public String getNetName()
	{
		return netName;
	}



	/**
	 * Gets the description of the Internet object.
	 */
	public String getNetDescription()
	{
		return netDescription;
	}



	/**
	 * Gets the object connected to this Internet object.
	 */
	public Object getConnectedDevice()
	{
		return connectedDevice;
	}



	// SETTERS

	/**
	 * Sets the name of the Network the Internet object represent a connection to.
	 */
	public void setNetName(String netName)
	{
		this.netName = netName;
	}



	/**
	 * Sets the description of the Internet object.
	 */
	public void setNetDescription(String netDescription)
	{
		this.netDescription = netDescription;
	}



	/**
	 * Sets the object connected to this Internet object.
	 */
	public void setConnectedDevice(Object connectedDevice)
	{
		this.connectedDevice = connectedDevice;
	}


}
