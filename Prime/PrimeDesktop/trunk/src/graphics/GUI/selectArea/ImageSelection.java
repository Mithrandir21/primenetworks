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
package graphics.GUI.selectArea;


import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.PrimeMain;

import java.awt.datatransfer.Transferable;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

import managment.CreateObjects;
import objects.Object;
import objects.peripheralObjects.GenericDevice;
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

			/**
			 * Step 1: If the gotten WidgetIcon is a GenericDevice and the type
			 * is "Unknown", the user has to select an Icon. If the type is
			 * anything other then "Unknown" the icon provided will be used.
			 * Step 2: If the gotten WidgetIcon is not a GenericDevice, a
			 * standard object will be created.
			 */

			if ( PrimeMain.currentCanvas != null )
			{
				if ( icon != null )
				{
					// Step 1.
					// If the device selected was a GenericDevice object
					if ( icon.getClassType().equals(GenericDevice.class) )
					{
						Object newObject = null;

						ImageIcon genericIcon = ImageLocator
								.getImageIconObject("Unknown");

						WidgetIcon genIcon = icon;

						// If it is an "Unknown" device
						if ( icon
								.getDescription()
								.equals(PrimeMain.texts
										.getString("selectAreaUnknownDeviceLabel")) )
						{
							// Ask the to select an Icon
							ImageIcon iconTemp = GraphicalFunctions
									.userIconSelection(null);

							// If the user selected a valid image
							if ( iconTemp != null )
							{
								genericIcon = iconTemp;
							}

							// Creates a new WidgetIcon with the user-selected
							// Icon
							genIcon = new WidgetIcon(genericIcon,
									icon.getClassType(), icon.getDescription());
						}

						// Creates the new Object
						newObject = CreateObjects.CreateObject(genIcon,
								PrimeMain.currentCanvas
										.getNumberOfWidgetsOnTheScene());

						WidgetObject widObj = new WidgetObject(
								PrimeMain.currentCanvas.getScene(), newObject,
								genIcon.getIconImage());

						return widObj;
					}
					// Step 2.
					else
					{
						Object newObject = CreateObjects.CreateObject(icon,
								PrimeMain.currentCanvas
										.getNumberOfWidgetsOnTheScene());

						return new WidgetObject(
								PrimeMain.currentCanvas.getScene(), newObject,
								icon.getIconImage());
					}
				}
			}

			return null;
		}
		return null;
	}
}