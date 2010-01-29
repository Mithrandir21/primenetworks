/**
 * 
 */
package graphics.GUI.workareaCanvas;


import graphics.PrimeMain1;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import actions.canvasActions.ActionAddWidgetToWorkareaCanvas;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class WorkareaTargetListener implements DropTargetListener
{
	// The WorkareaCanvas this class belongs to.
	WorkareaCanvas canvas;


	public WorkareaTargetListener(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.dnd.DropTargetListener#dragEnter(java.awt.dnd.DropTargetDragEvent
	 * )
	 */
	@Override
	public void dragEnter(DropTargetDragEvent dtde)
	{

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.dnd.DropTargetListener#dragExit(java.awt.dnd.DropTargetEvent)
	 */
	@Override
	public void dragExit(DropTargetEvent dte)
	{

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.dnd.DropTargetListener#dragOver(java.awt.dnd.DropTargetDragEvent
	 * )
	 */
	@Override
	public void dragOver(DropTargetDragEvent dtde)
	{

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.dnd.DropTargetListener#drop(java.awt.dnd.DropTargetDropEvent)
	 */
	@Override
	public void drop(DropTargetDropEvent dtde)
	{
		// Gets the transferable object
		Transferable tr = dtde.getTransferable();

		WidgetObject newObject = null;


		try
		{
			// Creates a WidgetObject from the transferable object
			newObject = (WidgetObject) tr.getTransferData(new DataFlavor(
					WidgetObject.class, "Widget Object"));

			// Gets the dimensions of the widget image
			Dimension objectSize = newObject.getImageDimension();

			// The location where the new widget is to be created
			Point objectPoint = dtde.getLocation();

			// Nullifies the transferable object
			dtde = null;


			int height = objectPoint.x - (objectSize.height / 2);

			int width = objectPoint.y - (objectSize.width / 2);

			objectPoint.setLocation(height, width);

			// Adds the action to the canvas
			ActionAddWidgetToWorkareaCanvas actionAdd = new ActionAddWidgetToWorkareaCanvas(
					PrimeMain1.texts
							.getString("actionAddWidgetToCanvasDescriptionText"),
					canvas, newObject, objectPoint);
			actionAdd.performAction(true);

		}
		catch ( UnsupportedFlavorException e )
		{
			System.out
					.println("WorkareTargetListener - UnsupportedFlavorException");
		}
		catch ( IOException e )
		{
			System.out.println("WorkareTargetListener - IOException");
		}
	}

	/*
	 * (non-Javadoc)
	 * @seejava.awt.dnd.DropTargetListener#dropActionChanged(java.awt.dnd.
	 * DropTargetDragEvent)
	 */
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde)
	{

	}

}
