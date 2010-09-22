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


import java.io.Serializable;


/**
 * An instance of this class will represent a set of permissions for a
 * {@link Group} on a specific device. Since neither the {@link Group} or the
 * device is given in this class, the instance of this class can be copied to
 * other groups or device to duplicate permissions.
 * 
 * An instance of this class will only be found inside a {@link Group} object.
 * 
 * @author Bahram Malaekeh
 */
public class Permissions implements Serializable, Cloneable
{
	/**
	 * Permission to read from a device.
	 */
	private boolean readAccess;


	/**
	 * Permission to write to a device.
	 */
	private boolean writeAccess;


	/**
	 * Permission to execute code on a device.
	 */
	private boolean executeAccess;


	/**
	 * A constructor for this class that takes as parameters access rights.
	 */
	public Permissions(boolean read, boolean write, boolean execute)
	{
		this.readAccess = read;
		this.writeAccess = write;
		this.executeAccess = execute;
	}



	// GETTERS

	/**
	 * Gets whether or not the group has permission to read from a device.
	 */
	public boolean hasReadAccess()
	{
		return this.readAccess;
	}


	/**
	 * Gets whether or not the group has permission to write to a device.
	 */
	public boolean hasWriteAccess()
	{
		return this.writeAccess;
	}


	/**
	 * Gets whether or not the group has permission to execute code on a device.
	 */
	public boolean hasExecuteAccess()
	{
		return this.executeAccess;
	}




	// SETTERS

	/**
	 * Sets whether or not the group has permission to read from a device.
	 */
	public void setReadAccess(boolean readAccess)
	{
		this.readAccess = readAccess;
	}



	/**
	 * Sets whether or not the group has permission to write to a device.
	 */
	public void setWriteAccess(boolean writeAccess)
	{
		this.writeAccess = writeAccess;
	}



	/**
	 * Sets whether or not the group has permission to execute code on a device.
	 */
	public void setExecuteAccess(boolean executeAccess)
	{
		this.executeAccess = executeAccess;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Permissions clone()
	{
		return new Permissions(hasReadAccess(), hasWriteAccess(),
				hasExecuteAccess());
	}
}
