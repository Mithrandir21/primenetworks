/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package widgetManipulation.Actions;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import managment.CanvasManagment;
import managment.ConnectionManagment;
import objects.Object;

import org.netbeans.api.visual.border.BorderFactory;
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
		Point sceneLocation = canvas.getScene()
				.convertSceneToLocal(objectPoint);

		// Sets the point on the scene as the widgets preferred location
		newObject.setPreferredLocation(sceneLocation);

		// Sets the location of the object


		newObject.setLabel(newObject.getObject().getObjectName());

		// // Creates the LabelWidget that is placed on scene
		// LabelWidget objectLabel = new LabelWidget(canvas.getScene(),
		// newObject
		// .getObject().getObjectName());
		// objectLabel.setAlignment(Alignment.CENTER);
		// objectLabel.setLayout(LayoutFactory.createAbsoluteLayout());
		//
		// newObject.addChild(objectLabel);
		//
		//
		// // newObject.setToolTipText(newObject.getObject().getDescription());
		// newObject.setLayout(LayoutFactory.createAbsoluteLayout());

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

		// newObject
		// .setBorder(BorderFactory.createDashedBorder(Color.black, 5, 5));

		// newObject.setBorder(BorderFactory.createBevelBorder(true));

		// newObject.setBorder(BorderFactory.createResizeBorder(5));

		// newObject.setBorder(BorderFactory.createRoundedBorder(7, 7,
		// Color.white, Color.black));

		// ---------------------------------------


		newObject.bringToFront();
		canvas.getMainLayer().addChild(newObject);
		canvas.addToNumberOfWidgetsOnTheCanvas();

		canvas.setSaved(false);
		canvas.setChanged(true);
		// PrimeMain1.updateCanvasAndObjectInfo();

		if ( withCleanUp )
		{
			canvas.cleanUp();
		}
	}


	/**
	 * Updates the LabelWidget that shows the widgetObjects name on the Scene on
	 * the canvas. This method finds the WidgetObject that contains the given
	 * object in all the different canvases.
	 * 
	 * @param widgetObj
	 * @param name
	 * @return The object with the updated name
	 */
	public static Object updateWidgetObjectCanvasName(
			WorkareaCanvas[] canvases, WidgetObject widgetObj, String name)
	{
		if ( canvases != null && canvases.length > 0 )
		{
			WorkareaCanvas canvas = CanvasManagment.findCanvas(
					widgetObj.getObject(), canvases);

			if ( canvas != null )
			{
				return updateWidgetObjectCanvasName(canvas,
						widgetObj.getObject(), name, false);
			}
		}
		return null;
	}


	/**
	 * Updates the LabelWidget that shows the widgetObjects name on the Scene on
	 * the canvas. This method finds the WidgetObject that contains the given
	 * object in all the different canvases.
	 * 
	 * @param widgetObj
	 * @param name
	 * @return The object with the updated name
	 */
	public static Object updateWidgetObjectCanvasName(WorkareaCanvas canvas,
			WidgetObject widgetObj, String name)
	{
		return updateWidgetObjectCanvasName(canvas, widgetObj.getObject(),
				name, false);
	}



	/**
	 * Updates the LabelWidget that shows the widgetObjects name on the Scene on
	 * the canvas. This method finds the WidgetObject that contains the given
	 * object in all the different canvases.
	 * 
	 * @param widgetObj
	 * @param name
	 * @return The object with the updated name
	 */
	public static Object updateWidgetObjectCanvasName(WorkareaCanvas canvas,
			WidgetObject widgetObj, String name, boolean override)
	{
		return updateWidgetObjectCanvasName(canvas, widgetObj.getObject(),
				name, override);
	}




	/**
	 * Updates the LabelWidget that shows the widgetObjects name on the Scene on
	 * the canvas. This method finds the WidgetObject that contains the given
	 * object in all the different canvases.
	 * 
	 * @param obj
	 * @param name
	 * @return The object with the updated name
	 */
	public static Object updateWidgetObjectCanvasName(WorkareaCanvas canvas,
			Object obj, String name, boolean override)
	{
		if ( obj != null )
		{
			WidgetObject widgetObj = CanvasManagment.findWidgetObject(obj,
					canvas);


			if ( override || !(obj.getObjectName().equals(name)) )
			{
				LabelWidget label = widgetObj.getLabelWidget();

				if ( label != null )
				{
					label.setLabel(name);

					// Sets the name of the object
					obj.setObjectName(name);

					canvas.setSaved(false);
					canvas.setChanged(true);

					canvas.cleanUp();
				}
			}


			return obj;
		}


		return null;
	}



	/**
	 * Updates all the LabelWidgets on all the scenes of the given
	 * {@link WorkareaCanvas} with the name of the object name that is within
	 * the WidgetObject.
	 */
	public static void updateWidgetObjectNamesOnAllCanvas(
			WorkareaCanvas[] canvases)
	{
		for ( int i = 0; i < canvases.length; i++ )
		{
			List<Widget> children = canvases[i].getMainLayer().getChildren();

			for ( Iterator<Widget> iter = children.iterator(); iter.hasNext(); )
			{
				WidgetObject temp = (WidgetObject) iter.next();

				WorkareaCanvasActions.updateWidgetObjectCanvasName(canvases[i],
						temp, temp.getObject().getObjectName());
			}

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
	public static boolean removeObject(WorkareaCanvas canvas, WidgetObject obj,
			boolean withCleanUp)
	{
		if ( canvas.getMainLayer().getChildren().contains(obj) )
		{
			// Removes all connection to the WidgetObject
			removeAllConnectionsToFromObject(canvas, obj.getObject());

			// Removes the WidgetObject from the canvas
			canvas.getMainLayer().removeChild(obj);

			canvas.subtractFromNumberOgWidgetsOnTheCanvas();

			canvas.setCurrentWidgetObject(null);

			canvas.setSaved(false);
			canvas.setChanged(true);

			if ( withCleanUp )
			{
				canvas.cleanUp();
			}

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
	public static boolean removeCurrentObject(WorkareaCanvas canvas)
	{
		// Removes all connection to the WidgetObject
		WidgetObject obj = canvas.getCurrentWidgetObject();

		if ( obj != null )
		{
			removeAllConnectionsToFromObject(canvas, obj.getObject());

			canvas.getMainLayer().removeChild(obj);

			canvas.subtractFromNumberOgWidgetsOnTheCanvas();

			canvas.setCurrentWidgetObject(null);

			canvas.setSaved(false);
			canvas.setChanged(true);

			return true;
		}

		return false;
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
		// Gets all the connections on the canvas
		Connection[] canvasCons = canvas.getConnections();

		if ( canvasCons != null )
		{
			for ( int i = 0; i < canvasCons.length; i++ )
			{
				if ( canvasCons[i] != null )
				{
					// If the given object is either object in the connection
					if ( canvasCons[i].getObject1().equals(obj)
							|| canvasCons[i].getObject2().equals(obj) )
					{
						try
						{
							removeConnection(canvas, canvasCons[i]);
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
	}


	/**
	 * Removes the {@link WidgetExtendedConnection} from the given
	 * {@link WorkareaCanvas}. It also removes the {@link Connection} within the
	 * {@link WidgetExtendedConnection} from the {@link Object Objects} that are
	 * connected.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} where the
	 *            {@link WidgetExtendedConnection} exists.
	 * @param widCon
	 *            The {@link WidgetExtendedConnection} that is to be removed.
	 */
	public static void removeWidgetConnection(WorkareaCanvas canvas,
			WidgetExtendedConnection widCon)
	{
		try
		{
			removeConnection(canvas, widCon.getConnection());
		}
		catch ( ConnectionDoesNotExist e )
		{
			System.out.println(e.getMessage());
			System.out.println("removeWidgetConnection - breakConnection");
		}
	}



	/**
	 * Removes the {@link Connection} from the given {@link WorkareaCanvas}. It
	 * also removes the {@link Connection} within the
	 * {@link WidgetExtendedConnection} from the {@link Object Objects} that are
	 * connected.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} where the
	 *            {@link WidgetExtendedConnection} exists.
	 * @param Con
	 *            The {@link Connection} that is to be removed.
	 */
	public static void removeWidgetConnection(WorkareaCanvas canvas,
			Connection Con)
	{
		try
		{
			removeConnection(canvas, Con);
		}
		catch ( ConnectionDoesNotExist e )
		{
			System.out.println(e.getMessage());
			System.out.println("removeWidgetConnection - breakConnection");
		}
	}


	/**
	 * This function attempts to remove an arraylist of connections between to
	 * {@link Object Objects} and at the same time remove the arraylist of
	 * {@link Connection} from the given {@link WorkareaCanvas}.
	 */
	public static void removeConnection(WorkareaCanvas canvas,
			ArrayList<Connection> con) throws ConnectionDoesNotExist
	{
		if ( con != null && (!con.isEmpty()) )
		{
			Connection[] cons = new Connection[1];
			cons = con.toArray(cons);

			for ( int i = 0; i < cons.length; i++ )
			{
				removeConnection(canvas, cons[i]);
			}
		}
	}



	/**
	 * This function attempts to remove a connection between to {@link Object
	 * Objects} and at the same time remove the {@link Connection} from the
	 * given {@link WorkareaCanvas}.
	 */
	public static void removeConnection(WorkareaCanvas canvas, Connection con)
			throws ConnectionDoesNotExist
	{
		if ( con != null )
		{
			// Removes the WidgetExtendedConnection from the
			// WorkareaCanvas scene
			WorkareaCanvasActions.removeConnectionFromConnectionLayer(canvas,
					ConnectionManagment.getConnection(canvas.getConnections(),
							con.getObject1(), con.getObject2()));

			// Removes the connection from the array of Connections
			// for each connected object
			Connection[] remainingConnection = ConnectionManagment
					.breakConnection(canvas.getConnections(), con);

			// Sets the connections of the WorkareaCanvas, without
			// the removed connection
			canvas.setConnections(remainingConnection);

			canvas.setSaved(false);
			canvas.setChanged(true);

			canvas.cleanUp();
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

		if ( found )
		{
			canvas.getConnectionLayer().removeChild(temp);

			canvas.setSaved(false);
			canvas.setChanged(true);
		}
	}



	/**
	 * The function removes the given {@link WidgetRoom} from the given
	 * {@link WorkareaCanvas}, if the WidgetRoom is found to be contained within
	 * the WorkareaCanvas.
	 * 
	 * @param widRoom
	 *            The {@link WidgetRoom} to be removed from the given
	 *            {@link WorkareaCanvas}.
	 */
	public static void removeRoom(WorkareaCanvas canvas, WidgetRoom widRoom,
			boolean withCleanUp)
	{
		if ( canvas != null && widRoom != null )
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

			if ( found )
			{
				canvas.getRoomLayer().removeChild(temp);

				canvas.setSaved(false);
				canvas.setChanged(true);
			}

			if ( withCleanUp )
			{
				canvas.cleanUp();
			}
		}
	}




	/**
	 * This method calls the revalidateWidgetLocations function in all the given
	 * {@link WorkareaCanvas WorkareaCanvases}, which will update the locations
	 * on all the WidgetObjects on their respective scenes.
	 * 
	 * @param canvases
	 */
	public static void revalidateWidgetLocations(WorkareaCanvas[] canvases)
	{
		for ( int i = 0; i < canvases.length; i++ )
		{
			canvases[i].revalidateWidgetLocations();

			canvases[i].setSaved(false);
			canvases[i].setChanged(true);
		}
	}



	/**
	 * This method calls the revalidateWidgetLocations function in the given
	 * {@link WorkareaCanvas}, which will update the locations on all the
	 * WidgetObjects on the scenes.
	 * 
	 * @param canvas
	 */
	public static void revalidateWidgetLocations(WorkareaCanvas canvas)
	{
		canvas.revalidateWidgetLocations();

		canvas.setSaved(false);
		canvas.setChanged(true);
	}
}
