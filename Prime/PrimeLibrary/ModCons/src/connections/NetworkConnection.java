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
 * A representation of a connection between networking devices. This can be
 * between a computer and a router, a computer and and another computer, a
 * router and another router and so on. In computers the connection is normally
 * maid with the NIC.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class NetworkConnection extends Connection implements Serializable
{
	/**
	 * A class constructor for the network connection.
	 * 
	 * @param From
	 *            The object the connection emanates from.
	 * @param To
	 *            The object which is to be connected to.
	 */
	public NetworkConnection(String Name, String Desc, Object From, Object To,
			String connection)
	{
		super(Name, Desc, From, To, connection);
	}
}
