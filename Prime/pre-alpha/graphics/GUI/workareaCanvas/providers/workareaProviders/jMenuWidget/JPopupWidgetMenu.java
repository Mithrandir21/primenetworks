package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuWidget;


import graphics.GUI.workareaCanvas.WorkareaCanvas;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class JPopupWidgetMenu
{
	/**
	 * 
	 */
	private JPopupMenu popup = new JPopupMenu();


	/**
	 * 
	 */
	private WorkareaWidgetActionListener widgetActListener;


	/**
	 * 
	 */
	private WorkareaCanvas canvas;


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param canvas
	 */
	public JPopupWidgetMenu(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}



	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param widget
	 * @return
	 */
	public JPopupMenu createPopupMenu(Widget widget)
	{
		widgetActListener = new WorkareaWidgetActionListener(canvas);

		canvas.setCurrentWidgetObject((WidgetObject) widget);


		InitialMenues(canvas);



		return popup;
	}


	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
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
