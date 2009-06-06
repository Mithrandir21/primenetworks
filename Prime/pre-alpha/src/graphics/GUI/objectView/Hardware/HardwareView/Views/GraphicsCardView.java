/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView.Views;


import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.GUI.objectView.Hardware.HardwareViewInterface;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareEditor;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import managment.ComponentsManagment;
import objects.Hardware;
import objects.Object;
import objects.hardwareObjects.GraphicsCard;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class GraphicsCardView extends JPanel implements HardwareViewInterface, ActionListener
{
	JTextField name = new JTextField(25);

	JTextArea desc = new JTextArea(3, 40);

	private JTextField producer = new JTextField(7);

	private JComboBox type;

	private JComboBox outputInterface;

	private JComboBox size;

	private JComboBox speed;

	private JComboBox maxMonitors;

	private JCheckBox isIntegrated;


	private Object mainObj;

	private GraphicsCard mainGC;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 * @param GPU
	 */
	public GraphicsCardView(Object obj, GraphicsCard GPU)
	{
		mainObj = obj;
		mainGC = GPU;
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
		JPanel p1 = HardwareEditor.GeneralInfo(GPU, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());





		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(mainGC);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);


		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel("Remove this component from this device");

		Button remove = new Button("Remove Component");
		remove.addActionListener(this);
		remove.setActionCommand("removeComp");

		buttons.add(label);
		buttons.add(remove);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 0.01;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2, 10, 10, 10);

		this.add(buttons, c);
	}


	/**
	 * This method creates and returns a JPanel that contains all the different settings of the given Hardware object.
	 * It uses the {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all the different components in
	 * the JPanel in grids.
	 * 
	 * @param GPU
	 *            The Hardware that will be examined and will fill inn the fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(GraphicsCard GPU)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[7];

		labels[0] = new JLabel("Producer");
		labels[0].setToolTipText("The producer of the GPU.");

		labels[1] = new JLabel("Interface");
		labels[1].setToolTipText("The GPU interface. (PCI, AGP, PCI-E)");

		labels[2] = new JLabel("Output Port");
		labels[2].setToolTipText("The GPU output port. ");

		labels[3] = new JLabel("Size");
		labels[3].setToolTipText("The size of the RAM on the GPU.");

		labels[4] = new JLabel("Speed");
		labels[4].setToolTipText("The speed of the GPU in MHz.");

		labels[5] = new JLabel("Max Monitors");
		labels[5].setToolTipText("The number of output ports.");

		labels[6] = new JLabel("Is Integrated");
		labels[6].setToolTipText("If the GPU is integrated on the mainboard.");


		Dimension tfSize = new Dimension(90, 20);

		// The producer
		labels[0].setLabelFor(producer);
		producer.setMaximumSize(tfSize);
		producer.setPreferredSize(tfSize);
		producer.setText(GPU.getProducer());
		producer.setToolTipText(labels[0].getToolTipText());


		panel.add(labels[0]);
		panel.add(producer);


		// The type of the GPU
		labels[1].setLabelFor(type);
		String[] typeString = { "", "PCI", "AGP", "PCI-E" };
		type = new JComboBox(typeString);
		type.setMaximumSize(tfSize);
		type.setPreferredSize(tfSize);
		type.setBackground(Color.WHITE);
		type.setToolTipText(labels[1].getToolTipText());
		type.setActionCommand("Interface");
		type.addActionListener(this);

		type.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(typeString, GPU.getType()));


		panel.add(labels[1]);
		panel.add(type);



		// The output port of the GPU
		labels[2].setLabelFor(outputInterface);
		String[] portString = { "", "VGA", "DVI", };
		outputInterface = new JComboBox(portString);
		outputInterface.setMaximumSize(tfSize);
		outputInterface.setPreferredSize(tfSize);
		outputInterface.setBackground(Color.WHITE);
		outputInterface.setToolTipText(labels[2].getToolTipText());
		outputInterface.setActionCommand("Port");
		outputInterface.addActionListener(this);

		outputInterface.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(portString, GPU.getOutputInterface()));


		panel.add(labels[2]);
		panel.add(outputInterface);



		// The size of the GPU
		labels[3].setLabelFor(size);
		String[] sizeString = { "", "64", "128", "256", "512" };
		size = new JComboBox(sizeString);
		size.setMaximumSize(tfSize);
		size.setPreferredSize(tfSize);
		size.setBackground(Color.WHITE);
		size.setToolTipText(labels[3].getToolTipText());
		size.setActionCommand("Size");
		size.addActionListener(this);

		size.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(sizeString, GPU.getSize()));


		panel.add(labels[3]);
		panel.add(size);



		// The speed of the GPU
		labels[4].setLabelFor(speed);
		String[] speedString = { "", "100", "200", "300", "400", "500" };
		speed = new JComboBox(speedString);
		speed.setMaximumSize(tfSize);
		speed.setPreferredSize(tfSize);
		speed.setBackground(Color.WHITE);
		speed.setToolTipText(labels[4].getToolTipText());
		speed.setActionCommand("Speed");
		speed.addActionListener(this);

		speed.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(speedString, GPU.getSpeed()));


		panel.add(labels[4]);
		panel.add(speed);



		// The speed of the GPU
		labels[5].setLabelFor(maxMonitors);
		String[] monitorsString = { "", "1", "2", "3" };
		maxMonitors = new JComboBox(monitorsString);
		maxMonitors.setMaximumSize(tfSize);
		maxMonitors.setPreferredSize(tfSize);
		maxMonitors.setBackground(Color.WHITE);
		maxMonitors.setToolTipText(labels[5].getToolTipText());
		maxMonitors.setActionCommand("MaxMonitors");
		maxMonitors.addActionListener(this);

		maxMonitors.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(monitorsString, GPU.getMaxMonitors()));


		panel.add(labels[5]);
		panel.add(maxMonitors);


		// The dual quad check box
		labels[6].setLabelFor(isIntegrated);
		isIntegrated = new JCheckBox();
		isIntegrated.setToolTipText(labels[6].getToolTipText());
		isIntegrated.setActionCommand("Integrated");
		isIntegrated.addActionListener(this);

		isIntegrated.setSelected(GPU.getIsIntegrated());


		panel.add(labels[6]);
		panel.add(isIntegrated);



		JLabel temp1 = new JLabel("");
		temp1.setMaximumSize(tfSize);
		temp1.setPreferredSize(tfSize);

		JLabel temp2 = new JLabel("");
		temp2.setMaximumSize(tfSize);
		temp2.setPreferredSize(tfSize);

		JLabel temp3 = new JLabel("");
		temp3.setMaximumSize(tfSize);
		temp3.setPreferredSize(tfSize);

		JLabel temp4 = new JLabel("");
		temp4.setMaximumSize(tfSize);
		temp4.setPreferredSize(tfSize);
		// adding components so that the layout is right
		panel.add(temp1);
		panel.add(temp2);
		panel.add(temp3);
		panel.add(temp4);


		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel, panel.getComponentCount(), // rows, cols
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
			mainGC.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainGC.setDescription(desc.getText());
		}

		mainGC.setProducer(producer.getText());

		mainGC.setType(type.getSelectedItem().toString());

		mainGC.setSupportedConnectionInterfaces(outputInterface.getSelectedItem().toString());


		if ( size.getSelectedItem().toString() != "" )
		{
			mainGC.setSize(Integer.parseInt(size.getSelectedItem().toString()));
		}
		else
		{
			mainGC.setSize(0);
		}


		if ( speed.getSelectedItem().toString() != "" )
		{
			mainGC.setSpeed(Integer.parseInt(speed.getSelectedItem().toString()));
		}
		else
		{
			mainGC.setSpeed(0);
		}


		if ( outputInterface.getSelectedItem().toString() != "" )
		{
			mainGC.setMaxMonitors(Integer.parseInt(maxMonitors.getSelectedItem().toString()));
		}
		else
		{
			mainGC.setMaxMonitors(0);
		}


		mainGC.setIsIntegrated(isIntegrated.isSelected());

	}



	/**
	 * Checks and validates the name and description for this hardware object.
	 */
	public boolean nameAndDescriptionCheck()
	{
		// Checks the name of the motherboard
		if ( name.getText().length() < 1 || name.getText().length() > 255 )
		{
			JOptionPane.showMessageDialog(this, "The component name must be between 1 and 255 characters.",
					"Error - Name", JOptionPane.INFORMATION_MESSAGE);

			return false;
		}

		// Checks the description of the motherboard.
		if ( desc.getText().length() < 1 )
		{
			JOptionPane.showMessageDialog(this, "The component description must be longer then 1 character.",
					"Error - Description", JOptionPane.INFORMATION_MESSAGE);

			return false;
		}

		// Possibility for more checks on the CPU values.

		return true;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof JComboBox && e.getActionCommand() != "Integrated" )
		{
			JComboBox box = (JComboBox) e.getSource();

			String command = box.getActionCommand();
			if ( command.equals("Interface") )
			{
				String msg = "The GPU will no longer be compatiable with the motherboard.\n\nDo you want to keep this change?";

				String[] typeString = { "", "PCI", "AGP", "PCI-E" };

				type = GraphicalFunctions.verifyChange(this, mainObj, GraphicsCard.class, mainGC.getType(), type
						.getSelectedItem().toString(), msg, typeString, type);
			}
		}
		else if ( e.getSource() instanceof Button )
		{
			Button check = (Button) e.getSource();

			String command = check.getActionCommand();

			if ( command.equals("removeComp") )
			{
				// Will remove the first variable from the list of components
				// that will be returned and set as the components for the main
				// object.
				mainObj.setAllComponents(ComponentsManagment.removeComponent(mainGC, mainObj.getComponents(), mainObj
						.getComponents().length));

				// Updates the views of the object to correctly show the
				// current info.
				PrimeMain1.getObjectView(mainObj).updateViewInfo();
			}
		}
		else
		{
			JCheckBox box = (JCheckBox) e.getSource();

			// TODO - isIntegrated
		}
	}


	@Override
	public boolean validateNecessaryData()
	{
		return true;
	}


	@Override
	public Hardware getViewHardware()
	{
		return mainGC;
	}
}
