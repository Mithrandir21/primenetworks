/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.objectView.Software.NewSoftware.NewOverview;


import graphics.PrimeMain;
import graphics.GUI.customOSviews.osSelectionOverView;
import graphics.GUI.objectView.Software.EditSoftware.EditOverview.SoftwareObjectView;
import graphics.GUI.objectView.Software.NewSoftware.NewViews.AntivirusNewView;
import graphics.GUI.objectView.Software.NewSoftware.NewViews.BackupNewView;
import graphics.GUI.objectView.Software.NewSoftware.NewViews.DatabaseNewView;
import graphics.GUI.objectView.Software.NewSoftware.NewViews.EmailNewView;
import graphics.GUI.objectView.Software.NewSoftware.NewViews.FirewallNewView;
import graphics.GUI.objectView.Software.NewSoftware.NewViews.GenericSoftwareNewView;
import graphics.GUI.objectView.Software.NewSoftware.NewViews.NASnewView;
import graphics.GUI.objectView.Software.NewSoftware.NewViews.OfficeSuiteNewView;
import graphics.GUI.objectView.Software.NewSoftware.NewViews.ProxyNewView;
import graphics.GUI.objectView.Software.NewSoftware.NewViews.RemoteDesktopNewView;
import graphics.GUI.objectView.Software.NewSoftware.NewViews.SecuritySuiteNewView;
import graphics.GUI.objectView.Software.NewSoftware.NewViews.VirtualizationNewView;
import graphics.GUI.objectView.Software.NewSoftware.NewViews.WebserverNewView;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;

