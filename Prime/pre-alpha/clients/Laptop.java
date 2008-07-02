package clients;


import java.io.Serializable;

import exceptions.ObjectNotFoundException;
import exceptions.ObjectNotFoundInArrayException;

import managment.ComponentsManagment;
import objects.*;
import objects.Object;


/**
 * This class represents a laptop machine.<br>
 * An instance of this object will contain an array of pointers to the different
 * components of the laptop system. This will represent the objects that make up
 * the actual laptop, like a mouse, a monitor, a HDD, a motherboard and so on.<br>
 * Each instance of this object will also contain an array of pointers to
 * devices connected to the system. These will represente the network around the
 * system, like switches, servers, firewalls, printer and so on. <br>
 * <br>
 * TODO - Make laptop rating system.<br>
 * A system that rates a laptop machine depending on the type of components it
 * has.(Components must then also have a way of rating themselfs.) <br>
 * <br>
 * TODO - Make node jumps checking system.<br>
 * A system that checks the number of "jumps", nodes, that stand between its
 * self and the internet.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Laptop extends Clients implements Serializable
{

	/**
	 * Constructor of a laptop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * laptop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the laptop.
	 * @param Desc
	 *            The description of the laptop.
	 */
	public Laptop(String Name, String Desc)
	{
		super(Name, Desc);
	}
	
	
	
	/**
	 * Constructor of a laptop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * laptop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the laptop.
	 * @param Desc
	 *            The description of the laptop.
	 */
	public Laptop(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}
	
	
	
	
	/**
	 * Constructor of a laptop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * laptop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the laptop.
	 * @param Desc
	 *            The description of the laptop.
	 * @param DesktopComponents
	 *            The initial components an instance of a laptop has.
	 */
	public Laptop(String Name, String Desc, Object[] DesktopComponents)
	{
		super(Name, Desc, DesktopComponents);
	}


	/**
	 * Constructor of a laptop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * laptop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the laptop.
	 * @param Desc
	 *            The description of the laptop.
	 * @param SupConInt
	 * 			  An array of strings that describes the supported connection
	 * 			  interfaces.
	 * @param DesktopComponents
	 *            The initial components an instance of a laptop has.
	 */
	public Laptop(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents)
	{
		super(Name, Desc, SupConInt, DesktopComponents);
	}
}
