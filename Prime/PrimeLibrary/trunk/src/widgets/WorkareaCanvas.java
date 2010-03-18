/**
 * 
 */
package widgets;


import java.awt.Point;
import java.awt.dnd.DropTarget;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.undo.UndoableEdit;

import logistical.CanvasUndoManager;
import logistical.cleanup;
import objects.Object;
import objects.Room;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.InternalNetworksCard;

import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetTransferHandler;
import widgetManipulation.WorkareaCanvasNetworkInfo;
import widgetManipulation.Actions.WorkareaCanvasActions;
import connections.Connection;




/**
 * A visual canvas that will hold all the object of any given network. The
 * object can be moved around, deleted or connected to other object. The canvas
 * has some special feature like zooming, scrolling and panning. The canvas has
 * D'n'D(Drag and Drop) features. Any Widget can be dragged and dropped onto the
 * the canvas. The widget will then be converted into a {@link WidgetObject
 * WidgetObject} and a standard object will be created. The object class depends
 * on the class of the dragged widget. The object will be created and place
 * within the WidgetObject.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */

public class WorkareaCanvas extends JPanel
{
	// The Name of the canvas
	private String CanvasName = "";

	// The individual serialnumber of the workareacanvas
	private double serial = 0;

	// The transferHandler that will take care of the drag and drop feature for
	// the canvas
	private TransferHandler TransHandler = new WidgetTransferHandler();

	private DropTarget dt = null;

	// The scene on the canvas which all objects and layers will be placed
	private ObjectScene scene = new ObjectScene();

	private JComponent myView = scene.createView();

	// The main layer of the scene where the WidgetObjects are placed
	private LayerWidget mainLayer = null;

	// The interaction layer where the interaction between the user and the
	// WidgetObjects takes place
	private LayerWidget interactionLayer = null;

	// The room layer where the network rooms will be placed
	private LayerWidget roomLayer = null;

	// The connection layer where the connections between WidgetObjects are
	// placed
	private LayerWidget connectionLayer = null;

	// An array that will contain all the canvases connection, which are
	// represented by a WidgetExtendedConnection.
	private Connection[] connections = new Connection[5];

	// The current WidgetObject in view
	private WidgetObject currentWidgetObject = null;


	// FIXME - Create array of amount of different object types on the scene
	private int numberOfWidgetsOnTheScene = 0;

	// The number of Network cards on the canvas
	private int numberOfNICs = 0;

	// A boolean saying if the canvas has been saved
	private boolean saved = false;

	// A boolean saying if the canvas has been changed in any way
	private boolean changed = false;

	// The WorkareaCanvas network info
	private WorkareaCanvasNetworkInfo networkInfo;

	// The undo manager for this canvas
	private CanvasUndoManager undoManager;




	/**
	 * A constructor for the canvas class. This constructor will set the drop
	 * handling and all the special features the canvas has.
	 */
	public WorkareaCanvas()
	{
		makeCanvasReady();
	}


	/**
	 * A constructor for the canvas class.
	 * 
	 * @param canvasName
	 *            The name of the Canvas.
	 */
	public WorkareaCanvas(String canvasName)
	{
		this.setCanvasName(canvasName);
		makeCanvasReady();
	}




	/**
	 * This constructor will set the drop handling and all the special features
	 * the canvas has.
	 */
	public void makeCanvasReady()
	{
		// A random number that will be the serial number of the network
		serial = (Math.random()) * 500;

		// Creating the actual view
		myView.setTransferHandler(TransHandler);

		// scene.getActions().addAction(ActionFactory.createSelectAction(new
		// CreateProvider()));
		// this.addMouseListener(new WorkareaCanvasListener(this));


		// This is the main layer of the scene where the WidgetsObjects are
		// placed.
		mainLayer = new LayerWidget(scene);
		scene.addChild(mainLayer);

		// This is room layer
		roomLayer = new LayerWidget(scene);
		scene.addChild(roomLayer);

		// This is the interaction layer
		interactionLayer = new LayerWidget(scene);
		scene.addChild(interactionLayer);

		// This is the connection layer where all the connections between the
		// objects are shown.
		connectionLayer = new LayerWidget(scene);
		scene.addChild(connectionLayer);

		// Initiates the undomanager for this canvas
		undoManager = new CanvasUndoManager(this);

		saved = false;
		changed = false;
	}


