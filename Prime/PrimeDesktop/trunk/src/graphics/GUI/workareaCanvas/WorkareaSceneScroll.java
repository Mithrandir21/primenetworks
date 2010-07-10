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
package graphics.GUI.workareaCanvas;


import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import javax.swing.JScrollPane;

import managment.DesktopCanvasManagment;
import widgets.WorkareaCanvas;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class WorkareaSceneScroll extends JScrollPane
{
	private WorkareaCanvas canvas;

	/**
	 * A constructor that takes a WorkareaCanvas and sets it to the classes private WorkareaCanvas.
	 * 
	 * @param canvas
	 *            The WorkareaCanvas that is to be placed inside the tab.
	 */
	public WorkareaSceneScroll(WorkareaCanvas canvas)
	{
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

		this.canvas = canvas;
		ActionsAdder.makeWorkareaCanvasReady(canvas);
		createNewCanvas(canvas.getCanvasName());
	}



	/**
	 * A constructor that takes a WorkareaCanvas and sets it to the classes private WorkareaCanvas. This function calls
	 * a method that sets the given name as the name of the given WorkareaCanvas.
	 * 
	 * @param name
	 *            The name of the tab which will contain the given WorkareaCanvas.
	 * @param canvas
	 *            The WorkareaCanvas that is to be placed inside the tab.
	 */
	public WorkareaSceneScroll(String name, WorkareaCanvas canvas)
	{
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

		this.canvas = canvas;
		ActionsAdder.makeWorkareaCanvasReady(canvas);
		createNewCanvas(name);
	}


	/**
	 * Sets this JScrollPanes view to the classes WorkareaCanvas. It also adds the the given and adds the given
	 * WorkareaCanvas with the given name to the systems array of WorkareaCanvases.
	 * 
	 * @param name
	 *            The name of the canvas.
	 */
	public void createNewCanvas(String name)
	{
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

		this.setViewportView(canvas.getMyView());


		// Adds the canvas the array of currently active WorkareaCanvas
		DesktopCanvasManagment.addCanvas(canvas, name);
	}


	/**
	 * Gets the classes WorkareaCanvas.
	 * 
	 * @return the canvas
	 */
	public WorkareaCanvas getCanvas()
	{
		return canvas;
	}
}
