/**
 * 
 */
package graphics.GUI.selectArea;

import graphics.WidgetIcon;
import hardware.HDD;
import infrastructure.Hub;
import infrastructure.Router;
import infrastructure.Switch;
import objects.Object;
import peripheral.Scanner;
import servers.BackupServer;
import servers.FirewallServer;
import servers.HTTPServer;
import servers.MailServer;
import servers.ProxyServer;
import clients.Desktop;
import clients.Laptop;


/**
 * A class that contains only one public method that
 * returns an object based on the given {@link graphics.WidgetIcon WidgetIcon}.
 *
 * @author Bahram Malaekeh
 */
public class CreateObjectDragged 
{
	
	/**
	 * Creates and returns an object based on the 
	 * {@link graphics.WidgetIcon WidgetIcon} classType.
	 * 
	 * The object created is a standard object with very
	 * little extra information. 
	 * 
	 * @param iconObject
	 *            The {@link graphics.WidgetIcon WidgetIcon} that contains 
	 *            the calssTypethats is the basis for the creation 
	 *            of the return object.
	 * @return Object
	 * 			  The newly created standard object.
	 */
	public Object CreateObject(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		Object newObject = null;
		String objectType = iconObject.getClassType().getName();
		
		
		if(objectType.equals("clients.Desktop"))
		{
			newObject = createDefaultDesktop(iconObject, numberOfWidgetsOnTheScene);
		}
		else if(objectType.equals("clients.Laptop"))
		{
			newObject = createDefaultLaptop(iconObject, numberOfWidgetsOnTheScene);
		}
		else if(objectType.equals("servers.HTTPServer"))
		{
			newObject = createDefaultHTTPServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if(objectType.equals("servers.BackupServer"))
		{
			newObject = createDefaultBackupServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if(objectType.equals("servers.MailServer"))
		{
			newObject = createDefaultMailServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if(objectType.equals("servers.FirewallServer"))
		{
			newObject = createDefaultFirewallServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if(objectType.equals("servers.ProxyServer"))
		{
			newObject = createDefaultProxyServer(iconObject, numberOfWidgetsOnTheScene);
		}
		else if(objectType.equals("hardware.HDD"))
		{
			newObject = createDefaultHDD(iconObject, numberOfWidgetsOnTheScene);
		}
		else if(objectType.equals("peripheral.Scanner"))
		{
			newObject = createDefaultScanner(iconObject, numberOfWidgetsOnTheScene);
		}
		else if(objectType.equals("infrastructure.Hub"))
		{
			newObject = createDefaultHub(iconObject, numberOfWidgetsOnTheScene);
		}
		else if(objectType.equals("infrastructure.Switch"))
		{
			newObject = createDefaultSwitch(iconObject, numberOfWidgetsOnTheScene);
		}
		else if(objectType.equals("infrastructure.Router"))
		{
			newObject = createDefaultRouter(iconObject, numberOfWidgetsOnTheScene);
		}
		
		
		return newObject;
	}
	
	

	public Desktop createDefaultDesktop(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Desktop" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		
		return new Desktop(objectName, objectDesc);
	}
	
	

	public Laptop createDefaultLaptop(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Laptop" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		
		return new Laptop(objectName, objectDesc);
	}


	
//	/**
//	 * @param iconObject
//	 * @return
//	 */
//	private ThinClient createDefaultThinClient(WidgetIcon iconObject)
//	{
//		String objectName = "Desktop" + numberOfWidgetsOnTheScene;
//		String objectDesc = iconObject.getDescription();
//		
//		if(objectDesc == "")
//		{
//			objectDesc = objectName;
//		}
//		
//		
//		return new ThinClient(objectName, objectDesc);
//	}
	
	

	public HTTPServer createDefaultHTTPServer(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "HTTP Server" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		String ObjectSWname = "Apache";
		String ObjectSWdesc = "Standard Webserver";
		String ObjectSWversion = "2.2";
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		
		return new HTTPServer(objectName, objectDesc, ObjectSWname, ObjectSWdesc, ObjectSWversion);
	}
	
	
	

	public BackupServer createDefaultBackupServer(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Backup Server" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		String ObjectSWname = "Backup";
		String ObjectSWdesc = "Standard backup server";
		String ObjectSWversion = "1";
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		return new BackupServer(objectName, objectDesc, ObjectSWname, ObjectSWdesc, ObjectSWversion);
	}
	
	

	public MailServer createDefaultMailServer(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Mail Server" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		String ObjectSWname = "Mail";
		String ObjectSWdesc = "Standard mail server";
		String ObjectSWversion = "1";
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		
		return new MailServer(objectName, objectDesc, ObjectSWname, ObjectSWdesc, ObjectSWversion);
	}

	
	
	public FirewallServer createDefaultFirewallServer(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Firewall Server" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		String ObjectSWname = "Firewall";
		String ObjectSWdesc = "Standard firewall server";
		String ObjectSWversion = "1";
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		
		return new FirewallServer(objectName, objectDesc, ObjectSWname, ObjectSWdesc, ObjectSWversion);
	}
	
	
	
	public ProxyServer createDefaultProxyServer(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Proxy Server" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		String ObjectSWname = "ProxyServer";
		String ObjectSWdesc = "Standard proxy server";
		String ObjectSWversion = "1";
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		
		return new ProxyServer(objectName, objectDesc, ObjectSWname, ObjectSWdesc, ObjectSWversion);
	}
	
	
	
	public HDD createDefaultHDD(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "HDD" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		String HHDtype = "IDE";
		int HDDsize = 80;
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		
		return new HDD(objectName, objectDesc, HHDtype, HDDsize);
	}
	
	
	
	public Scanner createDefaultScanner(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "HDD" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		String Sresolution = "1280x1020";
		String SconnectionInterfaces[] = { "USB" };
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		
		return new Scanner(objectName, objectDesc, Sresolution, SconnectionInterfaces);
	}
	
	
	
	
	public Hub createDefaultHub(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Hub" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		String[] SupConInt = { "RJ-45" };
		int outPorts = 16;
		int inPorts = 16;		
		String[] DuplexSupport = { "Full Duplex" };
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		
		return new Hub(objectName, objectDesc, SupConInt, outPorts, inPorts, DuplexSupport);
	}
	
	
	
	
	public Switch createDefaultSwitch(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Switch" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		String[] SupConInt = { "RJ-45" };
		int outPorts = 16;
		int inPorts = 16;		
		String[] DuplexSupport = { "Full Duplex" };
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		
		return new Switch(objectName, objectDesc, SupConInt, outPorts, inPorts, DuplexSupport);
	}
	
	
	
	public Router createDefaultRouter(WidgetIcon iconObject, int numberOfWidgetsOnTheScene)
	{
		String objectName = "Router" + numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		String[] SupConInt = { "RJ-45" };
		int outPorts = 16;
		int inPorts = 16;		
		String[] DuplexSupport = { "Full Duplex" };
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		
		return new Router(objectName, objectDesc, SupConInt, outPorts, inPorts, DuplexSupport);
	}
}
