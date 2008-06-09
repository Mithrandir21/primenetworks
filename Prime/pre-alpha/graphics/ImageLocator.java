/**
 * 
 */
package graphics;


import java.net.URL;

import javax.swing.ImageIcon;


/**
 * TODO - Description NEEDED!
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
			System.err.println("Couldn't find file: " + imgURL.getPath());
			return null;
		}
	}
	
	
	
	
	public static ImageIcon getImageIconObject(String name)
	{
		ImageIcon temp = null;
		
		for(int i = 0;i<PrimeMain1.images.size();i++)
		{
			temp = PrimeMain1.images.get(i);
			
			if(temp.getDescription().equals(name))
			{
				return temp;
			}
		}
		
		return null;
	}
}
