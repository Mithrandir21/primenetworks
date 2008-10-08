package graphics.GUI.objectView.Hardware.HardwareView;


import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.GUI.SpringUtilities;
import graphics.GUI.objectView.Hardware.HardwareEditor;
import hardware.HDD;
import hardware.Ram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import objects.Object;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class HDDView extends JPanel implements HardwareView, ActionListener
{
	JTextField name = new JTextField(25);

	JTextArea desc = new JTextArea(3, 40);

	private JTextField producer = new JTextField(7);

	private JComboBox type;

	private JComboBox subtype;

	private JComboBox size;

	private JComboBox transferSpeed;

	private JComboBox rpm;


	private Object mainObj;

	private HDD mainHDD;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 * @param hdd
	 */
	public HDDView(Object obj, HDD hdd)
	{
		mainObj = obj;
		mainHDD = hdd;
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

		ImageIcon icon = ImageLocator.getImageIconObject("Harddisc");
		JPanel p1 = HardwareEditor.GeneralInfo(hdd, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());





		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 5;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel p2 = createSpesificInfo(hdd);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);
	}


	private JPanel createSpesificInfo(HDD hdd)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[6];

		labels[0] = new JLabel("Producer");
		labels[0].setToolTipText("The producer of the hdd.");

		labels[1] = new JLabel("Type");
		labels[1].setToolTipText("The hdd type.");

		labels[2] = new JLabel("Subtype");
		labels[2].setToolTipText("The hdd subtype. (ATA-100, SATA-150 and so on.)");

		labels[3] = new JLabel("Size");
		labels[3].setToolTipText("The size of the HDD measured in GB.");

		labels[4] = new JLabel("Speed");
		labels[4].setToolTipText("The transfer speed of the HDD measured in MB.");

		labels[5] = new JLabel("RPM");
		labels[5].setToolTipText("The number of disk rotations per minute.");


		Dimension tfSize = new Dimension(90, 20);


		// The producer
		labels[0].setLabelFor(producer);
		producer.setMaximumSize(tfSize);
		producer.setPreferredSize(tfSize);
		producer.setText(hdd.getProducer());
		producer.setToolTipText(labels[0].getToolTipText());


		panel.add(labels[0]);
		panel.add(producer);


		// The type of the hdd
		labels[1].setLabelFor(type);
		String[] typeString = { "", "IDE", "SATA", "USB" };
		type = new JComboBox(typeString);
		type.setMaximumSize(tfSize);
		type.setPreferredSize(tfSize);
		type.setBackground(Color.WHITE);
		type.setToolTipText(labels[1].getToolTipText());
		type.setActionCommand("Type");
		type.addActionListener(this);

		type.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(typeString, hdd.getType()));


		panel.add(labels[1]);
		panel.add(type);



		// The subtype of the hdd
		labels[2].setLabelFor(subtype);
		String[] subtypeString = { "", "ATA-100", "ATA-133", "SATA-150", "SATA-300", "USBv1",
				"USBv2", "eSATA" };
		subtype = new JComboBox(subtypeString);
		subtype.setMaximumSize(tfSize);
		subtype.setPreferredSize(tfSize);
		subtype.setBackground(Color.WHITE);
		subtype.setToolTipText(labels[2].getToolTipText());
		subtype.setActionCommand("Subtype");
		subtype.addActionListener(this);

		subtype.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(subtypeString, hdd
				.getSubtype()));


		panel.add(labels[2]);
		panel.add(subtype);



		// The size of the hdd
		labels[3].setLabelFor(size);
		String[] sizeString = new String[29];
		sizeString[0] = "";

		int hddSize = 0;

		for ( int i = 1; i < sizeString.length; i++ )
		{
			if ( hddSize < 200 )
			{
				hddSize = hddSize + 10;
				sizeString[i] = "" + hddSize;
			}
			else
			{
				hddSize = hddSize + 100;
				sizeString[i] = "" + hddSize;
			}
		}

		size = new JComboBox(sizeString);
		size.setMaximumSize(tfSize);
		size.setPreferredSize(tfSize);
		size.setBackground(Color.WHITE);
		size.setToolTipText(labels[3].getToolTipText());
		size.setActionCommand("size");
		size.addActionListener(this);

		size.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(sizeString, hdd.getSize()));

		panel.add(labels[3]);
		panel.add(size);



		// The transfer speed of the hdd
		labels[4].setLabelFor(transferSpeed);
		String[] transSpeedString = { "", "16", "33", "66", "80", "133", "150", "300", "600" };
		transferSpeed = new JComboBox(typeString);
		transferSpeed.setMaximumSize(tfSize);
		transferSpeed.setPreferredSize(tfSize);
		transferSpeed.setBackground(Color.WHITE);
		transferSpeed.setToolTipText(labels[1].getToolTipText());
		transferSpeed.setActionCommand("Speed");
		transferSpeed.addActionListener(this);

		transferSpeed.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(transSpeedString, hdd
				.getSpeed()));


		panel.add(labels[4]);
		panel.add(transferSpeed);



		// The rpm of the hdd
		labels[5].setLabelFor(rpm);
		String[] rpmString = { "", "5400", "7200", "10000", "15000" };
		rpm = new JComboBox(rpmString);
		rpm.setMaximumSize(tfSize);
		rpm.setPreferredSize(tfSize);
		rpm.setBackground(Color.WHITE);
		rpm.setToolTipText(labels[1].getToolTipText());
		rpm.setActionCommand("Speed");
		rpm.addActionListener(this);

		rpm.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(rpmString, hdd.getRPM()));


		panel.add(labels[5]);
		panel.add(rpm);


		// Lay out the panel.
		SpringUtilities.makeCompactGrid(panel, 2, 6, // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad
		

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
		if ( name.getText() != "" )
		{
			mainHDD.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainHDD.setDescription(desc.getText());
		}
		
		if ( producer.getText() != "" )
		{
			mainHDD.setProducer(producer.getText());
		}
		
		if ( type.getSelectedItem().toString() != "" )
		{
//			// Will remove any objects with the given class from the components
//			// array of the motherboard object if the motherboard variable does
//			// not match the editor variable.
//			GraphicalFunctions.removeComponentFromObject(HDD.class, mainHDD.getType(), type
//					.getSelectedItem().toString(), mainObj);
			
			mainHDD.setType(type.getSelectedItem().toString());
		}
		
		if ( subtype.getSelectedItem().toString() != "" )
		{
			mainHDD.setSubtype(subtype.getSelectedItem().toString());
		}
		
		if ( size.getSelectedItem().toString() != "" )
		{
			mainHDD.setSize(Integer.parseInt(size.getSelectedItem().toString()));
		}
		
		if ( transferSpeed.getSelectedItem().toString() != "" )
		{
			mainHDD.setSpeed(Integer.parseInt(transferSpeed.getSelectedItem().toString()));
		}
		
		if ( rpm.getSelectedItem().toString() != "" )
		{
			mainHDD.setRPM(Integer.parseInt(rpm.getSelectedItem().toString()));
		}

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
		// Checks the name of the motherboard
		if ( name.getText().length() < 1 || name.getText().length() > 255 )
		{
			JOptionPane.showMessageDialog(this,
					"The motherboard name must be between 1 and 255 characters.", "Error - Name",
					JOptionPane.INFORMATION_MESSAGE);

			return false;
		}

		// Checks the description of the motherboard.
		if ( desc.getText().length() < 1 )
		{
			JOptionPane.showMessageDialog(this,
					"The motherboard description must be longer then 1 character.",
					"Error - Description", JOptionPane.INFORMATION_MESSAGE);

			return false;
		}

		// Possibility for more checks on the CPU values.
		
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


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof JComboBox )
		{
			JComboBox box = (JComboBox) e.getSource();

			String command = box.getActionCommand();
			
			if ( command.equals("Type") )
			{
				String msg = "The HDD will no longer be compatiable with the motherboard.\n\nDo you want to keep this change?";

				String[] typeString = { "", "IDE", "SATA", "USB" };

				type = GraphicalFunctions.verifyChange(this, mainObj, HDD.class, mainHDD.getType()
						, type.getSelectedItem().toString(), msg, typeString,
						type);
			}
			
		}
	}
}
