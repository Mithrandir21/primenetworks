/**
 * 
 */
package graphics;


import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import managment.ComponentsManagment;
import objects.Object;


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
		if ( mbVariable != "" && mbVariable != null )
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
		}
		return combo;
	}




	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param list
	 * @param possibilities
	 * @param data
	 * @return Returns the indexes of the given data in the array of
	 *         possibilities.
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
