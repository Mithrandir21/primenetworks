package graphics.GUI.objectView.Software.EditSoftware.EditViews;


import graphics.GraphicalFunctions;
import graphics.PrimeMain;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import managment.SoftwareManagment;
import objects.Object;
import objects.Software;
import objects.softwareObjects.Antivirus;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link Antivirus Antivirus} Software. The panel is made up
 * of 3 JPanel ordered in a column. The first one contains the name and
 * description of the object. The second panel contains the specific software
 * options. The third panel contains the button that can remove the software
 * from the computer.
 * 
 * @author Bahram Malaekeh
 */
public class AntivirusEditView extends JPanel implements SoftwareView, ActionListener
{
	// The name of the software object
	JTextField name = new JTextField(25);

	// The description of the software object.
	JTextArea desc = new JTextArea(3, 40);

	// Supported Operating systems
	private JList supportedOS;

	// List of operating systems
	private String[] OSs;

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
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param av
	 *            The {@link Antivirus Antivirus} software.
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

		ImageIcon icon = PrimeMain.objectImageIcons.get(Antivirus.class);
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

		JPanel p2 = createSpecificInfo(mainAV);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);



		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel(PrimeMain.texts
				.getString("swTabRemoveSoftwaretText"));

		Button remove = new Button(PrimeMain.texts
				.getString("swTabRemoveSoftwareButtonLabel"));
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
	 * This method creates and returns a JPanel that contains all the different
	 * settings of the given Software object. It uses the {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param av
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpecificInfo(Antivirus av)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[5];


		labels[0] = new JLabel(PrimeMain.texts.getString("avViewSupOSLabel"));
		labels[0].setToolTipText(PrimeMain.texts.getString("avViewSupOSTip"));

		labels[1] = new JLabel(PrimeMain.texts.getString("avViewActDateLabel"));
		labels[1]
				.setToolTipText(PrimeMain.texts.getString("avViewActDateTip"));

		labels[2] = new JLabel(PrimeMain.texts.getString("avViewExpDateLabel"));
		labels[2]
				.setToolTipText(PrimeMain.texts.getString("avViewExpDateTip"));

		labels[3] = new JLabel(PrimeMain.texts.getString("avViewLicenseLabel"));
		labels[3]
				.setToolTipText(PrimeMain.texts.getString("avViewLicenseTip"));

		labels[4] = new JLabel(PrimeMain.texts
				.getString("avViewActivatedLabel"));
		labels[4].setToolTipText(PrimeMain.texts
				.getString("avViewActivatedTip"));


		Dimension tfSize = new Dimension(90, 20);
		SimpleDateFormat format = new SimpleDateFormat(PrimeMain.texts
				.getString("avViewSimpleDateFormat"));


		// The supported operating systems by the Antivirus software.
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

		if ( mainAV.getSupportedOperatingSystems() != null )
		{
			if ( mainAV.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, listData, mainAV
								.getSupportedOperatingSystems()));
			}
		}

		panel.add(labels[0]);
		panel.add(listPane);



		// The Activated date
		labels[1].setLabelFor(actDate);
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

		if ( av.getActivationDate() != null )
		{
			actDate.setText(parsedAct.toString());
		}
		else
		{
			actDate.setText("");
		}
		actDate.setToolTipText(labels[1].getToolTipText());

		panel.add(labels[1]);
		panel.add(actDate);


		// The Expiration date
		labels[2].setLabelFor(expDate);
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

		if ( av.getActivationDate() != null )
		{
			expDate.setText(parsedExp.toString());
		}
		else
		{
			expDate.setText("");
		}
		expDate.setToolTipText(labels[2].getToolTipText());

		panel.add(labels[2]);
		panel.add(expDate);



		// The license key
		labels[3].setLabelFor(license);
		license.setMaximumSize(tfSize);
		license.setPreferredSize(tfSize);
		license.setText(av.getLicense());
		license.setToolTipText(labels[3].getToolTipText());


		panel.add(labels[3]);
		panel.add(license);



		// Whether or not the AV has been avtivated.
		labels[4].setLabelFor(activated);
		activated = new JCheckBox();
		activated.setToolTipText(labels[4].getToolTipText());
		activated.setActionCommand("activated");
		activated.addActionListener(this);

		activated.setSelected(av.getIsActivated());


		panel.add(labels[4]);
		panel.add(activated);


		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel,
				panel.getComponentCount(), // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad


		return panel;
	}




	@Override
	public void save()
	{
		SimpleDateFormat format = new SimpleDateFormat(PrimeMain.texts
				.getString("avViewSimpleDateFormat"));

		if ( name.getText() != "" )
		{
			mainAV.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainAV.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainAV.setSupportedOperatingSystems(OSs);
		}

		if ( !actDate.getText().equalsIgnoreCase("") )
		{
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

		if ( !expDate.getText().equalsIgnoreCase("") )
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

		if ( !license.getText().equalsIgnoreCase("") )
		{
			mainAV.setLicense(license.getText());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof Button )
		{
			Button check = (Button) e.getSource();

			String command = check.getActionCommand();

			if ( command.equals("removeSoft") )
			{
				// Will remove the first variable from the list of components
				// that will be returned and set as the components for the main
				// object.
				// mainObj.setAllComponents(ComponentsManagment.removeComponent(
				// CPUobj, mainObj.getComponents(), mainObj
				// .getComponents().length));
				mainObj.setSoftware(SoftwareManagment.removeSoftware(mainAV,
						mainObj));

				// Updates the views of the object to correctly show the
				// current info.
				ObjectView view = PrimeMain.getObjectView(mainObj);
				if ( view != null )
				{
					view.updateViewInfo();
				}
			}
		}
	}


	/**
	 * Handles the selections that are made in the "Supported Operating Systems"
	 * JList.
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


	@Override
	public Software getViewSoftware()
	{
		return mainAV;
	}

}
