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
package graphics.GUI.workareaCanvas.providers;


import java.awt.event.MouseEvent;

import managment.Settings;

import org.netbeans.api.visual.action.WidgetAction.Adapter;
import org.netbeans.api.visual.widget.Widget;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class WidgetRoomAdapterExtended extends Adapter
{
	/*
	 * (non-Javadoc)
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mouseClicked(org. netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mouseClicked(Widget widget, WidgetMouseEvent event)
	{
		if ( Settings.roomsManipulation )
		{
			// If button1, which can be whatever depending on what the OS has chosen, is clicked.
			if ( event.getButton() == MouseEvent.BUTTON1 )
			{
				// If button1 is double clicked.
				if ( event.getClickCount() == 2 )
				{
					// GraphicalFunctions.changeWidgetRoomName(widget);
				}
			}
			return State.CONSUMED;
		}

		return State.REJECTED;
	}



	/*
	 * (non-Javadoc)
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mousePressed(org. netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mousePressed(Widget widget, WidgetMouseEvent event)
	{
		if ( event.getButton() == MouseEvent.BUTTON1 )
		{
			// System.out.println("Mouse Pressed");
			widget.bringToBack();
		}
		return State.REJECTED;
	}



	@Override
	public State mouseReleased(Widget widget, WidgetMouseEvent event)
	{
		return State.REJECTED;
	}
}
