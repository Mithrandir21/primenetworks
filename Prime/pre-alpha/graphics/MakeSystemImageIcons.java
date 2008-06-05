/**
 * 
 */
package graphics;


import java.io.File;
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

	static int count = 0;

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
			System.out.println("BIatch");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File folder = new File(uri);

		visitAllFiles(folder);
		
		System.out.println();

		
		System.out.println(count);

	}


	private void traverse(String path)
	{
		System.out.println(path);

		File src = new File(path);

		System.out.println(src.getName());

		visitAllFiles(src);
	}




	// Process all files and directories under dir
	public static void visitAllDirsAndFiles(File dir)
	{

		if ( dir.isDirectory() )
		{
			System.out.println("K1");

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
		if ( dir.isDirectory() )
		{
			String[] children = dir.list();
			for ( int i = 0; i < children.length; i++ )
			{
				System.out.println(children[i]);
				visitAllFiles(new File(dir, children[i]));
			}
		}
		else
		{
			count++;
		}
	}
}
