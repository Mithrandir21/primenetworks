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
import java.io.EOFException;
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
import java.util.UUID;
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
				PrimeMain.ioLog
						.fine("Network folder did not exist for save. Creating folder...");
				folder.mkdir();
				PrimeMain.ioLog.fine("Folder, '" + folder.getName()
						+ "', created successfully.");
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
		PrimeMain.ioLog.info("Starting Network Map (canvas) Saving process.");
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
				PrimeMain.ioLog.fine("Open '" + file.getCanonicalPath()
						+ "' with FileOutputStream for saving.");
				FileOutputStream fout = new FileOutputStream(file);

				// The object stream file
				ObjectOutputStream oos = new ObjectOutputStream(fout);

				// The name of the canvas
				String nameOfCanvas = canvas.getCanvasName();

				// Writes out the name of the canvas
				PrimeMain.ioLog.fine("Writing out the name of the canvas, '"
						+ nameOfCanvas + "'");
				oos.writeObject(nameOfCanvas);

				// Writes out the serial number of the canvas
				PrimeMain.ioLog.fine("Writing out the serial of the canvas.");
				oos.writeObject(canvas.getSerial());

				// Writes out the WorkareaCanvasNetworkInfo
				PrimeMain.ioLog
						.fine("Writing out the NetworkInfo of the canvas.");
				oos.writeObject(canvas.getNetworkInfo());

				// Writes out the NetworkRules
				PrimeMain.ioLog.fine("Writing out the Rules of the canvas.");
				oos.writeObject(canvas.getRules());

				// Writes out the NetworkGroups
				PrimeMain.ioLog
						.fine("Writing out the Network Groups of the canvas.");
				oos.writeObject(canvas.getNetworkGroups());



				// WRITE OBJECTS ON THE CANVAS

				PrimeMain.ioLog
						.fine("Collecting objects on canvas and their icons.");

				// The objects on the canvas
				Object[] objects = canvas.getObjectsOnTheScene();

				// The ArrayList of object that are on the canvas
				ArrayList<Object> objectList = new ArrayList<Object>();

				// The ArrayList that will contain the ImageIcons for the
				// objects
				ArrayList<ImageIcon> objectIconList = new ArrayList<ImageIcon>();


				// There must be some objects on the canvas for there to be
				// saved any widgetObjects
				if ( objects.length > 0 )
				{
					// Goes through all the object on the canvas and adds then
					// to the arraylist
					for ( int i = 0; i < objects.length; i++ )
					{
						objectList.add(objects[i]);
						if ( objects[i].getVisualImage() != null )
						{
							objectIconList.add(objects[i].getVisualImage());
						}
						else
						{
							objectIconList.add(null);
						}
					}

				}

				PrimeMain.ioLog.fine("Writing out the objects on the canvas.");
				// Writes out the objects in the form of an arraylist, even if
				// it is empty
				oos.writeObject(objectList);

				PrimeMain.ioLog
						.fine("Writing out the icons of the objects on the canvas.");
				// Writes out the objects images in the form of an arraylist,
				// even if it is empty
				oos.writeObject(objectIconList);

				oos.flush();


				// END OF WRITE OBJECTS



				// WRITE WIDGETNETWORKINFO INSIDE THE WIDGETS
				PrimeMain.ioLog
						.fine("Collecting WidgetNetworkInfo for each object on the canvas.");

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

				PrimeMain.ioLog
						.fine("Writing out the WidgetNetworkInfo of the objects on the canvas.");
				// Writes out the WidgetNetworkInfo in the form of an arraylist,
				// even if it is empty
				oos.writeObject(widgetList);

				oos.flush();


				// END OF WRITE WIDGETNETWORKINFO


				// WRITE CONNECTIONS ON THE CANVAS
				PrimeMain.ioLog
						.fine("Collecting Connection and a list of break points for each connection on the canvas.");

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

				PrimeMain.ioLog
						.fine("Writing out the Connections on the canvas.");
				// Writes out the connections ArrayList, even if it is empty
				oos.writeObject(widConList);

				PrimeMain.ioLog
						.fine("Writing out the List of points for the Connections on the canvas.");
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
				PrimeMain.ioLog.fine("Collecting the Rooms on the canvas.");

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

				PrimeMain.ioLog.fine("Writing out the Rooms on the canvas.");
				// Writes out the room ArrayList, even if it is empty
				oos.writeObject(roomList);

				oos.flush();

				// END OF WRITE ROOMS


				oos.close();
			}
			catch ( Exception e )
			{
				PrimeMain.ioLog
						.warning("An Exception, '"
								+ e.getClass().getCanonicalName()
								+ "', was caught which means that the Network save action failed. "
								+ "(See Debug info for more detailed information.)");

				PrimeMain.ioLog.severe(e.getMessage());
				e.printStackTrace();
			}

			canvas.setSaved(true);
			canvas.setChanged(false);

			// Reloads the JTree
			PrimeMain.updateNetworkSelectionArea();

			PrimeMain.workTab.revalidate();
			PrimeMain.workTab.repaint();

			PrimeMain.ioLog.info("The canvas, '" + canvas.getCanvasName()
					+ "', has successfully been saved to " + file.getName()
					+ ".");
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
			PrimeMain.ioLog.info("Saving Standard Rules...");
			try
			{
				FileOutputStream fout = new FileOutputStream(file);
				PrimeMain.ioLog.fine("Opened file for standard rules writing.");

				// The object stream file
				ObjectOutputStream oos = new ObjectOutputStream(fout);

				oos.writeObject(PrimeMain.standardRules);
				PrimeMain.ioLog.fine("Written rules to file.");

				oos.flush();
				oos.close();

				return true;
			}
			catch ( Exception e )
			{
				PrimeMain.ioLog
						.warning("An Exception, '"
								+ e.getClass().getCanonicalName()
								+ "', was caught which means that the Standard Rule save action failed. "
								+ "(See Debug info for more detailed information.)");

				PrimeMain.ioLog.severe(e.getMessage());
				e.printStackTrace();
			}
		}

		return false;
	}



	/**
	 * Saves the systems custom OSs to a file, or deletes the
	 * file if custom OSs is empty.
	 */
	public static boolean saveCustomOS()
	{
		File file = new File("./resource/customOS.dat");
		PrimeMain.ioLog.info("Saving Custom OS...");

		// If there are no custom OSs, the custom file will be deleted.
		if ( PrimeMain.system_custom_OS.isEmpty() && file != null )
		{
			file.delete();
			PrimeMain.ioLog.fine("No custom OS to save.");

			return true;
		}

		return saveCustomOS(file);
	}


	/**
	 * TODO - Description
	 */
	public static boolean saveCustomOS(File file)
	{
		PrimeMain.ioLog.info("Saving Custom OS to file...");
		if ( PrimeMain.system_custom_OS != null
				&& (!PrimeMain.system_custom_OS.isEmpty()) && file != null )
		{
			try
			{
				FileOutputStream fout = new FileOutputStream(file);
				PrimeMain.ioLog.fine("Opened file for custom OS writing.");

				// The object stream file
				ObjectOutputStream oos = new ObjectOutputStream(fout);

				// get an Iterator object for ArrayList using iterator()
				// method.
				Iterator<OperatingSystem> itr = PrimeMain.system_custom_OS
						.iterator();

				while ( itr.hasNext() )
				{
					oos.writeObject(itr.next());
				}

				// Writes a null to indicate the end of the file for read in.
				oos.writeObject(null);

				oos.flush();
				oos.close();
				PrimeMain.ioLog.info("Custom OSs written to file.");

				return true;
			}
			catch ( Exception e )
			{
				PrimeMain.ioLog
						.warning("An Exception, '"
								+ e.getClass().getCanonicalName()
								+ "', was caught which means that the Custom OS save action failed. "
								+ "(See Debug info for more detailed information.)");

				PrimeMain.ioLog.severe(e.getMessage());
				e.printStackTrace();
			}
		}


		PrimeMain.ioLog
				.info("Either no custom OS found, file was NULL or variable NULL.");

		return false;
	}


	/**
	 * Saves the {@link Settings} instance to a file to preserve the users
	 * settings.
	 */
	public static void saveSettings()
	{
		PrimeMain.ioLog.info("Saving Settings to file...");
		File file = new File("./resource/settings.dat");

		try
		{
			FileOutputStream fout = new FileOutputStream(file);
			PrimeMain.ioLog.fine("Opened file for Settings writing.");

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
			oos.writeBoolean(managment.Settings.showSplash);

			oos.flush();
			oos.close();

			PrimeMain.ioLog.info("Settings saved to file.");
		}
		catch ( Exception e )
		{
			PrimeMain.ioLog
					.warning("An Exception, '"
							+ e.getClass().getCanonicalName()
							+ "', was caught which means that the Settings save action failed. "
							+ "(See Debug info for more detailed information.)");

			PrimeMain.ioLog.severe(e.getMessage());
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
		if ( canvasName != null )
		{
			PrimeMain.ioLog
					.info("Checking for a existing Network with the name '"
							+ canvasName + "'.");
			// Creates a file object(not the actual file)
			File file = new File("./resource/Data/" + canvasName
					+ File.separator + canvasName + ".dat");

			// If the file(network) exists
			if ( file.exists() )
			{
				PrimeMain.ioLog
						.fine("Network file exists with the given name. Ask user to overwrite.");
				try
				{
					FileInputStream fin = new FileInputStream(file);
					PrimeMain.ioLog.fine("Opened file for reading.");

					ObjectInputStream ois = new ObjectInputStream(fin);

					// The name of the file canvas
					String name = (String) ois.readObject();
					PrimeMain.ioLog
							.fine("Reading the name of the Network inside the existing file.");


					ois.close();

					// If the name of the file network is the same as the name
					// of
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
							PrimeMain.ioLog
									.info("User choose to overwrite the existing file.");
							return true;
						}

						PrimeMain.ioLog
								.info("User choose NOT to overwrite the existing file.");
						return false;
					}
				}
				catch ( FileNotFoundException e )
				{
					PrimeMain.ioLog
							.warning("An Exception, '"
									+ e.getClass().getCanonicalName()
									+ "', was caught which means that the checkAndVerify action failed. "
									+ "(See Debug info for more detailed information.)");

					PrimeMain.ioLog.severe(e.getMessage());
					e.printStackTrace();
				}
				catch ( IOException e )
				{
					PrimeMain.ioLog
							.warning("An Exception, '"
									+ e.getClass().getCanonicalName()
									+ "', was caught which means that the checkAndVerify action failed. "
									+ "(See Debug info for more detailed information.)");

					PrimeMain.ioLog.severe(e.getMessage());
					e.printStackTrace();
				}
				catch ( ClassNotFoundException e )
				{
					PrimeMain.ioLog
							.warning("An Exception, '"
									+ e.getClass().getCanonicalName()
									+ "', was caught which means that the checkAndVerify action failed. "
									+ "(See Debug info for more detailed information.)");

					PrimeMain.ioLog.severe(e.getMessage());
					e.printStackTrace();
				}
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
		if ( canvas != null )
		{
			PrimeMain.ioLog
					.info("Checking for a existing Network with the name '"
							+ canvas.getCanvasName() + "'.");
			String canvasName = canvas.getCanvasName();
			UUID canvasSerial = canvas.getSerial();

			// Creates a file object(not the actual file)
			File file = new File("./resource/Data/" + canvas.getCanvasName()
					+ File.separator + canvas.getCanvasName() + ".dat");

			// If the file(network) exists
			if ( file.exists() )
			{
				PrimeMain.ioLog
						.fine("Network file exists with the given name. Ask user to overwrite.");
				try
				{
					FileInputStream fin = new FileInputStream(file);
					PrimeMain.ioLog.fine("Opened file for reading.");

					ObjectInputStream ois = new ObjectInputStream(fin);

					// The name of the file canvas
					String name = (String) ois.readObject();

					// The serial of the file network
					UUID serial = (UUID) ois.readObject();


					ois.close();

					// If the name of the file network is the same as the name
					// of the given WorkareaCanvas.
					if ( name.equalsIgnoreCase(canvasName) )
					{
						// If the serial from the file is not the same as the
						// serial from the given WorkareaCanvas.
						if ( !serial.equals(canvasSerial) )
						{
							int answer = JOptionPane
									.showConfirmDialog(
											null,
											PrimeMain.texts
													.getString("overwriteNetworkWithTheSameNameMsg"),
											PrimeMain.texts
													.getString("overwrite"),
											JOptionPane.YES_NO_OPTION);

							// The user answers "yes"
							if ( answer == 0 )
							{
								PrimeMain.ioLog
										.info("User choose to overwrite the existing file.");
								return true;
							}

							PrimeMain.ioLog
									.info("User choose NOT to overwrite the existing file.");
							return false;
						}

						return true;
					}
				}
				catch ( FileNotFoundException e )
				{
					PrimeMain.ioLog
							.warning("An Exception, '"
									+ e.getClass().getCanonicalName()
									+ "', was caught which means that the checkAndVerify action failed. "
									+ "(See Debug info for more detailed information.)");

					PrimeMain.ioLog.severe(e.getMessage());
					e.printStackTrace();
				}
				catch ( IOException e )
				{
					PrimeMain.ioLog
							.warning("An Exception, '"
									+ e.getClass().getCanonicalName()
									+ "', was caught which means that the checkAndVerify action failed. "
									+ "(See Debug info for more detailed information.)");

					PrimeMain.ioLog.severe(e.getMessage());
					e.printStackTrace();
				}
				catch ( ClassNotFoundException e )
				{
					PrimeMain.ioLog
							.warning("An Exception, '"
									+ e.getClass().getCanonicalName()
									+ "', was caught which means that the checkAndVerify action failed. "
									+ "(See Debug info for more detailed information.)");

					PrimeMain.ioLog.severe(e.getMessage());
					e.printStackTrace();
				}
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
		PrimeMain.ioLog
				.info("Checking whether Network file exists with given name '"
						+ newName + "'.");
		// Creates a file object(not the actual file)
		File file = new File("./resource/Data/" + newName + File.separator
				+ newName + ".dat");

		// If the file(network) exists
		if ( file.exists() )
		{
			PrimeMain.ioLog.fine("File exists with given name.");
			try
			{
				FileInputStream fin = new FileInputStream(file);
				PrimeMain.ioLog
						.fine("Opened file for reading of name of Network inside file.");

				ObjectInputStream ois = new ObjectInputStream(fin);

				// The name of the file canvas
				String name = (String) ois.readObject();


				// Closes the file
				ois.close();

				// If the name of the file network is the same as the name of
				// the given WorkareaCanvas
				if ( name.equalsIgnoreCase(newName) )
				{
					PrimeMain.ioLog.info("File exists with same name.");
					return true;
				}
			}
			catch ( FileNotFoundException e )
			{
				PrimeMain.ioLog
						.warning("An Exception, '"
								+ e.getClass().getCanonicalName()
								+ "', was caught which means that the action, checking name in file, failed. "
								+ "(See Debug info for more detailed information.)");

				PrimeMain.ioLog.severe(e.getMessage());
				e.printStackTrace();
			}
			catch ( IOException e )
			{
				PrimeMain.ioLog
						.warning("An Exception, '"
								+ e.getClass().getCanonicalName()
								+ "', was caught which means that the action, checking name in file, failed. "
								+ "(See Debug info for more detailed information.)");

				PrimeMain.ioLog.severe(e.getMessage());
				e.printStackTrace();
			}
			catch ( ClassNotFoundException e )
			{
				PrimeMain.ioLog
						.warning("An Exception, '"
								+ e.getClass().getCanonicalName()
								+ "', was caught which means that the action, checking name in file, failed. "
								+ "(See Debug info for more detailed information.)");

				PrimeMain.ioLog.severe(e.getMessage());
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
		PrimeMain.ioLog.info("Renaming the Network file name...");
		// Checks to see whether a canvas file actually exists
		if ( fileWorkareaCanvasExist(canvas.getCanvasName()) )
		{
			PrimeMain.ioLog.fine("Network file exists with canvas name '"
					+ canvas.getCanvasName() + "'.");

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

				PrimeMain.ioLog.severe("Renaming Network file name failed.");

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

				PrimeMain.ioLog.info("Renaming Network file name success.");

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
		PrimeMain.ioLog.info("Deleting the Network file...");
		// If the file does not exist
		if ( !file.exists() )
		{
			String msg = "This file" + System.getProperty("line.separator")
					+ file.getName() + System.getProperty("line.separator")
					+ "does not exist in location"
					+ System.getProperty("line.separator")
					+ file.getAbsolutePath() + ".";

			JOptionPane.showMessageDialog(null, msg, "Error",
					JOptionPane.ERROR_MESSAGE);

			PrimeMain.ioLog.warning(msg);

			return false;
		}

		// If the file is a directory
		if ( file.isDirectory() )
		{
			String msg = "This" + System.getProperty("line.separator")
					+ file.getName() + System.getProperty("line.separator")
					+ "is a directory at"
					+ System.getProperty("line.separator")
					+ file.getAbsolutePath() + ".";

			JOptionPane.showMessageDialog(null, msg, "Error",
					JOptionPane.ERROR_MESSAGE);

			PrimeMain.ioLog.warning(msg);

			return false;
		}

		// If the file can not be written to(hence not deleted)
		if ( !file.canWrite() )
		{
			String msg = "This file" + System.getProperty("line.separator")
					+ file.getName() + System.getProperty("line.separator")
					+ "is write protected.";

			JOptionPane.showMessageDialog(null, msg, "Error",
					JOptionPane.ERROR_MESSAGE);

			PrimeMain.ioLog.warning(msg);

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

		PrimeMain.ioLog
				.fine("Checking if there exists and open canvas with the given name.");

		// No open canvas was found
		if ( canvas == null )
		{
			PrimeMain.ioLog.fine("No open canvas found with given name.");
			int answer = JOptionPane.showConfirmDialog(null,
					PrimeMain.texts.getString("actionDeleteWorkareaCanvasMsg")
							+ canvasName + "?",
					PrimeMain.texts.getString("confirm"),
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

			if ( answer == 0 )
			{
				PrimeMain.ioLog.fine("User chooses to delete Network file.");
				// Attempt to delete it
				boolean success = file.delete();

				if ( !success )
				{
					String msg = "The file"
							+ System.getProperty("line.separator")
							+ file.getName()
							+ System.getProperty("line.separator")
							+ "was NOT successfully deleted.";

					JOptionPane.showMessageDialog(null, msg, "Error",
							JOptionPane.ERROR_MESSAGE);

					PrimeMain.ioLog.warning(msg);

					return false;
				}
				else
				{
					// If the canvas has been deleted, the canvas folder must be
					// deleted.
					deleteDir(file.getParentFile());

					PrimeMain.ioLog.fine("Network file deleted.");

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
			PrimeMain.ioLog.fine("Open canvas found with given name.");
			// Gets the serial of the canvas in the given file
			UUID serial = getWorkareaCanvasSerialFromFile(file);

			PrimeMain.ioLog
					.fine("Determining if the found canvas is the same as the one saving.");

			if ( serial != null )
			{
				// A boolean on whether the open canvas is the same as the file.
				boolean sameCanvas = false;

				// Checking to see whether the open canvas has the serial number
				// as the canvas in the file.
				if ( serial.equals(canvas.getSerial()) )
				{
					PrimeMain.ioLog
							.fine("Open canvas the same as canvas in file.");
					sameCanvas = true;
				}

				int answer = JOptionPane.showConfirmDialog(
						null,
						PrimeMain.texts
								.getString("actionDeleteWorkareaCanvasMsg")
								+ canvasName + "?", PrimeMain.texts
								.getString("confirm"),
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);

				if ( answer == 0 )
				{
					PrimeMain.ioLog
							.fine("User chooses to overwrite the existing canvas.");
					// Attempt to delete it
					boolean success = file.delete();

					if ( !success )
					{
						String msg = "The file"
								+ System.getProperty("line.separator")
								+ file.getName()
								+ System.getProperty("line.separator")
								+ "was NOT successfully deleted.";

						JOptionPane.showMessageDialog(null, msg, "Error",
								JOptionPane.ERROR_MESSAGE);

						PrimeMain.ioLog.warning(msg);

						return false;
					}
					else
					{
						// If the canvas has been deleted, the canvas folder
						// must be deleted.
						deleteDir(file.getParentFile());


						// Reloads
						PrimeMain.updateNetworkSelectionArea();

						if ( sameCanvas )
						{
							PrimeMain.ioLog
									.fine("Closing open canvas with same name.");
							// Removes the WorkareScroll with the canvas
							try
							{
								PrimeMain.workTab.removeTabWithCanvas(
										canvasName, false);
							}
							catch ( CanvasNotFound e )
							{
								PrimeMain.ioLog
										.warning("An Exception, '"
												+ e.getClass()
														.getCanonicalName()
												+ "', was caught which means that the action, removeTabWithCanvas, failed. "
												+ "(See Debug info for more detailed information.)");

								PrimeMain.ioLog.severe(e.getMessage());
								e.printStackTrace();
							}
						}

						PrimeMain.ioLog.info("Network file deleted.");

						return true;
					}
				}
			}

			PrimeMain.ioLog.warning("Network file deletion failed.");
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
		PrimeMain.ioLog.info("Creating new canvas...");

		// IF the user has canceled
		if ( nameOfCanvas != null )
		{
			if ( Pattern.matches("([a-zA-Z������_0-9 ])*",
					nameOfCanvas) )
			{
				PrimeMain.ioLog.fine("Canvas name validated.");
				// Checks whether or not there exist a canvas with the same
				if ( !DesktopCanvasManagment.canvasExists(nameOfCanvas) )
				{
					PrimeMain.ioLog
							.fine("No canvas exists with the same name.");
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

					PrimeMain.ioLog.fine("Settings added to the canvas.");

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


					PrimeMain.ioLog.info("New canvas created successfully.");
				}
				else
				{
					String msg = PrimeMain.texts
							.getString("canvasExistWithNameMsg");

					JOptionPane.showMessageDialog(null, msg,
							PrimeMain.texts.getString("error"),
							JOptionPane.ERROR_MESSAGE);

					PrimeMain.ioLog.warning(msg);

					return false;
				}
			}
			else
			{
				String msg = PrimeMain.texts.getString("canvasNameNotValidMsg");

				JOptionPane.showMessageDialog(null, msg,
						PrimeMain.texts.getString("error"),
						JOptionPane.ERROR_MESSAGE);

				PrimeMain.ioLog.warning(msg);

				return false;
			}
		}

		return true;

		// TODO - Maybe add the new canvas to the JTree and save it?
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
		if ( file != null )
		{
			WorkareaCanvas canvas = new WorkareaCanvas();

			try
			{
				PrimeMain.ioLog.info("Attempting to open canvas in '"
						+ file.getName() + "'.");

				FileInputStream fin = new FileInputStream(file);

				ObjectInputStream ois = new ObjectInputStream(fin);

				/**
				 * -----MUST HAVE-----
				 * 1. Name of the canvas.
				 * 2. Serial number of the canvas
				 * 
				 * -----POSSIBLE FIELDS-----
				 * 3. WorkareaCanvasNetworkInfo
				 * 4. NetworkRules
				 * 5. NetworkGroups (ArrayList<Group>)
				 * 
				 * 6. Objects (ArrayList<Object>)
				 * 7. System Icons (ArrayList<ImageIcon>)
				 * 8. WidgetNetworkInfo (ArrayList<WidgetNetworkInfo>)
				 * 
				 * 9. Connections (ArrayList<Connection>)
				 * 10. Points for the WidgetConnections (ArrayList<List<Point>>)
				 * 11. Rooms (ArrayList<Room>)
				 */

				// The name of the canvas
				String name = (String) ois.readObject();
				canvas.setCanvasName(name);

				// The serial of the network
				UUID serial = (UUID) ois.readObject();
				canvas.setSerial(serial);

				PrimeMain.ioLog.fine("Gotten name and UUID. Creating fields.");


				// FIELDS CREATION
				WorkareaCanvasNetworkInfo WorkareaCanvasNetworkInfoFIELD = null;
				NetworkRules NetworkRulesFIELD = null;

				// The ArrayList that will hold the Groups
				ArrayList<Group> groupArrayList = new ArrayList<Group>();

				// The ArrayList that will hold the Objects
				ArrayList<Object> objectList = new ArrayList<Object>();

				// The ArrayList that will contain the ImageIcons for the
				// objects
				ArrayList<ImageIcon> objectIconList = new ArrayList<ImageIcon>();

				// The ArrayList that will hold the WidgetNetworkInfo
				ArrayList<WidgetNetworkInfo> widgetInfoList = new ArrayList<WidgetNetworkInfo>();

				// The ArrayList that will hold all the connections
				ArrayList<Connection> connectionList = new ArrayList<Connection>();

				// The ArrayList that will hold the Points of the
				// WidgetExtendedConnection
				ArrayList<List<Point>> widConPoints = new ArrayList<List<Point>>();

				// The ArrayList that will hold all the rooms
				ArrayList<Room> roomList = new ArrayList<Room>();


				// Reading objects
				java.lang.Object gottenObject = null;

				try
				{
					PrimeMain.ioLog.fine("Fields created. Reading in objects.");
					// Read an object
					gottenObject = ois.readObject();

					// While not get here if an EOFException is thrown during
					// reading.
					while ( gottenObject != null )
					{
						PrimeMain.ioLog.fine("Reading object.");

						if ( gottenObject instanceof WorkareaCanvasNetworkInfo )
						{
							PrimeMain.ioLog
									.fine("Setting WorkareaCanvasNetworkInfo");
							WorkareaCanvasNetworkInfoFIELD = (WorkareaCanvasNetworkInfo) gottenObject;
						}
						else if ( gottenObject instanceof NetworkRules )
						{
							PrimeMain.ioLog.fine("Setting NetworkRules");
							NetworkRulesFIELD = (NetworkRules) gottenObject;
						}
						else if ( gottenObject instanceof ArrayList<?> )
						{
							PrimeMain.ioLog.fine("Object ArrayList.");
							/**
							 * Creates a temporary ArrayList (for checking of
							 * index
							 * types) and then checks whether the ArrayList is
							 * empty.
							 */
							ArrayList<?> tempArrayList = (ArrayList<?>) gottenObject;

							if ( !tempArrayList.isEmpty() )
							{
								PrimeMain.ioLog
										.fine("ArrayList not empty. Determining type.");
								// Since it is not empty, it will get the first
								// not
								// NULL object
								java.lang.Object ArrayObject = null;

								// Iterates through the Object list
								for ( Iterator<?> list = tempArrayList
										.iterator(); ArrayObject == null; )
								{
									PrimeMain.ioLog
											.fine("Gotten temporary object type. Checking NULL.");
									java.lang.Object tempObject = list.next();

									if ( tempObject != null )
									{
										PrimeMain.ioLog
												.fine("Gotten temporary object not NULL. Setting as ArrayObject.");
										ArrayObject = tempObject;
									}
								}


								/**
								 * If the ArrayObject is not NULL, the object
								 * type
								 * is checked and then the gottenObject is cast
								 * and
								 * set to the correct field.
								 */
								if ( ArrayObject != null )
								{
									PrimeMain.ioLog
											.fine("ArrayObject object not NULL. Determining object Instanceof.");



									// Rooms
									if ( ArrayObject instanceof Room )
									{
										PrimeMain.ioLog.fine("Type Room");
										roomList = (ArrayList<Room>) gottenObject;
									}
									// Points on WidgetConnections
									else if ( ArrayObject instanceof List<?> )
									{
										PrimeMain.ioLog.fine("Type List<?>");
										widConPoints = (ArrayList<List<Point>>) gottenObject;
									}
									// Connections
									else if ( ArrayObject instanceof Connection )
									{
										PrimeMain.ioLog.fine("Type Connection");
										connectionList = (ArrayList<Connection>) gottenObject;
									}
									// WidgetNetworkInfo
									else if ( ArrayObject instanceof WidgetNetworkInfo )
									{
										PrimeMain.ioLog
												.fine("Type WidgetNetworkInfo");
										widgetInfoList = (ArrayList<WidgetNetworkInfo>) gottenObject;
									}
									// Image Icons
									else if ( ArrayObject instanceof ImageIcon )
									{
										PrimeMain.ioLog.fine("Type ImageIcon");
										objectIconList = (ArrayList<ImageIcon>) gottenObject;
									}
									// Objects
									else if ( ArrayObject instanceof Object )
									{
										PrimeMain.ioLog.fine("Type Object");
										objectList = (ArrayList<Object>) gottenObject;
									}
									// Groups
									else if ( ArrayObject instanceof Group )
									{
										PrimeMain.ioLog.fine("Type Group");
										groupArrayList = (ArrayList<Group>) gottenObject;
									}
								}
							}
						}

						PrimeMain.ioLog.fine("Reading the next object.");
						// Reads next object
						gottenObject = ois.readObject();
					}
				}
				catch ( EOFException e )
				{
					PrimeMain.ioLog.fine("End of file! (Expected.)");
				}


				// ---------------INPUTS WITHOUT FURTHER
				// MANIPULATION---------------
				PrimeMain.desktopProcLog
						.finer("Finished reading in input. Beginning processing.");
				if ( WorkareaCanvasNetworkInfoFIELD != null )
				{
					// Inputs the WorkareaCanvasNetworkInfo
					canvas.setNetworkInfo(WorkareaCanvasNetworkInfoFIELD);
				}
				else
				{
					// Creates a new network info object
					canvas.setNetworkInfo(new WorkareaCanvasNetworkInfo(canvas));
				}


				if ( NetworkRulesFIELD != null )
				{
					// Inputs the NetworkRules
					canvas.setRules(NetworkRulesFIELD);
				}
				else
				{
					canvas.setRules(new NetworkRules(canvas));
				}


				// Inputs the NetworkRules
				canvas.setNetworkGroups(groupArrayList);


				PrimeMain.desktopProcLog
						.finer("Puts the objects and networkinfo together and places a new widget on the canvas.");

				// The size of the new Objects array
				int objectArraySize = objectList.size();

				// The size of the new Objects array
				int widgetInfoArraySize = 0;

				// Iterates through the Object list
				for ( Iterator<WidgetNetworkInfo> it = widgetInfoList
						.iterator(); it.hasNext(); )
				{
					widgetInfoArraySize++;
					it.next();
				}


				/**
				 * If there were any objects found, the size of the image
				 * arraylist
				 * is the same as the object arraylist and the number of object
				 * found is the same as the number of widget infos found.
				 **/
				if ( objectArraySize == widgetInfoArraySize
						&& objectArraySize == objectIconList.size()
						&& objectArraySize > 0 )
				{
					PrimeMain.desktopProcLog.finer("Adding the objects.");
					// PLACES THE OBJECTS INSIDE THE ARRAYLIST INTO AN ARRAY;

					// The objects array
					Object[] objects = new Object[objectArraySize];

					ImageIcon[] images = new ImageIcon[objectArraySize];

					// The index of the objects array
					int objectIndex = 0;

					// Iterates through the list and adds the objects to the
					// objects
					// array
					for ( Iterator<Object> it = objectList.iterator(); it
							.hasNext(); )
					{
						objects[objectIndex] = it.next();
						objectIndex++;
					}

					// The index of the objects images array
					int objectImageIndex = 0;

					// Iterates through the list and adds the object images to
					// the
					// object images array
					for ( Iterator<ImageIcon> it = objectIconList.iterator(); it
							.hasNext(); )
					{
						images[objectImageIndex] = it.next();
						objectImageIndex++;
					}


					PrimeMain.desktopProcLog
							.finer("Adding the WidgetNetworkInfo.");
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

					// Goes through the array of objects and adds them to the
					// newly
					// made canvas
					for ( int i = 0; i < objects.length; i++ )
					{
						if ( objects[i] != null && widgetNetInfos[i] != null )
						{
							PrimeMain.desktopProcLog.fine("Adding object - "
									+ objects[i].getObjectName());
							ImageIcon icon = null;

							// If the object contains any custom image.
							if ( images[i] != null )
							{
								PrimeMain.desktopProcLog
										.fine("Object contains custom icon.");
								icon = images[i];
							}
							// If not, the systems original Image is used.
							else
							{
								PrimeMain.desktopProcLog
										.fine("Object contains standard icon.");
								icon = PrimeMain.objectImageIcons
										.get(objects[i].getClass());
							}

							// Creates a new WidgetObject that will be added to
							// the
							// scene
							WidgetObject newWidgetObject = new WidgetObject(
									canvas.getScene(), objects[i],
									icon.getImage());

							// Adds the network information about the object
							newWidgetObject
									.setWidgetNetworkInfo(widgetNetInfos[i]);

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

				PrimeMain.desktopProcLog
						.finer("Finished adding objects to canvas.");
				// END OF READ OBJECTS


				PrimeMain.desktopProcLog
						.finer("Adding connections between the objects.");
				// READ THE CONNECTIONS THAT TO BE PLACED ON THE CANVAS

				// The size of the new Connection array(which is also the size
				// of
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
					for ( Iterator<Connection> conIt = connectionList
							.iterator(); conIt.hasNext(); )
					{
						connections[connectionIndex] = conIt.next();
						connectionIndex++;
					}

					// Iterates through the list and adds the List<Point> to the
					// List<Point> array
					for ( Iterator<List<Point>> pointIt = widConPoints
							.iterator(); pointIt.hasNext(); )
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
							// Creates the connection between the two devices on
							// the
							// scene.
							WidgetExtendedConnection connection = new WidgetExtendedConnection(
									canvas, connections[i]);


							// Find the two object which are to be connected on
							// the
							// canvas
							WidgetObject sourceWidget = CanvasManagment
									.findWidgetObject(
											connections[i].getObject1(), canvas);
							WidgetObject targetWidget = CanvasManagment
									.findWidgetObject(
											connections[i].getObject2(), canvas);

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

				PrimeMain.desktopProcLog
						.finer("Finished adding connections to canvas.");
				// END OF CONNECTIONS



				PrimeMain.desktopProcLog.finer("Adding rooms on the canvas.");
				// READ THE NETWORK ROOMS

				// The size of the new Room array
				int roomArraySize = roomList.size();

				// If there were any objects found
				if ( roomArraySize > 0 )
				{
					// The room array
					Room[] rooms = new Room[roomArraySize];

					// The index of the room array
					int roomIndex = 0;

					// Iterates through the list and adds the room to the room
					// array
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

				PrimeMain.desktopProcLog
						.finer("Finished adding rooms to canvas.");
				// END OF READ NETWORK ROOMS



				ois.close();
			}
			catch ( FileNotFoundException e )
			{
				PrimeMain.ioLog
						.warning("An Exception, "
								+ e.getClass().getCanonicalName()
								+ ", was caught which means that the \"Load Network\" action failed. (See Debug info for more detailed information.)");

				PrimeMain.ioLog.severe(e.getMessage());
				e.printStackTrace();
			}
			catch ( IOException e )
			{
				PrimeMain.ioLog
						.warning("An Exception, "
								+ e.getClass().getCanonicalName()
								+ ", was caught which means that the \"Load Network\" action failed. (See Debug info for more detailed information.)");

				PrimeMain.ioLog.severe(e.getMessage());
				e.printStackTrace();
			}
			catch ( ClassNotFoundException e )
			{
				PrimeMain.ioLog
						.warning("An Exception, "
								+ e.getClass().getCanonicalName()
								+ ", was caught which means that the \"Load Network\" action failed. (See Debug info for more detailed information.)");

				PrimeMain.ioLog.severe(e.getMessage());
				e.printStackTrace();
			}

			return canvas;
		}

		return null;
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
	 * This function imports the users Custom {@link OperatingSystem}.
	 * 
	 * @see PrimeMain
	 */
	public static void loadCustomOS(File file)
	{
		PrimeMain.ioLog.info("Attempting to load Custom OS from '"
				+ file.getName() + "'.");

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

							PrimeMain.ioLog
									.fine("No Custom OS in the file system. Clearing Custom OSs in the running system.");
						}

						// While the temp is not null, hence not EOF
						while ( (temp = (OperatingSystem) ois.readObject()) != null )
						{
							// Adds the os to the array list
							PrimeMain.system_custom_OS.add(temp);
						}


						PrimeMain.ioLog.fine("Custom OSs read in from file.");

						ois.close();
					}
					catch ( Exception e )
					{
						PrimeMain.ioLog
								.warning("An error occured during file reading.");
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
		PrimeMain.ioLog.info("Attempting to load Standard Rules from '"
				+ file.getName() + "'.");

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

						PrimeMain.ioLog
								.fine("Standard Rules read in from file.");

						ois.close();
					}
					catch ( Exception e )
					{
						PrimeMain.ioLog
								.warning("An error occured during file reading.");
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

		PrimeMain.ioLog.info("Attempting to load system settings from '"
				+ file.getName() + "'.");

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
						managment.Settings.showSplash = ois.readBoolean();


						PrimeMain.ioLog.fine("Settings read in from file.");

						ois.close();
					}
					catch ( Exception e )
					{
						PrimeMain.ioLog
								.warning("An error occured during file reading.");
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
		if ( canvas != null )
		{
			PrimeMain.ioLog.info("Attempting to export network, "
					+ canvas.getCanvasName() + ", to file.");

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
							PrimeMain.texts
									.getString("overwriteNetworkExportMsg"),
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

				PrimeMain.ioLog.info("Export network, "
						+ canvas.getCanvasName() + ", to file successful.");
			}
		}

		PrimeMain.ioLog
				.warning("Saving Network in file failed because given network is NULL.");

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
		if ( canvas != null )
		{
			PrimeMain.ioLog.info("Attempting to export network, "
					+ canvas.getCanvasName() + ", as an image.");

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
							PrimeMain.texts
									.getString("overwriteNetworkImageMsg"),
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
							// Creates a new file with the path of the file and
							// the
							// extension
							file = new File(path + extension);
						}


						try
						{
							CanvasExporter.createImage(PrimeMain.currentCanvas,
									file, CanvasExporter.ImageType.JPG,
									CanvasExporter.ZoomType.ACTUAL_SIZE, false,
									false, 100, 1600, 1400);

							PrimeMain.ioLog.info("Export network, "
									+ canvas.getCanvasName()
									+ ", as image successful.");

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
							// Creates a new file with the path of the file and
							// the
							// extension
							file = new File(path + extension);
						}


						try
						{
							CanvasExporter.createImage(PrimeMain.currentCanvas,
									file, CanvasExporter.ImageType.PNG,
									CanvasExporter.ZoomType.ACTUAL_SIZE, false,
									false, 100, 1000, 1000);

							PrimeMain.ioLog.info("Export network, "
									+ canvas.getCanvasName()
									+ ", as image successful.");

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
		}

		PrimeMain.ioLog
				.warning("Saving Network as image failed because given network is NULL.");

		return false;
	}



	/**
	 * This function exports the Standard rules to a file. The file will have a
	 * .dat filetype. The user is presented with a choice on which folder to
	 * save the file in.
	 */
	public static boolean exportStandardRules()
	{
		PrimeMain.ioLog.info("Attempting to save Standard Rules to file.");

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

				PrimeMain.ioLog.info("Saving Standard Rules was successful.");
			}
		}

		PrimeMain.ioLog
				.warning("Saving Standard Rules failed because Standard Rules variable is NULL.");

		return false;
	}



	/**
	 * This function exports the Standard Objects list to a file. The file will
	 * have a .obj filetype. The user is presented with a choice on which folder
	 * to save the file in.
	 */
	public static boolean exportStandardObjects()
	{
		PrimeMain.ioLog.info("Attempting to save Standard Objects to file.");

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
					return saveObjectsFile(output);
				}

				PrimeMain.ioLog.info("Saving Standard Objects was successful.");
			}
		}

		PrimeMain.ioLog
				.warning("Saving Standard Objects failed because Standard Objects variable is NULL.");

		return false;
	}




	/**
	 * This function exports the Custom {@link OperatingSystem} within the
	 * system to a file. The file will have a .dat filetype. The user is
	 * presented with a choice on which folder to save the file in.
	 */
	public static boolean exportCustomOS()
	{
		PrimeMain.ioLog.info("Attempting to save Custom OSs to file.");

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

				PrimeMain.ioLog.info("Saving Custom OSs was successful.");
			}
		}

		PrimeMain.ioLog
				.warning("Saving Custom OSs failed because Custom OSs variable is NULL.");

		return false;
	}




	/**
	 * This function imports a Network from a file. The file will have a .dat
	 * filetype. The user is presented with {@link JFileChooser}.
	 */
	public static void importNetwork()
	{
		PrimeMain.ioLog.info("Attempting to import a Network from a file.");

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
					PrimeMain.ioLog
							.fine("A network was found in the system with the same name. User is asked to change imported network, overwrite or cancel.");

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

					// The user answers "Change"
					if ( answer == 0 )
					{
						PrimeMain.ioLog.fine("User selected \"Change\".");

						boolean tryAgain = true;


						while ( tryAgain )
						{
							PrimeMain.ioLog
									.fine("Asking the user to select a network name.");

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
										// FIXME - Serial nr of managed network
										newCanvas.setSerial(UUID.randomUUID());

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
						PrimeMain.ioLog.fine("User selected \"Overwrite\".");

						String newName = getFileNameWithoutExtension(file
								.getName());

						// If the file can be read
						if ( file.canRead() )
						{
							try
							{
								/**
								 * Now the function will check if the found
								 * canvas is open and since the user wants to
								 * overwrite the canvas with the same name, we
								 * must close the open canvas.
								 */
								PrimeMain.workTab.removeTabWithCanvas(newName,
										false);

								// The workareacanvas in the file
								WorkareaCanvas newCanvas = openCanvasFile(file);

								// Sets the new name for the network
								newCanvas.setCanvasName(newName);

								// Sets the a new serial number for the
								// network
								newCanvas.setSerial(UUID.randomUUID());

								// Adds the canvas to the program
								PrimeMain.getWorkarea().createNewCanvasTab(
										PrimeMain.glassPane, newCanvas);

								// Saves the canvas into the program
								saveWorkareaCanvas(newCanvas, false);

								newCanvas.setSaved(true);
							}
							catch ( CanvasNotFound e )
							{
								PrimeMain.ioLog
										.info("Canvas was closed before save took place.");
								// DO NOTHING, BECAUSE THE TAB IS CLOSED
							}
						}
					}
				}
				else
				{
					PrimeMain.ioLog
							.fine("No other network found with the same name or serial. Adding network.");
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
	 */
	public static void importStandardRules()
	{
		PrimeMain.ioLog
				.info("Attempting to import a Standard Rules from a file.");

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

					PrimeMain.ioLog
							.info("Imported the Standard Rules from a file successfully.");
				}
			}
		}
	}



	/**
	 * TODO - Description
	 */
	public static void importCustomOS()
	{
		PrimeMain.ioLog.info("Attempting to import a Custom OSs from a file.");

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

					PrimeMain.ioLog
							.info("Imported the Custom OSs from a file successfully.");
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
		PrimeMain.ioLog
				.info("Attempting to import a Standard Rules from a file.");

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

					PrimeMain.ioLog
							.info("Imported the Standard Rules from a file successfully.");
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

		saveObjectsFile(file);
	}



	/**
	 * Saves the objectlist from the {@link PrimeMain} to the given file.
	 */
	public static boolean saveObjectsFile(File file)
	{
		PrimeMain.ioLog
				.info("Attempting to save the Standard Objects of the running system in the given file '"
						+ file.getName() + "'.");

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

						PrimeMain.ioLog
								.info("Running systems Standard Objects written to file successfully.");

						return true;
					}
					catch ( Exception e )
					{
						PrimeMain.ioLog
								.warning("Standard Objects saving to file failed because writting to file throw exception.");
						e.printStackTrace();
					}
				}
				else
				{
					PrimeMain.ioLog.warning("Could not write to file '"
							+ file.getName() + "'.");
				}
			}
			else
			{
				PrimeMain.ioLog.warning("'" + file.getName()
						+ "' is not a file.");
			}
		}
		else
		{
			PrimeMain.ioLog.fine("Objects file,'" + file.getName()
					+ "', does not exist. Creating file.");

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

				PrimeMain.ioLog
						.info("Running systems Standard Objects written to file successfully.");

				return true;
			}
			catch ( Exception e )
			{
				PrimeMain.ioLog
						.warning("Standard Objects saving to file failed because writting to file throw exception.");
				e.printStackTrace();
			}
		}

		PrimeMain.ioLog.warning("Standard Objects saving to file failed.");

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
		PrimeMain.ioLog
				.info("Attempting to load the Standard Objects of the running system from the given file '"
						+ file.getName() + "'.");

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

							PrimeMain.ioLog
									.info("Loaded the Standard Objects from the given file successfully.");
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
					PrimeMain.ioLog.warning("Could not read from '"
							+ file.getName() + "' file.");
				}
			}
			else
			{
				PrimeMain.ioLog.warning("'" + file.getName()
						+ "' is not a file.");
			}
		}
		else
		{
			PrimeMain.ioLog.warning("'" + file.getName() + "' does not exist.");
		}
	}



	/**
	 * TODO - Description
	 */
	public static NetworkRules copyRules(NetworkRules standardRules,
			NetworkRules newRules)
	{
		PrimeMain.ioLog
				.info("Copying the given Network Rules from one given variable to another variable.");
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
	 * This function checks whether or not an standard rules file exists in the
	 * resources folder with the name rules.dat
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
		}

		return "";
	}



	/**
	 * This function attempts to retrieve the serial number of the
	 * {@link WorkareaCanvas} contained inside the give file.
	 */
	public static UUID getWorkareaCanvasSerialFromFile(File file)
	{
		if ( file != null )
		{
			PrimeMain.ioLog
					.info("Attempting to get the serial number of the canvas inside the given file.");


			UUID serial = null;


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
							serial = (UUID) ois.readObject();

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
						PrimeMain.ioLog.warning("Could not read from '"
								+ file.getName() + "' file.");
					}
				}
				else
				{
					PrimeMain.ioLog.warning("'" + file.getName()
							+ "' is not a file.");
				}
			}
			else
			{
				PrimeMain.ioLog.warning("'" + file.getName()
						+ "' does not exist.");
			}

			PrimeMain.ioLog
					.info("Serial number of the canvas inside was successfully retrieved.");

			return serial;
		}

		PrimeMain.ioLog
				.warning("Serial number retrieval failed because given file was NULL.");

		return null;
	}
}