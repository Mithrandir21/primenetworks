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
package graphics.GUI.programGUI;


import graphics.PrimeMain;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * This extension of the JFrame class will display an 'about' text about the
 * program.
 * 
 * @author Bahram Malaekeh
 */
public class AboutFrame extends JFrame
{

	public AboutFrame()
	{
		super(PrimeMain.texts.getString("aboutTitle"));
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(300, 200);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;



		this.add(Box.createRigidArea(new Dimension(0, 10)));

		JLabel name = new JLabel(PrimeMain.texts.getString("aboutText"));
		name.setAlignmentX(0.5f);
		this.add(name);

		add(Box.createRigidArea(new Dimension(0, 100)));

		JButton close = new JButton(PrimeMain.texts.getString("closeButton"));
		close.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				dispose();
			}
		});

		close.setAlignmentX(0.5f);
		this.add(close);
		this.setLocation(initXLocation, initYLocation);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(300, 200);
		this.setResizable(false);
	}
}
