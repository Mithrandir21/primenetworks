package graphics.GUI.CustomFileFilters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import managment.DesktopFileManagment;


/**
 * An {@link FileFilter} that is for standard object list. (See
 * {@link DesktopFileManagment} importStandardObjects.
 * 
 * @author Bahram Malaekeh
 */
public class OBJFilter extends FileFilter
{

	@Override
	public boolean accept(File file)
	{
		// If the file is a directory
		if ( file.isDirectory() )
		{
			return true;
		}


		// Gets the absolute path of the file
		String path = file.getAbsolutePath();

		String ext = ".obj";

		if ( path.endsWith(ext) )
		{
			return true;
		}

		return false;
	}

	@Override
	public String getDescription()
	{
		// TODO Auto-generated method stub
		return ".obj";
	}

}
