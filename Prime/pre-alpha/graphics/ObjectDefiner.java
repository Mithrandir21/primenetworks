/**
 * 
 */
package graphics;


import objects.Object;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ObjectDefiner
{
	public static Object defineObjectClass(Object object)
	{
		
		if ( object instanceof clients.Desktop )
		{
			return (clients.Desktop) object; 
		}
		else if ( object instanceof clients.Laptop )
		{
			return (clients.Laptop) object; 
		}
		else if ( object instanceof servers.HTTPServer )
		{
			return (servers.HTTPServer) object; 
		}
		else if ( object instanceof servers.BackupServer )
		{
			return (servers.BackupServer) object; 
		}
		else if ( object instanceof servers.MailServer )
		{
			return (servers.MailServer) object; 
		}
		else if ( object instanceof servers.FirewallServer )
		{
			return (servers.FirewallServer) object; 
		}
		else if ( object instanceof servers.ProxyServer )
		{
			return (servers.ProxyServer) object; 
		}
		else if ( object instanceof hardware.HDD )
		{
			return (hardware.HDD) object; 
		}
		else if ( object instanceof peripheral.Scanner )
		{
			return (peripheral.Scanner) object; 
		}
		else if ( object instanceof infrastructure.Hub )
		{
			return (infrastructure.Hub) object; 
		}
		else if ( object instanceof infrastructure.Switch )
		{
			return (infrastructure.Switch) object; 
		}
		else if ( object instanceof infrastructure.Router )
		{
			return (infrastructure.Router) object; 
		}
		
		return null;
	}
}
