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
package graphics.GUI.standardObjectEdit;


import graphics.PrimeMain;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Software.SoftwareView;
import graphics.GUI.objectView.Software.EditSoftware.EditViews.AntivirusEditView;
import graphics.GUI.objectView.Software.EditSoftware.EditViews.BackupEditView;
import graphics.GUI.objectView.Software.EditSoftware.EditViews.DatabaseEditView;
import graphics.GUI.objectView.Software.EditSoftware.EditViews.EmailEditView;
import graphics.GUI.objectView.Software.EditSoftware.EditViews.FirewallEditView;
import graphics.GUI.objectView.Software.EditSoftware.EditViews.OSEditView;
import graphics.GUI.objectView.Software.EditSoftware.EditViews.OfficeSuiteEditView;
import graphics.GUI.objectView.Software.EditSoftware.EditViews.ProxyEditView;
import graphics.GUI.objectView.Software.EditSoftware.EditViews.SecuritySuiteEditView;
import graphics.GUI.objectView.Software.EditSoftware.EditViews.WebserverEditView;

import java.awt.Component;

import javax.swing.JTabbedPane;

import managment.SoftwareManagment;
import objects.Object;
import objects.Software;
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
 * 
 */
public class ObjectSoftwareViewTabbed extends JTabbedPane
{

	private Object mainobj;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public ObjectSoftwareViewTabbed(Object obj)
	{
		mainobj = obj;
		populateTabs(obj);
	}


	/**
	 * Creates and adds tabs to this JTabbedPane instance based on the class of
	 * the software on the given object, such as {@link OperatingSystem},
	 * {@link Antivirus} or {@link Firewall}.
	 * 
	 * @param obj
	 *            The object that holds the software which in turn are the basis
	 *            for the creation of the hardware views.
	 */
	public void populateTabs(Object obj)
	{
		Software[] software = obj.getSoftware();

		for ( int i = 0; i < software.length; i++ )
		{
			if ( software[i] instanceof Antivirus )
			{
				this.addTab(PrimeMain.texts.getString("antivirus"), null,
						new AntivirusEditView(obj, (Antivirus) software[i]),
						PrimeMain.texts.getString("swTabAVtabDescription"));
			}
			else if ( software[i] instanceof Backup )
			{
				this.addTab(PrimeMain.texts.getString("backup"), null,
						new BackupEditView(obj, (Backup) software[i]),
						PrimeMain.texts.getString("swTabBackupTabDescription"));
			}
			else if ( software[i] instanceof Database )
			{
				this.addTab(PrimeMain.texts.getString("database"), null,
						new DatabaseEditView(obj, (Database) software[i]),
						PrimeMain.texts
								.getString("swTabDatabaseTabDescription"));
			}
			else if ( software[i] instanceof Email )
			{
				this.addTab(PrimeMain.texts.getString("email"), null,
						new EmailEditView(obj, (Email) software[i]),
						PrimeMain.texts.getString("swTabEmailTabDescription"));
			}
			else if ( software[i] instanceof Firewall )
			{
				this.addTab(PrimeMain.texts.getString("firewall"), null,
						new FirewallEditView(obj, (Firewall) software[i]),
						PrimeMain.texts
								.getString("swTabFirewallTabDescription"));
			}
			else if ( software[i] instanceof OfficeSuite )
			{
				this.addTab(
						PrimeMain.texts.getString("officeSuite"),
						null,
						new OfficeSuiteEditView(obj, (OfficeSuite) software[i]),
						PrimeMain.texts
								.getString("swTabOfficeSuiteTabDescription"));
			}
			else if ( software[i] instanceof OperatingSystem )
			{
				this.addTab(PrimeMain.texts.getString("operatingSystem"), null,
						new OSEditView(obj, (OperatingSystem) software[i]),
						PrimeMain.texts.getString("swTabOStabDescription"));
			}
			else if ( software[i] instanceof Proxy )
			{
				this.addTab(PrimeMain.texts.getString("proxy"), null,
						new ProxyEditView(obj, (Proxy) software[i]),
						PrimeMain.texts.getString("swTabProxyTabDescription"));
			}
			else if ( software[i] instanceof SecuritySuite )
			{
				this.addTab(PrimeMain.texts.getString("securitySuite"), null,
						new SecuritySuiteEditView(obj,
								(SecuritySuite) software[i]), PrimeMain.texts
								.getString("swTabSecSuiteTabDescription"));
			}
			else if ( software[i] instanceof Webserver )
			{
				this.addTab(PrimeMain.texts.getString("webserver"), null,
						new WebserverEditView(obj, (Webserver) software[i]),
						PrimeMain.texts
								.getString("swTabWebserverTabDescription"));
			}
		}

	}



	/**
	 * This method calls the save methods on all the different SoftwareViews and
	 * if the boolean given is true, calls also the validation methods on all
	 * views. If any of the validations fail, none save methods will be called.
	 */
	public void save()
	{
		/**
		 * Goes through all the views and saves the values since none of the
		 * views failed its validation.
		 */
		for ( int i = 0; i < this.getComponentCount(); i++ )
		{
			Component comp = this.getComponent(i);

			((SoftwareView) comp).save();
		}


		// Process all changes to the software of the object
		SoftwareManagment.processAllChanges(mainobj);

		ObjectView view = PrimeMain.getObjectView(mainobj);
		if ( view != null )
		{
			view.updateViewInfo();
		}
	}
}
