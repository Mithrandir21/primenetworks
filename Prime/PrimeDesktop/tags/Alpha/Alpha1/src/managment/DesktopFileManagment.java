/**
 * 
 */
package managment;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;
import graphics.GUI.JPGFilter;
import graphics.GUI.PNGFilter;
import graphics.GUI.selectArea.PrimeJTree.FileTreeNode;
import graphics.GUI.workareaCanvas.WorkareaSceneScroll;
import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import objects.Object;
import objects.Room;
import widgetManipulation.WorkareaCanvasNetworkInfo;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WidgetRoom;
import widgets.WorkareaCanvas;
import canvasManipulation.CanvasExporter;
import connections.Connection;
import connections.WidgetExtendedConnection;


/**
 * This class provides static functions that deal with the actual saving to
 * file, reading from file and so on. {@link WorkareaCanvas WorkareaCanvases}
 * are saved and loaded here. There are also testing and control functions.
 * 
 * @author Bahram Malaekeh
 */
public class DesktopFileManagment
{

	/**
	 * Saves the given WorkareaCanvas. This function creates a file object from
	 * the name of the WorkareaCanvas and passes that on the function that
	 * actually saves the WorkareaCanvas.
	 * 
	 * @param canvas
	 *            The WorkareaCanvas to be written out.
	 */
	public static void saveWorkareaCanvas(WorkareaCanvas canvas)
	{
		File file = new File("./resource/Data/" + canvas.getCanvasName()
				+ ".dat");

		saveCanvas(canvas, file);
	}


	/**
	 * Saves the given WorkareaCanvas to the given File.
	 * 
	 * @param canvas
	 *            The WorkareaCanvas to be written out.
	 * @param file
	 *            The File which the WorkareaCanvas will be written to.
	 */
	public static void saveWorkareaCanvas(WorkareaCanvas canvas, File file)
	{
		saveCanvas(canvas, file);
	}




	/**
	 * Saves the given WorkareaCanvas in the given File. The name of the
	 * WorkareaCanvas is written out to the file as an object, so is an
	 * ArrayList of the WidgetObjects and Connections on the WorkareaCanvas.
	 * 
	 * @param canvas
	 *            The WorkareaCanvas to be written out.
	 * @param file
	 *            The File which the WorkareaCanvas will be written to.
	 */
	private static void saveCanvas(WorkareaCanvas canvas, File file)
	{
		// Revalidates the locations of the objects on the scene
		canvas.revalidateWidgetLocations();

		boolean verified = checkAndVerify(canvas);

		if ( verified )
		{
			try
			{
				FileOutputStream fout = new FileOutputStream(file);

				// The object stream file
				ObjectOutputStream oos = new ObjectOutputStream(fout);

				// The name of the canvas
				String nameOfCanvas = canvas.getCanvasName();

				// Writes out the name of the canvas
				oos.writeObject(nameOfCanvas);

				// Writes out the serial number of the canvas
				oos.writeDouble(canvas.getSerial());

				// Writes out the WorkareaCanvasNetworkInfo
				oos.writeObject(canvas.getNetworkInfo());



				// WRITE OBJECTS ON THE CANVAS

				// The objects on the canvas
				Object[] objects = canvas.getObjectsOnTheScene();

				// The ArrayList of object that are on the canvas
				ArrayList<Object> objectList = new ArrayList<Object>();


				// There must be some objects on the canvas for there to be
				// saved any widgetObjects
				if ( objects.length > 0 )
				{
					// Goes through all the object on the canvas and adds then
					// to the arraylist
					for ( int i = 0; i < objects.length; i++ )
					{
						objectList.add(objects[i]);
					}

				}

				// Writes out the objects in the form of an arraylist, even if
				// it is empty
				oos.writeObject(objectList);

				oos.flush();


				// END OF WRITE OBJECTS



				// WRITE CONNECTIONS ON THE CANVAS

				// The canvas connections
				Connection[] connections = canvas.getConnections();

				// The ArrayList that will hold the connections
				ArrayList<Connection> connectionList = new ArrayList<Connection>();

				// The must be at least on connection on the canvas for there to
				// be saved any connections
				if ( connections.length > 0 && connections[0] != null )
				{
					// Goes through all the connections on the canvas and adds
					// them to the ArrayList
					for ( int i = 0; i < connections.length; i++ )
					{
						connectionList.add(connections[i]);
					}
				}

				// Writes out the connections ArrayList, even if it is empty
				oos.writeObject(connectionList);

				oos.flush();

				// END OF WRITE CONNECTIONS


				// WRITE ROOMS ON THE CANVAS

				// The canvas rooms
				Room[] rooms = canvas.getNetworkRooms();

				// The ArrayList that will hold the rooms objects
				ArrayList<Room> roomList = new ArrayList<Room>();

				// The must be at least on room on the canvas for there to be
				// saved any rooms
				if ( rooms.length > 0 && rooms[0] != null )
				{
					// Goes through all the rooms on the canvas and adds them to
					// the ArrayList
					for ( int i = 0; i < rooms.length; i++ )
					{
						roomList.add(rooms[i]);
					}
				}

				// Writes out the room ArrayList, even if it is empty
				oos.writeObject(roomList);

				oos.flush();

				// END OF WRITE ROOMS


				oos.close();
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}

			canvas.setSaved(true);
			canvas.setChanged(false);

			// Reloads the JTree
			PrimeMain1.updatePrimeTree();

			PrimeMain1.workTab.revalidate();
			PrimeMain1.workTab.repaint();
		}
	}




