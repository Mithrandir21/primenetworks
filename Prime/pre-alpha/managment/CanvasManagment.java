/**
 * 
 */
package managment;


import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.WorkareaCanvas;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class CanvasManagment
{



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
					System.out.println("Added");
					return;
				}
			}

			// If the method gets to this point it means that there were no
			// empty indexes found and there needs to be added an empty index at
			// the end of the array
			extendCanvasArray();

			addCanvas(newCanvas, name);
//
//			 extendCanvasArray();
//			
//			 newCanvas.setCanvasName(name);
//			 
//			 PrimeMain1.canvases[PrimeMain1.canvases.length - 1] = newCanvas;
//			 PrimeMain1.currentCanvas = newCanvas;

		}
		else
		{
			newCanvas.setCanvasName(name);
			PrimeMain1.canvases[0] = newCanvas;
			PrimeMain1.currentCanvas = newCanvas;
			
		}
	}



	public static void extendCanvasArray()
	{
		WorkareaCanvas[] temp = new WorkareaCanvas[PrimeMain1.canvases.length + 1];
		
//		System.out.println("PrimeMain1.canvases.length " + PrimeMain1.canvases.length);
//		System.out.println("temp.length " + temp.length);
		
		for ( int i = 0; i < PrimeMain1.canvases.length; i++ )
		{
			temp[i] = PrimeMain1.canvases[i];
		}
		
		PrimeMain1.canvases = temp;
	}



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
}
