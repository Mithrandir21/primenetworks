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
package graphics.GUI.visualObjectCustomization;


import graphics.GraphicalFunctions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import objects.Object;
import widgets.WidgetButton;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class VisualCustomListener implements ActionListener
{
	// The VisualCustomFrame that holds the buttons with the Images on
	private VisualCustomFrame frame;

	private JFileChooser fc;

	private Object object;

	/**
	 * A constructor the class that sets the VisualCustomFrame that holds the
	 * buttons with the Images on.
	 * 
	 * @param frame
	 */
	public VisualCustomListener(VisualCustomFrame frame)
	{
		this.frame = frame;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		WidgetButton button = (WidgetButton) e.getSource();

		// Gets the Object the Button represents
		object = button.getObject();


		File folder = new File("./resource/images/objectImages");

		ImageIcon image = GraphicalFunctions.userIconSelection(folder);

		if ( image != null )
		{
			frame.tempImageIcons.put(object.getClass(), image);
			frame.updateButtonImages();
		}
	}
}
