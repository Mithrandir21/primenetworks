/**
 * 
 */
package graphics;


import graphics.GUI.SpringUtilities;
import graphics.GUI.selectArea.ImageSelection;
import graphics.GUI.selectArea.TransferWidgetIconListener;
import graphics.GUI.workareaCanvas.WorkareaTabbed;
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuRoom.JMenuWidgetRoom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import managment.CanvasManagment;
import managment.ComponentsManagment;
import managment.RoomManagment;
import managment.Settings;
import objects.Object;
import objects.Room;
import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.clientObjects.ThinClient;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
import objects.infrastructureObjects.Router;
import objects.infrastructureObjects.Switch;
import objects.infrastructureObjects.WirelessRouter;
import objects.peripheralObjects.Fax;
import objects.peripheralObjects.MultifunctionPrinter;
import objects.peripheralObjects.NetworkMultifunctionPrinter;
import objects.peripheralObjects.NetworkPrinter;
import objects.peripheralObjects.Printer;
import objects.peripheralObjects.Scanner;
import objects.serverObjects.BackupServer;
import objects.serverObjects.DatabaseServer;
import objects.serverObjects.FirewallServer;
import objects.serverObjects.HTTPServer;
import objects.serverObjects.MailServer;
import objects.serverObjects.PrinterServer;
import objects.serverObjects.ProxyServer;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.RoomBorder;
import widgets.WidgetIcon;
import widgets.WidgetObject;
import widgets.WidgetRoom;
import widgets.WorkareaCanvas;


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
					int n = JOptionPane.showConfirmDialog(comp, msg,
							PrimeMain1.texts.getString("verify"),
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



	/**
	 * This method takes all the component inside the given parent container and
	 * orders them so that there are no more then 1 components in any one row.
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
	public static void make1xGrid(Container parent, int numberOfFields,
			int initialX, int initialY, int xPad, int yPad)
	{
		int rows = numberOfFields;


		SpringUtilities.makeCompactGrid(parent, rows, 1, initialX, initialY,
				xPad, yPad);

	}




	/**
	 * Updates the LabelWidget that shows the widgetObjects name on the Scene on
	 * the canvas.
	 * 
	 * @param obj
	 * @param widgetObj
	 * @param name
	 * @return The object with the updated name
	 */
	public static Object updateWidgetObjectCanvasName(Object obj,
			WidgetObject widgetObj, String name)
	{
		if ( !(obj.getObjectName().equals(name)) )
		{
			// Sets the actual objects new Name
			obj.setObjectName(name);

			List<Widget> children = widgetObj.getChildren();

			LabelWidget label = null;


			for ( Iterator<Widget> iter = children.iterator(); iter.hasNext(); )
			{
				Widget temp = iter.next();
				if ( temp instanceof LabelWidget )
				{
					label = (LabelWidget) temp;
				}
			}

			if ( label != null )
			{
				label.setLabel(name);
			}
		}


		return obj;
	}


	/**
	 * Updates the LabelWidget that shows the widgetObjects name on the Scene on
	 * the canvas. This method finds the WidgetObject that contains the given
	 * object in all the different canvases.
	 * 
	 * @param obj
	 * @param name
	 * @return The object with the updated name
	 */
	public static Object updateWidgetObjectCanvasName(WidgetObject obj,
			String name)
	{
		if ( !(obj.getObject().getObjectName().equals(name)) )
		{
			obj.getObject().setObjectName(name);

			List<Widget> children = obj.getChildren();

			LabelWidget label = null;

			for ( Iterator<Widget> iter = children.iterator(); iter.hasNext(); )
			{
				Widget temp = iter.next();
				if ( temp instanceof LabelWidget )
				{
					label = (LabelWidget) temp;
				}
			}

			if ( label != null )
			{
				label.setLabel(name);
			}
		}


		return obj.getObject();
	}



	/**
	 * Updates the LabelWidget that shows the widgetObjects name on the Scene on
	 * the canvas. This method finds the WidgetObject that contains the given
	 * object in all the different canvases.
	 * 
	 * @param obj
	 * @param name
	 * @return The object with the updated name
	 */
	public static Object updateWidgetObjectCanvasName(Object obj, String name)
	{
		WidgetObject widgetObj = CanvasManagment.findWidgetObject(obj,
				PrimeMain1.canvases);


		if ( !(obj.getObjectName().equals(name)) )
		{
			obj.setObjectName(name);

			List<Widget> children = widgetObj.getChildren();

			LabelWidget label = null;

			for ( Iterator<Widget> iter = children.iterator(); iter.hasNext(); )
			{
				Widget temp = iter.next();
				if ( temp instanceof LabelWidget )
				{
					label = (LabelWidget) temp;
				}
			}

			if ( label != null )
			{
				label.setLabel(name);
			}
		}


		return obj;
	}




	/**
	 * FIXME - FIX the update canvasName function.
	 */
	public static void updateCanvasNames()
	{
		JPanel workpanel = PrimeMain1.getWorkareaPanel();

		WorkareaTabbed pane = (WorkareaTabbed) workpanel.getComponent(0);

		int tabCount = pane.getTabCount();

		for ( int i = 0; i < tabCount; i++ )
		{
			// WorkareaSceneScroll canvasScroll = (WorkareaSceneScroll)
			// pane.getTabComponentAt(i);

			if ( pane.getTabComponentAt(i) == null )
			{
				System.out.println("feil");
			}
			else
			// FIXME - UPDATING Canvas Tab Name
			{
				System.out.println("riktig");
			}
		}
	}



	/**
	 * Updates all the LabelWidgets on the scene with the name of the object
	 * name that is within the WidgetObject.
	 */
	public static void updateWidgetObjectNamesOnAllCanvas()
	{
		WorkareaCanvas[] canvases = PrimeMain1.canvases;


		for ( int i = 0; i < canvases.length; i++ )
		{
			List<Widget> children = canvases[i].getMainLayer().getChildren();

			for ( Iterator<Widget> iter = children.iterator(); iter.hasNext(); )
			{
				WidgetObject temp = (WidgetObject) iter.next();


				updateWidgetObjectCanvasName(temp.getObject(), temp, temp
						.getObject().getObjectName());
			}

		}
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
			for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
			{
				if ( PrimeMain1.canvases[i] != null )
				{
					// List of all the Rooms on the Scene
					List<Widget> l = PrimeMain1.canvases[i].getRoomLayer()
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
														PrimeMain1.canvases[i])));
					}
				}
			}
		}
		else
		{
			for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
			{
				if ( PrimeMain1.canvases[i] != null )
				{
					// List of all the Rooms on the Scene
					List<Widget> l = PrimeMain1.canvases[i].getRoomLayer()
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
	}



	/**
	 * FIXME - ChangeWidgetRoomName Changes the title of the
	 * {@link TitledBorder} surrounding the {@link WidgetRoom} and the name of
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
	 * @return
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
	 * Returns a ImageIcon that represents the Object given.
	 * 
	 * @param obj
	 * @return
	 */
	public static ImageIcon getImageIconForObject(Object obj)
	{
		if ( obj instanceof Desktop )
		{
			return ImageLocator.getImageIconObject("Desktop");
		}
		else if ( obj instanceof Laptop )
		{
			return ImageLocator.getImageIconObject("Laptop");
		}
		else if ( obj instanceof ThinClient )
		{
			return ImageLocator.getImageIconObject("Screen");
		}
		else if ( obj instanceof HTTPServer )
		{
			return ImageLocator.getImageIconObject("HTTP Server");
		}
		else if ( obj instanceof BackupServer )
		{
			return ImageLocator.getImageIconObject("Backup Server");
		}
		else if ( obj instanceof DatabaseServer )
		{
			return ImageLocator.getImageIconObject("Database Server");
		}
		else if ( obj instanceof MailServer )
		{
			return ImageLocator.getImageIconObject("Mail Server");
		}
		else if ( obj instanceof FirewallServer )
		{
			return ImageLocator.getImageIconObject("Firewall Server");
		}
		else if ( obj instanceof ProxyServer )
		{
			return ImageLocator.getImageIconObject("Proxy Server");
		}
		else if ( obj instanceof PrinterServer )
		{
			return ImageLocator.getImageIconObject("Printer Server");
		}
		else if ( obj instanceof Scanner )
		{
			return ImageLocator.getImageIconObject("Scanner");
		}
		else if ( obj instanceof Printer )
		{
			return ImageLocator.getImageIconObject("Printer");
		}
		else if ( obj instanceof Fax )
		{
			return ImageLocator.getImageIconObject("Fax");
		}
		else if ( obj instanceof MultifunctionPrinter )
		{
			return ImageLocator.getImageIconObject("MultifunctionPrinter");
		}
		else if ( obj instanceof NetworkPrinter )
		{
			return ImageLocator.getImageIconObject("PrinterNetwork");
		}
		else if ( obj instanceof NetworkMultifunctionPrinter )
		{
			return ImageLocator
					.getImageIconObject("NetworkMultifunctionPrinter");
		}
		else if ( obj instanceof Hub )
		{
			return ImageLocator.getImageIconObject("Hub");
		}
		else if ( obj instanceof Switch )
		{
			return ImageLocator.getImageIconObject("Switch");
		}
		else if ( obj instanceof Router )
		{
			return ImageLocator.getImageIconObject("Router");
		}
		else if ( obj instanceof WirelessRouter )
		{
			return ImageLocator.getImageIconObject("WirelessRouter");
		}
		else if ( obj instanceof Internet )
		{
			return ImageLocator.getImageIconObject("Internet");
		}

		return null;
	}




	/**
	 * This method sets the widgetIcons, JLabel icon, transferhandler which
	 * takes care of the drag and drop functionality. It also adds an
	 * mouseListener to the JLabel.
	 */
	public static void widgetIconSetup(WidgetIcon widget)
	{
		widget.addMouseListener(new TransferWidgetIconListener());
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
}
