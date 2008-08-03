/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView;

import graphics.ImageLocator;
import graphics.GUI.objectView.Hardware.HardwareEditor;
import hardware.GraphicsCard;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class GraphicsCardView extends JPanel implements HardwareView
{
	JTextField name = new JTextField(25);
	JTextArea desc = new JTextArea(3,40);
	
	public GraphicsCardView(GraphicsCard GPU)
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
		
		ImageIcon icon = ImageLocator.getImageIconObject("GPU");
		JPanel p1 = HardwareEditor.GeneralInfo(GPU,icon,name,desc);
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

	public void save()
	{
		// TODO Auto-generated method stub
		
	}

	public boolean validateData()
	{
		// TODO Auto-generated method stub
		return true;
	}
}
