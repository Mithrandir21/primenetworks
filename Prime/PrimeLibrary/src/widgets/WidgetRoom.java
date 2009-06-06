/**
 * 
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
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class WidgetRoom extends Widget
{
	// The room that the WidgetRoom will visually represent on a WorkareaCanvas
	Room room;

	// The border of the WidgetRoom
	Border border;



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param scene
	 * @param name
	 */
	public WidgetRoom(Scene scene, String name)
	{
		super(scene);

		// The actual room object
		room = new Room(name);
	}



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param scene
	 * @param room
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
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the room
	 */
	public Room getRoom()
	{
		return room;
	}


	// SETTERS


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param room
	 *            the room to set
	 */
	public void setRoom(Room room)
	{
		this.room = room;
	}


	// CLASS METHODES
	/**
	 * Sets a preferred bounds that are specified relatively to the location of the widget.
	 * 
	 * @param preferredBounds
	 *            the preferred bounds; if null, then the preferred bounds are unset
	 */
	public void setPreferredRoomBounds(Rectangle preferredBounds)
	{
		this.setPreferredBounds(preferredBounds);
		room.setBounds(preferredBounds);
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param location
	 */
	public void setPreferredRoomLocation(Point preferredLocation)
	{
		this.setPreferredLocation(preferredLocation);
		room.setLocation(preferredLocation);
	}


	/**
	 * Returns whether the layer widget requires to repainted after revalidation.
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
