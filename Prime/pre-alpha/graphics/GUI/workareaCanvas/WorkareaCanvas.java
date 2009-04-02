/**
 * 
 */
package graphics.GUI.workareaCanvas;


import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuCanvas.JMenuWorkareaCanvas;

import java.awt.Point;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.Serializable;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import objects.Object;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.InternalNetworksCard;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;
import actions.graphicalActions.WorkareaCanvasActions;
import connections.Connection;




/**
 * A visual canvas that will hold all the object of any given network. The object can be moved around, deleted or
 * connected to other object. The canvas has a come special feature like zooming, scrolling and panning. The canvas has
 * D'n'D(Drag and Drop) features. Any Widget can be dragged and dropped onto the the canvas. The widget will then be
 * converted into a {@link WidgetObject WidgetObject} and a standard object will be created. The object class depends on
 * the class of the dragged widget. The object will be created and place within the WidgetObject.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */

public class WorkareaCanvas extends JPanel implements Serializable, DropTargetListener
{
	// The Name of the canvas
	private String CanvasName = "";

	// The transferHandler that will take care of the drag and drop feature for
	// the canvas
	private TransferHandler TransHandler = new WidgetTransferHandler();

	private DropTarget dt = null;

	// private JPopupMenu popup = CanvasMenu.createPopupMenu(this);

	// The scene on the canvas which all objects and layers will be placed
	private ObjectScene scene = new ObjectScene();

	private JComponent myView = scene.createView();

	// The main layer of the scene where the WidgetObjects are placed
	private LayerWidget mainLayer = null;

	// The interaction layer where the interaction between the user and the WidgetObjects takes place
	private LayerWidget interactionLayer = null;

	// The connection layer where the connections between WidgetObjects are placed
	private LayerWidget connectionLayer = null;

	// An array that will contain all the canvases connection, which are represented by a WidgetExtendedConnection.
	private Connection[] connections = new Connection[5];


	// FIXME - Create array of amount of different object types on the scene
	private int numberOfWidgetsOnTheScene = 0;

	// The number of Network cards on the canvas
	private int numberOfNICs = 0;

	// The current WidgetObject in view
	private WidgetObject currentWidgetObject = null;

	// A boolean saying if the canvas has been saved
	private boolean saved = false;

	// A boolean saying if the canvas has been changed in any way
	private boolean changed = false;




