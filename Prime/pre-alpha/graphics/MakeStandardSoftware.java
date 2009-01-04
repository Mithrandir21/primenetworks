package graphics;


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


public class MakeStandardSoftware
{
	/**
	 * TODO - Description
	 */
	public Antivirus getSt_AV()
	{
		Antivirus av = new Antivirus("Standard Antivirus",
				"This is the system standard antivirus.", "1");

		return av;
	}



	/**
	 * TODO - Description
	 */
	public Backup getSt_Backup()
	{
		Backup back = new Backup("Standard Backup",
				"This is the system standard backup.", "1");

		return back;
	}



	/**
	 * TODO - Description
	 */
	public Database getSt_Database()
	{
		Database db = new Database("Standard Database",
				"This is the system standard database.", "1");

		return db;
	}



	/**
	 * TODO - Description
	 */
	public Email getSt_Email()
	{
		Email mail = new Email("Standard Email",
				"This is the system standard Email.", "1");

		return mail;
	}



	/**
	 * TODO - Description
	 */
	public Firewall getSt_Firewall()
	{
		Firewall fw = new Firewall("Standard Firewall",
				"This is the system standard Firewall.", "1");

		return fw;
	}


	/**
	 * TODO - Description
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


		OfficeSuite offSuite = new OfficeSuite("Standard Office Suite",
				"This is the system standard Office Suite.", "1", formats);

		return offSuite;
	}



	/**
	 * TODO - Description
	 */
	public OperatingSystem getSt_OS()
	{
		OperatingSystem os = new OperatingSystem("Windows XP",
				"This is the system standard Operating System.", "5.1");

		return os;
	}



	/**
	 * TODO - Description
	 */
	public Proxy getSt_Proxy()
	{
		Proxy proxy = new Proxy("Standard Proxy",
				"This is the system standard Proxy.", "1");

		return proxy;
	}



	/**
	 * TODO - Description
	 * 
	 */
	public SecuritySuite getSt_SecSuite()
	{
		SecuritySuite secSuite = new SecuritySuite("Standard Security Suite",
				"This is the system standard Security Suite.", "1", false,
				false, false);
		
		
		return secSuite;
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public Webserver getSt_Webserver()
	{
		Webserver ws = new Webserver("Standard Webserver",
				"This is the system standard Webserver.", "1");
		
		return ws;
	}
}
