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
import objects.softwareObjects.Antivirus;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link Antivirus Antivirus} Software. The panel is made up
 * of 3 JPanel ordered in a column. The first one contains the name and
 * description of the object. The second panel contains the specific software
 * options. The third panel contains the button that can remove the software
 * from the computer.
 * 
 * @author Bahram Malaekeh
 */
public class AntivirusEditView extends JPanel implements SoftwareView,
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

	// The date of activation
	private JTextField actDate = new JTextField();

	// The date the license expires
	private JTextField expDate = new JTextField();

	// Whether or not the antivirus has been activated
	private JCheckBox activated;

	// The actual license of the program
	private JTextField license = new JTextField();


	private Object mainObj;

	private Antivirus mainAV;


	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param av
	 *            The {@link Antivirus Antivirus} software.
	 */
	public AntivirusEditView(Object obj, Antivirus av)
	{
		mainObj = obj;
		mainAV = av;
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

		ImageIcon icon = PrimeMain.objectImageIcons.get(Antivirus.class);
		JPanel p1 = SoftwareEditor.GeneralInfo(mainAV, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		JPanel p2 = createSpecificInfo(mainAV);
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
	 * @param av
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpecificInfo(Antivirus av)
	{
		Dimension tfSize = new Dimension(100, 20);


		JLabel[] labels = new JLabel[5];

		labels[0] = new JLabel(PrimeMain.texts.getString("avViewSupOSLabel"));
		labels[0].setToolTipText(PrimeMain.texts.getString("avViewSupOSTip"));

		labels[1] = new JLabel(PrimeMain.texts.getString("avViewActDateLabel"));
		labels[1].setToolTipText(PrimeMain.texts.getString("avViewActDateTip"));

		labels[2] = new JLabel(PrimeMain.texts.getString("avViewExpDateLabel"));
		labels[2].setToolTipText(PrimeMain.texts.getString("avViewExpDateTip"));

		labels[3] = new JLabel(PrimeMain.texts.getString("avViewLicenseLabel"));
		labels[3].setToolTipText(PrimeMain.texts.getString("avViewLicenseTip"));

		labels[4] = new JLabel(
				PrimeMain.texts.getString("avViewActivatedLabel"));
		labels[4].setToolTipText(PrimeMain.texts
				.getString("avViewActivatedTip"));


		SimpleDateFormat format = new SimpleDateFormat(
				PrimeMain.texts.getString("avViewSimpleDateFormat"));


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


		// The supported operating systems by the Antivirus software.
		labels[0].setLabelFor(supportedOS);
		panel.add(labels[0], c);

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

		if ( mainAV.getSupportedOperatingSystems() != null )
		{
			if ( mainAV.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, osNames,
						mainAV.getSupportedOperatingSystems()));
			}
		}

		c.insets = new Insets(20, 10, 10, 10); // padding
		c.gridx = 1; // column
		panel.add(listPane, c);



		// The Activated date
		c.gridx = 2; // column
		panel.add(labels[1], c);


		labels[1].setLabelFor(actDate);
		actDate.setMaximumSize(tfSize);
		actDate.setPreferredSize(tfSize);
		Date parsedAct = null;

		try
		{
			if ( av.getActivationDate() != null )
			{
				parsedAct = format.parse(av.getActivationDate().toString());
			}
		}
		catch ( ParseException e )
		{
			// DO nothing.
		}

		if ( av.getActivationDate() != null )
		{
			actDate.setText(parsedAct.toString());
		}
		else
		{
			actDate.setText("");
		}
		actDate.setToolTipText(labels[1].getToolTipText());

		c.gridx = 3; // column
		panel.add(actDate, c);




		// The Expiration date
		c.gridx = 4; // column
		panel.add(labels[2], c);


		labels[2].setLabelFor(expDate);
		expDate.setMaximumSize(tfSize);
		expDate.setPreferredSize(tfSize);
		Date parsedExp = null;

		try
		{
			if ( av.getExpirationDate() != null )
			{
				parsedExp = format.parse(av.getExpirationDate().toString());
			}
		}
		catch ( ParseException e )
		{
			// DO nothing.
		}

		if ( av.getActivationDate() != null )
		{
			expDate.setText(parsedExp.toString());
		}
		else
		{
			expDate.setText("");
		}
		expDate.setToolTipText(labels[2].getToolTipText());

		c.weightx = 1.0; // request any extra horizontal space
		c.gridx = 5; // column
		panel.add(expDate, c);


		// The license key
		c.insets = new Insets(10, 10, 10, 10); // padding
		c.gridwidth = 2; // 2 row wide
		c.weightx = 0; // request any extra horizontal space
		c.gridy = 1; // row
		c.gridx = 0; // column
		panel.add(labels[3], c);

		labels[3].setLabelFor(license);
		license.setMaximumSize(tfSize);
		license.setPreferredSize(tfSize);
		license.setText(av.getLicense());
		license.setToolTipText(labels[3].getToolTipText());

		c.insets = new Insets(10, 10, 10, 10); // padding
		c.gridwidth = 1; // 1 row wide
		c.gridx = 1; // column
		panel.add(license, c);



		// Whether or not the AV has been avtivated.
		activated = new JCheckBox(labels[4].getText());
		activated.setToolTipText(labels[4].getToolTipText());
		activated.setActionCommand("activated");
		activated.addActionListener(this);

		activated.setSelected(av.getIsActivated());


		c.weighty = 1.0; // request any extra horizontal space
		c.gridx = 2; // column
		panel.add(activated, c);



		return panel;
	}




	@Override
	public void save()
	{
		SimpleDateFormat format = new SimpleDateFormat(
				PrimeMain.texts.getString("avViewSimpleDateFormat"));

		if ( !name.getText().equals("") )
		{
			mainAV.setObjectName(name.getText());
		}

		if ( !desc.getText().equals("") )
		{
			mainAV.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainAV.setSupportedOperatingSystems(OSs);
		}

		if ( !actDate.getText().equalsIgnoreCase("") )
		{
			Date tempDate = null;

			try
			{
				tempDate = format.parse(actDate.getText());
			}
			catch ( ParseException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mainAV.setActivationDate(tempDate);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mainAV.setExpirationDate(tempDate);
		}

		mainAV.setActivated(activated.isSelected());

		if ( !license.getText().equalsIgnoreCase("") )
		{
			mainAV.setLicense(license.getText());
		}
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
				DesktopSoftwareManagment.removeSoftware(mainObj, mainAV);

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
		return mainAV;
	}
}
