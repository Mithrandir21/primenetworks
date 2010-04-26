package actions.canvasActions;

import graphics.PrimeMain1;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetRoom;
import widgets.WorkareaCanvas;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;



/**
 * @author bam
 *
 */
public class ActionDeleteAllRooms extends AbstractSystemAction
{
	
	
	/**
	 * A constructor for the class that takes a string, the action name, and an
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionDeleteAllRooms(String text, ImageIcon icon)
	{
		super(text, icon);
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionDeleteAllRooms(String text)
	{
		super(text);
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		WorkareaCanvas canvas = PrimeMain1.currentCanvas;

		if ( canvas != null )
		{
			String question = PrimeMain1.texts
					.getString("actionDeleteAllRoomsQuestions")
					+ "\n"
					+ PrimeMain1.texts.getString("thisCannotBeUndoneMsg");

			
			//Custom button text
			Object[] options = {PrimeMain1.texts.getString("yes"), 
					PrimeMain1.texts.getString("no")};
			

			int i = JOptionPane.showOptionDialog(null,question,
					PrimeMain1.texts.getString("actionDeleteAllRoomsName"),
				    JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options,
				    options[1]);
			
			// If the answer is yes
			if ( i == 0 )
			{
				// Gets all the WidgetRooms on the WorkareaCanvas
				WidgetRoom[] rooms = canvas.getNetworkWidgetRooms();
				
				
				for( int j = 0; j < rooms.length; j++)
				{
					canvas.getRoomLayer().removeChild(rooms[j]);
				}
				
				canvas.cleanUp();

				
				// Deletes all the undoable actions of the canvas
				canvas.getUndoManager().discardAllEdits();
			}

		}
	}

}
