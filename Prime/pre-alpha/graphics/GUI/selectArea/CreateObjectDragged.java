/**
 * 
 */
package graphics.GUI.selectArea;

import graphics.PrimeMain1;
import clients.*;
import servers.*;
import widgetManipulation.WidgetObject;
import infrastructure.*;
import hardware.*;
import graphics.WidgetIcon;
import objects.Object;
import peripheral.*;

/**
 * @author Bam
 *
 */
public class CreateObjectDragged 
{
	public Object CreateObject(WidgetIcon iconObject)
	{
		Object newObject = null;
		String objectType = iconObject.getClassType().getName();
		
		
		if(objectType.equals("clients.Desktop"))
		{
			newObject = createDefaultDesktop(iconObject);
		}
		else if(objectType.equals("clients.Laptop"))
		{
			newObject = createDefaultLaptop(iconObject);
		}
		else if(objectType.equals("servers.HTTPServer"))
		{
			newObject = createDefaultHTTPServer(iconObject);
		}
		else if(objectType.equals("servers.BackupServer"))
		{
			newObject = createDefaultBackupServer(iconObject);
		}
		else if(objectType.equals("servers.MailServer"))
		{
			newObject = createDefaultMailServer(iconObject);
		}
		else if(objectType.equals("servers.FirewallServer"))
		{
			newObject = createDefaultFirewallServer(iconObject);
		}
		else if(objectType.equals("servers.ProxyServer"))
		{
			newObject = createDefaultProxyServer(iconObject);
		}
		else if(objectType.equals("hardware.HDD"))
		{
			newObject = createDefaultHDD(iconObject);
		}
		else if(objectType.equals("infrastructure.Hub"))
		{
			newObject = createDefaultHub(iconObject);
		}
		else if(objectType.equals("infrastructure.Switch"))
		{
			newObject = createDefaultSwitch(iconObject);
		}
		else if(objectType.equals("infrastructure.Router"))
		{
			newObject = createDefaultRouter(iconObject);
		}
		
		
		PrimeMain1.numberOfWidgetsOnTheScene++;
		return newObject;
	}
	
	
	
	/**
	 * @param iconObject
	 * @return
	 */
	private Desktop createDefaultDesktop(WidgetIcon iconObject)
	{
		String objectName = "Desktop" + PrimeMain1.numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		
		return new Desktop(objectName, objectDesc);
	}
	
	
	
	/**
	 * @param iconObject
	 * @return
	 */
	private Laptop createDefaultLaptop(WidgetIcon iconObject)
	{
		String objectName = "Laptop" + PrimeMain1.numberOfWidgetsOnTheScene;
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
//		String objectName = "Desktop" + PrimeMain1.numberOfWidgetsOnTheScene;
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
	
	
	
	/**
	 * @param iconObject
	 * @return
	 */
	private HTTPServer createDefaultHTTPServer(WidgetIcon iconObject)
	{
		String objectName = "Desktop" + PrimeMain1.numberOfWidgetsOnTheScene;
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
	
	
	
	
	/**
	 * @param iconObject
	 * @return
	 */
	private BackupServer createDefaultBackupServer(WidgetIcon iconObject)
	{
		String objectName = "Desktop" + PrimeMain1.numberOfWidgetsOnTheScene;
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
	
	
	
	/**
	 * @param iconObject
	 * @return
	 */
	private MailServer createDefaultMailServer(WidgetIcon iconObject)
	{
		String objectName = "MailServer" + PrimeMain1.numberOfWidgetsOnTheScene;
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

	
	
	/**
	 * @param iconObject
	 * @return
	 */
	private FirewallServer createDefaultFirewallServer(WidgetIcon iconObject)
	{
		String objectName = "FirewallServer" + PrimeMain1.numberOfWidgetsOnTheScene;
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
	
	
	
	
	/**
	 * @param iconObject
	 * @return
	 */
	private ProxyServer createDefaultProxyServer(WidgetIcon iconObject)
	{
		String objectName = "ProxyServer" + PrimeMain1.numberOfWidgetsOnTheScene;
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
	
	
	
	
	/**
	 * @param iconObject
	 * @return
	 */
	private HDD createDefaultHDD(WidgetIcon iconObject)
	{
		String objectName = "HDD" + PrimeMain1.numberOfWidgetsOnTheScene;
		String objectDesc = iconObject.getDescription();
		
		String HHDtype = "IDE";
		int HDDsize = 80;
		
		if(objectDesc == "")
		{
			objectDesc = objectName;
		}
		
		
		return new HDD(objectName, objectDesc, HHDtype, HDDsize);
	}
	
	
	
	
	/**
	 * @param iconObject
	 * @return
	 */
	private Hub createDefaultHub(WidgetIcon iconObject)
	{
		String objectName = "Hub" + PrimeMain1.numberOfWidgetsOnTheScene;
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
	
	
	
	
	
	/**
	 * @param iconObject
	 * @return
	 */
	private Switch createDefaultSwitch(WidgetIcon iconObject)
	{
		String objectName = "Switch" + PrimeMain1.numberOfWidgetsOnTheScene;
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
	
	
	
	/**
	 * @param iconObject
	 * @return
	 */
	private Router createDefaultRouter(WidgetIcon iconObject)
	{
		String objectName = "Router" + PrimeMain1.numberOfWidgetsOnTheScene;
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
