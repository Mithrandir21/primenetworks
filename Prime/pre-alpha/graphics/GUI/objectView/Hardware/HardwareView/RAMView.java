/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import graphics.ImageLocator;
import graphics.GUI.objectView.Hardware.HardwareEditor;
import hardware.CPU;
import hardware.Ram;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class RAMView extends JPanel
{
	/**
	 * TODO - Description NEEDED!
	 *
	 * @param RAM
	 */
	public RAMView(Ram RAM)
	{
		
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 5, 10);
		
		ImageIcon icon = ImageLocator.getImageIconObject("RAM");
		JPanel p1 = HardwareEditor.GeneralInfo(RAM,icon);
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
}
