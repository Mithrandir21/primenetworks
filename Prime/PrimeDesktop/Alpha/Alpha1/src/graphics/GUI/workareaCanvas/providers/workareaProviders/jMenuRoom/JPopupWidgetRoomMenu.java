/**
 * 
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuRoom;



import graphics.PrimeMain1;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;
import widgets.WidgetRoom;
import widgets.WorkareaCanvas;
import actions.canvasActions.ActionRemoveRoom;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class JPopupWidgetRoomMenu
{
	/**
	 * The JPopup instance.
	 */
	private JPopupMenu popup = new JPopupMenu();


	/**
	 * The Listener that will listen to menus selected by the user in the
	 * JPopupMenu.
	 */
	private WorkareaWidgetRoomActionListener widgetActListener;


	/**
	 * The {@link WorkareaCanvas} the event will take place in.
	 */
	private WorkareaCanvas canvas;


	/**
	 * The widgetroom that this menu is for.
	 */
	private WidgetRoom widgetRoom;



	/**
	 * A constructor for this class that sets the {@link WorkareaCanvas} field.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} the event will take place in.
	 */
	public JPopupWidgetRoomMenu(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}




	/**
	 * Creates and sets up the the different fields of this class. It also
	 * returns the JpopupMenu shown to the user.
	 * 
	 * @param widget
	 *            The {@link WidgetObject} a JPopupMenu will be shown for.
	 * @return The JPopupMenu the JpopupMenu shown to the user.
	 */
	public JPopupMenu createPopupMenu(Widget widget)
	{
		widgetActListener = new WorkareaWidgetRoomActionListener(canvas,
				(WidgetRoom) widget);

		widgetRoom = (WidgetRoom) widget;

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

		menuItem = new JMenuItem(new ActionRemoveRoom(PrimeMain1.texts
				.getString("roomMenuDeleteRoomAction"), widgetRoom));
		// menuItem.setActionCommand("DeleteRoom");
		// menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);

		// menuItem = new JMenuItem("Rename this Room");
		// menuItem.setActionCommand("RenameRoom");
		// menuItem.addActionListener(widgetActListener);
		// popup.add(menuItem);
	}
}
