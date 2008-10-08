/**
 * 
 */
package graphics;


import hardware.CPU;
import hardware.Discdrive;
import hardware.ExternalNetworksCard;
import hardware.GraphicsCard;
import hardware.InternalNetworksCard;
import hardware.Motherboard;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import managment.ComponentsManagment;
import objects.Object;
import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class GraphicalFunctions
{



	/**
	 * This method find and returns the index of data if it is found in the
	 * array of strings. If not it will return 0;
	 * 
	 * @param strings
	 *            The array that is searched for the data.
	 * @param data
	 *            The integer that is searched for in the array of
	 *            strings(converted to Integers).
	 * @return Returns the index in the array where the data has been found. If
	 *         not found, 0 will be returned.
	 */
	public static int getIndexInJComboBox(String[] strings, int data)
	{
		int Index = 0;

		for ( int i = 0; i < strings.length; i++ )
		{
			if ( strings[i] != "" )
			{
				if ( Integer.parseInt(strings[i]) == data )
				{
					Index = i;
					i = strings.length;
				}
			}
		}

		return Index;
	}



	/**
	 * This method find and returns the index of data if it is found in the
	 * array of strings. If not it will return 0;
	 * 
	 * @param strings
	 *            The array that is searched for the data.
	 * @param data
	 *            The string that is searched for in the array of strings.
	 * @return Returns the index in the array where the data has been found. If
	 *         not found, 0 will be returned.
	 */
	public static int getIndexInJComboBox(String[] strings, String data)
	{
		int Index = 0;

		for ( int i = 0; i < strings.length; i++ )
		{
			if ( data != null && data != "" )
			{
				if ( strings[i].equals(data) )
				{
					Index = i;
					i = strings.length;
				}
			}
		}

		return Index;
	}


	/**
	 * TODO - Description
	 */
	public static void processAll(Object obj)
	{
		Object[] components = obj.getComponents();

		Motherboard mb = null;

		try
		{
			mb = (Motherboard) ComponentsManagment.getSpesificComponents(
					Motherboard.class, components, components.length)[0];
		}
		catch ( ObjectNotFoundException e1 )
		{
			// FIXME - ProcessAll motherboard get
			e1.printStackTrace();
		}


		// CPU

	}


	/**
	 * TODO - Description
	 */
	public static void processCPU(Motherboard mb, Object obj)
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		try
		{
			// Gets all the CPU components in the components array.
			Object[] cpus = ComponentsManagment.getSpesificComponents(
					CPU.class, components, components.length);

			// 
			for ( int i = 0; i < cpus.length; i++ )
			{
				CPU cpu = (CPU) cpus[i];

				if ( cpu.getSocket() != mb.getSocket() )
				{
					// Removes the actual components.
					obj.setAllComponents(ComponentsManagment.removeComponent(
							cpu, components, components.length));
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Does nothing if no objects are found.
		}
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public static void processDiscDrive(Motherboard mb, Object obj)
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		try
		{
			// Gets all the Discdrive components in the components array.
			Object[] drives = ComponentsManagment.getSpesificComponents(
					Discdrive.class, components, components.length);

			// 
			for ( int i = 0; i < drives.length; i++ )
			{
				Discdrive dicsdrive = (Discdrive) drives[i];

				if ( dicsdrive.getPort() != mb.getDUCconnectionType() )
				{
					// Removes the actual components.
					obj.setAllComponents(ComponentsManagment.removeComponent(
							dicsdrive, components, components.length));
				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Does nothing if no objects are found.
		}
	}

	
	
	/**
	 * TODO - Description
	 * 
	 */
	public static void processExternalNIC(Motherboard mb, Object obj)
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		try
		{
			// Gets all the ExternalNetworksCard components in the components array.
			Object[] extNICs = ComponentsManagment.getSpesificComponents(
					ExternalNetworksCard.class, components, components.length);

			// 
			for ( int i = 0; i < extNICs.length; i++ )
			{
				ExternalNetworksCard extNIC = (ExternalNetworksCard) extNICs[i];

//				if ( extNIC.getPort() != mb.getDUCconnectionType() )
//				{
//					// Removes the actual components.
//					obj.setAllComponents(ComponentsManagment.removeComponent(
//							extNIC, components, components.length));
//				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Does nothing if no objects are found.
		}
	}

	

	/**
	 * TODO - Description
	 * 
	 */
	public static void processInternalNIC(Motherboard mb, Object obj)
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		try
		{
			// Gets all the ExternalNetworksCard components in the components array.
			Object[] intNICs = ComponentsManagment.getSpesificComponents(
					InternalNetworksCard.class, components, components.length);

			// 
			for ( int i = 0; i < intNICs.length; i++ )
			{
				InternalNetworksCard intNIC= (InternalNetworksCard) intNICs[i];

//				if ( extNIC.getPort() != mb.getDUCconnectionType() )
//				{
//					// Removes the actual components.
//					obj.setAllComponents(ComponentsManagment.removeComponent(
//							extNIC, components, components.length));
//				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Does nothing if no objects are found.
		}
	}

	
	
	/**
	 * TODO - Description
	 * 
	 */
	public static void processGPU(Motherboard mb, Object obj)
	{
		// Gets all the components the object contains.
		Object[] components = obj.getComponents();

		try
		{
			// Gets all the GraphicsCard components in the components array.
			Object[] GPUs = ComponentsManagment.getSpesificComponents(
					GraphicsCard.class, components, components.length);

			// 
			for ( int i = 0; i < GPUs.length; i++ )
			{
				InternalNetworksCard gpu = (InternalNetworksCard) GPUs[i];

//				if ( extNIC.getPort() != mb.getDUCconnectionType() )
//				{
//					// Removes the actual components.
//					obj.setAllComponents(ComponentsManagment.removeComponent(
//							extNIC, components, components.length));
//				}
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Does nothing if no objects are found.
		}
	}

	

	/**
	 * This method will remove any component from the component list of the this
	 * classes object if the given Variable does not match the given
	 * newVariable.
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
			if ( ComponentsManagment.containsComponent(componentClass, mainObj
					.getComponents(), mainObj.getComponents().length) )
			{

				boolean objContains = true;

				Object[] returned = null;

				try
				{
					// Find the components with the given class on a motherboard
					returned = ComponentsManagment.getSpesificComponents(
							componentClass, mainObj.getComponents(), mainObj
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
						mainObj.setAllComponents(ComponentsManagment
								.removeComponents(returned, mainObj
										.getComponents(), mainObj
										.getComponents().length));
					}
					catch ( ObjectNotFoundInArrayException ex )
					{
						ex.printStackTrace();
					}
				}


				// Updates the views of the object to correctly show the
				// current info.
				PrimeMain1.objView.updateViewInfo();
			}
		}
	}



	/**
	 * This function does verifies that a component with the given class exists
	 * in the components array of the main selected component. If it does the
	 * function then checks to verify that the input slot/socket matches with
	 * the slot/socket of the found component. If the two slots/sockets do not
	 * match the user is asked to verify their choice that will then lead to the
	 * removal of the incompatible component.
	 * 
	 * @param comp
	 *            The components that will be the ancestor of the JOptionPane.
	 * @param mainObj
	 *            The main component. The object which all other objects are
	 *            children of.
	 * @param componentClass
	 *            The class of the component that will be searched for.
	 * @param mbVariable
	 *            The port, slot or socket on the motherboard.
	 * @param newVariable
	 *            The port, slot or socket that the user has chosen.
	 * @param msg
	 *            The message the user will see.
	 * @param strings
	 *            The strings in the selected JComboBox.
	 * @param combo
	 *            The JComboBox itself.
	 */
	public static JComboBox verifyChange(Component comp, Object mainObj,
			Class<?> componentClass, String mbVariable, String newVariable,
			String msg, String[] strings, JComboBox combo)
	{
		if ( ComponentsManagment.containsComponent(componentClass, mainObj
				.getComponents(), mainObj.getComponents().length) )
		{

			if ( !mbVariable.equals(newVariable) )
			{
				int n = JOptionPane.showConfirmDialog(comp, msg, "Verify",
						JOptionPane.YES_NO_OPTION);


				// If the answer is "No"
				if ( n == 1 )
				{

					combo.setSelectedIndex(getIndexInJComboBox(strings,
							mbVariable));
				}
			}
		}

		return combo;
	}




	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param list
	 * @param data
	 * @return
	 */
	public static JList getIndexInJList(JList list, String[] possibilities,
			String[] data)
	{
		int[] indices = new int[data.length];

		for ( int i = 0; i < data.length; i++ )
		{
			for ( int j = 0; j < possibilities.length; j++ )
			{
				if ( data[i].equals(possibilities[j]) )
				{
					indices[i] = j;

					// Stops the loop.
					j = possibilities.length;
				}
			}
		}


		list.setSelectedIndices(indices);

		return list;
	}
}
