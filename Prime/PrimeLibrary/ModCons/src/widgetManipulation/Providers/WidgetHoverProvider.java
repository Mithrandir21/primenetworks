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


import java.awt.Color;

import org.netbeans.api.visual.action.HoverProvider;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;


/**
 * This {@link HoverProvider} extension is meant to be used to provide a
 * {@link Widget} with hover-over feature.
 * 
 * @author Bahram Malaekeh
 */
public class WidgetHoverProvider implements HoverProvider
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.HoverProvider#widgetHovered(org.netbeans
	 * .api.visual.widget.Widget)
	 */
	@Override
	public void widgetHovered(Widget widget)
	{
		if ( widget != null )
		{
			// FIXME - make something out of this.
			// System.out.println(widget.getToolTipText());
			WidgetObject wid = (WidgetObject) widget;
			wid.setBorder(BorderFactory.createRoundedBorder(10, 10,
					Color.white, Color.black));
			System.out.println("Hovering over - ");
			// wid.getObject().getDescription());
		}
	}

}
