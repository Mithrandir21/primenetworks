package graphics.GUI.workareaCanvas;


import javax.swing.JScrollPane;

import managment.CanvasManagment;


public class WorkareaSceneScroll extends JScrollPane
{
	private WorkareaCanvas canvas = new WorkareaCanvas();

	public WorkareaSceneScroll(String name)
	{
		createNewCanvas(name);
	}


	public void createNewCanvas(String name)
	{
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

		this.setViewportView(canvas.getMyView());

		CanvasManagment.addCanvas(canvas, name);
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the canvas
	 */
	public WorkareaCanvas getCanvas()
	{
		return canvas;
	}
}
