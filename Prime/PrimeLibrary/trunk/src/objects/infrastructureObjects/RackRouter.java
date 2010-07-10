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
package objects.infrastructureObjects;



import java.io.Serializable;

import objects.hardwareObjects.Motherboard;


/**
 * A representation of a router that fits into a {@link objects.infrastructureObjects.Rack 19"-rack}. This class will
 * represent the exact same information that the {@link objects.infrastructureObjects.Router Router} class represents,
 * but with some additional information regarding the unit, like size.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class RackRouter extends Router implements Serializable
{
	// The number of "Shelfs" or "Us" a unit takes up.
	private int unitSize;


	/**
	 * Constructor for the RackRouter class.
	 * 
	 * @param Name
	 *            The name of the RackRouter.
	 * @param Desc
	 *            The description of the RackRouter.
	 */
	public RackRouter(String Name, String Desc, String[] SupConInt,
			Motherboard objectMB, int outPorts, int inPorts,
			String[] DuplexSupport)
	{
		super(Name, Desc, SupConInt, objectMB, outPorts, inPorts, DuplexSupport);
	}




	/**
	 * Gets the size, shelfs space need, for the unit.
	 * 
	 * @return the unitSize
	 */
	public int getUnitSize()
	{

		return unitSize;
	}




	/**
	 * Sets the size, shelfs space need, for the unit.
	 * 
	 * @param unitSize
	 *            the unitSize to set
	 */
	public void setUnitSize(int unitSize)
	{

		this.unitSize = unitSize;
	}
}
