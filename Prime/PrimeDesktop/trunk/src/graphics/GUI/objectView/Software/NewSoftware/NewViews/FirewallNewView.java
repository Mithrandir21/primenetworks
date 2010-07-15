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

import managment.SoftwareManagment;
import objects.Object;
import objects.Software;
import objects.softwareObjects.Firewall;


public class FirewallNewView extends JDialog implements SoftwareView, ActionListener
{
	// The name of the software object
	JTextField name = new JTextField(25);

	// The description of the software object.
	JTextArea desc = new JTextArea(3, 40);

	// Supported Operating systems
	private JList supportedOS;

	// List of operating systems
	private String[] OSs;

	// Has network-layer firewall feature
	private JCheckBox hasNetworkFirewall;

	// Has stateful firewall feature
	private JCheckBox hasStatefulFirewall;

	// Has application-layer firewall feature
	private JCheckBox hasApplicationFirewall;

	// Has DPI, deep package inspections, firewall feature
	private JCheckBox hasDPI;



	// NON-FIREWALLING FEATURE
	// Has proxy feature
	private JCheckBox hasProxy;

	// Has NAT feature
	private JCheckBox hasNAT;

	// Has VPN feature
	private JCheckBox hasVPN;

	// Has antivirus feature
	private JCheckBox hasAntivirus;

	// Has IDS, Intrusion Detection System, feature
	private JCheckBox hasIDS;

	// DIFFERENT SUPPORT FEATURES
	// Supports Modularity, third-party modules to extend functionality
	private JCheckBox supportsModularity;

	// Supports IP version 6
	private JCheckBox supportsIPv6;

	// Supports TTL, Transparent to traceroute
	private JCheckBox supportsTTL;

	// Supports RWA, Reject-with-answer
	private JCheckBox supportsRWA;

	// Supports a DMZ, de-militarized zone
	private JCheckBox supportsDMZ;

	// Supports ToDFilter, Time of day filter
	private JCheckBox supportsToD;

	// Supports forwarding
	private JCheckBox supportsForwarding;

	// Supports port forwarding
	private JCheckBox supportsPortForwarding;

	// Supports QoS, quality of service
	private JCheckBox supportsQos;

	// Supports TP, tarpit
	private JCheckBox supportsTarpit;




	private Object mainObj;

