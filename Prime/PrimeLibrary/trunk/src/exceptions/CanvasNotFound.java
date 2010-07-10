/**
 * 
 */
package exceptions;


import widgets.WorkareaCanvas;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class CanvasNotFound extends Exception
{
	/**
	 * The {@link WorkareaCanvas} that was not found.
	 */
	WorkareaCanvas canvas = null;


	/**
	 * A constructor for this class.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} that was not found.
	 */
	public CanvasNotFound(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}



	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String output = "The WorkareaCanvas, " + canvas.getCanvasName()
				+ ", was not found.";

		return output;
	}
}
