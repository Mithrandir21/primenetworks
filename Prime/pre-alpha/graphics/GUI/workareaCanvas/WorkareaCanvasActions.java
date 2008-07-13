package graphics.GUI.workareaCanvas;


import exceptions.ConnectionDoesNotExist;
import graphics.PrimeMain1;

import java.util.Iterator;
import java.util.List;

import managment.CanvasManagment;
import managment.ConnectionManagment;
import objects.Object;

import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;
import connections.Connection;
import connections.WidgetExtendedConnection;


public class WorkareaCanvasActions
{

	/**
	 * TODO - Description
	 */
	public static void deleteObject(WorkareaCanvas canvas, WidgetObject obj)
	{

	}


	/**
	 * TODO - Description
	 */
	public static void deleteCurrentObject(WorkareaCanvas canvas)
	{
		WidgetObject obj = PrimeMain1.currentCanvas.getCurrentWidgetObject();
		
		removeAllConnectionsToFromObject(canvas, obj.getObject());
		
		PrimeMain1.currentCanvas.getMainLayer().removeChild(obj);
	}


	/**
	 * TODO - Description
	 */
	public static void removeConnectionToObject(Object obj, Connection con)
	{

	}


	/**
	 * TODO - Description
	 */
	public static void removeAllConnectionsToFromObject(WorkareaCanvas canvas, Object obj)
	{
		Object[] connectedObjects = obj.getConnectedDevices();

		Connection[] canvasCons = canvas.getConnections();

		for ( int i = 0; i < connectedObjects.length; i++ )
		{
			try
			{
				CanvasManagment.removeConnectionFromConnectionLayer(ConnectionManagment.getConnection(canvasCons, obj,
						connectedObjects[i]));
				
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

	/**
	 * TODO - Description
	 */
	public static void checkObjectForConnetions(Object obj)
	{

	}
}
