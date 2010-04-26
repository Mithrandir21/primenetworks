/**
 * 
 */
package actions.canvasActions;


import graphics.PrimeMain1;

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
		WorkareaCanvas canvas = PrimeMain1.currentCanvas;

		if ( canvas != null )
		{
			String question = PrimeMain1.texts
					.getString("actionDeleteAllWidgetsQuestions")
					+ "\n"
					+ PrimeMain1.texts.getString("thisCannotBeUndoneMsg");



			//Custom button text
			Object[] options = { PrimeMain1.texts.getString("yes"),
					PrimeMain1.texts.getString("no") };
		

			int i = JOptionPane.showOptionDialog(null, question,
				PrimeMain1.texts.getString("actionDeleteAllWidgetsName"),
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[1]);
		
		
			// If the answer is yes
			if ( i == 0 )
			{
				// gets all the Widgets on the current scene
				WidgetObject[] objects = canvas.getWidgetObjectsOnTheScene();
				
				for( int j = 0; j < objects.length; j++ )
				{
					// Removes all connection to the WidgetObject
					WorkareaCanvasActions.removeAllConnectionsToFromObject(canvas,
							objects[j].getObject());

					// Removes the WidgetObject from the canvas
					canvas.getMainLayer().removeChild(objects[j]);
				
					canvas.subtractFromNumberOgWidgetsOnTheCanvas();
				}
				
				canvas.setCurrentWidgetObject(null);
				
				canvas.cleanUp();

				
				// Deletes all the undoable actions of the canvas
				canvas.getUndoManager().discardAllEdits();
			}

		}
	}
}
