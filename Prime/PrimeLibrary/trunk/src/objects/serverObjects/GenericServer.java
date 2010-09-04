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
package objects.serverObjects;


import objects.Object;
import objects.Servers;


/**
 * This generic server will be an "empty" server where the user can add any
 * desired software.
 * This class is created so the user can easily create unique server that will
 * reflect their needs.
 * 
 * @author Bahram Malaekeh
 */
public class GenericServer extends Servers
{

	/**
	 * A constructor for this Generic server class.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 */
	public GenericServer(String Name, String Desc)
	{
		super(Name, Desc);
	}


	/**
	 * A constructor for this Generic server class.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param SupConInt
	 *            The supported connection interfaces of the server.
	 */
	public GenericServer(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}

	/**
	 * A constructor for this Generic server class.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param ServerComponents
	 *            The initial components of the server.
	 */
	public GenericServer(String Name, String Desc, Object[] ServerComponents)
	{
		super(Name, Desc, ServerComponents);
	}


	/**
	 * A constructor for this Generic server class.
	 * 
	 * @param Name
	 *            The name of the server.
	 * @param Desc
	 *            The description of the server.
	 * @param SupConInt
	 *            The supported connection interfaces of the server.
	 * @param ServerComponents
	 *            The initial components of the server.
	 */
	public GenericServer(String Name, String Desc, String[] SupConInt,
			Object[] ServerComponents)
	{
		super(Name, Desc, SupConInt, ServerComponents);
	}

}
