/**
 * 
 */
package graphics.GUI.properties.objectTypes;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import objects.Infrastructure;
import objects.Object;


/**
 * This class creates a properties JPanel for {@link Infrastructure}.
 * 
 * @author Bahram Malaekeh
 */
public class InfrastructuresPropertiesView
{

	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public static JPanel getInfrastructuresPropertiesView(Object obj)
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






		return panel;
	}
}
