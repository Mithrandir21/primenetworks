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
package managment;


import exceptions.MotherboardNotFound;
import exceptions.ObjectNotFoundException;
import graphics.PrimeMain;
import objects.ExternalHardware;
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
import objects.infrastructureObjects.Modem;
import objects.infrastructureObjects.Router;
import objects.infrastructureObjects.Switch;
import objects.infrastructureObjects.WirelessRouter;
import objects.peripheralObjects.ExternalHDD;
import objects.peripheralObjects.Fax;
import objects.peripheralObjects.GenericDevice;
import objects.peripheralObjects.MultifunctionPrinter;
import objects.peripheralObjects.NetworkMultifunctionPrinter;
import objects.peripheralObjects.NetworkPrinter;
import objects.peripheralObjects.Printer;
import objects.peripheralObjects.Scanner;
import objects.rackUnits.Rack;
import objects.rackUnits.RackUnit;
import objects.serverObjects.AntivirusServer;
import objects.serverObjects.BackupServer;
import objects.serverObjects.DatabaseServer;
import objects.serverObjects.FirewallServer;
import objects.serverObjects.GenericServer;
import objects.serverObjects.HTTPServer;
import objects.serverObjects.MailServer;
import objects.serverObjects.NASServer;
import objects.serverObjects.PrinterServer;
import objects.serverObjects.ProxyServer;
import objects.serverObjects.VirtualizationServer;
import widgets.WidgetIcon;
import connections.ConnectionUtils;


/**
 * A class that contains only one public method that returns an object based on
 * the given {@link widgets.WidgetIcon WidgetIcon}.
 * 
 * @author Bahram Malaekeh
 */
public class CreateObjects
{

