package graphics.GUI.objectView.Hardware;


import graphics.ImageLocator;
import hardware.CPU;
import hardware.Diskdrive;
import hardware.ExternalNetworksCard;
import hardware.GraphicsCard;
import hardware.HDD;
import hardware.InternalNetworksCard;
import hardware.Motherboard;
import hardware.Ram;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
 */
public class HardwareObjectView extends JPanel
{
	/**
	 * TODO - Description NEEDED!
	 */
	public HardwareObjectView(Object obj)
	{
		int hwCount = 0;

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);

		ImageIcon temp = null;// ImageLocator.getImageIconObject("Motherboard");

		Object[] hwObj = obj.getComponents();

		String[] info = null;

		for ( int i = 0; i < hwObj.length; i++ )
		{
			if ( hwObj[i] instanceof Motherboard )
			{
				temp = ImageLocator.getImageIconObject("Motherboard");

				Motherboard mbObj = (Motherboard) hwObj[i];

				info = new String[5];

				info[0] = mbObj.getObjectName();
				info[1] = mbObj.getForm();
				info[2] = mbObj.getSocket();
				info[3] = mbObj.getGraphicalPort();
				info[4] = mbObj.getDescription();
			}
			else if ( hwObj[i] instanceof CPU )
			{
				temp = ImageLocator.getImageIconObject("CPU");

				CPU cpuObj = (CPU) hwObj[i];

				info = new String[4];

				info[0] = cpuObj.getObjectName();
				info[1] = cpuObj.getProducer();
				info[2] = cpuObj.getSocket();
				info[3] = cpuObj.getDescription();
			}
			else if ( hwObj[i] instanceof HDD )
			{
				temp = ImageLocator.getImageIconObject("Harddisc");

				HDD hddObj = (HDD) hwObj[i];

				info = new String[5];

				info[0] = hddObj.getObjectName();
				info[1] = hddObj.getProducer();
				info[2] = hddObj.getSubtype();
				info[3] = Integer.toString(hddObj.getSize());
				info[4] = hddObj.getDescription();
			}
			else if ( hwObj[i] instanceof Ram )
			{
				temp = ImageLocator.getImageIconObject("RAM");

				Ram ramObj = (Ram) hwObj[i];

				info = new String[5];

				info[0] = ramObj.getObjectName();
				info[1] = ramObj.getProducer();
				info[2] = ramObj.getSubtype();
				info[3] = Integer.toString(ramObj.getSize());
				info[4] = ramObj.getDescription();
			}
			else if ( hwObj[i] instanceof Diskdrive )
			{
				temp = ImageLocator.getImageIconObject("Optical-Drive");

				Diskdrive discObj = (Diskdrive) hwObj[i];

				info = new String[5];

				info[0] = discObj.getObjectName();
				info[1] = discObj.getProducer();
				info[2] = discObj.getSubtype();
				info[3] = Integer.toString(discObj.getSpeed());
				info[4] = discObj.getDescription();
			}
			else if ( hwObj[i] instanceof GraphicsCard )
			{
				temp = ImageLocator.getImageIconObject("GPU");

				GraphicsCard gpuObj = (GraphicsCard) hwObj[i];

				info = new String[5];

				info[0] = gpuObj.getObjectName();
				info[1] = gpuObj.getProducer();
				info[2] = gpuObj.getOutputInterface();
				info[3] = Integer.toString(gpuObj.getSpeed());
				info[4] = gpuObj.getDescription();
			}
			else if ( hwObj[i] instanceof InternalNetworksCard )
			{
				temp = ImageLocator.getImageIconObject("NIC");

				InternalNetworksCard nicObj = (InternalNetworksCard) hwObj[i];

				info = new String[5];

				info[0] = nicObj.getObjectName();
				info[1] = nicObj.getProducer();
				info[2] = nicObj.getProtocol();
				info[3] = Integer.toString(nicObj.getSpeed());
				info[4] = nicObj.getDescription();
			}
			else if ( hwObj[i] instanceof ExternalNetworksCard )
			{
				temp = ImageLocator.getImageIconObject("NIC");

				ExternalNetworksCard nicObj = (ExternalNetworksCard) hwObj[i];

				info = new String[5];

				info[0] = nicObj.getObjectName();
				info[1] = nicObj.getProducer();
				info[2] = nicObj.getProtocol();
				info[3] = Integer.toString(nicObj.getSpeed());
				info[4] = nicObj.getDescription();
			}

			assert temp != null;

			hwCount++;


			this.add(createHardwareJPanel(info, temp), c);

			if ( hwCount % 2 == 0 )
			{
				c.gridx = 0;
				c.gridy++;
			}
			else
			{
				c.gridx++;
			}
		}


		while ( hwCount < 8 )
		{
			JPanel panel = new JPanel();
			// panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.add(panel, c);

			hwCount++;

			if ( hwCount % 2 == 0 )
			{
				c.gridx = 0;
				c.gridy++;
			}
			else
			{
				c.gridx++;
			}
		}
	}


	private JPanel createHardwareJPanel(String[] texts, ImageIcon icon)
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
//		c.insets = new Insets(10, 10, 10, 5);

		JLabel image = new JLabel(icon);
		panel.add(image, c);

		
		// New constraints for text
		GridBagConstraints d = new GridBagConstraints();
		
		d.fill = GridBagConstraints.CENTER;

		d.gridx = 1;
		d.gridy = 0;
		d.weightx = 1;
		d.weighty = 0.2;
		d.gridwidth = 1;
		d.gridheight = 1;

		for ( int i = 0; i < texts.length; i++ )
		{
			if ( texts[i] != null )
			{
				JLabel text = new JLabel(texts[i]);
//				text.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(text, d);
				d.gridy++;
			}
		}


		return panel;
	}
}
