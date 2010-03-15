package actions.canvasActions;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import logistical.checkLogic;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * This action changes the name of a given {@link WidgetObject}. The action
 * automatically changed the name displayed on the {@link WorkareaCanvas} where
 * the {@link WidgetObject} is placed.
 * This action contains a undo/redo function.
 * 
 * @author Bahram Malaekeh
 */
public class ActionChangeWidgetObjectName extends AbstractSystemAction implements SystemActionInterface
{

	// The widget object where the name is to be changed
	WidgetObject widObject = null;

	// The new name of the widget object
	String newName = null;

	// The old name of the widget object
	String oldName = null;

	/**
	 * A constructor for the class that takes a string, the action name, an
	 * Icon, a {@link WidgetObject} and a String that is to be the new name of
	 * the given {@link WidgetObject}.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 * @param widgetObject
	 *            The {@link WidgetObject} whose name is to be changed.
	 * @param givenName
	 *            The new name of the {@link WidgetObject}.
	 */
	public ActionChangeWidgetObjectName(String text, ImageIcon icon,
			WidgetObject widgetObject, String givenName)
	{
		super(text, icon);
		widObject = widgetObject;
		newName = givenName;
		oldName = widgetObject.getObject().getObjectName();
	}

	/**
	 * A constructor for the class that takes a string, the action name, a
	 * {@link WidgetObject} and a String that is to be the new name of
	 * the given {@link WidgetObject}.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param widgetObject
	 *            The {@link WidgetObject} whose name is to be changed.
	 * @param givenName
	 *            The new name of the {@link WidgetObject}.
	 */
	public ActionChangeWidgetObjectName(String text, WidgetObject widgetObject,
			String givenName)
	{
		super(text);
		widObject = widgetObject;
		newName = givenName;
		oldName = widgetObject.getObject().getObjectName();
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
		widObject = null;
		newName = null;
		oldName = null;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getPresentationName()
	 */
	@Override
	public String getPresentationName()
	{
		return PrimeMain1.texts
				.getString("actionChangeWidgetNameActionPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return PrimeMain1.texts
				.getString("actionChangeWidgetNameRedoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return PrimeMain1.texts
				.getString("actionChangeWidgetNameUndoPresNameText");
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
		if ( widObject != null )
		{
			// Updates the name of the LabelWidget on the scene
			GraphicalFunctions.updateWidgetObjectCanvasName(widObject, newName);

			// Sets the name of the object
			widObject.getObject().setObjectName(newName);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#undo()
	 */
	@Override
	public void undo() throws CannotUndoException
	{
		if ( widObject != null )
		{
			// Updates the name of the LabelWidget on the
			// scene
			GraphicalFunctions.updateWidgetObjectCanvasName(widObject
					.getObject(), oldName);

			// Sets the name of the object
			widObject.getObject().setObjectName(oldName);
		}
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.SystemActionInterface#performAction(boolean)
	 */
	@Override
	public void performAction(boolean undoable)
	{
		if ( widObject != null )
		{
			// If the text is validated
			if ( checkLogic.validateName(newName) )
			{
				// Updates the name of the LabelWidget on the scene
				GraphicalFunctions.updateWidgetObjectCanvasName(widObject
						.getObject(), newName);

				// Sets the name of the object
				widObject.getObject().setObjectName(newName);
			}
			else
			{
				JOptionPane.showMessageDialog(null, PrimeMain1.texts
						.getString("actionChangeWidgetNameInvalidNameText"),
						PrimeMain1.texts.getString("error"),
						JOptionPane.ERROR_MESSAGE);
			}

			if ( undoable )
			{
				PrimeMain1.currentCanvas.addUndoableAction(this);
			}
		}
	}
}
