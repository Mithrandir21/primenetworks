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
package managment;


import objects.Object;
import objects.hardwareObjects.Motherboard;
import objects.peripheralObjects.Keyboard;
import objects.peripheralObjects.Monitor;
import objects.peripheralObjects.Mouse;



/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ExternalConnectionCheck
{

	public static boolean checkMBexternalPorts(Motherboard MB,
			Object[] connectedDevices, Object a)
	{
		// FIXME - Check if this is needed
		return false;
	}



	/**
	 * Javadoc-TODO - Description NEEDED!
	 */
	public static boolean checkMB_KeyboardConnected(Object[] connectedDevices)
	{
		for ( int i = 0; i < connectedDevices.length; i++ )
		{
			if ( connectedDevices[i] != null )
			{
				if ( connectedDevices[i].getClass().equals(Keyboard.class) )
				{
					return true;
				}
			}
		}


		return false;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 */
	public static boolean checkMB_MouseConnected(Object[] connectedDevices)
	{
		for ( int i = 0; i < connectedDevices.length; i++ )
		{
			if ( connectedDevices[i] != null )
			{
				if ( connectedDevices[i].getClass().equals(Mouse.class) )
				{
					return true;
				}
			}
		}


		return false;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 */
	public static boolean checkMB_MonitorConnected(Object[] connectedDevices)
	{
		for ( int i = 0; i < connectedDevices.length; i++ )
		{
			if ( connectedDevices[i] != null )
			{
				if ( connectedDevices[i].getClass().equals(Monitor.class) )
				{
					return true;
				}
			}
		}


		return false;
	}




	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param MB
	 * @param connectedDevices
	 */
	public static boolean checkMB_USBportsAvailable(Motherboard MB,
			Object[] connectedDevices)
	{
		// TODO - Fix check number of free USB ports
		return false;
	}

}
