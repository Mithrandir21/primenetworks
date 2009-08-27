/**
 * 
 */
package graphics.GUI;


import exceptions.ObjectNotFoundException;
import graphics.PrimeMain1;
import managment.ArrayManagment;
import managment.ComponentsManagment;
import objects.Object;
import objects.Software;
import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.clientObjects.ThinClient;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
import objects.infrastructureObjects.Router;
import objects.infrastructureObjects.Switch;
import objects.infrastructureObjects.WirelessRouter;
import objects.peripheralObjects.Fax;
import objects.peripheralObjects.MultifunctionPrinter;
import objects.peripheralObjects.NetworkMultifunctionPrinter;
import objects.peripheralObjects.NetworkPrinter;
import objects.peripheralObjects.Printer;
import objects.peripheralObjects.Scanner;
import objects.serverObjects.BackupServer;
import objects.serverObjects.DatabaseServer;
import objects.serverObjects.FirewallServer;
import objects.serverObjects.HTTPServer;
import objects.serverObjects.MailServer;
import objects.serverObjects.PrinterServer;
import objects.serverObjects.ProxyServer;
import widgets.WidgetIcon;


/**
 * A class that contains only one public method that returns an object based on the given
 * {@link widgets.WidgetIcon WidgetIcon}.
 * 
 * @author Bahram Malaekeh
 */
public class CreateObjects
{

	private static Object[] st_components = new Object[5];

	private static Software[] st_software = new Software[1];


