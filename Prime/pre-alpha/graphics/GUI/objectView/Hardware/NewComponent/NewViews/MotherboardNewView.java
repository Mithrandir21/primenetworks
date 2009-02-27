/**
 * 
 */
package graphics.GUI.objectView.Hardware.NewComponent.NewViews;


import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Hardware.HardwareViewInterface;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareEditor;

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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import actions.graphicalActions.WorkareaCanvasActions;

import objects.Object;
import objects.hardwareObjects.Motherboard;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class MotherboardNewView extends JFrame implements
		HardwareViewInterface, ActionListener
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
	public MotherboardNewView(Object obj, Motherboard mb)
	{
		super("New Motherboard");


		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));




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

		ImageIcon icon = ImageLocator.getImageIconObject("Motherboard");
		JPanel p1 = HardwareEditor.GeneralInfo(mb, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel p2 = createSpesificInfo(mb);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);



		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 0.01;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel buttons = createButtons();
		buttons.setBorder(BorderFactory.createEtchedBorder());

		this.add(buttons, c);




		this.setMinimumSize(new Dimension((int) scrnsize.getWidth() / 3,
				(int) scrnsize.getHeight() / 3));
		this.setSize(width, height);
		this.setVisible(true);
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


		labels[0] = new JLabel("Producer");
		labels[0].setToolTipText("The producer of the motherboard.");

		labels[1] = new JLabel("Form");
		labels[1]
				.setToolTipText("The form of the motherboard, ie. the dimensions.");

		labels[2] = new JLabel("Socket");
		labels[2]
				.setToolTipText("The socket on the motherboard which holds the CPU.");

		labels[3] = new JLabel("Bus Speed");
		labels[3]
				.setToolTipText("The bus speed, the speed of information transfer, on the motherboard.");

		labels[4] = new JLabel("Chipset");
		labels[4].setToolTipText("The chipset on the motherboard.");

		labels[5] = new JLabel("GPU Port");
		labels[5].setToolTipText("The graphics card port on the motherboard.");

		labels[6] = new JLabel("Connection Port");
		labels[6]
				.setToolTipText("The ports that are for connections to harddiscs and cdrom.");

		labels[7] = new JLabel("Ram Type");
		labels[7].setToolTipText("The ram port on the motherboard.");

		labels[8] = new JLabel("Audiocard Integrated");
		labels[8]
				.setToolTipText("Whether or not a audio card i integrated in the motherboard.");

		labels[9] = new JLabel("GPU integrated");
		labels[9]
				.setToolTipText("Whether or not a graphics card i integrated in the motherboard.");

		labels[10] = new JLabel("NIC integrated");
		labels[10]
				.setToolTipText("Whether or not a LAN card i integrated in the motherboard.");

		labels[11] = new JLabel("GPU installed");
		labels[11]
				.setToolTipText("Whether or not a graphical card is installed on the motherboard. Regardless of the status of the integrated GPU.");

		labels[12] = new JLabel("CPU socket");
		labels[12]
				.setToolTipText("The number of CPU sockets on the motherboard.");

		labels[13] = new JLabel("PCI slots");
		labels[13]
				.setToolTipText("The number of PCI slots on the motherboard.");

		labels[14] = new JLabel("RAM slots");
		labels[14]
				.setToolTipText("The number of RAM slots on the motherboard.");

		labels[15] = new JLabel("USB ports");
		labels[15]
				.setToolTipText("The number of USB ports on the motherboard.");

		labels[16] = new JLabel("DUC slots");
		labels[16]
				.setToolTipText("The number of connection ports (SATA or IDE) on the motherboard.");

		labels[17] = new JLabel("LAN ports");
		labels[17]
				.setToolTipText("The number of LAN ports on the motherboard.");



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
		String[] CPUsocketsStrings = { "", "1", "2", "3", "4", "5", "6" };
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
		String[] PCIslotsStrings = { "", "1", "2", "3", "4", "5", "6" };
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
		String[] RAMslotsStrings = { "", "1", "2", "3", "4", "5", "6" };
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
		String[] USBportsStrings = { "", "1", "2", "3", "4", "5", "6" };
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
		String[] DUCportsStrings = { "", "1", "2", "3", "4", "5", "6" };
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
					.getMaxUSBs()) )
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
		String[] LANportsStrings = { "", "1", "2", "3", "4", "5", "6" };
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
					.getMaxLANs()) )
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



	/**
	 * Creates a JPanel with two buttons that are listened for by
	 * actionlisteners.
	 */
	private JPanel createButtons()
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));


		JButton save = new JButton("Save");
		save.addActionListener(this);
		save.setActionCommand("save");

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(cancel);

		return buttons;
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof JButton )
		{
			if ( e.getActionCommand().equals("save") )
			{
				int answer = JOptionPane
						.showConfirmDialog(
								this,
								"By saving a new motherboard ALL the machines other components will be removed. Do you wish to do this?",
								"Verify", JOptionPane.YES_NO_OPTION);


				// If the answer is not No.
				if ( answer != 1 )
				{
					// Saves the current values of the new motherboard.
					save();

					
					// We have to remove all connection between this object and
					// other objects on the canvas
					WorkareaCanvasActions.removeAllConnectionsToFromObject(
							PrimeMain1.currentCanvas, mainObj);

					
					// Since the motherboard is where most of the connections
					// are
					// placed we first have to remove all connections to the
					// devices.
					mainObj.removeAllConnections();

					
					// Then we have to remove all the components that a object
					// contains.
					mainObj.removeAllComponents();

					
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


					// Closes the JFrame.
					this.dispose();
				}

			}
			else
			{
				assert (e.getActionCommand().equals("cancel"));

				this.dispose();
			}
		}



	}


	/*
	 * (non-Javadoc)
	 * @see
	 * graphics.GUI.objectView.Hardware.NewComponent.NewViews.HardwareNewView
	 * #save()
	 */
	@Override
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

		if ( producerField.getText() != "" )
		{
			mbObj.setProducer(producerField.getText());
		}

		if ( forms.getSelectedItem().toString() != "" )
		{
			mbObj.setForm(forms.getSelectedItem().toString());
		}

		if ( sockets.getSelectedItem().toString() != "" )
		{
			mbObj.setSocket(sockets.getSelectedItem().toString());
		}

		if ( busSpeeds.getSelectedItem().toString() != "" )
		{
			mbObj.setBusSpeed(Integer.parseInt(busSpeeds.getSelectedItem()
					.toString()));
		}
		
		if ( chipsetField.getText() != "" )
		{
			mbObj.setChipset(chipsetField.getText());
		}

		if ( gpuPorts.getSelectedItem().toString() != "" )
		{
			mbObj.setGraphicalPort(gpuPorts.getSelectedItem().toString());
		}
		
		if ( true )
		{
			mbObj.setDUCconnectionType(DUCPorts.getSelectedItem().toString());
		}

		if ( RAMPorts.getSelectedItem().toString() != "" )
		{
			mbObj.setRAMtype(RAMPorts.getSelectedItem().toString());
		}
		

		mbObj.setIntegAudioCard(intAudioCard.isSelected());
		mbObj.setIntegGraphicalCard(intGPU.isSelected());
		mbObj.setIntegLANcard(intNIC.isSelected());
		mbObj.setGraphicsCard(GPUinstalled.isSelected());

		
		if ( CPUsockets.getSelectedItem().toString() != "" )
		{
			mbObj.setMaxCPUs(Integer.parseInt(CPUsockets.getSelectedItem()
					.toString()));
		}

		if ( PCIslots.getSelectedItem().toString() != "" )
		{
			mbObj.setMaxPCIs(Integer.parseInt(PCIslots.getSelectedItem()
					.toString()));
		}
		
		if ( RAMslots.getSelectedItem().toString() != "" )
		{
			mbObj.setMaxRAMs(Integer.parseInt(RAMslots.getSelectedItem()
					.toString()));
		}

		if ( USBports.getSelectedItem().toString() != "" )
		{
			mbObj.setMaxUSBs(Integer.parseInt(USBports.getSelectedItem()
					.toString()));
		}

		if ( DUCports.getSelectedItem().toString() != "" )
		{
			mbObj.setMaxDUCs(Integer.parseInt(DUCports.getSelectedItem()
					.toString()));
		}

		if ( LANports.getSelectedItem().toString() != "" )
		{
			mbObj.setMaxIntegratedLANs(Integer.parseInt(LANports
					.getSelectedItem().toString()));
		}

	}


	@Override
	public boolean validateNecessaryData()
	{
		return true;
	}
}
