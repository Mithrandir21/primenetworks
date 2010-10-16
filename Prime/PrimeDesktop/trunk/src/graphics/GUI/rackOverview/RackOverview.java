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
package graphics.GUI.rackOverview;


import graphics.PrimeMain;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.rackUnits.Rack;
import widgets.WidgetObject;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class RackOverview extends JFrame implements ActionListener
{
	private WidgetObject widgetObj;

	private Rack rackObj;

	/**
	 * TODO - Description NEEDED!
	 * 
	 */
	public RackOverview(WidgetObject obj)
	{
		super(obj.getObject().getObjectName());

		if ( obj.getObject() instanceof Rack )
		{
			widgetObj = obj;

			rackObj = (Rack) obj.getObject();

			this.setIconImage(widgetObj.getImageWidget().getImage());


			// Get the default toolkit
			Toolkit toolkit = Toolkit.getDefaultToolkit();

			// Get the current screen size
			Dimension scrnsize = toolkit.getScreenSize();

			// Set size for the settings JFrame
			Dimension size = new Dimension(650, 625);

			int initYLocation = (scrnsize.height - size.height) / 3;
			int initXLocation = (scrnsize.width - size.width) / 2;

			// Get the content pane for this object
			Container c = this.getContentPane();
			JPanel panel = new JPanel();
			GridBagConstraints d = new GridBagConstraints();

			d.fill = GridBagConstraints.BOTH;
			// c.ipady = 0; // reset to default
			// c.ipadx = 0; // reset to default
			// c.weighty = 1.0; // request any extra vertical space
			d.weightx = 1.0; // request any extra horizontal space
			d.anchor = GridBagConstraints.WEST; // location
			d.insets = new Insets(10, 10, 5, 10); // padding
			// c.gridwidth = 1; // 1 row wide
			// c.gridheight = 1; // 1 columns wide
			d.gridy = 0; // row
			d.gridx = 0; // column







			c.add(panel);

			this.setPreferredSize(size);
			this.setLocation(initXLocation, initYLocation);
			this.setMinimumSize(size);
			this.setVisible(true);
		}


		// Resets the objectView object when closed.
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{
				PrimeMain.removeRackView(rackObj);
			}
		});
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
		// TODO Auto-generated method stub
	}


	/**
	 * TODO - Description
	 * 
	 */
	public Rack getRackObject()
	{
		return rackObj;
	}

}
