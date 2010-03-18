/**
 * 
 */
package managment;


import java.util.Iterator;
import java.util.List;

import logistical.cleanup;
import objects.Object;

import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class CanvasManagment
{
	/**

	 */
	/**
	 * Searches and, find found, returns a canvas from the systems canvases with
	 * the given name.
	 * 
	 * @param canvasName
	 *            The name of the canvas that is searched for.
	 * @param canvases
	 *            The {@link WorkareaCanvas WorkareaCanvases} that are to be
	 *            searched.
	 * @return Returns null if no {@link WorkareaCanvas} with the given name is
	 *         found.
	 */
	public static WorkareaCanvas findCanvas(String canvasName,
			WorkareaCanvas[] canvases)
	{
		for ( int i = 0; i < canvases.length; i++ )
		{
			if ( canvases[i] != null )
			{
				if ( canvases[i].getCanvasName().equals(canvasName) )
				{
					return canvases[i];
				}
			}
		}

		// Has not found any canvases with that name.
		return null;
	}



	/**
	 * Searches and, find found, returns a canvas from the systems canvases with
	 * the given name.
	 * 
	 * @param canvasName
	 *            The name of the canvas that is searched for.
	 * @param canvases
	 *            The {@link WorkareaCanvas WorkareaCanvases} that are to be
	 *            searched.
	 * @return Returns null if no {@link WorkareaCanvas} with the given name is
	 *         found.
	 */
	public static WorkareaCanvas findCanvas(Scene scene,
			WorkareaCanvas[] canvases)
	{
		for ( int i = 0; i < canvases.length; i++ )
		{
			if ( canvases[i] != null )
			{
				if ( canvases[i].getScene().equals(scene) )
				{
					return canvases[i];
				}
			}
		}

		// Has not found any canvases with that name.
		return null;
	}



	/**
	 * Searches and, find found, returns a canvas from the systems canvases with
	 * the given {@link Object}.
	 * 
	 * @param obj
	 *            The {@link Object} that is searched for.
	 * @param canvases
	 *            The {@link WorkareaCanvas WorkareaCanvases} that are to be
	 *            searched.
	 * @return Returns null if no {@link WorkareaCanvas} with the given {@link Object} is
	 *         found.
	 */
	public static WorkareaCanvas findCanvas(Object obj,
			WorkareaCanvas[] canvases)
	{
		return findCanvas(findWidgetObject(obj, canvases).getScene(), canvases);
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

			if ( temp.getObject().getObjectSerial() == obj.getObjectSerial() )
			{
				return temp;
			}
		}

		return null;
	}



	/**
	 * Finds and returns the WidgetObject that contains an Object based on the
	 * name of the object and the given object.
	 * 
	 * @param obj
	 * @param canvas
	 * @return The WidgetObject that contains the given object.
	 */
	public static WidgetObject findWidgetObjectByObjectName(String name,
			WorkareaCanvas canvas)
	{
		List<Widget> children = canvas.getMainLayer().getChildren();


		for ( Iterator<Widget> iter = children.iterator(); iter.hasNext(); )
		{
			WidgetObject temp = (WidgetObject) iter.next();

			if ( temp.getObject().getObjectName().equalsIgnoreCase(name) )
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
	 *            The {@link WorkareaCanvas WorkareaCanvases} that are to be
	 *            searched.
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

				if ( temp.getObject().getObjectSerial() == obj
						.getObjectSerial() )
				{
					return temp;
				}
			}
		}

		return null;
	}




	/**
	 * Finds a WorkareaCanvas in the systems canvas array.
	 * 
	 * @param canvas
	 *            The searched for WorkareaCanvas.
	 * @param canvases
	 *            The {@link WorkareaCanvas WorkareaCanvases} that are to be
	 *            searched.
	 * @return True if he WorkareaCanvas was found and False if not.
	 */
	public static boolean canvasExists(WorkareaCanvas canvas,
			WorkareaCanvas[] canvases)
	{
		for ( int i = 0; i < canvases.length; i++ )
		{
			if ( canvases[i] != null )
			{
				if ( canvases[i].getCanvasName().equalsIgnoreCase(
						canvas.getCanvasName()) )
				{
					return true;
				}
			}
		}

		// Has not found any canvases with that name.
		return false;
	}


	/**
	 * Finds a WorkareaCanvas with the given name in the systems canvas array.
	 * 
	 * @param canvasName
	 *            The name of the searched for WorkareaCanvas.
	 * @param canvases
	 *            The {@link WorkareaCanvas WorkareaCanvases} that are to be
	 *            searched.
	 * @return True if he WorkareaCanvas was found and False if not.
	 */
	public static boolean canvasExists(String canvasName,
			WorkareaCanvas[] canvases)
	{
		for ( int i = 0; i < canvases.length; i++ )
		{
			if ( canvases[i] != null )
			{
				if ( canvases[i].getCanvasName().equalsIgnoreCase(canvasName) )
				{
					return true;
				}
			}
		}

		// Has not found any canvases with that name.
		return false;
	}




	/**
	 * The method goes through all the given WorkareaCanvases given and returns
	 * a WorkareaCanvas array containing the WorkareaCanvases that have been
	 * changed and not been saved. If no workareaCanvases are found, it will
	 * return a null pointer.
	 * 
	 * @param canvases
	 *            The WorkareaCanvases to be searched.
	 * @return The WorkareaCanvases that have changed, but not been saved. Or a
	 *         null pointer if no WorkareaCanvases are found.
	 */
	public static WorkareaCanvas[] canvasesHaveChanged(WorkareaCanvas[] canvases)
	{
		WorkareaCanvas[] change = new WorkareaCanvas[canvases.length];


		for ( int i = 0; i < canvases.length; i++ )
		{
			if ( canvases[i] != null )
			{
				if ( canvases[i].isSaved() != true
						&& canvases[i].isChanged() == true )
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



	/**
	 * The method checks the given WorkareaCanvas and returns a boolean on whether or not the {@link WorkareaCanvas} has been
	 * changed and not been saved. If the given workareaCanvas is null, false will be returned.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} to be examined.
	 * @return A boolean on whether or not the {@link WorkareaCanvas} has been changed and not saved.
	 */
	public static boolean canvasHaveChanged(WorkareaCanvas canvas)
	{
		if ( canvas != null )
		{
			if ( canvas.isSaved() != true && canvas.isChanged() == true )
			{
				return true;
			}

			if ( canvas.isSaved() != true && canvas.isChanged() == false )
			{
				return true;
			}
		}

		return false;
	}
}
