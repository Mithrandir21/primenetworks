/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView.Views;


import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import managment.ArrayManagment;
import managment.ComponentsManagment;
import objects.Hardware;
import objects.Object;
import objects.hardwareObjects.CPU;
import objects.hardwareObjects.Discdrive;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.GraphicsCard;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.Motherboard;
import objects.hardwareObjects.Ram;
import actions.canvasActions.ActionDeleteAllConnections;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class MotherboardView extends JPanel implements HardwareViewInterface,
		ActionListener
{
	JTextField name = new JTextField(25);

	JTextArea desc = new JTextArea(3, 40);

	private JTextField producerField = new JTextField(7);

	private JComboBox forms;

	private JComboBox sockets;

	private JComboBox busSpeeds;

	private JTextField chipsetField = new JTextField(7);

	private JComboBox gpuPorts;

	private JComboBox DUCPorts;

	private JComboBox RAMPorts;

	private JCheckBox intAudioCard;

	private JCheckBox intGPU;

	private JCheckBox intNIC;

	private JCheckBox GPUinstalled;

	private JComboBox CPUsockets;

	private JComboBox PCIslots;

	private JComboBox RAMslots;

	private JComboBox USBports;

	private JComboBox DUCports;

	private JComboBox LANports;


	private Object mainObj;

	private Motherboard mbObj;


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 * @param mb
	 */
	public MotherboardView(Object obj, Motherboard mb)
	{
		mainObj = obj;
		mbObj = mb;
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

		ImageIcon icon = PrimeMain1.objectImageIcons.get(Motherboard.class);
		JPanel p1 = HardwareEditor.GeneralInfo(mb, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(mb);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);



		// THe remove component button
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
	 * @param mb
	 *            The Hardware that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(Motherboard mb)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[18];


		labels[0] = new JLabel(PrimeMain1.texts
				.getString("mbViewProducerLabel"));
		labels[0].setToolTipText(PrimeMain1.texts
				.getString("mbViewProducerTip"));

		labels[1] = new JLabel(PrimeMain1.texts.getString("mbViewFormLabel"));
		labels[1].setToolTipText(PrimeMain1.texts.getString("mbViewFormTip"));

		labels[2] = new JLabel(PrimeMain1.texts.getString("mbViewSocketLabel"));
		labels[2].setToolTipText(PrimeMain1.texts.getString("mbViewSocketTip"));

		labels[3] = new JLabel(PrimeMain1.texts
				.getString("mbViewBusSpeedLabel"));
		labels[3].setToolTipText(PrimeMain1.texts
				.getString("mbViewBusSpeedTip"));

		labels[4] = new JLabel(PrimeMain1.texts.getString("mbViewChipsetLabel"));
		labels[4]
				.setToolTipText(PrimeMain1.texts.getString("mbViewChipsetTip"));

		labels[5] = new JLabel(PrimeMain1.texts.getString("mbViewGPUportLabel"));
		labels[5]
				.setToolTipText(PrimeMain1.texts.getString("mbViewGPUportTip"));

		labels[6] = new JLabel(PrimeMain1.texts
				.getString("mbViewConnectionPortLabel"));
		labels[6].setToolTipText(PrimeMain1.texts
				.getString("mbViewConnectionPortTip"));

		labels[7] = new JLabel(PrimeMain1.texts.getString("mbViewRamTypeLabel"));
		labels[7]
				.setToolTipText(PrimeMain1.texts.getString("mbViewRamTypeTip"));

		labels[8] = new JLabel(PrimeMain1.texts
				.getString("mbViewAudioIntegratedLabel"));
		labels[8].setToolTipText(PrimeMain1.texts
				.getString("mbViewAudioIntegratedTip"));

		labels[9] = new JLabel(PrimeMain1.texts
				.getString("mbViewGPUIntegratedLabel"));
		labels[9].setToolTipText(PrimeMain1.texts
				.getString("mbViewGPUIntegratedTip"));

		labels[10] = new JLabel(PrimeMain1.texts
				.getString("mbViewNICIntegratedLabel"));
		labels[10].setToolTipText(PrimeMain1.texts
				.getString("mbViewNICIntegratedTip"));

		labels[11] = new JLabel(PrimeMain1.texts
				.getString("mbViewGPUinstalledLabel"));
		labels[11].setToolTipText(PrimeMain1.texts
				.getString("mbViewGPUinstalledTip"));

		labels[12] = new JLabel(PrimeMain1.texts
				.getString("mbViewCPUsocketLabel"));
		labels[12].setToolTipText(PrimeMain1.texts
				.getString("mbViewCPUsocketTip"));

		labels[13] = new JLabel(PrimeMain1.texts
				.getString("mbViewPCIslotsLabel"));
		labels[13].setToolTipText(PrimeMain1.texts
				.getString("mbViewPCIslotsTip"));

		labels[14] = new JLabel(PrimeMain1.texts
				.getString("mbViewRAMslotsLabel"));
		labels[14].setToolTipText(PrimeMain1.texts
				.getString("mbViewRAMslotsTip"));

		labels[15] = new JLabel(PrimeMain1.texts
				.getString("mbViewUSBportsLabel"));
		labels[15].setToolTipText(PrimeMain1.texts
				.getString("mbViewUSBportsTip"));

		labels[16] = new JLabel(PrimeMain1.texts
				.getString("mbViewDUCportLabel"));
		labels[16].setToolTipText(PrimeMain1.texts
				.getString("mbViewDUCportTip"));

		labels[17] = new JLabel(PrimeMain1.texts
				.getString("mbViewLANportsLabel"));
		labels[17].setToolTipText(PrimeMain1.texts
				.getString("mbViewLANportsTip"));



		Dimension tfSize = new Dimension(90, 20);



		// PRODUCER
		labels[0].setLabelFor(producerField);
		producerField.setMaximumSize(tfSize);
		producerField.setPreferredSize(tfSize);
		producerField.setText(mb.getProducer());
		producerField.setToolTipText(labels[0].getToolTipText());

		panel.add(labels[0]);
		panel.add(producerField);
		// --------------------------


		// FORM
		String[] formsStrings = { "", "ATX", "mATX", "eATX" };
		forms = new JComboBox(formsStrings);
		forms.setMaximumSize(tfSize);
		forms.setPreferredSize(tfSize);
		forms.setBackground(Color.WHITE);
		forms.setToolTipText(labels[1].getToolTipText());
		forms.setActionCommand("Form");
		forms.addActionListener(this);

		forms.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				formsStrings, mb.getForm()));

		labels[1].setLabelFor(forms);

		panel.add(labels[1]);
		panel.add(forms);
		// --------------------------


		// SOCKET
		String[] socketsStrings = { "", "Intel 775", "Intel 939", "AMD AM2",
				"AMD AM2+" };
		sockets = new JComboBox(socketsStrings);
		sockets.setMaximumSize(tfSize);
		sockets.setPreferredSize(tfSize);
		sockets.setBackground(Color.WHITE);
		sockets.setToolTipText(labels[2].getToolTipText());
		sockets.setActionCommand("Socket");
		sockets.addActionListener(this);

		sockets.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				socketsStrings, mb.getSocket()));

		labels[2].setLabelFor(sockets);

		panel.add(labels[2]);
		panel.add(sockets);
		// --------------------------


		// BUS SPEED
		String[] busspeedStrings = { "", "800", "1066" };
		busSpeeds = new JComboBox(busspeedStrings);
		busSpeeds.setMaximumSize(tfSize);
		busSpeeds.setPreferredSize(tfSize);
		busSpeeds.setBackground(Color.WHITE);
		busSpeeds.setToolTipText(labels[3].getToolTipText());
		busSpeeds.setActionCommand("Bus Speed");
		busSpeeds.addActionListener(this);


		busSpeeds.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				busspeedStrings, mb.getBusSpeed()));

		labels[3].setLabelFor(busSpeeds);

		panel.add(labels[3]);
		panel.add(busSpeeds);
		// --------------------------


		// CHIPSET
		labels[4].setLabelFor(chipsetField);
		chipsetField.setMaximumSize(tfSize);
		chipsetField.setPreferredSize(tfSize);
		chipsetField.setText(mb.getChipset());
		chipsetField.setToolTipText(labels[4].getToolTipText());


		panel.add(labels[4]);
		panel.add(chipsetField);
		// --------------------------


		// GPU PORT
		String[] gpuPortStrings = { "", "AGP", "PCI", "PCI-E" };
		gpuPorts = new JComboBox(gpuPortStrings);
		gpuPorts.setMaximumSize(tfSize);
		gpuPorts.setMinimumSize(tfSize);
		gpuPorts.setPreferredSize(tfSize);
		gpuPorts.setBackground(Color.WHITE);
		gpuPorts.setToolTipText(labels[5].getToolTipText());
		gpuPorts.setActionCommand("GPU Port");
		gpuPorts.addActionListener(this);


		int gpuPortIndex = 0;

		for ( int i = 1; i < gpuPortStrings.length; i++ )
		{
			if ( mb.getGraphicalPort() != null && mb.getGraphicalPort() != "" )
			{
				if ( gpuPorts.getItemAt(i).equals(mb.getGraphicalPort()) )
				{
					gpuPortIndex = i;
					i = gpuPortStrings.length;
				}
			}
		}

		gpuPorts.setSelectedIndex(gpuPortIndex);

		labels[5].setLabelFor(gpuPorts);

		panel.add(labels[5]);
		panel.add(gpuPorts);
		// --------------------------



		// DUC, connection port
		String[] DUCStrings = { "", "IDE", "SATA", "eSATA" };
		DUCPorts = new JComboBox(DUCStrings);
		DUCPorts.setMaximumSize(tfSize);
		DUCPorts.setMinimumSize(tfSize);
		DUCPorts.setPreferredSize(tfSize);
		DUCPorts.setBackground(Color.WHITE);
		DUCPorts.setToolTipText(labels[6].getToolTipText());
		DUCPorts.setActionCommand("DUC Port");
		DUCPorts.addActionListener(this);


		int DUCPortIndex = 0;

		for ( int i = 0; i < DUCStrings.length; i++ )
		{
			if ( mb.getDUCconnectionType() != null
					&& mb.getDUCconnectionType() != "" )
			{
				if ( DUCPorts.getItemAt(i).equals(mb.getDUCconnectionType()) )
				{
					DUCPortIndex = i;
					i = DUCStrings.length;
				}
			}
		}

		DUCPorts.setSelectedIndex(DUCPortIndex);

		labels[6].setLabelFor(DUCPorts);

		panel.add(labels[6]);
		panel.add(DUCPorts);
		// --------------------------



		// RAM
		String[] RAMStrings = { "", "DDR", "DDR2", "DDR3" };
		RAMPorts = new JComboBox(RAMStrings);
		RAMPorts.setMaximumSize(tfSize);
		RAMPorts.setMinimumSize(tfSize);
		RAMPorts.setPreferredSize(tfSize);
		RAMPorts.setBackground(Color.WHITE);
		RAMPorts.setToolTipText(labels[7].getToolTipText());
		RAMPorts.setActionCommand("RAM Port");
		RAMPorts.addActionListener(this);


		int RAMPortIndex = 0;

		for ( int i = 0; i < RAMStrings.length; i++ )
		{
			if ( mb.getRAMtype() != null && mb.getRAMtype() != "" )
			{
				if ( RAMPorts.getItemAt(i).equals(mb.getRAMtype()) )
				{
					RAMPortIndex = i;
					i = RAMStrings.length;
				}
			}
		}

		RAMPorts.setSelectedIndex(RAMPortIndex);

		labels[7].setLabelFor(RAMPorts);

		panel.add(labels[7]);
		panel.add(RAMPorts);
		// --------------------------



		// Integrated Audio card
		intAudioCard = new JCheckBox();
		intAudioCard.setSelected(mb.isIntegAudioCard());
		intAudioCard.setToolTipText(labels[8].getToolTipText());
		intAudioCard.setActionCommand("Int Audio");
		intAudioCard.addActionListener(this);

		labels[8].setLabelFor(intAudioCard);

		panel.add(labels[8]);
		panel.add(intAudioCard);
		// --------------------------



		// Integrated GPU card
		intGPU = new JCheckBox();
		intGPU.setSelected(mb.isIntegGraphicalCard());
		intGPU.setToolTipText(labels[9].getToolTipText());
		intGPU.setActionCommand("Int GPU");
		intGPU.addActionListener(this);

		labels[9].setLabelFor(intGPU);

		panel.add(labels[9]);
		panel.add(intGPU);
		// --------------------------



		// Integrated NIC
		intNIC = new JCheckBox();
		intNIC.setSelected(mb.isIntegLANcard());
		intNIC.setToolTipText(labels[10].getToolTipText());
		intNIC.setActionCommand("Int NIC");
		intNIC.addActionListener(this);

		labels[10].setLabelFor(intNIC);

		panel.add(labels[10]);
		panel.add(intNIC);
		// --------------------------



		// GPU installed, regardless of whether or not any integrate GPU exists.
		GPUinstalled = new JCheckBox();
		GPUinstalled.setSelected(mb.isGraphicsCardInstalled());
		GPUinstalled.setToolTipText(labels[11].getToolTipText());
		GPUinstalled.setActionCommand("GPU Installed");
		GPUinstalled.addActionListener(this);

		labels[11].setLabelFor(GPUinstalled);

		panel.add(labels[11]);
		panel.add(GPUinstalled);
		// --------------------------




		// CPU sockets
		String[] CPUsocketsStrings = { "0", "1", "2", "3", "4", "5", "6" };
		CPUsockets = new JComboBox(CPUsocketsStrings);
		CPUsockets.setMaximumSize(tfSize);
		CPUsockets.setMinimumSize(tfSize);
		CPUsockets.setPreferredSize(tfSize);
		CPUsockets.setBackground(Color.WHITE);
		CPUsockets.setToolTipText(labels[12].getToolTipText());
		CPUsockets.setActionCommand("CPU sockets");
		CPUsockets.addActionListener(this);

		int CPUsocketsIndex = 0;

		for ( int i = 1; i < CPUsocketsStrings.length; i++ )
		{
			if ( Integer.parseInt(CPUsockets.getItemAt(i).toString()) == (mb
					.getMaxCPUs()) )
			{
				CPUsocketsIndex = i;
				i = CPUsocketsStrings.length;
			}
		}

		CPUsockets.setSelectedIndex(CPUsocketsIndex);

		labels[12].setLabelFor(CPUsockets);

		panel.add(labels[12]);
		panel.add(CPUsockets);
		// --------------------------




		// PCI slots
		String[] PCIslotsStrings = { "0", "1", "2", "3", "4", "5", "6" };
		PCIslots = new JComboBox(PCIslotsStrings);
		PCIslots.setMaximumSize(tfSize);
		PCIslots.setMinimumSize(tfSize);
		PCIslots.setPreferredSize(tfSize);
		PCIslots.setBackground(Color.WHITE);
		PCIslots.setToolTipText(labels[13].getToolTipText());
		PCIslots.setActionCommand("PCI Slots");
		PCIslots.addActionListener(this);


		int PCIslotsIndex = 0;

		for ( int i = 1; i < PCIslotsStrings.length; i++ )
		{
			if ( Integer.parseInt(PCIslots.getItemAt(i).toString()) == (mb
					.getMaxPCIs()) )
			{
				PCIslotsIndex = i;
				i = PCIslotsStrings.length;
			}
		}

		PCIslots.setSelectedIndex(PCIslotsIndex);

		labels[13].setLabelFor(PCIslots);

		panel.add(labels[13]);
		panel.add(PCIslots);
		// --------------------------




		// RAM slots
		String[] RAMslotsStrings = { "0", "1", "2", "3", "4", "5", "6" };
		RAMslots = new JComboBox(RAMslotsStrings);
		RAMslots.setMaximumSize(tfSize);
		RAMslots.setMinimumSize(tfSize);
		RAMslots.setPreferredSize(tfSize);
		RAMslots.setBackground(Color.WHITE);
		RAMslots.setToolTipText(labels[14].getToolTipText());
		RAMslots.setActionCommand("RAM Slots");
		RAMslots.addActionListener(this);


		int RAMslotsIndex = 0;

		for ( int i = 1; i < RAMslotsStrings.length; i++ )
		{
			if ( Integer.parseInt(RAMslots.getItemAt(i).toString()) == (mb
					.getMaxRAMs()) )
			{
				RAMslotsIndex = i;
				i = RAMslotsStrings.length;
			}
		}

		RAMslots.setSelectedIndex(RAMslotsIndex);

		labels[14].setLabelFor(RAMslots);

		panel.add(labels[14]);
		panel.add(RAMslots);
		// --------------------------




		// USB slots
		String[] USBportsStrings = { "0", "1", "2", "3", "4", "5", "6" };
		USBports = new JComboBox(USBportsStrings);
		USBports.setMaximumSize(tfSize);
		USBports.setMinimumSize(tfSize);
		USBports.setPreferredSize(tfSize);
		USBports.setBackground(Color.WHITE);
		USBports.setToolTipText(labels[15].getToolTipText());
		USBports.setActionCommand("USB Ports");
		USBports.addActionListener(this);


		int USBportsIndex = 0;

		for ( int i = 1; i < USBportsStrings.length; i++ )
		{
			if ( Integer.parseInt(USBports.getItemAt(i).toString()) == (mb
					.getMaxUSBs()) )
			{
				USBportsIndex = i;
				i = USBportsStrings.length;
			}
		}

		USBports.setSelectedIndex(USBportsIndex);

		labels[15].setLabelFor(USBports);

		panel.add(labels[15]);
		panel.add(USBports);
		// --------------------------




		// DUC ports
		String[] DUCportsStrings = { "0", "1", "2", "3", "4", "5", "6" };
		DUCports = new JComboBox(DUCportsStrings);
		DUCports.setMaximumSize(tfSize);
		DUCports.setMinimumSize(tfSize);
		DUCports.setPreferredSize(tfSize);
		DUCports.setBackground(Color.WHITE);
		DUCports.setToolTipText(labels[16].getToolTipText());
		DUCports.setActionCommand("DUC Ports");
		DUCports.addActionListener(this);


		int DUCportsIndex = 0;

		for ( int i = 1; i < DUCportsStrings.length; i++ )
		{
			if ( Integer.parseInt(DUCports.getItemAt(i).toString()) == (mb
					.getMaxDUCs()) )
			{
				DUCportsIndex = i;
				i = DUCportsStrings.length;
			}
		}

		DUCports.setSelectedIndex(DUCportsIndex);

		labels[16].setLabelFor(DUCports);

		panel.add(labels[16]);
		panel.add(DUCports);
		// --------------------------




		// LAN ports
		String[] LANportsStrings = { "0", "1", "2", "3", "4", "5", "6" };
		LANports = new JComboBox(LANportsStrings);
		LANports.setMaximumSize(tfSize);
		LANports.setMinimumSize(tfSize);
		LANports.setPreferredSize(tfSize);
		LANports.setBackground(Color.WHITE);
		LANports.setToolTipText(labels[17].getToolTipText());
		LANports.setActionCommand("LAN Ports");
		LANports.addActionListener(this);


		int LANportsIndex = 0;

		for ( int i = 1; i < LANportsStrings.length; i++ )
		{
			if ( Integer.parseInt(LANports.getItemAt(i).toString()) == (mb
					.getMaxIntegLANs()) )
			{
				LANportsIndex = i;
				i = LANportsStrings.length;
			}
		}

		LANports.setSelectedIndex(LANportsIndex);

		labels[17].setLabelFor(LANports);

		panel.add(labels[17]);
		panel.add(LANports);
		// --------------------------



		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel,
				panel.getComponentCount(), // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad


		return panel;
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * graphics.GUI.objectView.Hardware.HardwareView.HardwareView#validateData()
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


		// Checks the form of the motherboard.
		if ( forms.getSelectedItem().toString().equals("") )
		{
			JOptionPane.showMessageDialog(this,
					"The motherboard must have a form.", "Error - Form",
					JOptionPane.INFORMATION_MESSAGE);

			return false;
		}


		// Checks the socket of the motherboard.
		if ( sockets.getSelectedItem().toString().equals("") )
		{
			JOptionPane.showMessageDialog(this,
					"The motherboard must have a socket so to place a CPU.",
					"Error - Socket", JOptionPane.INFORMATION_MESSAGE);

			return false;
		}


		return true;
	}

	@Override
	public Hardware getViewHardware()
	{
		return mbObj;
	}

	/*
	 * (non-Javadoc)
	 * @see graphics.GUI.objectView.Hardware.HardwareView.HardwareView#save()
	 */
	public void save()
	{
		if ( name.getText() != "" )
		{
			mbObj.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mbObj.setDescription(desc.getText());
		}

		mbObj.setProducer(producerField.getText());

		mbObj.setForm(forms.getSelectedItem().toString());

		mbObj.setSocket(sockets.getSelectedItem().toString());


		if ( busSpeeds.getSelectedItem().toString() != "" )
		{
			mbObj.setBusSpeed(Integer.parseInt(busSpeeds.getSelectedItem()
					.toString()));
		}
		else
		{
			mbObj.setBusSpeed(0);
		}


		mbObj.setChipset(chipsetField.getText());

		mbObj.setGraphicalPort(gpuPorts.getSelectedItem().toString());

		mbObj.setDUCconnectionType(DUCPorts.getSelectedItem().toString());

		mbObj.setRAMtype(RAMPorts.getSelectedItem().toString());


		mbObj.setIntegAudioCard(intAudioCard.isSelected());
		mbObj.setIntegGraphicalCard(intGPU.isSelected());
		mbObj.setIntegLANcard(intNIC.isSelected());
		mbObj.setGraphicsCard(GPUinstalled.isSelected());


		if ( CPUsockets.getSelectedItem().toString() != "" )
		{
			if ( mbObj.getMaxCPUs() != Integer.parseInt(CPUsockets
					.getSelectedItem().toString()) )
			{
				Object[] comp = null;
				try
				{
					// Gets all the CPUs from the objects components array.
					comp = ArrayManagment.getSpesificComponents(CPU.class,
							mainObj.getComponents(),
							mainObj.getComponents().length);

					// Removes all the CPUs from the objects components array.
					mainObj.setAllComponents(ComponentsManagment
							.removeComponents(comp, mainObj.getComponents(),
									mainObj.getComponents().length));

				}
				catch ( ObjectNotFoundException e )
				{

				}
				catch ( ObjectNotFoundInArrayException e )
				{

				}


				mbObj.setMaxCPUs(Integer.parseInt(CPUsockets.getSelectedItem()
						.toString()));
				mbObj.setCPUPortsAvailable(mbObj.getMaxCPUs());

				if ( comp != null )
				{
					// The number of components there are room for.
					int counter = mbObj.getMaxCPUs();

					// All the components of the main object(without the CPUs).
					Object[] mainComp = mainObj.getComponents();

					for ( int i = 0; i < counter; i++ )
					{
						// If i is smaller then the length of the comp array.
						if ( i < comp.length )
						{
							mainComp = ComponentsManagment.addComponent(
									comp[i], mainComp);
							mbObj.makeOneCPUportTaken();
						}
					}
					mainObj.setAllComponents(mainComp);
				}
			}
		}

		if ( PCIslots.getSelectedItem().toString() != "" )
		{
			if ( mbObj.getMaxPCIs() != Integer.parseInt(PCIslots
					.getSelectedItem().toString()) )
			{
				Object[] comp = null;
				try
				{
					// Gets all the CPUs from the objects components array.
					comp = ArrayManagment.getSpesificComponents(
							ExternalNetworksCard.class,
							mainObj.getComponents(),
							mainObj.getComponents().length);

					// Removes all the CPUs from the objects components array.
					mainObj.setAllComponents(ComponentsManagment
							.removeComponents(comp, mainObj.getComponents(),
									mainObj.getComponents().length));

				}
				catch ( ObjectNotFoundException e )
				{

				}
				catch ( ObjectNotFoundInArrayException e )
				{

				}


				mbObj.setMaxPCIs(Integer.parseInt(PCIslots.getSelectedItem()
						.toString()));
				mbObj.setPCIPortsAvailable(mbObj.getMaxPCIs());

				// If there are any components found
				if ( comp != null )
				{
					// The number of components there are room for.
					int counter = mbObj.getMaxPCIs();

					// All the components of the main object(without the CPUs).
					Object[] mainComp = mainObj.getComponents();

					for ( int i = 0; i < counter; i++ )
					{
						// If i is smaller then the length of the comp array.
						if ( i < comp.length )
						{
							mainComp = ComponentsManagment.addComponent(
									comp[i], mainComp);
							mbObj.makeOnePCIportTaken();
						}
					}
					mainObj.setAllComponents(mainComp);
				}
			}
		}

		if ( RAMslots.getSelectedItem().toString() != "" )
		{
			if ( mbObj.getMaxRAMs() != Integer.parseInt(RAMslots
					.getSelectedItem().toString()) )
			{
				Object[] comp = null;
				try
				{
					// Gets all the CPUs from the objects components array.
					comp = ArrayManagment.getSpesificComponents(Ram.class,
							mainObj.getComponents(),
							mainObj.getComponents().length);

					// Removes all the CPUs from the objects components array.
					mainObj.setAllComponents(ComponentsManagment
							.removeComponents(comp, mainObj.getComponents(),
									mainObj.getComponents().length));

				}
				catch ( ObjectNotFoundException e )
				{

				}
				catch ( ObjectNotFoundInArrayException e )
				{

				}


				mbObj.setMaxRAMs(Integer.parseInt(RAMslots.getSelectedItem()
						.toString()));
				mbObj.setRAMPortsAvailable(mbObj.getMaxRAMs());

				if ( comp != null )
				{
					// The number of components there are room for.
					int counter = mbObj.getMaxRAMs();
					// All the components of the main object(without the CPUs).
					Object[] mainComp = mainObj.getComponents();
					for ( int i = 0; i < counter; i++ )
					{
						// If i is smaller then the length of the comp array.
						if ( i < comp.length )
						{
							mainComp = ComponentsManagment.addComponent(
									comp[i], mainComp);
							mbObj.makeOneRAMportTaken();
						}
					}
					mainObj.setAllComponents(mainComp);
				}
			}
		}

		if ( USBports.getSelectedItem().toString() != "" )
		{
			mbObj.setMaxUSBs(Integer.parseInt(USBports.getSelectedItem()
					.toString()));
			// FIXME - MotherboardView MaxUSB
		}

		if ( DUCports.getSelectedItem().toString() != "" )
		{
			if ( mbObj.getMaxDUCs() != Integer.parseInt(DUCports
					.getSelectedItem().toString()) )
			{
				Object[] comp = null;

				try
				{
					// Gets all the CPUs from the objects components array.
					Object[] compHDD = ArrayManagment.getSpesificComponents(
							HDD.class, mainObj.getComponents(), mainObj
									.getComponents().length);

					Object[] compDisc = ArrayManagment.getSpesificComponents(
							Discdrive.class, mainObj.getComponents(), mainObj
									.getComponents().length);

					comp = new Object[compDisc.length + compHDD.length];

					// The different counters for the different arrays of
					// components.
					int hddCount = 0;
					int discCount = 0;

					// The tick/tack boolean.
					boolean tick = true;


					for ( int i = 0; i < comp.length; i++ )
					{
						// Tries to add the hdd first.
						if ( tick )
						{
							if ( hddCount < compHDD.length
									&& compHDD[hddCount] != null )
							{
								comp[i] = compHDD[hddCount];
								hddCount++;
								tick = false;
							}
							else
							{
								comp[i] = compDisc[discCount];
								discCount++;
								tick = false;
							}
						}
						// Tack
						else
						{
							if ( discCount < compDisc.length
									&& compDisc[discCount] != null )
							{
								comp[i] = compDisc[discCount];
								discCount++;
								tick = true;
							}
							else
							{
								comp[i] = compHDD[hddCount];
								hddCount++;
								tick = true;
							}
						}
					}

					// Removes all the CPUs from the objects components array.
					mainObj.setAllComponents(ComponentsManagment
							.removeComponents(comp, mainObj.getComponents(),
									mainObj.getComponents().length));

				}
				catch ( ObjectNotFoundException e )
				{

				}
				catch ( ObjectNotFoundInArrayException e )
				{

				}


				mbObj.setMaxDUCs(Integer.parseInt(DUCports.getSelectedItem()
						.toString()));
				mbObj.setDUCPortsAvailable(mbObj.getMaxDUCs());

				if ( comp != null )
				{
					// The number of components there are room for.
					int counter = mbObj.getMaxDUCs();

					// All the components of the main object(without the CPUs).
					Object[] mainComp = mainObj.getComponents();

					for ( int i = 0; i < counter; i++ )
					{
						// If i is smaller then the length of the comp array.
						if ( i < comp.length )
						{
							mainComp = ComponentsManagment.addComponent(
									comp[i], mainComp);
							mbObj.makeOneDUCportTaken();
						}
					}
					mainObj.setAllComponents(mainComp);
				}
			}
		}

		if ( LANports.getSelectedItem().toString() != "" )
		{

			// The currently available ports, before Max change
			int avail = mbObj.getIntegLANPortsAvailable();

			int newAvail = avail
					+ (Integer.parseInt(LANports.getSelectedItem().toString()) - mbObj
							.getMaxIntegLANs());


			mbObj.setMaxIntegratedLANs(Integer.parseInt(LANports
					.getSelectedItem().toString()));

			mbObj.setIntegLANPortsAvailable(newAvail);
		}
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
			if ( command.equals("Form") )
			{

			}
			/**
			 * 
			 */
			else if ( command.equals("Socket") )
			{
				String[] socketsStrings = { "", "Intel 775", "Intel 939",
						"AMD AM2", "AMD AM2+" };

				sockets = GraphicalFunctions
						.verifyChange(
								this,
								mainObj,
								CPU.class,
								mbObj.getSocket(),
								sockets.getSelectedItem().toString(),
								PrimeMain1.texts
										.getString("mbViewCPUnotCompatiableQuestionMsg"),
								socketsStrings, sockets);

			}
			else if ( command.equals("Bus Speed") )
			{

			}
			/**
			 * 
			 */
			else if ( command.equals("GPU Port") )
			{
				String[] gpuPortStrings = { "", "AGP", "PCI", "PCI-E" };

				gpuPorts = GraphicalFunctions
						.verifyChange(
								this,
								mainObj,
								GraphicsCard.class,
								mbObj.getGraphicalPort(),
								gpuPorts.getSelectedItem().toString(),
								PrimeMain1.texts
										.getString("mbViewGPUnotCompatiableQuestionMsg"),
								gpuPortStrings, gpuPorts);

			}
			else if ( command.equals("DUC Port") )
			{
				String[] DUCStrings = { "", "IDE", "SATA", "eSATA" };

				DUCPorts = GraphicalFunctions
						.verifyChange(
								this,
								mainObj,
								HDD.class,
								mbObj.getDUCconnectionType(),
								DUCPorts.getSelectedItem().toString(),
								PrimeMain1.texts
										.getString("mbViewDUCnotCompatiableQuestionMsg"),
								DUCStrings, DUCPorts);

			}
			else if ( command.equals("RAM Port") )
			{

			}
			else if ( command.equals("CPU sockets") )
			{

			}
			else if ( command.equals("PCI Slots") )
			{

			}
			else if ( command.equals("RAM Slots") )
			{

			}
			else if ( command.equals("USB Ports") )
			{

			}
			else if ( command.equals("DUC Ports") )
			{

			}
			else if ( command.equals("LAN Ports") )
			{

			}

		}
		else if ( e.getSource() instanceof Button )
		{
			Button check = (Button) e.getSource();

			String command = check.getActionCommand();

			if ( command.equals("removeComp") )
			{
				int n = JOptionPane.showConfirmDialog(this, PrimeMain1.texts
						.getString("mbViewReplaceMBquestionMsg"),
						PrimeMain1.texts.getString("verify"),
						JOptionPane.YES_NO_OPTION);


				// If the answer is "No"
				if ( n == 0 )
				{
					// We have to remove all connection between this object and
					// other objects on the canvas
					// WorkareaCanvasActions.removeAllConnectionsToFromObject(
					// PrimeMain1.currentCanvas, mainObj);
					ActionDeleteAllConnections action = new ActionDeleteAllConnections(
							PrimeMain1.texts
									.getString("actionDeleteAllConnectionName"));
					action.performAction(false);


					// Since the motherboard is where most of the connections
					// are
					// placed we first have to remove all connections to the
					// devices.
					mainObj.removeAllConnections();


					// Then we have to remove all the components that a object
					// contains.
					mainObj.removeAllComponents();


					// Resets all the ports so that all ports are available.
					mbObj.resetAllComponents();

					// Now that the object has no connections to other
					// components
					// and device
					// we can add the motherboard object.
					mainObj.addComponent(mbObj);


					// Updates the views of the object to correctly show the
					// current info.
					ObjectView view = PrimeMain1.getObjectView(mainObj);
					if ( view != null )
					{
						view.updateViewInfo();
					}
					// If no view is returned, then the standard object view is
					// open
					// and that should be updated.
					else if ( PrimeMain1.stdObjView != null )
					{
						PrimeMain1.stdObjView.getSplitView()
								.getHardStdObjView().updateTabInfo();
					}
				}
			}
		}
		else
		{
			assert (e.getSource() instanceof JCheckBox);

			JCheckBox check = (JCheckBox) e.getSource();

			String command = check.getActionCommand();
			if ( command.equals("Int Audio") )
			{

			}
			else if ( command.equals("Int GPU") )
			{

			}
			else if ( command.equals("Int NIC") )
			{

			}
			else if ( command.equals("GPU Installed") )
			{

			}
		}
	}
}
