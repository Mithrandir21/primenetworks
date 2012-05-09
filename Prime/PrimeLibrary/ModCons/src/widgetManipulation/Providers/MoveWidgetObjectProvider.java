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
package widgetManipulation.Providers;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.AlignWithMoveDecorator;
import org.netbeans.api.visual.action.AlignWithWidgetCollector;
import org.netbeans.api.visual.action.MoveProvider;
import org.netbeans.api.visual.action.MoveStrategy;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.modules.visual.action.AlignWithSupport;

import widgetManipulation.widgetAlign.PrimeAlignWithMove;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * This extension of the {@link AlignWithSupport} class is meant to be used when
 * users move {@link WidgetObject} in a {@link WorkareaCanvas}. This class will
 * align the {@link WidgetObject} with other {@link Widget Widgets}.
 * This class works together with the {@link PrimeAlignWithMove} class.
 * 
 * @author Bahram Malaekeh
 */
public class MoveWidgetObjectProvider extends AlignWithSupport implements
		MoveStrategy, MoveProvider
{


	private static final AlignWithMoveDecorator ALIGN_WITH_MOVE_DECORATOR_DEFAULT = new AlignWithMoveDecorator()
	{
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.netbeans.api.visual.action.AlignWithMoveDecorator#createLineWidget
		 * (org.netbeans.api.visual.widget.Scene)
		 */
		public ConnectionWidget createLineWidget(Scene scene)
		{
			ConnectionWidget widget = new ConnectionWidget(scene);
			widget.setStroke(new BasicStroke(1.0f, BasicStroke.JOIN_BEVEL,
					BasicStroke.CAP_BUTT, 5.0f, new float[] { 6.0f, 3.0f },
					0.0f));
			widget.setForeground(Color.BLUE);
			return widget;
		}
	};

	public MoveWidgetObjectProvider(AlignWithWidgetCollector collector,
			LayerWidget interractionLayer)
	{
		super(collector, interractionLayer, ALIGN_WITH_MOVE_DECORATOR_DEFAULT);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.MoveStrategy#locationSuggested(org.netbeans
	 * .api.visual.widget.Widget,
	 * java.awt.Point, java.awt.Point)
	 */
	public Point locationSuggested(Widget widget, Point originalLocation,
			Point suggestedLocation)
	{
		Point widgetLocation = widget.getLocation();
		Rectangle widgetBounds = widget.getBounds();
		Rectangle bounds = widget.convertSceneToLocal(widgetBounds);
		bounds.translate(suggestedLocation.x - widgetLocation.x,
				suggestedLocation.y - widgetLocation.y);
		Point point = super.locationSuggested(widget, bounds,
				suggestedLocation, true, true, true, true);
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
	 * .api.visual.widget.Widget,
	 * java.awt.Point)
	 */
	public void setNewLocation(Widget widget, Point location)
	{
		ActionFactory.createDefaultMoveProvider().setNewLocation(widget,
				location);
	}

}
