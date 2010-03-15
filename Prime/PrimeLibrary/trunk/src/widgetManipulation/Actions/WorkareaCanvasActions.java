package widgetManipulation.Actions;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import managment.ConnectionManagment;
import objects.Object;

import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;
import widgets.WidgetRoom;
import widgets.WorkareaCanvas;
import connections.Connection;
import connections.WidgetExtendedConnection;
import exceptions.ConnectionDoesNotExist;


/**
 * In this class the methods for the manipulation of the WidgetObject on any
 * canvas is placed. These methods include the removal of connections between
 * widgetObject, the removal of the WidgetObjects them selfs and other methods
 * that are necessary for the maintenance of the canvas..
 * 
 * @author Bahram Malaekeh
 */
public class WorkareaCanvasActions
{

	/**
	 * Creates a WidgetObject and adds that object to a given point gotten from
	 * the dropTargetDropEvent.
	 * 
	 * @param dtde
	 * @param canvas
	 */
	public static WidgetObject createWidgetOnCanvas(DropTargetDropEvent dtde,
			WorkareaCanvas canvas)
	{
		Transferable tr = dtde.getTransferable();
		WidgetObject newObject = null;

		try
		{
			newObject = (WidgetObject) tr.getTransferData(new DataFlavor(
					WidgetObject.class, "Widget Object"));

			Dimension objectSize = newObject.getImageDimension();

			Point objectPoint = dtde.getLocation();

			dtde = null;


			int height = objectPoint.x - (objectSize.height / 2);

			int width = objectPoint.y - (objectSize.width / 2);

			objectPoint.setLocation(height, width);

			canvas.addWidgetObject(newObject, objectPoint, true);

			// // Updates the sidebar with the object properties
			// PrimeMain1.updatePropertiesObjectArea(newObject.getObject(),
			// false);
			//
			// canvas.cleanUp();
		}
		catch ( UnsupportedFlavorException e )
		{
			System.out
					.println("WorkareaCanvasActions - UnsupportedFlavorException");
		}
		catch ( IOException e )
		{
			System.out.println("WorkareaCanvasActions - IOException");
		}

		return newObject;
	}


	/**
	 * This function determines what kind of {@link Object} the given object is
	 * and adds that Object to the given {@link WorkareaCanvas}. There is also
	 * an ImageIcon created that will serve as the ImageIcon for the {@link WidgetObject} that will be created and added to the
	 * {@link WorkareaCanvas}.
	 * 
	 * @param obj
	 *            The {@link Object} that will be examined and eventually added
	 *            to the given {@link WorkareaCanvas}.
	 * @param canvas
	 *            The {@link WorkareaCanvas} that the given Object will be added
	 *            to in the form of a {@link WidgetObject}.
	 * @param objectType
	 * @param objectIcon
	 * @return Returns the newly created WidgetObject that is added to the
	 *         WorkareaCanvas
	 */
	public static WidgetObject addObjectToCanvas(Object obj,
			WorkareaCanvas canvas, Class<?> objectType, ImageIcon objectIcon)
	{
		boolean set = false;
		objects.Object newObject = obj;
		WidgetObject newWidgetObject = null;


		// Creates a new WidgetObject that will be added to the scene
		newWidgetObject = new WidgetObject(canvas.getScene(), newObject,
				objectIcon.getImage());

		// Adds the given object to the given location
		canvas.addWidgetObject(newWidgetObject, obj.getLocation(), false);

		// Returns the newly created WidgetObject
		return newWidgetObject;
	}



	/**
	 * Adds the given WidgetObject at the given point on the scene. This method
	 * does not add all the functionality that a widgetObject will have like
	 * being able to connect to other widgets, being clicked or dragged. NB: For
	 * those functions ActionAdder.makeWidgetObjectReady(WorkareaCanvas,
	 * WidgetObject) must be called. It sets a description and places an empty
	 * border around the widgetObject.
	 * 
	 * @param newObject
	 * @param objectPoint
	 * @param canvas
	 */
	public static void addWidgetToCanvas(WidgetObject newObject,
			Point objectPoint, WorkareaCanvas canvas, boolean withCleanUp)
	{
		// Gets the point the Widget has on the scene
		Point sceneLocation = canvas.getScene().convertViewToScene(objectPoint);

		// Sets the point on the scene as the widgets preferred location
		newObject.setPreferredLocation(sceneLocation);

		// Creates the LabelWidget that is placed on scene
		LabelWidget objectLabel = new LabelWidget(canvas.getScene(), newObject
				.getObject().getObjectName());

		newObject.addChild(objectLabel);


		// newObject.setToolTipText(newObject.getObject().getDescription());
		newObject.setLayout(LayoutFactory.createAbsoluteLayout());

		// Adds hovering action to the widget.
		// newObject.getActions().addAction(
		// ActionFactory.createHoverAction(new WidgetHoverProvider()));


		// newObject.getActions().addAction(ActionFactory.
		// createRectangularSelectAction(
		// ActionFactory.createDefaultRectangularSelectDecorator(scene),
		// interactionLayer,
		// ActionFactory.createObjectSceneRectangularSelectProvider(scene)));


		// ----------DIFFERENT BORDERS------------
		newObject.setBorder(BorderFactory.createEmptyBorder());

		// newObject.setBorder(BorderFactory.createDashedBorder(Color.black,
		// 5, 5));

		// newObject.setBorder(BorderFactory.createBevelBorder(true));

		// newObject.setBorder(BorderFactory.createResizeBorder(5));

		// newObject.setBorder(BorderFactory.createRoundedBorder(7, 7,
		// Color.white, Color.black));

		// ---------------------------------------


		newObject.bringToFront();
		canvas.getMainLayer().addChild(newObject);
		canvas.addToNumberOfWidgetsOnTheCanvas();
		// PrimeMain1.updateCanvasAndObjectInfo();

		if ( withCleanUp )
		{
			canvas.cleanUp();
		}
	}


