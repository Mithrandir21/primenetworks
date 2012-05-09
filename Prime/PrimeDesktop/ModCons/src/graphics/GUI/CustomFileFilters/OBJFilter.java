/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.CustomFileFilters;


import java.io.File;

import javax.swing.filechooser.FileFilter;

import managment.DesktopFileManagment;


/**
 * An {@link FileFilter} that is for standard object list. (See {@link DesktopFileManagment} importStandardObjects.
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
