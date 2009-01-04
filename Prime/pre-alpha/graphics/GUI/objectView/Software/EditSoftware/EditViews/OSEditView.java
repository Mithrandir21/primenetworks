package graphics.GUI.objectView.Software.EditSoftware.EditViews;


import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.GUI.objectView.Software.SoftwareView;
import graphics.GUI.objectView.Software.EditSoftware.EditOverview.SoftwareEditor;

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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import objects.Object;
import objects.Software;
import software.OperatingSystem;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link OperatingSystem OperatingSystem} Software. The
 * panel is made up of 3 JPanel ordered in a column. The first one contains the
 * name and description of the object. The second panel contains the specific
 * software options. The third panel contains the button that can remove the
 * software from the computer.
 * 
 * @author Bahram Malaekeh
 */
public class OSEditView extends JPanel implements SoftwareView,
		ActionListener
{
	// The name of the software object
	JTextField name = new JTextField(25);

	// The description of the software object.
	JTextArea desc = new JTextArea(3, 40);

	// Supported File systems
	private JList supportedFS;

	private String[] fsData;

	// Has encrypted filesystem
	private JCheckBox encryptedFileSystem;

	// Has GUI
	private JCheckBox hasGUI;

	// The OS is 64 bit
	private JCheckBox is64bit;


	private Object mainObj;

	private OperatingSystem mainOS;


	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param OS
	 *            The {@link OperatingSystem OperatingSystem} software.
	 */
	public OSEditView(Object obj, OperatingSystem OS)
	{
		mainObj = obj;
		mainOS = OS;
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
		JPanel p1 = SoftwareEditor.GeneralInfo(mainOS, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(mainOS);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);



		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel("Remove this component from this software");

		Button remove = new Button("Remove Software");
		remove.addActionListener(this);
		remove.setActionCommand("removeSoft");

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
	 * Creates the JPanel that will contain the {@link Software Software}
	 * specific options. The layout of the returned panel will be
	 * {@link SpringLayout}.
	 */
	private JPanel createSpesificInfo(OperatingSystem OS)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[4];


		labels[0] = new JLabel("Supported FS");
		labels[0].setToolTipText("The supported file systems by the OS.");

		labels[1] = new JLabel("Supports Encrypted FS");
		labels[1]
				.setToolTipText("Whether or not the OS supports an encrypted file system.");

		labels[2] = new JLabel("Has GUI");
		labels[2].setToolTipText("Whether or not the OS has a GUI.");

		labels[3] = new JLabel("64-bit");
		labels[3].setToolTipText("Whether or not the OS is 64-Bit.");

		
		int childrenCount = 0;
		Dimension tfSize = new Dimension(90, 20);



		// The supported file system.
		labels[0].setLabelFor(supportedFS);
		String[] listData = { "FAT16", "FAT32", "NTFS", "EXT", "EXT2", "EXT3",
				"EXT4", "ReiserFS", "Reiser4", "ZFS", "XFS" };
		supportedFS = new JList(listData);
		ListSelectionModel listSelectionModel = supportedFS.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(supportedFS);
		listPane.setMaximumSize(new Dimension(160, 60));
		listPane.setPreferredSize(new Dimension(160, 60));
		listSelectionModel
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		if ( mainOS.getSupportedFS() != null )
		{
			if ( mainOS.getSupportedFS().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedFS, listData, mainOS.getSupportedFS()));
			}
		}

		panel.add(labels[0]);
		panel.add(listPane);
		childrenCount = childrenCount+2;



		// Whether or not the OS supports encrypted file system
		labels[1].setLabelFor(encryptedFileSystem);
		encryptedFileSystem = new JCheckBox();
		encryptedFileSystem.setMaximumSize(tfSize);
		encryptedFileSystem.setPreferredSize(tfSize);
		encryptedFileSystem.setToolTipText(labels[1].getToolTipText());
		encryptedFileSystem.setActionCommand("encFS");
		encryptedFileSystem.addActionListener(this);

		encryptedFileSystem.setSelected(mainOS.isEncryptedFileSystem());


		panel.add(labels[1]);
		panel.add(encryptedFileSystem);
		childrenCount = childrenCount+2;



		// Whether or not the OS has a GUI
		labels[2].setLabelFor(hasGUI);
		hasGUI = new JCheckBox();
		hasGUI.setMaximumSize(tfSize);
		hasGUI.setPreferredSize(tfSize);
		hasGUI.setToolTipText(labels[2].getToolTipText());
		hasGUI.setActionCommand("GUI");
		hasGUI.addActionListener(this);

		hasGUI.setSelected(mainOS.isHasGUI());


		panel.add(labels[2]);
		panel.add(hasGUI);
		childrenCount = childrenCount+2;




		// The 64 bit check box
		labels[3].setLabelFor(is64bit);
		is64bit = new JCheckBox();
		is64bit.setMaximumSize(tfSize);
		is64bit.setPreferredSize(tfSize);
		is64bit.setToolTipText(labels[3].getToolTipText());
		is64bit.setActionCommand("64bit");
		is64bit.addActionListener(this);

		is64bit.setSelected(mainOS.isIs64bit());


		panel.add(labels[3]);
		panel.add(is64bit);
		childrenCount = childrenCount+2;


		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel, childrenCount, // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad


		return panel;
	}



	@Override
	public void save()
	{
		if ( name.getText() != "" )
		{
			mainOS.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainOS.setDescription(desc.getText());
		}

		if ( supportedFS.getSelectedIndex() != -1 )
		{
			mainOS.setSupportedFS(fsData);
		}

		mainOS.setEncryptedFileSystem(encryptedFileSystem.isSelected());

		mainOS.setHasGUI(hasGUI.isSelected());

		mainOS.setIs64bit(is64bit.isSelected());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof Button )
		{
			Button check = (Button) e.getSource();

			String command = check.getActionCommand();

			if ( command.equals("removeComp") )
			{
				
			}
		}
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @author Bahram Malaekeh
	 */
	class SharedListSelectionHandler implements ListSelectionListener
	{
		/*
		 * (non-Javadoc)
		 * @see
		 * javax.swing.event.ListSelectionListener#valueChanged(javax.swing.
		 * event.ListSelectionEvent)
		 */
		public void valueChanged(ListSelectionEvent e)
		{
			int[] indeces = supportedFS.getSelectedIndices();

			if ( indeces.length == 0 )
			{
				fsData = null;
			}
			else
			{
				// Creates an array of strings with the length of the array with
				// the selected indices.
				fsData = new String[indeces.length];

				// Find out which indexes are selected.
				for ( int i = 0; i < indeces.length; i++ )
				{
					fsData[i] = (String) supportedFS.getSelectedValues()[i];
				}
			}
		}
	}
}
