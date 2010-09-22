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
package graphics.GUI.ghostGlass;


import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


/**
 * TODO - Description NEEDED!
 * 
 * @author Romain Guy
 */
public class GhostGlassPane extends JPanel
{
	private AlphaComposite composite;

	private BufferedImage dragged = null;

	private Point location = new Point(0, 0);

	public GhostGlassPane()
	{
		setOpaque(false);
		this.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
	}

	public void setImage(BufferedImage dragged)
	{
		this.dragged = dragged;
	}

	public void setPoint(Point location)
	{
		this.location = location;
	}

	public void paintComponent(Graphics g)
	{
		if ( this.dragged == null )
			return;

		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(this.composite);
		g2.drawImage(this.dragged,
				(int) (this.location.getX() - (this.dragged.getWidth(this) / 2)),
				(int) (this.location.getY() - (this.dragged.getHeight(this) / 2)), null);
	}
}
