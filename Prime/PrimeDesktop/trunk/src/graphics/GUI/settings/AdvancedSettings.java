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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import actions.systemActions.ActionResetStandardObjects;
import actions.systemActions.ActionResetStandardRules;
import actions.systemActions.ActionResetSystemSettings;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class AdvancedSettings extends JPanel
{
	/**
	 * TODO - Description NEEDED!
	 */
	public AdvancedSettings(ActionListener lis, JComboBox localeCombo)
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



		JPanel hardPanel = getAdvancedPanel(lis, localeCombo);
		hardPanel.setBorder(BorderFactory.createTitledBorder(PrimeMain.texts
				.getString("settingsShowMsgHardwareLabel")));
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.insets = new Insets(0, 10, 10, 10); // padding
		this.add(hardPanel, d);
	}



	/**
	 * This function returns a {@link JPanel} that contains advanced settings
	 * options with descriptions.
	 * 
	 * @param localeCombo
	 * 
	 */
	private JPanel getAdvancedPanel(ActionListener lis, JComboBox localeCombo)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();


		d.fill = GridBagConstraints.HORIZONTAL;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column



		panel.add(localeCombo, d);

		JLabel localesss = new JLabel(
				PrimeMain.texts.getString("settingsAdvancedLanguageLabel"));
		d.gridx = 1; // column
		panel.add(localesss, d);



		// Reset system standard objects Button
		ActionResetStandardObjects resetStdObj = new ActionResetStandardObjects(
				PrimeMain.texts
						.getString("settingsAdvancedResetStdObjButtonLabel"));
		JButton resetStandardObjectsButton = new JButton(resetStdObj);
		d.gridy = 1; // row
		d.gridx = 0; // column
		panel.add(resetStandardObjectsButton, d);


		JLabel resetStandardObjectsLabel = new JLabel(
				PrimeMain.texts.getString("settingsAdvancedResetStdObjLabel"));
		d.gridx = 1; // row
		panel.add(resetStandardObjectsLabel, d);




		// Reset system standard rules Button
		ActionResetStandardRules resetStdRules = new ActionResetStandardRules(
				PrimeMain.texts
						.getString("settingsAdvancedResetStdRulesButtonLabel"));
		JButton resetStandardRulesButton = new JButton(resetStdRules);
		d.gridy = 2; // row
		d.gridx = 0; // column
		panel.add(resetStandardRulesButton, d);


		JLabel resetStandardRulesLabel = new JLabel(
				PrimeMain.texts.getString("settingsAdvancedResetStdRulesLabel"));
		d.gridx = 1; // row
		panel.add(resetStandardRulesLabel, d);




		// Reset system settings Button
		ActionResetSystemSettings resetSettings = new ActionResetSystemSettings(
				PrimeMain.texts
						.getString("settingsAdvancedResetSettingsButtonLabel"));
		JButton resetSettingsButton = new JButton(resetSettings);
		d.gridy = 3; // row
		d.gridx = 0; // column
		panel.add(resetSettingsButton, d);


		JLabel resetSettingsLabel = new JLabel(
				PrimeMain.texts.getString("settingsAdvancedResetSettingsLabel"));
		d.weightx = 1.0; // request any extra horizontal space
		d.weighty = 1.0; // request any extra vertical space
		d.gridx = 1; // row
		panel.add(resetSettingsLabel, d);


		return panel;
	}
}
