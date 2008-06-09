package graphics.GUI.workareaCanvas;

import graphics.PrimeMain1;

import javax.swing.JScrollPane;

public class WorkareaSceneScroll extends JScrollPane
{
	public static WorkareaCanvas canvas = new WorkareaCanvas();
	
	public WorkareaSceneScroll()
	{
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		
		this.setViewportView(PrimeMain1.myView);
	}
}
