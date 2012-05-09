/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package objects.hardwareObjects;


import java.io.Serializable;

import objects.Hardware;


/**
 * This class represents a RAM within a {@link objects.Servers server} or {@link objects.Clients client} machine. It can
 * be a server, a desktop or a laptop. It contains information on what kind of capability the RAM has and what kind of
 * system it can fit into.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Ram extends Hardware implements Serializable
{

	// The company that produces the ram. Kingston, and so on.
	private String producer;

	// The type of ram. DDR,DDR2,DDR3 and so on.
	private String port;

	// The subtype of the ram type, DIMM and so on.
	private String subtype;

	// The ram size in MB
	private int size;

	// The speed of the ram
	private int speed;



	/**
	 * Constructor of a ram hardware.
	 * 
	 * @param Name
	 *            The name of the ram.
	 * @param Desc
	 *            The description of the ram.
	 * @param ramType
	 *            The type of ram. DDR,DDR2,DDR3 and so on.
	 * @param ramSize
	 *            The ram size in MB.
	 */
	public Ram(String Name, String Desc, String ramType, int ramSize)
	{
		super(Name, Desc);

		this.port = ramType;
		this.size = ramSize;
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get producer of the ram.
	 */
	public String getProducer()
	{
		return this.producer;
	}


	/**
	 * Get the type of the ram.
	 */
	public String getPort()
	{
		return this.port;
	}


	/**
	 * Get the subtype of the type of ram.
	 */
	public String getSubtype()
	{
		return this.subtype;
	}


	/**
	 * Get the size of the ram.
	 */
	public int getSize()
	{
		return this.size;
	}


	/**
	 * Get the speed of the ram.
	 */
	public int getSpeed()
	{
		return this.speed;
	}


	// SET METHODES

	/**
	 * Set method for producers of the ram.
	 */
	public void setProducer(String ramProducer)
	{
		this.producer = ramProducer;
	}


	/**
	 * Set method for type of the ram.
	 */
	public void setPort(String ramPort)
	{
		this.port = ramPort;
	}


	/**
	 * Set method for subtype of the ram.
	 */
	public void setSubtype(String ramSubtype)
	{
		this.subtype = ramSubtype;
	}


	/**
	 * Set method for size of the ram.
	 */
	public void setSize(int ramSize)
	{
		this.size = ramSize;
	}


	/**
	 * Set method for speed of the ram
	 */
	public void setSpeed(int ramSpeed)
	{
		this.speed = ramSpeed;
	}

}
