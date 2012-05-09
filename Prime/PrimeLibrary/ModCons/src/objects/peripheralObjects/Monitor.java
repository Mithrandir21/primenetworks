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
package objects.peripheralObjects;


import java.io.Serializable;

import objects.ExternalHardware;


/**
 * This class represents a monitor. This device can be connected to any device that supports the conncetion type of this
 * device. It contains information on what kind of capability the monitor has and what kind of connection interface it
 * has.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Monitor extends ExternalHardware implements Serializable
{

	// Monitor size. inches
	private int inches;

	// Type of monitor. CRT or LCD.
	private String monitorType;

	// Boolean to check whether or not a monitor is a laptop monitor.
	private boolean isLaptopMonitor = false;


	// Constructor of the class
	/**
	 * Description NEEDED!
	 * 
	 * @param Name
	 *            The name of the monitor.
	 * @param Desc
	 *            The description of the monitor.
	 * @param MonitorInches
	 *            Monitor size. In inches.
	 * @param type
	 *            Type of monitor. CRT or LCD.
	 * @param MonitorConnectionInterfaces
	 *            Monitor connection interface. A array of strings.
	 */
	public Monitor(String Name, String Desc, int MonitorInches, String type,
			String[] MonitorConnectionInterfaces)
	{
		super(Name, Desc, MonitorConnectionInterfaces);

		this.inches = MonitorInches;
		this.monitorType = type;
	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get monitor size.
	 */
	public int getMonitorSize()
	{
		return this.inches;
	}


	/**
	 * Gets the type of monitor. LCD or CRT.
	 */
	public String getMonitorType()
	{
		return this.monitorType;
	}



	/**
	 * Get boolean on whether or not the monitor is a laptop monitor.
	 */
	public boolean isLaptopMonitor()
	{
		return this.isLaptopMonitor;
	}



	// SET METHODES

	/**
	 * Sets monitor size.
	 */
	public void setMonitorSize(int MonitorInches)
	{
		this.inches = MonitorInches;
	}


	/**
	 * Sets the type of monitor. CRT or LCD.
	 */
	public void setMonitorType(String Type)
	{
		this.monitorType = Type;
	}


	/**
	 * Sets info on whether or not the monitor is a laptop monitor.
	 */
	public void setLaptopMonitor(boolean info)
	{
		this.isLaptopMonitor = info;
	}



}