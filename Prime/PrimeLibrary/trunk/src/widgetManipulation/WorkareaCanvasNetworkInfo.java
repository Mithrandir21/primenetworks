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
package widgetManipulation;



import java.io.Serializable;
import java.util.UUID;

import managment.NetworkManagment;
import widgets.WorkareaCanvas;


/**
 * This class contains network information about a given {@link WorkareaCanvas}.
 * This includes IP range and netmask.
 * 
 * @author Bahram Malaekeh
 */
public class WorkareaCanvasNetworkInfo implements Serializable
{

	/**
	 * The serial number of the {@link WorkareaCanvas} this set of rules applies
	 * to.
	 */
	private UUID canvasSerial;

	/**
	 * The networks netmask
	 */
	private String netmask = null;


	/**
	 * The IP range start IP
	 */
	private String ipRangeFrom = null;


	/**
	 * The IP range end IP
	 */
	private String ipRangeTo = null;


	/**
	 * The widgets notes
	 */
	private String networkNotes = null;


	// // The Array that contains all the available network IPs
	// private String[] availableIPs = new String[1];




	/**
	 * A constructor for the class that takes a {@link WorkareaCanvas} as the
	 * only argument. This class will contain network information for that
	 * {@link WorkareaCanvas}.
	 * 
	 * @param canvas
	 *            The WorkareaCanvas this information class belongs to.
	 */
	public WorkareaCanvasNetworkInfo(WorkareaCanvas canvas)
	{
		canvasSerial = canvas.getSerial();
	}



	// GETTERS


	/**
	 * Gets the serial number of the {@link WorkareaCanvas} this information
	 * applies to.
	 */
	public UUID getCanvasSerial()
	{
		return canvasSerial;
	}


	/**
	 * @return Gets the network mask set for this classes WorkareaCanvas.
	 */
	public String getNetmask()
	{
		// If the variable field is null
		if ( netmask == null )
		{
			return "";
		}
		return netmask;
	}




	/**
	 * Gets the IP range that this classes WorkareaCanvas starts with.
	 * 
	 * @return IP range start.
	 */
	public String getIpRangeFrom()
	{
		// If the variable field is null
		if ( ipRangeFrom == null )
		{
			return "";
		}
		return ipRangeFrom;
	}



	/**
	 * Gets the IP range that this classes WorkareaCanvas ends with.
	 * 
	 * @return IP range ends.
	 */
	public String getIpRangeTo()
	{
		// If the variable field is null
		if ( ipRangeTo == null )
		{
			return "";
		}
		return ipRangeTo;
	}



	/**
	 * @return Gets the network notes.
	 */
	public String getNetworkNotes()
	{
		// If the variable field is null
		if ( networkNotes == null )
		{
			return "";
		}
		return networkNotes;
	}



	// SETTERS


	/**
	 * Sets the netmask of the {@link WorkareaCanvas}. The given String is
	 * validated before being set.
	 */
	public boolean setNetmask(String netmask)
	{
		// If the string is empty
		if ( netmask.equals("") )
		{
			this.netmask = netmask;
			return true;
		}

		// If the string given matches the given pattern.
		if ( NetworkManagment.getIPpattern().matcher(netmask).matches() )
		{
			this.netmask = netmask;
			return true;
		}

		return false;
	}



	/**
	 * Sets the start of the IP range. The given String is validated before
	 * being set.
	 */
	public boolean setIpRangeFrom(String ipRangeFrom)
	{
		if ( ipRangeFrom != null && ipRangeFrom.equals("") )
		{
			this.ipRangeFrom = ipRangeFrom;
			return true;
		}


		// If the string given matches the given pattern.
		if ( NetworkManagment.getIPpattern().matcher(ipRangeFrom).matches() )
		{
			this.ipRangeFrom = ipRangeFrom;
			return true;
		}

		return false;
	}



