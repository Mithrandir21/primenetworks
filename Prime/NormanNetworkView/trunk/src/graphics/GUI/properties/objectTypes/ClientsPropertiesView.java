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
package graphics.GUI.properties.objectTypes;


import graphics.GraphicalFunctions;
import graphics.PrimeMain;
import graphics.GUI.properties.ObjectProperties;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import managment.DesktopCanvasManagment;
import objects.Clients;
import objects.Object;
import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.clientObjects.ThinClient;


/**
 * This class creates a properties JPanel for {@link Clients}.
 * 
 * @author Bahram Malaekeh
 */
public class ClientsPropertiesView extends AbstractObjectPropertiesView
		implements ActionListener
{

	// The client properties labels
	private JLabel name;


	private JComboBox comboBox = new JComboBox();


	private Clients objectView;


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public ClientsPropertiesView(Object obj)
	{
		super(obj);
		if ( obj instanceof Clients )
		{
			objectView = (Clients) obj;

			SaveKey key = new SaveKey();

			addSaveKeyListener(key);

			d.gridy = 1;
			this.add(getClientsPropertiesView(obj), d);


			d.weighty = 1.0; // request any extra vertical space
			d.gridy = 2;
			this.add(ObjectProperties.createButtons(this, false), d);
		}
	}

	/**
	 * This function populates the given JPanel with information about the the
	 * given Object.
	 * 
	 * @param panel
	 *            The JPanel that is to be populated.
	 * @param obj
	 *            The Object that is to populate the given JPanel.
	 */
	public JPanel getClientsPropertiesView(Object obj)
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


		JLabel label = null;

		if ( obj instanceof Desktop )
		{
			// Desktop Rate
			label = new JLabel(
					PrimeMain.texts.getString("propClientViewDesktopRateLabel"),
					SwingConstants.TRAILING);
			label.setToolTipText(PrimeMain.texts
					.getString("propClientViewDesktopRateTip"));
		}
		else if ( obj instanceof Laptop )
		{
			// Laptop Rate
			label = new JLabel(
					PrimeMain.texts.getString("propClientViewLaptopRateLabel"),
					SwingConstants.TRAILING);
			label.setToolTipText(PrimeMain.texts
					.getString("propClientViewLaptopRateTip"));
		}
		else if ( obj instanceof ThinClient )
		{
			// ThinClient Rate
			label = new JLabel(
					PrimeMain.texts
							.getString("propClientViewThinClientRateLabel"),
					SwingConstants.TRAILING);
			label.setToolTipText(PrimeMain.texts
					.getString("propClientViewThinClientRateTip"));
		}

		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(label, panelCons);



		String[] rates = new String[20];
		int temp = 5;

		for ( int i = 0; i < rates.length; i++ )
		{
			rates[i] = Integer.toString(temp);
			temp += 5;
		}


		comboBox.setModel(new DefaultComboBoxModel(rates));
		comboBox.setBackground(Color.white);
		comboBox.setEditable(false);
		comboBox.setName("Client Rates");

		Clients client = (Clients) obj;

		comboBox.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(rates,
				client.getClientRate()));

		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.weighty = 1.0; // request any extra vertical space
		panelCons.gridy++; // row
		panel.add(comboBox, panelCons);



		return panel;
	}


	/**
	 * Resets all specific fields to the info gotten from the {@link Object}
	 * viewed.
	 */
	private void resetFields()
	{
		resetGeneralFields();

		String[] rates = new String[20];
		int temp = 5;

		for ( int i = 0; i < rates.length; i++ )
		{
			rates[i] = Integer.toString(temp);
			temp += 5;
		}

		comboBox.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(rates,
				objectView.getClientRate()));
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
		 * 
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


		// The rate
		try
		{
			int theRate = Integer.parseInt(comboBox.getSelectedItem()
					.toString());
			objectView.setClientRate(theRate);
		}
		catch ( NumberFormatException e )
		{
			System.out.println("Number error: Parse"
					+ comboBox.getSelectedItem().toString()
					+ " --ClientPropertiesView");

			errorDuringSaving = true;
		}

		// If any errors occur during the saving process
		if ( !errorDuringSaving )
		{
			PrimeMain.updatePropertiesObjectArea(objectViewed, true);
		}


		DesktopCanvasManagment.canvasCleanUp(PrimeMain.currentCanvas);
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
