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
 * An action class that will perform a Undo action.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class ActionUndo extends AbstractSystemAction
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
	public ActionUndo(String text, ImageIcon icon)
	{
		super(text, icon);
		putValue(SHORT_DESCRIPTION, "This is a Undo action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_Z));
	}


	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionUndo(String text)
	{
		super(text);
		putValue(SHORT_DESCRIPTION, "This is a Undo action");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_Z));
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

			// If the manager has an undo to perform
			if ( manager.canUndo() )
			{
				// performs the undo
				manager.undo();

				// Cleans up the canvas
				PrimeMain1.currentCanvas.cleanUp();
			}
		}
	}

}
