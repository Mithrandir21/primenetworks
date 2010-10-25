/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuWidget;



import graphics.PrimeMain;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import actions.canvasActions.ActionDeleteWidget;
import actions.systemActions.ActionPaste;


/**
 * Class for the JPopupMenu for {@link WidgetObject WidgetObjects} on a {@link WorkareaCanvas}.
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

		menuItem = new JMenuItem(PrimeMain.texts
				.getString("widgetMenuOpenWidgetAction"));
		menuItem.setActionCommand("OpenDevice");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);


		popup.addSeparator();


		menuItem = new JMenuItem(PrimeMain.texts
				.getString("widgetMenuCopyWidgetAction"));
		menuItem.setActionCommand("CopyObject");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);


		menuItem = new JMenuItem(PrimeMain.texts
				.getString("widgetMenuCutWidgetAction"));
		menuItem.setActionCommand("CutObject");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);


		menuItem = new JMenuItem(new ActionPaste(PrimeMain.texts
				.getString("widgetMenuPasteWidgetAction")));

		// If neither the copy or cut pointers point to anything, the past
		// button will be disabled
		if ( PrimeMain.copyWidget == null && PrimeMain.cutWidget == null )
		{
			menuItem.setEnabled(false);
		}
		popup.add(menuItem);


		popup.addSeparator();

		menuItem = new JMenuItem(new ActionDeleteWidget(PrimeMain.texts
				.getString("widgetMenuDeleteWidgetAction")));
		popup.add(menuItem);

		menuItem = new JMenuItem(PrimeMain.texts
				.getString("widgetMenuDeleteConnectionToFromWidgetAction"));
		menuItem.setActionCommand("DeleteConnectionsObject");
		menuItem.addActionListener(widgetActListener);
		popup.add(menuItem);
	}
}
