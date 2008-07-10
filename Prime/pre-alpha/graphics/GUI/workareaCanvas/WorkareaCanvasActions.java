package graphics.GUI.workareaCanvas;


import managment.ConnectionManagment;
import objects.Object;
import widgetManipulation.WidgetObject;
import connections.Connection;
import exceptions.ConnectionDoesNotExist;


public class WorkareaCanvasActions
{

	public static void deleteObject(WorkareaCanvas canvas, WidgetObject obj)
	{

	}


	public static void deleteCurrentObject(WorkareaCanvas canvas)
	{
		removeAllConnectionsToFromObject(canvas,canvas.getCurrentWidgetObject().getObject());
	}


	public static void removeConnectionToObject(Object obj, Connection con)
	{

	}


	public static void removeAllConnectionsToFromObject(WorkareaCanvas canvas, Object obj)
	{
		Object[] connectedObjects = obj.getConnectedDevices();
		
		Connection[] canvasCons = canvas.getConnections();
		
		
		for(int i = 0 ; i<connectedObjects.length ; i++)
		{
			try
			{
				System.out.println(obj.getObjectName() + " - " + connectedObjects[i].getObjectName());
				ConnectionManagment.breakConnection(canvasCons, obj, connectedObjects[i]);
			}
			catch ( ConnectionDoesNotExist e )
			{
				System.out.println("removeAllConnectionsToFromObject - breakConnection");
			}
		}
		
		
		
		
		obj.removeConnections();
		
		
		
		System.out.println("This actually worked...");
		
		
	}

	public static void checkObjectForConnetions(Object obj)
	{

	}
}
