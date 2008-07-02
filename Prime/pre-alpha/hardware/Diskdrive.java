package hardware;
import java.io.Serializable;
import objects.Hardware;


/**
 * This class represents a diskdrive within a {@link  objects.Servers  server} or 
 * {@link  objects.Clients  client} machine. 
 * It can be a server, a desktop or a laptop. It contains information on what kind 
 * of capability the diskdrive has and what kind of system it can fit into.
 * The diskdrive can be anything from a CDROM or DVD-Burner to a Blu-Ray-Burner. 
 *
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Diskdrive extends Hardware implements Serializable 
{
	
	// The company that produces the Diskdrive. Sony, Samsung and so on.
	private String producer;
	
	// The type of Diskdrive. CDROM,DVDROM,DVDburner,Blu-Ray,HD-DVD and so on.
	private String type;
	
	// The subtype of the Diskdrive type, DualLayer, Doublesided and so on.
	private String subtype;
	
	// The speed of the Diskdrive
	private int transferSpeed;

	
	
	/**
	 * Constructor of a Diskdrive hardware
	 *
	 * @param Name The name of the Diskdrive.
	 * @param Desc The description of the Diskdrive.
	 * @param DiskdriveType The type of diskdrive. Like CDROM, DVD-RW and so on.
	 */
	public Diskdrive(String Name, String Desc, String DiskdriveType)
	{
		super(Name,Desc);

		type = DiskdriveType;
	}
	
	
	// Get and Set methodes for retrieving all datafields.
	
	// GET METHODES
	/**
	 * Get producer of the Diskdrive.
	 * 
	 */
	public String getProducer()
	{
		return producer;
	}
	
	

	/**
	 * Get the type of the Diskdrive.
	 * 
	 */
	public String getType()
	{
		return type;
	}	
	
	
	/**
	 * Get the subtype of the type of Diskdrive.
	 * 

	 */
	public String getSubtype()
	{
		return subtype;
	}	
	
	
	/**
	 * Get the transfer speed of the Diskdrive.
	 */
	public int getSpeed()
	{
		return transferSpeed;
	}
	
	
	// SET METHODES
	/**
     * Set method for producers of the Diskdrive.
     *
     */
	public void setProducer(String DiskdriveProducer)
	{
		producer = DiskdriveProducer;
	}
	

	/**
	 * Set method for type of the Diskdrive.
	 * 
	 */
	public void setType(String DiskdriveType)
	{
		type = DiskdriveType;
	}
	

	/**
	 * Set method for subtype of the Diskdrive.
	 * 
	 */
	public void setSubtype(String DiskdriveSubtype)
	{
		subtype = DiskdriveSubtype;
	}
	
	
	/**
	 * Set method for speed of the Diskdrive.
	 * 
	 */
	public void setSpeed(int DiskDrivetransferSpeed)
	{
		transferSpeed = DiskDrivetransferSpeed;
	}
	
}
