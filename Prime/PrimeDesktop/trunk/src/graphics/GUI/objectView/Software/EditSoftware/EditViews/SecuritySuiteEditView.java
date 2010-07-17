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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import objects.softwareObjects.SecuritySuite;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link SecuritySuite SecuritySuite} Software. The panel is
 * made up of 3 JPanel ordered in a column. The first one contains the name and
 * description of the object. The second panel contains the specific software
 * options. The third panel contains the button that can remove the software
 * from the computer.
 * 
 * @author Bahram Malaekeh
 */
public class SecuritySuiteEditView extends JPanel implements SoftwareView, ActionListener
{
	// The name of the software object
	JTextField name = new JTextField(25);

	// The description of the software object.
	JTextArea desc = new JTextArea(3, 40);

	// Supported Operating systems
	private JList supportedOS;

	// List of operating systems
	private String[] OSs;

	// Whether or not the security suite has been activated
	private JCheckBox activated;

	// The actual license of the program
	private JTextField license = new JTextField();

	// Whether or not the security suite contains an antivirus
	private JCheckBox hasAntivirus;

	// TO-DO: Set up connection between security suite and antivirus, firewall
	// and proxy

	// Whether or not the security suite contains an firewall
	private JCheckBox hasFirewall;

	// Whether or not the security suite contains an proxy
	private JCheckBox hasProxy;

	// The date of activation
	private JTextField actDate = new JTextField();

	// The date the license expires
	private JTextField expDate = new JTextField();



	private Object mainObj;

