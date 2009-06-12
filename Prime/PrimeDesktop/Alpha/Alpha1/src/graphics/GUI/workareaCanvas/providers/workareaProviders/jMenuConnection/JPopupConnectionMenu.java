/**
 * 
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuConnection;


import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.netbeans.api.visual.widget.Widget;

import widgets.WorkareaCanvas;
import connections.WidgetExtendedConnection;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class JPopupConnectionMenu
{
	/**
	 * The JPopup instance.
	 */
	private JPopupMenu popup = new JPopupMenu();

	/**
	 * The Listener that will listen to menus selected by the user in the JPopupMenu.
	 */
	private WorkareaConnectionActionListener widgetActListener;


	/**
	 * The {@link WorkareaCanvas} the event will take place in.
	 */
	private WorkareaCanvas canvas;


	/**
	 * A constructor for this class that sets the {@link WorkareaCanvas} field.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} the event will take place in.
	 */
	public JPopupConnectionMenu(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}



	/**
	 * Creates and sets up the the different fields of this class. It also returns the JpopupMenu shown to the user.
	 * 
	 * @param widget
	 *            The {@link WidgetExtendedConnection} a JPopupMenu will be shown for.
	 * @return The JPopupMenu the JpopupMenu shown to the user.
	 */
	public JPopupMenu createPopupMenu(Widget widget)
	{
		widgetActListener = new WorkareaConnectionActionListener(canvas, (WidgetExtendedConnection) widget);

		InitialMenues(canvas);


		return popup;
	}




	/**
	 * Initiates the JPopupMenu and add all the different JMenuItems.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} the event will take place in.
	 */
	private void InitialMenues(WorkareaCanvas canvas)
	{
		JMenuItem menuItem;

		menuItem = new JMenuItem("Delete Connection");
		menuItem.setActionCommand("DeleteThisConnection");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);

		menuItem = new JMenuItem("Another Option");
		// menuItem.setActionCommand("DeleteConnectionsObject");
		// menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);
	}


}
