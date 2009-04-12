/**
 * 
 */
package managment;


import graphics.PrimeMain1;
import graphics.GUI.selectArea.PrimeJTree.FileTreeNode;
import graphics.GUI.workareaCanvas.WorkareaCanvas;
import graphics.GUI.workareaCanvas.WorkareaSceneScroll;
import graphics.GUI.workareaCanvas.providers.AdapterExtended;

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
import javax.swing.JTree;

import objects.Object;

import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;

import widgetManipulation.WidgetObject;
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
		File file = new File("./Data/" + canvas.getCanvasName() + ".dat");

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

		try
		{
			FileOutputStream fout = new FileOutputStream(file);

			// The object stream file
			ObjectOutputStream oos = new ObjectOutputStream(fout);

			// The name of the canvas
			String nameOfCanvas = canvas.getCanvasName();

			// Writes out the name of the canvas
			oos.writeObject(nameOfCanvas);



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
	public static void deleteWorkareaCanvas(FileTreeNode fileNode, JTree tree)
	{
		// Checks on the file before any work is done
		File file = fileNode.getFile();



		// If the file does not exist
		if ( !file.exists() )
		{
			JOptionPane.showMessageDialog(null, "This file\n" + file.getName() + "\n" + "does not exist in location\n"
					+ file.getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);

			return;
		}

		// If the file is a directory
		if ( file.isDirectory() )
		{
			JOptionPane.showMessageDialog(null, "This \n" + file.getName() + "\n" + "is a directory at\n"
					+ file.getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);

			return;
		}

		// If the file can not be written to(hence not deleted)
		if ( !file.canWrite() )
		{
			JOptionPane.showMessageDialog(null, "This file\n" + file.getName() + "\n" + "is write protected.", "Error",
					JOptionPane.ERROR_MESSAGE);

			return;
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
			}
			else
			{
				// Reloads
				PrimeMain1.updatePrimeTree();

				JOptionPane.showMessageDialog(null, "The file\n" + file.getName() + "\n" + "was successfully deleted.",
						"Success", JOptionPane.PLAIN_MESSAGE);
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
		String nameOfCanvas = (String) JOptionPane.showInputDialog(null, "Network Name", "New Network Name",
				JOptionPane.QUESTION_MESSAGE);

		newCanvas(nameOfCanvas);
	}



	/**
	 * Creates a new WorkareaCanvas. The new WorkareaCanvas is opened in the workarea. The name of the new
	 * WorkareaCanvas will be the given String.
	 * 
	 * @param nameOfCanvas
	 */
	public static void newWorkareaCanvas(String nameOfCanvas)
	{
		newCanvas(nameOfCanvas);
	}



	/**
	 * Creates a new WorkareaCanvas with the given name. The name is checked to see if it matched with the allowed
	 * symbols, which are letters, numbers and underscore. After the WorkareaCanvas is created it is added to the
	 * programs workarea.
	 * 
	 * @param nameOfCanvas
	 *            The name that the WorkareaCanvas will have, after being checked.
	 */
	private static void newCanvas(String nameOfCanvas)
	{

		// IF the user has canceled
		if ( nameOfCanvas != null )
		{
			if ( Pattern.matches("([a-zA-ZøæåØÆÅ_0-9])*", nameOfCanvas) )
			{
				// Checks whether or not there exist a canvas with the same
				if ( !CanvasManagment.canvasExists(nameOfCanvas) )
				{
					WorkareaCanvas canvas = new WorkareaCanvas(nameOfCanvas);

					// First creates the WorkareaSceneScroll object that will
					// hold
					WorkareaSceneScroll newScroll = new WorkareaSceneScroll(canvas);


					// Tries to write out the WorkareaCanvas to a file
					try
					{
						FileOutputStream fout = new FileOutputStream("./resource/Data/" + canvas.getCanvasName() + ".dat");


						// The object stream file
						ObjectOutputStream oos = new ObjectOutputStream(fout);

						// Writes out the name of the canvas
						oos.writeObject(nameOfCanvas);


						oos.flush();
						oos.close();

						// Saves the canvas and creates the correct End Of File
						saveWorkareaCanvas(canvas);

						// Then we add the JScrollPane to the Screen
						PrimeMain1.workTab.createNewCanvasTab(newScroll);

						// Reloads the JTree
						PrimeMain1.updatePrimeTree();

						PrimeMain1.workTab.revalidate();
						PrimeMain1.workTab.repaint();
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

						// The array anchor
						connection.setTargetAnchorShape(AnchorShape.NONE);
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
		catch ( Exception e )
		{
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
