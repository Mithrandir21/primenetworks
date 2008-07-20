package graphics.GUI.objectView.Hardware;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

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
		
		
	}
	
	
	private JPanel createHardwareJPanel(Hardware object)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		
		
		
		
		
		return panel;
	}
}
