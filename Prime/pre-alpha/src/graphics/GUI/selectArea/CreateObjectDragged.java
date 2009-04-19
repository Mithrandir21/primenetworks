/**
 * 
 */
package graphics.GUI.selectArea;


import graphics.PrimeMain1;
import graphics.WidgetIcon;
import objects.Object;
import objects.Software;
import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.hardwareObjects.Motherboard;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
import objects.infrastructureObjects.Router;
import objects.infrastructureObjects.Switch;
import objects.infrastructureObjects.WirelessRouter;
import objects.peripheralObjects.Printer;
import objects.peripheralObjects.Scanner;
import objects.serverObjects.BackupServer;
import objects.serverObjects.FirewallServer;
import objects.serverObjects.HTTPServer;
import objects.serverObjects.MailServer;
import objects.serverObjects.ProxyServer;


/**
 * A class that contains only one public method that returns an object based on the given {@link graphics.WidgetIcon
 * WidgetIcon}.
 * 
 * @author Bahram Malaekeh
 */
public class CreateObjectDragged
{

	private Object[] st_components = new Object[5];

	private Software[] st_software = new Software[1];


	/**
	 * Creates and returns an object based on the {@link graphics.WidgetIcon WidgetIcon} classType. The object created
	 * is a standard object with very little extra information.
	 * 
	 * @param iconObject
	 *            The {@link graphics.WidgetIcon WidgetIcon} that contains the calssTypethats is the basis for the
	 *            creation of the return object.
	 * @return Object The newly created standard object.
	 */
	public Object CreateObject(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		Object newObject = null;
		String objectType = iconObject.getClassType().getName();

		createComponentsArray();
		createSoftwareArray();

		if ( objectType.equals("objects.clientObjects.Desktop") )
		{
			newObject = createDefaultDesktop(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.clientObjects.Laptop") )
		{
			newObject = createDefaultLaptop(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.clientObjects.ThinClient") )
		{
			newObject = createDefaultThinClient(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.serverObjects.HTTPServer") )
		{
			newObject = createDefaultHTTPServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.serverObjects.BackupServer") )
		{
			newObject = createDefaultBackupServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.serverObjects.MailServer") )
		{
			newObject = createDefaultMailServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.serverObjects.FirewallServer") )
		{
			newObject = createDefaultFirewallServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.serverObjects.ProxyServer") )
		{
			newObject = createDefaultProxyServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.peripheralObjects.Scanner") )
		{
			newObject = createDefaultScanner(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.peripheralObjects.Printer") )
		{
			newObject = createDefaultPrinter(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.infrastructureObjects.Hub") )
		{
			newObject = createDefaultHub(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.infrastructureObjects.Switch") )
		{
			newObject = createDefaultSwitch(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.infrastructureObjects.Router") )
		{
			newObject = createDefaultRouter(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.infrastructureObjects.WirelessRouter") )
		{
			newObject = createDefaultWirelessRouter(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("objects.infrastructureObjects.Internet") )
		{
			newObject = createDefaultInternet(iconObject, numberOfWidgetsOnTheScene);
		}


		return newObject;
	}



	/**
	 * 
	 */
	private void createComponentsArray()
	{
		st_components[0] = PrimeMain1.standard_internal_components.getSt_MB();

		Motherboard mb = (Motherboard) st_components[0];

		mb.makeOneCPUportTaken();
		mb.makeOneRAMportTaken();
		mb.makeOneDUCportTaken();// For the CPU
		mb.makeOneDUCportTaken();// For the HDD

		st_components[0] = mb;
		st_components[1] = PrimeMain1.standard_internal_components.getSt_CPU();
		st_components[2] = PrimeMain1.standard_internal_components.getSt_RAM();
		st_components[3] = PrimeMain1.standard_internal_components.getSt_HDD();
		st_components[4] = PrimeMain1.standard_internal_components.getSt_DVDRW();
	}


	/**
	 * 
	 */
	private void createSoftwareArray()
	{
		st_software[0] = PrimeMain1.standard_software.getSt_OS();
	}



	public Desktop createDefaultDesktop(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Desktop" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		String[] supportedConnectionInterfaces = { "RJ-45", "USB" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Desktop temp = new Desktop(objectName, objectDesc, supportedConnectionInterfaces, st_components);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	public Laptop createDefaultLaptop(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Laptop" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		String[] supportedConnectionInterfaces = { "RJ-45", "USB", "Wireless" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Laptop temp = new Laptop(objectName, objectDesc, supportedConnectionInterfaces, st_components);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	public Laptop createDefaultThinClient(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Thin Client" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		String[] supportedConnectionInterfaces = { "RJ-45", "USB" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		// TODO - ThinClient


		Laptop temp = new Laptop(objectName, objectDesc, supportedConnectionInterfaces, st_components);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	public HTTPServer createDefaultHTTPServer(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "HTTP Server" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		String[] supportedConnectionInterfaces = { "RJ-45", "USB" };

		String ObjectSWname = "Apache";
		String ObjectSWdesc = "Standard Webserver";
		String ObjectSWversion = "2.2";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setIntegLANcard(true);
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		HTTPServer temp = new HTTPServer(objectName, objectDesc, supportedConnectionInterfaces, st_components,
				ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}




	public BackupServer createDefaultBackupServer(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Backup Server" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		String[] supportedConnectionInterfaces = { "RJ-45", "USB" };

		String ObjectSWname = "Backup";
		String ObjectSWdesc = "Standard backup server";
		String ObjectSWversion = "1";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setIntegLANcard(true);
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		BackupServer temp = new BackupServer(objectName, objectDesc, supportedConnectionInterfaces, st_components,
				ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	public MailServer createDefaultMailServer(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Mail Server" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		String[] supportedConnectionInterfaces = { "RJ-45", "USB" };

		String ObjectSWname = "Mail";
		String ObjectSWdesc = "Standard mail server";
		String ObjectSWversion = "1";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setIntegLANcard(true);
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		MailServer temp = new MailServer(objectName, objectDesc, supportedConnectionInterfaces, st_components,
				ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	public FirewallServer createDefaultFirewallServer(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Firewall Server" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		String[] supportedConnectionInterfaces = { "RJ-45", "USB" };

		String ObjectSWname = "Firewall";
		String ObjectSWdesc = "Standard firewall server";
		String ObjectSWversion = "1";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setIntegLANcard(true);
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);

		FirewallServer temp = new FirewallServer(objectName, objectDesc, supportedConnectionInterfaces, st_components,
				ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	public ProxyServer createDefaultProxyServer(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Proxy Server" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		String[] supportedConnectionInterfaces = { "RJ-45", "USB" };

		String ObjectSWname = "ProxyServer";
		String ObjectSWdesc = "Standard proxy server";
		String ObjectSWversion = "1";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setIntegLANcard(true);
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);

		ProxyServer temp = new ProxyServer(objectName, objectDesc, supportedConnectionInterfaces, st_components,
				ObjectSWname, ObjectSWdesc, ObjectSWversion);

		temp.setSoftware(st_software);

		return temp;
	}



	public Scanner createDefaultScanner(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Scanner" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();

		String Sresolution = "1280x1020";
		String SconnectionInterfaces[] = { "USB" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		return new Scanner(objectName, objectDesc, Sresolution, SconnectionInterfaces, objectMB);
	}


	public Printer createDefaultPrinter(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Printer" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();

		String Sresolution = "1280x1020";
		String SconnectionInterfaces[] = { "USB" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		return new Printer(objectName, objectDesc, Sresolution, SconnectionInterfaces, objectMB);
	}




	public Hub createDefaultHub(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Hub" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();

		String[] SupConInt = { "RJ-45" };
		int outPorts = 16;
		int inPorts = 16;
		String[] DuplexSupport = { "Full Duplex" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components.getHw_MB();

		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(16);
		objectMB.setIntegLANPortsAvailable(16);


		return new Hub(objectName, objectDesc, SupConInt, objectMB, outPorts, inPorts, DuplexSupport);
	}




	public Switch createDefaultSwitch(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Switch" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();

		String[] SupConInt = { "RJ-45" };
		int outPorts = 16;
		int inPorts = 16;
		String[] DuplexSupport = { "Full Duplex" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components.getHw_MB();

		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(16);
		objectMB.setIntegLANPortsAvailable(16);


		return new Switch(objectName, objectDesc, SupConInt, objectMB, outPorts, inPorts, DuplexSupport);
	}



	public Router createDefaultRouter(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Router" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();

		String[] SupConInt = { "RJ-45" };
		int outPorts = 4;
		int inPorts = 4;
		String[] DuplexSupport = { "Full Duplex" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components.getHw_MB();

		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(4);
		objectMB.setIntegLANPortsAvailable(4);


		return new Router(objectName, objectDesc, SupConInt, objectMB, outPorts, inPorts, DuplexSupport);
	}
	
	
	
	public Router createDefaultWirelessRouter(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Wireless Router" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();

		String[] SupConInt = { "RJ-45", "Wireless" };
		int outPorts = 4;
		int inPorts = 4;
		String[] DuplexSupport = { "Full Duplex" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components.getHw_MB();

		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(4);
		objectMB.setIntegLANPortsAvailable(4);


		return new WirelessRouter(objectName, objectDesc, SupConInt, objectMB, outPorts, inPorts, DuplexSupport, true);
	}


	public Internet createDefaultInternet(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Internet" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();

		String[] SupConInt = { "RJ-45" };
		int outPorts = 1;
		int inPorts = 1;

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components.getHw_MB();

		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(1);
		objectMB.setIntegLANPortsAvailable(1);

		return new Internet(objectName, objectDesc, objectName, SupConInt, objectMB);
	}

}
