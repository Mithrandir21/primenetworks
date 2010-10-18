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


import exceptions.CanvasNotFound;
import graphics.GraphicalFunctions;
import graphics.PrimeMain;
import graphics.GUI.CustomFileFilters.DATFilter;
import graphics.GUI.CustomFileFilters.JPGFilter;
import graphics.GUI.CustomFileFilters.OBJFilter;
import graphics.GUI.CustomFileFilters.PNGFilter;
import graphics.GUI.ghostGlass.GhostGlassPane;
import graphics.GUI.selectArea.PrimeJTree.FileTreeNode;
import graphics.GUI.workareaCanvas.WorkareaSceneScroll;
import graphics.GUI.workareaCanvas.providers.ActionsAdder;
import groups.Group;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import objects.Object;
import objects.Room;
import objects.softwareObjects.OperatingSystem;
import widgetManipulation.NetworkRules;
import widgetManipulation.WidgetNetworkInfo;
import widgetManipulation.WorkareaCanvasNetworkInfo;
import widgets.WidgetObject;
import widgets.WidgetRoom;
import widgets.WorkareaCanvas;
import canvasManipulation.CanvasExporter;
import connections.Connection;
import connections.WidgetExtendedConnection;


/**
 * This class provides static functions that deal with the actual saving to
 * file, reading from file and so on. {@link WorkareaCanvas WorkareaCanvases}
 * are saved and loaded here. There are also testing
 * and control functions.
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
	 * @param verify
	 *            Verify if the file exists and if so can be overwritten.
	 */
	public static void saveWorkareaCanvas(WorkareaCanvas canvas, boolean verify)
	{
		File file = new File("./resource/Data/" + canvas.getCanvasName()
				+ File.separator + canvas.getCanvasName() + ".dat");

		// If the file does not exist
		if ( !(file.exists()) )
		{
			File folder = new File("./resource/Data/" + canvas.getCanvasName());

			// If the folder does not exist
			if ( !(folder.exists()) )
			{
				folder.mkdir();
			}
		}


		saveCanvas(canvas, file, verify);
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
		saveCanvas(canvas, file, true);
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
	 * @param verify
	 *            Verify if the file exists and if so can be overwritten
	 */
	private static boolean saveCanvas(WorkareaCanvas canvas, File file,
			boolean verify)
	{
		// Revalidates the locations of the objects on the scene
		canvas.revalidateWidgetLocations();

		boolean verified = true;

		if ( verify )
		{
			verified = checkAndVerify(canvas);
		}

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

				// Writes out the NetworkRules
				oos.writeObject(canvas.getRules());

				// Writes out the NetworkGroups
				oos.writeObject(canvas.getNetworkGroups());



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



				// WRITE WIDGETNETWORKINFO INSIDE THE WIDGETS

				// The widgets on the scene
				WidgetObject[] widgets = canvas.getWidgetObjectsOnTheScene();

				// The ArrayList of the WidgetNetworkInfo on the canvas
				ArrayList<WidgetNetworkInfo> widgetList = new ArrayList<WidgetNetworkInfo>();


				// There must be some widgets on the canvas for there to be
				// saved any widgetObjects
				if ( widgets.length > 0 )
				{
					// Goes through all the widgets on the canvas and adds the
					// WidgetNetworkInfo inside the widget to the arraylist
					for ( int i = 0; i < widgets.length; i++ )
					{
						widgetList.add(widgets[i].getWidgetNetworkInfo());
					}

				}

				// Writes out the WidgetNetworkInfo in the form of an arraylist,
				// even if
				// it is empty
				oos.writeObject(widgetList);

				oos.flush();


				// END OF WRITE WIDGETNETWORKINFO


				// WRITE CONNECTIONS ON THE CANVAS

				// The WidgetExtendedConnections on the scene of the canvas
				WidgetExtendedConnection[] widCons = canvas
						.getWidgetExtendedConnectionsOnTheScene();

				// The ArrayList that will hold the connections
				ArrayList<Connection> widConList = new ArrayList<Connection>();

				// The ArrayList that will hold the Points of the
				// WidgetExtendedConnection
				ArrayList<List<Point>> widConPoints = new ArrayList<List<Point>>();


				// The must be at least on connection on the canvas for there to
				// be saved any connections
				if ( widCons.length > 0 && widCons[0] != null )
				{
					// Goes through all the connections on the canvas and adds
					// them to the ArrayList
					for ( int i = 0; i < widCons.length; i++ )
					{
						// Adds the connection in the WidgetExtendedConnections
						widConList.add(widCons[i].getConnection());

						// Adds the List of Points inside the
						// WidgetExtendedConnections
						widConPoints.add(widCons[i].getControlPoints());
					}
				}


				// Writes out the connections ArrayList, even if it is empty
				oos.writeObject(widConList);

				// Writes out the Points ArrayList, even if it is empty
				oos.writeObject(widConPoints);

				oos.flush();


				// // The canvas connections
				// Connection[] connections = canvas.getConnections();
				//
				// // The ArrayList that will hold the connections
				// ArrayList<Connection> connectionList = new
				// ArrayList<Connection>();
				//
				// // The must be at least on connection on the canvas for there
				// to
				// // be saved any connections
				// if ( connections.length > 0 && connections[0] != null )
				// {
				// // Goes through all the connections on the canvas and adds
				// // them to the ArrayList
				// for ( int i = 0; i < connections.length; i++ )
				// {
				// connectionList.add(connections[i]);
				// }
				// }
				//
				// // Writes out the connections ArrayList, even if it is empty
				// oos.writeObject(connectionList);
				//
				// oos.flush();

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
			PrimeMain.updateNetworkSelectionArea();

			PrimeMain.workTab.revalidate();
			PrimeMain.workTab.repaint();

			return true;
		}

		return false;
	}



	/**
	 * Saves the given {@link NetworkRules} instance to a file to preserve the
	 * standard rules.
	 */
	public static boolean saveStandardRules()
	{
		File file = new File("./resource/rules.dat");

		return saveStandardRules(file);
	}



	/**
	 * Saves the given {@link NetworkRules} instance to the given file to
	 * preserve the standard rules.
	 */
	public static boolean saveStandardRules(File file)
	{
		if ( PrimeMain.standardRules != null && file != null )
		{
			try
			{
				FileOutputStream fout = new FileOutputStream(file);

				// The object stream file
				ObjectOutputStream oos = new ObjectOutputStream(fout);

				oos.writeObject(PrimeMain.standardRules);

				oos.flush();
				oos.close();

				return true;
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}

		return false;
	}



	/**
	 * Saves the given {@link NetworkRules} instance to a file to preserve the
	 * standard rules.
	 */
	public static boolean saveCustomOS()
	{
		File file = new File("./resource/customOS.dat");

		// If there are no custom OSs, the custom file will be deleted.
		if ( PrimeMain.system_custom_OS.isEmpty() && file != null )
		{
			file.delete();

			return true;
		}

		return saveCustomOS(file);
	}


	/**
	 * TODO - Description
	 * 
	 */
	public static boolean saveCustomOS(File file)
	{
		if ( PrimeMain.system_custom_OS != null
				&& (!PrimeMain.system_custom_OS.isEmpty()) && file != null )
		{
			try
			{
				FileOutputStream fout = new FileOutputStream(file);

				// The object stream file
				ObjectOutputStream oos = new ObjectOutputStream(fout);

				// The must be at least on room on the canvas for there to be
				// saved any rooms
				if ( !PrimeMain.system_custom_OS.isEmpty() )
				{
					// get an Iterator object for ArrayList using iterator()
					// method.
					Iterator<OperatingSystem> itr = PrimeMain.system_custom_OS
							.iterator();

					while ( itr.hasNext() )
					{
						oos.writeObject(itr.next());
					}

					// Writes a null to indicate the end of the file for read
					// in.
					oos.writeObject(null);
				}

				oos.flush();
				oos.close();

				return true;
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}

		return false;
	}




	/**
	 * Saves the {@link Settings} instance to a file to preserve the users
	 * settings.
	 */
	public static void saveSettings()
	{
		File file = new File("./resource/settings.dat");

		try
		{
			FileOutputStream fout = new FileOutputStream(file);

			// The object stream file
			ObjectOutputStream oos = new ObjectOutputStream(fout);

			oos.writeBoolean(managment.Settings.showConnectionErrorMessages);
			oos.writeBoolean(managment.Settings.showConnectionNoticeMessages);
			oos.writeBoolean(managment.Settings.showConnectionWarningMessages);
			oos.writeBoolean(managment.Settings.showHardwareErrorMessages);
			oos.writeBoolean(managment.Settings.showHardwareNoticeMessages);
			oos.writeBoolean(managment.Settings.showHardwareWarningMessages);
			oos.writeBoolean(managment.Settings.showNetworkErrorMessages);
			oos.writeBoolean(managment.Settings.showNetworkNoticeMessages);
			oos.writeBoolean(managment.Settings.showNetworkWarningMessages);
			oos.writeBoolean(managment.Settings.showSoftwareErrorMessages);
			oos.writeBoolean(managment.Settings.showSoftwareNoticeMessages);
			oos.writeBoolean(managment.Settings.showSoftwareWarningMessages);
			oos.writeBoolean(managment.Settings.showTOTD);
			oos.writeBoolean(managment.Settings.showOSicon);
			oos.writeBoolean(managment.Settings.showIP);
			oos.writeUTF(managment.Settings.primeLocale.toString());
			oos.writeBoolean(managment.Settings.originalImages);

			oos.flush();
			oos.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}



	/**
	 * This function checks whether or not there exist a file containing a
	 * WorkareaCanvas with the same name and serial as the given WorkareaCanvas.
	 * If not true is returned. If there exists a WorkareaCanvas with the same
	 * name, but not serial number the user is asked to verify overwriting that
	 * file.
	 * 
	 * @param canvas
	 * @return
	 */
	private static boolean checkAndVerify(String canvasName)
	{
		// Creates a file object(not the actual file)
		File file = new File("./resource/Data/" + canvasName + File.separator
				+ canvasName + ".dat");

		// If the file(network) exists
		if ( file.exists() )
		{
			try
			{
				FileInputStream fin = new FileInputStream(file);

				ObjectInputStream ois = new ObjectInputStream(fin);

				// The name of the file canvas
				String name = (String) ois.readObject();


				ois.close();

				// If the name of the file network is the same as the name of
				// the given WorkareaCanvas
				if ( name.equalsIgnoreCase(canvasName) )
				{
					int answer = JOptionPane
							.showConfirmDialog(
									null,
									PrimeMain.texts
											.getString("overwriteNetworkWithTheSameNameMsg"),
									PrimeMain.texts.getString("overwrite"),
									JOptionPane.YES_NO_OPTION);

					// The user answers "yes"
					if ( answer == 0 )
					{
						return true;
					}

					return false;
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
	 * This function checks whether or not there exist a file containing a
	 * WorkareaCanvas with the same name and serial as the given WorkareaCanvas.
	 * If not true is returned. If there exists a WorkareaCanvas with the same
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
				+ File.separator + canvas.getCanvasName() + ".dat");

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
										PrimeMain.texts
												.getString("overwriteNetworkWithTheSameNameMsg"),
										PrimeMain.texts.getString("overwrite"),
										JOptionPane.YES_NO_OPTION);

						// The user answers "yes"
						if ( answer == 0 )
						{
							return true;
						}

						return false;
					}

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


		return true;
	}



	/**
	 * The method check whether or not a file exist with the given newName
	 * String. If it does, the file is checked for a WorkareaCanvas
	 * 
	 * @param canvas
	 */
	public static boolean fileWorkareaCanvasExist(String newName)
	{
		// Creates a file object(not the actual file)
		File file = new File("./resource/Data/" + newName + File.separator
				+ newName + ".dat");

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
	 * function checks if the {@link WorkareaCanvas} exists. The function also
	 * changes the directory name of the network folder to match the network
	 * name.
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
		if ( fileWorkareaCanvasExist(canvas.getCanvasName()) )
		{
			// Creates a file object(not the actual file)
			File file = new File("./resource/Data/" + canvas.getCanvasName()
					+ File.separator + canvas.getCanvasName() + ".dat");

			// Creates a new file object with the new name(in the old folder)
			File fileNew = new File("./resource/Data/" + canvas.getCanvasName()
					+ File.separator + newName + ".dat");

			// Renames the old file
			boolean result = file.renameTo(fileNew);


			// The old folder
			File oldFolder = new File("./resource/Data/"
					+ canvas.getCanvasName());

			// The new folder
			File newFolder = new File("./resource/Data/" + newName);

			// Renaming the old folder to the new folder
			boolean folderResult = oldFolder.renameTo(newFolder);


			// If the rename was not possible
			if ( !result )
			{
				JOptionPane.showMessageDialog(null,
						PrimeMain.texts.getString("renameWasNotPossibleMsg"),
						PrimeMain.texts.getString("error"),
						JOptionPane.ERROR_MESSAGE);

				// If the file was not changed, but the folder was changed the
				// folder has to be changed back.
				if ( folderResult )
				{
					newFolder.renameTo(oldFolder);
				}
			}
			else
			{
				// Creates a new file object with the new name(in the new
				// folder)
				fileNew = new File("./resource/Data/" + newName
						+ File.separator + newName + ".dat");


				// Sets the WorkareaCanvas name
				canvas.setCanvasName(newName);

				// Saves the WorkareaCanvas to file
				saveWorkareaCanvas(canvas, fileNew);

				// Updates the JTree
				PrimeMain.updateNetworkSelectionArea();

				canvas.setSaved(true);
				canvas.setChanged(false);

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
	 * @param canvas
	 *            The WorkareaCanvas.
	 */
	public static boolean deleteWorkareaCanvas(WorkareaCanvas canvas)
	{
		// Creates a file object(not the actual file)
		File file = new File("./resource/Data/" + canvas.getCanvasName()
				+ File.separator + canvas.getCanvasName() + ".dat");

		return deleteWorkareaCanvas(file);
	}



	/**
	 * This function removes WorkareaCanvas with the given name from the system.
	 * It also deletes the file that contains the workareaCanvas from the file
	 * system, so this is permanent.
	 * 
	 * @param file
	 *            The file that contains the WorkareaCanvas.
	 */
	public static boolean deleteWorkareaCanvas(File file)
	{
		// If the file does not exist
		if ( !file.exists() )
		{
			JOptionPane.showMessageDialog(
					null,
					"This file" + System.getProperty("line.separator")
							+ file.getName()
							+ System.getProperty("line.separator")
							+ "does not exist in location"
							+ System.getProperty("line.separator")
							+ file.getAbsolutePath(), "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;
		}

		// If the file is a directory
		if ( file.isDirectory() )
		{
			JOptionPane.showMessageDialog(
					null,
					"This" + System.getProperty("line.separator")
							+ file.getName()
							+ System.getProperty("line.separator")
							+ "is a directory at"
							+ System.getProperty("line.separator")
							+ file.getAbsolutePath(), "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;
		}

		// If the file can not be written to(hence not deleted)
		if ( !file.canWrite() )
		{
			JOptionPane.showMessageDialog(
					null,
					"This file" + System.getProperty("line.separator")
							+ file.getName()
							+ System.getProperty("line.separator")
							+ "is write protected.", "Error",
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
			int answer = JOptionPane.showConfirmDialog(null,
					PrimeMain.texts.getString("actionDeleteWorkareaCanvasMsg")
							+ canvasName + "?",
					PrimeMain.texts.getString("confirm"),
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

			if ( answer == 0 )
			{
				// Attempt to delete it
				boolean success = file.delete();

				if ( !success )
				{
					JOptionPane.showMessageDialog(
							null,
							"The file" + System.getProperty("line.separator")
									+ file.getName()
									+ System.getProperty("line.separator")
									+ "was NOT successfully deleted.", "Error",
							JOptionPane.ERROR_MESSAGE);

					return false;
				}
				else
				{
					// If the canvas has been deleted, the canvas folder must be
					// deleted.
					deleteDir(file.getParentFile());


					// Reloads
					PrimeMain.updateNetworkSelectionArea();

					return true;
				}
			}
			return false;
		}
		// There was an open canvas found
		else
		{
			// Gets the serial of the canvas in the given file
			double serial = getWorkareaCanvasSerialFromFile(file);

			// A boolean on whether the open canvas is the same as the file.
			boolean sameCanvas = false;

			// Checking to see whether the open canvas has the serial number as
			// the canvas in the file.
			if ( serial == canvas.getSerial() )
			{
				sameCanvas = true;
			}

			int answer = JOptionPane.showConfirmDialog(null,
					PrimeMain.texts.getString("actionDeleteWorkareaCanvasMsg")
							+ canvasName + "?",
					PrimeMain.texts.getString("confirm"),
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

			if ( answer == 0 )
			{
				// Attempt to delete it
				boolean success = file.delete();

				if ( !success )
				{
					JOptionPane.showMessageDialog(
							null,
							"The file" + System.getProperty("line.separator")
									+ file.getName()
									+ System.getProperty("line.separator")
									+ "was NOT successfully deleted.", "Error",
							JOptionPane.ERROR_MESSAGE);

					return false;
				}
				else
				{
					// If the canvas has been deleted, the canvas folder must be
					// deleted.
					deleteDir(file.getParentFile());


					// Reloads
					PrimeMain.updateNetworkSelectionArea();

					if ( sameCanvas )
					{
						// Removes the WorkareScroll with the canvas
						try
						{
							PrimeMain.workTab.removeTabWithCanvas(canvasName,
									false);
						}
						catch ( CanvasNotFound e )
						{
							// log.warning("The WorkareaCanvas, "
							// + canvasName
							// +
							// ", was not found in the WorkareaCanvas main register.");
							e.printStackTrace();
						}
					}

					return true;
				}
			}
			return false;
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
	public static boolean newWorkareaCanvas(GhostGlassPane ghostGlass,
			String nameOfCanvas)
	{
		return newCanvas(ghostGlass, nameOfCanvas, null, null, null, null);
	}



	/**
	 * Creates a new WorkareaCanvas. The new WorkareaCanvas is opened in the
	 * workarea. The name of the new WorkareaCanvas will be the given String.
	 * 
	 * @param nameOfCanvas
	 */
	public static boolean newWorkareaCanvas(GhostGlassPane ghostGlass,
			String nameOfCanvas, String netmask, String IPfrom, String IPto,
			String networkDesc)
	{
		return newCanvas(ghostGlass, nameOfCanvas, netmask, IPfrom, IPto,
				networkDesc);
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
	private static boolean newCanvas(GhostGlassPane ghostGlass,
			String nameOfCanvas, String netmask, String IPfrom, String IPto,
			String networkDesc)
	{

		// IF the user has canceled
		if ( nameOfCanvas != null )
		{
			if ( Pattern.matches("([a-zA-Z������_0-9 ])*",
					nameOfCanvas) )
			{
				// Checks whether or not there exist a canvas with the same
				if ( !DesktopCanvasManagment.canvasExists(nameOfCanvas) )
				{
					WorkareaCanvas canvas = new WorkareaCanvas(nameOfCanvas);
					// ActionsAdder.makeWorkareaCanvasReady(ghostGlass, canvas);

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

					// Copies the standard rules to the canvas
					if ( PrimeMain.standardRules != null )
					{
						canvas.setRules(copyRules(PrimeMain.standardRules,
								canvas.getRules()));
					}

					canvas.setSaved(false);
					canvas.setChanged(true);

					// First creates the WorkareaSceneScroll object that will
					// hold
					WorkareaSceneScroll newScroll = new WorkareaSceneScroll(
							ghostGlass, canvas);

					// Then we add the JScrollPane to the Screen
					PrimeMain.workTab.createNewCanvasTab(newScroll, -1);

					// Reloads the JTree
					PrimeMain.updateNetworkSelectionArea();

					PrimeMain.workTab.revalidate();
					PrimeMain.workTab.repaint();

				}
				else
				{
					JOptionPane
							.showMessageDialog(null, PrimeMain.texts
									.getString("canvasExistWithNameMsg"),
									PrimeMain.texts.getString("error"),
									JOptionPane.ERROR_MESSAGE);

					return false;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						PrimeMain.texts.getString("canvasNameNotValidMsg"),
						PrimeMain.texts.getString("error"),
						JOptionPane.ERROR_MESSAGE);

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
		WorkareaCanvas canvas = openCanvasFile(file);

		PrimeMain.getWorkarea().createNewCanvasTab(PrimeMain.glassPane, canvas);

		canvas.setSaved(true);
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
		File file = new File("./resource/Data/" + canvasName + File.separator
				+ canvasName + ".dat");

		WorkareaCanvas canvas = openCanvasFile(file);

		PrimeMain.getWorkarea().createNewCanvasTab(PrimeMain.glassPane, canvas);

		canvas.setSaved(true);
	}



	/**
	 * This method opens a WorkareaCanvas from the given file, but does not add
	 * it to the systems array of canvases. It just returns the
	 * {@link WorkareaCanvas}.
	 * 
	 * @param file
	 */
	@SuppressWarnings("unchecked")
	private static WorkareaCanvas openCanvasFile(File file)
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

			try
			{
				// Reads the WorkareaCanvasNetworkInfo
				canvas.setNetworkInfo((WorkareaCanvasNetworkInfo) ois
						.readObject());
			}
			catch ( Exception e )
			{
				// Creates a new network info object
				canvas.setNetworkInfo(new WorkareaCanvasNetworkInfo(canvas));
			}

			try
			{
				// Reads the NetworkRules
				canvas.setRules((NetworkRules) ois.readObject());
			}
			catch ( IOException e )
			{
				canvas.setRules(new NetworkRules(canvas));
			}

			try
			{
				// Reads the NetworkRules
				canvas.setNetworkGroups((ArrayList<Group>) ois.readObject());
			}
			catch ( IOException e )
			{
				canvas.setNetworkGroups(new ArrayList<Group>());
			}


			// READS THE OBJECTS THAT ARE TO BE PLACED ON THE CANVAS


			// The ArrayList that will hold the Objects
			ArrayList<Object> objectList = new ArrayList<Object>();

			// Reads inn the ArrayList from the file stream
			objectList = (ArrayList<Object>) ois.readObject();

			// The size of the new Objects array
			int objectArraySize = objectList.size();


			// READS THE NETWORKINFO THAT ARE TO BE PLACED INSIDE THE WIDGET


			// The ArrayList that will hold the Widget
			ArrayList<WidgetNetworkInfo> widgetInfoList = new ArrayList<WidgetNetworkInfo>();

			// Reads inn the ArrayList from the file stream
			widgetInfoList = (ArrayList<WidgetNetworkInfo>) ois.readObject();

			// The size of the new Objects array
			int widgetInfoArraySize = 0;

			// Iterates through the Object list
			for ( Iterator<WidgetNetworkInfo> it = widgetInfoList.iterator(); it
					.hasNext(); )
			{
				widgetInfoArraySize++;
				it.next();
			}



			// PUTS THE OBJECTS AND NETWORKINFO TOGETHER AND PLACES A NEW
			// WIDGETS ON THE CANVAS

			// If there were any objects found
			if ( objectArraySize == widgetInfoArraySize && objectArraySize > 0 )
			{
				// PLACES THE OBJECTS INSIDE THE ARRAYLIST INTO AN ARRAY

				// The objects array
				Object[] objects = new Object[objectArraySize];

				// The index of the objects array
				int objectIndex = 0;

				// Iterates through the list and adds the objects to the objects
				// array
				for ( Iterator<Object> it = objectList.iterator(); it.hasNext(); )
				{
					objects[objectIndex] = it.next();
					objectIndex++;
				}


				// PLACES THE WIDGETNETWORKINFO INSIDE THE ARRAYLIST INTO AN
				// ARRAY

				// The WidgetNetworkInfo array
				WidgetNetworkInfo[] widgetNetInfos = new WidgetNetworkInfo[widgetInfoArraySize];

				// The index of the WidgetNetworkInfo array
				int widgetNetInfosIndex = 0;

				// Iterates through the list and adds the objects to the
				// WidgetNetworkInfo array
				for ( Iterator<WidgetNetworkInfo> it = widgetInfoList
						.iterator(); it.hasNext(); )
				{
					widgetNetInfos[widgetNetInfosIndex] = it.next();
					widgetNetInfosIndex++;
				}

				// Goes through the array of objects and adds them to the newly
				// made canvas
				for ( int i = 0; i < objects.length; i++ )
				{
					if ( objects[i] != null && widgetNetInfos[i] != null )
					{
						Class<?> objClass = GraphicalFunctions
								.getObjectClass(objects[i]);
						ImageIcon icon = PrimeMain.objectImageIcons
								.get(objects[i].getClass());

						// Creates a new WidgetObject that will be added to the
						// scene
						WidgetObject newWidgetObject = new WidgetObject(
								canvas.getScene(), objects[i], icon.getImage());

						// Adds the network information about the object
						newWidgetObject.setWidgetNetworkInfo(widgetNetInfos[i]);

						// Adds the given object to the given location
						DesktopCanvasManagment.addWidgetToCanvas(
								newWidgetObject, objects[i].getLocation(),
								canvas, false, true);

						// Adds the actions that the new widget supports
						ActionsAdder.makeWidgetObjectReady(canvas,
								newWidgetObject);
					}
				}
			}

			// END OF READ OBJECTS



			// READ THE CONNECTIONS THAT TO BE PLACED ON THE CANVAS



			// The ArrayList that will hold all the connections
			ArrayList<Connection> connectionList = new ArrayList<Connection>();

			// The ArrayList that will hold the Points of the
			// WidgetExtendedConnection
			ArrayList<List<Point>> widConPoints = new ArrayList<List<Point>>();

			// Reads inn the ArrayList from the file stream
			connectionList = (ArrayList<Connection>) ois.readObject();

			// Reads inn the ArrayList containing List<Point> from the file
			widConPoints = (ArrayList<List<Point>>) ois.readObject();


			// The size of the new Connection array(which is also the size of
			// the Points arraylist)
			int connectionArraySize = connectionList.size();


			// If there were any objects found
			if ( connectionArraySize > 0 )
			{
				// The connection array
				Connection[] connections = new Connection[connectionArraySize];

				// The List<Point> array
				List<Point>[] points = new List[connectionArraySize];

				// The index of the connection array
				int connectionIndex = 0;

				// The index of the List<Point> array
				int pointIndex = 0;


				// Iterates through the list and adds the connections to the
				// connections array
				for ( Iterator<Connection> conIt = connectionList.iterator(); conIt
						.hasNext(); )
				{
					connections[connectionIndex] = conIt.next();
					connectionIndex++;
				}

				// Iterates through the list and adds the List<Point> to the
				// List<Point> array
				for ( Iterator<List<Point>> pointIt = widConPoints.iterator(); pointIt
						.hasNext(); )
				{
					points[pointIndex] = pointIt.next();
					pointIndex++;
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
								canvas, connections[i]);


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

						// Sets the control Points for the
						// WidgetExtendedConnection
						connection.setControlPoints(points[i], true);

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
			int roomArraySize = roomList.size();

			// If there were any objects found
			if ( roomArraySize > 0 )
			{
				// The room array
				Room[] rooms = new Room[roomArraySize];

				// The index of the room array
				int roomIndex = 0;

				// Iterates through the list and adds the room to the room array
				for ( Iterator<Room> it = roomList.iterator(); it.hasNext(); )
				{
					rooms[roomIndex] = it.next();
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


		return canvas;
	}

	/**
	 * This function call the openCustomOSs function to import the users Custom
	 * {@link OperatingSystem}.
	 */
	public static void loadCustomOS()
	{
		// Gets the file written/selected
		File file = new File("./resource/customOS.dat");

		loadCustomOS(file);
	}



	/**
	 * Opens the {@link NetworkRules} instance from a given file and imports
	 * them to the system_custom_OS instance in PrimeMain.
	 * 
	 * @see PrimeMain
	 */
	public static void loadCustomOS(File file)
	{
		// If the Objects file exists
		if ( file.exists() )
		{
			// If the pointer is to a file and not anything else
			if ( file.isFile() )
			{
				// If the file can be written to
				if ( file.canRead() )
				{
					try
					{
						FileInputStream fin = new FileInputStream(file);

						ObjectInputStream ois = new ObjectInputStream(fin);

						OperatingSystem temp;

						if ( !PrimeMain.system_custom_OS.isEmpty() )
						{
							// Clears the list
							PrimeMain.system_custom_OS.clear();
						}

						// While the temp is not null, hence not EOF
						while ( (temp = (OperatingSystem) ois.readObject()) != null )
						{
							// Adds the os to the array list
							PrimeMain.system_custom_OS.add(temp);
						}

						ois.close();
					}
					catch ( Exception e )
					{
						e.printStackTrace();
					}
				}
			}
		}
	}


	/**
	 * Opens the {@link NetworkRules} instance from a given file to preserve the
	 * system standard rules.
	 */
	public static void openStandardRules(File file)
	{
		// If the Objects file exists
		if ( file.exists() )
		{
			// If the pointer is to a file and not anything else
			if ( file.isFile() )
			{
				// If the file can be written to
				if ( file.canRead() )
				{
					try
					{
						FileInputStream fin = new FileInputStream(file);

						ObjectInputStream ois = new ObjectInputStream(fin);

						PrimeMain.standardRules = (NetworkRules) ois
								.readObject();

						ois.close();
					}
					catch ( Exception e )
					{
						e.printStackTrace();
					}
				}
			}
		}
	}



	/**
	 * Opens the {@link NetworkRules} instance from a file to preserve the
	 * system standard rules.
	 */
	public static void openStandardRules()
	{
		File file = new File("./resource/rules.dat");

		openStandardRules(file);
	}



	/**
	 * Opens the {@link Settings} instance from a file to preserve the users
	 * settings from previous sessions.
	 */
	public static void openSettings()
	{
		File file = new File("./resource/settings.dat");

		// If the Objects file exists
		if ( file.exists() )
		{
			// If the pointer is to a file and not anything else
			if ( file.isFile() )
			{
				// If the file can be written to
				if ( file.canRead() )
				{
					try
					{
						FileInputStream fin = new FileInputStream(file);

						ObjectInputStream ois = new ObjectInputStream(fin);


						managment.Settings.showConnectionErrorMessages = ois
								.readBoolean();
						managment.Settings.showConnectionNoticeMessages = ois
								.readBoolean();
						managment.Settings.showConnectionWarningMessages = ois
								.readBoolean();
						managment.Settings.showHardwareErrorMessages = ois
								.readBoolean();
						managment.Settings.showHardwareNoticeMessages = ois
								.readBoolean();
						managment.Settings.showHardwareWarningMessages = ois
								.readBoolean();
						managment.Settings.showNetworkErrorMessages = ois
								.readBoolean();
						managment.Settings.showNetworkNoticeMessages = ois
								.readBoolean();
						managment.Settings.showNetworkWarningMessages = ois
								.readBoolean();
						managment.Settings.showSoftwareErrorMessages = ois
								.readBoolean();
						managment.Settings.showSoftwareNoticeMessages = ois
								.readBoolean();
						managment.Settings.showSoftwareWarningMessages = ois
								.readBoolean();
						managment.Settings.showTOTD = ois.readBoolean();
						managment.Settings.showOSicon = ois.readBoolean();
						managment.Settings.showIP = ois.readBoolean();
						managment.Settings.primeLocale = managment.Settings.systemLocale
								.valueOf(ois.readUTF());
						managment.Settings.originalImages = ois.readBoolean();


						ois.close();
					}
					catch ( Exception e )
					{
						e.printStackTrace();
					}
				}
			}
		}
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
				saveWorkareaCanvas(canvases[i], true);
			}
		}
	}



	/**
	 * This function exports the given {@link WorkareaCanvas} to a .dat file.
	 * The user is presented with a choice on which folder to save the image in.
	 * 
	 * @param canvas
	 */
	public static boolean exportNetwork(WorkareaCanvas canvas)
	{
		// The JFileChoose where the user will save the export
		JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);

		// Adds the filters
		fc.addChoosableFileFilter(new DATFilter());

		// Sets the selected file to the name of the
		fc.setSelectedFile(new File(canvas.getCanvasName()));

		// Shows the File chooser
		int returnVal = fc.showSaveDialog(null);

		// If the save button is pressed
		if ( returnVal == JFileChooser.APPROVE_OPTION )
		{
			// Gets the file written/selected
			File file = fc.getSelectedFile();

			// Creates the file with the right extension
			File output = new File(file.getAbsoluteFile() + ".dat");

			// Whether or not to overwrite
			boolean overwrite = false;

			// IF there already exists a file
			if ( output.exists() )
			{
				int answer = JOptionPane.showConfirmDialog(null,
						PrimeMain.texts.getString("overwriteNetworkExportMsg"),
						PrimeMain.texts.getString("overwrite"),
						JOptionPane.YES_NO_OPTION);

				if ( answer == 0 )
				{
					overwrite = true;
				}
			}
			else
			{
				overwrite = true;
			}


			// Will either overwrite or write a new file
			if ( overwrite )
			{
				return saveCanvas(canvas, output, false);
			}
		}

		return false;
	}



	/**
	 * This function exports the given {@link WorkareaCanvas} to an image. The
	 * image types are JPG and PNG. The user is presented with a choice on which
	 * folder to save the image in.
	 * 
	 * @param canvas
	 */
	public static boolean exportWorkareaCanvasAsImage(WorkareaCanvas canvas)
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

			// Whether or not to overwrite
			boolean overwrite = false;

			// IF there already exists a file
			if ( file.exists() )
			{
				int answer = JOptionPane.showConfirmDialog(null,
						PrimeMain.texts.getString("overwriteNetworkImageMsg"),
						PrimeMain.texts.getString("overwrite"),
						JOptionPane.YES_NO_OPTION);

				if ( answer == 0 )
				{
					overwrite = true;
				}
			}
			else
			{
				overwrite = true;
			}


			// Will either overwrite or write a new file
			if ( overwrite )
			{

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
						CanvasExporter.createImage(PrimeMain.currentCanvas,
								file, CanvasExporter.ImageType.JPG,
								CanvasExporter.ZoomType.ACTUAL_SIZE, false,
								false, 100, 1600, 1400);

						return true;
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
						CanvasExporter.createImage(PrimeMain.currentCanvas,
								file, CanvasExporter.ImageType.PNG,
								CanvasExporter.ZoomType.ACTUAL_SIZE, false,
								false, 100, 1000, 1000);

						return true;
					}
					catch ( IOException e )
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}


		return false;
	}



	/**
	 * This function exports the Standard rules to a file. The file will
	 * have a .dat filetype. The user is presented with a choice on which folder
	 * to save the file in.
	 */
	public static boolean exportStandardRules()
	{
		if ( PrimeMain.standardRules != null )
		{
			// The JFileChoose where the user will save the export
			JFileChooser fc = new JFileChooser();
			fc.setAcceptAllFileFilterUsed(false);

			// Adds the filters
			fc.addChoosableFileFilter(new DATFilter());

			// Sets the selected file to the name of the
			fc.setSelectedFile(new File("Rules"));

			// Shows the File chooser
			int returnVal = fc.showSaveDialog(null);

			// If the users choices a folder
			if ( returnVal == JFileChooser.APPROVE_OPTION )
			{
				File file = fc.getSelectedFile();

				// Creates the file with the right extension
				File output = new File(file.getAbsoluteFile() + ".dat");

				// Whether or not to overwrite
				boolean overwrite = false;

				// Just to make sure
				if ( output.exists() )
				{
					int answer = JOptionPane.showConfirmDialog(null,
							PrimeMain.texts
									.getString("overwriteStandardRuleFileMsg"),
							PrimeMain.texts.getString("overwrite"),
							JOptionPane.YES_NO_OPTION);

					if ( answer == 0 )
					{
						overwrite = true;
					}
				}
				else
				{
					overwrite = true;
				}


				// The file either does not exist or the user wants to overwrite
				if ( overwrite )
				{
					return saveStandardRules(output);
				}
			}
		}

		return false;
	}



	/**
	 * This function exports the Standard Objects list to a file. The file will
	 * have a .obj filetype. The user is presented with a choice on which folder
	 * to save the file in.
	 */
	public static boolean exportStandardObjects()
	{
		// If the object list not empty
		if ( !PrimeMain.objectlist.isEmpty() )
		{
			// The JFileChoose where the user will save the export
			JFileChooser fc = new JFileChooser();
			fc.setAcceptAllFileFilterUsed(false);

			// Adds the filters
			fc.addChoosableFileFilter(new OBJFilter());

			// Sets the selected file to the name of the
			fc.setSelectedFile(new File("Objects"));

			// Shows the File chooser
			int returnVal = fc.showSaveDialog(null);


			// If the users choices a folder
			if ( returnVal == JFileChooser.APPROVE_OPTION )
			{
				File file = fc.getSelectedFile();

				// Creates the file with the right extension
				File output = new File(file.getAbsoluteFile() + ".obj");

				// Whether or not to overwrite
				boolean overwrite = false;

				// Just to make sure
				if ( output.exists() )
				{
					int answer = JOptionPane.showConfirmDialog(null,
							PrimeMain.texts
									.getString("overwriteObjectsFileMsg"),
							PrimeMain.texts.getString("overwrite"),
							JOptionPane.YES_NO_OPTION);

					if ( answer == 0 )
					{
						overwrite = true;
					}
				}
				else
				{
					overwrite = true;
				}


				// The file either does not exist or the user wants to overwrite
				if ( overwrite )
				{
					return saveObjectFile(output);
				}
			}
		}

		return false;
	}




	/**
	 * This function exports the Custom {@link OperatingSystem} within the
	 * system to a file.
	 * The file will have a .dat filetype. The user is presented with a choice
	 * on which folder
	 * to save the file in.
	 */
	public static boolean exportCustomOS()
	{
		if ( !PrimeMain.system_custom_OS.isEmpty() )
		{
			// The JFileChoose where the user will save the export
			JFileChooser fc = new JFileChooser();
			fc.setAcceptAllFileFilterUsed(false);

			// Adds the filters
			fc.addChoosableFileFilter(new DATFilter());

			// Sets the selected file to the name of the
			fc.setSelectedFile(new File("CustomOS"));

			// Shows the File chooser
			int returnVal = fc.showSaveDialog(null);

			// If the users choices a folder
			if ( returnVal == JFileChooser.APPROVE_OPTION )
			{
				File file = fc.getSelectedFile();

				// Creates the file with the right extension
				File output = new File(file.getAbsoluteFile() + ".dat");

				// Whether or not to overwrite
				boolean overwrite = false;

				// Just to make sure
				if ( output.exists() )
				{
					int answer = JOptionPane.showConfirmDialog(null,
							PrimeMain.texts
									.getString("overwriteCustomOSFileMsg"),
							PrimeMain.texts.getString("overwrite"),
							JOptionPane.YES_NO_OPTION);

					if ( answer == 0 )
					{
						overwrite = true;
					}
				}
				else
				{
					overwrite = true;
				}


				// The file either does not exist or the user wants to overwrite
				if ( overwrite )
				{
					return saveCustomOS(output);
				}
			}
		}

		return false;
	}




	/**
	 * This function imports a Network from a file. The file will have a .dat
	 * filetype. The user is presented with {@link JFileChooser}.
	 */
	public static void importNetwork()
	{
		// The JFileChoose where the user will save the export
		JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);

		// Adds the filter
		fc.addChoosableFileFilter(new DATFilter());

		// Shows the File chooser
		int returnVal = fc.showOpenDialog(null);

		// If the save button is pressed
		if ( returnVal == JFileChooser.APPROVE_OPTION )
		{
			// Gets the file written/selected
			File file = fc.getSelectedFile();

			if ( file.exists() )
			{
				// Whether there exist network file with the same name
				boolean networkFileExists = DesktopFileManagment
						.fileWorkareaCanvasExist(getFileNameWithoutExtension(file
								.getName()));

				// If there exists a an open network canvas with the same name
				boolean networkCanvasExists = DesktopCanvasManagment
						.canvasExists(getFileNameWithoutExtension(file
								.getName()));

				// Whether the name of the imported network has to be changed
				boolean changeName = false;

				if ( networkFileExists )
				{
					changeName = true;
				}
				else if ( networkCanvasExists )
				{
					changeName = true;
				}


				if ( changeName )
				{
					// The options the user will be presented with.
					java.lang.Object[] options = {
							PrimeMain.texts.getString("change"),
							PrimeMain.texts.getString("overwrite"),
							PrimeMain.texts.getString("cancel") };

					// Asks the user whether or not to save
					int answer = JOptionPane
							.showOptionDialog(
									null,
									PrimeMain.texts
											.getString("verifyImportedNetworkNameChange"),
									PrimeMain.texts.getString("overwrite"),
									JOptionPane.WARNING_MESSAGE,
									JOptionPane.WARNING_MESSAGE, null, options,
									PrimeMain.texts.getString("change"));

					// The user answers yes
					if ( answer == 0 )
					{
						boolean tryAgain = true;


						while ( tryAgain )
						{
							// Gets the users new network name
							String newName = JOptionPane
									.showInputDialog(
											null,
											PrimeMain.texts
													.getString("writeNewNetworkNameMsg"));

							if ( newName != null
									&& !(newName.equals(""))
									&& Pattern.matches("([a-zA-Z_0-9 ])*",
											newName) )
							{
								// If the file can be read
								if ( file.canRead() )
								{
									try
									{
										/**
										 * Now the function will check if the
										 * found canvas is open and since the
										 * user wants to overwrite the canvas
										 * with the same name, we must close the
										 * open canvas.
										 */
										PrimeMain.workTab.removeTabWithCanvas(
												newName, false);


										// The workareacanvas in the file
										WorkareaCanvas newCanvas = openCanvasFile(file);

										// Sets the new name for the network
										newCanvas.setCanvasName(newName);

										// Sets the a new serial number for the
										// network
										newCanvas
												.setSerial((Math.random()) * 500);

										// Adds the canvas to the program
										PrimeMain.getWorkarea()
												.createNewCanvasTab(
														PrimeMain.glassPane,
														newCanvas);

										// Saves the canvas into the program
										saveWorkareaCanvas(newCanvas, false);

										newCanvas.setSaved(true);
									}
									catch ( CanvasNotFound e )
									{
										// DO NOTHING, BECAUSE THE TAB IS CLOSED
									}

									tryAgain = false;
								}
								else
								{
									tryAgain = true;
								}
							}
							else
							{
								// Whether or not the user wants to try again
								int n = JOptionPane
										.showConfirmDialog(
												null,
												PrimeMain.texts
														.getString("tryNewNetworkNameAgainMsg"),
												PrimeMain.texts
														.getString("verify"),
												JOptionPane.YES_NO_OPTION);

								// The user answers something else then yes
								if ( n != 0 )
								{
									tryAgain = false;
								}
							}
						}
					}
					else if ( answer == 1 )
					{
						String newName = getFileNameWithoutExtension(file
								.getName());

						// If the file can be read
						if ( file.canRead() )
						{
							try
							{
								/**
								 * Now the function will check if the
								 * found canvas is open and since the
								 * user wants to overwrite the canvas
								 * with the same name, we must close the
								 * open canvas.
								 */
								PrimeMain.workTab.removeTabWithCanvas(newName,
										false);

								// The workareacanvas in the file
								WorkareaCanvas newCanvas = openCanvasFile(file);

								// Sets the new name for the network
								newCanvas.setCanvasName(newName);

								// Sets the a new serial number for the
								// network
								newCanvas.setSerial((Math.random()) * 500);

								// Adds the canvas to the program
								PrimeMain.getWorkarea().createNewCanvasTab(
										PrimeMain.glassPane, newCanvas);

								// Saves the canvas into the program
								saveWorkareaCanvas(newCanvas, false);

								newCanvas.setSaved(true);
							}
							catch ( CanvasNotFound e )
							{
								// DO NOTHING, BECAUSE THE TAB IS CLOSED
							}
						}
					}
				}
				else
				{
					/**
					 * The WorkareaCanvas inside the file does not have the name
					 * or serial number. It is therefore just added to the
					 * system.
					 */
					// The workareacanvas in the file
					WorkareaCanvas newCanvas = openCanvasFile(file);

					// Adds the canvas to the program
					PrimeMain.getWorkarea().createNewCanvasTab(
							PrimeMain.glassPane, newCanvas);

					// Saves the canvas into the program
					saveWorkareaCanvas(newCanvas, true);

					newCanvas.setSaved(true);
				}
			}
		}
	}

	/**
	 * TODO - Description
	 * 
	 */
	public static void importStandardRules()
	{
		// The JFileChoose where the user will save the export
		JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);

		// Adds the filter
		fc.addChoosableFileFilter(new DATFilter());

		// Shows the File chooser
		int returnVal = fc.showOpenDialog(null);

		// If the save button is pressed
		if ( returnVal == JFileChooser.APPROVE_OPTION )
		{
			// Gets the file written/selected
			File file = fc.getSelectedFile();

			if ( file.exists() )
			{
				// The text shown to the user
				String text = PrimeMain.texts
						.getString("verifyStandardRulesListOverwrite")
						+ System.getProperty("line.separator")
						+ PrimeMain.texts.getString("thisCannotBeUndoneMsg");

				// Whether or not the user wants to overwrite the current
				// standard object list
				int answer = JOptionPane.showConfirmDialog(null, text,
						PrimeMain.texts.getString("overwrite"),
						JOptionPane.YES_NO_OPTION);

				// The user answers yes
				if ( answer == 0 )
				{
					openStandardRules(file);
				}
			}
		}

	}



	/**
	 * TODO - Description
	 * 
	 */
	public static void importCustomOS()
	{
		// The JFileChoose where the user will save the export
		JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);

		// Adds the filter
		fc.addChoosableFileFilter(new DATFilter());

		// Shows the File chooser
		int returnVal = fc.showOpenDialog(null);

		// If the save button is pressed
		if ( returnVal == JFileChooser.APPROVE_OPTION )
		{
			// Gets the file written/selected
			File file = fc.getSelectedFile();

			if ( file.exists() )
			{
				// The text shown to the user
				String text = PrimeMain.texts
						.getString("verifyCsutomOSListOverwrite")
						+ System.getProperty("line.separator")
						+ PrimeMain.texts.getString("thisCannotBeUndoneMsg");

				// Whether or not the user wants to overwrite the current
				// standard object list
				int answer = JOptionPane.showConfirmDialog(null, text,
						PrimeMain.texts.getString("overwrite"),
						JOptionPane.YES_NO_OPTION);

				// The user answers yes
				if ( answer == 0 )
				{
					loadCustomOS(file);
				}
			}
		}

	}



	/**
	 * This function imports a Standard Objects list from a file. The file will
	 * have a .obj filetype. The user is presented with {@link JFileChooser}.
	 */
	public static void importStandardObjects()
	{
		// The JFileChoose where the user will save the export
		JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);

		// Adds the filter
		fc.addChoosableFileFilter(new OBJFilter());

		// Shows the File chooser
		int returnVal = fc.showOpenDialog(null);

		// If the save button is pressed
		if ( returnVal == JFileChooser.APPROVE_OPTION )
		{
			// Gets the file written/selected
			File file = fc.getSelectedFile();

			if ( file.exists() )
			{
				// The text shown to the user
				String text = PrimeMain.texts
						.getString("verifyStandardObjectsListOverwrite")
						+ System.getProperty("line.separator")
						+ PrimeMain.texts.getString("thisCannotBeUndoneMsg");

				// Whether or not the user wants to overwrite the current
				// standard object list
				int answer = JOptionPane.showConfirmDialog(null, text,
						PrimeMain.texts.getString("overwrite"),
						JOptionPane.YES_NO_OPTION);

				// The user answers yes
				if ( answer == 0 )
				{
					openObjectsFile(file);

					// Tells the user that the change will take effect on
					// restart
					JOptionPane.showMessageDialog(null,
							PrimeMain.texts.getString("ChangeAfterRestart"));
				}
			}
		}
	}



	/**
	 * Saves the objectlist from the {@link PrimeMain} to the resource
	 * directory.
	 */
	public static void saveObjectsFile()
	{
		File file = new File("./resource/objects.obj");

		saveObjectFile(file);
	}



	/**
	 * Saves the objectlist from the {@link PrimeMain} to the given file.
	 */
	public static boolean saveObjectFile(File file)
	{
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
						oos.writeObject(PrimeMain.objectlist);

						// Flushes the stream
						oos.flush();

						// Closes the stream
						oos.close();

						return true;
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
				oos.writeObject(PrimeMain.objectlist);

				// Flushes the stream
				oos.flush();

				// Closes the stream
				oos.close();

				return true;
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}

		return false;
	}



	/**
	 * Opens the standard objects file that contains the systems standard
	 * objects located in the resource directory with the name objects.obj.
	 */
	public static void openObjectsFile()
	{
		File file = new File("./resource/objects.obj");

		openObjectsFile(file);
	}



	/**
	 * Opens the standard objects file that contains the systems standard
	 * objects from the given file(after verification).
	 */
	public static void openObjectsFile(File file)
	{
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

						boolean verified = false;


						// The arraylist of the systems standard Objects
						ArrayList<Object> testlist = new ArrayList<Object>();

						testlist = (ArrayList<Object>) ois.readObject();

						// If the list is not empty
						if ( !testlist.isEmpty() )
						{
							// Gets the first object in the list
							Object testObject = testlist.get(0);

							// If the name of the gotten object is not ""
							if ( !testObject.getObjectName().equals("") )
							{
								verified = true;
							}
						}


						// The file has been verified
						if ( verified )
						{
							// Reads inn the ArrayList from the file stream
							PrimeMain.objectlist = testlist;
						}

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
	 * 
	 */
	public static NetworkRules copyRules(NetworkRules standardRules,
			NetworkRules newRules)
	{
		// HARDWARE
		newRules.setUSBnotAllowed(standardRules.isUSBnotAllowed());
		newRules.setUSBportsAllowed(standardRules.getUSBportsAllowed());

		newRules.setLANnotAllowed(standardRules.isLANnotAllowed());
		newRules.setLANportsAllowed(standardRules.getLANportsAllowed());

		// SOFTWARE
		newRules.setOSrestriction(standardRules.isOSrestriction());
		newRules.setOSrestrictedName(standardRules.getOSrestrictedName());

		newRules.setAVrestriction(standardRules.isAVrestriction());
		newRules.setAVrestrictedName(standardRules.getAVrestrictedName());

		newRules.setFWrestriction(standardRules.isFWrestriction());
		newRules.setFWrestrictedName(standardRules.getFWrestrictedName());

		newRules.setEMailRestriction(standardRules.isEMailRestriction());
		newRules.setEMailRestrictedName(standardRules.getEMailRestrictedName());

		newRules.setOfficeSuiteRestriction(standardRules
				.isOfficeSuiteRestriction());
		newRules.setOfficeSuiteRestrictedName(standardRules
				.getOfficeSuiteRestrictedName());

		// INFRASTRUCTURE
		newRules.setCanConnectToInternet(standardRules.isCanConnectToInternet());

		newRules.setMustHaveFWbeforeInternet(standardRules
				.isMustHaveFWbeforeInternet());

		newRules.setMustHaveAVbeforeInternet(standardRules
				.isMustHaveAVbeforeInternet());

		newRules.setCanContainHub(standardRules.isCanContainHub());

		newRules.setCanContainWirelessRouter(standardRules
				.isCanContainWirelessRouter());


		return newRules;
	}



	/**
	 * This function checks whether or not an standard rules file exists in
	 * the resources folder with the name rules.dat
	 */
	public static boolean ruleFileExists()
	{
		File file = new File("./resource/rules.dat");

		if ( file.exists() )
		{
			if ( file.isFile() )
			{
				return true;
			}
		}

		return false;
	}



	/**
	 * This function checks whether or not an standard objects file exists in
	 * the resources folder with the objects.obj
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



	/**
	 * Deletes all files and subdirectories under dir. Returns true if all
	 * deletions were successful. If a deletion fails, the method stops
	 * attempting to delete and returns false.
	 */
	public static boolean deleteDir(File dir)
	{
		if ( dir.isDirectory() )
		{
			String[] children = dir.list();
			for ( int i = 0; i < children.length; i++ )
			{
				boolean success = deleteDir(new File(dir, children[i]));
				if ( !success )
				{
					return false;
				}
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}



	/**
	 * Returns the filename from the given string with the extension. Returns ""
	 * if the extension is more or less then 3 letters.
	 */
	public static String getFileNameWithoutExtension(String fileName)
	{
		int whereDot = fileName.lastIndexOf('.');

		if ( 0 < whereDot && whereDot <= fileName.length() - 2 )
		{
			return fileName.substring(0, whereDot);
			// extension = filename.substring(whereDot+1);
		}

		return "";
	}



	/**
	 * This function attempts to retrieve the serial number of the
	 * {@link WorkareaCanvas} contained inside the give file.
	 */
	public static double getWorkareaCanvasSerialFromFile(File file)
	{
		double serial = 0;


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

						// The name of the canvas(not used)
						ois.readObject();

						// The serial of the network
						serial = ois.readDouble();

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
					System.out.println("File cannot be read.");
				}
			}
			else
			{
				System.out.println("File pointer is to a folder, not a file.");
			}
		}
		else
		{
			System.out.println("File does not exist.");
		}


		return serial;
	}
}