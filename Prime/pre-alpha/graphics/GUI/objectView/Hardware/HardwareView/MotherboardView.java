/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView;


import graphics.ImageLocator;
import graphics.GUI.SpringUtilities;
import graphics.GUI.objectView.Hardware.HardwareEditor;
import hardware.Motherboard;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class MotherboardView extends JPanel
{
	/**
	 * TODO - Description NEEDED!
	 *
	 * @param mb
	 */
	public MotherboardView(Motherboard mb)
	{
		
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
		
		ImageIcon icon = ImageLocator.getImageIconObject("Motherboard");
		JPanel p1 = HardwareEditor.GeneralInfo(mb,icon);
		p1.setBorder(BorderFactory.createEtchedBorder());
		
		
		
		
		
		this.add(p1,c);
		
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 5;
		c.insets = new Insets(0, 10, 10, 10);
		
		JPanel p2 = createSpesificInfo(mb);
		p2.setBorder(BorderFactory.createEtchedBorder());
		
		this.add(p2,c);
	}
	
	
	private JPanel createSpesificInfo(Motherboard mb)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[18];
		
		
		labels[0] = new JLabel("Producer");
		labels[0].setToolTipText("The producer of the motherboard.");
		
		labels[1] = new JLabel("Form");
		labels[1].setToolTipText("The form of the motherboard, ie. the dimensions.");
		
		labels[2] = new JLabel("Socket");
		labels[2].setToolTipText("The socket on the motherboard which holds the CPU.");
		
		labels[3] = new JLabel("Bus Speed");
		labels[3].setToolTipText("The bus speed, the speed of information transfer, on the motherboard.");
		
		labels[4] = new JLabel("Chipset");
		labels[4].setToolTipText("The chipset on the motherboard.");
		
		labels[5] = new JLabel("GPU Port");
		labels[5].setToolTipText("The graphics card port on the motherboard.");
		
		labels[6] = new JLabel("Connection Port");
		labels[6].setToolTipText("The ports that are for connections to harddiscs and cdrom.");
		
		labels[7] = new JLabel("Ram Type");
		labels[7].setToolTipText("The ram port on the motherboard.");
		
		labels[8] = new JLabel("Audiocard Integrated");
		labels[8].setToolTipText("Whether or not a audio card i integrated in the motherboard.");
		
		labels[9] = new JLabel("GPU integrated");
		labels[9].setToolTipText("Whether or not a graphics card i integrated in the motherboard.");
		
		labels[10] = new JLabel("NIC integrated");
		labels[10].setToolTipText("Whether or not a LAN card i integrated in the motherboard.");
		
		labels[11] = new JLabel("GPU installed");
		labels[11].setToolTipText("Whether or not a graphical card is installed on the motherboard. Regardless of the status of the integrated GPU.");
		
		labels[12] = new JLabel("CPU socket");
		labels[12].setToolTipText("The number of CPU sockets on the motherboard.");
		
		labels[13] = new JLabel("PCI slots");
		labels[13].setToolTipText("The number of PCI slots on the motherboard.");
		
		labels[14] = new JLabel("RAM slots");
		labels[14].setToolTipText("The number of RAM slots on the motherboard.");
		
		labels[15] = new JLabel("USB ports");
		labels[15].setToolTipText("The number of USB ports on the motherboard.");
		
		labels[16] = new JLabel("DUC slots");
		labels[16].setToolTipText("The number of connection ports (SATA or IDE) on the motherboard.");
		
		labels[17] = new JLabel("LAN ports");
		labels[17].setToolTipText("The number of LAN ports on the motherboard.");
		
		
		
		Dimension tfSize = new Dimension(90,20);
		
		
		
		// PRODUCER
		JTextField producerField = new JTextField(7);
		labels[0].setLabelFor(producerField);
		producerField.setMaximumSize(tfSize);
		producerField.setPreferredSize(tfSize);
		producerField.setText(mb.getProducer());
		producerField.setToolTipText(labels[0].getToolTipText());
		
		panel.add(labels[0]);
		panel.add(producerField);
		// --------------------------
		
		
		// FORM
		String[] formsStrings = {"","ATX","mATX","eATX"};
		JComboBox forms = new JComboBox(formsStrings);
		forms.setMaximumSize(tfSize);
		forms.setPreferredSize(tfSize);
		forms.setBackground(Color.WHITE);
		forms.setToolTipText(labels[1].getToolTipText());

		int formIndex = 0;
		
		for(int i = 0;i<forms.getComponentCount();i++)
		{
			if ( mb.getForm() != null && mb.getForm() != "" )
			{
				if ( forms.getItemAt(i).equals(mb.getForm()) )
				{
					formIndex = i;
					i = forms.getComponentCount();
				}
			}
		}
		
		forms.setSelectedIndex(formIndex);
		
		labels[1].setLabelFor(forms);
		
		panel.add(labels[1]);
		panel.add(forms);
		// --------------------------
		
		
		// SOCKET
		String[] socketsStrings = {"","Intel 775","Intel 939","AMD AM2","AMD AM2+"};
		JComboBox sockets = new JComboBox(socketsStrings);
		sockets.setMaximumSize(tfSize);
		sockets.setPreferredSize(tfSize);
		sockets.setBackground(Color.WHITE);
		sockets.setToolTipText(labels[2].getToolTipText());

		
		int socketsIndex = 0;
		
		for(int i = 0;i<sockets.getComponentCount();i++)
		{
			if ( mb.getSocket() != null && mb.getSocket() != "" )
			{
				if ( sockets.getItemAt(i).equals(mb.getSocket()) )
				{
					socketsIndex = i;
					i = sockets.getComponentCount();
				}
			}
		}
		
		sockets.setSelectedIndex(socketsIndex);
		
		labels[2].setLabelFor(sockets);
		
		panel.add(labels[2]);
		panel.add(sockets);
		// --------------------------
		
		
		// BUS SPEED
		String[] busspeedStrings = {"","800","1066"};
		JComboBox busSpeeds = new JComboBox(busspeedStrings);
		busSpeeds.setMaximumSize(tfSize);
		busSpeeds.setPreferredSize(tfSize);
		busSpeeds.setBackground(Color.WHITE);
		busSpeeds.setToolTipText(labels[3].getToolTipText());

		
		int busSpeedIndex = 0;
		
		for(int i = 0;i<busSpeeds.getComponentCount();i++)
		{
			if ( mb.getBusSpeed() != 0 )
			{
				if ( busSpeeds.getItemAt(i).equals(mb.getBusSpeed()) )
				{
					busSpeedIndex = i;
					i = busSpeeds.getComponentCount();
				}
			}
		}
		
		busSpeeds.setSelectedIndex(busSpeedIndex);

		labels[3].setLabelFor(busSpeeds);
		
		panel.add(labels[3]);
		panel.add(busSpeeds);
		// --------------------------
		
		
		// CHIPSET
		JTextField chipsetField = new JTextField(7);
		labels[4].setLabelFor(chipsetField);
		chipsetField.setMaximumSize(tfSize);
		chipsetField.setPreferredSize(tfSize);
		chipsetField.setText(mb.getChipset());
		chipsetField.setToolTipText(labels[4].getToolTipText());
		
		
		panel.add(labels[4]);
		panel.add(chipsetField);
		// --------------------------
		
		
		// GPU PORT
		String[] gpuPortStrings = {"","AGP","PCI","PCI-E"};
		JComboBox gpuPorts = new JComboBox(gpuPortStrings);
		gpuPorts.setMaximumSize(tfSize);
		gpuPorts.setMinimumSize(tfSize);
		gpuPorts.setPreferredSize(tfSize);
		gpuPorts.setBackground(Color.WHITE);
		gpuPorts.setToolTipText(labels[5].getToolTipText());

		
		int gpuPortIndex = 0;
		
		for(int i = 0;i<gpuPorts.getComponentCount();i++)
		{
			if ( mb.getGraphicalPort() != null && mb.getGraphicalPort() != "" )
			{
				if ( gpuPorts.getItemAt(i).equals(mb.getGraphicalPort()) )
				{
					gpuPortIndex = i;
					i = gpuPorts.getComponentCount();
				}
			}
		}
		
		gpuPorts.setSelectedIndex(gpuPortIndex);

		labels[5].setLabelFor(gpuPorts);
		
		panel.add(labels[5]);
		panel.add(gpuPorts);
		// --------------------------
		
		
		
		// DUC, connection port
		String[] DUCStrings = {"","IDE","SATA","eSATA"};
		JComboBox DUCPorts = new JComboBox(DUCStrings);
		DUCPorts.setMaximumSize(tfSize);
		DUCPorts.setMinimumSize(tfSize);
		DUCPorts.setPreferredSize(tfSize);
		DUCPorts.setBackground(Color.WHITE);
		DUCPorts.setToolTipText(labels[6].getToolTipText());

		
		int DUCPortIndex = 0;
		
		for(int i = 0;i<DUCPorts.getComponentCount();i++)
		{
			if ( mb.getDUCconnectionType() != null && mb.getDUCconnectionType() != "" )
			{
				if ( DUCPorts.getItemAt(i).equals(mb.getDUCconnectionType()) )
				{
					DUCPortIndex = i;
					i = DUCPorts.getComponentCount();
				}
			}
		}
		
		DUCPorts.setSelectedIndex(DUCPortIndex);

		labels[6].setLabelFor(DUCPorts);
		
		panel.add(labels[6]);
		panel.add(DUCPorts);
		// --------------------------
		
		
		
		// RAM
		String[] RAMStrings = {"","DDR","DDR2","DDR3"};
		JComboBox RAMPorts = new JComboBox(RAMStrings);
		RAMPorts.setMaximumSize(tfSize);
		RAMPorts.setMinimumSize(tfSize);
		RAMPorts.setPreferredSize(tfSize);
		RAMPorts.setBackground(Color.WHITE);
		RAMPorts.setToolTipText(labels[7].getToolTipText());

		
		int RAMPortIndex = 0;
		
		for(int i = 0;i<RAMPorts.getComponentCount();i++)
		{
			if ( mb.getRAMtype() != null && mb.getRAMtype() != "" )
			{
				if ( RAMPorts.getItemAt(i).equals(mb.getRAMtype()) )
				{
					RAMPortIndex = i;
					i = RAMPorts.getComponentCount();
				}
			}
		}
		
		RAMPorts.setSelectedIndex(RAMPortIndex);

		labels[7].setLabelFor(RAMPorts);
		
		panel.add(labels[7]);
		panel.add(RAMPorts);
		// --------------------------
		
		
		
		// Integrated Audio card
		JCheckBox intAudioCard = new JCheckBox();
		intAudioCard.setSelected(mb.isIntegAudioCard());
		intAudioCard.setToolTipText(labels[8].getToolTipText());

		labels[8].setLabelFor(intAudioCard);
		
		panel.add(labels[8]);
		panel.add(intAudioCard);
		// --------------------------
		
		
		
		// Integrated GPU card
		JCheckBox intGPU = new JCheckBox();
		intGPU.setSelected(mb.isIntegGraphicalCard());
		intGPU.setToolTipText(labels[9].getToolTipText());

		labels[9].setLabelFor(intGPU);
		
		panel.add(labels[9]);
		panel.add(intGPU);
		// --------------------------
		
		
		
		// Integrated NIC
		JCheckBox intNIC = new JCheckBox();
		intNIC.setSelected(mb.isIntegLANcard());
		intNIC.setToolTipText(labels[10].getToolTipText());

		labels[10].setLabelFor(intNIC);
		
		panel.add(labels[10]);
		panel.add(intNIC);
		// --------------------------
		
		
		
		// GPU installed, regardless of whether or not any integrate GPU exists.
		JCheckBox GPUinstalled = new JCheckBox();
		GPUinstalled.setSelected(mb.isGraphicsCardInstalled());
		GPUinstalled.setToolTipText(labels[11].getToolTipText());

		labels[11].setLabelFor(GPUinstalled);
		
		panel.add(labels[11]);
		panel.add(GPUinstalled);
		// --------------------------
		
		
		
		
		// CPU sockets
		String[] CPUsocketsStrings = {"","1","2","3","4","5","6"};
		JComboBox CPUsockets = new JComboBox(CPUsocketsStrings);
		CPUsockets.setMaximumSize(tfSize);
		CPUsockets.setMinimumSize(tfSize);
		CPUsockets.setPreferredSize(tfSize);
		CPUsockets.setBackground(Color.WHITE);
		CPUsockets.setToolTipText(labels[12].getToolTipText());
		
		int CPUsocketsIndex = 0;
		
		for(int i = 1;i<CPUsockets.getComponentCount();i++)
		{
				if ( Integer.parseInt(CPUsockets.getItemAt(i).toString()) == (mb.getMaxCPUs()) )
				{
					CPUsocketsIndex = i;
					i = CPUsockets.getComponentCount();
				}
		}
		
		CPUsockets.setSelectedIndex(CPUsocketsIndex);

		labels[12].setLabelFor(CPUsockets);
		
		panel.add(labels[12]);
		panel.add(CPUsockets);
		// --------------------------
		
		
		
		
		// PCI slots
		String[] PCIslotsStrings = {"","1","2","3","4","5","6"};
		JComboBox PCIslots = new JComboBox(PCIslotsStrings);
		PCIslots.setMaximumSize(tfSize);
		PCIslots.setMinimumSize(tfSize);
		PCIslots.setPreferredSize(tfSize);
		PCIslots.setBackground(Color.WHITE);
		PCIslots.setToolTipText(labels[13].getToolTipText());

		
		int PCIslotsIndex = 0;
		
		for(int i = 1;i<PCIslots.getComponentCount();i++)
		{
				if ( Integer.parseInt(PCIslots.getItemAt(i).toString()) == (mb.getMaxPCIs()) )
				{
					PCIslotsIndex = i;
					i = PCIslots.getComponentCount();
				}
		}
		
		PCIslots.setSelectedIndex(PCIslotsIndex);

		labels[13].setLabelFor(PCIslots);
		
		panel.add(labels[13]);
		panel.add(PCIslots);
		// --------------------------
		
		
		
		
		// RAM slots
		String[] RAMslotsStrings = {"","1","2","3","4","5","6"};
		JComboBox RAMslots = new JComboBox(RAMslotsStrings);
		RAMslots.setMaximumSize(tfSize);
		RAMslots.setMinimumSize(tfSize);
		RAMslots.setPreferredSize(tfSize);
		RAMslots.setBackground(Color.WHITE);
		RAMslots.setToolTipText(labels[14].getToolTipText());

		
		int RAMslotsIndex = 0;
		
		for(int i = 1;i<RAMslots.getComponentCount();i++)
		{
				if ( Integer.parseInt(RAMslots.getItemAt(i).toString()) == (mb.getMaxRAMs()) )
				{
					RAMslotsIndex = i;
					i = RAMslots.getComponentCount();
				}
		}
		
		RAMslots.setSelectedIndex(RAMslotsIndex);

		labels[14].setLabelFor(RAMslots);
		
		panel.add(labels[14]);
		panel.add(RAMslots);
		// --------------------------
		
		
		
		
		// USB slots
		String[] USBportsStrings = {"","1","2","3","4","5","6"};
		JComboBox USBports = new JComboBox(USBportsStrings);
		USBports.setMaximumSize(tfSize);
		USBports.setMinimumSize(tfSize);
		USBports.setPreferredSize(tfSize);
		USBports.setBackground(Color.WHITE);
		USBports.setToolTipText(labels[15].getToolTipText());
		
		
		int USBportsIndex = 0;
		
		for(int i = 1;i<USBports.getComponentCount();i++)
		{
				if ( Integer.parseInt(USBports.getItemAt(i).toString()) == (mb.getMaxUSBs()) )
				{
					USBportsIndex = i;
					i = USBports.getComponentCount();
				}
		}
		
		USBports.setSelectedIndex(USBportsIndex);

		labels[15].setLabelFor(USBports);
		
		panel.add(labels[15]);
		panel.add(USBports);
		// --------------------------
		
		
		
		
		// DUC ports
		String[] DUCportsStrings = {"","1","2","3","4","5","6"};
		JComboBox DUCports = new JComboBox(DUCportsStrings);
		DUCports.setMaximumSize(tfSize);
		DUCports.setMinimumSize(tfSize);
		DUCports.setPreferredSize(tfSize);
		DUCports.setBackground(Color.WHITE);
		DUCports.setToolTipText(labels[16].getToolTipText());
		
		
		int DUCportsIndex = 0;
		
		for(int i = 1;i<DUCports.getComponentCount();i++)
		{
				if ( Integer.parseInt(DUCports.getItemAt(i).toString()) == (mb.getMaxUSBs()) )
				{
					DUCportsIndex = i;
					i = DUCports.getComponentCount();
				}
		}
		
		DUCports.setSelectedIndex(DUCportsIndex);

		labels[16].setLabelFor(DUCports);
		
		panel.add(labels[16]);
		panel.add(DUCports);
		// --------------------------
		
		
		
		
		// LAN ports
		String[] LANportsStrings = {"","1","2","3","4","5","6"};
		JComboBox LANports = new JComboBox(LANportsStrings);
		LANports.setMaximumSize(tfSize);
		LANports.setMinimumSize(tfSize);
		LANports.setPreferredSize(tfSize);
		LANports.setBackground(Color.WHITE);
		LANports.setToolTipText(labels[17].getToolTipText());

		
		int LANportsIndex = 0;
		
		for(int i = 1;i<LANports.getComponentCount();i++)
		{
				if ( Integer.parseInt(LANports.getItemAt(i).toString()) == (mb.getMaxUSBs()) )
				{
					LANportsIndex = i;
					i = LANports.getComponentCount();
				}
		}
		
		LANports.setSelectedIndex(LANportsIndex);

		labels[17].setLabelFor(LANports);
		
		panel.add(labels[17]);
		panel.add(LANports);
		// --------------------------
		
		
		
		
		
		
		
		// Lay out the panel.
		SpringUtilities.makeCompactGrid(panel, 6, 6, // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad
		
		
		
		return panel;
	}
}