	/**
	 * This function removes the given WidgetObject from the given canvas. It
	 * also removes all the connections to and from the given WidgetObject.
	 * 
	 * @param canvas
	 *            The canvas that the object is to be removed from.
	 * @param obj
	 *            The WidgetObject that is to be removed.
	 */
	public static boolean deleteObject(WorkareaCanvas canvas, WidgetObject obj)
	{
		if ( canvas.getMainLayer().getChildren().contains(obj) )
		{
			removeAllConnectionsToFromObject(canvas, obj.getObject());

			canvas.getMainLayer().removeChild(obj);

			canvas.subtractFromNumberOgWidgetsOnTheCanvas();

			canvas.setCurrentWidgetObject(null);

			return true;
		}

		return false;
	}


	/**
	 * This method deletes the currently selected object from the given canvas.
	 * It also removes all connection to and from the WidgetObject.
	 * 
	 * @param canvas
	 *            The canvas that object it to be removed from.
	 */
	public static void deleteCurrentObject(WorkareaCanvas canvas)
	{
		WidgetObject obj = canvas.getCurrentWidgetObject();

		removeAllConnectionsToFromObject(canvas, obj.getObject());

		canvas.getMainLayer().removeChild(obj);

		canvas.subtractFromNumberOgWidgetsOnTheCanvas();

		canvas.setCurrentWidgetObject(null);
	}


	/**
	 * FIXME - Make RemoveConnectioToObject function.
	 * 
	 * @param obj
	 * @param con
	 */
	public static void removeConnectionToObject(Object obj, Connection con)
	{

	}


	/**
	 * This method removes all connection to and from the given object. it also
	 * removes those connections from the array of connection in the given
	 * canvas.
	 * 
	 * @param canvas
	 *            The canvas where the connections are to be removed from.
	 * @param obj
	 *            The object which has the connections that are to be removed.
	 */
	public static void removeAllConnectionsToFromObject(WorkareaCanvas canvas,
			Object obj)
	{
		Object[] connectedObjects = obj.getConnectedDevices();

		Connection[] canvasCons = canvas.getConnections();

		if ( connectedObjects != null )
		{
			for ( int i = 0; i < connectedObjects.length; i++ )
			{
				if ( connectedObjects[i] != null )
				{
					try
					{
						// Removes the WidgetExtendedConnection from the
						// WorkareaCanvas scene
						removeConnectionFromConnectionLayer(canvas,
								ConnectionManagment.getConnection(canvasCons,
										obj, connectedObjects[i]));

						// Removes the connection from the array of Connections
						// for each connected object
						Connection[] remainingConnection = ConnectionManagment
								.breakConnection(canvasCons, obj,
										connectedObjects[i]);

						// Sets the connections of the WorkareaCanvas, without
						// the removed connection
						canvas.setConnections(remainingConnection);
					}
					catch ( ConnectionDoesNotExist e )
					{
						System.out.println(e.getMessage());
						System.out
								.println("removeAllConnectionsToFromObject - breakConnection");
					}
				}
			}
		}
	}

	/**
	 * FIXME - Make ChechObjectForConnections work.
	 * 
	 * @param obj
	 */
	public static void checkObjectForConnetions(Object obj)
	{

	}


	/**
	 * Removes the {@link WidgetExtendedConnection} from the given {@link WorkareaCanvas}. It also removes the {@link Connection}
	 * within the {@link WidgetExtendedConnection} from the {@link Object Objects} that are
	 * connected.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} where the {@link WidgetExtendedConnection} exists.
	 * @param widCon
	 *            The {@link WidgetExtendedConnection} that is to be removed.
	 */
	public static void removeWidgetConnection(WorkareaCanvas canvas,
			WidgetExtendedConnection widCon)
	{
		// Gets all the connections on a WorkareaCanvas
		Connection[] canvasCons = canvas.getConnections();

		try
		{
			// Removes the WidgetExtendedConnection from the WorkareaCanvas
			// scene
			removeConnectionFromConnectionLayer(canvas, ConnectionManagment
					.getConnection(canvasCons, widCon.getConnection()
							.getObject1(), widCon.getConnection().getObject2()));

			// Removes the connection from the array of Connections for each
			// connected object
			Connection[] remainingConnection = ConnectionManagment
					.breakConnection(canvasCons, widCon.getConnection()
							.getObject1(), widCon.getConnection().getObject2());

			// Sets the connections of the WorkareaCanvas, without the removed
			// connection
			canvas.setConnections(remainingConnection);
		}
		catch ( ConnectionDoesNotExist e )
		{
			System.out.println(e.getMessage());
			System.out.println("removeWidgetConnection - breakConnection");
		}
	}



