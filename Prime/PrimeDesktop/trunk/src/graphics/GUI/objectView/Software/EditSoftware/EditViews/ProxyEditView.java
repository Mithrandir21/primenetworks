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
import objects.softwareObjects.Proxy;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link Proxy Proxy} Software. The panel is made up of 3
 * JPanel ordered in a column. The first one contains the name and description
 * of the object. The second panel contains the specific software options. The
 * third panel contains the button that can remove the software from the
 * computer.
 * 
 * @author Bahram Malaekeh
 */
public class ProxyEditView extends JPanel implements SoftwareView,
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

	// Has caching feature
	private JCheckBox caching;

	// Has Web proxy feature
	private JCheckBox webProxy;

	// Has Anonymizing proxy feature
	private JCheckBox anonymizingProxy;

	// Has transparent proxy feature
	private JCheckBox transparentProxy;

	// Has reverse proxy feature
	private JCheckBox reverseProxy;


	// DIFFERENT SUPPORTED FEATURES
	// Supports IP version 6
	private JCheckBox supportsIPv6;

	// Supports SSL
	private JCheckBox supportsSSL;

	// Supports TLS
	private JCheckBox supportsTSL;

	// Supports HTTPS
	private JCheckBox supportsHTTPS;



	private Object mainObj;

	private Proxy mainProxy;

	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param proxy
	 *            The {@link Proxy Proxy} software.
	 */
	public ProxyEditView(Object obj, Proxy proxy)
	{
		mainObj = obj;
		mainProxy = proxy;
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

		ImageIcon icon = PrimeMain.objectImageIcons.get(Proxy.class);
		JPanel p1 = SoftwareEditor.GeneralInfo(mainProxy, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		JPanel p2 = createSpesificInfo(mainProxy);
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
	 * @param proxy
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(Proxy proxy)
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

		// The supported operating systems by the Proxy software.
		JLabel osLabel = new JLabel(
				PrimeMain.texts.getString("proxyViewSupOSLabel"));
		osLabel.setToolTipText(PrimeMain.texts.getString("proxyViewSupOSTip"));
		panel.add(osLabel, c);


		String[] osNames = DesktopSoftwareManagment.getSystemOSname();
		supportedOS = new JList(osNames);
		ListSelectionModel listSelectionModel = supportedOS.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(supportedOS);
		listPane.setMaximumSize(new Dimension(90, 60));
		listPane.setPreferredSize(new Dimension(90, 60));
		listSelectionModel
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		if ( mainProxy.getSupportedOperatingSystems() != null )
		{
			if ( mainProxy.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, osNames,
						mainProxy.getSupportedOperatingSystems()));
			}
		}

		c.gridx = 1; // column
		panel.add(listPane, c);



		// Whether or not the software has caching feature
		caching = new JCheckBox(
				PrimeMain.texts.getString("proxyViewCachingLabel"));
		caching.setToolTipText(PrimeMain.texts.getString("proxyViewCachingTip"));
		caching.setActionCommand("Caching");
		caching.addActionListener(this);

		caching.setSelected(mainProxy.hasCaching());

		c.gridx = 2; // column
		panel.add(caching, c);


		// Whether or not the software has Web proxy feature
		webProxy = new JCheckBox(
				PrimeMain.texts.getString("proxyViewProxyLabel"));
		webProxy.setToolTipText(PrimeMain.texts.getString("proxyViewProxyTip"));
		webProxy.setActionCommand("WebProxy");
		webProxy.addActionListener(this);

		webProxy.setSelected(mainProxy.hasWebProxy());

		c.weightx = 1.0; // request any extra horizontal space
		c.gridx = 3; // column
		panel.add(webProxy, c);



		// Whether or not the software has Anonymizing proxy feature
		anonymizingProxy = new JCheckBox(
				PrimeMain.texts.getString("proxyViewAnonymizingLabel"));
		anonymizingProxy.setToolTipText(PrimeMain.texts
				.getString("proxyViewAnonymizingTip"));
		anonymizingProxy.setActionCommand("Anonymizing");
		anonymizingProxy.addActionListener(this);

		anonymizingProxy.setSelected(mainProxy.hasAnonymizingProxy());

		c.insets = new Insets(10, 10, 10, 10); // padding
		c.gridwidth = 1; // 2 row wide
		c.weightx = 0; // request any extra horizontal space
		c.gridy = 1; // row
		c.gridx = 0; // column
		panel.add(anonymizingProxy, c);



		// Whether or not the software has transparent proxy feature
		transparentProxy = new JCheckBox(
				PrimeMain.texts.getString("proxyViewTransProxyLabel"));
		transparentProxy.setToolTipText(PrimeMain.texts
				.getString("proxyViewTransProxyTip"));
		transparentProxy.setActionCommand("Transparent");
		transparentProxy.addActionListener(this);

		transparentProxy.setSelected(mainProxy.hasTransparentProxy());

		c.gridwidth = 1; // 1 row wide
		c.gridx = 1; // column
		panel.add(transparentProxy, c);


		// Whether or not the software has reverse proxy feature
		reverseProxy = new JCheckBox(
				PrimeMain.texts.getString("proxyViewReverseProxyLabel"));
		reverseProxy.setToolTipText(PrimeMain.texts
				.getString("proxyViewReverseProxyTip"));
		reverseProxy.setActionCommand("ReverseProxy");
		reverseProxy.addActionListener(this);

		reverseProxy.setSelected(mainProxy.hasReverseProxy());

		c.gridx = 2; // column
		panel.add(reverseProxy, c);


		// Whether or not the software supports IP version 6
		supportsIPv6 = new JCheckBox(
				PrimeMain.texts.getString("proxyViewIPv6Label"));
		supportsIPv6.setToolTipText(PrimeMain.texts
				.getString("proxyViewIPv6Tip"));
		supportsIPv6.setActionCommand("SupportsIPv6");
		supportsIPv6.addActionListener(this);

		supportsIPv6.setSelected(mainProxy.supportsIPv6());

		c.gridx = 3; // column
		panel.add(supportsIPv6, c);


		// Whether or not the software supports SSL
		supportsSSL = new JCheckBox(
				PrimeMain.texts.getString("proxyViewSupSSLLabel"));
		supportsSSL.setToolTipText(PrimeMain.texts
				.getString("proxyViewSupSSLTip"));
		supportsSSL.setActionCommand("SupportsSSL");
		supportsSSL.addActionListener(this);

		supportsSSL.setSelected(mainProxy.supportsSSL());

		c.gridwidth = 1; // 2 row wide
		c.gridy = 2; // row
		c.gridx = 0; // column
		panel.add(supportsSSL, c);


		// Whether or not the software supports TSL
		supportsTSL = new JCheckBox(
				PrimeMain.texts.getString("proxyViewSupTSLLabel"));
		supportsTSL.setToolTipText(PrimeMain.texts
				.getString("proxyViewSupTSLTip"));
		supportsTSL.setActionCommand("SupportsTSL");
		supportsTSL.addActionListener(this);

		supportsTSL.setSelected(mainProxy.supportsTSL());

		c.gridwidth = 1; // 1 row wide
		c.gridx = 1; // column
		panel.add(supportsTSL, c);


		// Whether or not the software supports HTTPS
		supportsHTTPS = new JCheckBox(
				PrimeMain.texts.getString("proxyViewSupHTTPSLabel"));
		supportsHTTPS.setToolTipText(PrimeMain.texts
				.getString("proxyViewSupHTTPSTip"));
		supportsHTTPS.setActionCommand("SupportsHTTPS");
		supportsHTTPS.addActionListener(this);

		supportsHTTPS.setSelected(mainProxy.supportsHTTPS());

		c.weighty = 1.0; // request any extra horizontal space
		c.gridx = 2; // column
		panel.add(supportsHTTPS, c);


		return panel;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see graphics.GUI.objectView.Software.SoftwareEditView#save()
	 */
	@Override
	public void save()
	{
		if ( name.getText() != "" )
		{
			mainProxy.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainProxy.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainProxy.setSupportedOperatingSystems(OSs);
		}

		mainProxy.setSupportsCaching(caching.isSelected());

		mainProxy.setSupportsWebProxy(webProxy.isSelected());

		mainProxy.setSupportsAnonymizingProxy(anonymizingProxy.isSelected());

		mainProxy.setSupportsTransparentProxy(transparentProxy.isSelected());

		mainProxy.setSupportsReverseProxy(reverseProxy.isSelected());

		mainProxy.setSupportsIPv6(supportsIPv6.isSelected());

		mainProxy.setSupportsSSL(supportsSSL.isSelected());

		mainProxy.setSupportsTSL(supportsTSL.isSelected());

		mainProxy.setSupportsHTTPS(supportsHTTPS.isSelected());
	}

	/*
	 * (non-Javadoc)
	 * 
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
				DesktopSoftwareManagment.removeSoftware(mainObj, mainProxy);

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

			if ( command.equals("Caching") )
			{

			}
			else if ( command.equals("WebProxy") )
			{

			}
			else if ( command.equals("Anonymizing") )
			{

			}
			else if ( command.equals("Transparent") )
			{

			}
			else if ( command.equals("ReverseProxy") )
			{

			}
			else if ( command.equals("SupportsIPv6") )
			{

			}
			else if ( command.equals("SupportsSSL") )
			{

			}
			else if ( command.equals("SupportsTSL") )
			{

			}
			else if ( command.equals("SupportsHTTPS") )
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
		return mainProxy;
	}
}
