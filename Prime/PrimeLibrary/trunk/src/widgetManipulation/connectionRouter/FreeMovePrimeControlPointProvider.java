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
import java.util.ArrayList;
import java.util.List;

import org.netbeans.api.visual.action.MoveControlPointProvider;
import org.netbeans.api.visual.widget.ConnectionWidget;

import widgets.WorkareaCanvas;
import connections.WidgetExtendedConnection;


/**
 * This class is added to a {@link WidgetExtendedConnection} to added the free
 * movement feature a "Line-break" point. See
 * {@link AddRemovePrimeControlPointAction}.
 * 
 * @author Alex (Modified by Bahram Malaekeh)
 */
public class FreeMovePrimeControlPointProvider implements
		MoveControlPointProvider
{
	// The workareacanvas that this provider belongs to
	private WorkareaCanvas canvas;

	/**
	 * This is a constructor for the class that takes as parameter the
	 * {@link WorkareaCanvas} that the {@link WidgetExtendedConnection} that
	 * this class object will be added to.
	 */
	public FreeMovePrimeControlPointProvider(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.MoveControlPointProvider#locationSuggested
	 * (org.netbeans.api.visual.widget.ConnectionWidget, int, java.awt.Point)
	 */
	@Override
	public List<Point> locationSuggested(ConnectionWidget connectionWidget,
			int index, Point suggestedLocation)
	{
		List<Point> controlPoints = connectionWidget.getControlPoints();
		int cpSize = controlPoints.size() - 1;
		ArrayList<Point> list = new ArrayList<Point>(controlPoints);


		if ( index <= 0 || index >= cpSize )
		{
			return null;
		}

		if ( index == 1 )
		{
			list.set(
					0,
					connectionWidget.getSourceAnchor()
							.compute(connectionWidget.getSourceAnchorEntry())
							.getAnchorSceneLocation());
		}

		if ( index == cpSize - 1 )
		{
			list.set(
					cpSize,
					connectionWidget.getTargetAnchor()
							.compute(connectionWidget.getTargetAnchorEntry())
							.getAnchorSceneLocation());
		}

		canvas.setChanged(true);
		list.set(index, suggestedLocation);
		return list;
	}

}
