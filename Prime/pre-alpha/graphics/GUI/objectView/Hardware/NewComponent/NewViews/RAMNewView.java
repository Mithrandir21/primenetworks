/**
 * 
 */
package graphics.GUI.objectView.Hardware.NewComponent.NewViews;

import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.GUI.SpringUtilities;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareEditor;
import graphics.GUI.objectView.Hardware.HardwareView.Views.HardwareView;
import hardware.Ram;

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
public class RAMNewView extends JFrame implements HardwareView, ActionListener
{
	JTextField name = new JTextField(25);

	JTextArea desc = new JTextArea(3, 40);

	private JTextField producer = new JTextField(7);

	private JComboBox type;

	private JComboBox subtype;

	private JComboBox size;

	private JComboBox speed;


	private Object mainObj;

	private Ram RAMobj;

	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 * @param RAM
	 */
	public RAMNewView(Object obj, Ram RAM)
	{
		super("New RAM");


		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));
		
		
		mainObj = obj;
		RAMobj = RAM;
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

		ImageIcon icon = ImageLocator.getImageIconObject("RAM");
		JPanel p1 = HardwareEditor.GeneralInfo(RAM, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());

		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel p2 = createSpesificInfo(RAMobj);
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
	 * @param ram
	 * @return
	 */
	private JPanel createSpesificInfo(Ram ram)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[5];


		labels[0] = new JLabel("Producer");
		labels[0].setToolTipText("The producer of the ram.");

		labels[1] = new JLabel("Type");
		labels[1].setToolTipText("The ram type.");

		labels[2] = new JLabel("Subtype");
		labels[2].setToolTipText("The ram subtype. (DIMM and so on.)");

		labels[3] = new JLabel("Size");
		labels[3].setToolTipText("The size of the RAM measured in MB.");

		labels[4] = new JLabel("Speed");
		labels[4].setToolTipText("The speed of the RAM measured in Mhz.");


		Dimension tfSize = new Dimension(90, 20);


		// The producer
		labels[0].setLabelFor(producer);
		producer.setMaximumSize(tfSize);
		producer.setPreferredSize(tfSize);
		producer.setText(ram.getProducer());
		producer.setToolTipText(labels[0].getToolTipText());


		panel.add(labels[0]);
		panel.add(producer);


		// The type of the ram
		labels[1].setLabelFor(type);
		String[] typeString = { "", "SDRAM", "DDR", "DDR2", "DDR3" };
		type = new JComboBox(typeString);
		type.setMaximumSize(tfSize);
		type.setPreferredSize(tfSize);
		type.setBackground(Color.WHITE);
		type.setToolTipText(labels[1].getToolTipText());
		type.setActionCommand("Type");
		type.addActionListener(this);

		type.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				typeString, ram.getType()));


		panel.add(labels[1]);
		panel.add(type);



		// The subtype of the ram
		labels[2].setLabelFor(subtype);
		String[] subtypeString = { "", "DIMM", "SO-DIMM" };
		subtype = new JComboBox(subtypeString);
		subtype.setMaximumSize(tfSize);
		subtype.setPreferredSize(tfSize);
		subtype.setBackground(Color.WHITE);
		subtype.setToolTipText(labels[2].getToolTipText());
		subtype.setActionCommand("Subtype");
		subtype.addActionListener(this);

		subtype.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				subtypeString, ram.getSubtype()));


		panel.add(labels[2]);
		panel.add(subtype);



		// The size of the ram
		labels[3].setLabelFor(size);
		String[] sizeString = { "", "128", "256", "512", "1024", "2048", "4096" };
		size = new JComboBox(sizeString);
		size.setMaximumSize(tfSize);
		size.setPreferredSize(tfSize);
		size.setBackground(Color.WHITE);
		size.setToolTipText(labels[3].getToolTipText());
		size.setActionCommand("Subtype");
		size.addActionListener(this);

		size.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				sizeString, ram.getSize()));


		panel.add(labels[3]);
		panel.add(size);



		// The speed of the ram
		labels[4].setLabelFor(speed);
		String[] speedString = { "", "66", "100", "133", "200", "266", "333",
				"400", "466", "500", "533", "667", "800" };
		speed = new JComboBox(speedString);
		speed.setMaximumSize(tfSize);
		speed.setPreferredSize(tfSize);
		speed.setBackground(Color.WHITE);
		speed.setToolTipText(labels[4].getToolTipText());
		speed.setActionCommand("Speed");
		speed.addActionListener(this);

		speed.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				speedString, ram.getSpeed()));


		panel.add(labels[4]);
		panel.add(speed);
		
		
		JLabel temp1 = new JLabel("");
		temp1.setMaximumSize(tfSize);
		temp1.setPreferredSize(tfSize);

		JLabel temp2 = new JLabel("");
		temp2.setMaximumSize(tfSize);
		temp2.setPreferredSize(tfSize);
		

		// adding components so that the layout is right
		panel.add(temp1);
		panel.add(temp2);
		
		
		
		// Lay out the panel.
		SpringUtilities.makeCompactGrid(panel, 2, 6, // rows, cols
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