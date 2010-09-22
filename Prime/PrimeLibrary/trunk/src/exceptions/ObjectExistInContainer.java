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
package exceptions;


import objects.Object;


;

/**
 * This extension of the {@link Exception Exception} class is created and thrown when an object that is searched for is
 * found in a specific container(when it should not be found in that container).
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ObjectExistInContainer extends Exception
{
	// The object that was attempted added to a container
	Object object;


	/**
	 * Constructor for this exception including the {@link Object} found.
	 * 
	 * @param message
	 * @param FoundObject
	 */
	public ObjectExistInContainer(String message, Object FoundObject)
	{
		super(message);

		this.object = FoundObject;
	}



	/**
	 * Retrieves the object that was found.
	 */
	public Object getObject()
	{

		return this.object;
	}
}
