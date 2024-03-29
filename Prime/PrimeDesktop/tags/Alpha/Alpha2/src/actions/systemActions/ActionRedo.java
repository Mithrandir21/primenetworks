/**
 * 
 */
package actions.systemActions;


import graphics.PrimeMain1;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import logistical.AbstractSystemAction;
import logistical.CanvasUndoManager;


/**
 * An action class that will perform a Redo action.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionRedo extends AbstractSystemAction
{
	/**
	 * A constructor for the class that takes a string, the action name, and a
	 * Icon.
	 * 
	 * @param text
	 *            The name of the action.
	 * @param icon
	 *            The icon representing the action.
	 */
	public ActionRedo(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, PrimeMain1.texts
				.getString("actionRedoText"));
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_Y));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionRedo(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, PrimeMain1.texts
				.getString("actionRedoText"));
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_Y));
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		// If the current canvas is not null
		if ( PrimeMain1.currentCanvas != null )
		{
			// Gets the canvases undomanager
			CanvasUndoManager manager = PrimeMain1.currentCanvas
					.getUndoManager();

			// If the manager has an redo to perform
			if ( manager.canRedo() )
			{
				// performs the redo
				manager.redo();

				// Cleans up the canvas
				PrimeMain1.currentCanvas.cleanUp();
			}
		}
	}
}