	/**
	 * Creates and returns an object based on the {@link widgets.WidgetIcon WidgetIcon} classType.
	 * The object created is a standard object with very little extra information.
	 * 
	 * @param iconObject
	 *            The {@link widgets.WidgetIcon WidgetIcon} that contains the calssTypethats is the
	 *            basis for the creation of the return object.
	 * @return Object The newly created standard object.
	 */
	public Object CreateObject(WidgetIcon iconObject,
			int numberOfWidgetsOnTheScene)
	{
		Object newObject = null;
		String objectType = iconObject.getClassType().getName();
		String desc = iconObject.getDescription();

		if ( objectType.equals("objects.clientObjects.Desktop") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				// If the Object with the given class was not found in the arraylist
				newObject = createDefaultDesktop(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}


		}
		else if ( objectType.equals("objects.clientObjects.Laptop") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);
			}
			catch ( ObjectNotFoundException e )
			{
				// If the Object with the given class was not found in the arraylist
				newObject = createDefaultLaptop(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.clientObjects.ThinClient") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);
			}
			catch ( ObjectNotFoundException e )
			{
				// If the Object with the given class was not found in the arraylist
				newObject = createDefaultThinClient(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.serverObjects.HTTPServer") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				// If the Object with the given class was not found in the arraylist
				newObject = createDefaultHTTPServer(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.serverObjects.BackupServer") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultBackupServer(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.serverObjects.MailServer") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultMailServer(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.serverObjects.FirewallServer") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultFirewallServer(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.serverObjects.ProxyServer") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultProxyServer(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.serverObjects.PrinterServer") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultPrinterServer(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.serverObjects.DatabaseServer") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultDatabaseServer(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.peripheralObjects.Scanner") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultScanner(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.peripheralObjects.Printer") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultPrinter(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.peripheralObjects.Fax") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultFax(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType
				.equals("objects.peripheralObjects.MultifunctionPrinter") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultMFP(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.peripheralObjects.NetworkPrinter") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultNetworkPrinter(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType
				.equals("objects.peripheralObjects.NetworkMultifunctionPrinter") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultNetworkMFP(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.infrastructureObjects.Hub") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultHub(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.infrastructureObjects.Switch") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultSwitch(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.infrastructureObjects.Router") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultRouter(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType
				.equals("objects.infrastructureObjects.WirelessRouter") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultWirelessRouter(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}
		else if ( objectType.equals("objects.infrastructureObjects.Internet") )
		{
			try
			{
				newObject = ArrayManagment.getSpesificComponent(iconObject
						.getClassType(), PrimeMain1.objectlist);

			}
			catch ( ObjectNotFoundException e )
			{
				newObject = createDefaultInternet(desc);
				newObject.setObjectName(newObject.getObjectName()
						+ numberOfWidgetsOnTheScene);
			}
		}

		return newObject;
	}



	/**
	 * Creates all the system default objects.
	 */
	public static void createStandardObject()
	{
		createComponentsArray();
		createSoftwareArray();

		PrimeMain1.objectlist.add(createDefaultDesktop(""));
		PrimeMain1.objectlist.add(createDefaultLaptop(""));
		PrimeMain1.objectlist.add(createDefaultThinClient(""));
		PrimeMain1.objectlist.add(createDefaultHTTPServer(""));
		PrimeMain1.objectlist.add(createDefaultBackupServer(""));
		PrimeMain1.objectlist.add(createDefaultDatabaseServer(""));
		PrimeMain1.objectlist.add(createDefaultMailServer(""));
		PrimeMain1.objectlist.add(createDefaultFirewallServer(""));
		PrimeMain1.objectlist.add(createDefaultProxyServer(""));
		PrimeMain1.objectlist.add(createDefaultPrinterServer(""));
		PrimeMain1.objectlist.add(createDefaultScanner(""));
		PrimeMain1.objectlist.add(createDefaultPrinter(""));
		PrimeMain1.objectlist.add(createDefaultFax(""));
		PrimeMain1.objectlist.add(createDefaultMFP(""));
		PrimeMain1.objectlist.add(createDefaultNetworkPrinter(""));
		PrimeMain1.objectlist.add(createDefaultNetworkMFP(""));
		PrimeMain1.objectlist.add(createDefaultHub(""));
		PrimeMain1.objectlist.add(createDefaultSwitch(""));
		PrimeMain1.objectlist.add(createDefaultRouter(""));
		PrimeMain1.objectlist.add(createDefaultWirelessRouter(""));
		PrimeMain1.objectlist.add(createDefaultInternet(""));
	}



	/**
	 * 
	 */
	private static void createComponentsArray()
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
		st_components[4] = PrimeMain1.standard_internal_components
				.getSt_DVDRW();
	}


	/**
	 * 
	 */
	private static void createSoftwareArray()
	{
		st_software[0] = PrimeMain1.standard_software.getSt_OS();
	}



	private static Desktop createDefaultDesktop(String desc)
	{
		String objectName = "Desktop";
		String objectDesc = desc;

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Desktop temp = new Desktop(objectName, objectDesc, st_components);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);


		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}







	private static Laptop createDefaultLaptop(String desc)
	{
		String objectName = "Laptop";
		String objectDesc = desc;

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Laptop temp = new Laptop(objectName, objectDesc, st_components);

		// Internal Wireless NIC
		InternalNetworksCard intNIC = PrimeMain1.standard_internal_components
				.getSt_IntNIC();
		intNIC.setType("Wireless");

		// Add the internal NIC to the list of components on the Object(not the
		// "st_components" array of this class)
		temp.addComponent(intNIC);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	public static ThinClient createDefaultThinClient(String desc)
	{
		String objectName = "Thin Client";
		String objectDesc = desc;

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}


		Object[] st_componentsWithOutHDD = null;
		try
		{ // Gets the HDD from the array of components
			HDD removing = (HDD) ArrayManagment.getSpesificComponents(
					HDD.class, st_components, st_components.length)[0];

			// Removes the HDD from the array of components
			st_componentsWithOutHDD = ComponentsManagment.removeComponent(
					removing, st_components, st_components.length);
		}
		catch ( ObjectNotFoundException e )
		{
			// This is impossible since the HDD has just been added at the top
			// of this class
			e.printStackTrace();
		}


		ThinClient temp = new ThinClient(objectName, objectDesc,
				st_componentsWithOutHDD);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	private static HTTPServer createDefaultHTTPServer(String desc)
	{
		String objectName = "HTTP Server";
		String objectDesc = desc;

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


		HTTPServer temp = new HTTPServer(objectName, objectDesc, st_components,
				ObjectSWname, ObjectSWdesc, ObjectSWversion);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}




	private static BackupServer createDefaultBackupServer(String desc)
	{
		String objectName = "Backup Server";
		String objectDesc = desc;

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


		BackupServer temp = new BackupServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	private static DatabaseServer createDefaultDatabaseServer(String desc)
	{
		String objectName = "Backup Server";
		String objectDesc = desc;

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


		DatabaseServer temp = new DatabaseServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	private static MailServer createDefaultMailServer(String desc)
	{
		String objectName = "Mail Server";
		String objectDesc = desc;

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


		MailServer temp = new MailServer(objectName, objectDesc, st_components,
				ObjectSWname, ObjectSWdesc, ObjectSWversion);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	private static FirewallServer createDefaultFirewallServer(String desc)
	{
		String objectName = "Firewall Server";
		String objectDesc = desc;

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

		FirewallServer temp = new FirewallServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	private static ProxyServer createDefaultProxyServer(String desc)
	{
		String objectName = "Proxy Server";
		String objectDesc = desc;

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

		ProxyServer temp = new ProxyServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		temp.setSoftware(st_software);

		return temp;
	}



	private static PrinterServer createDefaultPrinterServer(String desc)
	{
		String objectName = "Printer Server";
		String objectDesc = desc;

		String ObjectSWname = "PrinterServer";
		String ObjectSWdesc = "Standard printer server";
		String ObjectSWversion = "1";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setIntegLANcard(true);
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);

		PrinterServer temp = new PrinterServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		temp.setSoftware(st_software);

		return temp;
	}



	private static Scanner createDefaultScanner(String desc)
	{
		String objectName = "Scanner";
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components
				.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		Scanner temp = new Scanner(objectName, objectDesc, Sresolution,
				objectMB);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}


	private static Printer createDefaultPrinter(String desc)
	{
		String objectName = "Printer";
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components
				.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		Printer temp = new Printer(objectName, objectDesc, Sresolution,
				objectMB);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}



	private static Fax createDefaultFax(String desc)
	{
		String objectName = "Fax";
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components
				.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);
		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(1);
		objectMB.setIntegLANPortsAvailable(1);


		Fax temp = new Fax(objectName, objectDesc, Sresolution, objectMB);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}


	private static MultifunctionPrinter createDefaultMFP(String desc)
	{
		String objectName = "MFP";
		String objectDesc = desc;

		String Sresolution[] = { "1280x1020" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components
				.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		MultifunctionPrinter temp = new MultifunctionPrinter(objectName,
				objectDesc, Sresolution, objectMB);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}



	private static NetworkPrinter createDefaultNetworkPrinter(String desc)
	{
		String objectName = "Printer";
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components
				.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(1);
		objectMB.setUSBPortsAvailable(1);
		objectMB.setIntegLANPortsAvailable(1);


		NetworkPrinter temp = new NetworkPrinter(objectName, objectDesc,
				Sresolution, objectMB);

		// Internal NIC
		// InternalNetworksCard intNIC =
		// PrimeMain1.standard_internal_components.getSt_IntNIC();
		// intNIC.setType("Wireless");

		// Add the internal NIC to the list of components on the Object(not the
		// "st_components" array of this class)
		// temp.addComponent(intNIC);


		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}



	private static NetworkMultifunctionPrinter createDefaultNetworkMFP(
			String desc)
	{
		String objectName = "MFP";
		String objectDesc = desc;

		String Sresolution[] = { "1280x1020" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components
				.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(1);
		objectMB.setUSBPortsAvailable(1);
		objectMB.setIntegLANPortsAvailable(1);


		NetworkMultifunctionPrinter temp = new NetworkMultifunctionPrinter(
				objectName, objectDesc, Sresolution, objectMB);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}





	private static Hub createDefaultHub(String desc)
	{
		String objectName = "Hub";
		String objectDesc = desc;

		int outPorts = 16;
		int inPorts = 16;
		String[] DuplexSupport = { "Full Duplex" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components
				.getHw_MB();

		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(16);
		objectMB.setIntegLANPortsAvailable(16);


		Hub temp = new Hub(objectName, objectDesc, null, objectMB, outPorts,
				inPorts, DuplexSupport);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}




	private static Switch createDefaultSwitch(String desc)
	{
		String objectName = "Switch";
		String objectDesc = desc;

		int outPorts = 16;
		int inPorts = 16;
		String[] DuplexSupport = { "Full Duplex" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components
				.getHw_MB();

		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(16);
		objectMB.setIntegLANPortsAvailable(16);


		Switch temp = new Switch(objectName, objectDesc, null, objectMB,
				outPorts, inPorts, DuplexSupport);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}



	private static Router createDefaultRouter(String desc)
	{
		String objectName = "Router";
		String objectDesc = desc;

		int outPorts = 4;
		int inPorts = 4;
		String[] DuplexSupport = { "Full Duplex" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components
				.getHw_MB();

		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(4);
		objectMB.setIntegLANPortsAvailable(4);


		Router temp = new Router(objectName, objectDesc, null, objectMB,
				outPorts, inPorts, DuplexSupport);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}



	private static Router createDefaultWirelessRouter(String desc)
	{
		String objectName = "Wireless Router";
		String objectDesc = desc;

		int outPorts = 4;
		int inPorts = 4;
		String[] DuplexSupport = { "Full Duplex" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components
				.getHw_MB();

		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(4);
		objectMB.setIntegLANPortsAvailable(4);


		WirelessRouter temp = new WirelessRouter(objectName, objectDesc, null,
				objectMB, outPorts, inPorts, DuplexSupport, true);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		String[] supConIntWithWLan = new String[supportedConnectionInterfaces.length + 1];

		// Adds all the previous supported connection interfaces to the new
		// array
		for ( int i = 0; i < supportedConnectionInterfaces.length; i++ )
		{
			supConIntWithWLan[i] = supportedConnectionInterfaces[i];
		}

		// Places the Wireless string at the end of the array
		supConIntWithWLan[supConIntWithWLan.length - 1] = "Wireless";


		temp.setSupportedConnectionInterfaces(supConIntWithWLan);

		return temp;
	}


	private static Internet createDefaultInternet(String desc)
	{
		String objectName = "Internet";
		String objectDesc = desc;

		String[] SupConInt = { "RJ-45" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain1.standard_internal_components
				.getHw_MB();

		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(1);
		objectMB.setIntegLANPortsAvailable(1);

		return new Internet(objectName, objectDesc, objectName, SupConInt,
				objectMB);
	}

}
