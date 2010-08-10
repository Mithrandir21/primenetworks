/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.workareaCanvas.providers;


import graphics.GUI.ghostGlass.GhostGlassPane;
import graphics.GUI.workareaCanvas.CreateProvider;
import graphics.GUI.workareaCanvas.RectangularAreaSelection;
import graphics.GUI.workareaCanvas.WorkareaTargetListener;
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuCanvas.JMenuWorkareaCanvas;
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuConnection.JMenuConnection;
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuWidget.JMenuWidget;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.dnd.DropTarget;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.AlignWithMoveDecorator;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.modules.visual.action.AlignWithMoveStrategyProvider;
import org.netbeans.modules.visual.action.SingleLayerAlignWithWidgetCollector;

import widgetManipulation.PrimeRectangularSelectDecorator;
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
 */
public class ActionsAdder
{
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 */
	public static void makeWorkareaCanvasReady(GhostGlassPane ghostGlass,
			WorkareaCanvas canvas)
	{
		// Adds the drag and drop action to the WorkareaCanvas
		canvas.setDropTarget(new DropTarget(canvas.getMyView(),
				new WorkareaTargetListener(canvas)));

		// Adds the zoom feature to the scene.
		// canvas.getScene().getActions().addAction(
		// ActionFactory.createZoomAction());
		canvas.getScene().getActions().addAction(
				ActionFactory.createMouseCenteredZoomAction(1.1));
		canvas.getScene().getActions().addAction(
				ActionFactory.createWheelPanAction());
		canvas.getScene().getActions().addAction(
				ActionFactory.createPopupMenuAction(new JMenuWorkareaCanvas(
						canvas)));


		canvas.getScene().getActions().addAction(new WorkareaCanvasListener());


		canvas.getScene().getActions().addAction(
				new RectangularAreaSelection(
						new PrimeRectangularSelectDecorator(canvas), canvas
								.getInteractionLayer(),
						new PrimeObjectSceneRectangularSelectProvider(canvas)));


		// canvas.setNetworkInfo(new WorkareaCanvasNetworkInfo(canvas));
	}


	/**
	 * Javadoc-TODO - Description
	 */
	public static void makeWidgetObjectReady(WorkareaCanvas canvas,
			WidgetObject newObject)
	{
		// // Here the create a connection between widgets on the scene is created
		newObject.getActions().addAction(
				new ExtendedWidgetConnectAction(ActionFactory
						.createDefaultConnectDecorator(), canvas
						.getInteractionLayer(), new GUIsceneConnectProvider(
						canvas)));

		// Adds the action of a user clicking on the object
		newObject.getActions().addAction(
				ActionFactory.createSelectAction(new CreateProvider()));


		// // Creates and add the move without align action
		// newObject.getActions().addAction(ActionFactory.createMoveAction());

		// Creates and add the move with align action
		newObject.getActions().addAction(
				ActionFactory.createAlignWithMoveAction(canvas.getMainLayer(),
						canvas.getInteractionLayer(), null));

		// PrimeAlignWithMove pa = new PrimeAlignWithMove(
		// new PrimeLayerAlignCollector(canvas.getMainLayer()), canvas
		// .getInteractionLayer(), decorator);
		//
		// newObject.getActions()
		// .addAction(ActionFactory.createMoveAction(pa, pa));

		// Adds the double clicking feature for the WidgetObject
		newObject.getActions().addAction(new WidgetAdapterExtended());


		newObject.getActions().addAction(
				ActionFactory.createPopupMenuAction(new JMenuWidget(canvas)));

		// Cleans up the canvas
		canvas.cleanUp();
	}

	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param newCon
	 */
	public static void makeWidgetConnectionReady(WorkareaCanvas canvas,
			WidgetExtendedConnection newCon)
	{
		// Adds the clicking ability
		newCon.getActions().addAction(new WidgetAdapterExtended());

		// Adds the JPopupMenu
		newCon.getActions().addAction(
				ActionFactory
						.createPopupMenuAction(new JMenuConnection(canvas)));
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param room
	 */
	public static void makeWidgetRoomReady(WorkareaCanvas canvas,
			WidgetRoom room)
	{
		// Gives the WidgetRoom the ability to be resized
		room.getActions()
				.addAction(
						new ResizeWidgetAction(ActionFactory
								.createFreeResizeStategy(), ActionFactory
								.createDefaultResizeControlPointResolver(),
								ActionFactory.createDefaultResizeProvider()));

		// Creates a Strategy and a Provider, in one, to be used for the logic behind moving a WidgetRoom
		AlignWithMoveStrategyProvider sp = new AlignWithMoveStrategyProvider(
				(new SingleLayerAlignWithWidgetCollector(canvas.getRoomLayer(),
						true)), canvas.getInteractionLayer(), decorator, true);

		// Gives the WidgetRoom the ability to be moved
		room.getActions().addAction(new MoveRoomAction(sp, sp));

		// Previous room moving logic
		// room.getActions().addAction(
		// new MoveRoomAction(ActionFactory.createFreeMoveStrategy(), ActionFactory.createDefaultMoveProvider()));

		// Clicking ability
		room.getActions().addAction(new WidgetRoomAdapterExtended());
	}


	private static AlignWithMoveDecorator decorator = new AlignWithMoveDecorator()
	{
		public ConnectionWidget createLineWidget(Scene scene)
		{
			ConnectionWidget widget = new ConnectionWidget(scene);
			BasicStroke STROKE = new BasicStroke(1.0f, BasicStroke.JOIN_BEVEL,
					BasicStroke.CAP_BUTT, 5.0f, new float[] { 6.0f, 3.0f },
					0.0f);
			widget.setStroke(STROKE);
			widget.setForeground(Color.BLUE);
			return widget;
		}
	};
}
