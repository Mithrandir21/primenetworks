/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView;


import graphics.ImageLocator;
import graphics.GUI.SpringUtilities;
import graphics.GUI.objectView.Hardware.HardwareEditor;
import hardware.Motherboard;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


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
		
		ImageIcon icon = ImageLocator.getImageIconObject("Motherboard");
		JPanel p1 = HardwareEditor.GeneralInfo(mb,icon);
		p1.setBorder(BorderFactory.createEtchedBorder());
		
		
		
		
		
		this.add(p1,c);
		
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 5;
		c.insets = new Insets(0, 10, 10, 10);
		
		JPanel p2 = createSpesificInfo(mb);
		p2.setBorder(BorderFactory.createEtchedBorder());
		
		this.add(p2,c);
	}
	
	
	private JPanel createSpesificInfo(Motherboard mb)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[6];
		
		
		labels[0] = new JLabel("Producer");
		labels[1] = new JLabel("Form");
		labels[2] = new JLabel("Socket");
		labels[3] = new JLabel("Bus Speed");
		labels[4] = new JLabel("Chipset");
		labels[5] = new JLabel("GPU Port");
		
		
		
		Dimension tfSize = new Dimension(1,7);
		
		
		
		// PRODUCER
		JTextField producerField = new JTextField(7);
		labels[0].setLabelFor(producerField);
		producerField.setMaximumSize(tfSize);
		producerField.setPreferredSize(tfSize);
		producerField.setText(mb.getProducer());
		
		panel.add(labels[0]);
		panel.add(producerField);
		// --------------------------
		
		
		// FORM
		String[] formsStrings = {"","ATX","mATX","eATX"};
		JComboBox forms = new JComboBox(formsStrings);
		forms.setMaximumSize(tfSize);
		forms.setPreferredSize(tfSize);
		forms.setBackground(Color.WHITE);

		int formIndex = 0;
		
		for(int i = 0;i<forms.getComponentCount();i++)
		{
			if ( mb.getForm() != null && mb.getForm() != "" )
			{
				if ( forms.getItemAt(i).equals(mb.getForm()) )
				{
					formIndex = i;
					i = forms.getComponentCount();
				}
			}
		}
		
		forms.setSelectedIndex(formIndex);
		
		labels[1].setLabelFor(forms);
		
		panel.add(labels[1]);
		panel.add(forms);
		// --------------------------
		
		
		// SOCKET
		String[] socketsStrings = {"","Intel 775","Intel 939","AMD AM2","AMD AM2+"};
		JComboBox sockets = new JComboBox(socketsStrings);
		sockets.setMaximumSize(tfSize);
		sockets.setPreferredSize(tfSize);
		sockets.setBackground(Color.WHITE);

		
		int socketsIndex = 0;
		
		for(int i = 0;i<sockets.getComponentCount();i++)
		{
			if ( mb.getSocket() != null && mb.getSocket() != "" )
			{
				if ( sockets.getItemAt(i).equals(mb.getSocket()) )
				{
					socketsIndex = i;
					i = sockets.getComponentCount();
				}
			}
		}
		
		sockets.setSelectedIndex(socketsIndex);
		
		labels[2].setLabelFor(sockets);
		
		panel.add(labels[2]);
		panel.add(sockets);
		// --------------------------
		
		
		// BUS SPEED
		String[] busspeedStrings = {"","800","1066"};
		JComboBox busSpeeds = new JComboBox(busspeedStrings);
		busSpeeds.setMaximumSize(tfSize);
		busSpeeds.setPreferredSize(tfSize);
		busSpeeds.setBackground(Color.WHITE);

		
		int busSpeedIndex = 0;
		
		for(int i = 0;i<busSpeeds.getComponentCount();i++)
		{
			if ( mb.getBusSpeed() != 0 )
			{
				if ( busSpeeds.getItemAt(i).equals(mb.getBusSpeed()) )
				{
					busSpeedIndex = i;
					i = busSpeeds.getComponentCount();
				}
			}
		}
		
		busSpeeds.setSelectedIndex(busSpeedIndex);

		labels[3].setLabelFor(busSpeeds);
		
		panel.add(labels[3]);
		panel.add(busSpeeds);
		// --------------------------
		
		
		// CHIPSET
		JTextField chipsetField = new JTextField(7);
		labels[4].setLabelFor(chipsetField);
		chipsetField.setMaximumSize(tfSize);
		chipsetField.setPreferredSize(tfSize);
		chipsetField.setText(mb.getChipset());
		
		panel.add(labels[4]);
		panel.add(chipsetField);
		// --------------------------
		
		
		// GPU PORT
		String[] gpuPortStrings = {"","AGP","PCI","PCI-E"};
		JComboBox gpuPorts = new JComboBox(gpuPortStrings);
		gpuPorts.setMaximumSize(tfSize);
		gpuPorts.setMinimumSize(tfSize);
		gpuPorts.setPreferredSize(tfSize);
		gpuPorts.setBackground(Color.WHITE);

		
		int gpuPortIndex = 0;
		
		for(int i = 0;i<gpuPorts.getComponentCount();i++)
		{
			if ( mb.getGraphicalPort() != null && mb.getGraphicalPort() != "" )
			{
				if ( gpuPorts.getItemAt(i).equals(mb.getGraphicalPort()) )
				{
					gpuPortIndex = i;
					i = gpuPorts.getComponentCount();
				}
			}
		}
		
		gpuPorts.setSelectedIndex(gpuPortIndex);

		labels[5].setLabelFor(gpuPorts);
		
		panel.add(labels[5]);
		panel.add(gpuPorts);
		
		
		
		// Lay out the panel.
		SpringUtilities.makeCompactGrid(panel, 2, 6, // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad
		
		
		return panel;
	}
}
