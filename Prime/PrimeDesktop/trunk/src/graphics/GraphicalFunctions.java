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
package graphics;


import graphics.GUI.SpringUtilities;
import graphics.GUI.ghostGlass.GhostMotionAdapter;
import graphics.GUI.selectArea.ImageSelection;
import graphics.GUI.selectArea.TransferWidgetIconListener;
import graphics.GUI.visualObjectCustomization.previewFileChooser.ImageFilter;
import graphics.GUI.visualObjectCustomization.previewFileChooser.ImagePreview;
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuRoom.JMenuWidgetRoom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import managment.ComponentsManagment;
import managment.RoomManagment;
import managment.Settings;
import managment.Settings.systemLocale;
import objects.Object;
import objects.Room;
import objects.Software.fileSystems;
import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.clientObjects.ThinClient;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
import objects.infrastructureObjects.Router;
import objects.infrastructureObjects.Switch;
import objects.infrastructureObjects.WirelessRouter;
import objects.peripheralObjects.MultifunctionPrinter;
import objects.peripheralObjects.NetworkMultifunctionPrinter;
import objects.peripheralObjects.NetworkPrinter;
import objects.peripheralObjects.Printer;
import objects.peripheralObjects.Scanner;
import objects.serverObjects.BackupServer;
import objects.serverObjects.FirewallServer;
import objects.serverObjects.HTTPServer;
import objects.serverObjects.MailServer;
import objects.serverObjects.ProxyServer;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.RoomBorder;
import widgets.WidgetIcon;
import widgets.WidgetRoom;


