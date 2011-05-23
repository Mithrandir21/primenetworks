package model.interfaces;


import java.net.InetAddress;
import java.util.Date;

import model.Connection;
import model.devices.Device;
import scan.Subnet;
import scan.status.Status;


/**
 * This interface has no status and no address of its own, but mirrors the state
 * of its counterpart Should be used for Switches and Hubs etc...
 * 
 * @author rakudave
 */

// TODO - @XStreamAlias("RepeaterIF")
public class TransparentIF implements NetworkIF
{
	private String name = "";

	private Device device;

	private Connection connection;

	private NetworkIF counterpart;

	private boolean useDevice;

	private Status status = Status.UNKNOWN;

	public TransparentIF(Device parent, Connection connection,
			NetworkIF counterpart)
	{
		device = parent;
		this.connection = connection;
		setCounterpart(counterpart);
		name = "Port " + (parent.getInterfaces().size() + 1);
	}

	@Override
	public InetAddress getAddress()
	{
		return null;
	}

	@Override
	public String getCanonicalName()
	{
		return "";
	}

	public NetworkIF getCounterpart()
	{
		return counterpart;
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
		return counterpart.getLastSeen();
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
		return null;
	}

	@Override
	public boolean setAddress(String addr)
	{
		return true;
	}

	public void setCounterpart(NetworkIF nif)
	{
		if ( nif == null )
			return;
		counterpart = nif;
		if ( !(nif instanceof PhysicalIF) )
		{
			useDevice = true;
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
		return true;
	}

	@Override
	public String toString()
	{
		return name + ": transparent";
	}

	@Override
	public void updateStatus()
	{
		Status newStatus = (useDevice) ? counterpart.getDevice().getStatus()
				: counterpart.getStatus();
		if ( !status.equals(newStatus) )
		{
			status = newStatus;
			connection.setStatus(this, status);
		}
	}

	@Override
	public Connection getConnection()
	{
		return connection;
	}
}
