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
package managment;


import java.io.Serializable;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class Settings implements Serializable
{
	// THE APPLICATION VERSION
	public static String appVersion = "Beta 3.2";


	// HARDWARE MESSAGES
	public static boolean showHardwareErrorMessages = true;

	public static boolean showHardwareWarningMessages = false;

	public static boolean showHardwareNoticeMessages = false;


	// SOFTWARE MESSAGES
	public static boolean showSoftwareErrorMessages = true;

	public static boolean showSoftwareWarningMessages = false;

	public static boolean showSoftwareNoticeMessages = false;


	// CONNECTION MESSAGES
	public static boolean showConnectionErrorMessages = true;

	public static boolean showConnectionWarningMessages = false;

	public static boolean showConnectionNoticeMessages = false;


	// NETWORK MESSAGES
	public static boolean showNetworkErrorMessages = true;

	public static boolean showNetworkWarningMessages = false;

	public static boolean showNetworkNoticeMessages = false;


	// CREATING NETWORK CONNECTION
	// A status saying whether or not the system is currently trying to create a
	// connection between two object on the workareaCanvas.
	public static boolean connecting = false;


	// CREATING ROOM BUTTON
	public static boolean roomsManipulation = false;


	// CREATING CONNECTION BUTTON
	public static boolean connectionToggle = false;


	// SHOW TIP OF THE DAY ON STARTUP
	public static boolean showTOFD = true;


	// SHOW OPERATING SYSTEM ICON
	public static boolean showOSicon = true;


	// SHOW IP LABEL FOR WIDGETS
	public static boolean showIP = true;


	// THE SYSTEMS STANDARD PERMISSION
	public static standardPermissions standPerm;




	private enum standardPermissions
	{
		NONE, READ, WRITE, EXECUTE, ALL
	}
}
