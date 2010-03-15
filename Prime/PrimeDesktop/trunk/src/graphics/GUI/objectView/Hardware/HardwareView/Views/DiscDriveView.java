/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView.Views;


import graphics.GraphicalFunctions;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import managment.ComponentsManagment;
import objects.Hardware;
import objects.Object;
import objects.hardwareObjects.Discdrive;
import connections.ConnectionUtils;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class DiscDriveView extends JPanel implements HardwareViewInterface, ActionListener
{
	private JTextField name = new JTextField(25);

	private JTextArea desc = new JTextArea(3, 40);

	private JTextField producer = new JTextField(7);

	private JComboBox type;

	private JComboBox port;

	private JComboBox subtype;

	private JComboBox speed;


	private Object mainObj;

	private Discdrive DiscObj;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 * @param discdrive
	 */
	public DiscDriveView(Object obj, Discdrive discdrive)
	{
		mainObj = obj;
		DiscObj = discdrive;
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

		ImageIcon icon = PrimeMain1.objectImageIcons.get(Discdrive.class);
		JPanel p1 = HardwareEditor.GeneralInfo(discdrive, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());





		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(discdrive);
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
	 * settings of the given Hardware object. It uses the {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param disc
	 *            The Hardware that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(Discdrive disc)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[5];

		labels[0] = new JLabel(PrimeMain1.texts
				.getString("discViewProducerLabel"));
		labels[0].setToolTipText(PrimeMain1.texts
				.getString("discViewProducerTip"));

		labels[1] = new JLabel(PrimeMain1.texts.getString("discViewTypeLabel"));
		labels[1].setToolTipText(PrimeMain1.texts.getString("discViewTypeTip"));

		labels[2] = new JLabel(PrimeMain1.texts.getString("discViewPortLabel"));
		labels[2].setToolTipText(PrimeMain1.texts.getString("discViewPortTip"));

		labels[3] = new JLabel(PrimeMain1.texts
				.getString("discViewSubtypeLabel"));
		labels[3].setToolTipText(PrimeMain1.texts
				.getString("discViewSubtypeTip"));

		labels[4] = new JLabel(PrimeMain1.texts.getString("discViewSpeedLabel"));
		labels[4]
				.setToolTipText(PrimeMain1.texts.getString("discViewSpeedTip"));


		Dimension tfSize = new Dimension(90, 20);


		// The producer
		labels[0].setLabelFor(producer);
		producer.setMaximumSize(tfSize);
		producer.setPreferredSize(tfSize);
		producer.setText(disc.getProducer());
		producer.setToolTipText(labels[0].getToolTipText());


		panel.add(labels[0]);
		panel.add(producer);


		// The type of the discdrive
		labels[1].setLabelFor(type);
		String[] typeString = { "", "CDROM", "DVDROM", "DVDRW", "Blu-Ray" };
		type = new JComboBox(typeString);
		type.setMaximumSize(tfSize);
		type.setPreferredSize(tfSize);
		type.setBackground(Color.WHITE);
		type.setToolTipText(labels[1].getToolTipText());
		type.setActionCommand("Type");
		type.addActionListener(this);

		type.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				typeString, disc.getType()));


		panel.add(labels[1]);
		panel.add(type);



		// The port of the discdrive
		labels[2].setLabelFor(port);
		String[] portString = { "", "IDE", "SATA", ConnectionUtils.USB };
		port = new JComboBox(portString);
		port.setMaximumSize(tfSize);
		port.setPreferredSize(tfSize);
		port.setBackground(Color.WHITE);
		port.setToolTipText(labels[2].getToolTipText());
		port.setActionCommand("Port");
		port.addActionListener(this);

		port.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				portString, disc.getPort()));


		panel.add(labels[2]);
		panel.add(port);


		// The subtype of the ram
		labels[3].setLabelFor(subtype);
		String[] subtypeString = { "", "DualLayer", "DoubleSided",
				"DualLayer/DoubleSided" };
		subtype = new JComboBox(subtypeString);
		subtype.setMaximumSize(tfSize);
		subtype.setPreferredSize(tfSize);
		subtype.setBackground(Color.WHITE);
		subtype.setToolTipText(labels[3].getToolTipText());
		subtype.setActionCommand("Subtype");
		subtype.addActionListener(this);

		subtype.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				subtypeString, disc.getSubtype()));


		panel.add(labels[3]);
		panel.add(subtype);


		// The speed of the ram
		labels[4].setLabelFor(speed);
		String[] speedString = { "", "2", "4", "6", "8", "10", "12", "14",
				"16", "18", "20", "22", "24", "32", "48" };
		speed = new JComboBox(speedString);
		speed.setMaximumSize(tfSize);
		speed.setPreferredSize(tfSize);
		speed.setBackground(Color.WHITE);
		speed.setToolTipText(labels[4].getToolTipText());
		speed.setActionCommand("Speed");
		speed.addActionListener(this);

		speed.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				speedString, disc.getSpeed()));


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
			DiscObj.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			DiscObj.setDescription(desc.getText());
		}

		DiscObj.setProducer(producer.getText());

		DiscObj.setType(type.getSelectedItem().toString());

		DiscObj.setPort(port.getSelectedItem().toString());

		DiscObj.setSubtype(subtype.getSelectedItem().toString());

		if ( speed.getSelectedItem().toString() != "" )
		{
			DiscObj.setSpeed(Integer.parseInt(speed.getSelectedItem()
					.toString()));
		}
		else
		{
			DiscObj.setSpeed(0);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof JComboBox )
		{
			JComboBox box = (JComboBox) e.getSource();

			String command = box.getActionCommand();

			if ( command.equals("Port") )
			{

				String[] portString = { "", "IDE", "SATA", ConnectionUtils.USB };

				port = GraphicalFunctions.verifyChange(this, mainObj,
						Discdrive.class, DiscObj.getPort(), port
								.getSelectedItem().toString(), PrimeMain1.texts
								.getString("discViewPortQuestionMsg"),
						portString, port);
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
						DiscObj, mainObj.getComponents(), mainObj
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


	@Override
	public boolean validateNecessaryData()
	{
		return true;
	}


	@Override
	public Hardware getViewHardware()
	{
		return DiscObj;
	}
}
