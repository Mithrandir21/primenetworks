/**
 * 
 */
package graphics.GUI.objectView.Hardware.NewComponent.NewViews;

import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.GUI.SpringUtilities;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareEditor;
import graphics.GUI.objectView.Hardware.HardwareView.Views.HardwareView;
import hardware.GraphicsCard;

import java.awt.Button;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
 * 
 */
public class GraphicsCardNewView extends JFrame implements HardwareView, ActionListener
{
	JTextField name = new JTextField(25);

	JTextArea desc = new JTextArea(3, 40);
	
	private JTextField producer = new JTextField(7);

	private JComboBox type;
	
	private JComboBox outputInterface;
	
	private JComboBox size;
	
	private JComboBox speed;
	
	private JComboBox maxMonitors;
	
	private JCheckBox isIntegrated;
	
	
	private Object mainObj;
	
	private GraphicsCard mainGC;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 * @param GPU
	 */
	public GraphicsCardNewView(Object obj, GraphicsCard GPU)
	{
		super("New GPU");


		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));
		
		
		
		mainObj = obj;
		mainGC = GPU;
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

		ImageIcon icon = ImageLocator.getImageIconObject("GPU");
		JPanel p1 = HardwareEditor.GeneralInfo(GPU, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());





		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel p2 = new JPanel();
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
	 * Javadoc-TODO - Description
	 * 
	 * @param GPU
	 * @return
	 */
	private JPanel createSpesificInfo(GraphicsCard GPU)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[7];
		
		labels[0] = new JLabel("Producer");
		labels[0].setToolTipText("The producer of the GPU.");

		labels[1] = new JLabel("Interface");
		labels[1].setToolTipText("The GPU interface. (PCI, AGP, PCI-E)");

		labels[2] = new JLabel("Output Port");
		labels[2].setToolTipText("The GPU output port. ");
		
		labels[3] = new JLabel("Size");
		labels[3].setToolTipText("The size of the RAM on the GPU.");
		
		labels[4] = new JLabel("Speed");
		labels[4].setToolTipText("The speed of the GPU in MHz.");
		
		labels[5] = new JLabel("Max Monitors");
		labels[5].setToolTipText("The number of output ports.");

		labels[6] = new JLabel("Is Integrated");
		labels[6].setToolTipText("If the GPU is integrated on the mainboard.");


		Dimension tfSize = new Dimension(90, 20);
		
		// The producer
		labels[0].setLabelFor(producer);
		producer.setMaximumSize(tfSize);
		producer.setPreferredSize(tfSize);
		producer.setText(GPU.getProducer());
		producer.setToolTipText(labels[0].getToolTipText());


		panel.add(labels[0]);
		panel.add(producer);


		// The type of the GPU
		labels[1].setLabelFor(type);
		String[] typeString = { "", "PCI", "AGP", "PCI-E" };
		type = new JComboBox(typeString);
		type.setMaximumSize(tfSize);
		type.setPreferredSize(tfSize);
		type.setBackground(Color.WHITE);
		type.setToolTipText(labels[1].getToolTipText());
		type.setActionCommand("Interface");
		type.addActionListener(this);

		type.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				typeString, GPU.getType()));


		panel.add(labels[1]);
		panel.add(type);

		
		
		// The output port of the GPU
		labels[2].setLabelFor(outputInterface);
		String[] portString = { "", "VGA", "DVI",};
		outputInterface = new JComboBox(portString);
		outputInterface.setMaximumSize(tfSize);
		outputInterface.setPreferredSize(tfSize);
		outputInterface.setBackground(Color.WHITE);
		outputInterface.setToolTipText(labels[2].getToolTipText());
		outputInterface.setActionCommand("Port");
		outputInterface.addActionListener(this);

		outputInterface.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				typeString, GPU.getOutputInterface()));


		panel.add(labels[2]);
		panel.add(outputInterface);

		
		
		// The size of the GPU
		labels[3].setLabelFor(size);
		String[] sizeString = { "", "Size1", "Size2", "Size3" };
		size = new JComboBox(portString);
		size.setMaximumSize(tfSize);
		size.setPreferredSize(tfSize);
		size.setBackground(Color.WHITE);
		size.setToolTipText(labels[3].getToolTipText());
		size.setActionCommand("Size");
		size.addActionListener(this);

		size.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				typeString, GPU.getSize()));


		panel.add(labels[3]);
		panel.add(size);
		
		
		
		// The speed of the GPU
		labels[4].setLabelFor(speed);
		String[] speedString = { "", "speed1", "Speed2", "Speed3" };
		speed = new JComboBox(speedString);
		speed.setMaximumSize(tfSize);
		speed.setPreferredSize(tfSize);
		speed.setBackground(Color.WHITE);
		speed.setToolTipText(labels[4].getToolTipText());
		speed.setActionCommand("Speed");
		speed.addActionListener(this);

		speed.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				typeString, GPU.getSpeed()));


		panel.add(labels[4]);
		panel.add(speed);		
		
		
		
		// The speed of the GPU
		labels[5].setLabelFor(maxMonitors);
		String[] monitorsString = { "", "1", "2", "3" };
		maxMonitors = new JComboBox(monitorsString);
		maxMonitors.setMaximumSize(tfSize);
		maxMonitors.setPreferredSize(tfSize);
		maxMonitors.setBackground(Color.WHITE);
		maxMonitors.setToolTipText(labels[5].getToolTipText());
		maxMonitors.setActionCommand("MaxMonitors");
		maxMonitors.addActionListener(this);

		maxMonitors.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				typeString, GPU.getMaxMonitors()));


		panel.add(labels[5]);
		panel.add(maxMonitors);	
		
		
		// The dual quad check box
		labels[6].setLabelFor(isIntegrated);
		isIntegrated = new JCheckBox();
		isIntegrated.setToolTipText(labels[8].getToolTipText());
		isIntegrated.setActionCommand("Integrated");
		isIntegrated.addActionListener(this);

		isIntegrated.setSelected(GPU.getIsIntegrated());


		panel.add(labels[6]);
		panel.add(isIntegrated);
		
		
		// Lay out the panel.
		SpringUtilities.makeCompactGrid(panel, 4, 6, // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad
		
		
		return panel;
	}
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @return
	 */
	private JPanel createButtons()
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		
		Button save = new Button("Save");
		save.addActionListener(this);
		save.setActionCommand("save");
		
		Button cancel = new Button("Cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		
		
		buttons.add(save);
		buttons.add(cancel);
		
		return buttons;
	}
	

	@Override
	public void save()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateChangedData()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateNecessaryData()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
}
