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
package graphics.GUI.objectView.Software.NewSoftware.NewViews;


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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import managment.DesktopSoftwareManagment;
import managment.SoftwareManagment;
import objects.Object;
import objects.Software;
import objects.softwareObjects.Backup;


public class BackupNewView extends JDialog implements SoftwareView, ActionListener
{
	// The name of the software object
	private JTextField name = new JTextField(25);

	// The description of the software object.
	private JTextArea desc = new JTextArea(3, 40);

	// Supported Operating systems
	private JList supportedOS;

	// List of operating systems
	private String[] OSs;

	// The type of backup
	private JTextField backupType = new JTextField();

	// Whether or not the software can use compression
	private JCheckBox compression;

	// Whether or not the software can use encryption
	private JCheckBox encryption;

	// The number of copies keeps
	private JComboBox duplicate;



	private Object mainObj;

	private Backup mainBack;


	/**
	 * A constructor for this class that will create a new JFrame which will
	 * contain fields for the adjustment of the newly created Backup software
	 * that will, if saved, be added to the software list of the given Object.
	 * 
	 * @param obj
	 *            The Object that the given software will be added to, if saved.
	 * @param back
	 *            The Software object that can be adjusted and then saved.
	 */
	public BackupNewView(Object obj, Backup back)
	{
		this.setTitle(PrimeMain.texts.getString("swNewBackupLabel"));

		Dimension size = new Dimension(760, 600);

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		int initYLocation = (scrnsize.height - size.height) / 2;
		int initXLocation = (scrnsize.width - size.width) / 2;

		mainObj = obj;
		mainBack = back;
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
		JPanel p1 = SoftwareEditor.GeneralInfo(mainBack, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		JPanel p2 = createSpesificInfo(mainBack);
		p2.setBorder(BorderFactory.createEtchedBorder());

		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1.0; // request any extra vertical space
		c.insets = new Insets(0, 10, 0, 10);
		this.add(p2, c);



		JPanel buttons = createButtons();
		buttons.setBorder(BorderFactory.createEtchedBorder());

		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0; // request any extra vertical space
		c.insets = new Insets(2, 10, 10, 10);
		this.add(buttons, c);



		this.setLocation(initXLocation, initYLocation);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setVisible(true);
	}



	/**
	 * This method creates and returns a JPanel that contains all the different
	 * settings of the given Software object. It uses the {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param back
	 *            The software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(Backup back)
	{
		Dimension tfSize = new Dimension(100, 20);


		JLabel[] labels = new JLabel[5];

		labels[0] = new JLabel(PrimeMain.texts
				.getString("backupViewSupOSLabel"));
		labels[0].setToolTipText(PrimeMain.texts
				.getString("backupViewSupOSTip"));

		labels[1] = new JLabel(PrimeMain.texts.getString("backupViewTypeLabel"));
		labels[1]
				.setToolTipText(PrimeMain.texts.getString("backupViewTypeTip"));

		labels[2] = new JLabel(PrimeMain.texts
				.getString("backupViewSupCompressionLabel"));
		labels[2].setToolTipText(PrimeMain.texts
				.getString("backupViewSupCompressionTip"));

		labels[3] = new JLabel(PrimeMain.texts
				.getString("backupViewSupEncryptionLabel"));
		labels[3].setToolTipText(PrimeMain.texts
				.getString("backupViewSupEncryptionTip"));

		labels[4] = new JLabel(PrimeMain.texts
				.getString("backupViewDuplicatesLabel"));
		labels[4].setToolTipText(PrimeMain.texts
				.getString("backupViewDuplicatesTip"));



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


		// The supported operating systems by the Email software.
		labels[0].setLabelFor(supportedOS);
		panel.add(labels[0], c);


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

		if ( mainBack.getSupportedOperatingSystems() != null )
		{
			if ( mainBack.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, osNames, mainBack
								.getSupportedOperatingSystems()));
			}
		}

		c.insets = new Insets(20, 10, 10, 10); // padding
		c.gridx = 1; // column
		panel.add(listPane, c);



		// Whether or not the software can use compression
		c.gridx = 2; // column
		panel.add(labels[2], c);

		labels[2].setLabelFor(compression);
		compression = new JCheckBox();
		compression.setToolTipText(labels[2].getToolTipText());
		compression.setActionCommand("SupportsCompression");
		compression.addActionListener(this);

		compression.setSelected(mainBack.supportsCompression());

		c.gridx = 3; // column
		panel.add(compression, c);



		// Whether or not the software can use encryption
		c.gridx = 4; // column
		panel.add(labels[3], c);

		labels[3].setLabelFor(encryption);
		encryption = new JCheckBox();
		encryption.setToolTipText(labels[3].getToolTipText());
		encryption.setActionCommand("SupportsEncryption");
		encryption.addActionListener(this);

		encryption.setSelected(mainBack.supportsEncryption());

		c.weightx = 1.0; // request any extra horizontal space
		c.gridx = 5; // column
		panel.add(encryption, c);


		// The type of backuprow
		c.insets = new Insets(10, 20, 10, 10); // padding
		c.weightx = 0; // request any extra horizontal space
		c.gridy = 1; // row
		c.gridx = 0; // column
		panel.add(labels[1], c);

		labels[1].setLabelFor(backupType);
		backupType = new JTextField();
		backupType.setMaximumSize(tfSize);
		backupType.setPreferredSize(tfSize);
		backupType.setText(mainBack.getBackupType());
		backupType.setToolTipText(labels[1].getToolTipText());


		c.insets = new Insets(10, 10, 10, 10); // padding
		c.gridwidth = 1; // 1 row wide
		c.gridx = 1; // column
		panel.add(backupType, c);



		// The number of copies keeps
		c.gridx = 2; // column
		panel.add(labels[4], c);

		labels[4].setLabelFor(duplicate);
		String[] dupStrings = { "0", "1", "2", "3", "4", "5", "6", "7", "8" };
		duplicate = new JComboBox(dupStrings);
		duplicate.setBackground(Color.WHITE);
		duplicate.setToolTipText(labels[4].getToolTipText());
		duplicate.setActionCommand("Duplicates");
		duplicate.addActionListener(this);

		duplicate.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				dupStrings, back.getDuplicate()));


		c.weighty = 1.0; // request any extra horizontal space
		c.gridx = 3; // column
		panel.add(duplicate, c);



		return panel;
	}


	/**
	 * Creates a JPanel with two buttons that are listened for by
	 * actionlisteners.
	 */
	private JPanel createButtons()
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));


		Button save = new Button(PrimeMain.texts.getString("save"));
		save.addActionListener(this);
		save.setActionCommand("save");

		Button cancel = new Button(PrimeMain.texts.getString("cancel"));
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(cancel);

		return buttons;
	}




	/*
	 * (non-Javadoc)
	 * @see graphics.GUI.objectView.Software.SoftwareView#save()
	 */
	@Override
	public void save()
	{
		if ( name.getText() != "" )
		{
			mainBack.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainBack.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainBack.setSupportedOperatingSystems(OSs);
		}

		// The type of backup
		if ( backupType.getText() != "" )
		{
			mainBack.setBackupType(backupType.getText());
		}

		// Whether or not the software can use compression
		mainBack.setSupportsCompression(compression.isSelected());

		// Whether or not the software can use encryption
		mainBack.setSupportsEncryption(encryption.isSelected());


		if ( duplicate.getSelectedItem().toString() != "" )
		{
			// The number of copies keeps
			mainBack.setDuplicate(Integer.parseInt(duplicate.getSelectedItem()
					.toString()));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			// Saves the current values of the new motherboard.
			save();


			// Checks whether or not the software is compatible with the OS
			if ( SoftwareManagment.validateSoftware(mainBack, mainObj) )
			{
				// Sets an array with the newly added software object
				mainObj.setSoftware(SoftwareManagment.addSoftware(mainBack,
						mainObj));


				// Updates the views of the object to correctly show the
				// current info.
				ObjectView view = PrimeMain.getObjectView(mainObj);
				if ( view != null )
				{
					view.updateViewInfo();
				}


				// Closes the JFrame.
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, PrimeMain.texts
						.getString("swNewCompatibilityQuestion"));
			}



		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
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
		return mainBack;
	}
}
