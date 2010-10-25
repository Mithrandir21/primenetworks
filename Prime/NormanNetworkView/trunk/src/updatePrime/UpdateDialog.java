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
package updatePrime;


import graphics.PrimeMain;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * This {@link JDialog} extension will provide the user a small windows with
 * information about an update available for the application.
 * 
 * @author Bahram Malaekeh
 */
public class UpdateDialog extends JDialog implements ActionListener
{
	/**
	 * A constructor for the class that sets up the dimensions and panels.
	 */
	public UpdateDialog()
	{
		this.setTitle(PrimeMain.texts.getString("newVersionDialogLabel"));

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(300, 200);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;


		setupPanel();


		this.setPreferredSize(size);
		this.setLocation(initXLocation, initYLocation);
		this.setMinimumSize(size);
		this.setVisible(true);
		this.setResizable(false);
	}



	/**
	 * This method sets up the text shown to the user about the update and the
	 * buttons for downloading and canceling.
	 */
	private void setupPanel()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTH; // location
		c.insets = new Insets(10, 10, 10, 10); // padding
		c.gridwidth = 2; // 2 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column


		JTextArea newMsgArea = new JTextArea(
				PrimeMain.texts.getString("newVersionText"));
		newMsgArea.setEditable(false);
		newMsgArea.setLineWrap(true);
		newMsgArea.setWrapStyleWord(true);
		newMsgArea.setBorder(BorderFactory.createEtchedBorder());

		mainPanel.add(newMsgArea, c);



		JButton download = new JButton(PrimeMain.texts.getString("download"));
		download.setActionCommand("download");
		download.addActionListener(this);
		JButton cancel = new JButton(PrimeMain.texts.getString("cancel"));
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);


		c.weighty = 0; // request any extra vertical space
		c.weightx = 0; // request any extra horizontal space
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(download, c);

		c.gridx = 1;
		c.gridy = 1;
		mainPanel.add(cancel, c);



		this.add(mainPanel);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("download") )
		{
			PrimeDesktopUpdateService.openUpdateURL();
		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
		}
	}
}
