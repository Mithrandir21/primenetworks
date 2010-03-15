/**
 * 
 */
package graphics.GUI.properties.objectTypes;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;

import java.awt.Color;
import java.awt.Dimension;

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
	public static void getClientsPropertiesView(JPanel panel, Object obj)
	{
		Dimension tfSize = new Dimension(5, 20);

		if ( obj instanceof Desktop )
		{
			// Desktop Rate
			JLabel desktopRate = new JLabel(PrimeMain1.texts
					.getString("propClientViewDesktopRateLabel"),
					SwingConstants.TRAILING);
			desktopRate.setToolTipText(PrimeMain1.texts
					.getString("propClientViewDesktopRateTip"));
			panel.add(desktopRate);
		}
		else if ( obj instanceof Laptop )
		{
			// Laptop Rate
			JLabel laptopRate = new JLabel(PrimeMain1.texts
					.getString("propClientViewLaptopRateLabel"),
					SwingConstants.TRAILING);
			laptopRate.setToolTipText(PrimeMain1.texts
					.getString("propClientViewLaptopRateTip"));
			panel.add(laptopRate);
		}
		else if ( obj instanceof ThinClient )
		{
			// ThinClient Rate
			JLabel thinClientRate = new JLabel(PrimeMain1.texts
					.getString("propClientViewThinClientRateLabel"),
					SwingConstants.TRAILING);
			thinClientRate.setToolTipText(PrimeMain1.texts
					.getString("propClientViewThinClientRateTip"));
			panel.add(thinClientRate);
		}

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
		comboBox.setMaximumSize(tfSize);
		comboBox.setPreferredSize(tfSize);
		comboBox.setName("Client Rates");

		Clients client = (Clients) obj;

		comboBox.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(rates,
				client.getClientRate()));

		panel.add(comboBox);
	}
}
