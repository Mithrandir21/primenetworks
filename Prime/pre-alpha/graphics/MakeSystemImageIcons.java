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
 * 
 */
public class MakeSystemImageIcons
{
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

		String[] names = folder.list();
		
		for(int i = 0;i<folder.list().length; i++)
		{
			System.out.println(names[i]);
		}
		
	}
}
