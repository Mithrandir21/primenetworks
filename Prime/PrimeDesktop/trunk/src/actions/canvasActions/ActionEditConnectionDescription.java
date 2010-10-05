/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package actions.canvasActions;


import graphics.PrimeMain;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

import logistical.AbstractSystemAction;
import logistical.SystemActionInterface;
import connections.WidgetExtendedConnection;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ActionEditConnectionDescription extends AbstractSystemAction
		implements SystemActionInterface
{
	// The widgetExtendedConnection
	private WidgetExtendedConnection widCon = null;

	// The previous description
	private String previousDescription = null;

	// The new description
	private String newDescription = null;



	/**
	 * A constructor for the class that takes a string which will be the name of
	 * the action.
	 * 
	 * @param text
	 *            The name of the action.
	 */
	public ActionEditConnectionDescription(String text,
			WidgetExtendedConnection widgetCon)
	{
		super(text);
		widCon = widgetCon;
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
	public ActionEditConnectionDescription(String text, ImageIcon icon,
			WidgetExtendedConnection widgetCon)
	{
		super(text, icon);
		widCon = widgetCon;
	}

	/*
	 * (non-Javadoc)
	 * 
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
	 * 
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
	 * 
	 * @see logistical.AbstractSystemAction#canRedo()
	 */
	@Override
	public boolean canRedo()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#canUndo()
	 */
	@Override
	public boolean canUndo()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#die()
	 */
	@Override
	public void die()
	{
		this.widCon = null;
		this.previousDescription = null;
		this.newDescription = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#getPresentationName()
	 */
	@Override
	public String getPresentationName()
	{
		return PrimeMain.texts
				.getString("actionChangeConnectionDescriptionActionPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#getRedoPresentationName()
	 */
	@Override
	public String getRedoPresentationName()
	{
		return PrimeMain.texts
				.getString("actionChangeConnectionDescriptionRedoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#getUndoPresentationName()
	 */
	@Override
	public String getUndoPresentationName()
	{
		return PrimeMain.texts
				.getString("actionChangeConnectionDescriptionUndoPresNameText");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#isSignificant()
	 */
	@Override
	public boolean isSignificant()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#redo()
	 */
	@Override
	public void redo() throws CannotRedoException
	{
		if ( this.widCon != null )
		{
			if ( newDescription != null && !newDescription.equals("") )
			{
				widCon.setConnectionDescription(newDescription);
				widCon.revalidate();
				widCon.repaint();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.AbstractSystemAction#undo()
	 */
	@Override
	public void undo() throws CannotUndoException
	{
		if ( this.widCon != null )
		{
			if ( previousDescription != null && !previousDescription.equals("") )
			{
				widCon.setConnectionDescription(previousDescription);
				widCon.revalidate();
				widCon.repaint();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logistical.SystemActionInterface#performAction(boolean)
	 */
	@Override
	public void performAction(boolean undoable)
	{
		if ( this.widCon != null )
		{
			// Presents the user with an input dialog
			String newText = (String) JOptionPane.showInputDialog(null,
					PrimeMain.texts.getString("editWidConTipText"),
					PrimeMain.texts.getString("editWidConTitle"),
					JOptionPane.PLAIN_MESSAGE, null, null,
					widCon.getToolTipText());

			// If the gotten description is not null(user can possibly close the
			// current canvas before pressing ok or cancel.)
			if ( newText != null && PrimeMain.currentCanvas != null )
			{
				// If the gotten description is not the same as the old
				// description
				if ( !newText.equals(widCon.getToolTipText()) )
				{
					if ( !newText.equals("") )
					{
						previousDescription = widCon.getToolTipText();
						newDescription = newText;

						widCon.setConnectionDescription(newText);
						widCon.revalidate();
						widCon.repaint();

						if ( undoable )
						{
							PrimeMain.currentCanvas.addUndoableAction(this);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, PrimeMain.texts
								.getString("editWidConEmptyTextMsg"),
								PrimeMain.texts.getString("error"),
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

}
