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
package graphics.GUI.ghostGlass;


import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.SwingUtilities;


/**
 * TODO - Description NEEDED!
 * 
 * @author Romain Guy
 */
public class GhostMotionAdapter extends MouseMotionAdapter
{
	private GhostGlassPane glassPane;

	public GhostMotionAdapter(GhostGlassPane glassPane)
	{
		this.glassPane = glassPane;
	}

	public void mouseDragged(MouseEvent e)
	{
		Component c = e.getComponent();

		Point p = (Point) e.getPoint().clone();
		SwingUtilities.convertPointToScreen(p, c);
		SwingUtilities.convertPointFromScreen(p, this.glassPane);
		this.glassPane.setPoint(p);

		this.glassPane.repaint();
	}
}
