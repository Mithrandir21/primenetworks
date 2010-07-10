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
package widgetManipulation.wdigetAlign;


import java.awt.Point;
import java.awt.Rectangle;

import org.netbeans.api.visual.action.AlignWithMoveDecorator;
import org.netbeans.api.visual.action.MoveProvider;
import org.netbeans.api.visual.action.MoveStrategy;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.modules.visual.action.AlignWithSupport;

import widgets.WidgetObject;



/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PrimeAlignWithMove extends AlignWithSupport implements MoveStrategy, MoveProvider
{

	public PrimeAlignWithMove(PrimeLayerAlignCollector collector,
			LayerWidget interractionLayer, AlignWithMoveDecorator decorator)
	{
		super(collector, interractionLayer, decorator);
	}

	public Point locationSuggested(Widget widget, Point originalLocation,
			Point suggestedLocation)
	{
		Point widgetLocation = widget.getLocation();

		WidgetObject widObj = (WidgetObject) widget;

		Rectangle widgetBounds = widObj.getClientArea();

		Rectangle imageWidgetBoud = widObj.getImageWidget().getBounds();


		// Rectangle bounds = widObj.convertLocalToScene(new Rectangle(
		// imageWidgetBoud.x, widgetBounds.y, imageWidgetBoud.width,
		// widgetBounds.height));
		// Rectangle bounds = widObj.convertLocalToScene(new Rectangle(
		// imageWidgetBoud.x, widgetBounds.y, imageWidgetBoud.width,
		// widgetBounds.height));

		Rectangle bounds = widObj.convertLocalToScene(widgetBounds);

		bounds.translate(suggestedLocation.x - widgetLocation.x,
				suggestedLocation.y - widgetLocation.y);


		Point point = super.locationSuggested(widget, bounds,
				suggestedLocation, true, true, true, true);


		return widObj.getParentWidget().convertSceneToLocal(point);
	}

	public void movementStarted(Widget widget)
	{
		show();
	}

	public void movementFinished(Widget widget)
	{
		hide();
	}

	public Point getOriginalLocation(Widget widget)
	{
		return widget.getPreferredLocation();
	}

	public void setNewLocation(Widget widget, Point location)
	{
		widget.setPreferredLocation(location);
	}

}
