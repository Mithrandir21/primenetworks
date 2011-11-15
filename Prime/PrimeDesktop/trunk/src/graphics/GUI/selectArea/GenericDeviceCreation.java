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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logistical.checkLogic;
import managment.ComponentsManagment;
import objects.ExternalHardware;
import objects.hardwareObjects.Discdrive;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import connections.ConnectionUtils;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class GenericDeviceCreation extends JDialog implements ActionListener
{
	private ExternalHardware exObject = null;


	private JTextField nameField = new JTextField("");


	private JCheckBox supportsWireless = new JCheckBox("Wifi");

	private JCheckBox supportsLAN = new JCheckBox("LAN");

	private JCheckBox supportsCOAX = new JCheckBox("COAX");

	private JCheckBox supportsFIBER = new JCheckBox("Fiber");

	private JCheckBox containsHDD = new JCheckBox("HDD (1GB)");

	private JCheckBox containsDiskdrive = new JCheckBox("Diskdriver");


	public GenericDeviceCreation(ExternalHardware obj)
	{
		this.setTitle("Generic Device Options");

		exObject = obj;

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(240, 390);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;

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
		this.setVisible(true);


		// Resets the ruleFrame object when closed.
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{

			}
		});
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


		JLabel deviceName = new JLabel("Name of the Device:");
		d.gridy++; // row
		optionsPanel.add(deviceName, d);

		d.gridy++; // row
		optionsPanel.add(nameField, d);


		// The question asked
		JLabel Question = new JLabel("What does the device support?");
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
				setSelectedOptions();
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
	private void setSelectedOptions()
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
			}
			catch ( MotherboardNotFound e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
