package graphics.GUI.workareaCanvas.providers;


import java.awt.Point;

import javax.swing.JPopupMenu;

import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.widget.Widget;


public class JMenuProvider implements PopupMenuProvider
{

	@Override
	public JPopupMenu getPopupMenu(Widget widget, Point localLocation)
	{
		JPopupMenuProvider provider = new JPopupMenuProvider();

		return provider.createPopupMenu(widget);
	}
}
