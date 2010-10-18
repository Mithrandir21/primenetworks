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
package graphics.GUI.customNetworks;


import graphics.ImageLocator;
import graphics.PrimeMain;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXTaskPaneContainer;

import widgetManipulation.NetworkRules;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class StandardRulesFrame extends JDialog implements ActionListener
{
	/**
	 * A boolean on whether there has been any changes to the rules.
	 */
	public static boolean changed = false;


	/**
	 * TODO - Description NEEDED!
	 * 
	 */
	public StandardRulesFrame()
	{
		this.setTitle(PrimeMain.texts
				.getString("openStandardNetworkRulesLabel"));

		ImageIcon frameIcon = ImageLocator.getImageIconObject("Rules");
		this.setIconImage(frameIcon.getImage());

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(650, 560);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;



		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		// d.insets = new Insets(0, 0, 5, 0); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column


		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(createRulesTabes());

		this.add(scroll, d);

		// d.insets = new Insets(0, 0, 0, 5); // padding
		d.weighty = 0; // request any extra vertical space
		d.weightx = 0; // request any extra horizontal space
		d.gridy = 1; // row
		d.gridx = 0; // column
		this.add(createButtons(this), d);




		this.setPreferredSize(size);
		this.setLocation(initXLocation, initYLocation);
		this.setMinimumSize(size);
		this.setVisible(true);


		// Resets the ruleFrame object when closed.
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{
				// Removes the pointer to this Object in the system registry.
				PrimeMain.rulesFrame = null;
			}
		});


		JOptionPane.showMessageDialog(this,
				PrimeMain.texts.getString("standardRulesWarningMsg"),
				PrimeMain.texts.getString("caution"),
				JOptionPane.WARNING_MESSAGE);
	}



	/**
	 * Creates a {@link JXTaskPaneContainer} that will contain options for
	 * different aspects of the {@link NetworkRules}.
	 */
	private JXTaskPaneContainer createRulesTabes()
	{
		JXTaskPaneContainer tpc = new JXTaskPaneContainer();

		tpc.add(new HardwareRulesPanel(PrimeMain.standardRules));
		tpc.add(new SoftwareRulesPanel(PrimeMain.standardRules));
		tpc.add(new InfrastructureRulesPanel(PrimeMain.standardRules));


		return tpc;
	}








	/**
	 * Creates a JPanel with two buttons that are listened for by the given
	 * actionlisteners.
	 */
	public static JPanel createButtons(ActionListener lis)
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));


		Button save = new Button(PrimeMain.texts.getString("save"));
		save.addActionListener(lis);
		save.setActionCommand(PrimeMain.texts.getString("save"));

		Button cancel = new Button(PrimeMain.texts.getString("cancel"));
		cancel.addActionListener(lis);
		cancel.setActionCommand(PrimeMain.texts.getString("cancel"));

		buttons.add(save);
		buttons.add(cancel);

		return buttons;
	}


	/**
	 * TODO - Description
	 * 
	 */
	private void saveRules(NetworkRules rules)
	{
		// HARDWARE RULES


		// The USB not allowed rule
		rules.setUSBnotAllowed(HardwareRulesPanel.USBnotAllowedBox.isSelected());

		// The USB ports number
		if ( HardwareRulesPanel.USBports.getSelectedItem().toString()
				.equals("Unlimited") )
		{
			rules.setUSBportsAllowed(-1);
		}
		else
		{
			rules.setUSBportsAllowed(Integer
					.parseInt(HardwareRulesPanel.USBports.getSelectedItem()
							.toString()));
		}



		// The LAN not allowed rule
		rules.setLANnotAllowed(HardwareRulesPanel.LANnotAllowedBox.isSelected());

		// The LAN ports number
		if ( HardwareRulesPanel.LANports.getSelectedItem().toString()
				.equals("Unlimited") )
		{
			rules.setLANportsAllowed(-1);
		}
		else
		{
			rules.setLANportsAllowed(Integer
					.parseInt(HardwareRulesPanel.LANports.getSelectedItem()
							.toString()));
		}


		// SOFTWARE RULES

		// // OS restriction
		// rules
		// .setOSrestriction(SoftwareRulesPanel.OSrestrictionBox
		// .isSelected());
		//
		// if ( rules.isOSrestriction() )
		// {
		// rules.setOSrestrictedName(SoftwareRulesPanel.OSresName.getText());
		// }
		// else
		// {
		// rules.setOSrestrictedName("");
		// }
		//
		//
		// // AV restriction
		// rules
		// .setAVrestriction(SoftwareRulesPanel.AVrestrictionBox
		// .isSelected());
		//
		// if ( rules.isAVrestriction() )
		// {
		// rules.setAVrestrictedName(SoftwareRulesPanel.AVresName.getText());
		// }
		// else
		// {
		// rules.setAVrestrictedName("");
		// }
		//
		//
		// // FW restriction
		// rules
		// .setFWrestriction(SoftwareRulesPanel.FWrestrictionBox
		// .isSelected());
		//
		// if ( rules.isFWrestriction() )
		// {
		// rules.setFWrestrictedName(SoftwareRulesPanel.FWresName.getText());
		// }
		// else
		// {
		// rules.setFWrestrictedName("");
		// }
		//
		//
		// // Email restriction
		// rules.setEMailRestriction(SoftwareRulesPanel.EmailRestrictionBox
		// .isSelected());
		//
		// if ( rules.isEMailRestriction() )
		// {
		// rules.setEMailRestrictedName(SoftwareRulesPanel.EmailResName
		// .getText());
		// }
		// else
		// {
		// rules.setEMailRestrictedName("");
		// }
		//
		//
		// // Office suite restriction
		// rules
		// .setOfficeSuiteRestriction(SoftwareRulesPanel.OfficeSuiteRestrictionBox
		// .isSelected());
		//
		// if ( rules.isOfficeSuiteRestriction() )
		// {
		// rules
		// .setOfficeSuiteRestrictedName(SoftwareRulesPanel.OfficeSuiteResName
		// .getText());
		// }
		// else
		// {
		// rules.setOfficeSuiteRestrictedName("");
		// }



		// INFRASTRUCTURE RULES

		// Internet allowed
		rules.setCanConnectToInternet(InfrastructureRulesPanel.internetAllowedBox
				.isSelected());

		// // Antivirus before internet
		// rules
		// .setMustHaveAVbeforeInternet(InfrastructureRulesPanel.AVbeforeInternet
		// .isSelected());
		//
		// // Firewall before internet
		// rules
		// .setMustHaveFWbeforeInternet(InfrastructureRulesPanel.FWbeforeInternet
		// .isSelected());

		// Hub allowed
		rules.setCanContainHub(InfrastructureRulesPanel.hubAllowed.isSelected());

		// Wireless allowed
		rules.setCanContainWirelessRouter(InfrastructureRulesPanel.wirelessAllowed
				.isSelected());
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
		if ( e.getActionCommand().equals(PrimeMain.texts.getString("save")) )
		{
			String question = PrimeMain.texts
					.getString("standardRulesChangesMsg");


			// Custom button text
			Object[] options = { PrimeMain.texts.getString("yes"),
					PrimeMain.texts.getString("no") };


			int i = JOptionPane.showOptionDialog(null, question,
					PrimeMain.texts.getString("confirm"),
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			// If the answer is yes
			if ( i == 0 )
			{
				saveRules(PrimeMain.standardRules);

				this.dispose();

				// Removes the pointer to this Object in the system registry.
				PrimeMain.rulesFrame = null;
			}
		}
		else if ( e.getActionCommand().equals(
				PrimeMain.texts.getString("cancel")) )
		{
			this.dispose();
		}
	}

}
