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

import managment.ComponentsManagment;
import objects.Object;
import objects.Software;
import software.Antivirus;
import software.Backup;
import software.Database;
import software.Email;
import software.Firewall;
import software.OfficeSuite;
import software.OperatingSystem;
import software.Proxy;
import software.SecuritySuite;
import software.Webserver;


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
		
		
		for( int i = 0; i < software.length; i++)
		{
			if( software[i] instanceof Antivirus )
			{
				String desc = "Antivirus" + ending;
				this.addTab("Antivirus", null, new AntivirusEditView(obj,
						 (Antivirus) software[i]), desc);
			}
			else if( software[i] instanceof Backup )
			{
				String desc = "Backup" + ending;
				this.addTab("Backup", null, new BackupEditView(obj,
						 (Backup) software[i]), desc);
			}
			else if( software[i] instanceof Database )
			{
				String desc = "Database" + ending;
				this.addTab("Database", null, new DatabaseEditView(obj,
						 (Database) software[i]), desc);
			}
			else if( software[i] instanceof Email )
			{
				String desc = "Email" + ending;
				this.addTab("Email", null, new EmailEditView(obj,
						 (Email) software[i]), desc);
			}
			else if( software[i] instanceof Firewall )
			{
				String desc = "Firewall" + ending;
				this.addTab("Firewall", null, new FirewallEditView(obj,
						 (Firewall) software[i]), desc);
			}
			else if( software[i] instanceof OfficeSuite )
			{
				String desc = "OfficeSuite" + ending;
				this.addTab("OfficeSuite", null, new OfficeSuiteEditView(obj,
						 (OfficeSuite) software[i]), desc);
			}
			else if( software[i] instanceof OperatingSystem )
			{
				String desc = "OperatingSystem" + ending;
				this.addTab("OperatingSystem", null, new OSEditView(obj,
						 (OperatingSystem) software[i]), desc);
			}
			else if( software[i] instanceof Proxy )
			{
				String desc = "Proxy" + ending;
				this.addTab("Proxy", null, new ProxyEditView(obj,
						 (Proxy) software[i]), desc);
			}
			else if( software[i] instanceof SecuritySuite )
			{
				String desc = "SecuritySuite" + ending;
				this.addTab("SecuritySuite", null, new SecuritySuiteEditView(obj,
						 (SecuritySuite) software[i]), desc);
			}
			else if( software[i] instanceof Webserver )
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
	 * 
	 * @param verify
	 *            Boolean saying if validation should be run on the data.
	 * @return If the save methods in each of the views does not return false,
	 *         which would mean that it did not save, the method will return
	 *         true. Else it will return false;
	 */
	public boolean save(boolean verify)
	{
		boolean validationFailed = false;


		// If verify is set, the views will be validated.
		if ( verify )
		{
			// Boolean array that contains the validation status of each view.
			boolean[] verified = new boolean[this.getComponentCount()];
			
			// FIXME
//			/**
//			 * Goes through all the views and gets the validation status of each
//			 * one and places that boolean in the validation array.
//			 */
//			for ( int i = 0; i < this.getComponentCount(); i++ )
//			{
//				Component comp = this.getComponent(i);
//
//				verified[i] = ((HardwareViewInterface) comp).validateNecessaryData();
//			}

			/**
			 * If any of the validation function in any of the views return
			 * false the loop will end and none of the values for any of the
			 * views will be saved. The JFrame will also not exit so the user
			 * has a chance to change the value.
			 */
			for ( int i = 0; i < verified.length; i++ )
			{
				if ( verified[i] == false )
				{
					validationFailed = true;
					i = verified.length;
				}
			}
		}
		// Checks if any of the views failed its validation.
		if ( validationFailed == false )
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

			// // The motherboard save.
			// Component comp = this.getComponent(0);
			// ((HardwareView) comp).save();


			ComponentsManagment.processAllChanges(mainobj);


			// Returns a boolean showing that everything i saved.
			return true;
		}
		// Atleast one of the validations have failed.
		else
		{
			/**
			 * Shows that at least one of the validations have failed and that
			 * nothing has been saved.
			 */
			return false;
		}
	}


}
