package graphics.GUI.objectView.Software.EditSoftware.EditViews;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;
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

import objects.Object;
import objects.Software;
import objects.softwareObjects.SecuritySuite;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link SecuritySuite SecuritySuite} Software. The panel is
 * made up of 3 JPanel ordered in a column. The first one contains the name and
 * description of the object. The second panel contains the specific software
 * options. The third panel contains the button that can remove the software
 * from the computer.
 * 
 * @author Bahram Malaekeh
 */
public class SecuritySuiteEditView extends JPanel implements SoftwareView, ActionListener
{
	// The name of the software object
	JTextField name = new JTextField(25);

	// The description of the software object.
	JTextArea desc = new JTextArea(3, 40);

	// Supported Operating systems
	private JList supportedOS;

	// List of operating systems
	private String[] OSs;

	// Whether or not the security suite has been activated
	private JCheckBox activated;

	// The actual license of the program
	private JTextField license = new JTextField(25);

	// Whether or not the security suite contains an antivirus
	private JCheckBox hasAntivirus;

	// TO-DO: Set up connection between security suite and antivirus, firewall
	// and proxy

	// Whether or not the security suite contains an firewall
	private JCheckBox hasFirewall;

	// Whether or not the security suite contains an proxy
	private JCheckBox hasProxy;

	// The date of activation
	private JTextField actDate = new JTextField(10);

	// The date the license expires
	private JTextField expDate = new JTextField(10);



	private Object mainObj;

	private SecuritySuite mainSecSuite;


	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param secSuite
	 *            The {@link SecuritySuite SecuritySuite} software.
	 */
	public SecuritySuiteEditView(Object obj, SecuritySuite secSuite)
	{
		mainObj = obj;
		mainSecSuite = secSuite;
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

		ImageIcon icon = PrimeMain1.objectImageIcons.get(SecuritySuite.class);
		JPanel p1 = SoftwareEditor.GeneralInfo(mainSecSuite, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(mainSecSuite);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);



		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel(PrimeMain1.texts
				.getString("swTabRemoveSoftwaretText"));

		Button remove = new Button(PrimeMain1.texts
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
	 * @param secSuite
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(SecuritySuite secSuite)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[8];


		labels[0] = new JLabel(PrimeMain1.texts
				.getString("secSuiteViewSupOSLabel"));
		labels[0].setToolTipText(PrimeMain1.texts
				.getString("secSuiteViewSupOSTip"));

		labels[1] = new JLabel(PrimeMain1.texts
				.getString("secSuiteViewActivatedLabel"));
		labels[1].setToolTipText(PrimeMain1.texts
				.getString("secSuiteViewActivatedTip"));

		labels[2] = new JLabel(PrimeMain1.texts
				.getString("secSuiteViewLicenseLabel"));
		labels[2].setToolTipText(PrimeMain1.texts
				.getString("secSuiteViewLicenseTip"));

		labels[3] = new JLabel(PrimeMain1.texts
				.getString("secSuiteViewHasAntivirusLabel"));
		labels[3].setToolTipText(PrimeMain1.texts
				.getString("secSuiteViewHasAntivirusTip"));

		labels[4] = new JLabel(PrimeMain1.texts
				.getString("secSuiteViewHasFirewallLabel"));
		labels[4].setToolTipText(PrimeMain1.texts
				.getString("secSuiteViewHasFirewallTip"));

		labels[5] = new JLabel(PrimeMain1.texts
				.getString("secSuiteViewHasProxyLabel"));
		labels[5].setToolTipText(PrimeMain1.texts
				.getString("secSuiteViewHasProxyTip"));

		labels[6] = new JLabel(PrimeMain1.texts
				.getString("secSuiteViewActDateLabel"));
		labels[6].setToolTipText(PrimeMain1.texts
				.getString("secSuiteViewActDateTip"));

		labels[7] = new JLabel(PrimeMain1.texts
				.getString("secSuiteViewExpDateLabel"));
		labels[7].setToolTipText(PrimeMain1.texts
				.getString("secSuiteViewExpDateTip"));


		Dimension tfSize = new Dimension(90, 20);
		SimpleDateFormat format = new SimpleDateFormat(PrimeMain1.texts
				.getString("secSuiteViewSimpleDateFormat"));

		// --------------------------------------------------------------

		// The supported operating systems by the Email software.
		labels[0].setLabelFor(supportedOS);
		String[] listData = { "Windows 98", "Windows 2000", "Windows XP",
				"Windows Vista", "Linux", "Novell" };
		supportedOS = new JList(listData);
		ListSelectionModel listSelectionModel = supportedOS.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(supportedOS);
		listPane.setMaximumSize(new Dimension(130, 60));
		listPane.setPreferredSize(new Dimension(130, 60));
		listSelectionModel
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		if ( mainSecSuite.getSupportedOperatingSystems() != null )
		{
			if ( mainSecSuite.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, listData, mainSecSuite
								.getSupportedOperatingSystems()));
			}
		}

		panel.add(labels[0]);
		panel.add(listPane);

		// --------------------------------------------------------------

		// Whether or not the security suite has been activated
		labels[1].setLabelFor(activated);
		activated = new JCheckBox();
		activated.setMaximumSize(tfSize);
		activated.setPreferredSize(tfSize);
		activated.setToolTipText(labels[1].getToolTipText());
		activated.setActionCommand("Activated");
		activated.addActionListener(this);

		activated.setSelected(mainSecSuite.isActivated());

		panel.add(labels[1]);
		panel.add(activated);

		// --------------------------------------------------------------

		// The actual license of the program
		labels[2].setLabelFor(license);
		license.setMaximumSize(tfSize);
		license.setPreferredSize(tfSize);
		license.setText(mainSecSuite.getLicense());
		;
		license.setToolTipText(labels[2].getToolTipText());


