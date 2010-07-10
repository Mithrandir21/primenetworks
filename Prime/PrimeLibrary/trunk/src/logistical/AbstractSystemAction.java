/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package logistical;


import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public abstract class AbstractSystemAction extends AbstractAction implements UndoableEdit
{

	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param text
	 * @param icon
	 */
	public AbstractSystemAction(String text, ImageIcon icon)
	{
		super(text, icon);
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param text
	 */
	public AbstractSystemAction(String text)
	{
		super(text);
	}



	@Override
	public boolean addEdit(UndoableEdit anEdit)
	{
		return false;
	}

	@Override
	public boolean canRedo()
	{
		return false;
	}

	@Override
	public boolean canUndo()
	{
		return false;
	}

	@Override
	public void die()
	{

	}

	@Override
	public String getPresentationName()
	{
		return null;
	}

	@Override
	public String getRedoPresentationName()
	{
		return null;
	}

	@Override
	public String getUndoPresentationName()
	{
		return null;
	}

	@Override
	public boolean isSignificant()
	{
		return false;
	}

	@Override
	public void redo() throws CannotRedoException
	{
	}

	@Override
	public boolean replaceEdit(UndoableEdit anEdit)
	{
		return false;
	}

	@Override
	public void undo() throws CannotUndoException
	{

	}

}
