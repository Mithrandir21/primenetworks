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
package exceptions;


import objects.Object;
import objects.hardwareObjects.Motherboard;


/**
 * This {@link Exception} represents a failure to connect a not given
 * {@link Object} to the port of the given {@link Motherboard} on the given
 * {@link Object}.
 * 
 * @author Bahram Malaekeh
 */
public class PortIsNotRegisteredOnMotherboard extends Exception
{
	Motherboard mb = null;

	Object obj = null;

	String conType = "";


	/**
	 * A constructor that takes the {@link Motherboard} that failed to connect
	 * and the {@link Object} on which the {@link Motherboard} is installed.
	 */
	public PortIsNotRegisteredOnMotherboard(Motherboard aMB, Object aOb,
			String type)
	{
		this.mb = aMB;
		this.obj = aOb;
		this.conType = type;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "The motherboard, " + this.mb.getObjectName()
				+ ", on the object " + this.obj.getObjectName()
				+ " has not registered " + this.conType
				+ " ports in the correct way.";

		return output;
	}
}
