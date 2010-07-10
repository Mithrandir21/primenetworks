/**
 * 
 */
package graphics.GUI.customNetworks;


import graphics.GraphicalFunctions;
import graphics.PrimeMain;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXTaskPane;

import widgetManipulation.NetworkRules;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class HardwareRulesPanel extends JXTaskPane implements ActionListener
{
	/**
	 * USB allowed.
	 */
	public static JCheckBox USBnotAllowedBox;


	/**
	 * The number of USB ports.
	 */
	public static JComboBox USBports = new JComboBox();

	/**
	 * LAN allowed.
	 */
	public static JCheckBox LANnotAllowedBox;


	/**
	 * The number of LAN ports.
	 */
	public static JComboBox LANports = new JComboBox();




	/**
	 * TODO - Description NEEDED!
	 * 
	 */
	public HardwareRulesPanel(NetworkRules rules)
	{
		this
				.setTitle(PrimeMain.texts
						.getString("networkRulesHardwareTabLabel"));
		this.setSpecial(true);
		this.setCollapsed(false);


		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.NONE;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTHWEST; // location
		// d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column

		String[] numbers = { "Unlimited", "1", "2", "3", "4", "5", "6" };

		USBnotAllowedBox = new JCheckBox(PrimeMain.texts
				.getString("hardwareRulesUSBnotAllowedLabel"), rules
				.isUSBnotAllowed());
		USBnotAllowedBox.setToolTipText(PrimeMain.texts
				.getString("hardwareRulesUSBnotAllowedTip"));

		USBports = new JComboBox(numbers);
		USBports.addActionListener(this);
		USBports.setBackground(Color.WHITE);

		USBports.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				numbers, rules.getUSBportsAllowed()));


		this.add(createOptionPanel(USBnotAllowedBox, PrimeMain.texts
				.getString("hardwareRulesUSBportsAllowedLabel"), USBports), d);


		LANnotAllowedBox = new JCheckBox(PrimeMain.texts
				.getString("hardwareRulesLANnotAllowedLabel"), rules
				.isLANnotAllowed());
		LANnotAllowedBox.setToolTipText(PrimeMain.texts
				.getString("hardwareRulesLANnotAllowedTip"));

		LANports = new JComboBox(numbers);
		LANports.addActionListener(this);
		LANports.setBackground(Color.WHITE);

		LANports.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				numbers, rules.getLANportsAllowed()));

		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.gridy = 0; // row
		d.gridx = 1; // column
		this.add(createOptionPanel(LANnotAllowedBox, PrimeMain.texts
				.getString("hardwareRulesLANportsAllowedLabel"), LANports), d);
	}




	/**
	 * TODO - Description
	 * 
	 */
	private JPanel createOptionPanel(JCheckBox check, String comboString,
			JComboBox box)
	{
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		d.insets = new Insets(5, 5, 0, 5); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column

		check.addActionListener(this);
		panel.add(check, d);


		JPanel numberPanel = new JPanel(new GridBagLayout());
		GridBagConstraints numberCons = new GridBagConstraints();
		numberPanel.setBorder(BorderFactory.createEtchedBorder());

		numberCons.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		numberCons.anchor = GridBagConstraints.NORTH; // location
		numberCons.insets = new Insets(5, 5, 5, 5); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		numberCons.gridy = 0; // row
		numberCons.gridx = 0; // column

		// numberCons.insets = new Insets(5, 20, 0, 5); // padding
		numberPanel.add(new JLabel(comboString), numberCons);


		box.setEnabled(!check.isSelected());

		// numberCons.insets = new Insets(0, 20, 0, 0); // padding
		numberCons.gridy = 1; // row
		numberPanel.add(box, numberCons);



		d.insets = new Insets(0, 5, 0, 5); // padding
		d.gridy = 1; // row
		panel.add(numberPanel, d);

		return panel;
	}




	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource().equals(USBnotAllowedBox) )
		{
			JCheckBox usb = (JCheckBox) e.getSource();

			USBports.setEnabled(!usb.isSelected());
		}
		else if ( e.getSource().equals(LANnotAllowedBox) )
		{
			JCheckBox lan = (JCheckBox) e.getSource();

			LANports.setEnabled(!lan.isSelected());
		}
		NetworkRulesFrame.changed = true;
	}
}
