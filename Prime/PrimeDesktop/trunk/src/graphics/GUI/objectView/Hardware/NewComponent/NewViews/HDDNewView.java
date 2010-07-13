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
import objects.hardwareObjects.HDD;
import connections.ConnectionUtils;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class HDDNewView extends JDialog implements HardwareViewInterface, ActionListener
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
	public HDDNewView(Object obj, HDD hdd)
	{
		this.setTitle(PrimeMain.texts.getString("newHWnewHDDlabel"));


		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));



		mainObj = obj;
		mainHDD = hdd;
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

		ImageIcon icon = PrimeMain.objectImageIcons.get(HDD.class);
		JPanel p1 = HardwareEditor.GeneralInfo(hdd, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		JPanel p2 = createSpesificInfo(hdd);
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
	 * @param hdd
	 *            The Hardware that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(HDD hdd)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[6];

		labels[0] = new JLabel(PrimeMain.texts
				.getString("hddViewProducerLabel"));
		labels[0].setToolTipText(PrimeMain.texts
				.getString("hddViewProducerTip"));

		labels[1] = new JLabel(PrimeMain.texts.getString("hddViewTypeLabel"));
		labels[1].setToolTipText(PrimeMain.texts.getString("hddViewTypeTip"));

		labels[2] = new JLabel(PrimeMain.texts.getString("hddViewSubtypeLabel"));
		labels[2]
				.setToolTipText(PrimeMain.texts.getString("hddViewSubtypeTip"));

		labels[3] = new JLabel(PrimeMain.texts.getString("hddViewSizeLabel"));
		labels[3].setToolTipText(PrimeMain.texts.getString("hddViewSizeTip"));

		labels[4] = new JLabel(PrimeMain.texts.getString("hddViewSpeedLabel"));
		labels[4].setToolTipText(PrimeMain.texts.getString("hddViewSpeedTip"));

		labels[5] = new JLabel(PrimeMain.texts.getString("hddViewRPMLabel"));
		labels[5].setToolTipText(PrimeMain.texts.getString("hddViewRPMTip"));


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
		String[] typeString = { "", "IDE", "SATA", ConnectionUtils.USB };
		type = new JComboBox(typeString);
		type.setMaximumSize(tfSize);
		type.setPreferredSize(tfSize);
		type.setBackground(Color.WHITE);
		type.setToolTipText(labels[1].getToolTipText());
		type.setActionCommand("Type");
		type.addActionListener(this);

		type.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				typeString, hdd.getPort()));


		panel.add(labels[1]);
		panel.add(type);



		// The subtype of the hdd
		labels[2].setLabelFor(subtype);
		String[] subtypeString = { "", "ATA-100", "ATA-133", "SATA-150",
				"SATA-300", "USBv1", "USBv2", "eSATA" };
		subtype = new JComboBox(subtypeString);
		subtype.setMaximumSize(tfSize);
		subtype.setPreferredSize(tfSize);
		subtype.setBackground(Color.WHITE);
		subtype.setToolTipText(labels[2].getToolTipText());
		subtype.setActionCommand("Subtype");
		subtype.addActionListener(this);

		subtype.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				subtypeString, hdd.getSubtype()));


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

		size.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				sizeString, hdd.getSize()));

		panel.add(labels[3]);
		panel.add(size);



		// The transfer speed of the hdd
		labels[4].setLabelFor(transferSpeed);
		String[] transSpeedString = { "", "16", "33", "66", "80", "133", "150",
				"300", "600" };
		transferSpeed = new JComboBox(typeString);
		transferSpeed.setMaximumSize(tfSize);
		transferSpeed.setPreferredSize(tfSize);
		transferSpeed.setBackground(Color.WHITE);
		transferSpeed.setToolTipText(labels[1].getToolTipText());
		transferSpeed.setActionCommand("Speed");
		transferSpeed.addActionListener(this);

		transferSpeed.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				transSpeedString, hdd.getSpeed()));


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

		rpm.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(rpmString,
				hdd.getRPM()));


		panel.add(labels[5]);
		panel.add(rpm);


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
			mainHDD.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainHDD.setDescription(desc.getText());
		}

		mainHDD.setProducer(producer.getText());

		mainHDD.setPort(type.getSelectedItem().toString());

		mainHDD.setSubtype(subtype.getSelectedItem().toString());


		if ( size.getSelectedItem().toString() != "" )
		{
			mainHDD
					.setSize(Integer
							.parseInt(size.getSelectedItem().toString()));
		}
		else
		{
			mainHDD.setSize(0);
		}


		if ( transferSpeed.getSelectedItem().toString() != "" )
		{
			mainHDD.setSpeed(Integer.parseInt(transferSpeed.getSelectedItem()
					.toString()));
		}
		else
		{
			mainHDD.setSpeed(0);
		}


		if ( rpm.getSelectedItem().toString() != "" )
		{
			mainHDD.setRPM(Integer.parseInt(rpm.getSelectedItem().toString()));
		}
		else
		{
			mainHDD.setRPM(0);
		}

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
				ComponentsManagment.processHDDmatch(mainObj, mainHDD, this);

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
		return mainHDD;
	}
}
