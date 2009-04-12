/**
 * 
 */
package graphics.GUI.workareaCanvas.providers;


import org.netbeans.api.visual.action.WidgetAction.Adapter;
import org.netbeans.api.visual.widget.Widget;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class WorkareaCanvasListener extends Adapter
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction$Adapter#mouseClicked(org. netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mouseClicked(Widget widget, WidgetMouseEvent event)
	{
		return State.REJECTED;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction.Adapter#mousePressed(org.netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mousePressed(Widget widget, WidgetMouseEvent event)
	{
		return State.REJECTED;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction.Adapter#mouseDragged(org.netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	@Override
	public State mouseDragged(Widget widget, WidgetMouseEvent event)
	{
		return State.REJECTED;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction.Adapter#keyPressed(org.netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetKeyEvent)
	 */
	@Override
	public State keyPressed(Widget widget, WidgetKeyEvent event)
	{
		System.out.println(event.getKeyChar() + " 1");
		return State.REJECTED;
	}


	public State keyTyped(Widget widget, WidgetKeyEvent event)
	{
		System.out.println(event.getKeyChar() + " 2");
		return State.REJECTED;
	}


	public State keyReleased(Widget widget, WidgetKeyEvent event)
	{
		System.out.println(event.getKeyChar() + " 3");
		return State.REJECTED;
	}
}
