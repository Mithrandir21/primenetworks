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
import java.util.logging.Logger;


/**
 * This class contains final static fields that represent the systems settings.
 * The fields in this class are used throughout the system.
 * 
 * @author Bahram Malaekeh
 */
public class Settings implements Serializable
{
	// THE APPLICATION VERSION
	public final static String appVersion = "Beta 3.4";


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
	public static boolean showTOTD = true;


	// SHOW OPERATING SYSTEM ICON
	public static boolean showOSicon = true;


	// SHOW IP LABEL FOR WIDGETS
	public static boolean showIP = true;


	// THE SYSTEMS STANDARD PERMISSION
	public static standardPermissions standPerm = standardPermissions.ALL;


	// THE SYSTEM LOCALE(LANGUAGE GUIDE)
	public static systemLocale primeLocale = systemLocale.en;


	/**
	 * This boolean will control the flow information within the system.
	 * While the system is in debug mode, it will print out any error to a
	 * console. But outside debug mode it will attempt to send them to a
	 * {@link Logger}.
	 */
	public static boolean debug = false;


	/**
	 * This function resets all settings to the original.
	 */
	public static void resetSettings()
	{
		// HARDWARE MESSAGES
		showHardwareErrorMessages = true;

		showHardwareWarningMessages = false;

		showHardwareNoticeMessages = false;


		// SOFTWARE MESSAGES
		showSoftwareErrorMessages = true;

		showSoftwareWarningMessages = false;

		showSoftwareNoticeMessages = false;


		// CONNECTION MESSAGES
		showConnectionErrorMessages = true;

		showConnectionWarningMessages = false;

		showConnectionNoticeMessages = false;


		// NETWORK MESSAGES
		showNetworkErrorMessages = true;

		showNetworkWarningMessages = false;

		showNetworkNoticeMessages = false;


		// CREATING NETWORK CONNECTION
		// A status saying whether or not the system is currently trying to
		// create a connection between two object on the workareaCanvas.
		connecting = false;


		// CREATING ROOM BUTTON
		roomsManipulation = false;


		// CREATING CONNECTION BUTTON
		connectionToggle = false;


		// SHOW TIP OF THE DAY ON STARTUP
		showTOTD = true;


		// SHOW OPERATING SYSTEM ICON
		showOSicon = true;


		// SHOW IP LABEL FOR WIDGETS
		showIP = true;


		// THE SYSTEMS STANDARD PERMISSION
		standPerm = standardPermissions.ALL;


		// THE SYSTEM LOCALE(LANGUAGE GUIDE)
		primeLocale = systemLocale.en;


		// DEBUG MODE
		debug = false;
	}



	/**
	 * This {@link Enum} contains all the different types of standard
	 * permissions.
	 * 
	 * @author Bahram Malaekeh
	 */
	private enum standardPermissions
	{
		NONE, READ, WRITE, EXECUTE, ALL
	}


	/**
	 * This {@link Enum} contains all the possible locales possible in the
	 * system.
	 * 
	 * @author Bahram Malaekeh
	 */
	public enum systemLocale
	{
		en, no_NO
	}
}