	/**
	 * A constructor for the canvas class. This constructor will set the drop handling and all the special features the
	 * canvas has.
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
	 * This constructor will set the drop handling and all the special features the canvas has.
	 */
	public void makeCanvasReady()
	{
		// Creating the actual view
		myView.setTransferHandler(TransHandler);

		dt = new DropTarget(myView, this);

		// scene.getActions().addAction(ActionFactory.createSelectAction(new
		// CreateProvider()));
		// this.addMouseListener(new WorkareaCanvasListener(this));



		// Adds the zoom feature to the scene.
		scene.getActions().addAction(ActionFactory.createZoomAction());
		scene.getActions().addAction(ActionFactory.createPanAction());
		scene.getActions().addAction(ActionFactory.createPopupMenuAction(new JMenuWorkareaCanvas(this)));


		// This is the main layer of the scene where the WidgetsObjects are
		// placed.
		mainLayer = new LayerWidget(scene);
		scene.addChild(mainLayer);

		// This is the interaction layer
		interactionLayer = new LayerWidget(scene);
		scene.addChild(interactionLayer);

		// This is the connection layer where all the connections between the
		// objects are shown.
		connectionLayer = new LayerWidget(scene);
		scene.addChild(connectionLayer);

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
	 * Gets the view of the scene. Scene can only be viewed through a view like this.
	 * 
	 * @see org.netbeans.api.visual.widget.Scene#getView() View
	 * @return the myView
	 */
	public JComponent getMyView()
	{
		return myView;
	}


	/**
	 * Gets the main layer of the scene. This is where the {@link WidgetObject WidgetObjects} are placed.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 * @return the mainLayer
	 */
	public LayerWidget getMainLayer()
	{
		return mainLayer;
	}


	/**
	 * Gets the interaction layer of the scene. This is where the actions a user sees take place like clicks and menus.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 * @return the interactionLayer
	 */
	public LayerWidget getInteractionLayer()
	{
		return interactionLayer;
	}


	/**
	 * Gets the connection layer of the scene. This is where the {@link connections.WidgetExtendedConnection
	 * connections} between objects in the scene are placed.
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
	 * Gets a boolean that tells whether or not this {@link WorkareaCanvas} has been saved.
	 */
	public boolean isSaved()
	{
		return saved;
	}


	/**
	 * Gets a boolean that tells whether or not this {@link WorkareaCanvas} has been altered and not saved.
	 */
	public boolean isChanged()
	{
		return changed;
	}


	/**
	 * Gets all the {@link WidgetObject WidgetObjectes} on the scene of this {@link WorkareaCanvas}.
	 * 
	 * @return An {@link WidgetObject} array with all the {@link WidgetObject WidgetObjectes} on the scene.
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
	 * Gets the array of connections between objects. <i>Note: These connections are not the visual connections shown on
	 * the scene, but the actual connections between the objects within the different widgetObjects.</i>
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
	 * Sets the given string as the canvas name.
	 */
	public void setCanvasName(String canvasName)
	{
		CanvasName = canvasName;
	}



	/**
	 * Sets the WorkareaCanvases scene.
	 */
	public void setScene(ObjectScene scene)
	{
		this.scene = scene;
	}




	/**
	 * Sets the view of the scene. Scene can only be viewed through a view like this.
	 * 
	 * @see org.netbeans.api.visual.widget.Scene#getView() View
	 */
	public void setMyView(JComponent view)
	{
		myView = view;
	}


	/**
	 * Sets the main layer of the scene. This is where the {@link WidgetObject WidgetObjects} are placed.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 */
	public void setMainLayer(LayerWidget main)
	{
		mainLayer = main;
	}


	/**
	 * Sets the interaction layer of the scene. This is where the actions a user sees take place like clicks and menus.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 */
	public void setInteractionLayer(LayerWidget inter)
	{
		interactionLayer = inter;
	}


	/**
	 * Sets the connection layer of the scene. This is where the {@link connections.WidgetExtendedConnection
	 * connections} between objects in the scene are placed.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 */
	public void getConnectionLayer(LayerWidget con)
	{
		connectionLayer = con;
	}


	/**
	 * Sets the connections array for the canvas. <i>Note: These connections are not the visual connections shown on the
	 * scene, but the actual connections between the objects within the different widgetObjects.</i>
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
		if ( currentWidgetObject == null )
		{
			this.currentWidgetObject = null;
		}
		else
		{
			this.currentWidgetObject = currentWidgetObject;
		}
	}



	/**
	 * Sets a boolean on whether or not this {@link WorkareaCanvas} has been saved.
	 */
	public void setSaved(boolean saved)
	{
		this.saved = saved;
	}


	/**
	 * Sets a boolean on whether or not this {@link WorkareaCanvas} has been altered and not saved.
	 */
	public void setChanged(boolean changed)
	{
		this.changed = changed;
	}


	/**
	 * Adds 1 to the number of network cards on the scene.
	 */
	public void addNIC()
	{
		numberOfNICs++;
	}


	// ------------------ TRANSFER METHODES -----------------------





	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.dnd.DropTargetListener#dragEnter(java.awt.dnd.DropTargetDragEvent )
	 */
	@Override
	public void dragEnter(DropTargetDragEvent dtde)
	{
		// System.out.println(getCanvasName());
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.dnd.DropTargetListener#dragExit(java.awt.dnd.DropTargetEvent)
	 */
	@Override
	public void dragExit(DropTargetEvent dte)
	{
		// System.out.println("Drag Exit");
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.dnd.DropTargetListener#dragOver(java.awt.dnd.DropTargetDragEvent )
	 */
	@Override
	public void dragOver(DropTargetDragEvent dtde)
	{
		// System.out.println("Drag Over");
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.dnd.DropTargetListener#drop(java.awt.dnd.DropTargetDropEvent)
	 */
	@Override
	public void drop(DropTargetDropEvent dtde)
	{
		WorkareaCanvasActions.createWidgetOnCanvas(dtde, this);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @seejava.awt.dnd.DropTargetListener#dropActionChanged(java.awt.dnd. DropTargetDragEvent)
	 */
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde)
	{

	}



	/**
	 * Adds the given WidgetObject at the given point on the scene.
	 * 
	 * @param newObject
	 * @param objectPoint
	 */
	public void addWidgetObject(WidgetObject newObject, Point objectPoint, boolean withCleanUp)
	{
		// Sets the location of the object.
		newObject.getObject().setLocation(objectPoint);

		WorkareaCanvasActions.addWidgetToCanvas(newObject, objectPoint, this, withCleanUp);
	}


	/**
	 * This method cleans up the canvas. The scene and all the views are repainted and revalidated. The properties panel
	 * is also updated.
	 */
	public void cleanUp()
	{
		doRepaint();

		PrimeMain1.updatePropertiesCanvasArea();
		PrimeMain1.updateCanvasAndObjectInfo();

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
	 * This function goes through all the widgetObjects on the scene and sets the location of the widget on the scene to
	 * the location of the object within the WidgetObject.
	 */
	public void revalidateWidgetLocations()
	{
		WidgetObject[] widgets = getWidgetObjectsOnTheScene();

		// Iterates through the Object list
		for ( int i = 0; i < widgets.length; i++ )
		{
			// Gets the object from the widget
			Object obj = widgets[i].getObject();

			// Sets the object location
			obj.setLocation(widgets[i].getLocation());
		}
	}




	/**
	 * I HATE EVERYBODY!!!!!
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