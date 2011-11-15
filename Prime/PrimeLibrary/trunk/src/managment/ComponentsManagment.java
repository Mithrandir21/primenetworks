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
package managment;


import java.awt.Component;
import java.awt.Point;
import java.util.logging.Level;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import logistical.LibraryLogging;
import logistical.cleanup;
import objects.Hardware;
import objects.Object;
import objects.Software;
import objects.hardwareObjects.CPU;
import objects.hardwareObjects.Discdrive;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.GraphicsCard;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import objects.hardwareObjects.Ram;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WorkareaCanvas;
import connections.Connection;
import connections.ConnectionUtils;
import connections.WidgetExtendedConnection;
import exceptions.ConnectionDoesNotExist;
import exceptions.MotherboardNotFound;
import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;


/**
 * Class that contains different functions that add, remove and replace
 * components from a given array. It is used in the different parts of the
 * program, specially {@link objects.clientObjects.Desktop Desktops},
 * {@link objects.clientObjects.Laptop Laptops}, {@link objects.Servers Servers}
 * and {@link objects.rackUnits.Rack Racks}.
 * 
 * @author Bahram Malaekeh
 * @version 0.2
 */
public class ComponentsManagment
{
	// ADD COMPONENTS

	/**
	 * Function to add component(s) to the the components list.
	 * 
	 * @param NewComponents
	 *            An array of new components.
	 * @param componentCounter
	 *            The counter that tells how many components are in the current
	 *            components array.
	 * @param components
	 *            The current components list.
	 * @throws Exception
	 *             Throws an exception with a message which says that the
	 *             component is already present.
	 */
	public static Object[] addComponents(Object[] NewComponents,
			Object[] components, int componentCounter)
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
		Object[] tempComponents = new Object[componentCounter
				+ numberOfNewComponents];

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
			if ( areFound[i] )
			{
				// A try/catch in case one or more of the given components are
				// found in the given devices components array.
				try
				{
					throw new Exception("The component "
							+ NewComponents[i].getObjectName()
							+ " is already present.");
				}
				catch ( Exception e )
				{
					LibraryLogging.libraryLog
							.logp(Level.SEVERE,
									"ComponentsManagment",
									"addComponents",
									"One or more of the components to be added to a device was already present in the devices components array.");

					if ( Settings.debug )
					{
						e.printStackTrace();
					}
					return components;
				}
			}
		}


		// Adds the old components to the new array
		System.arraycopy(components, 0, tempComponents, 0, componentCounter);


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
	 *             Throws an exception with a message which says that the
	 *             component is already present.
	 */
	public static Object[] addComponent(Object NewComponent, Object[] components)
	{
		// Makes a new components array with the added number of indexes
		Object[] tempComponents = new Object[components.length + 1];

		// The boolean that tells whether or not the object already is in the
		// array
		boolean isFound = arrayContains(components, NewComponent);

		// Checks to see if the was found in the array of objects.
		if ( isFound )
		{
			// A try/catch in case the object is null.
			try
			{
				throw new Exception("The component "
						+ NewComponent.getObjectName() + " is already present.");
			}
			catch ( Exception e )
			{
				LibraryLogging.libraryLog
						.logp(Level.SEVERE,
								"ComponentsManagment",
								"addComponent",
								"The components to be added to a device was already present in the devices components array.");

				if ( Settings.debug )
				{
					e.printStackTrace();
				}
				return components;
			}
		}


		System.arraycopy(components, 0, tempComponents, 0, components.length);


		// Adds the new components at the end of the array of components.
		tempComponents[components.length] = NewComponent;


		return tempComponents;
	}


	/**
	 * Function for replacing a specific given component with a given new
	 * component.
	 * 
	 * @param NewComponent
	 *            The component to replace the previous one.
	 * @param OldComponent
	 *            The component to be replaced.
	 * @param componentCounter
	 *            The counter that tells how many components are in the current
	 *            components array.
	 * @param components
	 *            The current components list.
	 */
	public static Object[] changeComponent(Object NewComponent,
			Object OldComponent, Object[] components, int componentCounter)
	{
		// The boolean array that tells whether or not any of the object already
		// are in the array
		boolean isFound = arrayContains(components, NewComponent);

		// Checks to see if any of the
		if ( isFound )
		{
			// A try/catch incase the object is null.
			try
			{
				throw new Exception("The component "
						+ NewComponent.getObjectName() + " is already present.");
			}
			catch ( Exception e )
			{
				LibraryLogging.libraryLog
						.logp(Level.SEVERE,
								"ComponentsManagment",
								"changeComponent",
								"The new components to replace an older component in a device was already present in the devices components array.");

				if ( Settings.debug )
				{
					e.printStackTrace();
				}
				return components;
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



	// COMPONENTS CHECKS
	/**
	 * This method process all the changes made to the software the object
	 * contains. It first finds the motherboard of the given object and then
	 * calls all the other methods in this class to validate the software for
	 * compatibility.
	 * 
	 * @param obj
	 *            The object that contains the software which will be validated.
	 * @throws MotherboardNotFound
	 */
	public static void processAllChanges(Object obj) throws MotherboardNotFound
	{
		processCPUchanges(obj);

		processDiscDriveChanges(obj);

		// THESE ARE RUN SOMEWHERE ELSE
		// processExternalNICchanges(mb, obj);
		// processInternalNICchanges(mb, obj);

		processGPUchanges(obj);

		processHDDchanges(obj);

		processRAMchanges(obj);

		// Determines the supported connection interface on the device.
		obj.revalidateSupportedConnectionInterfaces();
	}


	/**
	 * Checks compatibility of the any CPU component with the motherboard.
	 * Removes the ones that are not compatible.
	 */
	public static void processCPUchanges(Object obj) throws MotherboardNotFound
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		Motherboard mb = ComponentsManagment.getObjectMotherboard(obj);

		try
		{
			// Gets all the CPU components in the components array.
			Object[] cpus = ArrayManagment.getSpesificComponents(CPU.class,
					components, components.length);

			for ( int i = 0; i < cpus.length; i++ )
			{
				// Gets all the Discdrive components in the components array.
				if ( !mb.getSocket().equals("") && mb.getSocket() != null )
				{
					CPU cpu = (CPU) cpus[i];
					// Checks the socket of the cpu versus the socket on the
					// motherboard
					if ( !cpu.getSocket().equals(mb.getSocket()) )
					{
						removeCPU(obj, cpu);
					}
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Log entry
			LibraryLogging.libraryLog
					.logp(Level.FINEST,
							"ComponentsManagment",
							"processCPUchanges",
							"No CPU components were found in the given Object '"
									+ obj.getObjectName()
									+ "'. This does not indicates an error, only informational.");

			// No need to print stack because the exception is used
			// intentionally.
		}
	}

	/**
	 * Checks compatibility of the any DicsDrive component with the motherboard.
	 * Removes the ones that are not compatible.
	 */
	public static void processDiscDriveChanges(Object obj)
			throws MotherboardNotFound
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		Motherboard mb = ComponentsManagment.getObjectMotherboard(obj);

		try
		{
			// Gets all the Discdrive components in the components array.
			Object[] drives = ArrayManagment.getSpesificComponents(
					Discdrive.class, components, components.length);

			//
			for ( int i = 0; i < drives.length; i++ )
			{
				// If the motherboard actual has a value that can be checked.
				if ( !mb.getDUCconnectionType().equals("")
						&& mb.getDUCconnectionType() != null )
				{
					Discdrive dicsdrive = (Discdrive) drives[i];
					if ( !dicsdrive.getPort().equals(mb.getDUCconnectionType()) )
					{
						removeDiscdrive(obj, dicsdrive);
					}
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Log entry
			LibraryLogging.libraryLog
					.logp(Level.FINEST,
							"ComponentsManagment",
							"processDiscDriveChanges",
							"No Discdrive components were found in the given Object '"
									+ obj.getObjectName()
									+ "'. This does not indicates an error, only informational.");

			// No need to print stack because the exception is used
			// intentionally.
		}
	}


	/**
	 * Checks compatibility of the any GPU component with the motherboard.
	 * Removes the ones that are not compatible.
	 */
	public static void processGPUchanges(Object obj) throws MotherboardNotFound
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		Motherboard mb = ComponentsManagment.getObjectMotherboard(obj);

		try
		{
			// Gets all the GraphicsCard components in the components array.
			Object[] GPUs = ArrayManagment.getSpesificComponents(
					GraphicsCard.class, components, components.length);

			//
			for ( int i = 0; i < GPUs.length; i++ )
			{
				// If the motherboard actual has a value that can be checked.
				if ( !mb.getGraphicalPort().equals("")
						&& mb.getGraphicalPort() != null )
				{
					GraphicsCard gpu = (GraphicsCard) GPUs[i];
					if ( !gpu.getType().equals(mb.getGraphicalPort()) )
					{
						removeGPU(obj, gpu);
					}
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Log entry
			LibraryLogging.libraryLog
					.logp(Level.FINEST,
							"ComponentsManagment",
							"processGPUchanges",
							"No GraphicsCard components were found in the given Object '"
									+ obj.getObjectName()
									+ "'. This does not indicates an error, only informational.");

			// No need to print stack because the exception is used
			// intentionally.
		}
	}


	/**
	 * Checks compatibility of the any HDD component with the motherboard.
	 * Removes the ones that are not compatible.
	 */
	public static void processHDDchanges(Object obj) throws MotherboardNotFound
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		Motherboard mb = ComponentsManagment.getObjectMotherboard(obj);

		try
		{
			// Gets all the HDD components in the components array.
			Object[] HDDs = ArrayManagment.getSpesificComponents(HDD.class,
					components, components.length);

			// If the port to the motherboard is not the same
			for ( int i = 0; i < HDDs.length; i++ )
			{
				// If the motherboard actual has a value that can be checked.
				if ( !mb.getDUCconnectionType().equals("")
						&& mb.getDUCconnectionType() != null )
				{
					HDD hdd = (HDD) HDDs[i];
					if ( !hdd.getPort().equals(mb.getDUCconnectionType()) )
					{
						removeHDD(obj, hdd);
					}
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Log entry
			LibraryLogging.libraryLog
					.logp(Level.FINEST,
							"ComponentsManagment",
							"processHDDchanges",
							"No HDD components were found in the given Object '"
									+ obj.getObjectName()
									+ "'. This does not indicates an error, only informational.");

			// No need to print stack because the exception is used
			// intentionally.
		}
	}


	/**
	 * Checks compatibility of the any RAM component with the motherboard.
	 * Removes the ones that are not compatible.
	 */
	public static void processRAMchanges(Object obj) throws MotherboardNotFound
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		Motherboard mb = ComponentsManagment.getObjectMotherboard(obj);

		try
		{
			// Gets all the Ram components in the components array.
			Object[] RAMs = ArrayManagment.getSpesificComponents(Ram.class,
					components, components.length);

			//
			for ( int i = 0; i < RAMs.length; i++ )
			{
				// If the motherboard actual has a value that can be checked.
				if ( !mb.getRAMtype().equals("") && mb.getRAMtype() != null )
				{
					Ram RAM = (Ram) RAMs[i];
					// If the port to the motherboard is not the same
					if ( !RAM.getPort().equals(mb.getRAMtype()) )
					{
						removeRAM(obj, RAM);
					}
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Log entry
			LibraryLogging.libraryLog
					.logp(Level.FINEST,
							"ComponentsManagment",
							"processRAMchanges",
							"No Ram components were found in the given Object '"
									+ obj.getObjectName()
									+ "'. This does not indicates an error, only informational.");

			// No need to print stack because the exception is used
			// intentionally.
		}
	}





	/**
	 * Checks, and if possible, adds the given cpu to the components array of
	 * the given Object. The checks consists of tests on whether or not the
	 * there are available sockets on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param cpu
	 *            The CPU that will be tested and, if possible, added to the
	 *            object.
	 * @param comp
	 *            This will be the component that message to the user will be
	 *            shown over.
	 * @return Returns true or false based on whether or not the given CPU
	 *         object is added to the main object.
	 * @throws MotherboardNotFound
	 */
	public static void processCPUmatch(Object mainObj, CPU cpu, Component comp)
			throws MotherboardNotFound
	{
		Motherboard mb = ComponentsManagment.getObjectMotherboard(mainObj);

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
				JOptionPane.showMessageDialog(
						comp,
						"The socket on the motherboard, " + mb.getSocket()
								+ ", does not match the CPU socket, "
								+ cpu.getSocket() + ".", "Info",
						JOptionPane.INFORMATION_MESSAGE);

				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.FINE,
								"ComponentsManagment",
								"processCPUmatch",
								"No CPU socket on the given Motherboard on the device '"
										+ mainObj.getObjectName()
										+ "' matches the socket on the given CPU. This indicates a user error, not a system error.");
			}
		}
		// If there are not available sockets.
		else
		{
			JOptionPane.showMessageDialog(comp,
					"There are no available CPU sockets left on the machine.",
					"Info", JOptionPane.INFORMATION_MESSAGE);// Log entry

			LibraryLogging.libraryLog
					.logp(Level.FINE,
							"ComponentsManagment",
							"processCPUmatch",
							"No available CPU socket on the given Motherboard on the device '"
									+ mainObj.getObjectName()
									+ "'. This indicates a user error, not a system error.");
		}
	}



	/**
	 * Checks, and if possible, adds the given ram to the components array of
	 * the given Object. The checks consists of tests on whether or not the
	 * there are available ports on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param ram
	 *            The RAM that will be tested and, if possible, added to the
	 *            object.
	 * @param comp
	 *            This will be the component that message to the user will be
	 *            shown over.
	 * @return Returns true or false based on whether or not the given Ram
	 *         object is added to the main object.
	 */
	public static void processRAMmatch(Object mainObj, Ram ram, Component comp)
			throws MotherboardNotFound
	{
		Motherboard mb = ComponentsManagment.getObjectMotherboard(mainObj);

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
				JOptionPane.showMessageDialog(
						comp,
						"The port on the motherboard, " + mb.getRAMtype()
								+ ", does not match the RAM ports, "
								+ ram.getPort() + ".", "Info",
						JOptionPane.INFORMATION_MESSAGE);

				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.FINE,
								"ComponentsManagment",
								"processRAMmatch",
								"No RAM port on the given Motherboard on the device '"
										+ mainObj.getObjectName()
										+ "' matches the port on the given Ram. This indicates a user error, not a system error.");
			}
		}
		// If there are not available type.
		else
		{
			JOptionPane.showMessageDialog(comp,
					"There are no available RAM ports left on the machine.",
					"Info", JOptionPane.INFORMATION_MESSAGE);

			LibraryLogging.libraryLog
					.logp(Level.FINE,
							"ComponentsManagment",
							"processRAMmatch",
							"No available RAM port on the given Motherboard on the device '"
									+ mainObj.getObjectName()
									+ "'. This indicates a user error, not a system error.");
		}
	}



	/**
	 * Checks, and if possible, adds the given harddisc to the components array
	 * of the given Object. The checks consists of tests on whether or not the
	 * there are available ports on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param hdd
	 *            The HDD that will be tested and, if possible, added to the
	 *            object.
	 * @param comp
	 *            This will be the component that message to the user will be
	 *            shown over.
	 * @return Returns true or false based on whether or not the given HDD
	 *         object is added to the main object.
	 */
	public static void processHDDmatch(Object mainObj, HDD hdd, Component comp)
			throws MotherboardNotFound
	{
		Motherboard mb = ComponentsManagment.getObjectMotherboard(mainObj);

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
				JOptionPane.showMessageDialog(
						comp,
						"The port on the motherboard, "
								+ mb.getDUCconnectionType()
								+ ", does not match the HDD ports, "
								+ hdd.getPort() + ".", "Info",
						JOptionPane.INFORMATION_MESSAGE);

				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.FINE,
								"ComponentsManagment",
								"processHDDmatch",
								"No DUC port on the given Motherboard on the device '"
										+ mainObj.getObjectName()
										+ "' matches the port on the given HDD. This indicates a user error, not a system error.");
			}
		}
		// If there are not available type.
		else
		{
			JOptionPane.showMessageDialog(comp,
					"There are no available HDD ports left on the machine.",
					"Info", JOptionPane.INFORMATION_MESSAGE);

			LibraryLogging.libraryLog
					.logp(Level.FINE,
							"ComponentsManagment",
							"processHDDmatch",
							"No available DUC port on the given Motherboard on the device '"
									+ mainObj.getObjectName()
									+ "'. This indicates a user error, not a system error.");
		}
	}



	/**
	 * Checks, and if possible, adds the given discdrive to the components array
	 * of the given Object. The checks consists of tests on whether or not the
	 * there are available ports on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param dics
	 *            The Discdrive that will be tested and, if possible, added to
	 *            the object.
	 * @param comp
	 *            This will be the component that message to the user will be
	 *            shown over.
	 * @return Returns true or false based on whether or not the given Discdrive
	 *         object is added to the main object.
	 */
	public static void processDiscDrivematch(Object mainObj, Discdrive dics,
			Component comp) throws MotherboardNotFound
	{
		Motherboard mb = ComponentsManagment.getObjectMotherboard(mainObj);

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
				JOptionPane.showMessageDialog(
						comp,
						"The port on the motherboard, "
								+ mb.getDUCconnectionType()
								+ ", does not match the Disc ports, "
								+ dics.getPort() + ".", "Info",
						JOptionPane.INFORMATION_MESSAGE);

				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.FINE,
								"ComponentsManagment",
								"processDiscDrivematch",
								"No DUC port on the given Motherboard on the device '"
										+ mainObj.getObjectName()
										+ "' matches the port on the given Discdrive. This indicates a user error, not a system error.");
			}
		}
		// If there are not available type.
		else
		{
			JOptionPane.showMessageDialog(comp,
					"There are no available Disc ports left on the machine.",
					"Info", JOptionPane.INFORMATION_MESSAGE);

			LibraryLogging.libraryLog
					.logp(Level.FINE,
							"ComponentsManagment",
							"processDiscDrivematch",
							"No available DUC port on the given Motherboard on the device '"
									+ mainObj.getObjectName()
									+ "'. This indicates a user error, not a system error.");
		}
	}



	/**
	 * Checks, and if possible, adds the given graphicsCard to the components
	 * array of the given Object. The checks consists of tests on whether or not
	 * the there are available ports on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param GPU
	 *            The GraphicsCard that will be tested and, if possible, added
	 *            to the object.
	 * @param comp
	 *            This will be the component that message to the user will be
	 *            shown over.
	 * @return Returns true or false based on whether or not the given Ram
	 *         object is added to the main object.
	 */
	public static void processGPUmatch(Object mainObj, GraphicsCard GPU,
			Component comp) throws MotherboardNotFound
	{
		Motherboard mb = ComponentsManagment.getObjectMotherboard(mainObj);

		boolean availablePort = mb.isGraphicsCardInstalled();

		// Check the availability of ports.
		if ( !(availablePort) )
		{
			// Checks the match between the types of disc.
			if ( GPU.getType().equals(mb.getGraphicalPort()) )
			{
				// First we add the component to the components list of the main
				// object.
				mainObj.addComponent(GPU);

				// Then we set the ports to the motherboard
				mb.setGraphicsCardInstalled(true);
			}
			// If the types don't match.
			else
			{
				JOptionPane.showMessageDialog(
						comp,
						"The port on the motherboard, " + mb.getGraphicalPort()
								+ ", does not match the GPU ports, "
								+ GPU.getType() + ".", "Info",
						JOptionPane.INFORMATION_MESSAGE);

				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.FINE,
								"ComponentsManagment",
								"processGPUmatch",
								"No GPU port on the given Motherboard on the device '"
										+ mainObj.getObjectName()
										+ "' matches the port on the given GraphicsCard. This indicates a user error, not a system error.");
			}
		}
		// If there are not available type.
		else
		{
			JOptionPane.showMessageDialog(comp,
					"There are no available GPU ports left on the machine.",
					"Info", JOptionPane.INFORMATION_MESSAGE);

			LibraryLogging.libraryLog
					.logp(Level.FINE,
							"ComponentsManagment",
							"processGPUmatch",
							"No available GPU port on the given Motherboard on the device '"
									+ mainObj.getObjectName()
									+ "'. This indicates a user error, not a system error.");
		}
	}



	/**
	 * Checks, and if possible, adds the given Internal Networks Card to the
	 * components array of the given Object. The checks consists of tests on
	 * whether or not the there are available ports on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param nic
	 *            The Internal Networks Card that will be tested and, if
	 *            possible, added to the object.
	 * @param comp
	 *            This will be the component that message to the user will be
	 *            shown over.
	 * @return Returns true or false based on whether or not the given NIC
	 *         object is added to the main object.
	 */
	public static void processInternalNICmatch(Object mainObj,
			InternalNetworksCard nic, Component comp)
			throws MotherboardNotFound
	{
		Motherboard mb = ComponentsManagment.getObjectMotherboard(mainObj);

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
			JOptionPane.showMessageDialog(comp,
					"There are no available PCI ports left on the machine.",
					"Info", JOptionPane.INFORMATION_MESSAGE);

			LibraryLogging.libraryLog
					.logp(Level.FINE,
							"ComponentsManagment",
							"processInternalNICmatch",
							"No available PCI port on the given Motherboard on the device '"
									+ mainObj.getObjectName()
									+ "'. This indicates a user error, not a system error.");
		}
	}


	/**
	 * Checks, and if possible, adds the given External Networks Card to the
	 * components array of the given Object. The checks consists of tests on
	 * whether or not the there are available ports on the motherboard.
	 * 
	 * @param mainObj
	 *            The main object. (Like a desktop or a server).
	 * @param mb
	 *            The objects motherboard.
	 * @param nic
	 *            The External Networks Card that will be tested and, if
	 *            possible, added to the object.
	 * @param comp
	 *            This will be the component that message to the user will be
	 *            shown over.
	 * @return Returns true or false based on whether or not the given NIC
	 *         object is added to the main object.
	 */
	public static void processExternalNICmatch(Object mainObj,
			ExternalNetworksCard nic, Component comp)
			throws MotherboardNotFound
	{
		Motherboard mb = ComponentsManagment.getObjectMotherboard(mainObj);

		int availablePort = mb.getUSBPortsAvailable();

		// Check the availability of ports.
		if ( availablePort > 0 )
		{
			// First we add the component to the components list of the main
			// object.
			mainObj.addComponent(nic);

			// Then we set the ports to the motherboard
			mb.makeOneUSBportTaken();
		}
		// If there are not available type.
		else
		{
			JOptionPane.showMessageDialog(comp,
					"There are no available USB ports left on the machine.",
					"Info", JOptionPane.INFORMATION_MESSAGE);

			LibraryLogging.libraryLog
					.logp(Level.FINE,
							"ComponentsManagment",
							"processExternalNICmatch",
							"No available USB port on the given Motherboard on the device '"
									+ mainObj.getObjectName()
									+ "'. This indicates a user error, not a system error.");
		}
	}


	// REMOVALE FUNCTIONS



	/**
	 * Function to remove an array of components from the array of components.
	 * 
	 * @param ToBeRemoved
	 *            Component to be removed.
	 * @param componentCounter
	 *            The counter that tells how many components are in the current
	 *            components array.
	 * @param components
	 *            The current components list.
	 * @throws ObjectNotFoundInArrayException
	 *             Throws an exception which states that one or more of the
	 *             object that to be removed do not exist in the components
	 *             object they are trying to be removed from.
	 */
	public static Object[] removeComponents(Object[] ToBeRemoved,
			Object[] components, int componentCounter)
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
			if ( !(objectFound[i]) )
			{
				ObjectNotFoundInArrayException exception = new ObjectNotFoundInArrayException(
						"Object was not found, hence cannot "
								+ "be deleted. Contact systemadminstrator.",
						ToBeRemoved[i]);

				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.SEVERE,
								"ComponentsManagment",
								"removeComponents",
								"No or more of the components to be removed were not found in the given array of components.");

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
	public static Object[] removeComponent(Object ToBeRemoved,
			Object[] components, int componentCounter)
	{
		// Goes through all the components and removes the one(s) to be removed
		for ( int i = 0; i < componentCounter; i++ )
		{
			if ( components[i] != null )
			{
				if ( components[i].equals(ToBeRemoved) )
				{
					components[i] = null;

					// Log entry
					LibraryLogging.libraryLog
							.logp(Level.FINER,
									"ComponentsManagment",
									"removeComponent",
									"The given component has been removed from the given array of components. Informational only.");
				}
			}
		}


		// Cleans the array of any null pointers
		components = cleanup.cleanObjectArray(components);

		return components;
	}




	/**
	 * This method will remove any component with the given class from the
	 * component list of the this classes object if the given Variable does not
	 * match the given newVariable.
	 * 
	 * @param componentClass
	 *            The class of the components that might be removed.
	 * @param variable
	 *            The variable on the motherboard. Like the socket or GPU port.
	 * @param newVariable
	 *            The editor variable that will be checked for differences.
	 * @param mainObj
	 *            The object that the objects with the given class will be
	 *            removed from.
	 */
	public static void removeComponentFromObject(Class<?> componentClass,
			String variable, String newVariable, Object mainObj)
	{
		if ( !variable.equals(newVariable) )
		{
			if ( ComponentsManagment.containsComponent(componentClass,
					mainObj.getComponents(), mainObj.getComponents().length) )
			{

				boolean objContains = true;

				Object[] returned = null;

				try
				{
					// Find the components with the given class on a motherboard
					returned = ArrayManagment.getSpesificComponents(
							componentClass, mainObj.getComponents(),
							mainObj.getComponents().length);
				}
				catch ( ObjectNotFoundException ex )
				{
					// Log entry
					LibraryLogging.libraryLog
							.log(Level.FINEST,
									"No components were found of the given class type, '"
											+ componentClass
											+ "', in the given Object, '"
											+ mainObj.getObjectName()
											+ "'. This does not indicate an error, only informational.");

					objContains = false;
				}


				if ( objContains )
				{
					try
					{
						mainObj.setAllComponents(ComponentsManagment
								.removeComponents(returned,
										mainObj.getComponents(),
										mainObj.getComponents().length));
					}
					catch ( ObjectNotFoundInArrayException e )
					{
						// Log entry
						LibraryLogging.libraryLog
								.logp(Level.SEVERE,
										"ComponentsManagment",
										"removeComponentFromObject",
										"The components with the given class, '"
												+ componentClass
												+ "', found in the given Object, '"
												+ mainObj.getObjectName()
												+ "', were not found that objects components array.");

						if ( Settings.debug )
						{
							e.printStackTrace();
						}
					}
				}
			}
		}
	}


	/**
	 * This method will determine, by way of {@link Object} subclass, which
	 * removal function to call for removal of the given component
	 * {@link Object} from the given {@link Object}.
	 * 
	 * @throws MotherboardNotFound
	 */
	public static boolean removeComponent(WorkareaCanvas canvas, Object obj,
			Object component) throws MotherboardNotFound
	{
		if ( component instanceof GraphicsCard )
		{
			return removeGPU(obj, (GraphicsCard) component);
		}
		else if ( component instanceof CPU )
		{
			return removeCPU(obj, (CPU) component);
		}
		else if ( component instanceof HDD )
		{
			return removeHDD(obj, (HDD) component);
		}
		else if ( component instanceof Ram )
		{
			return removeRAM(obj, (Ram) component);
		}
		else if ( component instanceof Discdrive )
		{
			return removeDiscdrive(obj, (Discdrive) component);
		}
		else if ( component instanceof InternalNetworksCard )
		{
			return removeInternalNIC(canvas, obj,
					(InternalNetworksCard) component);
		}
		else if ( component instanceof ExternalNetworksCard )
		{
			return removeExternalNIC(canvas, obj,
					(ExternalNetworksCard) component);
		}


		return false;
	}



	/**
	 * This function attempts to remove the given {@link GraphicsCard} object
	 * from the components within the given {@link Object}.
	 * 
	 * @return True or false is returned depending on whether the given
	 *         component is removed.
	 * @throws MotherboardNotFound
	 */
	public static boolean removeGPU(Object obj, GraphicsCard gpu)
			throws MotherboardNotFound
	{
		// Attempts to remove the given object from the components array
		Object[] remainingComponents = ComponentsManagment.removeComponent(gpu,
				obj.getComponents(), obj.getComponents().length);

		// Since the components cannot be empty, because everything has a
		// Motherboard, this if is always valid.
		if ( remainingComponents != null && remainingComponents.length != 0 )
		{
			// Gets the object motherboard
			Motherboard mb = getObjectMotherboard(obj);

			if ( mb != null )
			{
				mb.setGraphicsCardInstalled(false);
			}

			// Sets the remaining components as the objects components
			obj.setAllComponents(remainingComponents);

			return true;
		}

		return false;
	}


	/**
	 * This function attempts to remove the given {@link CPU} object from the
	 * components within the given {@link Object}.
	 * 
	 * @return True or false is returned depending on whether the given
	 *         component is removed.
	 * @throws MotherboardNotFound
	 */
	public static boolean removeCPU(Object obj, CPU cpu)
			throws MotherboardNotFound
	{
		// Attempts to remove the given object from the components array
		Object[] remainingComponents = ComponentsManagment.removeComponent(cpu,
				obj.getComponents(), obj.getComponents().length);

		// Since the components cannot be empty, because everything has a
		// Motherboard, this if is always valid.
		if ( remainingComponents != null && remainingComponents.length != 0 )
		{
			// Gets the object motherboard
			Motherboard mb = getObjectMotherboard(obj);

			if ( mb != null )
			{
				mb.makeOneCPUportAvailable();
			}

			// Sets the remaining components as the objects components
			obj.setAllComponents(remainingComponents);

			return true;
		}

		return false;
	}


	/**
	 * This function attempts to remove the given {@link HDD} object from the
	 * components within the given {@link Object}.
	 * 
	 * @return True or false is returned depending on whether the given
	 *         component is removed.
	 * @throws MotherboardNotFound
	 */
	public static boolean removeHDD(Object obj, HDD hdd)
			throws MotherboardNotFound
	{
		// Attempts to remove the given object from the components array
		Object[] remainingComponents = ComponentsManagment.removeComponent(hdd,
				obj.getComponents(), obj.getComponents().length);

		// Since the components cannot be empty, because everything has a
		// Motherboard, this if is always valid.
		if ( remainingComponents != null && remainingComponents.length != 0 )
		{
			// Gets the object motherboard
			Motherboard mb = getObjectMotherboard(obj);

			if ( mb != null )
			{
				mb.makeOneDUCportAvailable();
			}

			// Sets the remaining components as the objects components
			obj.setAllComponents(remainingComponents);

			return true;
		}

		return false;
	}


	/**
	 * This function attempts to remove the given {@link Discdrive} object from
	 * the components within the given {@link Object}.
	 * 
	 * @return True or false is returned depending on whether the given
	 *         component is removed.
	 * @throws MotherboardNotFound
	 */
	public static boolean removeDiscdrive(Object obj, Discdrive disc)
			throws MotherboardNotFound
	{
		// Attempts to remove the given object from the components array
		Object[] remainingComponents = ComponentsManagment.removeComponent(
				disc, obj.getComponents(), obj.getComponents().length);

		// Since the components cannot be empty, because everything has a
		// Motherboard, this if is always valid.
		if ( remainingComponents != null && remainingComponents.length != 0 )
		{
			// Gets the object motherboard
			Motherboard mb = getObjectMotherboard(obj);

			if ( mb != null )
			{
				mb.makeOneDUCportAvailable();
			}

			// Sets the remaining components as the objects components
			obj.setAllComponents(remainingComponents);

			return true;
		}

		return false;
	}


	/**
	 * This function attempts to remove the given {@link Ram} object from the
	 * components within the given {@link Object}.
	 * 
	 * @return True or false is returned depending on whether the given
	 *         component is removed.
	 * @throws MotherboardNotFound
	 */
	public static boolean removeRAM(Object obj, Ram ram)
			throws MotherboardNotFound
	{
		// Attempts to remove the given object from the components array
		Object[] remainingComponents = ComponentsManagment.removeComponent(ram,
				obj.getComponents(), obj.getComponents().length);

		// Since the components cannot be empty, because everything has a
		// Motherboard, this if is always valid.
		if ( remainingComponents != null && remainingComponents.length != 0 )
		{
			// Gets the object motherboard
			Motherboard mb = getObjectMotherboard(obj);

			if ( mb != null )
			{
				mb.makeOneRAMportAvailable();
			}

			// Sets the remaining components as the objects components
			obj.setAllComponents(remainingComponents);

			return true;
		}

		return false;
	}




	/**
	 * This function removes the given {@link InternalNetworksCard} from the
	 * array of hardware components in the given {@link Object}.
	 * It also removes the connection, both on the given {@link WorkareaCanvas}
	 * and in the {@link Object}, that the {@link InternalNetworksCard}
	 * contains.
	 * 
	 * @return True or false is returned depending on whether the given
	 *         component is removed.
	 * @throws MotherboardNotFound
	 */
	public static boolean removeInternalNIC(WorkareaCanvas canvas, Object obj,
			InternalNetworksCard nic) throws MotherboardNotFound
	{
		if ( canvas != null )
		{
			try
			{
				// Attempts to remove the connection from the NIC, if any.
				ConnectionManagment.removeConnectionFromInternalNIC(canvas,
						obj, nic);
			}
			catch ( ConnectionDoesNotExist e1 )
			{
				// DOES NOTHING
			}
		}

		// Attempts to remove the given object from the components array
		Object[] remainingComponents = ComponentsManagment.removeComponent(nic,
				obj.getComponents(), obj.getComponents().length);

		// Since the components cannot be empty, because everything has a
		// Motherboard, this if is always valid.
		if ( remainingComponents != null && remainingComponents.length != 0 )
		{
			// Gets the object motherboard
			Motherboard mb = getObjectMotherboard(obj);

			if ( mb != null )
			{
				mb.makeOnePCIportAvailable();
			}

			// Sets the remaining components as the objects components
			obj.setAllComponents(remainingComponents);

			if ( canvas != null )
			{
				canvas.setSaved(false);
				canvas.setChanged(true);
			}

			return true;
		}

		return false;
	}



	/**
	 * This function removes the given {@link ExternalNetworksCard} from the
	 * array of hardware components in the given {@link Object}.
	 * It also removes the connection, both on the given {@link WorkareaCanvas}
	 * and in the {@link Object}, that the {@link ExternalNetworksCard}
	 * contains.
	 * 
	 * @throws MotherboardNotFound
	 */
	public static boolean removeExternalNIC(WorkareaCanvas canvas, Object obj,
			ExternalNetworksCard nic) throws MotherboardNotFound
	{
		if ( canvas != null )
		{
			try
			{
				// Attempts to remove the connection from the NIC, if any.
				ConnectionManagment.removeConnectionFromExternalNIC(canvas,
						obj, nic);
			}
			catch ( ConnectionDoesNotExist e1 )
			{
				// Does nothing
			}
		}

		// Attempts to remove the given object from the components array
		Object[] remainingComponents = ComponentsManagment.removeComponent(nic,
				obj.getComponents(), obj.getComponents().length);

		// Since the components cannot be empty, because everything has a
		// Motherboard, this if is always valid.
		if ( remainingComponents != null && remainingComponents.length != 0 )
		{
			// Gets the object motherboard
			Motherboard mb = getObjectMotherboard(obj);

			if ( mb != null )
			{
				mb.makeOneUSBportAvailable();
			}

			// Sets the remaining components as the objects components
			obj.setAllComponents(remainingComponents);

			if ( canvas != null )
			{
				canvas.setSaved(false);
				canvas.setChanged(true);
			}

			return true;
		}

		return false;

	}



	// PORTS FUNCTION


	/**
	 * Processes the PCI settings with the InternalNetworksCards objects.
	 */
	public static void PCIportsValidation(Object mainObj, Motherboard mbObj,
			JComboBox PCIslots)
	{
		if ( mbObj.getMaxPCIs() > Integer.parseInt(PCIslots.getSelectedItem()
				.toString()) )
		{
			Object[] comp = null;
			try
			{
				// Gets all the InternalNetworksCards from the objects
				// components array.
				comp = ArrayManagment.getSpesificComponents(
						InternalNetworksCard.class, mainObj.getComponents(),
						mainObj.getComponents().length);

				// Removes all the InternalNetworksCards from the objects
				// components array.
				mainObj.setAllComponents(ComponentsManagment.removeComponents(
						comp, mainObj.getComponents(),
						mainObj.getComponents().length));

			}
			catch ( ObjectNotFoundException e )
			{
				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.FINEST,
								"ComponentsManagment",
								"PCIportsValidation",
								"No InternalNetworksCards components were found in the given Object '"
										+ mainObj.getObjectName()
										+ "'. This does not indicate an error, only informational.");

				// No need to print stack because the exception is used
				// intentionally.
			}
			catch ( ObjectNotFoundInArrayException e )
			{
				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.SEVERE,
								"ComponentsManagment",
								"PCIportsValidation",
								"The InternalNetworksCards found in the given Object, '"
										+ mainObj.getObjectName()
										+ "', were not found that objects components array.");

				if ( Settings.debug )
				{
					e.printStackTrace();
				}
			}


			mbObj.setMaxPCIs(Integer.parseInt(PCIslots.getSelectedItem()
					.toString()));
			mbObj.setPCIPortsAvailable(mbObj.getMaxPCIs());

			// If there are any components found
			if ( comp != null )
			{
				// The number of components there are room for.
				int counter = mbObj.getMaxPCIs();

				// All the components of the main object(without the
				// ExternalNetworksCards).
				Object[] mainComp = mainObj.getComponents();

				for ( int i = 0; i < counter; i++ )
				{
					// If i is smaller then the length of the comp array.
					if ( i < comp.length )
					{
						mainComp = ComponentsManagment.addComponent(comp[i],
								mainComp);
						mbObj.makeOnePCIportTaken();
					}
				}
				mainObj.setAllComponents(mainComp);
			}
		}
		else
		{
			// The number of taken PCI ports(The number of ports - the number of
			// ports available)
			int takenPorts = mbObj.getMaxPCIs() - mbObj.getPCIPortsAvailable();

			// Sets the max PCI ports
			mbObj.setMaxPCIs(Integer.parseInt(PCIslots.getSelectedItem()
					.toString()));
			mbObj.setPCIPortsAvailable(mbObj.getMaxPCIs() - takenPorts);
		}
	}



	/**
	 * Processes the RAM settings with the RAM objects.
	 */
	public static void RAMportsValidation(Object mainObj, Motherboard mbObj,
			JComboBox RAMslots)
	{

		if ( mbObj.getMaxRAMs() > Integer.parseInt(RAMslots.getSelectedItem()
				.toString()) )
		{
			Object[] comp = null;
			try
			{
				// Gets all the RAM from the objects components array.
				comp = ArrayManagment
						.getSpesificComponents(Ram.class,
								mainObj.getComponents(),
								mainObj.getComponents().length);

				// Removes all the RAM from the objects components array.
				mainObj.setAllComponents(ComponentsManagment.removeComponents(
						comp, mainObj.getComponents(),
						mainObj.getComponents().length));

			}
			catch ( ObjectNotFoundException e )
			{
				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.FINEST,
								"ComponentsManagment",
								"RAMportsValidation",
								"No RAM components were found in the given Object '"
										+ mainObj.getObjectName()
										+ "'. This does not indicate an error, only informational.");

				// No need to print stack because the exception is used
				// intentionally.
			}
			catch ( ObjectNotFoundInArrayException e )
			{
				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.SEVERE,
								"ComponentsManagment",
								"RAMportsValidation",
								"The Ram components found in the given Object, '"
										+ mainObj.getObjectName()
										+ "', were not found that objects components array.");

				if ( Settings.debug )
				{
					e.printStackTrace();
				}
			}


			mbObj.setMaxRAMs(Integer.parseInt(RAMslots.getSelectedItem()
					.toString()));
			mbObj.setRAMPortsAvailable(mbObj.getMaxRAMs());

			if ( comp != null )
			{
				// The number of components there are room for.
				int counter = mbObj.getMaxRAMs();
				// All the components of the main object(without the RAM).
				Object[] mainComp = mainObj.getComponents();
				for ( int i = 0; i < counter; i++ )
				{
					// If i is smaller then the length of the comp array.
					if ( i < comp.length )
					{
						mainComp = ComponentsManagment.addComponent(comp[i],
								mainComp);
						mbObj.makeOneRAMportTaken();
					}
				}
				mainObj.setAllComponents(mainComp);
			}
		}
		else
		{
			// The number of taken RAM ports(The number of ports - the number of
			// ports available)
			int takenPorts = mbObj.getMaxRAMs() - mbObj.getDUCPortsAvailable();

			// Sets the max RAM ports
			mbObj.setMaxRAMs(Integer.parseInt(RAMslots.getSelectedItem()
					.toString()));
			mbObj.setRAMPortsAvailable(mbObj.getMaxRAMs() - takenPorts);
		}
	}



	/**
	 * Processes the DUC settings with the {@link HDD} and {@link Discdrive}
	 * objects.
	 */
	public static void DUCportsValidation(Object mainObj, Motherboard mbObj,
			JComboBox DUCports)
	{
		if ( mbObj.getMaxDUCs() > Integer.parseInt(DUCports.getSelectedItem()
				.toString()) )
		{
			Object[] comp = null;

			Object[] compHDD = null;

			Object[] compDisc = null;

			try
			{
				// Gets all the HDDs from the objects components array.
				compHDD = ArrayManagment
						.getSpesificComponents(HDD.class,
								mainObj.getComponents(),
								mainObj.getComponents().length);


				// Gets all the Discdrives from the objects components array.
				compDisc = ArrayManagment.getSpesificComponents(
						Discdrive.class, mainObj.getComponents(),
						mainObj.getComponents().length);
			}
			catch ( ObjectNotFoundException e )
			{
				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.FINEST,
								"ComponentsManagment",
								"DUCportsValidation",
								"No HDD or DiscDrive components were found in the given Object '"
										+ mainObj.getObjectName()
										+ "'. This does not indicate an error, only informational.");

				// No need to print stack because the exception is used
				// intentionally.
			}


			int compLenght = 0;

			if ( compHDD != null )
			{
				compLenght = compLenght + compHDD.length;
			}

			if ( compDisc != null )
			{
				compLenght = compLenght + compDisc.length;
			}

			comp = new Object[compLenght];


			// The different counters for the different arrays of
			// components.
			int hddCount = 0;
			int discCount = 0;

			// The tick/tack boolean.
			boolean tick = true;


			for ( int i = 0; i < comp.length; i++ )
			{
				// Tries to add the hdd first.
				if ( tick )
				{

					if ( compHDD != null && hddCount < compHDD.length
							&& compHDD[hddCount] != null )
					{
						comp[i] = compHDD[hddCount];
						hddCount++;
						tick = false;
					}
					else
					{
						comp[i] = compDisc[discCount];
						discCount++;
						tick = false;
					}
				}
				// Tack
				else
				{
					if ( compDisc != null && discCount < compDisc.length
							&& compDisc[discCount] != null )
					{
						comp[i] = compDisc[discCount];
						discCount++;
						tick = true;
					}
					else
					{
						comp[i] = compHDD[hddCount];
						hddCount++;
						tick = true;
					}
				}
			}

			// Removes all the components from the objects components array.
			try
			{
				mainObj.setAllComponents(ComponentsManagment.removeComponents(
						comp, mainObj.getComponents(),
						mainObj.getComponents().length));
			}
			catch ( ObjectNotFoundInArrayException e )
			{
				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.SEVERE,
								"ComponentsManagment",
								"DUCportsValidation",
								"The components found in the given Object, '"
										+ mainObj.getObjectName()
										+ "', were not found that objects components array.");

				if ( Settings.debug )
				{
					e.printStackTrace();
				}
			}



			mbObj.setMaxDUCs(Integer.parseInt(DUCports.getSelectedItem()
					.toString()));
			mbObj.setDUCPortsAvailable(mbObj.getMaxDUCs());

			if ( comp != null )
			{
				// The number of components there are room for.
				int counter = mbObj.getMaxDUCs();

				// All the components of the main object(without the CPUs).
				Object[] mainComp = mainObj.getComponents();

				for ( int i = 0; i < counter; i++ )
				{
					// If i is smaller then the length of the comp array.
					if ( i < comp.length )
					{
						mainComp = ComponentsManagment.addComponent(comp[i],
								mainComp);
						mbObj.makeOneDUCportTaken();
					}
				}
				mainObj.setAllComponents(mainComp);
			}
		}
		else
		{
			// The number of taken DUC ports(The number of ports - the number of
			// ports available)
			int takenPorts = mbObj.getMaxDUCs() - mbObj.getDUCPortsAvailable();

			// Sets the max DUC ports
			mbObj.setMaxDUCs(Integer.parseInt(DUCports.getSelectedItem()
					.toString()));
			mbObj.setDUCPortsAvailable(mbObj.getMaxDUCs() - takenPorts);
		}
	}


	/**
	 * Processes the USB settings with the ExternalNetworksCard objects.
	 */
	public static void USBportsValidation(Object mainObj, Motherboard mbObj,
			JComboBox USBports, WorkareaCanvas canvas)
	{
		USBportsValidation(mainObj, mbObj,
				Integer.parseInt(USBports.getSelectedItem().toString()), canvas);
	}



	/**
	 * Processes the USB settings with the ExternalNetworksCard objects.
	 */
	public static void USBportsValidation(Object mainObj, Motherboard mbObj,
			int USBports, WorkareaCanvas canvas)
	{
		if ( mainObj != null && mbObj != null && canvas != null )
		{
			if ( mbObj.getMaxUSBs() > USBports )
			{
				int newMaxUSBports = USBports;

				Object[] externalNICs = new Object[0];

				try
				{
					// Gets all the ExternalNetworksCard from the objects
					// components array.
					externalNICs = ArrayManagment.getSpesificComponents(
							ExternalNetworksCard.class,
							mainObj.getComponents(),
							mainObj.getComponents().length);
				}
				catch ( ObjectNotFoundException e )
				{
					// Log entry
					LibraryLogging.libraryLog
							.logp(Level.FINEST,
									"ComponentsManagment",
									"USBportsValidation",
									"No ExternalNetworksCard components were found in the given Object '"
											+ mainObj.getObjectName()
											+ "'. This does not indicate an error, only informational.");

					// No need to print stack because the exception is used
					// intentionally.
				}


				// Gets all the connections, networked and devices
				Connection[] allConnections = mainObj.getAllConnections();

				// ------------------------------------------------------------------------------
				// If the number of USB ports is bigger then the number of
				// externalNICS, which would mean that there is room
				// for connected USB devices.
				if ( newMaxUSBports > externalNICs.length )
				{
					// The connections array that will hold all the connections
					// that are with USB
					Connection[] USBconnections = ConnectionManagment
							.connectedToBy(mainObj, ConnectionUtils.USB);


					// The number of USB ports left after External NICs are
					// added.
					int leftUSBportsAfterExternalNICs = newMaxUSBports
							- externalNICs.length;

					if ( USBconnections != null )
					{
						// If the number of available USB ports is less then the
						// number of USB devices
						if ( leftUSBportsAfterExternalNICs < USBconnections.length )
						{
							for ( int i = leftUSBportsAfterExternalNICs; i < USBconnections.length; i++ )
							{
								// Removes the connection
								WorkareaCanvasActions.removeWidgetConnection(
										canvas, USBconnections[i]);
							}
						}
					}

					// ------------------------------------------------------------------------------
					// The number of taken USB ports(The number of ports - the
					// number of ports available)
					int takenPorts = mbObj.getMaxUSBs()
							- mbObj.getUSBPortsAvailable();

					// Sets the max USB ports
					mbObj.setMaxUSBs(USBports);
					mbObj.setUSBPortsAvailable(mbObj.getMaxUSBs() - takenPorts);

					canvas.setSaved(false);
					canvas.setChanged(true);
				}
				// There aren't enough USB port for all the externalNICs so
				// every USB connection will be removed and only
				// externalNICs will be added until the USB ports run out.
				else
				{

					try
					{
						// Removes all the ExternalNetworksCard from the objects
						// components array.
						mainObj.setAllComponents(ComponentsManagment
								.removeComponents(externalNICs,
										mainObj.getComponents(),
										mainObj.getComponents().length));


						// Disconnect all the existing USB connections
						for ( int i = 0; i < allConnections.length; i++ )
						{
							if ( allConnections[i] != null )
							{
								// If the connection type is USB
								if ( allConnections[i].getConnectionType()
										.equals(ConnectionUtils.USB) )
								{
									// Removes the connection
									WorkareaCanvasActions
											.removeWidgetConnection(canvas,
													allConnections[i]);
								}
							}
						}


						// Sets the max USB ports

						mbObj.setMaxUSBs(USBports);
						mbObj.setUSBPortsAvailable(mbObj.getMaxUSBs());

						// Adds the previously removed ExternalNICs in order
						// until there are no more USB ports available.
						if ( externalNICs != null )
						{
							// The number of components there are room for.
							int counter = mbObj.getMaxUSBs();
							// All the components of the main object(without the
							// ExternalNICs).
							Object[] mainComp = mainObj.getComponents();
							for ( int i = 0; i < counter; i++ )
							{
								// If i is smaller then the length of the
								// externalNICs array.
								if ( i < externalNICs.length )
								{
									mainComp = ComponentsManagment
											.addComponent(externalNICs[i],
													mainComp);
									mbObj.makeOneUSBportTaken();
								}
							}
							mainObj.setAllComponents(mainComp);
						}



						if ( canvas != null )
						{
							canvas.setSaved(false);
							canvas.setChanged(true);
						}
					}
					catch ( ObjectNotFoundInArrayException e )
					{
						// Log entry
						LibraryLogging.libraryLog
								.logp(Level.SEVERE,
										"ComponentsManagment",
										"USBportsValidation",
										"The ExternalNetworksCard components found in the given Object, '"
												+ mainObj.getObjectName()
												+ "', were not found that objects components array.");

						if ( Settings.debug )
						{
							e.printStackTrace();
						}
					}
				}
			}
			else
			{
				// The number of taken USB ports(The number of ports - the
				// number of ports available)
				int takenPorts = mbObj.getMaxUSBs()
						- mbObj.getUSBPortsAvailable();

				// Sets the max USB ports
				mbObj.setMaxUSBs(USBports);
				mbObj.setUSBPortsAvailable(mbObj.getMaxUSBs() - takenPorts);


				if ( canvas != null )
				{
					canvas.setSaved(false);
					canvas.setChanged(true);
				}
			}


			// Determines the supported connection interface on the device.
			mainObj.revalidateSupportedConnectionInterfaces();
		}
	}

	/**
	 * Processes the CPU settings with the CPU objects.
	 */
	public static void CPUportsValidation(Object mainObj, Motherboard mbObj,
			JComboBox CPUsockets)
	{
		if ( mbObj.getMaxCPUs() > Integer.parseInt(CPUsockets.getSelectedItem()
				.toString()) )
		{
			Object[] comp = null;
			try
			{
				// Gets all the CPUs from the objects components array.
				comp = ArrayManagment
						.getSpesificComponents(CPU.class,
								mainObj.getComponents(),
								mainObj.getComponents().length);

				// Removes all the CPUs from the objects components array.
				mainObj.setAllComponents(ComponentsManagment.removeComponents(
						comp, mainObj.getComponents(),
						mainObj.getComponents().length));

			}
			catch ( ObjectNotFoundException e )
			{
				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.FINEST,
								"ComponentsManagment",
								"CPUportsValidation",
								"No CPU components were found in the given Object '"
										+ mainObj.getObjectName()
										+ "'. This does not indicate an error, only informational.");

				// No need to print stack because the exception is used
				// intentionally.
			}
			catch ( ObjectNotFoundInArrayException e )
			{
				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.SEVERE,
								"ComponentsManagment",
								"CPUportsValidation",
								"The CPU components found in the given Object, '"
										+ mainObj.getObjectName()
										+ "', were not found that objects components array.");

				if ( Settings.debug )
				{
					e.printStackTrace();
				}
			}


			mbObj.setMaxCPUs(Integer.parseInt(CPUsockets.getSelectedItem()
					.toString()));
			mbObj.setCPUPortsAvailable(mbObj.getMaxCPUs());

			if ( comp != null )
			{
				// The number of components there are room for.
				int counter = mbObj.getMaxCPUs();

				// All the components of the main object(without the CPUs).
				Object[] mainComp = mainObj.getComponents();

				for ( int i = 0; i < counter; i++ )
				{
					// If i is smaller then the length of the comp array.
					if ( i < comp.length )
					{
						mainComp = ComponentsManagment.addComponent(comp[i],
								mainComp);
						mbObj.makeOneCPUportTaken();
					}
				}
				mainObj.setAllComponents(mainComp);
			}
		}
		else
		{
			// The number of taken CPU ports(The number of ports - the number of
			// ports available)
			int takenPorts = mbObj.getMaxCPUs() - mbObj.getCPUPortsAvailable();

			// Sets the max CPU ports
			mbObj.setMaxCPUs(Integer.parseInt(CPUsockets.getSelectedItem()
					.toString()));
			mbObj.setCPUPortsAvailable(mbObj.getMaxCPUs() - takenPorts);
		}
	}

	/**
	 * Processes the LAN settings with the connected objects.
	 * <i>The process first finds all {@link Object Objects} connected to the
	 * main {@link Object}. Then it finds the network
	 * cards, internal and external. It then removes the objects set as
	 * connected to the different network cards from the array of
	 * removable Objects, because in this function only the {@link Motherboard
	 * Motherboards} lan ports are are being changed.
	 * When only {@link Object Objects} connected to the {@link Motherboard} are
	 * left, they will be removed depending on the
	 * difference between the number of lan and the number of connected Objects.
	 */
	public static void LANportsValidation(Object mainObj, Motherboard mbObj,
			JComboBox LANports, WorkareaCanvas canvas)
	{
		portsValidation(mainObj, mbObj,
				Integer.parseInt(LANports.getSelectedItem().toString()),
				ConnectionUtils.RJ45, canvas);
	}



	/**
	 * Processes the Ports settings with the connected objects.
	 * <i>The process first finds all {@link Object Objects} connected to the
	 * main {@link Object}. Then it finds the network
	 * cards, internal and external. It then removes the objects set as
	 * connected to the different network cards from the array of
	 * removable Objects, because in this function only the {@link Motherboard
	 * Motherboards} Ports ports are are being changed.
	 * When only {@link Object Objects} connected to the {@link Motherboard} are
	 * left, they will be removed depending on the
	 * difference between the number of Ports and the number of connected
	 * Objects.
	 */
	public static void portsValidation(Object mainObj, Motherboard mbObj,
			int ports, String portType, WorkareaCanvas canvas)
	{
		boolean toManyIntegratedPorts = false;

		/**
		 * The new number of allowed ports is smaller then the current
		 * number of integrated ports.
		 */
		if ( portType.equals(ConnectionUtils.RJ45) )
		{
			toManyIntegratedPorts = (ports < mbObj.getMaxIntegLANs());
		}
		else if ( portType.equals(ConnectionUtils.Coax) )
		{
			toManyIntegratedPorts = (ports < mbObj.getMaxCoaxs());
		}
		else if ( portType.equals(ConnectionUtils.Fiber) )
		{
			toManyIntegratedPorts = (ports < mbObj.getMaxFibers());
		}



		if ( toManyIntegratedPorts )
		{
			int newMaxPorts = ports;

			// The connections array that will hold all the connections that are
			// with portType
			Object[] connectedObjects = connectedToBy(mainObj, portType);

			try
			{
				// Gets all the InternalNetworksCard from the objects components
				// array.
				Object[] internalNICs = ArrayManagment.getSpesificComponents(
						InternalNetworksCard.class, mainObj.getComponents(),
						mainObj.getComponents().length);

				for ( int i = 0; i < internalNICs.length; i++ )
				{
					InternalNetworksCard nic = (InternalNetworksCard) internalNICs[i];

					// If the NICs connection object is not null and connection
					// type is RJ-45
					if ( nic.getConnectedObject() != null
							&& nic.getConnectionType().equals(portType) )
					{
						// Goes through all the portType connected objects
						for ( int j = 0; j < connectedObjects.length; j++ )
						{
							// If the index is not null
							if ( connectedObjects[j] != null )
							{
								// If the connected object serial is the same as
								// the one connected to the NIC
								if ( connectedObjects[j].getObjectSerial() == nic
										.getConnectedObject().getObjectSerial() )
								{
									// The object connected to the NIC is
									// removed from the removable portType
									// connected objects
									connectedObjects[j] = null;
								}
							}
						}
					}
				}


			}
			catch ( ObjectNotFoundException e )
			{
				// Log entry
				LibraryLogging.libraryLog
						.logp(Level.FINEST,
								"ComponentsManagment",
								"portsValidation",
								"No InternalNetworksCard components were found in the given Object '"
										+ mainObj.getObjectName()
										+ "'. This does not indicate an error, only informational.");

				// No need to print stack because the exception is used
				// intentionally.
			}

			try
			{
				// Gets all the InternalNetworksCard from the objects components
				// array.
				Object[] externalNICs = ArrayManagment.getSpesificComponents(
						ExternalNetworksCard.class, mainObj.getComponents(),
						mainObj.getComponents().length);


				for ( int i = 0; i < externalNICs.length; i++ )
				{
					ExternalNetworksCard nic = (ExternalNetworksCard) externalNICs[i];

					// If the NICs connection object is not null and connection
					// type is portType
					if ( nic.getConnectedObject() != null
							&& nic.getConnectionType().equals(portType) )
					{
						// Goes through all the RJ45 connected objects
						for ( int j = 0; j < connectedObjects.length; j++ )
						{
							// If the index is not null
							if ( connectedObjects[j] != null )
							{
								// If the connected object serial is the same as
								// the one connected to the NIC
								if ( connectedObjects[j].getObjectSerial() == nic
										.getConnectedObject().getObjectSerial() )
								{
									// The object connected to the NIC is
									// removed from the removable portType
									// connected objects
									connectedObjects[j] = null;
								}
							}
						}
					}
				}
			}
			catch ( ObjectNotFoundException e )
			{
				// No ExternalNetworksCard found
			}


			// Removes the null pointers and shortens the array
			connectedObjects = cleanup.cleanObjectArray(connectedObjects);


			// Now connectedObjects contains only objects connected to the
			// motherboard

			// The number of portType ports left after connected objects are
			// added.
			int leftPorts = newMaxPorts - connectedObjects.length;


			if ( connectedObjects != null )
			{
				// If the number of available portType ports is less then the
				// number of portType devices
				if ( leftPorts < 0 )
				{
					for ( int i = connectedObjects.length + leftPorts; i < connectedObjects.length; i++ )
					{
						// Gets the WidgetExtendedConnection between the two
						// objects
						WidgetExtendedConnection widCon = null;
						try
						{
							widCon = ConnectionManagment.findWidgetConnections(
									canvas, mainObj, connectedObjects[i]);
						}
						catch ( ConnectionDoesNotExist e )
						{
							// Log entry
							LibraryLogging.libraryLog.logp(
									Level.SEVERE,
									"ComponentsManagment",
									"portsValidation",
									"A connection that should exist between two objects, '"
											+ mainObj.getObjectName()
											+ "' and '"
											+ connectedObjects[i]
													.getObjectName()
											+ "', does not exist.");

							if ( Settings.debug )
							{
								e.printStackTrace();
							}
						}

						if ( widCon != null )
						{
							// Removes the connection
							WorkareaCanvasActions.removeWidgetConnection(
									canvas, widCon);
						}
					}
				}
			}
		}



		/**
		 * Motherboard port setup after internal- and external-NIC connected
		 * Object removal.
		 * Excess motherboards ports(and connected Objects) have also been
		 * removed.
		 */
		if ( portType.equals(ConnectionUtils.RJ45) )
		{
			int takenPorts = mbObj.getMaxIntegLANs()
					- mbObj.getIntegLANPortsAvailable();

			// Sets the max LAN ports
			mbObj.setMaxIntegratedLANs(ports);
			mbObj.setIntegLANPortsAvailable(mbObj.getMaxIntegLANs()
					- takenPorts);
		}
		else if ( portType.equals(ConnectionUtils.Coax) )
		{
			int takenPorts = mbObj.getMaxCoaxs()
					- mbObj.getCoaxPortsAvailable();

			// Sets the max COAX ports
			mbObj.setMaxCoaxs(ports);
			mbObj.setCoaxPortsAvailable(mbObj.getMaxCoaxs() - takenPorts);
		}
		else if ( portType.equals(ConnectionUtils.Fiber) )
		{
			int takenPorts = mbObj.getMaxFibers()
					- mbObj.getFiberPortsAvailable();

			// Sets the max FIBER ports
			mbObj.setMaxFibers(ports);
			mbObj.setFiberPortsAvailable(mbObj.getMaxFibers() - takenPorts);
		}


		// Determines the supported connection interface on the device.
		mainObj.revalidateSupportedConnectionInterfaces();

		if ( canvas != null )
		{
			canvas.setSaved(false);
			canvas.setChanged(true);
		}
	}





	/**
	 * Processes the COAX settings with the connected objects.
	 * <i>The process first finds all {@link Object Objects} connected to the
	 * main {@link Object}. Then it finds the network
	 * cards, internal and external. It then removes the objects set as
	 * connected to the different network cards from the array of
	 * removable Objects, because in this function only the {@link Motherboard
	 * Motherboards} COAX ports are are being changed.
	 * When only {@link Object Objects} connected to the {@link Motherboard} are
	 * left, they will be removed depending on the
	 * difference between the number of COAX and the number of connected
	 * Objects.
	 */
	public static void COAXportsValidation(Object mainObj, Motherboard mbObj,
			JComboBox COAXports, WorkareaCanvas canvas)
	{
		portsValidation(mainObj, mbObj,
				Integer.parseInt(COAXports.getSelectedItem().toString()),
				ConnectionUtils.Coax, canvas);
	}


	/**
	 * Processes the FIBER settings with the connected objects.
	 * <i>The process first finds all {@link Object Objects} connected to the
	 * main {@link Object}. Then it finds the network
	 * cards, internal and external. It then removes the objects set as
	 * connected to the different network cards from the array of
	 * removable Objects, because in this function only the {@link Motherboard
	 * Motherboards} FIBER ports are are being changed.
	 * When only {@link Object Objects} connected to the {@link Motherboard} are
	 * left, they will be removed depending on the
	 * difference between the number of FIBER and the number of connected
	 * Objects.
	 */
	public static void FIBERportsValidation(Object mainObj, Motherboard mbObj,
			JComboBox FIBERports, WorkareaCanvas canvas)
	{
		portsValidation(mainObj, mbObj,
				Integer.parseInt(FIBERports.getSelectedItem().toString()),
				ConnectionUtils.Fiber, canvas);
	}



	// SEARCH FUNCTIONS

	/**
	 * Get specific components by searching for components with the give class
	 * type.
	 * 
	 * @return Returns an array of components that match with the given class.
	 * @param componentCounter
	 *            The counter that tells how many components are in the current
	 *            components array.
	 * @param components
	 *            The current components list.
	 * @throws ObjectNotFoundException
	 *             Throws an exception which states that there were not objects
	 *             found with the given class.
	 */
	@SuppressWarnings("unchecked")
	public static boolean containsComponent(Class ComponentClass,
			Object[] components, int componentCounter)
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
			if ( components[i] != null )
			{
				/*
				 * If the given components class matches the present components
				 * class, it will be added to the container
				 */
				if ( components[i].getClass().equals(ComponentClass) )
				{
					componentsFound[tempCounter] = components[i];

					objectFound = true;
				}
			}
		}

		return objectFound;
	}



	// CHECK FUNCTIONS
	/**
	 * Check function to determine whether or not the the given array contains
	 * the given object.
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
	 * Check function to determine whether or not the the given array contains
	 * any of the given objects in the searchObjects array.
	 * 
	 * @param array
	 *            The array that is to be checked.
	 * @param searchObjects
	 *            The objects that is to be searched for.
	 * @return The array of booleans that tells, by way of the index, which
	 *         object are found.
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
	 * Finds and returns all the objects that are connected to the given object
	 * with the given connection type. Returns an array of object with the
	 * connected object, if any are found.
	 * 
	 * @param connectedTo
	 *            The object that will be examined for connections to other
	 *            objects.
	 * @param conType
	 *            The type of connection between the two objects.
	 * @return Returns all the objects connected to the given object with the
	 *         given connection type
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
				System.arraycopy(devCons, 0, cons, netCons.length,
						devCons.length);
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


		if ( foundComp != null )
		{
			// Removes all the empty indexes from the array.
			foundComp = cleanup.cleanObjectArray(foundComp);
		}



		return foundComp;
	}





	/**
	 * Determines and returns the supported interfaces depending on the hardware
	 * on the object.
	 * 
	 * @param obj
	 *            The {@link Object} where supported interfaces are going to be
	 *            searched for.
	 * @return The interfaces supported by the given {@link Object}.
	 */
	public static String[] getSupportedInterfaces(Object obj)
	{
		String[] interfaces = new String[5];

		// The components of the give object
		Object[] components = obj.getComponents();

		boolean lan = false;
		boolean usb = false;
		boolean wlan = false;
		boolean coax = false;
		boolean fiber = false;


		// Goes through all the components
		for ( int i = 0; i < components.length; i++ )
		{
			if ( components[i] instanceof Motherboard )
			{
				Motherboard mbTemp = (Motherboard) components[i];

				// LAN
				if ( mbTemp.getMaxIntegLANs() > 0 )
				{
					lan = true;
				}

				// USB
				if ( mbTemp.getMaxUSBs() > 0 )
				{
					usb = true;
				}

				// COAX
				if ( mbTemp.getMaxCoaxs() > 0 )
				{
					coax = true;
				}

				// FIBER
				if ( mbTemp.getMaxFibers() > 0 )
				{
					fiber = true;
				}
			}
			else if ( components[i] instanceof ExternalNetworksCard )
			{
				ExternalNetworksCard extNICtemp = (ExternalNetworksCard) components[i];

				if ( extNICtemp.getConnectionType()
						.equals(ConnectionUtils.RJ45) )
				{
					lan = true;
				}
				else if ( extNICtemp.getConnectionType().equals(
						ConnectionUtils.Wireless) )
				{
					wlan = true;
				}
				else if ( extNICtemp.getConnectionType().equals(
						ConnectionUtils.Coax) )
				{
					coax = true;
				}
				else if ( extNICtemp.getConnectionType().equals(
						ConnectionUtils.Fiber) )
				{
					fiber = true;
				}
			}
			else if ( components[i] instanceof InternalNetworksCard )
			{
				InternalNetworksCard intNICtemp = (InternalNetworksCard) components[i];

				if ( intNICtemp.getConnectionType()
						.equals(ConnectionUtils.RJ45) )
				{
					lan = true;
				}
				else if ( intNICtemp.getConnectionType().equals(
						ConnectionUtils.Wireless) )
				{
					wlan = true;
				}
				else if ( intNICtemp.getConnectionType().equals(
						ConnectionUtils.Coax) )
				{
					coax = true;
				}
				else if ( intNICtemp.getConnectionType().equals(
						ConnectionUtils.Fiber) )
				{
					fiber = true;
				}
			}
		}

		if ( lan )
		{
			interfaces[0] = ConnectionUtils.RJ45;
		}

		if ( usb )
		{
			interfaces[1] = ConnectionUtils.USB;
		}

		if ( wlan )
		{
			interfaces[2] = ConnectionUtils.Wireless;
		}

		if ( coax )
		{
			interfaces[3] = ConnectionUtils.Coax;
		}

		if ( fiber )
		{
			interfaces[4] = ConnectionUtils.Fiber;
		}


		interfaces = cleanup.cleanObjectArray(interfaces);

		return interfaces;
	}



	/**
	 * This method does a deep copy of the given {@link Object}. It clones the
	 * given object, doing a "shallow" clone first. It then goes through the
	 * {@link Hardware} and {@link Software} arrays inside
	 * the given {@link Object} and clones each array index individually. It
	 * then places
	 * the newly cloned hardware/software objects into a new array and places
	 * those arrays into the the clone of the given object. The x and y
	 * locations of the object is also added to by 30 each.
	 * 
	 * @param copyFrom
	 *            The {@link Object} that is to be copied.
	 * @return An exact deep copy of the given {@link Object}.
	 */
	public static Object deepObjectCopy(Object copyFrom)
	{
		Object copyTo = copyFrom.clone();

		// --------------------------------------------------

		// Copies the components to a new array
		Object[] oldComponents = copyFrom.getComponents();

		if ( oldComponents != null && oldComponents.length > 0 )
		{

			Object[] newComponents = new Object[oldComponents.length];


			if ( oldComponents[0] != null )
			{
				for ( int i = 0; i < oldComponents.length; i++ )
				{
					newComponents[i] = oldComponents[i].clone();
				}
			}

			// Copies the components of the copyFrom object to copyTo object
			copyTo.setAllComponents(newComponents);

			// Removes all the connection the newly created object contains.
			copyTo.removeAllConnections();
		}


		// --------------------------------------------------

		// Copies the software to a new array
		if ( copyFrom.getSoftware() != null )
		{
			Software[] oldSoftware = copyFrom.getSoftware();
			Software[] newSoftware = new Software[oldSoftware.length];

			if ( oldSoftware.length > 0 && oldSoftware[0] != null )
			{
				for ( int i = 0; i < oldSoftware.length; i++ )
				{
					newSoftware[i] = (Software) oldSoftware[i].clone();
				}
			}

			// Copies the software of the copyFrom object to copyTo object
			copyTo.setSoftware(newSoftware);
		}
		// --------------------------------------------------

		if ( copyFrom.getLocation() != null )
		{
			Point newLocation = new Point(copyFrom.getLocation().x + 30,
					copyFrom.getLocation().y + 30);
			copyTo.setLocation(newLocation);
		}

		// --------------------------------------------------

		// Creates a new serial number for the copied object
		copyTo.createRandomLongSerial();

		// --------------------------------------------------


		return copyTo;
	}




	/**
	 * This function attempts to find the objects {@link Motherboard} in the
	 * objects components array.
	 * Null is returned if nothing is found.
	 */
	public static Motherboard getObjectMotherboard(Object obj)
			throws MotherboardNotFound
	{
		Motherboard mb = null;

		// Gets the all objects motherboard components
		Object[] components;

		try
		{
			components = obj.getSpesificComponents(Motherboard.class);

			if ( components != null && components.length != 0 )
			{
				// if the first object in the array is a motherboard object
				if ( components[0] instanceof Motherboard )
				{
					mb = (Motherboard) components[0];
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Log entry
			LibraryLogging.libraryLog.logp(Level.SEVERE, "ComponentsManagment",
					"getObjectMotherboard",
					"No Motherboard components was found in the given Object '"
							+ obj.getObjectName()
							+ "'. Alle devices must contain a Motherboard.");

			if ( Settings.debug )
			{
				e.printStackTrace();
			}

			throw new MotherboardNotFound(obj);
		}

		return mb;
	}
}
