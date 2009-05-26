/**
 * 
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuRoom;


import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import widgetManipulation.WidgetRoom;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
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
	public WorkareaWidgetRoomActionListener(WorkareaCanvas canvas, WidgetRoom room)
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
				System.out.println(room.getRoom().getRoomName() + "delete room");
			}
			else if( actionName.equals("RenameRoom") )
			{
				System.out.println(room.getRoom().getRoomName() + "rename room");
			}
		}


		canvas.cleanUp();
	}

}
