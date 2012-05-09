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
package objects;



import java.io.Serializable;

import objects.hardwareObjects.Motherboard;


/**
 * CLASSDesc - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class Infrastructure extends Object implements Serializable
{

	/**
	 * A constructor for any Infrastructure object.
	 * 
	 * @param Name
	 *            The name of the Object.
	 * @param Desc
	 *            The description of the object.
	 */
	public Infrastructure(String Name, String Desc)
	{
		super(Name, Desc);
	}


	/**
	 * A constructor for any Infrastructure object.
	 * 
	 * @param Name
	 *            The name of the Object.
	 * @param Desc
	 *            The description of the object.
	 * @param SupConInt
	 *            A String array of supported interfaces.
	 */
	public Infrastructure(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}


	/**
	 * A constructor for any Infrastructure object.
	 * 
	 * @param Name
	 *            The name of the Object.
	 * @param Desc
	 *            The description of the object.
	 * @param objectComponents
	 *            An Objects array that contains the objects internal
	 *            components.
	 */
	public Infrastructure(String Name, String Desc, Object[] objectComponents)
	{
		super(Name, Desc, objectComponents);
	}


	/**
	 * A constructor for any Infrastructure object.
	 * 
	 * @param Name
	 *            The name of the Object.
	 * @param Desc
	 *            The description of the object.
	 * @param SupConInt
	 *            A String array of supported interfaces.
	 * @param mb
	 *            The objects Motherboard.
	 */
	public Infrastructure(String Name, String Desc, String[] SupConInt,
			Motherboard mb)
	{
		super(Name, Desc, SupConInt, mb);
	}


	/**
	 * A constructor for any Infrastructure object.
	 * 
	 * @param Name
	 *            The name of the Object.
	 * @param Desc
	 *            The description of the object.
	 * @param SupConInt
	 *            A String array of supported interfaces.
	 * @param objectComponents
	 *            An Objects array that contains the objects internal
	 *            components.
	 */
	public Infrastructure(String Name, String Desc, String[] SupConInt,
			Object[] objectComponents)
	{
		super(Name, Desc, SupConInt, objectComponents);
	}
}
