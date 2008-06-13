/**
 * 
 */
package graphics.GUI.properties;


import graphics.ObjectDefiner;
import graphics.GUI.SpringUtilities;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import clients.Desktop;

import objects.Object;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ObjectProperties extends JPanel
{
	Object objectViewed = null;
	
	
	public ObjectProperties()
	{
		
	}
	
	
	public ObjectProperties(Object object)
	{
		objectViewed = object;
		
//		Desktop ffa = ObjectDefiner.defineObjectClass(object);
		
		// this.setPreferredSize(new Dimension(200,500));
		this.setLayout(new SpringLayout());

		String[] labels = {"Name: ", "Fax: ", "Email: ", "Description: "};
		int numPairs = labels.length;

		//Create and populate the panel.
		for (int i = 0; i < numPairs; i++) {
		    JLabel l = new JLabel(labels[i], JLabel.TRAILING);
		    this.add(l);
		    JTextField textField = new JTextField(10);
		    l.setLabelFor(textField);
		    this.add(textField);
		}

		//Lay out the panel.
		SpringUtilities.makeCompactGrid(this,
		                                numPairs, 2, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad

		// JTextField tf = new JTextField("Testfield",10);
		//		
		// // this.add(tf);
		// this.add(new JLabel("GG"));
	}
}
