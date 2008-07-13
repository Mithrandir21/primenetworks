package clients;


import java.io.Serializable;

import objects.Clients;
import objects.Object;


/**
 * This class represents a desktop machine. This can be any machine that has the
 * necessary equipment to run by its self without the need for add from any
 * other objects. <br>
 * An instance of this object will contain an array of pointers to the different
 * components of the desktop system. This will represent the objects that make
 * up the actual desktop, like a mouse, a monitor, a HDD, a motherboard and so
 * on.<br>
 * Each instance of this object will also contain an array of pointers to
 * devices connected to the system. These will reperesente the network around the
 * system, like switches, servers, firewalls, printer and so on. <br>
 * <br>
 * TODO - Make desktop rating system.<br>
 * A system that rates a desktop machine depending on the type of components it
 * has.(Components must then also have a way of rating themselfs.) <br>
 * <br>
 * TODO - Make node jumps checking system.<br>
 * A system that checks the number of "jumps", nodes, that stand between its
 * self and the internet.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class Desktop extends Clients implements Serializable
{
	
	
	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 */
	public Desktop(String Name, String Desc)
	{
		super(Name, Desc);
	}
	
	
	
	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 * @param SupConInt
	 *            The initial components an instance of a desktop has.
	 */
	public Desktop(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}



	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 * @param DesktopComponents
	 *            The initial components an instance of a desktop has.
	 */
	public Desktop(String Name, String Desc, Object[] DesktopComponents)
	{
		super(Name, Desc, DesktopComponents);
	}


	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 * @param SupConInt
	 * 			  An array of strings that describes the supported connection
	 * 			  interfaces.
	 * @param DesktopComponents
	 *            The initial components an instance of a desktop has.
	 */
	public Desktop(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents)
	{
		super(Name, Desc, SupConInt, DesktopComponents);
	}
}