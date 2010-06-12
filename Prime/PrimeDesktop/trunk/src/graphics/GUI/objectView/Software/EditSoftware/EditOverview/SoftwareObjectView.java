package graphics.GUI.objectView.Software.EditSoftware.EditOverview;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;
import graphics.GUI.objectView.Software.NewSoftware.NewOverview.NewSoftwareChoice;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import objects.Clients;
import objects.Object;
import objects.Servers;
import objects.softwareObjects.Antivirus;
import objects.softwareObjects.Backup;
import objects.softwareObjects.Database;
import objects.softwareObjects.Email;
import objects.softwareObjects.Firewall;
import objects.softwareObjects.OfficeSuite;
import objects.softwareObjects.OperatingSystem;
import objects.softwareObjects.Proxy;
import objects.softwareObjects.SecuritySuite;
import objects.softwareObjects.Webserver;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
@SuppressWarnings("serial")
public class SoftwareObjectView extends JPanel implements ActionListener
{

	private Object givenObject = null;

	private SoftwareEditor SWeditor = null;



	/**
	 * TODO - Description NEEDED!
	 */
	public SoftwareObjectView(Object obj)
	{
		populiateInfo(obj);
	}


	/**
	 * Creates and places JPanels with some information about the internal
	 * components of the given object. It packs the JPanel and places them two
	 * in a row.
	 * 
	 * @param obj
	 */
	public void populiateInfo(Object obj)
	{
		givenObject = obj;

		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();


		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		// d.anchor = GridBagConstraints.NORTH; // location
		// d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column

		ImageIcon temp = null;

		Object[] swObj = obj.getSoftware();

		String[] info = null;


		JPanel swPanel = new JPanel(new GridBagLayout());
		GridBagConstraints d2 = new GridBagConstraints();


		d2.fill = GridBagConstraints.BOTH;
		// d2.ipady = 0; // reset to default
		// d2.ipadx = 0; // reset to default
		// d2.weighty = 1.0; // request any extra vertical space
		d2.weightx = 0.1; // request any extra horizontal space
		d2.anchor = GridBagConstraints.NORTHWEST; // location
		d2.insets = new Insets(10, 10, 10, 10); // padding
		// d2.gridwidth = 1; // 2 row wide
		d2.gridheight = 1; // 2 columns wide
		d2.gridy = 0; // row
		d2.gridx = 0; // column


		// If there are no software objects.
		if ( swObj != null )
		{
			for ( int i = 0; i < swObj.length; i++ )
			{
				if ( swObj[i] instanceof Antivirus )
				{
					temp = PrimeMain1.objectImageIcons.get(Antivirus.class);

					Antivirus antiVirusObj = (Antivirus) swObj[i];

					info = new String[4];

					String text = null;

					text = antiVirusObj.getObjectName();
					if ( text != "" && text != null )
					{
						info[0] = text;
					}

					text = antiVirusObj.getVersion();
					if ( text != "" && text != null )
					{
						info[1] = PrimeMain1.texts
								.getString("swTabVersionLabel")
								+ ": " + text;
					}

					Date date = antiVirusObj.getExpirationDate();
					if ( date != null )
					{
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						info[2] = PrimeMain1.texts
								.getString("swTabExpiresLabel")
								+ ": "
								+ Calendar.DAY_OF_MONTH
								+ "/"
								+ Calendar.MONTH + "/" + Calendar.YEAR;
					}

					text = antiVirusObj.getDescription();
					if ( text != "" && text != null )
					{
						info[3] = PrimeMain1.texts
								.getString("swTabDescriptionLabel")
								+ ": " + text;
					}

				}
				else if ( swObj[i] instanceof Backup )
				{
					temp = PrimeMain1.objectImageIcons.get(Backup.class);

					Backup backupObj = (Backup) swObj[i];

					info = new String[3];

					String text = null;

					text = backupObj.getObjectName();
					if ( text != "" && text != null )
					{
						info[0] = text;
					}

					text = backupObj.getVersion();
					if ( text != "" && text != null )
					{
						info[1] = PrimeMain1.texts
								.getString("swTabVersionLabel")
								+ ": " + text;
					}

					text = backupObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = PrimeMain1.texts
								.getString("swTabDescriptionLabel")
								+ ": " + text;
					}

				}
				else if ( swObj[i] instanceof Database )
				{
					temp = PrimeMain1.objectImageIcons.get(Database.class);

					Database hddObj = (Database) swObj[i];

					info = new String[3];

					String text = null;

					text = hddObj.getObjectName();
					if ( text != "" && text != null )
					{
						info[0] = text;
					}

					text = hddObj.getVersion();
					if ( text != "" && text != null )
					{
						info[1] = PrimeMain1.texts
								.getString("swTabProducerLabel")
								+ ": " + text;
					}

					text = hddObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = PrimeMain1.texts
								.getString("swTabDescriptionLabel")
								+ ": " + text;
					}

				}
				else if ( swObj[i] instanceof Email )
				{
					temp = PrimeMain1.objectImageIcons.get(Email.class);

					Email emailObj = (Email) swObj[i];

					info = new String[3];

					String text = null;

					text = emailObj.getObjectName();
					if ( text != "" && text != null )
					{
						info[0] = text;
					}

					text = emailObj.getVersion();
					if ( text != "" && text != null )
					{
						info[1] = PrimeMain1.texts
								.getString("swTabVersionLabel")
								+ ": " + text;
					}

					text = emailObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = PrimeMain1.texts
								.getString("swTabDescriptionLabel")
								+ ": " + text;
					}

				}
				else if ( swObj[i] instanceof Firewall )
				{
					temp = PrimeMain1.objectImageIcons.get(Firewall.class);

					Firewall firewallObj = (Firewall) swObj[i];

					info = new String[3];

					String text = null;

					text = firewallObj.getObjectName();
					if ( text != "" && text != null )
					{
						info[0] = text;
					}

					text = firewallObj.getVersion();
					if ( text != "" && text != null )
					{
						info[1] = PrimeMain1.texts
								.getString("swTabVersionLabel")
								+ ": " + text;
					}

					text = firewallObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = PrimeMain1.texts
								.getString("swTabDescriptionLabel")
								+ ": " + text;
					}
				}
				else if ( swObj[i] instanceof OfficeSuite )
				{
					temp = PrimeMain1.objectImageIcons.get(OfficeSuite.class);

					OfficeSuite OffSuitObj = (OfficeSuite) swObj[i];

					info = new String[5];

					String text = null;

					text = OffSuitObj.getObjectName();
					if ( text != "" && text != null )
					{
						info[0] = text;
					}

					text = OffSuitObj.getVersion();
					if ( text != "" && text != null )
					{
						info[1] = PrimeMain1.texts
								.getString("swTabVersionLabel")
								+ ": " + text;
					}

					text = OffSuitObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = PrimeMain1.texts
								.getString("swTabDescriptionLabel")
								+ ": " + text;
					}
				}
				else if ( swObj[i] instanceof OperatingSystem )
				{
					temp = PrimeMain1.objectImageIcons
							.get(OperatingSystem.class);

					OperatingSystem OSObj = (OperatingSystem) swObj[i];

					info = new String[3];

					String text = null;

					text = OSObj.getObjectName();
					if ( text != "" && text != null )
					{
						info[0] = text;
					}

					text = OSObj.getVersion();
					if ( text != "" && text != null )
					{
						info[1] = PrimeMain1.texts
								.getString("swTabVersionLabel")
								+ ": " + text;
					}

					text = OSObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = PrimeMain1.texts
								.getString("swTabDescriptionLabel")
								+ ": " + text;
					}

				}
				else if ( swObj[i] instanceof Proxy )
				{
					temp = PrimeMain1.objectImageIcons.get(Proxy.class);

					Proxy proxyObj = (Proxy) swObj[i];

					info = new String[3];

					String text = null;

					text = proxyObj.getObjectName();
					if ( text != "" && text != null )
					{
						info[0] = text;
					}

					text = proxyObj.getVersion();
					if ( text != "" && text != null )
					{
						info[1] = PrimeMain1.texts
								.getString("swTabProducerLabel")
								+ ": " + text;
					}

					text = proxyObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = PrimeMain1.texts
								.getString("swTabDescriptionLabel")
								+ ": " + text;
					}

				}
				else if ( swObj[i] instanceof SecuritySuite )
				{
					temp = PrimeMain1.objectImageIcons.get(SecuritySuite.class);

					SecuritySuite secSuiteObj = (SecuritySuite) swObj[i];

					info = new String[3];

					String text = null;

					text = secSuiteObj.getObjectName();
					if ( text != "" && text != null )
					{
						info[0] = text;
					}

					text = secSuiteObj.getVersion();
					if ( text != "" && text != null )
					{
						info[1] = PrimeMain1.texts
								.getString("swTabProducerLabel")
								+ ": " + text;
					}

					text = secSuiteObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = PrimeMain1.texts
								.getString("swTabDescriptionLabel")
								+ ": " + text;
					}

				}
				else if ( swObj[i] instanceof Webserver )
				{
					temp = PrimeMain1.objectImageIcons.get(Webserver.class);

					Webserver webServObj = (Webserver) swObj[i];

					info = new String[3];

					String text = null;

					text = webServObj.getObjectName();
					if ( text != "" && text != null )
					{
						info[0] = text;
					}

					text = webServObj.getVersion();
					if ( text != "" && text != null )
					{
						info[1] = PrimeMain1.texts
								.getString("swTabProducerLabel")
								+ ": " + text;
					}

					text = webServObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = PrimeMain1.texts
								.getString("swTabDescriptionLabel")
								+ ": " + text;
					}

				}

				assert temp != null;


				if ( i == 0 )
				{
					d2.gridx = 0;
					d2.gridy = 0;
				}
				else if ( i % 2 == 0 )
				{
					d2.gridx = 0;
					d2.gridy++;
				}
				else
				{
					d2.gridx = 1;
				}



				JPanel panel = createSoftwareJPanel(info, temp);
				panel.addMouseListener(new SoftwareMouseListener(panel,
						givenObject, swObj[i]));
				swPanel.add(panel, d2);
			}
		}


		if ( swObj.length < 2 )
		{
			if ( swPanel.getComponentCount() == 1 )
			{
				// Adds a big JPanel at the bottom right to take the remaining space
				JPanel emptyPanel1 = new JPanel();
				d2.weightx = 0.30; // request any extra horizontal space
				d2.gridx = 1;
				d2.gridy = 0;
				d2.gridheight = 1;
				d2.gridwidth = 1;
				// d2.weighty = 1.0;
				swPanel.add(emptyPanel1, d2);
			}
		}

		JPanel emptyPanel = new JPanel();
		d2.gridx = 0;
		d2.gridy++;
		d2.gridwidth = 2;
		d2.weighty = 1.0;
		swPanel.add(emptyPanel, d2);


		JScrollPane hwScroll = new JScrollPane(swPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		hwScroll.setBorder(BorderFactory.createEmptyBorder());

		this.add(hwScroll, d);




		d.fill = GridBagConstraints.VERTICAL;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 0.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.BASELINE_TRAILING; // location
		d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 1; // row
		d.gridx = 0; // column


		// Adds the panel with the buttons to the main panel
		this.add(getButtonsPanel(obj), d);
	}



	/**
	 * The buttons to the bottom of the Object view.
	 */
	private JPanel getButtonsPanel(Object obj)
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));


		if ( obj instanceof Clients || obj instanceof Servers )
		{
			Button edit = new Button(PrimeMain1.texts
					.getString("swTabEditSoftwareButtonLabel"));
			edit.addActionListener(this);
			edit.setActionCommand("edit");

			Button addNew = new Button(PrimeMain1.texts
					.getString("swTabNewSoftwareButtonLabel"));
			addNew.addActionListener(this);
			addNew.setActionCommand("newSoft");

			buttons.add(edit);
			buttons.add(addNew);

			return buttons;
		}
		else
		{
			JLabel text = new JLabel(PrimeMain1.texts
					.getString("swTabRestrictionText"));
			buttons.add(text);
		}


		return buttons;
	}





	/**
	 * Creates a JPanel and adds the given Icon and Strings. The strings are
	 * place vertical.
	 * 
	 * @param texts
	 *            The strings with the information about the Hardware component.
	 * @param icon
	 *            The ImageIcon that will represent the Hardware component.
	 * @return Returns a JPanel with both the ImageIcon and the hardware
	 *         information.
	 */
	public static JPanel createSoftwareJPanel(String[] texts, ImageIcon icon)
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBackground(Color.white);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.NONE;


		// Icon image.
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = texts.length;

		JLabel image = new JLabel(icon);
		panel.add(image, c);


		// New constraints for text
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.CENTER;

		d.gridx = 1;
		d.gridy = 0;
		d.weightx = 1;
		d.weighty = 0.2;
		d.gridwidth = 1;
		d.gridheight = 1;

		/**
		 * Adds the given texts to the panel.
		 */
		for ( int i = 0; i < texts.length; i++ )
		{
			if ( texts[i] != null )
			{
				JLabel text = new JLabel(GraphicalFunctions
						.verifyDescriptionLength(texts[i]));
				// text.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(text, d);
				d.gridy++;
			}
		}


		return panel;
	}



	/**
	 * Gets the software editor that where software can be edited.
	 */
	public SoftwareEditor createNewSoftwareEditor(Object obj)
	{
		return new SoftwareEditor(obj);
	}





	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("edit") )
		{
			if ( givenObject.getSoftware() != null
					&& givenObject.getSoftware().length != 0 )
			{
				SWeditor = new SoftwareEditor(givenObject);
			}
		}
		else if ( e.getActionCommand().equals("newSoft") )
		{
			new NewSoftwareChoice(givenObject);
		}
	}




	/**
	 * This method removes all the JPanels showing the components information
	 * and then creates them again with the current information. This method is
	 * used when hardware information is changed or a component is added or
	 * removed. It also calls the update function in the Hardware Editor view.
	 */
	public void updateTabInfo()
	{
		if ( SWeditor != null )
		{
			SWeditor.SoftwarePanelRevalidate();
		}

		this.removeAll();

		this.populiateInfo(givenObject);

		this.repaint();
		this.revalidate();
	}
}