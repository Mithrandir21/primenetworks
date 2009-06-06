package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuWidget;



import java.awt.Point;

import javax.swing.JPopupMenu;

import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.widget.Widget;

import widgets.WorkareaCanvas;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class JMenuWidget implements PopupMenuProvider
{

	/**
	 * 
	 */
	private WorkareaCanvas canvas;


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param canvas
	 */
	public JMenuWidget(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.PopupMenuProvider#getPopupMenu(org.netbeans.api.visual.widget.Widget,
	 * java.awt.Point)
	 */
	@Override
	public JPopupMenu getPopupMenu(Widget widget, Point localLocation)
	{
		JPopupWidgetMenu provider = new JPopupWidgetMenu(canvas);

		return provider.createPopupMenu(widget);
	}
}
