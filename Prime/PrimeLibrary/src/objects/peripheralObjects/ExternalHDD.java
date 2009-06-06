package objects.peripheralObjects;


import java.io.Serializable;

import objects.ExternalHardware;


/**
 * This class represents a external harddrive. This device can be connected to any device that supports the conncetion
 * type of this device. It contains information on what kind of capability the harddrive has and what kind of connection
 * interface is has. <br>
 * <br>
 * <b>Notation</b>: The external harddrive will be refered to as "externalHDD" in the remainder of this document.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ExternalHDD extends ExternalHardware implements Serializable
{

	// The company that produces the HDD. Western Digital, Samsung and so on.
	private String producer;

	// The subtype of the HDD type, USB1, USB2, Firewire and so on.
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
	public ExternalHDD(String Name, String Desc, String HDDType, int HDDSize, String[] ConnectionInterface)
	{
		super(Name, Desc, ConnectionInterface);

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