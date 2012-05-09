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
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class GenericDevice extends ExternalHardware implements Serializable
{
	/**
	 * The boolean on whether the object has been customized.
	 */
	private boolean customized;


	/**
	 * Constructor of the GenericDevice class.
	 * 
	 * @param Name
	 *            The name of the GenericDevice.
	 * @param Desc
	 *            The description of the GenericDevice.
	 * @param objectMB
	 *            The {@link Motherboard} of the object.
	 */
	public GenericDevice(String Name, String Desc, Motherboard objectMB)
	{
		super(Name, Desc, objectMB);
		customized = false;
	}


	/**
	 * @return the customized
	 */
	public boolean isCustomized()
	{
		return customized;
	}


	/**
	 * @param customized
	 *            the customized to set
	 */
	public void setCustomized(boolean customized)
	{
		this.customized = customized;
	}
}
