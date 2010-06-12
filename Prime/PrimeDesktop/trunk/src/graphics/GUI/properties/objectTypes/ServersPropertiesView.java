/**
 * 
 */
package graphics.GUI.properties.objectTypes;


import graphics.PrimeMain1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import objects.Object;
import objects.Servers;


/**
 * This class creates a properties JPanel for {@link Servers}.
 * 
 * @author Bahram Malaekeh
 */
public class ServersPropertiesView
{

	/**
	 * This function populates a returned JPanel with server information about
	 * the the given Object.
	 * 
	 * @param obj
	 */
	public static JPanel getServersPropertiesView(Object obj)
	{
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.HORIZONTAL;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		d.weightx = 0.8; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		d.insets = new Insets(0, 0, 5, 0); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column


		Servers server = (Servers) obj;


		// Supports on-site-access
		JLabel supOnSiteAccessLabel = new JLabel(PrimeMain1.texts
				.getString("propServerViewSupOnSiteAccessLabel"),
				SwingConstants.TRAILING);
		supOnSiteAccessLabel.setToolTipText(PrimeMain1.texts
				.getString("propServerViewSupOnSiteAccessTip"));
		d.insets = new Insets(0, 0, 5, 0); // padding
		d.gridy++; // row
		panel.add(supOnSiteAccessLabel, d);

		JCheckBox supOnSiteAccessField = new JCheckBox();
		supOnSiteAccessField.setSelected(server.supportsOnSiteAccess());
		supOnSiteAccessField.setName("supOnSiteAccess");
		supOnSiteAccessLabel.setLabelFor(supOnSiteAccessField);
		d.insets = new Insets(0, 0, 10, 0); // padding
		d.gridy++; // row
		panel.add(supOnSiteAccessField, d);


		// Supports Remote Access
		JLabel supRemoteAccessLabel = new JLabel(PrimeMain1.texts
				.getString("propServerViewSupRemoteAccessLabel"),
				SwingConstants.TRAILING);
		supRemoteAccessLabel.setToolTipText(PrimeMain1.texts
				.getString("propServerViewSupRemoteAccessTip"));
		d.insets = new Insets(0, 0, 5, 0); // padding
		d.gridy++; // row
		panel.add(supRemoteAccessLabel, d);

		JCheckBox supRemoteAccessField = new JCheckBox();
		supRemoteAccessField.setSelected(server.supportsRemoteAccess());
		supRemoteAccessField.setName("supRemoteAccess");
		supRemoteAccessLabel.setLabelFor(supRemoteAccessField);
		d.insets = new Insets(0, 0, 10, 0); // padding
		d.gridy++; // row
		panel.add(supRemoteAccessField, d);


		// Index 2 - Supported Remote Access Protocols
		JLabel supRemoteAccProtoLabel = new JLabel(PrimeMain1.texts
				.getString("propServerViewSupRemoteProtocolsLabel"),
				SwingConstants.TRAILING);
		supRemoteAccProtoLabel.setToolTipText(PrimeMain1.texts
				.getString("propServerViewSupRemoteProtocolsTip"));
		d.insets = new Insets(0, 0, 5, 0); // padding
		d.gridy++; // row
		panel.add(supRemoteAccProtoLabel, d);

		JTextField supRemoteAccProtoField = new JTextField(10);
		supRemoteAccProtoField.setName("supRemoteAccProto");
		supRemoteAccProtoLabel.setLabelFor(supRemoteAccProtoField);
		d.insets = new Insets(0, 0, 10, 0); // padding
		d.gridy++; // row
		panel.add(supRemoteAccProtoField, d);


		// Index 3 - Software name
		JLabel mainSWnameLabel = new JLabel(PrimeMain1.texts
				.getString("propServerViewSoftwareNameLabel"),
				SwingConstants.TRAILING);
		mainSWnameLabel.setToolTipText(PrimeMain1.texts
				.getString("propServerViewSoftwareNameTip"));
		d.insets = new Insets(0, 0, 5, 0); // padding
		d.gridy++; // row
		panel.add(mainSWnameLabel, d);

		JTextField mainSWnameField = new JTextField(10);
		mainSWnameField.setName("Main SW Name");
		mainSWnameLabel.setLabelFor(mainSWnameField);
		d.insets = new Insets(0, 0, 10, 0); // padding
		d.gridy++; // row
		panel.add(mainSWnameField, d);


		return panel;
	}
}
