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
import objects.softwareObjects.Webserver;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link Webserver Webserver} Software. The panel is made up
 * of 3 JPanel ordered in a column. The first one contains the name and
 * description of the object. The second panel contains the specific software
 * options. The third panel contains the button that can remove the software
 * from the computer.
 * 
 * @author Bahram Malaekeh
 */
public class WebserverEditView extends JPanel implements SoftwareView, ActionListener
{
	// The name of the software object
	JTextField name = new JTextField(25);

	// The description of the software object.
	JTextArea desc = new JTextArea(3, 40);

	// Supported Operating systems
	private JList supportedOS;

	// List of operating systems
	private String[] OSs;

	// Supports Virtual hosting feature
	private JCheckBox hasVirtualHosting;

	// Supports HTTP compression
	private JCheckBox hasCompression;



	// DIFFERENT SECURITY FEATURES
	// Supports basic access authentication
	private JCheckBox supportsBasic;

	// Supports digest access authentication
	private JCheckBox supportsDigest;


	// SUPPORT FOR HTTPS
	// Supports SSL, Secure Sockets Layers
	private JCheckBox supportsSSL;

	// Supports TSL, Transport Layer Security
	private JCheckBox supportsTSL;


	// DIFFERENT SUPPORTED FEATURES
	// Supports IPv6
	private JCheckBox supportsIPv6;

	// Supports SSI, Server Side Includes
	private JCheckBox supportsSSI;

	// Supports CGI
	private JCheckBox supportsCGI;

	// Supports SCGI, Simple Common Gateway Interface
	private JCheckBox supportsSCGI;

	// Supports FastCGI
	private JCheckBox supportsFastCGI;

	// Supports JSP
	private JCheckBox supportsJSP;

	// Supports PHP
	private JCheckBox supportsPHP;

	// Supports ASP
	private JCheckBox supportsASP;

	// Supports ASP .net
	private JCheckBox supportsASPnet;



	private Object mainObj;

	private Webserver mainWebSer;

	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param webserver
	 *            The {@link Webserver Webserver} software.
	 */
	public WebserverEditView(Object obj, Webserver webserver)
	{
		mainObj = obj;
		mainWebSer = webserver;
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


		ImageIcon icon = PrimeMain.objectImageIcons.get(Webserver.class);
		JPanel p1 = SoftwareEditor.GeneralInfo(mainWebSer, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		JPanel p2 = createSpesificInfo(mainWebSer);
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
	 * @param webserver
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(Webserver webserver)
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

		// --------------------------------------------------------------

		// The supported operating systems by the Webserver software.
		JLabel osLabel = new JLabel(PrimeMain.texts
				.getString("fwViewSupOSLabel"));
		osLabel.setToolTipText(PrimeMain.texts.getString("fwViewSupOSTip"));
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

		if ( mainWebSer.getSupportedOperatingSystems() != null )
		{
			if ( mainWebSer.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, osNames, mainWebSer
								.getSupportedOperatingSystems()));
			}
		}

		c.gridx = 1; // column
		panel.add(listPane, c);

		// --------------------------------------------------------------

		// Whether or not the software supports Virtual hosting feature
		hasVirtualHosting = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupVirtualHostingLabel"));
		hasVirtualHosting.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupVirtualHostingTip"));
		hasVirtualHosting.setActionCommand("HasVirtualHosting");
		hasVirtualHosting.addActionListener(this);

		hasVirtualHosting.setSelected(mainWebSer.HasVirtualHosting());

		c.gridx = 2; // column
		panel.add(hasVirtualHosting, c);

		// --------------------------------------------------------------

		// Whether or not the software supports ASP .net
		supportsASPnet = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupASPNetLabel"));
		supportsASPnet.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupASPNetTip"));
		supportsASPnet.setActionCommand("SupportsASPnet");
		supportsASPnet.addActionListener(this);

		supportsASPnet.setSelected(mainWebSer.supportsASPnet());

		c.weightx = 1.0; // request any extra horizontal space
		c.gridx = 3; // column
		panel.add(supportsASPnet, c);

		// --------------------------------------------------------------

