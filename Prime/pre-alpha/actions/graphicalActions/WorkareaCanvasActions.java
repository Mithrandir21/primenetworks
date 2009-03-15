package actions.graphicalActions;


import exceptions.ConnectionDoesNotExist;
import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.WidgetIcon;
import graphics.GUI.selectArea.CreateObjectDragged;
import graphics.GUI.workareaCanvas.WorkareaCanvas;
import graphics.GUI.workareaCanvas.providers.AdapterExtended;
import graphics.GUI.workareaCanvas.providers.CreateProvider;
import graphics.GUI.workareaCanvas.providers.SceneConnectProvider;
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuWidget.JMenuWidget;

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
import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Router;
import objects.infrastructureObjects.Switch;
import objects.peripheralObjects.Scanner;
import objects.serverObjects.BackupServer;
import objects.serverObjects.FirewallServer;
import objects.serverObjects.HTTPServer;
import objects.serverObjects.MailServer;
import objects.serverObjects.ProxyServer;
import objects.softwareObjects.Webserver;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;
import connections.Connection;
import connections.WidgetExtendedConnection;


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
	public static void createWidgetOnCanvas(DropTargetDropEvent dtde, WorkareaCanvas canvas)
	{
		Transferable tr = dtde.getTransferable();
		WidgetObject newObject = null;

		try
		{
			newObject = (WidgetObject) tr.getTransferData(new DataFlavor(WidgetObject.class,
					"Widget Object"));

			Dimension objectSize = newObject.getImageDimension();

			Point objectPoint = dtde.getLocation();

			dtde = null;


			int height = objectPoint.x - (objectSize.height / 2);

			int width = objectPoint.y - (objectSize.width / 2);

			objectPoint.setLocation(height, width);

			canvas.addWidgetObject(newObject, objectPoint, true);


			canvas.cleanUp();
		}
		catch ( UnsupportedFlavorException e )
		{
			System.out.println("ActionCreateWidgetObject - UnsupportedFlavorException");
		}
		catch ( IOException e )
		{
			System.out.println("ActionCreateWidgetObject - IOException");
		}
	}
	
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param obj
	 * @param canvas
	 */
	public static void addObjectToCanvas(Object obj, WorkareaCanvas canvas)
	{
		boolean set = false;
		Class<?> objectType = null;
		ImageIcon objectIcon = null;
		objects.Object newObject = obj;
		WidgetObject newWidgetObject = null;
		
		
		if ( obj.getClass().equals(Desktop.class) )
		{
			objectType = Desktop.class;
			objectIcon = ImageLocator.getImageIconObject("Desktop");

			set = true;
		}
		else if ( obj.getClass().equals(Laptop.class) )
		{
			objectType = Laptop.class;
			objectIcon = ImageLocator.getImageIconObject("Laptop");

			set = true;
		}
		else if ( obj.getClass().equals(Webserver.class) )
		{
			objectType = HTTPServer.class;
			objectIcon = ImageLocator.getImageIconObject("Web-server");

			set = true;
		}
		else if ( obj.getClass().equals(MailServer.class) )
		{
			objectType = MailServer.class;
			objectIcon = ImageLocator.getImageIconObject("Email-server");

			set = true;
		}
		else if ( obj.getClass().equals(BackupServer.class) )
		{
			objectType = BackupServer.class;
			objectIcon = ImageLocator.getImageIconObject("Data-server");

			set = true;
		}
		else if ( obj.getClass().equals(FirewallServer.class) )
		{
			objectType = FirewallServer.class;
			objectIcon = ImageLocator.getImageIconObject("Firewall-server");

			set = true;
		}
		else if ( obj.getClass().equals(ProxyServer.class) )
		{
			objectType = ProxyServer.class;
			objectIcon = ImageLocator.getImageIconObject("Proxy-server");

			set = true;
		}
		else if ( obj.getClass().equals(Hub.class) )
		{
			objectType = Hub.class;
			objectIcon = ImageLocator.getImageIconObject("Hub");

			set = true;
		}
		else if ( obj.getClass().equals(Switch.class) )
		{
			objectType = Switch.class;
			objectIcon = ImageLocator.getImageIconObject("Switch");

			set = true;
		}
		else if ( obj.getClass().equals(Router.class) )
		{
			objectType = Router.class;
			objectIcon = ImageLocator.getImageIconObject("Router");

			set = true;
		}
		else if ( obj.getClass().equals(Router.class) )
		{
			objectType = Router.class;
			objectIcon = ImageLocator.getImageIconObject("WirelessRouter");

			set = true;
		}
		else if ( obj.getClass().equals(Scanner.class) )
		{
			objectType = Scanner.class;
			objectIcon = ImageLocator.getImageIconObject("Scanner");

			set = true;
		}
		
		
		
		if ( set == true )
		{
			WidgetIcon newObjectIcon = new WidgetIcon(objectIcon,
					objectType);


			// Creates a new WidgetObject that will be added to the scene
			newWidgetObject = new WidgetObject(canvas.getScene(), newObject, objectIcon
					.getImage());

			// Adds the given object to the given location
			canvas.addWidgetObject(newWidgetObject, obj.getLocation(), false);
		}
	}
	


	/**
	 * Adds the given WidgetObject at the given point on the scene. This method
	 * adds all the functionality that a widgetObject will have like being able
	 * to connect to other widgets, being clicked or dragged. It also sets a
	 * description and places an empty border around the widgetObject.
	 * 
	 * @param newObject
	 * @param objectPoint
	 * @param canvas
	 */
	public static void addWidgetToCanvas(WidgetObject newObject, Point objectPoint,
			WorkareaCanvas canvas, boolean withCleanUp)
	{
		Point sceneLocation = canvas.getScene().convertViewToScene(objectPoint);


		newObject.setPreferredLocation(sceneLocation);


		newObject.getActions().addAction(
				ActionFactory.createExtendedConnectAction(canvas.getInteractionLayer(),
						new SceneConnectProvider(canvas)));


		//			
		newObject.getActions().addAction(ActionFactory.createSelectAction(new CreateProvider()));


		newObject.getActions().addAction(
				ActionFactory.createAlignWithMoveAction(canvas.getMainLayer(), canvas
						.getInteractionLayer(), null));



		newObject.getActions().addAction(new AdapterExtended());


		LabelWidget objectLabel = new LabelWidget(canvas.getScene(), newObject.getObject()
				.getObjectName());

		newObject.addChild(objectLabel);


		newObject.setToolTipText(newObject.getObject().getDescription());


		// Adds hovering action to the widget.
		// newObject.getActions().addAction(
		// ActionFactory.createHoverAction(new WidgetHoverProvider()));


		newObject.getActions().addAction(
				ActionFactory.createPopupMenuAction(new JMenuWidget(canvas)));



		// newObject.getActions().addAction(ActionFactory.
		// createRectangularSelectAction(
		// ActionFactory.createDefaultRectangularSelectDecorator(scene),
		// interactionLayer,
		// ActionFactory.createObjectSceneRectangularSelectProvider(scene)));


		// ----------DIFFERENT BORDERS------------
		newObject.setBorder(BorderFactory.createEmptyBorder());

		// newObject.setBorder(BorderFactory.createDashedBorder(Color.black,
		// 5, 5));

		// newObject.setBorder(BorderFactory.createBevelBorder(false));

		// newObject.setBorder(BorderFactory.createResizeBorder(5));

		// newObject.setBorder(BorderFactory.createRoundedBorder(10, 10,
		// Color.white, Color.black));

		// newObject.getActions().addAction(ActionFactory.
		// createAddRemoveControlPointAction(1.0,3.0));
		// ---------------------------------------

		canvas.getMainLayer().addChild(newObject);
		canvas.addToNumberOfWidgetsOnTheCanvas();

		if(withCleanUp)
		{
			canvas.cleanUp();
		}
	}


	/**
	 * This function removes the given WidgetObject from the given canvas. It
	 * also removes all the connections to and from the given widgetobject.
	 * 
	 * @param canvas
	 *            The canvas that the object is to be removed from.
	 * @param obj
	 *            The WidgetObject that is to be removed.
	 */
	public static void deleteObject(WorkareaCanvas canvas, WidgetObject obj)
	{
		removeAllConnectionsToFromObject(canvas, obj.getObject());

		canvas.getMainLayer().removeChild(obj);


		canvas.setCurrentWidgetObject(null);
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
	public static void removeAllConnectionsToFromObject(WorkareaCanvas canvas, Object obj)
	{
		Object[] connectedObjects = obj.getConnectedDevices();

		Connection[] canvasCons = canvas.getConnections();

		if ( connectedObjects != null )
		{
			for ( int i = 0; i < connectedObjects.length; i++ )
			{
				try
				{
					removeConnectionFromConnectionLayer(canvas, ConnectionManagment.getConnection(
							canvasCons, obj, connectedObjects[i]));

					canvas.setConnections(ConnectionManagment.breakConnection(canvasCons, obj,
							connectedObjects[i]));
				}
				catch ( ConnectionDoesNotExist e )
				{
					System.out.println(e.getMessage());
					System.out.println("removeAllConnectionsToFromObject - breakConnection");
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
	 * Removes the given {@link connections.Connection Connection} from the
	 * given canvas.
	 * 
	 * @param canvas
	 *            The canvas which the connection is to be removed from.
	 * @param con
	 *            The connection to be removed.
	 */
	public static void removeConnectionFromConnectionLayer(WorkareaCanvas canvas, Connection con)
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
			PrimeMain1.currentCanvas.getConnectionLayer().removeChild(temp);
		}
	}




	/**
	 * Removes the given {@link connections.Connection Connection} from the
	 * currently active canvas.
	 * 
	 * @param con
	 *            The connection to be removed.
	 */
	public static void removeConnectionFromConnectionLayer(Connection con)
	{
		List<Widget> list = PrimeMain1.currentCanvas.getConnectionLayer().getChildren();

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
			PrimeMain1.currentCanvas.getConnectionLayer().removeChild(temp);
		}
	}




	/**
	 * Removes the given {@link connections.Connection Connection} from the
	 * given canvas.
	 * 
	 * @param con
	 *            The connection to be removed.
	 * @param canvas
	 *            The given that the connection is to be removed from.
	 */
	public static void removeConnectionFromCanvas(Connection con, WorkareaCanvas canvas)
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
}
