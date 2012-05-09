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
package connections;


import java.io.Serializable;

import objects.Object;


/**
 * A representation of a non-networking connection between different devices.
 * This connection represents any connection that is not a networking
 * connection, like a connection between a computer and a printer, a computer
 * and a scanner, a printer and a network router(USB connection) and so on.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class DeviceConnection extends Connection implements Serializable
{

	/**
	 * A class constructor for the connection between device.
	 * 
	 * @param From
	 *            The object the connection emanates from.
	 * @param To
	 *            The object which is to be connected to.
	 * @param connection
	 *            A string that says what kind of connection there is between
	 *            the objects.
	 */
	public DeviceConnection(String Name, String Desc, Object From, Object To,
			String connection)
	{
		super(Name, Desc, From, To, connection);
	}

}
