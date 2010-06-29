/**
 * 
 */
package managment;


import graphics.PrimeMain;
import widgets.WorkareaCanvas;


/**
 * In this class the method to maintain and process canvases are placed. Here are methods that can add or remove canvas
 * 
 * @author Bahram Malaekeh
 */
public class DesktopCanvasManagment
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
		if ( PrimeMain.canvases[0] != null )
		{
			// Goes through the entire canvas array.
			for ( int i = 0; i < PrimeMain.canvases.length; i++ )
			{
				// if any index is null.
				if ( PrimeMain.canvases[i] == null )
				{
					newCanvas.setCanvasName(name);

					// Sets the newCanvas in at that index.
					PrimeMain.canvases[i] = newCanvas;

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
			PrimeMain.canvases[0] = newCanvas;
			PrimeMain.currentCanvas = newCanvas;

		}
	}



	/**
	 * Extends the array that holds the system canvases with one index.
	 */
	public static void extendCanvasArray()
	{
		WorkareaCanvas[] temp = new WorkareaCanvas[PrimeMain.canvases.length + 1];

		for ( int i = 0; i < PrimeMain.canvases.length; i++ )
		{
			temp[i] = PrimeMain.canvases[i];
		}

		PrimeMain.canvases = temp;
	}



	/**
	 * Searches and, find found, returns a canvas from the systems canvases with the given name.
	 * 
	 * @param canvasName
	 *            The name of the canvas that is searched for.
	 */
	public static WorkareaCanvas findCanvas(String canvasName)
	{
		for ( int i = 0; i < PrimeMain.canvases.length; i++ )
		{
			if ( PrimeMain.canvases[i] != null )
			{
				if ( PrimeMain.canvases[i].getCanvasName().equals(canvasName) )
				{
					return PrimeMain.canvases[i];
				}
			}
		}

		// Has not found any canvases with that name.
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
		if ( PrimeMain.canvases[0] != null )
		{
			// Goes through the entire canvas array.
			for ( int i = 0; i < PrimeMain.canvases.length; i++ )
			{
				// if any index not equal null.
				if ( PrimeMain.canvases[i] != null )
				{
					// If the canvas at index i has the same name as the gievn
					// canvas
					if ( PrimeMain.canvases[i].getCanvasName().equalsIgnoreCase(canvas.getCanvasName()) )
					{
						// Removes the canvas from the array
						PrimeMain.canvases[i] = null;

						// Removes the null pointer in the array
						PrimeMain.canvases = logistical.cleanup.cleanObjectArray(PrimeMain.canvases);

						// If all the canvases have been removed, a new array with one index is created
						if ( PrimeMain.canvases.length == 0 )
						{
							PrimeMain.canvases = new WorkareaCanvas[1];
						}

						return;
					}
				}
			}
		}
		// TODO - Maybe give the user feedback about the missing canvas
	}


	/**
	 * This function removes WorkareaCanvas with the given name from the systems WorkareaCanvas array.
	 * 
	 * @param canvasName
	 *            The name of the workareaCanvas that is to be removed.
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
			if ( PrimeMain.canvases[0] != null )
			{
				// Goes through the entire canvas array.
				for ( int i = 0; i < PrimeMain.canvases.length; i++ )
				{
					// if any index is null.
					if ( PrimeMain.canvases[i] == null )
					{
						// If the canvas at index i has the same name as the
						// gievn canvas
						if ( PrimeMain.canvases[i].getCanvasName().equalsIgnoreCase(canvas.getCanvasName()) )
						{
							// Removes the canvas from the array
							PrimeMain.canvases[i] = null;

							// Removes the null pointer in the array
							PrimeMain.canvases = logistical.cleanup.cleanObjectArray(PrimeMain.canvases);

							return;
						}
					}
				}
			}
		}
		// TODO - Maybe give the user feedback about the missing canvas
	}



	/**
	 * Finds a WorkareaCanvas in the systems canvas array.
	 * 
	 * @param canvas
	 *            The searched for WorkareaCanvas.
	 * @return True if he WorkareaCanvas was found and False if not.
	 */
	public static boolean canvasExists(WorkareaCanvas canvas)
	{
		for ( int i = 0; i < PrimeMain.canvases.length; i++ )
		{
			if ( PrimeMain.canvases[i] != null )
			{
				if ( PrimeMain.canvases[i].getCanvasName().equalsIgnoreCase(canvas.getCanvasName()) )
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
	 * @return True if he WorkareaCanvas was found and False if not.
	 */
	public static boolean canvasExists(String canvasName)
	{
		for ( int i = 0; i < PrimeMain.canvases.length; i++ )
		{
			if ( PrimeMain.canvases[i] != null )
			{
				if ( PrimeMain.canvases[i].getCanvasName().equalsIgnoreCase(canvasName) )
				{
					return true;
				}
			}
		}

		// Has not found any canvases with that name.
		return false;
	}

}
