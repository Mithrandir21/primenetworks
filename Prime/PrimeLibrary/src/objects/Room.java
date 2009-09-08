/**
 * 
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
		roomName = name;
	}




	// GETTERS

	/**
	 * Gets the name of the Room.
	 */
	public String getRoomName()
	{
		return roomName;
	}


	/**
	 * Gets the location of the Room, which will be the point of the top left corner of the room.
	 */
	public Point getLocation()
	{
		return location;
	}


	/**
	 * Gets the bounds of the room, represented by a {@link Rectangle}.
	 */
	public Rectangle getBounds()
	{
		return bounds;
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
