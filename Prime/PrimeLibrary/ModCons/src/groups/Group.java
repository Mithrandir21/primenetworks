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
package groups;


import java.awt.Desktop;
import java.io.Serializable;
import java.util.HashMap;

import objects.Object;
import objects.Servers;


/**
 * An instance of this class will represent a group of users with certain rights
 * in regards to different {@link Desktop Desktops}, {@link Servers} or other
 * equipment that allows for group permissions control.
 * 
 * This class will NOT contain any information about any single user, but should
 * be used to referrer to a group of users as defined by the user.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class Group implements Serializable
{
	/**
	 * The name of the group. (Should be descriptive.)
	 */
	private String groupName;


	/**
	 * The description of the group.
	 */
	private String groupDescription;


	/**
	 * The HashMap that contains group permissions for widgetObjects.
	 * The {@link Long} will be the {@link Object} serial number.
	 */
	private HashMap<Long, Permissions> devicePermissions;



	/**
	 * A constructor for the class that takes the group name and group
	 * description.
	 */
	public Group(String name, String desc)
	{
		this.groupName = name;
		this.groupDescription = desc;
		this.devicePermissions = new HashMap<Long, Permissions>();
	}


	/**
	 * A constructor for the class that takes the group name, group
	 * description and a HashMap<Long, Permissions> as the groups set of
	 * permissions.
	 * (Should only be used when copying a group.)
	 */
	public Group(String name, String desc,
			HashMap<Long, Permissions> permissions)
	{
		this.groupName = name;
		this.groupDescription = desc;
		this.devicePermissions = permissions;
	}



	// GETTERS

	/**
	 * Gets the name of the group.
	 */
	public String getGroupName()
	{
		return this.groupName;
	}


	/**
	 * Gets the description of the group.
	 */
	public String getGroupDescription()
	{
		return this.groupDescription;
	}


	/**
	 * Gets the {@link HashMap} with the group {@link Permissions}.
	 */
	public HashMap<Long, Permissions> getDevicePermissions()
	{
		return this.devicePermissions;
	}




	// SETTERS

	/**
	 * Sets the name of the group.
	 */
	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}


	/**
	 * Sets the description of the group.
	 */
	public void setGroupDescription(String groupDescription)
	{
		this.groupDescription = groupDescription;
	}


	/**
	 * Sets the {@link HashMap} with the group {@link Permissions}.
	 */
	public void setDevicePermissions(
			HashMap<Long, Permissions> devicePermissions)
	{
		this.devicePermissions = devicePermissions;
	}
}
