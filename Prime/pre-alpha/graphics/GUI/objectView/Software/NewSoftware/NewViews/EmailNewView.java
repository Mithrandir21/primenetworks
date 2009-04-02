package graphics.GUI.objectView.Software.NewSoftware.NewViews;


import graphics.GraphicalFunctions;
import graphics.ImageLocator;
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
import javax.swing.JOptionPane;
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
import objects.softwareObjects.Email;


public class EmailNewView extends JFrame implements SoftwareView, ActionListener
{
	// The name of the software object
	JTextField name = new JTextField(25);

	// The description of the software object.
	JTextArea desc = new JTextArea(3, 40);

	// Supported Operating systems
	private JList supportedOS;

	// List of operating systems
	private String[] OSs;

	// Supports POP3
	private JCheckBox supportsPOP3;


	// Supports SMTP
	private JCheckBox supportsSMTP;


	// Supports IMAP
	private JCheckBox supportsIMAP;


	// Supports NNTP
	private JCheckBox supportsNNTP;


	// Supports SSL
	private JCheckBox supportsSSL;


	// Supports webmail
	private JCheckBox supportsWebmail;


	private Object mainObj;

	private Email mainEmail;




	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param obj
	 * @param email
	 */
	public EmailNewView(Object obj, Email email)
	{
		super("New Backup");

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));

		mainObj = obj;
		mainEmail = email;
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
		JPanel p1 = SoftwareEditor.GeneralInfo(mainEmail, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(mainEmail);
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



		this.setMinimumSize(new Dimension((int) scrnsize.getWidth() / 3, (int) scrnsize.getHeight() / 3));
		this.setSize(width, height);
		this.setVisible(true);
	}


	/**
	 * This method creates and returns a JPanel that contains all the different settings of the given Software object.
	 * It uses the {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all the different components in
	 * the JPanel in grids.
	 * 
	 * @param email
	 *            The Software that will be examined and will fill inn the fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(Email email)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[7];


		labels[0] = new JLabel("Supported OS");
		labels[0].setToolTipText("The supported Operating Systems by the software.");

		labels[1] = new JLabel("Supports POP3");
		labels[1].setToolTipText("Whether or not the software supports POP3.");

		labels[2] = new JLabel("Supports SMTP");
		labels[2].setToolTipText("Whether or not the software supports SMTP.");

		labels[3] = new JLabel("Supports IMAP");
		labels[3].setToolTipText("Whether or not the software supports IMAP.");

		labels[4] = new JLabel("Supports NNTP");
		labels[4].setToolTipText("Whether or not the software supports NNTP.");

		labels[5] = new JLabel("Supports SSL");
		labels[5].setToolTipText("Whether or not the software supports SSL.");

		labels[6] = new JLabel("Supports Webmail");
		labels[6].setToolTipText("Whether or not the software supports webmail.");


		Dimension tfSize = new Dimension(90, 20);



		// The supported operating systems by the Email software.
		labels[0].setLabelFor(supportedOS);
		String[] listData = { "Windows 98", "Windows 2000", "Windows XP", "Windows Vista", "Linux", "Novell" };
		supportedOS = new JList(listData);
		ListSelectionModel listSelectionModel = supportedOS.getSelectionModel();
		listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(supportedOS);
		listPane.setMaximumSize(new Dimension(160, 60));
		listPane.setPreferredSize(new Dimension(160, 60));
		listSelectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		if ( mainEmail.getSupportedOperatingSystems() != null )
		{
			if ( mainEmail.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(supportedOS, listData, mainEmail
						.getSupportedOperatingSystems()));
			}
		}

		panel.add(labels[0]);
		panel.add(listPane);



		// Whether or not the software supports pop3.
		labels[1].setLabelFor(supportsPOP3);
		supportsPOP3 = new JCheckBox();
		supportsPOP3.setMaximumSize(tfSize);
		supportsPOP3.setPreferredSize(tfSize);
		supportsPOP3.setToolTipText(labels[1].getToolTipText());
		supportsPOP3.setActionCommand("POP3");
		supportsPOP3.addActionListener(this);

		supportsPOP3.setSelected(mainEmail.SupportsPOP3());

		panel.add(labels[1]);
		panel.add(supportsPOP3);


		// Whether or not the software supports SMTP.
		labels[2].setLabelFor(supportsSMTP);
		supportsSMTP = new JCheckBox();
		supportsSMTP.setMaximumSize(tfSize);
		supportsSMTP.setPreferredSize(tfSize);
		supportsSMTP.setToolTipText(labels[2].getToolTipText());
		supportsSMTP.setActionCommand("SMTP");
		supportsSMTP.addActionListener(this);

		supportsSMTP.setSelected(mainEmail.SupportsSMTP());

		panel.add(labels[2]);
		panel.add(supportsSMTP);


		// Whether or not the software supports IMAP.
		labels[3].setLabelFor(supportsIMAP);
		supportsIMAP = new JCheckBox();
		supportsIMAP.setMaximumSize(tfSize);
		supportsIMAP.setPreferredSize(tfSize);
		supportsIMAP.setToolTipText(labels[3].getToolTipText());
		supportsIMAP.setActionCommand("IMAP");
		supportsIMAP.addActionListener(this);

		supportsIMAP.setSelected(mainEmail.SupportsIMAP());

		panel.add(labels[3]);
		panel.add(supportsIMAP);


		// Whether or not the software supports NNTP.
		labels[4].setLabelFor(supportsNNTP);
		supportsNNTP = new JCheckBox();
		supportsNNTP.setMaximumSize(tfSize);
		supportsNNTP.setPreferredSize(tfSize);
		supportsNNTP.setToolTipText(labels[4].getToolTipText());
		supportsNNTP.setActionCommand("NNTP");
		supportsNNTP.addActionListener(this);

		supportsNNTP.setSelected(mainEmail.SupportsNNTP());

		panel.add(labels[4]);
		panel.add(supportsNNTP);


		// Whether or not the software supports SSL.
		labels[5].setLabelFor(supportsSSL);
		supportsSSL = new JCheckBox();
		supportsSSL.setMaximumSize(tfSize);
		supportsSSL.setPreferredSize(tfSize);
		supportsSSL.setToolTipText(labels[5].getToolTipText());
		supportsSSL.setActionCommand("SSL");
		supportsSSL.addActionListener(this);

		supportsSSL.setSelected(mainEmail.SupportsSSL());

		panel.add(labels[5]);
		panel.add(supportsSSL);



		// Whether or not the software supports Webmail.
		labels[6].setLabelFor(supportsWebmail);
		supportsWebmail = new JCheckBox();
		supportsWebmail.setMaximumSize(tfSize);
		supportsWebmail.setPreferredSize(tfSize);
		supportsWebmail.setToolTipText(labels[6].getToolTipText());
		supportsWebmail.setActionCommand("Webmail");
		supportsWebmail.addActionListener(this);

		supportsWebmail.setSelected(mainEmail.SupportsWebmail());

		panel.add(labels[6]);
		panel.add(supportsWebmail);



		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel, panel.getComponentCount(), // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad


		return panel;
	}


	/**
	 * Creates a JPanel with two buttons that are listened for by actionlisteners.
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
			mainEmail.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainEmail.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainEmail.setSupportedOperatingSystems(OSs);
		}

		mainEmail.setSupportsPOP3(supportsPOP3.isSelected());

		mainEmail.setSupportsSMTP(supportsSMTP.isSelected());

		mainEmail.setSupportsIMAP(supportsIMAP.isSelected());

		mainEmail.setSupportsNNTP(supportsNNTP.isSelected());

		mainEmail.setSupportsSSL(supportsSSL.isSelected());

		mainEmail.setSupportsWebmail(supportsWebmail.isSelected());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			// Saves the current values of the new motherboard.
			save();

			// Checks whether or not the software is compatible with the OS
			if ( SoftwareManagment.validateSoftware(mainEmail, mainObj) )
			{
				// Sets an array with the newly added software object
				mainObj.setSoftware(SoftwareManagment.addSoftware(mainEmail, mainObj));


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
			else
			{
				JOptionPane.showMessageDialog(this, "The supported Operating System chosen is not "
						+ "compatible with the objects Operating System");
			}

		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
		}

	}


	/**
	 * Handles the selections that are made in the "Supported Operating Systems" JList.
	 */
	class SharedListSelectionHandler implements ListSelectionListener
	{
		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing. event.ListSelectionEvent)
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
