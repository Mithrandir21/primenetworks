/**
 * 
 */
package managment;


import java.util.Iterator;
import java.util.List;

import org.netbeans.api.visual.widget.Widget;

import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.WorkareaCanvas;
import widgetManipulation.WidgetObject;
import objects.Object;


/**
 * In this class the method to maintain and process canvases are placed. Here
 * are methods that can add or remove canvas
 * 
 * @author Bahram Malaekeh
 */
public class CanvasManagment
{

	/**
	 * Adds the given canvas to the array of currently active canvas in the
	 * system. It also sets the given name as the name of the canvas object.
	 * 
	 * @param newCanvas
	 *            The new canvas that is to be added to systems array of
	 *            canvases.
	 * @param name
	 *            The name that is to be set as the name for the canvas object.
	 */
	public static void addCanvas(WorkareaCanvas newCanvas, String name)
	{

		// If the first canvas location is not null, which means that there are
		// previous created canvases.
		if ( PrimeMain1.canvases[0] != null )
		{
			// Goes through the entier canvas array.
			for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
			{
				// if any index is null.
				if ( PrimeMain1.canvases[i] == null )
				{
					newCanvas.setCanvasName(name);

					// Sets the newCanvas in at that index.
					PrimeMain1.canvases[i] = newCanvas;

					// Ends the method by returning.
					return;
				}
			}

			// If the method gets to this point it means that there were no
			// empty indexes found and there needs to be added an empty index at
			// the end of the array
			extendCanvasArray();

			addCanvas(newCanvas, name);
		}
		else
		{
			newCanvas.setCanvasName(name);
			PrimeMain1.canvases[0] = newCanvas;
			PrimeMain1.currentCanvas = newCanvas;

		}
	}



	/**
	 * Extends the array that holds the system canvases with one index.
	 */
	public static void extendCanvasArray()
	{
		WorkareaCanvas[] temp = new WorkareaCanvas[PrimeMain1.canvases.length + 1];

		for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
		{
			temp[i] = PrimeMain1.canvases[i];
		}

		PrimeMain1.canvases = temp;
	}



	/**
	 * Searches and, find found, returns a canvas from the systems canvases with
	 * the given name.
	 * 
	 * @param canvasName
	 *            The name of the canvas that is searched for.
	 */
	public static WorkareaCanvas findCanvas(String canvasName)
	{
		for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
		{
			if ( PrimeMain1.canvases[i].getName().equals(canvasName) )
			{
				return PrimeMain1.canvases[i];
			}
		}

		// Has not found any canvases with that name.
		return null;
	}


	/**
	 * Finds and returns the WidgetObject that contains an Object that is equal
	 * to the given object.
	 * 
	 * @param obj
	 * @param canvas
	 * @return The WidgetObject that contains the given object.
	 */
	public static WidgetObject findWidgetObject(Object obj,
			WorkareaCanvas canvas)
	{
		List<Widget> children = canvas.getMainLayer().getChildren();


		for ( Iterator<Widget> iter = children.iterator(); iter.hasNext(); )
		{
			WidgetObject temp = (WidgetObject) iter.next();
			
			if ( temp.getObject().getObjectName().equals(obj.getObjectName()) )
			{
				return temp;
			}
		}

		return null;
	}
	
	
	
	/**
	 * Finds and returns the WidgetObject that contains an Object based on the name
	 * of the object and the given object.
	 * 
	 * @param obj
	 * @param canvas
	 * @return The WidgetObject that contains the given object.
	 */
	public static WidgetObject findWidgetObjectByObjectName(Object obj,
			WorkareaCanvas canvas)
	{
		List<Widget> children = canvas.getMainLayer().getChildren();


		for ( Iterator<Widget> iter = children.iterator(); iter.hasNext(); )
		{
			WidgetObject temp = (WidgetObject) iter.next();
			
			if ( temp.getObject().getObjectName().equals(obj.getObjectName()) )
			{
				return temp;
			}
		}

		return null;
	}



	/**
	 * Finds and returns the WidgetObject that contains an Object that is equal
	 * to the given object. This method checks an array of canvases.
	 * 
	 * @param obj
	 * @param canvas
	 * @return The WidgetObject that contains the given object.
	 */
	public static WidgetObject findWidgetObject(Object obj,
			WorkareaCanvas[] canvas)
	{
		for ( int i = 0; i < canvas.length; i++ )
		{
			List<Widget> children = canvas[i].getMainLayer().getChildren();


			for ( Iterator<Widget> iter = children.iterator(); iter.hasNext(); )
			{
				WidgetObject temp = (WidgetObject) iter.next();

				if ( temp.getObject().equals(obj) )
				{
					return temp;
				}
			}
		}


		return null;
	}
}
