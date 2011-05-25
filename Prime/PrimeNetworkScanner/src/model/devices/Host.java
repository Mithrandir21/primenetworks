package model.devices;


import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Connection;
import model.interfaces.LogicalIF;
import model.interfaces.NetworkIF;
import scan.status.Status;


/**
 * @author rakudave
 */
public class Host implements Device
{
	private String name = "", vendor = "", model = "", location = "",
			otherID = "", description = "";

	private Status status;

	private Date lastStatusChange;

	private Type type;

	private LinkedList<NetworkIF> interfaces;

	private LinkedList<DeviceListener> listeners;

	private Map<String, String> metadata;

	public Host()
	{
		this(Type.Workstation);
	}

	public Host(Type t)
	{
		type = t;
		interfaces = new LinkedList<NetworkIF>();
		listeners = new LinkedList<DeviceListener>();
		initMetadata();
		status = Status.UNKNOWN;
	}

	@Override
	public void addInterface(NetworkIF i)
	{
		if ( i != null && interfaces != null && !interfaces.contains(i) )
			interfaces.add(i);
	}

	@Override
	public void addDeviceListener(DeviceListener listener)
	{
		if ( listener != null && listeners != null
				&& !listeners.contains(listener) )
			listeners.add(listener);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NetworkIF> getInterfaces()
	{
		return (List<NetworkIF>) interfaces.clone();
	}

	@Override
	public Date getLastSeen()
	{
		return lastStatusChange;
	}

	public String getLocation()
	{
		return location;
	}

	@Override
	public String getModel()
	{
		return model;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getOtherID()
	{
		return otherID;
	}

	@Override
	public Status getStatus()
	{
		return status;
	}

	@Override
	public Type getType()
	{
		return type;
	}

	@Override
	public String getVendor()
	{
		return vendor;
	}

	@Override
	public void notifyListeners(DeviceEvent e)
	{
		for ( DeviceListener l : listeners )
		{
			try
			{
				l.deviceChanged(e);
			}
			catch ( Exception ex )
			{
				// TODO - Comment
			}
		}
	}

	@Override
	public void removeInterface(NetworkIF i)
	{
		interfaces.remove(i);
	}

	@Override
	public void removeDeviceListener(DeviceListener listener)
	{
		listeners.remove(listener);
	}

	public void setLocation(String location)
	{
		if ( this.location.equals(location) )
			return;
		this.location = location;
		notifyListeners(new DeviceEvent(this,
				DeviceEvent.Type.PROPERTY_CHANGED, location));
	}

	@Override
	public void setModel(String modelNr)
	{
		if ( model.equals(modelNr) )
			return;
		model = modelNr;
		notifyListeners(new DeviceEvent(this,
				DeviceEvent.Type.PROPERTY_CHANGED, modelNr));
	}

	@Override
	public void setName(String name)
	{
		if ( this.name.equals(name) )
			return;
		this.name = name;
		notifyListeners(new DeviceEvent(this,
				DeviceEvent.Type.PROPERTY_CHANGED, name));
	}

	@Override
	public void setOtherID(String other)
	{
		if ( otherID.equals(other) )
			return;
		if ( other != null )
			otherID = other;
		notifyListeners(new DeviceEvent(this,
				DeviceEvent.Type.PROPERTY_CHANGED, other));
	}

	@Override
	public void setType(Type type)
	{
		if ( this.type.equals(type) )
			return;
		this.type = type;
		notifyListeners(new DeviceEvent(this,
				DeviceEvent.Type.PROPERTY_CHANGED, type));
	}

	@Override
	public void setVendor(String vendor)
	{
		if ( this.vendor.equals(vendor) )
			return;
		this.vendor = vendor;
		notifyListeners(new DeviceEvent(this,
				DeviceEvent.Type.PROPERTY_CHANGED, vendor));
	}

	@Override
	public void updateStatus()
	{
		Status newStatus = Status.UNKNOWN;
		for ( NetworkIF i : interfaces )
		{
			i.updateStatus();
			if ( newStatus.compareTo(i.getStatus()) > 0
					&& !(i instanceof LogicalIF) )
				newStatus = i.getStatus();
		}
		if ( !status.equals(newStatus) )
		{
			status = newStatus;
			lastStatusChange = new Date(System.currentTimeMillis());
			notifyListeners(new DeviceEvent(this,
					DeviceEvent.Type.STATUS_CHANGED, null));
		}
	}

	@Override
	public String toString()
	{
		return name;
	}

	@Override
	public NetworkIF getInterfaceFor(Connection c)
	{
		if ( c == null )
			return null;
		for ( NetworkIF nif : getInterfaces() )
		{
			if ( c == nif.getConnection() )
				return nif;
		}
		// TODO - Comment Logger.debug("getInterfaceFor: nothing found");
		return null;
	}

	@Override
	public String getDesctription()
	{
		return description;
	}

	@Override
	public void setDescription(String description)
	{
		if ( description != null )
			this.description = description;
	}

	@Override
	public void setMetadata(String id, String data)
	{
		initMetadata();
		if ( metadata.put(id, data) != null )
		{
			notifyListeners(new DeviceEvent(this,
					DeviceEvent.Type.PROPERTY_CHANGED, id + "=" + data));
		}
	}

	@Override
	public String getMetadata(String id)
	{
		initMetadata();
		return metadata.get(id);
	}

	@Override
	public void removeMetadata(String id)
	{
		initMetadata();
		metadata.remove(id);
	}

	private void initMetadata()
	{
		if ( metadata == null )
			metadata = new HashMap<String, String>();
	}
}