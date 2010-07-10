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
package graphics.GUI.objectView.Software.EditSoftware.EditViews;


import graphics.GraphicalFunctions;
import graphics.PrimeMain;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import objects.Object;
import objects.Software;
import objects.softwareObjects.Backup;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link Backup Backup} Software. The panel is made up of 3
 * JPanel ordered in a column. The first one contains the name and description
 * of the object. The second panel contains the specific software options. The
 * third panel contains the button that can remove the software from the
 * computer.
 * 
 * @author Bahram Malaekeh
 */
public class BackupEditView extends JPanel implements SoftwareView, ActionListener
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
	private JTextField backupType;

	// Whether or not the software can use compression
	private JCheckBox compression;

	// Whether or not the software can use encryption
	private JCheckBox encryption;

	// The number of copies keeps
	private JComboBox duplicate;





	private Object mainObj;

	private Backup mainBack;


	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param back
	 *            The {@link Backup Backup} software.
	 */
	public BackupEditView(Object obj, Backup back)
	{
		mainObj = obj;
		mainBack = back;
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 5, 10);

		ImageIcon icon = PrimeMain.objectImageIcons.get(Backup.class);
		JPanel p1 = SoftwareEditor.GeneralInfo(mainBack, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(mainBack);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);



		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel(PrimeMain.texts
				.getString("swTabRemoveSoftwaretText"));

		Button remove = new Button(PrimeMain.texts
				.getString("swTabRemoveSoftwareButtonLabel"));
		remove.addActionListener(this);
		remove.setActionCommand("removeSoft");

		buttons.add(label);
		buttons.add(remove);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 0.01;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2, 10, 10, 10);

		this.add(buttons, c);
	}


	/**
	 * This method creates and returns a JPanel that contains all the different
	 * settings of the given Software object. It uses the {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param back
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(Backup back)
	{
		JPanel panel = new JPanel(new SpringLayout());
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


		Dimension tfSize = new Dimension(90, 20);


		// The supported operating systems by the Email software.
		labels[0].setLabelFor(supportedOS);
		String[] listData = { "Windows 98", "Windows 2000", "Windows XP",
				"Windows Vista", "Linux", "Novell" };
		supportedOS = new JList(listData);
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
						supportedOS, listData, mainBack
								.getSupportedOperatingSystems()));
			}
		}

		panel.add(labels[0]);
		panel.add(listPane);


		// Whether or not the software can use compression
		labels[2].setLabelFor(compression);
		compression = new JCheckBox();
		compression.setMaximumSize(tfSize);
		compression.setPreferredSize(tfSize);
		compression.setToolTipText(labels[2].getToolTipText());
		compression.setActionCommand("SupportsCompression");
		compression.addActionListener(this);

		compression.setSelected(mainBack.supportsCompression());

		panel.add(labels[2]);
		panel.add(compression);


		// Whether or not the software can use encryption
		labels[3].setLabelFor(encryption);
		encryption = new JCheckBox();
		encryption.setMaximumSize(tfSize);
		encryption.setPreferredSize(tfSize);
		encryption.setToolTipText(labels[3].getToolTipText());
		encryption.setActionCommand("SupportsEncryption");
		encryption.addActionListener(this);

		encryption.setSelected(mainBack.supportsEncryption());

		panel.add(labels[3]);
		panel.add(encryption);


		// The type of backup
		labels[1].setLabelFor(backupType);
		backupType = new JTextField();
		backupType.setMaximumSize(tfSize);
		backupType.setPreferredSize(tfSize);
		backupType.setText(mainBack.getBackupType());
		backupType.setToolTipText(labels[1].getToolTipText());


		panel.add(labels[1]);
		panel.add(backupType);


		// The number of copies keeps
		labels[4].setLabelFor(duplicate);
		String[] dupStrings = { "0", "1", "2", "3", "4", "5", "6", "7", "8" };
		duplicate = new JComboBox(dupStrings);
		duplicate.setMaximumSize(tfSize);
		duplicate.setPreferredSize(tfSize);
		duplicate.setBackground(Color.WHITE);
		duplicate.setToolTipText(labels[4].getToolTipText());
		duplicate.setActionCommand("Duplicates");
		duplicate.addActionListener(this);

		duplicate.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				dupStrings, back.getDuplicate()));


		panel.add(labels[4]);
		panel.add(duplicate);


		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel,
				panel.getComponentCount(), // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad


		return panel;
	}



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

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub

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
