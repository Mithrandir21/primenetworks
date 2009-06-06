/**
 * 
 */
package graphics.GUI.properties.objectTypes;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;
import graphics.GUI.properties.PropertiesArea;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import managment.CanvasManagment;
import objects.Object;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class GeneralPropertiesView
{

	/**
	 * Examines the given WorkareaCanvas and adds the canvas name and the number of objects on the given JPanel.
	 */
	public static void getGeneralCanvasProperties(JPanel panel, WorkareaCanvas canvas)
	{
		Dimension tfSize = new Dimension(5, 20);


		// Name
		JLabel nameLabel = new JLabel("Name", SwingConstants.TRAILING);
		nameLabel.setToolTipText("The name of the network map.");
		panel.add(nameLabel);

		JTextField nameField = new JTextField(canvas.getCanvasName());
		nameField.setMaximumSize(tfSize);
		nameField.setPreferredSize(tfSize);
		nameField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				int key = e.getKeyCode();
				if ( key == KeyEvent.VK_ENTER )
				{
					PropertiesArea objPro = (PropertiesArea) PrimeMain1.propertiesPanel.getComponent(0);
					objPro.getObjectPropertiePanel().saveAction();
				}
			}
		});
		nameLabel.setLabelFor(nameField);
		nameField.setName("Name_Canvas");
		panel.add(nameField);



		// The number of objects on the network map.
		JLabel objectCountLabel = new JLabel("Objects count", SwingConstants.TRAILING);
		objectCountLabel.setToolTipText("The number of objects in the network.");
		panel.add(objectCountLabel);

		JTextField objectCountField = new JTextField("" + canvas.getNumberOfWidgetsOnTheScene());
		objectCountField.setMaximumSize(tfSize);
		objectCountField.setPreferredSize(tfSize);
		objectCountField.setEditable(false);
		objectCountLabel.setLabelFor(objectCountField);
		panel.add(objectCountField);


		// The IP Mask of the Network
		JLabel netmaskLabel = new JLabel("Netmask", SwingConstants.TRAILING);
		netmaskLabel.setToolTipText("The network netmask.");
		panel.add(netmaskLabel);

		JComboBox netmaskCombo = new JComboBox();
		netmaskCombo.setName("Netmask");
		netmaskCombo.setMaximumSize(tfSize);
		netmaskCombo.setPreferredSize(tfSize);
		netmaskLabel.setLabelFor(netmaskCombo);
		String[] netmasks = new String[] { "", "255.255.255.0", "255.255.0.0", "255.0.0.0" };
		netmaskCombo.setModel(new DefaultComboBoxModel(netmasks));
		netmaskCombo.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(netmasks, canvas.getNetworkInfo()
				.getNetmask()));
		panel.add(netmaskCombo);


		// The start of the IP range
		JLabel IPrangeStartLabel = new JLabel("IP Range Start", SwingConstants.TRAILING);
		IPrangeStartLabel.setToolTipText("The start of the IP range.");
		panel.add(IPrangeStartLabel);

		JTextField IPrangeStartField = new JTextField();
		IPrangeStartField.setName("IP range start");
		String ipFrom = canvas.getNetworkInfo().getIpRangeFrom();
		if ( !(ipFrom == null) )
		{
			IPrangeStartField.setText(ipFrom);
		}

		IPrangeStartField.setMaximumSize(tfSize);
		IPrangeStartField.setPreferredSize(tfSize);
		IPrangeStartLabel.setLabelFor(IPrangeStartField);
		panel.add(IPrangeStartField);


		// The start of the IP range
		JLabel IPrangeEndLabel = new JLabel("IP Range End", SwingConstants.TRAILING);
		IPrangeEndLabel.setToolTipText("The end of the IP range.");
		panel.add(IPrangeEndLabel);

		JTextField IPrangeEndField = new JTextField();
		IPrangeEndField.setName("IP range end");
		String ipTo = canvas.getNetworkInfo().getIpRangeTo();
		if ( !(ipTo == null) )
		{
			IPrangeEndField.setText(ipTo);
		}

		IPrangeEndField.setMaximumSize(tfSize);
		IPrangeEndField.setPreferredSize(tfSize);
		IPrangeEndLabel.setLabelFor(IPrangeEndField);
		panel.add(IPrangeEndField);
	}


	/**
	 * Examines the given object. Adds the information about the object name and different information about the object
	 * to the given JPanel.
	 */
	public static void getGeneralObjectProperties(JPanel panel, Object obj)
	{
		Dimension tfSize = new Dimension(5, 20);

		// Gets the WidgetObject so that the IP address can be added
		WidgetObject widObj = CanvasManagment.findWidgetObject(obj, PrimeMain1.currentCanvas);



		// Name
		JLabel nameLabel = new JLabel("Name", SwingConstants.TRAILING);
		nameLabel.setToolTipText("The name of the device.");
		panel.add(nameLabel);

		JTextField nameField = new JTextField(obj.getObjectName());
		nameField.setMaximumSize(tfSize);
		nameField.setPreferredSize(tfSize);
		nameField.setName("Name Object");
		nameField.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				int key = e.getKeyCode();
				if ( key == KeyEvent.VK_ENTER )
				{
					PropertiesArea objPro = (PropertiesArea) PrimeMain1.propertiesPanel.getComponent(0);
					objPro.getObjectPropertiePanel().saveAction();
				}
			}
		});
		nameLabel.setLabelFor(nameField);
		panel.add(nameField);


		// Supported connection interfaces
		JLabel supConIntLabel = new JLabel("Supported Connection Interfaces", SwingConstants.TRAILING);
		supConIntLabel.setToolTipText("The types of connections the device is capable"
				+ " of connecting to, like USB og RJ-45.");
		panel.add(supConIntLabel);

		JComboBox subConCombo = new JComboBox();
		subConCombo.setModel(new DefaultComboBoxModel(obj.getSupportedConnectionInterfaces()));
		subConCombo.setMaximumSize(tfSize);
		subConCombo.setPreferredSize(tfSize);
		subConCombo.setEditable(false);
		subConCombo.setName("supConInt");
		supConIntLabel.setLabelFor(subConCombo);
		panel.add(subConCombo);



		// Number of components
		JLabel numConLabel = new JLabel("Number of devices connected", SwingConstants.TRAILING);
		numConLabel.setToolTipText("The number of devices connected to this devices.");
		panel.add(numConLabel);

		JTextField numConField = new JTextField(Integer.toString((obj.getNumberOfConnectedDevices())));
		numConField.setEditable(false);
		numConField.setMaximumSize(tfSize);
		numConField.setPreferredSize(tfSize);
		numConField.setName("numCon");
		numConLabel.setLabelFor(numConField);
		panel.add(numConField);


		// Number of nodes
		JLabel numbJumpsLabel = new JLabel("Number of jumps", SwingConstants.TRAILING);
		numbJumpsLabel.setToolTipText("The numbers of devices between this device and a router outside"
				+ " of this system.");
		panel.add(numbJumpsLabel);

		JTextField numbJumpsField = new JTextField("0");
		numbJumpsField.setEditable(false);
		numbJumpsField.setMaximumSize(tfSize);
		numbJumpsField.setPreferredSize(tfSize);
		numbJumpsField.setName("numbJumps");
		numbJumpsLabel.setLabelFor(numbJumpsField);
		panel.add(numbJumpsField);

		// If not WidgetObject is found
		if ( widObj != null )
		{
			// The IP of the object
			JLabel IPLabel = new JLabel("Device IP", SwingConstants.TRAILING);
			IPLabel.setToolTipText("The IP of the Object in the network.");
			panel.add(IPLabel);

			JTextField IPfield = new JTextField();
			IPfield.setName("Object IP");
			String ipFrom = widObj.getWidgetNetworkInfo().getIp();
			if ( !(ipFrom == null) )
			{
				IPfield.setText(ipFrom);
			}

			IPfield.setMaximumSize(tfSize);
			IPfield.setPreferredSize(tfSize);
			IPLabel.setLabelFor(IPfield);
			panel.add(IPfield);
		}
	}

}
