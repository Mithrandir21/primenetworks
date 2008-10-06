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
import exceptions.PortIsNotRegisteredOnMotherboard;


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
	 * FIXME - Implement connections between internal components and the machine
	 * itself.
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
	 * An array of deviceConnections that represent the connections.
	 */
	private DeviceConnection[] deviceConnections;


	/**
	 * Counts the number of objects it is connected to
	 */
	private int connectedDevicesCounter;



	/**
	 * Constructor of an object.<br>
	 * This constructor also sets the number of components in the system to "0".
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
	 * name and description. This constructor also sets the supported user
	 * interfaces the object supports.
	 * 
	 * @param Name
	 *            The name of an object
	 * @param Desc
	 *            The description of any object
	 * @param SupConInt
	 *            The supported connection interfaces an instance of a object
	 *            supports.
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
	 * name and description. This constructor also sets the initial components
	 * that an object starts of with. Like {@link hardware.Motherboard
	 * motherboards}.
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
	 * name and description. This constructor also sets the supported user
	 * interfaces the object supports and sets the initial components that an
	 * object starts of with. Like {@link hardware.Motherboard motherboards}.
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
	 * name and description. This constructor also sets the supported user
	 * interfaces the object supports and set the objects
	 * {@link hardware.Motherboard motherboards}. This constructor is good for
	 * the creation of infrastructure which may only have a motherboard.
	 * 
	 * @param Name
	 *            The name of an object
	 * @param Desc
	 *            The description of any object
	 * @param SupConInt
	 *            The supported connection interfaces an instance of a object
	 *            supports.
	 * @param objectMB
	 *            The {@link hardware.Motherboard motherboard} of an object.
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
	 * @return Returns an array of {@link objects.Object Objects} containing the
	 *         components that make up the system.
	 */
	public Object[] getComponents()
	{
		return components;
	}


	/**
	 * Get all the devices connected to the computer.
	 * 
	 * @return Returns an array of {@link objects.Object Objects} containing the
	 *         devices that are connected the system.
	 */
	public Object[] getConnectedDevices()
	{

		return connectedDevices;
	}


	/**
	 * Get all the connections between the computer and devices it is connected
	 * to.
	 * 
	 * @return Returns an array of {@link connections.Connection Connections}.
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
	 * Description NEEDED!
	 * 
	 * @param supportedConnectionInterfaces
	 *            the supportedConnectionInterfaces to set
	 */
	public void setSupportedConnectionInterfaces(String supportedConnectionInterface)
	{
		String[] one = { supportedConnectionInterface };
		this.supportedConnectionInterfaces = one;
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
	 * Function to remove connected devices from the array of connected devices.
	 * 
	 * @param ToBeRemoved
	 *            Connected device to be removed.
	 */
	public void removeConnectedDevices(Object ToBeRemoved) throws ObjectNotFoundInArrayException
	{
		Object[] newObject = new Object[1];
		newObject[0] = ToBeRemoved;
		connectedDevices = ComponentsManagment.removeComponents(newObject, connectedDevices,
				connectedDevicesCounter);

		// Sets the new count for number of connected devices in the array
		connectedDevicesCounter = connectedDevices.length;
	}


	/**
	 * Removes all the objects in the connectedDevices by replacing the
	 * connectedDevices with an empty array with 5 indexes.
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
	 * Function to add device(s) to the the connected devices list. NOTES - THE
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
	 * Adds the given connection by determining what kind of class the
	 * connection is an instance of, and then passes it on to the correct
	 * method.
	 */
	public void addConnection(Connection con)
	{
		if ( con instanceof InternalConnection )
		{
			addInternalConnection((InternalConnection) con);
		}
		else if ( con instanceof DeviceConnection )
		{
			addDeviceConnection((DeviceConnection) con);
		}
		else
		// This will then be a network connection.
		{
			addNetworkConnection((NetworkConnection) con);
		}

	}


	/**
	 * Removes the given connection by determining what kind of class the
	 * connection is an instance of, and then passes it on to the correct
	 * method.
	 */
	public void removeConnection(Connection con)
	{
		if ( con instanceof InternalConnection )
		{
			removeInternalConnection((InternalConnection) con);
		}
		else if ( con instanceof DeviceConnection )
		{
			removeDeviceConnection((DeviceConnection) con);
		}
		else
		// This will then be a network connection.
		{
			removeNetworkConnection((NetworkConnection) con);
		}

		try
		{
			releaseSingelConnectionPort(con);
		}
		catch ( PortIsNotRegisteredOnMotherboard e )
		{
			System.out.println(e.getMessage());
		}

	}


	/**
	 * Releases all the connection ports on a motherboard by determining what
	 * kind of class the connection is an instance of, and then clearing the
	 * correct ports on the {@link hardware.Motherboard motherboard} of the
	 * object.
	 */
	public void releaseAllConnectionPorts(Connection con)
	{
		Motherboard objectAmotherboard = null;

		try
		{
			objectAmotherboard = (Motherboard) getSpesificComponents(Motherboard.class)[0];
		}
		catch ( ObjectNotFoundException e )
		{
			System.out
					.println("Object - releaseConnectionPorts - Network connection - cant find Motherboard");
		}


		if ( con instanceof InternalConnection )
		{
			objectAmotherboard.setMaxCPUs(objectAmotherboard.getMaxCPUs());
			objectAmotherboard.setMaxDUCs(objectAmotherboard.getMaxDUCs());
			objectAmotherboard.setMaxPCIs(objectAmotherboard.getMaxPCIs());
			objectAmotherboard.setMaxRAMs(objectAmotherboard.getMaxRAMs());
		}
		else if ( con instanceof DeviceConnection )
		{
			objectAmotherboard.setMaxUSBs(objectAmotherboard.getMaxUSBs());
		}
		else
		// This will then be a network connection.
		{

			// Sets the arrays on the actual motherboard component to an array
			// of booleans with the given length of the last array, but where
			// all the indexes are false.
			objectAmotherboard
					.setMaxIntegratedLANs(objectAmotherboard.getIntegLANPortsAvailable().length);

		}
	}



	public void releaseAllNetworkConnectionPorts()
	{
		Motherboard objectAmotherboard = null;

		try
		{
			objectAmotherboard = (Motherboard) getSpesificComponents(Motherboard.class)[0];
		}
		catch ( ObjectNotFoundException e )
		{
			System.out
					.println("Object - releaseConnectionPorts - Network connection - cant find Motherboard");
		}

		// Sets the arrays on the actual motherboard component to an array
		// of booleans with the given length of the last array, but where
		// all the indexes are false.
		objectAmotherboard.setIntegLANPortsAvailable(new boolean[objectAmotherboard
				.getIntegLANPortsAvailable().length]);

	}


	public void releaseSingelConnectionPort(Connection con) throws PortIsNotRegisteredOnMotherboard
	{
		Motherboard objectMotherboard = null;

		try
		{
			objectMotherboard = (Motherboard) getSpesificComponents(Motherboard.class)[0];
		}
		catch ( ObjectNotFoundException e )
		{
			System.out
					.println("Object - releaseConnectionPorts - Network connection - cant find Motherboard");
		}


		if ( con instanceof InternalConnection )
		{
			// FIXME - Have to fix the removal function on ports.
		}
		else if ( con instanceof DeviceConnection )
		{
			// Gets the array of ports on the motherboard.
			boolean[] ports = objectMotherboard.getUSBPortsAvailable();

			// Gets the first index where the value is true, which means its a
			// taken port.
			int Index = getFirstTakenIndex(ports);

			// If the returned index is -1, then there are no taken ports and
			// something has gone wrong.
			if ( Index == -1 )
			{
				throw new exceptions.PortIsNotRegisteredOnMotherboard(objectMotherboard, this,
						"USB");
			}

			// Sets the port index to false, which means that it is not taken.
			ports[Index] = false;

			// Sorts the boolean array on the boolean true.
			ports = cleanup.cleanObjectArray(ports, true);

			// Sets the arrays on the actual motherboard component to an array
			// of booleans.
			objectMotherboard.setUSBPortsAvailable(ports);
		}
		else
		// This will then be a network connection.
		{
			// Gets the array of ports on the motherboard.
			boolean[] ports = objectMotherboard.getIntegLANPortsAvailable();

			// Gets the first index where the value is true, which means its a
			// taken port.
			int Index = getFirstTakenIndex(ports);

			// If the returned index is -1, then there are no taken ports and
			// something has gone wrong.
			if ( Index == -1 )
			{
				throw new exceptions.PortIsNotRegisteredOnMotherboard(objectMotherboard, this,
						"LAN");
			}

			// Sets the port index to false, which means that it is not taken.
			ports[Index] = false;

			// Sorts the boolean array on the boolean true.
			ports = cleanup.cleanObjectArray(ports, true);

			// Sets the arrays on the actual motherboard component to an array
			// of booleans.
			objectMotherboard.setIntegLANPortsAvailable(ports);
		}
	}



	/**
	 * Adds the givens connection to the array of internal connections. If
	 * necessary, initiates the array or extends the array.
	 */
	public void addInternalConnection(InternalConnection con)
	{
		if ( internalConnections == null )
		{
			internalConnections = initConnection(internalConnections);
		}

		for ( int i = 0; i < internalConnections.length; i++ )
		{
			if ( internalConnections[i] == null )
			{
				internalConnections[i] = con;
				return;
			}
		}


		// If the function gets to this point it means that there where no
		// available indexes and the array needs to be extended.
		internalConnections = extendConnectionArray(internalConnections);

		// Runs the function again with the extended array.
		addInternalConnection(con);

	}


	/**
	 * Adds the givens connection to the array of network connections. If
	 * necessary, initiates the array or extends the array.
	 */
	public void addNetworkConnection(NetworkConnection con)
	{
		if ( networkConnections == null )
		{
			networkConnections = initConnection(networkConnections);
		}

		for ( int i = 0; i < networkConnections.length; i++ )
		{
			if ( networkConnections[i] == null )
			{
				networkConnections[i] = con;
				return;
			}
		}

		// If the function gets to this point it means that there where no
		// available indexes and the array needs to be extended.
		networkConnections = extendConnectionArray(networkConnections);

		// Runs the function again with the extended array.
		addNetworkConnection(con);

	}


	/**
	 * Adds the givens connection to the array of device connections. If
	 * necessary, initiates the array or extends the array.
	 */
	public void addDeviceConnection(DeviceConnection con)
	{
		if ( deviceConnections == null )
		{
			deviceConnections = initConnection(deviceConnections);
		}

		for ( int i = 0; i < deviceConnections.length; i++ )
		{
			if ( deviceConnections[i] == null )
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
	 * Finds and removes the given internal connection from the array of
	 * connections.
	 */
	public boolean removeInternalConnection(InternalConnection con)
	{

		for ( int i = 0; i < internalConnections.length; i++ )
		{
			if ( internalConnections[i].equals(con) )
			{
				internalConnections[i] = null;
				internalConnections = cleanup.cleanObjectArray(internalConnections);
				return true;
			}
		}

		return false;
	}



	/**
	 * Removes all the internal connections by replacing the internalConnections
	 * with an empty array with 5 indexes.
	 */
	public void removeAllInternalConnections()
	{
		internalConnections = new InternalConnection[5];
	}



	/**
	 * Finds and removes the given network connection from the array of
	 * connections.
	 */
	public boolean removeNetworkConnection(NetworkConnection con)
	{

		for ( int i = 0; i < networkConnections.length; i++ )
		{
			if ( networkConnections[i].equals(con) )
			{
				networkConnections[i] = null;
				networkConnections = cleanup.cleanObjectArray(networkConnections);
				return true;
			}
		}



		return false;
	}


	/**
	 * Removes all the network connections by replacing the networkConnections
	 * with an empty array with 5 indexes.
	 */
	public void removeAllNetworkConnections()
	{
		networkConnections = new NetworkConnection[5];
	}



	/**
	 * Finds and removes the given device connection from the array of
	 * connections.
	 */
	public boolean removeDeviceConnection(DeviceConnection con)
	{

		for ( int i = 0; i < deviceConnections.length; i++ )
		{
			if ( deviceConnections[i].equals(con) )
			{
				deviceConnections[i] = null;
				deviceConnections = cleanup.cleanObjectArray(deviceConnections);
				return true;
			}
		}

		return false;
	}


	/**
	 * Removes all the device connections by replacing the deviceConnections
	 * with an empty array with 5 indexes.
	 */
	public void removeAllDeviceConnections()
	{
		deviceConnections = new DeviceConnection[5];
	}



	/**
	 * This method is used to initiate the given array, giving it 5 empty
	 * indexes.
	 */
	private NetworkConnection[] initConnection(NetworkConnection[] array)
	{
		return array = new NetworkConnection[5];
	}


	/**
	 * This method is used to initiate the given array, giving it 5 empty
	 * indexes.
	 */
	private DeviceConnection[] initConnection(DeviceConnection[] array)
	{
		return array = new DeviceConnection[5];
	}


	/**
	 * This method is used to initiate the given array, giving it 5 empty
	 * indexes.
	 */
	private InternalConnection[] initConnection(InternalConnection[] array)
	{
		return array = new InternalConnection[5];
	}



	/**
	 * Extends the given array with 5 indexes.
	 */
	private NetworkConnection[] extendConnectionArray(NetworkConnection[] array)
	{
		NetworkConnection[] temp = new NetworkConnection[array.length + 5];

		for ( int i = 0; i < array.length; i++ )
		{
			temp[i] = array[i];
		}

		return temp;
	}


	/**
	 * Extends the given array with 5 indexes.
	 */
	private DeviceConnection[] extendConnectionArray(DeviceConnection[] array)
	{
		DeviceConnection[] temp = new DeviceConnection[array.length + 5];

		for ( int i = 0; i < array.length; i++ )
		{
			temp[i] = array[i];
		}

		return temp;
	}


	/**
	 * Extends the given array with 5 indexes.
	 */
	private InternalConnection[] extendConnectionArray(InternalConnection[] array)
	{
		InternalConnection[] temp = new InternalConnection[array.length + 5];

		for ( int i = 0; i < array.length; i++ )
		{
			temp[i] = array[i];
		}

		return temp;
	}


	/**
	 * Gets the first index that has the value true and returns that index. This
	 * method is mainly used internally for setting and releasing ports on a
	 * motherboard.
	 */
	private int getFirstTakenIndex(boolean[] array)
	{
		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] == true )
			{
				return i;
			}
		}

		return -1;
	}


	/**
	 * Checks whether or not an array of booleans contains the given boolean.
	 */
	private boolean checkArray(boolean[] array, boolean shouldContain)
	{
		boolean found = false;

		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i] == shouldContain )
			{
				found = true;
			}
		}

		return found;
	}



	/**
	 * Gets the number of actual connected devices, by it with RJ-45 or USB and
	 * so on.
	 */
	public int getNumberOfConnectedDevices()
	{
		int found = 0;

		if ( connectedDevices != null )
		{
			for ( int i = 0; i < connectedDevices.length; i++ )
			{
				if ( !connectedDevices[i].equals(null) )
				{
					found++;
				}
			}
		}

		return found;
	}

}
