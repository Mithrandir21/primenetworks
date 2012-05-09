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
package widgetManipulation.connectionRouter;


import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Widget;

import widgets.WorkareaCanvas;
import connections.WidgetExtendedConnection;


/**
 * This class is added to a {@link WidgetExtendedConnection} to add "Line-break"
 * functions.
 * 
 * @author Alex (Modified by Bahram Malaekeh)
 */
public class AddRemovePrimeControlPointAction extends WidgetAction.Adapter
{
	// The WorkareaCanvas this action belongs to
	private WorkareaCanvas canvas;

	private double createSensitivity;

	private double deleteSensitivity;

	private ConnectionWidget.RoutingPolicy routingPolicy;


	public AddRemovePrimeControlPointAction(double createSensitivity,
			double deleteSensitivity,
			ConnectionWidget.RoutingPolicy routingPolicy, WorkareaCanvas canvas)
	{
		this.createSensitivity = createSensitivity;
		this.deleteSensitivity = deleteSensitivity;
		this.routingPolicy = routingPolicy;
		this.canvas = canvas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.WidgetAction.Adapter#mouseClicked(org.
	 * netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	public State mouseClicked(Widget widget, WidgetMouseEvent event)
	{
		if ( event.getButton() == MouseEvent.BUTTON1
				&& event.getClickCount() == 2
				&& widget instanceof ConnectionWidget )
		{
			addRemoveControlPoint((ConnectionWidget) widget, event.getPoint());
			// Sets the canvas as changed
			canvas.setChanged(true);
			return State.CONSUMED;
		}
		return State.REJECTED;
	}


	/**
	 * Adds or removes a control point on a specified location
	 * 
	 * @param widget
	 *            the connection widget
	 * @param localLocation
	 *            the local location
	 */
	private void addRemoveControlPoint(ConnectionWidget widget,
			Point localLocation)
	{
		ArrayList<Point> list = new ArrayList<Point>(widget.getControlPoints());

		if ( !removeControlPoint(localLocation, list, deleteSensitivity) )
		{
			Point exPoint = null;
			int index = 0;

			for ( Point elem : list )
			{
				if ( exPoint != null )
				{
					Line2D l2d = new Line2D.Double(exPoint, elem);
					if ( l2d.ptLineDist(localLocation) < createSensitivity )
					{
						list.add(index, localLocation);
						break;
					}
				}
				exPoint = elem;
				index++;
			}
		}

		if ( routingPolicy != null )
		{
			widget.setRoutingPolicy(routingPolicy);
		}

		widget.setControlPoints(list, false);
	}

	private boolean removeControlPoint(Point point, ArrayList<Point> list,
			double deleteSensitivity)
	{
		for ( Point elem : list )
		{
			if ( elem.distance(point) < deleteSensitivity )
			{
				list.remove(elem);
				// Sets the canvas as changed
				canvas.setChanged(true);
				return true;
			}
		}
		return false;
	}


}
