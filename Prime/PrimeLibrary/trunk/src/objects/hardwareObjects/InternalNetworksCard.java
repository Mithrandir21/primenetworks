package objects.hardwareObjects;


import java.io.Serializable;

import objects.Hardware;
import objects.Object;


/**
 * This class represents a networks interface card within a {@link objects.Servers server} or {@link objects.Clients
 * client} machine. It can be a server, a desktop or a laptop. It contains information on what kind of capability the
 * networks interface card has. It also contains information on transfer speeds, MACs, connection types and so on. <br>
 * <br>
 * <b>Notation</b>: The networks interface card will be referred to as "NIC" in the remainder of this document.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class InternalNetworksCard extends Hardware implements Serializable
{

	// The company that produces the NIC. D-Link, Linksys, Cisco and so on.
	private String producer;

	// The port that connects with the motherboard
	private String port;

	// The MAC address of the NIC
	private String MAC;

	// The connection type of NIC. Wired, wireless or Coax.
	private String connectionType;

	// The speed of the NIC. 10 Mbps, 100 Mbps or 1000 Mbps
	private int transferSpeed;

	// The NIC protocol. Ethernet, token and so on
	private String protocol;

	// The standards supported by the NIC. IEEE 802.3, IEEE 802.3u, IEEE 802.1x
	// and so on
	private String[] supportedStandards;

	// Whether or not the NIC has support for IP version 6, IPv6
	private boolean supportsIPv6;

	// The Object this NIC is connected to
	private Object connectedObject;




	/**
	 * Constructor of a NIC hardware
	 * 
	 * @param Name
	 *            The name of the MB.
	 * @param Desc
	 *            The description of the MB.
	 * @param NICproducer
	 *            The company that produces the NIC. D-Link, Linksys, Cisco and so on.
	 * @param NIC_MAC
	 *            The MAC address of the NIC.
	 * @param NICconnectionType
	 *            The connection type of NIC. Wired, wireless or Coax.
	 */
	public InternalNetworksCard(String Name, String Desc, String NICproducer,
			String NICport, String NIC_MAC, String NICconnectionType)
	{
		super(Name, Desc);

		producer = NICproducer;
		port = NICport;
		MAC = NIC_MAC;
		connectionType = NICconnectionType;
	}



	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get producer of the NIC.
	 */
	public String getProducer()
	{
		return producer;
	}


	/**
	 * Get the port that connects the device to a motherboard.
	 */
	public String getPort()
	{
		return port;
	}


	/**
	 * Get the MAC of the NIC.
	 */
	public String getMAC()
	{
		return MAC;
	}


	/**
	 * Get the connection type of NIC.
	 */
	public String getConnectionType()
	{
		return connectionType;
	}


	/**
	 * Get the transfer speed of the NIC.
	 */
	public int getSpeed()
	{
		return transferSpeed;
	}


	/**
	 * Get the protocol of the NIC.
	 */
	public String getProtocol()
	{
		return protocol;
	}


	/**
	 * Get the standard(s) of the NIC.
	 */
	public String[] getSupportedStandards()
	{
		return supportedStandards;
	}


	/**
	 * Get the protocol of the NIC.
	 */
	public boolean getIPv6support()
	{
		return supportsIPv6;
	}


	/**
	 * Get the {@link Object} this NIC is connected to.
	 */
	public Object getConnectedObject()
	{
		return connectedObject;
	}

	// SET METHODES

	/**
	 * Set method for producers of the NIC.
	 */
	public void setProducer(String NICProducer)
	{
		producer = NICProducer;
	}


	/**
	 * Set the port that connects the device to a motherboard.
	 */
	public void setPort(String NICport)
	{
		port = NICport;
	}


	/**
	 * Set method for the type of the NIC.(Wired or Wireless)
	 */
	public void setType(String type)
	{
		connectionType = type;
	}



	/**
	 * Set method for the MAC of the NIC.
	 */
	public void setMAC(String NIC_MAC)
	{
		MAC = NIC_MAC;
	}


	/**
	 * Set method for connection type of the NIC.
	 */
	public void setSubtype(String NICconnectionType)
	{
		connectionType = NICconnectionType;
	}


	/**
	 * Set method for speed of the NIC.
	 */
	public void setSpeed(int NICtransferSpeed)
	{
		transferSpeed = NICtransferSpeed;
	}


	/**
	 * Set method for connection type of the NIC.
	 */
	public void setSupportedStandards(String NICsupportedStandards[])
	{
		supportedStandards = NICsupportedStandards;
	}


	/**
	 * Set method for speed of the NIC.
	 */
	public void setSupportsIPv6(boolean NICsupportsIPv6)
	{
		supportsIPv6 = NICsupportsIPv6;
	}


	/**
	 * Sets the {@link Object} this NIC is connected to.
	 */
	public void setConnectedObject(Object obj)
	{
		connectedObject = obj;
	}

}