	/**
	 * Creates and returns an object based on the {@link widgets.WidgetIcon
	 * WidgetIcon} classType. The object created is a standard object with very
	 * little extra information.
	 * 
	 * @param iconObject
	 *            The {@link widgets.WidgetIcon WidgetIcon} that contains the
	 *            calssTypethats is the basis for the creation of the return
	 *            object.
	 * @return Object The newly created standard object.
	 */
	public static Object CreateObject(WidgetIcon iconObject,
			int numberOfWidgetsOnTheScene)
	{
		PrimeMain.ioLog.info("Starting Object Creation.");
		Object newObject = null;
		String objectType = iconObject.getClassType().getSimpleName();
		String desc = iconObject.getDescription();

		boolean genericDevice = false;


		if ( !objectType.equals("GenericDevice") )
		{
			try
			{
				// Gets the object in the given ArrayList with the given class
				newObject = ArrayManagment.getSpesificComponent(
						iconObject.getClassType(), PrimeMain.objectlist);
				PrimeMain.ioLog
						.fine("Standard Object was found on systems Std Object list.");
			}
			catch ( ObjectNotFoundException e )
			{
				PrimeMain.ioLog
						.fine("Standard Object was NOT found on systems Std Object list. Creating a default "
								+ objectType + ".");
				if ( objectType.equals("Desktop") )
				{
					newObject = createDefaultDesktop(desc);
				}
				else if ( objectType.equals("Laptop") )
				{
					newObject = createDefaultLaptop(desc);
				}
				else if ( objectType.equals("ThinClient") )
				{
					newObject = createDefaultThinClient(desc);
				}
				else if ( objectType.equals("GenericServer") )
				{
					newObject = createGenericServer(desc);
				}
				else if ( objectType.equals("HTTPServer") )
				{
					newObject = createDefaultHTTPServer(desc);
				}
				else if ( objectType.equals("BackupServer") )
				{
					newObject = createDefaultBackupServer(desc);
				}
				else if ( objectType.equals("NASServer") )
				{
					newObject = createDefaultBackupServer(desc);
				}
				else if ( objectType.equals("DatabaseServer") )
				{
					newObject = createDefaultDatabaseServer(desc);
				}
				else if ( objectType.equals("VirtualizationServer") )
				{
					newObject = createDefaultVirtualizationServer(desc);
				}
				else if ( objectType.equals("MailServer") )
				{
					newObject = createDefaultMailServer(desc);
				}
				else if ( objectType.equals("FirewallServer") )
				{
					newObject = createDefaultFirewallServer(desc);
				}
				else if ( objectType.equals("ProxyServer") )
				{
					newObject = createDefaultProxyServer(desc);
				}
				else if ( objectType.equals("PrinterServer") )
				{
					newObject = createDefaultPrinterServer(desc);
				}
				else if ( objectType.equals("ExternalHDD") )
				{
					newObject = createDefaultNAS(desc);
				}
				else if ( objectType.equals("Scanner") )
				{
					newObject = createDefaultScanner(desc);
				}
				else if ( objectType.equals("Printer") )
				{
					newObject = createDefaultPrinter(desc);
				}
				else if ( objectType.equals("Fax") )
				{
					newObject = createDefaultFax(desc);
				}
				else if ( objectType.equals("MultifunctionPrinter") )
				{
					newObject = createDefaultMFP(desc);
				}
				else if ( objectType.equals("NetworkPrinter") )
				{
					newObject = createDefaultNetworkPrinter(desc);
				}
				else if ( objectType.equals("NetworkMultifunctionPrinter") )
				{
					newObject = createDefaultNetworkMFP(desc);
				}
				else if ( objectType.equals("Hub") )
				{
					newObject = createDefaultHub(desc);
				}
				else if ( objectType.equals("Switch") )
				{
					newObject = createDefaultSwitch(desc);
				}
				else if ( objectType.equals("Router") )
				{
					newObject = createDefaultRouter(desc);
				}
				else if ( objectType.equals("Modem") )
				{
					newObject = createDefaultModem(desc);
				}
				else if ( objectType.equals("WirelessRouter") )
				{
					newObject = createDefaultWirelessRouter(desc);
				}
				else if ( objectType.equals("Internet") )
				{
					newObject = createDefaultInternet(desc);
				}
				else if ( objectType.equals("Rack") )
				{
					newObject = createDefaultRack(desc);
				}
				else if ( objectType.equals("RackHub") )
				{
					newObject = createDefaultRackUnitWithObject(Hub.class, desc);
				}
				else if ( objectType.equals("RackRouter") )
				{
					newObject = createDefaultRackUnitWithObject(Router.class,
							desc);
				}
				else if ( objectType.equals("RackSwitch") )
				{
					newObject = createDefaultRackUnitWithObject(Switch.class,
							desc);
				}
			}
		}
		// The Object is a GenericDevice.
		else
		{
			if ( newObject == null && objectType.equals("GenericDevice") )
			{
				if ( desc.startsWith("GenericDevice - ") )
				{
					PrimeMain.ioLog
							.fine("The object is a custom GenericDevice.");

					try
					{
						// Gets the object in the given ArrayList with the given
						// class
						newObject = ArrayManagment
								.getSpesificComponent(
										iconObject.getClassType(),
										PrimeMain.objectlist);
						PrimeMain.ioLog
								.fine("Custom GenericDevice was found on systems Std Object list.");
					}
					catch ( ObjectNotFoundException e )
					{
						PrimeMain.ioLog
								.warning("The custom GenericDevice was not found in the list of Standard Objects.");
					}
				}
				else
				{
					PrimeMain.ioLog.fine("The object is a GenericDevice.");
					genericDevice = true;
				}
			}
		}


		// If no object was created
		if ( !genericDevice && newObject != null )
		{
			// Makes a exact copy of the object
			Object copiedObject = ComponentsManagment.deepObjectCopy(newObject);

			// Adds the numbers of Widgets on the canvas to the end of the
			// object name.
			copiedObject.setObjectName(newObject.getObjectName()
					+ Integer.toString(numberOfWidgetsOnTheScene));

			PrimeMain.ioLog.fine("Deep Copied Object created with new name, '"
					+ copiedObject.getObjectName() + "'.");

			return copiedObject;
		}

		// If the user wants to create a generic object.
		if ( genericDevice )
		{
			return getGenericExternalHardware();
		}


		PrimeMain.ioLog
				.warning("No new object was created. - CreateObjects - Fatal Error.");

		return null;
	}

