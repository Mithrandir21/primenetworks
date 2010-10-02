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


import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import org.netbeans.api.visual.action.ResizeControlPointResolver;
import org.netbeans.api.visual.action.ResizeProvider;
import org.netbeans.api.visual.action.ResizeStrategy;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.Widget;


/**
 * This class handles mouse interactions in regards to the resizing of a
 * {@link Widget}.
 * 
 */
public class ResizeWidgetAction extends WidgetAction.LockedAdapter
{
	private ResizeStrategy strategy;

	private ResizeControlPointResolver resolver;

	private ResizeProvider provider;

	private Widget resizingWidget = null;

	private ResizeProvider.ControlPoint controlPoint;

	private Rectangle originalSceneRectangle = null;

	private Insets insets;

	private Point dragSceneLocation = null;

	public ResizeWidgetAction(ResizeStrategy strategy,
			ResizeControlPointResolver resolver, ResizeProvider provider)
	{
		this.strategy = strategy;
		this.resolver = resolver;
		this.provider = provider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction.LockedAdapter#isLocked()
	 */
	protected boolean isLocked()
	{
		return this.resizingWidget != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.WidgetAction.LockedAdapter#mousePressed
	 * (org.netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	public State mousePressed(Widget widget, WidgetMouseEvent event)
	{
		if ( event.getButton() == MouseEvent.BUTTON1
				&& event.getClickCount() == 1 )
		{
			this.insets = widget.getBorder().getInsets();
			this.controlPoint = this.resolver.resolveControlPoint(widget,
					event.getPoint());
			if ( this.controlPoint != null )
			{
				this.resizingWidget = widget;
				this.originalSceneRectangle = null;
				if ( widget.isPreferredBoundsSet() )
					this.originalSceneRectangle = widget.getPreferredBounds();
				if ( this.originalSceneRectangle == null )
					this.originalSceneRectangle = widget.getBounds();
				if ( this.originalSceneRectangle == null )
					this.originalSceneRectangle = widget.getPreferredBounds();
				this.dragSceneLocation = widget.convertLocalToScene(event
						.getPoint());
				this.provider.resizingStarted(widget);
				return State.createLocked(widget, this);
			}
		}
		return State.REJECTED;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.WidgetAction.LockedAdapter#mouseReleased
	 * (org.netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	public State mouseReleased(Widget widget, WidgetMouseEvent event)
	{
		boolean state = resize(widget, event.getPoint());
		if ( state )
		{
			this.resizingWidget = null;
			this.provider.resizingFinished(widget);
		}
		return state ? State.CONSUMED : State.REJECTED;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.WidgetAction.LockedAdapter#mouseDragged
	 * (org.netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	public State mouseDragged(Widget widget, WidgetMouseEvent event)
	{
		return resize(widget, event.getPoint()) ? State.createLocked(widget,
				this) : State.REJECTED;
	}

	private boolean resize(Widget widget, Point newLocation)
	{
		if ( this.resizingWidget == null )
			return false;

		if ( !this.resizingWidget.equals(widget) )
			return false;

		newLocation = widget.convertLocalToScene(newLocation);
		int dx = newLocation.x - dragSceneLocation.x;
		int dy = newLocation.y - dragSceneLocation.y;
		int minx = insets.left + insets.right;
		int miny = insets.top + insets.bottom;

		Rectangle rectangle = new Rectangle(this.originalSceneRectangle);
		switch ( this.controlPoint )
		{
		case BOTTOM_CENTER:
			resizeToBottom(miny, rectangle, dy);
			break;
		case BOTTOM_LEFT:
			resizeToLeft(minx, rectangle, dx);
			resizeToBottom(miny, rectangle, dy);
			break;
		case BOTTOM_RIGHT:
			resizeToRight(minx, rectangle, dx);
			resizeToBottom(miny, rectangle, dy);
			break;
		case CENTER_LEFT:
			resizeToLeft(minx, rectangle, dx);
			break;
		case CENTER_RIGHT:
			resizeToRight(minx, rectangle, dx);
			break;
		case TOP_CENTER:
			resizeToTop(miny, rectangle, dy);
			break;
		case TOP_LEFT:
			resizeToLeft(minx, rectangle, dx);
			resizeToTop(miny, rectangle, dy);
			break;
		case TOP_RIGHT:
			resizeToRight(minx, rectangle, dx);
			resizeToTop(miny, rectangle, dy);
			break;
		}

		widget.setPreferredBounds(this.strategy.boundsSuggested(widget,
				this.originalSceneRectangle, rectangle, this.controlPoint));
		return true;
	}

	private static void resizeToTop(int miny, Rectangle rectangle, int dy)
	{
		if ( rectangle.height - dy < miny )
			dy = rectangle.height - miny;
		rectangle.y += dy;
		rectangle.height -= dy;
	}

	private static void resizeToBottom(int miny, Rectangle rectangle, int dy)
	{
		if ( rectangle.height + dy < miny )
			dy = miny - rectangle.height;
		rectangle.height += dy;
	}

	private static void resizeToLeft(int minx, Rectangle rectangle, int dx)
	{
		if ( rectangle.width - dx < minx )
			dx = rectangle.width - minx;
		rectangle.x += dx;
		rectangle.width -= dx;
	}

	private static void resizeToRight(int minx, Rectangle rectangle, int dx)
	{
		if ( rectangle.width + dx < minx )
			dx = minx - rectangle.width;
		rectangle.width += dx;
	}
}
