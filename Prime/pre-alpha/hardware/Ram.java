package hardware;
import java.io.Serializable;
import objects.Hardware;

/**
 * This class represents a RAM within a {@link  objects.Servers  server} or 
 * {@link  objects.Clients  client} machine. 
 * It can be a server, a desktop or a laptop. It contains information on what kind 
 * of capability the RAM has and what kind of system it can fit into.
 *
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Ram extends Hardware implements Serializable 
{

	// The company that produces the ram. Kingston, and so on.
	private String producer;
	
	// The type of ram. DDR,DDR2,DDR3 and so on.
	private String type;
	
	// The subtype of the ram type, DIMM and so on.
	private String subtype;
	
	// The ram size in MB
	private int size;
	
	// The speed of the ram
	private int speed;
	
	

	/**
	 * Constructor of a ram hardware.
	 *
	 * @param Name The name of the ram.
	 * @param Desc The description of the ram.
	 * @param ramType The type of ram. DDR,DDR2,DDR3 and so on.
	 * @param ramSize The ram size in MB.
	 */
	public Ram(String Name, String Desc, String ramType,int ramSize)
	{
		super(Name,Desc);

		type = ramType;
		size = ramSize;
	}
	
	
	// Get and Set methodes for retrieving all datafields.
	
	// GET METHODES

	/**
	 * Get producer of the ram.
	 * 
	 */
	public String getProducer()
	{
		return producer;
	}
	
	
	/**
	 * Get the type of the ram.
	 * 
	 */
	public String getType()
	{
		return type;
	}	
	

	/**
	 * Get the subtype of the type of ram.
	 * 
	 */
	public String getSubtype()
	{
		return subtype;
	}	
	

	/**
	 * Get the size of the ram.
	 * 
	 */
	public int getSize()
	{
		return size;
	}	
	

	/**
	 * Get the speed of the ram.
	 * 
	 */
	public int getSpeed()
	{
		return speed;
	}
	
	
	// SET METHODES

	/**
	 * Set method for producers of the ram.
	 * 
	 */
	public void setProducer(String ramProducer)
	{
		producer = ramProducer;
	}
	

	/**
	 * Set method for type of the ram.
	 * 
	 */
	public void setType(String ramType)
	{
		type = ramType;
	}
	

	/**
	 * Set method for subtype of the ram.
	 * 
	 */
	public void setSubtype(String ramSubtype)
	{
		subtype = ramSubtype;
	}
	

	/**
	 * Set method for size of the ram.
	 * 
	 */
	public void setSize(int ramSize)
	{
		size = ramSize;
	}
	

	/**
	 * Set method for speed of the ram
	 * 
	 */
	public void setSpeed(int ramSpeed)
	{
		speed = ramSpeed;
	}
	
}