	private SecuritySuite mainSecSuite;


	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param secSuite
	 *            The {@link SecuritySuite SecuritySuite} software.
	 */
	public SecuritySuiteEditView(Object obj, SecuritySuite secSuite)
	{
		mainObj = obj;
		mainSecSuite = secSuite;
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


		ImageIcon icon = PrimeMain.objectImageIcons.get(SecuritySuite.class);
		JPanel p1 = SoftwareEditor.GeneralInfo(mainSecSuite, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		JPanel p2 = createSpesificInfo(mainSecSuite);
		p2.setBorder(BorderFactory.createEtchedBorder());

		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1.0; // request any extra vertical space
		c.insets = new Insets(0, 10, 0, 10);
		this.add(p2, c);



		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel(PrimeMain.texts
				.getString("swTabRemoveSoftwaretText"));

		Button remove = new Button(PrimeMain.texts
				.getString("swTabRemoveSoftwareButtonLabel"));
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
	 * settings of the given Software object. It uses the {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param secSuite
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(SecuritySuite secSuite)
	{
		Dimension tfSize = new Dimension(100, 20);

		JLabel[] labels = new JLabel[3];


		labels[0] = new JLabel(PrimeMain.texts
				.getString("secSuiteViewLicenseLabel"));
		labels[0].setToolTipText(PrimeMain.texts
				.getString("secSuiteViewLicenseTip"));


		labels[1] = new JLabel(PrimeMain.texts
				.getString("secSuiteViewActDateLabel"));
		labels[1].setToolTipText(PrimeMain.texts
				.getString("secSuiteViewActDateTip"));

		labels[2] = new JLabel(PrimeMain.texts
				.getString("secSuiteViewExpDateLabel"));
		labels[2].setToolTipText(PrimeMain.texts
				.getString("secSuiteViewExpDateTip"));


		SimpleDateFormat format = new SimpleDateFormat(PrimeMain.texts
				.getString("secSuiteViewSimpleDateFormat"));

		// --------------------------------------------------------------
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.NONE;
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

		// The supported operating systems by the Email software.
		JLabel osLabel = new JLabel(PrimeMain.texts
				.getString("secSuiteViewSupOSLabel"));
		osLabel.setToolTipText(PrimeMain.texts
				.getString("secSuiteViewSupOSTip"));
		panel.add(osLabel, c);


		String[] osNames = DesktopSoftwareManagment.getSystemOSname();
		supportedOS = new JList(osNames);
		ListSelectionModel listSelectionModel = supportedOS.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(supportedOS);
		listPane.setMaximumSize(new Dimension(130, 60));
		listPane.setPreferredSize(new Dimension(130, 60));
		listSelectionModel
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		if ( mainSecSuite.getSupportedOperatingSystems() != null )
		{
			if ( mainSecSuite.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, osNames, mainSecSuite
								.getSupportedOperatingSystems()));
			}
		}

		c.gridx = 1; // column
		panel.add(listPane, c);

		// --------------------------------------------------------------

		// Whether or not the security suite has been activated
		activated = new JCheckBox(PrimeMain.texts
				.getString("secSuiteViewActivatedLabel"));
		activated.setToolTipText(PrimeMain.texts
				.getString("secSuiteViewActivatedTip"));
		activated.setActionCommand("Activated");
		activated.addActionListener(this);

		activated.setSelected(mainSecSuite.isActivated());

		c.gridx = 2; // column
		panel.add(activated, c);

		// --------------------------------------------------------------

		// The actual license of the program
		labels[0].setLabelFor(license);
		license.setMaximumSize(tfSize);
		license.setPreferredSize(tfSize);
		license.setText(mainSecSuite.getLicense());
		license.setToolTipText(labels[2].getToolTipText());


		c.gridx = 3; // column
		panel.add(labels[0], c);

		c.weightx = 1.0; // request any extra horizontal space
		c.gridx = 4; // column
		panel.add(license, c);

		// --------------------------------------------------------------

		// Whether or not the security suite contains an antivirus
		hasAntivirus = new JCheckBox(PrimeMain.texts
				.getString("secSuiteViewHasAntivirusLabel"));
		hasAntivirus.setToolTipText(PrimeMain.texts
				.getString("secSuiteViewHasAntivirusTip"));
		hasAntivirus.setActionCommand("HasAntivirus");
		hasAntivirus.addActionListener(this);

		hasAntivirus.setSelected(mainSecSuite.hasAntivirus());

		c.insets = new Insets(10, 10, 10, 10); // padding
		c.weightx = 0; // request any extra horizontal space
		c.gridy = 1; // row
		c.gridx = 0; // column
		panel.add(hasAntivirus, c);

		// --------------------------------------------------------------

		// TODO: Set up connection between security suite and antivirus,
		// firewall
		// and proxy


		// Whether or not the security suite contains an firewall
		hasFirewall = new JCheckBox(PrimeMain.texts
				.getString("secSuiteViewHasFirewallLabel"));
		hasFirewall.setToolTipText(PrimeMain.texts
				.getString("secSuiteViewHasFirewallTip"));
		hasFirewall.setActionCommand("HasFirewall");
		hasFirewall.addActionListener(this);

		hasFirewall.setSelected(mainSecSuite.hasFirewall());

		c.gridx = 1; // column
		panel.add(hasFirewall, c);

		// --------------------------------------------------------------

		// Whether or not the security suite contains an proxy
		hasProxy = new JCheckBox(PrimeMain.texts
				.getString("secSuiteViewHasProxyLabel"));
		hasProxy.setToolTipText(PrimeMain.texts
				.getString("secSuiteViewHasProxyTip"));
		hasProxy.setActionCommand("HasProxy");
		hasProxy.addActionListener(this);

		hasProxy.setSelected(mainSecSuite.hasProxy());

		c.gridx = 2; // column
		panel.add(hasProxy, c);

		// --------------------------------------------------------------

		// The Activated date
		labels[1].setLabelFor(actDate);
		Date parsedAct = null;

		try
		{
			if ( mainSecSuite.getActivationDate() != null )
			{
				parsedAct = format.parse(mainSecSuite.getActivationDate()
						.toString());
			}
		}
		catch ( ParseException e )
		{
			// DO nothing.
		}

		if ( parsedAct != null )
		{
			actDate.setText(parsedAct.toString());
		}
		actDate.setMaximumSize(tfSize);
		actDate.setPreferredSize(tfSize);
		actDate.setToolTipText(labels[1].getToolTipText());

		c.gridx = 3; // column
		panel.add(labels[1], c);
		c.gridx = 4; // column
		panel.add(actDate, c);

		// --------------------------------------------------------------

		// The Expiration date
		labels[2].setLabelFor(expDate);
		Date parsedExp = null;

		try
		{
			if ( mainSecSuite.getExpirationDate() != null )
			{
				parsedExp = format.parse(mainSecSuite.getExpirationDate()
						.toString());
			}
		}
		catch ( ParseException e )
		{
			// DO nothing.
		}

		if ( parsedExp != null )
		{
			expDate.setText(parsedExp.toString());
		}
		expDate.setMaximumSize(tfSize);
		expDate.setPreferredSize(tfSize);
		expDate.setToolTipText(labels[2].getToolTipText());

		c.gridy = 2; // row
		c.gridx = 0; // column
		panel.add(labels[2], c);
		c.weighty = 1.0; // request any extra horizontal space
		c.gridx = 1; // column
		panel.add(expDate, c);

		// --------------------------------------------------------------


		return panel;
	}



	/*
	 * (non-Javadoc)
	 * @see graphics.GUI.objectView.Software.SoftwareEditView#save()
	 */
	@Override
	public void save()
	{
		SimpleDateFormat format = new SimpleDateFormat(PrimeMain.texts
				.getString("secSuiteViewSimpleDateFormat"));

		if ( name.getText() != "" )
		{
			mainSecSuite.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainSecSuite.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainSecSuite.setSupportedOperatingSystems(OSs);
		}


		if ( license.getText() != "" )
		{
			mainSecSuite.setLicense(license.getText());
		}


		mainSecSuite.setHasAntivirus(hasAntivirus.isSelected());

		mainSecSuite.sethasFirewall(hasFirewall.isSelected());

		mainSecSuite.sethasProxy(hasProxy.isSelected());


		if ( !actDate.getText().equalsIgnoreCase("") )
		{
			Date tempDate = null;

			try
			{
				tempDate = format.parse(actDate.getText());
			}
			catch ( ParseException e )
			{
				e.printStackTrace();
			}

			mainSecSuite.setActivationDate(tempDate);
		}

		if ( !expDate.getText().equalsIgnoreCase("") )
		{
			Date tempDate = null;

			try
			{
				tempDate = format.parse(expDate.getText());
			}
			catch ( ParseException e )
			{
				e.printStackTrace();
			}

			mainSecSuite.setExpirationDate(tempDate);
		}

	}



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof Button )
		{
			Button check = (Button) e.getSource();

			String command = check.getActionCommand();

			if ( command.equals("removeSoft") )
			{
				DesktopSoftwareManagment.removeSoftware(mainObj, mainSecSuite);

				// Updates the views of the object to correctly show the current info.
				ObjectView view = PrimeMain.getObjectView(mainObj);
				if ( view != null )
				{
					view.updateViewInfo();
				}
			}
		}
		else if ( e.getSource() instanceof JCheckBox )
		{
			JCheckBox box = (JCheckBox) e.getSource();

			String command = box.getActionCommand();

			if ( command.equals("Activated") )
			{

			}
			else if ( command.equals("HasAntivirus") )
			{

			}
			else if ( command.equals("HasFirewall") )
			{

			}
			else if ( command.equals("HasProxy") )
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
		 * @see
		 * javax.swing.event.ListSelectionListener#valueChanged(javax.swing.
		 * event.ListSelectionEvent)
		 */
		public void valueChanged(ListSelectionEvent e)
		{
			int[] indexes = supportedOS.getSelectedIndices();

			if ( indexes.length == 0 )
			{
				OSs = null;
			}
			else
			{
				// Creates an array of strings with the length of the array with
				// the selected indices.
				OSs = new String[indexes.length];

				// Find out which indexes are selected.
				for ( int i = 0; i < indexes.length; i++ )
				{
					OSs[i] = (String) supportedOS.getSelectedValues()[i];
				}
			}
		}
	}


	@Override
	public Software getViewSoftware()
	{
		return mainSecSuite;
	}

}
