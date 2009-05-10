/**
 * 
 */
package managment;


import graphics.GUI.RoomBorder;
import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.Color;

import javax.swing.border.TitledBorder;

import objects.Room;

import org.netbeans.api.visual.action.ActionFactory;

import widgetManipulation.WidgetRoom;
import widgetManipulation.Actions.MoveRoomAction;
import widgetManipulation.Actions.ResizeWidgetAction;


/**
 * The class contains various function for the management of {@link WidgetRoom WidgetRooms}.
 * This includes functions to create rooms, adds rooms and remove rooms.
 * 
 * @author Bahram Malaekeh
 */
public class RoomManagment
{
	
	
	/**
	 * This function adds all the actions a WidgetRoom 
	 * 
	 * @param room
	 * @return
	 */
	public static WidgetRoom addActionsToWidgetRoom(WidgetRoom room)
	{
		room.getActions().addAction(
				new ResizeWidgetAction(ActionFactory.createFreeResizeStategy(), ActionFactory
						.createDefaultResizeControlPointResolver(), ActionFactory.createDefaultResizeProvider()));

		room.getActions().addAction(
				new MoveRoomAction(ActionFactory.createFreeMoveStrategy(), ActionFactory.createDefaultMoveProvider()));
		// asdasd
		TitledBorder border = javax.swing.BorderFactory.createTitledBorder(new RoomBorder(Color.BLACK), "Testing");
		room.setBorder(border);
		// widget.setBorder(new RoomBorder(Color.BLACK));
		// widget.setBorder (scene.getLookFeel().getMiniBorder(ObjectState.createNormal().deriveSelected (true)));

		room.bringToBack();

		return room;
	}





	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param room
	 */
	public static void addRoom(WorkareaCanvas canvas, Room room)
	{
		WidgetRoom widRoom = createWidgetRoom(canvas, room);

		widRoom.setPreferredLocation(canvas.getScene().convertViewToScene(room.getLocation()));
		widRoom.setPreferredBounds(room.getBounds());

		canvas.getRoomLayer().addChild(widRoom);
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param room
	 */
	public static void addRoom(WorkareaCanvas canvas, WidgetRoom widRoom)
	{
		canvas.getRoomLayer().addChild(widRoom);
	}





	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param name
	 * @return
	 */
	public static WidgetRoom createWidgetRoom(WorkareaCanvas canvas, String name)
	{
		WidgetRoom widRoom = new WidgetRoom(canvas.getScene(), name);

		widRoom = RoomManagment.addActionsToWidgetRoom(widRoom);

		return widRoom;
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param room
	 * @return
	 */
	public static WidgetRoom createWidgetRoom(WorkareaCanvas canvas, Room room)
	{
		WidgetRoom widRoom = new WidgetRoom(canvas.getScene(), room);

		widRoom = RoomManagment.addActionsToWidgetRoom(widRoom);

		return widRoom;
	}
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param widRoom
	 * @return
	 */
	public static boolean deleteWidgetRoom(WorkareaCanvas canvas, WidgetRoom widRoom)
	{
		return false;
	}
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param name
	 * @return
	 */
	public static boolean deleteWidgetRoom(WorkareaCanvas canvas, String name)
	{
		return false;
	}
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param room
	 * @return
	 */
	public static boolean deleteWidgetRoom(WorkareaCanvas canvas, Room room)
	{
		return false;
	}
}
