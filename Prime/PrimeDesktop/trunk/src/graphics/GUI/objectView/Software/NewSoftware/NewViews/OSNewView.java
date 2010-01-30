/**
 * 
 */
package graphics.GUI.objectView.Software.NewSoftware.NewViews;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Software.SoftwareView;
import graphics.GUI.objectView.Software.EditSoftware.EditOverview.SoftwareEditor;

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
import javax.swing.JFrame;
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

import managment.SoftwareManagment;
import objects.Object;
import objects.softwareObjects.OperatingSystem;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class OSNewView extends JFrame implements SoftwareView, ActionListener
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
	public OSNewView(Object obj, OperatingSystem OS)
	{
		super(PrimeMain1.texts.getString("swNewOSLabel"));

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));

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

		ImageIcon icon = PrimeMain1.objectImageIcons.get(OperatingSystem.class);
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
	 * settings of the given Software object. It uses the
	 * {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param OS
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(OperatingSystem OS)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[4];


		labels[0] = new JLabel(PrimeMain1.texts.getString("osViewSupFSLabel"));
		labels[0].setToolTipText(PrimeMain1.texts.getString("osViewSupFSTip"));

		labels[1] = new JLabel(PrimeMain1.texts
				.getString("osViewSupEnctyptedFSLabel"));
		labels[1].setToolTipText(PrimeMain1.texts
				.getString("osViewSupEnctyptedFSTip"));

		labels[2] = new JLabel(PrimeMain1.texts.getString("osViewHasGUILabel"));
		labels[2].setToolTipText(PrimeMain1.texts.getString("osViewHasGUITip"));

		labels[3] = new JLabel(PrimeMain1.texts.getString("osView64BitLabel"));
		labels[3].setToolTipText(PrimeMain1.texts.getString("osView64BitTip"));


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


		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel, // rows,
				panel.getComponentCount(),// cols
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
		if ( e.getActionCommand().equals("save") )
		{
			// Saves the current values of the new motherboard.
			save();


			// Sets an array with the newly added software object
			mainObj.setSoftware(SoftwareManagment.addSoftware(mainOS, mainObj));


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
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
		}
	}


	/**
	 * Handles the selections that are made in the "Supported Operating Systems"
	 * JList.
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
