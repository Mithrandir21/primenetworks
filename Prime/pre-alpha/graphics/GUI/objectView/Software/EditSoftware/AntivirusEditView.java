package graphics.GUI.objectView.Software.EditSoftware;

import graphics.ImageLocator;
import graphics.GUI.SpringUtilities;
import graphics.GUI.objectView.Software.SoftwareEditView;
import graphics.GUI.objectView.Software.SoftwareEditor;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import objects.Object;
import software.Antivirus;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class AntivirusEditView extends JPanel implements SoftwareEditView, ActionListener
{
	// The name of the software object
	JTextField name = new JTextField(25);

	// The description of the software object.
	JTextArea desc = new JTextArea(3, 40);

	// The date of activation
	private JTextField actDate = new JTextField(10);

	// The date the license expires
	private JTextField expDate = new JTextField(10);
	
	// Whether or not the antivirus has been activated
	private JCheckBox activated;
	
	// The actual license of the program
	private JTextField license = new JTextField(100);
	
	
	private Object mainObj;
	
	private Antivirus mainAV;
	
	
	/**
	 * TODO - Description NEEDED!
	 *
	 * @param obj
	 * @param av
	 */
	public AntivirusEditView(Object obj, Antivirus av)
	{
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
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(mainAV);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);



		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel("Remove this component from this device");

		Button save = new Button("Remove Component");
		save.addActionListener(this);
		save.setActionCommand("removeComp");

		buttons.add(label);
		buttons.add(save);

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
	 * TODO - Description
	 * 
	 */
	private JPanel createSpesificInfo(Antivirus av)
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
		SimpleDateFormat format = new SimpleDateFormat("dd/M/yyyy");
		
		
		// The Activated date
		labels[0].setLabelFor(actDate);
		actDate.setMaximumSize(tfSize);
		actDate.setPreferredSize(tfSize);
		Date parsedAct = null;
		
		try
		{
			parsedAct = format.parse(av.getActivationDate().toString());
		}
		catch ( ParseException e )
		{
			// DO nothing.
			System.out.println("�sdjfj");
		}
		
		actDate.setText(parsedAct.toString());
		actDate.setToolTipText(labels[0].getToolTipText());

		panel.add(labels[0]);
		panel.add(actDate);

		
		
		// The Expiration date
		labels[1].setLabelFor(expDate);
		expDate.setMaximumSize(tfSize);
		expDate.setPreferredSize(tfSize);
		Date parsedExp = null;
		
		try
		{
			parsedExp = format.parse(av.getExpirationDate().toString());
		}
		catch ( ParseException e )
		{
			// DO nothing.
			System.out.println("�sdjfj");
		}
		
		actDate.setText(parsedExp.toString());
		actDate.setToolTipText(labels[1].getToolTipText());

		panel.add(labels[0]);
		panel.add(actDate);
		
		
		
		// Whether or not the AV has been avtivated.
		labels[2].setLabelFor(activated);
		activated = new JCheckBox();
		activated.setToolTipText(labels[2].getToolTipText());
		activated.setActionCommand("activated");
		activated.addActionListener(this);

		activated.setSelected(av.getIsActivated());


		panel.add(labels[2]);
		panel.add(activated);
		
		
		
		// The license key
		labels[3].setLabelFor(license);
		license.setMaximumSize(tfSize);
		license.setPreferredSize(tfSize);
		license.setText(av.getLicense());
		license.setToolTipText(labels[3].getToolTipText());


		panel.add(labels[3]);
		panel.add(license);
		
		
		
		JLabel temp1 = new JLabel("");
		temp1.setMaximumSize(tfSize);
		temp1.setPreferredSize(tfSize);

		JLabel temp2 = new JLabel("");
		temp2.setMaximumSize(tfSize);
		temp2.setPreferredSize(tfSize);

		JLabel temp3 = new JLabel("");
		temp3.setMaximumSize(tfSize);
		temp3.setPreferredSize(tfSize);
		
		// adding components so that the layout is right
		panel.add(temp1);
		panel.add(temp2);
		panel.add(temp3);
		
		
		// Lay out the panel.
		SpringUtilities.makeCompactGrid(panel, 2, 6, // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad
		
		
		
		return panel;
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