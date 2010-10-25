/**
 * 
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuRoom;



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
public class JMenuWidgetRoom implements PopupMenuProvider
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
	public JMenuWidgetRoom(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}



	@Override
	public JPopupMenu getPopupMenu(Widget widget, Point localLocation)
	{
		JPopupWidgetRoomMenu provider = new JPopupWidgetRoomMenu(canvas);

		return provider.createPopupMenu(widget);
	}

}
