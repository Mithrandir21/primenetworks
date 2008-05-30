package graphics.GUI.workareaCanvas;

import graphics.PrimeMain1;

import javax.swing.JScrollPane;

public class WorkareaSceneScroll extends JScrollPane
{
	private WorkareaCanvas canvas;
	
	
	public WorkareaSceneScroll()
	{
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		
		canvas = new WorkareaCanvas();
		this.setViewportView(PrimeMain1.myView);
		
		
	}
}
