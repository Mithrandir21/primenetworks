package hardware;


import java.io.Serializable;

import objects.Hardware;


/**
 * This class represents a harddrive within a {@link objects.Servers server} or
 * {@link objects.Clients client} machine. It can be a server, a desktop or a
 * laptop. It contains information on what kind of capability the harddrive has
 * and what kind of system it can fit into. <br>
 * <br>
 * <b>Notation</b>: The harddrive will be refered to as "HDD" in the remainder
 * of this document.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class HDD extends Hardware implements Serializable
{

	// The company that produces the HDD. Western Digital, Samsung and so on.
	private String producer;

	// The type of HDD. IDE,ATA,SATA,SCSI,USB and so on.
	private String type;

	// The subtype of the HDD type, SATA-150, USB2, ATA-100 and so on.
	private String subtype;

	// The HDD size in GB
	private int size;

	// The speed of the HDD
	private int transferSpeed;

	// The RPM of the HDD
	private int rpm;



	/**
	 * Constructor of a HDD hardware
	 * 
	 * @param Name
	 *            The name of the HDD.
	 * @param Desc
	 *            The description of the HDD.
	 * @param HDDType
	 *            The type of HDD. IDE,ATA,SATA,SCSI,USB and so on.
	 * @param HDDSize
	 *            The HDD size in GB
	 */
	public HDD(String Name, String Desc, String HDDType, int HDDSize)
	{
		super(Name, Desc);

		type = HDDType;
		size = HDDSize;
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get producer of the HDD.
	 */
	public String getProducer()
	{
		return producer;
	}



	/**
	 * Get the type of the HDD.
	 */
	public String getType()
	{
		return type;
	}


	/**
	 * Get the subtype of the type of HDD.
	 */
	public String getSubtype()
	{
		return subtype;
	}


	/**
	 * Get the size of the HDD.
	 */
	public int getSize()
	{
		return size;
	}


	/**
	 * Get the transfer speed of the HDD.
	 */
	public int getSpeed()
	{
		return transferSpeed;
	}


	/**
	 * Get the RPM of the HDD.
	 */
	public int getRPM()
	{
		return rpm;
	}


	// SET METHODES

	/**
	 * Set method for producers of the HDD.
	 */
	public void setProducer(String HDDProducer)
	{
		producer = HDDProducer;
	}


	/**
	 * Set method for type of the HDD.
	 */
	public void setType(String HDDType)
	{
		type = HDDType;
	}


	/**
	 * Set method for subtype of the HDD.
	 */
	public void setSubtype(String HDDSubtype)
	{
		subtype = HDDSubtype;
	}


	/**
	 * Set method for size of the HDD.
	 */
	public void setSize(int HDDSize)
	{
		size = HDDSize;
	}


	/**
	 * Set method for speed of the HDD.
	 */
	public void setSpeed(int HDDtransferSpeed)
	{
		transferSpeed = HDDtransferSpeed;
	}


	/**
	 * Set method for RPM of the HDD.
	 */
	public void setRPM(int HDDrpm)
	{
		rpm = HDDrpm;
	}

}
