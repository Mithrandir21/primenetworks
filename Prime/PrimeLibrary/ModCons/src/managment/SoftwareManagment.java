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


import java.awt.Desktop;

import logistical.cleanup;
import objects.Object;
import objects.Servers;
import objects.Software;
import objects.softwareObjects.Antivirus;
import objects.softwareObjects.Backup;
import objects.softwareObjects.Database;
import objects.softwareObjects.Email;
import objects.softwareObjects.Firewall;
import objects.softwareObjects.GenericSoftware;
import objects.softwareObjects.NASsoftware;
import objects.softwareObjects.OfficeSuite;
import objects.softwareObjects.OperatingSystem;
import objects.softwareObjects.Proxy;
import objects.softwareObjects.RemoteDesktop;
import objects.softwareObjects.SecuritySuite;
import objects.softwareObjects.VirtualizationSoftware;
import objects.softwareObjects.Webserver;


/**
 * Class that contains different functions that adds, removes and replaces
 * software. These functions work in regards to {@link Object Objects}, such as
 * a {@link Desktop} or a {@link Servers}.
 * 
 * @author Bahram Malaekeh
 */
public class SoftwareManagment
{

	/**
	 * This function creates a new array of {@link Software} from the array of
	 * software in the given {@link Object}. It then adds the given
	 * {@link Software} to the newly created software array and
	 * returns that
	 * array.
	 * 
	 * @param sw
	 *            The {@link Software} to be added.
	 * @param obj
	 *            The {@link Object} that holds the current array of
	 *            {@link Software}.
	 * @return A new array of {@link Software} that will contain all the
	 *         software given by the given {@link Object} and the given
	 *         {@link Software}.
	 */
	public static Software[] addSoftware(Software sw, Object obj)
	{
		Software[] curSW = obj.getSoftware();

		Software[] newSW = null;

		if ( curSW != null )
		{
			newSW = new Software[curSW.length + 1];


			// Addes the new items to the end of the new array
			System.arraycopy(curSW, 0, newSW, 0, curSW.length);


			// The index which is the same as the length of the current software
			// array will be exactly one index behind the end of all the current
			// software. Therefore the new software will be placed there.
			newSW[curSW.length] = sw;
		}
		else
		{
			// Creates an array with only one index
			newSW = new Software[1];

			// Sets the new software
			newSW[0] = sw;
		}


		return newSW;
	}




	/**
	 * This function creates a new array of {@link Software} from the array of
	 * software in the given {@link Object}. It then removes the given
	 * {@link Software} from the newly created software array and
	 * returns that
	 * array. If the given {@link Software} is not found in the array of the
	 * given {@link Object}, no changes will be made to the {@link Software}
	 * array.
	 * 
	 * @param sw
	 *            The {@link Software} to be removed.
	 * @param obj
	 *            The {@link Object} that holds the current array of
	 *            {@link Software}.
	 * @return A new array of {@link Software} that will contain all the
	 *         software given by the given {@link Object}, minus the
	 *         {@link Software} object given.
	 */
	public static Software[] removeSoftware(Software sw, Object obj)
	{
		Software[] curSW = obj.getSoftware();

		// Gets the index of the software object
		int index = ArrayManagment.arrayContainsReturnsIndex(curSW, sw);


		// Removes the object at the given index
		if ( index != -1 )
		{
			curSW[index] = null;
		}

		// Removes the null pointer in the array
		curSW = cleanup.cleanObjectArray(curSW);


		// Returns the array
		return curSW;
	}


	/**
	 * This function processes all the changes that has been made to the given
	 * object. It calls on method that validate and, if necessary, remove
	 * hardware from the the object.
	 */
	public static void processAllChanges(Object obj)
	{
		Software[] software = obj.getSoftware();

		for ( int i = 0; i < software.length; i++ )
		{
			// If the software is not an OperatingSystem object
			if ( !(software[i] instanceof OperatingSystem) )
			{
				// Checks whether or not the given software is still compatible
				if ( !(SoftwareManagment.validateSoftware(software[i], obj)) )
				{
					// If the software is not compatible the index of
					// that software will be set to null
					software[i] = null;
				}
			}
		}

		// Removes the null pointers in the software array
		software = cleanup.cleanObjectArray(software);

		// Sets the objects software
		obj.setSoftware(software);
	}



