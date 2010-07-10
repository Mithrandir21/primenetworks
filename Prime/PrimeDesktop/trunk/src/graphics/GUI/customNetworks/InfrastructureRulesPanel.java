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
package graphics.GUI.customNetworks;


import graphics.PrimeMain;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import org.jdesktop.swingx.JXTaskPane;

import widgetManipulation.NetworkRules;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class InfrastructureRulesPanel extends JXTaskPane implements ActionListener
{
	/**
	 * Internet object allowed.
	 */
	public static JCheckBox internetAllowedBox;

	/**
	 * Firewall object allowed.
	 */
	public static JCheckBox FWbeforeInternet;

	/**
	 * Antivirus object allowed.
	 */
	public static JCheckBox AVbeforeInternet;

	/**
	 * Hub object allowed.
	 */
	public static JCheckBox hubAllowed;

	/**
	 * Wireless router object allowed.
	 */
	public static JCheckBox wirelessAllowed;



	/**
	 * TODO - Description NEEDED!
	 * 
	 */
	public InfrastructureRulesPanel(NetworkRules rules)
	{
		this.setTitle(PrimeMain.texts
				.getString("networkRulesInfrastructureTabLabel"));
		this.setSpecial(true);
		this.setCollapsed(false);


		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.NONE;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTHWEST; // location
		// d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column


		internetAllowedBox = new JCheckBox(PrimeMain.texts
				.getString("infraRulesInternetObjectLabel"), rules
				.isCanConnectToInternet());
		internetAllowedBox.setToolTipText(PrimeMain.texts
				.getString("infraRulesInternetObjectTip"));
		internetAllowedBox.addActionListener(this);

		this.add(internetAllowedBox, d);


		FWbeforeInternet = new JCheckBox(PrimeMain.texts
				.getString("infraRulesFWbeforeInternetLabel"), rules
				.isMustHaveFWbeforeInternet());
		FWbeforeInternet.setToolTipText(PrimeMain.texts
				.getString("infraRulesFWbeforeInternetTip"));
		FWbeforeInternet.addActionListener(this);
		FWbeforeInternet.setEnabled(false);

		d.gridy = 0; // row
		d.gridx = 1; // column
		this.add(FWbeforeInternet, d);


		AVbeforeInternet = new JCheckBox(PrimeMain.texts
				.getString("infraRulesAVbeforeInternetLabel"), rules
				.isMustHaveAVbeforeInternet());
		AVbeforeInternet.setToolTipText(PrimeMain.texts
				.getString("infraRulesAVbeforeInternetTip"));
		AVbeforeInternet.addActionListener(this);
		AVbeforeInternet.setEnabled(false);

		d.weightx = 1.0; // request any extra horizontal space
		d.gridy = 0; // row
		d.gridx = 2; // column
		this.add(AVbeforeInternet, d);


		hubAllowed = new JCheckBox(PrimeMain.texts
				.getString("infraRulesHubAllowedLabel"), rules
				.isCanContainHub());
		hubAllowed.setToolTipText(PrimeMain.texts
				.getString("infraRulesHubAllowedTip"));
		hubAllowed.addActionListener(this);

		d.weightx = 0; // request any extra horizontal space
		d.gridy = 1; // row
		d.gridx = 0; // column
		this.add(hubAllowed, d);


		wirelessAllowed = new JCheckBox(PrimeMain.texts
				.getString("infraRulesWirelessRouterAllowedLabel"), rules
				.isCanContainWirelessRouter());
		wirelessAllowed.setToolTipText(PrimeMain.texts
				.getString("infraRulesWirelessRouterAllowedTip"));
		wirelessAllowed.addActionListener(this);

		d.weighty = 1.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		d.gridy = 1; // row
		d.gridx = 1; // column
		this.add(wirelessAllowed, d);
	}



	@Override
	public void actionPerformed(ActionEvent e)
	{
		NetworkRulesFrame.changed = true;
	}
}