	/**
	 * Creates all the system default objects.
	 */
	public static void createStandardObject()
	{
		PrimeMain.ioLog.info("Creating Default System Objects.");
		PrimeMain.objectlist.add(createDefaultDesktop(""));
		PrimeMain.objectlist.add(createDefaultLaptop(""));
		PrimeMain.objectlist.add(createDefaultThinClient(""));
		PrimeMain.objectlist.add(createGenericServer(""));
		PrimeMain.objectlist.add(createDefaultAntivirusServer(""));
		PrimeMain.objectlist.add(createDefaultHTTPServer(""));
		PrimeMain.objectlist.add(createDefaultBackupServer(""));
		PrimeMain.objectlist.add(createDefaultNASServer(""));
		PrimeMain.objectlist.add(createDefaultDatabaseServer(""));
		PrimeMain.objectlist.add(createDefaultVirtualizationServer(""));
		PrimeMain.objectlist.add(createDefaultMailServer(""));
		PrimeMain.objectlist.add(createDefaultFirewallServer(""));
		PrimeMain.objectlist.add(createDefaultProxyServer(""));
		PrimeMain.objectlist.add(createDefaultPrinterServer(""));
		PrimeMain.objectlist.add(createDefaultNAS(""));
		PrimeMain.objectlist.add(createDefaultScanner(""));
		PrimeMain.objectlist.add(createDefaultPrinter(""));
		PrimeMain.objectlist.add(createDefaultFax(""));
		PrimeMain.objectlist.add(createDefaultMFP(""));
		PrimeMain.objectlist.add(createDefaultNetworkPrinter(""));
		PrimeMain.objectlist.add(createDefaultNetworkMFP(""));
		PrimeMain.objectlist.add(createDefaultHub(""));
		PrimeMain.objectlist.add(createDefaultSwitch(""));
		PrimeMain.objectlist.add(createDefaultRouter(""));
		PrimeMain.objectlist.add(createDefaultModem(""));
		PrimeMain.objectlist.add(createDefaultWirelessRouter(""));
		PrimeMain.objectlist.add(createDefaultInternet(""));
		PrimeMain.objectlist.add(createDefaultRack(""));
		// PrimeMain.objectlist.add(createDefaultRackUnitWithObject(
		// AntivirusServer.class, ""));
		// PrimeMain.objectlist.add(createDefaultRackUnitWithObject(
		// BackupServer.class, ""));
		// PrimeMain.objectlist.add(createDefaultRackUnitWithObject(
		// DatabaseServer.class, ""));
		// PrimeMain.objectlist.add(createDefaultRackUnitWithObject(
		// FirewallServer.class, ""));
		// PrimeMain.objectlist.add(createDefaultRackUnitWithObject(
		// HTTPServer.class, ""));
		// PrimeMain.objectlist.add(createDefaultRackUnitWithObject(
		// MailServer.class, ""));
		// PrimeMain.objectlist.add(createDefaultRackUnitWithObject(
		// NASServer.class, ""));
		// PrimeMain.objectlist.add(createDefaultRackUnitWithObject(
		// PrinterServer.class, ""));
		// PrimeMain.objectlist.add(createDefaultRackUnitWithObject(
		// ProxyServer.class, ""));
		// PrimeMain.objectlist.add(createDefaultRackUnitWithObject(
		// VirtualizationServer.class, ""));
	}



	/**
	 * 
	 */
	private static Object[] createComponentsArray()
	{
		PrimeMain.ioLog.fine("Creating Default Components array.");
		Object[] components = new Object[5];

		components[0] = PrimeMain.standard_internal_components.getSt_MB();

		Motherboard mb = (Motherboard) components[0];

		mb.makeOneCPUportTaken();
		mb.makeOneRAMportTaken();
		mb.makeOneDUCportTaken();// For the CPU
		mb.makeOneDUCportTaken();// For the HDD

		components[0] = mb;
		components[1] = PrimeMain.standard_internal_components.getSt_CPU();
		components[2] = PrimeMain.standard_internal_components.getSt_RAM();
		components[3] = PrimeMain.standard_internal_components.getSt_HDD();
		components[4] = PrimeMain.standard_internal_components.getSt_DVDRW();


		return components;
	}


