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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXTaskPane;

import widgetManipulation.NetworkRules;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class SoftwareRulesPanel extends JXTaskPane implements ActionListener
{
	/**
	 * Operating System restriction on the network.
	 */
	public static JCheckBox OSrestrictionBox;

	/**
	 * The name of the Operating System restriction on the network.
	 */
	public static JTextField OSresName;


	/**
	 * Antivirus restriction on the network.
	 */
	public static JCheckBox AVrestrictionBox;

	/**
	 * The name of the Antivirus restriction on the network.
	 */
	public static JTextField AVresName;


	/**
	 * Firewall restriction on the network.
	 */
	public static JCheckBox FWrestrictionBox;

	/**
	 * The name of the Firewall restriction on the network.
	 */
	public static JTextField FWresName;


	/**
	 * Email restriction on the network.
	 */
	public static JCheckBox EmailRestrictionBox;

	/**
	 * The name of the Email restriction on the network.
	 */
	public static JTextField EmailResName;


	/**
	 * Office suite restriction on the network.
	 */
	public static JCheckBox OfficeSuiteRestrictionBox;

	/**
	 * The name of the Office suite restriction on the network.
	 */
	public static JTextField OfficeSuiteResName;




	/**
	 * TODO - Description NEEDED!
	 * 
	 */
	public SoftwareRulesPanel(NetworkRules rules)
	{
		this
				.setTitle(PrimeMain.texts
						.getString("networkRulesSoftwareTabLabel"));
		this.setSpecial(true);
		this.setCollapsed(true);


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


		// OS Restriction
		OSrestrictionBox = new JCheckBox(PrimeMain.texts
				.getString("softwareRulesOSrestrictionLabel"), rules
				.isOSrestriction());
		OSrestrictionBox.setToolTipText(PrimeMain.texts
				.getString("softwareRulesOSrestrictionTip"));

		OSresName = new JTextField(rules.getOSrestrictedName());
		OSresName.setBackground(Color.WHITE);

		this.add(createOptionPanel(OSrestrictionBox, PrimeMain.texts
				.getString("softwareRulesOSrestrictionNameLabel"), OSresName),
				d);



		// Antivirus Restriction
		AVrestrictionBox = new JCheckBox(PrimeMain.texts
				.getString("softwareRulesAVrestrictionLabel"), rules
				.isOSrestriction());
		AVrestrictionBox.setToolTipText(PrimeMain.texts
				.getString("softwareRulesAVrestrictionTip"));

		AVresName = new JTextField(rules.getAVrestrictedName());
		AVresName.setBackground(Color.WHITE);

		d.gridy = 0; // row
		d.gridx = 1; // column
		this.add(createOptionPanel(AVrestrictionBox, PrimeMain.texts
				.getString("softwareRulesAVrestrictionNameLabel"), AVresName),
				d);



		// Firewall Restriction
		FWrestrictionBox = new JCheckBox(PrimeMain.texts
				.getString("softwareRulesFWrestrictionLabel"), rules
				.isOSrestriction());
		FWrestrictionBox.setToolTipText(PrimeMain.texts
				.getString("softwareRulesFWrestrictionTip"));

		FWresName = new JTextField(rules.getAVrestrictedName());
		FWresName.setBackground(Color.WHITE);

		d.weightx = 1.0; // request any extra horizontal space
		d.gridy = 0; // row
		d.gridx = 2; // column
		this.add(createOptionPanel(FWrestrictionBox, PrimeMain.texts
				.getString("softwareRulesFWrestrictionNameLabel"), FWresName),
				d);



		// Email Restriction
		EmailRestrictionBox = new JCheckBox(PrimeMain.texts
				.getString("softwareRulesEMailRestrictionLabel"), rules
				.isOSrestriction());
		EmailRestrictionBox.setToolTipText(PrimeMain.texts
				.getString("softwareRulesEMailRestrictionTip"));

		EmailResName = new JTextField(rules.getAVrestrictedName());
		EmailResName.setBackground(Color.WHITE);

		d.weightx = 0; // request any extra horizontal space
		d.gridy = 1; // row
		d.gridx = 0; // column
		this.add(createOptionPanel(EmailRestrictionBox, PrimeMain.texts
				.getString("softwareRulesEMailRestrictionNameLabel"),
				EmailResName), d);



		// Office Suite Restriction
		OfficeSuiteRestrictionBox = new JCheckBox(PrimeMain.texts
				.getString("softwareRulesOfficeSuiteRestrictionLabel"), rules
				.isOSrestriction());
		OfficeSuiteRestrictionBox.setToolTipText(PrimeMain.texts
				.getString("softwareRulesOfficeSuiteRestrictionTip"));

		OfficeSuiteResName = new JTextField(rules.getAVrestrictedName());
		OfficeSuiteResName.setBackground(Color.WHITE);

		d.weighty = 1.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		d.gridy = 1; // row
		d.gridx = 1; // column
		this.add(createOptionPanel(OfficeSuiteRestrictionBox, PrimeMain.texts
				.getString("softwareRulesOfficeSuiteRestrictionNameLabel"),
				OfficeSuiteResName), d);
	}




	/**
	 * TODO - Description
	 * 
	 */
	private JPanel createOptionPanel(JCheckBox check, String comboString,
			JTextField field)
	{
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		d.insets = new Insets(5, 5, 0, 5); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column

		check.addActionListener(this);
		check.setEnabled(false);
		panel.add(check, d);


		JPanel numberPanel = new JPanel(new GridBagLayout());
		GridBagConstraints numberCons = new GridBagConstraints();
		numberPanel.setBorder(BorderFactory.createEtchedBorder());
		Dimension size = new Dimension(150, 50);
		numberPanel.setPreferredSize(size);
		numberPanel.setMinimumSize(size);

		numberCons.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		numberCons.anchor = GridBagConstraints.NORTH; // location
		numberCons.insets = new Insets(5, 5, 0, 5); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		numberCons.gridy = 0; // row
		numberCons.gridx = 0; // column

		numberPanel.add(new JLabel(comboString), numberCons);


		field.setEnabled(check.isSelected());

		numberCons.insets = new Insets(0, 5, 5, 5); // padding
		// numberCons.weighty = 1.0; // request any extra vertical space
		numberCons.weightx = 1.0; // request any extra horizontal space
		numberCons.gridy = 1; // row
		numberPanel.add(field, numberCons);



		d.insets = new Insets(0, 5, 0, 5); // padding
		d.gridy = 1; // row
		panel.add(numberPanel, d);

		return panel;
	}




	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource().equals(OSrestrictionBox) )
		{
			JCheckBox OS = (JCheckBox) e.getSource();

			OSresName.setEnabled(OS.isSelected());
		}
		else if ( e.getSource().equals(AVrestrictionBox) )
		{
			JCheckBox av = (JCheckBox) e.getSource();

			AVresName.setEnabled(av.isSelected());
		}
		else if ( e.getSource().equals(FWrestrictionBox) )
		{
			JCheckBox fw = (JCheckBox) e.getSource();

			FWresName.setEnabled(fw.isSelected());
		}
		else if ( e.getSource().equals(EmailRestrictionBox) )
		{
			JCheckBox email = (JCheckBox) e.getSource();

			EmailResName.setEnabled(email.isSelected());
		}
		else if ( e.getSource().equals(OfficeSuiteRestrictionBox) )
		{
			JCheckBox office = (JCheckBox) e.getSource();

			OfficeSuiteResName.setEnabled(office.isSelected());
		}
		NetworkRulesFrame.changed = true;
	}
}
