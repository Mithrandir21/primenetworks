/**
 * 
 */
package objects.serverObjects;


import objects.Object;
import objects.Servers;


/**
 * This class will represent a Network Attached Storage server.
 * 
 * @author Bahram Malaekeh
 */
public class NASServer extends Servers
{
	/**
	 * A constructor for this NAS server class.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 */
	public NASServer(String Name, String Desc)
	{
		super(Name, Desc);
	}


	/**
	 * A constructor for this NAS server class.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param SupConInt
	 *            The supported connection interfaces of the server.
	 */
	public NASServer(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}

	/**
	 * A constructor for this NAS server class.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param ServerComponents
	 *            The initial components of the server.
	 */
	public NASServer(String Name, String Desc, Object[] ServerComponents)
	{
		super(Name, Desc, ServerComponents);
	}


	/**
	 * A constructor for this NAS server class.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param SupConInt
	 *            The supported connection interfaces of the server.
	 * @param ServerComponents
	 *            The initial components of the server.
	 */
	public NASServer(String Name, String Desc, String[] SupConInt,
			Object[] ServerComponents)
	{
		super(Name, Desc, SupConInt, ServerComponents);
	}
}
