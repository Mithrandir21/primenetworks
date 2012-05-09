/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package objects;



import java.awt.Point;
import java.io.Serializable;
import java.util.Random;

import javax.swing.ImageIcon;

import logistical.cleanup;
import managment.ArrayManagment;
import managment.ComponentsManagment;
import objects.hardwareObjects.Motherboard;
import widgetManipulation.NetworkRules;
import widgets.WorkareaCanvas;
import connections.Connection;
import connections.ConnectionUtils;
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
 * FIXME - Make Object rating system.<br>
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public abstract class Object implements Serializable, Cloneable
{

	// THE OBJECT ITSELF

	/**
	 * 
	 */
	private static final long serialVersionUID = 215161254854759898L;


	private long objectSerial;


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



	// THE MACHINE ITSELF

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
	 * The software that will be running on the object.
	 */
	private Software[] software;



	// VISUAL PROPERTIES
	/**
	 * The location of the object on a scene.
	 */
	private Point location;


	/**
	 * The ImageIcon that will represent the Object visually
	 */
	private ImageIcon visualImage;



	/**
	 * A boolean that says whether an object is exempted the
	 * {@link NetworkRules} that apply to the {@link WorkareaCanvas} it is
	 * inside.
	 */
	private boolean exemptedNetworkRules = false;


	/**
	 * Constructor of an object.<br>
	 * This constructor also sets the number of components in the system to "0".
	 * 
	 * @param Name
	 *            The name of the object.
	 * @param Desc
	 *            The description of the object.
	 */
	protected Object(String Name, String Desc)
	{
		name = Name;
		description = Desc;
		supportedConnectionInterfaces = null;

		componentCounter = 0;

		components = null;

		// Creates a random LONG serial number for the object
		createRandomLongSerial();
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
	protected Object(String Name, String Desc, String[] SupConInt)
	{
		name = Name;
		description = Desc;
		supportedConnectionInterfaces = SupConInt;

		componentCounter = 0;

		components = null;

		// Creates a random LONG serial number for the object
		createRandomLongSerial();
	}



	/**
	 * The constructor of the object superclass. All objects must have both a
	 * name and description. This constructor also sets the initial components
	 * that an object starts of with. Like
	 * {@link objects.hardwareObjects.Motherboard motherboards}.
	 * 
	 * @param Name
	 *            The name of an object
	 * @param Desc
	 *            The description of any object
	 * @param objectComponents
	 *            The initial components an instance of a object has.
	 */
	protected Object(String Name, String Desc, Object[] objectComponents)
	{
		name = Name;
		description = Desc;
		supportedConnectionInterfaces = null;

		componentCounter = objectComponents.length;

		components = objectComponents;

		// Creates a random LONG serial number for the object
		createRandomLongSerial();
	}



	/**
	 * The constructor of the object superclass. All objects must have both a
	 * name and description. This constructor also sets the supported user
	 * interfaces the object supports and sets the initial components that an
	 * object starts of with. Like {@link objects.hardwareObjects.Motherboard
	 * motherboards}.
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
	protected Object(String Name, String Desc, String[] SupConInt,
			Object[] objectComponents)
	{
		name = Name;
		description = Desc;
		supportedConnectionInterfaces = SupConInt;

		componentCounter = objectComponents.length;

		components = objectComponents;

		// Creates a random LONG serial number for the object
		createRandomLongSerial();
	}


	/**
	 * The constructor of the object superclass. All objects must have both a
	 * name and description. This constructor also sets the supported user
	 * interfaces the object supports and set the objects
	 * {@link objects.hardwareObjects.Motherboard motherboards}. This
	 * constructor is good for the creation of infrastructure which may only
	 * have a motherboard.
	 * 
	 * @param Name
	 *            The name of an object
	 * @param Desc
	 *            The description of any object
	 * @param SupConInt
	 *            The supported connection interfaces an instance of a object
	 *            supports.
	 * @param objectMB
	 *            The {@link objects.hardwareObjects.Motherboard motherboard} of
	 *            an object.
	 */
	protected Object(String Name, String Desc, String[] SupConInt,
			Motherboard objectMB)
	{
		name = Name;
		description = Desc;
		supportedConnectionInterfaces = SupConInt;

		componentCounter = 1;

		components = new Object[1];
		components[0] = objectMB;

		// Creates a random LONG serial number for the object
		createRandomLongSerial();
	}



	/**
	 * This function creates a random {@link Long} that is set as the objects
	 * serial number.
	 */
	public void createRandomLongSerial()
	{
		// Default seed comes from system time.
		Random r = new Random();

		objectSerial = r.nextLong();
	}



	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Gets the serial of the object.
	 */
	public long getObjectSerial()
	{
		return objectSerial;
	}


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
	 * Get all the network connections between the computer and devices it is
	 * connected to.
	 * 
	 * @return Returns an array of {@link connections.Connection Connections}.
	 */
	public Connection[] getNetworkConnections()
	{

		return networkConnections;
	}



	/**
	 * Get all the device connections between the computer and devices it is
	 * connected to.
	 * 
	 * @return Returns an array of {@link connections.Connection Connections}.
	 */
	public Connection[] getDeviceConnections()
	{

		return deviceConnections;
	}



	/**
	 * Get specific components by searching for components with the give class
	 * type.
	 * 
	 * @return Returns an array of components that match with the given class.
	 */
	public Object[] getSpesificComponents(Class<?> ComponentClass)
			throws ObjectNotFoundException
	{
		Object[] componentsFound = ArrayManagment.getSpesificComponents(
				ComponentClass, components, componentCounter);

		return componentsFound;
	}


	/**
	 * Get specific connected device by searching for devices with the give
	 * class type.
	 * 
	 * @return Returns an array of connected devices that match with the given
	 *         class.
	 */
	public Object[] getSpesificConncetedDevices(Class<?> connectedDeviceClass)
			throws ObjectNotFoundException
	{
		Object[] componentsFound = ArrayManagment.getSpesificComponents(
				connectedDeviceClass, components, componentCounter);

		return componentsFound;
	}



	/**
	 * Gets the ImageIcon that represents the Object visually.
	 */
	public ImageIcon getVisualImage()
	{
		return visualImage;
	}


	/**
	 * Gets an array containing all the {@link Software} "installed" on the
	 * object.
	 * 
	 * @return the software
	 */
	public Software[] getSoftware()
	{
		return software;
	}


	/**
	 * Gets the {@link Point} location of the object. This point is used to
	 * determine location on a {@link WorkareaCanvas}.
	 * 
	 * @return the location
	 */
	public Point getLocation()
	{
		return location;
	}


	/**
	 * A boolean on whether or not the object is exempted the Network rules, ie.
	 * {@link NetworkRules}.
	 * 
	 * @return the exemptedNetworkRules
	 */
	public boolean isExemptedNetworkRules()
	{
		return exemptedNetworkRules;
	}



	// SETTERS


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
	public void setSupportedConnectionInterfaces(
			String[] supportedConnectionInterfaces)
	{
		this.supportedConnectionInterfaces = supportedConnectionInterfaces;
	}


	/**
	 * Description NEEDED!
	 * 
	 * @param supportedConnectionInterface
	 *            the supportedConnectionInterface to set
	 */
	public void setSupportedConnectionInterfaces(
			String supportedConnectionInterface)
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
	 * Sets the {@link NetworkConnection NetworkConnections} array representing
	 * only network connections.
	 * 
	 * @param connections
	 *            the connections to set
	 */
	public void setConnections(NetworkConnection[] connections)
	{
		this.networkConnections = connections;
	}


	/**
	 * Sets the ImageIcon that will represent the Object visually. (Allows
	 * NULL).
	 */
	public void setVisualImage(ImageIcon visImage)
	{
		visualImage = visImage;
	}


	/**
	 * Sets the given of {@link Software} as the software "installed" on this
	 * object.
	 * 
	 * @param software
	 *            the software to set
	 */
	public void setSoftware(Software[] software)
	{
		this.software = software;
	}


	/**
	 * Sets the {@link Point} location of this object. Used to determine
	 * location on a {@link WorkareaCanvas}.
	 * 
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Point location)
	{
		this.location = location;
	}


	/**
	 * Sets a boolean on whether or not the object is exempted the Network
	 * rules, ie. {@link NetworkRules}.
	 * 
	 * @param exemptedNetworkRules
	 *            the exemptedNetworkRules to set
	 */
	public void setExemptedNetworkRules(boolean exemptedNetworkRules)
	{
		this.exemptedNetworkRules = exemptedNetworkRules;
	}



	// CLASS METHODES

	// COMPONENTS MANIPULATION

	/**
	 * Function for replacing a specific given component with a given new
	 * component.
	 * 
	 * (Note: Will run revalidateSupportedConnectionInterfaces().)
	 * 
	 * @param NewComponent
	 *            The component to replace the previous one.
	 * @param OldComponent
	 *            The component to be replaced.
	 */
	public void changeComponent(Object NewComponent, Object OldComponent)
	{
		components = ComponentsManagment.changeComponent(NewComponent,
				OldComponent, components, componentCounter);

		// Sets the new count for number of components in the array
		componentCounter = components.length;

		revalidateSupportedConnectionInterfaces();
	}



	/**
	 * Function to remove components from the array of components.
	 * 
	 * (Note: Will run revalidateSupportedConnectionInterfaces().)
	 * 
	 * @param ToBeRemoved
	 *            Component to be removed.
	 */
	public void removeComponent(Object[] ToBeRemoved)
			throws ObjectNotFoundInArrayException
	{
		components = ComponentsManagment.removeComponents(ToBeRemoved,
				components, componentCounter);

		// Sets the new count for number of components in the array
		componentCounter = components.length;

		revalidateSupportedConnectionInterfaces();
	}



	/**
	 * Function to add components to the components list.
	 * 
	 * (Note: Will run revalidateSupportedConnectionInterfaces().)
	 * 
	 * @param NewComponents
	 *            An array of new components.
	 */
	public void addComponents(Object[] NewComponents)
	{
		components = ComponentsManagment.addComponents(NewComponents,
				components, componentCounter);

		// Sets the new count for number of components in the array
		componentCounter = components.length;

		revalidateSupportedConnectionInterfaces();
	}



	/**
	 * Function to add component to the components list.
	 * 
	 * (Note: Will run revalidateSupportedConnectionInterfaces().)
	 * 
	 * @param NewComponent
	 */
	public void addComponent(Object NewComponent)
	{
		Object[] temp = new Object[1];
		temp[0] = NewComponent;

		components = ComponentsManagment.addComponents(temp, components,
				componentCounter);

		// Sets the new count for number of components in the array
		componentCounter = components.length;

		revalidateSupportedConnectionInterfaces();
	}


	// CONNECTED DEVICES MANIPULATION


	/**
	 * Function for replacing a specific given connected device with a given new
	 * connected devices.
	 * 
	 * @param NewconnectedDevice
	 *            The connected device to replace the previous one.
	 * @param OldconnectedDevice
	 *            The connected device to be replaced.
	 */
	public void changeConnectedDevice(Object NewconnectedDevice,
			Object OldconnectedDevice)
	{
		components = ComponentsManagment.changeComponent(NewconnectedDevice,
				OldconnectedDevice, connectedDevices, connectedDevicesCounter);

		// Sets the new count for number of connected devices in the array
		connectedDevicesCounter = connectedDevices.length;
	}



	/**
	 * Function to remove connected devices from the array of connected devices.
	 * 
	 * @param ToBeRemoved
	 *            Connected device to be removed.
	 */
	public void removeConnectedDevices(Object[] ToBeRemoved)
			throws ObjectNotFoundInArrayException
	{
		connectedDevices = ComponentsManagment.removeComponents(ToBeRemoved,
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
	public void removeConnectedDevices(Object ToBeRemoved)
			throws ObjectNotFoundInArrayException
	{
		Object[] newObject = new Object[1];
		newObject[0] = ToBeRemoved;
		connectedDevices = ComponentsManagment.removeComponents(newObject,
				connectedDevices, connectedDevicesCounter);

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

		releaseSingelConnectionPort(con.getConnectionType());
	}



	/**
	 * Function to add device(s) to the the connected devices list.
	 * 
	 * @param NewConnectedDevices
	 *            An array of new devices.
	 */
	public void addConnectedDevices(Object[] NewConnectedDevices)
	{
		connectedDevices = ComponentsManagment.addComponents(
				NewConnectedDevices, connectedDevices, connectedDevicesCounter);

		// Sets the new count for number of connected devices in the array
		connectedDevicesCounter = connectedDevices.length;
	}


	/**
	 * Function to add device(s) to the the connected devices list.
	 * 
	 * @param NewConnectedDevice
	 *            A new device.
	 */
	public void addConnectedDevices(Object NewConnectedDevice)
	{
		Object[] newObject = new Object[1];
		newObject[0] = NewConnectedDevice;
		connectedDevices = ComponentsManagment.addComponents(newObject,
				connectedDevices, connectedDevicesCounter);

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
	 * Releases all the connection ports on a motherboard by determining what
	 * kind of class the connection is an instance of, and then clearing the
	 * correct ports on the {@link objects.hardwareObjects.Motherboard
	 * motherboard} of the object.
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
			objectAmotherboard.setMaxIntegratedLANs(objectAmotherboard
					.getMaxIntegLANs());

		}
	}



	/**
	 * Removes all connected devices(USB), network(LAN) connections and resets
	 * the connected devices counter.
	 */
	public void removeAllConnections()
	{
		removeAllConnectedDevices();

		removeAllNetworkConnections();

		releaseAllNetworkConnectionPorts();

		releaseAllUSBConnectionPorts();

		resetConnectedDevicesCounter();
	}




	/**
	 * Removes all internal components from this object.
	 */
	public void removeAllComponents()
	{
		try
		{
			removeComponent(components);
		}
		catch ( ObjectNotFoundInArrayException e )
		{
			System.out.println("Object - removeAllComponents()");
			e.printStackTrace();
		}



	}


	/**
	 * Releases all the USB connections on the objects motherboard.
	 */
	public void releaseAllUSBConnectionPorts()
	{
		Motherboard objectAmotherboard = null;

		try
		{
			objectAmotherboard = (Motherboard) getSpesificComponents(Motherboard.class)[0];
		}
		catch ( ObjectNotFoundException e )
		{
			System.out.println("Object - releaseConnectionPorts "
					+ "- Network connection - cant find Motherboard");
		}

		// Sets the arrays on the actual motherboard component to an array
		// of booleans with the given length of the last array, but where
		// all the indexes are false.
		objectAmotherboard
				.setUSBPortsAvailable(objectAmotherboard.getMaxUSBs());
	}


	/**
	 * Releases all the Network(LAN or RJ-45) connections on the objects
	 * motherboard.
	 */
	public void releaseAllNetworkConnectionPorts()
	{
		Motherboard objectAmotherboard = null;

		try
		{
			objectAmotherboard = (Motherboard) getSpesificComponents(Motherboard.class)[0];
		}
		catch ( ObjectNotFoundException e )
		{
			System.out.println("Object - releaseConnectionPorts "
					+ "- Network connection - cant find Motherboard");
		}

		// Sets the arrays on the actual motherboard component to an array
		// of booleans with the given length of the last array, but where
		// all the indexes are false.
		objectAmotherboard.setIntegLANPortsAvailable(objectAmotherboard
				.getMaxIntegLANs());
	}


	/**
	 * Releases a single connection port on the objects motherboard. The type of
	 * port released depends on the class of the given connection.
	 * 
	 * @param con
	 *            The connection that is to determine what kind of port is to be
	 *            released.
	 * @throws PortIsNotRegisteredOnMotherboard
	 */
	public void releaseSingelConnectionPort(String conType)
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


		if ( conType.equals(ConnectionUtils.USB) )
		{
			objectMotherboard.makeOneUSBportAvailable();
		}
		else if ( conType.equals(ConnectionUtils.RJ45) )
		{
			objectMotherboard.makeOneIntLANportAvailable();
		}
		else if ( conType.equals(ConnectionUtils.Coax) )
		{
			objectMotherboard.makeOneCoaxPortAvailable();
		}
		else if ( conType.equals(ConnectionUtils.Fiber) )
		{
			objectMotherboard.makeOneFiberPortAvailable();
		}
	}



	// CONNECTION ARRAY MANIPULATION

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
				internalConnections = cleanup
						.cleanObjectArray(internalConnections);
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
				networkConnections = cleanup
						.cleanObjectArray(networkConnections);
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



	// INITIATION FUNCTIONS

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


	// EXTENTION FUNCTIONS

	/**
	 * Extends the given array with 5 indexes.
	 */
	private NetworkConnection[] extendConnectionArray(NetworkConnection[] array)
	{
		NetworkConnection[] temp = new NetworkConnection[array.length + 5];

		// Addes the new items to the end of the new array
		System.arraycopy(array, 0, temp, 0, array.length);

		return temp;
	}


	/**
	 * Extends the given array with 5 indexes.
	 */
	private DeviceConnection[] extendConnectionArray(DeviceConnection[] array)
	{
		DeviceConnection[] temp = new DeviceConnection[array.length + 5];

		// Addes the new items to the end of the new array
		System.arraycopy(array, 0, temp, 0, array.length);

		return temp;
	}


	/**
	 * Extends the given array with 5 indexes.
	 */
	private InternalConnection[] extendConnectionArray(
			InternalConnection[] array)
	{
		InternalConnection[] temp = new InternalConnection[array.length + 5];

		// Addes the new items to the end of the new array
		System.arraycopy(array, 0, temp, 0, array.length);

		return temp;
	}




	// MICS FUNCTIONS
	/**
	 * This function rechecks the supported interfaces of the object.
	 */
	public void revalidateSupportedConnectionInterfaces()
	{
		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(this);

		this.setSupportedConnectionInterfaces(supportedConnectionInterfaces);
	}


	/**
	 * Gets the number of actual connected devices, be it with RJ-45 or USB and
	 * so on.
	 */
	public int getNumberOfConnectedDevices()
	{
		int found = 0;

		if ( connectedDevices != null )
		{
			for ( int i = 0; i < connectedDevices.length; i++ )
			{
				if ( connectedDevices[i] != null )
				{
					found++;
				}
			}
		}

		return found;
	}



	/**
	 * This function concats both the network connections and the device
	 * connection into one single connection array and returns
	 * the new combined connection array.
	 * 
	 * @return An {@link Connection} array containing all the connections to and
	 *         from this object.
	 */
	public Connection[] getAllConnections()
	{
		int connectionsLenght = 0;

		if ( networkConnections != null )
		{
			connectionsLenght += networkConnections.length;
		}

		if ( deviceConnections != null )
		{
			connectionsLenght += deviceConnections.length;
		}




		// Creates a connection array the size of both the connection arrays.
		Connection[] connections = new Connection[connectionsLenght];



		if ( connectionsLenght > 0 )
		{
			if ( networkConnections != null )
			{
				System.arraycopy(networkConnections, 0, connections, 0,
						networkConnections.length);
			}

			if ( deviceConnections != null )
			{
				if ( networkConnections != null )
				{
					System.arraycopy(deviceConnections, 0, connections,
							networkConnections.length, deviceConnections.length);
				}
				else
				{
					System.arraycopy(deviceConnections, 0, connections, 0,
							deviceConnections.length);
				}
			}
		}


		return connections;
	}

	/**
	 * Resets the connected devices counter to 0.
	 */
	public void resetConnectedDevicesCounter()
	{
		connectedDevicesCounter = 0;
	}


	/**
	 * This just calls the clone() method of the super class of all classes,
	 * java.lang.Object. It is placed here so that when cloned is called, there
	 * will be no need to cast it to this class.
	 */
	@Override
	public Object clone()
	{
		try
		{
			// call clone in Object.
			return (Object) super.clone();
		}
		catch ( CloneNotSupportedException e )
		{
			System.out.println("Cloning not allowed.");
			return this;
		}
	}
}
