package graphics.GUI.workareaCanvas;

import graphics.PrimeMain1;

import javax.swing.JScrollPane;

import managment.CanvasManagment;

public class WorkareaSceneScroll extends JScrollPane
{
	public WorkareaSceneScroll()
	{
		createNewCanvas();
	}
	
	
	public void createNewCanvas()
	{
		WorkareaCanvas canvas = new WorkareaCanvas();
		
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		
		this.setViewportView(canvas.myView);
		
		CanvasManagment.addCanvas(canvas);
		
		
	}
}