/**
 * This class contains methods and functions that perform action on graphical
 * components. Like finding and selecting indexes in JComboBoxes or JLists. Or
 * arranging components in panels or frames.
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
			if ( !strings[i].equals("") )
			{
				try
				{
					if ( Integer.parseInt(strings[i]) == data )
					{
						Index = i;
						i = strings.length;
					}
				}
				catch ( NumberFormatException e )
				{
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
			if ( data != null && !data.equals("") )
			{
				try
				{
					if ( strings[i].equals(data) )
					{
						Index = i;
						i = strings.length;
					}
				}
				catch ( NumberFormatException e )
				{
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
		if ( !mbVariable.equals("") && mbVariable != null )
		{
			if ( ComponentsManagment.containsComponent(componentClass,
					mainObj.getComponents(), mainObj.getComponents().length) )
			{

				if ( !mbVariable.equals(newVariable) )
				{
					int n = JOptionPane.showConfirmDialog(comp, msg,
							PrimeMain.texts.getString("verify"),
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
	 * This method looks for the given possibilities in the given data. If any
	 * are found the indexes of those are selected in the given list and that
	 * list is returned.
	 * 
	 * @param list
	 *            The list that will have selected indexes.
	 * @param possibilities
	 *            The information the method will look for in data.
	 * @param data
	 *            The data that will be searched.
	 * @return Returns the indexes of the given data in the array of
	 *         possibilities.
	 */
	public static fileSystems[] getFSInJList(JList list)
	{
		ArrayList<fileSystems> arrayList = new ArrayList<fileSystems>();

		java.lang.Object[] objString = list.getSelectedValues();

		for ( int i = 0; i < objString.length; i++ )
		{
			if ( objString[i] != null )
			{
				arrayList.add(fileSystems.valueOf(objString[i].toString()));
			}
		}

		return arrayList.toArray(new fileSystems[0]);
	}


	/**
	 * This method looks for the given possibilities in the given data. If any
	 * are found the indexes of those are selected in the given list and that
	 * list is returned.
	 * 
	 * @param list
	 *            The list that will have selected indexes.
	 * @param possibilities
	 *            The information the method will look for in data.
	 * @param data
	 *            The data that will be searched.
	 * @return Returns the indexes of the given data in the array of
	 *         possibilities.
	 */
	public static JList getIndexInJList(JList list, String[] possibilities,
			fileSystems[] data)
	{
		int[] indices = new int[data.length];

		for ( int i = 0; i < data.length; i++ )
		{
			for ( int j = 0; j < possibilities.length; j++ )
			{
				if ( data[i].toString().equals(possibilities[j]) )
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
	 * This method looks for the given possibilities in the given data. If any
	 * are found the indexes of those are selected in the given list and that
	 * list is returned.
	 * 
	 * @param list
	 *            The list that will have selected indexes.
	 * @param possibilities
	 *            The information the method will look for in data.
	 * @param data
	 *            The data that will be searched.
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
			numberOfFields -= 6;
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


	/**
	 * Javadoc-TODO - Description
	 */
	public static void JPopupMenuesToggle()
	{
		// If the setting for the Room manipulation is set to true
		if ( Settings.roomsManipulation )
		{
			// Goes through
			for ( int i = 0; i < PrimeMain.canvases.length; i++ )
			{
				if ( PrimeMain.canvases[i] != null )
				{
					// List of all the Rooms on the Scene
					List<Widget> l = PrimeMain.canvases[i].getRoomLayer()
							.getChildren();

					// Converts that list to an array of Objects
					java.lang.Object[] roomTemp = l.toArray();

					// Creates an array with the length of the all the children
					// on the canvas
					WidgetRoom[] roomWidgets = new WidgetRoom[roomTemp.length];

					// Casts all the objects in the converted list to
					// widgetobjects
					for ( int j = 0; j < roomWidgets.length; j++ )
					{
						roomWidgets[j] = (WidgetRoom) roomTemp[j];

						// Add the JMenuPopup action the WidgetRoom
						roomWidgets[j]
								.getActions()
								.addAction(
										ActionFactory
												.createPopupMenuAction(new JMenuWidgetRoom(
														PrimeMain.canvases[i])));
					}
				}
			}
		}
		else
		{
			for ( int i = 0; i < PrimeMain.canvases.length; i++ )
			{
				if ( PrimeMain.canvases[i] != null )
				{
					// List of all the Rooms on the Scene
					List<Widget> l = PrimeMain.canvases[i].getRoomLayer()
							.getChildren();

					// Converts that list to an array of Objects
					java.lang.Object[] roomTemp = l.toArray();

					// Creates an array with the length of the all the children
					// on the canvas
					WidgetRoom[] roomWidgets = new WidgetRoom[roomTemp.length];

					// Casts all the objects in the converted list to
					// widgetobjects
					for ( int j = 0; j < roomWidgets.length; j++ )
					{
						roomWidgets[j] = (WidgetRoom) roomTemp[j];

						// Gets the size of the action chain
						int size = roomWidgets[j].getActions().getActions()
								.size();

						// If the size is 4
						if ( size == 4 )
						{
							// Removes the last action in the chain
							roomWidgets[j].getActions().removeAction(size - 1);
						}
					}
				}
			}
		}
		//
		// // If the setting for the Room manipulation is set to true
		// if ( Settings.connectionToggle )
		// {
		// // Goes through
		// for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
		// {
		// if ( PrimeMain1.canvases[i] != null )
		// {
		// // List of all the Rooms on the Scene
		// List<Widget> l = PrimeMain1.canvases[i]
		// .getConnectionLayer().getChildren();
		//
		// // Converts that list to an array of Objects
		// java.lang.Object[] conTemp = l.toArray();
		//
		// // Creates an array with the length of the all the children
		// // on the canvas
		// WidgetExtendedConnection[] conWidgets = new
		// WidgetExtendedConnection[conTemp.length];
		//
		// // Casts all the objects in the converted list to
		// // widgetobjects
		// for ( int j = 0; j < conWidgets.length; j++ )
		// {
		// conWidgets[j] = (WidgetExtendedConnection) conTemp[j];
		//
		// // Add the JMenuPopup action the WidgetRoom
		// conWidgets[j]
		// .getActions()
		// .addAction(
		// ActionFactory
		// .createPopupMenuAction(new JMenuConnection(
		// PrimeMain1.canvases[i])));
		// }
		// }
		// }
		// }
		// else
		// {
		// for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
		// {
		// if ( PrimeMain1.canvases[i] != null )
		// {
		// // List of all the Rooms on the Scene
		// List<Widget> l = PrimeMain1.canvases[i]
		// .getConnectionLayer().getChildren();
		//
		// // Converts that list to an array of Objects
		// java.lang.Object[] conTemp = l.toArray();
		//
		// // Creates an array with the length of the all the children
		// // on the canvas
		// WidgetExtendedConnection[] conWidgets = new
		// WidgetExtendedConnection[conTemp.length];
		//
		// // Casts all the objects in the converted list to
		// // widgetobjects
		// for ( int j = 0; j < conWidgets.length; j++ )
		// {
		// conWidgets[j] = (WidgetExtendedConnection) conTemp[j];
		//
		// // Gets the size of the action chain
		// int size = conWidgets[j].getActions().getActions()
		// .size();
		//
		// System.out.println(size);
		//
		// // If the size is 4
		// if ( size == 3 )
		// {
		// // Removes the last action in the chain
		// conWidgets[j].getActions().removeAction(size - 1);
		// }
		// }
		// }
		// }
		// }
	}



	/**
	 * FIXME - ChangeWidgetRoomName Changes the title of the
	 * {@link TitledBorder} surrounding the {@link WidgetRoom} and the name
	 * of
	 * the {@link Room} inside the {@link WidgetRoom} object.
	 */
	public static void changeWidgetRoomName(Widget widget)
	{
		WidgetRoom room = (WidgetRoom) widget;
		// The user types in the new name of the room
		String roomName = JOptionPane.showInputDialog(null,
				"New name of the room?", "Name", JOptionPane.QUESTION_MESSAGE);

		// The user has pressed "Cancel"
		if ( roomName != null )
		{
			// If the name typed in by the user is validatet
			if ( RoomManagment.checkNewRoomName(roomName) )
			{
				// Sets the name of the Room object inside the WidgetRoom
				room.getRoom().setRoomName(roomName);

				// Creates a new TitledBorder with the given string
				TitledBorder border = javax.swing.BorderFactory
						.createTitledBorder(new RoomBorder(Color.BLACK),
								roomName);

				// Sets the newly created TitledBorder as the border for the the
				// given WidgetRoom.
				room.setBorder(border);

				// Repaints the given WidgetRoom
				room.repaint();
			}
		}
	}



	/**
	 * Gets the class of the given Object.
	 * 
	 * @param obj
	 */
	public static Class<?> getObjectClass(Object obj)
	{
		if ( obj.getClass().equals(Desktop.class) )
		{
			return Desktop.class;
		}
		else if ( obj.getClass().equals(Laptop.class) )
		{
			return Laptop.class;
		}
		else if ( obj.getClass().equals(ThinClient.class) )
		{
			return ThinClient.class;
		}
		else if ( obj.getClass().equals(HTTPServer.class) )
		{
			return HTTPServer.class;
		}
		else if ( obj.getClass().equals(MailServer.class) )
		{
			return MailServer.class;
		}
		else if ( obj.getClass().equals(BackupServer.class) )
		{
			return BackupServer.class;
		}
		else if ( obj.getClass().equals(FirewallServer.class) )
		{
			return FirewallServer.class;
		}
		else if ( obj.getClass().equals(ProxyServer.class) )
		{
			return ProxyServer.class;
		}
		else if ( obj.getClass().equals(Hub.class) )
		{
			return Hub.class;
		}
		else if ( obj.getClass().equals(Switch.class) )
		{
			return Switch.class;
		}
		else if ( obj.getClass().equals(Router.class) )
		{
			return Router.class;
		}
		else if ( obj.getClass().equals(WirelessRouter.class) )
		{
			return WirelessRouter.class;
		}
		else if ( obj.getClass().equals(Internet.class) )
		{
			return Internet.class;
		}
		else if ( obj.getClass().equals(Scanner.class) )
		{
			return Scanner.class;
		}
		else if ( obj.getClass().equals(Printer.class) )
		{
			return Printer.class;
		}
		else if ( obj.getClass().equals(NetworkPrinter.class) )
		{
			return NetworkPrinter.class;
		}
		else if ( obj.getClass().equals(MultifunctionPrinter.class) )
		{
			return MultifunctionPrinter.class;
		}
		else if ( obj.getClass().equals(NetworkMultifunctionPrinter.class) )
		{
			return NetworkMultifunctionPrinter.class;
		}

		return null;
	}



	/**
	 * This function attempts to return the a string depending on the given
	 * {@link systemLocale}.
	 * 
	 * If no corresponding {@link systemLocale} is found, the primeLocale from
	 * the Settings class will be returned.
	 */
	public static String getSystemLocale(systemLocale language)
	{
		if ( language.equals(systemLocale.en) )
		{
			return PrimeMain.texts.getString("english");
		}
		else if ( language.equals(systemLocale.no_NO) )
		{
			return PrimeMain.texts.getString("norwegian");
		}

		return PrimeMain.texts.getString("english");
	}



	/**
	 * This function attempts to return the {@link systemLocale} that
	 * corresponds with the given string.
	 * 
	 * If no corresponding {@link systemLocale} is found, the primeLocale from
	 * the Settings class will be returned.
	 */
	public static systemLocale getSystemLocale(String language)
	{
		if ( language.equals(PrimeMain.texts.getString("english")) )
		{
			return systemLocale.en;
		}
		else if ( language.equals(PrimeMain.texts.getString("norwegian")) )
		{
			return systemLocale.no_NO;
		}

		return managment.Settings.primeLocale;
	}



	/**
	 * This method sets the widgetIcons, JLabel icon, transferhandler which
	 * takes care of the drag and drop functionality. It also adds an
	 * mouseListener to the JLabel.
	 */
	public static void widgetIconSetup(WidgetIcon widget, ImageIcon icon)
	{
		widget.addMouseListener(new TransferWidgetIconListener(
				PrimeMain.glassPane, widget.getText(), icon));
		widget.addMouseMotionListener(new GhostMotionAdapter(
				PrimeMain.glassPane));
		widget.setTransferHandler(new ImageSelection());
	}



	/**
	 * This function shortens a String to 30 chars if the string is longer then
	 * 30 char. It adds 3 "." at the end of the shortend string.
	 */
	public static String verifyDescriptionLength(String Desc)
	{
		String shortDesc = "";

		// If the string is longer then 30 chars
		if ( Desc.length() > 30 )
		{
			// Goes through the first 27 chars
			for ( int i = 0; i < 27; i++ )
			{
				shortDesc = new StringBuffer(shortDesc).insert(i,
						Desc.charAt(i)).toString();
			}

			shortDesc = new StringBuffer(shortDesc).insert(27, ".").toString();
			shortDesc = new StringBuffer(shortDesc).insert(28, ".").toString();
			shortDesc = new StringBuffer(shortDesc).insert(29, ".").toString();
		}
		else
		{
			return Desc;
		}

		return shortDesc;
	}



	/**
	 * This function returns the File pointer for the given {@link ImageIcon}.
	 * The search will take place inside the images folder for the program. The
	 * function will return Null if no pointer is found.
	 * 
	 * @return A {@link File} pointer to the {@link ImageIcon} file.
	 */
	public static File getImageIconFile(ImageIcon image)
	{
		// The location of the images for the system
		URI uri = new File("./resource/images").toURI();

		File folder = new File(uri);


		// This returned file should contain a pointer to the ImageIcon, or Null
		return findImageFile(folder, image.getDescription());
	}



	/**
	 * Goes through all files and directories under a given folder. It finds and
	 * sets all files within this given folder with the file extensions *.png,
	 * *.jpg and *.gif. When it finds a file with the given name it returns the
	 * file.
	 */
	private static File findImageFile(File dir, String imageName)
	{
		if ( dir.isDirectory() )
		{
			String[] children = dir.list();
			for ( int i = 0; i < children.length; i++ )
			{
				File found = null;

				found = findImageFile(new File(dir, children[i]), imageName);

				// If found is not null, there has been found a file with the
				// given name, and found will be returned.
				if ( found != null )
				{
					return found;
				}
			}
		}
		else
		{
			String name = dir.getName();

			if ( name.endsWith(".png") || name.endsWith(".jpg")
					|| name.endsWith(".gif") )
			{
				name = name.substring(0, (name.length() - 4));

				// If the name of the file is the same as the given imageName
				if ( name.equalsIgnoreCase(imageName) )
				{
					return dir;
				}
			}
		}

		return null;
	}



	/**
	 * @return A String array that contains all the {@link fileSystems} enums
	 *         converted to string.
	 */
	public static String[] getAllFs()
	{
		String[] listData = { fileSystems.FAT16.toString(),
				fileSystems.FAT32.toString(), fileSystems.exFAT.toString(),
				fileSystems.NTFS.toString(), fileSystems.HFS.toString(),
				fileSystems.HFSplus.toString(), fileSystems.HPFS.toString(),
				fileSystems.UFS.toString(), fileSystems.EXT2.toString(),
				fileSystems.EXT3.toString(), fileSystems.EXT4.toString(),
				fileSystems.XFS.toString(), fileSystems.BTRFS.toString(),
				fileSystems.ISO9660.toString(), fileSystems.ODS5.toString(),
				fileSystems.JFS.toString(), fileSystems.VxFS.toString(),
				fileSystems.ZFS.toString(), fileSystems.ReiserFS.toString(),
				fileSystems.SWAP.toString(), fileSystems.UDF.toString() };


		return listData;
	}



	/**
	 * This function will allow the user to select an Icon (max 48x48) and
	 * returns the selected ImageIcon.
	 * NOTE: Given file pointer CAN BE NULL.
	 */
	public static ImageIcon userIconSelection(File folder)
	{
		ImageIcon objectIcon = null;

		JFileChooser fc = null;

		if ( folder == null )
		{
			folder = new File("./resource/images/objectImages/GeneralIcons");
		}

		// Set up the file chooser.
		if ( fc == null )
		{
			fc = new JFileChooser(folder);

			// Add a custom file filter and disable the default
			// (Accept All) file filter.
			fc.addChoosableFileFilter(new ImageFilter());
			fc.setAcceptAllFileFilterUsed(false);

			// Add the preview pane.
			fc.setAccessory(new ImagePreview(fc));
		}

		boolean done = false;

		while ( !done )
		{
			// Show it.
			int returnVal = fc.showDialog(null, "Icon");

			// Process the results.
			if ( returnVal == JFileChooser.APPROVE_OPTION )
			{
				File file = fc.getSelectedFile();

				ImageIcon image = null;

				try
				{
					image = ImageLocator.createImageIcon(file.toURI().toURL());
				}
				catch ( MalformedURLException e1 )
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				if ( image.getIconHeight() <= 48 && image.getIconWidth() <= 48 )
				{
					objectIcon = image;

					// Finished
					done = true;
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"The image choosen must be 48x48 or smaller.",
							"Size Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if ( returnVal == JFileChooser.CANCEL_OPTION )
			{
				// Finished
				done = true;
			}
		}

		fc.setSelectedFile(null);


		return objectIcon;
	}
}
