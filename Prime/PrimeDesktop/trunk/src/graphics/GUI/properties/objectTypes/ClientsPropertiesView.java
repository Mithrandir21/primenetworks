/**
 * 
 */
package graphics.GUI.properties.objectTypes;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import objects.Clients;
import objects.Object;
import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.clientObjects.ThinClient;


/**
 * This class creates a properties JPanel for {@link Clients}.
 * 
 * @author Bahram Malaekeh
 */
public class ClientsPropertiesView
{

	// The client properties labels
	JLabel name;



	/**
	 * This function populates the given JPanel with information about the the
	 * given Object.
	 * 
	 * @param panel
	 *            The JPanel that is to be populated.
	 * @param obj
	 *            The Object that is to populate the given JPanel.
	 */
	public static JPanel getClientsPropertiesView(Object obj)
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


		JLabel label = null;

		if ( obj instanceof Desktop )
		{
			// Desktop Rate
			label = new JLabel(PrimeMain1.texts
					.getString("propClientViewDesktopRateLabel"),
					SwingConstants.TRAILING);
			label.setToolTipText(PrimeMain1.texts
					.getString("propClientViewDesktopRateTip"));
		}
		else if ( obj instanceof Laptop )
		{
			// Laptop Rate
			label = new JLabel(PrimeMain1.texts
					.getString("propClientViewLaptopRateLabel"),
					SwingConstants.TRAILING);
			label.setToolTipText(PrimeMain1.texts
					.getString("propClientViewLaptopRateTip"));
		}
		else if ( obj instanceof ThinClient )
		{
			// ThinClient Rate
			label = new JLabel(PrimeMain1.texts
					.getString("propClientViewThinClientRateLabel"),
					SwingConstants.TRAILING);
			label.setToolTipText(PrimeMain1.texts
					.getString("propClientViewThinClientRateTip"));
		}

		d.insets = new Insets(0, 0, 10, 0); // padding
		d.gridy++; // row
		panel.add(label, d);



		String[] rates = new String[20];
		int temp = 5;

		for ( int i = 0; i < rates.length; i++ )
		{
			rates[i] = Integer.toString(temp);
			temp = temp + 5;
		}


		JComboBox comboBox = new JComboBox(rates);
		comboBox.setBackground(Color.white);
		comboBox.setEditable(false);
		comboBox.setName("Client Rates");

		Clients client = (Clients) obj;

		comboBox.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(rates,
				client.getClientRate()));

		d.insets = new Insets(0, 0, 10, 0); // padding
		d.weighty = 1.0; // request any extra vertical space
		d.gridy++; // row
		panel.add(comboBox, d);



		return panel;
	}
}
