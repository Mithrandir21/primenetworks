/**
 * 
 */
package graphics.GUI.properties.objectTypes;


import graphics.GraphicalFunctions;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import objects.Clients;
import objects.Object;
import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.clientObjects.ThinClient;


/**
 * CLASSDesc - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ClientsPropertiesView
{

	// The client properties labels
	JLabel name;



	/**
	 * This function populates the given JPanel with information about the the given Object.
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
			JLabel desktopRate = new JLabel("Desktop Rate", SwingConstants.TRAILING);
			desktopRate.setToolTipText("The desktop rating...");
			panel.add(desktopRate);
		}
		else if( obj instanceof Laptop )
		{
			// Laptop Rate
			JLabel laptopRate = new JLabel("Laptop Rate", SwingConstants.TRAILING);
			laptopRate.setToolTipText("The laptop rating...");
			panel.add(laptopRate);
		}
		else if( obj instanceof ThinClient )
		{
			// ThinClient Rate
			JLabel thinClientRate = new JLabel("Thin Client Rate", SwingConstants.TRAILING);
			thinClientRate.setToolTipText("The thin client rating...");
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

		comboBox.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(rates, client.getClientRate()));

		panel.add(comboBox);
	}
}
