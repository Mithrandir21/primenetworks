package actions.canvasActions;


import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;

import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.widget.LabelWidget;

import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ActionAddWidgetToWorkareaCanvas extends AbstractSystemAction
		implements SystemActionInterface
{
	// The canvas where the deletion is taking place
	private WorkareaCanvas canvas = null;

	// The widget object
	private WidgetObject widObject = null;

	// The point where the Object is to be added
	private Point objectPoint;

	/**
	 * A constructor for the class that takes a string, the action name, and a
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
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
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
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
		return PrimeMain1.texts
				.getString("actionAddWidgetToCanvasActionPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return PrimeMain1.texts
				.getString("actionAddWidgetToCanvasRedoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return PrimeMain1.texts
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
			// Adds the widget to the canvas main layer
			canvas.getMainLayer().addChild(widObject);

			widObject.bringToFront();

			canvas.addToNumberOfWidgetsOnTheCanvas();

			canvas.cleanUp();
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
			// Removes the WidgetObject from the canvas
			canvas.getMainLayer().removeChild(widObject);

			canvas.subtractFromNumberOgWidgetsOnTheCanvas();

			canvas.setCurrentWidgetObject(null);

			canvas.cleanUp();
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

		// Sets the point on the scene as the widgets preferred location
		widObject.setPreferredLocation(sceneLocation);

		// Creates the LabelWidget that is placed on scene
		LabelWidget objectLabel = new LabelWidget(canvas.getScene(), widObject
				.getObject().getObjectName());

		widObject.addChild(objectLabel);


		// newObject.setToolTipText(newObject.getObject().getDescription());
		widObject.setLayout(LayoutFactory.createAbsoluteLayout());

		// ----------DIFFERENT BORDERS------------

		widObject.setBorder(BorderFactory.createEmptyBorder());

		// ---------------------------------------


		// Adds the actions that the new widget supports
		ActionsAdder.makeWidgetObjectReady(canvas, widObject);

		widObject.bringToFront();

		// Adds the widget to the canvas main layer
		canvas.getMainLayer().addChild(widObject);

		canvas.addToNumberOfWidgetsOnTheCanvas();

		canvas.cleanUp();

		if ( undoable )
		{
			canvas.addUndoableAction(this);
		}
	}
}
