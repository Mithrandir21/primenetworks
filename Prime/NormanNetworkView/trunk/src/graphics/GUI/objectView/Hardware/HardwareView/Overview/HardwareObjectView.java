/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.objectView.Hardware.HardwareView.Overview;


import graphics.GraphicalFunctions;
import graphics.PrimeMain;
import graphics.GUI.objectView.Hardware.NewComponent.NewOverview.NewComponentChoice;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import objects.Clients;
import objects.Object;
import objects.Servers;
import objects.hardwareObjects.CPU;
import objects.hardwareObjects.Discdrive;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.GraphicsCard;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import objects.hardwareObjects.Ram;
import objects.rackUnits.Rack;


/**
 * This class extends the JPanel class and contains methods to create smaller
 * JPanel that are placed inside this JPanel which show information about the
 * internal components of any object in the system. It also is the place the one
 * can open a HardwareEditor JFrame to edit the hardware information.
 * 
 * @author Bahram Malaekeh
 */
@SuppressWarnings("serial")
public class HardwareObjectView extends JPanel implements ActionListener
{
	private Object givenObject = null;

	private HardwareEditor hwEditor = null;


	/**
	 * This constructor calls the populiateInfo method.
	 * 
	 * @param obj
	 */
	public HardwareObjectView(Object obj)
	{
		populiateInfo(obj);
	}


