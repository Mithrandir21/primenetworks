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
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuCanvas;



import graphics.PrimeMain;

import java.awt.Point;

import javax.swing.JPopupMenu;

import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.widget.Widget;

import widgets.WorkareaCanvas;


/**
 * This class provides a JPopupMenu by implementing the PopupMenuProvider.
 * 
 * @author Bahram Malaekeh
 */
public class JMenuWorkareaCanvas implements PopupMenuProvider
{
	/**
	 * The canvas that the menu will be shown on.
	 */
	private WorkareaCanvas canvas;


	/**
	 * A constructor for this class.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} this PopupMenuProvider belongs to.
	 */
	public JMenuWorkareaCanvas(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * org.netbeans.api.visual.action.PopupMenuProvider#getPopupMenu(org.netbeans
	 * .api.visual.widget.Widget, java.awt.Point)
	 */
	@Override
	public JPopupMenu getPopupMenu(Widget widget, Point localLocation)
	{
		// Sets the current WidgetObject for the systems current canvas
		PrimeMain.currentCanvas.setCurrentWidgetObject(null);

		JPopupCanvasMenu provider = new JPopupCanvasMenu(canvas);


		return provider.createPopupMenu(localLocation);
	}

}
