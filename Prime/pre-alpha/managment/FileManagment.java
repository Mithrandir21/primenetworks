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
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTree;

import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;

import connections.Connection;
import connections.WidgetExtendedConnection;

import objects.Object;
import widgetManipulation.WidgetObject;
import actions.graphicalActions.WorkareaCanvasActions;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class FileManagment
{

	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 */
	public static void saveWorkareaCanvas(WorkareaCanvas canvas)
	{
		try
		{
			// The file that will be examined and read for canvas information
			FileOutputStream fout = new FileOutputStream("./Data/" + canvas.getCanvasName() + ".dat");

			// The object stream file
			ObjectOutputStream oos = new ObjectOutputStream(fout);

			// The name of the canvas
			String nameOfCanvas = canvas.getCanvasName();

			// Writes out the name of the canvas
			oos.writeObject(nameOfCanvas);



			// WRITE OBJECTS ON THE CANVAS

			// The objects on the canvas
			Object[] objects = canvas.getObjectsOnTheScene();


			// There must be some objects on the canvas for there to be saved
			// any widgetObjects
			if ( objects.length > 0 )
			{
				// The ArrayList of object that are on the canvas
				ArrayList<Object> objectList = new ArrayList<Object>();

				
				// Goes through all the object on the canvas and adds then to
				// the arraylist.
				for ( int i = 0; i < objects.length; i++ )
				{
					objectList.add(objects[i]);
				}

				// Writes out the objects in the form of an arraylist.
				oos.writeObject(objectList);
			}

			oos.flush();


			// END OF WRITE OBJECTS



			// WRITE CONNECTIONS ON THE CANVAS

			// The canvas connections
			Connection[] connections = canvas.getConnections();


			// The must be at least on connection on the canvas for there to be
			// saved any connections
			if ( connections.length > 0 )
			{
				// The ArrayList that will hold the connections
				ArrayList<Connection> connectionList = new ArrayList<Connection>();

				// Goes through all the connections on the canvas and adds them
				// to the ArrayList
				for ( int i = 0; i < connections.length; i++ )
				{
					connectionList.add(connections[i]);
				}

				// Writes out the connections ArrayList
				oos.writeObject(connectionList);
			}


			// END OF WRITE CONNECTIONS



			oos.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		canvas.setSaved(true);
	}


	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param file
	 */
	public static void saveWorkareaCanvas(WorkareaCanvas canvas, File file)
	{
		try
		{
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			// XMLEncoder e = new XMLEncoder(oos);
			// e.writeObject(canvas);
			// e.flush();
			// e.writeObject(canvas.getScene());
			// e.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param file
	 */
	public static void deleteWorkareaCanvas(FileTreeNode fileNode, JTree tree)
	{
		// Checks on the file before any work is done
		File file = fileNode.getFile();


		// If the file is a directory
		if ( file.isDirectory() )
		{
			JOptionPane.showMessageDialog(null, "This \n" + file.getName() + "\n" + "is a directory at\n"
					+ file.getAbsolutePath());

			return;
		}


		// If the file does not exist
		if ( !file.exists() )
		{
			JOptionPane.showMessageDialog(null, "This file\n" + file.getName() + "\n" + "does not exist in location\n"
					+ file.getAbsolutePath());

			return;
		}

		// If the file can not be written to(hence not deleted)
		if ( !file.canWrite() )
		{
			JOptionPane.showMessageDialog(null, "This file\n" + file.getName() + "\n" + "is write protected.");

			return;
		}



		// Attempt to delete it
		boolean success = file.delete();

		if ( !success )
		{
			JOptionPane.showMessageDialog(null, "The file\n" + file.getName() + "\n" + "was NOT successfully deleted.");
		}
		else
		{
			// Reloads
			PrimeMain1.updatePrimeTree();

			JOptionPane.showMessageDialog(null, "The file\n" + file.getName() + "\n" + "was successfully deleted.");


		}
	}


	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 */
	public static void deleteWorkareaCanvas(WorkareaCanvas canvas)
	{

	}




	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public static void newWorkareaCanvas()
	{
		String nameOfCanvas = (String) JOptionPane.showInputDialog(null, "Network Name", "New Network Name",
				JOptionPane.QUESTION_MESSAGE);

		if ( Pattern.matches("([a-zA-ZøæåØÆÅ_0-9])*", nameOfCanvas) )
		{
			// First creates the WorkareaSceneScroll object that will hold
			WorkareaSceneScroll newScroll = new WorkareaSceneScroll(nameOfCanvas);

			// Then we add the JScrollPane to the Screen
			PrimeMain1.workTab.createNewCanvasTab(newScroll);
			PrimeMain1.workTab.revalidate();
			PrimeMain1.workTab.repaint();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "This name, (" + nameOfCanvas + "), is not an accepted name.\n"
					+ "The name can only contains letters, numbers and an underscore.");
		}

		// Maybe add the new canvas to the JTree and save it?
	}




	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param file
	 */
	@SuppressWarnings("unchecked")
	public static void openWorkareaCanvas(File file)
	{
		WorkareaCanvas canvas = new WorkareaCanvas();

		try
		{
			// The input file that is going to be read from.
			FileInputStream fin = new FileInputStream(file);

			// The input stream
			ObjectInputStream ois = new ObjectInputStream(fin);

			// Read the name of the canvas
			String canvasName = (String) ois.readObject();

			// Sets the canvas name
			canvas.setCanvasName(canvasName);


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

			// Goes through the array of objects and adds them to the newly made
			// canvas
			for ( int i = 0; i < objects.length; i++ )
			{
				WorkareaCanvasActions.addObjectToCanvas(objects[i], canvas);
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
					WidgetExtendedConnection connection = new WidgetExtendedConnection(canvas.getScene(),
							connections[i]);


					// Find the two object which are to be connected on the
					// canvas
					WidgetObject sourceWidget = CanvasManagment.findWidgetObjectByObjectName(connections[i]
							.getObject1(), canvas);
					WidgetObject targetWidget = CanvasManagment.findWidgetObjectByObjectName(connections[i]
							.getObject2(), canvas);

					// Adds the connection to the connection array for the
					// WorkareaCanvas.
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


			ois.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}


		PrimeMain1.getWorkarea().createNewCanvasTab(canvas);
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvasName
	 */
	public static void openWorkareaCanvas(String canvasName)
	{
		WorkareaCanvas canvas = null;

		try
		{
			FileInputStream fin = new FileInputStream(canvasName + ".dat");
			ObjectInputStream ois = new ObjectInputStream(fin);






		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		if ( canvas == null )
		{
			System.out.println("Still not right. Canvas == null");
		}
		else
		{
			System.out.println(canvas.getCanvasName());
			PrimeMain1.getWorkarea().createNewCanvasTab(canvas);
		}
	}

}
