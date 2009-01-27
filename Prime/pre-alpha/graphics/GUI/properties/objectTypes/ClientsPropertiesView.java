/**
 * 
 */
package graphics.GUI.properties.objectTypes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import objects.Object;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class ClientsPropertiesView
{
	
	// The client proterties labels
	
	JLabel name;
	
	
	
	/**
	 * TODO - Description NEEDED!
	 *
	 * @param obj
	 */
	public static void getClientsPropertiesView(JPanel panel, Object obj)
	{
		// Desktop Rate
		JLabel dekstopRate = new JLabel("Desktop Rate",
				SwingConstants.TRAILING);
		dekstopRate.setToolTipText("The desktop rating...");
		panel.add(dekstopRate);
		
		
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
		Dimension tfSize = new Dimension(5, 20);
		comboBox.setMaximumSize(tfSize);
		comboBox.setPreferredSize(tfSize);
		comboBox.setName("rates");


		panel.add(comboBox);
	}
}