		// Whether or not the software supports basic access authentication
		supportsBasic = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupBasicAccessLabel"));
		supportsBasic.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupBasicAccessTip"));
		supportsBasic.setActionCommand("SupportsBasic");
		supportsBasic.addActionListener(this);

		supportsBasic.setSelected(mainWebSer.supportsBasic());

		c.insets = new Insets(10, 10, 10, 10); // padding
		c.weightx = 0; // request any extra horizontal space
		c.gridy = 1; // row
		c.gridx = 0; // column
		panel.add(supportsBasic, c);

		// --------------------------------------------------------------

		// Whether or not the software supports basic access authentication
		supportsDigest = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupDigestAccessLabel"));
		supportsDigest.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupDigestAccessTip"));
		supportsDigest.setActionCommand("SupportsDigest");
		supportsDigest.addActionListener(this);

		supportsDigest.setSelected(mainWebSer.supportsDigest());

		c.gridx = 1; // column
		panel.add(supportsDigest, c);

		// --------------------------------------------------------------

		// Whether or not the software supports SSL
		supportsSSL = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupSSLLabel"));
		supportsSSL.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupSSLTip"));
		supportsSSL.setActionCommand("SupportsSSL");
		supportsSSL.addActionListener(this);

		supportsSSL.setSelected(mainWebSer.supportsSSL());

		c.gridx = 2; // column
		panel.add(supportsSSL, c);

		// --------------------------------------------------------------

		// Whether or not the software supports TSL
		supportsTSL = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupTSLLabel"));
		supportsTSL.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupTSLTip"));
		supportsTSL.setActionCommand("SupportsTSL");
		supportsTSL.addActionListener(this);

		supportsTSL.setSelected(mainWebSer.supportsTSL());

		c.gridx = 3; // column
		panel.add(supportsTSL, c);

		// --------------------------------------------------------------

		// Whether or not the software supports IPv6
		supportsIPv6 = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupIPv6Label"));
		supportsIPv6.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupIPv6Tip"));
		supportsIPv6.setActionCommand("SupportsIPv6");
		supportsIPv6.addActionListener(this);

		supportsIPv6.setSelected(mainWebSer.supportsIPv6());

		c.gridy = 2; // row
		c.gridx = 0; // column
		panel.add(supportsIPv6, c);

		// --------------------------------------------------------------

		// Whether or not the software supports SSI, Server Side Includes
		supportsSSI = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupSSILabel"));
		supportsSSI.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupSSITip"));
		supportsSSI.setActionCommand("SupportsSSI");
		supportsSSI.addActionListener(this);

		supportsSSI.setSelected(mainWebSer.supportsSSI());

		c.gridx = 1; // column
		panel.add(supportsSSI, c);

		// --------------------------------------------------------------

		// Whether or not the software supports SSI, Server Side Includes
		supportsCGI = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupCGILabel"));
		supportsCGI.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupCGITip"));
		supportsCGI.setActionCommand("SupportsCGI");
		supportsCGI.addActionListener(this);

		supportsCGI.setSelected(mainWebSer.supportsCGI());

		c.gridx = 2; // column
		panel.add(supportsCGI, c);

		// --------------------------------------------------------------

		// Whether or not the software supports SCGI, Simple Common Gateway
		// Interface
		supportsSCGI = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupSCGILabel"));
		supportsSCGI.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupSCGITip"));
		supportsSCGI.setActionCommand("SupportsSCGI");
		supportsSCGI.addActionListener(this);

		supportsSCGI.setSelected(mainWebSer.supportsSCGI());

		c.gridx = 3; // column
		panel.add(supportsSCGI, c);

		// --------------------------------------------------------------

		// Whether or not the software supports FastCGI
		supportsFastCGI = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupFastCGILabel"));
		supportsFastCGI.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupFastCGITip"));
		supportsFastCGI.setActionCommand("SupportsFastCGI");
		supportsFastCGI.addActionListener(this);

		supportsFastCGI.setSelected(mainWebSer.supportsFastCGI());

		c.gridy = 3; // row
		c.gridx = 0; // column
		panel.add(supportsFastCGI, c);

		// --------------------------------------------------------------