	/**
	 * Removes the {@link Connection} from the given {@link WorkareaCanvas}. It also removes the {@link Connection} within the
	 * {@link WidgetExtendedConnection} from the {@link Object Objects} that are
	 * connected.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} where the {@link WidgetExtendedConnection} exists.
	 * @param Con
	 *            The {@link Connection} that is to be removed.
	 */
	public static void removeWidgetConnection(WorkareaCanvas canvas,
			Connection Con)
	{
		// Gets all the connections on a WorkareaCanvas
		Connection[] canvasCons = canvas.getConnections();

		WidgetExtendedConnection widCon = null;

		try
		{
			widCon = ConnectionManagment.findWidgetConnection(canvas, Con
					.getObject1(), Con.getObject2());

			// Removes the WidgetExtendedConnection from the WorkareaCanvas
			// scene
			removeConnectionFromConnectionLayer(canvas, ConnectionManagment
					.getConnection(canvasCons, widCon.getConnection()
							.getObject1(), widCon.getConnection().getObject2()));

			// Removes the connection from the array of Connections for each
			// connected object
			Connection[] remainingConnection = ConnectionManagment
					.breakConnection(canvasCons, widCon.getConnection()
							.getObject1(), widCon.getConnection().getObject2());

			// Sets the connections of the WorkareaCanvas, without the removed
			// connection
			canvas.setConnections(remainingConnection);
		}
		catch ( ConnectionDoesNotExist e )
		{
			System.out.println(e.getMessage());
			System.out.println("removeWidgetConnection - breakConnection");
		}
	}




	/**
	 * Removes the given {@link connections.Connection Connection} from the
	 * given canvas.
	 * 
	 * @param canvas
	 *            The canvas which the connection is to be removed from.
	 * @param con
	 *            The connection to be removed.
	 */
	public static void removeConnectionFromConnectionLayer(
			WorkareaCanvas canvas, Connection con)
	{
		List<Widget> list = canvas.getConnectionLayer().getChildren();

		WidgetExtendedConnection temp = null;

		boolean found = false;


		WidgetExtendedConnection testingWidget = null;

		for ( Iterator<?> iter = list.iterator(); iter.hasNext(); )
		{
			testingWidget = (WidgetExtendedConnection) iter.next();

			if ( testingWidget.getConnection().equals(con) )
			{
				found = true;
				temp = testingWidget;
			}
		}

		if ( found == true )
		{
			canvas.getConnectionLayer().removeChild(temp);
		}
	}



	/**
	 * The function removes the given {@link WidgetRoom} from the given {@link WorkareaCanvas}, if the WidgetRoom is found to be
	 * contained within
	 * the WorkareaCanvas.
	 * 
	 * @param widRoom
	 *            The {@link WidgetRoom} to be removed from the given {@link WorkareaCanvas}.
	 */
	public static void removeRoom(WorkareaCanvas canvas, WidgetRoom widRoom)
	{
		List<Widget> list = canvas.getRoomLayer().getChildren();

		WidgetRoom temp = null;

		boolean found = false;


		WidgetRoom testingWidget = null;

		for ( Iterator<?> iter = list.iterator(); iter.hasNext(); )
		{
			testingWidget = (WidgetRoom) iter.next();

			if ( testingWidget.equals(widRoom) )
			{
				found = true;
				temp = testingWidget;
			}
		}

		if ( found == true )
		{
			canvas.getRoomLayer().removeChild(temp);
		}
	}




	/**
	 * This method calls the revalidateWidgetLocations function in all the given {@link WorkareaCanvas WorkareaCanvases}, which
	 * will update the locations
	 * on all the WidgetObjects on their respective scenes.
	 * 
	 * @param canvases
	 */
	public static void revalidateWidgetLocations(WorkareaCanvas[] canvases)
	{
		for ( int i = 0; i < canvases.length; i++ )
		{
			canvases[i].revalidateWidgetLocations();
		}
	}



	/**
	 * This method calls the revalidateWidgetLocations function in the given {@link WorkareaCanvas}, which will update the
	 * locations on all the
	 * WidgetObjects on the scenes.
	 * 
	 * @param canvas
	 */
	public static void revalidateWidgetLocations(WorkareaCanvas canvas)
	{
		canvas.revalidateWidgetLocations();
	}
}
