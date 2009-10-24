package actions.canvasActions;


import graphics.GraphicalFunctions;

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


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ActionChangeWidgetObjectName extends AbstractSystemAction
		implements SystemActionInterface
{

	// The widget object where the name is to be changed
	WidgetObject widObject = null;

	// The new name of the widget object
	String newName = null;

	// The old name of the widget object
	String oldName = null;

	/**
	 * A constructor for the class that takes a string, the action name, and a
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 * @param widgetObject
	 * @param givenName
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
	 * /** A constructor for the class that takes a string which will be the
	 * name of the action.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param widgetObject
	 * @param givenName
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
		performAction();
	}



	@Override
	public boolean addEdit(UndoableEdit anEdit)
	{
		return false;
	}

	@Override
	public boolean canRedo()
	{
		return true;
	}

	@Override
	public boolean canUndo()
	{
		return true;
	}

	@Override
	public void die()
	{
		widObject = null;
		newName = null;
		oldName = null;
	}

	@Override
	public String getPresentationName()
	{
		return "Object Name Change";
	}

	@Override
	public String getRedoPresentationName()
	{
		return "Change Object Name to New Name";
	}

	@Override
	public String getUndoPresentationName()
	{
		return "Change Object Name back to Old Name";
	}

	@Override
	public boolean isSignificant()
	{
		return true;
	}

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

	@Override
	public void performAction()
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
				JOptionPane.showMessageDialog(null, "The name provided, "
						+ newName + ", was not a valid name.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
