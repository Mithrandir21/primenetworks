package managment;


import java.awt.Component;

import javax.swing.JOptionPane;

import logistical.cleanup;
import objects.Object;
import objects.hardwareObjects.CPU;
import objects.hardwareObjects.Discdrive;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.GraphicsCard;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import objects.hardwareObjects.Ram;
import connections.Connection;
import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;
import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;


/**
 * Class that contains different function that add, remove and replace components from a given array. It is used in the
 * different parts of the program, specially {@link objects.clientObjects.Desktop Desktops},
 * {@link objects.clientObjects.Laptop Laptops}, {@link objects.Servers Servers} and
 * {@link objects.infrastructureObjects.Rack Racks}.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ComponentsManagment
{

	/**
	 * Function to add component(s) to the the components list.
	 * 
	 * @param NewComponents
	 *            An array of new components.
	 * @param componentCounter
	 *            The counter that tells how many components are in the current components array.
	 * @param components
	 *            The current components list.
	 * @throws Exception
	 *             Throws an exception with a message which says that the component is already present.
	 */
	public static Object[] addComponents(Object[] NewComponents, Object[] components, int componentCounter)
	{

		// The number of new components to be added to the components array
		int numberOfNewComponents = 0;

		// Checks to see whether any of the indexes in the new components array
		// is null.
		for ( int i = 0; i < NewComponents.length; i++ )
		{
			if ( NewComponents[i] != null )
			{
				numberOfNewComponents++;
			}
		}

		// Makes a new components array with the added number of indexes
		Object[] tempComponents = new Object[componentCounter + numberOfNewComponents];

		// The boolean array that tells whether or not any of the objects
		// already are in the array
		boolean[] areFound = new boolean[0];
		if ( components != null )
		{
			areFound = arrayContains(components, NewComponents);
		}

		// Checks to see if any of the
		for ( int i = 0; i < areFound.length; i++ )
		{
			if ( areFound[i] == true )
			{
				// A try/catch in case the object is null.
				try
				{
					throw new Exception("The component " + NewComponents[i].getObjectName() + " is already present.");
				}
				catch ( Exception e )
				{
					e.printStackTrace();
					return components;
				}
			}
		}


		// Adds the old components to the new array
		for ( int i = 0; i < componentCounter; i++ )
		{
			tempComponents[i] = components[i];
		}


		// Addes the new components to the end of the new array
		for ( int i = 0; i < numberOfNewComponents; i++ )
		{
			tempComponents[componentCounter] = NewComponents[i];

			componentCounter++;
		}

		return tempComponents;
	}


	/**
	 * Function to add a single component to the the components list.
	 * 
	 * @param NewComponent
	 *            A single new component.
	 * @param components
	 *            The current components list.
	 * @throws Exception
	 *             Throws an exception with a message which says that the component is already present.
	 */
	public static Object[] addComponent(Object NewComponent, Object[] components)
	{
		// Makes a new components array with the added number of indexes
		Object[] tempComponents = new Object[components.length + 1];

		// The boolean that tells whether or not the object already is in the
		// array
		boolean isFound = arrayContains(components, NewComponent);

		// Checks to see if the was found in the array of objects.
		if ( isFound == true )
		{
			// A try/catch in case the object is null.
			try
			{
				throw new Exception("The component " + NewComponent.getObjectName() + " is already present.");
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				return components;
			}
		}


		for ( int i = 0; i < components.length; i++ )
		{
			tempComponents[i] = components[i];
		}


		// Adds the new components at the end of the array of components.
		tempComponents[components.length] = NewComponent;


		return tempComponents;
	}


	/**
	 * Function to remove an array of components from the array of components.
	 * 
	 * @param ToBeRemoved
	 *            Component to be removed.
	 * @param componentCounter
	 *            The counter that tells how many components are in the current components array.
	 * @param components
	 *            The current components list.
	 * @throws ObjectNotFoundInArrayException
	 *             Throws an exception which states that one or more of the object that to be removed do not exist in
	 *             the components object they are trying to be removed from.
	 */
	public static Object[] removeComponents(Object[] ToBeRemoved, Object[] components, int componentCounter)
			throws ObjectNotFoundInArrayException
	{
		// booleans to check whether the objects to be removed are found or not
		boolean[] objectFound = new boolean[ToBeRemoved.length];

		// Number of components removed
		int componentsRemoved = 0;


		// Goes through all the components and removes the one(s) to be removed
		for ( int i = 0; i < componentCounter; i++ )
		{
			if ( components[i] != null )
			{
				for ( int j = 0; j < ToBeRemoved.length; j++ )
				{
					if ( ToBeRemoved[j] != null )
					{
						if ( components[i].equals(ToBeRemoved[j]) )
						{
							components[i] = null;

							objectFound[j] = true;

							j = ToBeRemoved.length;
							componentsRemoved++;
						}
					}
				}
			}
		}

		// Checks whether all the objects were found and removed
		for ( int i = 0; i < objectFound.length; i++ )
		{
			if ( objectFound[i] == false )
			{
				ObjectNotFoundInArrayException exception = new ObjectNotFoundInArrayException(
						"Object was not found, hence cannot " + "be deleted. Contact systemadminstrator.",
						ToBeRemoved[i]);

				throw exception;
			}
		}

		// Cleans the array of any null pointers
		components = cleanup.cleanObjectArray(components);

		return components;
	}


	/**
	 * Function to remove an array of components from the array of components.
	 */
	public static Object[] removeComponent(Object ToBeRemoved, Object[] components, int componentCounter)
	{
		// Goes through all the components and removes the one(s) to be removed
		for ( int i = 0; i < componentCounter; i++ )
		{
			if ( components[i] != null )
			{
				if ( components[i].equals(ToBeRemoved) )
				{
					components[i] = null;
				}
			}
		}


		// Cleans the array of any null pointers
		components = cleanup.cleanObjectArray(components);

		return components;
	}

	/**
	 * Function for replacing a specific given component with a given new component.
	 * 
	 * @param NewComponent
	 *            The component to replace the previous one.
	 * @param OldComponent
	 *            The component to be replaced.
	 * @param componentCounter
	 *            The counter that tells how many components are in the current components array.
	 * @param components
	 *            The current components list.
	 */
	public static Object[] changeComponent(Object NewComponent, Object OldComponent, Object[] components,
			int componentCounter)
	{
		// The boolean array that tells whether or not any of the object already
		// are in the array
		boolean isFound = arrayContains(components, NewComponent);

		// Checks to see if any of the
		if ( isFound == true )
		{
			// A try/catch incase the object is null.
			try
			{
				throw new Exception("The component " + NewComponent.getObjectName() + " is already present.");
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}

		}



		// Goes through all the components and replaces the old component with
		// the new one
		for ( int i = 0; i < componentCounter; i++ )
		{
			if ( components[i].equals(OldComponent) )
			{
				components[i] = NewComponent;

				// Sets the index to the lenght of the component to get out of
				// the loop
				i = componentCounter;
			}
		}


		components = cleanup.cleanObjectArray(components);


		return components;
	}


	/**
	 * This method will remove any component with the given class from the component list of the this classes object if
	 * the given Variable does not match the given newVariable.
	 * 
	 * @param componentClass
	 *            The class of the components that might be removed.
	 * @param variable
	 *            The variable on the motherboard. Like the socket or GPU port.
	 * @param newVariable
	 *            The editor variable that will be checked for differences.
	 * @param mainObj
	 *            The object that the objects with the given class will be removed from.
	 */
	public static void removeComponentFromObject(Class<?> componentClass, String variable, String newVariable,
			Object mainObj)
	{
		if ( !variable.equals(newVariable) )
		{
			if ( ComponentsManagment.containsComponent(componentClass, mainObj.getComponents(),
					mainObj.getComponents().length) )
			{

				boolean objContains = true;

				Object[] returned = null;

				try
				{
					// Find the components with the given class on a motherboard
					returned = ArrayManagment.getSpesificComponents(componentClass, mainObj.getComponents(), mainObj
							.getComponents().length);
				}
				catch ( ObjectNotFoundException ex )
				{
					objContains = false;
				}


				if ( objContains )
				{
					try
					{
						mainObj.setAllComponents(ComponentsManagment.removeComponents(returned,
								mainObj.getComponents(), mainObj.getComponents().length));
					}
					catch ( ObjectNotFoundInArrayException ex )
					{
						ex.printStackTrace();
					}
				}


				// Updates the views of the object to correctly show the
				// current info.
				ObjectView view = PrimeMain1.getObjectView(mainObj);
				if ( view != null )
				{
					view.updateViewInfo();
				}
			}
		}
	}




	// COMPONENTS CHECKS
	/**
	 * This method process all the changes made to the software the object contains. It first finds the motherboard of
	 * the given object and then calls all the other methods in this class to validate the software for compatibility.
	 * 
	 * @param obj
	 *            The object that contains the software which will be validated.
	 */
	public static void processAllChanges(Object obj)
	{
		Object[] components = obj.getComponents();

		Motherboard mb = null;

		try
		{
			mb = (Motherboard) ArrayManagment.getSpesificComponents(Motherboard.class, components, components.length)[0];
		}
		catch ( ObjectNotFoundException e1 )
		{
			// FIXME - ProcessAll motherboard get
			e1.printStackTrace();
		}

		// FIXME - Fix the remove ports function where the mb has less ports then connected
		// System.out.println(mb.getMaxLANs());
		// System.out.println(mb.getMaxIntegLANs());
		// System.out.println(mb.getIntegLANPortsAvailable());

		if ( mb != null )
		{
			processCPUchanges(mb, obj);

			processDiscDriveChanges(mb, obj);

			processExternalNICchanges(mb, obj);

			processInternalNICchanges(mb, obj);

			processGPUchanges(mb, obj);

			processHDDchanges(mb, obj);

			processRAMchanges(mb, obj);
		}

		// Updates the views of the object to correctly show the
		// current info.
		ObjectView view = PrimeMain1.getObjectView(obj);
		if ( view != null )
		{
			view.updateViewInfo();
		}
	}


	/**
	 * Checks compatibility of the any CPU component with the motherboard. Removes the ones that are not compatible.
	 */
	public static void processCPUchanges(Motherboard mb, Object obj)
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		try
		{
			// FIXME - How to get an objects motherboard.
			// Gets all the CPU components in the components array.
			Object[] cpus = ArrayManagment.getSpesificComponents(CPU.class, components, components.length);

			for ( int i = 0; i < cpus.length; i++ )
			{
				// Gets all the Discdrive components in the components array.
				if ( mb.getSocket() != "" && mb.getSocket() != null )
				{
					CPU cpu = (CPU) cpus[i];
					// Checks the socket of the cpu versus the socket on the
					// motherboard
					if ( cpu.getSocket() != mb.getSocket() )
					{
						// Removes the actual components.
						obj.setAllComponents(ComponentsManagment.removeComponent(cpu, components, components.length));

						mb.makeOneCPUportAvailable();
					}
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Does nothing if no objects are found.
		}
	}



	/**
	 * Checks compatibility of the any DicsDrive component with the motherboard. Removes the ones that are not
	 * compatible.
	 */
	public static void processDiscDriveChanges(Motherboard mb, Object obj)
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		try
		{
			// Gets all the Discdrive components in the components array.
			Object[] drives = ArrayManagment.getSpesificComponents(Discdrive.class, components, components.length);

			// 
			for ( int i = 0; i < drives.length; i++ )
			{
				// If the motherboard actual has a value that can be checked.
				if ( mb.getDUCconnectionType() != "" && mb.getDUCconnectionType() != null )
				{
					Discdrive dicsdrive = (Discdrive) drives[i];
					if ( dicsdrive.getPort() != mb.getDUCconnectionType() )
					{
						// Removes the actual components.
						obj.setAllComponents(ComponentsManagment.removeComponent(dicsdrive, components,
								components.length));

						mb.makeOneDUCportAvailable();
					}
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Does nothing if no objects are found.
		}
	}



	/**
	 * Checks compatibility of the any ExternalNIC component with the motherboard. Removes the ones that are not
	 * compatible.
	 */
	public static void processExternalNICchanges(Motherboard mb, Object obj)
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		try
		{
			// Gets all the ExternalNetworksCard components in the components
			// array.
			Object[] extNICs = ArrayManagment.getSpesificComponents(ExternalNetworksCard.class, components,
					components.length);

			// 
			for ( int i = 0; i < extNICs.length; i++ )
			{
				ExternalNetworksCard extNIC = (ExternalNetworksCard) extNICs[i];

				// if ( mb.getUSBPortsAvailable() )
				// { TODO - External NIC check
				// // Removes the actual components.
				// obj.setAllComponents(ComponentsManagment.removeComponent(
				// extNIC, components, components.length));
				// }
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Does nothing if no objects are found.
		}
	}



	/**
	 * Checks compatibility of the any InternalNIC component with the motherboard. Removes the ones that are not
	 * compatible.
	 */
	public static void processInternalNICchanges(Motherboard mb, Object obj)
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		try
		{
			// Gets all the ExternalNetworksCard components in the components
			// array.
			Object[] intNICs = ArrayManagment.getSpesificComponents(InternalNetworksCard.class, components,
					components.length);

			// 
			for ( int i = 0; i < intNICs.length; i++ )
			{
				// If the motherboard actual has a value that can be checked.
				if ( mb.getDUCconnectionType() != "" && mb.getDUCconnectionType() != null )
				{
					InternalNetworksCard intNIC = (InternalNetworksCard) intNICs[i];
					if ( intNIC.getConnectionType() != mb.getDUCconnectionType() )
					{
						// Removes the actual components.
						obj
								.setAllComponents(ComponentsManagment.removeComponent(intNIC, components,
										components.length));

						mb.makeOnePCIportAvailable();
					}
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Does nothing if no objects are found.
		}
	}



	/**
	 * Checks compatibility of the any GPU component with the motherboard. Removes the ones that are not compatible.
	 */
	public static void processGPUchanges(Motherboard mb, Object obj)
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		try
		{
			// Gets all the GraphicsCard components in the components array.
			Object[] GPUs = ArrayManagment.getSpesificComponents(GraphicsCard.class, components, components.length);

			// 
			for ( int i = 0; i < GPUs.length; i++ )
			{
				// If the motherboard actual has a value that can be checked.
				if ( mb.getGraphicalPort() != "" && mb.getGraphicalPort() != null )
				{
					InternalNetworksCard gpu = (InternalNetworksCard) GPUs[i];
					if ( gpu.getConnectionType() != mb.getGraphicalPort() )
					{
						// Removes the actual components.
						obj.setAllComponents(ComponentsManagment.removeComponent(gpu, components, components.length));
					}
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Does nothing if no objects are found.
		}
	}



	/**
	 * Checks compatibility of the any HDD component with the motherboard. Removes the ones that are not compatible.
	 */
	public static void processHDDchanges(Motherboard mb, Object obj)
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		try
		{
			// Gets all the HDD components in the components array.
			Object[] HDDs = ArrayManagment.getSpesificComponents(HDD.class, components, components.length);

			// If the port to the motherboard is not the same
			for ( int i = 0; i < HDDs.length; i++ )
			{
				// If the motherboard actual has a value that can be checked.
				if ( mb.getDUCconnectionType() != "" && mb.getDUCconnectionType() != null )
				{
					HDD hdd = (HDD) HDDs[i];
					if ( hdd.getPort() != mb.getDUCconnectionType() )
					{
						// Removes the actual components.
						obj.setAllComponents(ComponentsManagment.removeComponent(hdd, components, components.length));

						mb.makeOneDUCportAvailable();
					}
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Does nothing if no objects are found.
		}
	}



	/**
	 * Checks compatibility of the any RAM component with the motherboard. Removes the ones that are not compatible.
	 */
	public static void processRAMchanges(Motherboard mb, Object obj)
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		try
		{
			// Gets all the Ram components in the components array.
			Object[] RAMs = ArrayManagment.getSpesificComponents(Ram.class, components, components.length);

			// 
			for ( int i = 0; i < RAMs.length; i++ )
			{
				// If the motherboard actual has a value that can be checked.
				if ( mb.getRAMtype() != "" && mb.getRAMtype() != null )
				{
					Ram RAM = (Ram) RAMs[i];
					// If the port to the motherboard is not the same
					if ( RAM.getPort() != mb.getRAMtype() )
					{
						// Removes the actual components.
						obj.setAllComponents(ComponentsManagment.removeComponent(RAM, components, components.length));

						mb.makeOneRAMportAvailable();
					}
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Does nothing if no objects are found.
		}
	}





	/**
	 * Checks, and if possible, adds the given cpu to the components array of the given Object. The checks consists of
	 * tests on whether or not the there are available sockets on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param cpu
	 *            The CPU that will be tested and, if possible, added to the object.
	 * @param comp
	 *            This will be the component that message to the user will be shown over.
	 * @return Returns true or false based on whether or not the given CPU object is added to the main object.
	 */
	public static boolean processCPUmatch(Object mainObj, Motherboard mb, CPU cpu, Component comp)
	{
		int availablePort = mb.getCPUPortsAvailable();

		// Check the availability of sockets.
		if ( availablePort > 0 )
		{
			// Checks the match between the sockets.
			if ( cpu.getSocket().equals(mb.getSocket()) )
			{
				// First we add the component to the components list of the main
				// object.
				mainObj.addComponent(cpu);
				
				// Then we set the ports to the motherboard
				mb.makeOneCPUportTaken();
			}
			// If the sockets don't match.
			else
			{
				JOptionPane.showMessageDialog(comp, "The socket on the motherboard, " + mb.getSocket()
						+ ", does not match the CPU socket, " + cpu.getSocket() + ".", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		// If there are not available sockets.
		else
		{
			JOptionPane.showMessageDialog(comp, "There are no available CPU sockets left on the machine.", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		}



		return false;
	}



	/**
	 * Checks, and if possible, adds the given ram to the components array of the given Object. The checks consists of
	 * tests on whether or not the there are available ports on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param ram
	 *            The RAM that will be tested and, if possible, added to the object.
	 * @param comp
	 *            This will be the component that message to the user will be shown over.
	 * @return Returns true or false based on whether or not the given Ram object is added to the main object.
	 */
	public static boolean processRAMmatch(Object mainObj, Motherboard mb, Ram ram, Component comp)
	{
		int availablePort = mb.getRAMPortsAvailable();

		// Check the availability of ports.
		if ( availablePort > 0 )
		{
			// Checks the match between the types of ram.
			if ( ram.getPort().equals(mb.getRAMtype()) )
			{
				// First we add the component to the components list of the main
				// object.
				mainObj.addComponent(ram);

				// Then we set the ports to the motherboard
				mb.makeOneRAMportTaken();
			}
			// If the types don't match.
			else
			{
				JOptionPane.showMessageDialog(comp, "The port on the motherboard, " + mb.getRAMtype()
						+ ", does not match the RAM ports, " + ram.getPort() + ".", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		// If there are not available type.
		else
		{
			JOptionPane.showMessageDialog(comp, "There are no available RAM ports left on the machine.", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		}



		return false;
	}



	/**
	 * Checks, and if possible, adds the given harddisc to the components array of the given Object. The checks consists
	 * of tests on whether or not the there are available ports on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param hdd
	 *            The HDD that will be tested and, if possible, added to the object.
	 * @param comp
	 *            This will be the component that message to the user will be shown over.
	 * @return Returns true or false based on whether or not the given HDD object is added to the main object.
	 */
	public static boolean processHDDmatch(Object mainObj, Motherboard mb, HDD hdd, Component comp)
	{
		int availablePort = mb.getDUCPortsAvailable();

		// Check the availability of ports.
		if ( availablePort > 0 )
		{
			// Checks the match between the types of hdd.
			if ( hdd.getPort().equals(mb.getDUCconnectionType()) )
			{
				// First we add the component to the components list of the main
				// object.
				mainObj.addComponent(hdd);

				// Then we set the ports to the motherboard
				mb.makeOneDUCportTaken();
			}
			// If the types don't match.
			else
			{
				JOptionPane.showMessageDialog(comp, "The port on the motherboard, " + mb.getDUCconnectionType()
						+ ", does not match the HDD ports, " + hdd.getPort() + ".", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		// If there are not available type.
		else
		{
			JOptionPane.showMessageDialog(comp, "There are no available HDD ports left on the machine.", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		}



		return false;
	}



	/**
	 * Checks, and if possible, adds the given discdrive to the components array of the given Object. The checks
	 * consists of tests on whether or not the there are available ports on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param dics
	 *            The Discdrive that will be tested and, if possible, added to the object.
	 * @param comp
	 *            This will be the component that message to the user will be shown over.
	 * @return Returns true or false based on whether or not the given Discdrive object is added to the main object.
	 */
	public static boolean processDiscDrivematch(Object mainObj, Motherboard mb, Discdrive dics, Component comp)
	{
		int availablePort = mb.getDUCPortsAvailable();

		// Check the availability of ports.
		if ( availablePort > 0 )
		{
			// Checks the match between the types of disc.
			if ( dics.getPort().equals(mb.getDUCconnectionType()) )
			{
				// First we add the component to the components list of the main
				// object.
				mainObj.addComponent(dics);

				// Then we set the ports to the motherboard
				mb.makeOneDUCportTaken();
			}
			// If the types don't match.
			else
			{
				JOptionPane.showMessageDialog(comp, "The port on the motherboard, " + mb.getDUCconnectionType()
						+ ", does not match the Disc ports, " + dics.getPort() + ".", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		// If there are not available type.
		else
		{
			JOptionPane.showMessageDialog(comp, "There are no available Disc ports left on the machine.", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		}

		return false;
	}



	/**
	 * Checks, and if possible, adds the given graphicsCard to the components array of the given Object. The checks
	 * consists of tests on whether or not the there are available ports on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param GPU
	 *            The GraphicsCard that will be tested and, if possible, added to the object.
	 * @param comp
	 *            This will be the component that message to the user will be shown over.
	 * @return Returns true or false based on whether or not the given Ram object is added to the main object.
	 */
	public static boolean processGPUmatch(Object mainObj, Motherboard mb, GraphicsCard GPU, Component comp)
	{
		boolean availablePort = mb.isGraphicsCardInstalled();

		// Check the availability of ports.
		if ( availablePort == false )
		{
			// Checks the match between the types of disc.
			if ( GPU.getType().equals(mb.getGraphicalPort()) )
			{
				// First we add the component to the components list of the main
				// object.
				mainObj.addComponent(GPU);

				// Then we set the ports to the motherboard
				mb.setGraphicsCard(true);
			}
			// If the types don't match.
			else
			{
				JOptionPane.showMessageDialog(comp, "The port on the motherboard, " + mb.getGraphicalPort()
						+ ", does not match the GPU ports, " + GPU.getType() + ".", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		// If there are not available type.
		else
		{
			JOptionPane.showMessageDialog(comp, "There are no available GPU ports left on the machine.", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		}

		return false;
	}



	/**
	 * Checks, and if possible, adds the given Internal Networks Card to the components array of the given Object. The
	 * checks consists of tests on whether or not the there are available ports on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param nic
	 *            The Internal Networks Card that will be tested and, if possible, added to the object.
	 * @param comp
	 *            This will be the component that message to the user will be shown over.
	 * @return Returns true or false based on whether or not the given NIC object is added to the main object.
	 */
	public static boolean processInternalNICmatch(Object mainObj, Motherboard mb, InternalNetworksCard nic,
			Component comp)
	{
		int availablePort = mb.getPCIPortsAvailable();

		// Check the availability of ports.
		if ( availablePort > 0 )
		{
			// First we add the component to the components list of the main
			// object.
			mainObj.addComponent(nic);

			// Then we set the ports to the motherboard
			mb.makeOnePCIportTaken();
		}
		// If there are not available type.
		else
		{
			JOptionPane.showMessageDialog(comp, "There are no available PCI ports left on the machine.", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		}

		return false;
	}


	/**
	 * Checks, and if possible, adds the given External Networks Card to the components array of the given Object. The
	 * checks consists of tests on whether or not the there are available ports on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param nic
	 *            The External Networks Card that will be tested and, if possible, added to the object.
	 * @param comp
	 *            This will be the component that message to the user will be shown over.
	 * @return Returns true or false based on whether or not the given NIC object is added to the main object.
	 */
	public static boolean processExternalNICmatch(Object mainObj, Motherboard mb, ExternalNetworksCard nic,
			Component comp)
	{
		int availablePort = mb.getUSBPortsAvailable();

		// Check the availability of ports.
		if ( availablePort > 0 )
		{
			// First we add the component to the components list of the main
			// object.
			mainObj.addComponent(nic);

			// FIXME - MUST BE CHECKED

			// Then we set the ports to the motherboard
			mb.makeOneIntLANportTaken();
		}
		// If there are not available type.
		else
		{
			JOptionPane.showMessageDialog(comp, "There are no available USB ports left on the machine.", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		}

		return false;
	}



	// SEARCH FUNCTIONS

	/**
	 * Get specific components by searching for components with the give class type.
	 * 
	 * @return Returns an array of components that match with the given class.
	 * @param componentCounter
	 *            The counter that tells how many components are in the current components array.
	 * @param components
	 *            The current components list.
	 * @throws ObjectNotFoundException
	 *             Throws an exception which states that there were not objects found with the given class.
	 */
	@SuppressWarnings("unchecked")
	public static boolean containsComponent(Class ComponentClass, Object[] components, int componentCounter)
	{
		// boolean to check whether the object is found or not
		boolean objectFound = false;

		// Counter for number of components found
		int tempCounter = 0;

		// Container that will hold all the found components
		Object[] componentsFound = new Object[componentCounter];


		// Searches for components of the given class
		for ( int i = 0; i < componentCounter; i++ )
		{
			/*
			 * If the given components class matches the present components class, it will be added to the container
			 */
			if ( components[i].getClass().equals(ComponentClass) )
			{
				componentsFound[tempCounter] = components[i];

				objectFound = true;
			}
		}

		return objectFound;
	}



	// public static Object handleMBavailabilityChange(Class ComponentClass,
	// Object obj)
	// {
	// return obj;
	// }


	// CHECK FUNCTIONS
	/**
	 * Check function to determine whether or not the the given array contains the given object.
	 * 
	 * @param array
	 *            The array that is to be checked.
	 * @param searchObject
	 *            The object that is to be searched for.
	 */
	public static boolean arrayContains(Object[] array, Object searchObject)
	{
		// Boolean to tell whether or not the given object is found within the
		// given array.
		boolean foundObject = false;


		for ( int i = 0; i < array.length; i++ )
		{
			if ( array[i].equals(searchObject) )
			{
				foundObject = true;

				// Sets i to array length to get out of the loop.
				i = array.length;
			}
		}

		return foundObject;
	}



	/**
	 * Check function to determine whether or not the the given array contains any of the given objects in the
	 * searchObjects array.
	 * 
	 * @param array
	 *            The array that is to be checked.
	 * @param searchObjects
	 *            The objects that is to be searched for.
	 * @return The array of booleans that tells, by way of the index, which object are found.
	 */
	public static boolean[] arrayContains(Object[] array, Object[] searchObjects)
	{
		// Boolean to tell whether or not the given object is found within the
		// given array.
		boolean[] foundObject = new boolean[searchObjects.length];


		for ( int i = 0; i < array.length; i++ )
		{
			// See if the array index is not null
			if ( array[i] != null )
			{
				for ( int j = 0; j < searchObjects.length; j++ )
				{
					// See if the array index is not null
					if ( searchObjects[j] != null )
					{
						if ( array[i].equals(searchObjects[j]) )
						{
							foundObject[j] = true;
						}
					}
				}
			}
		}

		return foundObject;
	}



	/**
	 * Finds and returns all the objects that are connected to the given object with the given connection type. Returns
	 * an array of object with the connected object, if any are found.
	 * 
	 * @param connectedTo
	 *            The object that will be examined for connections to other objects.
	 * @param conType
	 *            The type of connection between the two objects.
	 * @return Returns all the objects connected to the given object with the given connection type
	 */
	public static Object[] connectedToBy(Object connectedTo, String conType)
	{
		// The array that will hold all the matching objects.
		Object[] foundComp = null;

		Connection[] cons = null;

		int index = 0;


		Connection[] netCons = connectedTo.getNetworkConnections();

		Connection[] devCons = connectedTo.getDeviceConnections();


		// Finds the number of overall connections.
		if ( netCons != null )
		{
			index = index + netCons.length;
		}

		if ( devCons != null )
		{
			index = index + devCons.length;
		}


		// The array that will hold all of the objects connections.
		cons = new Connection[index];


		if ( netCons != null )
		{
			// Adding the networkconnections to the array.
			System.arraycopy(netCons, 0, cons, 0, netCons.length);
		}



		if ( devCons != null )
		{
			if ( netCons != null )
			{
				// Adding the deviceconnections to the array.
				System.arraycopy(devCons, 0, cons, netCons.length, devCons.length);
			}
			else
			{
				// Adding the deviceconnections to the array.
				System.arraycopy(devCons, 0, cons, 0, devCons.length);
			}
		}



		// Creating an array with the length of cons.
		foundComp = new Object[cons.length];

		// Matching the connection types to the given conType.
		for ( int i = 0; i < cons.length; i++ )
		{
			if ( cons[i] != null )
			{
				if ( cons[i].getConnectionType().equals(conType) )
				{
					if ( cons[i].getObject1().equals(connectedTo) )
					{
						foundComp[i] = cons[i].getObject2();
					}
					else
					{
						foundComp[i] = cons[i].getObject1();
					}
				}
			}
		}


		// Removes all the empty indexes from the array.
		foundComp = cleanup.cleanObjectArray(foundComp);



		return foundComp;
	}
}
