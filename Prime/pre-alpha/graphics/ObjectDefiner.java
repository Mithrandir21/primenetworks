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
			return object;
		}
		else if ( object instanceof clients.Laptop )
		{
			return object;
		}
		else if ( object instanceof servers.HTTPServer )
		{
			return object;
		}
		else if ( object instanceof servers.BackupServer )
		{
			return object;
		}
		else if ( object instanceof servers.MailServer )
		{
			return object;
		}
		else if ( object instanceof servers.FirewallServer )
		{
			return object;
		}
		else if ( object instanceof servers.ProxyServer )
		{
			return object;
		}
		else if ( object instanceof hardware.HDD )
		{
			return object;
		}
		else if ( object instanceof peripheral.Scanner )
		{
			return object;
		}
		else if ( object instanceof infrastructure.Hub )
		{
			return object;
		}
		else if ( object instanceof infrastructure.Switch )
		{
			return object;
		}
		else if ( object instanceof infrastructure.Router )
		{
			return object;
		}

		return null;
	}
}
