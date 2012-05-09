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
package graphics.GUI.standardObjectEdit.StandardViews;


import graphics.PrimeMain;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import objects.Object;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ObjectViewPanel extends JPanel
{
	// The tabbedPane that will hold the two views
	private JTabbedPane pane;


	private HardwareObjectView HWview;


	private SoftwareObjectView SWview;


	/**
	 * A constructor for this class that takes an {@link Object} a variable.
	 */
	public ObjectViewPanel(Object obj)
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTHWEST; // location
		c.insets = new Insets(5, 5, 5, 5); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column



		pane = new JTabbedPane();

		HWview = new HardwareObjectView(obj);

		SWview = new SoftwareObjectView(obj);


		pane.addTab(PrimeMain.texts.getString("hardwareTabLabel"), null, HWview);


		pane.addTab(PrimeMain.texts.getString("softwareTabLabel"), null, SWview);


		this.add(pane, c);
	}


	/**
	 * This method removes all the JPanels showing the components information
	 * and then creates them again with the information from the given Object.
	 * This method is used when hardware/software object is changed.It also
	 * calls the update function in the Hardware/Software Editor view.
	 */
	public void changeObjectView(Object obj)
	{
		if ( HWview != null && SWview != null )
		{
			StandardObjectHardwareEditor objectHWview = HWview
					.getHardwareView();
			StandardObjectSoftwareEditor objectSWview = SWview
					.getSoftwareView();

			if ( objectHWview == null && objectSWview == null )
			{
				HWview.changeObjectView(obj);
				SWview.changeObjectView(obj);
			}
			else
			{
				if ( objectHWview != null )
				{
					objectHWview.toFront();
				}

				if ( objectSWview != null )
				{
					objectSWview.toFront();
				}
			}
		}

	}



	/**
	 * Gets the view that holds the hardware info about the currently selected
	 * object.
	 */
	public HardwareObjectView getHardStdObjView()
	{
		return HWview;
	}


	/**
	 * Gets the view that holds the software info about the currently selected
	 * object.
	 */
	public SoftwareObjectView getSoftStdObjView()
	{
		return SWview;
	}
}
