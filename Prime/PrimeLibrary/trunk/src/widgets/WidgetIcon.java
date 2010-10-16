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
package widgets;


import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;



/**
 * The WidgetIcon class represents an JLabel based icon that represents an
 * {@link objects.Object
 * Object} within the system. This is a dragable object that can be dropped
 * within the {@link widgets.WorkareaCanvas Canvas}.
 * 
 * @author Bahram Malaekeh
 */
public class WidgetIcon extends JLabel
{
	/**
	 * This represents the class type what the icon represents. Like
	 * {@link objects.clientObjects.Desktop Desktop}.
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
	 * A constructor that creates a JLabel icon with the given ImageIcon and
	 * sets the classType of
	 * the represented {@link objects.Object Object}. The constructor also adds
	 * a mouseListener and
	 * sets a transferhandler for the JLabel icon.
	 * 
	 * @param icon
	 *            The icon that is to represent the {@link objects.Object
	 *            Object}.
	 * @param objectType
	 *            The object class of the represented {@link objects.Object
	 *            Object}.
	 */
	public WidgetIcon(ImageIcon icon, Class<?> objectType)
	{
		super(icon);
		this.img = icon.getImage();
		this.classType = objectType;
	}



	/**
	 * A constructor that creates a JLabel icon with the given ImageIcon, sets
	 * the classType of the
	 * represented {@link objects.Object Object} and gives the JLabel icon a
	 * name. The constructor
	 * also adds a mouseListener and sets a transferhandler for the JLabel icon.
	 * 
	 * @param icon
	 *            The icon that is to represent the {@link objects.Object
	 *            Object}.
	 * @param objectType
	 *            The object class of the represented {@link objects.Object
	 *            Object}.
	 * @param name
	 *            The name of the JLabel.
	 */
	public WidgetIcon(ImageIcon icon, Class<?> objectType, String name)
	{
		super(icon);
		this.img = icon.getImage();
		this.classType = objectType;
		this.setText(name);
	}


	/**
	 * A constructor that creates a JLabel icon with the given ImageIcon, sets
	 * the classType of the
	 * represented {@link objects.Object Object}, gives the JLabel icon a name
	 * and sets a
	 * description of the object representer. The constructor also adds a
	 * mouseListener and sets a
	 * transferhandler for the JLabel icon.
	 * 
	 * @param icon
	 *            The icon that is to represent the {@link objects.Object
	 *            Object}.
	 * @param objectType
	 *            The object class of the represented {@link objects.Object
	 *            Object}.
	 * @param name
	 *            The name of the JLabel.
	 * @param desc
	 *            The description of the represented
	 */
	public WidgetIcon(ImageIcon icon, Class<?> objectType, String name,
			String desc)
	{
		super(icon);
		this.img = icon.getImage();
		this.classType = objectType;
		this.setText(name);
		this.description = desc;
	}


	// GETTERS

	/**
	 * @return The class of the {@link objects.Object Object} that the JLabel
	 *         icon represents.
	 */
	public Class<?> getClassType()
	{
		return this.classType;
	}


	/**
	 * @return The description of the {@link objects.Object Object} that the
	 *         JLabel icon represents.
	 */
	public String getDescription()
	{
		return this.description;
	}



	/**
	 * @return Returns the image that is shown as the JLabel icon.
	 */
	public Image getIconImage()
	{
		return this.img;
	}
}
