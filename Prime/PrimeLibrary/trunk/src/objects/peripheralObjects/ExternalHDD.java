/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package objects.peripheralObjects;


import java.io.Serializable;

import objects.ExternalHardware;
import objects.hardwareObjects.Motherboard;


/**
 * This class represents a external harddrive. This device can be connected to
 * any device that supports the conncetion
 * type of this device. It contains information on what kind of capability the
 * harddrive has and what kind of connection
 * interface is has. <br>
 * <br>
 * <b>Notation</b>: The external harddrive will be refered to as "externalHDD"
 * in the remainder of this document.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ExternalHDD extends ExternalHardware implements Serializable
{

	// The company that produces the HDD. Western Digital, Samsung and so on.
	private String producer;

	// The type of HDD. IDE,ATA,SATA,SCSI,USB and so on.
	private String port;

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
	public ExternalHDD(String Name, String Desc, String HDDType, int HDDSize,
			String[] ConnectionInterface)
	{
		super(Name, Desc, ConnectionInterface);

		this.size = HDDSize;
	}



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
	public ExternalHDD(String Name, String Desc, String HDDType, int HDDSize)
	{
		super(Name, Desc);

		this.size = HDDSize;
	}



	/**
	 * Constructor of a HDD hardware. (Specifically for use with Motherboard.)
	 * 
	 * @param Name
	 *            The name of the HDD.
	 * @param Desc
	 *            The description of the HDD.
	 * @param HDDSize
	 *            The HDD size in GB
	 * @param Motherboard
	 *            The "motherboard" of the device.
	 */
	public ExternalHDD(String Name, String Desc, int HDDSize,
			Motherboard objectMB)
	{
		super(Name, Desc, objectMB);

		this.size = HDDSize;
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get producer of the HDD.
	 */
	public String getProducer()
	{
		return this.producer;
	}


	/**
	 * Get the type of the HDD.
	 */
	public String getPort()
	{
		return this.port;
	}


	/**
	 * Get the subtype of the type of HDD.
	 */
	public String getSubtype()
	{
		return this.subtype;
	}


	/**
	 * Get the size of the HDD.
	 */
	public int getSize()
	{
		return this.size;
	}


	/**
	 * Get the transfer speed of the HDD.
	 */
	public int getSpeed()
	{
		return this.transferSpeed;
	}


	/**
	 * Get the RPM of the HDD.
	 */
	public int getRPM()
	{
		return this.rpm;
	}


	// SET METHODES

	/**
	 * Set method for producers of the HDD.
	 */
	public void setProducer(String HDDProducer)
	{
		this.producer = HDDProducer;
	}


	/**
	 * Set method for type of the HDD.
	 */
	public void setPort(String HDDport)
	{
		this.port = HDDport;
	}


	/**
	 * Set method for subtype of the HDD.
	 */
	public void setSubtype(String HDDSubtype)
	{
		this.subtype = HDDSubtype;
	}


	/**
	 * Set method for size of the HDD.
	 */
	public void setSize(int HDDSize)
	{
		this.size = HDDSize;
	}


	/**
	 * Set method for speed of the HDD.
	 */
	public void setSpeed(int HDDtransferSpeed)
	{
		this.transferSpeed = HDDtransferSpeed;
	}


	/**
	 * Set method for RPM of the HDD.
	 */
	public void setRPM(int HDDrpm)
	{
		this.rpm = HDDrpm;
	}
}