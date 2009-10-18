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
public abstract class AbstractSystemAction extends AbstractAction implements
		UndoableEdit
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
