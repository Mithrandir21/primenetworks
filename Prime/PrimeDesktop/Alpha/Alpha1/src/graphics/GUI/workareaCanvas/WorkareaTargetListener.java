/**
 * 
 */
package graphics.GUI.workareaCanvas;

import graphics.GUI.workareaCanvas.providers.ActionsAdder;

import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import widgetManipulation.Actions.WorkareaCanvasActions;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;

/**
 * Javadoc-TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class WorkareaTargetListener implements DropTargetListener
{
	// The WorkareaCanvas this class belongs to.
	WorkareaCanvas canvas;
	
	
	public WorkareaTargetListener(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}
	

	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dragEnter(java.awt.dnd.DropTargetDragEvent)
	 */
	@Override
	public void dragEnter(DropTargetDragEvent dtde)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dragExit(java.awt.dnd.DropTargetEvent)
	 */
	@Override
	public void dragExit(DropTargetEvent dte)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dragOver(java.awt.dnd.DropTargetDragEvent)
	 */
	@Override
	public void dragOver(DropTargetDragEvent dtde)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#drop(java.awt.dnd.DropTargetDropEvent)
	 */
	@Override
	public void drop(DropTargetDropEvent dtde)
	{
		WidgetObject added = WorkareaCanvasActions.createWidgetOnCanvas(dtde, canvas);
		
		// Adds the actions that the new widget supports
		ActionsAdder.makeWidgetObjectReady(canvas,added);
	}

	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dropActionChanged(java.awt.dnd.DropTargetDragEvent)
	 */
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde)
	{
		// TODO Auto-generated method stub

	}

}
