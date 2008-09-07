/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView;

import graphics.ImageLocator;
import graphics.GUI.objectView.Hardware.HardwareEditor;
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
	
	JTextArea desc = new JTextArea(3,40);
	
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
	
	
	Object mainObj;
	
	CPU CPUobj;
	
	
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
		JPanel p1 = HardwareEditor.GeneralInfo(cpu,icon,name,desc);
		p1.setBorder(BorderFactory.createEtchedBorder());
		
		
		this.add(p1,c);
		
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 5;
		c.insets = new Insets(0, 10, 10, 10);
		
		JPanel p2 = createSpesificInfo(cpu);
		p2.setBorder(BorderFactory.createEtchedBorder());
		
		this.add(p2,c);
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
		
		
		// The socket
		String[] socketsStrings = { "", "Intel 775", "Intel 939", "AMD AM2", "AMD AM2+" };
		socket = new JComboBox(socketsStrings);
		socket.setMaximumSize(tfSize);
		socket.setPreferredSize(tfSize);
		socket.setBackground(Color.WHITE);
		socket.setToolTipText(labels[1].getToolTipText());
		socket.setActionCommand("Socket");
		socket.addActionListener(this);
		
		
		// The mhz
		String[] mhertz = new String[77];
		mhertz[0] = "";
		
		int zero = 0;
		
		for(int i = 1;i<mhertz.length;i++)
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

		
		// The level 1 cache
		String[] level1CacheStrings = { "", "8", "16", "32", "64"};
		level1Cache = new JComboBox(level1CacheStrings);
		level1Cache.setMaximumSize(tfSize);
		level1Cache.setPreferredSize(tfSize);
		level1Cache.setBackground(Color.WHITE);
		level1Cache.setToolTipText(labels[3].getToolTipText());
		level1Cache.setActionCommand("Level 1 Cache");
		level1Cache.addActionListener(this);
		
		
		// The level 2 cache
		String[] level2CacheStrings = { "", "8", "16", "32", "64", "128", "256", "512", "1024", "2048"};
		level2Cache = new JComboBox(level2CacheStrings);
		level2Cache.setMaximumSize(tfSize);
		level2Cache.setPreferredSize(tfSize);
		level2Cache.setBackground(Color.WHITE);
		level2Cache.setToolTipText(labels[4].getToolTipText());
		level2Cache.setActionCommand("Level 1 Cache");
		level2Cache.addActionListener(this);
		
		
		// The nanometer
		String[] nanometerStrings = { "", "65", "45", "35"};
		nanometer = new JComboBox(nanometerStrings);
		nanometer.setMaximumSize(tfSize);
		nanometer.setPreferredSize(tfSize);
		nanometer.setBackground(Color.WHITE);
		nanometer.setToolTipText(labels[3].getToolTipText());
		nanometer.setActionCommand("Nanometer");
		nanometer.addActionListener(this);
		
		
		// The front side bus
		String[] fsbStrings = { "", "100", "133", "166", "200", "266", "333"};
		nanometer = new JComboBox(nanometerStrings);
		nanometer.setMaximumSize(tfSize);
		nanometer.setPreferredSize(tfSize);
		nanometer.setBackground(Color.WHITE);
		nanometer.setToolTipText(labels[3].getToolTipText());
		nanometer.setActionCommand("Nanometer");
		nanometer.addActionListener(this);
		
		
		return panel;
	}

	/* (non-Javadoc)
	 * @see graphics.GUI.objectView.Hardware.HardwareView.HardwareView#save()
	 */
	@Override
	public void save()
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see graphics.GUI.objectView.Hardware.HardwareView.HardwareView#validateNecessaryData()
	 */
	@Override
	public boolean validateNecessaryData()
	{
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see graphics.GUI.objectView.Hardware.HardwareView.HardwareView#validateChangedData()
	 */
	@Override
	public boolean validateChangedData()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
