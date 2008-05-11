package clients;


import java.io.Serializable;
import connections.*;

import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;
import objects.*;
import objects.Object;
import logistical.*;
import managment.ComponentsManagment;


/**
 * This class represents a desktop machine. This can be any machine that has the
 * necessery equipment to run by its self without the need for add from any
 * other obejcts. <br>
 * An instance of this object will contain an array of pointers to the different
 * components of the desktop system. This will represent the objects that make
 * up the actual desktop, like a mouse, a monitor, a HDD, a motherboard and so
 * on.<br>
 * Each instance of this object will also contain an array of pointers to
 * devices connected to the system. These will represente the network around the
 * system, like switches, servers, firewalls, printer and so on. <br>
 * <br>
 * TODO - Make desktop rating system.<br>
 * A system that rates a desktop machine depending on the type of components it
 * has.(Components must then also have a way of rating themselfs.) <br>
 * <br>
 * TODO - Make node jumps checking system.<br>
 * A system that checks the number of "jumps", nodes, that stand between its
 * self and the internet.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class Desktop extends Clients implements Serializable
{

	// THE MASKIN ITSELF

	// An array that contains pointers to the objects that make up a computer.
	private Object[] components;

	// Counts number of components in the components list
	private int componentCounter;


	// THE DEVICES SURROUNDING IT

	// An array of the objects it is connected to, whether by USB, RJ-45,
	// bluetooth, PS-2 and so on.
	private Object[] connectedDevices;

	// An array of connection object which represent the connection between the
	// outside devices.
	private Connection[] connections;

	// Counts the number of objects it is connected to
	private int connectedDevicesCounter;


	// NETWORK INFORMATION FIELDS

	// Desktop rating
	private int desktopRate;

	// Nodes before it reaches the first router outside of the systems own
	// routers, ie the Internet.
	private int numberOfNodes;





	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 * @param DesktopComponents
	 *            The initial components an instance of a desktop has.
	 */
	public Desktop(String Name, String Desc, Object[] DesktopComponents)
	{
		super(Name, Desc);

		componentCounter = DesktopComponents.length;

		components = DesktopComponents;

		desktopRate = 0; // Not been rated yet.
	}


	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 * @param SupConInt
	 * 			  An array of strings that describes the supported connection
	 * 			  interfaces.
	 * @param DesktopComponents
	 *            The initial components an instance of a desktop has.
	 */
	public Desktop(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents)
	{
		super(Name, Desc, SupConInt);

		componentCounter = DesktopComponents.length;

		components = DesktopComponents;

		desktopRate = 0; // Not been rated yet.
	}



	// Get and Set methodes for retrieving all datafields.


	// GET METHODES


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

		return connections;
	}



	/**
	 * Get desktop rating.
	 */
	public int getDesktopRating()
	{
		return desktopRate;
	}


	/**
	 * Get the number of nodes between the maskin and the internet.
	 */
	public int getNumberJumps()
	{
		return numberOfNodes;
	}



	/**
	 * Get spesific components by searching for components with the give class
	 * type.
	 * 
	 * @return Returns an array of components that match with the given class.
	 */
	public Object[] getSpesificComponents(Class ComponentClass)
			throws ObjectNotFoundException
	{
		Object[] componentsFound = ComponentsManagment.getSpesificComponents(
				ComponentClass, components, componentCounter);

		return componentsFound;
	}



	/**
	 * Get spesific connceted device by searching for devices with the give
	 * class type.
	 * 
	 * @return Returns an array of connected devices that match with the given
	 *         class.
	 */
	public Object[] getSpesificConncetedDevices(Class connectedDeviceClass)
			throws ObjectNotFoundException
	{
		Object[] componentsFound = ComponentsManagment.getSpesificComponents(
				connectedDeviceClass, components, componentCounter);

		return componentsFound;
	}


	// SET METHODES

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
	public void setConnections(Connection[] connections)
	{

		this.connections = connections;
	}



	/**
	 * Set method for desktop rating.
	 */
	public void setDekstopRating(int rate)
	{
		desktopRate = rate;
	}


	/**
	 * Set method for number of jumps to the internet.
	 */
	public void setNumberOfJumps(int jumps)
	{
		numberOfNodes = jumps;
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
		components = ComponentsManagment.changeComponent(NewComponent,
				OldComponent, components, componentCounter);

		// Sets the new count for number of components in the array
		componentCounter = components.length;
	}



	/**
	 * Function to remove components from the array of components.
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
	}



	/**
	 * Function to add component(s) to the the components list. NOTES - THE
	 * SYSTEM WILL CHECK AT A EARLIER POINT SEE IF THERE IS ROOM FOR THESE
	 * COMPONENTS
	 * 
	 * @param NewComponents
	 *            An array of new components.
	 */
	public void addComponents(Object[] NewComponents) throws Exception
	{
		components = ComponentsManagment.addComponents(NewComponents,
				components, componentCounter);

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
	 * Function to add device(s) to the the connceted devices list. NOTES - THE
	 * SYSTEM WILL CHECK AT A EARLIER POINT SEE IF THERE IS ROOM FOR THESE
	 * COMPONENTS
	 * 
	 * @param NewConnectedDevices
	 *            An array of new devices.
	 */
	public void addConnectedDevices(Object[] NewConnectedDevices)
			throws Exception
	{
		connectedDevices = ComponentsManagment.addComponents(
				NewConnectedDevices, connectedDevices, connectedDevicesCounter);

		// Sets the new count for number of connected devices in the array
		connectedDevicesCounter = connectedDevices.length;
	}




}