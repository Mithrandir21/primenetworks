package actions.canvasActions;

import graphics.PrimeMain1;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import widgets.WorkareaCanvas;

import logistical.AbstractSystemAction;

public class ActionDeleteAllConnections extends AbstractSystemAction 
{

	/**
	 * A constructor for the class that takes a string, the action name, and an
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionDeleteAllConnections(String text, ImageIcon icon)
	{
		super(text, icon);
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionDeleteAllConnections(String text)
	{
		super(text);
	}

	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		WorkareaCanvas canvas = PrimeMain1.currentCanvas;

		if ( canvas != null )
		{
			String question = PrimeMain1.texts
					.getString("actionDeleteAllConnectionsQuestions")
					+ "\n"
					+ PrimeMain1.texts.getString("thisCannotBeUndoneMsg");

			int i = JOptionPane.showConfirmDialog(null, question,
					PrimeMain1.texts.getString("actionDeleteAllConnectionsName"),
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			// If the answer is yes
			if ( i == 0 )
			{

			}

		}
	}

}