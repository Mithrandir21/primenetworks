/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView.Views;


import exceptions.ConnectionDoesNotExist;
import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;
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

import logistical.cleanup;
import managment.ArrayManagment;
import managment.CanvasManagment;
import managment.ComponentsManagment;
import managment.ConnectionManagment;
import objects.Hardware;
import objects.Object;
import objects.hardwareObjects.CPU;
import objects.hardwareObjects.Discdrive;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.GraphicsCard;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import objects.hardwareObjects.Ram;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WorkareaCanvas;
import actions.canvasActions.ActionDeleteAllConnectionsToAndFrom;
import connections.Connection;
import connections.ConnectionUtils;
import connections.WidgetExtendedConnection;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class MotherboardView extends JPanel implements HardwareViewInterface, ActionListener
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

		ImageIcon icon = PrimeMain.objectImageIcons.get(Motherboard.class);
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

		JLabel label = new JLabel(PrimeMain.texts
				.getString("hwTabRemoveThisComponentLabel"));

		Button remove = new Button(PrimeMain.texts
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
	 * @param mb
	 *            The Hardware that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(Motherboard mb)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[18];


		labels[0] = new JLabel(PrimeMain.texts
				.getString("mbViewProducerLabel"));
		labels[0].setToolTipText(PrimeMain.texts
				.getString("mbViewProducerTip"));

		labels[1] = new JLabel(PrimeMain.texts.getString("mbViewFormLabel"));
		labels[1].setToolTipText(PrimeMain.texts.getString("mbViewFormTip"));

		labels[2] = new JLabel(PrimeMain.texts.getString("mbViewSocketLabel"));
		labels[2].setToolTipText(PrimeMain.texts.getString("mbViewSocketTip"));

		labels[3] = new JLabel(PrimeMain.texts
				.getString("mbViewBusSpeedLabel"));
		labels[3].setToolTipText(PrimeMain.texts
				.getString("mbViewBusSpeedTip"));

		labels[4] = new JLabel(PrimeMain.texts.getString("mbViewChipsetLabel"));
		labels[4]
				.setToolTipText(PrimeMain.texts.getString("mbViewChipsetTip"));

		labels[5] = new JLabel(PrimeMain.texts.getString("mbViewGPUportLabel"));
		labels[5]
				.setToolTipText(PrimeMain.texts.getString("mbViewGPUportTip"));

		labels[6] = new JLabel(PrimeMain.texts
				.getString("mbViewConnectionPortLabel"));
		labels[6].setToolTipText(PrimeMain.texts
				.getString("mbViewConnectionPortTip"));

		labels[7] = new JLabel(PrimeMain.texts.getString("mbViewRamTypeLabel"));
		labels[7]
				.setToolTipText(PrimeMain.texts.getString("mbViewRamTypeTip"));

		labels[8] = new JLabel(PrimeMain.texts
				.getString("mbViewAudioIntegratedLabel"));
		labels[8].setToolTipText(PrimeMain.texts
				.getString("mbViewAudioIntegratedTip"));

		labels[9] = new JLabel(PrimeMain.texts
				.getString("mbViewGPUIntegratedLabel"));
		labels[9].setToolTipText(PrimeMain.texts
				.getString("mbViewGPUIntegratedTip"));

		labels[10] = new JLabel(PrimeMain.texts
				.getString("mbViewNICIntegratedLabel"));
		labels[10].setToolTipText(PrimeMain.texts
				.getString("mbViewNICIntegratedTip"));

		labels[11] = new JLabel(PrimeMain.texts
				.getString("mbViewGPUinstalledLabel"));
		labels[11].setToolTipText(PrimeMain.texts
				.getString("mbViewGPUinstalledTip"));

		labels[12] = new JLabel(PrimeMain.texts
				.getString("mbViewCPUsocketLabel"));
		labels[12].setToolTipText(PrimeMain.texts
				.getString("mbViewCPUsocketTip"));

		labels[13] = new JLabel(PrimeMain.texts
				.getString("mbViewPCIslotsLabel"));
		labels[13].setToolTipText(PrimeMain.texts
				.getString("mbViewPCIslotsTip"));

		labels[14] = new JLabel(PrimeMain.texts
				.getString("mbViewRAMslotsLabel"));
		labels[14].setToolTipText(PrimeMain.texts
				.getString("mbViewRAMslotsTip"));

		labels[15] = new JLabel(PrimeMain.texts
				.getString("mbViewUSBportsLabel"));
		labels[15].setToolTipText(PrimeMain.texts
				.getString("mbViewUSBportsTip"));

		labels[16] = new JLabel(PrimeMain.texts
				.getString("mbViewDUCportLabel"));
		labels[16].setToolTipText(PrimeMain.texts
				.getString("mbViewDUCportTip"));

		labels[17] = new JLabel(PrimeMain.texts
				.getString("mbViewLANportsLabel"));
		labels[17].setToolTipText(PrimeMain.texts
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

		mbObj.setGraphicalPortType(gpuPorts.getSelectedItem().toString());

		mbObj.setDUCconnectionType(DUCPorts.getSelectedItem().toString());

		mbObj.setRAMtype(RAMPorts.getSelectedItem().toString());


		mbObj.setIntegAudioCard(intAudioCard.isSelected());
		mbObj.setIntegGraphicalCard(intGPU.isSelected());
		mbObj.setIntegLANcard(intNIC.isSelected());
		mbObj.setGraphicsCardInstalled(GPUinstalled.isSelected());


		if ( CPUsockets.getSelectedItem().toString() != "" )
		{
			CPUsetup();
		}

		if ( PCIslots.getSelectedItem().toString() != "" )
		{
			PCIsetup();
		}

		if ( RAMslots.getSelectedItem().toString() != "" )
		{
			RAMsetup();
		}

		if ( USBports.getSelectedItem().toString() != "" )
		{
			USBsetup();
		}

		if ( DUCports.getSelectedItem().toString() != "" )
		{
			DUCsetup();
		}

		if ( LANports.getSelectedItem().toString() != "" )
		{
			LANsetup();
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
								PrimeMain.texts
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
								PrimeMain.texts
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
								PrimeMain.texts
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
				int n = JOptionPane.showConfirmDialog(this, PrimeMain.texts
						.getString("mbViewReplaceMBquestionMsg"),
						PrimeMain.texts.getString("verify"),
						JOptionPane.YES_NO_OPTION);


				// If the answer is "No"
				if ( n == 0 )
				{
					// We have to remove all connection between this object and other objects on the canvas
					// WorkareaCanvasActions.removeAllConnectionsToFromObject( PrimeMain1.currentCanvas, mainObj);
					ActionDeleteAllConnectionsToAndFrom action = new ActionDeleteAllConnectionsToAndFrom(
							PrimeMain.texts
									.getString("actionDeleteAllConnectionName"));
					action.performAction(false);


					// Since the motherboard is where most of the connections are placed we first have to remove all connections
					// to the devices.
					mainObj.removeAllConnections();


					// Then we have to remove all the components that a object contains.
					mainObj.removeAllComponents();


					// Resets all the ports so that all ports are available.
					mbObj.resetAllComponents();

					// Now that the object has no connections to other components and device we can add the motherboard object.
					mainObj.addComponent(mbObj);


					// Updates the views of the object to correctly show the current info.
					ObjectView view = PrimeMain.getObjectView(mainObj);
					if ( view != null )
					{
						view.updateViewInfo();
					}
					// If no view is returned, then the standard object view is open and that should be updated.
					else if ( PrimeMain.stdObjView != null )
					{
						PrimeMain.stdObjView.getSplitView()
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



	/**
	 * Processes the CPU settings with the CPU objects.
	 */
	private void CPUsetup()
	{
		if ( mbObj.getMaxCPUs() > Integer.parseInt(CPUsockets.getSelectedItem()
				.toString()) )
		{
			Object[] comp = null;
			try
			{
				// Gets all the CPUs from the objects components array.
				comp = ArrayManagment.getSpesificComponents(CPU.class, mainObj
						.getComponents(), mainObj.getComponents().length);

				// Removes all the CPUs from the objects components array.
				mainObj.setAllComponents(ComponentsManagment.removeComponents(
						comp, mainObj.getComponents(),
						mainObj.getComponents().length));

			}
			catch ( ObjectNotFoundException e )
			{

			}
			catch ( ObjectNotFoundInArrayException e )
			{
				e.printStackTrace();
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
						mainComp = ComponentsManagment.addComponent(comp[i],
								mainComp);
						mbObj.makeOneCPUportTaken();
					}
				}
				mainObj.setAllComponents(mainComp);
			}
		}
		else
		{
			// The number of taken CPU ports(The number of ports - the number of ports available)
			int takenPorts = mbObj.getMaxCPUs() - mbObj.getCPUPortsAvailable();

			// Sets the max CPU ports
			mbObj.setMaxCPUs(Integer.parseInt(CPUsockets.getSelectedItem()
					.toString()));
			mbObj.setCPUPortsAvailable(mbObj.getMaxCPUs() - takenPorts);
		}
	}


	/**
	 * Processes the PCI settings with the InternalNetworksCards objects.
	 */
	private void PCIsetup()
	{
		if ( mbObj.getMaxPCIs() > Integer.parseInt(PCIslots.getSelectedItem()
				.toString()) )
		{
			Object[] comp = null;
			try
			{
				// Gets all the InternalNetworksCards from the objects components array.
				comp = ArrayManagment.getSpesificComponents(
						InternalNetworksCard.class, mainObj.getComponents(),
						mainObj.getComponents().length);

				// Removes all the InternalNetworksCards from the objects components array.
				mainObj.setAllComponents(ComponentsManagment.removeComponents(
						comp, mainObj.getComponents(),
						mainObj.getComponents().length));

			}
			catch ( ObjectNotFoundException e )
			{

			}
			catch ( ObjectNotFoundInArrayException e )
			{
				e.printStackTrace();
			}


			mbObj.setMaxPCIs(Integer.parseInt(PCIslots.getSelectedItem()
					.toString()));
			mbObj.setPCIPortsAvailable(mbObj.getMaxPCIs());

			// If there are any components found
			if ( comp != null )
			{
				// The number of components there are room for.
				int counter = mbObj.getMaxPCIs();

				// All the components of the main object(without the ExternalNetworksCards).
				Object[] mainComp = mainObj.getComponents();

				for ( int i = 0; i < counter; i++ )
				{
					// If i is smaller then the length of the comp array.
					if ( i < comp.length )
					{
						mainComp = ComponentsManagment.addComponent(comp[i],
								mainComp);
						mbObj.makeOnePCIportTaken();
					}
				}
				mainObj.setAllComponents(mainComp);
			}
		}
		else
		{
			// The number of taken PCI ports(The number of ports - the number of ports available)
			int takenPorts = mbObj.getMaxPCIs() - mbObj.getPCIPortsAvailable();

			// Sets the max PCI ports
			mbObj.setMaxPCIs(Integer.parseInt(PCIslots.getSelectedItem()
					.toString()));
			mbObj.setPCIPortsAvailable(mbObj.getMaxPCIs() - takenPorts);
		}
	}



	/**
	 * Processes the RAM settings with the RAM objects.
	 */
	private void RAMsetup()
	{

		if ( mbObj.getMaxRAMs() > Integer.parseInt(RAMslots.getSelectedItem()
				.toString()) )
		{
			Object[] comp = null;
			try
			{
				// Gets all the RAM from the objects components array.
				comp = ArrayManagment.getSpesificComponents(Ram.class, mainObj
						.getComponents(), mainObj.getComponents().length);

				// Removes all the RAM from the objects components array.
				mainObj.setAllComponents(ComponentsManagment.removeComponents(
						comp, mainObj.getComponents(),
						mainObj.getComponents().length));

			}
			catch ( ObjectNotFoundException e )
			{

			}
			catch ( ObjectNotFoundInArrayException e )
			{
				e.printStackTrace();
			}


			mbObj.setMaxRAMs(Integer.parseInt(RAMslots.getSelectedItem()
					.toString()));
			mbObj.setRAMPortsAvailable(mbObj.getMaxRAMs());

			if ( comp != null )
			{
				// The number of components there are room for.
				int counter = mbObj.getMaxRAMs();
				// All the components of the main object(without the RAM).
				Object[] mainComp = mainObj.getComponents();
				for ( int i = 0; i < counter; i++ )
				{
					// If i is smaller then the length of the comp array.
					if ( i < comp.length )
					{
						mainComp = ComponentsManagment.addComponent(comp[i],
								mainComp);
						mbObj.makeOneRAMportTaken();
					}
				}
				mainObj.setAllComponents(mainComp);
			}
		}
		else
		{
			// The number of taken RAM ports(The number of ports - the number of ports available)
			int takenPorts = mbObj.getMaxRAMs() - mbObj.getDUCPortsAvailable();

			// Sets the max RAM ports
			mbObj.setMaxRAMs(Integer.parseInt(RAMslots.getSelectedItem()
					.toString()));
			mbObj.setRAMPortsAvailable(mbObj.getMaxRAMs() - takenPorts);
		}
	}



	/**
	 * Processes the DUC settings with the {@link HDD} and {@link Discdrive} objects.
	 */
	private void DUCsetup()
	{
		if ( mbObj.getMaxDUCs() > Integer.parseInt(DUCports.getSelectedItem()
				.toString()) )
		{
			Object[] comp = null;

			Object[] compHDD = null;

			Object[] compDisc = null;

			try
			{
				// Gets all the HDDs from the objects components array.
				compHDD = ArrayManagment
						.getSpesificComponents(HDD.class, mainObj
								.getComponents(),
								mainObj.getComponents().length);


				// Gets all the Discdrives from the objects components array.
				compDisc = ArrayManagment.getSpesificComponents(
						Discdrive.class, mainObj.getComponents(), mainObj
								.getComponents().length);
			}
			catch ( ObjectNotFoundException e )
			{

			}


			int compLenght = 0;

			if ( compHDD != null )
			{
				compLenght = compLenght + compHDD.length;
			}

			if ( compDisc != null )
			{
				compLenght = compLenght + compDisc.length;
			}

			comp = new Object[compLenght];


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

					if ( compHDD != null && hddCount < compHDD.length
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
					if ( compDisc != null && discCount < compDisc.length
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

			// Removes all the components from the objects components array.
			try
			{
				mainObj.setAllComponents(ComponentsManagment.removeComponents(
						comp, mainObj.getComponents(),
						mainObj.getComponents().length));
			}
			catch ( ObjectNotFoundInArrayException e )
			{
				e.printStackTrace();
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
						mainComp = ComponentsManagment.addComponent(comp[i],
								mainComp);
						mbObj.makeOneDUCportTaken();
					}
				}
				mainObj.setAllComponents(mainComp);
			}
		}
		else
		{
			// The number of taken DUC ports(The number of ports - the number of ports available)
			int takenPorts = mbObj.getMaxDUCs() - mbObj.getDUCPortsAvailable();

			// Sets the max DUC ports
			mbObj.setMaxDUCs(Integer.parseInt(DUCports.getSelectedItem()
					.toString()));
			mbObj.setDUCPortsAvailable(mbObj.getMaxDUCs() - takenPorts);
		}
	}



	/**
	 * Processes the USB settings with the ExternalNetworksCard objects.
	 */
	private void USBsetup()
	{
		if ( mbObj.getMaxUSBs() > Integer.parseInt(USBports.getSelectedItem()
				.toString()) )
		{
			int newMaxUSBports = Integer.parseInt(USBports.getSelectedItem()
					.toString());

			Object[] externalNICs = new Object[0];

			try
			{
				// Gets all the ExternalNetworksCard from the objects components array.
				externalNICs = ArrayManagment.getSpesificComponents(
						ExternalNetworksCard.class, mainObj.getComponents(),
						mainObj.getComponents().length);
			}
			catch ( ObjectNotFoundException e )
			{

			}


			// Gets all the connections, networked and devices
			Connection[] allConnections = mainObj.getAllConnections();

			// ------------------------------------------------------------------------------
			// If the number of USB ports is bigger then the number of externalNICS, which would mean that there is room
			// for connected USB devices.
			if ( newMaxUSBports > externalNICs.length )
			{
				// The connections array that will hold all the connections that are with USB
				Connection[] USBconnections = ConnectionManagment
						.connectedToBy(mainObj, ConnectionUtils.USB);


				// The number of USB ports left after External NICs are added.
				int leftUSBportsAfterExternalNICs = newMaxUSBports
						- externalNICs.length;

				if ( USBconnections != null )
				{
					// If the number of available USB ports is less then the number of USB devices
					if ( leftUSBportsAfterExternalNICs < USBconnections.length )
					{
						for ( int i = leftUSBportsAfterExternalNICs; i < USBconnections.length; i++ )
						{
							// Removes the connection
							WorkareaCanvasActions.removeWidgetConnection(
									CanvasManagment.findCanvas(mainObj,
											PrimeMain.canvases),
									USBconnections[i]);
						}
					}
				}

				// ------------------------------------------------------------------------------
				// The number of taken USB ports(The number of ports - the number of ports available)
				int takenPorts = mbObj.getMaxUSBs()
						- mbObj.getUSBPortsAvailable();

				// Sets the max USB ports
				mbObj.setMaxUSBs(Integer.parseInt(USBports.getSelectedItem()
						.toString()));
				mbObj.setUSBPortsAvailable(mbObj.getMaxUSBs() - takenPorts);
			}
			// There aren't enough USB port for all the externalNICs so every USB connection will be removed and only
			// externalNICs will be added until the USB ports run out.
			else
			{

				try
				{
					// Removes all the ExternalNetworksCard from the objects components array.
					mainObj.setAllComponents(ComponentsManagment
							.removeComponents(externalNICs, mainObj
									.getComponents(),
									mainObj.getComponents().length));


					// Disconnect all the existing USB connections
					for ( int i = 0; i < allConnections.length; i++ )
					{
						if ( allConnections[i] != null )
						{
							// If the connection type is USB
							if ( allConnections[i].getConnectionType().equals(
									ConnectionUtils.USB) )
							{
								// Removes the connection
								WorkareaCanvasActions.removeWidgetConnection(
										CanvasManagment.findCanvas(mainObj,
												PrimeMain.canvases),
										allConnections[i]);
							}
						}
					}


					// Sets the max USB ports

					mbObj.setMaxUSBs(Integer.parseInt(USBports
							.getSelectedItem().toString()));
					mbObj.setUSBPortsAvailable(mbObj.getMaxUSBs());

					// Adds the previously removed ExternalNICs in order until there are no more USB ports available.
					if ( externalNICs != null )
					{
						// The number of components there are room for.
						int counter = mbObj.getMaxUSBs();
						// All the components of the main object(without the ExternalNICs).
						Object[] mainComp = mainObj.getComponents();
						for ( int i = 0; i < counter; i++ )
						{
							// If i is smaller then the length of the externalNICs array.
							if ( i < externalNICs.length )
							{
								mainComp = ComponentsManagment.addComponent(
										externalNICs[i], mainComp);
								mbObj.makeOneUSBportTaken();
							}
						}
						mainObj.setAllComponents(mainComp);
					}

				}
				catch ( ObjectNotFoundInArrayException e )
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			// The number of taken USB ports(The number of ports - the number of ports available)
			int takenPorts = mbObj.getMaxUSBs() - mbObj.getUSBPortsAvailable();

			// Sets the max USB ports
			mbObj.setMaxUSBs(Integer.parseInt(USBports.getSelectedItem()
					.toString()));
			mbObj.setUSBPortsAvailable(mbObj.getMaxUSBs() - takenPorts);
		}
	}



	/**
	 * Processes the LAN settings with the connected objects.
	 * <i>The process first finds all {@link Object Objects} connected to the main {@link Object}. Then it finds the network
	 * cards, internal and external. It then removes the objects set as connected to the different network cards from the array of
	 * removable Objects, because in this function only the {@link Motherboard Motherboards} lan ports are are being changed.
	 * When only {@link Object Objects} connected to the {@link Motherboard} are left, they will be removed depending on the
	 * difference between the number of lan and the number of connected Objects.
	 */
	private void LANsetup()
	{
		if ( mbObj.getMaxIntegLANs() > Integer.parseInt(LANports
				.getSelectedItem().toString()) )
		{
			int newMaxLANports = Integer.parseInt(LANports.getSelectedItem()
					.toString());

			// The connections array that will hold all the connections that are with RJ45
			Object[] LANconnectedObjects = ConnectionManagment
					.objectsConnectedToBy(mainObj, ConnectionUtils.RJ45);

			try
			{
				// Gets all the InternalNetworksCard from the objects components array.
				Object[] internalNICs = internalNICs = ArrayManagment
						.getSpesificComponents(InternalNetworksCard.class,
								mainObj.getComponents(), mainObj
										.getComponents().length);

				for ( int i = 0; i < internalNICs.length; i++ )
				{
					InternalNetworksCard nic = (InternalNetworksCard) internalNICs[i];

					// If the NICs connection object is not null and connection type is RJ-45
					if ( nic.getConnectedObject() != null
							&& nic.getConnectionType().equals(
									ConnectionUtils.RJ45) )
					{
						// Goes through all the RJ45 connected objects
						for ( int j = 0; j < LANconnectedObjects.length; j++ )
						{
							// If the index is not null
							if ( LANconnectedObjects[j] != null )
							{
								// If the connected object serial is the same as the one connected to the NIC
								if ( LANconnectedObjects[j].getObjectSerial() == nic
										.getConnectedObject().getObjectSerial() )
								{
									// The object connected to the NIC is removed from the removable RJ45 connected objcets
									LANconnectedObjects[j] = null;
								}
							}
						}
					}
				}


			}
			catch ( ObjectNotFoundException e )
			{
				// No InternalNetworksCard found
			}

			try
			{
				// Gets all the InternalNetworksCard from the objects components array.
				Object[] externalNICs = externalNICs = ArrayManagment
						.getSpesificComponents(ExternalNetworksCard.class,
								mainObj.getComponents(), mainObj
										.getComponents().length);


				for ( int i = 0; i < externalNICs.length; i++ )
				{
					ExternalNetworksCard nic = (ExternalNetworksCard) externalNICs[i];

					// If the NICs connection object is not null and connection type is RJ-45
					if ( nic.getConnectedObject() != null
							&& nic.getConnectionType().equals(
									ConnectionUtils.RJ45) )
					{
						// Goes through all the RJ45 connected objects
						for ( int j = 0; j < LANconnectedObjects.length; j++ )
						{
							// If the index is not null
							if ( LANconnectedObjects[j] != null )
							{
								// If the connected object serial is the same as the one connected to the NIC
								if ( LANconnectedObjects[j].getObjectSerial() == nic
										.getConnectedObject().getObjectSerial() )
								{
									// The object connected to the NIC is removed from the removable RJ45 connected objcets
									LANconnectedObjects[j] = null;
								}
							}
						}
					}
				}
			}
			catch ( ObjectNotFoundException e )
			{
				// No ExternalNetworksCard found
			}


			// Removes the null pointers and shortens the array
			LANconnectedObjects = cleanup.cleanObjectArray(LANconnectedObjects);


			// Now LANconnectedObjects contains only objects connected to the motherboard

			// The number of LAN ports left after connected objects are added.
			int leftLANports = newMaxLANports - LANconnectedObjects.length;


			if ( LANconnectedObjects != null )
			{
				// If the number of available RJ-45 ports is less then the number of RJ-45 devices
				if ( leftLANports < 0 )
				{
					for ( int i = LANconnectedObjects.length + leftLANports; i < LANconnectedObjects.length; i++ )
					{
						// Gets the WorkareaCanvas the two objects are on
						WorkareaCanvas canvas = CanvasManagment.findCanvas(
								mainObj, PrimeMain.canvases);

						// Gets the WidgetExtendedConnection between the two objects
						WidgetExtendedConnection widCon = null;
						try
						{
							widCon = ConnectionManagment.findWidgetConnection(
									canvas, mainObj, LANconnectedObjects[i]);
						}
						catch ( ConnectionDoesNotExist e )
						{
							e.printStackTrace();
						}

						if ( widCon != null )
						{
							// Removes the connection
							WorkareaCanvasActions.removeWidgetConnection(
									canvas, widCon);
						}
					}
				}
			}
		}
		int takenPorts = mbObj.getMaxIntegLANs()
				- mbObj.getIntegLANPortsAvailable();

		// Sets the max LAN ports
		mbObj.setMaxIntegratedLANs(Integer.parseInt(LANports.getSelectedItem()
				.toString()));
		mbObj.setIntegLANPortsAvailable(mbObj.getMaxIntegLANs() - takenPorts);
	}

}
