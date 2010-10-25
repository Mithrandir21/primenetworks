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
package graphics.GUI.objectView.Software.EditSoftware.EditViews;


import graphics.GraphicalFunctions;
import graphics.PrimeMain;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Software.SoftwareView;
import graphics.GUI.objectView.Software.EditSoftware.EditOverview.SoftwareEditor;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import managment.DesktopSoftwareManagment;
import objects.Object;
import objects.Software;
import objects.softwareObjects.Backup;
import objects.softwareObjects.RemoteDesktop;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class RemoteDesktopEditView extends JPanel implements SoftwareView,
		ActionListener
{
	// The name of the software object
	private JTextField name = new JTextField(25);

	// The description of the software object.
	private JTextArea desc = new JTextArea(3, 40);

	// Supported Operating systems
	private JList supportedOS;

	// List of operating systems
	private String[] OSs;

	// Supports encryption
	private JCheckBox supEncryption;

	// Supports file transfer
	private JCheckBox supFileTransfer;

	// Supports audio
	private JCheckBox supAudio;

	// Supports Multiple sessions
	private JCheckBox supMultiSessions;

	// Supports seamless windows
	private JCheckBox supSeamlessWindows;



	private Object mainObj;

	private RemoteDesktop remDeskSoft;


	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param remSoft
	 *            The {@link RemoteDesktop} software.
	 */
	public RemoteDesktopEditView(Object obj, RemoteDesktop remSoft)
	{
		mainObj = obj;
		remDeskSoft = remSoft;
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.WEST; // location
		c.insets = new Insets(10, 10, 5, 10); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column

		ImageIcon icon = PrimeMain.objectImageIcons.get(Backup.class);
		JPanel p1 = SoftwareEditor.GeneralInfo(remDeskSoft, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		JPanel p2 = createSpesificInfo(remDeskSoft);
		p2.setBorder(BorderFactory.createEtchedBorder());

		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1.0; // request any extra vertical space
		c.insets = new Insets(0, 10, 0, 10);
		this.add(p2, c);



		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel(
				PrimeMain.texts.getString("swTabRemoveSoftwaretText"));

		Button remove = new Button(
				PrimeMain.texts.getString("swTabRemoveSoftwareButtonLabel"));
		remove.addActionListener(this);
		remove.setActionCommand("removeSoft");

		buttons.add(label);
		buttons.add(remove);

		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0; // request any extra vertical space
		c.insets = new Insets(2, 10, 10, 10);
		this.add(buttons, c);
	}


	/**
	 * This method creates and returns a JPanel that contains all the different
	 * settings of the given Software object. It uses the
	 * {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param remSoft
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(RemoteDesktop remSoft)
	{
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		// c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTH; // location
		c.insets = new Insets(20, 20, 10, 10); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column

		// The supported operating systems by the NAS software.
		JLabel osLabel = new JLabel(
				PrimeMain.texts.getString("remDeskViewSupOSLabel"));
		osLabel.setToolTipText(PrimeMain.texts.getString("remDeskViewSupOSTip"));
		panel.add(osLabel, c);


		String[] osNames = DesktopSoftwareManagment.getSystemOSname();
		supportedOS = new JList(osNames);
		ListSelectionModel listSelectionModel = supportedOS.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(supportedOS);
		listPane.setMaximumSize(new Dimension(160, 60));
		listPane.setPreferredSize(new Dimension(160, 60));
		listSelectionModel
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		if ( remDeskSoft.getSupportedOperatingSystems() != null )
		{
			if ( remDeskSoft.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, osNames,
						remDeskSoft.getSupportedOperatingSystems()));
			}
		}

		c.gridx = 1; // column
		panel.add(listPane, c);


		// Whether or not the software supports Encryption
		supEncryption = new JCheckBox(
				PrimeMain.texts.getString("remDeskViewSupEncryptionLabel"));
		supEncryption.setToolTipText(PrimeMain.texts
				.getString("remDeskViewSupEncryptionTip"));
		supEncryption.setActionCommand("SupportsEncryption");
		supEncryption.addActionListener(this);

		supEncryption.setSelected(remDeskSoft.supportsEncryption());

		c.gridx = 2; // column
		panel.add(supEncryption, c);


		// Whether or not the software supports File Transfer
		supFileTransfer = new JCheckBox(
				PrimeMain.texts.getString("remDeskViewSupFileTransferLabel"));
		supFileTransfer.setToolTipText(PrimeMain.texts
				.getString("remDeskViewSupFileTransferTip"));
		supFileTransfer.setActionCommand("SupportsFileTransfer");
		supFileTransfer.addActionListener(this);

		supFileTransfer.setSelected(remDeskSoft.supportsFileTransfer());

		c.weightx = 1.0; // request any extra horizontal space
		c.gridx = 3; // column
		panel.add(supFileTransfer, c);


		// Whether or not the software supports Audio
		supAudio = new JCheckBox(
				PrimeMain.texts.getString("remDeskViewSupAudioLabel"));
		supAudio.setToolTipText(PrimeMain.texts
				.getString("remDeskViewSupAudioTip"));
		supAudio.setActionCommand("SupportsAudio");

		supAudio.setSelected(remDeskSoft.supportsAudio());


		c.insets = new Insets(10, 10, 10, 10); // padding
		c.gridy = 1; // row
		c.gridx = 0; // column
		panel.add(supAudio, c);



		// Whether or not the software supports Multi Sessions
		supMultiSessions = new JCheckBox(
				PrimeMain.texts.getString("remDeskViewSupMultiSessionsLabel"));
		supMultiSessions.setToolTipText(PrimeMain.texts
				.getString("remDeskViewSupMultiSessionsTip"));
		supMultiSessions.setActionCommand("SupportsMultiSessions");

		supMultiSessions.setSelected(remDeskSoft.supportsMultiSessions());

		c.gridx = 1; // column
		panel.add(supMultiSessions, c);



		// Whether or not the software supports Seamless Windows
		supSeamlessWindows = new JCheckBox(
				PrimeMain.texts.getString("remDeskViewSupSeamlessWindowsLabel"));
		supSeamlessWindows.setToolTipText(PrimeMain.texts
				.getString("remDeskViewSupSeamlessWindowsTip"));
		supSeamlessWindows.setActionCommand("SupportsSeamlessWindows");

		supSeamlessWindows.setSelected(remDeskSoft.supportsSeamlessWindows());

		c.weighty = 1.0; // request any extra horizontal space
		c.gridx = 2; // column
		panel.add(supSeamlessWindows, c);


		return panel;
	}



	@Override
	public void save()
	{
		if ( !name.getText().equals("") )
		{
			remDeskSoft.setObjectName(name.getText());
		}

		if ( !desc.getText().equals("") )
		{
			remDeskSoft.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			remDeskSoft.setSupportedOperatingSystems(OSs);
		}

		// Supports encryption
		remDeskSoft.setSupportsEncryption(supEncryption.isSelected());

		// Supports file transfer
		remDeskSoft.setSupportsFileTransfer(supFileTransfer.isSelected());

		// Supports audio
		remDeskSoft.setSupportsAudio(supAudio.isSelected());

		// Supports Multiple sessions
		remDeskSoft.setSupportsMultiSessions(supMultiSessions.isSelected());

		// Supports seamless windows
		remDeskSoft.setSupportsSeamlessWindows(supSeamlessWindows.isSelected());
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof Button )
		{
			Button check = (Button) e.getSource();

			String command = check.getActionCommand();

			if ( command.equals("removeSoft") )
			{
				DesktopSoftwareManagment.removeSoftware(mainObj, remDeskSoft);

				// Updates the views of the object to correctly show the current
				// info.
				ObjectView view = PrimeMain.getObjectView(mainObj);
				if ( view != null )
				{
					view.updateViewInfo();
				}
				// If no view is returned, then the standard object view is
				// open and that should be updated.
				else if ( PrimeMain.stdObjView != null )
				{
					PrimeMain.stdObjView.getSplitView().getObjView()
							.getSoftStdObjView().updateTabInfo();
				}
			}
		}
	}


	/**
	 * Handles the selections that are made in the "Supported Operating Systems"
	 * JList.
	 */
	private class SharedListSelectionHandler implements ListSelectionListener
	{
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.event.ListSelectionListener#valueChanged(javax.swing.
		 * event.ListSelectionEvent)
		 */
		public void valueChanged(ListSelectionEvent e)
		{
			int[] indeces = supportedOS.getSelectedIndices();

			if ( indeces.length == 0 )
			{
				OSs = null;
			}
			else
			{
				// Creates an array of strings with the length of the array with
				// the selected indices.
				OSs = new String[indeces.length];

				// Find out which indexes are selected.
				for ( int i = 0; i < indeces.length; i++ )
				{
					OSs[i] = (String) supportedOS.getSelectedValues()[i];
				}
			}
		}
	}


	@Override
	public Software getViewSoftware()
	{
		return remDeskSoft;
	}
}