	/**
	 * 
	 */
	private static Software[] createSoftwareArray()
	{
		Software[] st_software = new Software[1];
		st_software[0] = PrimeMain.system_standard_OS[1];


		return st_software;
	}


	/**
	 * 
	 */
	private static Software[] createServerSoftwareArray()
	{
		Software[] st_software = new Software[1];
		st_software[0] = PrimeMain.system_standard_OS[2];


		return st_software;
	}



	private static Desktop createDefaultDesktop(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Desktop.");
		String objectName = PrimeMain.texts.getString("desktop");
		String objectDesc = desc;

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Desktop temp = new Desktop(objectName, objectDesc,
				createComponentsArray());

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(createSoftwareArray());

		return temp;
	}



	private static Laptop createDefaultLaptop(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Laptop.");
		String objectName = PrimeMain.texts.getString("laptop");
		String objectDesc = desc;

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		// Gets the standard components that will be added to the laptop
		Object[] st_components = createComponentsArray();

		Motherboard mb = (Motherboard) st_components[0];
		mb.makeOnePCIportTaken(); // For the NIC that will be added further
									// down.


		Laptop temp = new Laptop(objectName, objectDesc, st_components);


		// Internal Wireless NIC
		InternalNetworksCard intNIC = PrimeMain.standard_internal_components
				.getSt_IntNIC();
		intNIC.setType(ConnectionUtils.Wireless);

		// Add the internal NIC to the list of components on the Object(not the
		// "st_components" array of this class)
		temp.addComponent(intNIC);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(createSoftwareArray());

		return temp;
	}



	public static ThinClient createDefaultThinClient(String desc)
	{
		PrimeMain.ioLog.finer("Creating default ThinClient.");
		String objectName = PrimeMain.texts.getString("thinClient");
		String objectDesc = desc;

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = createComponentsArray();



		ThinClient temp = new ThinClient(objectName, objectDesc, st_components);
		try
		{
			PrimeMain.ioLog
					.finer("Attempting to remove default HDD from ThinClient.");
			// Gets the HDD from the array of components
			HDD removing = (HDD) ArrayManagment.getSpesificComponents(
					HDD.class, st_components, st_components.length)[0];

			ComponentsManagment.removeHDD(temp, removing);
			PrimeMain.ioLog.finer("Default HDD removed from ThinClient.");
		}
		catch ( ObjectNotFoundException e )
		{
			PrimeMain.ioLog
					.warning("HDD not found in ThinClient. - createDefaultThinClient - Fatal error.");
			e.printStackTrace();
		}
		catch ( MotherboardNotFound e )
		{
			PrimeMain.ioLog
					.warning("Motherboard not found in ThinClient. - createDefaultThinClient - Fatal error.");
			e.printStackTrace();
		}

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(createSoftwareArray());

		return temp;
	}



	private static GenericServer createGenericServer(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Generic Server.");
		String objectName = PrimeMain.texts.getString("GeneralServer");
		String objectDesc = desc;

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = createComponentsArray();



		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		GenericServer temp = new GenericServer(objectName, objectDesc,
				st_components);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(createServerSoftwareArray());

		return temp;
	}



	private static AntivirusServer createDefaultAntivirusServer(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Antivirus Server.");
		String objectName = PrimeMain.texts.getString("antivirusServer");
		String objectDesc = desc;

		String ObjectSWname = PrimeMain.texts.getString("antivirus");
		String ObjectSWdesc = "Standard antivirus server";
		String ObjectSWversion = "1";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = createComponentsArray();


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		AntivirusServer temp = new AntivirusServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(createServerSoftwareArray());

		return temp;
	}



