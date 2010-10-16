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


import java.net.URL;

import javax.swing.ImageIcon;


/**
 * This class contains methods that get images, ImageIcons, from a given URL or
 * finds and returns an ImageIcon based on whether or not the given String is
 * the name of any of the ImageIcons in the systems main ImageIcons.
 * 
 * @author Bahram Malaekeh
 */
public class ImageLocator
{


	/**
	 * @return Returns an ImageIcon, or null if the path was invalid.
	 */
	public static ImageIcon createImageIcon(URL imgURL)
	{
		if ( imgURL != null )
		{
			return new ImageIcon(imgURL);
		}
		else
		{
			System.err.println("Error in the image handling function.");
			return null;
		}
	}




	/**
	 * Finds and returns an ImageIcon based on whether or not the given String
	 * is the name of any of the ImageIcons in the systems main ImageIcons.
	 */
	public static ImageIcon getImageIconObject(String name)
	{
		ImageIcon temp = null;

		for ( int i = 0; i < PrimeMain.images.size(); i++ )
		{
			temp = PrimeMain.images.get(i);

			if ( temp.getDescription().equals(name) )
			{
				return temp;
			}
		}

		return null;
	}
}
