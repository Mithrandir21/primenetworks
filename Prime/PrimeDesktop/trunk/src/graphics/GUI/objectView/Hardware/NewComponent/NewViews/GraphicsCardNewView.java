/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.objectView.Hardware.NewComponent.NewViews;


import exceptions.MotherboardNotFound;
import graphics.GraphicalFunctions;
import graphics.PrimeMain;
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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
public class GraphicsCardNewView extends JDialog implements HardwareViewInterface, ActionListener
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
	public GraphicsCardNewView(Object obj, GraphicsCard GPU)
	{
		this.setTitle(PrimeMain.texts.getString("newHWnewGPUlabel"));


		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));



		mainObj = obj;
		mainGC = GPU;
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.WEST; // location
		c.insets = new Insets(10, 10, 5, 10); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column

		ImageIcon icon = PrimeMain.objectImageIcons.get(GraphicsCard.class);
		JPanel p1 = HardwareEditor.GeneralInfo(GPU, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		JPanel p2 = createSpesificInfo(GPU);
		p2.setBorder(BorderFactory.createEtchedBorder());

		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1.0; // request any extra vertical space
		c.insets = new Insets(0, 10, 0, 10);
		this.add(p2, c);



		JPanel buttons = createButtons();
		buttons.setBorder(BorderFactory.createEtchedBorder());

		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0; // request any extra vertical space
		c.insets = new Insets(2, 10, 10, 10);
		this.add(buttons, c);




		this.setMinimumSize(new Dimension((int) scrnsize.getWidth() / 3,
				(int) scrnsize.getHeight() / 3));
		this.setSize(width, height);
		this.setVisible(true);
	}


	/**
	 * This method creates and returns a JPanel that contains all the different
	 * settings of the given Hardware object. It uses the {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param GPU
	 *            The Hardware that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(GraphicsCard GPU)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[7];

		labels[0] = new JLabel(PrimeMain.texts
				.getString("gpuViewProducerLabel"));
		labels[0].setToolTipText(PrimeMain.texts
				.getString("gpuViewProducerTip"));

		labels[1] = new JLabel(PrimeMain.texts
				.getString("gpuViewInterfaceLabel"));
		labels[1].setToolTipText(PrimeMain.texts
				.getString("gpuViewInterfaceTip"));

		labels[2] = new JLabel(PrimeMain.texts
				.getString("gpuViewOutputPortLabel"));
		labels[2].setToolTipText(PrimeMain.texts
				.getString("gpuViewOutputPortTip"));

		labels[3] = new JLabel(PrimeMain.texts.getString("gpuViewSizeLabel"));
		labels[3].setToolTipText(PrimeMain.texts.getString("gpuViewSizeTip"));

		labels[4] = new JLabel(PrimeMain.texts.getString("gpuViewSpeedLabel"));
		labels[4].setToolTipText(PrimeMain.texts.getString("gpuViewSpeedTip"));

		labels[5] = new JLabel(PrimeMain.texts
				.getString("gpuViewMaxMonitorsLabel"));
		labels[5].setToolTipText(PrimeMain.texts
				.getString("gpuViewMaxMonitorsTip"));

		labels[6] = new JLabel(PrimeMain.texts
				.getString("gpuViewIsIntegratedLabel"));
		labels[6].setToolTipText(PrimeMain.texts
				.getString("gpuViewIsIntegratedTip"));


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

		type.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				typeString, GPU.getType()));


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

		outputInterface.setSelectedIndex(GraphicalFunctions
				.getIndexInJComboBox(portString, GPU.getOutputInterface()));


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

		size.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				sizeString, GPU.getSize()));


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

		speed.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				speedString, GPU.getSpeed()));


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

		maxMonitors.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				monitorsString, GPU.getMaxMonitors()));


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


		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel,
				panel.getComponentCount(), // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad


		return panel;
	}


	/**
	 * Creates a JPanel with two buttons that are listened for by
	 * actionlisteners.
	 */
	private JPanel createButtons()
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));


		Button save = new Button(PrimeMain.texts.getString("save"));
		save.addActionListener(this);
		save.setActionCommand("save");

		Button cancel = new Button(PrimeMain.texts.getString("cancel"));
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(cancel);

		return buttons;
	}


	@Override
	public boolean save()
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

		mainGC.setSupportedConnectionInterfaces(outputInterface
				.getSelectedItem().toString());


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
			mainGC.setSpeed(Integer
					.parseInt(speed.getSelectedItem().toString()));
		}
		else
		{
			mainGC.setSpeed(0);
		}


		if ( maxMonitors.getSelectedItem().toString() != "" )
		{
			mainGC.setMaxMonitors(Integer.parseInt(maxMonitors
					.getSelectedItem().toString()));
		}
		else
		{
			mainGC.setMaxMonitors(0);
		}


		mainGC.setIsIntegrated(isIntegrated.isSelected());

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			// Saves the current values of the new motherboard.
			save();

			try
			{
				ComponentsManagment.processGPUmatch(mainObj, mainGC, this);

				// Updates the views of the object to correctly show the
				// current info.
				ObjectView view = PrimeMain.getObjectView(mainObj);
				if ( view != null )
				{
					view.updateViewInfo();
				}
				// If no view is returned, then the standard object view is open
				// and that should be updated.
				else if ( PrimeMain.stdObjView != null )
				{
					PrimeMain.stdObjView.getSplitView().getHardStdObjView()
							.updateTabInfo();
				}


				// Closes the JFrame.
				this.dispose();
			}
			catch ( MotherboardNotFound e1 )
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
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
