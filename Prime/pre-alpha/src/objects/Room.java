/**
 * 
 */
package objects;


import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class Room implements Serializable
{
	// The name of the room
	String roomName;

	// The top left corner location of the room
	Point location;

	// The bounds of the room, the size
	Rectangle bounds;



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param name
	 */
	public Room(String name)
	{
		roomName = name;
	}




	// GETTERS

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the roomName
	 */
	public String getRoomName()
	{
		return roomName;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the location
	 */
	public Point getLocation()
	{
		return location;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the bounds
	 */
	public Rectangle getBounds()
	{
		return bounds;
	}


	// SETTERS

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param roomName
	 *            the roomName to set
	 */
	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Point location)
	{
		this.location = location;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param bounds
	 *            the bounds to set
	 */
	public void setBounds(Rectangle bounds)
	{
		this.bounds = bounds;
	}
}
