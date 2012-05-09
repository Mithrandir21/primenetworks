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
import java.net.URI;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


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
		Dimension size = new Dimension(400, 300);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;



		this.add(Box.createRigidArea(new Dimension(0, 10)));

		JTextArea name = new JTextArea(PrimeMain.texts.getString("aboutHeader"));
		name.setEditable(false);
		name.setBackground(this.getBackground());
		name.setLineWrap(true);
		name.setWrapStyleWord(true);
		name.setAlignmentX(0.5f);
		this.add(name);

		add(Box.createRigidArea(new Dimension(0, 15)));

		JTextArea license = new JTextArea(
				PrimeMain.texts.getString("aboutLicenseText"));
		license.setEditable(false);
		license.setBackground(this.getBackground());
		license.setLineWrap(true);
		license.setWrapStyleWord(true);
		license.setAlignmentX(0.5f);
		this.add(license);

		JEditorPane gpl = new JEditorPane("text/html",
				"<a href='http://www.gnu.org/licenses/gpl.html'>GPLv3</a>");
		gpl.setEditable(false);
		gpl.setOpaque(false);
		gpl.setAlignmentX(0.5f);
		gpl.addHyperlinkListener(new HyperlinkListener()
		{
			public void hyperlinkUpdate(HyperlinkEvent hle)
			{
				if ( HyperlinkEvent.EventType.ACTIVATED.equals(hle
						.getEventType()) )
				{
					java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

					if ( !desktop.isSupported(java.awt.Desktop.Action.BROWSE) )
					{
						System.err
								.println("Desktop doesn't support the browse action (fatal)");
						System.exit(1);
					}

					try
					{
						URI uri = hle.getURL().toURI();
						desktop.browse(uri);
					}
					catch ( Exception e )
					{
						System.err.println(e.getMessage());
					}
				}
			}
		});

		this.add(gpl);



		add(Box.createRigidArea(new Dimension(0, 15)));

		JTextArea creator = new JTextArea(
				PrimeMain.texts.getString("aboutCreatorText"));
		creator.setEditable(false);
		creator.setBackground(this.getBackground());
		creator.setLineWrap(true);
		creator.setWrapStyleWord(true);
		creator.setAlignmentX(0.5f);
		this.add(creator);

		JLabel email = new JLabel("Bahram.malaekeh@gmail.com");
		email.setAlignmentX(0.5f);
		this.add(email);

		add(Box.createRigidArea(new Dimension(0, 50)));

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
		this.setSize(size);
		this.setResizable(false);
	}
}
