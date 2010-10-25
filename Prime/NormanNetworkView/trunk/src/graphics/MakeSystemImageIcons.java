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
package graphics;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;

import javax.swing.ImageIcon;

import objects.hardwareObjects.CPU;
import objects.hardwareObjects.Discdrive;
import objects.hardwareObjects.ExternalNetworksCard;
import objects.hardwareObjects.GraphicsCard;
import objects.hardwareObjects.HDD;
import objects.hardwareObjects.InternalNetworksCard;
import objects.hardwareObjects.Motherboard;
import objects.hardwareObjects.Ram;
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
 * A class that contains methods for the creation and collection of the system
 * standard ImageIcon that are used, among other thing, for the creation of
 * objects or buttons.
 * 
 * @author Bahram Malaekeh
 */
public class MakeSystemImageIcons
{

	/**
	 * Gets the URL of this class to be used to locate all the system images and
	 * Icons. It calls the method visitAllFiles.
	 */
	public static void getImageIcons(boolean original)
	{
		URI uri = new File("./resource/images").toURI();

		File folder = new File(uri);

		visitAllFiles(folder);

		setupAllSystemImageIcons(original);
	}


	/**
	 * Goes through all files and directories under a given folder. It finds and
	 * sets all files within this given folder with the file extensions *.png,
	 * *.jpg and *.gif. It then creates ImageIcons of these files and adds them
	 * to the system standard ImageIcons, located in PrimeMain1, for later use
	 * in objects or buttons.
	 */
	public static void visitAllFiles(File dir)
	{
		if ( dir.isDirectory() )
		{
			String[] children = dir.list();
			for ( int i = 0; i < children.length; i++ )
			{
				visitAllFiles(new File(dir, children[i]));
			}
		}
		else
		{
			String name = dir.getName();

			if ( name.endsWith(".png") || name.endsWith(".jpg")
					|| name.endsWith(".gif") )
			{
				try
				{
					name = name.substring(0, (name.length() - 4));

					ImageIcon toBeAdded = ImageLocator.createImageIcon(dir
							.toURI().toURL());
					toBeAdded.setDescription(name);

					PrimeMain.images.add(toBeAdded);
				}
				catch ( MalformedURLException e )
				{
					e.printStackTrace();
				}
			}
		}
	}



	/**
	 * This method initiates the different setup methods for system images.
	 */
	public static void setupAllSystemImageIcons(boolean original)
	{
		// Sets up the images for main objects.
		setupObjectImageIcon(original);

		// Sets up the images for the hardware objects.
		setupHardwareImageIcon(original);

		// Sets up the images for the software objects.
		setupSoftwareImageIcon(original);
	}



	/**
	 * Sets up both the systems standard objects ImageIcons and the systems
	 * hashmap of the object ImageIcon.
	 */
	private static void setupObjectImageIcon(boolean original)
	{
		// Desktop
		setupImage(0, "Desktop", original);


		// Laptop
		setupImage(1, "Laptop", original);


		// Thin Client
		setupImage(2, "Thin Client", original);


		// Generic Server
		setupImage(3, "Server", original);


		// Antivirus Server
		setupImage(4, "Antivirus Server", original);


		// HTTP Server
		setupImage(5, "HTTP Server", original);


		// Backup Server
		setupImage(6, "Backup Server", original);


		// NAS Server
		setupImage(7, "NAS Server", original);


		// Database Server
		setupImage(8, "Database Server", original);


		// Virtualization Server
		setupImage(9, "Virtualization Server", original);


		// Mail Server
		setupImage(10, "Mail Server", original);


		// Firewall Server
		setupImage(11, "Firewall Server", original);


		// Proxy Server
		setupImage(12, "Proxy Server", original);


		// Printer Server
		setupImage(13, "Printer Server", original);


		// NAS
		setupImage(14, "ExternalHDD", original);


		// Scanner
		setupImage(15, "Scanner", original);


		// Printer
		setupImage(16, "Printer", original);


		// Fax
		setupImage(17, "Fax", original);


		// MFP
		setupImage(18, "MFP", original);


		// Network Printer
		setupImage(19, "Network Printer", original);


		// NetworkMultifunctionPrinter
		setupImage(20, "NetworkMultifunctionPrinter", original);


		// Hub
		setupImage(21, "Hub", original);


		// Switch
		setupImage(22, "Switch", original);


		// Router
		setupImage(23, "Router", original);


		// Modem
		setupImage(24, "Modem", original);


		// Wireless Router
		setupImage(25, "Wireless Router", original);


		// Internet
		setupImage(26, "Internet", original);


		// Rack
		setupImage(27, "Rack", original);
	}


