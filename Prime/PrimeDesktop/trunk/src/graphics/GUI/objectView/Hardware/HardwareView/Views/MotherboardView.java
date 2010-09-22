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
package graphics.GUI.objectView.Hardware.HardwareView.Views;


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

import managment.ComponentsManagment;
import objects.ExternalHardware;
import objects.Hardware;
import objects.Infrastructure;
import objects.Object;
import objects.hardwareObjects.CPU;
import objects.hardwareObjects.GraphicsCard;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.Motherboard;
import widgetManipulation.NetworkRules;
import actions.canvasActions.ActionDeleteAllConnectionsToAndFrom;


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

	private JComboBox CoaxPorts;

	private JComboBox FiberPorts;


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


		ImageIcon icon = PrimeMain.objectImageIcons.get(Motherboard.class);
		JPanel p1 = HardwareEditor.GeneralInfo(mb, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);



		JPanel p2 = createSpesificInfo(mb);
		p2.setBorder(BorderFactory.createEtchedBorder());

		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1.0; // request any extra vertical space
		c.insets = new Insets(0, 10, 0, 10);
		this.add(p2, c);



		// THe remove component button
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel(
				PrimeMain.texts.getString("hwTabRemoveThisComponentLabel"));

		Button remove = new Button(
				PrimeMain.texts.getString("hwTabRemoveComponentButtonLabel"));
		remove.addActionListener(this);
		remove.setActionCommand("removeComp");

		buttons.add(label);
		buttons.add(remove);

		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0; // request any extra vertical space
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
		JLabel[] labels = new JLabel[20];


		labels[0] = new JLabel(PrimeMain.texts.getString("mbViewProducerLabel"));
		labels[0]
				.setToolTipText(PrimeMain.texts.getString("mbViewProducerTip"));

		labels[1] = new JLabel(PrimeMain.texts.getString("mbViewFormLabel"));
		labels[1].setToolTipText(PrimeMain.texts.getString("mbViewFormTip"));

		labels[2] = new JLabel(PrimeMain.texts.getString("mbViewSocketLabel"));
		labels[2].setToolTipText(PrimeMain.texts.getString("mbViewSocketTip"));

		labels[3] = new JLabel(PrimeMain.texts.getString("mbViewBusSpeedLabel"));
		labels[3]
				.setToolTipText(PrimeMain.texts.getString("mbViewBusSpeedTip"));

		labels[4] = new JLabel(PrimeMain.texts.getString("mbViewChipsetLabel"));
		labels[4].setToolTipText(PrimeMain.texts.getString("mbViewChipsetTip"));

		labels[5] = new JLabel(PrimeMain.texts.getString("mbViewGPUportLabel"));
		labels[5].setToolTipText(PrimeMain.texts.getString("mbViewGPUportTip"));

		labels[6] = new JLabel(
				PrimeMain.texts.getString("mbViewConnectionPortLabel"));
		labels[6].setToolTipText(PrimeMain.texts
				.getString("mbViewConnectionPortTip"));

		labels[7] = new JLabel(PrimeMain.texts.getString("mbViewRamTypeLabel"));
		labels[7].setToolTipText(PrimeMain.texts.getString("mbViewRamTypeTip"));

		labels[8] = new JLabel(
				PrimeMain.texts.getString("mbViewAudioIntegratedLabel"));
		labels[8].setToolTipText(PrimeMain.texts
				.getString("mbViewAudioIntegratedTip"));

		labels[9] = new JLabel(
				PrimeMain.texts.getString("mbViewGPUIntegratedLabel"));
		labels[9].setToolTipText(PrimeMain.texts
				.getString("mbViewGPUIntegratedTip"));

		labels[10] = new JLabel(
				PrimeMain.texts.getString("mbViewNICIntegratedLabel"));
		labels[10].setToolTipText(PrimeMain.texts
				.getString("mbViewNICIntegratedTip"));

		labels[11] = new JLabel(
				PrimeMain.texts.getString("mbViewGPUinstalledLabel"));
		labels[11].setToolTipText(PrimeMain.texts
				.getString("mbViewGPUinstalledTip"));

		labels[12] = new JLabel(
				PrimeMain.texts.getString("mbViewCPUsocketLabel"));
		labels[12].setToolTipText(PrimeMain.texts
				.getString("mbViewCPUsocketTip"));

		labels[13] = new JLabel(
				PrimeMain.texts.getString("mbViewPCIslotsLabel"));
		labels[13].setToolTipText(PrimeMain.texts
				.getString("mbViewPCIslotsTip"));

		labels[14] = new JLabel(
				PrimeMain.texts.getString("mbViewRAMslotsLabel"));
		labels[14].setToolTipText(PrimeMain.texts
				.getString("mbViewRAMslotsTip"));

		labels[15] = new JLabel(
				PrimeMain.texts.getString("mbViewUSBportsLabel"));
		labels[15].setToolTipText(PrimeMain.texts
				.getString("mbViewUSBportsTip"));

		labels[16] = new JLabel(PrimeMain.texts.getString("mbViewDUCportLabel"));
		labels[16]
				.setToolTipText(PrimeMain.texts.getString("mbViewDUCportTip"));

		labels[17] = new JLabel(
				PrimeMain.texts.getString("mbViewLANportsLabel"));
		labels[17].setToolTipText(PrimeMain.texts
				.getString("mbViewLANportsTip"));

		labels[18] = new JLabel(
				PrimeMain.texts.getString("mbViewCoaxportsLabel"));
		labels[18].setToolTipText(PrimeMain.texts
				.getString("mbViewCoaxportsTip"));

		labels[19] = new JLabel(
				PrimeMain.texts.getString("mbViewFiberportsLabel"));
		labels[19].setToolTipText(PrimeMain.texts
				.getString("mbViewFiberportsTip"));



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
			if ( mb.getGraphicalPort() != null && !mb.getGraphicalPort().equals("") )
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
					&& !mb.getDUCconnectionType().equals("") )
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
			if ( mb.getRAMtype() != null && !mb.getRAMtype().equals("") )
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
		String[] infraLanPortsStrings = { "0", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
				"28", "29", "30", "31", "32", "33", "34", "35", "36", "37",
				"38", "39", "40" };

		String[] portsNumber;

		if ( mainObj instanceof Infrastructure )
		{
			portsNumber = infraLanPortsStrings;
		}
		else
		{
			portsNumber = LANportsStrings;
		}

		LANports = new JComboBox(portsNumber);
		LANports.setMaximumSize(tfSize);
		LANports.setMinimumSize(tfSize);
		LANports.setPreferredSize(tfSize);
		LANports.setBackground(Color.WHITE);
		LANports.setToolTipText(labels[17].getToolTipText());
		LANports.setActionCommand("LAN Ports");
		LANports.addActionListener(this);


		int LANportsIndex = 0;

		for ( int i = 1; i < portsNumber.length; i++ )
		{
			if ( Integer.parseInt(LANports.getItemAt(i).toString()) == (mb
					.getMaxIntegLANs()) )
			{
				LANportsIndex = i;
				i = portsNumber.length;
			}
		}

		LANports.setSelectedIndex(LANportsIndex);

		labels[17].setLabelFor(LANports);

		panel.add(labels[17]);
		panel.add(LANports);
		// --------------------------




		// Coax ports
		String[] COAXportsStrings = { "0", "1", "2", "3", "4", "5", "6" };
		String[] infraCOAXPortsStrings = { "0", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
				"28", "29", "30", "31", "32", "33", "34", "35", "36", "37",
				"38", "39", "40" };

		String[] CoaxPortsNumber;

		if ( mainObj instanceof Infrastructure )
		{
			CoaxPortsNumber = infraCOAXPortsStrings;
		}
		else
		{
			CoaxPortsNumber = COAXportsStrings;
		}

		CoaxPorts = new JComboBox(CoaxPortsNumber);
		CoaxPorts.setMaximumSize(tfSize);
		CoaxPorts.setMinimumSize(tfSize);
		CoaxPorts.setPreferredSize(tfSize);
		CoaxPorts.setBackground(Color.WHITE);
		CoaxPorts.setToolTipText(labels[17].getToolTipText());
		CoaxPorts.setActionCommand("COAX Ports");
		CoaxPorts.addActionListener(this);


		int COAXportsIndex = 0;

		for ( int i = 1; i < CoaxPortsNumber.length; i++ )
		{
			if ( Integer.parseInt(CoaxPorts.getItemAt(i).toString()) == (mb
					.getMaxCoaxs()) )
			{
				COAXportsIndex = i;
				i = CoaxPortsNumber.length;
			}
		}

		CoaxPorts.setSelectedIndex(COAXportsIndex);

		labels[18].setLabelFor(CoaxPorts);

		panel.add(labels[18]);
		panel.add(CoaxPorts);
		// --------------------------




		// Fiber ports
		String[] FiberportsStrings = { "0", "1", "2", "3", "4", "5", "6" };
		String[] infraFiberPortsStrings = { "0", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
				"28", "29", "30", "31", "32", "33", "34", "35", "36", "37",
				"38", "39", "40" };

		String[] FiberPortsNumber;

		if ( mainObj instanceof Infrastructure )
		{
			FiberPortsNumber = infraFiberPortsStrings;
		}
		else
		{
			FiberPortsNumber = FiberportsStrings;
		}

		FiberPorts = new JComboBox(FiberPortsNumber);
		FiberPorts.setMaximumSize(tfSize);
		FiberPorts.setMinimumSize(tfSize);
		FiberPorts.setPreferredSize(tfSize);
		FiberPorts.setBackground(Color.WHITE);
		FiberPorts.setToolTipText(labels[17].getToolTipText());
		FiberPorts.setActionCommand("COAX Ports");
		FiberPorts.addActionListener(this);


		int FiberportsIndex = 0;

		for ( int i = 1; i < FiberPortsNumber.length; i++ )
		{
			if ( Integer.parseInt(FiberPorts.getItemAt(i).toString()) == (mb
					.getMaxFibers()) )
			{
				FiberportsIndex = i;
				i = FiberPortsNumber.length;
			}
		}

		FiberPorts.setSelectedIndex(FiberportsIndex);

		labels[19].setLabelFor(FiberPorts);

		panel.add(labels[19]);
		panel.add(FiberPorts);
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
	 * 
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
	 * 
	 * @see graphics.GUI.objectView.Hardware.HardwareView.HardwareView#save()
	 */
	public boolean save()
	{
		boolean validated = true;

		if ( !name.getText().equals("") )
		{
			mbObj.setObjectName(name.getText());
		}

		if ( !desc.getText().equals("") )
		{
			mbObj.setDescription(desc.getText());
		}

		mbObj.setProducer(producerField.getText());

		mbObj.setForm(forms.getSelectedItem().toString());

		mbObj.setSocket(sockets.getSelectedItem().toString());


		if ( !busSpeeds.getSelectedItem().toString().equals("") )
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


		if ( !CPUsockets.getSelectedItem().toString().equals("") )
		{
			ComponentsManagment.CPUportsValidation(mainObj, mbObj, CPUsockets);
		}

		if ( !PCIslots.getSelectedItem().toString().equals("") )
		{
			ComponentsManagment.PCIportsValidation(mainObj, mbObj, PCIslots);
		}

		if ( !RAMslots.getSelectedItem().toString().equals("") )
		{
			ComponentsManagment.RAMportsValidation(mainObj, mbObj, RAMslots);
		}

		if ( !USBports.getSelectedItem().toString().equals("") )
		{
			boolean standardObject = false;

			// Checks to see whether this is a object being shown as a standard
			// object
			if ( PrimeMain.stdObjView != null )
			{
				if ( mainObj.getObjectSerial() == PrimeMain.stdObjView
						.getSplitView().getObjView().getHardStdObjView()
						.getGivenObject().getObjectSerial() )
				{
					standardObject = true;
				}
			}


			if ( standardObject || mainObj.isExemptedNetworkRules()
					|| mainObj instanceof Infrastructure )
			{
				ComponentsManagment.USBportsValidation(mainObj, mbObj,
						USBports, PrimeMain.currentCanvas);
			}
			else
			{
				if ( PrimeMain.currentCanvas != null )
				{
					NetworkRules rules = PrimeMain.currentCanvas.getRules();
					// If USB is allowed
					if ( !rules.isUSBnotAllowed() )
					{
						// USB ports are unlimited
						if ( rules.getUSBportsAllowed() == -1 )
						{
							ComponentsManagment.USBportsValidation(mainObj,
									mbObj, USBports, PrimeMain.currentCanvas);
						}
						// If there is a max number of USB ports allowed
						else
						{
							// If the user has selected a valid option
							if ( USBports.getSelectedIndex() != -1 )
							{
								// The new number of ports
								int newPortsNumber = Integer.parseInt(USBports
										.getSelectedItem().toString());

								// If the new number of ports is equal to, or
								// less then, the maximum number of allowed
								// ports.
								if ( newPortsNumber <= rules
										.getUSBportsAllowed() )
								{
									ComponentsManagment.USBportsValidation(
											mainObj, mbObj, USBports,
											PrimeMain.currentCanvas);
								}
								else
								{
									JOptionPane
											.showMessageDialog(
													this,
													PrimeMain.texts
															.getString("rulesUSBviolationMsg"),
													PrimeMain.texts
															.getString("error"),
													JOptionPane.ERROR_MESSAGE);

									validated = false;
								}
							}
						}
					}
					else
					{
						// If the user has selected number of ports
						if ( USBports.getSelectedIndex() != -1 )
						{
							// The new number of ports
							int newPortsNumber = Integer.parseInt(USBports
									.getSelectedItem().toString());

							// If the new number of ports is 0
							if ( newPortsNumber != 0 )
							{
								JOptionPane
										.showMessageDialog(
												this,
												PrimeMain.texts
														.getString("rulesUSBnotAllowedMsg"),
												PrimeMain.texts
														.getString("error"),
												JOptionPane.ERROR_MESSAGE);

								validated = false;
							}
						}
					}
				}
			}
		}



		if ( !DUCports.getSelectedItem().toString().equals("") )
		{
			ComponentsManagment.DUCportsValidation(mainObj, mbObj, DUCports);
		}




		if ( !LANports.getSelectedItem().toString().equals("") )
		{
			boolean standardObject = false;

			// Checks to see whether this is a object being shown as a standard
			// object
			if ( PrimeMain.stdObjView != null )
			{
				if ( mainObj.getObjectSerial() == PrimeMain.stdObjView
						.getSplitView().getObjView().getHardStdObjView()
						.getGivenObject().getObjectSerial() )
				{
					standardObject = true;
				}
			}


			if ( standardObject || mainObj.isExemptedNetworkRules()
					|| mainObj instanceof Infrastructure
					|| mainObj instanceof ExternalHardware )
			{
				ComponentsManagment.LANportsValidation(mainObj, mbObj,
						LANports, PrimeMain.currentCanvas);
			}
			else
			{
				if ( PrimeMain.currentCanvas != null )
				{
					NetworkRules rules = PrimeMain.currentCanvas.getRules();
					// If LAN is allowed
					if ( !rules.isLANnotAllowed() )
					{
						// LAN ports are unlimited
						if ( rules.getLANportsAllowed() == -1 )
						{
							ComponentsManagment.LANportsValidation(mainObj,
									mbObj, LANports, PrimeMain.currentCanvas);
						}
						// If there is a max number of LAN ports allowed
						else
						{
							// If the user has selected a valid option
							if ( LANports.getSelectedIndex() != -1 )
							{
								// The new number of ports
								int newPortsNumber = Integer.parseInt(LANports
										.getSelectedItem().toString());

								// If the new number of ports is equal to, or
								// less then, the maximum number of allowed
								// ports.
								if ( newPortsNumber <= rules
										.getLANportsAllowed() )
								{
									ComponentsManagment.LANportsValidation(
											mainObj, mbObj, LANports,
											PrimeMain.currentCanvas);
								}
								else
								{
									JOptionPane
											.showMessageDialog(
													this,
													PrimeMain.texts
															.getString("rulesLANviolationMsg"),
													PrimeMain.texts
															.getString("error"),
													JOptionPane.ERROR_MESSAGE);

									validated = false;
								}
							}
						}
					}
					else
					{
						// If the user has selected number of ports
						if ( LANports.getSelectedIndex() != -1 )
						{
							// The new number of ports
							int newPortsNumber = Integer.parseInt(LANports
									.getSelectedItem().toString());

							// If the new number of ports is 0
							if ( newPortsNumber != 0 )
							{
								JOptionPane
										.showMessageDialog(
												this,
												PrimeMain.texts
														.getString("rulesLANnotAllowedMsg"),
												PrimeMain.texts
														.getString("error"),
												JOptionPane.ERROR_MESSAGE);

								validated = false;
							}
						}
					}
				}
			}
		}



		if ( !CoaxPorts.getSelectedItem().toString().equals("") )
		{
			boolean standardObject = false;

			// Checks to see whether this is a object being shown as a standard
			// object
			if ( PrimeMain.stdObjView != null )
			{
				if ( mainObj.getObjectSerial() == PrimeMain.stdObjView
						.getSplitView().getObjView().getHardStdObjView()
						.getGivenObject().getObjectSerial() )
				{
					standardObject = true;
				}
			}


			if ( standardObject || mainObj.isExemptedNetworkRules()
					|| mainObj instanceof Infrastructure
					|| mainObj instanceof ExternalHardware )
			{
				ComponentsManagment.COAXportsValidation(mainObj, mbObj,
						CoaxPorts, PrimeMain.currentCanvas);
			}
			else
			{
				if ( PrimeMain.currentCanvas != null )
				{
					NetworkRules rules = PrimeMain.currentCanvas.getRules();
					// If COAX is allowed
					if ( !rules.isCOAXnotAllowed() )
					{
						// COAX ports are unlimited
						if ( rules.getCOAXportsAllowed() == -1 )
						{
							ComponentsManagment.COAXportsValidation(mainObj,
									mbObj, CoaxPorts, PrimeMain.currentCanvas);
						}
						// If there is a max number of COAX ports allowed
						else
						{
							// If the user has selected a valid option
							if ( CoaxPorts.getSelectedIndex() != -1 )
							{
								// The new number of ports
								int newPortsNumber = Integer.parseInt(CoaxPorts
										.getSelectedItem().toString());

								// If the new number of ports is equal to, or
								// less then, the maximum number of allowed
								// ports.
								if ( newPortsNumber <= rules
										.getCOAXportsAllowed() )
								{
									ComponentsManagment.COAXportsValidation(
											mainObj, mbObj, CoaxPorts,
											PrimeMain.currentCanvas);
								}
								else
								{
									JOptionPane
											.showMessageDialog(
													this,
													PrimeMain.texts
															.getString("rulesCOAXviolationMsg"),
													PrimeMain.texts
															.getString("error"),
													JOptionPane.ERROR_MESSAGE);

									validated = false;
								}
							}
						}
					}
					else
					{
						// If the user has selected number of ports
						if ( CoaxPorts.getSelectedIndex() != -1 )
						{
							// The new number of ports
							int newPortsNumber = Integer.parseInt(CoaxPorts
									.getSelectedItem().toString());

							// If the new number of ports is 0
							if ( newPortsNumber != 0 )
							{
								JOptionPane
										.showMessageDialog(
												this,
												PrimeMain.texts
														.getString("rulesCOAXnotAllowedMsg"),
												PrimeMain.texts
														.getString("error"),
												JOptionPane.ERROR_MESSAGE);

								validated = false;
							}
						}
					}
				}
			}
		}



		if ( !FiberPorts.getSelectedItem().toString().equals("") )
		{
			boolean standardObject = false;

			// Checks to see whether this is a object being shown as a standard
			// object
			if ( PrimeMain.stdObjView != null )
			{
				if ( mainObj.getObjectSerial() == PrimeMain.stdObjView
						.getSplitView().getObjView().getHardStdObjView()
						.getGivenObject().getObjectSerial() )
				{
					standardObject = true;
				}
			}


			if ( standardObject || mainObj.isExemptedNetworkRules()
					|| mainObj instanceof Infrastructure
					|| mainObj instanceof ExternalHardware )
			{
				ComponentsManagment.FIBERportsValidation(mainObj, mbObj,
						FiberPorts, PrimeMain.currentCanvas);
			}
			else
			{
				if ( PrimeMain.currentCanvas != null )
				{
					NetworkRules rules = PrimeMain.currentCanvas.getRules();
					// If FIBER is allowed
					if ( !rules.isFIBERnotAllowed() )
					{
						// FIBER ports are unlimited
						if ( rules.getFIBERportsAllowed() == -1 )
						{
							ComponentsManagment.FIBERportsValidation(mainObj,
									mbObj, FiberPorts, PrimeMain.currentCanvas);
						}
						// If there is a max number of FIBER ports allowed
						else
						{
							// If the user has selected a valid option
							if ( FiberPorts.getSelectedIndex() != -1 )
							{
								// The new number of ports
								int newPortsNumber = Integer
										.parseInt(FiberPorts.getSelectedItem()
												.toString());

								// If the new number of ports is equal to, or
								// less then, the maximum number of allowed
								// ports.
								if ( newPortsNumber <= rules
										.getFIBERportsAllowed() )
								{
									ComponentsManagment.FIBERportsValidation(
											mainObj, mbObj, FiberPorts,
											PrimeMain.currentCanvas);
								}
								else
								{
									JOptionPane
											.showMessageDialog(
													this,
													PrimeMain.texts
															.getString("rulesFIBERviolationMsg"),
													PrimeMain.texts
															.getString("error"),
													JOptionPane.ERROR_MESSAGE);

									validated = false;
								}
							}
						}
					}
					else
					{
						// If the user has selected number of ports
						if ( FiberPorts.getSelectedIndex() != -1 )
						{
							// The new number of ports
							int newPortsNumber = Integer.parseInt(FiberPorts
									.getSelectedItem().toString());

							// If the new number of ports is 0
							if ( newPortsNumber != 0 )
							{
								JOptionPane
										.showMessageDialog(
												this,
												PrimeMain.texts
														.getString("rulesFIBERnotAllowedMsg"),
												PrimeMain.texts
														.getString("error"),
												JOptionPane.ERROR_MESSAGE);

								validated = false;
							}
						}
					}
				}
			}
		}

		return validated;
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
			else if ( command.equals("COAX Ports") )
			{

			}
			else if ( command.equals("FIBER Ports") )
			{

			}
		}
		else if ( e.getSource() instanceof Button )
		{
			Button check = (Button) e.getSource();

			String command = check.getActionCommand();

			if ( command.equals("removeComp") )
			{
				int n = JOptionPane
						.showConfirmDialog(this, PrimeMain.texts
								.getString("mbViewReplaceMBquestionMsg"),
								PrimeMain.texts.getString("verify"),
								JOptionPane.YES_NO_OPTION);


				// If the answer is "No"
				if ( n == 0 )
				{
					// We have to remove all connection between this object and
					// other objects on the canvas
					// WorkareaCanvasActions.removeAllConnectionsToFromObject(
					// PrimeMain1.currentCanvas, mainObj);
					ActionDeleteAllConnectionsToAndFrom action = new ActionDeleteAllConnectionsToAndFrom(
							PrimeMain.texts
									.getString("actionDeleteAllConnectionName"));
					action.performAction(false);


					// Since the motherboard is where most of the connections
					// are placed we first have to remove all connections
					// to the devices.
					mainObj.removeAllConnections();


					// Then we have to remove all the components that a object
					// contains.
					mainObj.removeAllComponents();


					// Resets all the ports so that all ports are available.
					mbObj.resetAllComponents();

					// Now that the object has no connections to other
					// components and device we can add the motherboard object.
					mainObj.addComponent(mbObj);


					// Updates the views of the object to correctly show the
					// current info.
					ObjectView view = PrimeMain.getObjectView(mainObj);
					if ( view != null )
					{
						view.updateViewInfo();
					}
					// If no view is returned, then the standard object view is
					// open and that should be updated.
					else if ( PrimeMain.stdObjView != null )
					{
						PrimeMain.stdObjView.getSplitView().getObjView()
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
