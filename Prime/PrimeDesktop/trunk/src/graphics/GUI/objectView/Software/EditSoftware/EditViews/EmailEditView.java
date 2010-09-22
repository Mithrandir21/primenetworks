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
import objects.softwareObjects.Email;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link Email Email} Software. The panel is made up of 3
 * JPanel ordered in a column. The first one contains the name and description
 * of the object. The second panel contains the specific software options. The
 * third panel contains the button that can remove the software from the
 * computer.
 * 
 * @author Bahram Malaekeh
 */
public class EmailEditView extends JPanel implements SoftwareView,
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

	// Supports POP3
	private JCheckBox supportsPOP3;


	// Supports SMTP
	private JCheckBox supportsSMTP;


	// Supports IMAP
	private JCheckBox supportsIMAP;


	// Supports NNTP
	private JCheckBox supportsNNTP;


	// Supports SSL
	private JCheckBox supportsSSL;


	// Supports webmail
	private JCheckBox supportsWebmail;



	private Object mainObj;

	private Email mainEmail;


	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param email
	 *            The {@link Email Email} software.
	 */
	public EmailEditView(Object obj, Email email)
	{
		mainObj = obj;
		mainEmail = email;
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

		ImageIcon icon = PrimeMain.objectImageIcons.get(Email.class);
		JPanel p1 = SoftwareEditor.GeneralInfo(mainEmail, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);



		JPanel p2 = createSpesificInfo(mainEmail);
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
	 * @param email
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(Email email)
	{
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.NONE;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		// c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTHWEST; // location
		c.insets = new Insets(20, 20, 10, 10); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column


		// The supported operating systems by the Antivirus software.
		JLabel osLabel = new JLabel(
				PrimeMain.texts.getString("emailViewSupOSLabel"));
		osLabel.setToolTipText(PrimeMain.texts.getString("emailViewSupOSTip"));
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

		if ( mainEmail.getSupportedOperatingSystems() != null )
		{
			if ( mainEmail.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, osNames,
						mainEmail.getSupportedOperatingSystems()));
			}
		}


		c.insets = new Insets(20, 10, 10, 10); // padding
		c.gridx = 1; // column
		panel.add(listPane, c);



		// Whether or not the software supports pop3.
		supportsPOP3 = new JCheckBox(
				PrimeMain.texts.getString("emailViewSupPOP3Label"));
		supportsPOP3.setToolTipText(PrimeMain.texts
				.getString("emailViewSupPOP3Tip"));
		supportsPOP3.setActionCommand("POP3");
		supportsPOP3.addActionListener(this);

		supportsPOP3.setSelected(mainEmail.SupportsPOP3());

		c.gridx = 2; // column
		panel.add(supportsPOP3, c);


		// Whether or not the software supports SMTP.
		supportsSMTP = new JCheckBox(
				PrimeMain.texts.getString("emailViewSupSMTPLabel"));
		supportsSMTP.setToolTipText(PrimeMain.texts
				.getString("emailViewSupSMTPTip"));
		supportsSMTP.setActionCommand("SMTP");
		supportsSMTP.addActionListener(this);

		supportsSMTP.setSelected(mainEmail.SupportsSMTP());

		c.weightx = 1.0; // request any extra horizontal space
		c.gridx = 3; // column
		panel.add(supportsSMTP, c);




		// Whether or not the software supports IMAP.

		supportsIMAP = new JCheckBox(
				PrimeMain.texts.getString("emailViewSupIMAPLabel"));
		supportsIMAP.setToolTipText(PrimeMain.texts
				.getString("emailViewSupIMAPTip"));
		supportsIMAP.setActionCommand("IMAP");
		supportsIMAP.addActionListener(this);

		supportsIMAP.setSelected(mainEmail.SupportsIMAP());

		c.insets = new Insets(10, 20, 10, 10); // padding
		c.gridwidth = 2; // 2 row wide
		c.weightx = 0; // request any extra horizontal space
		c.gridy = 1; // row
		c.gridx = 0; // column
		panel.add(supportsIMAP, c);


		// Whether or not the software supports NNTP.
		supportsNNTP = new JCheckBox(
				PrimeMain.texts.getString("emailViewSupNNTPLabel"));
		supportsNNTP.setToolTipText(PrimeMain.texts
				.getString("emailViewSupNNTPTip"));
		supportsNNTP.setActionCommand("NNTP");
		supportsNNTP.addActionListener(this);

		supportsNNTP.setSelected(mainEmail.SupportsNNTP());

		c.insets = new Insets(10, 10, 10, 10); // padding
		c.gridwidth = 1; // 1 row wide
		c.gridx = 2; // column
		panel.add(supportsNNTP, c);


		// Whether or not the software supports SSL.
		supportsSSL = new JCheckBox(
				PrimeMain.texts.getString("emailViewSupSSLLabel"));
		supportsSSL.setToolTipText(PrimeMain.texts
				.getString("emailViewSupSSLTip"));
		supportsSSL.setActionCommand("SSL");
		supportsSSL.addActionListener(this);

		supportsSSL.setSelected(mainEmail.SupportsSSL());

		c.gridx = 3; // column
		panel.add(supportsSSL, c);



		// Whether or not the software supports Webmail.
		supportsWebmail = new JCheckBox(
				PrimeMain.texts.getString("emailViewSupWebmailLabel"));
		supportsWebmail.setToolTipText(PrimeMain.texts
				.getString("emailViewSupWebmailTip"));
		supportsWebmail.setActionCommand("Webmail");
		supportsWebmail.addActionListener(this);

		supportsWebmail.setSelected(mainEmail.SupportsWebmail());

		c.insets = new Insets(10, 20, 10, 10); // padding
		c.gridwidth = 2; // 2 row wide
		c.weighty = 1.0; // request any extra horizontal space
		c.gridy = 2; // row
		c.gridx = 0; // column
		panel.add(supportsWebmail, c);



		return panel;
	}



	@Override
	public void save()
	{
		if ( !name.getText().equals("") )
		{
			mainEmail.setObjectName(name.getText());
		}

		if ( !desc.getText().equals("") )
		{
			mainEmail.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainEmail.setSupportedOperatingSystems(OSs);
		}

		mainEmail.setSupportsPOP3(supportsPOP3.isSelected());

		mainEmail.setSupportsSMTP(supportsSMTP.isSelected());

		mainEmail.setSupportsIMAP(supportsIMAP.isSelected());

		mainEmail.setSupportsNNTP(supportsNNTP.isSelected());

		mainEmail.setSupportsSSL(supportsSSL.isSelected());

		mainEmail.setSupportsWebmail(supportsWebmail.isSelected());

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
				DesktopSoftwareManagment.removeSoftware(mainObj, mainEmail);

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

			if ( command.equals("POP3") )
			{

			}
			else if ( command.equals("SMTP") )
			{

			}
			else if ( command.equals("IMAP") )
			{

			}
			else if ( command.equals("NNTP") )
			{

			}
			else if ( command.equals("SSL") )
			{

			}
			else if ( command.equals("Webmail") )
			{

			}
		}
	}



	/**
	 * Handles the selections that are made in the "Supported Operating Systems"
	 * JList.
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
		return mainEmail;
	}

}
