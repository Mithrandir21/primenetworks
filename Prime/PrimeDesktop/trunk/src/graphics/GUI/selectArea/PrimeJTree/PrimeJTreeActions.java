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
package graphics.GUI.selectArea.PrimeJTree;


import graphics.PrimeMain;

import javax.swing.JTree;

import managment.DesktopFileManagment;
import widgets.WorkareaCanvas;


/**
 * This class contains actions a user can perform on the network models, which
 * are nodes in a {@link JTree}.
 * 
 * @author Bahram Malaekeh
 */
public class PrimeJTreeActions
{

	/**
	 * This method attempts to open the file inside the given {@link FileTreeNode}. This will should contain a
	 * {@link WorkareaCanvas}.
	 * If the {@link WorkareaCanvas} is already opened, the method will do
	 * nothing.
	 * 
	 * @param file
	 *            The {@link FileTreeNode} that should contains a File, which
	 *            will be attempted read and opened.
	 */
	public static void openFile(FileTreeNode file)
	{
		// Gets the name of the canvas depending on the file
		String canvasName = DesktopFileManagment.getCanvasName(file.getFile());

		// If a name was retrieved
		if ( canvasName != null )
		{
			// Whether or not
			boolean exists = PrimeMain.getWorkarea().existsTabWithGivenName(
					canvasName);

			// There does not exist a tab with that name already
			if ( exists == false )
			{
				DesktopFileManagment.openWorkareaCanvas(file.getFile());
			}
			else
			{
				PrimeMain.getWorkarea().bringCanvasToFront(canvasName);
			}
		}
	}


	/**
	 * The method calls on the DeleteWorkareaCanvas function in {@link DesktopFileManagment}.
	 * 
	 * @param file
	 */
	public static void deleteFile(FileTreeNode file, JTree tree)
	{
		DesktopFileManagment.deleteWorkareaCanvas(file);
	}



}
