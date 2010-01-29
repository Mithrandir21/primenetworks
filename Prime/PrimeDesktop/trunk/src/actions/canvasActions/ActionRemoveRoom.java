package actions.canvasActions;


import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import managment.RoomManagment;

import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetRoom;
import widgets.WorkareaCanvas;


public class ActionRemoveRoom extends AbstractSystemAction implements
		SystemActionInterface
{
	// The canvas where the deletion is taking place
	private WorkareaCanvas canvas = null;

	// The widget room that is to be deleted
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
	public ActionRemoveRoom(String text, ImageIcon icon, WidgetRoom widRoom)
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
	public ActionRemoveRoom(String text, WidgetRoom widRoom)
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
		performAction(true);
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
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getPresentationName()
	 */
	@Override
	public String getPresentationName()
	{
		return PrimeMain1.texts.getString("actionDeleteRoomActionPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return PrimeMain1.texts.getString("actionDeleteRoomRedoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return PrimeMain1.texts.getString("actionDeleteRoomUndoPresNameText");
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
	 * @see logistical.AbstractSystemAction#undo()
	 */
	@Override
	public void undo() throws CannotUndoException
	{
		RoomManagment.addRoom(canvas, room);

		// Adds the actions supported by the WidgetRoom
		ActionsAdder.makeWidgetRoomReady(canvas, room);
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.SystemActionInterface#performAction()
	 */
	@Override
	public void performAction(boolean undoable)
	{
		// Sets the current canvas
		canvas = PrimeMain1.currentCanvas;

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

		canvas.cleanUp();

		if ( undoable )
		{
			canvas.addUndoableAction(this);
		}
	}
}