	private static HTTPServer createDefaultHTTPServer(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Webserver Server.");
		String objectName = PrimeMain.texts.getString("httpServer");
		String objectDesc = desc;

		String ObjectSWname = "Apache";
		String ObjectSWdesc = "Standard Webserver";
		String ObjectSWversion = "2.2";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = createComponentsArray();



		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		HTTPServer temp = new HTTPServer(objectName, objectDesc, st_components,
				ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(createServerSoftwareArray());

		return temp;
	}



	private static BackupServer createDefaultBackupServer(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Backup Server.");
		String objectName = PrimeMain.texts.getString("backupServer");
		String objectDesc = desc;

		String ObjectSWname = PrimeMain.texts.getString("backup");
		String ObjectSWdesc = "Standard backup server";
		String ObjectSWversion = "1";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = createComponentsArray();


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		BackupServer temp = new BackupServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(createServerSoftwareArray());

		return temp;
	}



	private static NASServer createDefaultNASServer(String desc)
	{
		PrimeMain.ioLog.finer("Creating default NAS Server.");
		String objectName = PrimeMain.texts.getString("NASServer");
		String objectDesc = desc;

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = createComponentsArray();


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		NASServer temp = new NASServer(objectName, objectDesc, st_components);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(createServerSoftwareArray());

		return temp;
	}



	private static DatabaseServer createDefaultDatabaseServer(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Database Server.");
		String objectName = "Database Server";
		String objectDesc = desc;

		String ObjectSWname = "Database";
		String ObjectSWdesc = "Standard database server";
		String ObjectSWversion = "1";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = createComponentsArray();


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		DatabaseServer temp = new DatabaseServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(createServerSoftwareArray());

		return temp;
	}



	private static VirtualizationServer createDefaultVirtualizationServer(
			String desc)
	{
		PrimeMain.ioLog.finer("Creating default Virtualization Server.");
		String objectName = PrimeMain.texts.getString("virtualizationServer");
		String objectDesc = desc;

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = createComponentsArray();


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		VirtualizationServer temp = new VirtualizationServer(objectName,
				objectDesc, st_components);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(createServerSoftwareArray());

		return temp;
	}



	private static MailServer createDefaultMailServer(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Mail Server.");
		String objectName = PrimeMain.texts.getString("mailServer");
		String objectDesc = desc;

		String ObjectSWname = PrimeMain.texts.getString("email");
		String ObjectSWdesc = "Standard mail server";
		String ObjectSWversion = "1";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = createComponentsArray();


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		MailServer temp = new MailServer(objectName, objectDesc, st_components,
				ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(createServerSoftwareArray());

		return temp;
	}



	private static FirewallServer createDefaultFirewallServer(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Firewall Server.");
		String objectName = PrimeMain.texts.getString("firewallServer");
		String objectDesc = desc;

		String ObjectSWname = PrimeMain.texts.getString("firewall");
		String ObjectSWdesc = "Standard firewall server";
		String ObjectSWversion = "1";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = createComponentsArray();


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);

		FirewallServer temp = new FirewallServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(createServerSoftwareArray());

		return temp;
	}



	private static ProxyServer createDefaultProxyServer(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Proxy Server.");
		String objectName = PrimeMain.texts.getString("proxyServer");
		String objectDesc = desc;

		String ObjectSWname = PrimeMain.texts.getString("proxy");
		String ObjectSWdesc = "Standard proxy server";
		String ObjectSWversion = "1";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = createComponentsArray();


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);

		ProxyServer temp = new ProxyServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		temp.setSoftware(createServerSoftwareArray());

		return temp;
	}



	private static PrinterServer createDefaultPrinterServer(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Print Server.");
		String objectName = PrimeMain.texts.getString("printerServer");
		String objectDesc = desc;

		String ObjectSWname = "PrinterServer";
		String ObjectSWdesc = "Standard printer server";
		String ObjectSWversion = "1";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = createComponentsArray();


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);

		PrinterServer temp = new PrinterServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		temp.setSoftware(createServerSoftwareArray());

