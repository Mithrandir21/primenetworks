package graphics.GUI.workareaCanvas;


import graphics.PrimeMain1;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import managment.RoomManagment;

import org.netbeans.api.visual.action.RectangularSelectDecorator;
import org.netbeans.api.visual.action.RectangularSelectProvider;
import org.netbeans.api.visual.action.WidgetAction.LockedAdapter;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetRoom;


public class RectangularAreaSelection extends LockedAdapter
{

	private RectangularSelectDecorator decorator;

	private LayerWidget roomLayer;

	private RectangularSelectProvider provider;


	private WidgetRoom selectionWidget;

	private Rectangle selectionSceneRectangle;


	public RectangularAreaSelection(RectangularSelectDecorator decorator, LayerWidget roomLayer,
			RectangularSelectProvider provider)
	{
		this.decorator = decorator;
		this.roomLayer = roomLayer;
		this.provider = provider;
	}


	@Override
	protected boolean isLocked()
	{
		return selectionSceneRectangle != null;
	}


	public State mousePressed(Widget widget, WidgetMouseEvent event)
	{
		// If the event button was the left click and the it was clicked once.
		// This if also need the "ALT" button to be held
		if ( (event.getButton() == MouseEvent.BUTTON1 && event.getClickCount() == 1)
				&& ((event.getModifiers() & MouseEvent.ALT_MASK) != 0) )
		{
			// Creates the widgetRoom
			selectionWidget = (WidgetRoom) decorator.createSelectionWidget();
			assert selectionWidget != null;
			
			// Add the widgetRoom to the canvas
			RoomManagment.addRoom(PrimeMain1.currentCanvas, selectionWidget);
			
			// Creates and sets the rectangle that is the bounds of the WidgetRoom
			selectionSceneRectangle = new Rectangle(widget.convertLocalToScene(event.getPoint()));
			move(widget, event.getPoint());
			return State.createLocked(widget, this);
		}

		return State.REJECTED;
	}


	public State mouseReleased(Widget widget, WidgetMouseEvent event)
	{
		if ( selectionSceneRectangle != null )
		{
			move(widget, event.getPoint());
			// selectionWidget.getParentWidget ().removeChild (selectionWidget);
			// provider.performSelection (selectionSceneRectangle);
			selectionSceneRectangle = null;
		}
		return State.REJECTED;
	}


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
		selectionWidget.setPreferredRoomLocation(selectionSceneRectangle.getLocation());
		
		int w = selectionSceneRectangle.width;
		int h = selectionSceneRectangle.height;
		
		// Sets the bounds of the WidgetRooms
		selectionWidget.setPreferredRoomBounds(new Rectangle(w >= 0 ? 0 : w, h >= 0 ? 0 : h, w >= 0 ? w : -w,
				h >= 0 ? h : -h));
	}

	private void move(Widget widget, Point newLocation)
	{
		Point sceneLocation = widget.convertLocalToScene(newLocation);
		selectionSceneRectangle.width = sceneLocation.x - selectionSceneRectangle.x;
		selectionSceneRectangle.height = sceneLocation.y - selectionSceneRectangle.y;
		resolveSelectionWidgetLocationBounds();
	}
}
