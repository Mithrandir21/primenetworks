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
package widgetManipulation;


import managment.RoomManagment;

import org.netbeans.api.visual.action.RectangularSelectDecorator;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetRoom;
import widgets.WorkareaCanvas;


/**
 * This class will contains only a method that calls the
 * {@link RoomManagment#createWidgetRoom(WorkareaCanvas, objects.Room)} that
 * creates a {@link WidgetRoom} on the classes {@link WorkareaCanvas}.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PrimeRectangularSelectDecorator implements
		RectangularSelectDecorator
{
	private WorkareaCanvas canvas;

	public PrimeRectangularSelectDecorator(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.RectangularSelectDecorator#
	 * createSelectionWidget()
	 */
	@Override
	public Widget createSelectionWidget()
	{
		WidgetRoom widget = RoomManagment.createWidgetRoom(this.canvas,
				"RoomName");

		return widget;
	}
}