		return temp;
	}



	private static ExternalHDD createDefaultNAS(String desc)
	{
		PrimeMain.ioLog.finer("Creating default NAS object.");
		String objectName = PrimeMain.texts.getString("nas");
		String objectDesc = desc;

		int HDDsize = 500;


		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		HDD internalHDD = PrimeMain.standard_internal_components.getSt_HDD();
		internalHDD.setSize(1000);

		objectMB.setDUCconnectionType(internalHDD.getPort());

		ExternalHDD temp = new ExternalHDD(objectName, objectDesc, HDDsize,
				objectMB);

		temp.addComponent(internalHDD);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	private static Scanner createDefaultScanner(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Scanner object.");
		String objectName = PrimeMain.texts.getString("scanner");
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		Scanner temp = new Scanner(objectName, objectDesc, Sresolution,
				objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	private static Printer createDefaultPrinter(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Printer object.");
		String objectName = PrimeMain.texts.getString("printer");
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		Printer temp = new Printer(objectName, objectDesc, Sresolution,
				objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	private static Fax createDefaultFax(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Fax object.");
		String objectName = PrimeMain.texts.getString("fax");
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);
		objectMB.setMaxIntegratedLANs(1);
		objectMB.setIntegLANPortsAvailable(1);


		Fax temp = new Fax(objectName, objectDesc, Sresolution, objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	private static MultifunctionPrinter createDefaultMFP(String desc)
	{
		PrimeMain.ioLog
				.finer("Creating default Multi Function Printer object.");
		String objectName = PrimeMain.texts.getString("multifunctionPrinter");
		String objectDesc = desc;

		String Sresolution[] = { "1280x1020" };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		MultifunctionPrinter temp = new MultifunctionPrinter(objectName,
				objectDesc, Sresolution, objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	private static NetworkPrinter createDefaultNetworkPrinter(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Network Printer object.");
		String objectName = PrimeMain.texts.getString("printer");
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setMaxIntegratedLANs(1);
		objectMB.setUSBPortsAvailable(1);
		objectMB.setIntegLANPortsAvailable(1);


		NetworkPrinter temp = new NetworkPrinter(objectName, objectDesc,
				Sresolution, objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	private static NetworkMultifunctionPrinter createDefaultNetworkMFP(
			String desc)
	{
		PrimeMain.ioLog
				.finer("Creating default Network Multi Function Printer object.");
		String objectName = PrimeMain.texts
				.getString("networkMultifunctionPrinter");
		String objectDesc = desc;

		String Sresolution[] = { "1280x1020" };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

		objectMB.setMaxUSBs(1);
		objectMB.setMaxIntegratedLANs(1);
		objectMB.setUSBPortsAvailable(1);
		objectMB.setIntegLANPortsAvailable(1);


		NetworkMultifunctionPrinter temp = new NetworkMultifunctionPrinter(
				objectName, objectDesc, Sresolution, objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	private static Hub createDefaultHub(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Hub object.");
		String objectName = PrimeMain.texts.getString("hub");
		String objectDesc = desc;

		int outPorts = 16;
		int inPorts = 16;
		String[] DuplexSupport = { ConnectionUtils.FullDuplex };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

		objectMB.setMaxIntegratedLANs(16);
		objectMB.setIntegLANPortsAvailable(16);


		Hub temp = new Hub(objectName, objectDesc, null, objectMB, outPorts,
				inPorts, DuplexSupport);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	private static Switch createDefaultSwitch(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Switch object.");
		String objectName = PrimeMain.texts.getString("switch");
		String objectDesc = desc;

		int outPorts = 16;
		int inPorts = 16;
		String[] DuplexSupport = { ConnectionUtils.FullDuplex };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

		objectMB.setMaxIntegratedLANs(16);
		objectMB.setIntegLANPortsAvailable(16);


		Switch temp = new Switch(objectName, objectDesc, null, objectMB,
				outPorts, inPorts, DuplexSupport);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	private static Router createDefaultRouter(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Router object.");
		String objectName = PrimeMain.texts.getString("router");
		String objectDesc = desc;

		int outPorts = 4;
		int inPorts = 4;
		String[] DuplexSupport = { ConnectionUtils.FullDuplex };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

		objectMB.setMaxIntegratedLANs(4);
		objectMB.setIntegLANPortsAvailable(4);


		Router temp = new Router(objectName, objectDesc, null, objectMB,
				outPorts, inPorts, DuplexSupport);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	private static Modem createDefaultModem(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Modem object.");
		String objectName = PrimeMain.texts.getString("modem");
		String objectDesc = desc;


		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

		objectMB.setMaxIntegratedLANs(1);
		objectMB.setIntegLANPortsAvailable(1);
		objectMB.setMaxCoaxs(1);
		objectMB.setCoaxPortsAvailable(1);


		Modem temp = new Modem(objectName, objectDesc, null, objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	private static Router createDefaultWirelessRouter(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Wireless-router object.");
		String objectName = PrimeMain.texts.getString("wirelessRouter");
		String objectDesc = desc;

		int outPorts = 4;
		int inPorts = 4;
		String[] DuplexSupport = { ConnectionUtils.FullDuplex };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

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
		supConIntWithWLan[supConIntWithWLan.length - 1] = ConnectionUtils.Wireless;


		temp.setSupportedConnectionInterfaces(supConIntWithWLan);

		return temp;
	}



	private static Internet createDefaultInternet(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Internet object.");
		String objectName = PrimeMain.texts.getString("internet");
		String objectDesc = desc;

		String[] SupConInt = { ConnectionUtils.RJ45, ConnectionUtils.Coax };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

		objectMB.setMaxIntegratedLANs(1);
		objectMB.setIntegLANPortsAvailable(1);
		objectMB.setCoaxPortsAvailable(1);
		objectMB.setMaxCoaxs(1);


		Internet temp = new Internet(objectName, objectDesc, objectName,
				SupConInt, objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();


		return temp;
	}




	// RACK OBJECTS


	private static Rack createDefaultRack(String desc)
	{
		PrimeMain.ioLog.finer("Creating default Rack object.");
		String objectName = PrimeMain.texts.getString("rack");
		String objectDesc = desc;

		String[] SupConInt = {};

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}


		Rack rack = new Rack(objectName, objectDesc, SupConInt, 24);

		return rack;
	}



	private static RackUnit createDefaultRackUnitWithObject(Class<?> classType,
			String desc)
	{
		Object obj = null;

		if ( classType.equals(Hub.class) )
		{
			obj = createDefaultHub(desc);
		}
		else if ( classType.equals(Router.class) )
		{
			obj = createDefaultRouter(desc);
		}
		else if ( classType.equals(Switch.class) )
		{
			obj = createDefaultSwitch(desc);
		}
		else if ( classType.equals(AntivirusServer.class) )
		{
			obj = createDefaultAntivirusServer(desc);
		}
		else if ( classType.equals(BackupServer.class) )
		{
			obj = createDefaultBackupServer(desc);
		}
		else if ( classType.equals(DatabaseServer.class) )
		{
			obj = createDefaultDatabaseServer(desc);
		}
		else if ( classType.equals(FirewallServer.class) )
		{
			obj = createDefaultFirewallServer(desc);
		}
		else if ( classType.equals(HTTPServer.class) )
		{
			obj = createDefaultHTTPServer(desc);
		}
		else if ( classType.equals(MailServer.class) )
		{
			obj = createDefaultMailServer(desc);
		}
		else if ( classType.equals(NASServer.class) )
		{
			obj = createDefaultNASServer(desc);
		}
		else if ( classType.equals(PrinterServer.class) )
		{
			obj = createDefaultPrinterServer(desc);
		}
		else if ( classType.equals(ProxyServer.class) )
		{
			obj = createDefaultProxyServer(desc);
		}
		else if ( classType.equals(VirtualizationServer.class) )
		{
			obj = createDefaultVirtualizationServer(desc);
		}


		if ( obj != null )
		{
			return new RackUnit(obj.getObjectName(), obj.getDescription(), obj);
		}


		// If no object was created.
		return null;
	}



	// GENERAL HARDWARE
	private static ExternalHardware getGenericExternalHardware()
	{
		PrimeMain.ioLog
				.info("Creating default Generic External Hardware object.");
		Motherboard objectMB = PrimeMain.standard_internal_components
				.getHw_MB();

		GenericDevice temp = new GenericDevice(
				PrimeMain.texts.getString("genericDevice"),
				PrimeMain.texts.getString("genericDevice"), objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}
}
