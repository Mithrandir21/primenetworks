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
public class InternalNICNewView extends JDialog implements
		HardwareViewInterface, ActionListener
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

	private InternalNetworksCard IntNIC;



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 * @param intNIC
	 */
	public InternalNICNewView(Object obj, InternalNetworksCard intNIC)
	{
		this.setTitle(PrimeMain.texts.getString("newHWnewIntNIClabel"));

		Dimension size = new Dimension(750, 600);

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		int initYLocation = (scrnsize.height - size.height) / 2;
		int initXLocation = (scrnsize.width - size.width) / 2;



		mainObj = obj;
		IntNIC = intNIC;
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

		ImageIcon icon = PrimeMain.objectImageIcons
				.get(InternalNetworksCard.class);
		JPanel p1 = HardwareEditor.GeneralInfo(intNIC, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		JPanel p2 = createSpesificInfo(IntNIC);
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
	 * settings of the given Hardware object. It uses the
	 * {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param NIC
	 *            The Hardware that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(InternalNetworksCard NIC)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[7];


		labels[0] = new JLabel(
				PrimeMain.texts.getString("intNICviewProducerLabel"));
		labels[0].setToolTipText(PrimeMain.texts
				.getString("intNICviewProcucerTip"));

		labels[1] = new JLabel(PrimeMain.texts.getString("intNICviewMACLabel"));
		labels[1].setToolTipText(PrimeMain.texts.getString("intNICviewMACTip"));

		labels[2] = new JLabel(PrimeMain.texts.getString("intNICviewTypeLabel"));
		labels[2]
				.setToolTipText(PrimeMain.texts.getString("intNICviewTypeTip"));

		labels[3] = new JLabel(
				PrimeMain.texts.getString("intNICviewSpeedLabel"));
		labels[3].setToolTipText(PrimeMain.texts
				.getString("intNICviewSpeedTip"));

		labels[4] = new JLabel(
				PrimeMain.texts.getString("intNICviewProtocolLabel"));
		labels[4].setToolTipText(PrimeMain.texts
				.getString("intNICviewProtocolTip"));

		labels[5] = new JLabel(
				PrimeMain.texts.getString("intNICviewSupportedStandarsLabel"));
		labels[5].setToolTipText(PrimeMain.texts
				.getString("intNICviewSupportedStandarsTip"));

		labels[6] = new JLabel(
				PrimeMain.texts.getString("intNICviewSupportsIPv6Label"));
		labels[6].setToolTipText(PrimeMain.texts
				.getString("intNICviewSupportsIPv6Tip"));


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
			IntNIC.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			IntNIC.setDescription(desc.getText());
		}

		IntNIC.setProducer(producer.getText());

		IntNIC.setMAC(MAC.getText());

		IntNIC.setType(conType.getSelectedItem().toString());


		if ( transferSpeed.getSelectedItem().toString() != "" )
		{
			IntNIC.setSpeed(Integer.parseInt(transferSpeed.getSelectedItem()
					.toString()));
		}
		else
		{
			IntNIC.setSpeed(0);
		}


		IntNIC.setSupportedConnectionInterfaces(protocol.getSelectedItem()
				.toString());

		if ( supStandards.getSelectedIndex() == -1 )
		{
			IntNIC.setSupportedStandards(standars);
		}

		IntNIC.setSupportsIPv6(supIPv6.isSelected());

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

					// Saves the current values of the new internal.
					save();

					// VALIDATES THE USB/LAN PORTS AGAINS THE NETWORK RULES

					NetworkRules rules = canvas.getRules();

					// Resets to test the LAN rule
					valid = false;

					if ( mainObj.isExemptedNetworkRules() )
					{
						valid = true;
					}
					else
					{
						if ( IntNIC.getConnectionType().equals(
								ConnectionUtils.RJ45) )
						{
							// If LAN is allowed
							if ( !rules.isLANnotAllowed() )
							{
								int lanNICs = 0;

								try
								{
									// Gets all the InternalNetworksCard from
									// the objects components array.
									Object[] externalNICs = ArrayManagment
											.getSpesificComponents(
													ExternalNetworksCard.class,
													mainObj.getComponents(),
													mainObj.getComponents().length);

									if ( externalNICs != null )
									{
										// Goes through all the gotten nics
										for ( int i = 0; i < externalNICs.length; i++ )
										{
											if ( externalNICs[i] != null )
											{
												ExternalNetworksCard nic = (ExternalNetworksCard) externalNICs[i];
												// If the connection type is
												// LAN(rj45)
												if ( nic.getConnectionType()
														.equals(ConnectionUtils.RJ45) )
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
								 * If the number of integrated LAN ports,
								 * internal LAN(RJ45) NICs and the this 1(one)
								 * object
								 * adds
								 * up
								 * to equal or less then the number of allowed
								 * LANs.
								 */
								if ( rules.getLANportsAllowed() == -1
										|| (mbBoard.getMaxIntegLANs() + lanNICs + 1) <= rules
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

					if ( valid )
					{
						ComponentsManagment.processInternalNICmatch(mainObj,
								IntNIC, this);


						// Updates the views of the object to correctly show the
						// current info.
						ObjectView view = PrimeMain.getObjectView(mainObj);
						if ( view != null )
						{
							view.updateViewInfo();
						}
						// If no view is returned, then the standard object view
						// is open
						// and that should be updated.
						else if ( PrimeMain.stdObjView != null )
						{
							PrimeMain.stdObjView.getSplitView().getObjView()
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
				catch ( MotherboardNotFound e1 )
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			// If there is no Canvas
			else
			{
				try
				{
					ComponentsManagment.processInternalNICmatch(mainObj,
							IntNIC, this);

					// Updates the views of the object to correctly show the
					// current info.
					ObjectView view = PrimeMain.getObjectView(mainObj);
					if ( view != null )
					{
						view.updateViewInfo();
					}
					// If no view is returned, then the standard object view is
					// open
					// and that should be updated.
					else if ( PrimeMain.stdObjView != null )
					{
						PrimeMain.stdObjView.getSplitView().getObjView()
								.getHardStdObjView().updateTabInfo();
					}

					// Closes the JFrame.
					this.dispose();
				}
				catch ( MotherboardNotFound e1 )
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		 * 
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
		return IntNIC;
	}
}
