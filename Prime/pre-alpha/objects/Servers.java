package objects;
import java.io.Serializable;



/**
 * An abstract super class for all servers objects in the system, 
 * including {@link  clients.Desktop  MUST CHANGE} and {@link  clients.Laptop  MUST CHANGE}.
 * 
 * MUST ADD INFO!
 *
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public abstract class Servers extends Object implements Serializable
{

	
	
	/**
	 * Constructor for an abstract server superclass. 
	 * 
	 * MORE INFO NEEDED!
	 *
	 * @param Name The name of the server.
	 * @param Desc The description of the server.
	 */
	public Servers(String Name, String Desc)
	{
		super(Name,Desc);
	}
	
	/**
	 * Constructor for an abstract server superclass. 
	 * 
	 * MORE INFO NEEDED!
	 *
	 * @param Name The name of the server.
	 * @param Desc The description of the server.
	 */
	public Servers(String Name, String Desc, String[] SupConInt)
	{
		super(Name,Desc,SupConInt);
	}
}
