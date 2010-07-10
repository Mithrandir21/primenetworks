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


import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.border.Border;

import managment.Settings;
import objects.Room;

import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;


/**
 * This class represents a {@link Widget} on a {@link WorkareaCanvas}. It will
 * be shown on a {@link WorkareaCanvas} as an empty rectangle with a black
 * rounded border.
 * 
 * @author Bahram Malaekeh
 */
public class WidgetRoom extends Widget
{
	// The room that the WidgetRoom will visually represent on a WorkareaCanvas
	Room room;

	// The border of the WidgetRoom
	Border border;



	/**
	 * A constructor for the class that take a {@link Scene} and the name of the {@link Room} as an argument.
	 * 
	 * @param scene
	 *            The {@link Scene} in a {@link WorkareaCanvas} that the {@link Widget} will be all to.
	 * @param name
	 *            THe name of the {@link Room} inside this class. Also the name
	 *            of the {@link Widget} on the scene.
	 */
	public WidgetRoom(Scene scene, String name)
	{
		super(scene);

		// The actual room object
		room = new Room(name);
	}



	/**
	 * A constructor for the class that take a {@link Scene} and the name of the {@link Room} as an argument.
	 * 
	 * @param scene
	 *            The {@link Scene} in a {@link WorkareaCanvas} that the {@link Widget} will be all to.
	 * @param room
	 *            The {@link Room} inside this class.
	 */
	public WidgetRoom(Scene scene, Room room)
	{
		super(scene);

		// The actual room object
		this.room = room;


		this.setPreferredLocation(room.getLocation());
		// this.setPreferredBounds(this.getBounds());
	}


	// GETTERS


	/**
	 * Gets the {@link Room} that this {@link Widget} represents on a {@link WorkareaCanvas}.
	 */
	public Room getRoom()
	{
		return room;
	}


	// SETTERS


	/**
	 * Sets the {@link Room} that this {@link Widget} represents on a {@link WorkareaCanvas}.
	 */
	public void setRoom(Room room)
	{
		this.room = room;
	}


	// CLASS METHODES
	/**
	 * Sets a preferred bounds that are specified relatively to the location of
	 * the widget.
	 * 
	 * @param preferredBounds
	 *            the preferred bounds; if null, then the preferred bounds are
	 *            unset
	 */
	public void setPreferredRoomBounds(Rectangle preferredBounds)
	{
		this.setPreferredBounds(preferredBounds);
		room.setBounds(preferredBounds);
	}



	/**
	 * Sets a preferred location of the top left corner of the widget.
	 * 
	 * @param preferredLocation
	 *            The preferred location; if null, then the preferred bounds are
	 *            unset
	 */
	public void setPreferredRoomLocation(Point preferredLocation)
	{
		this.setPreferredLocation(preferredLocation);
		room.setLocation(preferredLocation);
	}


	/**
	 * Returns whether the layer widget requires to repainted after
	 * revalidation.
	 * 
	 * @return always false
	 */
	@Override
	protected boolean isRepaintRequiredForRevalidating()
	{
		return true;
	}


	/**
	 * Returns whether a specified local location is part of the layer widget.
	 * 
	 * @param localLocation
	 *            the local location
	 * @return always false
	 */
	@Override
	public boolean isHitAt(Point localLocation)
	{
		if ( Settings.connecting == true )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
