package graphics.GUI.objectView.Software.EditSoftware.EditOverview;


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
 */
public class SoftwareEditorTabbed extends JTabbedPane
{

	private Object mainobj;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public SoftwareEditorTabbed(Object obj)
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


		String ending = " options and values.";


		for ( int i = 0; i < software.length; i++ )
		{
			if ( software[i] instanceof Antivirus )
			{
				String desc = "Antivirus" + ending;
				this.addTab("Antivirus", null, new AntivirusEditView(obj,
						(Antivirus) software[i]), desc);
			}
			else if ( software[i] instanceof Backup )
			{
				String desc = "Backup" + ending;
				this.addTab("Backup", null, new BackupEditView(obj,
						(Backup) software[i]), desc);
			}
			else if ( software[i] instanceof Database )
			{
				String desc = "Database" + ending;
				this.addTab("Database", null, new DatabaseEditView(obj,
						(Database) software[i]), desc);
			}
			else if ( software[i] instanceof Email )
			{
				String desc = "Email" + ending;
				this.addTab("Email", null, new EmailEditView(obj,
						(Email) software[i]), desc);
			}
			else if ( software[i] instanceof Firewall )
			{
				String desc = "Firewall" + ending;
				this.addTab("Firewall", null, new FirewallEditView(obj,
						(Firewall) software[i]), desc);
			}
			else if ( software[i] instanceof OfficeSuite )
			{
				String desc = "OfficeSuite" + ending;
				this.addTab("OfficeSuite", null, new OfficeSuiteEditView(obj,
						(OfficeSuite) software[i]), desc);
			}
			else if ( software[i] instanceof OperatingSystem )
			{
				String desc = "OperatingSystem" + ending;
				this.addTab("OperatingSystem", null, new OSEditView(obj,
						(OperatingSystem) software[i]), desc);
			}
			else if ( software[i] instanceof Proxy )
			{
				String desc = "Proxy" + ending;
				this.addTab("Proxy", null, new ProxyEditView(obj,
						(Proxy) software[i]), desc);
			}
			else if ( software[i] instanceof SecuritySuite )
			{
				String desc = "SecuritySuite" + ending;
				this.addTab("SecuritySuite", null, new SecuritySuiteEditView(
						obj, (SecuritySuite) software[i]), desc);
			}
			else if ( software[i] instanceof Webserver )
			{
				String desc = "Webserver" + ending;
				this.addTab("Webserver", null, new WebserverEditView(obj,
						(Webserver) software[i]), desc);
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
	}


}
