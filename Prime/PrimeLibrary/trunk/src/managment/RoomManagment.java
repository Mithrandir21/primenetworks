/**
 * 
 */
package managment;



import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import logistical.checkLogic;
import objects.Room;

import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.RoomBorder;
import widgets.WidgetRoom;
import widgets.WorkareaCanvas;


/**
 * The class contains various function for the management of {@link WidgetRoom WidgetRooms}. This includes functions to
 * create rooms, adds rooms and remove rooms.
 * 
 * @author Bahram Malaekeh
 */
public class RoomManagment
{
	/**
	 * This function adds all the actions a WidgetRoom has, like the ability to resize and move.
	 * 
	 * @param room
	 *            The {@link WidgetRoom} that will have actions added to it an returned.
	 * @return The {@link WidgetRoom} with added actions.
	 */
	public static WidgetRoom addActionsToWidgetRoom(WidgetRoom room)
	{
		// TitledBorder border = javax.swing.BorderFactory.createTitledBorder(new RoomBorder(Color.BLACK), "Room Name");
		// room.setBorder(border);

		RoomBorder border = new RoomBorder(Color.BLACK);

		room
				.setBorder(BorderFactory.createSwingBorder(room.getScene(),
						border));
		// room.setBorder(new RoomBorder(Color.BLACK));
		// widget.setBorder (scene.getLookFeel().getMiniBorder(ObjectState.createNormal().deriveSelected (true)));

		room.bringToBack();

		return room;
	}





	/**
	 * Creates a {@link WidgetRoom} with the given {@link Room} and adds that to the {@link LayerWidget RoomLayer} of
	 * the given {@link WorkareaCanvas}.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} the {@link WidgetRoom} is added to.
	 * @param room
	 *            The {@link Room} from which a {@link WidgetRoom} will be created and added to the given {@link WorkareaCanvas}.
	 */
	public static WidgetRoom addRoom(WorkareaCanvas canvas, Room room)
	{

		WidgetRoom widRoom = createWidgetRoom(canvas, room);

		widRoom.setPreferredLocation(canvas.getScene().convertViewToScene(
				room.getLocation()));
		widRoom.setPreferredBounds(room.getBounds());


		canvas.getRoomLayer().addChild(widRoom);

		canvas.setSaved(false);
		canvas.setChanged(true);

		return widRoom;
	}



	/**
	 * Adds the given {@link WidgetRoom} to the {@link LayerWidget RoomLayer} of the given {@link WorkareaCanvas}.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} the {@link WidgetRoom} is added to.
	 * @param widRoom
	 *            The {@link WidgetRoom} that is to be added to the given {@link WorkareaCanvas}.
	 */
	public static void addRoom(WorkareaCanvas canvas, WidgetRoom widRoom)
	{
		canvas.getRoomLayer().addChild(widRoom);

		canvas.setSaved(false);
		canvas.setChanged(true);
	}





	/**
	 * Creates and returns a new {@link WidgetRoom} object, with all actions.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} where the {@link WidgetRoom} is to be added.
	 * @param name
	 *            The name of the {@link Room} that is created inside the {@link WidgetRoom} object.
	 * @return A newly created {@link WidgetRoom} with all necessary actions attached.
	 */
	public static WidgetRoom createWidgetRoom(WorkareaCanvas canvas, String name)
	{
		WidgetRoom widRoom = new WidgetRoom(canvas.getScene(), name);

		widRoom = RoomManagment.addActionsToWidgetRoom(widRoom);

		return widRoom;
	}



	/**
	 * Creates and returns a new {@link WidgetRoom} object, with all actions.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} where the {@link WidgetRoom} is to be added.
	 * @param room
	 *            The {@link Room} that is placed inside the {@link WidgetRoom} object.
	 * @return A newly created {@link WidgetRoom} with all necessary actions attached.
	 */
	public static WidgetRoom createWidgetRoom(WorkareaCanvas canvas, Room room)
	{
		WidgetRoom widRoom = new WidgetRoom(canvas.getScene(), room);

		widRoom = RoomManagment.addActionsToWidgetRoom(widRoom);

		return widRoom;
	}


