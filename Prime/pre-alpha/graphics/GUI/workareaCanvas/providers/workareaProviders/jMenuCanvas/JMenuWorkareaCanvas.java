/**
 * 
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuCanvas;

import graphics.GUI.workareaCanvas.WorkareaCanvas;
import graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuWidget.JPopupWidgetMenu;

import java.awt.Point;

import javax.swing.JPopupMenu;

import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.widget.Widget;

/**
 * This class provides a JPopupMenu by implementing the PopupMenuProvider.
 *
 * @author Bahram Malaekeh
 * 
 */
public class JMenuWorkareaCanvas implements PopupMenuProvider
{
	/**
	 * The canvas that the menu will be shown on.
	 */
	private WorkareaCanvas canvas;
	
	
	/**
	 * Javadoc-TODO - Description NEEDED!
	 *
	 * @param canvas
	 */
	public JMenuWorkareaCanvas(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}
	
	
	/* (non-Javadoc)
	 * @see org.netbeans.api.visual.action.PopupMenuProvider#getPopupMenu(org.netbeans.api.visual.widget.Widget, java.awt.Point)
	 */
	@Override
	public JPopupMenu getPopupMenu(Widget widget, Point localLocation)
	{
		JPopupCanvasMenu provider = new JPopupCanvasMenu(canvas);
		
		
		return provider.createPopupMenu(localLocation);
	}

}
