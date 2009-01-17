/**
 * 
 */
package graphics;


import graphics.GUI.SpringUtilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import managment.ComponentsManagment;
import objects.Object;


/**
 * This class contains methods and functions that perform action on graphical components.
 * Like finding and selecting indexes in JComboBoxes or JLists.
 * Or arranging components in panels or frames.
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
	 * This method looks for the given possibilities in the given data.
	 * If any are found the indexes of those are selected in the given list
	 * and that list is returned. 
	 * 
	 * @param list The list that will have selected indexes.
	 * @param possibilities The information the method will look for in data. 
	 * @param data The data that will be searched.
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




	/**
	 * This method takes all the component inside the given parent container and
	 * orders them so that there are no more then 6 components in any one row.
	 * This method is mostly used by the Object View like Hardware and Software
	 * views that show settings options.
	 * 
	 * @param parent
	 *            The container that holds the components that are to be
	 *            arranged.
	 * @param numberOfFields
	 *            The number of components in the container.
	 * @param initialX
	 *            The initial x location that the first component should be
	 *            placed.
	 * @param initialY
	 *            The initial y location that the first component should be
	 *            placed.
	 * @param xPad
	 *            The x padding that all components will have.
	 * @param yPad
	 *            The y padding that all components will have.
	 */
	public static void make6xGrid(Container parent, int numberOfFields,
			int initialX, int initialY, int xPad, int yPad)
	{
		Dimension tfSize = new Dimension(90, 20);

		int rows = 1;

		while ( numberOfFields > 6 )
		{
			numberOfFields = numberOfFields - 6;
			rows++;
		}

		while ( numberOfFields > -2 )
		{
			JLabel temp1 = new JLabel("");
			temp1.setMaximumSize(tfSize);
			temp1.setPreferredSize(tfSize);

			parent.add(temp1);

			numberOfFields--;
		}


		SpringUtilities.makeCompactGrid(parent, rows, 6, initialX, initialY,
				xPad, yPad);

	}
}
