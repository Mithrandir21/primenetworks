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
package graphics.GUI.settings;


import graphics.PrimeMain;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class DisplaySettings extends JPanel
{
	/**
	 * TODO - Description NEEDED!
	 */
	public DisplaySettings(JCheckBox[] checkBox, ActionListener lis)
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();


		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		d.insets = new Insets(10, 20, 5, 20); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column



		JPanel hardPanel = getDisplayPanel(checkBox);
		hardPanel.setBorder(BorderFactory.createTitledBorder(PrimeMain.texts
				.getString("settingsShowMsgHardwareLabel")));
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.insets = new Insets(0, 10, 10, 10); // padding
		this.add(hardPanel, d);
	}



	/**
	 * Creates a JPanel where the checkboxes given are placed vertical in a row.
	 */
	private JPanel getDisplayPanel(JCheckBox[] checkBox)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();


		d.fill = GridBagConstraints.HORIZONTAL;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column


		// CheckBox 0 - Display Widget IP
		checkBox[0].setText(PrimeMain.texts
				.getString("settingsShowWidgetIpText"));
		checkBox[0].setToolTipText(PrimeMain.texts
				.getString("settingsShowWidgetIpText"));
		panel.add(checkBox[0], d);


		// CheckBox 1 - Display Widget OperatingSystem icon
		checkBox[1].setText(PrimeMain.texts
				.getString("settingsShowWidgetOSiconText"));
		checkBox[1].setToolTipText(PrimeMain.texts
				.getString("settingsShowWidgetOSiconText"));
		d.gridy = 1; // row
		panel.add(checkBox[1], d);


		// CheckBox 2 - Display TOTD
		checkBox[2].setText(PrimeMain.texts.getString("settingsShowTOTDText"));
		checkBox[2].setToolTipText(PrimeMain.texts
				.getString("settingsShowTOTDText"));
		d.gridy = 2; // row
		panel.add(checkBox[2], d);


		// CheckBox 3 - Display Splash screen
		checkBox[3].setText(PrimeMain.texts
				.getString("settingsShowSplashScreen"));
		checkBox[3].setToolTipText(PrimeMain.texts
				.getString("settingsShowSplashScreen"));
		d.gridy = 3; // row
		panel.add(checkBox[3], d);


		// CheckBox 4 - Display Check Update
		checkBox[4].setText(PrimeMain.texts.getString("settingsCheckUpdates"));
		checkBox[4].setToolTipText(PrimeMain.texts
				.getString("settingsCheckUpdates"));
		d.weighty = 1.0; // request any extra vertical space
		d.gridy = 4; // row
		panel.add(checkBox[4], d);


		return panel;
	}
}
