package graphics.GUI.CustomFileFilters;

import java.io.File;

import javax.swing.filechooser.FileFilter;


/**
 * This .dat file filter is for exporting and importing of networks.
 * 
 * @author Bahram Malaekeh
 */
public class DATFilter extends FileFilter
{

	/*
	 * (non-Javadoc)
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

		String ext = ".dat";

		if ( path.endsWith(ext) )
		{
			return true;
		}

		return false;
	}

	@Override
	public String getDescription()
	{
		return ".dat";
	}

}
