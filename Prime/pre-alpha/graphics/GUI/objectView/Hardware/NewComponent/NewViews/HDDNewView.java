/**
 * 
 */
package graphics.GUI.objectView.Hardware.NewComponent.NewViews;

import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.GUI.SpringUtilities;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareEditor;
import graphics.GUI.objectView.Hardware.HardwareView.Views.HardwareView;
import hardware.HDD;

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
public class HDDNewView extends JFrame implements HardwareView, ActionListener
{
	JTextField name = new JTextField(25);

	JTextArea desc = new JTextArea(3, 40);

	private JTextField producer = new JTextField(7);

	private JComboBox type;

	private JComboBox subtype;

	private JComboBox size;

	private JComboBox transferSpeed;

	private JComboBox rpm;


	private Object mainObj;

	private HDD mainHDD;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 * @param hdd
	 */
	public HDDNewView(Object obj, HDD hdd)
	{
		super("New HDD");


		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));
		
		
		
		mainObj = obj;
		mainHDD = hdd;
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

		ImageIcon icon = ImageLocator.getImageIconObject("Harddisc");
		JPanel p1 = HardwareEditor.GeneralInfo(hdd, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());





		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel p2 = createSpesificInfo(hdd);
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


	private JPanel createSpesificInfo(HDD hdd)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[6];

		labels[0] = new JLabel("Producer");
		labels[0].setToolTipText("The producer of the hdd.");

		labels[1] = new JLabel("Type");
		labels[1].setToolTipText("The hdd type.");

		labels[2] = new JLabel("Subtype");
		labels[2].setToolTipText("The hdd subtype. (ATA-100, SATA-150 and so on.)");

		labels[3] = new JLabel("Size");
		labels[3].setToolTipText("The size of the HDD measured in GB.");

		labels[4] = new JLabel("Speed");
		labels[4].setToolTipText("The transfer speed of the HDD measured in MB.");

		labels[5] = new JLabel("RPM");
		labels[5].setToolTipText("The number of disk rotations per minute.");


		Dimension tfSize = new Dimension(90, 20);


		// The producer
		labels[0].setLabelFor(producer);
		producer.setMaximumSize(tfSize);
		producer.setPreferredSize(tfSize);
		producer.setText(hdd.getProducer());
		producer.setToolTipText(labels[0].getToolTipText());


		panel.add(labels[0]);
		panel.add(producer);


		// The type of the hdd
		labels[1].setLabelFor(type);
		String[] typeString = { "", "IDE", "SATA", "USB" };
		type = new JComboBox(typeString);
		type.setMaximumSize(tfSize);
		type.setPreferredSize(tfSize);
		type.setBackground(Color.WHITE);
		type.setToolTipText(labels[1].getToolTipText());
		type.setActionCommand("Type");
		type.addActionListener(this);

		type.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(typeString, hdd.getType()));


		panel.add(labels[1]);
		panel.add(type);



		// The subtype of the hdd
		labels[2].setLabelFor(subtype);
		String[] subtypeString = { "", "ATA-100", "ATA-133", "SATA-150", "SATA-300", "USBv1",
				"USBv2", "eSATA" };
		subtype = new JComboBox(subtypeString);
		subtype.setMaximumSize(tfSize);
		subtype.setPreferredSize(tfSize);
		subtype.setBackground(Color.WHITE);
		subtype.setToolTipText(labels[2].getToolTipText());
		subtype.setActionCommand("Subtype");
		subtype.addActionListener(this);

		subtype.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(subtypeString, hdd
				.getSubtype()));


		panel.add(labels[2]);
		panel.add(subtype);



		// The size of the hdd
		labels[3].setLabelFor(size);
		String[] sizeString = new String[29];
		sizeString[0] = "";

		int hddSize = 0;

		for ( int i = 1; i < sizeString.length; i++ )
		{
			if ( hddSize < 200 )
			{
				hddSize = hddSize + 10;
				sizeString[i] = "" + hddSize;
			}
			else
			{
				hddSize = hddSize + 100;
				sizeString[i] = "" + hddSize;
			}
		}

		size = new JComboBox(sizeString);
		size.setMaximumSize(tfSize);
		size.setPreferredSize(tfSize);
		size.setBackground(Color.WHITE);
		size.setToolTipText(labels[3].getToolTipText());
		size.setActionCommand("size");
		size.addActionListener(this);

		size.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(sizeString, hdd.getSize()));

		panel.add(labels[3]);
		panel.add(size);



		// The transfer speed of the hdd
		labels[4].setLabelFor(transferSpeed);
		String[] transSpeedString = { "", "16", "33", "66", "80", "133", "150", "300", "600" };
		transferSpeed = new JComboBox(typeString);
		transferSpeed.setMaximumSize(tfSize);
		transferSpeed.setPreferredSize(tfSize);
		transferSpeed.setBackground(Color.WHITE);
		transferSpeed.setToolTipText(labels[1].getToolTipText());
		transferSpeed.setActionCommand("Speed");
		transferSpeed.addActionListener(this);

		transferSpeed.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(transSpeedString, hdd
				.getSpeed()));


		panel.add(labels[4]);
		panel.add(transferSpeed);



		// The rpm of the hdd
		labels[5].setLabelFor(rpm);
		String[] rpmString = { "", "5400", "7200", "10000", "15000" };
		rpm = new JComboBox(rpmString);
		rpm.setMaximumSize(tfSize);
		rpm.setPreferredSize(tfSize);
		rpm.setBackground(Color.WHITE);
		rpm.setToolTipText(labels[1].getToolTipText());
		rpm.setActionCommand("Speed");
		rpm.addActionListener(this);

		rpm.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(rpmString, hdd.getRPM()));


		panel.add(labels[5]);
		panel.add(rpm);


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