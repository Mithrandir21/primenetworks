package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuWidget;



import graphics.PrimeMain1;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import actions.ActionPaste;
import actions.canvasActions.ActionDeleteWidget;


/**
 * Class for the JPopupMenu for {@link WidgetObject WidgetObjects} on a
 * {@link WorkareaCanvas}.
 * 
 * @author Bahram Malaekeh
 */
public class JPopupWidgetMenu
{
	/**
	 * The JPopup instance.
	 */
	private JPopupMenu popup = new JPopupMenu();


	/**
	 * The Listener that will listen to menus selected by the user in the
	 * JPopupMenu.
	 */
	private WorkareaWidgetActionListener widgetActListener;


	/**
	 * The {@link WorkareaCanvas} the event will take place in.
	 */
	private WorkareaCanvas canvas;


	/**
	 * The {@link Widget} that the actions are to be performed for.
	 */
	private Widget widget;


	/**
	 * A constructor for this class that sets the {@link WorkareaCanvas} field.
	 * 
	 * @param canvas
	 *            The {@link WorkareaCanvas} the event will take place in.
	 */
	public JPopupWidgetMenu(WorkareaCanvas canvas, Widget widget)
	{
		this.canvas = canvas;
		this.widget = widget;
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
		widgetActListener = new WorkareaWidgetActionListener(canvas, widget);

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

		menuItem = new JMenuItem("Open Device");
		menuItem.setActionCommand("OpenDevice");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);


		popup.addSeparator();


		menuItem = new JMenuItem("Copy Object");
		menuItem.setActionCommand("CopyObject");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);


		menuItem = new JMenuItem("Cut Object");
		menuItem.setActionCommand("CutObject");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);


		menuItem = new JMenuItem(new ActionPaste("Paste Object"));

		// If neither the copy or cut pointers point to anything, the past
		// button will be disabled
		if ( PrimeMain1.copyWidget == null && PrimeMain1.cutWidget == null )
		{
			menuItem.setEnabled(false);
		}
		popup.add(menuItem);


		popup.addSeparator();

		menuItem = new JMenuItem(new ActionDeleteWidget("Delete this object"));
		popup.add(menuItem);

		menuItem = new JMenuItem(
				"Delete all connection to and from this object");
		menuItem.setActionCommand("DeleteConnectionsObject");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);
	}
}