		// Whether or not the software supports JSP
		supportsJSP = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupJSPLabel"));
		supportsJSP.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupJSPTip"));
		supportsJSP.setActionCommand("SupportsJSP");
		supportsJSP.addActionListener(this);

		supportsJSP.setSelected(mainWebSer.supportsJSP());

		c.gridx = 1; // column
		panel.add(supportsJSP, c);

		// --------------------------------------------------------------

		// Whether or not the software supports JSP
		supportsPHP = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupPHPLabel"));
		supportsPHP.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupPHPTip"));
		supportsPHP.setActionCommand("SupportsPHP");
		supportsPHP.addActionListener(this);

		supportsPHP.setSelected(mainWebSer.supportsPHP());

		c.gridx = 2; // column
		panel.add(supportsPHP, c);

		// --------------------------------------------------------------

		// Whether or not the software supports ASP
		supportsASP = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupASPLabel"));
		supportsASP.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupASPTip"));
		supportsASP.setActionCommand("SupportsASP");
		supportsASP.addActionListener(this);

		supportsASP.setSelected(mainWebSer.supportsASP());

		c.gridx = 3; // column
		panel.add(supportsASP, c);

		// --------------------------------------------------------------

		// Whether or not the software supports HTTP compression
		hasCompression = new JCheckBox(PrimeMain.texts
				.getString("webserverViewSupHTTPcompLabel"));
		hasCompression.setToolTipText(PrimeMain.texts
				.getString("webserverViewSupHTTPcompTip"));
		hasCompression.setActionCommand("HasCompression");
		hasCompression.addActionListener(this);

		hasCompression.setSelected(mainWebSer.HasCompression());


		c.weighty = 1.0; // request any extra horizontal space
		c.gridy = 4; // row
		c.gridx = 0; // column
		panel.add(hasCompression, c);

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
		if ( name.getText() != "" )
		{
			mainWebSer.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainWebSer.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainWebSer.setSupportedOperatingSystems(OSs);
		}


		mainWebSer.setHasVirtualHosting(hasVirtualHosting.isSelected());

		mainWebSer.setHasCompression(hasCompression.isSelected());

		mainWebSer.setSupportsBasic(supportsBasic.isSelected());

		mainWebSer.setSupportsDigest(supportsDigest.isSelected());

		mainWebSer.setSupportsSSL(supportsSSL.isSelected());

		mainWebSer.setSupportsTSL(supportsTSL.isSelected());

		mainWebSer.setSupportsIPv6(supportsIPv6.isSelected());

		mainWebSer.setSupportsSSI(supportsSSI.isSelected());

		mainWebSer.setSupportsCGI(supportsCGI.isSelected());

		mainWebSer.setSupportsSCGI(supportsSCGI.isSelected());

		mainWebSer.setSupportsFastCGI(supportsFastCGI.isSelected());

		mainWebSer.setSupportsJSP(supportsJSP.isSelected());

		mainWebSer.setSupportsPHP(supportsPHP.isSelected());

		mainWebSer.setSupportsASP(supportsASP.isSelected());

		mainWebSer.setSupportsASPnet(supportsASPnet.isSelected());
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
				DesktopSoftwareManagment.removeSoftware(mainObj, mainWebSer);

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

			if ( command.equals("HasVirtualHosting") )
			{

			}
			else if ( command.equals("HasCompression") )
			{

			}
			else if ( command.equals("SupportsBasic") )
			{

			}
			else if ( command.equals("SupportsDigest") )
			{

			}
			else if ( command.equals("SupportsSSL") )
			{

			}
			else if ( command.equals("SupportsTSL") )
			{

			}
			else if ( command.equals("SupportsIPv6") )
			{

			}
			else if ( command.equals("SupportsSSI") )
			{

			}
			else if ( command.equals("SupportsCGI") )
			{

			}
			else if ( command.equals("SupportsSCGI") )
			{

			}
			else if ( command.equals("SupportsFastCGI") )
			{

			}
			else if ( command.equals("SupportsJSP") )
			{

			}
			else if ( command.equals("supportsPHP") )
			{

			}
			else if ( command.equals("SupportsASP") )
			{

			}
			else if ( command.equals("SupportsASPnet") )
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
		return mainWebSer;
	}

}
