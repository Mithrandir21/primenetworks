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
 * This class represents a mouse. This device can be connected to any device that supports the conncetion type of this
 * device. It contains information on what kind of capability the mouse has and what kind of connection interface it
 * has.
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public class Mouse extends ExternalHardware implements Serializable
{

	// Boolean to check whether or not a mouse is a laptop mouse
	private boolean isLaptopMouse;


	// Constructor of the class
	/**
	 * Constructor of the mouse class.
	 * 
	 * @param Name
	 *            The name of the mouse.
	 * @param Desc
	 *            The description of the mouse.
	 * @param MouseConnectionInterface
	 *            The connection interface supported by a mouse.
	 */
	public Mouse(String Name, String Desc, String[] MouseConnectionInterface)
	{
		super(Name, Desc, MouseConnectionInterface);

	}


	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Get info on whether or not a mouse is a laptop mouse.
	 */
	public boolean isLaptopMouse()
	{
		return isLaptopMouse;
	}


	// SET METHODES

	/**
	 * Sets info on whether or not a mouse is a laptop mouse.
	 */
	public void setIsLaptopMouse(boolean info)
	{
		isLaptopMouse = info;
	}
}