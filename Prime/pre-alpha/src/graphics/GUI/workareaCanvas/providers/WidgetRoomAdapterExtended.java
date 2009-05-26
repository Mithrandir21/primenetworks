/**
 * 
 */
package graphics.GUI.workareaCanvas.providers;


import graphics.PrimeMain1;
import graphics.Settings;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import managment.RoomManagment;

import org.netbeans.api.visual.action.WidgetAction.Adapter;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetRoom;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class WidgetRoomAdapterExtended extends Adapter
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mouseClicked(org. netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mouseClicked(Widget widget, WidgetMouseEvent event)
	{
		if ( Settings.roomsManipulation )
		{
			// If button1, which can be whatever depending on what the OS has chosen, is clicked.
			if ( event.getButton() == MouseEvent.BUTTON1 )
			{
				// If button1 is double clicked.
				if ( event.getClickCount() == 2 )
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
							RoomManagment.changeWidgetRoomName(room, roomName);

							PrimeMain1.currentCanvas.cleanUp();
						}
					}
				}
			}
			return State.CONSUMED;
		}

		return State.REJECTED;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mousePressed(org. netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mousePressed(Widget widget, WidgetMouseEvent event)
	{
		if ( event.getButton() == MouseEvent.BUTTON1 )
		{
			// System.out.println("Mouse Pressed");
			widget.bringToBack();
		}
		return State.REJECTED;
	}



	@Override
	public State mouseReleased(Widget widget, WidgetMouseEvent event)
	{
		return State.REJECTED;
	}
}
