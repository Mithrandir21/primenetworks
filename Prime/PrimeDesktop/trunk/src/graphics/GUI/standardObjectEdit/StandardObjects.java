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
package graphics.GUI.standardObjectEdit;


import graphics.PrimeMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.border.Border;


/**
 * This JFrame will show a selection JScrollPane that show all the systems
 * Standard Objects and a JPanel that shows all components of the selected
 * standard object.
 * 
 * @author Bahram Malaekeh
 */
public class StandardObjects extends JDialog
{
	// A simple border that is gray
	Border grayline = BorderFactory.createLineBorder(Color.GRAY);

	// The JPanel that holds both the array of object buttons and the
	// visual standard object view.
	private StandardViewSpilt splitView;

	/**
	 * A constructor for this class that sets the JFrame name, size and the
	 * location of the top left corner of the frame. It also adds a new {@link StandardViewSpilt} to the JFrame.
	 */
	public StandardObjects()
	{
		this.setTitle(PrimeMain.texts.getString("standardObjectsFrameLabel"));

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(850, 550);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;



		splitView = new StandardViewSpilt();
		splitView.setBorder(grayline);

		this.add(splitView);




		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setLocation(initXLocation, initYLocation);
		this.setVisible(true);
		this.pack();


		// Resets the objectView object when closed.
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{
				// Removes the pointer to this Object in the system registry.
				PrimeMain.stdObjView = null;
			}
		});
	}



	/**
	 * Gets the pointer to the splitview tat holds the views for the standard
	 * Objects.
	 * 
	 * @return the splitView
	 */
	public StandardViewSpilt getSplitView()
	{
		return splitView;
	}
}