	/**
	 * The given {@link WidgetRoom} is, if found, removed from the RoomLayer of the given {@link WorkareaCanvas}.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} where the a {@link WidgetRoom} is searched for and removed if found.
	 * @param widRoom
	 *            The {@link WidgetRoom} which will be removed if found.
	 */
	public static boolean deleteWidgetRoom(WorkareaCanvas canvas,
			WidgetRoom widRoom)
	{
		// If the room layer of the given WorkareaCanvas contains the WidgetRoom
		if ( canvas.getRoomLayer().getChildren().contains(widRoom) )
		{
			canvas.getRoomLayer().removeChild(widRoom);

			canvas.setSaved(false);
			canvas.setChanged(true);

			return true;
		}

		return false;
	}


	/**
	 * The RoomLayer in the given {@link WorkareaCanvas} is search for a {@link WidgetRoom} that has a name equal to the
	 * given String name. If a {@link WidgetRoom} with the given name is found, it is removed.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} where the a {@link WidgetRoom} is searched for and removed if found.
	 * @param name
	 *            The name of the {@link Room} which will be inside a {@link WidgetRoom}, which will be removed if
	 *            found.
	 * @return True if the {@link WidgetRoom} is found and removed. False if no {@link WidgetRoom} is found with the
	 *         given name.
	 */
	public static boolean deleteWidgetRoom(WorkareaCanvas canvas, String name)
	{
		// The list of all the children of the the roomlayer of the given WorkareaCanvas
		List<Widget> list = canvas.getRoomLayer().getChildren();

		// A testing WidgetRoom
		WidgetRoom testingWidget = null;

		// Goes through all the children in the list
		for ( Iterator<?> iter = list.iterator(); iter.hasNext(); )
		{
			// Casts the Widget to a WidgetRoom
			testingWidget = (WidgetRoom) iter.next();

			// If the name of the Room object within the WidgetRoom is equal to the String name given
			if ( testingWidget.getRoom().getRoomName().equals(name) )
			{
				canvas.getRoomLayer().removeChild(testingWidget);

				canvas.setSaved(false);
				canvas.setChanged(true);

				return true;
			}
		}

		return false;
	}

	/**
	 * The RoomLayer in the given {@link WorkareaCanvas} is search for a {@link WidgetRoom} containing a {@link Room} equal to the
	 * given {@link Room}. If a {@link WidgetRoom} with the given name is found, it is removed.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} where the a {@link WidgetRoom} is searched for and removed if found.
	 * @param room
	 *            The {@link Room} which will be inside a {@link WidgetRoom}, which will be removed if found.
	 * @return True if the {@link WidgetRoom} is found and removed. False if no {@link WidgetRoom} is found containing
	 *         the given {@link Room}.
	 */
	public static boolean deleteWidgetRoom(WorkareaCanvas canvas, Room room)
	{
		// The list of all the children of the the roomlayer of the given WorkareaCanvas
		List<Widget> list = canvas.getRoomLayer().getChildren();

		// A testing WidgetRoom
		WidgetRoom testingWidget = null;

		// Goes through all the children in the list
		for ( Iterator<?> iter = list.iterator(); iter.hasNext(); )
		{
			// Casts the Widget to a WidgetRoom
			testingWidget = (WidgetRoom) iter.next();

			// If the Room object within the WidgetRoom is equal to the given Room
			if ( testingWidget.getRoom().equals(room) )
			{
				canvas.getRoomLayer().removeChild(testingWidget);

				canvas.setSaved(false);
				canvas.setChanged(true);

				return true;
			}
		}

		return false;
	}




	/**
	 * Validates the given string as a {@link WidgetRoom} name.
	 * 
	 * @param newName
	 *            The New name of the WidgetRoom
	 */
	public static boolean checkNewRoomName(String newName)
	{
		return checkLogic.validateName(newName);
	}
}
