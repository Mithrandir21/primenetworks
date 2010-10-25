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
import graphics.ImageLocator;
import graphics.PrimeMain;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import managment.Settings;


/**
 * This JFrame extension will contain the framework for the settings JFrame.
 * 
 * @author Bahram Malaekeh
 */
public class SettingsOverview extends JFrame
{
	public static JCheckBox[] messagesCheckBox = new JCheckBox[12];

	public static JCheckBox[] displayCheckBox = new JCheckBox[3];

	public static JComboBox localeCombo;

	/**
	 * A constructor for the class that sets up the settings variables and
	 * JPanels of the display.
	 */
	public SettingsOverview()
	{
		super(PrimeMain.texts.getString("settingsFrameLabel"));
		Dimension size = new Dimension(600, 530);

		setUpMessageCheckBoxes();

		SettingsListener setListener = new SettingsListener(this,
				messagesCheckBox, displayCheckBox, localeCombo);

		// Get the content pane for this object
		Container c = this.getContentPane();

		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();



		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;

		ImageIcon frameIcon = ImageLocator.getImageIconObject("Settings");
		this.setIconImage(frameIcon.getImage());




		panel.add(getSettingsTabs(setListener));



		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		JButton save = new JButton(PrimeMain.texts.getString("save"));
		save.addActionListener(setListener);
		save.setActionCommand("save");

		JButton apply = new JButton(PrimeMain.texts.getString("apply"));
		apply.addActionListener(setListener);
		apply.setActionCommand("apply");

		JButton cancel = new JButton(PrimeMain.texts.getString("cancel"));
		cancel.addActionListener(setListener);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(apply);
		buttons.add(cancel);

		buttons.setMaximumSize(new Dimension((int) scrnsize.getWidth(), 1));

		panel.add(buttons);


		c.add(panel);



		this.setPreferredSize(size);
		this.setMinimumSize(size);
		// this.setResizable(false);
		this.setLocation(initXLocation, initYLocation);
		this.setVisible(true);
	}




	/**
	 * Creates a JTabbedPane where a {@link NetworkMessagesSettings} panel is
	 * placed.
	 * 
	 * @return A JTabbedPane with a {@link NetworkMessagesSettings} panel
	 *         inside.
	 */
	private JTabbedPane getSettingsTabs(ActionListener lis)
	{
		JTabbedPane tabs = new JTabbedPane();

		ImageIcon frameIcon = ImageLocator
				.getImageIconObject("ProcessingSettings");
		tabs.addTab(PrimeMain.texts.getString("settingsShowMsgPanelLabel"),
				frameIcon, new NetworkMessagesSettings(messagesCheckBox, lis),
				PrimeMain.texts.getString("settingsShowMsgPanelTip"));

		tabs.addTab(PrimeMain.texts.getString("settingsDisplayPanelLabel"),
				frameIcon, new DisplaySettings(displayCheckBox, lis),
				PrimeMain.texts.getString("settingsDisplayPanelTip"));

		tabs.addTab(PrimeMain.texts.getString("settingsAdvancedPanelLabel"),
				frameIcon, new AdvancedSettings(lis, localeCombo),
				PrimeMain.texts.getString("settingsAdvancedPanelTip"));

		return tabs;
	}


	/**
	 * Sets up the check boxes with the correct variables, the program settings.
	 */
	private void setUpMessageCheckBoxes()
	{
		// MESSAGES
		messagesCheckBox[0] = new JCheckBox();
		messagesCheckBox[0].setSelected(Settings.showHardwareErrorMessages);

		messagesCheckBox[1] = new JCheckBox();
		messagesCheckBox[1].setSelected(Settings.showHardwareWarningMessages);

		messagesCheckBox[2] = new JCheckBox();
		messagesCheckBox[2].setSelected(Settings.showHardwareNoticeMessages);

		messagesCheckBox[3] = new JCheckBox();
		messagesCheckBox[3].setSelected(Settings.showSoftwareErrorMessages);

		messagesCheckBox[4] = new JCheckBox();
		messagesCheckBox[4].setSelected(Settings.showSoftwareWarningMessages);

		messagesCheckBox[5] = new JCheckBox();
		messagesCheckBox[5].setSelected(Settings.showSoftwareNoticeMessages);

		messagesCheckBox[6] = new JCheckBox();
		messagesCheckBox[6].setSelected(Settings.showConnectionErrorMessages);

		messagesCheckBox[7] = new JCheckBox();
		messagesCheckBox[7].setSelected(Settings.showConnectionWarningMessages);

		messagesCheckBox[8] = new JCheckBox();
		messagesCheckBox[8].setSelected(Settings.showConnectionNoticeMessages);

		messagesCheckBox[9] = new JCheckBox();
		messagesCheckBox[9].setSelected(Settings.showNetworkErrorMessages);

		messagesCheckBox[10] = new JCheckBox();
		messagesCheckBox[10].setSelected(Settings.showNetworkWarningMessages);

		messagesCheckBox[11] = new JCheckBox();
		messagesCheckBox[11].setSelected(Settings.showNetworkNoticeMessages);


		// DISPLAY
		displayCheckBox[0] = new JCheckBox();
		displayCheckBox[0].setSelected(Settings.showIP);

		displayCheckBox[1] = new JCheckBox();
		displayCheckBox[1].setSelected(Settings.showOSicon);

		displayCheckBox[2] = new JCheckBox();
		displayCheckBox[2].setSelected(Settings.showTOTD);


		// LOCALES
		String[] locales = { PrimeMain.texts.getString("english"),
				PrimeMain.texts.getString("norwegian") };

		localeCombo = new JComboBox(locales);
		localeCombo.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				locales,
				GraphicalFunctions.getSystemLocale(Settings.primeLocale)));
		localeCombo.setBackground(Color.WHITE);
	}

}
