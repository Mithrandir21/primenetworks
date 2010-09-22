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



import java.awt.Rectangle;
import java.util.HashSet;

import org.netbeans.api.visual.action.RectangularSelectProvider;

import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class PrimeObjectSceneRectangularSelectProvider implements
		RectangularSelectProvider
{
	private WorkareaCanvas canvas;

	public PrimeObjectSceneRectangularSelectProvider(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.RectangularSelectProvider#performSelection
	 * (java.awt.Rectangle)
	 */
	@Override
	public void performSelection(Rectangle sceneSelection)
	{
		boolean entirely = sceneSelection.width > 0;
		int w = sceneSelection.width;
		int h = sceneSelection.height;
		Rectangle rect = new Rectangle(w >= 0 ? 0 : w, h >= 0 ? 0 : h,
				w >= 0 ? w : -w, h >= 0 ? h : -h);
		rect.translate(sceneSelection.x, sceneSelection.y);


		WidgetObject[] objArray = this.canvas.getWidgetObjectsOnTheScene();
		HashSet<WidgetObject> set = new HashSet<WidgetObject>();

		for ( int i = 0; i < objArray.length; i++ )
		{
			WidgetObject object = objArray[i];

			if ( object == null )
			{
				continue;
			}
			if ( entirely )
			{
				Rectangle widgetRect = object.convertLocalToScene(object
						.getBounds());
				if ( rect.contains(widgetRect) )
				{
					set.add(object);
				}
			}
			else
			{
				// if ( object instanceof ConnectionWidget )
				// {
				// System.out.println("testing....2");
				// ConnectionWidget conn = (ConnectionWidget) object;
				// java.util.List<Point> points = conn.getControlPoints();
				// for ( int j = points.size() - 2; j >= 0; j-- )
				// {
				// Point p1 = widget.convertLocalToScene(points.get(j));
				// Point p2 = widget.convertLocalToScene(points.get(j + 1));
				// if ( new Line2D.Float(p1, p2).intersects(rect) )
				// set.add(object);
				// }
				// }
				// else
				// {
				// System.out.println("testing....3");
				// Rectangle widgetRect =
				// widget.convertLocalToScene(widget.getBounds());
				// if ( rect.intersects(widgetRect) )
				// set.add(object);
				// }
			}
		}
		// Iterator<WidgetObject> iterator = set.iterator();
		// scene.setFocusedObject(iterator.hasNext() ? iterator.next() : null);
		// scene.userSelectionSuggested(set, false);
	}

}
