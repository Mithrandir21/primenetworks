package objects.infrastructureObjects;


import java.io.Serializable;

import managment.ArrayManagment;
import managment.ComponentsManagment;
import objects.Infrastructure;
import objects.Object;
import objects.Servers;
import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class SingleServiceServer extends Infrastructure implements Serializable
{

	// THE MASKIN ITSELF

	// An array that contains pointers to the objects that make up a computer.
	private Object[] components;

	// Counts number of components in the components list
	private int componentCounter;


	// THE DEVICES SURROUNDING IT

	// An array of the objects it is connected to, whether by USB, RJ-45,
	// bluetooth and so on.
	private Object[] connectedDevices;

	// Counts the number of objects it is connected to
	private int connectedDevicesCounter;


	// NETWORK INFORMATION FIELDS

	// Server rating
	private int serverRate;

	// Nodes before it reaches the first router outside of the systems own
	// routers, ie the Internet.
	private int numberOfNodes;



	// THE SOFTWARE SERVER CONNECTION

	// The server object the machine will be connected to
	private Servers ServerApplication;




	/**
	 * Constructor of a server computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * server rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param ServerComponents
	 *            The initial components an instance of a server has.
	 */
	public SingleServiceServer(String Name, String Desc, String[] SupConInt,
			Object[] ServerComponents, Servers app)
	{
		super(Name, Desc, SupConInt);

		componentCounter = ServerComponents.length;

		components = ServerComponents;

		serverRate = 0; // Not been rated yet.

		ServerApplication = app;
	}



	// Get and Set methodes for retrieving all datafields.


	// GET METHODES


	/**
	 * Get all components of a computer.
	 * 
	 * @return Returns an array of {@link objects.Object Objects} containing the
	 *         components that make up the system.
	 */
	@Override
	public Object[] getComponents()
	{
		return components;
	}


	/**
	 * Get server rating.
	 */
	public int getServerRating()
	{
		return serverRate;
	}


	/**
	 * Get the number of nodes between the maskin and the internet.
	 */
	public int getNumberJumps()
	{
		return numberOfNodes;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @return the serverApplication
	 */
	public Servers getServerApplication()
	{

		return ServerApplication;
	}



	/**
	 * Get spesific components by searching for components with the give class
	 * type.
	 * 
	 * @return Returns an array of components that match with the given class.
	 */
	@Override
	public Object[] getSpesificComponents(Class<?> ComponentClass)
			throws ObjectNotFoundException
	{
		Object[] componentsFound = ArrayManagment.getSpesificComponents(
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
	@Override
	public Object[] getSpesificConncetedDevices(Class<?> connectedDeviceClass)
			throws ObjectNotFoundException
	{
		Object[] componentsFound = ArrayManagment.getSpesificComponents(
				connectedDeviceClass, components, componentCounter);

		return componentsFound;
	}


	// SET METHODES



	/**
	 * Set method for replacing of all components.
	 * 
	 * @param NewComponents
	 *            Array of new components the server will contain.
	 */
	@Override
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
	 *            Array of new connceted devices the server is connected to.
	 */
	@Override
	public void setAllConnectedDevices(Object[] NewConnectedDevices)
	{
		connectedDevices = NewConnectedDevices;

		// Sets the number of connected devices to the length of the new array
		connectedDevicesCounter = NewConnectedDevices.length;
	}



	/**
	 * Set method for server rating.
	 */
	public void setServerRating(int rate)
	{
		serverRate = rate;
	}


	/**
	 * Set method for number of jumps to the internet.
	 */
	public void setNumberOfJumps(int jumps)
	{
		numberOfNodes = jumps;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @param serverApplication
	 *            the serverApplication to set
	 */
	public void setServerApplication(Servers serverApplication)
	{

		ServerApplication = serverApplication;
	}



	// CLASS METHODES


	// COMPONENTS MANIPULATION



	/**
	 * Function for replacing a spesific given component with a given new
	 * component.
	 * 
	 * @param NewComponent
	 *            The component to replace the previous one.
	 * @param OldComponent
	 *            The component to be replaced.
	 */
	@Override
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
	@Override
	public void removeComponent(Object[] ToBeRemoved)
			throws ObjectNotFoundInArrayException
	{
		components = ComponentsManagment.removeComponents(ToBeRemoved,
				components, componentCounter);

		// Sets the new count for number of components in the array
		componentCounter = components.length;
	}



	/**
	 * Function to add component(s) to the the components list.
	 * 
	 * @param NewComponents
	 *            An array of new components.
	 */
	@Override
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
	@Override
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
	@Override
	public void removeConnectedDevices(Object[] ToBeRemoved)
			throws ObjectNotFoundInArrayException
	{
		connectedDevices = ComponentsManagment.removeComponents(ToBeRemoved,
				connectedDevices, connectedDevicesCounter);

		// Sets the new count for number of connected devices in the array
		connectedDevicesCounter = connectedDevices.length;
	}


	/**
	 * Function to add device(s) to the the connceted devices list.
	 * 
	 * @param NewConnectedDevices
	 *            An array of new devices.
	 */
	@Override
	public void addConnectedDevices(Object[] NewConnectedDevices)
	{
		connectedDevices = ComponentsManagment.addComponents(
				NewConnectedDevices, connectedDevices, connectedDevicesCounter);

		// Sets the new count for number of connected devices in the array
		connectedDevicesCounter = connectedDevices.length;
	}
}
