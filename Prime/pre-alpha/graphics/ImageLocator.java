/**
 * 
 */
package graphics;


import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ImageLocator
{
	/** Returns an ImageIcon, or null if the path was invalid. */
	/**
	 * @return Returns an Image, or null if the path was invalid.
	 */
	public static Image createImage(URL imgURL)
	{
		if ( imgURL != null )
		{
			return new ImageIcon(imgURL).getImage();
		}
		else
		{
			System.err.println("Couldn't find file: " + imgURL.getPath());
			return null;
		}
	}


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
			System.err.println("Couldn't find file: " + imgURL.getPath());
			return null;
		}
	}
}
