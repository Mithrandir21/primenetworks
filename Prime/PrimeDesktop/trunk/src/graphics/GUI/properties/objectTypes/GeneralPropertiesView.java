/**
 * 
 */
package graphics.GUI.properties.objectTypes;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;
import graphics.GUI.properties.PropertiesArea;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
 * This class creates a properties JPanel containing general information about a
 * given {@link WorkareaCanvas} or given {@link Object}.
 * 
 * @author Bahram Malaekeh
 */
public class GeneralPropertiesView
{

	/**
	 * Examines the given WorkareaCanvas and adds the canvas name and the number
	 * of objects on the given JPanel.
	 */
	public static JPanel getGeneralCanvasProperties(WorkareaCanvas canvas)
	{
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.HORIZONTAL;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		d.weightx = 0.8; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		d.insets = new Insets(0, 0, 5, 0); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column


		// Name
		JLabel nameLabel = new JLabel(PrimeMain1.texts
				.getString("propGeneralViewCanvasNameLabel"),
				SwingConstants.TRAILING);
		nameLabel.setToolTipText(PrimeMain1.texts
				.getString("propGeneralViewCanvasNameTip"));
		panel.add(nameLabel, d);


		JTextField nameField = new JTextField(canvas.getCanvasName());
		nameField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				int key = e.getKeyCode();
				if ( key == KeyEvent.VK_ENTER )
				{
					PropertiesArea objPro = (PropertiesArea) PrimeMain1.propertiesPanel
							.getComponent(0);
					objPro.getObjectPropertiePanel().saveAction();
				}
			}
		});
		// nameLabel.setLabelFor(nameField);
		nameField.setName("Name_Canvas");
		d.insets = new Insets(0, 0, 10, 0); // padding
		d.gridy++; // row
		panel.add(nameField, d);



		// The number of objects on the network map.
		JLabel objectCountLabel = new JLabel(PrimeMain1.texts
				.getString("propGeneralViewObjectsCountLabel"),
				SwingConstants.TRAILING);
		objectCountLabel.setToolTipText(PrimeMain1.texts
				.getString("propGeneralViewObjectsCountTip"));
		d.insets = new Insets(0, 0, 5, 0); // padding
		d.gridy++; // row
		panel.add(objectCountLabel, d);

		JTextField objectCountField = new JTextField(""
				+ canvas.getNumberOfWidgetsOnTheScene());
		objectCountField.setEditable(false);
		d.insets = new Insets(0, 0, 10, 0); // padding
		d.gridy++; // row
		panel.add(objectCountField, d);


		// The IP Mask of the Network
		JLabel netmaskLabel = new JLabel(PrimeMain1.texts
				.getString("propGeneralViewNetmaskLabel"),
				SwingConstants.TRAILING);
		netmaskLabel.setToolTipText(PrimeMain1.texts
				.getString("propGeneralViewNetmaskTip"));
		d.insets = new Insets(0, 0, 5, 0); // padding
		d.gridy++; // row
		panel.add(netmaskLabel, d);

		JComboBox netmaskCombo = new JComboBox();
		netmaskCombo.setName("Netmask");
		String[] netmasks = new String[] { "", "255.255.255.0", "255.255.0.0",
				"255.0.0.0" };
		netmaskCombo.setModel(new DefaultComboBoxModel(netmasks));
		netmaskCombo.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				netmasks, canvas.getNetworkInfo().getNetmask()));
		d.insets = new Insets(0, 0, 10, 0); // padding
		d.gridy++; // row
		panel.add(netmaskCombo, d);


		// The start of the IP range
		JLabel IPrangeStartLabel = new JLabel(PrimeMain1.texts
				.getString("propGeneralViewIPStartsLabel"),
				SwingConstants.TRAILING);
		IPrangeStartLabel.setToolTipText(PrimeMain1.texts
				.getString("propGeneralViewIPStartsTip"));
		d.insets = new Insets(0, 0, 5, 0); // padding
		d.gridy++; // row
		panel.add(IPrangeStartLabel, d);

		JTextField IPrangeStartField = new JTextField();
		IPrangeStartField.setName("IP range start");
		String ipFrom = canvas.getNetworkInfo().getIpRangeFrom();
		if ( !(ipFrom == null) )
		{
			IPrangeStartField.setText(ipFrom);
		}

		d.insets = new Insets(0, 0, 10, 0); // padding
		d.gridy++; // row
		panel.add(IPrangeStartField, d);


		// The start of the IP range
		JLabel IPrangeEndLabel = new JLabel(PrimeMain1.texts
				.getString("propGeneralViewIPendsLabel"),
				SwingConstants.TRAILING);
		IPrangeEndLabel.setToolTipText(PrimeMain1.texts
				.getString("propGeneralViewIPendsTip"));
		d.insets = new Insets(0, 0, 5, 0); // padding
		d.gridy++; // row
		panel.add(IPrangeEndLabel, d);

		JTextField IPrangeEndField = new JTextField();
		IPrangeEndField.setName("IP range end");
		String ipTo = canvas.getNetworkInfo().getIpRangeTo();
		if ( !(ipTo == null) )
		{
			IPrangeEndField.setText(ipTo);
		}

		d.insets = new Insets(0, 0, 10, 0); // padding
		d.weighty = 1.0; // request any extra vertical space
		d.gridy++; // row
		panel.add(IPrangeEndField, d);


		return panel;
	}


	/**
	 * Examines the given object. Adds the information about the object name and
	 * different information about the object to the given JPanel.
	 */
	public static JPanel getGeneralObjectProperties(Object obj)
	{
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.HORIZONTAL;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		d.weightx = 0.8; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		d.insets = new Insets(0, 0, 5, 0); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column



		// Gets the WidgetObject so that the IP address can be added
		WidgetObject widObj = CanvasManagment.findWidgetObject(obj,
				PrimeMain1.currentCanvas);



		// Name
		JLabel nameLabel = new JLabel(PrimeMain1.texts
				.getString("propGeneralViewObjectNameLabel"),
				SwingConstants.TRAILING);
		nameLabel.setToolTipText(PrimeMain1.texts
				.getString("propGeneralViewObjectNameTip"));
		panel.add(nameLabel, d);

		JTextField nameField = new JTextField(obj.getObjectName());
		nameField.setName("Name Object");
		nameField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				int key = e.getKeyCode();
				if ( key == KeyEvent.VK_ENTER )
				{
					PropertiesArea objPro = (PropertiesArea) PrimeMain1.propertiesPanel
							.getComponent(0);
					objPro.getObjectPropertiePanel().saveAction();
				}
			}
		});
		d.insets = new Insets(0, 0, 10, 0); // padding
		d.gridy++; // row
		panel.add(nameField, d);


		// Supported connection interfaces
		JLabel supConIntLabel = new JLabel(PrimeMain1.texts
				.getString("propGeneralViewSupConIntLabel"),
				SwingConstants.TRAILING);
		supConIntLabel.setToolTipText(PrimeMain1.texts
				.getString("propGeneralViewSupConIntTip"));
		d.insets = new Insets(0, 0, 5, 0); // padding
		d.gridy++; // row
		panel.add(supConIntLabel, d);

		JComboBox subConCombo = new JComboBox();
		subConCombo.setModel(new DefaultComboBoxModel(obj
				.getSupportedConnectionInterfaces()));
		subConCombo.setEditable(false);
		subConCombo.setName("supConInt");
		d.insets = new Insets(0, 0, 10, 0); // padding
		d.gridy++; // row
		panel.add(subConCombo, d);



		// Number of components
		JLabel numConLabel = new JLabel(PrimeMain1.texts
				.getString("propGeneralViewNumConDevicesLabel"),
				SwingConstants.TRAILING);
		numConLabel.setToolTipText(PrimeMain1.texts
				.getString("propGeneralViewNumConDevicesTip"));
		d.insets = new Insets(0, 0, 5, 0); // padding
		d.gridy++; // row
		panel.add(numConLabel, d);

		JTextField numConField = new JTextField(Integer.toString((obj
				.getNumberOfConnectedDevices())));
		numConField.setEditable(false);
		numConField.setName("numCon");
		d.insets = new Insets(0, 0, 10, 0); // padding
		d.gridy++; // row
		panel.add(numConField, d);


		// Number of nodes
		JLabel numbJumpsLabel = new JLabel(PrimeMain1.texts
				.getString("propGeneralViewNumJumpsLabel"),
				SwingConstants.TRAILING);
		numbJumpsLabel.setToolTipText(PrimeMain1.texts
				.getString("propGeneralViewNumJumpsTip"));
		d.insets = new Insets(0, 0, 5, 0); // padding
		d.gridy++; // row
		panel.add(numbJumpsLabel, d);

		JTextField numbJumpsField = new JTextField("0");
		numbJumpsField.setEditable(false);
		numbJumpsField.setName("numbJumps");
		d.insets = new Insets(0, 0, 10, 0); // padding
		d.gridy++; // row
		panel.add(numbJumpsField, d);

		// If not WidgetObject is found
		if ( widObj != null )
		{
			// The IP of the object
			JLabel IPLabel = new JLabel(PrimeMain1.texts
					.getString("propGeneralViewDeviceIPLabel"),
					SwingConstants.TRAILING);
			IPLabel.setToolTipText(PrimeMain1.texts
					.getString("propGeneralViewDeviceIPTip"));
			d.insets = new Insets(0, 0, 5, 0); // padding
			d.gridy++; // row
			panel.add(IPLabel, d);

			JTextField IPfield = new JTextField();
			IPfield.setName("Object IP");
			String ipFrom = widObj.getWidgetNetworkInfo().getIp();
			if ( !(ipFrom == null) )
			{
				IPfield.setText(ipFrom);
			}

			d.insets = new Insets(0, 0, 10, 0); // padding
			d.gridy++; // row
			panel.add(IPfield, d);
		}



		return panel;
	}

}