import objects.Object;
import objects.softwareObjects.Antivirus;
import objects.softwareObjects.Backup;
import objects.softwareObjects.Database;
import objects.softwareObjects.Email;
import objects.softwareObjects.Firewall;
import objects.softwareObjects.GenericSoftware;
import objects.softwareObjects.NASsoftware;
import objects.softwareObjects.OfficeSuite;
import objects.softwareObjects.OperatingSystem;
import objects.softwareObjects.Proxy;
import objects.softwareObjects.RemoteDesktop;
import objects.softwareObjects.SecuritySuite;
import objects.softwareObjects.VirtualizationSoftware;
import objects.softwareObjects.Webserver;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class NewSoftwaresViews extends JPanel implements MouseListener
{
	private JPanel genPanel = null;

	private JPanel avPanel = null;

	private JPanel backupPanel = null;

	private JPanel nasPanel = null;

	private JPanel dbPanel = null;

	private JPanel virtPanel = null;

	private JPanel emailPanel = null;

	private JPanel fwPanel = null;

	private JPanel offSuitePanel = null;

	private JPanel osPanel = null;

	private JPanel proxyPanel = null;

	private JPanel secSuitePanel = null;

	private JPanel webserverPanel = null;

	private JPanel remDesktopPanel = null;

	private GenericSoftware genObj = null;

	private Antivirus avobj = null;

	private NASsoftware nasObj = null;

	private Backup backupObj = null;

	private VirtualizationSoftware virtObj = null;

	private Database dbObj = null;

	private Email emailObj = null;

	private Firewall fwObj = null;

	private OfficeSuite offSuiteObj = null;

	// private OperatingSystem osObj = null;

	private Proxy proxyObj = null;

	private SecuritySuite secSuiteObj = null;

	private Webserver webServerObj = null;

	private RemoteDesktop remDeskObj = null;


	private Object mainObj = null;



	public NewSoftwaresViews(Object obj)
	{
		mainObj = obj;

		this.setLayout(new GridLayout(0, 2, 3, 5));


		// Generic software
		ImageIcon gentemp = PrimeMain.objectImageIcons
				.get(GenericSoftware.class);

		String[] genInfo = new String[3];

		genObj = PrimeMain.standard_software.getSt_GenSoftware();

		String text = null;

		text = genObj.getObjectName();
		if ( text != "" && text != null )
		{
			genInfo[0] = text;
		}

		text = genObj.getVersion();
		if ( text != "" && text != null )
		{
			genInfo[1] = PrimeMain.texts.getString("swTabVersionLabel") + ": "
					+ text;
		}

		text = genObj.getDescription();
		if ( text != "" && text != null )
		{
			genInfo[2] = PrimeMain.texts.getString("swTabDescriptionLabel")
					+ ": " + text;
		}


		assert gentemp != null;

		// Create Generic Software JPanel
		genPanel = SoftwareObjectView.createSoftwareJPanel(genInfo, gentemp);
		genPanel.addMouseListener(this);
		genPanel.setName("General Software");



		// Antivirus software
		ImageIcon avtemp = PrimeMain.objectImageIcons.get(Antivirus.class);

		String[] avInfo = new String[3];

		avobj = PrimeMain.standard_software.getSt_AV();

		text = null;

		text = avobj.getObjectName();
		if ( text != "" && text != null )
		{
			avInfo[0] = text;
		}

		text = avobj.getVersion();
		if ( text != "" && text != null )
		{
			avInfo[1] = PrimeMain.texts.getString("swTabVersionLabel") + ": "
					+ text;
		}

		text = avobj.getDescription();
		if ( text != "" && text != null )
		{
			avInfo[2] = PrimeMain.texts.getString("swTabDescriptionLabel")
					+ ": " + text;
		}


		assert avtemp != null;

		// Create antivirus JPanel
		avPanel = SoftwareObjectView.createSoftwareJPanel(avInfo, avtemp);
		avPanel.addMouseListener(this);
		avPanel.setName("Avtivirus");




		// Backup software
		ImageIcon backtemp = PrimeMain.objectImageIcons.get(Backup.class);

		String[] backInfo = new String[3];

		backupObj = PrimeMain.standard_software.getSt_Backup();

		text = null;

		text = backupObj.getObjectName();
		if ( text != "" && text != null )
		{
			backInfo[0] = text;
		}

		text = backupObj.getVersion();
		if ( text != "" && text != null )
		{
			backInfo[1] = PrimeMain.texts.getString("swTabVersionLabel") + ": "
					+ text;
		}

		text = backupObj.getDescription();
		if ( text != "" && text != null )
		{
			backInfo[2] = PrimeMain.texts.getString("swTabDescriptionLabel")
					+ ": " + text;
		}


		assert backtemp != null;

		// Create backup JPanel
		backupPanel = SoftwareObjectView.createSoftwareJPanel(backInfo,
				backtemp);
		backupPanel.addMouseListener(this);
		backupPanel.setName("Backup");



		// NasSoftware software
		ImageIcon nastemp = PrimeMain.objectImageIcons.get(NASsoftware.class);

		String[] nasInfo = new String[3];

		nasObj = PrimeMain.standard_software.getSt_NASSoftware();

		text = null;

		text = nasObj.getObjectName();
		if ( text != "" && text != null )
		{
			nasInfo[0] = text;
		}

		text = nasObj.getVersion();
		if ( text != "" && text != null )
		{
			nasInfo[1] = PrimeMain.texts.getString("swTabVersionLabel") + ": "
					+ text;
		}

		text = nasObj.getDescription();
		if ( text != "" && text != null )
		{
			nasInfo[2] = PrimeMain.texts.getString("swTabDescriptionLabel")
					+ ": " + text;
		}


		assert nastemp != null;

		// Create NasSoftware JPanel
		nasPanel = SoftwareObjectView.createSoftwareJPanel(nasInfo, nastemp);
		nasPanel.addMouseListener(this);
		nasPanel.setName("NAS");




		// Database software
		ImageIcon dbtemp = PrimeMain.objectImageIcons.get(Database.class);

		String[] dbInfo = new String[3];

		dbObj = PrimeMain.standard_software.getSt_Database();

		text = null;

		text = dbObj.getObjectName();
		if ( text != "" && text != null )
		{
			dbInfo[0] = text;
		}

		text = dbObj.getVersion();
		if ( text != "" && text != null )
		{
			dbInfo[1] = PrimeMain.texts.getString("swTabVersionLabel") + ": "
					+ text;
		}

		text = dbObj.getDescription();
		if ( text != "" && text != null )
		{
			dbInfo[2] = PrimeMain.texts.getString("swTabDescriptionLabel")
					+ ": " + text;
		}


		assert dbtemp != null;

		// Create database JPanel
		dbPanel = SoftwareObjectView.createSoftwareJPanel(dbInfo, dbtemp);
		dbPanel.addMouseListener(this);
		dbPanel.setName("Database");



		// Virtualization software
		ImageIcon virttemp = PrimeMain.objectImageIcons
				.get(VirtualizationSoftware.class);

		String[] virtInfo = new String[3];

		virtObj = PrimeMain.standard_software.getSt_VirtualizationSoftware();

		text = null;

		text = virtObj.getObjectName();
		if ( text != "" && text != null )
		{
			virtInfo[0] = text;
		}

		text = virtObj.getVersion();
		if ( text != "" && text != null )
		{
			virtInfo[1] = PrimeMain.texts.getString("swTabVersionLabel") + ": "
					+ text;
		}

		text = virtObj.getDescription();
		if ( text != "" && text != null )
		{
			virtInfo[2] = PrimeMain.texts.getString("swTabDescriptionLabel")
					+ ": " + text;
		}


		assert virttemp != null;

		// Create NasSoftware JPanel
		virtPanel = SoftwareObjectView.createSoftwareJPanel(virtInfo, virttemp);
		virtPanel.addMouseListener(this);
		virtPanel.setName("Virtualization");



		// Email software
		ImageIcon Emailtemp = PrimeMain.objectImageIcons.get(Email.class);

		String[] emailInfo = new String[3];

		emailObj = PrimeMain.standard_software.getSt_Email();

		text = null;

		text = emailObj.getObjectName();
		if ( text != "" && text != null )
		{
			emailInfo[0] = text;
		}

		text = emailObj.getVersion();
		if ( text != "" && text != null )
		{
			emailInfo[1] = PrimeMain.texts.getString("swTabVersionLabel")
					+ ": " + text;
		}

		text = emailObj.getDescription();
		if ( text != "" && text != null )
		{
			emailInfo[2] = PrimeMain.texts.getString("swTabDescriptionLabel")
					+ ": " + text;
		}


		assert Emailtemp != null;

		// Create database JPanel
		emailPanel = SoftwareObjectView.createSoftwareJPanel(emailInfo,
				Emailtemp);
		emailPanel.addMouseListener(this);
		emailPanel.setName("Email");



		// Firewall software
		ImageIcon fwtemp = PrimeMain.objectImageIcons.get(Firewall.class);

		String[] fwInfo = new String[3];

		fwObj = PrimeMain.standard_software.getSt_Firewall();

		text = null;

		text = fwObj.getObjectName();
		if ( text != "" && text != null )
		{
			fwInfo[0] = text;
		}

		text = fwObj.getVersion();
		if ( text != "" && text != null )
		{
			fwInfo[1] = PrimeMain.texts.getString("swTabVersionLabel") + ": "
					+ text;
		}

		text = fwObj.getDescription();
		if ( text != "" && text != null )
		{
			fwInfo[2] = PrimeMain.texts.getString("swTabDescriptionLabel")
					+ ": " + text;
		}


		assert fwtemp != null;

		// Create firewall JPanel
		fwPanel = SoftwareObjectView.createSoftwareJPanel(fwInfo, fwtemp);
		fwPanel.addMouseListener(this);
		fwPanel.setName("Firewall");



		// Office Suite software
		ImageIcon offSuitetemp = PrimeMain.objectImageIcons
				.get(OfficeSuite.class);

		String[] offSuiteInfo = new String[3];

		offSuiteObj = PrimeMain.standard_software.getSt_OfficeSuite();

		text = null;

		text = offSuiteObj.getObjectName();
		if ( text != "" && text != null )
		{
			offSuiteInfo[0] = text;
		}

		text = offSuiteObj.getVersion();
		if ( text != "" && text != null )
		{
			offSuiteInfo[1] = PrimeMain.texts.getString("swTabVersionLabel")
					+ ": " + text;
		}

		text = offSuiteObj.getDescription();
		if ( text != "" && text != null )
		{
			offSuiteInfo[2] = PrimeMain.texts
					.getString("swTabDescriptionLabel") + ": " + text;
		}


		assert offSuitetemp != null;

		// Create Office Suite JPanel
		offSuitePanel = SoftwareObjectView.createSoftwareJPanel(offSuiteInfo,
				offSuitetemp);
		offSuitePanel.addMouseListener(this);
		offSuitePanel.setName("Office Suite");



		// Operating System software
		ImageIcon OStemp = PrimeMain.objectImageIcons
				.get(OperatingSystem.class);

		String[] OSInfo = { PrimeMain.texts.getString("operatingSystem"),
				PrimeMain.texts.getString("swTabOsNameText") };


		assert OStemp != null;

		// Create Operating System JPanel
		osPanel = SoftwareObjectView.createSoftwareJPanel(OSInfo, OStemp);
		osPanel.addMouseListener(this);
		osPanel.setName("Operating System");



		// Proxy software
		ImageIcon proxytemp = PrimeMain.objectImageIcons.get(Proxy.class);

		String[] proxyInfo = new String[3];

		proxyObj = PrimeMain.standard_software.getSt_Proxy();

		text = null;

		text = proxyObj.getObjectName();
		if ( text != "" && text != null )
		{
			proxyInfo[0] = text;
		}

		text = proxyObj.getVersion();
		if ( text != "" && text != null )
		{
			proxyInfo[1] = PrimeMain.texts.getString("swTabVersionLabel")
					+ ": " + text;
		}

		text = proxyObj.getDescription();
		if ( text != "" && text != null )
		{
			proxyInfo[2] = PrimeMain.texts.getString("swTabDescriptionLabel")
					+ ": " + text;
		}


		assert proxytemp != null;

		// Create Proxy JPanel
		proxyPanel = SoftwareObjectView.createSoftwareJPanel(proxyInfo,
				proxytemp);
		proxyPanel.addMouseListener(this);
		proxyPanel.setName("Proxy");



		// Security Suite software
		ImageIcon secSuitetemp = PrimeMain.objectImageIcons
				.get(SecuritySuite.class);

		String[] secSuiteInfo = new String[3];

		secSuiteObj = PrimeMain.standard_software.getSt_SecSuite();

		text = null;

		text = secSuiteObj.getObjectName();
		if ( text != "" && text != null )
		{
			secSuiteInfo[0] = text;
		}

		text = secSuiteObj.getVersion();
		if ( text != "" && text != null )
		{
			secSuiteInfo[1] = PrimeMain.texts.getString("swTabVersionLabel")
					+ ": " + text;
		}

		text = secSuiteObj.getDescription();
		if ( text != "" && text != null )
		{
			secSuiteInfo[2] = PrimeMain.texts
					.getString("swTabDescriptionLabel") + ": " + text;
		}


		assert secSuitetemp != null;

		// Create Security Suite JPanel
		secSuitePanel = SoftwareObjectView.createSoftwareJPanel(secSuiteInfo,
				secSuitetemp);
		secSuitePanel.addMouseListener(this);
		secSuitePanel.setName("Security Suite");



		// Websesrver software
		ImageIcon webSertemp = PrimeMain.objectImageIcons.get(Webserver.class);

		String[] webSerInfo = new String[3];

		webServerObj = PrimeMain.standard_software.getSt_Webserver();

		text = null;

		text = webServerObj.getObjectName();
		if ( text != "" && text != null )
		{
			webSerInfo[0] = text;
		}

		text = webServerObj.getVersion();
		if ( text != "" && text != null )
		{
			webSerInfo[1] = PrimeMain.texts.getString("swTabVersionLabel")
					+ ": " + text;
		}

		text = webServerObj.getDescription();
		if ( text != "" && text != null )
		{
			webSerInfo[2] = PrimeMain.texts.getString("swTabDescriptionLabel")
					+ ": " + text;
		}


		assert webSertemp != null;

		// Create Websesrver JPanel
		webserverPanel = SoftwareObjectView.createSoftwareJPanel(webSerInfo,
				webSertemp);
		webserverPanel.addMouseListener(this);
		webserverPanel.setName("Websesrver");



		// Remote Desktop software
		ImageIcon remDeskTemp = PrimeMain.objectImageIcons
				.get(RemoteDesktop.class);

		String[] remDeskInfo = new String[3];

		remDeskObj = PrimeMain.standard_software.getSt_RemoteDesktopSoftware();

		text = null;

		text = remDeskObj.getObjectName();
		if ( text != "" && text != null )
		{
			remDeskInfo[0] = text;
		}

		text = remDeskObj.getVersion();
		if ( text != "" && text != null )
		{
			remDeskInfo[1] = PrimeMain.texts.getString("swTabVersionLabel")
					+ ": " + text;
		}

		text = remDeskObj.getDescription();
		if ( text != "" && text != null )
		{
			remDeskInfo[2] = PrimeMain.texts.getString("swTabDescriptionLabel")
					+ ": " + text;
		}


		assert remDeskTemp != null;

		// Create Remote Desktop JPanel
		remDesktopPanel = SoftwareObjectView.createSoftwareJPanel(remDeskInfo,
				remDeskTemp);
		remDesktopPanel.addMouseListener(this);
		remDesktopPanel.setName("Remote Desktop");



		this.add(genPanel);
		this.add(avPanel);
		this.add(backupPanel);
		this.add(nasPanel);
		this.add(dbPanel);
		this.add(virtPanel);
		this.add(emailPanel);
		this.add(fwPanel);
		this.add(offSuitePanel);
		this.add(osPanel);
		this.add(proxyPanel);
		this.add(secSuitePanel);
		this.add(webserverPanel);
		this.add(remDesktopPanel);
	}


	@Override
	public void mouseClicked(MouseEvent e)
	{
		JPanel panel = (JPanel) e.getSource();


		if ( panel.getName().equals("General Software") )
		{
			new GenericSoftwareNewView(mainObj, genObj);
			// Creates a new object after the first object is passed to the
			// view.
			genObj = PrimeMain.standard_software.getSt_GenSoftware();
		}
		else if ( panel.getName().equals("Avtivirus") )
		{
			new AntivirusNewView(mainObj, avobj);
			// Creates a new object after the first object is passed to the
			// view.
			avobj = PrimeMain.standard_software.getSt_AV();
		}
		else if ( panel.getName().equals("Backup") )
		{
			new BackupNewView(mainObj, backupObj);
			// Creates a new object after the first object is passed to the
			// view.
			backupObj = PrimeMain.standard_software.getSt_Backup();
		}
		else if ( panel.getName().equals("NAS") )
		{
			new NASnewView(mainObj, nasObj);
			// Creates a new object after the first object is passed to the
			// view.
			nasObj = PrimeMain.standard_software.getSt_NASSoftware();
		}
		else if ( panel.getName().equals("Database") )
		{
			new DatabaseNewView(mainObj, dbObj);
			// Creates a new object after the first object is passed to the
			// view.
			dbObj = PrimeMain.standard_software.getSt_Database();
		}
		else if ( panel.getName().equals("Virtualization") )
		{
			new VirtualizationNewView(mainObj, virtObj);
			// Creates a new object after the first object is passed to the
			// view.
			virtObj = PrimeMain.standard_software
					.getSt_VirtualizationSoftware();
		}
		else if ( panel.getName().equals("Email") )
		{
			new EmailNewView(mainObj, emailObj);
			// Creates a new object after the first object is passed to the
			// view.
			emailObj = PrimeMain.standard_software.getSt_Email();
		}
		else if ( panel.getName().equals("Firewall") )
		{
			new FirewallNewView(mainObj, fwObj);
			// Creates a new object after the first object is passed to the
			// view.
			fwObj = PrimeMain.standard_software.getSt_Firewall();
		}
		else if ( panel.getName().equals("Office Suite") )
		{
			new OfficeSuiteNewView(mainObj, offSuiteObj);
			// Creates a new object after the first object is passed to the
			// view.
			offSuiteObj = PrimeMain.standard_software.getSt_OfficeSuite();
		}
		else if ( panel.getName().equals("Operating System") )
		{
			if ( PrimeMain.osSelect == null )
			{
				PrimeMain.osSelect = new osSelectionOverView(mainObj);
			}
			else
			{
				PrimeMain.osSelect.toFront();
			}
		}
		else if ( panel.getName().equals("Proxy") )
		{
			new ProxyNewView(mainObj, proxyObj);
			// Creates a new object after the first object is passed to the
			// view.
			proxyObj = PrimeMain.standard_software.getSt_Proxy();
		}
		else if ( panel.getName().equals("Security Suite") )
		{
			new SecuritySuiteNewView(mainObj, secSuiteObj);
			// Creates a new object after the first object is passed to the
			// view.
			secSuiteObj = PrimeMain.standard_software.getSt_SecSuite();
		}
		else if ( panel.getName().equals("Webserver") )
		{
			new WebserverNewView(mainObj, webServerObj);
			// Creates a new object after the first object is passed to the
			// view.
			webServerObj = PrimeMain.standard_software.getSt_Webserver();
		}
		else if ( panel.getName().equals("Remote Desktop") )
		{
			new RemoteDesktopNewView(mainObj, remDeskObj);
			// Creates a new object after the first object is passed to the
			// view.
			remDeskObj = PrimeMain.standard_software
					.getSt_RemoteDesktopSoftware();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		JPanel panel = (JPanel) e.getSource();

		Border raised = BorderFactory.createRaisedBevelBorder();

		if ( panel.getName().equals("General Software") )
		{
			genPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Avtivirus") )
		{
			avPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Backup") )
		{
			backupPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("NAS") )
		{
			nasPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Database") )
		{
			dbPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Virtualization") )
		{
			virtPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Email") )
		{
			emailPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Firewall") )
		{
			fwPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Office Suite") )
		{
			offSuitePanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Operating System") )
		{
			osPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Proxy") )
		{
			proxyPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Security Suite") )
		{
			secSuitePanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Webserver") )
		{
			webserverPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Remote Desktop") )
		{
			remDesktopPanel.setBorder(raised);
		}
	}


	@Override
	public void mouseExited(MouseEvent e)
	{
		JPanel panel = (JPanel) e.getSource();

		Border normal = BorderFactory.createEtchedBorder();


		if ( panel.getName().equals("General Software") )
		{
			genPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Avtivirus") )
		{
			avPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Backup") )
		{
			backupPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("NAS") )
		{
			nasPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Database") )
		{
			dbPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Virtualization") )
		{
			virtPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Email") )
		{
			emailPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Firewall") )
		{
			fwPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Office Suite") )
		{
			offSuitePanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Operating System") )
		{
			osPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Proxy") )
		{
			proxyPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Security Suite") )
		{
			secSuitePanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Webserver") )
		{
			webserverPanel.setBorder(normal);
		}
		else if ( panel.getName().equals("Remote Desktop") )
		{
			remDesktopPanel.setBorder(normal);
		}
	}


	@Override
	public void mousePressed(MouseEvent e)
	{
		JPanel panel = (JPanel) e.getSource();

		Border lowered = BorderFactory.createLoweredBevelBorder();


		if ( panel.getName().equals("General Software") )
		{
			genPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Avtivirus") )
		{
			avPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Backup") )
		{
			backupPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("NAS") )
		{
			nasPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Database") )
		{
			dbPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Virtualization") )
		{
			virtPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Email") )
		{
			emailPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Firewall") )
		{
			fwPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Office Suite") )
		{
			offSuitePanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Operating System") )
		{
			osPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Proxy") )
		{
			proxyPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Security Suite") )
		{
			secSuitePanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Webserver") )
		{
			webserverPanel.setBorder(lowered);
		}
		else if ( panel.getName().equals("Remote Desktop") )
		{
			remDesktopPanel.setBorder(lowered);
		}
	}


	@Override
	public void mouseReleased(MouseEvent e)
	{
		JPanel panel = (JPanel) e.getSource();

		Border raised = BorderFactory.createRaisedBevelBorder();


		if ( panel.getName().equals("General Software") )
		{
			genPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Avtivirus") )
		{
			avPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Backup") )
		{
			backupPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("NAS") )
		{
			nasPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Database") )
		{
			dbPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Virtualization") )
		{
			virtPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Email") )
		{
			emailPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Firewall") )
		{
			fwPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Office Suite") )
		{
			offSuitePanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Operating System") )
		{
			osPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Proxy") )
		{
			proxyPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Security Suite") )
		{
			secSuitePanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Webserver") )
		{
			webserverPanel.setBorder(raised);
		}
		else if ( panel.getName().equals("Remote Desktop") )
		{
			remDesktopPanel.setBorder(raised);
		}
	}
}
