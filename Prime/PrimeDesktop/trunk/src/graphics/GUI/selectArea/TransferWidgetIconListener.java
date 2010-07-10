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


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.TransferHandler;


/**
 * This is the classes mouseListener. It gets the source of the mouse event, gets the transferhandler for the source and
 * sets the action for the handler to exportAsDrag. Contains only a "mousePressed" method.
 * 
 * @author Bahram Malaekeh
 */
public class TransferWidgetIconListener extends MouseAdapter
{
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		JLabel comp = (JLabel) e.getSource();
		TransferHandler handler = comp.getTransferHandler();
		handler.exportAsDrag(comp, e, TransferHandler.COPY);
	}
}
