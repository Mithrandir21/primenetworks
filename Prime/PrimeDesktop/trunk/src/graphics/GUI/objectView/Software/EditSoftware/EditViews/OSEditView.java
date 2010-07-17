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
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Software.SoftwareView;

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
import objects.softwareObjects.OperatingSystem;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link OperatingSystem OperatingSystem} Software. The
 * panel is made up of 3 JPanel ordered in a column. The first one contains the
 * name and description of the object. The second panel contains the specific
 * software options. The third panel contains the button that can remove the
 * software from the computer.
 * 
 * @author Bahram Malaekeh
 */
public class OSEditView extends JPanel implements SoftwareView, ActionListener
{
	// The name of the software object
	JTextField name = new JTextField(25);

	// The description of the software object.
	JTextArea desc = new JTextArea(3, 40);

	// Supported File systems
	private JList supportedFS;

	private String[] fsData;

	// Has encrypted filesystem
	private JCheckBox encryptedFileSystem;

	// Has GUI
	private JCheckBox hasGUI;

	// The OS is 64 bit
	private JCheckBox is64bit;

	// The version of the OS
	private JTextField osVersion = new JTextField();


	private Object mainObj;

	private OperatingSystem mainOS;


	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param OS
	 *            The {@link OperatingSystem OperatingSystem} software.
	 */
	public OSEditView(Object obj, OperatingSystem OS)
	{
		mainObj = obj;
		mainOS = OS;

		boolean nonEditable = DesktopSoftwareManagment.foundInStandardOS(OS
				.getObjectName());


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

		ImageIcon icon = PrimeMain.objectImageIcons.get(OperatingSystem.class);
		JPanel p1 = GeneralInfo(mainOS, icon, name, desc, nonEditable);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		JPanel p2 = createSpesificInfo(mainOS, nonEditable);
		p2.setBorder(BorderFactory.createEtchedBorder());

		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1.0; // request any extra vertical space
		c.insets = new Insets(0, 10, 0, 10);
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
		c.weighty = 0; // request any extra vertical space
		c.insets = new Insets(2, 10, 10, 10);
		this.add(buttons, c);
	}


