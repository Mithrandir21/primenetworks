package graphics;


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
 * /** In this class the systems standard internal components are made and served to the rest of the system. When any
 * new object is made, the internal components such as motherboard or CPU will be provided by the functions in this
 * class.
 * 
 * @author Bahram Malaekeh
 */
public class MakeStandardSoftware
{

	/**
	 * Creates and returns what the system will regard as the standard Antivirus.
	 * 
	 * @return A new antivirus
	 */
	public Antivirus getSt_AV()
	{
		Antivirus av = new Antivirus("Standard Antivirus", "This is the system standard antivirus.", "1");

		return av;
	}



	/**
	 * Creates and returns what the system will regard as the standard Backup software.
	 * 
	 * @return A new Backup software
	 */
	public Backup getSt_Backup()
	{
		Backup back = new Backup("Standard Backup", "This is the system standard backup.", "1");

		return back;
	}



	/**
	 * Creates and returns what the system will regard as the standard Database.
	 * 
	 * @return A new Database
	 */
	public Database getSt_Database()
	{
		Database db = new Database("Standard Database", "This is the system standard database.", "1");

		return db;
	}



	/**
	 * Creates and returns what the system will regard as the standard Email software. This is software that can read
	 * emails.
	 * 
	 * @return A new Email software
	 */
	public Email getSt_Email()
	{
		Email mail = new Email("Standard Email", "This is the system standard Email.", "1");

		return mail;
	}



	/**
	 * Creates and returns what the system will regard as the standard Firewall.
	 * 
	 * @return A new Firewall
	 */
	public Firewall getSt_Firewall()
	{
		Firewall fw = new Firewall("Standard Firewall", "This is the system standard Firewall.", "1");

		return fw;
	}


	/**
	 * Creates and returns what the system will regard as the standard Office Suite. This suite has a set of predefined
	 * file types it supports. Other file types can be added by the user.
	 * 
	 * @return A new Office Suite
	 */
	public OfficeSuite getSt_OfficeSuite()
	{
		String[] formats = new String[15];
		formats[0] = ".doc";
		formats[1] = ".txt";
		formats[2] = ".xml";
		formats[3] = ".xls";
		formats[4] = ".ppt";
		formats[5] = ".rtf";


		OfficeSuite offSuite = new OfficeSuite("Standard Office Suite", "This is the system standard Office Suite.",
				"1", formats);

		return offSuite;
	}



	/**
	 * Creates and returns what the system will regard as the standard Operating System. This Operating system will be
	 * the software that all software that is to be installed on the system will be checked against for compatibility.
	 * If the user has added other Operating systems to the machine, those will also be included in the check for
	 * software compatibility,
	 * 
	 * @return A new Operating System
	 */
	public OperatingSystem getSt_OS()
	{
		OperatingSystem os = new OperatingSystem("Windows XP", "This is the system standard Operating System.", "5.1");

		return os;
	}



	/**
	 * Creates and returns what the system will regard as the standard Proxy.
	 * 
	 * @return A new Proxy
	 */
	public Proxy getSt_Proxy()
	{
		Proxy proxy = new Proxy("Standard Proxy", "This is the system standard Proxy.", "1");

		return proxy;
	}



	/**
	 * Creates and returns what the system will regard as the standard Security Suite.
	 * 
	 * @return A new Security Suite
	 */
	public SecuritySuite getSt_SecSuite()
	{
		SecuritySuite secSuite = new SecuritySuite("Standard Security Suite",
				"This is the system standard Security Suite.", "1", false, false, false);


		return secSuite;
	}



	/**
	 * Creates and returns what the system will regard as the standard Webserver.
	 * 
	 * @return A new Webserver
	 */
	public Webserver getSt_Webserver()
	{
		Webserver ws = new Webserver("Standard Webserver", "This is the system standard Webserver.", "1");

		return ws;
	}
}
