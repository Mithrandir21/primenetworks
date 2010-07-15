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
import graphics.SystemFunctions;
import graphics.GUI.objectView.ObjectView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import managment.SoftwareManagment;
import objects.Object;
import objects.softwareObjects.OperatingSystem;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class osSelectionOverView extends JDialog implements ActionListener
{
	// The main object
	private Object mainObj;

	private JPanel mainPanel;



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public osSelectionOverView()
	{
		this.setTitle(PrimeMain.texts.getString("selectOSdialogTitle"));

		initDialog();
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public osSelectionOverView(Object obj)
	{
		this.setTitle(PrimeMain.texts.getString("selectOSdialogTitle"));

		mainObj = obj;

		initDialog();
	}




	/**
	 * Sets up the size, position and components of the JDialog.
	 */
	private void initDialog()
	{
		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(800, 560);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;


		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		// d.insets = new Insets(0, 0, 5, 0); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column

		mainPanel = populateDialog();
		this.add(mainPanel, d);


		this.setLocation(initXLocation, initYLocation);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setVisible(true);

		// Resets the osSelect when closed.
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{
				PrimeMain.osSelect = null;
			}
		});
	}



	/**
	 * TODO - Description
	 * 
	 */
	private JPanel populateDialog()
	{
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		// d.insets = new Insets(0, 0, 5, 0); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column




		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setLayout(new GridBagLayout());
		GridBagConstraints conPanel1 = new GridBagConstraints();

		conPanel1.fill = GridBagConstraints.BOTH;
		// conPanel1.ipady = 0; // reset to default
		// conPanel1.ipadx = 0; // reset to default
		// conPanel1.weighty = 1.0; // request any extra vertical space
		// conPanel1.weightx = 1.0; // request any extra horizontal space
		conPanel1.anchor = GridBagConstraints.NORTH; // location
		conPanel1.insets = new Insets(5, 5, 5, 5); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		conPanel1.gridy = 0; // row
		conPanel1.gridx = 0; // column


		panel1.add(getStandardOS(), conPanel1);


		// conPanel1.weighty = 0; // request any extra vertical space
		// conPanel1.weightx = 0; // request any extra horizontal space
		conPanel1.gridy = 1; // row
		panel1.add(new JSeparator(), conPanel1);

		conPanel1.weighty = 1.0; // request any extra vertical space
		conPanel1.weightx = 1.0; // request any extra horizontal space
		conPanel1.insets = new Insets(5, 5, 5, 5); // padding
		conPanel1.gridy = 2; // row
		panel1.add(getCustomOS(), conPanel1);




		JScrollPane scroll = new JScrollPane();
		// Increases how far the scroll bar scrolls on one step of a mouse wheel
		scroll.getVerticalScrollBar().setUnitIncrement(30);
		scroll.setViewportView(panel1);

		panel.add(scroll, d);



		d.fill = GridBagConstraints.HORIZONTAL;
		d.weighty = 0; // request any extra vertical space
		// d.weightx = 0; // request any extra horizontal space
		d.anchor = GridBagConstraints.SOUTHEAST; // location
		d.gridy = 1; // row
		d.gridx = 0; // column
		panel.add(getButtons(), d);

		return panel;
	}




	/**
	 * Gets the buttons for the operating systems overview.
	 */
	private JPanel getButtons()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.NONE;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.SOUTHEAST; // location
		d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column


		JButton newCustomOS = new JButton(PrimeMain.texts
				.getString("newCustomOSlabel"));
		newCustomOS.setActionCommand(PrimeMain.texts
				.getString("newCustomOSlabel"));
		newCustomOS.addActionListener(this);
		panel.add(newCustomOS, d);



		JButton cancel = new JButton(PrimeMain.texts.getString("cancel"));
		cancel.setActionCommand(PrimeMain.texts.getString("cancel"));
		cancel.addActionListener(this);
		d.weighty = 0; // request any extra vertical space
		d.weightx = 0; // request any extra horizontal space
		d.gridx = 1; // column
		panel.add(cancel, d);


		return panel;
	}




	/**
	 * TODO - Description
	 * 
	 */
	private JXTaskPaneContainer getStandardOS()
	{
		JXTaskPaneContainer tpc = new JXTaskPaneContainer();
		tpc.setBorder(javax.swing.BorderFactory
				.createTitledBorder(PrimeMain.texts
						.getString("borderTitleStandard")));

		if ( PrimeMain.system_standard_OS != null )
		{
			for ( int i = 0; i < PrimeMain.system_standard_OS.length; i++ )
			{
				if ( PrimeMain.system_standard_OS[i] != null )
				{
					tpc.add(getOSpane(PrimeMain.system_standard_OS[i], true,
							false));
				}
			}
		}

		return tpc;
	}


	/**
	 * TODO - Description
	 * 
	 */
	private JXTaskPaneContainer getCustomOS()
	{
		JXTaskPaneContainer tpc = new JXTaskPaneContainer();
		tpc.setBorder(javax.swing.BorderFactory
				.createTitledBorder(PrimeMain.texts
						.getString("borderTitleCustom")));


		if ( PrimeMain.system_custom_OS != null )
		{
			if ( !PrimeMain.system_custom_OS.isEmpty() )
			{
				// get an Iterator object for ArrayList using iterator() method.
				Iterator<OperatingSystem> itr = PrimeMain.system_custom_OS
						.iterator();

				while ( itr.hasNext() )
				{
					OperatingSystem os = itr.next();
					tpc.add(getOSpane(os, true, true));
				}
			}
		}

		return tpc;
	}



	/**
	 * TODO - Description
	 * 
	 */
	private JXTaskPane getOSpane(OperatingSystem os, boolean collapsed,
			boolean editable)
	{
		JXTaskPane pane = new JXTaskPane();
		pane.setTitle(os.getObjectName());
		pane.setSpecial(true);
		pane.setCollapsed(collapsed);


		pane.setLayout(new GridBagLayout());
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
		JList supportedFS = new JList(listData);
		JScrollPane listPane = new JScrollPane(supportedFS);
		listPane.setMaximumSize(new Dimension(160, 60));
		listPane.setPreferredSize(new Dimension(160, 60));
		supportedFS.setEnabled(false);

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
		JCheckBox encryptedFileSystem = new JCheckBox(PrimeMain.texts
				.getString("osViewSupEnctyptedFSLabel"));
		encryptedFileSystem.setToolTipText(PrimeMain.texts
				.getString("osViewSupEnctyptedFSTip"));
		encryptedFileSystem.setSelected(os.isEncryptedFileSystem());
		encryptedFileSystem.setEnabled(false);

		conPanel1.gridx = 2; // column
		panel1.add(encryptedFileSystem, conPanel1);



		// Whether or not the OS has a GUI
		JCheckBox hasGUI = new JCheckBox(PrimeMain.texts
				.getString("osViewSupEnctyptedFSLabel"));
		hasGUI.setToolTipText(PrimeMain.texts
				.getString("osViewSupEnctyptedFSTip"));
		hasGUI.setSelected(os.isHasGUI());
		hasGUI.setEnabled(false);

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
		JCheckBox is64bit = new JCheckBox(PrimeMain.texts
				.getString("osViewSupEnctyptedFSLabel"));
		is64bit.setToolTipText(PrimeMain.texts
				.getString("osViewSupEnctyptedFSTip"));
		is64bit.setSelected(os.isIs64bit());
		is64bit.setEnabled(false);

		conPanel2.gridx = 0; // column
		panel2.add(is64bit, conPanel2);


		d.gridy = 1;
		pane.add(panel2, d);





		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridBagLayout());
		GridBagConstraints conPanel3 = new GridBagConstraints();

		conPanel3.fill = GridBagConstraints.NONE;
		// conPanel3.ipady = 0; // reset to default
		// conPanel3.ipadx = 0; // reset to default
		// conPanel3.weighty = 1.0; // request any extra vertical space
		// conPanel3.weightx = 1.0; // request any extra horizontal space
		conPanel3.anchor = GridBagConstraints.NORTHWEST; // location
		conPanel3.insets = new Insets(10, 10, 10, 10); // padding
		// conPanel3.gridwidth = 1; // 2 row wide
		// conPanel3.gridheight = 1; // 2 columns wide
		conPanel3.gridy = 0; // row
		conPanel3.gridx = 0; // column


		// Desc
		JLabel descLabel = new JLabel(PrimeMain.texts
				.getString("swTabSWdescriptionLabel"));

		conPanel3.gridx = 0; // column
		panel3.add(descLabel, conPanel3);


		// Description
		JScrollPane descScroll = new JScrollPane();
		JTextArea desc = new JTextArea(5, 60);
		desc.setName("Description");
		desc.setText(os.getDescription());
		desc.setFont(descLabel.getFont());
		descScroll.setViewportView(desc);
		desc.setEditable(false);


		conPanel3.weighty = 1.0; // request any extra vertical space
		conPanel3.weightx = 1.0; // request any extra horizontal space
		conPanel3.gridy = 1; // row
		conPanel3.gridx = 0; // column
		panel3.add(descScroll, conPanel3);



		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.gridy = 2;
		pane.add(panel3, d);




		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridBagLayout());
		GridBagConstraints conPanel4 = new GridBagConstraints();

		conPanel4.fill = GridBagConstraints.NONE;
		// conPanel4.ipady = 0; // reset to default
		// conPanel4.ipadx = 0; // reset to default
		// conPanel4.weighty = 1.0; // request any extra vertical space
		// conPanel4.weightx = 1.0; // request any extra horizontal space
		conPanel4.anchor = GridBagConstraints.SOUTHEAST; // location
		conPanel4.insets = new Insets(5, 5, 5, 5); // padding
		// conPanel4.gridwidth = 1; // 2 row wide
		// conPanel4.gridheight = 1; // 2 columns wide


		if ( editable )
		{
			JButton edit = new JButton(PrimeMain.texts.getString("edit"));
			edit.setActionCommand("Edit" + os.getObjectName());
			edit.addActionListener(this);

			conPanel4.gridy = 0; // row
			conPanel4.gridx = 0; // column
			panel4.add(edit, conPanel4);
			conPanel4.gridx = 1;


			JButton remove = new JButton(PrimeMain.texts.getString("remove"));
			remove.setActionCommand("Remove" + os.getObjectName());
			remove.addActionListener(this);

			panel4.add(remove, conPanel4);
			conPanel4.gridx = 2;
		}


		if ( mainObj != null )
		{
			JButton install = new JButton(PrimeMain.texts.getString("install")
					+ " " + os.getObjectName());
			install.setActionCommand(os.getObjectName());
			install.addActionListener(this);

			conPanel4.insets = new Insets(5, 0, 5, 5); // padding
			conPanel4.gridy = 0;
			panel4.add(install, conPanel4);
		}




		d.anchor = GridBagConstraints.SOUTHEAST; // location
		d.gridy = 3;
		pane.add(panel4, d);


		return pane;
	}



	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof JButton )
		{
			if ( e.getActionCommand().equals(
					PrimeMain.texts.getString("cancel")) )
			{
				PrimeMain.osSelect = null;
				// Closes the JFrame.
				this.dispose();
			}
			else if ( e.getActionCommand().equals(
					PrimeMain.texts.getString("newCustomOSlabel")) )
			{
				new OSNewView();
			}
			else
			{
				// Determines whether the name is found as a standard OS
				boolean found = false;

				// If a the edit button has been pressed on one of the custom OS
				if ( !found )
				{
					if ( !PrimeMain.system_custom_OS.isEmpty() )
					{
						// get an Iterator object for ArrayList using iterator() method.
						Iterator<OperatingSystem> itr = PrimeMain.system_custom_OS
								.iterator();

						while ( itr.hasNext() && found == false )
						{
							OperatingSystem os = itr.next();

							if ( e.getActionCommand().equalsIgnoreCase(
									"Edit" + os.getObjectName()) )
							{
								new OSedit(os);

								found = true;
							}
						}
					}
				}



				// If a the remove button has been pressed on one of the custom OS
				if ( !found )
				{
					if ( !PrimeMain.system_custom_OS.isEmpty() )
					{
						// get an Iterator object for ArrayList using iterator() method.
						Iterator<OperatingSystem> itr = PrimeMain.system_custom_OS
								.iterator();

						while ( itr.hasNext() && found == false )
						{
							OperatingSystem os = itr.next();

							if ( e.getActionCommand().equalsIgnoreCase(
									"Remove" + os.getObjectName()) )
							{
								String question = PrimeMain.texts
										.getString("osRemovalConfirmationMsg");


								// Custom button text
								String[] options = {
										PrimeMain.texts.getString("yes"),
										PrimeMain.texts.getString("no") };


								int i = JOptionPane.showOptionDialog(null,
										question, PrimeMain.texts
												.getString("confirm"),
										JOptionPane.YES_NO_CANCEL_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										options, options[1]);

								// If the answer is yes
								if ( i == 0 )
								{
									PrimeMain.system_custom_OS.remove(os);

									found = true;
								}
							}
						}
					}
				}


				// If the OS was not found
				if ( !found )
				{
					// Determines whether the name name is found as a standard OS
					found = SystemFunctions.foundInStandardOS(e
							.getActionCommand());

					// If the OS was not found
					if ( found )
					{
						OperatingSystem newOS = (OperatingSystem) SystemFunctions
								.getStandardOS(e.getActionCommand()).clone();
						// Creates serial number
						newOS.createRandomLongSerial();

						// Sets an array with the newly added software object
						mainObj.setSoftware(SoftwareManagment.addSoftware(
								newOS, mainObj));
					}
				}


				// If a Custom OS install button pressed
				if ( !found )
				{
					found = SystemFunctions.foundInCustomOS(e
							.getActionCommand());

					// If the OS was not found
					if ( found )
					{
						OperatingSystem newOS = (OperatingSystem) SystemFunctions
								.getCustomOS(e.getActionCommand()).clone();
						// Creates serial number
						newOS.createRandomLongSerial();

						// Sets an array with the newly added software object
						mainObj.setSoftware(SoftwareManagment.addSoftware(
								newOS, mainObj));
					}
				}


				if ( found )
				{
					if ( mainObj != null )
					{
						// Updates the views of the object to correctly show the current info.
						ObjectView view = PrimeMain.getObjectView(mainObj);
						if ( view != null )
						{
							view.updateViewInfo();
						}
					}

					// Closes the JDialog
					this.dispose();
					PrimeMain.osSelect = null;
				}
			}
		}
	}

	/**
	 * Updates the list of {@link OperatingSystem} shown in this class.
	 */
	public void updateOSlist()
	{
		this.remove(mainPanel);

		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		// d.insets = new Insets(0, 0, 5, 0); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column

		mainPanel = populateDialog();
		this.add(mainPanel, d);

		mainPanel.revalidate();
		mainPanel.repaint();
		this.repaint();
	}
}
