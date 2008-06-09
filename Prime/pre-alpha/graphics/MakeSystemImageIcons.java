/**
 * 
 */
package graphics;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class MakeSystemImageIcons
{

//	static int count = 0;

	public void getImageIcons()
	{
		URL url = this.getClass().getResource("images");
		URI uri = null;
		
		try
		{
			String t = url.toString();
			uri = new URI(t);
		}
		catch ( URISyntaxException e )
		{
			e.printStackTrace();
		}
		
		File folder = new File(uri);

		visitAllFiles(folder);
		
	}
	

	// Process all files and directories under dir
	public static void visitAllDirsAndFiles(File dir)
	{

		if ( dir.isDirectory() )
		{

			String[] children = dir.list();
			for ( int i = 0; i < children.length; i++ )
			{
				visitAllDirsAndFiles(new File(dir, children[i]));
			}
		}
	}

	// Process only directories under dir
	public static void visitAllDirs(File dir)
	{
		if ( dir.isDirectory() )
		{
			String[] children = dir.list();
			for ( int i = 0; i < children.length; i++ )
			{
				System.out.println(children[i]);

				visitAllDirs(new File(dir, children[i]));
			}
		}
	}

	// Process only files under dir
	public static void visitAllFiles(File dir)
	{
//		try
//		{
//			System.out.println("Does this." + count + " " + dir.getCanonicalPath());
//		}
//		catch ( IOException e )
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
			
			if ( name.contains(".png") || name.contains(".jpg") || name.contains(".gif") )
			{
				try
				{
					name = name.substring(0, (name.length()-4));
					
					ImageIcon toBeAdded = ImageLocator.createImageIcon(dir.toURI().toURL());
					toBeAdded.setDescription(name);
					
					PrimeMain1.images.add(toBeAdded);
				}
				catch ( MalformedURLException e )
				{
					e.printStackTrace();
				}
			}
		}
	}
}
