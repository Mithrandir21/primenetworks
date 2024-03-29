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

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logistical.checkLogic;
import managment.DesktopFileManagment;
import managment.NetworkManagment;
import objects.Object;
import widgets.WorkareaCanvas;


/**
 * This class creates a properties JPanel containing general information about a
 * given {@link WorkareaCanvas} or given {@link Object}.
 * 
 * @author Bahram Malaekeh
 */
public class AbstractCanvasPropertiesView extends JPanel
{
	/**
	 * 
	 */
	public static JTextField nameField = new JTextField();

	/**
	 * 
	 */
	public static JTextField objectCountField = new JTextField();

	/**
	 * 
	 */
	public static JComboBox netmaskCombo = new JComboBox();

	/**
	 * 
	 */
	public static JTextField IPrangeStartField = new JTextField();

	/**
	 * 
	 */
	public static JTextField IPrangeEndField = new JTextField();

	/**
	 * 
	 */
	public static boolean errorDuringSaving = false;

	/**
	 * 
	 */
	public static GridBagConstraints d;


	public static WorkareaCanvas canvasViewed;



	/**
	 * A constructor for the class that takes a {@link WorkareaCanvas} as a
	 * parameter.
	 */
	public AbstractCanvasPropertiesView(WorkareaCanvas canvas)
	{
		if ( canvas != null )
		{
			canvasViewed = canvas;

			this.setLayout(new GridBagLayout());
			d = new GridBagConstraints();

			d.fill = GridBagConstraints.HORIZONTAL;
			// d.ipady = 0; // reset to default
			// d.ipadx = 0; // reset to default
			// d.weighty = 1.0; // request any extra vertical space
			d.weightx = 0.8; // request any extra horizontal space
			d.anchor = GridBagConstraints.NORTH; // location
			d.insets = new Insets(0, 0, 5, 0); // padding
			// d.gridwidth = 1; // 2 row wide
			// d.gridheight = 1; // 2 columns wide
			d.gridy = 0; // row
			d.gridx = 0; // column


			this.add(getGeneralCanvasProperties(canvas), d);
		}
	}


	/**
	 * Examines the given WorkareaCanvas and adds the canvas name and the number
	 * of objects on the given JPanel.
	 */
	public static JPanel getGeneralCanvasProperties(WorkareaCanvas canvas)
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


		// Name
		JLabel nameLabel = new JLabel(
				PrimeMain.texts.getString("propGeneralViewCanvasNameLabel"),
				SwingConstants.TRAILING);
		nameLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewCanvasNameTip"));
		panel.add(nameLabel, panelCons);


		nameField.setText(canvas.getCanvasName());
		// nameLabel.setLabelFor(nameField);
		nameField.setName("Name_Canvas");
		removeAllKeyListeners(nameField);
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(nameField, panelCons);



		// The number of objects on the network map.
		JLabel objectCountLabel = new JLabel(
				PrimeMain.texts.getString("propGeneralViewObjectsCountLabel"),
				SwingConstants.TRAILING);
		objectCountLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewObjectsCountTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(objectCountLabel, panelCons);

		objectCountField.setText("" + canvas.getNumberOfWidgetsOnTheScene());
		objectCountField.setEditable(false);
		removeAllKeyListeners(objectCountField);
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(objectCountField, panelCons);


		// The IP Mask of the Network
		JLabel netmaskLabel = new JLabel(
				PrimeMain.texts.getString("propGeneralViewNetmaskLabel"),
				SwingConstants.TRAILING);
		netmaskLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewNetmaskTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(netmaskLabel, panelCons);


