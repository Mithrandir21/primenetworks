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
package graphics.GUI.customOSviews;


import graphics.GraphicalFunctions;
import graphics.PrimeMain;
import graphics.GUI.objectView.Software.EditSoftware.EditOverview.SoftwareEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import managment.DesktopSoftwareManagment;
import objects.softwareObjects.OperatingSystem;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class OSedit extends JDialog implements ActionListener
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

	private OperatingSystem mainOS;



	public OSedit(OperatingSystem OS)
	{
		this.setTitle(PrimeMain.texts.getString("edit"));

		mainOS = OS;

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		Dimension size = new Dimension(760, 420);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;

		// Creates a new Standard Operating System
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.WEST; // location
		c.insets = new Insets(10, 10, 5, 10); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column

		ImageIcon icon = PrimeMain.objectImageIcons.get(OperatingSystem.class);
		JPanel p1 = SoftwareEditor.GeneralInfo(mainOS, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		JPanel p2 = createSpesificInfo(mainOS);
		p2.setBorder(BorderFactory.createEtchedBorder());

		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0; // request any extra vertical space
		c.insets = new Insets(0, 10, 10, 10);
		this.add(p2, c);


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
	 * @param OS
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(OperatingSystem os)
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

		conPanel1.gridx = 2; // column
		panel1.add(encryptedFileSystem, conPanel1);



		// Whether or not the OS has a GUI
		hasGUI = new JCheckBox(PrimeMain.texts
				.getString("osViewSupEnctyptedFSLabel"));
		hasGUI.setToolTipText(PrimeMain.texts
				.getString("osViewSupEnctyptedFSTip"));
		hasGUI.setSelected(os.isHasGUI());

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
		osVersion.setToolTipText(PrimeMain.texts.getString("osViewVersionTip"));

		conPanel2.gridx = 2; // column
		panel2.add(osVersion, conPanel2);

		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.gridy = 1;
		pane.add(panel2, d);



		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridBagLayout());
		GridBagConstraints conPanel3 = new GridBagConstraints();

		conPanel3.fill = GridBagConstraints.NONE;
		// conPanel4.ipady = 0; // reset to default
		// conPanel4.ipadx = 0; // reset to default
		// conPanel4.weighty = 1.0; // request any extra vertical space
		// conPanel4.weightx = 1.0; // request any extra horizontal space
		conPanel3.anchor = GridBagConstraints.SOUTHEAST; // location
		conPanel3.insets = new Insets(5, 5, 5, 5); // padding
		// conPanel4.gridwidth = 1; // 2 row wide
		// conPanel4.gridheight = 1; // 2 columns wide


		JButton save = new JButton(PrimeMain.texts.getString("saveOS"));
		save.setActionCommand(PrimeMain.texts.getString("saveOS"));
		save.addActionListener(this);

		conPanel3.gridy = 0; // row
		conPanel3.gridx = 0; // column
		panel3.add(save, conPanel3);


		JButton cancel = new JButton(PrimeMain.texts.getString("cancel"));
		cancel.setActionCommand(PrimeMain.texts.getString("cancel"));
		cancel.addActionListener(this);

		conPanel3.gridy = 0; // row
		conPanel3.gridx = 1; // column
		conPanel3.insets = new Insets(5, 0, 5, 5); // padding
		panel3.add(cancel, conPanel3);


		d.anchor = GridBagConstraints.SOUTHEAST; // location
		d.gridy = 2;
		pane.add(panel3, d);


		return pane;
	}




	public void save()
	{
		if ( !name.getText().equals("") )
		{
			mainOS.setObjectName(name.getText());
		}

		if ( !desc.getText().equals("") )
		{
			mainOS.setDescription(desc.getText());
		}

		if ( supportedFS.getSelectedIndex() != -1 )
		{
			mainOS.setFs(GraphicalFunctions.getFSInJList(supportedFS));
		}

		if ( !osVersion.getText().equals("") )
		{
			mainOS.setVersion(osVersion.getText());
		}

		mainOS.setEncryptedFileSystem(encryptedFileSystem.isSelected());

		mainOS.setHasGUI(hasGUI.isSelected());

		mainOS.setIs64bit(is64bit.isSelected());
	}


	/**
	 * Returns a boolean on whether or not the information in the fields are valid.
	 */
	public boolean validateCustomOS()
	{
		// Checks whether any filesystems have been selected
		if ( supportedFS.getSelectedIndex() == -1 )
		{
			JOptionPane.showMessageDialog(null, PrimeMain.texts
					.getString("osNoFSselectedMsg"), PrimeMain.texts
					.getString("error"), JOptionPane.ERROR_MESSAGE);

			return false;
		}


		// Checks to see whether the description is empty
		if ( desc.getText().equals("") || desc.getText().equals("") )
		{
			JOptionPane.showMessageDialog(null, PrimeMain.texts
					.getString("osNoDescriptionMsg"), PrimeMain.texts
					.getString("error"), JOptionPane.ERROR_MESSAGE);

			return false;
		}


		// Checks the standard OS names
		if ( DesktopSoftwareManagment.foundInStandardOS(name.getText()) )
		{
			JOptionPane.showMessageDialog(null, PrimeMain.texts
					.getString("osSameNameAsAstandardOS"), PrimeMain.texts
					.getString("error"), JOptionPane.ERROR_MESSAGE);

			return false;
		}


		// Checks the custom OS names with a check for matching serial numbers
		if ( !PrimeMain.system_custom_OS.isEmpty() )
		{
			// get an Iterator object for ArrayList using iterator() method.
			Iterator<OperatingSystem> itr = PrimeMain.system_custom_OS
					.iterator();

			while ( itr.hasNext() )
			{
				OperatingSystem os = itr.next();

				// If the name is the same and the Serial number is different, ie different Operating System
				if ( name.getText().equalsIgnoreCase(os.getObjectName())
						&& (os.getObjectSerial() != mainOS.getObjectSerial()) )
				{
					JOptionPane.showMessageDialog(null, PrimeMain.texts
							.getString("osSameNameAsAcustomOS"),
							PrimeMain.texts.getString("error"),
							JOptionPane.ERROR_MESSAGE);

					return false;
				}
			}
		}

		return true;
	}



	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals(PrimeMain.texts.getString("saveOS")) )
		{
			// If the OS is valid(errors will be given in the validation)
			if ( validateCustomOS() )
			{
				save();

				// Closes the JDialog
				this.dispose();
			}
		}
		else if ( e.getActionCommand().equals(
				PrimeMain.texts.getString("cancel")) )
		{
			// Closes the JDialog
			this.dispose();
		}
	}
}
