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


import graphics.GraphicalFunctions;
import graphics.PrimeMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import managment.Settings;


/**
 * This class listens for the changes the user make in the system settings,
 * specifically in the {@link SettingsOverview} JFrame.
 * 
 * @author Bahram Malaekeh
 */
public class SettingsListener implements ActionListener
{
	private JCheckBox[] messageCheckBox;

	private JCheckBox[] displayCheckBox;

	private JComboBox systemLocale;

	private SettingsOverview settingsFrame;

	/**
	 * A constructor for the class.
	 * 
	 * @param panel
	 *            The panel where the checkboxes are located and where the user
	 *            will make desired changes.
	 * @param checkBox
	 *            The actual checkboxes set and unset by the user.
	 */
	public SettingsListener(SettingsOverview panel, JCheckBox[] msgBox,
			JCheckBox[] displayBox, JComboBox locale)
	{
		messageCheckBox = msgBox;
		displayCheckBox = displayBox;
		systemLocale = locale;
		settingsFrame = panel;
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
		if ( e.getSource() instanceof JButton )
		{
			JButton button = (JButton) e.getSource();
			String command = button.getActionCommand();

			if ( command.equals("save") )
			{
				setMessagesSettings();
				settingsFrame.dispose();
			}
			else if ( command.equals("apply") )
			{
				setMessagesSettings();
			}
			else if ( command.equals("cancel") )
			{
				settingsFrame.dispose();
			}
		}
		else if ( e.getActionCommand().equals(
				PrimeMain.texts.getString("settingsSelectAllLabel")) )
		{
			JCheckBox box = (JCheckBox) e.getSource();

			if ( box.isSelected() )
			{
				for ( int i = 0; i < SettingsOverview.messagesCheckBox.length; i++ )
				{
					SettingsOverview.messagesCheckBox[i].setSelected(true);
				}
			}
			else
			{
				for ( int i = 0; i < SettingsOverview.messagesCheckBox.length; i++ )
				{
					SettingsOverview.messagesCheckBox[i].setSelected(false);
				}
			}
		}
	}

	/**
	 * Sets the program settings reflecting what the classes checkboxes show.
	 */
	private void setMessagesSettings()
	{
		// MESSAGES
		Settings.showHardwareErrorMessages = messageCheckBox[0].isSelected();
		Settings.showHardwareWarningMessages = messageCheckBox[1].isSelected();
		Settings.showHardwareNoticeMessages = messageCheckBox[2].isSelected();

		Settings.showSoftwareErrorMessages = messageCheckBox[3].isSelected();
		Settings.showSoftwareWarningMessages = messageCheckBox[4].isSelected();
		Settings.showSoftwareNoticeMessages = messageCheckBox[5].isSelected();

		Settings.showConnectionErrorMessages = messageCheckBox[6].isSelected();
		Settings.showConnectionWarningMessages = messageCheckBox[7]
				.isSelected();
		Settings.showConnectionNoticeMessages = messageCheckBox[8].isSelected();

		Settings.showNetworkErrorMessages = messageCheckBox[9].isSelected();
		Settings.showNetworkWarningMessages = messageCheckBox[10].isSelected();
		Settings.showNetworkNoticeMessages = messageCheckBox[11].isSelected();


		// DISPLAY
		Settings.showIP = displayCheckBox[0].isSelected();
		Settings.showOSicon = displayCheckBox[1].isSelected();

		// Gets the new systemLocale
		managment.Settings.systemLocale newLocale = GraphicalFunctions
				.getSystemLocale(systemLocale.getSelectedItem().toString());

		if ( !newLocale.equals(Settings.primeLocale) )
		{
			JOptionPane.showMessageDialog(null,
					PrimeMain.texts.getString("settingsAdvancedLanguageMsg"));

			// Sets the locale
			Settings.primeLocale = newLocale;
		}


		PrimeMain.updateCanvasAndObjectInfo();
		PrimeMain.runAllCanvasUpdate();
	}
}
