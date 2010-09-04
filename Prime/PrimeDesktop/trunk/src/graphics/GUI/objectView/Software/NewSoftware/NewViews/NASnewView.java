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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import managment.DesktopSoftwareManagment;
import managment.SoftwareManagment;
import objects.Object;
import objects.Software;
import objects.softwareObjects.NASsoftware;
import objects.softwareObjects.OfficeSuite;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class NASnewView extends JDialog implements SoftwareView, ActionListener
{
	// The name of the software object
	JTextField name = new JTextField(25);

	// The description of the software object.
	JTextArea desc = new JTextArea(3, 40);

	// Supported Operating systems
	private JList supportedOS;

	// List of operating systems
	private String[] OSs;

	// Supports RAID 5
	private JCheckBox supRaid5;

	// Supports encryption
	private JCheckBox supEncryption;

	// Supports S.M.A.R.T. monitoring
	private JCheckBox supSMARTmon;

	// Supports a web interface
	private JCheckBox supWebInterface;

	private Object mainObj;

	private NASsoftware nasSoft;



	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param nasSoftware
	 *            The {@link NASsoftware} software.
	 */
	public NASnewView(Object obj, NASsoftware nasSoftware)
	{
		this.setTitle(PrimeMain.texts.getString("swNewNASLabel"));

		Dimension size = new Dimension(750, 600);

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		int initYLocation = (scrnsize.height - size.height) / 2;
		int initXLocation = (scrnsize.width - size.width) / 2;

		mainObj = obj;
		nasSoft = nasSoftware;
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

		ImageIcon icon = PrimeMain.objectImageIcons.get(OfficeSuite.class);
		JPanel p1 = SoftwareEditor.GeneralInfo(nasSoft, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		JPanel p2 = createSpesificInfo(nasSoft);
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
	 * Creates the JPanel that will contain the {@link Software Software}
	 * specific options. The layout of the returned panel will
	 * be {@link SpringLayout}.
	 */
	private JPanel createSpesificInfo(NASsoftware nasSoftware)
	{
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		// c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTH; // location
		c.insets = new Insets(20, 10, 10, 10); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column


		// The supported operating systems by the Antivirus software.
		JLabel osLabel = new JLabel(
				PrimeMain.texts.getString("nasViewSupOSLabel"));
		osLabel.setToolTipText(PrimeMain.texts.getString("nasViewSupOSTip"));
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

		if ( nasSoft.getSupportedOperatingSystems() != null )
		{
			if ( nasSoft.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, osNames,
						nasSoft.getSupportedOperatingSystems()));
			}
		}

		c.gridx = 1; // column
		panel.add(listPane, c);



		// Whether or not the software supports RAID 5
		supRaid5 = new JCheckBox(
				PrimeMain.texts.getString("nasViewSupRaid5Label"));
		supRaid5.setToolTipText(PrimeMain.texts.getString("nasViewSupRaid5Tip"));
		supRaid5.setActionCommand("SupportsRAID5");
		supRaid5.addActionListener(this);

		supRaid5.setSelected(nasSoft.supportsRaid5());

		c.gridx = 2; // column
		panel.add(supRaid5, c);



		// Whether or not the software can use encryption
		supEncryption = new JCheckBox(
				PrimeMain.texts.getString("nasViewSupEncryptionLabel"));
		supEncryption.setToolTipText(PrimeMain.texts
				.getString("nasViewSupEncryptionTip"));
		supEncryption.setActionCommand("SupportsEncryption");
		supEncryption.addActionListener(this);

		supEncryption.setSelected(nasSoft.supportsEncryption());

		c.weightx = 1.0; // request any extra horizontal space
		c.gridx = 3; // column
		panel.add(supEncryption, c);


		// Whether or not the software supports Web interface
		supWebInterface = new JCheckBox(
				PrimeMain.texts.getString("nasViewSupWebInterfaceLabel"));
		supWebInterface.setToolTipText(PrimeMain.texts
				.getString("nasViewSupWebInterfaceTip"));
		supWebInterface.setActionCommand("SupportsWebInterface");

		supWebInterface.setSelected(nasSoft.supportsWebInterface());


		c.insets = new Insets(10, 10, 10, 10); // padding
		c.gridy = 1; // row
		c.gridx = 0; // column
		panel.add(supWebInterface, c);



		// Whether or not the software supports SMART monitoring
		supSMARTmon = new JCheckBox(
				PrimeMain.texts.getString("nasViewSupSMARTmonLabel"));
		supSMARTmon.setToolTipText(PrimeMain.texts
				.getString("nasViewSupSMARTmonTip"));
		supSMARTmon.setActionCommand("SupportsSMARTmon");

		supSMARTmon.setSelected(nasSoft.supportsSMARTmon());

		c.weighty = 1.0; // request any extra horizontal space
		c.gridx = 1; // column
		panel.add(supSMARTmon, c);




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


		Button save = new Button("Save");
		save.addActionListener(this);
		save.setActionCommand("save");

		Button cancel = new Button("Cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(cancel);

		return buttons;
	}




	@Override
	public void save()
	{
		if ( name.getText() != "" )
		{
			nasSoft.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			nasSoft.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			nasSoft.setSupportedOperatingSystems(OSs);
		}

		// Supports RAID 5
		nasSoft.setSupportsRaid5(supRaid5.isSelected());

		// Supports encryption
		nasSoft.setSupportsEncryption(supEncryption.isSelected());

		// Supports S.M.A.R.T. monitoring
		nasSoft.setSupportsSMARTmon(supSMARTmon.isSelected());

		// Supports a web interface
		nasSoft.setSupportsWebInterface(supWebInterface.isSelected());
	}



	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			// Saves the current values of the new motherboard.
			save();


			// Checks whether or not the software is compatible with the OS
			if ( SoftwareManagment.validateSoftware(nasSoft, mainObj) )
			{
				// Sets an array with the newly added software object
				mainObj.setSoftware(SoftwareManagment.addSoftware(nasSoft,
						mainObj));


				// Updates the views of the object to correctly show the
				// current info.
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


				// Closes the JFrame.
				this.dispose();
			}
			else
			{
				JOptionPane
						.showMessageDialog(this, PrimeMain.texts
								.getString("swNewCompatibilityQuestion"));
			}



		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
		}

	}


	@Override
	public Software getViewSoftware()
	{
		return nasSoft;
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
}
