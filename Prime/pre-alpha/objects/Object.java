package objects;


import hardware.Motherboard;

import java.io.Serializable;

import logistical.cleanup;
import managment.ComponentsManagment;

import connections.Connection;
import connections.DeviceConnection;
import connections.InternalConnection;
import connections.NetworkConnection;
import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;


/**
 * Object is a super class for all objects created within the system. Both
 * hardware and software. MUST ADD INFO!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public abstract class Object implements Serializable
{

	// THE OBJECT ITSELF

	/**
	 * The name of an object
	 */
	private String name;


	/**
	 * The description of any object
	 */
	private String description;


	/**
	 * Connection interfaces supported by a device
	 */
	private String[] supportedConnectionInterfaces;



	// THE MASKIN ITSELF

	/**
	 * An array that contains pointers to the objects that make up a computer.
	 */
	private Object[] components;
	
	
	/**
	 * FIXME - Implement connections between internal components and the machine itself.
	 */
	private InternalConnection[] internalConnections;
	
	
	/**
	 * Counts number of components in the components list
	 */
	private int componentCounter;


	// THE DEVICES SURROUNDING IT

	/**
	 * An array of the objects it is connected to, whether by USB, RJ-45,
	 * bluetooth, PS-2 and so on.
	 */
	private Object[] connectedDevices;

	/**
	 * An array of connection object which represent the connection between the
	 * object and outside devices.
	 */
	private NetworkConnection[] networkConnections;
	
	
	/**
	 * An array of deviceConnections that represent the connections inside the computer 
	 * itself.
	 */
	private DeviceConnection[] deviceConnections;

	
	/**
	 * Counts the number of objects it is connected to
	 */
	private int connectedDevicesCounter;



	/**
	 * Constructor of a object computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * object rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the object.
	 * @param Desc
	 *            The description of the object.
	 */
	public Object(String Name, String Desc)
	{
		name = Name;
		description = Desc;
		supportedConnectionInterfaces = null;

		componentCounter = 0;

		components = null;
	}


	/**
	 * The constructor of the object superclass. All objects must have both a
	 * name and description. TODO
	 * 
	 * @param Name
	 *            The name of an object
	 * @param Desc
	 *            The description of any object
	 */
	public Object(String Name, String Desc, String[] SupConInt)
	{
		name = Name;
		description = Desc;
		supportedConnectionInterfaces = SupConInt;

		componentCounter = 0;

		components = null;
	}



	/**
	 * The constructor of the object superclass. All objects must have both a
	 * name and description. TODO
	 * 
	 * @param Name
	 *            The name of an object
	 * @param Desc
	 *            The description of any object
	 * @param objectComponents
	 *            The initial components an instance of a object has.
	 */
	public Object(String Name, String Desc, Object[] objectComponents)
	{
		name = Name;
		description = Desc;
		supportedConnectionInterfaces = null;

		componentCounter = objectComponents.length;

		components = objectComponents;
	}



	/**
	 * The constructor of the object superclass. All objects must have both a
	 * name and description. TODO
	 * 
	 * @param Name
	 *            The name of an object
	 * @param Desc
	 *            The description of any object
	 * @param SupConInt
	 *            The supported connection interfaces an instance of a object
	 *            supports.
	 * @param objectComponents
	 *            The initial components an instance of a object has.
	 */
	public Object(String Name, String Desc, String[] SupConInt, Object[] objectComponents)
	{
		name = Name;
		description = Desc;
		supportedConnectionInterfaces = SupConInt;

		componentCounter = objectComponents.length;

		components = objectComponents;
	}


	/**
	 * The constructor of the object superclass. All objects must have both a
	 * name and description. TODO
	 * 
	 * @param Name
	 *            The name of an object
	 * @param Desc
	 *            The description of any object
	 * @param SupConInt
	 *            The supported connection interfaces an instance of a object
	 *            supports.
	 * @param objectComponents
	 *            The initial components an instance of a object has.
	 */
	public Object(String Name, String Desc, String[] SupConInt, Motherboard objectMB)
	{
		name = Name;
		description = Desc;
		supportedConnectionInterfaces = SupConInt;

		componentCounter = 1;

		components = new Object[1];
		components[0] = objectMB;
	}



	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Returns the name of the object.
	 */
	public String getObjectName()
	{

		return name;
	}


	/**
	 * Returns the description of the object.
	 */
	public String getDescription()
	{

		return description;
	}


	/**
	 * Description NEEDED!
	 * 
	 * @return the supportedConnectionInterfaces
	 */
	public String[] getSupportedConnectionInterfaces()
	{
		return supportedConnectionInterfaces;
	}


	/**
	 * Get all components of a computer.
	 * 
	 * @return Returns an array of {@link  objects.Object  Objects} containing
	 *         the components that make up the system.
	 */
	public Object[] getComponents()
	{
		return components;
	}


	/**
	 * Get all the devices connected to the computer.
	 * 
	 * @return Returns an array of {@link  objects.Object  Objects} containing
	 *         the devices that are connected the system.
	 */
	public Object[] getConnectedDevices()
	{

		return connectedDevices;
	}


	/**
	 * Get all the connections between the computer and devices it is connected
	 * to.
	 * 
	 * @return Returns an array of {@link  connections.Connection Connections}.
	 */
	public Connection[] getConnections()
	{

		return networkConnections;
	}



	/**
	 * Get spesific components by searching for components with the give class
	 * type.
	 * 
	 * @return Returns an array of components that match with the given class.
	 */
	public Object[] getSpesificComponents(Class<?> ComponentClass) throws ObjectNotFoundException
	{
		Object[] componentsFound = ComponentsManagment.getSpesificComponents(ComponentClass,
				components, componentCounter);

		return componentsFound;
	}


	/**
	 * Get spesific connceted device by searching for devices with the give
	 * class type.
	 * 
	 * @return Returns an array of connected devices that match with the given
	 *         class.
	 */
	public Object[] getSpesificConncetedDevices(Class<?> connectedDeviceClass)
			throws ObjectNotFoundException
	{
		Object[] componentsFound = ComponentsManagment.getSpesificComponents(connectedDeviceClass,
				components, componentCounter);

		return componentsFound;
	}



	// SET METHODES

	/**
	 * Sets the name of the object.
	 */
	public void setObjectName(String ObjectName)
	{

		name = ObjectName;
	}


	/**
	 * Sets the description of the object.
	 */
	public void setDescription(String ObjectDescription)
	{

		description = ObjectDescription;
	}


	/**
	 * Description NEEDED!
	 * 
	 * @param supportedConnectionInterfaces
	 *            the supportedConnectionInterfaces to set
	 */
	public void setSupportedConnectionInterfaces(String[] supportedConnectionInterfaces)
	{
		this.supportedConnectionInterfaces = supportedConnectionInterfaces;
	}


	/**
	 * Set method for replacing of all components.
	 * 
	 * @param NewComponents
	 *            Array of new components the desktop will contain.
	 */
	public void setAllComponents(Object[] NewComponents)
	{
		components = NewComponents;

		// Sets the number of components to the length of the new array
		componentCounter = NewComponents.length;
	}


	/**
	 * Set method for replacing of all connected devices.
	 * 
	 * @param NewConnectedDevices
	 *            Array of new connceted devices the desktop is connected to.
	 */
	public void setAllConnectedDevices(Object[] NewConnectedDevices)
	{
		connectedDevices = NewConnectedDevices;

		// Sets the number of connected devices to the length of the new array
		connectedDevicesCounter = NewConnectedDevices.length;
	}


	/**
	 * Description NEEDED!
	 * 
	 * @param connections
	 *            the connections to set
	 */
	public void setConnections(NetworkConnection[] connections)
	{
		this.networkConnections = connections;
	}



	// CLASS METHODES
	
	/**
	 * 
	 */
	public void removeConnections()
	{
		connectedDevices = new Object[0];
		
		removeAllDeviceConnections();
		
		removeAllNetworkConnections();
		
		connectedDevicesCounter = 0;
	}
	


	// COMPONENTS MANIPULATION


	/**
	 * Function for replacing a spesific given component with a given new
	 * component. TODO - CHECK COMPATABILITY OF OLD AND NEW COMPONENT
	 * 
	 * @param NewComponent
	 *            The component to replace the previous one.
	 * @param OldComponent
	 *            The component to be replaced.
	 */
	public void changeComponent(Object NewComponent, Object OldComponent)
	{
		components = ComponentsManagment.changeComponent(NewComponent, OldComponent, components,
				componentCounter);

		// Sets the new count for number of components in the array
		componentCounter = components.length;
	}



	/**
	 * Function to remove components from the array of components.
	 * 
	 * @param ToBeRemoved
	 *            Component to be removed.
	 */
	public void removeComponent(Object[] ToBeRemoved) throws ObjectNotFoundInArrayException
	{
		components = ComponentsManagment
				.removeComponents(ToBeRemoved, components, componentCounter);

		// Sets the new count for number of components in the array
		componentCounter = components.length;
	}



	/**
	 * Function to add component(s) to the the components list. NOTES - THE
	 * SYSTEM WILL CHECK AT AN EARLIER POINT TOO SEE IF THERE IS ROOM FOR THESE
	 * COMPONENTS
	 * 
	 * @param NewComponents
	 *            An array of new components.
	 */
	public void addComponents(Object[] NewComponents) throws Exception
	{
		components = ComponentsManagment.addComponents(NewComponents, components, componentCounter);

		// Sets the new count for number of components in the array
		componentCounter = components.length;
	}


	// CONNECTED DEVICES MANIPULATION


	/**
	 * Function for replacing a spesific given connected device with a given new
	 * connected devices.
	 * 
	 * @param NewconnectedDevice
	 *            The connected device to replace the previous one.
	 * @param OldconnectedDevice
	 *            The connected device to be replaced.
	 */
	public void changeConnectedDevice(Object NewconnectedDevice, Object OldconnectedDevice)
	{
		components = ComponentsManagment.changeComponent(NewconnectedDevice, OldconnectedDevice,
				connectedDevices, connectedDevicesCounter);

		// Sets the new count for number of connected devices in the array
		connectedDevicesCounter = connectedDevices.length;
	}



	/**
	 * Function to remove connected devices from the array of connected devices.
	 * 
	 * @param ToBeRemoved
	 *            Connected device to be removed.
	 */
	public void removeConnectedDevices(Object[] ToBeRemoved) throws ObjectNotFoundInArrayException
	{
		connectedDevices = ComponentsManagment.removeComponents(ToBeRemoved, connectedDevices,
				connectedDevicesCounter);

		// Sets the new count for number of connected devices in the array
		connectedDevicesCounter = connectedDevices.length;
	}
	
	
	/**
	 * 
	 */
	public void removeAllConnectedDevices()
	{
		connectedDevices = new Object[5];
	}


	/**
	 * Function to add device(s) to the the connected devices list. NOTES - THE
	 * SYSTEM WILL CHECK AT AN EARLIER POINT TOO SEE IF THERE IS ROOM FOR THESE
	 * COMPONENTS
	 * 
	 * @param NewConnectedDevices
	 *            An array of new devices.
	 */
	public void addConnectedDevices(Object[] NewConnectedDevices) 
	{
		connectedDevices = ComponentsManagment.addComponents(NewConnectedDevices, connectedDevices,
				connectedDevicesCounter);

		// Sets the new count for number of connected devices in the array
		connectedDevicesCounter = connectedDevices.length;
	}
	
	
	/**
	 * Function to add device(s) to the the connceted devices list. NOTES - THE
	 * SYSTEM WILL CHECK AT AN EARLIER POINT TOO SEE IF THERE IS ROOM FOR THESE
	 * COMPONENTS
	 * 
	 * @param NewConnectedDevice
	 *            A new device.
	 */
	public void addConnectedDevices(Object NewConnectedDevice) 
	{
		Object[] newObject = new Object[1];
		newObject[0] = NewConnectedDevice;
		connectedDevices = ComponentsManagment.addComponents(newObject, connectedDevices,
				connectedDevicesCounter);

		// Sets the new count for number of connected devices in the array
		connectedDevicesCounter = connectedDevices.length;
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void addConnection(Connection con)
	{
		if(con instanceof InternalConnection)
		{
			addInternalConnection((InternalConnection) con);
		}
		else if(con instanceof DeviceConnection)
		{
			addDeviceConnection((DeviceConnection) con);
		}
		else // This will then be a network connection.
		{
			addNetworkConnection((NetworkConnection) con);
		}
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void removeConnection(Connection con)
	{
		if(con instanceof InternalConnection)
		{
			removeInternalConnection((InternalConnection) con);
		}
		else if(con instanceof DeviceConnection)
		{
			removeDeviceConnection((DeviceConnection) con);
		}
		else // This will then be a network connection.
		{
			removeNetworkConnection((NetworkConnection) con);
		}
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void releaseConnectionPorts(Connection con)
	{
		if(con instanceof InternalConnection)
		{
			addInternalConnection((InternalConnection) con);
		}
		else if(con instanceof DeviceConnection)
		{
			addDeviceConnection((DeviceConnection) con);
		}
		else // This will then be a network connection.
		{
			addNetworkConnection((NetworkConnection) con);
		}
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void addInternalConnection(InternalConnection con)
	{
		if(internalConnections == null)
		{
			internalConnections = initConnection(internalConnections);
		}
		
		for(int i = 0; i<internalConnections.length; i++)
		{
			if(internalConnections[i] == null)
			{
				internalConnections[i] = con;
				return;
			}
		}
		
		
		// If the function gets to this point it means that there where no
		// available indexes and the array needs to be extended.
		internalConnections = extendConnectionArray(internalConnections);
		addInternalConnection(con);
		
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void addNetworkConnection(NetworkConnection con)
	{
		if(networkConnections == null)
		{
			networkConnections = initConnection(networkConnections);
		}
		
		for(int i = 0; i<networkConnections.length; i++)
		{
			if(networkConnections[i] == null)
			{
				networkConnections[i] = con;
				return;
			}
		}
		
		// If the function gets to this point it means that there where no
		// available indexes and the array needs to be extended.
		networkConnections = extendConnectionArray(networkConnections);
		addNetworkConnection(con);
		
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void addDeviceConnection(DeviceConnection con)
	{
		if(deviceConnections == null)
		{
			deviceConnections = initConnection(deviceConnections);
		}
		
		for(int i = 0; i<deviceConnections.length; i++)
		{
			if(deviceConnections[i] == null)
			{
				deviceConnections[i] = con;
				return;
			}
		}
		
		
		// If the function gets to this point it means that there where no
		// available indexes and the array needs to be extended.
		deviceConnections = extendConnectionArray(deviceConnections);
		
		// Runs the function again with the extended array.		
		addDeviceConnection(con);
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public boolean removeInternalConnection(InternalConnection con)
	{
		
		for(int i = 0; i<internalConnections.length; i++)
		{
			if(internalConnections[i].equals(con))
			{
				internalConnections[i] = null;
				return true;
			}
		}
		
		return false;
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void removeAllInternalConnections()
	{
		internalConnections = new InternalConnection[5];
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public boolean removeNetworkConnection(NetworkConnection con)
	{
		
		for(int i = 0; i<networkConnections.length; i++)
		{
			if(networkConnections[i].equals(con))
			{
				networkConnections[i] = null;
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void removeAllNetworkConnections()
	{
		networkConnections = new NetworkConnection[5];
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public boolean removeDeviceConnection(DeviceConnection con)
	{
		
		for(int i = 0; i<deviceConnections.length; i++)
		{
			if(deviceConnections[i].equals(con))
			{
				deviceConnections[i] = null;
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void removeAllDeviceConnections()
	{
		deviceConnections = new DeviceConnection[5];
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	private NetworkConnection[] initConnection(NetworkConnection[] array)
	{
		return array = new NetworkConnection[5];
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	private DeviceConnection[] initConnection(DeviceConnection[] array)
	{
		return array = new DeviceConnection[5];
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	private InternalConnection[] initConnection(InternalConnection[] array)
	{
		return array = new InternalConnection[5];
	}
	
	
	

	/**
	 * TODO - Description
	 * 
	 */
	private NetworkConnection[] extendConnectionArray(NetworkConnection[] array)
	{
		NetworkConnection[] temp = new NetworkConnection[array.length + 5];
		
		for(int i = 0; i<array.length; i++)
		{
			temp[i] = array[i];
		}
		
		return temp;
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	private DeviceConnection[] extendConnectionArray(DeviceConnection[] array)
	{
		DeviceConnection[] temp = new DeviceConnection[array.length + 5];
		
		for(int i = 0; i<array.length; i++)
		{
			temp[i] = array[i];
		}
		
		return temp;
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	private InternalConnection[] extendConnectionArray(InternalConnection[] array)
	{
		InternalConnection[] temp = new InternalConnection[array.length + 5];
		
		for(int i = 0; i<array.length; i++)
		{
			temp[i] = array[i];
		}
		
		return temp;
	}
	
	
}
