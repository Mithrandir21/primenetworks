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
package graphics.GUI.properties;



import javax.swing.JScrollPane;

import objects.Object;
import widgets.WorkareaCanvas;


/**
 * An extension of the JScrollPane class that is used to show the properties of any chosen canvas or object.
 * 
 * @author Bahram Malaekeh
 */
public class ObjectScrollProperties extends JScrollPane
{
	private ObjectProperties objProp;

	public ObjectScrollProperties()
	{
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
	}



	/**
	 * Sets the view inside the JScrollPane to a new ObjectProperties with the given object as a parameter.
	 */
	public void newObjectSelectedPropertiesTab(Object object)
	{
		objProp = new ObjectProperties(object);

		this.setViewportView(objProp);
	}



	/**
	 * Sets the view inside the JScrollPane to a new ObjectProperties with the given canvas as a parameter.
	 */
	public void newObjectSelectedPropertiesTab(WorkareaCanvas canvas)
	{
		objProp = new ObjectProperties(canvas);

		this.setViewportView(objProp);
	}


	/**
	 * @return Returns the ObjectPropertie Panel that contains the actual fields and buttons.
	 */
	public ObjectProperties getObjectPropertiePanel()
	{
		return objProp;
	}
}