	private Firewall mainFW;


	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param fw
	 *            The {@link Firewall Firewall} software.
	 */
	public FirewallNewView(Object obj, Firewall fw)
	{
		this.setTitle(PrimeMain.texts.getString("swNewFirewallLabel"));

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));

		mainObj = obj;
		mainFW = fw;
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

		ImageIcon icon = PrimeMain.objectImageIcons.get(Firewall.class);
		JPanel p1 = SoftwareEditor.GeneralInfo(mainFW, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		JPanel p2 = createSpesificInfo(mainFW);
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



		this.setMinimumSize(new Dimension((int) scrnsize.getWidth() / 3,
				(int) scrnsize.getHeight() / 3));
		this.setSize(width, height);
		this.setVisible(true);
	}


	/**
	 * This method creates and returns a JPanel that contains all the different
	 * settings of the given Software object. It uses the {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param fw
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(Firewall fw)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[20];


		labels[0] = new JLabel(PrimeMain.texts.getString("fwViewSupOSLabel"));
		labels[0].setToolTipText(PrimeMain.texts.getString("fwViewSupOSTip"));

		labels[1] = new JLabel(PrimeMain.texts
				.getString("fwViewNetworkFirewallLabel"));
		labels[1].setToolTipText(PrimeMain.texts
				.getString("fwViewNetworkFirewallTip"));

		labels[2] = new JLabel(PrimeMain.texts
				.getString("fwViewStatefulFirewallLabel"));
		labels[2].setToolTipText(PrimeMain.texts
				.getString("fwViewStatefulFirewallTip"));

		labels[3] = new JLabel(PrimeMain.texts
				.getString("fwViewApplicationFirewallLabel"));
		labels[3].setToolTipText(PrimeMain.texts
				.getString("fwViewApplicationFirewallTip"));

		labels[4] = new JLabel(PrimeMain.texts.getString("fwViewSupDPILabel"));
		labels[4].setToolTipText(PrimeMain.texts.getString("fwViewSupDPITip"));

		labels[5] = new JLabel(PrimeMain.texts.getString("fwViewHasProxyLabel"));
		labels[5]
				.setToolTipText(PrimeMain.texts.getString("fwViewHasProxyTip"));

		labels[6] = new JLabel(PrimeMain.texts.getString("fwViewHasNATLabel"));
		labels[6].setToolTipText(PrimeMain.texts.getString("fwViewHasNATTip"));

		labels[7] = new JLabel(PrimeMain.texts.getString("fwViewHasVPNLabel"));
		labels[7].setToolTipText(PrimeMain.texts.getString("fwViewHasVPNTip"));

		labels[8] = new JLabel(PrimeMain.texts
				.getString("fwViewHasAntivirusLabel"));
		labels[8].setToolTipText(PrimeMain.texts
				.getString("fwViewHasAntivirusTip"));

		labels[9] = new JLabel(PrimeMain.texts.getString("fwViewHasIDSLabel"));
		labels[9].setToolTipText(PrimeMain.texts.getString("fwViewHasIDSTip"));

		labels[10] = new JLabel(PrimeMain.texts
				.getString("fwViewSupModularityLabel"));
		labels[10].setToolTipText(PrimeMain.texts
				.getString("fwViewSupModularityTip"));

		labels[11] = new JLabel(PrimeMain.texts.getString("fwViewSupIPv6Label"));
		labels[11]
				.setToolTipText(PrimeMain.texts.getString("fwViewSupIPv6Tip"));

		labels[12] = new JLabel(PrimeMain.texts.getString("fwViewSupTTLLabel"));
		labels[12].setToolTipText(PrimeMain.texts.getString("fwViewSupTTLTip"));

		labels[13] = new JLabel(PrimeMain.texts.getString("fwViewSupRWALabel"));
		labels[13].setToolTipText(PrimeMain.texts.getString("fwViewSupRWATip"));

		labels[14] = new JLabel(PrimeMain.texts.getString("fwViewSupDMZLabel"));
		labels[14].setToolTipText(PrimeMain.texts.getString("fwViewSupDMZTip"));

		labels[15] = new JLabel(PrimeMain.texts
				.getString("fwViewSupToDFilterLabel"));
		labels[15].setToolTipText(PrimeMain.texts
				.getString("fwViewSupToDFilterTip"));

		labels[16] = new JLabel(PrimeMain.texts
				.getString("fwViewSupForwardingLabel"));
		labels[16].setToolTipText(PrimeMain.texts
				.getString("fwViewSupForwardingTip"));

		labels[17] = new JLabel(PrimeMain.texts
				.getString("fwViewSupPortForwardingLabel"));
		labels[17].setToolTipText(PrimeMain.texts
				.getString("fwViewSupPortForwardingTip"));

		labels[18] = new JLabel(PrimeMain.texts.getString("fwViewSupQoSLabel"));
		labels[18].setToolTipText(PrimeMain.texts.getString("fwViewSupQoSTip"));

		labels[19] = new JLabel(PrimeMain.texts
				.getString("fwViewSupTarpitLabel"));
		labels[19].setToolTipText(PrimeMain.texts
				.getString("fwViewSupTarpitTip"));


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

		if ( mainFW.getSupportedOperatingSystems() != null )
		{
			if ( mainFW.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, listData, mainFW
								.getSupportedOperatingSystems()));
			}
		}

		panel.add(labels[0]);
		panel.add(listPane);


		// Whether or not the software supports network firewall
		labels[1].setLabelFor(hasNetworkFirewall);
		hasNetworkFirewall = new JCheckBox();
		hasNetworkFirewall.setMaximumSize(tfSize);
		hasNetworkFirewall.setPreferredSize(tfSize);
		hasNetworkFirewall.setToolTipText(labels[1].getToolTipText());
		hasNetworkFirewall.setActionCommand("NetworkFW");
		hasNetworkFirewall.addActionListener(this);

		hasNetworkFirewall.setSelected(mainFW.HasNetworkFirewall());

		panel.add(labels[1]);
		panel.add(hasNetworkFirewall);


		// Whether or not the software supports stateful firewall
		labels[2].setLabelFor(hasStatefulFirewall);
		hasStatefulFirewall = new JCheckBox();
		hasStatefulFirewall.setMaximumSize(tfSize);
		hasStatefulFirewall.setPreferredSize(tfSize);
		hasStatefulFirewall.setToolTipText(labels[2].getToolTipText());
		hasStatefulFirewall.setActionCommand("StatefulFW");
		hasStatefulFirewall.addActionListener(this);

		hasStatefulFirewall.setSelected(mainFW.HasStatefulFirewall());

		panel.add(labels[2]);
		panel.add(hasStatefulFirewall);


		// Whether or not the software supports stateful firewall
		labels[3].setLabelFor(hasApplicationFirewall);
		hasApplicationFirewall = new JCheckBox();
		hasApplicationFirewall.setMaximumSize(tfSize);
		hasApplicationFirewall.setPreferredSize(tfSize);
		hasApplicationFirewall.setToolTipText(labels[3].getToolTipText());
		hasApplicationFirewall.setActionCommand("StatefulFW");
		hasApplicationFirewall.addActionListener(this);

		hasApplicationFirewall.setSelected(mainFW.HasApplicationFirewall());

		panel.add(labels[3]);
		panel.add(hasApplicationFirewall);



		// Whether or not the software supports DPI, Deep package inspection.
		labels[4].setLabelFor(hasDPI);
		hasDPI = new JCheckBox();
		hasDPI.setMaximumSize(tfSize);
		hasDPI.setPreferredSize(tfSize);
		hasDPI.setToolTipText(labels[4].getToolTipText());
		hasDPI.setActionCommand("HasDPI");
		hasDPI.addActionListener(this);

		hasDPI.setSelected(mainFW.HasDPI());

		panel.add(labels[4]);
		panel.add(hasDPI);



		// Whether or not the software has proxy feature
		labels[5].setLabelFor(hasProxy);
		hasProxy = new JCheckBox();
		hasProxy.setMaximumSize(tfSize);
		hasProxy.setPreferredSize(tfSize);
		hasProxy.setToolTipText(labels[5].getToolTipText());
		hasProxy.setActionCommand("HasProxy");
		hasProxy.addActionListener(this);

		hasProxy.setSelected(mainFW.HasProxy());

		panel.add(labels[5]);
		panel.add(hasProxy);


		// Whether or not the software has NAT feature
		labels[6].setLabelFor(hasNAT);
		hasNAT = new JCheckBox();
		hasNAT.setMaximumSize(tfSize);
		hasNAT.setPreferredSize(tfSize);
		hasNAT.setToolTipText(labels[6].getToolTipText());
		hasNAT.setActionCommand("HasNAT");
		hasNAT.addActionListener(this);

		hasNAT.setSelected(mainFW.HasNAT());

		panel.add(labels[6]);
		panel.add(hasNAT);


		// Whether or not the software has VPN feature
		labels[7].setLabelFor(hasVPN);
		hasVPN = new JCheckBox();
		hasVPN.setMaximumSize(tfSize);
		hasVPN.setPreferredSize(tfSize);
		hasVPN.setToolTipText(labels[7].getToolTipText());
		hasVPN.setActionCommand("HasVPN");
		hasVPN.addActionListener(this);

		hasVPN.setSelected(mainFW.HasVPN());

		panel.add(labels[7]);
		panel.add(hasVPN);


		// Whether or not the software has antivirus feature
		labels[8].setLabelFor(hasAntivirus);
		hasAntivirus = new JCheckBox();
		hasAntivirus.setMaximumSize(tfSize);
		hasAntivirus.setPreferredSize(tfSize);
		hasAntivirus.setToolTipText(labels[8].getToolTipText());
		hasAntivirus.setActionCommand("HasAV");
		hasAntivirus.addActionListener(this);

		hasAntivirus.setSelected(mainFW.HasAntivirus());

		panel.add(labels[8]);
		panel.add(hasAntivirus);


		// Whether or not the software has IDS, Intrusion Detection System,
		// feature
		labels[9].setLabelFor(hasIDS);
		hasIDS = new JCheckBox();
		hasIDS.setMaximumSize(tfSize);
		hasIDS.setPreferredSize(tfSize);
		hasIDS.setToolTipText(labels[9].getToolTipText());
		hasIDS.setActionCommand("HasIDS");
		hasIDS.addActionListener(this);

		hasIDS.setSelected(mainFW.HasIDS());

		panel.add(labels[9]);
		panel.add(hasIDS);



		// DIFFERENT SUPPORT FEATURES

		// Whether or not the software supports Modularity, third-party modules
		// to extend functionality
		labels[10].setLabelFor(supportsModularity);
		supportsModularity = new JCheckBox();
		supportsModularity.setMaximumSize(tfSize);
		supportsModularity.setPreferredSize(tfSize);
		supportsModularity.setToolTipText(labels[10].getToolTipText());
		supportsModularity.setActionCommand("SupportsModularity");
		supportsModularity.addActionListener(this);

		supportsModularity.setSelected(mainFW.SupportsModularity());

		panel.add(labels[10]);
		panel.add(supportsModularity);


		// Whether or not the software supports IP version 6
		labels[11].setLabelFor(supportsIPv6);
		supportsIPv6 = new JCheckBox();
		supportsIPv6.setMaximumSize(tfSize);
		supportsIPv6.setPreferredSize(tfSize);
		supportsIPv6.setToolTipText(labels[11].getToolTipText());
		supportsIPv6.setActionCommand("SupporsIPv6");
		supportsIPv6.addActionListener(this);

		supportsIPv6.setSelected(mainFW.SupportsIPv6());

		panel.add(labels[11]);
		panel.add(supportsIPv6);


		// Whether or not the software supports TTL, Transparent to traceroute
		labels[12].setLabelFor(supportsTTL);
		supportsTTL = new JCheckBox();
		supportsTTL.setMaximumSize(tfSize);
		supportsTTL.setPreferredSize(tfSize);
		supportsTTL.setToolTipText(labels[12].getToolTipText());
		supportsTTL.setActionCommand("SupportsTTL");
		supportsTTL.addActionListener(this);

		supportsTTL.setSelected(mainFW.SupportsTTL());

		panel.add(labels[12]);
		panel.add(supportsTTL);


		// Whether or not the software supports RWA, Reject-with-answer
		labels[13].setLabelFor(supportsRWA);
		supportsRWA = new JCheckBox();
		supportsRWA.setMaximumSize(tfSize);
		supportsRWA.setPreferredSize(tfSize);
		supportsRWA.setToolTipText(labels[13].getToolTipText());
		supportsRWA.setActionCommand("SupportsRWA");
		supportsRWA.addActionListener(this);

		supportsRWA.setSelected(mainFW.SupportsRWA());

		panel.add(labels[13]);
		panel.add(supportsRWA);


		// Whether or not the software supports a DMZ, de-militarized zone
		labels[14].setLabelFor(supportsDMZ);
		supportsDMZ = new JCheckBox();
		supportsDMZ.setMaximumSize(tfSize);
		supportsDMZ.setPreferredSize(tfSize);
		supportsDMZ.setToolTipText(labels[14].getToolTipText());
		supportsDMZ.setActionCommand("SupportsDMZ");
		supportsDMZ.addActionListener(this);

		supportsDMZ.setSelected(mainFW.SupportsDMZ());

		panel.add(labels[14]);
		panel.add(supportsDMZ);


		// Whether or not the software supports ToDFilter, Time of day filter
		labels[15].setLabelFor(supportsToD);
		supportsToD = new JCheckBox();
		supportsToD.setMaximumSize(tfSize);
		supportsToD.setPreferredSize(tfSize);
		supportsToD.setToolTipText(labels[15].getToolTipText());
		supportsToD.setActionCommand("SupportsToD");
		supportsToD.addActionListener(this);

		supportsToD.setSelected(mainFW.SupportsToD());

		panel.add(labels[15]);
		panel.add(supportsToD);


		// Whether or not the software supports forwarding
		labels[16].setLabelFor(supportsForwarding);
		supportsForwarding = new JCheckBox();
		supportsForwarding.setMaximumSize(tfSize);
		supportsForwarding.setPreferredSize(tfSize);
		supportsForwarding.setToolTipText(labels[16].getToolTipText());
		supportsForwarding.setActionCommand("SupportsForwarding");
		supportsForwarding.addActionListener(this);

		supportsForwarding.setSelected(mainFW.SupportsForwarding());

		panel.add(labels[16]);
		panel.add(supportsForwarding);


		// Whether or not the software supports port forwarding
		labels[17].setLabelFor(supportsPortForwarding);
		supportsPortForwarding = new JCheckBox();
		supportsPortForwarding.setMaximumSize(tfSize);
		supportsPortForwarding.setPreferredSize(tfSize);
		supportsPortForwarding.setToolTipText(labels[17].getToolTipText());
		supportsPortForwarding.setActionCommand("SupportsPortForwarding");
		supportsPortForwarding.addActionListener(this);

		supportsPortForwarding.setSelected(mainFW.SupportsPortForwarding());

		panel.add(labels[17]);
		panel.add(supportsPortForwarding);


		// Whether or not the software supports QoS, quality of service
		labels[18].setLabelFor(supportsQos);
		supportsQos = new JCheckBox();
		supportsQos.setMaximumSize(tfSize);
		supportsQos.setPreferredSize(tfSize);
		supportsQos.setToolTipText(labels[18].getToolTipText());
		supportsQos.setActionCommand("SupportsQoS");
		supportsQos.addActionListener(this);

		supportsQos.setSelected(mainFW.SupportsQos());

		panel.add(labels[18]);
		panel.add(supportsQos);


		// Whether or not the software supports TP, tarpit
		labels[19].setLabelFor(supportsTarpit);
		supportsTarpit = new JCheckBox();
		supportsTarpit.setMaximumSize(tfSize);
		supportsTarpit.setPreferredSize(tfSize);
		supportsTarpit.setToolTipText(labels[19].getToolTipText());
		supportsTarpit.setActionCommand("SupportsTP");
		supportsTarpit.addActionListener(this);

		supportsTarpit.setSelected(mainFW.SupportsTarpit());

		panel.add(labels[19]);
		panel.add(supportsTarpit);



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
	public void save()
	{
		if ( name.getText() != "" )
		{
			mainFW.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainFW.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainFW.setSupportedOperatingSystems(OSs);
		}

		mainFW.setHasNetworkFirewall(hasNetworkFirewall.isSelected());

		mainFW.setHasStatefulFirewall(hasStatefulFirewall.isSelected());

		mainFW.setHasApplicationFirewall(hasApplicationFirewall.isSelected());

		mainFW.setHasDPI(hasDPI.isSelected());


		mainFW.setHasProxy(hasProxy.isSelected());

		mainFW.setHasNAT(hasNAT.isSelected());

		mainFW.setHasVPN(hasVPN.isSelected());

		mainFW.setHasAntivirus(hasAntivirus.isSelected());

		mainFW.setHasIDS(hasIDS.isSelected());


		mainFW.setSupportsModularity(supportsModularity.isSelected());

		mainFW.setSupportsIPv6(supportsIPv6.isSelected());

		mainFW.setSupportsTTL(supportsTTL.isSelected());

		mainFW.setSupportsRWA(supportsRWA.isSelected());

		mainFW.setSupportsDMZ(supportsDMZ.isSelected());

		mainFW.setSupportsToD(supportsToD.isSelected());

		mainFW.setSupportsForwarding(supportsForwarding.isSelected());

		mainFW.setSupportsPortForwarding(supportsPortForwarding.isSelected());

		mainFW.setSupportsQos(supportsQos.isSelected());

		mainFW.setSupportsTarpit(supportsTarpit.isSelected());
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			// Saves the current values of the new motherboard.
			save();


			// Checks whether or not the software is compatible with the OS
			if ( SoftwareManagment.validateSoftware(mainFW, mainObj) )
			{
				// Sets an array with the newly added software object
				mainObj.setSoftware(SoftwareManagment.addSoftware(mainFW,
						mainObj));


				// Updates the views of the object to correctly show the
				// current info.
				ObjectView view = PrimeMain.getObjectView(mainObj);
				if ( view != null )
				{
					view.updateViewInfo();
				}


				// Closes the JFrame.
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, PrimeMain.texts
						.getString("swNewCompatibilityQuestion"));
			}

		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
		}

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
		return mainFW;
	}

}
