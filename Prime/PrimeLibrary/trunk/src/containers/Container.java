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
package containers;


import java.io.Serializable;
import java.util.ArrayList;

import objects.Object;
import exceptions.ObjectDoesNotExistInContainer;
import exceptions.ObjectExistInContainer;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public abstract class Container implements Serializable
{
	// The arraylist that will contain objects
	ArrayList<Object> container = new ArrayList<Object>();


	/**
	 * Description
	 */
	public ArrayList<Object> getContainer()
	{
		return container;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @param container
	 *            the container to set
	 */
	public void setContainer(ArrayList<Object> container)
	{
		this.container = container;
	}



	/**
	 * Description
	 */
	public boolean addObject(Object obj) throws ObjectExistInContainer
	{
		// Check to see if the specific object already exist in the container
		if ( container.contains(obj) )
		{
			throw new ObjectExistInContainer("This object,"
					+ obj.getObjectName()
					+ ", already exist in this container.", obj);
		}

		// Returns true if added and false if not.
		return container.add(obj);
	}



	/**
	 * Description
	 */
	public boolean removeObject(Object obj)
			throws ObjectDoesNotExistInContainer
	{
		// Checks to see if object really exist in the container
		if ( !container.contains(obj) )
		{
			throw new ObjectDoesNotExistInContainer("This object,"
					+ obj.getObjectName()
					+ ", does not exist in this container.", obj);
		}


		return container.remove(obj);
	}



	/**
	 * Description
	 */
	public boolean containsObject(Object obj)
	{

		return container.contains(obj);
	}
}
