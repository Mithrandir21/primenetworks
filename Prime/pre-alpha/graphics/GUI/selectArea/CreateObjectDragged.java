/**
 * 
 */
package graphics.GUI.selectArea;


import graphics.PrimeMain1;
import graphics.WidgetIcon;
import hardware.Motherboard;
import infrastructure.Hub;
import infrastructure.Router;
import infrastructure.Switch;
import objects.Object;
import peripheral.Printer;
import peripheral.Scanner;
import servers.BackupServer;
import servers.FirewallServer;
import servers.HTTPServer;
import servers.MailServer;
import servers.ProxyServer;
import clients.Desktop;
import clients.Laptop;


/**
 * A class that contains only one public method that returns an object based on
 * the given {@link graphics.WidgetIcon WidgetIcon}.
 * 
 * @author Bahram Malaekeh
 */
public class CreateObjectDragged
{

	private Object[] st_components = new Object[5];


	/**
	 * Creates and returns an object based on the {@link graphics.WidgetIcon
	 * WidgetIcon} classType. The object created is a standard object with very
	 * little extra information.
	 * 
	 * @param iconObject
	 *            The {@link graphics.WidgetIcon WidgetIcon} that contains the
	 *            calssTypethats is the basis for the creation of the return
	 *            object.
	 * @return Object The newly created standard object.
	 */
	public Object CreateObject(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		Object newObject = null;
		String objectType = iconObject.getClassType().getName();

		createComponentsArray();

		if ( objectType.equals("clients.Desktop") )
		{
			newObject = createDefaultDesktop(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("clients.Laptop") )
		{
			newObject = createDefaultLaptop(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("clients.ThinClient") )
		{
			newObject = createDefaultThinClient(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("servers.HTTPServer") )
		{
			newObject = createDefaultHTTPServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("servers.BackupServer") )
		{
			newObject = createDefaultBackupServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("servers.MailServer") )
		{
			newObject = createDefaultMailServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("servers.FirewallServer") )
		{
			newObject = createDefaultFirewallServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("servers.ProxyServer") )
		{
			newObject = createDefaultProxyServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("peripheral.Scanner") )
		{
			newObject = createDefaultScanner(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("peripheral.Printer") )
		{
			newObject = createDefaultPrinter(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("infrastructure.Hub") )
		{
			newObject = createDefaultHub(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("infrastructure.Switch") )
		{
			newObject = createDefaultSwitch(iconObject, numberOfWidgetsOnTheScene);
		}
		else if ( objectType.equals("infrastructure.Router") )
		{
			newObject = createDefaultRouter(iconObject, numberOfWidgetsOnTheScene);
		}


		return newObject;
	}



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



	public Desktop createDefaultDesktop(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Desktop" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		String[] supportedConnectionInterfaces = { "RJ-45", "USB" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}


		return new Desktop(objectName, objectDesc, supportedConnectionInterfaces, st_components);
	}



	public Laptop createDefaultLaptop(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Laptop" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		String[] supportedConnectionInterfaces = { "RJ-45", "USB" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}


		return new Laptop(objectName, objectDesc, supportedConnectionInterfaces, st_components);
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


		return new Laptop(objectName, objectDesc, supportedConnectionInterfaces, st_components);
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


		return new HTTPServer(objectName, objectDesc, supportedConnectionInterfaces, st_components,
				ObjectSWname, ObjectSWdesc, ObjectSWversion);
	}




	public BackupServer createDefaultBackupServer(WidgetIcon iconObject,
			int numberOfWidgetsOnTheScene)
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


		return new BackupServer(objectName, objectDesc, supportedConnectionInterfaces,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);
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


		return new MailServer(objectName, objectDesc, supportedConnectionInterfaces, st_components,
				ObjectSWname, ObjectSWdesc, ObjectSWversion);
	}



	public FirewallServer createDefaultFirewallServer(WidgetIcon iconObject,
			int numberOfWidgetsOnTheScene)
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


		return new FirewallServer(objectName, objectDesc, supportedConnectionInterfaces,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);
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


		return new ProxyServer(objectName, objectDesc, supportedConnectionInterfaces,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);
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


		return new Hub(objectName, objectDesc, SupConInt, objectMB, outPorts, inPorts,
				DuplexSupport);
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


		return new Switch(objectName, objectDesc, SupConInt, objectMB, outPorts, inPorts,
				DuplexSupport);
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


		return new Router(objectName, objectDesc, SupConInt, objectMB, outPorts, inPorts,
				DuplexSupport);
	}
}
