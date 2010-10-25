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

import managment.ComponentsManagment;
import objects.Hardware;
import objects.Object;
import objects.hardwareObjects.CPU;
import objects.hardwareObjects.Motherboard;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class CPUNewView extends JFrame implements HardwareViewInterface,
		ActionListener
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
	 * A constructor for this class that will create a new JFrame which will
	 * contain fields for the adjustment of the newly created CPU hardware that
	 * will, if saved, be added to the hardware list of the given Object.
	 * 
	 * @param obj
	 *            The Object that the given hardware will be added to, if saved.
	 * @param cpu
	 *            The Hardware object that can be adjusted and then saved.
	 */
	public CPUNewView(Object obj, CPU cpu)
	{
		super(PrimeMain1.texts.getString("newHWnewCPULabel"));

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));

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
		JPanel p1 = HardwareEditor.GeneralInfo(CPUobj, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel p2 = createSpesificInfo(CPUobj);
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
	 * @param cpu
	 *            The hardware that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(CPU cpu)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[10];


		labels[0] = new JLabel(PrimeMain1.texts
				.getString("cpuViewProducerLabel"));
		labels[0].setToolTipText(PrimeMain1.texts
				.getString("cpuViewProducerTip"));

		labels[1] = new JLabel(PrimeMain1.texts.getString("cpuViewSocketLabel"));
		labels[1]
				.setToolTipText(PrimeMain1.texts.getString("cpuViewSocketTip"));

		labels[2] = new JLabel(PrimeMain1.texts.getString("cpuViewSpeedLabel"));
		labels[2].setToolTipText(PrimeMain1.texts.getString("cpuViewSpeedTip"));

		labels[3] = new JLabel(PrimeMain1.texts
				.getString("cpuViewLevel1CacheLabel"));
		labels[3].setToolTipText(PrimeMain1.texts
				.getString("cpuViewLevel1CacheTip"));

		labels[4] = new JLabel(PrimeMain1.texts
				.getString("cpuViewLevel2CacheLabel"));
		labels[4].setToolTipText(PrimeMain1.texts
				.getString("cpuViewLevel2CacheTip"));

		labels[5] = new JLabel(PrimeMain1.texts
				.getString("cpuViewNanometersLabel"));
		labels[5].setToolTipText(PrimeMain1.texts
				.getString("cpuViewNanometersTip"));

		labels[6] = new JLabel(PrimeMain1.texts.getString("cpuViewFSBLabel"));
		labels[6].setToolTipText(PrimeMain1.texts.getString("cpuViewFSBTip"));

		labels[7] = new JLabel(PrimeMain1.texts
				.getString("cpuViewDualCoreLabel"));
		labels[7].setToolTipText(PrimeMain1.texts
				.getString("cpuViewDualCoreTip"));

		labels[8] = new JLabel(PrimeMain1.texts
				.getString("cpuViewQuadCoreLabel"));
		labels[8].setToolTipText(PrimeMain1.texts
				.getString("cpuViewQuadCoreTip"));

		labels[9] = new JLabel(PrimeMain1.texts.getString("cpuView64BitLabel"));
		labels[9].setToolTipText(PrimeMain1.texts.getString("cpuView64BitTip"));

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
		String[] socketsStrings = { "", "Intel 775", "Intel 939", "AMD AM2",
				"AMD AM2+" };
		socket = new JComboBox(socketsStrings);
		socket.setMaximumSize(tfSize);
		socket.setPreferredSize(tfSize);
		socket.setBackground(Color.WHITE);
		socket.setToolTipText(labels[1].getToolTipText());
		socket.setActionCommand("Socket");
		socket.addActionListener(this);

		socket.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				socketsStrings, cpu.getSocket()));


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

		mhz.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(mhertz, cpu
				.getSpeed()));


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

		level1Cache.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				level1CacheStrings, cpu.getLevel1CacheSize()));


		panel.add(labels[3]);
		panel.add(level1Cache);


		// The level 2 cache
		labels[4].setLabelFor(level2Cache);
		String[] level2CacheStrings = { "", "8", "16", "32", "64", "128",
				"256", "512", "1024", "2048" };
		level2Cache = new JComboBox(level2CacheStrings);
		level2Cache.setMaximumSize(tfSize);
		level2Cache.setPreferredSize(tfSize);
		level2Cache.setBackground(Color.WHITE);
		level2Cache.setToolTipText(labels[4].getToolTipText());
		level2Cache.setActionCommand("Level 1 Cache");
		level2Cache.addActionListener(this);

		level2Cache.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				level2CacheStrings, cpu.getLevel2CacheSize()));


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

		nanometer.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				nanometerStrings, cpu.getNanometer()));


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

		fsb.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(fsbStrings,
				cpu.getBusSpeed()));


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


		Button save = new Button(PrimeMain1.texts.getString("save"));
		save.addActionListener(this);
		save.setActionCommand("save");

		Button cancel = new Button(PrimeMain1.texts.getString("cancel"));
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(cancel);

		return buttons;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			// Saves the current values of the new motherboard.
			save();

			ComponentsManagment.processCPUmatch(mainObj, (Motherboard) mainObj
					.getComponents()[0], CPUobj, this);


			// Updates the views of the object to correctly show the
			// current info.
			ObjectView view = PrimeMain1.getObjectView(mainObj);
			if ( view != null )
			{
				view.updateViewInfo();
			}
			// If no view is returned, then the standard object view is open
			// and that should be updated.
			else if ( PrimeMain1.stdObjView != null )
			{
				PrimeMain1.stdObjView.getSplitView().getHardStdObjView()
						.updateTabInfo();
			}


			// Closes the JFrame.
			this.dispose();

		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
		}

	}


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

		CPUobj.setProducer(producerField.getText());

		CPUobj.setSocketType(socket.getSelectedItem().toString());


		if ( mhz.getSelectedItem().toString() != "" )
		{
			CPUobj.setSpeed(Integer.parseInt(mhz.getSelectedItem().toString()));
		}
		else
		{
			CPUobj.setSpeed(0);
		}


		if ( level1Cache.getSelectedItem().toString() != "" )
		{
			CPUobj.setLevel1CacheSize(Integer.parseInt(level1Cache
					.getSelectedItem().toString()));
		}
		else
		{
			CPUobj.setLevel1CacheSize(0);
		}


		if ( level2Cache.getSelectedItem().toString() != "" )
		{
			CPUobj.setLevel2CacheSize(Integer.parseInt(level2Cache
					.getSelectedItem().toString()));
		}
		else
		{
			CPUobj.setLevel2CacheSize(0);
		}


		if ( nanometer.getSelectedItem().toString() != "" )
		{
			CPUobj.setNanometer(Integer.parseInt(nanometer.getSelectedItem()
					.toString()));
		}
		else
		{
			CPUobj.setNanometer(0);
		}


		if ( fsb.getSelectedItem().toString() != "" )
		{
			CPUobj.setFSB(Integer.parseInt(fsb.getSelectedItem().toString()));
		}
		else
		{
			CPUobj.setFSB(0);
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


	@Override
	public boolean validateNecessaryData()
	{
		return true;
	}


	@Override
	public Hardware getViewHardware()
	{
		return CPUobj;
	}

}
