/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView;


import graphics.ImageLocator;
import graphics.GUI.objectView.Hardware.HardwareEditor;
import hardware.CPU;
import hardware.Ram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import objects.Object;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class RAMView extends JPanel implements HardwareView
{
	JTextField name = new JTextField(25);

	JTextArea desc = new JTextArea(3, 40);
	
	private JTextField producer = new JTextField(7);
	
	private JComboBox type;
	
	private JComboBox subtype;
	
	private JComboBox size;
	
	private JComboBox speed;
	
	
	Object mainObj;

	Ram RAMobj;

	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 * 
	 * @param RAM
	 */
	public RAMView(Object obj, Ram RAM)
	{
		mainObj = obj;
		RAMobj = RAM;
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
		JPanel p1 = HardwareEditor.GeneralInfo(RAM, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 5;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel p2 = new JPanel();
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);
	}
	
	
	private JPanel createSpesificInfo(CPU cpu)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[5];
		
		
		labels[0] = new JLabel("Producer");
		labels[0].setToolTipText("The producer of the ram.");

		labels[1] = new JLabel("Type");
		labels[1].setToolTipText("The ram type.");

		labels[2] = new JLabel("Subtype");
		labels[2].setToolTipText("The ram subtype. (DIMM and so on.)");

		labels[3] = new JLabel("Size");
		labels[3].setToolTipText("The size of the RAM measured in MB.");
		
		labels[4] = new JLabel("Speed");
		labels[4].setToolTipText("The speed of the RAM measured in Mhz.");
		

		Dimension tfSize = new Dimension(90, 20);
		
		
		return panel;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphics.GUI.objectView.Hardware.HardwareView.HardwareView#save()
	 */
	@Override
	public void save()
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seegraphics.GUI.objectView.Hardware.HardwareView.HardwareView#
	 * validateNecessaryData()
	 */
	@Override
	public boolean validateNecessaryData()
	{
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seegraphics.GUI.objectView.Hardware.HardwareView.HardwareView#
	 * validateChangedData()
	 */
	@Override
	public boolean validateChangedData()
	{
		// TODO Auto-generated method stub
		return false;
	}
}
