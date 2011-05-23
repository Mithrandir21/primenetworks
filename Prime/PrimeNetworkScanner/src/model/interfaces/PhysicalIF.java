package model.interfaces;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import model.Connection;
import model.devices.Device;
import model.devices.DeviceEvent;
import model.devices.DeviceEvent.Type;
import scan.Subnet;
import scan.status.IsReachable;
import scan.status.PingMethod;
import scan.status.Status;


/**
 * @author rakudave
 */
// TODO - @XStreamAlias("PhysicalIF")
public class PhysicalIF implements NetworkIF
{
	private InetAddress address, gateway;

	private Subnet subnet;

	private String name = "", macAddress;

	private Device device;

	private Connection connection;

	private PingMethod method;

	private Status status;

	private Date lastSeen;

	public PhysicalIF(Device parent, Connection connection, String addr)
	{
		this(parent, connection, addr, "", IsReachable.getInstance());
	}

	public PhysicalIF(Device parent, Connection connection, String addr,
			PingMethod m)
	{
		this(parent, connection, addr, "", m);
	}

	public PhysicalIF(Device parent, Connection connection, String macAddress,
			String addr)
	{
		this(parent, connection, addr, macAddress, IsReachable.getInstance());
	}

	public PhysicalIF(Device parent, Connection connection, String addr,
			String macAddress, PingMethod m)
	{
		device = parent;
		this.connection = connection;
		setAddress(addr);
		setMacAddress(macAddress);
		setPingMethod(m);
		status = Status.UNKNOWN;
		name = "eth" + parent.getInterfaces().size();
	}

	@Override
	public InetAddress getAddress()
	{
		return address;
	}

	@Override
	public String getCanonicalName()
	{
		return address.getCanonicalHostName();
	}

	@Override
	public Device getDevice()
	{
		return device;
	}

	@Override
	public InetAddress getGateway()
	{
		return gateway;
	}

	@Override
	public Date getLastSeen()
	{
		return lastSeen;
	}

	public String getMacAddress()
	{
		return macAddress;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public Status getStatus()
	{
		return status;
	}

	@Override
	public Subnet getSubnet()
	{
		return subnet;
	}

	@Override
	public boolean setAddress(String addr)
	{
		try
		{
			address = InetAddress.getByName(addr);
			return true;
		}
		catch ( UnknownHostException e )
		{
			status = Status.NOT_FOUND;
			// TODO - Logger.warn("Address not found: " + addr);
			return false;
		}
	}

	@Override
	public boolean setGateway(String gateway)
	{
		try
		{
			this.gateway = InetAddress.getByName(gateway);
			return true;
		}
		catch ( UnknownHostException e )
		{
			// TODO - Logger.warn("Gateway not found: " + gateway);
			return false;
		}
	}

	public void setMacAddress(String macAddress)
	{
		this.macAddress = macAddress;
	}

	/**
	 * Set a new method that this network interface should use to determine its
	 * status
	 * 
	 * @param m
	 *            The new method to be used
	 * @see PingMethod
	 */
	public void setPingMethod(PingMethod m)
	{
		if ( m != null )
			method = m;
	}

	@Override
	public boolean setSubnet(String subnet)
	{
		if ( subnet == null || subnet.isEmpty() )
			return false;
		if ( !(subnet.contains(".")) )
		{
			try
			{
				this.subnet = new Subnet(subnet);
			}
			catch ( Exception e )
			{
				// TODO - Logger.warn("Invalid CIDR-format for subnet " +
				// subnet);
				return false;
			}
		}
		else
		{
			try
			{
				this.subnet = new Subnet(address.getHostAddress(), subnet);
			}
			catch ( Exception e )
			{
				// TODO - Logger.warn("Address undefined or Subnet not found: "
				// + subnet);
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString()
	{
		return name + ": " + address.getHostAddress();
	}

	@Override
	public void updateStatus()
	{
		Status newStatus = method.getStatus(address);
		if ( status != newStatus )
		{
			lastSeen = new Date(System.currentTimeMillis());
			status = newStatus;
			connection.setStatus(this, status);
			device.notifyListeners(new DeviceEvent(device,
					Type.INTERFACE_STATUS_CHANGED, this));
		}
	}

	@Override
	public Connection getConnection()
	{
		return connection;
	}

	@Override
	public void setName(String name)
	{
		if ( name != null )
			this.name = name;
	}

	/**
	 * Get the method that this network interface uses to determine its status
	 * 
	 * @returnThe new method to be used
	 * @see PingMethod
	 */
	public PingMethod getPingMethod()
	{
		return method;
	}

}
