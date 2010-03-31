package objects.clientObjects;


import objects.Clients;
import objects.Object;




/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ThinClient extends Clients
{

	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the thin client rating to "0"(since the
	 * rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the thin client.
	 * @param Desc
	 *            The description of the thin client.
	 */
	public ThinClient(String Name, String Desc)
	{
		super(Name, Desc);
	}



	/**
	 * Constructor of a thin client computer.<br>
	 * This constructor also sets the number of components in the system and the thin client rating to "0"(since the
	 * rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the thin client.
	 * @param Desc
	 *            The description of the thin client.
	 * @param SupConInt
	 *            The initial components an instance of a thin client has.
	 */
	public ThinClient(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}



	/**
	 * Constructor of a thin client computer.<br>
	 * This constructor also sets the number of components in the system and the thin client rating to "0"(since the
	 * rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the thin client.
	 * @param Desc
	 *            The description of the thin client.
	 * @param components
	 *            components The initial components an instance of a thin client has.
	 */
	public ThinClient(String Name, String Desc, Object[] components)
	{
		super(Name, Desc, components);
	}


	/**
	 * Constructor of a thin client computer.<br>
	 * This constructor also sets the number of components in the system and the thin client rating to "0"(since the
	 * rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the thin client.
	 * @param Desc
	 *            The description of the thin client.
	 * @param SupConInt
	 *            An array of strings that describes the supported connection interfaces.
	 * @param components
	 *            components The initial components an instance of a thin client has.
	 */
	public ThinClient(String Name, String Desc, String[] SupConInt,
			Object[] components)
	{
		super(Name, Desc, SupConInt, components);
	}
}
