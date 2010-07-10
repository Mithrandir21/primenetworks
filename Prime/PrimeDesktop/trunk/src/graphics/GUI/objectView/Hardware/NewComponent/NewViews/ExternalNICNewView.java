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
package graphics.GUI.objectView.Hardware.NewComponent.NewViews;


import exceptions.MotherboardNotFound;
import exceptions.ObjectNotFoundException;
import graphics.GraphicalFunctions;
import graphics.PrimeMain;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Hardware.HardwareViewInterface;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareEditor;

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
import javax.swing.JFrame;
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

import managment.ArrayManagment;
import managment.CanvasManagment;
import managment.ComponentsManagment;
import objects.Hardware;
import objects.Object;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import widgetManipulation.NetworkRules;
import widgets.WorkareaCanvas;
import connections.ConnectionUtils;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ExternalNICNewView extends JFrame implements HardwareViewInterface, ActionListener
{
	private JTextField name = new JTextField(25);

	private JTextArea desc = new JTextArea(3, 40);

	private JTextField producer = new JTextField(7);

	private JTextField MAC;

	private JComboBox conType;

	private JComboBox transferSpeed;

	private JComboBox protocol;

	private JList supStandards;

	private String[] standars;

	private JCheckBox supIPv6;


	private Object mainObj;

	private ExternalNetworksCard extNIC;



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 * @param NIC
	 */
	public ExternalNICNewView(Object obj, ExternalNetworksCard NIC)
	{
		super(PrimeMain.texts.getString("newHWnewExtNIClabel"));


		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));



		mainObj = obj;
		extNIC = NIC;
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

		ImageIcon icon = PrimeMain.objectImageIcons
				.get(ExternalNetworksCard.class);
		JPanel p1 = HardwareEditor.GeneralInfo(NIC, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel p2 = createSpesificInfo(NIC);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);


		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 0.01;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel buttons = createButtons();
		buttons.setBorder(BorderFactory.createEtchedBorder());

		this.add(buttons, c);




		this.setMinimumSize(new Dimension((int) scrnsize.getWidth() / 3,
				(int) scrnsize.getHeight() / 3));
		this.setSize(width, height);
		this.setVisible(true);
	}


	/**
	 * This method creates and returns a JPanel that contains all the different
	 * settings of the given Hardware object. It uses the {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param NIC
	 *            The Hardware that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(ExternalNetworksCard NIC)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[7];


		labels[0] = new JLabel(PrimeMain.texts
				.getString("extNICviewProducerLabel"));
		labels[0].setToolTipText(PrimeMain.texts
				.getString("extNICviewProcucerTip"));

		labels[1] = new JLabel(PrimeMain.texts.getString("extNICviewMACLabel"));
		labels[1].setToolTipText(PrimeMain.texts.getString("extNICviewMACTip"));

		labels[2] = new JLabel(PrimeMain.texts.getString("extNICviewTypeLabel"));
		labels[2]
				.setToolTipText(PrimeMain.texts.getString("extNICviewTypeTip"));

		labels[3] = new JLabel(PrimeMain.texts
				.getString("extNICviewSpeedLabel"));
		labels[3].setToolTipText(PrimeMain.texts
				.getString("extNICviewSpeedTip"));

		labels[4] = new JLabel(PrimeMain.texts
				.getString("extNICviewProtocolLabel"));
		labels[4].setToolTipText(PrimeMain.texts
				.getString("extNICviewProtocolTip"));

		labels[5] = new JLabel(PrimeMain.texts
				.getString("extNICviewSupportedStandarsLabel"));
		labels[5].setToolTipText(PrimeMain.texts
				.getString("extNICviewSupportedStandarsTip"));

		labels[6] = new JLabel(PrimeMain.texts
				.getString("extNICviewSupportsIPv6Label"));
		labels[6].setToolTipText(PrimeMain.texts
				.getString("extNICviewSupportsIPv6Tip"));


		Dimension tfSize = new Dimension(90, 20);


		// The producer
		labels[0].setLabelFor(producer);
		producer.setMaximumSize(tfSize);
		producer.setPreferredSize(tfSize);
		producer.setText(NIC.getProducer());
		producer.setToolTipText(labels[0].getToolTipText());

		panel.add(labels[0]);
		panel.add(producer);



		// The MAC address of the NIC
		labels[1].setLabelFor(MAC);
		MAC = new JTextField(7);
		MAC.setMaximumSize(tfSize);
		MAC.setPreferredSize(tfSize);
		MAC.setText(NIC.getMAC());
		MAC.setToolTipText(labels[1].getToolTipText());

		panel.add(labels[1]);
		panel.add(MAC);


		// The connection type supported by the NIC
		labels[2].setLabelFor(conType);
		String[] conTypeString = { ConnectionUtils.RJ45,
				ConnectionUtils.Wireless, ConnectionUtils.Coax,
				ConnectionUtils.Fiber };
		conType = new JComboBox(conTypeString);
		conType.setMaximumSize(tfSize);
		conType.setPreferredSize(tfSize);
		conType.setBackground(Color.WHITE);
		conType.setToolTipText(labels[2].getToolTipText());
		conType.setActionCommand("ConnectionType");
		conType.addActionListener(this);

		conType.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				conTypeString, NIC.getConnectionType()));


		panel.add(labels[2]);
		panel.add(conType);



		// The transfer speed supported by the NIC
		labels[3].setLabelFor(transferSpeed);
		String[] speedString = { "", "10", "100", "1000" };
		transferSpeed = new JComboBox(speedString);
		transferSpeed.setMaximumSize(tfSize);
		transferSpeed.setPreferredSize(tfSize);
		transferSpeed.setBackground(Color.WHITE);
		transferSpeed.setToolTipText(labels[3].getToolTipText());
		transferSpeed.setActionCommand("Speed");
		transferSpeed.addActionListener(this);

		transferSpeed.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				speedString, NIC.getSpeed()));


		panel.add(labels[3]);
		panel.add(transferSpeed);



		// The transfer speed supported by the NIC
		labels[4].setLabelFor(protocol);
		String[] protocolString = { "", "Ethernet", "Token Ring", "ATM" };
		protocol = new JComboBox(protocolString);
		protocol.setMaximumSize(tfSize);
		protocol.setPreferredSize(tfSize);
		protocol.setBackground(Color.WHITE);
		protocol.setToolTipText(labels[4].getToolTipText());
		protocol.setActionCommand("Protocol");
		protocol.addActionListener(this);

		protocol.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				protocolString, NIC.getProtocol()));


		panel.add(labels[4]);
		panel.add(protocol);


		// The 64 bit check box
		labels[6].setLabelFor(supIPv6);
		supIPv6 = new JCheckBox();
		supIPv6.setMaximumSize(tfSize);
		supIPv6.setPreferredSize(tfSize);
		supIPv6.setToolTipText(labels[6].getToolTipText());
		supIPv6.setActionCommand("IPv6");
		supIPv6.addActionListener(this);

		supIPv6.setSelected(NIC.getIPv6support());

		panel.add(labels[6]);
		panel.add(supIPv6);


		// The supported standards by the NIC.
		labels[5].setLabelFor(supStandards);
		String[] listData = { "802.3i", "802.3x", "802.3y", "802.3ab",
				"802.3an", "802.11a", "802.11b", "802.11g", "802.11n",
				"802.11y" };
		supStandards = new JList(listData);
		supStandards.setMaximumSize(new Dimension(90, 60));
		supStandards.setPreferredSize(new Dimension(90, 60));
		ListSelectionModel listSelectionModel = supStandards
				.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(supStandards);
		listPane.setMaximumSize(new Dimension(90, 60));
		listPane.setPreferredSize(new Dimension(90, 60));
		listSelectionModel
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		if ( NIC.getSupportedStandards() != null )
		{
			if ( NIC.getSupportedStandards().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supStandards, listData, NIC.getSupportedStandards()));
			}
		}

		panel.add(labels[5]);
		panel.add(listPane);


		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel,
				panel.getComponentCount(), // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad



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



	@Override
	public boolean save()
	{
		if ( name.getText() != "" )
		{
			extNIC.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			extNIC.setDescription(desc.getText());
		}

		extNIC.setProducer(producer.getText());

		extNIC.setMAC(MAC.getText());

		extNIC.setConnectionType(conType.getSelectedItem().toString());

		if ( transferSpeed.getSelectedItem().toString() != "" )
		{
			extNIC.setSpeed(Integer.parseInt(transferSpeed.getSelectedItem()
					.toString()));
		}
		else
		{
			extNIC.setSpeed(0);
		}


		extNIC.setSupportedConnectionInterfaces(protocol.getSelectedItem()
				.toString());

		if ( supStandards.getSelectedIndex() == -1 )
		{
			extNIC.setSupportedStandards(standars);
		}

		extNIC.setSupportsIPv6(supIPv6.isSelected());

		return true;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			WorkareaCanvas canvas = CanvasManagment.findCanvas(mainObj,
					PrimeMain.canvases);

			if ( canvas != null )
			{
				String errorMsg = "";

				boolean valid = false;

				try
				{
					Motherboard mbBoard = ComponentsManagment
							.getObjectMotherboard(mainObj);

					// Saves the current values of the new motherboard.
					save();

					if ( mainObj.isExemptedNetworkRules() )
					{
						valid = true;
					}
					else
					{
						// VALIDATES THE USB/LAN PORTS AGAINS THE NETWORK RULES

						NetworkRules rules = canvas.getRules();

						// USB
						// If USB is allowed and the maximum number of allowed ports is unlimited.
						if ( !rules.isUSBnotAllowed() )
						{
							valid = true;
						}
						else
						{
							errorMsg = PrimeMain.texts
									.getString("rulesUSBviolationMsg");
						}

						// If the usb part is validated
						if ( valid )
						{
							// Resets to test the LAN rule
							valid = false;

							if ( extNIC.getConnectionType().equals(
									ConnectionUtils.RJ45) )
							{
								// If LAN is allowed
								if ( !rules.isLANnotAllowed() )
								{
									int lanNICs = 0;

									try
									{
										// Gets all the InternalNetworksCard from the objects components array.
										Object[] internalNICs = ArrayManagment
												.getSpesificComponents(
														InternalNetworksCard.class,
														mainObj.getComponents(),
														mainObj.getComponents().length);

										if ( internalNICs != null )
										{
											// Goes through all the gotten nics
											for ( int i = 0; i < internalNICs.length; i++ )
											{
												if ( internalNICs[i] != null )
												{
													InternalNetworksCard nic = (InternalNetworksCard) internalNICs[i];
													// If the connection type is LAN(rj45)
													if ( nic
															.getConnectionType()
															.equals(
																	ConnectionUtils.RJ45) )
													{
														lanNICs++;
													}
												}
											}
										}
									}
									catch ( ObjectNotFoundException ex )
									{
										// NO INTERNAL NICS FOUND
									}

									/**
									 * If the number of integrated LAN ports, internal LAN(RJ45) NICs and the this 1(one) object
									 * adds
									 * up
									 * to equal or less then the number of allowed LANs.
									 */
									if ( rules.getLANportsAllowed() == -1
											|| (mbBoard.getMaxIntegLANs()
													+ lanNICs + 1) <= rules
													.getLANportsAllowed() )
									{
										valid = true;
									}
									else
									{
										errorMsg = PrimeMain.texts
												.getString("rulesNoMoreLANportsAllowed");
									}
								}
								else
								{
									errorMsg = PrimeMain.texts
											.getString("rulesLANnotAllowedMsg");
								}
							}
							else
							{
								valid = true;
							}
						}
					}

					if ( valid )
					{
						ComponentsManagment.processExternalNICmatch(mainObj,
								extNIC, this);

						// Updates the views of the object to correctly show the
						// current info.
						ObjectView view = PrimeMain.getObjectView(mainObj);
						if ( view != null )
						{
							view.updateViewInfo();
						}
						// If no view is returned, then the standard object view is open
						// and that should be updated.
						else if ( PrimeMain.stdObjView != null )
						{
							PrimeMain.stdObjView.getSplitView()
									.getHardStdObjView().updateTabInfo();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this, errorMsg,
								PrimeMain.texts.getString("error"),
								JOptionPane.ERROR_MESSAGE);
					}

					if ( valid )
					{
						// Closes the JFrame.
						this.dispose();
					}
				}
				catch ( MotherboardNotFound e2 )
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
		}
	}

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @author Bahram Malaekeh
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
			int[] indeces = supStandards.getSelectedIndices();

			if ( indeces.length == 0 )
			{
				standars = null;
			}
			else
			{
				// Creates an array of strings with the length of the array with
				// the selected indices.
				standars = new String[indeces.length];

				// Find out which indexes are selected.
				for ( int i = 0; i < indeces.length; i++ )
				{
					standars[i] = (String) supStandards.getSelectedValues()[i];
				}
			}
		}
	}

	@Override
	public boolean validateNecessaryData()
	{
		return true;
	}


	@Override
	public Hardware getViewHardware()
	{
		return extNIC;
	}
}
