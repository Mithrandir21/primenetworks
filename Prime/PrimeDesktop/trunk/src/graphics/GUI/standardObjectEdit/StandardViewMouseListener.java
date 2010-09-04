/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.standardObjectEdit;


import exceptions.ObjectNotFoundException;
import graphics.PrimeMain;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import managment.ArrayManagment;
import objects.Object;
import widgets.WidgetIcon;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class StandardViewMouseListener extends MouseAdapter
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		WidgetIcon button = (WidgetIcon) e.getSource();

		Object newObject = null;

		try
		{
			// Gets the object in the given ArrayList with the given class
			newObject = ArrayManagment.getSpesificComponent(
					button.getClassType(), PrimeMain.objectlist);
		}
		catch ( ObjectNotFoundException ex )
		{
			System.out.println(PrimeMain.texts
					.getString("standardObjectsNotFoundInArrayMsg")
					+ " - "
					+ button.getName());
			ex.printStackTrace();
		}

		// Changes the object currently viewed
		PrimeMain.stdObjView.getSplitView().getObjView()
				.changeObjectView(newObject);
	}
}