	/**
	 * This method creates and returns a JPanel that contains all the different
	 * settings of the given Software object. It uses the {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param nonEditable
	 * 
	 * @param OS
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(OperatingSystem os, boolean nonEditable)
	{
		Dimension tfSize = new Dimension(100, 20);

		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.NONE;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTHWEST; // location
		d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column



		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		GridBagConstraints conPanel1 = new GridBagConstraints();

		conPanel1.fill = GridBagConstraints.NONE;
		// conPanel1.ipady = 0; // reset to default
		// conPanel1.ipadx = 0; // reset to default
		// conPanel1.weighty = 1.0; // request any extra vertical space
		// conPanel1.weightx = 1.0; // request any extra horizontal space
		conPanel1.anchor = GridBagConstraints.NORTHWEST; // location
		conPanel1.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		conPanel1.gridy = 0; // row
		conPanel1.gridx = 0; // column


		// The supported file system.
		JLabel fsLabel = new JLabel(PrimeMain.texts
				.getString("osViewSupFSLabel"));
		fsLabel.setToolTipText(PrimeMain.texts.getString("osViewSupFSTip"));

		panel1.add(fsLabel, conPanel1);


		String[] listData = GraphicalFunctions.getAllFs();
		supportedFS = new JList(listData);
		JScrollPane listPane = new JScrollPane(supportedFS);
		listPane.setMaximumSize(new Dimension(160, 60));
		listPane.setPreferredSize(new Dimension(160, 60));
		supportedFS
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		if ( nonEditable )
		{
			supportedFS.setEnabled(false);
		}

		if ( os.getFs() != null )
		{
			if ( os.getFs().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedFS, listData, os.getFs()));
			}
		}

		conPanel1.gridx = 1; // column
		panel1.add(listPane, conPanel1);



		// Whether or not the OS supports encrypted file system
		encryptedFileSystem = new JCheckBox(PrimeMain.texts
				.getString("osViewSupEnctyptedFSLabel"));
		encryptedFileSystem.setToolTipText(PrimeMain.texts
				.getString("osViewSupEnctyptedFSTip"));
		encryptedFileSystem.setSelected(os.isEncryptedFileSystem());
		if ( nonEditable )
		{
			encryptedFileSystem.setEnabled(false);
		}

		conPanel1.gridx = 2; // column
		panel1.add(encryptedFileSystem, conPanel1);



		// Whether or not the OS has a GUI
		hasGUI = new JCheckBox(PrimeMain.texts
				.getString("osViewSupEnctyptedFSLabel"));
		hasGUI.setToolTipText(PrimeMain.texts
				.getString("osViewSupEnctyptedFSTip"));
		hasGUI.setSelected(os.isHasGUI());
		if ( nonEditable )
		{
			hasGUI.setEnabled(false);
		}

		conPanel1.gridx = 3; // column
		panel1.add(hasGUI, conPanel1);


		pane.add(panel1, d);




		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		GridBagConstraints conPanel2 = new GridBagConstraints();

		conPanel2.fill = GridBagConstraints.NONE;
		// conPanel2.ipady = 0; // reset to default
		// conPanel2.ipadx = 0; // reset to default
		// conPanel2.weighty = 1.0; // request any extra vertical space
		// conPanel2.weightx = 1.0; // request any extra horizontal space
		conPanel2.anchor = GridBagConstraints.NORTHWEST; // location
		conPanel2.insets = new Insets(10, 10, 10, 10); // padding
		// conPanel2.gridwidth = 1; // 2 row wide
		// conPanel2.gridheight = 1; // 2 columns wide
		conPanel2.gridy = 0; // row
		conPanel2.gridx = 0; // column

		// The 64 bit check box
		is64bit = new JCheckBox(PrimeMain.texts
				.getString("osViewSupEnctyptedFSLabel"));
		is64bit.setToolTipText(PrimeMain.texts
				.getString("osViewSupEnctyptedFSTip"));
		is64bit.setSelected(os.isIs64bit());
		if ( nonEditable )
		{
			is64bit.setEnabled(false);
		}

		conPanel2.gridx = 0; // column
		panel2.add(is64bit, conPanel2);


		// The version of the OS
		JLabel versionLabel = new JLabel(PrimeMain.texts
				.getString("osViewVersionLabel"));
		conPanel2.gridx = 1; // column
		panel2.add(versionLabel, conPanel2);

		osVersion.setMaximumSize(tfSize);
		osVersion.setPreferredSize(tfSize);
		osVersion.setText(mainOS.getVersion());
		if ( nonEditable )
		{
			osVersion.setEditable(false);
		}
		osVersion.setToolTipText(PrimeMain.texts.getString("osViewVersionTip"));

		conPanel2.gridx = 2; // column
		panel2.add(osVersion, conPanel2);


		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.gridy = 1;
		pane.add(panel2, d);

		return pane;
	}



	@Override
	public void save()
	{
		if ( name.getText() != "" )
		{
			mainOS.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainOS.setDescription(desc.getText());
		}

		if ( supportedFS.getSelectedIndex() != -1 )
		{
			mainOS.setFs(GraphicalFunctions.getFSInJList(supportedFS));
		}

		if ( osVersion.getText() != "" )
		{
			mainOS.setVersion(osVersion.getText());
		}

		mainOS.setEncryptedFileSystem(encryptedFileSystem.isSelected());

		mainOS.setHasGUI(hasGUI.isSelected());

		mainOS.setIs64bit(is64bit.isSelected());
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
				int answer = JOptionPane.showConfirmDialog(this,
						PrimeMain.texts.getObject("osViewRemovalQuestionText"),
						PrimeMain.texts.getString("verify"),
						JOptionPane.YES_NO_OPTION);

				// If the user verifies the choice
				if ( answer == JOptionPane.YES_OPTION )
				{
					// Removes the OS from the software array of the main object
					DesktopSoftwareManagment.removeSoftware(mainObj, mainOS);

					// Process all changes to the software of the object
					SoftwareManagment.processAllChanges(mainObj);

					// Updates the views of the object to correctly show the
					// current info.
					ObjectView view = PrimeMain.getObjectView(mainObj);
					if ( view != null )
					{
						view.updateViewInfo();
					}
				}
			}
		}
	}


	/**
	 * Handles the selections that are made in the "Supported Operating Systems"
	 * JList.
	 */
	class SharedListSelectionHandler implements ListSelectionListener
	{
		/*
		 * (non-Javadoc)
		 * @see
		 * javax.swing.event.ListSelectionListener#valueChanged(javax.swing.
		 * event.ListSelectionEvent)
		 */
		public void valueChanged(ListSelectionEvent e)
		{
			int[] indeces = supportedFS.getSelectedIndices();

			if ( indeces.length == 0 )
			{
				fsData = null;
			}
			else
			{
				// Creates an array of strings with the length of the array with
				// the selected indices.
				fsData = new String[indeces.length];

				// Find out which indexes are selected.
				for ( int i = 0; i < indeces.length; i++ )
				{
					fsData[i] = (String) supportedFS.getSelectedValues()[i];
				}
			}
		}
	}


