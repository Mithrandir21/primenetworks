/**
 * 
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
public class ObjectSManipulationsTests
{

	Object[] components = new Object[5];

	Motherboard HW_MB;

	Software[] st_software = new Software[1];



	Object[] objects = new Object[22];


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




		objects[0] = createDefaultDesktop("");
		objects[1] = createDefaultLaptop("");
		objects[2] = createDefaultThinClient("");
		objects[3] = createDefaultHTTPServer("");
		objects[4] = createDefaultBackupServer("");
		objects[5] = createDefaultDatabaseServer("");
		objects[6] = createDefaultMailServer("");
		objects[7] = createDefaultFirewallServer("");
		objects[8] = createDefaultProxyServer("");
		objects[9] = createDefaultPrinterServer("");
		objects[10] = createDefaultScanner("");
		objects[11] = createDefaultPrinter("");
		objects[12] = createDefaultFax("");
		objects[13] = createDefaultMFP("");
		objects[14] = createDefaultNetworkPrinter("");
		objects[15] = createDefaultNetworkMFP("");
		objects[16] = createDefaultHub("");
		objects[17] = createDefaultSwitch("");
		objects[18] = createDefaultRouter("");
		objects[19] = createDefaultModem("");
		objects[20] = createDefaultWirelessRouter("");
		objects[21] = createDefaultInternet("");
	}


	@Test
	public void objectFieldsManipulation()
	{
		Assert.assertNotNull(objects[0]);


		Desktop desk = (Desktop) objects[0];

		desk.setObjectName("ObjectNameTest");
		Assert.assertEquals("ObjectNameTest", desk.getObjectName());

		desk.setDescription("ObjectDescTest");
		Assert.assertEquals("ObjectDescTest", desk.getDescription());
	}


	@Test
	public void objectMotherboardManipulation()
	{
		Assert.assertNotNull(objects[0]);

		// The first component is a Motherboard component
		Assert.assertEquals(Motherboard.class, objects[0].getComponents()[0]
				.getClass());

		Motherboard mb = (Motherboard) objects[0].getComponents()[0];



		mb.setProducer("MBProducerTest");
		Assert.assertEquals("MBProducerTest", mb.getProducer());

		mb.setForm("MBFormTest");
		Assert.assertEquals("MBFormTest", mb.getForm());

		mb.setSocket("MBSocketTest");
		Assert.assertEquals("MBSocketTest", mb.getSocket());

		mb.setBusSpeed(100);
		Assert.assertEquals(100, mb.getBusSpeed());

		mb.setChipset("MBChipsetTest");
		Assert.assertEquals("MBChipsetTest", mb.getChipset());

		mb.setMaxCPUs(100);
		Assert.assertEquals(100, mb.getMaxCPUs());

		mb.setMaxPCIs(100);
		Assert.assertEquals(100, mb.getMaxPCIs());

		mb.setMaxRAMs(100);
		Assert.assertEquals(100, mb.getMaxRAMs());

		mb.setMaxUSBs(100);
		Assert.assertEquals(100, mb.getMaxUSBs());

		mb.setMaxDUCs(100);
		Assert.assertEquals(100, mb.getMaxDUCs());

		mb.setMaxIntegratedLANs(100);
		Assert.assertEquals(100, mb.getMaxIntegLANs());

		mb.setMaxCoaxs(100);
		Assert.assertEquals(100, mb.getMaxCoaxs());

		mb.setGraphicalPortType("MBGPUportTypeTest");
		Assert.assertEquals("MBGPUportTypeTest", mb.getGraphicalPort());

		mb.setDUCconnectionType("MBDUCportTypeTest");
		Assert.assertEquals("MBDUCportTypeTest", mb.getDUCconnectionType());

		mb.setRAMtype("MBramPortTypeTest");
		Assert.assertEquals("MBramPortTypeTest", mb.getRAMtype());

		mb.setIntegAudioCard(true);
		Assert.assertEquals(true, mb.isIntegAudioCard());

		mb.setIntegGraphicalCard(true);
		Assert.assertEquals(true, mb.isIntegGraphicalCard());

		mb.setIntegLANcard(true);
		Assert.assertEquals(true, mb.isIntegLANcard());

		mb.setGraphicsCardInstalled(false);
		Assert.assertEquals(false, mb.isGraphicsCardInstalled());


		mb.setCPUPortsAvailable(3);
		Assert.assertEquals(3, mb.getCPUPortsAvailable());
		mb.makeOneCPUportTaken();
		Assert.assertEquals(2, mb.getCPUPortsAvailable());
		mb.makeOneCPUportAvailable();
		Assert.assertEquals(3, mb.getCPUPortsAvailable());



		mb.setPCIPortsAvailable(3);
		Assert.assertEquals(3, mb.getPCIPortsAvailable());
		mb.makeOnePCIportTaken();
		Assert.assertEquals(2, mb.getPCIPortsAvailable());
		mb.makeOnePCIportAvailable();
		Assert.assertEquals(3, mb.getPCIPortsAvailable());


		mb.setRAMPortsAvailable(3);
		Assert.assertEquals(3, mb.getRAMPortsAvailable());
		mb.makeOneRAMportTaken();
		Assert.assertEquals(2, mb.getRAMPortsAvailable());
		mb.makeOneRAMportAvailable();
		Assert.assertEquals(3, mb.getRAMPortsAvailable());


		mb.setUSBPortsAvailable(3);
		Assert.assertEquals(3, mb.getUSBPortsAvailable());
		mb.makeOneUSBportTaken();
		Assert.assertEquals(2, mb.getUSBPortsAvailable());
		mb.makeOneUSBportAvailable();
		Assert.assertEquals(3, mb.getUSBPortsAvailable());


		mb.setDUCPortsAvailable(3);
		Assert.assertEquals(3, mb.getDUCPortsAvailable());
		mb.makeOneDUCportTaken();
		Assert.assertEquals(2, mb.getDUCPortsAvailable());
		mb.makeOneDUCportAvailable();
		Assert.assertEquals(3, mb.getDUCPortsAvailable());


		mb.setIntegLANPortsAvailable(3);
		Assert.assertEquals(3, mb.getIntegLANPortsAvailable());
		mb.makeOneIntLANportTaken();
		Assert.assertEquals(2, mb.getIntegLANPortsAvailable());
		mb.makeOneIntLANportAvailable();
		Assert.assertEquals(3, mb.getIntegLANPortsAvailable());


		mb.setCoaxPortsAvailable(3);
		Assert.assertEquals(3, mb.getCoaxPortsAvailable());
		mb.makeOneCoaxPortTaken();
		Assert.assertEquals(2, mb.getCoaxPortsAvailable());
		mb.makeOneCoaxPortAvailable();
		Assert.assertEquals(3, mb.getCoaxPortsAvailable());
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
