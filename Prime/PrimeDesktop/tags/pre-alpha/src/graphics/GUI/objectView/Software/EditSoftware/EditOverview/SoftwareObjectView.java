package graphics.GUI.objectView.Software.EditSoftware.EditOverview;


import graphics.ImageLocator;
import graphics.GUI.objectView.Software.NewSoftware.NewOverview.NewSoftwareChoice;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
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
	 * Creates and places JPanels with some information about the internal components of the given object. It packs the
	 * JPanel and places them two in a row.
	 * 
	 * @param obj
	 */
	public void populiateInfo(Object obj)
	{
		givenObject = obj;

		int swCount = 0;

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);

		ImageIcon temp = null;

		Object[] swObj = obj.getSoftware();

		String[] info = null;

		// If there are no software objects.
		if ( swObj != null )
		{
			for ( int i = 0; i < swObj.length; i++ )
			{
				if ( swObj[i] instanceof Antivirus )
				{
					temp = ImageLocator.getImageIconObject("Antivirus-Software");

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
						info[1] = "Version: " + text;
					}

					Date d = antiVirusObj.getExpirationDate();
					if ( d != null )
					{
						Calendar cal = Calendar.getInstance();
						cal.setTime(d);
						info[2] = "Expires: " + Calendar.DAY_OF_MONTH + "/" + Calendar.MONTH + "/" + Calendar.YEAR;
					}

					text = antiVirusObj.getDescription();
					if ( text != "" && text != null )
					{
						info[3] = "Description: " + text;
					}

				}
				else if ( swObj[i] instanceof Backup )
				{
					temp = ImageLocator.getImageIconObject("Backup-Software");

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
						info[1] = "Version: " + text;
					}

					text = backupObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = "Description: " + text;
					}

				}
				else if ( swObj[i] instanceof Database )
				{
					temp = ImageLocator.getImageIconObject("Database-Software");

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
						info[1] = "Producer: " + text;
					}

					text = hddObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = "Description: " + text;
					}

				}
				else if ( swObj[i] instanceof Email )
				{
					temp = ImageLocator.getImageIconObject("Email-Software");

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
						info[1] = "Version: " + text;
					}

					text = emailObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = "Description: " + text;
					}

				}
				else if ( swObj[i] instanceof Firewall )
				{
					temp = ImageLocator.getImageIconObject("Firewall-Software");

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
						info[1] = "Version: " + text;
					}

					text = firewallObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = "Description: " + text;
					}
				}
				else if ( swObj[i] instanceof OfficeSuite )
				{
					temp = ImageLocator.getImageIconObject("OfficeSuite-Software");

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
						info[1] = "Version: " + text;
					}

					text = OffSuitObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = "Description: " + text;
					}
				}
				else if ( swObj[i] instanceof OperatingSystem )
				{
					temp = ImageLocator.getImageIconObject("OperatingSystem-Software");

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
						info[1] = "Version: " + text;
					}

					text = OSObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = "Description: " + text;
					}

				}
				else if ( swObj[i] instanceof Proxy )
				{
					temp = ImageLocator.getImageIconObject("Proxy-Software");

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
						info[1] = "Producer: " + text;
					}

					text = proxyObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = "Description: " + text;
					}

				}
				else if ( swObj[i] instanceof SecuritySuite )
				{
					temp = ImageLocator.getImageIconObject("SecuritySuite-Software");

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
						info[1] = "Producer: " + text;
					}

					text = secSuiteObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = "Description: " + text;
					}

				}
				else if ( swObj[i] instanceof Webserver )
				{
					temp = ImageLocator.getImageIconObject("Webserver-Software");

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
						info[1] = "Producer: " + text;
					}

					text = webServObj.getDescription();
					if ( text != "" && text != null )
					{
						info[2] = "Description: " + text;
					}

				}

				assert temp != null;

				swCount++;


				this.add(createSoftwareJPanel(info, temp), c);

				if ( swCount % 2 == 0 )
				{
					c.gridx = 0;
					c.gridy++;
				}
				else
				{
					c.gridx++;
				}
			}
		}
		/**
		 * Creates empty JPanels and adds them to the main panel until there are 8 panels in the main panel. This is
		 * done so that the panels that actually have content will be placed correctly.
		 */
		while ( swCount < 8 )
		{
			JPanel panel = new JPanel();
			this.add(panel, c);

			swCount++;

			if ( swCount % 2 == 0 )
			{
				c.gridx = 0;
				c.gridy++;
			}
			else
			{
				c.gridx++;
			}
		}



		JLabel temp1 = new JLabel("");
		temp1.setMaximumSize(new Dimension(90, 20));
		temp1.setPreferredSize(new Dimension(90, 20));


		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.BOTH;

		d.gridx = 0;

		if ( swCount % 2 == 0 )
		{
			d.gridy = c.gridy++;
		}
		else
		{
			// Adds an empty components so that the spacing comes out correct.
			// Because of the SpringUtilities.makeCompactGrid.
			this.add(temp1);
			d.gridy = c.gridy++;
		}


		d.gridy = c.gridy++;
		d.weightx = 1;
		d.weighty = 1;
		d.gridwidth = 1;
		d.gridheight = 1;
		d.insets = new Insets(10, 10, 10, 10);


		if ( obj instanceof Clients || obj instanceof Servers )
		{
			JPanel buttons = new JPanel();
			buttons.setLayout(new FlowLayout(FlowLayout.LEADING));

			Button edit = new Button("Edit Software");
			edit.addActionListener(this);
			edit.setActionCommand("edit");

			Button addNew = new Button("Install Software");
			addNew.addActionListener(this);
			addNew.setActionCommand("newSoft");

			buttons.add(edit);
			buttons.add(addNew);

			this.add(buttons, d);
		}
		else
		{
			JPanel note = new JPanel();
			note.setLayout(new FlowLayout(FlowLayout.LEADING));

			JLabel text = new JLabel("You can currently not add software to infrastructure or peripherals.");
			note.add(text);

			this.add(note, d);
		}

	}





	/**
	 * Creates a JPanel and adds the given Icon and Strings. The strings are place vertical.
	 * 
	 * @param texts
	 *            The strings with the information about the Hardware component.
	 * @param icon
	 *            The ImageIcon that will represent the Hardware component.
	 * @return Returns a JPanel with both the ImageIcon and the hardware information.
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
				JLabel text = new JLabel(texts[i]);
				// text.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(text, d);
				d.gridy++;
			}
		}


		return panel;
	}






	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("edit") )
		{
			SWeditor = new SoftwareEditor(givenObject);
		}
		else if ( e.getActionCommand().equals("newSoft") )
		{
			new NewSoftwareChoice(givenObject);
		}
	}




	/**
	 * This method removes all the JPanels showing the components information and then creates them again with the
	 * current information. This method is used when hardware information is changed or a component is added or removed.
	 * It also calls the update function in the Hardware Editor view.
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