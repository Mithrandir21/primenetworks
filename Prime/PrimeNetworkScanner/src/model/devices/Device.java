package model.devices;


import java.util.Date;
import java.util.List;

import model.Connection;
import model.interfaces.NetworkIF;
import scan.status.Status;


/**
 * Defines any connectable device on a network. The actual connections are
 * handles by one or more NetworkIFs, so this only serves as "container" for all
 * those NetworkIFs and is responsible for saving its current location on the
 * screen as well as device-specific infos such as vendor and model.
 * 
 * @author rakudave
 */
// TODO - @XStreamAlias("Device")
public interface Device
{
	/**
	 * The type of this device, e.g. Router, Switch etc... This is mostly used
	 * to paint the map and reduce instanceof-calls
	 */
	public static enum Type
	{
		Cellphone, Cloud, Database, Firewall, Hub, IP_Phone, L3Switch, Laptop, Managed_Switch, Modem, Multimedia, NAS, Other, Printer, Router, Router_Firewall, Server, Switch, Video, Wireless, Workstation
	}

	/**
	 * Add an network interface to this device.
	 * 
	 * @param i
	 *            The interface to be added
	 * @see NetworkIF
	 */
	public void addInterface(NetworkIF i);

	/**
	 * Add a listener to this device. This listener will be notified every time
	 * this device changes its state, properties have changed or a device is
	 * added/removed
	 * 
	 * @param listener
	 *            The device listener to be added
	 * @see DeviceListener
	 * @see DeviceEvent
	 */
	public void addDeviceListener(DeviceListener listener);

	@Override
	public boolean equals(Object o);

	/**
	 * @return device description, e.g. "Ubuntu Linux 2.6 x64"
	 */
	public String getDesctription();

	/**
	 * Get all interfaces that are attached to this device
	 * 
	 * @return a list of interfaces
	 * @see NetworkIF
	 */
	public List<NetworkIF> getInterfaces();

	/**
	 * Query a device for the NetworkIF which is attached to a Connection
	 * 
	 * @param c
	 *            the connection
	 * @return matching NetworkIF or null if not found
	 */
	public NetworkIF getInterfaceFor(Connection c);

	/**
	 * @return the last time any interface of this device has been up.
	 */
	public Date getLastSeen();

	/**
	 * @return Location of this device, such as
	 *         "742 Evergreen Terrace, 2nd floor"
	 */
	public String getLocation();

	/**
	 * @return the model number of this device
	 */
	public String getModel();

	/**
	 * @return the name of this device
	 */
	public String getName();

	/**
	 * @return If Type == Other, this will specify the custom type identifier
	 * @see Type
	 */
	public String getOtherID();

	/**
	 * @return the best status of all interfaces (i.e. "up" if at least one
	 *         interface is up)
	 * @see Status
	 */
	public Status getStatus();

	/**
	 * @return The type of the device
	 * @see Type
	 */
	public Type getType();

	/**
	 * @return the vendor of this device
	 */
	public String getVendor();

	@Override
	public int hashCode();

	/**
	 * Notifies all listeners of this device
	 * 
	 * @param e
	 *            a DeviceEvent to be sent along
	 * @see DeviceEvent
	 */
	public void notifyListeners(DeviceEvent e);

	/**
	 * Removes an interface from this device
	 * 
	 * @param i
	 *            Interface to be removed
	 * @see NetworkIF
	 */
	public void removeInterface(NetworkIF i);

	/**
	 * Removes a listener from this device
	 * 
	 * @param listener
	 *            to be removed
	 * @see DeviceListener
	 */
	public void removeDeviceListener(DeviceListener listener);

	/**
	 * Set a description of this device, e.g. "Ubuntu Linux 2.6 x64"
	 * 
	 * @param description
	 */
	public void setDescription(String description);

	/**
	 * Location of this device, such as "742 Evergreen Terrace, 2nd floor"
	 * 
	 * @param location
	 */
	public void setLocation(String location);

	/**
	 * Set the model number of this device
	 * 
	 * @param model
	 *            number
	 */
	public void setModel(String model);

	/**
	 * Set a new name for this device
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * Set the ID of what exactly is meant when setting Type = Other
	 * 
	 * @param other
	 *            identification string
	 * @see Type
	 */
	public void setOtherID(String other);

	/**
	 * Set device type
	 * 
	 * @param type
	 *            of this device
	 * @see Type
	 */
	public void setType(Type type);

	/**
	 * Set the vendor of this device
	 * 
	 * @param vendor
	 */
	public void setVendor(String vendor);

	/**
	 * Updates the state of this device by updating all attached interfaces
	 */
	public void updateStatus();

	/**
	 * Add metadata to a device, such as notes etc.
	 * 
	 * @param id
	 *            Identifier
	 * @param data
	 *            Data
	 */
	void setMetadata(String id, String data);

	/**
	 * Retrieve previously stored metadata
	 * 
	 * @param id
	 *            Identifier
	 * @return data or null if not found
	 */
	String getMetadata(String id);

	/**
	 * Removes previously stored metadata
	 * 
	 * @param id
	 *            Identifier
	 */
	void removeMetadata(String id);

}