/**
 * 
 */
package actions.canvasActions;


import graphics.PrimeMain1;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import logistical.AbstractSystemAction;
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

			int i = JOptionPane.showConfirmDialog(null, question,
					PrimeMain1.texts.getString("actionDeleteAllWidgetsName"),
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			// If the answer is yes
			if ( i == 0 )
			{

			}

		}
	}
}
