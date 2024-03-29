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


import graphics.GUI.selectArea.ObjectSelection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;

import objects.peripheralObjects.GenericDevice;



/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class SystemFunctions
{

	/**
	 * This function copies the given file into the Custom folder inside the
	 * Image folder for the program.
	 * 
	 * @return Whether or not the new file was created.
	 */
	public static boolean copyIconIntoTheSystem(File iconFile)
	{
		// The file pointer
		File iconFolder = new File("./resource/images/custom");

		// Checks whether the folder exists
		if ( !(iconFolder.exists()) )
		{
			iconFolder.mkdir();
		}


		// The file pointer to the new icon file in the system folders
		File newFile = new File("./resource/images/custom" + File.separator
				+ iconFile.getName());


		// Tries to write the file out. Returns happen here
		try
		{
			FileChannel inChannel = new FileInputStream(iconFile).getChannel();
			FileChannel outChannel = new FileOutputStream(newFile).getChannel();
			try
			{
				inChannel.transferTo(0, inChannel.size(), outChannel);
			}
			finally
			{
				if ( inChannel != null )
					inChannel.close();
				if ( outChannel != null )
					outChannel.close();
			}

			// File has been written
			return true;
		}
		catch ( IOException e )
		{
			// File has not been written
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * This function checks whether or not the given Icon({@link File}) exists
	 * within the custom icon directory of this program.
	 */
	public boolean IconExistsInSystem(File file)
	{
		// The URI pointer to the folder that is to holds the
		URI uri = new File("./resource/images/custom").toURI();

		// The file pointer
		File iconFolder = new File(uri);


		// Checks whether the folder exists or if the pointer is a file
		if ( !(iconFolder.exists()) || iconFolder.isFile() )
		{
			return false;
		}


		// Goes through the entire custom images folders
		return fileExistsInGivenDirectory(iconFolder, file);
	}




	/**
	 * TODO - This function will try to backup the systems
	 */
	public boolean backupObject(File file)
	{




		return false;
	}



	/**
	 * Goes through all files and directories under a given folder.
	 */
	private boolean fileExistsInGivenDirectory(File dir, File file)
	{
		if ( dir.isDirectory() )
		{
			String[] children = dir.list();
			for ( int i = 0; i < children.length; i++ )
			{
				boolean found = false;

				found = fileExistsInGivenDirectory(new File(dir, children[i]),
						file);

				if ( found )
				{
					return found;
				}
			}
		}
		else
		{
			/**
			 * If the function gets here, it means that the "dir" pointer is now
			 * pointing to a file.
			 */
			if ( dir.getName().equals(file.getName()) )
			{
				return true;
			}
		}

		return false;
	}




	/**
	 * Adds a given {@link GenericDevice} to the list of Standard Devices.
	 */
	public static boolean addToStandardObjects(GenericDevice exObject)
	{
		PrimeMain.desktopProcLog
				.info("Adding a GenericDevice object to the list of Standard Devices.");

		if ( exObject != null && PrimeMain.objectlist != null )
		{
			PrimeMain.objectlist.add(exObject);

			ObjectSelection.reloadGenericDevicesIcons();
			return true;
		}


		PrimeMain.desktopProcLog
				.warning("Was not able to add a given GenericDevice to the Standard Devices list. - SystemFunctions - addToStandardObjects");
		return false;
	}
}
