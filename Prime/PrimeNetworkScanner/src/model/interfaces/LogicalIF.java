package model.interfaces;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import model.Connection;
import model.devices.Device;
import scan.Subnet;
import scan.status.Status;


/**
 * @author rakudave
 */

// TODO - @XStreamAlias("LogicalIF")
public class LogicalIF implements NetworkIF
{
	private String name = "";

	private InetAddress address;

	private Subnet subnet;

	private Device device;

	private Connection connection;

	public LogicalIF(Device parent, Connection connection, String addr)
	{
		device = parent;
		this.connection = connection;
		setAddress(addr);
		name = "lo" + parent.getInterfaces().size();
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
		return null;
	}

	@Override
	public Date getLastSeen()
	{
		return device.getLastSeen();
	}

	@Override
	public String getName()
	{
		return address.getHostName();
	}

	@Override
	public Status getStatus()
	{
		return device.getStatus();
	}

	@Override
	public Subnet getSubnet()
	{
		if ( subnet == null )
			subnet = new Subnet("192.168.0.1/24");
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
			System.err.println("Address not found: " + addr);
			return false;
		}
	}

	@Override
	public boolean setGateway(String gateway)
	{
		return true;
	}

	@Override
	public void setName(String name)
	{
		if ( name != null )
			this.name = name;
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
		connection.setStatus(this, getStatus());
	}

	@Override
	public Connection getConnection()
	{
		return connection;
	}

}
