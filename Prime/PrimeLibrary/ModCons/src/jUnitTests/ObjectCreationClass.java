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
package jUnitTests;


import java.util.ArrayList;

import managment.ArrayManagment;
import managment.ComponentsManagment;
import objects.Object;
import objects.Software;
import objects.clientObjects.Desktop;
import objects.clientObjects.Laptop;
import objects.clientObjects.ThinClient;
import objects.hardwareObjects.CPU;
import objects.hardwareObjects.Discdrive;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import objects.hardwareObjects.Ram;
import objects.infrastructureObjects.Hub;
import objects.infrastructureObjects.Internet;
import objects.infrastructureObjects.Modem;
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
import objects.softwareObjects.OperatingSystem;

import org.junit.Assert;

import connections.ConnectionUtils;
import exceptions.MotherboardNotFound;
import exceptions.ObjectNotFoundException;


/**
 * This test class contains functions that test the systems ability to create
 * {@link Object Objects} correctly.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ObjectCreationClass
{
	private static Object[] objects = new Object[22];

	private static Object[] components = new Object[5];

	private static Software[] st_software = new Software[1];

	private static Motherboard HW_MB;


	public static Object[] ObjectCreationClass()
	{
		components[0] = new Motherboard("Standard MB", "Standard MB Desc",
				"MB Producer", "ATX", "Intel 775", "DDR2", 1, 3, 2, 4, 4, 0, 0,
				"SATA", "AGP", true, true, 1);

		HW_MB = new Motherboard("Standard Hardware MB",
				"Standard Hardware Desc");



		Motherboard mb = (Motherboard) components[0];

		mb.makeOneCPUportTaken();
		mb.makeOneRAMportTaken();
		mb.makeOneDUCportTaken();// For the CPU
		mb.makeOneDUCportTaken();// For the HDD

		components[0] = mb;
		components[1] = new CPU("Standard CPU", "Standard CPU Desc",
				"Intel 775", 512);
		components[2] = new Ram("Standard RAM", "Standard RAM Desc", "DDR2",
				1024);
		components[3] = new HDD("Standard HDD", "Standard HDD Desc", "SATA",
				160);
		components[4] = new Discdrive("Standard Discdrive",
				"Standard Discdrive Desc", "DVDRW", "SATA");

		st_software[0] = new OperatingSystem("Windows XP", "Standard XP OS",
				"5.1");




		ObjectCreation();



		return objects;
	}




	public static ArrayList<Object> ObjectCreationClassArrayList()
	{
		components[0] = new Motherboard("Standard MB", "Standard MB Desc",
				"MB Producer", "ATX", "Intel 775", "DDR2", 1, 3, 2, 4, 4, 0, 0,
				"SATA", "AGP", true, true, 1);

		HW_MB = new Motherboard("Standard Hardware MB",
				"Standard Hardware Desc");



		Motherboard mb = (Motherboard) components[0];

		mb.makeOneCPUportTaken();
		mb.makeOneRAMportTaken();
		mb.makeOneDUCportTaken();// For the CPU
		mb.makeOneDUCportTaken();// For the HDD

		components[0] = mb;
		components[1] = new CPU("Standard CPU", "Standard CPU Desc",
				"Intel 775", 512);
		components[2] = new Ram("Standard RAM", "Standard RAM Desc", "DDR2",
				1024);
		components[3] = new HDD("Standard HDD", "Standard HDD Desc", "SATA",
				160);
		components[4] = new Discdrive("Standard Discdrive",
				"Standard Discdrive Desc", "DVDRW", "SATA");

		st_software[0] = new OperatingSystem("Windows XP", "Standard XP OS",
				"5.1");


		ObjectCreation();


		ArrayList<Object> list = new ArrayList<Object>();

		for ( int i = 0; i < objects.length; i++ )
		{
			list.add(objects[i]);
		}

		return list;
	}



	/**
	 * Creates all the system default objects.
	 */
	public static void ObjectCreation()
	{
		objects[0] = createDefaultDesktop("");
		Assert.assertNotNull(objects[0]);
		objects[1] = createDefaultLaptop("");
		Assert.assertNotNull(objects[1]);
		objects[2] = createDefaultThinClient("");
		Assert.assertNotNull(objects[2]);
		objects[3] = createDefaultHTTPServer("");
		Assert.assertNotNull(objects[3]);
		objects[4] = createDefaultBackupServer("");
		Assert.assertNotNull(objects[4]);
		objects[5] = createDefaultDatabaseServer("");
		Assert.assertNotNull(objects[5]);
		objects[6] = createDefaultMailServer("");
		Assert.assertNotNull(objects[6]);
		objects[7] = createDefaultFirewallServer("");
		Assert.assertNotNull(objects[7]);
		objects[8] = createDefaultProxyServer("");
		Assert.assertNotNull(objects[8]);
		objects[9] = createDefaultPrinterServer("");
		Assert.assertNotNull(objects[9]);
		objects[10] = createDefaultScanner("");
		Assert.assertNotNull(objects[10]);
		objects[11] = createDefaultPrinter("");
		Assert.assertNotNull(objects[1]);
		objects[12] = createDefaultFax("");
		Assert.assertNotNull(objects[12]);
		objects[13] = createDefaultMFP("");
		Assert.assertNotNull(objects[13]);
		objects[14] = createDefaultNetworkPrinter("");
		Assert.assertNotNull(objects[14]);
		objects[15] = createDefaultNetworkMFP("");
		Assert.assertNotNull(objects[15]);
		objects[16] = createDefaultHub("");
		Assert.assertNotNull(objects[16]);
		objects[17] = createDefaultSwitch("");
		Assert.assertNotNull(objects[17]);
		objects[18] = createDefaultRouter("");
		Assert.assertNotNull(objects[18]);
		objects[19] = createDefaultModem("");
		Assert.assertNotNull(objects[19]);
		objects[20] = createDefaultWirelessRouter("");
		Assert.assertNotNull(objects[20]);
		objects[21] = createDefaultInternet("");
		Assert.assertNotNull(objects[21]);
	}






	public static Desktop createDefaultDesktop(String desc)
	{
		String objectName = "Desktop";
		String objectDesc = desc;

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Desktop temp = new Desktop(objectName, objectDesc, components);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}







	public static Laptop createDefaultLaptop(String desc)
	{
		String objectName = "Laptop";
		String objectDesc = desc;

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		// Gets the standard components that will be added to the laptop
		Object[] st_components = components;

		Motherboard mb = (Motherboard) st_components[0];
		mb.makeOnePCIportTaken(); // For the NIC that will be added further
									// down.


		Laptop temp = new Laptop(objectName, objectDesc, st_components);


		// Internal Wireless NIC
		InternalNetworksCard intNIC = new InternalNetworksCard("Standard NIC",
				"Standard NIC Desc", "Standard NIC Producer",
				ConnectionUtils.PCI, "00:00:00:00:00:01", ConnectionUtils.RJ45);
		intNIC.setType(ConnectionUtils.Wireless);

		// Add the internal NIC to the list of components on the Object(not the
		// "st_components" array of this class)
		temp.addComponent(intNIC);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	public static ThinClient createDefaultThinClient(String desc)
	{
		String objectName = "ThinClient";
		String objectDesc = desc;

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;


		ThinClient temp = new ThinClient(objectName, objectDesc, st_components);

		try
		{ // Gets the HDD from the array of components
			HDD removing = (HDD) ArrayManagment.getSpesificComponents(
					HDD.class, st_components, st_components.length)[0];

			ComponentsManagment.removeHDD(temp, removing);
		}
		catch ( ObjectNotFoundException e )
		{
			// This is impossible since the HDD has just been added at the top
			// of this class
			e.printStackTrace();
		}
		catch ( MotherboardNotFound e )
		{
			e.printStackTrace();
		}

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	public static HTTPServer createDefaultHTTPServer(String desc)
	{
		String objectName = "HttpServer";
		String objectDesc = desc;

		String ObjectSWname = "Apache";
		String ObjectSWdesc = "Standard Webserver";
		String ObjectSWversion = "2.2";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;



		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		HTTPServer temp = new HTTPServer(objectName, objectDesc, st_components,
				ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}




	public static BackupServer createDefaultBackupServer(String desc)
	{
		String objectName = "BackupServer";
		String objectDesc = desc;

		String ObjectSWname = "BackupSoftware";
		String ObjectSWdesc = "Standard backup server";
		String ObjectSWversion = "1";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		BackupServer temp = new BackupServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	public static DatabaseServer createDefaultDatabaseServer(String desc)
	{
		String objectName = "Database Server";
		String objectDesc = desc;

		String ObjectSWname = "Database Software";
		String ObjectSWdesc = "Standard Database Software";
		String ObjectSWversion = "1";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		DatabaseServer temp = new DatabaseServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	public static MailServer createDefaultMailServer(String desc)
	{
		String objectName = "MailServer";
		String objectDesc = desc;

		String ObjectSWname = "Email Software";
		String ObjectSWdesc = "Standard mail Software";
		String ObjectSWversion = "1";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);


		MailServer temp = new MailServer(objectName, objectDesc, st_components,
				ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	public static FirewallServer createDefaultFirewallServer(String desc)
	{
		String objectName = "FirewallServer";
		String objectDesc = desc;

		String ObjectSWname = "Firewall Software";
		String ObjectSWdesc = "Standard firewall Software";
		String ObjectSWversion = "1";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);

		FirewallServer temp = new FirewallServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	public static ProxyServer createDefaultProxyServer(String desc)
	{
		String objectName = "ProxyServer";
		String objectDesc = desc;

		String ObjectSWname = "Proxy Software";
		String ObjectSWdesc = "Standard proxy Software";
		String ObjectSWversion = "1";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);

		ProxyServer temp = new ProxyServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		temp.setSoftware(st_software);

		return temp;
	}



	public static PrinterServer createDefaultPrinterServer(String desc)
	{
		String objectName = "PrinterServer";
		String objectDesc = desc;

		String ObjectSWname = "PrinterServer Software";
		String ObjectSWdesc = "Standard printer Software";
		String ObjectSWversion = "1";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;


		Motherboard serverMB = (Motherboard) st_components[0];
		serverMB.setMaxIntegratedLANs(2);
		serverMB.setIntegLANPortsAvailable(2);

		PrinterServer temp = new PrinterServer(objectName, objectDesc,
				st_components, ObjectSWname, ObjectSWdesc, ObjectSWversion);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		temp.setSoftware(st_software);

		return temp;
	}



	public static Scanner createDefaultScanner(String desc)
	{
		String objectName = "Scanner";
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		Scanner temp = new Scanner(objectName, objectDesc, Sresolution,
				objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}


	public static Printer createDefaultPrinter(String desc)
	{
		String objectName = "Printer";
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		Printer temp = new Printer(objectName, objectDesc, Sresolution,
				objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	public static Fax createDefaultFax(String desc)
	{
		String objectName = "Fax";
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);
		objectMB.setMaxIntegratedLANs(1);
		objectMB.setIntegLANPortsAvailable(1);


		Fax temp = new Fax(objectName, objectDesc, Sresolution, objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}


	public static MultifunctionPrinter createDefaultMFP(String desc)
	{
		String objectName = "MultifunctionPrinter";
		String objectDesc = desc;

		String Sresolution[] = { "1280x1020" };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		MultifunctionPrinter temp = new MultifunctionPrinter(objectName,
				objectDesc, Sresolution, objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	public static NetworkPrinter createDefaultNetworkPrinter(String desc)
	{
		String objectName = "Printer";
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

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



	public static NetworkMultifunctionPrinter createDefaultNetworkMFP(
			String desc)
	{
		String objectName = "NetworkMultifunctionPrinter";
		String objectDesc = desc;

		String Sresolution[] = { "1280x1020" };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

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





	public static Hub createDefaultHub(String desc)
	{
		String objectName = "Hub";
		String objectDesc = desc;

		int outPorts = 16;
		int inPorts = 16;
		String[] DuplexSupport = { ConnectionUtils.FullDuplex };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setMaxIntegratedLANs(16);
		objectMB.setIntegLANPortsAvailable(16);


		Hub temp = new Hub(objectName, objectDesc, null, objectMB, outPorts,
				inPorts, DuplexSupport);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}




	public static Switch createDefaultSwitch(String desc)
	{
		String objectName = "Switch";
		String objectDesc = desc;

		int outPorts = 16;
		int inPorts = 16;
		String[] DuplexSupport = { ConnectionUtils.FullDuplex };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setMaxIntegratedLANs(16);
		objectMB.setIntegLANPortsAvailable(16);


		Switch temp = new Switch(objectName, objectDesc, null, objectMB,
				outPorts, inPorts, DuplexSupport);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	public static Router createDefaultRouter(String desc)
	{
		String objectName = "Router";
		String objectDesc = desc;

		int outPorts = 4;
		int inPorts = 4;
		String[] DuplexSupport = { ConnectionUtils.FullDuplex };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setMaxIntegratedLANs(4);
		objectMB.setIntegLANPortsAvailable(4);


		Router temp = new Router(objectName, objectDesc, null, objectMB,
				outPorts, inPorts, DuplexSupport);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	public static Modem createDefaultModem(String desc)
	{
		String objectName = "Modem";
		String objectDesc = desc;


		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setMaxIntegratedLANs(1);
		objectMB.setIntegLANPortsAvailable(1);
		objectMB.setMaxCoaxs(1);
		objectMB.setCoaxPortsAvailable(1);


		Modem temp = new Modem(objectName, objectDesc, null, objectMB);

		// Determines the supported connection interface on the device.
		temp.revalidateSupportedConnectionInterfaces();

		return temp;
	}



	public static Router createDefaultWirelessRouter(String desc)
	{
		String objectName = "WirelessRouter";
		String objectDesc = desc;

		int outPorts = 4;
		int inPorts = 4;
		String[] DuplexSupport = { ConnectionUtils.FullDuplex };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setMaxIntegratedLANs(4);
		objectMB.setIntegLANPortsAvailable(4);


		WirelessRouter temp = new WirelessRouter(objectName, objectDesc, null,
				objectMB, outPorts, inPorts, DuplexSupport, true);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		String[] supConIntWithWLan = new String[supportedConnectionInterfaces.length + 1];

		// Adds all the previous supported connection interfaces to the new
		// array
		System.arraycopy(supportedConnectionInterfaces, 0, supConIntWithWLan,
				0, supportedConnectionInterfaces.length);

		// Places the Wireless string at the end of the array
		supConIntWithWLan[supConIntWithWLan.length - 1] = ConnectionUtils.Wireless;


		temp.setSupportedConnectionInterfaces(supConIntWithWLan);

		return temp;
	}

	public static Internet createDefaultInternet(String desc)
	{
		String objectName = "Internet";
		String objectDesc = desc;

		String[] SupConInt = { ConnectionUtils.RJ45, ConnectionUtils.Coax };

		if ( objectDesc.equals("") )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

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

}
