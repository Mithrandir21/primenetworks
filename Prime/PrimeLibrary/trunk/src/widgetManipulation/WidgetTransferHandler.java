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
package widgetManipulation;


import java.awt.datatransfer.DataFlavor;

import javax.swing.TransferHandler;

import widgets.WidgetObject;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class WidgetTransferHandler extends TransferHandler
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.TransferHandler#canImport(javax.swing.TransferHandler.
	 * TransferSupport)
	 */
	@Override
	public boolean canImport(TransferSupport support)
	{
		DataFlavor[] flavors = support.getDataFlavors();
		if ( flavors[0].equals(new DataFlavor(WidgetObject.class,
				"Widget Object")) )
		{
			return true;
		}

		return false;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.TransferHandler#importData(javax.swing.TransferHandler.
	 * TransferSupport)
	 */
	@Override
	public boolean importData(TransferSupport support)
	{
		return true;
	}



}
