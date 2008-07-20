package graphics.GUI.objectView.Hardware;

import graphics.ImageLocator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objects.Hardware;
import objects.Object;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class HardwareObjectView extends JPanel
{
	/**
	 * TODO - Description NEEDED!
	 *
	 */
	public HardwareObjectView(Object obj)
	{
		this.setLayout(new GridLayout(0,2));
		
		System.out.println("here");
		
		ImageIcon t = ImageLocator.getImageIconObject("Motherboard");
		
		if(t==null)
		{
			System.out.println("null");
		}
		
		Hardware hwObj = (Hardware) obj.getComponents()[0];
		
		this.add(createHardwareJPanel(hwObj, t));
		
	}
	
	
	private JPanel createHardwareJPanel(Hardware object, ImageIcon icon)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.NONE;
		
		
		// Icon image.
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 3;
		c.insets = new Insets(10,10,10,5);
		
		JLabel image = new JLabel(icon);
		panel.add(image,c);
		
		
		
		return panel;
	}
}
