package graphics.GUI.objectView.Software.NewSoftware.NewViews;


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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import objects.Object;
import objects.softwareObjects.OfficeSuite;


public class OfficeSuiteNewView extends JFrame implements SoftwareView, ActionListener
{
	// The name of the software object
	JTextField name = new JTextField(25);

	// The description of the software object.
	JTextArea desc = new JTextArea(3, 40);

	// FIXME - has to be fixed officesuiteNewView

	private Object mainObj;

	private OfficeSuite mainOffSuite;


	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param offSuite
	 *            The {@link OfficeSuite OfficeSuite} software.
	 */
	public OfficeSuiteNewView(Object obj, OfficeSuite offSuite)
	{
		super("New Backup");

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));

		mainObj = obj;
		mainOffSuite = offSuite;
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
		JPanel p1 = SoftwareEditor.GeneralInfo(mainOffSuite, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(mainOffSuite);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);


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
	 * @param offSuite
	 *            The Software that will be examined and will fill inn the fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(OfficeSuite offSuite)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[4];


		labels[0] = new JLabel("Activated Date");
		labels[0].setToolTipText("The date that the AV was activated.");

		labels[1] = new JLabel("Expiration Date");
		labels[1].setToolTipText("The date that the AV will expire.");

		labels[2] = new JLabel("Activated");
		labels[2].setToolTipText("Whether or not the AV is activated.");

		labels[3] = new JLabel("License");
		labels[3].setToolTipText("The license key for the AV.");


		Dimension tfSize = new Dimension(90, 20);



		return panel;
	}


	/**
	 * Creates a JPanel with two buttons that are listened for by actionlisteners.
	 * 
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
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}

}
