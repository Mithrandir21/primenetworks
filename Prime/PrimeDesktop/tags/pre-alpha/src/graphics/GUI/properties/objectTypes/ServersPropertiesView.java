/**
 * 
 */
package graphics.GUI.properties.objectTypes;


import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import objects.Object;
import objects.Servers;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ServersPropertiesView
{

	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public static void getServersPropertiesView(JPanel panel, Object obj)
	{
		Servers server = (Servers) obj;


		Dimension tfSize = new Dimension(5, 20);


		// Supports on-site-access
		JLabel supOnSiteAccessLabel = new JLabel("Supports On-Site-Access", SwingConstants.TRAILING);
		supOnSiteAccessLabel.setToolTipText("Whether or not this device supports "
				+ "on-site-access, i.e. it has a keyboard, mouse and screen.");
		panel.add(supOnSiteAccessLabel);

		JCheckBox supOnSiteAccessField = new JCheckBox();
		supOnSiteAccessField.setSelected(server.supportsOnSiteAccess());
		supOnSiteAccessField.setMaximumSize(tfSize);
		supOnSiteAccessField.setPreferredSize(tfSize);
		supOnSiteAccessField.setName("supOnSiteAccess");
		supOnSiteAccessLabel.setLabelFor(supOnSiteAccessField);
		panel.add(supOnSiteAccessField);


		// Supports Remote Access
		JLabel supRemoteAccessLabel = new JLabel("Supports Remote Access", SwingConstants.TRAILING);
		supRemoteAccessLabel.setToolTipText("Whether or not this device supports remote access.");
		panel.add(supRemoteAccessLabel);

		JCheckBox supRemoteAccessField = new JCheckBox();
		supRemoteAccessField.setSelected(server.supportsRemoteAccess());
		supRemoteAccessField.setMaximumSize(tfSize);
		supRemoteAccessField.setPreferredSize(tfSize);
		supRemoteAccessField.setName("supRemoteAccess");
		supRemoteAccessLabel.setLabelFor(supRemoteAccessField);
		panel.add(supRemoteAccessField);


		// Index 2 - Supported Remote Access Protocols
		JLabel supRemoteAccProtoLabel = new JLabel("Supported Remote Protocols", SwingConstants.TRAILING);
		supRemoteAccProtoLabel.setToolTipText("The supported remote access protocols.");
		panel.add(supRemoteAccProtoLabel);

		JTextField supRemoteAccProtoField = new JTextField(10);
		supRemoteAccProtoField.setMaximumSize(tfSize);
		supRemoteAccProtoField.setPreferredSize(tfSize);
		supRemoteAccProtoField.setName("supRemoteAccProto");
		supRemoteAccProtoLabel.setLabelFor(supRemoteAccProtoField);
		panel.add(supRemoteAccProtoField);


		// Index 3 - Software name
		JLabel mainSWnameLabel = new JLabel("Software Name", SwingConstants.TRAILING);
		mainSWnameLabel.setToolTipText("The name of the main software running on this device.");
		panel.add(mainSWnameLabel);

		JTextField mainSWnameField = new JTextField(10);
		mainSWnameField.setMaximumSize(tfSize);
		mainSWnameField.setPreferredSize(tfSize);
		mainSWnameField.setName("Main SW Name");
		mainSWnameLabel.setLabelFor(mainSWnameField);
		panel.add(mainSWnameField);

	}
}
