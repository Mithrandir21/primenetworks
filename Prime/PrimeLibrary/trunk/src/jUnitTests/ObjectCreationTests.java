/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jUnitTests;


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
import org.junit.Before;
import org.junit.Test;

import connections.ConnectionUtils;
import exceptions.MotherboardNotFound;
import exceptions.ObjectNotFoundException;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ObjectCreationTests
{
	// @BeforeClass
	// public static void oneTimeSetUp()
	// {
	// System.out.println("@BeforeClass - oneTimeSetUp");
	// }
	//
	// @AfterClass
	// public static void oneTimeTearDown()
	// {
	// System.out.println("@AfterClass - oneTimeTearDown");
	// }
	//
	// @After
	// public void tearDown()
	// {
	// System.out.println("@After - tearDown");
	// }



	Object[] components = new Object[5];

	Motherboard HW_MB;

	Software[] st_software = new Software[1];



	Object[] objects = new Object[22];


	/**
	 * TODO - Description
	 */
	@Before
	public void setUp() throws Exception
	{
		components[0] = new Motherboard("Standard MB", "Standard MB Desc",
				"MB Producer", "ATX", "Intel 775", "DDR2", 1, 3, 2, 4, 4, 0,
				"SATA", "AGP", true, true, true, 1);

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
	}


	/**
	 * Creates all the system default objects.
	 */
	@Test
	public void ObjectCreation()
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



	@Test
	public void objectDataReading()
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




		Desktop desk = (Desktop) objects[0];
		Assert.assertNotNull(desk);

		// The Object serial is not empty
		Assert.assertNotNull(desk.getObjectSerial());

		// The Object name is not empty
		Assert.assertNotNull(desk.getObjectName());

		// The Object description is not empty
		Assert.assertNotNull(desk.getDescription());

		// The Object supported interfaces is not empty
		Assert.assertNotNull(desk.getSupportedConnectionInterfaces());

		// The Components array is not empty
		Assert.assertNotNull(desk.getComponents());

		// The Components array is not empty
		Assert.assertNotNull(desk.getComponents()[0]);

		// The first component is a Motherboard component
		Assert.assertEquals(Motherboard.class, desk.getComponents()[0]
				.getClass());

		Motherboard mb = (Motherboard) desk.getComponents()[0];


		// The Motherboard serial is not empty
		Assert.assertNotNull(mb.getObjectSerial());

		// The Motherboard name is not empty
		Assert.assertNotNull(mb.getObjectName());

		// The Motherboard description is not empty
		Assert.assertNotNull(mb.getDescription());

		// The Motherboard supported interfaces is empty
		Assert.assertNull(mb.getSupportedConnectionInterfaces());
	}



	private Desktop createDefaultDesktop(String desc)
	{
		String objectName = "Desktop";
		String objectDesc = desc;

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Desktop temp = new Desktop(objectName, objectDesc, components);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);


		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}







	private Laptop createDefaultLaptop(String desc)
	{
		String objectName = "Laptop";
		String objectDesc = desc;

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		// Gets the standard components that will be added to the laptop
		Object[] st_components = components;

		Motherboard mb = (Motherboard) st_components[0];
		mb.makeOnePCIportTaken(); // For the NIC that will be added further down.


		Laptop temp = new Laptop(objectName, objectDesc, st_components);


		// Internal Wireless NIC
		InternalNetworksCard intNIC = new InternalNetworksCard("Standard NIC",
				"Standard NIC Desc", "Standard NIC Producer",
				ConnectionUtils.PCI, "00:00:00:00:00:01", ConnectionUtils.RJ45);
		intNIC.setType(ConnectionUtils.Wireless);

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



	public ThinClient createDefaultThinClient(String desc)
	{
		String objectName = "ThinClient";
		String objectDesc = desc;

		if ( objectDesc == "" )
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
			// This is impossible since the HDD has just been added at the top of this class
			e.printStackTrace();
		}
		catch ( MotherboardNotFound e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		// Adds OS
		temp.setSoftware(st_software);

		return temp;
	}



	private HTTPServer createDefaultHTTPServer(String desc)
	{
		String objectName = "HttpServer";
		String objectDesc = desc;

		String ObjectSWname = "Apache";
		String ObjectSWdesc = "Standard Webserver";
		String ObjectSWversion = "2.2";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;



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




	private BackupServer createDefaultBackupServer(String desc)
	{
		String objectName = "BackupServer";
		String objectDesc = desc;

		String ObjectSWname = "BackupSoftware";
		String ObjectSWdesc = "Standard backup server";
		String ObjectSWversion = "1";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;


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



	private DatabaseServer createDefaultDatabaseServer(String desc)
	{
		String objectName = "Database Server";
		String objectDesc = desc;

		String ObjectSWname = "Database Software";
		String ObjectSWdesc = "Standard Database Software";
		String ObjectSWversion = "1";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;


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



	private MailServer createDefaultMailServer(String desc)
	{
		String objectName = "MailServer";
		String objectDesc = desc;

		String ObjectSWname = "Email Software";
		String ObjectSWdesc = "Standard mail Software";
		String ObjectSWversion = "1";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;


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



	private FirewallServer createDefaultFirewallServer(String desc)
	{
		String objectName = "FirewallServer";
		String objectDesc = desc;

		String ObjectSWname = "Firewall Software";
		String ObjectSWdesc = "Standard firewall Software";
		String ObjectSWversion = "1";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;


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



	private ProxyServer createDefaultProxyServer(String desc)
	{
		String objectName = "ProxyServer";
		String objectDesc = desc;

		String ObjectSWname = "Proxy Software";
		String ObjectSWdesc = "Standard proxy Software";
		String ObjectSWversion = "1";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;


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



	private PrinterServer createDefaultPrinterServer(String desc)
	{
		String objectName = "PrinterServer";
		String objectDesc = desc;

		String ObjectSWname = "PrinterServer Software";
		String ObjectSWdesc = "Standard printer Software";
		String ObjectSWversion = "1";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Object[] st_components = components;


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



	private Scanner createDefaultScanner(String desc)
	{
		String objectName = "Scanner";
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		Scanner temp = new Scanner(objectName, objectDesc, Sresolution,
				objectMB);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}


	private Printer createDefaultPrinter(String desc)
	{
		String objectName = "Printer";
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		Printer temp = new Printer(objectName, objectDesc, Sresolution,
				objectMB);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}



	private Fax createDefaultFax(String desc)
	{
		String objectName = "Fax";
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

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


	private MultifunctionPrinter createDefaultMFP(String desc)
	{
		String objectName = "MultifunctionPrinter";
		String objectDesc = desc;

		String Sresolution[] = { "1280x1020" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setMaxUSBs(1);
		objectMB.setUSBPortsAvailable(1);


		MultifunctionPrinter temp = new MultifunctionPrinter(objectName,
				objectDesc, Sresolution, objectMB);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}



	private NetworkPrinter createDefaultNetworkPrinter(String desc)
	{
		String objectName = "Printer";
		String objectDesc = desc;

		String Sresolution = "1280x1020";

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setMaxUSBs(1);
		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(1);
		objectMB.setUSBPortsAvailable(1);
		objectMB.setIntegLANPortsAvailable(1);


		NetworkPrinter temp = new NetworkPrinter(objectName, objectDesc,
				Sresolution, objectMB);

		// Internal NIC
		// InternalNetworksCard intNIC =
		// HW_MB.getSt_IntNIC();
		// intNIC.setType(ConnectionUtils.Wireless);

		// Add the internal NIC to the list of components on the Object(not the
		// "st_components" array of this class)
		// temp.addComponent(intNIC);


		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}



	private NetworkMultifunctionPrinter createDefaultNetworkMFP(String desc)
	{
		String objectName = "NetworkMultifunctionPrinter";
		String objectDesc = desc;

		String Sresolution[] = { "1280x1020" };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

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





	private Hub createDefaultHub(String desc)
	{
		String objectName = "Hub";
		String objectDesc = desc;

		int outPorts = 16;
		int inPorts = 16;
		String[] DuplexSupport = { ConnectionUtils.FullDuplex };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

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




	private Switch createDefaultSwitch(String desc)
	{
		String objectName = "Switch";
		String objectDesc = desc;

		int outPorts = 16;
		int inPorts = 16;
		String[] DuplexSupport = { ConnectionUtils.FullDuplex };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

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



	private Router createDefaultRouter(String desc)
	{
		String objectName = "Router";
		String objectDesc = desc;

		int outPorts = 4;
		int inPorts = 4;
		String[] DuplexSupport = { ConnectionUtils.FullDuplex };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

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



	private Modem createDefaultModem(String desc)
	{
		String objectName = "Modem";
		String objectDesc = desc;


		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(1);
		objectMB.setIntegLANPortsAvailable(1);
		objectMB.setMaxCoaxs(1);
		objectMB.setCoaxPortsAvailable(1);


		Modem temp = new Modem(objectName, objectDesc, null, objectMB);

		// // Internal Wireless NIC
		// InternalNetworksCard intNIC = HW_MB
		// .getSt_IntNIC();
		// intNIC.setType(ConnectionUtils.Coax);
		//
		// // Add the internal NIC to the list of components on the Object(not
		// the
		// // "st_components" array of this class)
		// temp.addComponent(intNIC);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);

		return temp;
	}



	private Router createDefaultWirelessRouter(String desc)
	{
		String objectName = "WirelessRouter";
		String objectDesc = desc;

		int outPorts = 4;
		int inPorts = 4;
		String[] DuplexSupport = { ConnectionUtils.FullDuplex };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

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
		supConIntWithWLan[supConIntWithWLan.length - 1] = ConnectionUtils.Wireless;


		temp.setSupportedConnectionInterfaces(supConIntWithWLan);

		return temp;
	}


	private Internet createDefaultInternet(String desc)
	{
		String objectName = "Internet";
		String objectDesc = desc;

		String[] SupConInt = { ConnectionUtils.RJ45, ConnectionUtils.Coax };

		if ( objectDesc == "" )
		{
			objectDesc = objectName;
		}

		Motherboard objectMB = HW_MB;

		objectMB.setIntegLANcard(true);
		objectMB.setMaxIntegratedLANs(1);
		objectMB.setIntegLANPortsAvailable(1);
		objectMB.setCoaxPortsAvailable(1);
		objectMB.setMaxCoaxs(1);


		Internet temp = new Internet(objectName, objectDesc, objectName,
				SupConInt, objectMB);

		// // Internal Wireless NIC
		// InternalNetworksCard intNIC = HW_MB
		// .getSt_IntNIC();
		// intNIC.setType(ConnectionUtils.Coax);
		//
		// // Add the internal NIC to the list of components on the Object(not
		// the
		// // "st_components" array of this class)
		// temp.addComponent(intNIC);

		String[] supportedConnectionInterfaces = ComponentsManagment
				.getSupportedInterfaces(temp);

		temp.setSupportedConnectionInterfaces(supportedConnectionInterfaces);


		return temp;
	}

}