	/**
	 * Gets the name of the canvas.
	 * 
	 * @return the canvasName
	 */
	public String getCanvasName()
	{
		return CanvasName;
	}


	/**
	 * Gets the unique network serial.
	 * 
	 * @return the serial
	 */
	public double getSerial()
	{
		return serial;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the dt
	 */
	public DropTarget getDropTarget()
	{
		return dt;
	}


	/**
	 * Gets the scene.
	 * 
	 * @see org.netbeans.api.visual.widget.Scene Scene
	 * @return the scene
	 */
	public Scene getScene()
	{
		return scene;
	}


	/**
	 * Gets the scene.
	 * 
	 * @see org.netbeans.api.visual.widget.Scene Scene
	 * @return the scene
	 */
	public ObjectScene getObjectScene()
	{
		return scene;
	}


	/**
	 * Gets the view of the scene. Scene can only be viewed through a view like
	 * this.
	 * 
	 * @see org.netbeans.api.visual.widget.Scene#getView() View
	 * @return the myView
	 */
	public JComponent getMyView()
	{
		return myView;
	}


	/**
	 * Returns the main layer of the scene. This is where the {@link WidgetObject WidgetObjects} are placed.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 * @return the mainLayer
	 */
	public LayerWidget getMainLayer()
	{
		return mainLayer;
	}


	/**
	 * Returns the interaction layer of the scene. This is where the actions a
	 * user sees take place like clicks and menus.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 * @return the interactionLayer
	 */
	public LayerWidget getInteractionLayer()
	{
		return interactionLayer;
	}


	/**
	 * Returns the {@link LayerWidget} that holds all the {@link WidgetRoom
	 * WidgetRooms}.
	 * 
	 * @return Returns the {@link LayerWidget} that holds all the {@link WidgetRoom WidgetRooms}.
	 */
	public LayerWidget getRoomLayer()
	{
		return roomLayer;
	}


	/**
	 * Gets the connection layer of the scene. This is where the {@link connections.WidgetExtendedConnection connections} between
	 * objects
	 * in the scene are placed.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 * @return the connectionLayer
	 */
	public LayerWidget getConnectionLayer()
	{
		return connectionLayer;
	}


	/**
	 * Gets the number of objects on the Scene.
	 * 
	 * @return the numberOfWidgetsOnTheScene
	 */
	public int getNumberOfWidgetsOnTheScene()
	{
		return numberOfWidgetsOnTheScene;
	}


	/**
	 * Gets a boolean that tells whether or not this {@link WorkareaCanvas} has
	 * been saved.
	 */
	public boolean isSaved()
	{
		return saved;
	}


	/**
	 * Gets a boolean that tells whether or not this {@link WorkareaCanvas} has
	 * been altered and not saved.
	 */
	public boolean isChanged()
	{
		return changed;
	}


	/**
	 * Gets the {@link WorkareaCanvasNetworkInfo} that holds network information
	 * like netmask and IP range.
	 * 
	 * @return A {@link WorkareaCanvasNetworkInfo} with the network information
	 *         about the {@link WorkareaCanvas}.
	 */
	public WorkareaCanvasNetworkInfo getNetworkInfo()
	{
		return networkInfo;
	}


	/**
	 * Gets all the {@link WidgetObject WidgetObjectes} on the scene of this {@link WorkareaCanvas}.
	 * 
	 * @return An {@link WidgetObject} array with all the {@link WidgetObject
	 *         WidgetObjectes} on the scene.
	 */
	public WidgetObject[] getWidgetObjectsOnTheScene()
	{
		// Get a list of Widgets in the mainlayer
		List<Widget> l = mainLayer.getChildren();

		// Converts that list to an array of Objects
		java.lang.Object[] childrenTemp = l.toArray();

		// Creates an array with the length of the all the children on the
		// canvas
		WidgetObject[] childrenWidgets = new WidgetObject[childrenTemp.length];

		// Casts all the objects in the converted list to widgetobjects
		for ( int i = 0; i < childrenWidgets.length; i++ )
		{
			childrenWidgets[i] = (WidgetObject) childrenTemp[i];
		}


		return childrenWidgets;
	}



	/**
	 * This method gets all the objects on the scene.
	 */
	public Object[] getObjectsOnTheScene()
	{
		// Get a list of Widgets in the mainlayer
		List<Widget> l = mainLayer.getChildren();

		// Converts that list to an array of Objects
		java.lang.Object[] childrenTemp = l.toArray();

		// Creates an array with the length of the all the children on the
		// canvas
		WidgetObject[] childrenWidgets = new WidgetObject[childrenTemp.length];

		// Casts all the objects in the converted list to widgetobjects
		for ( int i = 0; i < childrenWidgets.length; i++ )
		{
			childrenWidgets[i] = (WidgetObject) childrenTemp[i];
		}

		// Creates an array with the length of the all the children on the
		// canvas
		Object[] childrenObject = new Object[childrenTemp.length];

		// Gets and places all the objects from within every widgetobject in an
		// array
		for ( int i = 0; i < childrenObject.length; i++ )
		{
			childrenObject[i] = childrenWidgets[i].getObject();
		}

		// Returns an array with only the scenes objects.
		return childrenObject;
	}



	/**
	 * This method gets all {@link WidgetRoom WidgetRooms} in the {@link WorkareaCanvas}.
	 */
	public WidgetRoom[] getNetworkWidgetRooms()
	{
		// Get a list of Widgets in the mainlayer
		List<Widget> l = roomLayer.getChildren();

		// Converts that list to an array of Objects
		java.lang.Object[] roomTemp = l.toArray();

		// Creates an array with the length of the all the children on the
		// canvas
		WidgetRoom[] roomWidgets = new WidgetRoom[roomTemp.length];

		// Casts all the objects in the converted list to widgetobjects
		for ( int i = 0; i < roomWidgets.length; i++ )
		{
			roomWidgets[i] = (WidgetRoom) roomTemp[i];
		}

		roomWidgets = cleanup.cleanObjectArray(roomWidgets);

		// Returns an array with only the scenes objects.
		return roomWidgets;
	}



	/**
	 * This method gets all {@link Room Rooms} contained inside the {@link WidgetRoom WidgetRooms} in the {@link WorkareaCanvas}.
	 */
	public Room[] getNetworkRooms()
	{
		// Get a list of Widgets in the mainlayer
		List<Widget> l = roomLayer.getChildren();

		// Converts that list to an array of Objects
		java.lang.Object[] roomTemp = l.toArray();

		// Creates an array with the length of the all the children on the
		// canvas
		WidgetRoom[] roomWidgets = new WidgetRoom[roomTemp.length];

		// Casts all the objects in the converted list to widgetobjects
		for ( int i = 0; i < roomWidgets.length; i++ )
		{
			roomWidgets[i] = (WidgetRoom) roomTemp[i];
		}

		// Creates an array with the length of the all the children on the
		// canvas
		Room[] rooms = new Room[roomTemp.length];

		// Gets and places all the objects from within every widgetobject in an
		// array
		for ( int i = 0; i < rooms.length; i++ )
		{
			rooms[i] = roomWidgets[i].getRoom();
		}

		// Returns an array with only the scenes objects.
		return rooms;
	}





	/**
	 * Gets the number of Networks cards on the Scene.
	 * 
	 * @return Returns the number of network cards on the scene. Both {@link InternalNetworksCard}s and
	 *         {@link ExternalNetworksCard}s.
	 */
	public int getNumberOfNICsOnTheScene()
	{
		return numberOfNICs;
	}


	/**
	 * Adds one int to the number of Widgets on the canvas scene.
	 */
	public void addToNumberOfWidgetsOnTheCanvas()
	{
		numberOfWidgetsOnTheScene++;
	}



	/**
	 * Subtracts one int from the the number of Widgets on the canvas scene.
	 */
	public void subtractFromNumberOgWidgetsOnTheCanvas()
	{
		numberOfWidgetsOnTheScene--;
	}


	/**
	 * Gets the array of connections between objects. <i>Note: These connections
	 * are not the visual connections shown on the scene, but the actual
	 * connections between the objects within the different widgetObjects.</i>
	 */
	public Connection[] getConnections()
	{
		return connections;
	}


	/**
	 * Gets the currently selected widgetObject.
	 * 
	 * @return Returns the currently selected widgetObject.
	 */
	public WidgetObject getCurrentWidgetObject()
	{
		return currentWidgetObject;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the undoManager
	 */
	public CanvasUndoManager getUndoManager()
	{
		return undoManager;
	}


	/**
	 * Sets the given string as the canvas name.
	 */
	public void setCanvasName(String canvasName)
	{
		CanvasName = canvasName;
	}



	/**
	 * Sets the unique network serial.
	 * 
	 * @param serial
	 *            the serial to set
	 */
	public void setSerial(double serial)
	{
		this.serial = serial;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the dt
	 */
	public void setDropTarget(DropTarget dt)
	{
		this.dt = dt;
	}


	/**
	 * Sets the WorkareaCanvases scene.
	 */
	public void setScene(ObjectScene scene)
	{
		this.scene = scene;
	}




	/**
	 * Sets the view of the scene. Scene can only be viewed through a view like
	 * this.
	 * 
	 * @see org.netbeans.api.visual.widget.Scene#getView() View
	 */
	public void setMyView(JComponent view)
	{
		myView = view;
	}


	/**
	 * Sets the main layer of the scene. This is where the {@link WidgetObject
	 * WidgetObjects} are placed.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 */
	public void setMainLayer(LayerWidget main)
	{
		mainLayer = main;
	}


	/**
	 * Sets the interaction layer of the scene. This is where the actions a user
	 * sees take place like clicks and menus.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 */
	public void setInteractionLayer(LayerWidget inter)
	{
		interactionLayer = inter;
	}


	/**
	 * Sets the room layer of the scene. This is where the {@link WidgetRoom
	 * WidgetRooms} are placed.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 */
	public void setRoomLayer(LayerWidget roomLayer)
	{
		this.roomLayer = roomLayer;
	}


	/**
	 * Sets the connection layer of the scene. This is where the {@link connections.WidgetExtendedConnection connections} between
	 * objects
	 * in the scene are placed.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 */
	public void getConnectionLayer(LayerWidget con)
	{
		connectionLayer = con;
	}


	/**
	 * Sets the connections array for the canvas. <i>Note: These connections are
	 * not the visual connections shown on the scene, but the actual connections
	 * between the objects within the different widgetObjects.</i>
	 * 
	 * @param connections
	 */
	public void setConnections(Connection[] connections)
	{
		this.connections = connections;
	}



	/**
	 * Sets the current widgetObject.
	 * 
	 * @param currentWidgetObject
	 */
	public void setCurrentWidgetObject(WidgetObject currentWidgetObject)
	{
		// If the given WidgetObject is null
		if ( currentWidgetObject == null )
		{
			// If the current selected widgetObject is not null
			if ( this.currentWidgetObject != null )
			{
				// // Removes the borders around the current selected object
				// this.currentWidgetObject.setBorder(BorderFactory
				// .createEmptyBorder());
				this.currentWidgetObject = null;
			}
		}
		else
		{
			// If the current selected widgetObject is not null
			if ( this.currentWidgetObject != null )
			{
				// // Removes the borders around the current selected object
				// this.currentWidgetObject.setBorder(BorderFactory
				// .createEmptyBorder());
				this.currentWidgetObject = null;
			}

			// The now currently selected widgetObject is given a border
			this.currentWidgetObject = currentWidgetObject;
			// this.currentWidgetObject.setBorder(BorderFactory
			// .createRoundedBorder(7, 7, Color.white, Color.black));
		}
	}



	/**
	 * Sets a boolean on whether or not this {@link WorkareaCanvas} has been
	 * saved.
	 */
	public void setSaved(boolean saved)
	{
		this.saved = saved;
	}


	/**
	 * Sets a boolean on whether or not this {@link WorkareaCanvas} has been
	 * altered and not saved.
	 */
	public void setChanged(boolean changed)
	{
		this.changed = changed;
	}


	/**
	 * Sets the {@link WorkareaCanvasNetworkInfo} that holds network information
	 * like netmask and IP range.
	 * 
	 * @return A {@link WorkareaCanvasNetworkInfo} with the network information
	 *         about the {@link WorkareaCanvas}.
	 */
	public void setNetworkInfo(WorkareaCanvasNetworkInfo networkInfo)
	{
		this.networkInfo = networkInfo;
	}


	/**
	 * Adds 1 to the number of network cards on the scene.
	 */
	public void addNIC()
	{
		numberOfNICs++;
	}


	/**
	 * Adds the given WidgetObject at the given point on the scene.
	 * 
	 * @param newObject
	 * @param objectPoint
	 */
	public void addWidgetObject(WidgetObject newObject, Point objectPoint,
			boolean withCleanUp)
	{
		// Sets the location of the object.
		newObject.getObject().setLocation(objectPoint);

		WorkareaCanvasActions.addWidgetToCanvas(newObject, objectPoint, this,
				withCleanUp);
	}


	/**
	 * Adds a undoable action to the canvases undomanager.
	 */
	public void addUndoableAction(UndoableEdit action)
	{
		undoManager.addEdit(action);
	}


	/**
	 * This method cleans up the canvas. The scene and all the views are
	 * repainted and revalidated. The properties panel is also updated.
	 */
	public void cleanUp()
	{
		doRepaint();

		saved = false;
		changed = true;

		scene.revalidate();
		scene.repaint();

		myView.revalidate();
		myView.repaint();

		mainLayer.revalidate();
		mainLayer.repaint();

		interactionLayer.revalidate();
		interactionLayer.repaint();

		connectionLayer.revalidate();
		connectionLayer.repaint();
	}




	/**
	 * This function goes through all the widgetObjects on the scene and sets
	 * the location of the widget on the scene to the location of the object
	 * within the WidgetObject.
	 */
	public void revalidateWidgetLocations()
	{

		// The Widgets on the scene
		WidgetObject[] widgets = this.getWidgetObjectsOnTheScene();

		// Iterates through the Object list
		for ( int i = 0; i < widgets.length; i++ )
		{
			// Gets the object from the widget
			Object obj = widgets[i].getObject();

			// Sets the object location
			obj.setLocation(widgets[i].getLocation());
		}


		// The rooms on the scene
		WidgetRoom[] rooms = this.getNetworkWidgetRooms();

		// Iterates through the Room list
		for ( int i = 0; i < rooms.length; i++ )
		{
			// Gets the room from the widget
			Room room = rooms[i].getRoom();

			// Sets the rooms location
			room.setLocation(rooms[i].getLocation());

			// Sets the rooms bounds
			room.setBounds(rooms[i].getBounds());
		}
	}




	/**
	 * This function stops the random NullPointerExceptions. (Combined
	 * org.netbeans.api and Java bug).
	 */
	public void doRepaint()
	{
		// The Nodes API can fire events outside the AWT Thread
		if ( SwingUtilities.isEventDispatchThread() )
		{
			repaint();
			scene.getScene().validate();
			// required or repaint() doesn’t work
		}
		else
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					repaint();
					scene.getScene().validate();
				}
			});
		}
	}
}