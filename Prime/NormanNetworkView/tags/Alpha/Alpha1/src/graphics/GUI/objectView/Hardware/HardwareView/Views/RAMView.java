/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView.Views;


import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;
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
import objects.hardwareObjects.Ram;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class RAMView extends JPanel implements HardwareViewInterface,
		ActionListener
{
	JTextField name = new JTextField(25);

	JTextArea desc = new JTextArea(3, 40);

	private JTextField producer = new JTextField(7);

	private JComboBox type;

	private JComboBox subtype;

	private JComboBox size;

	private JComboBox speed;


	private Object mainObj;

	private Ram RAMobj;

	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
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
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(RAMobj);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);


		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel(PrimeMain1.texts
				.getString("hwTabRemoveThisComponentLabel"));

		Button remove = new Button(PrimeMain1.texts
				.getString("hwTabRemoveComponentButtonLabel"));
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
	 * This method creates and returns a JPanel that contains all the different
	 * settings of the given Hardware object. It uses the
	 * {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param ram
	 *            The Hardware that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(Ram ram)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[5];


		labels[0] = new JLabel(PrimeMain1.texts
				.getString("ramViewProducerLabel"));
		labels[0].setToolTipText(PrimeMain1.texts
				.getString("ramViewProducerTip"));

		labels[1] = new JLabel(PrimeMain1.texts.getString("ramViewTypeLabel"));
		labels[1].setToolTipText(PrimeMain1.texts.getString("ramViewTypeTip"));

		labels[2] = new JLabel(PrimeMain1.texts
				.getString("ramViewSubtypeLabel"));
		labels[2].setToolTipText(PrimeMain1.texts
				.getString("ramViewSubtypeTip"));

		labels[3] = new JLabel(PrimeMain1.texts.getString("ramViewSizeLabel"));
		labels[3].setToolTipText(PrimeMain1.texts.getString("ramViewSizeTip"));

		labels[4] = new JLabel(PrimeMain1.texts.getString("ramViewSpeedLabel"));
		labels[4].setToolTipText(PrimeMain1.texts.getString("ramViewSpeedTip"));

		Dimension tfSize = new Dimension(90, 20);


		// The producer
		labels[0].setLabelFor(producer);
		producer.setMaximumSize(tfSize);
		producer.setPreferredSize(tfSize);
		producer.setText(ram.getProducer());
		producer.setToolTipText(labels[0].getToolTipText());


		panel.add(labels[0]);
		panel.add(producer);


		// The type of the ram
		labels[1].setLabelFor(type);
		String[] typeString = { "", "SDRAM", "DDR", "DDR2", "DDR3" };
		type = new JComboBox(typeString);
		type.setMaximumSize(tfSize);
		type.setPreferredSize(tfSize);
		type.setBackground(Color.WHITE);
		type.setToolTipText(labels[1].getToolTipText());
		type.setActionCommand("Type");
		type.addActionListener(this);

		type.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				typeString, ram.getPort()));


		panel.add(labels[1]);
		panel.add(type);



		// The subtype of the ram
		labels[2].setLabelFor(subtype);
		String[] subtypeString = { "", "DIMM", "SO-DIMM" };
		subtype = new JComboBox(subtypeString);
		subtype.setMaximumSize(tfSize);
		subtype.setPreferredSize(tfSize);
		subtype.setBackground(Color.WHITE);
		subtype.setToolTipText(labels[2].getToolTipText());
		subtype.setActionCommand("Subtype");
		subtype.addActionListener(this);

		subtype.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				subtypeString, ram.getSubtype()));


		panel.add(labels[2]);
		panel.add(subtype);



		// The size of the ram
		labels[3].setLabelFor(size);
		String[] sizeString = { "", "128", "256", "512", "1024", "2048", "4096" };
		size = new JComboBox(sizeString);
		size.setMaximumSize(tfSize);
		size.setPreferredSize(tfSize);
		size.setBackground(Color.WHITE);
		size.setToolTipText(labels[3].getToolTipText());
		size.setActionCommand("Subtype");
		size.addActionListener(this);

		size.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				sizeString, ram.getSize()));


		panel.add(labels[3]);
		panel.add(size);



		// The speed of the ram
		labels[4].setLabelFor(speed);
		String[] speedString = { "", "66", "100", "133", "200", "266", "333",
				"400", "466", "500", "533", "667", "800" };
		speed = new JComboBox(speedString);
		speed.setMaximumSize(tfSize);
		speed.setPreferredSize(tfSize);
		speed.setBackground(Color.WHITE);
		speed.setToolTipText(labels[4].getToolTipText());
		speed.setActionCommand("Speed");
		speed.addActionListener(this);

		speed.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				speedString, ram.getSpeed()));


		panel.add(labels[4]);
		panel.add(speed);



		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel,
				panel.getComponentCount(), // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad


		return panel;
	}


	/*
	 * (non-Javadoc)
	 * @see graphics.GUI.objectView.Hardware.HardwareView.HardwareView#save()
	 */
	@Override
	public void save()
	{
		if ( name.getText() != "" )
		{
			RAMobj.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			RAMobj.setDescription(desc.getText());
		}

		RAMobj.setProducer(producer.getText());

		RAMobj.setPort(type.getSelectedItem().toString());

		RAMobj.setSubtype(subtype.getSelectedItem().toString());


		if ( size.getSelectedItem().toString() != "" )
		{
			RAMobj.setSize(Integer.parseInt(size.getSelectedItem().toString()));
		}
		else
		{
			RAMobj.setSize(0);
		}


		if ( speed.getSelectedItem().toString() != "" )
		{
			RAMobj.setSpeed(Integer
					.parseInt(speed.getSelectedItem().toString()));
		}
		else
		{
			RAMobj.setSpeed(0);
		}


	}



	/**
	 * This method validates necessary information that is being saved.
	 */
	public boolean validateNecessaryData()
	{
		// Checks the name of the motherboard
		if ( name.getText().length() < 1 || name.getText().length() > 255 )
		{
			JOptionPane
					.showMessageDialog(
							this,
							"The motherboard name must be between 1 and 255 characters.",
							"Error - Name", JOptionPane.INFORMATION_MESSAGE);

			return false;
		}

		// Checks the description of the motherboard.
		if ( desc.getText().length() < 1 )
		{
			JOptionPane
					.showMessageDialog(
							this,
							"The motherboard description must be longer then 1 character.",
							"Error - Description",
							JOptionPane.INFORMATION_MESSAGE);

			return false;
		}

		// Possibility for more checks on the CPU values.

		return true;
	}


	@Override
	public Hardware getViewHardware()
	{
		return RAMobj;
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof JComboBox )
		{
			JComboBox box = (JComboBox) e.getSource();

			String command = box.getActionCommand();

			if ( command.equals("Type") )
			{
				String[] typeString = { "", "SDRAM", "DDR", "DDR2", "DDR3" };

				type = GraphicalFunctions.verifyChange(this, mainObj,
						Ram.class, RAMobj.getPort(), type.getSelectedItem()
								.toString(), PrimeMain1.texts
								.getString("ramViewNotCompatiableQuestionMsg"),
						typeString, type);
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
				mainObj.setAllComponents(ComponentsManagment.removeComponent(
						RAMobj, mainObj.getComponents(), mainObj
								.getComponents().length));


				// Updates the views of the object to correctly show the
				// current info.
				ObjectView view = PrimeMain1.getObjectView(mainObj);
				if ( view != null )
				{
					view.updateViewInfo();
				}
				// If no view is returned, then the standard object view is open
				// and that should be updated.
				else if ( PrimeMain1.stdObjView != null )
				{
					PrimeMain1.stdObjView.getSplitView().getHardStdObjView()
							.updateTabInfo();
				}
			}
		}
	}
}
