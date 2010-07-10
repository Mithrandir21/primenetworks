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
package graphics.GUI.objectView.Network;


import graphics.PrimeMain;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objects.Object;
import widgets.WidgetObject;


/**
 * This class sets up the information displayed to the user about the network
 * status of a given {@link Object}.
 * 
 * @author Bahram Malaekeh
 */
public class NetworkView extends JPanel
{
	public JTextField widgetIPfield = new JTextField();


	public JTextField widgetNetmaskField = new JTextField();


	public JTextField widgetMacField = new JTextField();


	public JTextField widgetDefaultGatewayField = new JTextField();


	public JTextField widgetNetworkNameField = new JTextField();


	public JTextArea widgetNotesArea = new JTextArea();



	public JCheckBox exemptedNetworkRules = new JCheckBox();




	public NetworkView(WidgetObject obj)
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();


		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		// d.gridy = 0; // row
		// d.gridx = 0; // column





		JPanel fieldsPanel = new JPanel();

		fieldsPanel.setLayout(new GridBagLayout());
		GridBagConstraints fieldD = new GridBagConstraints();


		fieldD.fill = GridBagConstraints.BOTH;
		// fieldD.ipady = 0; // reset to default
		// fieldD.ipadx = 0; // reset to default
		// fieldD.weighty = 1.0; // request any extra vertical space
		fieldD.weightx = 0; // request any extra horizontal space
		fieldD.anchor = GridBagConstraints.CENTER; // location
		fieldD.insets = new Insets(5, 5, 5, 5); // padding
		// fieldD.gridwidth = 1; // 2 row wide
		// fieldD.gridheight = 1; // 2 columns wide




		fieldD.gridy = 0; // row
		fieldD.gridx = 0; // column
		JLabel widgetIPlabel = new JLabel(PrimeMain.texts
				.getString("netIPLabel"));
		fieldsPanel.add(widgetIPlabel, fieldD);

		fieldD.gridx = 1; // column
		widgetIPfield = new JTextField(obj.getWidgetNetworkInfo().getIp());
		fieldsPanel.add(widgetIPfield, fieldD);

		fieldD.gridx = 2; // column
		JLabel widgetIPexamplelabel = new JLabel("Example: 192.168.1.1");
		fieldsPanel.add(widgetIPexamplelabel, fieldD);




		fieldD.gridy = 1; // row
		fieldD.gridx = 0; // column
		JLabel widgetNetmaskLabel = new JLabel(PrimeMain.texts
				.getString("netNetmaskLabel"));
		fieldsPanel.add(widgetNetmaskLabel, fieldD);

		fieldD.gridx = 1; // column
		widgetNetmaskField = new JTextField(obj.getWidgetNetworkInfo()
				.getNetmask());
		fieldsPanel.add(widgetNetmaskField, fieldD);

		fieldD.gridx = 2; // column
		JLabel widgetSubnetExampleLabel = new JLabel("Example: 255.255.0.0");
		fieldsPanel.add(widgetSubnetExampleLabel, fieldD);




		fieldD.gridy = 2; // row
		fieldD.gridx = 0; // column
		JLabel widgetMacLabel = new JLabel(PrimeMain.texts
				.getString("netMacLabel"));
		fieldsPanel.add(widgetMacLabel, fieldD);

		fieldD.gridx = 1; // column
		widgetMacField = new JTextField(obj.getWidgetNetworkInfo().getMAC());
		fieldsPanel.add(widgetMacField, fieldD);

		fieldD.gridx = 2; // column
		JLabel widgetMacExampleLabel = new JLabel("Example: 01:23:45:67:89:AB");
		fieldsPanel.add(widgetMacExampleLabel, fieldD);




		fieldD.gridy = 3; // row
		fieldD.gridx = 0; // column
		JLabel widgetDefaultGatewayLabel = new JLabel(PrimeMain.texts
				.getString("netDefaultGatewayLabel"));
		fieldsPanel.add(widgetDefaultGatewayLabel, fieldD);

		fieldD.gridx = 1; // column
		widgetDefaultGatewayField = new JTextField(obj.getWidgetNetworkInfo()
				.getDefaultGateway());
		fieldsPanel.add(widgetDefaultGatewayField, fieldD);

		fieldD.gridx = 2; // column
		JLabel widgetDefaultGatewayExampleLabel = new JLabel(
				"Example: 192.168.4.1");
		fieldsPanel.add(widgetDefaultGatewayExampleLabel, fieldD);




		fieldD.gridy = 4; // row
		fieldD.gridx = 0; // column
		JLabel widgetNetworkNameLabel = new JLabel(PrimeMain.texts
				.getString("netNetworkNameLabel"));
		fieldsPanel.add(widgetNetworkNameLabel, fieldD);

		fieldD.gridx = 1; // column
		widgetNetworkNameField = new JTextField(obj.getWidgetNetworkInfo()
				.getNetworkName());
		fieldsPanel.add(widgetNetworkNameField, fieldD);

		// fieldD.weightx = 1.0; // request any extra horizontal space
		fieldD.gridx = 2; // column
		JLabel widgetNetworkNameExampleLabel = new JLabel("Example: Office");
		fieldsPanel.add(widgetNetworkNameExampleLabel, fieldD);






		fieldD.gridy = 5; // row
		fieldD.gridx = 0; // column
		JLabel exemptedLabel = new JLabel(PrimeMain.texts
				.getString("propGeneralViewExemptedRulesLabel"));
		fieldsPanel.add(exemptedLabel, fieldD);

		fieldD.weightx = 1.0; // request any extra horizontal space
		fieldD.gridx = 1; // column
		exemptedNetworkRules = new JCheckBox();
		exemptedNetworkRules.setSelected(obj.getObject()
				.isExemptedNetworkRules());
		fieldsPanel.add(exemptedNetworkRules, fieldD);




		d.gridy = 0; // row
		d.gridx = 0; // column
		this.add(fieldsPanel, d);








		JPanel notePanel = new JPanel();

		notePanel.setLayout(new GridBagLayout());
		GridBagConstraints noteD = new GridBagConstraints();


		noteD.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// fieldD.weighty = 1.0; // request any extra vertical space
		// fieldD.weightx = 1.0; // request any extra horizontal space
		// d.anchor = GridBagConstraints.NORTH; // location
		// d.insets = new Insets(5, 5, 5, 5); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide




		noteD.gridy = 0; // row
		noteD.gridx = 0; // column
		JLabel widgetNotesLabel = new JLabel(PrimeMain.texts
				.getString("netNetworkNotesLabel"));
		notePanel.add(widgetNotesLabel, noteD);

		noteD.weighty = 1.0; // request any extra vertical space
		noteD.weightx = 1.0; // request any extra horizontal space
		noteD.gridy = 1; // row
		widgetNotesArea = new JTextArea(obj.getWidgetNetworkInfo()
				.getWidgetNotes());
		JScrollPane notesScroll = new JScrollPane(widgetNotesArea);
		notePanel.add(notesScroll, noteD);



		d.gridwidth = 2;
		d.gridy = 1; // row
		d.gridx = 0; // column
		this.add(notePanel, d);
	}
}
