package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuWidget;



import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * Class for the JPopupMenu for {@link WidgetObject WidgetObjects} on a {@link WorkareaCanvas}.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class JPopupWidgetMenu
{
	/**
	 * The JPopup instance.
	 */
	private JPopupMenu popup = new JPopupMenu();


	/**
	 * The Listener that will listen to menus selected by the user in the JPopupMenu.
	 */
	private WorkareaWidgetActionListener widgetActListener;


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
	public JPopupWidgetMenu(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}



	/**
	 * Creates and sets up the the different fields of this class. It also returns the JpopupMenu shown to the user.
	 * 
	 * @param widget
	 *            The {@link WidgetObject} a JPopupMenu will be shown for.
	 * @return The JPopupMenu the JpopupMenu shown to the user.
	 */
	public JPopupMenu createPopupMenu(Widget widget)
	{
		widgetActListener = new WorkareaWidgetActionListener(canvas);

		canvas.setCurrentWidgetObject((WidgetObject) widget);


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

		menuItem = new JMenuItem("Delete this object");
		menuItem.setActionCommand("DeleteThisObject");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);

		menuItem = new JMenuItem("Delete all connection to and from this object");
		menuItem.setActionCommand("DeleteConnectionsObject");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);

		menuItem = new JMenuItem("A popup menu item");
		menuItem.setActionCommand("CreateNewItem");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);

		menuItem = new JMenuItem("Another popup menu item");
		menuItem.setActionCommand("CreateAnotherNewItem");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);

		menuItem = new JMenuItem("Yet Another popup menu item");
		menuItem.setActionCommand("CreateYetAnotherNewItem");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);
	}
}
