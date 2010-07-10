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


import java.awt.BasicStroke;
import java.awt.Color;

import org.netbeans.api.visual.action.AlignWithMoveDecorator;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PrimeAlignWithMoveDecorator implements AlignWithMoveDecorator
{

	/*
	 * (non-Javadoc)
	 * @see org.netbeans.api.visual.action.AlignWithMoveDecorator#createLineWidget(org.netbeans.api.visual.widget.Scene)
	 */
	@Override
	public ConnectionWidget createLineWidget(Scene scene)
	{
		ConnectionWidget widget = new ConnectionWidget(scene);
		widget.setStroke(new BasicStroke(1.0f, BasicStroke.JOIN_BEVEL,
				BasicStroke.CAP_BUTT, 5.0f, new float[] { 6.0f, 3.0f }, 0.0f));
		widget.setForeground(Color.BLUE);
		return widget;
	}

}
