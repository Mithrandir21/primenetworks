package graphics.GUI.objectView.Network;


import exceptions.ObjectNotFoundException;
import graphics.ImageLocator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import logistical.cleanup;
import managment.ArrayManagment;
import managment.ComponentsManagment;
import objects.Object;
import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.hardwareObjects.CPU;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.Motherboard;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Router;
import objects.infrastructureObjects.Switch;
import objects.peripheralObjects.Printer;
import objects.peripheralObjects.Scanner;
import objects.serverObjects.BackupServer;
import objects.serverObjects.FirewallServer;
import objects.serverObjects.HTTPServer;
import objects.serverObjects.MailServer;
import objects.serverObjects.ProxyServer;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class NetworkObjectView extends JPanel
{
	private Object givenObject = null;

	/**
	 * A constructor for the class.
	 */
	public NetworkObjectView(Object obj)
	{
		populateInfo(obj);
	}



	/**
	 * This method sets the layout and adds two JPanel with information about
	 * objects connected the given object. One of the panels are for objects
	 * connected by way of USB and the other one by way of RJ-45.
	 * 
	 * @param obj
	 */
	private void populateInfo(Object obj)
	{
		givenObject = obj;

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);


		this.add(determine(obj, "USB"), c);


		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;


		this.add(determine(obj, "RJ-45"), c);

	}



	/**
	 * This method passes the object on the right function depending on the
	 * conType string.
	 * 
	 * @param obj
	 *            The object that is to be passed on.
	 * @param conType
	 *            The string that detemines the type of connecion.
	 * @return
	 */
	private JPanel determine(Object obj, String conType)
	{
		JPanel panel = null;


		if ( conType.equals("USB") )
		{
			panel = USBpanel(obj);
		}
		else if ( conType.equals("RJ-45") )
		{
			panel = LANpanel(obj);
		}


		return panel;
	}



	/**
	 * Creates and returns a JPanel that contains information about the objects
	 * that are connected to the given object by way of USB.
	 * 
	 * @param obj
	 *            The Object that is to be examined for connections.
	 * @return A JPanel with information about objects connections by way of
	 *         USB.
	 */
	private JPanel USBpanel(Object obj)
	{
		JPanel panel = null;

		Object[] matched = ComponentsManagment.connectedToBy(obj, "USB");

		panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;


		// Icon image.
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(5, 5, 0, 5);
		// c.gridheight = texts.length;

		int hwCount = 0;


		for ( int i = 0; i < matched.length; i++ )
		{
			JPanel objPanel = createPanel(matched[i]);
			objPanel.setMaximumSize(new Dimension(90, 20));
			objPanel.setPreferredSize(new Dimension(90, 20));

			panel.add(createPanel(matched[i]), c);

			if ( i % 2 == 0 )
			{
				c.gridx++;
			}
			else
			{
				c.gridx = 0;
				c.gridy++;
			}

			hwCount++;
		}



		while ( hwCount < 10 )
		{
			JPanel p = new JPanel();
			// p.setBorder(BorderFactory.createEtchedBorder());
			panel.add(p, c);

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



		TitledBorder title;
		title = BorderFactory.createTitledBorder("USB");
		panel.setBorder(title);


		return panel;
	}



	/**
	 * Creates and returns a JPanel that contains information about the objects
	 * that are connected to the given object by way of RJ-45.
	 * 
	 * @param obj
	 *            The Object that is to be examined for connections.
	 * @return A JPanel with information about objects connections by way of
	 *         RJ-45.
	 */
	private JPanel LANpanel(Object obj)
	{
		JPanel panel = null;

		Object[] matched = ComponentsManagment.connectedToBy(obj, "RJ-45");

		panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;


		// Icon image.
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(5, 5, 0, 5);
		// c.gridheight = texts.length;

		int hwCount = 0;


		for ( int i = 0; i < matched.length; i++ )
		{
			JPanel objPanel = createPanel(matched[i]);
			objPanel.setMaximumSize(new Dimension(90, 20));
			objPanel.setPreferredSize(new Dimension(90, 20));

			panel.add(createPanel(matched[i]), c);

			if ( i % 2 == 0 )
			{
				c.gridx++;
			}
			else
			{
				c.gridx = 0;
				c.gridy++;
			}

			hwCount++;
		}


		while ( hwCount < 10 )
		{
			JPanel p = new JPanel();
			// p.setBorder(BorderFactory.createEtchedBorder());
			panel.add(p, c);

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



		TitledBorder title;
		title = BorderFactory.createTitledBorder("RJ-45");
		panel.setBorder(title);


		return panel;
	}



	/**
	 * Creates a JPanel that will contains key information about the given
	 * object depending on the class of the Object.
	 * 
	 * @param obj
	 *            The Object that is to be examined.
	 * @return The JPanel with the specific information.
	 */
	private JPanel createPanel(Object obj)
	{
		JPanel panel = null;

		ImageIcon icon = null;

		if ( obj instanceof Desktop )
		{
			icon = ImageLocator.getImageIconObject("Desktop");
		}
		else if ( obj instanceof Laptop )
		{
			icon = ImageLocator.getImageIconObject("Laptop");
		}
		// else if(obj instanceof ThinClient)
		// {
		// icon = ImageLocator.getImageIconObject("ThinClient");
		// }
		else if ( obj instanceof HTTPServer )
		{
			icon = ImageLocator.getImageIconObject("Web-server");
		}
		else if ( obj instanceof BackupServer )
		{
			icon = ImageLocator.getImageIconObject("Data-server");
		}
		else if ( obj instanceof MailServer )
		{
			icon = ImageLocator.getImageIconObject("Email-server");
		}
		else if ( obj instanceof FirewallServer )
		{
			icon = ImageLocator.getImageIconObject("Firewall-server");
		}
		else if ( obj instanceof ProxyServer )
		{
			icon = ImageLocator.getImageIconObject("Proxy-server");
		}
		else if ( obj instanceof Scanner )
		{
			icon = ImageLocator.getImageIconObject("Scanner");
		}
		else if ( obj instanceof Printer )
		{
			icon = ImageLocator.getImageIconObject("Printer");
		}
		else if ( obj instanceof Hub )
		{
			icon = ImageLocator.getImageIconObject("Hub");

		}
		else if ( obj instanceof Switch )
		{
			icon = ImageLocator.getImageIconObject("Switch");
		}
		else if ( obj instanceof Router )
		{
			icon = ImageLocator.getImageIconObject("Router");
		}

		String[] texts = check(obj);

		panel = createJPanel(texts, icon);


		return panel;
	}




	/**
	 * Returns a String array with information about the given Object.
	 * 
	 * @param obj
	 *            The object that is to be examined.
	 * @return An array with information about the given object, like name,
	 *         description, number of cpus, etc.
	 */
	private String[] check(Object obj)
	{
		String[] info = new String[5];

		String text = "";

		text = obj.getObjectName();
		if ( text != "" && text != null )
		{
			info[0] = "Name: " + text;
		}

		text = obj.getDescription();
		if ( text != "" && text != null )
		{
			info[1] = "Description: " + text;
		}



		Motherboard mbObj = null;

		CPU[] cpus = null;

		HDD[] hdds = null;


		try
		{
			mbObj = (Motherboard) ArrayManagment.getSpesificComponents(
					Motherboard.class, obj.getComponents(),
					obj.getComponents().length)[0];



			Object[] cpusArray = ArrayManagment.getSpesificComponents(
					CPU.class, obj.getComponents(), obj.getComponents().length);

			cpus = new CPU[cpusArray.length];
			for ( int i = 0; i < cpusArray.length; i++ )
			{
				cpus[i] = (CPU) cpusArray[i];
			}



			Object[] hddsArray = ArrayManagment.getSpesificComponents(
					HDD.class, obj.getComponents(), obj.getComponents().length);


			hdds = new HDD[hddsArray.length];
			for ( int i = 0; i < hddsArray.length; i++ )
			{
				hdds[i] = (HDD) hddsArray[i];
			}
		}
		catch ( ObjectNotFoundException e )
		{
			// Does nothing. Checked later.
		}



		if ( mbObj != null )
		{
			if ( mbObj.getSocket() != "" )
			{
				info[2] = "Socket: " + mbObj.getSocket();
			}
		}

		if ( cpus != null )
		{
			text = "";
			int speed = 0;


			if ( cpus.length == 1 )
			{
				text = "Speed: " + cpus[0].getSpeed();
			}
			else if ( cpus.length > 1 )
			{


				for ( int i = 0; i < cpus.length; i++ )
				{
					speed = speed + cpus[i].getSpeed();
				}

				if ( speed != 0 )
				{
					text = "Speed: " + speed;
				}
			}

			if ( speed != 0 )
			{
				info[3] = text;
			}
			else
			{
				info[3] = null;
			}
		}

		if ( hdds != null )
		{
			text = "";
			if ( hdds.length == 1 )
			{
				text = "Size: " + hdds[0].getSize();
			}
			else if ( hdds.length > 1 )
			{
				int size = 0;

				for ( int i = 0; i < hdds.length; i++ )
				{
					size = size + hdds[i].getSize();
				}

				if ( size != 0 )
				{
					text = "Size: " + size;
				}
			}

			info[4] = text;
		}


		info = cleanup.cleanObjectArray(info);

		return info;
	}


	/**
	 * Creates a JPanel and adds the given Icon and Strings. The strings are
	 * place vertical.
	 * 
	 * @param texts
	 *            The strings with the information about the Hardware component.
	 * @param icon
	 *            The ImageIcon that will represent the Hardware component.
	 * @return Returns a JPanel with both the ImageIcon and the hardware
	 *         information.
	 */
	private static JPanel createJPanel(String[] texts, ImageIcon icon)
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBackground(Color.white);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.NONE;


		// Icon image.
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = texts.length;

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

		/**
		 * Adds the given texts to the panel.
		 */
		for ( int i = 0; i < texts.length; i++ )
		{
			if ( texts[i] != null )
			{
				JLabel text = new JLabel(texts[i]);
				// text.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(text, d);
				d.gridy++;
			}
		}

		return panel;
	}
}