	/**
	 * Sets the end of the IP range. The given String is validated before being
	 * set.
	 */
	public boolean setIpRangeTo(String ipRangeTo)
	{
		if ( ipRangeTo != null && ipRangeTo.equals("") )
		{
			this.ipRangeTo = ipRangeTo;
			return true;
		}

		// If the string given matches the given pattern.
		if ( NetworkManagment.getIPpattern().matcher(ipRangeTo).matches() )
		{
			this.ipRangeTo = ipRangeTo;
			return true;
		}

		return false;
	}



	/**
	 * Sets the Network notes for the network.
	 */
	public void setNetworkNotes(String networkNotes)
	{
		this.networkNotes = networkNotes;
	}


	//
	// public void setAvailableIPs(String[] availableIPs)
	// {
	// this.availableIPs = availableIPs;
	// }





	// CLASS FUNCTIONS


	// FIXME - Do something about this
	// /**
	// * Gets the first available IP address in this classes array of available
	// * IPs. The IP which is returned is then
	// * removed from the IP array.
	// *
	// * @return Returns the first available IP address. If none are found or
	// * the array is null, null is returned.
	// */
	// public String getFirstAvailableIP()
	// {
	// // If the available IPs array is not null and the first index is not
	// // empty
	// if ( availableIPs != null && !(availableIPs[0].equals("")) )
	// {
	// // Gets the available IP
	// String ip = availableIPs[0];
	//
	// // Sets the array index to null
	// availableIPs[0] = null;
	//
	// // Cleans up the String array so as to not have a empty first index
	// availableIPs = logistical.cleanup.cleanObjectArray(availableIPs);
	//
	// // Returns the IP that was previously at index 0 in the array
	// return ip;
	// }
	//
	//
	// // The array is either null or the first index is null
	// return null;
	// }
	//
	//
	// public void createAvailableIPs()
	// {
	// // SINCE THE IP RANGES ARE CHECK AND VALIDATED ON WRITTING, THE WILL BE
	// // VALID.
	// // Splits the fromIP into strings on the symbol "."
	// String[] fromString = ipRangeFrom.split("\\.");
	//
	// // Splits the toIP into strings on the symbol "."
	// String[] toString = ipRangeTo.split("\\.");
	//
	//
	//
	// int[] from = new int[4];
	// int[] to = new int[4];
	//
	//
	// // Converts the strings to int
	// for ( int i = 0; i < 4; i++ )
	// {
	// from[i] = Integer.parseInt(fromString[i]);
	// to[i] = Integer.parseInt(toString[i]);
	// }
	//
	//
	// int numberOfIPs = 0;
	//
	//
	// // Check the first numbers in the IP, before the first "."
	// // Eks:
	// // 1. 192 < 193
	// if ( from[0] < to[0] )
	// {
	//
	// }
	// // 1. 192 == 192
	// else if ( from[0] == to[0] )
	// {
	// // 2. 40 < 41
	// if ( from[1] < to[1] )
	// {
	// return true;
	// }
	// // 2. 40 == 40
	// else if ( from[1] == to[1] )
	// {
	// // 3. 90 < 91
	// if ( from[2] < to[2] )
	// {
	// return true;
	// }
	// // 3. 90 == 90
	// else if ( from[2] == to[2] )
	// {
	// // 4. 1 < 2
	// if ( from[3] < to[3] )
	// {
	// return true;
	// }
	// // 4. 1 == 1
	// else if ( from[3] == to[3] )
	// {
	//
	// }
	// }
	// }
	// }
	// }
	//
	//
	//
	//
	// /**
	// * Adds the given String to the array of the currently available network
	// * IPs.
	// *
	// * @param IP
	// * The IP to be added to the array of available network IPs.
	// */
	// private void addIPtoAvailable(String IP)
	// {
	// // Creates a new array with one more index
	// String[] ips = new String[availableIPs.length + 1];
	//
	// // Adds all the IPs from the current IP array
	// for ( int i = 0; i < availableIPs.length; i++ )
	// {
	// ips[i] = availableIPs[i];
	// }
	//
	//
	// // Adds the last IP
	// ips[ips.length] = IP;
	//
	// // Sets the newly created IP array, with the new IP at the end, as the
	// current network IP array
	// availableIPs = ips;
	// }
}