	@Override
	public Software getViewSoftware()
	{
		return mainOS;
	}



	/**
	 * Creates a JPanel that shows an Icon representing the hardware object and
	 * two fields with the name and description of the hardware object.
	 * 
	 * @param sw
	 *            The actual hardware object.
	 * @param icon
	 *            The Icon representing the hardware component.
	 * @param name
	 *            A JTextField that will contain the name of the object.
	 * @param desc
	 *            A JTextArea that holds the description of the object.
	 * @param nonEditable
	 * @return Returns the created JPanel with all the information about the
	 *         hardware object.
	 */
	public static JPanel GeneralInfo(Software sw, ImageIcon icon,
			JTextField name, JTextArea desc, boolean nonEditable)
	{
		JPanel genPanel = new JPanel();
		genPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		// c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTHWEST; // location
		c.insets = new Insets(10, 10, 10, 10); // padding
		// c.gridwidth = 1; // 2 row wide
		c.gridheight = 2; // 2 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column

		JLabel image = new JLabel(icon);
		genPanel.add(image, c);


		JLabel nameLabel = new JLabel(PrimeMain.texts
				.getString("swTabSWnameLabel"));
		c.gridheight = 1; // 2 columns wide
		c.gridx = 1;
		c.gridy = 0;
		genPanel.add(nameLabel, c);


		name.setName("Name");
		name.setText(sw.getObjectName());
		JLabel t = new JLabel();
		name.setFont(t.getFont());
		if ( nonEditable )
		{
			name.setEditable(false);
		}
		// name.setBorder(BorderFactory.createEmptyBorder());
		c.gridx = 2;
		c.gridy = 0;
		genPanel.add(name, c);



		JLabel descLabel = new JLabel(PrimeMain.texts
				.getString("swTabSWdescriptionLabel"));
		c.gridx = 1;
		c.gridy = 1;
		genPanel.add(descLabel, c);


		// Description
		JScrollPane descScroll = new JScrollPane();
		desc.setName("Description");
		desc.setText(sw.getDescription());
		desc.setFont(t.getFont());
		descScroll.setViewportView(desc);
		if ( nonEditable )
		{
			desc.setEditable(false);
		}

		c.weightx = 1.0; // request any extra horizontal space
		c.gridx = 2;
		c.gridy = 1;
		genPanel.add(descScroll, c);

		return genPanel;
	}

}
