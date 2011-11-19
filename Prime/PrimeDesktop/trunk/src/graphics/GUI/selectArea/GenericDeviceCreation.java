/**
 * 
 */
package graphics.GUI.selectArea;


import exceptions.MotherboardNotFound;
import graphics.PrimeMain;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logistical.checkLogic;
import managment.ComponentsManagment;
import managment.DesktopCanvasManagment;
import objects.ExternalHardware;
import objects.hardwareObjects.Discdrive;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import objects.peripheralObjects.GenericDevice;
import connections.ConnectionUtils;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class GenericDeviceCreation extends JFrame implements ActionListener
{
	private ExternalHardware exObject = null;


	private JTextField nameField = new JTextField("");


	private JCheckBox supportsWireless = new JCheckBox(ConnectionUtils.Wireless);

	private JCheckBox supportsLAN = new JCheckBox(ConnectionUtils.RJ45);

	private JCheckBox supportsUSB = new JCheckBox(ConnectionUtils.USB);

	private JCheckBox supportsCOAX = new JCheckBox(ConnectionUtils.Coax);

	private JCheckBox supportsFIBER = new JCheckBox(ConnectionUtils.Fiber);

	private JCheckBox containsHDD = new JCheckBox(
			PrimeMain.texts.getString("genericDeviceHDDoptionLabel"));

	private JCheckBox containsDiskdrive = new JCheckBox(
			PrimeMain.texts.getString("discdrive"));


	public GenericDeviceCreation(GenericDevice obj)
	{
		this.setTitle(PrimeMain.texts.getString("genericDeviceWindowsTitle"));

		exObject = obj;

		// Set size for the settings JFrame
		Dimension size = new Dimension(240, 400);

		int initYLocation = (PrimeMain.scrnsize.height - size.height) / 3;
		int initXLocation = (PrimeMain.scrnsize.width - size.width) / 2;

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.ipadx = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra horizontal space
		c.anchor = GridBagConstraints.NORTH; // location
		c.insets = new Insets(5, 5, 5, 5); // padding
		// c.gridwidth = 1; // 1 row wide
		// c.gridheight = 1; // 1 columns wide
		c.gridy = 0; // row
		c.gridx = 0; // column


		this.add(getOptionsPanel(), c);


		JPanel buttons = createButtons();
		// buttons.setBorder(BorderFactory.createEtchedBorder());

		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0; // request any extra vertical space
		c.anchor = GridBagConstraints.SOUTH; // location
		this.add(buttons, c);



		this.setPreferredSize(size);
		this.setLocation(initXLocation, initYLocation);
		this.setMinimumSize(size);
		this.pack();
		this.setVisible(true);
	}



	private JPanel getOptionsPanel()
	{
		JPanel optionsPanel = new JPanel();

		optionsPanel.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		d.insets = new Insets(0, 15, 5, 15); // padding
		d.gridwidth = 1; // 2 row wide
		d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column


		JLabel deviceName = new JLabel(
				PrimeMain.texts.getString("genericDeviceNameLabel"));
		d.gridy++; // row
		optionsPanel.add(deviceName, d);

		d.gridy++; // row
		optionsPanel.add(nameField, d);


		// The question asked
		JLabel Question = new JLabel(
				PrimeMain.texts.getString("genericDeviceSupportsLabel"));
		d.insets = new Insets(10, 15, 5, 0); // padding
		d.gridy++; // row
		optionsPanel.add(Question, d);

		// The actual options
		d.insets = new Insets(0, 15, 5, 0); // padding
		d.gridy++; // row
		optionsPanel.add(supportsWireless, d);

		d.gridy++; // row
		optionsPanel.add(supportsLAN, d);

		d.gridy++; // row
		optionsPanel.add(supportsUSB, d);

		d.gridy++; // row
		optionsPanel.add(supportsCOAX, d);

		d.gridy++; // row
		optionsPanel.add(supportsFIBER, d);


		d.gridy++; // row
		optionsPanel.add(containsHDD, d);

		d.gridy++; // row
		optionsPanel.add(containsDiskdrive, d);


		return optionsPanel;
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


	/**
	 * This function will return this classes customized
	 * {@link ExternalHardware}.
	 */
	public ExternalHardware getCustomizedGenericDevice()
	{
		return exObject;
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			// If the written name is valid
			if ( checkLogic.validateName(nameField.getText()) )
			{
				// Sets the name and desc of the object
				exObject.setObjectName(nameField.getText());
				exObject.setDescription(nameField.getText());

				// Sets the object
				if ( setSelectedOptions() )
				{
					DesktopCanvasManagment.runAllCanvasNameUpdate();
					PrimeMain.updatePropertiesCanvasArea(true);

					this.dispose();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, PrimeMain.texts
						.getString("actionChangeWidgetNameInvalidNameText"),
						PrimeMain.texts.getString("error"),
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
		}
	}


	/**
	 * This function reads all the options provided by the user and then creates
	 * the necessary devices and ports on this class {@link ExternalHardware}.
	 */
	private boolean setSelectedOptions()
	{
		if ( exObject != null )
		{
			try
			{
				// Gets the Motherboard of the ExternalObject
				Motherboard mb = ComponentsManagment
						.getObjectMotherboard(exObject);

				if ( mb != null )
				{
					// Adds InternalNIC with wireless
					if ( supportsWireless.isSelected() )
					{
						InternalNetworksCard wNic = PrimeMain.standard_internal_components
								.getSt_IntNIC();
						wNic.setType(ConnectionUtils.Wireless);

						exObject.addComponent(wNic);
					}

					// Adds InternalNIC with wireless
					if ( supportsLAN.isSelected() )
					{
						InternalNetworksCard nic = PrimeMain.standard_internal_components
								.getSt_IntNIC();
						nic.setType(ConnectionUtils.RJ45);

						exObject.addComponent(nic);
					}

					if ( supportsUSB.isSelected() )
					{
						mb.setMaxUSBs(1);
						mb.setUSBPortsAvailable(1);
					}

					if ( supportsCOAX.isSelected() )
					{
						mb.setMaxCoaxs(1);
						mb.setCoaxPortsAvailable(1);
					}

					if ( supportsFIBER.isSelected() )
					{
						mb.setMaxFibers(1);
						mb.setFiberPortsAvailable(1);
					}

					if ( containsHDD.isSelected() )
					{
						// Creates the HDD
						HDD internalHDD = PrimeMain.standard_internal_components
								.getSt_HDD();
						internalHDD.setSize(1000);

						// Sets the MB hdd port
						mb.setDUCconnectionType(internalHDD.getPort());

						// Adds the HDD to the device
						exObject.addComponent(internalHDD);
					}

					if ( containsDiskdrive.isSelected() )
					{
						// Creates the Discdrive
						Discdrive drive = PrimeMain.standard_internal_components
								.getSt_DVDRW();

						// Sets the MB discdrive port
						mb.setDUCconnectionType(drive.getPort());

						// Adds the HDD to the device
						exObject.addComponent(drive);
					}

					// Determines the supported connection interface on the
					// device.
					exObject.revalidateSupportedConnectionInterfaces();
				}


				return true;
			}
			catch ( MotherboardNotFound e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// No exObject exists
		return false;
	}
}
