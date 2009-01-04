package graphics.GUI.objectView.Software.NewSoftware.NewViews;

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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import objects.Software;
import software.Backup;

public class BackupNewView extends JFrame implements SoftwareView,ActionListener
{
	// The name of the software object
	private JTextField name = new JTextField(25);

	// The description of the software object.
	private JTextArea desc = new JTextArea(3, 40);

	// Supported Operating systems
	private JList supportedOS;

	// List of operating systems
	private String[] OSs;

	// The type of backup
	private JTextField backupType = new JTextField(7);

	// Whether or not the software can use compression
	private JCheckBox compression;

	// Whether or not the software can use encryption
	private JCheckBox encryption;

	// The number of copies keeps
	private JComboBox duplicate;



	private Object mainObj;

	private Backup mainBack;


	public BackupNewView(Object obj, Backup back)
	{
		super("New Backup");
		
		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));

		mainObj = obj;
		mainBack = back;
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
		JPanel p1 = SoftwareEditor.GeneralInfo(mainBack, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(mainBack);
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
	 * Creates the JPanel that will contain the {@link Software Software}
	 * specific options. The layout of the returned panel will be
	 * {@link SpringLayout}.
	 */
	private JPanel createSpesificInfo(Backup back)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[5];


		labels[0] = new JLabel("Supported OS");
		labels[0].setToolTipText("The supported Operating Systems by the software.");

		labels[1] = new JLabel("Backup Type");
		labels[1].setToolTipText("The type of backup.(\"Complete\" or just \"Changes\")");

		labels[2] = new JLabel("Supports Compression");
		labels[2].setToolTipText("Whether or not the software support compression.");

		labels[3] = new JLabel("Supports Encryption");
		labels[3].setToolTipText("Whether or not the software support encryption.");
		
		labels[4] = new JLabel("Duplicates");
		labels[4].setToolTipText("How many duplicates of the backup the software keeps track of.");


		int childrenCount = 0;
		Dimension tfSize = new Dimension(90, 20);


		// The supported operating systems by the Email software.
		labels[0].setLabelFor(supportedOS);
		String[] listData = { "Windows 98", "Windows 2000", "Windows XP",
				"Windows Vista", "Linux", "Novell" };
		supportedOS = new JList(listData);
		ListSelectionModel listSelectionModel = supportedOS.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(supportedOS);
		listPane.setMaximumSize(new Dimension(160, 60));
		listPane.setPreferredSize(new Dimension(160, 60));
		listSelectionModel
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		if ( mainBack.getSupportedOperatingSystems() != null )
		{
			if ( mainBack.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, listData, mainBack
								.getSupportedOperatingSystems()));
			}
		}

		panel.add(labels[0]);
		panel.add(listPane);
		childrenCount = childrenCount+2;


		// Whether or not the software can use compression
		labels[2].setLabelFor(compression);
		compression = new JCheckBox();
		compression.setMaximumSize(tfSize);
		compression.setPreferredSize(tfSize);
		compression.setToolTipText(labels[2].getToolTipText());
		compression.setActionCommand("SupportsCompression");
		compression.addActionListener(this);

		compression.setSelected(mainBack.supportsCompression());

		panel.add(labels[2]);
		panel.add(compression);
		childrenCount = childrenCount+2;


		// Whether or not the software can use encryption
		labels[3].setLabelFor(encryption);
		encryption = new JCheckBox();
		encryption.setMaximumSize(tfSize);
		encryption.setPreferredSize(tfSize);
		encryption.setToolTipText(labels[3].getToolTipText());
		encryption.setActionCommand("SupportsEncryption");
		encryption.addActionListener(this);

		encryption.setSelected(mainBack.supportsEncryption());

		panel.add(labels[3]);
		panel.add(encryption);
		childrenCount = childrenCount+2;
		
		
		// The type of backup
		labels[1].setLabelFor(backupType);
		backupType = new JTextField();
		backupType.setMaximumSize(tfSize);
		backupType.setPreferredSize(tfSize);
		backupType.setText(mainBack.getBackupType());
		backupType.setToolTipText(labels[1].getToolTipText());


		panel.add(labels[1]);
		panel.add(backupType);
		childrenCount = childrenCount+2;


		// The number of copies keeps
		labels[4].setLabelFor(duplicate);
		String[] dupStrings = { "0", "1", "2", "3", "4", "5", "6", "7", "8" };
		duplicate = new JComboBox(dupStrings);
		duplicate.setMaximumSize(tfSize);
		duplicate.setPreferredSize(tfSize);
		duplicate.setBackground(Color.WHITE);
		duplicate.setToolTipText(labels[4].getToolTipText());
		duplicate.setActionCommand("Duplicates");
		duplicate.addActionListener(this);

		duplicate.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				dupStrings, back.getDuplicate()));


		panel.add(labels[4]);
		panel.add(duplicate);
		childrenCount = childrenCount+2;

		
		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel, childrenCount, // rows, cols
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
		if ( name.getText() != "" )
		{
			mainBack.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainBack.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainBack.setSupportedOperatingSystems(OSs);
		}

		// The type of backup
		if ( backupType.getText() != "" )
		{
			mainBack.setBackupType(backupType.getText());
		}

		// Whether or not the software can use compression
		mainBack.setSupportsCompression(compression.isSelected());

		// Whether or not the software can use encryption
		mainBack.setSupportsEncryption(encryption.isSelected());

		
		if ( duplicate.getSelectedItem().toString() != "" ) 
		{
			// The number of copies keeps
			mainBack.setDuplicate(Integer.parseInt(duplicate.getSelectedItem()
					.toString()));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			// Saves the current values of the new motherboard.
			save();
			
			// Sets an array with the newly added software object
			mainObj.setSoftware(SoftwareManagment.addSoftware(mainBack, mainObj));

			// Closes the JFrame.
			this.dispose();

		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
		}

	}
	
	

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @author Bahram Malaekeh
	 */
	private class SharedListSelectionHandler implements ListSelectionListener
	{
		/*
		 * (non-Javadoc)
		 * @see
		 * javax.swing.event.ListSelectionListener#valueChanged(javax.swing.
		 * event.ListSelectionEvent)
		 */
		public void valueChanged(ListSelectionEvent e)
		{
			int[] indeces = supportedOS.getSelectedIndices();

			if ( indeces.length == 0 )
			{
				OSs = null;
			}
			else
			{
				// Creates an array of strings with the length of the array with
				// the selected indices.
				OSs = new String[indeces.length];

				// Find out which indexes are selected.
				for ( int i = 0; i < indeces.length; i++ )
				{
					OSs[i] = (String) supportedOS.getSelectedValues()[i];
				}
			}
		}
	}
}
