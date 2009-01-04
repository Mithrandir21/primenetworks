package graphics.GUI.objectView.Software.NewSoftware.NewViews;

import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Software.SoftwareView;
import graphics.GUI.objectView.Software.EditSoftware.EditOverview.SoftwareEditor;
import hardware.Motherboard;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import managment.ComponentsManagment;
import managment.SoftwareManagment;

import objects.Object;
import software.Antivirus;

public class AntivirusNewView extends JFrame implements SoftwareView,ActionListener
{
	// The name of the software object
	JTextField name = new JTextField(25);

	// The description of the software object.
	JTextArea desc = new JTextArea(3, 40);

	// The date of activation
	private JTextField actDate = new JTextField(7);

	// The date the license expires
	private JTextField expDate = new JTextField(7);

	// Whether or not the antivirus has been activated
	private JCheckBox activated;

	// The actual license of the program
	private JTextField license = new JTextField(7);


	private Object mainObj;

	private Antivirus mainAV;
	
	
	public AntivirusNewView(Object obj, Antivirus av)
	{
		super("New Antivirus");

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));

		mainObj = obj;
		mainAV = av;
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
		JPanel p1 = SoftwareEditor.GeneralInfo(mainAV, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel p2 = createSpesificInfo(mainAV);
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
	 * @param cpu
	 * @return
	 */
	private JPanel createSpesificInfo(Antivirus av)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[4];


		labels[0] = new JLabel("Activated Date");
		labels[0].setToolTipText("The date that the AV was activated.");

		labels[1] = new JLabel("Expiration Date");
		labels[1].setToolTipText("The date that the AV will expire.");

		labels[2] = new JLabel("License");
		labels[2].setToolTipText("The license key for the AV.");
		
		labels[3] = new JLabel("Activated");
		labels[3].setToolTipText("Whether or not the AV is activated.");


		int childrenCount = 0;
		Dimension tfSize = new Dimension(90, 20);
		SimpleDateFormat format = new SimpleDateFormat("dd/M/yyyy");


		// The Activated date
		labels[0].setLabelFor(actDate);
		actDate.setMaximumSize(tfSize);
		actDate.setPreferredSize(tfSize);
		Date parsedAct = null;

		try
		{
			if ( av.getActivationDate() != null )
			{
				parsedAct = format.parse(av.getActivationDate().toString());
			}
		}
		catch ( ParseException e )
		{
			// DO nothing.
			System.out.println("Error - AntivirusEditView - Activated Date");
		}

		if( av.getActivationDate() != null )
		{
			actDate.setText(parsedAct.toString());
		}
		else
		{
			actDate.setText("");
		}
		actDate.setToolTipText(labels[0].getToolTipText());

		panel.add(labels[0]);
		panel.add(actDate);
		childrenCount = childrenCount+2;


		// The Expiration date
		labels[1].setLabelFor(expDate);
		expDate.setMaximumSize(tfSize);
		expDate.setPreferredSize(tfSize);
		Date parsedExp = null;

		try
		{
			if ( av.getExpirationDate() != null )
			{
				parsedExp = format.parse(av.getExpirationDate().toString());
			}
		}
		catch ( ParseException e )
		{
			// DO nothing.
			System.out.println("Error - AntivirusEditView - Expiration Date");
		}
		
		if( av.getActivationDate() != null )
		{
			expDate.setText(parsedExp.toString());
		}
		else
		{
			expDate.setText("");
		}
		expDate.setToolTipText(labels[1].getToolTipText());

		panel.add(labels[1]);
		panel.add(expDate);
		childrenCount = childrenCount+2;



		// The license key
		labels[2].setLabelFor(license);
		license.setMaximumSize(tfSize);
		license.setPreferredSize(tfSize);
		license.setText(av.getLicense());
		license.setToolTipText(labels[2].getToolTipText());


		panel.add(labels[2]);
		panel.add(license);
		childrenCount = childrenCount+2;

		

		// Whether or not the AV has been avtivated.
		labels[3].setLabelFor(activated);
		activated = new JCheckBox();
		activated.setToolTipText(labels[3].getToolTipText());
		activated.setActionCommand("activated");
		activated.addActionListener(this);

		activated.setSelected(av.getIsActivated());


		panel.add(labels[3]);
		panel.add(activated);
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
		SimpleDateFormat format = new SimpleDateFormat("dd/M/yyyy");

		if ( name.getText() != "" )
		{
			mainAV.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainAV.setDescription(desc.getText());
		}

		if ( actDate.getText() != "" && actDate != null )
		{
			System.out.println("Dette er helt teit." + "\"" + actDate.getText() +"\"");
			Date tempDate = null;

			try
			{
				tempDate = format.parse(actDate.getText());
			}
			catch ( ParseException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mainAV.setActivationDate(tempDate);
		}

		if ( expDate != null && expDate.getText() != "" )
		{
			Date tempDate = null;

			try
			{
				tempDate = format.parse(expDate.getText());
			}
			catch ( ParseException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mainAV.setExpirationDate(tempDate);
		}

		mainAV.setActivated(activated.isSelected());

		if ( license.getText() != "" )
		{
			mainAV.setLicense(license.getText());
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
			mainObj.setSoftware(SoftwareManagment.addSoftware(mainAV, mainObj));

			// Closes the JFrame.
			this.dispose();

		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
		}

	}

}
