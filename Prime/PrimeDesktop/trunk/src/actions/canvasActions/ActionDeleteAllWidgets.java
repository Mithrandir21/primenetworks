/**
 * 
 */
package actions.canvasActions;


import graphics.PrimeMain;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import logistical.AbstractSystemAction;
import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * This action deletes all the widgets and connections on a {@link WorkareaCanvas}.
 * It removes everything, except rooms from {@link WorkareaCanvas}.
 * This method does <b>not</b> have a redo/undo actions.
 * 
 * @author Bahram Malaekeh
 */
public class ActionDeleteAllWidgets extends AbstractSystemAction
{
	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionDeleteAllWidgets(String text)
	{
		super(text);
	}


	/**
	 * A constructor for the class that takes a string, the action name, and an
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionDeleteAllWidgets(String text, ImageIcon icon)
	{
		super(text, icon);
	}


	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		WorkareaCanvas canvas = PrimeMain.currentCanvas;

		if ( canvas != null )
		{
			String question = PrimeMain.texts
					.getString("actionDeleteAllWidgetsQuestions")
					+ "\n" + PrimeMain.texts.getString("thisCannotBeUndoneMsg");



			// Custom button text
			Object[] options = { PrimeMain.texts.getString("yes"),
					PrimeMain.texts.getString("no") };


			int i = JOptionPane.showOptionDialog(null, question,
					PrimeMain.texts.getString("actionDeleteAllWidgetsName"),
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);


			// If the answer is yes
			if ( i == 0 )
			{
				// gets all the Widgets on the current scene
				WidgetObject[] objects = canvas.getWidgetObjectsOnTheScene();

				for ( int j = 0; j < objects.length; j++ )
				{
					WorkareaCanvasActions.removeObject(canvas, objects[j],
							false);
				}

				canvas.cleanUp();


				// Deletes all the undoable actions of the canvas
				canvas.getUndoManager().discardAllEdits();
			}

		}
	}
}
