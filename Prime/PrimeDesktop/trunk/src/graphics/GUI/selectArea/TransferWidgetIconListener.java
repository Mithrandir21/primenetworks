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
package graphics.GUI.selectArea;


import graphics.GUI.ghostGlass.GhostGlassPane;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Transparency;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;


/**
 * This is the classes mouseListener. It gets the source of the mouse event, gets the transferhandler for the source and
 * sets the action for the handler to exportAsDrag. Contains only a "mousePressed" method.
 * 
 * @author Bahram Malaekeh
 */
public class TransferWidgetIconListener extends MouseAdapter
{
	protected GhostGlassPane glassPane;

	private BufferedImage image;

	public TransferWidgetIconListener(GhostGlassPane glassPane, String action,
			ImageIcon icon)
	{
		this.glassPane = glassPane;

		Image img = icon.getImage();

		// Create a buffered image with a format that's compatible with the screen
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();

		// Create the buffered image
		GraphicsDevice gs = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gs.getDefaultConfiguration();

		image = gc.createCompatibleImage(img.getWidth(null), img
				.getHeight(null), Transparency.TRANSLUCENT);

		// Copy image to buffered image
		Graphics g = image.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(img, 0, 0, null);
		g.dispose();
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e)
	{
		Component c = e.getComponent();

		glassPane.setVisible(true);

		Point p = (Point) e.getPoint().clone();
		SwingUtilities.convertPointToScreen(p, c);
		SwingUtilities.convertPointFromScreen(p, glassPane);

		glassPane.setPoint(p);
		glassPane.setImage(image);
		glassPane.repaint();
	}


	public void mouseReleased(MouseEvent e)
	{
		Component c = e.getComponent();

		Point p = (Point) e.getPoint().clone();
		SwingUtilities.convertPointToScreen(p, c);

		SwingUtilities.convertPointFromScreen(p, glassPane);

		glassPane.setPoint(p);
		glassPane.setVisible(false);
		glassPane.setImage(null);

		fireDrop(e);
	}


	private void fireDrop(MouseEvent e)
	{
		JLabel comp = (JLabel) e.getSource();
		TransferHandler handler = comp.getTransferHandler();
		handler.exportAsDrag(comp, e, TransferHandler.COPY);

		// This is done because Drop only happens after the mouse has moved, not on actual MouseRelease
		try
		{
			Robot robot = new Robot();
			Point locOnScreen = e.getLocationOnScreen();
			robot.mouseMove(locOnScreen.x - 1, locOnScreen.y - 1);
		}
		catch ( AWTException e1 )
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
