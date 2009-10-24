package actions.canvasActions;


import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.providers.ActionsAdder;
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuRoom.JMenuWidgetRoom;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import managment.RoomManagment;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetRoom;
import widgets.WorkareaCanvas;


public class ActionCreateRoom extends AbstractSystemAction implements
		SystemActionInterface
{
	// The canvas where the deletion is taking place
	private WorkareaCanvas canvas = null;

	// The WidgetRoom that is created
	private WidgetRoom room = null;

	/**
	 * A constructor for the class that takes a string, the action name, and a
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionCreateRoom(String text, ImageIcon icon, WidgetRoom widRoom)
	{
		super(text, icon);
		room = widRoom;
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionCreateRoom(String text, WidgetRoom widRoom)
	{
		super(text);
		room = widRoom;
	}




	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		performAction();
	}



	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#canRedo()
	 */
	@Override
	public boolean canRedo()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#canUndo()
	 */
	@Override
	public boolean canUndo()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#die()
	 */
	@Override
	public void die()
	{
		canvas = null;
		room = null;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getPresentationName()
	 */
	@Override
	public String getPresentationName()
	{
		return "Create a Room";
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return "Re-Create a Room";
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return "Remove a newly created Room";
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#isSignificant()
	 */
	@Override
	public boolean isSignificant()
	{
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#redo()
	 */
	@Override
	public void redo() throws CannotRedoException
	{
		RoomManagment.addRoom(canvas, room);

		// Adds the actions supported by the WidgetRoom
		ActionsAdder.makeWidgetRoomReady(canvas, room);
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#undo()
	 */
	@Override
	public void undo() throws CannotUndoException
	{
		// Gets all the rooms on the current canvas
		List<Widget> list = canvas.getRoomLayer().getChildren();

		WidgetRoom temp = null;

		boolean found = false;


		WidgetRoom testingWidget = null;

		for ( Iterator<?> iter = list.iterator(); iter.hasNext(); )
		{
			testingWidget = (WidgetRoom) iter.next();

			if ( testingWidget.equals(room) )
			{
				found = true;
				temp = testingWidget;
			}
		}

		if ( found == true )
		{
			canvas.getRoomLayer().removeChild(temp);
		}
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.SystemActionInterface#performAction()
	 */
	@Override
	public void performAction()
	{
		if ( room != null )
		{
			canvas = PrimeMain1.currentCanvas;

			if ( room.getBounds().height < 50 && room.getBounds().width < 50 )
			{
				// Removes the WidgetRoom from the roomLayer
				room.getParentWidget().removeChild(room);
			}
			else
			{
				// Add the JMenuPopup action the WidgetRoom
				room.getActions().addAction(
						ActionFactory
								.createPopupMenuAction(new JMenuWidgetRoom(
										PrimeMain1.currentCanvas)));

				// Repaints roomLayer
				canvas.cleanUp();

				canvas.addUndoableAction(this);
			}
		}
	}
}
