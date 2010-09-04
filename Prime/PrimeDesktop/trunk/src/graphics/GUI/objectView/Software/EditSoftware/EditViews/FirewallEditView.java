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
package graphics.GUI.objectView.Software.EditSoftware.EditViews;


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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import managment.DesktopSoftwareManagment;
import objects.Object;
import objects.Software;
import objects.softwareObjects.Firewall;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link Firewall Firewall} Software. The panel is made up
 * of 3 JPanel ordered in a column. The first one contains the name and
 * description of the object. The second panel contains the specific software
 * options. The third panel contains the button that can remove the software
 * from the computer.
 * 
 * @author Bahram Malaekeh
 */
public class FirewallEditView extends JPanel implements SoftwareView,
		ActionListener
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
	public FirewallEditView(Object obj, Firewall fw)
	{
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



		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel(
				PrimeMain.texts.getString("swTabRemoveSoftwaretText"));

		Button remove = new Button(
				PrimeMain.texts.getString("swTabRemoveSoftwareButtonLabel"));
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
	 * settings of the given Software object. It uses the
	 * {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param fw
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(Firewall fw)
	{
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		// c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTHWEST; // location
		c.insets = new Insets(20, 10, 10, 10); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column

		// The supported operating systems by the Antivirus software.
		JLabel osLabel = new JLabel(
				PrimeMain.texts.getString("fwViewSupOSLabel"));
		osLabel.setToolTipText(PrimeMain.texts.getString("fwViewSupOSTip"));
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

		if ( mainFW.getSupportedOperatingSystems() != null )
		{
			if ( mainFW.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, osNames,
						mainFW.getSupportedOperatingSystems()));
			}
		}

		c.gridx = 1; // column
		panel.add(listPane, c);

		hasNetworkFirewall = new JCheckBox(
				PrimeMain.texts.getString("fwViewNetworkFirewallLabel"));
		hasNetworkFirewall.setToolTipText(PrimeMain.texts
				.getString("fwViewNetworkFirewallTip"));
		hasNetworkFirewall.setActionCommand("NetworkFW");
		hasNetworkFirewall.addActionListener(this);

		hasNetworkFirewall.setSelected(mainFW.HasNetworkFirewall());

		c.gridx = 2; // column
		panel.add(hasNetworkFirewall, c);


		// Whether or not the software supports stateful firewall
		hasStatefulFirewall = new JCheckBox(
				PrimeMain.texts.getString("fwViewStatefulFirewallLabel"));
		hasStatefulFirewall.setToolTipText(PrimeMain.texts
				.getString("fwViewStatefulFirewallTip"));
		hasStatefulFirewall.setActionCommand("StatefulFW");
		hasStatefulFirewall.addActionListener(this);

		hasStatefulFirewall.setSelected(mainFW.HasStatefulFirewall());

		c.weightx = 1.0; // request any extra horizontal space
		c.gridx = 3; // column
		panel.add(hasStatefulFirewall, c);


		// Whether or not the software supports stateful firewall
		hasApplicationFirewall = new JCheckBox(
				PrimeMain.texts.getString("fwViewApplicationFirewallLabel"));
		hasApplicationFirewall.setToolTipText(PrimeMain.texts
				.getString("fwViewApplicationFirewallTip"));
		hasApplicationFirewall.setActionCommand("StatefulFW");
		hasApplicationFirewall.addActionListener(this);

		hasApplicationFirewall.setSelected(mainFW.HasApplicationFirewall());

		c.insets = new Insets(10, 10, 10, 10); // padding
		c.gridwidth = 1; // 2 row wide
		c.weightx = 0; // request any extra horizontal space
		c.gridy = 1; // row
		c.gridx = 0; // column
		panel.add(hasApplicationFirewall, c);



		// Whether or not the software supports DPI, Deep package inspection.
		hasDPI = new JCheckBox(PrimeMain.texts.getString("fwViewSupDPILabel"));
		hasDPI.setToolTipText(PrimeMain.texts.getString("fwViewSupDPITip"));
		hasDPI.setActionCommand("HasDPI");
		hasDPI.addActionListener(this);

		hasDPI.setSelected(mainFW.HasDPI());

		c.gridx = 1; // column
		panel.add(hasDPI, c);



		// Whether or not the software has proxy feature
		hasProxy = new JCheckBox(
				PrimeMain.texts.getString("fwViewHasProxyLabel"));
		hasProxy.setToolTipText(PrimeMain.texts.getString("fwViewHasProxyTip"));
		hasProxy.setActionCommand("HasProxy");
		hasProxy.addActionListener(this);

		hasProxy.setSelected(mainFW.HasProxy());

		c.gridx = 2; // column
		panel.add(hasProxy, c);


		// Whether or not the software has NAT feature
		hasNAT = new JCheckBox(PrimeMain.texts.getString("fwViewHasNATLabel"));
		hasNAT.setToolTipText(PrimeMain.texts.getString("fwViewHasNATTip"));
		hasNAT.setActionCommand("HasNAT");
		hasNAT.addActionListener(this);

		hasNAT.setSelected(mainFW.HasNAT());

		c.gridx = 3; // column
		panel.add(hasNAT, c);


		// Whether or not the software has VPN feature
		hasVPN = new JCheckBox(PrimeMain.texts.getString("fwViewHasVPNLabel"));
		hasVPN.setToolTipText(PrimeMain.texts.getString("fwViewHasVPNTip"));
		hasVPN.setActionCommand("HasVPN");
		hasVPN.addActionListener(this);

		hasVPN.setSelected(mainFW.HasVPN());

		c.gridwidth = 1; // 2 row wide
		c.gridy = 2; // row
		c.gridx = 0; // column
		panel.add(hasVPN, c);


		// Whether or not the software has antivirus feature
		hasAntivirus = new JCheckBox(
				PrimeMain.texts.getString("fwViewHasAntivirusLabel"));
		hasAntivirus.setToolTipText(PrimeMain.texts
				.getString("fwViewHasAntivirusTip"));
		hasAntivirus.setActionCommand("HasAV");
		hasAntivirus.addActionListener(this);

		hasAntivirus.setSelected(mainFW.HasAntivirus());

		c.gridwidth = 1; // 1 row wide
		c.gridx = 1; // column
		panel.add(hasAntivirus, c);


		// Whether or not the software has IDS, Intrusion Detection System,
		// feature
		hasIDS = new JCheckBox(PrimeMain.texts.getString("fwViewHasIDSLabel"));
		hasIDS.setToolTipText(PrimeMain.texts.getString("fwViewHasIDSTip"));
		hasIDS.setActionCommand("HasIDS");
		hasIDS.addActionListener(this);

		hasIDS.setSelected(mainFW.HasIDS());

		c.gridx = 2; // column
		panel.add(hasIDS, c);



		// DIFFERENT SUPPORT FEATURES

		// Whether or not the software supports Modularity, third-party modules
		// to extend functionality
		supportsModularity = new JCheckBox(
				PrimeMain.texts.getString("fwViewSupModularityLabel"));
		supportsModularity.setToolTipText(PrimeMain.texts
				.getString("fwViewSupModularityTip"));
		supportsModularity.setActionCommand("SupportsModularity");
		supportsModularity.addActionListener(this);

		supportsModularity.setSelected(mainFW.SupportsModularity());

		c.gridx = 3; // column
		panel.add(supportsModularity, c);


		// Whether or not the software supports IP version 6
		supportsIPv6 = new JCheckBox(
				PrimeMain.texts.getString("fwViewSupIPv6Label"));
		supportsIPv6.setToolTipText(PrimeMain.texts
				.getString("fwViewSupIPv6Tip"));
		supportsIPv6.setActionCommand("SupporsIPv6");
		supportsIPv6.addActionListener(this);

		supportsIPv6.setSelected(mainFW.SupportsIPv6());

		c.gridy = 3; // row
		c.gridx = 0; // column
		panel.add(supportsIPv6, c);


		// Whether or not the software supports TTL, Transparent to traceroute
		supportsTTL = new JCheckBox(
				PrimeMain.texts.getString("fwViewSupTTLLabel"));
		supportsTTL
				.setToolTipText(PrimeMain.texts.getString("fwViewSupTTLTip"));
		supportsTTL.setActionCommand("SupportsTTL");
		supportsTTL.addActionListener(this);

		supportsTTL.setSelected(mainFW.SupportsTTL());

		c.gridx = 1; // column
		panel.add(supportsTTL, c);


		// Whether or not the software supports RWA, Reject-with-answer
		supportsRWA = new JCheckBox(
				PrimeMain.texts.getString("fwViewSupRWALabel"));
		supportsRWA
				.setToolTipText(PrimeMain.texts.getString("fwViewSupRWATip"));
		supportsRWA.setActionCommand("SupportsRWA");
		supportsRWA.addActionListener(this);

		supportsRWA.setSelected(mainFW.SupportsRWA());

		c.gridx = 2; // column
		panel.add(supportsRWA, c);


		// Whether or not the software supports a DMZ, de-militarized zone
		supportsDMZ = new JCheckBox(
				PrimeMain.texts.getString("fwViewSupDMZLabel"));
		supportsDMZ
				.setToolTipText(PrimeMain.texts.getString("fwViewSupDMZTip"));
		supportsDMZ.setActionCommand("SupportsDMZ");
		supportsDMZ.addActionListener(this);

		supportsDMZ.setSelected(mainFW.SupportsDMZ());

		c.gridx = 3; // column
		panel.add(supportsDMZ, c);


		// Whether or not the software supports ToDFilter, Time of day filter
		supportsToD = new JCheckBox(
				PrimeMain.texts.getString("fwViewSupToDFilterLabel"));
		supportsToD.setToolTipText(PrimeMain.texts
				.getString("fwViewSupToDFilterTip"));
		supportsToD.setActionCommand("SupportsToD");
		supportsToD.addActionListener(this);

		supportsToD.setSelected(mainFW.SupportsToD());

		c.gridy = 4; // row
		c.gridx = 0; // column
		panel.add(supportsToD, c);


		// Whether or not the software supports forwarding
		supportsForwarding = new JCheckBox(
				PrimeMain.texts.getString("fwViewSupForwardingLabel"));
		supportsForwarding.setToolTipText(PrimeMain.texts
				.getString("fwViewSupForwardingTip"));
		supportsForwarding.setActionCommand("SupportsForwarding");
		supportsForwarding.addActionListener(this);

		supportsForwarding.setSelected(mainFW.SupportsForwarding());

		c.gridx = 1; // column
		panel.add(supportsForwarding, c);


		// Whether or not the software supports port forwarding
		supportsPortForwarding = new JCheckBox(
				PrimeMain.texts.getString("fwViewSupPortForwardingLabel"));
		supportsPortForwarding.setToolTipText(PrimeMain.texts
				.getString("fwViewSupPortForwardingTip"));
		supportsPortForwarding.setActionCommand("SupportsPortForwarding");
		supportsPortForwarding.addActionListener(this);

		supportsPortForwarding.setSelected(mainFW.SupportsPortForwarding());

		c.gridx = 2; // column
		panel.add(supportsPortForwarding, c);


		// Whether or not the software supports QoS, quality of service
		supportsQos = new JCheckBox(
				PrimeMain.texts.getString("fwViewSupQoSLabel"));
		supportsQos
				.setToolTipText(PrimeMain.texts.getString("fwViewSupQoSTip"));
		supportsQos.setActionCommand("SupportsQoS");
		supportsQos.addActionListener(this);

		supportsQos.setSelected(mainFW.SupportsQos());

		c.gridx = 3; // column
		panel.add(supportsQos, c);


		// Whether or not the software supports TP, tarpit
		supportsTarpit = new JCheckBox(
				PrimeMain.texts.getString("fwViewSupTarpitLabel"));
		supportsTarpit.setToolTipText(PrimeMain.texts
				.getString("fwViewSupTarpitTip"));
		supportsTarpit.setActionCommand("SupportsTP");
		supportsTarpit.addActionListener(this);

		supportsTarpit.setSelected(mainFW.SupportsTarpit());

		c.weighty = 1.0; // request any extra horizontal space
		c.gridy = 5; // row
		c.gridx = 0; // column
		panel.add(supportsTarpit, c);


		return panel;
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
		if ( e.getSource() instanceof Button )
		{
			Button check = (Button) e.getSource();

			String command = check.getActionCommand();

			if ( command.equals("removeSoft") )
			{
				DesktopSoftwareManagment.removeSoftware(mainObj, mainFW);

				// Updates the views of the object to correctly show the current
				// info.
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
			}
		}
		else if ( e.getSource() instanceof JCheckBox )
		{
			JCheckBox box = (JCheckBox) e.getSource();

			String command = box.getActionCommand();

			if ( command.equals("NetworkFW") )
			{

			}
			else if ( command.equals("StatefulFW") )
			{

			}
			else if ( command.equals("HasDPI") )
			{

			}
			else if ( command.equals("HasProxy") )
			{

			}
			else if ( command.equals("HasNAT") )
			{

			}
			else if ( command.equals("HasVPN") )
			{

			}
			else if ( command.equals("HasAV") )
			{

			}
			else if ( command.equals("HasIDS") )
			{

			}
			else if ( command.equals("SupportsModularity") )
			{

			}
			else if ( command.equals("SupporsIPv6") )
			{

			}
			else if ( command.equals("SupportsTTL") )
			{

			}
			else if ( command.equals("SupportsTTL") )
			{

			}
			else if ( command.equals("SupportsRWA") )
			{

			}
			else if ( command.equals("SupportsDMZ") )
			{

			}
			else if ( command.equals("SupportsToD") )
			{

			}
			else if ( command.equals("SupportsForwarding") )
			{

			}
			else if ( command.equals("SupportsPortForwarding") )
			{

			}
			else if ( command.equals("SupportsQoS") )
			{

			}
			else if ( command.equals("SupportsTP") )
			{

			}
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


	@Override
	public Software getViewSoftware()
	{
		return mainFW;
	}

}
