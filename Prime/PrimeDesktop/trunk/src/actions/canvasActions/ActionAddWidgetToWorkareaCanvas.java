package actions.canvasActions;


import graphics.PrimeMain;
import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import managment.DesktopCanvasManagment;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * This action adds a given {@link WidgetObject} to a given {@link WorkareaCanvas} at a given {@link Point}.
 * This action contains a undo/redo function.
 * 
 * @author Bahram Malaekeh
 */
public class ActionAddWidgetToWorkareaCanvas extends AbstractSystemAction implements SystemActionInterface
{
	// The canvas where the deletion is taking place
	private WorkareaCanvas canvas = null;

	// The widget object
	private WidgetObject widObject = null;

	// The point where the Object is to be added
	private Point objectPoint;

	/**
	 * A constructor for the class that takes a string, the action name, an
	 * Icon, a {@link WorkareaCanvas}, a {@link WidgetObject} and a {@link Point}.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 * @param canvas
	 *            The {@link WorkareaCanvas} that the given {@link WidgetObject} is to be added to.
	 * @param widObject
	 *            The {@link WidgetObject} that is to be added to the given {@link WorkareaCanvas}.
	 * @param objectPoint
	 *            The {@link Point} where the {@link WidgetObject} is to be
	 *            placed on the {@link WorkareaCanvas}.
	 */
	public ActionAddWidgetToWorkareaCanvas(String text, ImageIcon icon,
			WorkareaCanvas canvas, WidgetObject widObject, Point objectPoint)
	{
		super(text, icon);
		this.canvas = canvas;
		this.widObject = widObject;
		this.objectPoint = objectPoint;
	}


	/**
	 * A constructor for the class that takes a string, the action name, a {@link WorkareaCanvas}, a {@link WidgetObject} and a
	 * {@link Point}.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param canvas
	 *            The {@link WorkareaCanvas} that the given {@link WidgetObject} is to be added to.
	 * @param widObject
	 *            The {@link WidgetObject} that is to be added to the given {@link WorkareaCanvas}.
	 * @param objectPoint
	 *            The {@link Point} where the {@link WidgetObject} is to be
	 *            placed on the {@link WorkareaCanvas}.
	 */
	public ActionAddWidgetToWorkareaCanvas(String text, WorkareaCanvas canvas,
			WidgetObject widObject, Point objectPoint)
	{
		super(text);
		this.canvas = canvas;
		this.widObject = widObject;
		this.objectPoint = objectPoint;
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
		canvas = null;
		widObject = null;
		objectPoint = null;
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getPresentationName()
	 */
	@Override
	public String getPresentationName()
	{
		return PrimeMain.texts
				.getString("actionAddWidgetToCanvasActionPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return PrimeMain.texts
				.getString("actionAddWidgetToCanvasRedoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return PrimeMain.texts
				.getString("actionAddWidgetToCanvasUndoPresNameText");
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
			// Gets the point the Widget has on the scene
			Point sceneLocation = canvas.getScene().convertViewToScene(
					objectPoint);

			// Adds the newly created WidgetObject to the classes canvas
			DesktopCanvasManagment.addWidgetToCanvas(widObject, sceneLocation,
					canvas, true, true);

			// Adds the actions that the new widget supports
			ActionsAdder.makeWidgetObjectReady(canvas, widObject);
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
			WorkareaCanvasActions.removeObject(canvas, widObject, true);
		}
	}


	/*
	 * (non-Javadoc)
	 * @see logistical.SystemActionInterface#performAction()
	 */
	@Override
	public void performAction(boolean undoable)
	{
		// Gets the point the Widget has on the scene
		Point sceneLocation = canvas.getScene().convertViewToScene(objectPoint);

		// Adds the newly created WidgetObject to the classes canvas
		DesktopCanvasManagment.addWidgetToCanvas(widObject, sceneLocation,
				canvas, true, true);

		// Adds the actions that the new widget supports
		ActionsAdder.makeWidgetObjectReady(canvas, widObject);

		if ( undoable )
		{
			canvas.addUndoableAction(this);
		}
	}
}
