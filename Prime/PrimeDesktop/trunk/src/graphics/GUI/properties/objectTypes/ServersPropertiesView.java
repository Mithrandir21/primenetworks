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
package graphics.GUI.properties.objectTypes;


import graphics.PrimeMain;
import graphics.GUI.properties.ObjectProperties;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import objects.Object;
import objects.Servers;


/**
 * This class creates a properties JPanel for {@link Servers}.
 * 
 * @author Bahram Malaekeh
 */
public class ServersPropertiesView extends AbstractObjectPropertiesView implements ActionListener
{
	/**
	 * 
	 */
	private static JCheckBox supOnSiteAccessField = new JCheckBox();

	/**
	 * 
	 */
	private static JCheckBox supRemoteAccessField = new JCheckBox();

	/**
	 * 
	 */
	private static JTextField supRemoteAccProtoField = new JTextField(10);

	/**
	 * 
	 */
	private static JTextField mainSWnameField = new JTextField(10);


	private Servers objectViewed;

	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public ServersPropertiesView(Object obj)
	{
		super(obj);
		if ( obj instanceof Servers )
		{
			objectViewed = (Servers) obj;

			nameField.addKeyListener(new SaveKey());

			d.gridy = 1;
			this.add(getServersPropertiesView(obj), d);


			d.weighty = 1.0; // request any extra vertical space
			d.gridy = 2;
			this.add(ObjectProperties.createButtons(this, false), d);
		}
	}

	/**
	 * This function populates a returned JPanel with server information about
	 * the the given Object.
	 * 
	 * @param obj
	 */
	public static JPanel getServersPropertiesView(Object obj)
	{
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints panelCons = new GridBagConstraints();

		panelCons.fill = GridBagConstraints.HORIZONTAL;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		panelCons.weightx = 0.8; // request any extra horizontal space
		panelCons.anchor = GridBagConstraints.NORTH; // location
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		panelCons.gridy = 0; // row
		panelCons.gridx = 0; // column


		Servers server = (Servers) obj;


		// Supports on-site-access
		JLabel supOnSiteAccessLabel = new JLabel(PrimeMain.texts
				.getString("propServerViewSupOnSiteAccessLabel"),
				SwingConstants.TRAILING);
		supOnSiteAccessLabel.setToolTipText(PrimeMain.texts
				.getString("propServerViewSupOnSiteAccessTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(supOnSiteAccessLabel, panelCons);


		supOnSiteAccessField.setSelected(server.supportsOnSiteAccess());
		supOnSiteAccessField.setName("supOnSiteAccess");
		supOnSiteAccessLabel.setLabelFor(supOnSiteAccessField);
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(supOnSiteAccessField, panelCons);


		// Supports Remote Access
		JLabel supRemoteAccessLabel = new JLabel(PrimeMain.texts
				.getString("propServerViewSupRemoteAccessLabel"),
				SwingConstants.TRAILING);
		supRemoteAccessLabel.setToolTipText(PrimeMain.texts
				.getString("propServerViewSupRemoteAccessTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(supRemoteAccessLabel, panelCons);


		supRemoteAccessField.setSelected(server.supportsRemoteAccess());
		supRemoteAccessField.setName("supRemoteAccess");
		supRemoteAccessLabel.setLabelFor(supRemoteAccessField);
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(supRemoteAccessField, panelCons);


		// Index 2 - Supported Remote Access Protocols
		JLabel supRemoteAccProtoLabel = new JLabel(PrimeMain.texts
				.getString("propServerViewSupRemoteProtocolsLabel"),
				SwingConstants.TRAILING);
		supRemoteAccProtoLabel.setToolTipText(PrimeMain.texts
				.getString("propServerViewSupRemoteProtocolsTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(supRemoteAccProtoLabel, panelCons);


		supRemoteAccProtoField.setName("supRemoteAccProto");
		supRemoteAccProtoLabel.setLabelFor(supRemoteAccProtoField);
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(supRemoteAccProtoField, panelCons);


		// Index 3 - Software name
		JLabel mainSWnameLabel = new JLabel(PrimeMain.texts
				.getString("propServerViewSoftwareNameLabel"),
				SwingConstants.TRAILING);
		mainSWnameLabel.setToolTipText(PrimeMain.texts
				.getString("propServerViewSoftwareNameTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(mainSWnameLabel, panelCons);


		mainSWnameField.setName("Main SW Name");
		mainSWnameLabel.setLabelFor(mainSWnameField);
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(mainSWnameField, panelCons);


		return panel;
	}


	/**
	 * Resets all specific fields to the info gotten from the {@link Object} viewed.
	 */
	private void resetFields()
	{
		resetGeneralFields();

		supOnSiteAccessField.setSelected(objectViewed.supportsOnSiteAccess());

		supRemoteAccessField.setSelected(objectViewed.supportsRemoteAccess());
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @author Bahram Malaekeh
	 * 
	 */
	public class SaveKey extends KeyAdapter
	{
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			{
				if ( key == KeyEvent.VK_ENTER )
				{
					saveAction();
				}
			}
		}
	}



	/**
	 * TODO - Description
	 * 
	 */
	private void saveAction()
	{
		generalSaveAction();

		// The access fields
		objectViewed.setSupportsOnSiteAccess(supOnSiteAccessField.isSelected());

		objectViewed.setSupportsRemoteAccess(supRemoteAccessField.isSelected());


		// If any errors occur during the saving process
		if ( !errorDuringSaving )
		{
			PrimeMain.updatePropertiesObjectArea(objectViewed, true);
		}
	}



	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals(PrimeMain.texts.getString("reset")) )
		{
			resetFields();
		}
		else if ( e.getActionCommand()
				.equals(PrimeMain.texts.getString("save")) )
		{
			saveAction();
		}
	}
}
