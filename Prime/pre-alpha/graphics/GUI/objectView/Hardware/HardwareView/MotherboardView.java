/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import hardware.Motherboard;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class MotherboardView extends JPanel
{
	/**
	 * TODO - Description NEEDED!
	 *
	 * @param mb
	 */
	public MotherboardView(Motherboard mb)
	{
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 5, 10);
		
		
		JPanel p1 = new JPanel();
		p1.setBorder(BorderFactory.createEtchedBorder());
		
		
		
		
		
		this.add(p1,c);
		
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 5;
		c.insets = new Insets(0, 10, 10, 10);
		
		JPanel p2 = new JPanel();
		p2.setBorder(BorderFactory.createEtchedBorder());
		
		this.add(p2,c);
	}
	
	
	
	private JPanel GeneralInfo(Motherboard mb)
	{
		JPanel genPanel = new JPanel();
		
		
		return genPanel;
	}
}
