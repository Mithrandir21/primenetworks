package graphics.GUI.workareaCanvas;


import exceptions.ConnectionDoesNotExist;
import graphics.PrimeMain1;

import java.util.Iterator;
import java.util.List;

import managment.ConnectionManagment;
import objects.Object;

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
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param obj
	 */
	public static void deleteObject(WorkareaCanvas canvas, WidgetObject obj)
	{

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
		WidgetObject obj = PrimeMain1.currentCanvas.getCurrentWidgetObject();

		removeAllConnectionsToFromObject(canvas, obj.getObject());

		PrimeMain1.currentCanvas.getMainLayer().removeChild(obj);
		
		
		PrimeMain1.currentCanvas.setCurrentWidgetObject(null);
	}


	/**
	 * Javadoc-TODO - Description
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
					removeConnectionFromConnectionLayer(canvas,ConnectionManagment.getConnection(
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
	 * Javadoc-TODO - Description
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