	/**
	 * This function sets up the image icon for the system.
	 * If the given boolean is true, the system will load original icons. (Used
	 * when reseting.)
	 */
	private static void setupImage(int objectListID, String name,
			boolean original)
	{
		if ( name != null && objectListID > -1 )
		{
			if ( original )
			{
				// Gets the objects ImageIcon
				ImageIcon objectIcon = ImageLocator.getImageIconObject(name);

				// Sets the Objects Visual Image
				PrimeMain.objectlist.get(objectListID).setVisualImage(
						objectIcon);

				// Places the ImageIcon into the systems hashmap of
				// ImageIcons
				PrimeMain.objectImageIcons.put(
						PrimeMain.objectlist.get(objectListID).getClass(),
						objectIcon);
			}
			else
			{
				if ( PrimeMain.objectlist.get(objectListID).getVisualImage() == null )
				{
					// Gets the objects ImageIcon
					ImageIcon objectIcon = ImageLocator
							.getImageIconObject(name);

					// Sets the Objects Visual Image
					PrimeMain.objectlist.get(objectListID).setVisualImage(
							objectIcon);

					// Places the ImageIcon into the systems hashmap of
					// ImageIcons
					PrimeMain.objectImageIcons.put(
							PrimeMain.objectlist.get(objectListID).getClass(),
							objectIcon);
				}
				else
				{
					// Places the ImageIcon into the systems hashmap of
					// ImageIcons
					PrimeMain.objectImageIcons.put(
							PrimeMain.objectlist.get(objectListID).getClass(),
							PrimeMain.objectlist.get(objectListID)
									.getVisualImage());
				}
			}
		}
	}







	/**
	 * Sets up the systems hashmap of the hardware objects ImageIcon.
	 */
	private static void setupHardwareImageIcon(boolean original)
	{
		// CPU
		setupImage(CPU.class, "CPU", original);


		// Discdrive
		setupImage(Discdrive.class, "Optical-Drive", original);


		// ExternalNetworksCard
		setupImage(ExternalNetworksCard.class, "ExternalNIC", original);


		// GraphicsCard
		setupImage(GraphicsCard.class, "GPU", original);


		// HDD
		setupImage(HDD.class, "Harddisc", original);


		// InternalNetworksCard
		setupImage(InternalNetworksCard.class, "NIC", original);


		// Motherboard
		setupImage(Motherboard.class, "Motherboard", original);


		// Ram
		setupImage(Ram.class, "RAM", original);
	}


	/**
	 * This function sets up the image icon for the system.
	 * If the given boolean is true, the system will load original icons. (Used
	 * when reseting.)
	 */
	private static void setupImage(Class<?> classType, String name,
			boolean original)
	{
		// Loads the system original icon
		if ( original )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject(name);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(classType, objectIcon);
		}
		else
		{
			if ( PrimeMain.objectImageIcons.get(classType) == null )
			{
				// Gets the objects ImageIcon
				ImageIcon objectIcon = ImageLocator.getImageIconObject(name);

				// Places the ImageIcon into the systems hashmap of ImageIcons
				PrimeMain.objectImageIcons.put(classType, objectIcon);
			}
		}
	}

	/**
	 * Sets up the systems hashmap of the software objects ImageIcon.
	 */
	private static void setupSoftwareImageIcon(boolean original)
	{
		// GenericSoftware
		setupImage(GenericSoftware.class, "Generic-Software", original);


		// Antivirus
		setupImage(Antivirus.class, "Antivirus-Software", original);


		// Backup
		setupImage(Backup.class, "Backup-Software", original);


		// NASsoftware
		setupImage(NASsoftware.class, "NAS-Software", original);


		// Database
		setupImage(Database.class, "Database-Software", original);


		// VirtualizationSoftware
		setupImage(VirtualizationSoftware.class, "Virtualization-Software",
				original);


		// Email
		setupImage(Email.class, "Email-Software", original);


		// Firewall
		setupImage(Firewall.class, "Firewall-Software", original);


		// OfficeSuite
		setupImage(OfficeSuite.class, "OfficeSuite-Software", original);


		// OperatingSystem
		setupImage(OperatingSystem.class, "OperatingSystem-Software", original);


		// Proxy
		setupImage(Proxy.class, "Proxy-Software", original);


		// SecuritySuite
		setupImage(SecuritySuite.class, "SecuritySuite-Software", original);


		// Webserver
		setupImage(Webserver.class, "Webserver-Software", original);


		// RemoteDesktop
		setupImage(RemoteDesktop.class, "RemoteDesktop-Software", original);
	}


}
