/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView.Views;


import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.GUI.SpringUtilities;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareEditor;
import hardware.CPU;

import java.awt.Color;
import java.awt.Dimension;
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

import objects.Object;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class CPUView extends JPanel implements HardwareView, ActionListener
{
	JTextField name = new JTextField(25);

	JTextArea desc = new JTextArea(3, 40);

	private JTextField producerField = new JTextField(7);

	private JComboBox socket;

	private JComboBox mhz;

	private JComboBox level1Cache;

	private JComboBox level2Cache;

	private JComboBox nanometer;

	private JComboBox fsb;

	private JCheckBox dualCore;

	private JCheckBox quadCore;

	private JCheckBox bit64;


	private Object mainObj;

	private CPU CPUobj;


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 * @param cpu
	 */
	public CPUView(Object obj, CPU cpu)
	{
		mainObj = obj;
		CPUobj = cpu;
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

		ImageIcon icon = ImageLocator.getImageIconObject("CPU");
		JPanel p1 = HardwareEditor.GeneralInfo(cpu, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 5;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel p2 = createSpesificInfo(cpu);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);
	}

	private JPanel createSpesificInfo(CPU cpu)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[10];


		labels[0] = new JLabel("Producer");
		labels[0].setToolTipText("The producer of the cpu.");

		labels[1] = new JLabel("Socket");
		labels[1].setToolTipText("The socket type.");

		labels[2] = new JLabel("Speed");
		labels[2].setToolTipText("The speed of the CPU measured in Mhz.");

		labels[3] = new JLabel("Level 1 Cache");
		labels[3].setToolTipText("The size of the level 1 cache.");

		labels[4] = new JLabel("Level 2 Cache");
		labels[4].setToolTipText("The size of the level 2 cache.");

		labels[5] = new JLabel("Nanometers");
		labels[5].setToolTipText("The amount of space taken up by a block on the CPU.");

		labels[6] = new JLabel("FSB");
		labels[6].setToolTipText("The fsb, Front Side Bus, of the CPU.");

		labels[7] = new JLabel("Dual Core");
		labels[7].setToolTipText("Whether or not the CPU is dual core.");

		labels[8] = new JLabel("Quad Core");
		labels[8].setToolTipText("Whether or not the CPU is quad core.");

		labels[9] = new JLabel("64 Bit");
		labels[9].setToolTipText("Whether or not the CPU is a 64 Bit CPU.");


		Dimension tfSize = new Dimension(90, 20);



		// The producer
		labels[0].setLabelFor(producerField);
		producerField.setMaximumSize(tfSize);
		producerField.setPreferredSize(tfSize);
		producerField.setText(cpu.getProducer());
		producerField.setToolTipText(labels[0].getToolTipText());


		panel.add(labels[0]);
		panel.add(producerField);



		// The socket
		labels[1].setLabelFor(socket);
		String[] socketsStrings = { "", "Intel 775", "Intel 939", "AMD AM2", "AMD AM2+" };
		socket = new JComboBox(socketsStrings);
		socket.setMaximumSize(tfSize);
		socket.setPreferredSize(tfSize);
		socket.setBackground(Color.WHITE);
		socket.setToolTipText(labels[1].getToolTipText());
		socket.setActionCommand("Socket");
		socket.addActionListener(this);

		socket.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(socketsStrings, cpu
				.getSocket()));


		panel.add(labels[1]);
		panel.add(socket);



		// The mhz
		labels[2].setLabelFor(mhz);
		String[] mhertz = new String[77];
		mhertz[0] = "";

		int zero = 0;

		for ( int i = 1; i < mhertz.length; i++ )
		{
			zero = zero + 50;
			mhertz[i] = "" + zero;
		}

		mhz = new JComboBox(mhertz);
		mhz.setMaximumSize(tfSize);
		mhz.setPreferredSize(tfSize);
		mhz.setBackground(Color.WHITE);
		mhz.setToolTipText(labels[2].getToolTipText());
		mhz.setActionCommand("Speed");
		mhz.addActionListener(this);

		mhz.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(mhertz, cpu.getSpeed()));


		panel.add(labels[2]);
		panel.add(mhz);


		// The level 1 cache
		labels[3].setLabelFor(level1Cache);
		String[] level1CacheStrings = { "", "8", "16", "32", "64" };
		level1Cache = new JComboBox(level1CacheStrings);
		level1Cache.setMaximumSize(tfSize);
		level1Cache.setPreferredSize(tfSize);
		level1Cache.setBackground(Color.WHITE);
		level1Cache.setToolTipText(labels[3].getToolTipText());
		level1Cache.setActionCommand("Level 1 Cache");
		level1Cache.addActionListener(this);

		level1Cache.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(level1CacheStrings, cpu
				.getLevel1CacheSize()));


		panel.add(labels[3]);
		panel.add(level1Cache);


		// The level 2 cache
		labels[4].setLabelFor(level2Cache);
		String[] level2CacheStrings = { "", "8", "16", "32", "64", "128", "256", "512", "1024",
				"2048" };
		level2Cache = new JComboBox(level2CacheStrings);
		level2Cache.setMaximumSize(tfSize);
		level2Cache.setPreferredSize(tfSize);
		level2Cache.setBackground(Color.WHITE);
		level2Cache.setToolTipText(labels[4].getToolTipText());
		level2Cache.setActionCommand("Level 1 Cache");
		level2Cache.addActionListener(this);

		level2Cache.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(level2CacheStrings, cpu
				.getLevel2CacheSize()));


		panel.add(labels[4]);
		panel.add(level2Cache);


		// The nanometer
		labels[5].setLabelFor(nanometer);
		String[] nanometerStrings = { "", "65", "45", "35" };
		nanometer = new JComboBox(nanometerStrings);
		nanometer.setMaximumSize(tfSize);
		nanometer.setPreferredSize(tfSize);
		nanometer.setBackground(Color.WHITE);
		nanometer.setToolTipText(labels[5].getToolTipText());
		nanometer.setActionCommand("Nanometer");
		nanometer.addActionListener(this);

		nanometer.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(nanometerStrings, cpu
				.getNanometer()));


		panel.add(labels[5]);
		panel.add(nanometer);


		// The front side bus
		labels[6].setLabelFor(fsb);
		String[] fsbStrings = { "", "100", "133", "166", "200", "266", "333" };
		fsb = new JComboBox(fsbStrings);
		fsb.setMaximumSize(tfSize);
		fsb.setPreferredSize(tfSize);
		fsb.setBackground(Color.WHITE);
		fsb.setToolTipText(labels[6].getToolTipText());
		fsb.setActionCommand("FSB");
		fsb.addActionListener(this);

		fsb.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(fsbStrings, cpu.getBusSpeed()));


		panel.add(labels[6]);
		panel.add(fsb);


		// The dual core check box
		labels[7].setLabelFor(dualCore);
		dualCore = new JCheckBox();
		dualCore.setToolTipText(labels[7].getToolTipText());
		dualCore.setActionCommand("DualCore");
		dualCore.addActionListener(this);

		dualCore.setSelected(cpu.isDualCore());


		panel.add(labels[7]);
		panel.add(dualCore);


		// The dual quad check box
		labels[8].setLabelFor(quadCore);
		quadCore = new JCheckBox();
		quadCore.setToolTipText(labels[8].getToolTipText());
		quadCore.setActionCommand("QuadCore");
		quadCore.addActionListener(this);

		quadCore.setSelected(cpu.isQuadCore());


		panel.add(labels[8]);
		panel.add(quadCore);


		// The 64 bit check box
		labels[9].setLabelFor(bit64);
		bit64 = new JCheckBox();
		bit64.setMaximumSize(tfSize);
		bit64.setPreferredSize(tfSize);
		bit64.setToolTipText(labels[9].getToolTipText());
		bit64.setActionCommand("64 Bit");
		bit64.addActionListener(this);

		bit64.setSelected(cpu.is64Bit());


		panel.add(labels[9]);
		panel.add(bit64);

		JLabel temp1 = new JLabel("");
		temp1.setMaximumSize(tfSize);
		temp1.setPreferredSize(tfSize);

		JLabel temp2 = new JLabel("");
		temp2.setMaximumSize(tfSize);
		temp2.setPreferredSize(tfSize);

		JLabel temp3 = new JLabel("");
		temp3.setMaximumSize(tfSize);
		temp3.setPreferredSize(tfSize);

		JLabel temp4 = new JLabel("");
		temp4.setMaximumSize(tfSize);
		temp4.setPreferredSize(tfSize);
		// adding components so that the layout is right
		panel.add(temp1);
		panel.add(temp2);
		panel.add(temp3);
		panel.add(temp4);


		// Lay out the panel.
		SpringUtilities.makeCompactGrid(panel, 4, 6, // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad



		return panel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphics.GUI.objectView.Hardware.HardwareView.HardwareView#save()
	 */
	@Override
	public void save()
	{
		if ( name.getText() != "" )
		{
			CPUobj.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			CPUobj.setDescription(desc.getText());
		}

		if ( producerField.getText() != "" )
		{
			CPUobj.setProducer(producerField.getText());
		}

		if ( socket.getSelectedItem().toString() != "" )
		{			
			CPUobj.setSocketType(socket.getSelectedItem().toString());
		}

		if ( mhz.getSelectedItem().toString() != "" )
		{
			CPUobj.setSpeed(Integer.parseInt(mhz.getSelectedItem().toString()));
		}

		if ( level1Cache.getSelectedItem().toString() != "" )
		{
			CPUobj.setLevel1CacheSize(Integer.parseInt(level1Cache.getSelectedItem().toString()));
		}

		if ( level2Cache.getSelectedItem().toString() != "" )
		{
			CPUobj.setLevel2CacheSize(Integer.parseInt(level2Cache.getSelectedItem().toString()));
		}

		if ( nanometer.getSelectedItem().toString() != "" )
		{
			CPUobj.setNanometer(Integer.parseInt(nanometer.getSelectedItem().toString()));
		}

		if ( fsb.getSelectedItem().toString() != "" )
		{
			CPUobj.setFSB(Integer.parseInt(fsb.getSelectedItem().toString()));
		}


		if ( dualCore.isSelected() )
		{
			CPUobj.setCPUcores(2);
		}
		else if ( quadCore.isSelected() )
		{
			CPUobj.setCPUcores(4);
		}

		CPUobj.set64Bit(bit64.isSelected());
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seegraphics.GUI.objectView.Hardware.HardwareView.HardwareView#
	 * validateNecessaryData()
	 */
	@Override
	public boolean validateNecessaryData()
	{
		// Checks the name of the motherboard
		if ( name.getText().length() < 1 || name.getText().length() > 255 )
		{
			JOptionPane.showMessageDialog(this,
					"The motherboard name must be between 1 and 255 characters.", "Error - Name",
					JOptionPane.INFORMATION_MESSAGE);

			return false;
		}

		// Checks the description of the motherboard.
		if ( desc.getText().length() < 1 )
		{
			JOptionPane.showMessageDialog(this,
					"The motherboard description must be longer then 1 character.",
					"Error - Description", JOptionPane.INFORMATION_MESSAGE);

			return false;
		}

		// Possibility for more checks on the CPU values.
		
		return true;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @seegraphics.GUI.objectView.Hardware.HardwareView.HardwareView#
	 * validateChangedData()
	 */
	@Override
	public boolean validateChangedData()
	{
		// TODO Auto-generated method stub
		return false;
	}

	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof JComboBox )
		{
			JComboBox box = (JComboBox) e.getSource();

			String command = box.getActionCommand();
			if ( command.equals("Socket") )
			{
				String msg = "The CPU will no longer be compatiable with the motherboard.\n\nDo you want to keep this change?";

				String[] socketsStrings = { "", "Intel 775", "Intel 939", "AMD AM2", "AMD AM2+" };

				socket = GraphicalFunctions.verifyChange(this, mainObj, CPU.class, CPUobj
						.getSocket(), socket.getSelectedItem().toString(), msg, socketsStrings,
						socket);

			}
		}
		else
		{
			assert (e.getSource() instanceof JCheckBox);
			
			JCheckBox check = (JCheckBox) e.getSource();
			
			String command = check.getActionCommand();
			
			if ( command.equals("DualCore") )
			{
				if ( quadCore.isSelected() )
				{
					String msg = "This CPU is setup as a Quad Core.\n\nDo you wish to change this to a Dual Core?";
					
					int n = JOptionPane.showConfirmDialog(this, msg, "Verify",
							JOptionPane.YES_NO_OPTION);
					
					// If the answer is "No"
					if ( n == 1 )
					{
						dualCore.setSelected(false);
					}
					else
					{
						quadCore.setSelected(false);
						dualCore.setSelected(true);
					}
				}
			}
			else if ( command.equals("QuadCore") )
			{
				if ( dualCore.isSelected() )
				{
					String msg = "This CPU is setup as a Dual Core.\n\nDo you wish to change this to a Quad Core?";
					
					int n = JOptionPane.showConfirmDialog(this, msg, "Verify",
							JOptionPane.YES_NO_OPTION);
					
					// If the answer is "No"
					if ( n == 1 )
					{
						quadCore.setSelected(false);
					}
					else
					{
						dualCore.setSelected(false);
						quadCore.setSelected(true);
					}
				}
			}
			else if( command.equals("64 Bit") )
			{
				if( bit64.isSelected() )
				{
					String msg = "Changing this setting might cause software problems.\n\nDo you wish to keep this change?";
					
					int n = JOptionPane.showConfirmDialog(this, msg, "Verify",
							JOptionPane.YES_NO_OPTION);
					
					if ( n == 1 )
					{
						bit64.setSelected(false);
					}
				}
			}
		}

	}
}