		panel.add(labels[2]);
		panel.add(license);

		// --------------------------------------------------------------

		// Whether or not the security suite contains an antivirus
		labels[3].setLabelFor(hasAntivirus);
		hasAntivirus = new JCheckBox();
		hasAntivirus.setMaximumSize(tfSize);
		hasAntivirus.setPreferredSize(tfSize);
		hasAntivirus.setToolTipText(labels[3].getToolTipText());
		hasAntivirus.setActionCommand("HasAntivirus");
		hasAntivirus.addActionListener(this);

		hasAntivirus.setSelected(mainSecSuite.hasAntivirus());

		panel.add(labels[3]);
		panel.add(hasAntivirus);

		// --------------------------------------------------------------

		// TO-DO: Set up connection between security suite and antivirus,
		// firewall
		// and proxy


		// Whether or not the security suite contains an firewall
		labels[4].setLabelFor(hasFirewall);
		hasFirewall = new JCheckBox();
		hasFirewall.setMaximumSize(tfSize);
		hasFirewall.setPreferredSize(tfSize);
		hasFirewall.setToolTipText(labels[4].getToolTipText());
		hasFirewall.setActionCommand("HasFirewall");
		hasFirewall.addActionListener(this);

		hasFirewall.setSelected(mainSecSuite.hasFirewall());

		panel.add(labels[4]);
		panel.add(hasFirewall);

		// --------------------------------------------------------------

		// Whether or not the security suite contains an proxy
		labels[5].setLabelFor(hasProxy);
		hasProxy = new JCheckBox();
		hasProxy.setMaximumSize(tfSize);
		hasProxy.setPreferredSize(tfSize);
		hasProxy.setToolTipText(labels[5].getToolTipText());
		hasProxy.setActionCommand("HasProxy");
		hasProxy.addActionListener(this);

		hasProxy.setSelected(mainSecSuite.hasProxy());

		panel.add(labels[5]);
		panel.add(hasProxy);

		// --------------------------------------------------------------

		// The Activated date
		labels[6].setLabelFor(actDate);
		actDate.setMaximumSize(tfSize);
		actDate.setPreferredSize(tfSize);
		Date parsedAct = null;

		try
		{
			if ( mainSecSuite.getActivationDate() != null )
			{
				parsedAct = format.parse(mainSecSuite.getActivationDate()
						.toString());
			}
		}
		catch ( ParseException e )
		{
			// DO nothing.
			System.out
					.println("Error - SecuritySuiteEditView - Activated Date");
		}

		if ( parsedAct != null )
		{
			actDate.setText(parsedAct.toString());
		}
		actDate.setToolTipText(labels[6].getToolTipText());

		panel.add(labels[6]);
		panel.add(actDate);

		// --------------------------------------------------------------

		// The Expiration date
		labels[7].setLabelFor(expDate);
		expDate.setMaximumSize(tfSize);
		expDate.setPreferredSize(tfSize);
		Date parsedExp = null;

		try
		{
			if ( mainSecSuite.getExpirationDate() != null )
			{
				parsedExp = format.parse(mainSecSuite.getExpirationDate()
						.toString());
			}
		}
		catch ( ParseException e )
		{
			// DO nothing.
			System.out
					.println("Error - SecuritySuiteEditView - Expiration Date");
		}

		if ( parsedExp != null )
		{
			expDate.setText(parsedExp.toString());
		}
		expDate.setToolTipText(labels[7].getToolTipText());

		panel.add(labels[7]);
		panel.add(expDate);

		// --------------------------------------------------------------



		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel,
				panel.getComponentCount(), // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad



		return panel;
	}



	/*
	 * (non-Javadoc)
	 * @see graphics.GUI.objectView.Software.SoftwareEditView#save()
	 */
	@Override
	public void save()
	{
		SimpleDateFormat format = new SimpleDateFormat(PrimeMain1.texts
				.getString("secSuiteViewSimpleDateFormat"));

		if ( name.getText() != "" )
		{
			mainSecSuite.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainSecSuite.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainSecSuite.setSupportedOperatingSystems(OSs);
		}


		if ( license.getText() != "" )
		{
			mainSecSuite.setLicense(license.getText());
		}


		mainSecSuite.setHasAntivirus(hasAntivirus.isSelected());

		mainSecSuite.sethasFirewall(hasFirewall.isSelected());

		mainSecSuite.sethasProxy(hasProxy.isSelected());


		if ( !actDate.getText().equalsIgnoreCase("") )
		{
			Date tempDate = null;

			try
			{
				tempDate = format.parse(actDate.getText());
			}
			catch ( ParseException e )
			{
				e.printStackTrace();
			}

			mainSecSuite.setActivationDate(tempDate);
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
				e.printStackTrace();
			}

			mainSecSuite.setExpirationDate(tempDate);
		}

	}



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof JCheckBox )
		{
			JCheckBox box = (JCheckBox) e.getSource();

			String command = box.getActionCommand();

			if ( command.equals("Activated") )
			{

			}
			else if ( command.equals("HasAntivirus") )
			{

			}
			else if ( command.equals("HasFirewall") )
			{

			}
			else if ( command.equals("HasProxy") )
			{

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
			int[] indexes = supportedOS.getSelectedIndices();

			if ( indexes.length == 0 )
			{
				OSs = null;
			}
			else
			{
				// Creates an array of strings with the length of the array with
				// the selected indices.
				OSs = new String[indexes.length];

				// Find out which indexes are selected.
				for ( int i = 0; i < indexes.length; i++ )
				{
					OSs[i] = (String) supportedOS.getSelectedValues()[i];
				}
			}
		}
	}


	@Override
	public Software getViewSoftware()
	{
		return mainSecSuite;
	}

}
