/**
 * 
 */
package graphics.GUI.workareaCanvas.providers;


import graphics.GUI.workareaCanvas.CreateProvider;
import graphics.GUI.workareaCanvas.RectangularAreaSelection;
import graphics.GUI.workareaCanvas.WorkareaTargetListener;
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuCanvas.JMenuWorkareaCanvas;
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuConnection.JMenuConnection;
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuWidget.JMenuWidget;

import java.awt.dnd.DropTarget;

import org.netbeans.api.visual.action.ActionFactory;

import widgetManipulation.PrimeRectangularSelectDecorator;
import widgetManipulation.WorkareaCanvasNetworkInfo;
import widgetManipulation.Actions.ExtendedWidgetConnectAction;
import widgetManipulation.Actions.MoveRoomAction;
import widgetManipulation.Actions.ResizeWidgetAction;
import widgetManipulation.Providers.PrimeObjectSceneRectangularSelectProvider;
import widgets.WidgetObject;
import widgets.WidgetRoom;
import widgets.WorkareaCanvas;
import connections.WidgetExtendedConnection;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ActionsAdder
{
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 */
	public static void makeWorkareaCanvasReady(WorkareaCanvas canvas)
	{
		// Adds the drag and drop action to the WorkareaCanvas
		canvas.setDropTarget(new DropTarget(canvas.getMyView(), new WorkareaTargetListener(canvas)));

		// Adds the zoom feature to the scene.
		canvas.getScene().getActions().addAction(ActionFactory.createZoomAction());
		canvas.getScene().getActions().addAction(ActionFactory.createPanAction());
		canvas.getScene().getActions().addAction(ActionFactory.createPopupMenuAction(new JMenuWorkareaCanvas(canvas)));


		canvas.getScene().getActions().addAction(new WorkareaCanvasListener());


		canvas.getScene().getActions().addAction(
				new RectangularAreaSelection(new PrimeRectangularSelectDecorator(canvas), canvas.getInteractionLayer(),
						new PrimeObjectSceneRectangularSelectProvider(canvas)));


		canvas.setNetworkInfo(new WorkareaCanvasNetworkInfo(canvas));
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param obj
	 */
	public static void makeWidgetObjectReady(WorkareaCanvas canvas, WidgetObject newObject)
	{
		// // Here the create a connection between widgets on the scene is created
		newObject.getActions().addAction(
				new ExtendedWidgetConnectAction(ActionFactory.createDefaultConnectDecorator(), canvas
						.getInteractionLayer(), new GUIsceneConnectProvider(canvas)));

		// Adds the action of a user clicking on the object
		newObject.getActions().addAction(ActionFactory.createSelectAction(new CreateProvider()));


		// Creates and add the move with align action
		newObject.getActions().addAction(
				ActionFactory.createAlignWithMoveAction(canvas.getMainLayer(), canvas.getInteractionLayer(), null));


		// Adds the double clicking feature for the WidgetObject
		newObject.getActions().addAction(new WidgetAdapterExtended());


		newObject.getActions().addAction(ActionFactory.createPopupMenuAction(new JMenuWidget(canvas)));

		// Cleans up the canvas
		canvas.cleanUp();
	}




	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param newObject
	 */
	public static void makeWidgetConnectionReady(WorkareaCanvas canvas, WidgetExtendedConnection newCon)
	{
		// Adds the clicking ability
		newCon.getActions().addAction(new WidgetAdapterExtended());

		// Adds the JPopupMenu
		newCon.getActions().addAction(ActionFactory.createPopupMenuAction(new JMenuConnection(canvas)));
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param room
	 */
	public static void makeWidgetRoomReady(WorkareaCanvas canvas, WidgetRoom room)
	{
		// Gives the WidgetRoom the ability to be resized
		room.getActions().addAction(
				new ResizeWidgetAction(ActionFactory.createFreeResizeStategy(), ActionFactory
						.createDefaultResizeControlPointResolver(), ActionFactory.createDefaultResizeProvider()));

		// Gives the WidgetRoom the ability to be moved
		room.getActions().addAction(
				new MoveRoomAction(ActionFactory.createFreeMoveStrategy(), ActionFactory.createDefaultMoveProvider()));

		// Clicking ability
		room.getActions().addAction(new WidgetRoomAdapterExtended());
	}
}
