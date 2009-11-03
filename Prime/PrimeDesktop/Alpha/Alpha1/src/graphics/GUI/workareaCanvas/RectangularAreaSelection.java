package graphics.GUI.workareaCanvas;


import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import managment.RoomManagment;
import managment.Settings;

import org.netbeans.api.visual.action.RectangularSelectDecorator;
import org.netbeans.api.visual.action.RectangularSelectProvider;
import org.netbeans.api.visual.action.WidgetAction.LockedAdapter;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetRoom;
import actions.canvasActions.ActionCreateRoom;


public class RectangularAreaSelection extends LockedAdapter
{

	private RectangularSelectDecorator decorator;

	private LayerWidget roomLayer;

	private RectangularSelectProvider provider;


	private WidgetRoom selectionWidget;

	private Rectangle selectionSceneRectangle;


	public RectangularAreaSelection(RectangularSelectDecorator decorator,
			LayerWidget roomLayer, RectangularSelectProvider provider)
	{
		this.decorator = decorator;
		this.roomLayer = roomLayer;
		this.provider = provider;
	}


	/*
	 * (non-Javadoc)
	 * @see org.netbeans.api.visual.action.WidgetAction.LockedAdapter#isLocked()
	 */
	@Override
	protected boolean isLocked()
	{
		return selectionSceneRectangle != null;
	}


	@Override
	public State mousePressed(Widget widget, WidgetMouseEvent event)
	{
		// If the event button was the left click and the it was clicked once.
		if ( (event.getButton() == MouseEvent.BUTTON1 && event.getClickCount() == 1)
				&& Settings.roomsManipulation )
		{
			// Creates the widgetRoom
			selectionWidget = (WidgetRoom) decorator.createSelectionWidget();

			assert selectionWidget != null;

			// Adds the actions supported by the WidgetRoom
			ActionsAdder.makeWidgetRoomReady(PrimeMain1.currentCanvas,
					selectionWidget);

			// Add the widgetRoom to the canvas
			RoomManagment.addRoom(PrimeMain1.currentCanvas, selectionWidget);

			// Creates and sets the rectangle that is the bounds of the
			// WidgetRoom
			selectionSceneRectangle = new Rectangle(widget
					.convertLocalToScene(event.getPoint()));
			move(widget, event.getPoint());
			return State.createLocked(widget, this);
		}

		return State.REJECTED;
	}


	@Override
	public State mouseReleased(Widget widget, WidgetMouseEvent event)
	{
		if ( selectionSceneRectangle != null )
		{
			move(widget, event.getPoint());

			ActionCreateRoom action = new ActionCreateRoom("Create a Room",
					selectionWidget);
			action.performAction(true);

			// Prevents double calling of the action
			selectionSceneRectangle = null;
		}
		return State.REJECTED;
	}


	@Override
	public State mouseDragged(Widget widget, WidgetMouseEvent event)
	{
		if ( selectionSceneRectangle != null )
		{
			move(widget, event.getPoint());
			return State.createLocked(widget, this);
		}
		return State.REJECTED;
	}



	private void resolveSelectionWidgetLocationBounds()
	{
		// Sets the top left most location of the WidgetRoom
		selectionWidget.setPreferredRoomLocation(selectionSceneRectangle
				.getLocation());

		int w = selectionSceneRectangle.width;
		int h = selectionSceneRectangle.height;

		// Sets the bounds of the WidgetRooms
		selectionWidget.setPreferredRoomBounds(new Rectangle(w >= 0 ? 0 : w,
				h >= 0 ? 0 : h, w >= 0 ? w : -w, h >= 0 ? h : -h));
	}

	private void move(Widget widget, Point newLocation)
	{
		Point sceneLocation = widget.convertLocalToScene(newLocation);
		selectionSceneRectangle.width = sceneLocation.x
				- selectionSceneRectangle.x;
		selectionSceneRectangle.height = sceneLocation.y
				- selectionSceneRectangle.y;
		resolveSelectionWidgetLocationBounds();
	}
}
