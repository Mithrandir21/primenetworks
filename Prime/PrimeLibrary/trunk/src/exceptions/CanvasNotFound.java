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
package exceptions;


import widgets.WorkareaCanvas;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class CanvasNotFound extends Exception
{
	/**
	 * The {@link WorkareaCanvas} that was not found.
	 */
	WorkareaCanvas canvas = null;


	/**
	 * A constructor for this class.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} that was not found.
	 */
	public CanvasNotFound(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}



	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "The WorkareaCanvas, " + this.canvas.getCanvasName()
				+ ", was not found.";

		return output;
	}
}
