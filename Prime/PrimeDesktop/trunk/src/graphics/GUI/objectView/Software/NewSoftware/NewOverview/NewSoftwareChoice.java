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
package graphics.GUI.objectView.Software.NewSoftware.NewOverview;


import graphics.PrimeMain;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;

import objects.Object;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class NewSoftwareChoice extends JDialog
{
	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public NewSoftwareChoice(Object obj)
	{
		this.setTitle(PrimeMain.texts.getString("swTabSWnewSoftware"));

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));


		// Get the content pane for this object
		Container c = this.getContentPane();

		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


		panel.add(new NewSoftwaresViews(obj));


		this.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();

		cons.fill = GridBagConstraints.BOTH;

		cons.gridx = 0;
		cons.gridy = 0;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.insets = new Insets(10, 10, 10, 10);



		c.add(panel, cons);

		this.setMinimumSize(new Dimension((int) scrnsize.getWidth() / 3,
				(int) scrnsize.getHeight() / 3));
		this.setSize(width, height);
		this.setVisible(true);
	}
}
