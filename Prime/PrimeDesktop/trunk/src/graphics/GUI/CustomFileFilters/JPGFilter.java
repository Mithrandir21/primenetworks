/**
 * 
 */
package graphics.GUI.CustomFileFilters;


import java.io.File;

import javax.swing.filechooser.FileFilter;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class JPGFilter extends FileFilter
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
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

		String ext = ".jpg";

		if ( path.endsWith(ext) )
		{
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription()
	{
		return ".jpg";
	}

}
