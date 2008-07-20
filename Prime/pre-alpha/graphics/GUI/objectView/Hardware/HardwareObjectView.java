package graphics.GUI.objectView.Hardware;

import graphics.ImageLocator;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
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
		this.setLayout(new GridBagLayout());
GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.NONE;
		
		
		ImageIcon t = ImageLocator.getImageIconObject("Motherboard");
		
		Hardware hwObj = (Hardware) obj.getComponents()[0];
		
		this.add(createHardwareJPanel((Hardware) obj.getComponents()[0], t));
		this.add(createHardwareJPanel((Hardware) obj.getComponents()[1], t));
		this.add(createHardwareJPanel((Hardware) obj.getComponents()[2], t));
		this.add(createHardwareJPanel((Hardware) obj.getComponents()[3], t));
		this.add(createHardwareJPanel((Hardware) obj.getComponents()[4], t));
		
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
		c.gridheight = 2;
		c.insets = new Insets(10,10,10,5);
		
		JLabel image = new JLabel(icon);
		panel.add(image,c);
		
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,10,2,5);
		
		JLabel text1 = new JLabel(object.getObjectName());
		text1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		text1.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		panel.add(text1,c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,10,2,5);
		
		JLabel text2 = new JLabel(object.getDescription());
		text2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		text2.setAlignmentX(Component.LEFT_ALIGNMENT);

		panel.add(text2,c);
		
		
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		return panel;
	}
}
