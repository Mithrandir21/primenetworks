/**
 * 
 */
package managment;


import graphics.PrimeMain1;
import graphics.GUI.selectArea.PrimeJTree.FileTreeNode;
import graphics.GUI.workareaCanvas.WorkareaCanvas;
import graphics.GUI.workareaCanvas.WorkareaSceneScroll;
import graphics.GUI.workareaCanvas.providers.AdapterExtended;

import java.awt.BasicStroke;
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

import javax.swing.JOptionPane;

import objects.Object;

import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;

import widgetManipulation.WidgetObject;
import widgetManipulation.WorkareaCanvasNetworkInfo;
import actions.graphicalActions.WorkareaCanvasActions;
import connections.Connection;
import connections.WidgetExtendedConnection;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class FileManagment
{

	/**
	 * Saves the given WorkareaCanvas. This function creates a file object from the name of the WorkareaCanvas and
	 * passes that on the function that actually saves the WorkareaCanvas.
	 * 
	 * @param canvas
	 *            The WorkareaCanvas to be written out.
	 */
	public static void saveWorkareaCanvas(WorkareaCanvas canvas)
	{
		File file = new File("./resource/Data/" + canvas.getCanvasName() + ".dat");

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
	 * Saves the given WorkareaCanvas in the given File. The name of the WorkareaCanvas is written out to the file as an
	 * object, so is an ArrayList of the WidgetObjects and Connections on the WorkareaCanvas.
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


				// There must be some objects on the canvas for there to be saved any widgetObjects
				if ( objects.length > 0 )
				{
					// Goes through all the object on the canvas and adds then to the arraylist
					for ( int i = 0; i < objects.length; i++ )
					{
						objectList.add(objects[i]);
					}

				}

				// Writes out the objects in the form of an arraylist, even if it is empty
				oos.writeObject(objectList);

				oos.flush();


				// END OF WRITE OBJECTS



				// WRITE CONNECTIONS ON THE CANVAS

				// The canvas connections
				Connection[] connections = canvas.getConnections();

				// The ArrayList that will hold the connections
				ArrayList<Connection> connectionList = new ArrayList<Connection>();

				// The must be at least on connection on the canvas for there to be saved any connections
				if ( connections.length > 0 && connections[0] != null )
				{
					// Goes through all the connections on the canvas and adds them to the ArrayList
					for ( int i = 0; i < connections.length; i++ )
					{
						connectionList.add(connections[i]);
					}
				}

				// Writes out the connections ArrayList, even if it is empty
				oos.writeObject(connectionList);

				// END OF WRITE CONNECTIONS


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
	 * This function checks whether or not there exist a file containing a WorkareaCanvas with the same and serial as
	 * the given WorkareaCanvas. If not true is returned. If there exists a WorkareaCanvas with the same name, but not
	 * serial number the user is asked to verify overwriting that file.
	 * 
	 * @param canvas
	 * @return
	 */
	private static boolean checkAndVerify(WorkareaCanvas canvas)
	{
		String canvasName = canvas.getCanvasName();
		double canvasSerial = canvas.getSerial();

		// Creates a file object(not the actual file)
		File file = new File("./resource/Data/" + canvas.getCanvasName() + ".dat");

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

				// If the name of the file network is the same as the name of the given WorkareaCanvas
				if ( name.equalsIgnoreCase(canvasName) )
				{
					// If the serial from the file is not the same as the serial from the given WorkareaCanvas
					if ( serial != canvasSerial )
					{
						int answer = JOptionPane.showConfirmDialog(null,
								"There exists a network with given name. Do you wish to overwrite that file?",
								"Overwrite", JOptionPane.YES_NO_OPTION);

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
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @return
	 */
	public static boolean fileWorkareaCanvasExist(WorkareaCanvas canvas, String newName)
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

				// If the name of the file network is the same as the name of the given WorkareaCanvas
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
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param newName
	 */
	public static boolean changeFileName(WorkareaCanvas canvas, String newName)
	{
		// Checks to see whether a canvas file actually exists
		if ( fileWorkareaCanvasExist(canvas, canvas.getCanvasName()) )
		{
			// Creates a file object(not the actual file)
			File file = new File("./resource/Data/" + canvas.getCanvasName() + ".dat");

			// Creates a new file object with the new name
			File fileNew = new File("./resource/Data/" + newName + ".dat");

			// Renames the old file
			boolean result = file.renameTo(fileNew);

			// If the rename was not possible
			if ( !result )
			{
				JOptionPane.showMessageDialog(null, "Rename was not possible.", "Error", JOptionPane.ERROR_MESSAGE);
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
	 * This function removes WorkareaCanvas with the given name from the system. It also deletes the file that contains
	 * the workareaCanvas from the file system, so this is permanent.
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
	 * This function removes WorkareaCanvas with the given name from the system. It also deletes the file that contains
	 * the workareaCanvas from the file system, so this is permanent.
	 * 
	 * @param fileNode
	 *            The file that contains the WorkareaCanvas.
	 */
	public static boolean deleteWorkareaCanvas(WorkareaCanvas canvas)
	{
		// Creates a file object(not the actual file)
		File file = new File("./resource/Data/" + canvas.getCanvasName() + ".dat");

		return deleteWorkareaCanvas(file);
	}




	/**
	 * This function removes WorkareaCanvas with the given name from the system. It also deletes the file that contains
	 * the workareaCanvas from the file system, so this is permanent.
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
			JOptionPane.showMessageDialog(null, "This file\n" + file.getName() + "\n" + "does not exist in location\n"
					+ file.getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);

			return false;
		}

		// If the file is a directory
		if ( file.isDirectory() )
		{
			JOptionPane.showMessageDialog(null, "This \n" + file.getName() + "\n" + "is a directory at\n"
					+ file.getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);

			return false;
		}

		// If the file can not be written to(hence not deleted)
		if ( !file.canWrite() )
		{
			JOptionPane.showMessageDialog(null, "This file\n" + file.getName() + "\n" + "is write protected.", "Error",
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
			canvas = CanvasManagment.findCanvas(canvasName);
		}

		// No open canvas was found
		if ( canvas == null )
		{
			// Attempt to delete it
			boolean success = file.delete();

			if ( !success )
			{
				JOptionPane.showMessageDialog(null, "The file\n" + file.getName() + "\n"
						+ "was NOT successfully deleted.", "Error", JOptionPane.ERROR_MESSAGE);

				return false;
			}
			else
			{
				// Reloads
				PrimeMain1.updatePrimeTree();

				JOptionPane.showMessageDialog(null, "The file\n" + file.getName() + "\n" + "was successfully deleted.",
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
				JOptionPane.showMessageDialog(null, "The file\n" + file.getName() + "\n"
						+ "was NOT successfully deleted.", "Error", JOptionPane.ERROR_MESSAGE);

				return false;
			}
			else
			{
				// Reloads
				PrimeMain1.updatePrimeTree();

				// Removes the WorkareScroll with the canvas
				PrimeMain1.workTab.removeTabWithCanvas(canvasName, false);

				// Removed the canvas from the systems canvas array
				CanvasManagment.removeWorkareaCanvas(canvas);

				JOptionPane.showMessageDialog(null, "The file\n" + file.getName() + "\n" + "was successfully deleted.",
						"Success", JOptionPane.PLAIN_MESSAGE);

				return true;
			}
		}
	}




	/**
	 * Creates a new WorkareaCanvas. The user is asked what the name of the new workareaCanvas will be. The new
	 * WorkareaCanvas is then opened in the workarea.
	 * 
	 */
	public static void newWorkareaCanvas()
	{
		new graphics.GUI.workareaCanvas.CreateNewWorkareaCanvas();
	}



	/**
	 * Creates a new WorkareaCanvas. The new WorkareaCanvas is opened in the workarea. The name of the new
	 * WorkareaCanvas will be the given String.
	 * 
	 * @param nameOfCanvas
	 */
	public static void newWorkareaCanvas(String nameOfCanvas)
	{
		newCanvas(nameOfCanvas, null, null, null, null);
	}



	/**
	 * Creates a new WorkareaCanvas. The new WorkareaCanvas is opened in the workarea. The name of the new
	 * WorkareaCanvas will be the given String.
	 * 
	 * @param nameOfCanvas
	 */
	public static void newWorkareaCanvas(String nameOfCanvas, String netmask, String IPfrom, String IPto,
			String networkDesc)
	{
		newCanvas(nameOfCanvas, netmask, IPfrom, IPto, networkDesc);
	}



	/**
	 * Creates a new WorkareaCanvas with the given name. The name is checked to see if it matched with the allowed
	 * symbols, which are letters, numbers and underscore. After the WorkareaCanvas is created it is added to the
	 * programs workarea.
	 * 
	 * @param nameOfCanvas
	 *            The name that the WorkareaCanvas will have, after being checked.
	 */
	private static void newCanvas(String nameOfCanvas, String netmask, String IPfrom, String IPto, String networkDesc)
	{

		// IF the user has canceled
		if ( nameOfCanvas != null )
		{
			if ( Pattern.matches("([a-zA-ZøæåØÆÅ_0-9 ])*", nameOfCanvas) )
			{
				// Checks whether or not there exist a canvas with the same
				if ( !CanvasManagment.canvasExists(nameOfCanvas) )
				{
					WorkareaCanvas canvas = new WorkareaCanvas(nameOfCanvas);

					if ( netmask != null && IPfrom != null && IPto != null )
					{
						// If the netmask, ip from and ip to strings are not ""
						if ( (!netmask.equals("")) && (!(IPfrom.equals(""))) && (!(IPto.equals(""))) )
						{
							// Creates a new network info object
							WorkareaCanvasNetworkInfo netInfo = canvas.getNetworkInfo();

							// Sets the netmask of the network
							netInfo.setNetmask(netmask);

							// Sets the IP range start of the network
							netInfo.setIpRangeFrom(IPfrom);

							// Sets the IP range end of the network
							netInfo.setIpRangeTo(IPto);

							// Sets the network notes
							netInfo.setNetworkNotes(networkDesc);

							// Sets the newly created network info object as the networks info object
							canvas.setNetworkInfo(netInfo);
						}
					}

					// First creates the WorkareaSceneScroll object that will
					// hold
					WorkareaSceneScroll newScroll = new WorkareaSceneScroll(canvas);

					// Then we add the JScrollPane to the Screen
					PrimeMain1.workTab.createNewCanvasTab(newScroll, -1);

					// Reloads the JTree
					PrimeMain1.updatePrimeTree();

					PrimeMain1.workTab.revalidate();
					PrimeMain1.workTab.repaint();

				}
				else
				{
					JOptionPane.showMessageDialog(null, "There exist a Canvas with the same name as this Canvas.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This name, (" + nameOfCanvas + "), is not an accepted name.\n"
						+ "The name can only contains letters, numbers and an underscore.");
			}
		}

		// Maybe add the new canvas to the JTree and save it?
	}





	/**
	 * This method opens a WorkareaCanvas from the given file. It adds the opened canvas the systems Workarea where the
	 * WorkareaCanvas can be edited. It also adds all the objects and connections to the canvas.
	 * 
	 * @param file
	 */
	public static void openWorkareaCanvas(File file)
	{
		openCanvas(file);
	}



	/**
	 * This method opens a WorkareaCanvas from the given file. It adds the opened canvas the systems Workarea where the
	 * WorkareaCanvas can be edited. It also adds all the objects and connections to the canvas.
	 * 
	 * This method creates a File from the given string.
	 * 
	 * @param canvasName
	 */
	public static void openWorkareaCanvas(String canvasName)
	{
		File file = new File("./Data/" + canvasName + ".dat");

		openCanvas(file);
	}




	/**
	 * This method opens a WorkareaCanvas from the given file. It adds the opened canvas the systems Workarea where the
	 * WorkareaCanvas can be edited. It also adds all the objects and connections to the canvas.
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

				// Iterates through the list and adds the objects to the objects array
				for ( Iterator it = objectList.iterator(); it.hasNext(); )
				{
					objects[objectIndex] = (Object) it.next();
					objectIndex++;
				}

				// Goes through the array of objects and adds them to the newly made canvas
				for ( int i = 0; i < objects.length; i++ )
				{
					if ( objects[i] != null )
					{
						WorkareaCanvasActions.addObjectToCanvas(objects[i], canvas);
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

				// Iterates through the list and adds the connections to the connections array
				for ( Iterator it = connectionList.iterator(); it.hasNext(); )
				{
					connections[connectionIndex] = (Connection) it.next();
					connectionIndex++;
				}


				// Goes through the entire connections array and adds the connections to the WorkareaCanvas
				for ( int i = 0; i < connections.length; i++ )
				{
					if ( connections[i] != null )
					{
						// Creates the connection between the two devices on the scene.
						WidgetExtendedConnection connection = new WidgetExtendedConnection(canvas.getScene(),
								connections[i]);


						// Find the two object which are to be connected on the canvas
						WidgetObject sourceWidget = CanvasManagment.findWidgetObjectByObjectName(connections[i]
								.getObject1(), canvas);
						WidgetObject targetWidget = CanvasManagment.findWidgetObjectByObjectName(connections[i]
								.getObject2(), canvas);

						// Adds the connection to the connection array for the WorkareaCanvas
						ConnectionManagment.addConnection(connections[i], false, canvas);

						BasicStroke stroke = null;

						if ( connections[i].getConnectionType().equals("Wireless") )
						{
							stroke = new BasicStroke(1.0f, // Width
									BasicStroke.JOIN_BEVEL, // End cap
									BasicStroke.CAP_BUTT, // Join style
									5.0f, // Miter limit
									new float[] { 21.0f, 13.0f }, // Dash pattern
									0.0f); // Dash phase
						}
						else
						{
							stroke = new BasicStroke(1.0f,// Width
									BasicStroke.JOIN_BEVEL, // End cap
									BasicStroke.CAP_BUTT, // Join style
									5.0f, // Miter limit
									new float[] { 1.0f }, // Dash pattern
									0.0f); // Dash phase
						}

						// The array anchor
						connection.setTargetAnchorShape(AnchorShape.NONE);
						connection.setStroke(stroke);
						connection.setToolTipText("This is a connection");
						connection.getActions().addAction(new AdapterExtended());
						connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceWidget));
						connection.setTargetAnchor(AnchorFactory.createRectangularAnchor(targetWidget));
						canvas.getConnectionLayer().addChild(connection);
					}
				}
			}

			// END OF CONNECTIONS


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

		// As long as there are only two parts to the filename, *name* and ".dat"
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

		// As long as there are only two parts to the filename, *name* and ".dat"
		if ( names.length == 2 && names[1].equalsIgnoreCase("dat") )
		{
			String name = names[0];

			return name;
		}


		return null;
	}



	/**
	 * This function goes through all the given WorkareaCanvases and checks if the canvas has been changed since last
	 * save. If it has been changed, it saves that canvas.
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
				// If the canvas has not been saved(since the last change) and there has been some change
				if ( canvases[i].isSaved() != true && canvases[i].isChanged() == true )
				{
					saveWorkareaCanvas(canvases[i]);
				}
			}
		}
	}

}
