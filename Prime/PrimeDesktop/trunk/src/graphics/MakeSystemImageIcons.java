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
import objects.softwareObjects.OfficeSuite;
import objects.softwareObjects.OperatingSystem;
import objects.softwareObjects.Proxy;
import objects.softwareObjects.SecuritySuite;
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
	public void getImageIcons()
	{
		URI uri = new File("./resource/images").toURI();

		File folder = new File(uri);

		visitAllFiles(folder);

		setupAllSystemImageIcons();
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
	 * TODO - Description
	 */
	public static void setupAllSystemImageIcons()
	{
		// Sets up the images for main objects.
		setupObjectImageIcon();

		// Sets up the images for the hardware objects.
		setupHardwareImageIcon();

		// Sets up the images for the software objects.
		setupSoftwareImageIcon();
	}



	/**
	 * Sets up both the systems standard objects ImageIcons and the systems
	 * hashmap of the object ImageIcon.
	 */
	private static void setupObjectImageIcon()
	{
		// Desktop
		if ( PrimeMain.objectlist.get(0).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("Desktop");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(0).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(0)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(0)
					.getClass(), PrimeMain.objectlist.get(0).getVisualImage());
		}


		// Laptop
		if ( PrimeMain.objectlist.get(1).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("Laptop");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(1).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(1)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(1)
					.getClass(), PrimeMain.objectlist.get(1).getVisualImage());
		}


		// Thin Client
		if ( PrimeMain.objectlist.get(2).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Thin Client");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(2).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(2)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(2)
					.getClass(), PrimeMain.objectlist.get(2).getVisualImage());
		}


		// HTTP Server
		if ( PrimeMain.objectlist.get(3).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("HTTP Server");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(3).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(3)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(3)
					.getClass(), PrimeMain.objectlist.get(3).getVisualImage());
		}


		// Backup Server
		if ( PrimeMain.objectlist.get(4).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Backup Server");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(4).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(4)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(4)
					.getClass(), PrimeMain.objectlist.get(4).getVisualImage());
		}


		// Database Server
		if ( PrimeMain.objectlist.get(5).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Database Server");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(5).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(5)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(5)
					.getClass(), PrimeMain.objectlist.get(5).getVisualImage());
		}


		// Mail Server
		if ( PrimeMain.objectlist.get(6).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Mail Server");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(6).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(6)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(6)
					.getClass(), PrimeMain.objectlist.get(6).getVisualImage());
		}


		// Firewall Server
		if ( PrimeMain.objectlist.get(7).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Firewall Server");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(7).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(7)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(7)
					.getClass(), PrimeMain.objectlist.get(7).getVisualImage());
		}


		// Proxy Server
		if ( PrimeMain.objectlist.get(8).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Proxy Server");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(8).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(8)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(8)
					.getClass(), PrimeMain.objectlist.get(8).getVisualImage());
		}


		// Printer Server
		if ( PrimeMain.objectlist.get(9).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Printer Server");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(9).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(9)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(9)
					.getClass(), PrimeMain.objectlist.get(9).getVisualImage());
		}


		// Scanner
		if ( PrimeMain.objectlist.get(10).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("Scanner");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(10).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(10)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(10)
					.getClass(), PrimeMain.objectlist.get(10).getVisualImage());
		}


		// Printer
		if ( PrimeMain.objectlist.get(11).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("Printer");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(11).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(11)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(11)
					.getClass(), PrimeMain.objectlist.get(11).getVisualImage());
		}


		// Fax
		if ( PrimeMain.objectlist.get(12).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("Fax");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(12).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(12)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(12)
					.getClass(), PrimeMain.objectlist.get(12).getVisualImage());
		}


		// MFP
		if ( PrimeMain.objectlist.get(13).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("MFP");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(13).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(13)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(13)
					.getClass(), PrimeMain.objectlist.get(13).getVisualImage());
		}


		// Network Printer
		if ( PrimeMain.objectlist.get(14).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Network Printer");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(14).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(14)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(14)
					.getClass(), PrimeMain.objectlist.get(14).getVisualImage());
		}


		// NetworkMultifunctionPrinter
		if ( PrimeMain.objectlist.get(15).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("NetworkMultifunctionPrinter");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(15).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(15)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(15)
					.getClass(), PrimeMain.objectlist.get(15).getVisualImage());
		}


		// Hub
		if ( PrimeMain.objectlist.get(16).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("Hub");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(16).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(16)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(16)
					.getClass(), PrimeMain.objectlist.get(16).getVisualImage());
		}


		// Switch
		if ( PrimeMain.objectlist.get(17).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("Switch");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(17).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(17)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(17)
					.getClass(), PrimeMain.objectlist.get(17).getVisualImage());
		}


		// Router
		if ( PrimeMain.objectlist.get(18).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("Router");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(18).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(18)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(18)
					.getClass(), PrimeMain.objectlist.get(18).getVisualImage());
		}


		// Modem
		if ( PrimeMain.objectlist.get(19).getVisualImage() == null )
		{
			// FIXME - MODEM ICON
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("Router");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(19).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(19)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(19)
					.getClass(), PrimeMain.objectlist.get(19).getVisualImage());
		}


		// Wireless Router
		if ( PrimeMain.objectlist.get(20).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Wireless Router");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(20).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(20)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(20)
					.getClass(), PrimeMain.objectlist.get(20).getVisualImage());
		}


		// Internet
		if ( PrimeMain.objectlist.get(21).getVisualImage() == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("Internet");

			// Sets the Objects Visual Image
			PrimeMain.objectlist.get(21).setVisualImage(objectIcon);

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(21)
					.getClass(), objectIcon);
		}
		else
		{
			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(PrimeMain.objectlist.get(21)
					.getClass(), PrimeMain.objectlist.get(21).getVisualImage());
		}
	}



	/**
	 * Sets up the systems hashmap of the hardware objects ImageIcon.
	 */
	private static void setupHardwareImageIcon()
	{
		if ( PrimeMain.objectImageIcons.get(CPU.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("CPU");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(CPU.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(Discdrive.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Optical-Drive");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(Discdrive.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(ExternalNetworksCard.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("ExternalNIC");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(ExternalNetworksCard.class,
					objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(GraphicsCard.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("GPU");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(GraphicsCard.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(HDD.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("Harddisc");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(HDD.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(InternalNetworksCard.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("NIC");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(InternalNetworksCard.class,
					objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(Motherboard.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Motherboard");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(Motherboard.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(Ram.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator.getImageIconObject("RAM");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(Ram.class, objectIcon);
		}
	}




	/**
	 * Sets up the systems hashmap of the software objects ImageIcon.
	 */
	private static void setupSoftwareImageIcon()
	{
		if ( PrimeMain.objectImageIcons.get(Antivirus.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Antivirus-Software");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(Antivirus.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(Backup.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Backup-Software");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(Backup.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(Database.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Database-Software");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(Database.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(Email.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Email-Software");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(Email.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(Firewall.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Firewall-Software");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(Firewall.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(OfficeSuite.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("OfficeSuite-Software");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(OfficeSuite.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(OperatingSystem.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("OperatingSystem-Software");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(OperatingSystem.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(Proxy.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Proxy-Software");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(Proxy.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(SecuritySuite.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("SecuritySuite-Software");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(SecuritySuite.class, objectIcon);
		}


		if ( PrimeMain.objectImageIcons.get(Webserver.class) == null )
		{
			// Gets the objects ImageIcon
			ImageIcon objectIcon = ImageLocator
					.getImageIconObject("Webserver-Software");

			// Places the ImageIcon into the systems hashmap of ImageIcons
			PrimeMain.objectImageIcons.put(Webserver.class, objectIcon);
		}
	}


}
