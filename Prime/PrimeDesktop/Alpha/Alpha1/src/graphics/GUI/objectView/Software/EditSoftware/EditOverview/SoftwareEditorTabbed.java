package graphics.GUI.objectView.Software.EditSoftware.EditOverview;


import graphics.PrimeMain1;
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

		for ( int i = 0; i < software.length; i++ )
		{
			if ( software[i] instanceof Antivirus )
			{
				this.addTab(PrimeMain1.texts.getString("antivirus"), null,
						new AntivirusEditView(obj, (Antivirus) software[i]),
						PrimeMain1.texts.getString("swTabAVtabDescription"));
			}
			else if ( software[i] instanceof Backup )
			{
				this
						.addTab(PrimeMain1.texts.getString("backup"), null,
								new BackupEditView(obj, (Backup) software[i]),
								PrimeMain1.texts
										.getString("swTabBackupTabDescription"));
			}
			else if ( software[i] instanceof Database )
			{
				this.addTab(PrimeMain1.texts.getString("database"), null,
						new DatabaseEditView(obj, (Database) software[i]),
						PrimeMain1.texts
								.getString("swTabDatabaseTabDescription"));
			}
			else if ( software[i] instanceof Email )
			{
				this.addTab(PrimeMain1.texts.getString("email"), null,
						new EmailEditView(obj, (Email) software[i]),
						PrimeMain1.texts.getString("swTabEmailTabDescription"));
			}
			else if ( software[i] instanceof Firewall )
			{
				this.addTab(PrimeMain1.texts.getString("firewall"), null,
						new FirewallEditView(obj, (Firewall) software[i]),
						PrimeMain1.texts
								.getString("swTabFirewallTabDescription"));
			}
			else if ( software[i] instanceof OfficeSuite )
			{
				this
						.addTab(
								PrimeMain1.texts.getString("officeSuite"),
								null,
								new OfficeSuiteEditView(obj,
										(OfficeSuite) software[i]),
								PrimeMain1.texts
										.getString("swTabOfficeSuiteTabDescription"));
			}
			else if ( software[i] instanceof OperatingSystem )
			{
				this.addTab(PrimeMain1.texts.getString("operatingSystem"),
						null,
						new OSEditView(obj, (OperatingSystem) software[i]),
						PrimeMain1.texts.getString("swTabOStabDescription"));
			}
			else if ( software[i] instanceof Proxy )
			{
				this.addTab(PrimeMain1.texts.getString("proxy"), null,
						new ProxyEditView(obj, (Proxy) software[i]),
						PrimeMain1.texts.getString("swTabProxyTabDescription"));
			}
			else if ( software[i] instanceof SecuritySuite )
			{
				this.addTab(PrimeMain1.texts.getString("securitySuite"), null,
						new SecuritySuiteEditView(obj,
								(SecuritySuite) software[i]), PrimeMain1.texts
								.getString("swTabSecSuiteTabDescription"));
			}
			else if ( software[i] instanceof Webserver )
			{
				this.addTab(PrimeMain1.texts.getString("webserver"), null,
						new WebserverEditView(obj, (Webserver) software[i]),
						PrimeMain1.texts
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

		ObjectView view = PrimeMain1.getObjectView(mainobj);
		if ( view != null )
		{
			view.updateViewInfo();
		}
	}


}
