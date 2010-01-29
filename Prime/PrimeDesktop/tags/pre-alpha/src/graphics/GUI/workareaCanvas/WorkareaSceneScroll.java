package graphics.GUI.workareaCanvas;


import javax.swing.JScrollPane;

import managment.CanvasManagment;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class WorkareaSceneScroll extends JScrollPane
{
	private WorkareaCanvas canvas;

	/**
	 * The constructor for the class that creates a new WorkareaCanvas with the given name.
	 * 
	 * @param name
	 *            The name of the new WorkareaCanvas.
	 */
	public WorkareaSceneScroll(String name)
	{
		canvas = new WorkareaCanvas();
		createNewCanvas(name);
	}


	/**
	 * A constructor that takes a WorkareaCanvas and sets it to the classes private WorkareaCanvas.
	 * 
	 * @param canvas
	 *            The WorkareaCanvas that is to be placed inside the tab.
	 */
	public WorkareaSceneScroll(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
		createNewCanvas(canvas.getCanvasName());
	}



	/**
	 * A constructor that takes a WorkareaCanvas and sets it to the classes private WorkareaCanvas. This function calls
	 * a method that sets the given name as the name of the given WorkareaCanvas.
	 * 
	 * @param name
	 *            The name of the tab which will contain the given WorkareaCanvas.
	 * @param canvas
	 *            The WorkareaCanvas that is to be placed inside the tab.
	 */
	public WorkareaSceneScroll(String name, WorkareaCanvas canvas)
	{
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

		this.canvas = canvas;
		createNewCanvas(name);
	}


	/**
	 * Sets this JScrollPanes view to the classes WorkareaCanvas. It also adds the the given and adds the given
	 * WorkareaCanvas with the given name to the systems array of WorkareaCanvases.
	 * 
	 * @param name
	 *            The name of the canvas.
	 */
	public void createNewCanvas(String name)
	{
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

		this.setViewportView(canvas.getMyView());

		// Adds the canvas the array of currently active WorkareaCanvas
		CanvasManagment.addCanvas(canvas, name);
	}


	/**
	 * Gets the classes WorkareaCanvas.
	 * 
	 * @return the canvas
	 */
	public WorkareaCanvas getCanvas()
	{
		return canvas;
	}
}
