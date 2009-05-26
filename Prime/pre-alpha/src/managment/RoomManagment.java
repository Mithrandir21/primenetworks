/**
 * 
 */
package managment;


import graphics.PrimeMain1;
import graphics.Settings;
import graphics.GUI.RoomBorder;
import graphics.GUI.workareaCanvas.WorkareaCanvas;
import graphics.GUI.workareaCanvas.providers.WidgetRoomAdapterExtended;
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuRoom.JMenuWidgetRoom;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import logistical.checkLogic;
import objects.Room;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetRoom;
import widgetManipulation.Actions.MoveRoomAction;
import widgetManipulation.Actions.ResizeWidgetAction;


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
		// Gives the WidgetRoom the ability to be resized
		room.getActions().addAction(
				new ResizeWidgetAction(ActionFactory.createFreeResizeStategy(), ActionFactory
						.createDefaultResizeControlPointResolver(), ActionFactory.createDefaultResizeProvider()));

		// Gives the WidgetRoom the ability to be moved
		room.getActions().addAction(
				new MoveRoomAction(ActionFactory.createFreeMoveStrategy(), ActionFactory.createDefaultMoveProvider()));

		// Clicking ability
		room.getActions().addAction(new WidgetRoomAdapterExtended());
		
		room.getActions().addAction(
				ActionFactory.createPopupMenuAction(new JMenuWidgetRoom(PrimeMain1.currentCanvas)));
		

		TitledBorder border = javax.swing.BorderFactory.createTitledBorder(new RoomBorder(Color.BLACK), "RoomName");
		room.setBorder(border);
		// widget.setBorder(new RoomBorder(Color.BLACK));
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
	 *            The {@link Room} from which a {@link WidgetRoom} will be created and added to the given
	 *            {@link WorkareaCanvas}.
	 */
	public static void addRoom(WorkareaCanvas canvas, Room room)
	{

		WidgetRoom widRoom = createWidgetRoom(canvas, room);

		widRoom.setPreferredLocation(canvas.getScene().convertViewToScene(room.getLocation()));
		widRoom.setPreferredBounds(room.getBounds());

		canvas.getRoomLayer().addChild(widRoom);
	}



	/**
	 * Adds the given {@link WidgetRoom} to the {@link LayerWidget RoomLayer} of the given {@link WorkareaCanvas}.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} the {@link WidgetRoom} is added to.
	 * @param room
	 *            The {@link WidgetRoom} that is to be added to the given {@link WorkareaCanvas}.
	 */
	public static void addRoom(WorkareaCanvas canvas, WidgetRoom widRoom)
	{
		canvas.getRoomLayer().addChild(widRoom);
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
	 * @param widRoom
	 * @return
	 */
	public static boolean deleteWidgetRoom(WorkareaCanvas canvas, WidgetRoom widRoom)
	{
		// If the room layer of the given WorkareaCanvas contains the WidgetRoom
		if ( canvas.getRoomLayer().getChildren().contains(widRoom) )
		{
			canvas.getRoomLayer().removeChild(widRoom);
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
				return true;
			}
		}

		return false;
	}

	/**
	 * The RoomLayer in the given {@link WorkareaCanvas} is search for a {@link WidgetRoom} containing a {@link Room}
	 * equal to the given {@link Room}. If a {@link WidgetRoom} with the given name is found, it is removed.
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
				return true;
			}
		}

		return false;
	}




	/**
	 * Validates the given string as a {@link WidgetRoom} name.
	 * 
	 * @param room
	 * @return
	 */
	public static boolean checkNewRoomName(String newName)
	{
		return checkLogic.validateName(newName);
	}




	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public static void JPopupMenuesToggle()
	{
		// If the setting for the Room manipulation is set to true
		if ( Settings.roomsManipulation )
		{
			// Goes through
			for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
			{
				if ( PrimeMain1.canvases[i] != null )
				{
					// List of all the Rooms on the Scene
					List<Widget> l = PrimeMain1.canvases[i].getRoomLayer().getChildren();

					// Converts that list to an array of Objects
					java.lang.Object[] roomTemp = l.toArray();

					// Creates an array with the length of the all the children on the canvas
					WidgetRoom[] roomWidgets = new WidgetRoom[roomTemp.length];

					// Casts all the objects in the converted list to widgetobjects
					for ( int j = 0; j < roomWidgets.length; j++ )
					{
						roomWidgets[j] = (WidgetRoom) roomTemp[j];

						// Add the JMenuPopup action the WidgetRoom
						roomWidgets[j].getActions().addAction(
								ActionFactory.createPopupMenuAction(new JMenuWidgetRoom(PrimeMain1.canvases[i])));
					}
				}
			}
		}
		else
		{
			for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
			{
				if ( PrimeMain1.canvases[i] != null )
				{
					// List of all the Rooms on the Scene
					List<Widget> l = PrimeMain1.canvases[i].getRoomLayer().getChildren();

					// Converts that list to an array of Objects
					java.lang.Object[] roomTemp = l.toArray();

					// Creates an array with the length of the all the children on the canvas
					WidgetRoom[] roomWidgets = new WidgetRoom[roomTemp.length];

					// Casts all the objects in the converted list to widgetobjects
					for ( int j = 0; j < roomWidgets.length; j++ )
					{
						roomWidgets[j] = (WidgetRoom) roomTemp[j];

						// Gets the size of the action chain
						int size = roomWidgets[j].getActions().getActions().size();

						// If the size is 4
						if ( size == 4 )
						{
							// Removes the last action in the chain
							roomWidgets[j].getActions().removeAction(size - 1);
						}
					}
				}
			}
		}
	}
	
	
	
	/**
	 * Changes the title of the {@link TitledBorder} surrounding the {@link WidgetRoom} and the name of the {@link Room}
	 * inside the {@link WidgetRoom} object.
	 */
	public static void changeWidgetRoomName(Widget widget)
	{
		WidgetRoom room = (WidgetRoom) widget;
		// The user types in the new name of the room
		String roomName = JOptionPane.showInputDialog(null, "New name of the room?", "Name",
				JOptionPane.QUESTION_MESSAGE);

		// The user has pressed "Cancel"
		if ( roomName != null )
		{
			// If the name typed in by the user is validatet
			if ( RoomManagment.checkNewRoomName(roomName) )
			{
				// Sets the name of the Room object inside the WidgetRoom
				room.getRoom().setRoomName(roomName);

				// Creates a new TitledBorder with the given string
				TitledBorder border = javax.swing.BorderFactory.createTitledBorder(new RoomBorder(Color.BLACK), roomName);

				// Sets the newly created TitledBorder as the border for the the given WidgetRoom.
				room.setBorder(border);

				// Repaints the given WidgetRoom
				room.repaint();

				PrimeMain1.currentCanvas.cleanUp();
			}
		}
	}
}
