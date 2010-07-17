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


import graphics.PrimeMain;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Object;
import objects.Software;
import objects.softwareObjects.Antivirus;
import objects.softwareObjects.Email;
import objects.softwareObjects.Firewall;
import objects.softwareObjects.OfficeSuite;
import objects.softwareObjects.OperatingSystem;
import widgetManipulation.NetworkRules;
import widgets.WorkareaCanvas;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class DesktopSoftwareManagment
{
	/**
	 * TODO - Description
	 * 
	 */
	public static boolean softwareRulesCompliance(WorkareaCanvas canvas,
			Object obj, boolean withCleanUp)
	{
		if ( canvas != null && obj != null )
		{
			// Gets all the software of the given Object
			Software[] soft = obj.getSoftware();

			if ( soft != null && soft.length > 0 )
			{
				// Goes throught all the software objects
				for ( int i = 0; i < soft.length; i++ )
				{
					// Gets whether the software complies with the network rules
					boolean complies = softwareRulesCompliance(canvas, soft[i],
							withCleanUp);

					// If the software does not comply
					if ( !complies )
					{
						return false;
					}
				}
			}

			// If the process gets here, it means that all the software objects comply with the network rules.
			return true;
		}


		return false;
	}


	/**
	 * TODO - Description
	 * 
	 */
	public static boolean softwareRulesCompliance(WorkareaCanvas canvas,
			Software soft, boolean withCleanUp)
	{
		if ( canvas != null && soft != null )
		{
			// Gets the network rules
			NetworkRules rules = canvas.getRules();

			// If the network is restricted to a specific OperatingSystem
			if ( rules.isOSrestriction() )
			{
				// If the software given is an OperatingSystem
				if ( soft instanceof OperatingSystem )
				{
					System.out.println("OperatingSystem");
				}
			}


			// If the network is restricted to a specific Antivirus
			if ( rules.isAVrestriction() )
			{
				// If the software given is an Antivirus
				if ( soft instanceof Antivirus )
				{
					System.out.println("Antivirus");
				}
			}


			// If the network is restricted to a specific Firewall
			if ( rules.isFWrestriction() )
			{
				// If the software given is an Firewall
				if ( soft instanceof Firewall )
				{
					System.out.println("Firewall");
				}
			}


			// If the network is restricted to a specific Email
			if ( rules.isEMailRestriction() )
			{
				// If the software given is an Email
				if ( soft instanceof Email )
				{
					System.out.println("Email");
				}
			}


			// If the network is restricted to a specific OfficeSuite
			if ( rules.isOfficeSuiteRestriction() )
			{
				// If the software given is an OfficeSuite
				if ( soft instanceof OfficeSuite )
				{
					System.out.println("OfficeSuite");
				}
			}

			if ( withCleanUp )
			{
				canvas.cleanUp();
			}

			return true;
		}


		// If the canvas is is NULL but the software is not
		if ( soft != null )
		{
			return true;
		}


		return false;
	}



	/**
	 * TODO - Description
	 * 
	 */
	public static boolean removeSoftware(Object obj, Software soft)
	{
		// If the object and soft is not NULL and object and soft(which are both Object) are the same.
		if ( obj != null && soft != null && obj != soft )
		{
			// Attempts to find the canvas the object belongs to
			WorkareaCanvas canvas = CanvasManagment.findCanvas(obj,
					PrimeMain.canvases);

			// This method call allows for canvas to be null
			if ( softwareRulesCompliance(canvas, soft, true) )
			{
				obj.setSoftware(SoftwareManagment.removeSoftware(soft, obj));

				return true;
			}
		}

		return false;
	}




	/**
	 * Checks whether the name is found to be the name of a standard {@link OperatingSystem}.
	 */
	public static boolean foundInStandardOS(String name)
	{
		for ( int i = 0; i < PrimeMain.system_standard_OS.length; i++ )
		{
			if ( name.equalsIgnoreCase(PrimeMain.system_standard_OS[i]
					.getObjectName()) )
			{
				return true;
			}
		}

		return false;
	}



	/**
	 * Checks whether the name is found to be the name of a custom {@link OperatingSystem}.
	 */
	public static boolean foundInCustomOS(String name)
	{
		if ( !PrimeMain.system_custom_OS.isEmpty() )
		{
			// get an Iterator object for ArrayList using iterator() method.
			Iterator<OperatingSystem> itr = PrimeMain.system_custom_OS
					.iterator();

			while ( itr.hasNext() )
			{
				if ( name.equalsIgnoreCase(itr.next().getObjectName()) )
				{
					return true;
				}
			}
		}

		return false;
	}




	/**
	 * Returns {@link OperatingSystem} with the name found to be the name of a standard {@link OperatingSystem}.
	 */
	public static OperatingSystem getStandardOS(String name)
	{
		for ( int i = 0; i < PrimeMain.system_standard_OS.length; i++ )
		{
			if ( name.equalsIgnoreCase(PrimeMain.system_standard_OS[i]
					.getObjectName()) )
			{
				return PrimeMain.system_standard_OS[i];
			}
		}

		return null;
	}



	/**
	 * Returns {@link OperatingSystem} with the name found to be the name of a custom {@link OperatingSystem}.
	 */
	public static OperatingSystem getCustomOS(String name)
	{

		if ( !PrimeMain.system_custom_OS.isEmpty() )
		{
			// get an Iterator object for ArrayList using iterator() method.
			Iterator<OperatingSystem> itr = PrimeMain.system_custom_OS
					.iterator();

			while ( itr.hasNext() )
			{
				OperatingSystem os = itr.next();

				if ( name.equalsIgnoreCase(os.getObjectName()) )
				{
					return os;
				}
			}
		}

		return null;
	}



	/**
	 * This function returns an array of strings representing the names of all the {@link OperatingSystem} within the system,
	 * standard and custom.
	 */
	public static String[] getSystemOSname()
	{
		ArrayList<String> names = new ArrayList<String>();

		// Gets the System Standard OS names
		if ( PrimeMain.system_standard_OS != null )
		{
			for ( int i = 0; i < PrimeMain.system_standard_OS.length; i++ )
			{
				// Adds the name of the OS to the array list
				names.add(PrimeMain.system_standard_OS[i].getObjectName());
			}
		}

		// Gets the System Custom OS names
		if ( !PrimeMain.system_custom_OS.isEmpty() )
		{
			// get an Iterator object for ArrayList using iterator() method.
			Iterator<OperatingSystem> itr = PrimeMain.system_custom_OS
					.iterator();

			while ( itr.hasNext() )
			{
				OperatingSystem os = itr.next();

				if ( os != null )
				{
					names.add(os.getObjectName());
				}
			}
		}


		return names.toArray(new String[0]);
	}
}
