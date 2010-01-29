/**
 * 
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuConnection;


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
public class JMenuConnection implements PopupMenuProvider
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
	public JMenuConnection(WorkareaCanvas canvas)
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
		JPopupConnectionMenu provider = new JPopupConnectionMenu(canvas);

		return provider.createPopupMenu(widget);
	}

}
