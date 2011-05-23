package model;


import java.util.HashMap;
import java.util.Map;

import model.interfaces.NetworkIF;
import scan.status.Status;


/**
 * @author rakudave
 */
public class Connection
{
	/**
	 * The type of cable used to connect this interface and its counterpart.
	 * This is mostly used to paint the map.
	 */
	public static enum Type
	{
		Coaxial, Ethernet, Fiber, Phone, Serial, Tunnel, Wireless
	}

	private Type type;

	private Status status;

	private double bandwidth;

	private Map<NetworkIF, Status> statusMap;

	/**
	 * Create a new Connection with the type Ethernet and a bandwidth of 100Mb/s
	 * 
	 * @see Type
	 */
	public Connection()
	{
		this(Type.Ethernet, 100);
	}

	/**
	 * Create a new Connection of a certain type and a bandwidth of 100Mb/s
	 * 
	 * @param type
	 *            of cable
	 * @see Type
	 */
	public Connection(Type type)
	{
		this(type, 100);
	}

	/**
	 * Create a new Connection of a certain type and bandwidth
	 * 
	 * @param type
	 *            of cable
	 * @param bandwidth
	 *            in megabits per second
	 */
	public Connection(Type type, double bandwidth)
	{
		setType(type);
		setBandwidth(bandwidth);
		status = Status.UNKNOWN;
		statusMap = new HashMap<NetworkIF, Status>(2);
	}

	/**
	 * @return the bandwidth of this link in megabits per second
	 */
	public double getBandwidth()
	{
		return bandwidth;
	}

	/**
	 * @return The current status of this connection, i.e. the combined status
	 *         of the hosts that are connected with this connection
	 */
	public Status getStatus()
	{
		return status;
	}

	/**
	 * @return The type of cable used to connect two devices
	 */
	public Type getType()
	{
		return type;
	}

	/**
	 * Set the bandwidth of this connection
	 * 
	 * @param bandwidth
	 *            in megabits per second
	 */
	public void setBandwidth(double bandwidth)
	{
		if ( bandwidth > 0 )
			this.bandwidth = bandwidth;
	}

	/**
	 * Set the status of this connection
	 * 
	 * @param the
	 *            combined status of the hosts that are connected with this
	 *            connection
	 */
	public void setStatus(NetworkIF netIF, Status status)
	{
		statusMap.put(netIF, status);
		Status newStatus = Status.UNKNOWN;
		for ( Status s : statusMap.values() )
		{
			if ( newStatus.compareTo(s) > 0 )
				newStatus = s;
		}
		this.status = newStatus;
	}

	/**
	 * Set the type of cable used to connect two devices
	 * 
	 * @param type
	 *            of cable
	 * @see Type
	 */
	public void setType(Type type)
	{
		this.type = type;
	}

}
