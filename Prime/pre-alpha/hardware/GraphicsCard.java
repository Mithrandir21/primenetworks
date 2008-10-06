package hardware;


import java.io.Serializable;

import objects.Hardware;


/**
 * This class represents a graphical card within a {@link objects.Servers
 * server} or {@link objects.Clients client} machine. It can be a server, a
 * desktop or a laptop. It contains information on what kind of capability the
 * graphical card has and what kind of system it can fit into. <br>
 * <br>
 * <b>Notation</b>: The graphical card will be referred to as "GC" in the
 * remainder of this document.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class GraphicsCard extends Hardware implements Serializable
{

	// The company that produces the GC. "Point of view", XFX and so on.
	private String producer;

	// The type of GC. AGP,PCI, PCI-Express and so on.
	private String type;

	// The type of output of a GC. VGA, DVI and so on.
	private String outputInterface;

	// The size of GC ram in MB
	private int size;

	// The speed of the GC
	private int Speed;

	// The maximum number of monitors output ports on the GC
	private int maxMonitors;

	// Whether or not the GC is integrated on the MB
	private boolean isIntegrated;



	/**
	 * Constructor of a graphics card hardware.
	 * 
	 * @param Name
	 *            The name of the GC.
	 * @param Desc
	 *            The description of the GC.
	 * @param GCtype
	 *            The type of GC. AGP,PCI, PCI-Express and so on.
	 * @param GCram
	 *            The size of GC ram in MB.
	 * @param GCoutputInterface
	 *            The type of output of a GC. VGA, DVI and so on.
	 * @param GCisIntegrated
	 *            Whether or not the GC is integrated on the MB.
	 */
	public GraphicsCard(String Name, String Desc, String GCtype, int GCram,
			String GCoutputInterface, boolean GCisIntegrated)
	{
		super(Name, Desc);

		type = GCtype;
		size = GCram;
		outputInterface = GCoutputInterface;
		isIntegrated = GCisIntegrated;
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get producer of the GC.
	 * 
	 */
	public String getProducer()
	{
		return producer;
	}



	/**
	 * Get the type of the GC.
	 * 
	 */
	public String getType()
	{
		return type;
	}


	/**
	 * Get the output interface of the GC
	 * 
	 */
	public String getOutputInterface()
	{
		return outputInterface;
	}


	/**
	 * Get the size of the GC.
	 * 
	 */
	public int getSize()
	{
		return size;
	}


	/**
	 * Get the speed of the GC.
	 * 
	 */
	public int getSpeed()
	{
		return Speed;
	}


	/**
	 * Get the max number of monitors output ports on the GC.
	 * 
	 */
	public int getMaxMonitors()
	{
		return maxMonitors;
	}


	/**
	 * Get the information on whether or not the GC is integrated on the
	 * motherboard or not.
	 * 
	 */
	public boolean getIsIntegrated()
	{
		return isIntegrated;
	}


	// SET METHODES

	/**
	 * Set method for producers of the GC.
	 * 
	 */
	public void setProducer(String GCproducer)
	{
		producer = GCproducer;
	}


	/**
	 * Set method for type of the GC.
	 * 
	 */
	public void setType(String GCtype)
	{
		type = GCtype;
	}


	/**
	 * Set method for subtype of the GC
	 * 
	 */
	public void setSubtype(String GCoutputInterface)
	{
		outputInterface = GCoutputInterface;
	}


	/**
	 * Set method for size of the GC.
	 * 
	 */
	public void setSize(int GCsize)
	{
		size = GCsize;
	}


	/**
	 * Set method for speed of the GC.
	 * 
	 */
	public void setSpeed(int GCspeed)
	{
		Speed = GCspeed;
	}


	/**
	 * Set method for RPM of the GC.
	 * 
	 */
	public void setMaxMonitors(int GCmaxMonitors)
	{
		maxMonitors = GCmaxMonitors;
	}


	/**
	 * Set method for whether or not the GC is integrated on the motherboard.
	 * 
	 */
	public void setIsIntegrated(boolean GCisIntegrated)
	{
		isIntegrated = GCisIntegrated;
	}

}