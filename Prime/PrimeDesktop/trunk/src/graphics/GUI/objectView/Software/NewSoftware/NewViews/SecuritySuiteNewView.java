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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import objects.softwareObjects.SecuritySuite;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class SecuritySuiteNewView extends JDialog implements SoftwareView, ActionListener
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
	private JTextField license = new JTextField(25);

	// Whether or not the security suite contains an antivirus
	private JCheckBox hasAntivirus;

	// TO-DO: Set up connection between security suite and antivirus, firewall
	// and proxy

	// Whether or not the security suite contains an firewall
	private JCheckBox hasFirewall;

	// Whether or not the security suite contains an proxy
	private JCheckBox hasProxy;

	// The date of activation
	private JTextField actDate = new JTextField(10);

	// The date the license expires
	private JTextField expDate = new JTextField(10);



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
	public SecuritySuiteNewView(Object obj, SecuritySuite secSuite)
	{
		this.setTitle(PrimeMain.texts.getString("swNewSecuritySuiteLabel"));

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));

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
	 * @param secSuite
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(SecuritySuite secSuite)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[8];


		labels[0] = new JLabel(PrimeMain.texts
				.getString("secSuiteViewSupOSLabel"));
		labels[0].setToolTipText(PrimeMain.texts
				.getString("secSuiteViewSupOSTip"));

		labels[1] = new JLabel(PrimeMain.texts
				.getString("secSuiteViewActivatedLabel"));
		labels[1].setToolTipText(PrimeMain.texts
				.getString("secSuiteViewActivatedTip"));

		labels[2] = new JLabel(PrimeMain.texts
				.getString("secSuiteViewLicenseLabel"));
		labels[2].setToolTipText(PrimeMain.texts
				.getString("secSuiteViewLicenseTip"));

		labels[3] = new JLabel(PrimeMain.texts
				.getString("secSuiteViewHasAntivirusLabel"));
		labels[3].setToolTipText(PrimeMain.texts
				.getString("secSuiteViewHasAntivirusTip"));

		labels[4] = new JLabel(PrimeMain.texts
				.getString("secSuiteViewHasFirewallLabel"));
		labels[4].setToolTipText(PrimeMain.texts
				.getString("secSuiteViewHasFirewallTip"));

		labels[5] = new JLabel(PrimeMain.texts
				.getString("secSuiteViewHasProxyLabel"));
		labels[5].setToolTipText(PrimeMain.texts
				.getString("secSuiteViewHasProxyTip"));

		labels[6] = new JLabel(PrimeMain.texts
				.getString("secSuiteViewActDateLabel"));
		labels[6].setToolTipText(PrimeMain.texts
				.getString("secSuiteViewActDateTip"));

		labels[7] = new JLabel(PrimeMain.texts
				.getString("secSuiteViewExpDateLabel"));
		labels[7].setToolTipText(PrimeMain.texts
				.getString("secSuiteViewExpDateTip"));


		Dimension tfSize = new Dimension(90, 20);
		SimpleDateFormat format = new SimpleDateFormat(PrimeMain.texts
				.getString("secSuiteViewSimpleDateFormat"));

		// --------------------------------------------------------------

		// The supported operating systems by the Email software.
		labels[0].setLabelFor(supportedOS);
		String[] listData = { "Windows 98", "Windows 2000", "Windows XP",
				"Windows Vista", "Linux", "Novell" };
		supportedOS = new JList(listData);
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
						supportedOS, listData, mainSecSuite
								.getSupportedOperatingSystems()));
			}
		}

		panel.add(labels[0]);
		panel.add(listPane);

		// --------------------------------------------------------------

		// Whether or not the security suite has been activated
		labels[1].setLabelFor(activated);
		activated = new JCheckBox();
		activated.setMaximumSize(tfSize);
		activated.setPreferredSize(tfSize);
		activated.setToolTipText(labels[1].getToolTipText());
		activated.setActionCommand("Activated");
		activated.addActionListener(this);

		activated.setSelected(mainSecSuite.isActivated());

		panel.add(labels[1]);
		panel.add(activated);

		// --------------------------------------------------------------

		// The actual license of the program
		labels[2].setLabelFor(license);
		license.setMaximumSize(tfSize);
		license.setPreferredSize(tfSize);
		license.setText(mainSecSuite.getLicense());
		;
		license.setToolTipText(labels[2].getToolTipText());


		panel.add(labels[2]);
		panel.add(license);

		// --------------------------------------------------------------

		// Whether or not the security suite contains an antivirus
		labels[3].setLabelFor(hasAntivirus);
		hasAntivirus = new JCheckBox();
		hasAntivirus.setMaximumSize(tfSize);
		hasAntivirus.setPreferredSize(tfSize);
		hasAntivirus.setToolTipText(labels[3].getToolTipText());
		hasAntivirus.setActionCommand("HasAntivirus");
		hasAntivirus.addActionListener(this);

		hasAntivirus.setSelected(mainSecSuite.hasAntivirus());

		panel.add(labels[3]);
		panel.add(hasAntivirus);

		// --------------------------------------------------------------

		// TO-DO: Set up connection between security suite and antivirus,
		// firewall
		// and proxy


		// Whether or not the security suite contains an firewall
		labels[4].setLabelFor(hasFirewall);
		hasFirewall = new JCheckBox();
		hasFirewall.setMaximumSize(tfSize);
		hasFirewall.setPreferredSize(tfSize);
		hasFirewall.setToolTipText(labels[4].getToolTipText());
		hasFirewall.setActionCommand("HasFirewall");
		hasFirewall.addActionListener(this);

		hasFirewall.setSelected(mainSecSuite.hasFirewall());

		panel.add(labels[4]);
		panel.add(hasFirewall);

		// --------------------------------------------------------------

		// Whether or not the security suite contains an proxy
		labels[5].setLabelFor(hasProxy);
		hasProxy = new JCheckBox();
		hasProxy.setMaximumSize(tfSize);
		hasProxy.setPreferredSize(tfSize);
		hasProxy.setToolTipText(labels[5].getToolTipText());
		hasProxy.setActionCommand("HasProxy");
		hasProxy.addActionListener(this);

		hasProxy.setSelected(mainSecSuite.hasProxy());

		panel.add(labels[5]);
		panel.add(hasProxy);

		// --------------------------------------------------------------

		// The Activated date
		labels[6].setLabelFor(actDate);
		actDate.setMaximumSize(tfSize);
		actDate.setPreferredSize(tfSize);
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
			System.out
					.println("Error - SecuritySuiteEditView - Activated Date");
		}

		if ( parsedAct != null )
		{
			actDate.setText(parsedAct.toString());
		}
		actDate.setToolTipText(labels[6].getToolTipText());

		panel.add(labels[6]);
		panel.add(actDate);

		// --------------------------------------------------------------

		// The Expiration date
		labels[7].setLabelFor(expDate);
		expDate.setMaximumSize(tfSize);
		expDate.setPreferredSize(tfSize);
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
			System.out
					.println("Error - SecuritySuiteEditView - Expiration Date");
		}

		if ( parsedExp != null )
		{
			expDate.setText(parsedExp.toString());
		}
		expDate.setToolTipText(labels[7].getToolTipText());

		panel.add(labels[7]);
		panel.add(expDate);

		// --------------------------------------------------------------



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



	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			// Saves the current values of the new motherboard.
			save();

			// Checks whether or not the software is compatible with the OS
			if ( SoftwareManagment.validateSoftware(mainSecSuite, mainObj) )
			{
				// Sets an array with the newly added software object
				mainObj.setSoftware(SoftwareManagment.addSoftware(mainSecSuite,
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
		return mainSecSuite;
	}


}