	/**
	 * Creates and places JPanels with some information about the internal
	 * components of the given object. It packs the JPanel and places them two
	 * in a row.
	 * 
	 * @param obj
	 */
	public void populiateInfo(Object obj)
	{
		givenObject = obj;

		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();


		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		// d.anchor = GridBagConstraints.NORTH; // location
		// d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column

		ImageIcon temp = null;

		Object[] hwObj = obj.getComponents();

		String[] info = null;


		JPanel hwPanel = new JPanel(new GridBagLayout());
		GridBagConstraints d2 = new GridBagConstraints();


		d2.fill = GridBagConstraints.BOTH;
		// d2.ipady = 0; // reset to default
		// d2.ipadx = 0; // reset to default
		// d2.weighty = 1.0; // request any extra vertical space
		d2.weightx = 0.1; // request any extra horizontal space
		d2.anchor = GridBagConstraints.NORTHWEST; // location
		d2.insets = new Insets(10, 10, 10, 10); // padding
		// d2.gridwidth = 1; // 2 row wide
		d2.gridheight = 1; // 2 columns wide
		d2.gridy = 0; // row
		d2.gridx = 0; // column


		if ( hwObj != null && hwObj.length > 0 )
		{
			for ( int i = 0; i < hwObj.length; i++ )
			{
				if ( hwObj[i] instanceof Motherboard )
				{
					temp = PrimeMain.objectImageIcons.get(Motherboard.class);

					Motherboard mbObj = (Motherboard) hwObj[i];

					info = new String[5];

					String text = null;

					text = mbObj.getObjectName();
					if ( text != null && text.length() > 0 )
					{
						info[0] = text;
					}

					text = mbObj.getForm();
					if ( text != null && text.length() > 0 )
					{
						info[1] = PrimeMain.texts.getString("hwTabFormLabel")
								+ ": " + text;
					}

					text = mbObj.getSocket();
					if ( text != null && text.length() > 0 )
					{
						info[2] = PrimeMain.texts.getString("hwTabSocketLabel")
								+ ": " + text;
					}

					text = mbObj.getGraphicalPort();
					if ( text != null && text.length() > 0 )
					{
						info[3] = PrimeMain.texts
								.getString("hwTabGPUslotLabel") + ": " + text;
					}

					text = mbObj.getDescription();
					if ( text != null && text.length() > 0 )
					{
						info[4] = text;
					}


				}
				else if ( hwObj[i] instanceof CPU )
				{
					temp = PrimeMain.objectImageIcons.get(CPU.class);

					CPU cpuObj = (CPU) hwObj[i];

					info = new String[4];

					String text = null;

					text = cpuObj.getObjectName();
					if ( text != null && text.length() > 0 )
					{
						info[0] = text;
					}

					text = cpuObj.getProducer();
					if ( text != null && text.length() > 0 )
					{
						info[1] = PrimeMain.texts
								.getString("hwTabProducerLabel") + ": " + text;
					}

					text = cpuObj.getSocket();
					if ( text != null && text.length() > 0 )
					{
						info[2] = PrimeMain.texts.getString("hwTabSocketLabel")
								+ ": " + text;
					}

					text = cpuObj.getDescription();
					if ( text != null && text.length() > 0 )
					{
						info[3] = text;
					}


				}
				else if ( hwObj[i] instanceof HDD )
				{
					temp = PrimeMain.objectImageIcons.get(HDD.class);

					HDD hddObj = (HDD) hwObj[i];

					info = new String[5];

					String text = null;

					text = hddObj.getObjectName();
					if ( text != null && text.length() > 0 )
					{
						info[0] = text;
					}

					text = hddObj.getProducer();
					if ( text != null && text.length() > 0 )
					{
						info[1] = PrimeMain.texts
								.getString("hwTabProducerLabel") + ": " + text;
					}

					text = hddObj.getSubtype();
					if ( text != null && text.length() > 0 )
					{
						info[2] = PrimeMain.texts.getString("hwTabTypeLabel")
								+ ": " + text;
					}

					if ( hddObj.getSize() != 0 )
					{
						info[3] = PrimeMain.texts.getString("hwTabSizeLabel")
								+ ": " + Integer.toString(hddObj.getSize());
					}

					text = hddObj.getDescription();
					if ( text != null && text.length() > 0 )
					{
						info[4] = text;
					}

				}
				else if ( hwObj[i] instanceof Ram )
				{
					temp = PrimeMain.objectImageIcons.get(Ram.class);

					Ram ramObj = (Ram) hwObj[i];

					info = new String[5];

					String text = null;

					text = ramObj.getObjectName();
					if ( text != null && text.length() > 0 )
					{
						info[0] = text;
					}

					text = ramObj.getProducer();
					if ( text != null && text.length() > 0 )
					{
						info[1] = PrimeMain.texts
								.getString("hwTabProducerLabel") + ": " + text;
					}

					text = ramObj.getSubtype();
					if ( text != null && text.length() > 0 )
					{
						info[2] = PrimeMain.texts.getString("hwTabTypeLabel")
								+ ": " + text;
					}

					if ( ramObj.getSize() != 0 )
					{
						info[3] = PrimeMain.texts.getString("hwTabSizeLabel")
								+ ": " + Integer.toString(ramObj.getSize());
					}

					text = ramObj.getDescription();
					if ( text != null && text.length() > 0 )
					{
						info[4] = text;
					}

				}
				else if ( hwObj[i] instanceof Discdrive )
				{
					temp = PrimeMain.objectImageIcons.get(Discdrive.class);

					Discdrive discObj = (Discdrive) hwObj[i];

					info = new String[5];

					String text = null;

					text = discObj.getObjectName();
					if ( text != null && text.length() > 0 )
					{
						info[0] = text;
					}

					text = discObj.getProducer();
					if ( text != null && text.length() > 0 )
					{
						info[1] = PrimeMain.texts
								.getString("hwTabProducerLabel") + ": " + text;
					}

					text = discObj.getSubtype();
					if ( text != null && text.length() > 0 )
					{
						info[2] = PrimeMain.texts.getString("hwTabTypeLabel")
								+ ": " + text;
					}

					if ( discObj.getSpeed() != 0 )
					{
						info[3] = PrimeMain.texts.getString("hwTabSpeedLabel")
								+ ": " + Integer.toString(discObj.getSpeed());
					}

					text = discObj.getDescription();
					if ( text != null && text.length() > 0 )
					{
						info[4] = text;
					}

				}
				else if ( hwObj[i] instanceof GraphicsCard )
				{
					temp = PrimeMain.objectImageIcons.get(GraphicsCard.class);

					GraphicsCard gpuObj = (GraphicsCard) hwObj[i];

					info = new String[5];

					String text = null;

					text = gpuObj.getObjectName();
					if ( !gpuObj.getObjectName().equals("")
							&& gpuObj.getObjectName() != null )
					{
						info[0] = gpuObj.getObjectName();
					}

					text = gpuObj.getProducer();
					if ( text != null && text.length() > 0 )
					{
						info[1] = PrimeMain.texts
								.getString("hwTabProducerLabel") + ": " + text;
					}

					text = gpuObj.getOutputInterface();
					if ( text != null && text.length() > 0 )
					{
						info[2] = PrimeMain.texts.getString("hwTabOutputLabel")
								+ ": " + text;
					}

					if ( gpuObj.getSpeed() != 0 )
					{
						info[3] = PrimeMain.texts.getString("hwTabSpeedLabel")
								+ ": " + Integer.toString(gpuObj.getSpeed());
					}

					text = gpuObj.getDescription();
					if ( text != null && text.length() > 0 )
					{
						info[4] = text;
					}

				}
				else if ( hwObj[i] instanceof InternalNetworksCard )
				{
					temp = PrimeMain.objectImageIcons
							.get(InternalNetworksCard.class);

					InternalNetworksCard nicObj = (InternalNetworksCard) hwObj[i];

					info = new String[5];

					String text = null;

					text = nicObj.getObjectName();
					if ( text != null && text.length() > 0 )
					{
						info[0] = text;
					}

					text = nicObj.getProducer();
					if ( text != null && text.length() > 0 )
					{
						info[1] = PrimeMain.texts
								.getString("hwTabProducerLabel") + ": " + text;
					}

					text = nicObj.getProtocol();
					if ( text != null && text.length() > 0 )
					{
						info[2] = PrimeMain.texts
								.getString("hwTabProducerLabel") + ": " + text;
					}

					if ( nicObj.getSpeed() != 0 )
					{
						info[3] = PrimeMain.texts
								.getString("hwTabProducerLabel")
								+ ": "
								+ Integer.toString(nicObj.getSpeed());
					}

					text = nicObj.getDescription();
					if ( text != null && text.length() > 0 )
					{
						info[4] = text;
					}

				}
				else if ( hwObj[i] instanceof ExternalNetworksCard )
				{
					temp = PrimeMain.objectImageIcons
							.get(ExternalNetworksCard.class);

					ExternalNetworksCard nicObj = (ExternalNetworksCard) hwObj[i];

					info = new String[5];

					String text = null;

					text = nicObj.getObjectName();
					if ( text != null && text.length() > 0 )
					{
						info[0] = text;
					}

					text = nicObj.getProducer();
					if ( text != null && text.length() > 0 )
					{
						info[1] = PrimeMain.texts
								.getString("hwTabProducerLabel") + ": " + text;
					}

					text = nicObj.getProtocol();
					if ( text != null && text.length() > 0 )
					{
						info[2] = PrimeMain.texts
								.getString("hwTabProducerLabel") + ": " + text;
					}

					if ( nicObj.getSpeed() != 0 )
					{
						info[3] = PrimeMain.texts
								.getString("hwTabProducerLabel")
								+ ": "
								+ Integer.toString(nicObj.getSpeed());
					}

					text = nicObj.getDescription();
					if ( text != null && text.length() > 0 )
					{
						info[4] = text;
					}
				}

				assert temp != null;



				if ( i == 0 )
				{
					d2.gridheight = 2;
					d2.gridx = 0;
					d2.gridy = 0;
				}
				else if ( i == 1 )
				{
					d2.weightx = 0; // request any extra horizontal space
					d2.gridheight = 1;
					d2.gridx = 1;
					d2.gridy = 0;
				}
				else if ( i == 2 )
				{
					d2.weightx = 0.5; // request any extra horizontal space
					d2.gridx = 1;
					d2.gridy = 1;
				}
				else if ( i == 3 )
				{
					d2.gridx = 0;
					d2.gridy = 2;
				}
				else if ( i % 2 == 0 )
				{
					d2.gridx = 1;
				}
				else
				{
					d2.gridx = 0;
					d2.gridy++;
				}


				JPanel panel = createHardwareJPanel(info, temp);
				panel.addMouseListener(new HardwareMouseListener(panel,
						givenObject, hwObj[i]));
				hwPanel.add(panel, d2);
			}


			if ( hwObj.length < 3 )
			{
				if ( hwPanel.getComponentCount() == 1 )
				{
					// Adds a big JPanel at the bottom right to take the
					// remaining space
					JPanel emptyPanel1 = new JPanel();
					d2.weightx = 0.34; // request any extra horizontal space
					d2.gridx = 1;
					d2.gridy = 0;
					d2.gridheight = 1;
					d2.gridwidth = 1;
					// d2.weighty = 1.0;
					hwPanel.add(emptyPanel1, d2);


					// Adds a big JPanel at the bottom right to take the
					// remaining space
					JPanel emptyPanel2 = new JPanel();
					d2.gridx = 1;
					d2.gridy = 1;
					// d2.gridwidth = 1;
					// d2.weighty = 1.0;
					hwPanel.add(emptyPanel2, d2);
				}
				/**
				 * Since there can not be a device without a Motherboard, the
				 * object
				 * has at least one object. So the count can only be 2 or 3.
				 */
				else
				{
					// Adds a big JPanel at the bottom right to take the
					// remaining space
					JPanel emptyPanel = new JPanel();
					d2.weightx = 0.10; // request any extra horizontal space
					d2.gridx = 1;
					d2.gridy = 1;
					d2.gridheight = 1;
					d2.gridwidth = 1;
					// d2.weighty = 1.0;
					hwPanel.add(emptyPanel, d2);
				}
			}
		}


		// Adds a big JPanel at the bottom to take the remaining space
		JPanel emptyPanel = new JPanel();
		d2.gridx = 0;
		d2.gridy++;
		d2.gridwidth = 2;
		d2.weighty = 1.0;
		hwPanel.add(emptyPanel, d2);


		JScrollPane hwScroll = new JScrollPane(hwPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		hwScroll.setBorder(BorderFactory.createEmptyBorder());

		this.add(hwScroll, d);




		d.fill = GridBagConstraints.VERTICAL;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 0.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.BASELINE_TRAILING; // location
		d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 1; // row
		d.gridx = 0; // column


		// Adds the panel with the buttons to the main panel
		this.add(getButtonsPanel(obj), d);

	}


	/**
	 * The buttons to the bottom of the Object view.
	 */
	private JPanel getButtonsPanel(Object obj)
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		if ( !(obj instanceof Rack) )
		{
			Button edit = new Button("Edit Components");
			edit.addActionListener(this);
			edit.setActionCommand("edit");

			buttons.add(edit);


			// If the object is a Client or Server Object, the user can add
			// components.
			if ( obj instanceof Clients || obj instanceof Servers )
			{
				Button addNew = new Button("New Components");
				addNew.addActionListener(this);
				addNew.setActionCommand("newComp");

				buttons.add(addNew);
			}
		}

		return buttons;
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
	public static JPanel createHardwareJPanel(String[] texts, ImageIcon icon)
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
				JLabel text = new JLabel(
						GraphicalFunctions.verifyDescriptionLength(texts[i]));
				// text.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(text, d);
				d.gridy++;
			}
		}


		return panel;
	}



	/**
	 * Gets the hardware editor that where hardware can be edited.
	 */
	public HardwareEditor createNewHardwareEditor(Object obj)
	{
		return new HardwareEditor(obj);
	}




	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("edit") )
		{
			if ( givenObject.getComponents() != null
					&& givenObject.getComponents().length != 0 )
			{
				hwEditor = new HardwareEditor(givenObject);
			}
		}
		else if ( e.getActionCommand().equals("newComp") )
		{
			new NewComponentChoice(givenObject);
		}
	}



	/**
	 * This method removes all the JPanels showing the components information
	 * and then creates them again with the current information. This method is
	 * used when hardware information is changed or a component is added or
	 * removed. It also calls the update function in the Hardware Editor view.
	 */
	public void updateTabInfo()
	{
		if ( hwEditor != null )
		{
			hwEditor.HardwarePanelRevalidate();
		}

		this.removeAll();

		this.populiateInfo(givenObject);

		this.repaint();
		this.revalidate();
	}
}
