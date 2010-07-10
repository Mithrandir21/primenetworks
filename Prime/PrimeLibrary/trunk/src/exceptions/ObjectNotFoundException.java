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


/**
 * This is exception class extends the super class, {@link java.lang.Exception Exception} class. It is an exception made
 * to handle situations that occur when a object class, such as a {@link objects.hardwareObjects.CPU CPU} or
 * {@link objects.hardwareObjects.HDD motherboard}, is searched for and not found. It also contains a datafield for the
 * object that was not found.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ObjectNotFoundException extends Exception
{
	// A container for the class of the object that was not found.
	Class<Object> objectType;


	/**
	 * Constructs a new exception with the specified detail message, along with the class of object that is not found.
	 * It stores the class of the object that was not found, which can be accessed by the {@link #getObjectClass()
	 * getObjectClass()} function.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
	 * @param type
	 *            The class of the object that was not found within an array
	 */
	public ObjectNotFoundException(String message, Class<Object> type)
	{
		super(message);

		objectType = type;
	}



	/**
	 * Retrieves the class of the object that was not found.
	 */
	public Class<Object> getObjectClass()
	{
		return objectType;
	}
}
