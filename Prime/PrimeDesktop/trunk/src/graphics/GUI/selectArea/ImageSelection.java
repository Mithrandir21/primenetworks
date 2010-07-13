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
package graphics.GUI.selectArea;


import graphics.PrimeMain;

import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import managment.CreateObjects;

import objects.Object;
import widgets.WidgetIcon;
import widgets.WidgetObject;


/**
 * This class is where the {@link WidgetObject} is created when an image is
 * clicked and dragged out of the selection area.
 * 
 * @author Bahram Malaekeh
 */
public class ImageSelection extends TransferHandler
{

	/*
	 * (non-Javadoc)
	 * @see javax.swing.TransferHandler#getSourceActions(javax.swing.JComponent)
	 */
	@Override
	public int getSourceActions(JComponent c)
	{
		return TransferHandler.COPY;
	}



	/*
	 * (non-Javadoc)
	 * @see
	 * javax.swing.TransferHandler#createTransferable(javax.swing.JComponent)
	 */
	@Override
	public Transferable createTransferable(JComponent comp)
	{
		if ( comp instanceof WidgetIcon )
		{
			WidgetIcon icon = (WidgetIcon) comp;

			if ( PrimeMain.currentCanvas != null )
			{
				Object newObject = new CreateObjects().CreateObject(icon,
						PrimeMain.currentCanvas.getNumberOfWidgetsOnTheScene());

				return new WidgetObject(PrimeMain.currentCanvas.getScene(),
						newObject, icon.getIconImage());
			}

			return null;
		}
		return null;
	}

}