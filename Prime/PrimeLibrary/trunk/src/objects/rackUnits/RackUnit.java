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
package objects.rackUnits;


import java.io.Serializable;

import managment.ConnectionManagment;
import objects.Object;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class RackUnit extends Object implements Serializable
{
	// The Object that this rack "contains".
	private Object rackObject;


	// The number of "Shelfs" or "Us" a unit takes up.
	private int unitSize;


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param Name
	 * @param Desc
	 */
	public RackUnit(String Name, String Desc, Object obj)
	{
		super(Name, Desc);
		rackObject = obj;
	}


	// GETTERS


	/**
	 * Gets the {@link Object} that this rack "contains".
	 */
	public Object getRackObject()
	{
		return rackObject;
	}



	// SETTERS

	/**
	 * Sets the {@link Object} that this rack "contains".
	 */
	public void setRackObject(Object rackObject)
	{
		this.rackObject = rackObject;
	}



	/**
	 * Gets the size, shelfs space need, for the unit.
	 */
	public int getUnitSize()
	{
		return this.unitSize;
	}



	/**
	 * Sets the size, shelfs space need, for the unit.
	 */
	public void setUnitSize(int unitSize)
	{
		this.unitSize = unitSize;
	}



	/**
	 * This function will returns a boolean on whether or not the {@link Object}
	 * since this rack unit supports the connection type given.
	 */
	public boolean supportsConType(String conType)
	{
		return ConnectionManagment.checkDeviceConnectiontypeSupport(this,
				conType);
	}
}
