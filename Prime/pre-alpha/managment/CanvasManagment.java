/**
 * 
 */
package managment;


import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.util.Iterator;
import java.util.List;

import logistical.cleanup;
import objects.Object;

import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;


/**
 * In this class the method to maintain and process canvases are placed. Here are methods that can add or remove canvas
 * 
 * @author Bahram Malaekeh
 */
public class CanvasManagment
{

	/**
	 * Adds the given canvas to the array of currently active canvas in the system. It also sets the given name as the
	 * name of the canvas object.
	 * 
	 * @param newCanvas
	 *            The new canvas that is to be added to systems array of canvases.
	 * @param name
	 *            The name that is to be set as the name for the canvas object.
	 */
	public static void addCanvas(WorkareaCanvas newCanvas, String name)
	{

		// If the first canvas location is not null, which means that there are
		// previous created canvases.
		if ( PrimeMain1.canvases[0] != null )
		{
			// Goes through the entire canvas array.
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
	 * Searches and, find found, returns a canvas from the systems canvases with the given name.
	 * 
	 * @param canvasName
	 *            The name of the canvas that is searched for.
	 */
	public static WorkareaCanvas findCanvas(String canvasName)
	{
		for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
		{
			if ( PrimeMain1.canvases[i] != null )
			{
				if ( PrimeMain1.canvases[i].getCanvasName().equals(canvasName) )
				{
					return PrimeMain1.canvases[i];
				}
			}
		}

		// Has not found any canvases with that name.
		return null;
	}


	/**
	 * Finds and returns the WidgetObject that contains an Object that is equal to the given object.
	 * 
	 * @param obj
	 * @param canvas
	 * @return The WidgetObject that contains the given object.
	 */
	public static WidgetObject findWidgetObject(Object obj, WorkareaCanvas canvas)
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
	 * Finds and returns the WidgetObject that contains an Object based on the name of the object and the given object.
	 * 
	 * @param obj
	 * @param canvas
	 * @return The WidgetObject that contains the given object.
	 */
	public static WidgetObject findWidgetObjectByObjectName(Object obj, WorkareaCanvas canvas)
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
	 * Finds and returns the WidgetObject that contains an Object that is equal to the given object. This method checks
	 * an array of canvases.
	 * 
	 * @param obj
	 * @param canvas
	 * @return The WidgetObject that contains the given object.
	 */
	public static WidgetObject findWidgetObject(Object obj, WorkareaCanvas[] canvas)
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



	/**
	 * Removes the given canvas from the systems array of open canvases. It does this by comparing the names of the
	 * canvas(ignoring case).
	 * 
	 * @param canvas
	 */
	public static void removeWorkareaCanvas(WorkareaCanvas canvas)
	{
		// If the first canvas location is not null, which means that there are
		// previous created canvases.
		if ( PrimeMain1.canvases[0] != null )
		{
			// Goes through the entire canvas array.
			for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
			{
				// if any index not equal null.
				if ( PrimeMain1.canvases[i] != null )
				{
					// If the canvas at index i has the same name as the gievn
					// canvas
					if ( PrimeMain1.canvases[i].getCanvasName().equalsIgnoreCase(canvas.getCanvasName()) )
					{
						// Removes the canvas from the array
						PrimeMain1.canvases[i] = null;

						// Removes the null pointer in the array
						PrimeMain1.canvases = logistical.cleanup.cleanObjectArray(PrimeMain1.canvases);

						return;
					}
				}
			}
		}
		// TODO - Maybe give the user feedback about the missing canvas
	}


	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 */
	public static void removeWorkareaCanvas(String canvasName)
	{
		// Find the canvas that has the given name
		WorkareaCanvas canvas = findCanvas(canvasName);


		if ( canvas != null )
		{
			// If the first canvas location is not null, which means that there
			// are
			// previous created canvases.
			if ( PrimeMain1.canvases[0] != null )
			{
				// Goes through the entire canvas array.
				for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
				{
					// if any index is null.
					if ( PrimeMain1.canvases[i] == null )
					{
						// If the canvas at index i has the same name as the
						// gievn canvas
						if ( PrimeMain1.canvases[i].getCanvasName().equalsIgnoreCase(canvas.getCanvasName()) )
						{
							// Removes the canvas from the array
							PrimeMain1.canvases[i] = null;

							// Removes the null pointer in the array
							PrimeMain1.canvases = logistical.cleanup.cleanObjectArray(PrimeMain1.canvases);

							return;
						}
					}
				}
			}
		}
		// TODO - Maybe give the user feedback about the missing canvas
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @return
	 */
	public static boolean canvasExists(WorkareaCanvas canvas)
	{
		for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
		{
			if ( PrimeMain1.canvases[i] != null )
			{
				if ( PrimeMain1.canvases[i].getCanvasName().equalsIgnoreCase(canvas.getCanvasName()) )
				{
					return true;
				}
			}
		}

		// Has not found any canvases with that name.
		return false;
	}


	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @return
	 */
	public static boolean canvasExists(String canvasName)
	{
		for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
		{
			if ( PrimeMain1.canvases[i] != null )
			{
				if ( PrimeMain1.canvases[i].getCanvasName().equalsIgnoreCase(canvasName) )
				{
					return true;
				}
			}
		}

		// Has not found any canvases with that name.
		return false;
	}





	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvases
	 * @return
	 */
	public static WorkareaCanvas[] canvasesHaveChanged(WorkareaCanvas[] canvases)
	{
		WorkareaCanvas[] change = new WorkareaCanvas[canvases.length];


		for ( int i = 0; i < canvases.length; i++ )
		{
			if ( canvases[i] != null )
			{
				if ( canvases[i].isSaved() != true && canvases[i].isChanged() == true )
				{
					change[i] = canvases[i];
				}
			}
		}

		change = cleanup.cleanObjectArray(change);


		// If no canvases were changed(and not saved)
		if ( change.length < 1 )
		{
			return null;
		}


		return change;
	}

}
