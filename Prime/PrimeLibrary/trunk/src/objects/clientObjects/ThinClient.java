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
package objects.clientObjects;


import objects.Clients;
import objects.Object;




/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ThinClient extends Clients
{

	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the thin client rating to "0"(since the
	 * rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the thin client.
	 * @param Desc
	 *            The description of the thin client.
	 */
	public ThinClient(String Name, String Desc)
	{
		super(Name, Desc);
	}



	/**
	 * Constructor of a thin client computer.<br>
	 * This constructor also sets the number of components in the system and the thin client rating to "0"(since the
	 * rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the thin client.
	 * @param Desc
	 *            The description of the thin client.
	 * @param SupConInt
	 *            The initial components an instance of a thin client has.
	 */
	public ThinClient(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}



	/**
	 * Constructor of a thin client computer.<br>
	 * This constructor also sets the number of components in the system and the thin client rating to "0"(since the
	 * rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the thin client.
	 * @param Desc
	 *            The description of the thin client.
	 * @param components
	 *            components The initial components an instance of a thin client has.
	 */
	public ThinClient(String Name, String Desc, Object[] components)
	{
		super(Name, Desc, components);
	}


	/**
	 * Constructor of a thin client computer.<br>
	 * This constructor also sets the number of components in the system and the thin client rating to "0"(since the
	 * rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the thin client.
	 * @param Desc
	 *            The description of the thin client.
	 * @param SupConInt
	 *            An array of strings that describes the supported connection interfaces.
	 * @param components
	 *            components The initial components an instance of a thin client has.
	 */
	public ThinClient(String Name, String Desc, String[] SupConInt,
			Object[] components)
	{
		super(Name, Desc, SupConInt, components);
	}
}
