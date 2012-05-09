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
package widgetManipulation.widgetAlign;


import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.AlignWithMoveDecorator;
import org.netbeans.api.visual.action.AlignWithWidgetCollector;
import org.netbeans.api.visual.action.MoveProvider;
import org.netbeans.api.visual.action.MoveStrategy;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.modules.visual.action.AlignWithSupport;

import widgetManipulation.Providers.MoveWidgetObjectProvider;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;



/**
 * This extension of the {@link AlignWithSupport} class is meant to be used when
 * users move {@link WidgetObject} in a {@link WorkareaCanvas}. This class will
 * align the {@link WidgetObject} with other {@link Widget Widgets}.
 * This class works together with the {@link MoveWidgetObjectProvider} class.
 * 
 * @author David Kaspar (Extended by Bahram Malaekeh)
 * 
 */
public class PrimeAlignWithMove extends AlignWithSupport implements
		MoveStrategy, MoveProvider
{
	private WorkareaCanvas canvas;

	private boolean outerBounds;

	/**
	 * A constructor for the class.
	 * 
	 * @param collector
	 * @param interractionLayer
	 * @param decorator
	 * @param outerBounds
	 * @param canvas
	 */
	public PrimeAlignWithMove(AlignWithWidgetCollector collector,
			LayerWidget interractionLayer, AlignWithMoveDecorator decorator,
			boolean outerBounds, WorkareaCanvas canvas)
	{
		super(collector, interractionLayer, decorator);
		this.outerBounds = outerBounds;
		this.canvas = canvas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.MoveStrategy#locationSuggested(org.netbeans
	 * .api.visual.widget.Widget, java.awt.Point, java.awt.Point)
	 */
	public Point locationSuggested(Widget widget, Point originalLocation,
			Point suggestedLocation)
	{
		Point widgetLocation = widget.getLocation();
		Rectangle widgetBounds = outerBounds ? widget.getBounds() : widget
				.getClientArea();
		Rectangle bounds = widget.convertLocalToScene(widgetBounds);
		bounds.translate(suggestedLocation.x - widgetLocation.x,
				suggestedLocation.y - widgetLocation.y);
		Insets insets = widget.getBorder().getInsets();
		if ( !outerBounds )
		{
			suggestedLocation.x += insets.left;
			suggestedLocation.y += insets.top;
		}
		Point point = super.locationSuggested(widget, bounds,
				suggestedLocation, true, true, true, true);
		if ( !outerBounds )
		{
			point.x -= insets.left;
			point.y -= insets.top;
		}
		return widget.getParentWidget().convertSceneToLocal(point);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.MoveProvider#movementStarted(org.netbeans
	 * .api.visual.widget.Widget)
	 */
	public void movementStarted(Widget widget)
	{
		canvas.setChanged(true);
		show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.MoveProvider#movementFinished(org.netbeans
	 * .api.visual.widget.Widget)
	 */
	public void movementFinished(Widget widget)
	{
		hide();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.MoveProvider#getOriginalLocation(org.netbeans
	 * .api.visual.widget.Widget)
	 */
	public Point getOriginalLocation(Widget widget)
	{
		return ActionFactory.createDefaultMoveProvider().getOriginalLocation(
				widget);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.MoveProvider#setNewLocation(org.netbeans
	 * .api.visual.widget.Widget, java.awt.Point)
	 */
	public void setNewLocation(Widget widget, Point location)
	{
		ActionFactory.createDefaultMoveProvider().setNewLocation(widget,
				location);
	}

}