	/**
	 * This function checks whether or not there exist a file containing a
	 * WorkareaCanvas with the same and serial as the given WorkareaCanvas. If
	 * not true is returned. If there exists a WorkareaCanvas with the same
	 * name, but not serial number the user is asked to verify overwriting that
	 * file.
	 * 
	 * @param canvas
	 * @return
	 */
	private static boolean checkAndVerify(WorkareaCanvas canvas)
	{
		String canvasName = canvas.getCanvasName();
		double canvasSerial = canvas.getSerial();

		// Creates a file object(not the actual file)
		File file = new File("./resource/Data/" + canvas.getCanvasName()
				+ ".dat");

		// If the file(network) exists
		if ( file.exists() )
		{
			try
			{
				FileInputStream fin = new FileInputStream(file);

				ObjectInputStream ois = new ObjectInputStream(fin);

				// The name of the file canvas
				String name = (String) ois.readObject();

				// The serial of the file network
				double serial = ois.readDouble();


				ois.close();

				// If the name of the file network is the same as the name of
				// the given WorkareaCanvas
				if ( name.equalsIgnoreCase(canvasName) )
				{
					// If the serial from the file is not the same as the serial
					// from the given WorkareaCanvas
					if ( serial != canvasSerial )
					{
						int answer = JOptionPane
								.showConfirmDialog(
										null,
										PrimeMain1.texts
												.getString("overwriteNetworkWithTheSameNameMsg"),
										PrimeMain1.texts.getString("overwrite"),
										JOptionPane.YES_NO_OPTION);

						// The user answers "yes"
						if ( answer == 0 )
						{
							return true;
						}
						else
						{
							return false;
						}
					}
					else
					{
						return true;
					}
				}


			}
			catch ( FileNotFoundException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch ( IOException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch ( ClassNotFoundException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return true;
	}




	/**
	 * The method check whether or not a file exist with the given newName
	 * String. If it does, the file is checked for a WorkareaCanvas
	 * 
	 * @param canvas
	 * @return
	 */
	public static boolean fileWorkareaCanvasExist(WorkareaCanvas canvas,
			String newName)
	{
		String canvasName = canvas.getCanvasName();

		// Creates a file object(not the actual file)
		File file = new File("./resource/Data/" + newName + ".dat");

		// If the file(network) exists
		if ( file.exists() )
		{
			try
			{
				FileInputStream fin = new FileInputStream(file);

				ObjectInputStream ois = new ObjectInputStream(fin);

				// The name of the file canvas
				String name = (String) ois.readObject();


				// Closes the file
				ois.close();

				// If the name of the file network is the same as the name of
				// the given WorkareaCanvas
				if ( name.equalsIgnoreCase(newName) )
				{
					return true;
				}
			}
			catch ( FileNotFoundException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch ( IOException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch ( ClassNotFoundException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return false;
	}




	/**
	 * This function renames the file a given {@link WorkareaCanvas} is located
	 * in(if is has ever been saved), to a file with the given string. The
	 * function checks if the {@link WorkareaCanvas} exists.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} thats inside the file to be
	 *            renamed.
	 * @param newName
	 *            The name of the new file.
	 * @return True, if the file rename was successful, and false, if not.
	 */
	public static boolean changeFileName(WorkareaCanvas canvas, String newName)
	{
		// Checks to see whether a canvas file actually exists
		if ( fileWorkareaCanvasExist(canvas, canvas.getCanvasName()) )
		{
			// Creates a file object(not the actual file)
			File file = new File("./resource/Data/" + canvas.getCanvasName()
					+ ".dat");

			// Creates a new file object with the new name
			File fileNew = new File("./resource/Data/" + newName + ".dat");

			// Renames the old file
			boolean result = file.renameTo(fileNew);

			// If the rename was not possible
			if ( !result )
			{
				JOptionPane.showMessageDialog(null, PrimeMain1.texts
						.getString("renameWasNotPossibleMsg"), PrimeMain1.texts
						.getString("error"), JOptionPane.ERROR_MESSAGE);
			}
			else
			{

				// Sets the WorkareaCanvas name
				canvas.setCanvasName(newName);

				// Saves the WorkareaCanvas to file
				saveWorkareaCanvas(canvas, fileNew);

				// Updates the JTree
				PrimeMain1.updatePrimeTree();

				return true;
			}
		}

		return false;
	}




	/**
	 * This function removes WorkareaCanvas with the given name from the system.
	 * It also deletes the file that contains the workareaCanvas from the file
	 * system, so this is permanent.
	 * 
	 * @param fileNode
	 *            The file that contains the WorkareaCanvas.
	 */
	public static boolean deleteWorkareaCanvas(FileTreeNode fileNode)
	{
		// Checks on the file before any work is done
		File file = fileNode.getFile();

		return deleteWorkareaCanvas(file);
	}



	/**
	 * This function removes WorkareaCanvas with the given name from the system.
	 * It also deletes the file that contains the workareaCanvas from the file
	 * system, so this is permanent.
	 * 
	 * @param fileNode
	 *            The file that contains the WorkareaCanvas.
	 */
	public static boolean deleteWorkareaCanvas(WorkareaCanvas canvas)
	{
		// Creates a file object(not the actual file)
		File file = new File("./resource/Data/" + canvas.getCanvasName()
				+ ".dat");

		return deleteWorkareaCanvas(file);
	}




	/**
	 * This function removes WorkareaCanvas with the given name from the system.
	 * It also deletes the file that contains the workareaCanvas from the file
	 * system, so this is permanent.
	 * 
	 * @param fileNode
	 *            The file that contains the WorkareaCanvas.
	 * @param tree
	 *            The JTree that the fileNode will be deleted from.
	 */
	public static boolean deleteWorkareaCanvas(File file)
	{

		// If the file does not exist
		if ( !file.exists() )
		{
			JOptionPane.showMessageDialog(null, "This file\n" + file.getName()
					+ "\n" + "does not exist in location\n"
					+ file.getAbsolutePath(), "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;
		}

		// If the file is a directory
		if ( file.isDirectory() )
		{
			JOptionPane.showMessageDialog(null, "This \n" + file.getName()
					+ "\n" + "is a directory at\n" + file.getAbsolutePath(),
					"Error", JOptionPane.ERROR_MESSAGE);

			return false;
		}

		// If the file can not be written to(hence not deleted)
		if ( !file.canWrite() )
		{
			JOptionPane.showMessageDialog(null, "This file\n" + file.getName()
					+ "\n" + "is write protected.", "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;
		}

		// Gets the canvas name, if possible, from the file
		String canvasName = getCanvasName(file);

		// Creates an empty canvas
		WorkareaCanvas canvas = null;

		// If the name of the canvas was possible to the from the file
		if ( canvasName != null )
		{
			canvas = DesktopCanvasManagment.findCanvas(canvasName);
		}

		// No open canvas was found
		if ( canvas == null )
		{
			// Attempt to delete it
			boolean success = file.delete();

			if ( !success )
			{
				JOptionPane.showMessageDialog(null, "The file\n"
						+ file.getName() + "\n"
						+ "was NOT successfully deleted.", "Error",
						JOptionPane.ERROR_MESSAGE);

				return false;
			}
			else
			{
				// Reloads
				PrimeMain1.updatePrimeTree();

				JOptionPane.showMessageDialog(null, "The file\n"
						+ file.getName() + "\n" + "was successfully deleted.",
						"Success", JOptionPane.PLAIN_MESSAGE);

				return true;
			}
		}
		// There was an open canvas found
		else
		{
			// TODO - Close canvas, remove it from the canvas array and then
			// delete the canvas

			// Attempt to delete it
			boolean success = file.delete();

			if ( !success )
			{
				JOptionPane.showMessageDialog(null, "The file\n"
						+ file.getName() + "\n"
						+ "was NOT successfully deleted.", "Error",
						JOptionPane.ERROR_MESSAGE);

				return false;
			}
			else
			{
				// Reloads
				PrimeMain1.updatePrimeTree();

				// Removes the WorkareScroll with the canvas
				PrimeMain1.workTab.removeTabWithCanvas(canvasName, false);

				// Removed the canvas from the systems canvas array
				DesktopCanvasManagment.removeWorkareaCanvas(canvas);

				JOptionPane.showMessageDialog(null, "The file\n"
						+ file.getName() + "\n" + "was successfully deleted.",
						"Success", JOptionPane.PLAIN_MESSAGE);

				return true;
			}
		}
	}




	/**
	 * Creates a new WorkareaCanvas. The user is asked what the name of the new
	 * workareaCanvas will be. The new WorkareaCanvas is then opened in the
	 * workarea.
	 */
	public static void newWorkareaCanvas()
	{
		new graphics.GUI.workareaCanvas.CreateNewWorkareaCanvas();
	}



	/**
	 * Creates a new WorkareaCanvas. The new WorkareaCanvas is opened in the
	 * workarea. The name of the new WorkareaCanvas will be the given String.
	 * 
	 * @param nameOfCanvas
	 */
	public static boolean newWorkareaCanvas(String nameOfCanvas)
	{
		return newCanvas(nameOfCanvas, null, null, null, null);
	}



	/**
	 * Creates a new WorkareaCanvas. The new WorkareaCanvas is opened in the
	 * workarea. The name of the new WorkareaCanvas will be the given String.
	 * 
	 * @param nameOfCanvas
	 */
	public static boolean newWorkareaCanvas(String nameOfCanvas,
			String netmask, String IPfrom, String IPto, String networkDesc)
	{
		return newCanvas(nameOfCanvas, netmask, IPfrom, IPto, networkDesc);
	}



	/**
	 * Creates a new WorkareaCanvas with the given name. The name is checked to
	 * see if it matched with the allowed symbols, which are letters, numbers
	 * and underscore. After the WorkareaCanvas is created it is added to the
	 * programs workarea.
	 * 
	 * @param nameOfCanvas
	 *            The name that the WorkareaCanvas will have, after being
	 *            checked.
	 */
	private static boolean newCanvas(String nameOfCanvas, String netmask,
			String IPfrom, String IPto, String networkDesc)
	{

		// IF the user has canceled
		if ( nameOfCanvas != null )
		{
			if ( Pattern.matches("([a-zA-Z������_0-9 ])*", nameOfCanvas) )
			{
				// Checks whether or not there exist a canvas with the same
				if ( !DesktopCanvasManagment.canvasExists(nameOfCanvas) )
				{
					WorkareaCanvas canvas = new WorkareaCanvas(nameOfCanvas);
					ActionsAdder.makeWorkareaCanvasReady(canvas);

					if ( netmask != null && IPfrom != null && IPto != null )
					{
						// If the netmask, ip from and ip to strings are not ""
						if ( (!netmask.equals("")) && (!(IPfrom.equals("")))
								&& (!(IPto.equals(""))) )
						{
							// Creates a new network info object
							WorkareaCanvasNetworkInfo netInfo = canvas
									.getNetworkInfo();

							// Sets the netmask of the network
							netInfo.setNetmask(netmask);

							// Sets the IP range start of the network
							netInfo.setIpRangeFrom(IPfrom);

							// Sets the IP range end of the network
							netInfo.setIpRangeTo(IPto);

							// Sets the network notes
							netInfo.setNetworkNotes(networkDesc);

							// Sets the newly created network info object as the
							// networks info object
							canvas.setNetworkInfo(netInfo);
						}
					}

					// First creates the WorkareaSceneScroll object that will
					// hold
					WorkareaSceneScroll newScroll = new WorkareaSceneScroll(
							canvas);

					// Then we add the JScrollPane to the Screen
					PrimeMain1.workTab.createNewCanvasTab(newScroll, -1);

					// Reloads the JTree
					PrimeMain1.updatePrimeTree();

					PrimeMain1.workTab.revalidate();
					PrimeMain1.workTab.repaint();

				}
				else
				{
					JOptionPane.showMessageDialog(null, PrimeMain1.texts
							.getString("canvasExistWithNameMsg"),
							PrimeMain1.texts.getString("error"),
							JOptionPane.ERROR_MESSAGE);

					return false;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, PrimeMain1.texts
						.getString("canvasNameNotValidMsg"), PrimeMain1.texts
						.getString("error"), JOptionPane.ERROR_MESSAGE);

				return false;
			}
		}

		return true;

		// Maybe add the new canvas to the JTree and save it?
	}





	/**
	 * This method opens a WorkareaCanvas from the given file. It adds the
	 * opened canvas the systems Workarea where the WorkareaCanvas can be
	 * edited. It also adds all the objects and connections to the canvas.
	 * 
	 * @param file
	 */
	public static void openWorkareaCanvas(File file)
	{
		openCanvas(file);
	}



	/**
	 * This method opens a WorkareaCanvas from the given file. It adds the
	 * opened canvas the systems Workarea where the WorkareaCanvas can be
	 * edited. It also adds all the objects and connections to the canvas. This
	 * method creates a File from the given string.
	 * 
	 * @param canvasName
	 */
	public static void openWorkareaCanvas(String canvasName)
	{
		File file = new File("./Data/" + canvasName + ".dat");

		openCanvas(file);
	}




	/**
	 * This method opens a WorkareaCanvas from the given file. It adds the
	 * opened canvas the systems Workarea where the WorkareaCanvas can be
	 * edited. It also adds all the objects and connections to the canvas.
	 * 
	 * @param file
	 */
	@SuppressWarnings("unchecked")
	private static void openCanvas(File file)
	{
		WorkareaCanvas canvas = new WorkareaCanvas();

		try
		{
			FileInputStream fin = new FileInputStream(file);

			ObjectInputStream ois = new ObjectInputStream(fin);

			// The name of the canvas
			String name = (String) ois.readObject();
			canvas.setCanvasName(name);

			// The serial of the network
			double serial = ois.readDouble();
			canvas.setSerial(serial);

			// Reads the WorkareaCanvasNetworkInfo
			canvas.setNetworkInfo((WorkareaCanvasNetworkInfo) ois.readObject());


			// READS THE OBJECTS THAT ARE TO BE PLACED ON THE CANVAS


			// The ArrayList that will hold the Objects
			ArrayList<Object> objectList = new ArrayList<Object>();

			// Reads inn the ArrayList from the file stream
			objectList = (ArrayList<Object>) ois.readObject();

			// The size of the new Objects array
			int objectArraySize = 0;

			// Iterates through the Object list
			for ( Iterator it = objectList.iterator(); it.hasNext(); )
			{
				objectArraySize++;
				it.next();
			}

			// If there were any objects found
			if ( objectArraySize > 0 )
			{
				// The objects array
				Object[] objects = new Object[objectArraySize];

				// The index of the objects array
				int objectIndex = 0;

				// Iterates through the list and adds the objects to the objects
				// array
				for ( Iterator it = objectList.iterator(); it.hasNext(); )
				{
					objects[objectIndex] = (Object) it.next();
					objectIndex++;
				}

				// Goes through the array of objects and adds them to the newly
				// made canvas
				for ( int i = 0; i < objects.length; i++ )
				{
					if ( objects[i] != null )
					{
						Class<?> objClass = GraphicalFunctions
								.getObjectClass(objects[i]);
						ImageIcon icon = GraphicalFunctions
								.getImageIconForObject(objects[i]);

						WidgetObject added = WorkareaCanvasActions
								.addObjectToCanvas(objects[i], canvas,
										objClass, icon);

						// Adds the actions that the new widget supports
						ActionsAdder.makeWidgetObjectReady(canvas, added);
					}
				}
			}

			// END OF READ OBJECTS



			// READ THE CONNECTIONS THAT TO BE PLACED ON THE CANVAS



			// The ArrayList that will hold all the connections
			ArrayList<Connection> connectionList = new ArrayList<Connection>();

			// Reads inn the ArrayList from the file stream
			connectionList = (ArrayList<Connection>) ois.readObject();

			// The size of the new Connection array
			int connectionArraySize = 0;

			// Iterates through the connection list
			for ( Iterator it = connectionList.iterator(); it.hasNext(); )
			{
				connectionArraySize++;
				it.next();
			}

			// If there were any objects found
			if ( connectionArraySize > 0 )
			{
				// The connection array
				Connection[] connections = new Connection[connectionArraySize];

				// The index of the connection array
				int connectionIndex = 0;

				// Iterates through the list and adds the connections to the
				// connections array
				for ( Iterator it = connectionList.iterator(); it.hasNext(); )
				{
					connections[connectionIndex] = (Connection) it.next();
					connectionIndex++;
				}


				// Goes through the entire connections array and adds the
				// connections to the WorkareaCanvas
				for ( int i = 0; i < connections.length; i++ )
				{
					if ( connections[i] != null )
					{
						// Creates the connection between the two devices on the
						// scene.
						WidgetExtendedConnection connection = new WidgetExtendedConnection(
								canvas.getScene(), connections[i]);


						// Find the two object which are to be connected on the
						// canvas
						WidgetObject sourceWidget = CanvasManagment
								.findWidgetObject(connections[i].getObject1(),
										canvas);
						WidgetObject targetWidget = CanvasManagment
								.findWidgetObject(connections[i].getObject2(),
										canvas);

						// Creates the whole connection with all actions
						connection = ConnectionManagment
								.createWidgetExtendedConnection(canvas,
										connections[i], connection,
										sourceWidget, targetWidget);

						// Adds the different actions
						ActionsAdder.makeWidgetConnectionReady(canvas,
								connection);

						// Add the connection the connection layer
						canvas.getConnectionLayer().addChild(connection);
					}
				}
			}

			// END OF CONNECTIONS



			// READ THE NETWORK ROOMS


			// The ArrayList that will hold all the rooms
			ArrayList<Room> roomList = new ArrayList<Room>();

			// Reads inn the ArrayList from the file stream
			roomList = (ArrayList<Room>) ois.readObject();

			// The size of the new Room array
			int roomArraySize = 0;

			// Iterates through the room list
			for ( Iterator it = roomList.iterator(); it.hasNext(); )
			{
				roomArraySize++;
				it.next();
			}

			// If there were any objects found
			if ( roomArraySize > 0 )
			{
				// The room array
				Room[] rooms = new Room[roomArraySize];

				// The index of the room array
				int roomIndex = 0;

				// Iterates through the list and adds the room to the room array
				for ( Iterator it = roomList.iterator(); it.hasNext(); )
				{
					rooms[roomIndex] = (Room) it.next();
					roomIndex++;
				}

				// Goes through the entire connections array and adds the
				// connections to the WorkareaCanvas
				for ( int i = 0; i < rooms.length; i++ )
				{
					if ( rooms[i] != null )
					{
						WidgetRoom room = RoomManagment.addRoom(canvas,
								rooms[i]);

						// Adds the actions supported by the WidgetRoom
						ActionsAdder.makeWidgetRoomReady(canvas, room);
					}
				}
			}


			// END OF READ NETWORK ROOMS



			ois.close();
		}
		catch ( FileNotFoundException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( ClassNotFoundException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		PrimeMain1.getWorkarea().createNewCanvasTab(canvas);
	}








	/**
	 * Finds the first part of any given string divided by one "." symbol.
	 * 
	 * @param file
	 * @return Returns the first part of the given file name without the ".*".
	 */
	public static String getCanvasName(File file)
	{
		String[] names = file.getName().split("\\.");

		// As long as there are only two parts to the filename, *name* and
		// ".dat"
		if ( names.length == 2 && names[1].equalsIgnoreCase("dat") )
		{
			String name = names[0];

			return name;
		}


		return null;
	}




	/**
	 * Finds the first part of any given string divided by "." symbol.
	 * 
	 * @param string
	 * @return Returns the first part of the given string without the ".*".
	 */
	public String getCanvasName(String string)
	{
		String[] names = string.split("\\.");

		// As long as there are only two parts to the filename, *name* and
		// ".dat"
		if ( names.length == 2 && names[1].equalsIgnoreCase("dat") )
		{
			String name = names[0];

			return name;
		}


		return null;
	}



	/**
	 * This function goes through all the given WorkareaCanvases and checks if
	 * the canvas has been changed since last save. If it has been changed, it
	 * saves that canvas.
	 * 
	 * @param canvases
	 *            The workareaCanvases that are to be checked and saved
	 */
	public static void saveCanvases(WorkareaCanvas[] canvases)
	{
		for ( int i = 0; i < canvases.length; i++ )
		{
			if ( canvases[i] != null )
			{
				// // If the canvas has not been saved(since the last change)
				// and there has been some change
				// if ( canvases[i].isSaved() != true && canvases[i].isChanged()
				// == true )
				// {
				saveWorkareaCanvas(canvases[i]);
				// }
			}
		}
	}





	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 */
	public static void exportWorkareaCanvas(WorkareaCanvas canvas)
	{
		// The JFileChoose where the user will save the export
		JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);

		// Adds the filters
		fc.addChoosableFileFilter(new JPGFilter());
		fc.addChoosableFileFilter(new PNGFilter());

		// Sets the selected file to the name of the
		fc.setSelectedFile(new File(canvas.getCanvasName()));

		// Shows the File chooser
		int returnVal = fc.showSaveDialog(null);

		// If the save button is pressed
		if ( returnVal == JFileChooser.APPROVE_OPTION )
		{
			// Gets the file written/selected
			File file = fc.getSelectedFile();

			// Gets the absolute path of the file
			String path = file.getAbsolutePath();

			// Gets the extension that is currently selected
			String extension = fc.getFileFilter().getDescription();


			if ( extension.equals(".jpg") )
			{
				// If the file does not ends with the extension
				if ( !(path.endsWith(extension)) )
				{
					// Creates a new file with the path of the file and the
					// extension
					file = new File(path + extension);
				}


				try
				{
					CanvasExporter.createImage(PrimeMain1.currentCanvas, file,
							CanvasExporter.ImageType.JPG,
							CanvasExporter.ZoomType.ACTUAL_SIZE, false, false,
							100, 1600, 1400);
				}
				catch ( IOException e )
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if ( extension.equals(".png") )
			{
				// If the file does not ends with the extension
				if ( !(path.endsWith(extension)) )
				{
					// Creates a new file with the path of the file and the
					// extension
					file = new File(path + extension);
				}


				try
				{
					CanvasExporter.createImage(PrimeMain1.currentCanvas, file,
							CanvasExporter.ImageType.PNG,
							CanvasExporter.ZoomType.ACTUAL_SIZE, false, false,
							100, 1000, 1000);
				}
				catch ( IOException e )
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}



	/**
	 * TODO - Description
	 */
	public static void saveObjectsFile()
	{
		File file = new File("./resource/objects.obj");


		// If the Objects file exists
		if ( file.exists() )
		{
			// If the pointer is to a file and not anything else
			if ( file.isFile() )
			{
				// If the file can be written to
				if ( file.canWrite() )
				{
					try
					{
						FileOutputStream fout = new FileOutputStream(file);

						// The object stream file
						ObjectOutputStream oos = new ObjectOutputStream(fout);

						// Writes out the systems list of Objects even if it is
						// empty
						oos.writeObject(PrimeMain1.objectlist);

						// Flushes the stream
						oos.flush();

						// Closes the stream
						oos.close();
					}
					catch ( Exception e )
					{
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("saveObjectsFile - file.canWrite()");
				}
			}
			else
			{
				System.out.println("saveObjectsFile - file.isFile()");
			}
		}
		else
		{
			try
			{
				FileOutputStream fout = new FileOutputStream(file);

				// The object stream file
				ObjectOutputStream oos = new ObjectOutputStream(fout);

				// Writes out the systems list of Objects even if it is
				// empty
				oos.writeObject(PrimeMain1.objectlist);

				// Flushes the stream
				oos.flush();

				// Closes the stream
				oos.close();
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
	}



	/**
	 * TODO - Description
	 */
	@SuppressWarnings("unchecked")
	public static void openObjectsFile()
	{
		File file = new File("./resource/objects.obj");


		// If the Objects file exists
		if ( file.exists() )
		{
			// If the pointer is a file and nothing else
			if ( file.isFile() )
			{
				// If the file can be read
				if ( file.canRead() )
				{
					try
					{
						FileInputStream fin = new FileInputStream(file);

						ObjectInputStream ois = new ObjectInputStream(fin);

						// Reads inn the ArrayList from the file stream
						PrimeMain1.objectlist = (ArrayList<Object>) ois
								.readObject();

						ois.close();
					}
					catch ( Exception e )
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("openObjectsFile - file.canRead()");
				}
			}
			else
			{
				// FIXME - FIX this
				System.out.println("openObjectsFile - file.isFile()");
			}
		}
		else
		{
			System.out.println("openObjectsFile - file.exists()");
		}
	}



	/**
	 * TODO - Description
	 */
	public static boolean objectsFileExists()
	{
		File file = new File("./resource/objects.obj");

		if ( file.exists() )
		{
			if ( file.isFile() )
			{
				return true;
			}
		}

		return false;
	}
}
