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
package objects;


import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;


/**
 * This class represents a Room. A room has only a name, location and bound.
 * 
 * @author Bahram Malaekeh
 */
public class Room implements Serializable
{
	// The name of the room
	private String roomName;

	// The top left corner location of the room
	private Point location;

	// The bounds of the room, the size
	private Rectangle bounds;



	/**
	 * A constructor that takes the name of the Room as an argument.
	 * 
	 * @param name
	 *            The name of the Room.
	 */
	public Room(String name)
	{
		this.roomName = name;
	}




	// GETTERS

	/**
	 * Gets the name of the Room.
	 */
	public String getRoomName()
	{
		return this.roomName;
	}


	/**
	 * Gets the location of the Room, which will be the point of the top left corner of the room.
	 */
	public Point getLocation()
	{
		return this.location;
	}


	/**
	 * Gets the bounds of the room, represented by a {@link Rectangle}.
	 */
	public Rectangle getBounds()
	{
		return this.bounds;
	}


	// SETTERS

	/**
	 * Sets the name of the Room.
	 */
	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}


	/**
	 * Sets the location of the Room, which will be the point of the top left corner of the room.
	 */
	public void setLocation(Point location)
	{
		this.location = location;
	}


	/**
	 * Sets the bounds of the room, represented by a {@link Rectangle}.
	 */
	public void setBounds(Rectangle bounds)
	{
		this.bounds = bounds;
	}
}
