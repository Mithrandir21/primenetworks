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
package logistical;


import javax.swing.undo.UndoManager;

import widgets.WorkareaCanvas;


/**
 * Contained in this class are methods for setting and retrieving a specific
 * {@link WorkareaCanvas} that is tied to the {@link UndoManager} that this
 * class extends.
 * No other functions are implemented in this class.
 * 
 * @author Bahram Malaekeh
 */
public class CanvasUndoManager extends UndoManager
{
	// The canvas which this manager belongs to
	private WorkareaCanvas canvas = null;

	/**
	 * This constructor sets the class {@link WorkareaCanvas} with the given
	 * {@link WorkareaCanvas}.
	 * 
	 * @param canvas
	 */
	public CanvasUndoManager(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}


	/**
	 * Returns the {@link WorkareaCanvas} that this class contains.
	 */
	public WorkareaCanvas getCanvas()
	{
		return this.canvas;
	}


	/**
	 * Sets the {@link WorkareaCanvas} that this class contains.
	 */
	public void setCanvas(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}
}
