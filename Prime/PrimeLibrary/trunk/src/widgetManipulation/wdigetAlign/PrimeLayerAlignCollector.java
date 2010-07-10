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


import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.netbeans.api.visual.action.AlignWithWidgetCollector;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;


public class PrimeLayerAlignCollector implements AlignWithWidgetCollector
{
	private LayerWidget collectionLayer;

	public PrimeLayerAlignCollector(LayerWidget collectionLayer)
	{
		this.collectionLayer = collectionLayer;
	}

	public java.util.List<Rectangle> getRegions(Widget movingWidget)
	{
		List<Widget> children = collectionLayer.getChildren();
		ArrayList<Rectangle> regions = new ArrayList<Rectangle>(children.size());

		ArrayList<WidgetObject> widgetObjects = new ArrayList<WidgetObject>();
		for ( Widget widget : children )
		{
			if ( widget != movingWidget && widget instanceof WidgetObject )
			{
				widgetObjects.add((WidgetObject) widget);
			}
		}

		// for ( WidgetObject widget : widgetObjects )
		// {
		// if ( widget != movingWidget )
		// {
		// // regions.add(widget.getImageWidget().convertLocalToScene(
		// // outerBounds ? widget.getBounds() : widget
		// // .getClientArea()));
		// // regions.add(widget.getImageWidget().convertLocalToScene(
		// // widget.getBounds()));
		// regions.add(widget.getImageWidget().convertLocalToScene(
		// widget.getClientArea()));
		// }
		// }

		// for ( WidgetObject widget : widgetObjects )
		// {
		// if ( widget != movingWidget )
		// {
		// Rectangle widgetBound = widget.getBounds();
		// Rectangle imageWidgetBoud = widget.getImageWidget().getBounds();
		//
		//
		// Rectangle region = new Rectangle(imageWidgetBoud.x,
		// widgetBound.y, imageWidgetBoud.width,
		// widgetBound.height);
		//
		// regions.add(widget.convertLocalToScene(region));
		// }
		// }


		for ( Widget widget : children )
		{
			if ( widget != movingWidget )
			{
				Rectangle widgetBound = widget.getBounds();

				regions.add(widget.convertLocalToScene(widgetBound));
			}
		}

		return regions;
	}
}
