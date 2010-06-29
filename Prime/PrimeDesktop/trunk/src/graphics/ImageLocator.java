/**
 * 
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
			if ( imgURL != null )
			{
				System.err.println("Couldn't find file: " + imgURL.getPath());
			}
			else
			{
				System.err.println("Error in the image handling function.");
			}
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
