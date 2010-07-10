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
package widgets;


import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import objects.Object;


/**
 * This class represents a JButton which contains a pointer to an {@link Object} class that the JButton will represent.
 * 
 * @author Bahram Malaekeh
 */
public class WidgetButton extends JButton
{
	/**
	 * This represents the class type what the icon represents. Like {@link objects.clientObjects.Desktop Desktop}.
	 */
	private Class<?> classType;

	/**
	 * This is the description of the object that is represents.
	 */
	private String description = "";

	/**
	 * The icon image.
	 */
	private Image img = null;


	/**
	 * The Object the Button represents.
	 */
	private Object object = null;




	/**
	 * A constructor that creates a JButton icon with the given ImageIcon and
	 * sets the classType of the represented {@link objects.Object Object}.
	 * 
	 * @param obj
	 *            The object represented {@link objects.Object Object}.
	 */
	public WidgetButton(Object obj)
	{
		super(obj.getVisualImage());
		object = obj;
		img = obj.getVisualImage().getImage();
		classType = obj.getClass();
		setText(obj.getObjectName());
	}


	/**
	 * A constructor that creates a JButton icon with the given ImageIcon and
	 * sets the classType of the represented {@link objects.Object Object}.
	 * 
	 * @param icon
	 *            The icon that is to represent the {@link objects.Object
	 *            Object}.
	 * @param objectType
	 *            The object class of the represented {@link objects.Object
	 *            Object}.
	 */
	public WidgetButton(ImageIcon icon, Class<?> objectType)
	{
		super(icon);
		img = icon.getImage();
		classType = objectType;
	}



	/**
	 * A constructor that creates a JButton icon with the given ImageIcon, sets
	 * the classType of the represented {@link objects.Object Object} and gives
	 * the JButton icon a name.
	 * 
	 * @param icon
	 *            The icon that is to represent the {@link objects.Object
	 *            Object}.
	 * @param objectType
	 *            The object class of the represented {@link objects.Object
	 *            Object}.
	 * @param name
	 *            The name of the JButton.
	 */
	public WidgetButton(ImageIcon icon, Class<?> objectType, String name)
	{
		super(icon);
		img = icon.getImage();
		classType = objectType;
		setText(name);
	}


	/**
	 * A constructor that creates a JButton icon with the given ImageIcon, sets
	 * the classType of the represented {@link objects.Object Object}, gives the
	 * JButton icon a name and sets a description of the object representer.
	 * 
	 * @param icon
	 *            The icon that is to represent the {@link objects.Object
	 *            Object}.
	 * @param objectType
	 *            The object class of the represented {@link objects.Object
	 *            Object}.
	 * @param name
	 *            The name of the JButton.
	 * @param desc
	 *            The description of the represented.
	 */
	public WidgetButton(ImageIcon icon, Class<?> objectType, String name,
			String desc)
	{
		super(icon);
		img = icon.getImage();
		classType = objectType;
		setText(name);
		description = desc;
	}


	// GETTERS




	/**
	 * @return The class of the {@link objects.Object Object} that the JButton
	 *         icon represents.
	 */
	public Class<?> getClassType()
	{
		return classType;
	}


	/**
	 * @return The description of the {@link objects.Object Object} that the
	 *         JButton icon represents.
	 */
	public String getDescription()
	{
		return description;
	}



	/**
	 * @return Returns the image that is shown as the JButton icon.
	 */
	public Image getIconImage()
	{
		return img;
	}


	/**
	 * @return Returns the Object represented by the button.
	 */
	public Object getObject()
	{
		return object;
	}
}
