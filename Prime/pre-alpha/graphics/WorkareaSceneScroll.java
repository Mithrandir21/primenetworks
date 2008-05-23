package graphics;

import javax.swing.*;

public class WorkareaSceneScroll extends JScrollPane
{
	private WorkareaCanvas canvas;
	
	
	public WorkareaSceneScroll()
	{
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		
		canvas = new WorkareaCanvas();
		this.setViewportView(canvas.myView);
		
		
	}
}
