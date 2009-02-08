/**
 * 
 */
package graphics.GUI.properties.objectTypes;


import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import objects.Object;


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
		Dimension tfSize = new Dimension(5, 20);


		// Supports on-site-access
		JLabel supOnSiteAccessLabel = new JLabel("Supports On-Site-Access",
				SwingConstants.TRAILING);
		supOnSiteAccessLabel
				.setToolTipText("Whether or not this device supports "
						+ "on-site-access, i.e. it has a keyboard, mouse and screen.");
		panel.add(supOnSiteAccessLabel);

		JTextField supOnSiteAccessField = new JTextField(10);
		supOnSiteAccessField.setMaximumSize(tfSize);
		supOnSiteAccessField.setPreferredSize(tfSize);
		supOnSiteAccessField.setName("supOnSiteAccess");
		supOnSiteAccessLabel.setLabelFor(supOnSiteAccessField);
		panel.add(supOnSiteAccessField);


		// Supports Remote Access
		JLabel supRemoteAccessLabel = new JLabel("Supports Remote Access");
		supRemoteAccessLabel
				.setToolTipText("Whether or not this device supports remote access.");
		panel.add(supRemoteAccessLabel);

		JTextField supRemoteAccessField = new JTextField(10);
		supRemoteAccessField.setMaximumSize(tfSize);
		supRemoteAccessField.setPreferredSize(tfSize);
		supOnSiteAccessField.setName("supRemoteAccess");
		supRemoteAccessLabel.setLabelFor(supRemoteAccessField);
		panel.add(supRemoteAccessField);


		// Index 2 - Supported Remote Access Protocols
		JLabel supRemoteAccProtoLabel = new JLabel("Supported Remote Protocols");
		supRemoteAccProtoLabel
				.setToolTipText("The supported remote access protocols.");
		panel.add(supRemoteAccProtoLabel);

		JTextField supRemoteAccProtoField = new JTextField(10);
		supRemoteAccProtoField.setMaximumSize(tfSize);
		supRemoteAccProtoField.setPreferredSize(tfSize);
		supOnSiteAccessField.setName("supRemoteAccProto");
		supRemoteAccProtoLabel.setLabelFor(supRemoteAccProtoField);
		panel.add(supRemoteAccProtoField);


		// Index 3 - Software name
		JLabel mainSWnameLabel = new JLabel("Software Name");
		mainSWnameLabel
				.setToolTipText("The name of the main software running on this device.");
		panel.add(mainSWnameLabel);

		JTextField mainSWnameField = new JTextField(10);
		mainSWnameField.setMaximumSize(tfSize);
		mainSWnameField.setPreferredSize(tfSize);
		supOnSiteAccessField.setName("mainSWname");
		mainSWnameLabel.setLabelFor(mainSWnameField);
		panel.add(mainSWnameField);

	}
}
