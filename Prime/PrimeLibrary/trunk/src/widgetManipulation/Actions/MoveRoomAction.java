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
package widgetManipulation.Actions;



import java.awt.Point;
import java.awt.event.MouseEvent;

import managment.Settings;

import org.netbeans.api.visual.action.MoveProvider;
import org.netbeans.api.visual.action.MoveStrategy;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.Widget;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 */
public class MoveRoomAction extends WidgetAction.LockedAdapter
{
	private MoveStrategy strategy;

	private MoveProvider provider;

	private Widget movingWidget = null;

	private Point dragSceneLocation = null;

	private Point originalSceneLocation = null;


	public MoveRoomAction(MoveStrategy strategy, MoveProvider provider)
	{
		this.strategy = strategy;
		this.provider = provider;
	}

	protected boolean isLocked()
	{
		return this.movingWidget != null;
	}

	public State mousePressed(Widget widget, WidgetMouseEvent event)
	{
		if ( (event.getButton() == MouseEvent.BUTTON1 && event.getClickCount() == 1)
				&& ((event.getModifiers() & MouseEvent.SHIFT_MASK) != 0)
				&& Settings.roomsManipulation )
		{
			this.movingWidget = widget;
			this.originalSceneLocation = this.provider.getOriginalLocation(widget);
			if ( this.originalSceneLocation == null )
				this.originalSceneLocation = new Point();
			this.dragSceneLocation = widget.convertLocalToScene(event.getPoint());
			this.provider.movementStarted(widget);
			return State.createLocked(widget, this);
		}
		return State.REJECTED;
	}

	public State mouseReleased(Widget widget, WidgetMouseEvent event)
	{
		boolean state = move(widget, event.getPoint());
		if ( state )
		{
			this.movingWidget = null;
			this.provider.movementFinished(widget);
		}
		return state ? State.CONSUMED : State.REJECTED;
	}

	public State mouseDragged(Widget widget, WidgetMouseEvent event)
	{
		return move(widget, event.getPoint()) ? State
				.createLocked(widget, this) : State.REJECTED;
	}

	private boolean move(Widget widget, Point newLocation)
	{
		if ( !this.movingWidget.equals(widget) )
			return false;
		newLocation = widget.convertLocalToScene(newLocation);
		Point location = new Point(originalSceneLocation.x + newLocation.x
				- dragSceneLocation.x, originalSceneLocation.y + newLocation.y
				- dragSceneLocation.y);
		this.provider.setNewLocation(widget, this.strategy.locationSuggested(widget,
				this.originalSceneLocation, location));
		return true;
	}

}
