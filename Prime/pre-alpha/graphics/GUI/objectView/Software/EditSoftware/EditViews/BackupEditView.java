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
import javax.swing.JComboBox;
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
import software.Backup;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link Backup Backup} Software. The panel is made up of 3
 * JPanel ordered in a column. The first one contains the name and description
 * of the object. The second panel contains the specific software options. The
 * third panel contains the button that can remove the software from the
 * computer.
 * 
 * @author Bahram Malaekeh
 */
public class BackupEditView extends JPanel implements SoftwareView,
		ActionListener
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
	private JTextField backupType;

	// Whether or not the software can use compression
	private JCheckBox compression;

	// Whether or not the software can use encryption
	private JCheckBox encryption;

	// The number of copies keeps
	private JComboBox duplicate;





	private Object mainObj;

	private Backup mainBack;


	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param back
	 *            The {@link Backup Backup} software.
	 */
	public BackupEditView(Object obj, Backup back)
	{
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
	 * This method creates and returns a JPanel that contains all the
	 * different settings of the given Software object. It uses the
	 * {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order
	 * all the different components in the JPanel in grids.
	 * 
	 * @param back The Software that will be examined and will fill inn the fields.
	 * @return A JPanel that contains fields to set the given objects settings.
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
		
		
		// The type of backup
		labels[1].setLabelFor(backupType);
		backupType = new JTextField();
		backupType.setMaximumSize(tfSize);
		backupType.setPreferredSize(tfSize);
		backupType.setText(mainBack.getBackupType());
		backupType.setToolTipText(labels[1].getToolTipText());


		panel.add(labels[1]);
		panel.add(backupType);


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

		
		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel, panel.getComponentCount(), // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad
		

		return panel;
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
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub

	}


	/**
	 * Handles the selections that are made in the "Supported Operating Systems" JList.
	 *  
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
