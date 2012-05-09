package actions.canvasActions;


import graphics.GraphicalFunctions;
import graphics.PrimeMain;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


public class ActionChangeWidgetIcon extends AbstractSystemAction implements
		SystemActionInterface
{
	// The canvas where the change is taking place
	private WorkareaCanvas canvas = null;

	// The widget object where the name is to be changed
	private WidgetObject widObject = null;

	// The previous object image
	private ImageIcon oldIcon = null;

	// The new object image
	private ImageIcon newIcon = null;


	/**
	 * A constructor for the class that takes a string, the action name, and an
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionChangeWidgetIcon(String text, ImageIcon icon,
			WidgetObject widgetObject, WorkareaCanvas canvas)
	{
		super(text, icon);
		this.canvas = canvas;
		widObject = widgetObject;
		oldIcon = widObject.getObject().getVisualImage();
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionChangeWidgetIcon(String text, WidgetObject widgetObject,
			WorkareaCanvas canvas)
	{
		super(text);
		this.canvas = canvas;
		widObject = widgetObject;
		oldIcon = widObject.getObject().getVisualImage();
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
	 * @see
	 * logistical.AbstractSystemAction#addEdit(javax.swing.undo.UndoableEdit)
	 */
	@Override
	public boolean addEdit(UndoableEdit anEdit)
	{
		return false;
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
		this.canvas = null;
		this.widObject = null;
		this.oldIcon = null;
		this.newIcon = null;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getPresentationName()
	 */
	@Override
	public String getPresentationName()
	{
		return PrimeMain.texts
				.getString("actionChangeWidgetIconActionPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return PrimeMain.texts
				.getString("actionChangeWidgetIconRedoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return PrimeMain.texts
				.getString("actionChangeWidgetIconUndoPresNameText");
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
		widObject.setWidgetImage(newIcon);
		canvas.cleanUp();
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#undo()
	 */
	@Override
	public void undo() throws CannotUndoException
	{
		widObject.setWidgetImage(oldIcon);
		canvas.cleanUp();
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.SystemActionInterface#performAction(boolean)
	 */
	@Override
	public void performAction(boolean undoable)
	{
		ImageIcon image = GraphicalFunctions.userIconSelection(null);

		if ( image != null )
		{
			newIcon = image;
			widObject.setWidgetImage(newIcon);
			canvas.cleanUp();

			if ( undoable )
			{
				canvas.addUndoableAction(this);
			}
		}
	}
}