		netmaskCombo.setName("Netmask");
		String[] netmasks = new String[] { "", "255.255.255.0", "255.255.0.0",
				"255.0.0.0" };
		netmaskCombo.setModel(new DefaultComboBoxModel(netmasks));
		netmaskCombo.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				netmasks, canvas.getNetworkInfo().getNetmask()));
		removeAllKeyListeners(netmaskCombo);
		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(netmaskCombo, panelCons);


		// The start of the IP range
		JLabel IPrangeStartLabel = new JLabel(
				PrimeMain.texts.getString("propGeneralViewIPStartsLabel"),
				SwingConstants.TRAILING);
		IPrangeStartLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewIPStartsTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(IPrangeStartLabel, panelCons);


		IPrangeStartField.setName("IP range start");
		removeAllKeyListeners(IPrangeStartField);
		String ipFrom = canvas.getNetworkInfo().getIpRangeFrom();
		if ( !(ipFrom == null) )
		{
			IPrangeStartField.setText(ipFrom);
		}

		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.gridy++; // row
		panel.add(IPrangeStartField, panelCons);


		// The start of the IP range
		JLabel IPrangeEndLabel = new JLabel(
				PrimeMain.texts.getString("propGeneralViewIPendsLabel"),
				SwingConstants.TRAILING);
		IPrangeEndLabel.setToolTipText(PrimeMain.texts
				.getString("propGeneralViewIPendsTip"));
		panelCons.insets = new Insets(0, 0, 5, 0); // padding
		panelCons.gridy++; // row
		panel.add(IPrangeEndLabel, panelCons);


		IPrangeEndField.setName("IP range end");
		removeAllKeyListeners(IPrangeEndField);
		String ipTo = canvas.getNetworkInfo().getIpRangeTo();
		if ( !(ipTo == null) )
		{
			IPrangeEndField.setText(ipTo);
		}

		panelCons.insets = new Insets(0, 0, 10, 0); // padding
		panelCons.weighty = 1.0; // request any extra vertical space
		panelCons.gridy++; // row
		panel.add(IPrangeEndField, panelCons);


		return panel;
	}



	/**
	 * Resets all general fields to the info gotten from the
	 * {@link WorkareaCanvas} viewed.
	 * It also resets the errorDuringSaving to false.
	 */
	public void resetGeneralFields()
	{
		nameField.setText(canvasViewed.getCanvasName());

		objectCountField.setText(""
				+ canvasViewed.getNumberOfWidgetsOnTheScene());


		String[] netmasks = new String[] { "", "255.255.255.0", "255.255.0.0",
				"255.0.0.0" };
		netmaskCombo.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				netmasks, canvasViewed.getNetworkInfo().getNetmask()));

		IPrangeStartField.setText(canvasViewed.getNetworkInfo()
				.getIpRangeFrom());

		IPrangeEndField.setText(canvasViewed.getNetworkInfo().getIpRangeTo());

		errorDuringSaving = false;
	}


	/**
	 * This function save, with the necessary checks and controls, the general
	 * {@link WorkareaCanvas} settings.
	 */
	public void generalSaveAction()
	{
		if ( canvasViewed != null )
		{
			// The netmask
			if ( netmaskCombo.getSelectedIndex() != -1 )
			{
				// Casts the selected index to a String
				String selected = netmaskCombo.getSelectedItem().toString();

				// If the selected index is not ""
				if ( !(selected.equals("")) )
				{
					// If the string gotten is not valid
					if ( !(canvasViewed.getNetworkInfo().setNetmask(selected)) )
					{
						JOptionPane.showMessageDialog(null,
								PrimeMain.texts.getString("netmaskNotValidMsg")
										+ "\"" + selected + "\".",
								PrimeMain.texts.getString("error"),
								JOptionPane.ERROR_MESSAGE);

						errorDuringSaving = true;
					}
				}
			}

			// The Canvas name
			String canvasName = nameField.getText();
			if ( !(canvasName.equals("")) )
			{
				if ( checkLogic.validateName(canvasName) )
				{
					// If the name of the currently selected
					// WorkareaCanvas is not the same as the
					// name of the name in the canvasName field.
					if ( !(canvasViewed.getCanvasName().equals(canvasName)) )
					{
						// No canvas was found with the name
						if ( !(DesktopFileManagment
								.fileWorkareaCanvasExist(canvasName)) )
						{
							PrimeMain.workTab.updateCanvasName(canvasViewed,
									canvasName);
						}
						else
						{
							JOptionPane
									.showMessageDialog(
											null,
											PrimeMain.texts
													.getString("canvasExistWithNameMsg")
													+ "\"" + canvasName + "\".",
											PrimeMain.texts.getString("error"),
											JOptionPane.ERROR_MESSAGE);

							errorDuringSaving = true;
						}
					}
				}
				else
				{
					JOptionPane
							.showMessageDialog(
									null,
									PrimeMain.texts
											.getString("actionChangeWidgetNameInvalidNameText"),
									PrimeMain.texts.getString("error"),
									JOptionPane.ERROR_MESSAGE);

					// Focuses on the JTextField
					nameField.requestFocusInWindow();

					errorDuringSaving = true;
				}
			}


			// The IP range for a WorkareaCanvas
			String IPrangeStart = null;
			String IPrangeEnd = null;

			// Sets the local IPrangeStart string that might be used later
			IPrangeStart = IPrangeStartField.getText();


			// Sets the local IPrangeEnd string that might be used later
			IPrangeEnd = IPrangeEndField.getText();



			// Testing and setting of the IP range
			// If none of the strings a null
			if ( !(IPrangeStart == null) )
			{
				if ( !(IPrangeEnd == null) )
				{
					// Checks whether or not the range between the two is valid
					try
					{
						if ( !(IPrangeStart.equals(""))
								&& !(IPrangeEnd.equals("")) )
						{
							if ( NetworkManagment.processRange(IPrangeStart,
									IPrangeEnd) )
							{
								if ( !(canvasViewed.getNetworkInfo()
										.setIpRangeFrom(IPrangeStart)) )
								{
									JOptionPane
											.showMessageDialog(
													null,
													PrimeMain.texts
															.getString("saveNetworkNotValidIPerrorMsg"),
													PrimeMain.texts
															.getString("error"),
													JOptionPane.ERROR_MESSAGE);

									// Updates the properties area
									PrimeMain.updatePropertiesCanvasArea(true);

									errorDuringSaving = true;
								}
								if ( !(canvasViewed.getNetworkInfo()
										.setIpRangeTo(IPrangeEnd)) )
								{

									JOptionPane
											.showMessageDialog(
													null,
													PrimeMain.texts
															.getString("saveNetworkNotValidIPerrorMsg"),
													PrimeMain.texts
															.getString("error"),
													JOptionPane.ERROR_MESSAGE);

									// Updates the properties area
									PrimeMain.updatePropertiesCanvasArea(true);

									errorDuringSaving = true;
								}
							}
						}
						else
						{
							if ( !(canvasViewed.getNetworkInfo()
									.setIpRangeFrom(IPrangeStart)) )
							{
								JOptionPane
										.showMessageDialog(
												null,
												PrimeMain.texts
														.getString("saveNetworkNotValidIPerrorMsg"),
												PrimeMain.texts
														.getString("error"),
												JOptionPane.ERROR_MESSAGE);

								// Updates the properties area
								PrimeMain.updatePropertiesCanvasArea(true);

								errorDuringSaving = true;
							}
							if ( !(canvasViewed.getNetworkInfo()
									.setIpRangeTo(IPrangeEnd)) )
							{
								JOptionPane
										.showMessageDialog(
												null,
												PrimeMain.texts
														.getString("saveNetworkNotValidIPerrorMsg"),
												PrimeMain.texts
														.getString("error"),
												JOptionPane.ERROR_MESSAGE);

								// Updates the properties area
								PrimeMain.updatePropertiesCanvasArea(true);

								errorDuringSaving = true;
							}
						}
					}
					catch ( Exception exp )
					{
						String output = exp.getMessage();

						JOptionPane.showMessageDialog(null, output,
								PrimeMain.texts.getString("error"),
								JOptionPane.ERROR_MESSAGE);

						// Updates the properties area
						PrimeMain.updatePropertiesCanvasArea(true);

						errorDuringSaving = true;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							PrimeMain.texts.getString("ipMustHaveEndRange"),
							PrimeMain.texts.getString("error"),
							JOptionPane.ERROR_MESSAGE);

					errorDuringSaving = true;

				}
			}
			else
			{
				if ( !(IPrangeEnd == null) )
				{
					JOptionPane.showMessageDialog(null,
							PrimeMain.texts.getString("ipMustHaveStartRange"),
							PrimeMain.texts.getString("error"),
							JOptionPane.ERROR_MESSAGE);

					errorDuringSaving = true;
				}
			}
		}
	}


	/**
	 * This method adds the given {@link KeyAdapter} to this classes fields.
	 */
	public void addSaveKeyListener(KeyAdapter adapter)
	{
		nameField.addKeyListener(adapter);

		objectCountField.addKeyListener(adapter);

		netmaskCombo.addKeyListener(adapter);

		IPrangeStartField.addKeyListener(adapter);

		IPrangeEndField.addKeyListener(adapter);
	}



	/**
	 * This method removes all, if any, {@link KeyListener KeyListeners} from
	 * the given {@link Component}.
	 */
	private static void removeAllKeyListeners(Component comp)
	{
		KeyListener[] keys = comp.getKeyListeners();

		if ( keys != null && keys.length > 0 )
		{
			for ( int i = 0; i < keys.length; i++ )
			{
				comp.removeKeyListener(keys[i]);
			}
		}
	}

}
