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
package graphics.GUI.standardObjectEdit.StandardViews;


import graphics.GraphicalFunctions;
import graphics.PrimeMain;
import graphics.GUI.objectView.Hardware.NewComponent.NewOverview.NewComponentChoice;

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
import javax.swing.JLabel;
import javax.swing.JPanel;

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
import objects.peripheralObjects.GenericDevice;
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

	private StandardObjectHardwareEditor hwEditor = null;


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

		int hwCount = 0;

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

		ImageIcon temp = null;

		Object[] hwObj = obj.getComponents();

		String[] info = null;


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
						info[1] = "Form: " + text;
					}

					text = mbObj.getSocket();
					if ( text != null && text.length() > 0 )
					{
						info[2] = "Socket: " + text;
					}

					text = mbObj.getGraphicalPort();
					if ( text != null && text.length() > 0 )
					{
						info[3] = "GPU slot: " + text;
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
						info[1] = "Producer: " + text;
					}

					text = cpuObj.getSocket();
					if ( text != null && text.length() > 0 )
					{
						info[2] = "Socket: " + text;
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
						info[1] = "Producer: " + text;
					}

					text = hddObj.getSubtype();
					if ( text != null && text.length() > 0 )
					{
						info[2] = "Type: " + text;
					}

					if ( hddObj.getSize() != 0 )
					{
						info[3] = "Size: " + Integer.toString(hddObj.getSize());
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
						info[1] = "Producer: " + text;
					}

					text = ramObj.getSubtype();
					if ( text != null && text.length() > 0 )
					{
						info[2] = "Type: " + text;
					}

					if ( ramObj.getSize() != 0 )
					{
						info[3] = "Size: " + Integer.toString(ramObj.getSize());
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
						info[1] = "Producer: " + text;
					}

					text = discObj.getSubtype();
					if ( text != null && text.length() > 0 )
					{
						info[2] = "Type: " + text;
					}

					if ( discObj.getSpeed() != 0 )
					{
						info[3] = "Speed: "
								+ Integer.toString(discObj.getSpeed());
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
						info[1] = "Producer: " + text;
					}

					text = gpuObj.getOutputInterface();
					if ( text != null && text.length() > 0 )
					{
						info[2] = "Output: " + text;
					}

					if ( gpuObj.getSpeed() != 0 )
					{
						info[3] = "Speed: "
								+ Integer.toString(gpuObj.getSpeed());
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
						info[1] = "Producer: " + text;
					}

					text = nicObj.getProtocol();
					if ( text != null && text.length() > 0 )
					{
						info[2] = "Output: " + text;
					}

					if ( nicObj.getSpeed() != 0 )
					{
						info[3] = "Speed: "
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
						info[1] = "Producer: " + text;
					}

					text = nicObj.getProtocol();
					if ( text != null && text.length() > 0 )
					{
						info[2] = "Output: " + text;
					}

					if ( nicObj.getSpeed() != 0 )
					{
						info[3] = "Speed: "
								+ Integer.toString(nicObj.getSpeed());
					}

					text = nicObj.getDescription();
					if ( text != null && text.length() > 0 )
					{
						info[4] = text;
					}
				}

				assert temp != null;

				hwCount++;


				JPanel panel = createHardwareJPanel(info, temp);
				panel.addMouseListener(new HardwareMouseListener(panel,
						givenObject, hwObj[i]));
				this.add(panel, c);

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

			/**
			 * Creates empty JPanels and adds them to the main panel until there
			 * are
			 * 8 panels in the main panel. This is done so that the panels that
			 * actually have content will be placed correctly.
			 */
			while ( hwCount < 8 )
			{
				JPanel panel = new JPanel();
				this.add(panel, c);

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
		}



		JLabel temp1 = new JLabel("");
		temp1.setMaximumSize(new Dimension(90, 20));
		temp1.setPreferredSize(new Dimension(90, 20));


		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.BOTH;

		d.gridx = 0;

		if ( hwCount % 2 == 0 )
		{
			d.gridy = c.gridy++;
		}
		else
		{
			// Adds an empty components so that the spacing comes out correct.
			// Because of the SpringUtilities.makeCompactGrid.
			this.add(temp1);
			d.gridy = c.gridy++;
		}



		d.gridy = c.gridy++;
		d.weightx = 1;
		d.weighty = 1;
		d.gridwidth = 2;
		d.gridheight = 1;
		d.insets = new Insets(10, 10, 10, 10);

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
			Button edit = new Button(
					PrimeMain.texts.getString("hwTabHWEditComponentLabel"));
			edit.addActionListener(this);
			edit.setActionCommand("edit");

			buttons.add(edit);


			boolean addNewCompButton = false;

			// If the object is a Client or Server Object, the user can add
			// components.
			if ( obj instanceof Clients || obj instanceof Servers
					|| obj instanceof GenericDevice )
			{
				addNewCompButton = true;
			}

			if ( addNewCompButton )
			{
				Button addNew = new Button(
						PrimeMain.texts.getString("hwTabHWNewComponentLabel"));
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
	public StandardObjectHardwareEditor createNewHardwareEditor(Object obj)
	{
		return hwEditor = new StandardObjectHardwareEditor(obj);
	}




	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("edit") )
		{
			hwEditor = new StandardObjectHardwareEditor(givenObject);
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


	/**
	 * This method removes all the JPanels showing the components information
	 * and then creates them again with the information from the given Object.
	 * This method is used when hardware object is changed.It also calls the
	 * update function in the Hardware Editor view.
	 */
	public void changeObjectView(Object obj)
	{
		if ( hwEditor != null )
		{
			hwEditor.HardwarePanelRevalidate();
		}

		givenObject = obj;


		this.removeAll();

		this.populiateInfo(givenObject);

		this.repaint();
		this.revalidate();
	}


	/**
	 * Returns the {@link Object} being shown in this class.
	 */
	public Object getGivenObject()
	{
		return givenObject;
	}


	/**
	 * Returns the {@link StandardObjectHardwareEditor}.
	 */
	public StandardObjectHardwareEditor getHardwareView()
	{
		return hwEditor;
	}



	/**
	 * This functions disposes the {@link StandardObjectHardwareEditor} editor
	 * and sets it to NULL.
	 */
	public void disposeHardwareView()
	{
		if ( hwEditor != null )
		{
			hwEditor.dispose();
			hwEditor = null;
		}
	}
}