	/**
	 * The method validates the given software in the given object. It checks
	 * for compatibility between the objects Operating system and the Operating
	 * systems supported by the given software.
	 * 
	 * @param sw
	 *            The software that is to be validated.
	 * @param obj
	 *            The object that the software is to be validated against.
	 */
	public static boolean validateSoftware(Software sw, Object obj)
	{
		OperatingSystem[] os = null;

		String osName = null;

		// Gets the possible OS
		os = getOperatingSystem(obj);

		boolean foundCompatible = false;


		// If any Operating System is found
		if ( os != null )
		{
			// Goes through all the Operating Systems found
			for ( int i = 0; i < os.length; i++ )
			{

				if ( os != null )
				{
					String[] swOS = null;

					// Gets the name of the Operating system.
					osName = os[i].getObjectName();

					if ( sw instanceof GenericSoftware )
					{
						GenericSoftware swObj = (GenericSoftware) sw;

						swOS = swObj.getSupportedOperatingSystems();
					}
					else if ( sw instanceof Antivirus )
					{
						Antivirus swObj = (Antivirus) sw;

						swOS = swObj.getSupportedOperatingSystems();
					}
					else if ( sw instanceof Backup )
					{
						Backup swObj = (Backup) sw;

						swOS = swObj.getSupportedOperatingSystems();
					}
					else if ( sw instanceof NASsoftware )
					{
						NASsoftware swObj = (NASsoftware) sw;

						swOS = swObj.getSupportedOperatingSystems();
					}
					else if ( sw instanceof Database )
					{
						Database swObj = (Database) sw;

						swOS = swObj.getSupportedOperatingSystems();

					}
					else if ( sw instanceof VirtualizationSoftware )
					{
						VirtualizationSoftware swObj = (VirtualizationSoftware) sw;

						swOS = swObj.getSupportedOperatingSystems();
					}
					else if ( sw instanceof Email )
					{
						Email swObj = (Email) sw;

						swOS = swObj.getSupportedOperatingSystems();

					}
					else if ( sw instanceof Firewall )
					{
						Firewall swObj = (Firewall) sw;

						swOS = swObj.getSupportedOperatingSystems();

					}
					else if ( sw instanceof OfficeSuite )
					{
						OfficeSuite swObj = (OfficeSuite) sw;

						swOS = swObj.getSupportedOperatingSystems();

					}
					else if ( sw instanceof Proxy )
					{
						Proxy swObj = (Proxy) sw;

						swOS = swObj.getSupportedOperatingSystems();

					}
					else if ( sw instanceof SecuritySuite )
					{
						SecuritySuite swObj = (SecuritySuite) sw;

						swOS = swObj.getSupportedOperatingSystems();

					}
					else if ( sw instanceof Webserver )
					{
						Webserver swObj = (Webserver) sw;

						swOS = swObj.getSupportedOperatingSystems();
					}
					else if ( sw instanceof RemoteDesktop )
					{
						RemoteDesktop swObj = (RemoteDesktop) sw;

						swOS = swObj.getSupportedOperatingSystems();
					}


					if ( swOS != null )
					{
						// If the OS name is found to be an OS that the software
						// supports
						if ( ArrayManagment.arrayContains(swOS, osName) )
						{
							foundCompatible = true;
						}
					}
				}
			}
		}

		// If the methods gets to this point it means that the object does not
		// contain any OS and there can be no validation of compatibility
		return foundCompatible;
	}



	/**
	 * Find all the OperatingSystem instance in the software array of the
	 * given Object and returns an array of {@link OperatingSystem}. If it does
	 * not exist, it will return a null pointer.
	 */
	public static OperatingSystem[] getOperatingSystem(Object obj)
	{
		Software[] software = obj.getSoftware();

		if ( software != null )
		{
			// Creates an array with as many indexes as the object contains
			// software
			OperatingSystem[] OperatingSystems = new OperatingSystem[software.length];

			for ( int i = 0; i < software.length; i++ )
			{
				if ( software[i] instanceof OperatingSystem )
				{
					// Gets the first object in the array of software thats is
					// an
					// instance of OperatingSystem
					OperatingSystems[i] = (OperatingSystem) software[i];
				}
			}

			// Removes the null pointers in the software array
			OperatingSystems = cleanup.cleanObjectArray(OperatingSystems);


			if ( OperatingSystems.length > 0 )
			{
				return OperatingSystems;
			}
		}

		return null;
	}


	/**
	 * This method removes all the software within the given object and replaces
	 * them with an array of software only populated by the given Operating
	 * System object.
	 * 
	 * @param os
	 *            The {@link OperatingSystem} object that is to be the only
	 *            {@link Software} object "installed" on the given
	 *            {@link Object}.
	 * @param obj
	 *            The {@link Object} where the new {@link OperatingSystem} is to
	 *            be "installed".
	 */
	public static void formatSystem(OperatingSystem os, Object obj)
	{
		// Sets the OS object as the only object in the array
		Software[] temp = { os };

		// Sets the array to the software of the object
		obj.setSoftware(temp);
	}
}
