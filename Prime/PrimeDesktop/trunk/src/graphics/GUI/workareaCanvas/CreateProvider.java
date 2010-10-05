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
package graphics.GUI.workareaCanvas;


import graphics.PrimeMain;

import java.awt.Point;

import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class CreateProvider implements SelectProvider
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.SelectProvider#isAimingAllowed(org.netbeans
	 * .api.visual.widget.Widget, java.awt.Point, boolean)
	 */
	public boolean isAimingAllowed(Widget arg0, Point arg1, boolean arg2)
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.SelectProvider#isSelectionAllowed(org.
	 * netbeans.api.visual.widget.Widget, java.awt.Point, boolean)
	 */
	public boolean isSelectionAllowed(Widget arg0, Point arg1, boolean arg2)
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.SelectProvider#select(org.netbeans.api
	 * .visual.widget.Widget, java.awt.Point, boolean)
	 */
	public void select(Widget relatedWidget, Point localLocation,
			boolean invertSelection)
	{
		if ( relatedWidget instanceof WidgetObject
				&& PrimeMain.currentCanvas != null )
		{
			relatedWidget.bringToFront();

			WidgetObject widgetobj = (WidgetObject) relatedWidget;

			// Sets the current WidgetObject for the systems current canvas
			PrimeMain.currentCanvas.setCurrentWidgetObject(widgetobj);

			// Updates the information panel with information from the selected
			// object.
			PrimeMain.updatePropertiesObjectArea(widgetobj.getObject(), false);
		}
	}
}
