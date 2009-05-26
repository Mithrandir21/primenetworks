/**
 * 
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuRoom;


import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import managment.RoomManagment;
import widgetManipulation.WidgetRoom;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class WorkareaWidgetRoomActionListener implements ActionListener
{
	/**
	 * 
	 */
	private WorkareaCanvas canvas;


	/**
	 * 
	 */
	private WidgetRoom room;



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param canvas
	 */
	public WorkareaWidgetRoomActionListener(WorkareaCanvas canvas,
			WidgetRoom room)
	{
		this.canvas = canvas;
		this.room = room;
	}



	@Override
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem action = (JMenuItem) e.getSource();


		String actionName = "";

		if ( action.getActionCommand() != null )
		{
			actionName = action.getActionCommand();
		}

		if ( !actionName.equals("") )
		{
			if ( actionName.equals("DeleteRoom") )
			{
				// Calls the remove WidgetRoom function
				RoomManagment.deleteWidgetRoom(canvas, room);
			}
			else if ( actionName.equals("RenameRoom") )
			{
				// Call the function that asks the user for a new name and then
				// sets that name as the name for the given room
				RoomManagment.changeWidgetRoomName(room);
			}
		}


		canvas.cleanUp();
	}

}
