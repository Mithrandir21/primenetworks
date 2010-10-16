/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuWidget;


import graphics.PrimeMain;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.rackOverview.RackOverview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import managment.DesktopCanvasManagment;
import objects.rackUnits.Rack;

import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import actions.canvasActions.ActionDeleteAllConnectionsToAndFrom;



/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class WorkareaWidgetActionListener implements ActionListener
{

	/**
	 * The {@link WorkareaCanvas} the event will take place in.
	 */
	private WorkareaCanvas canvas;


	/**
	 * The {@link Widget} that the actions are to be performed for.
	 */
	private WidgetObject widget;



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param canvas
	 */
	public WorkareaWidgetActionListener(WorkareaCanvas canvas, Widget widget)
	{
		this.canvas = canvas;
		this.widget = (WidgetObject) widget;
	}



	@Override
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem action = (JMenuItem) e.getSource();

		String actionName = "";

		if ( action.getActionCommand() != null )
		{
			actionName = action.getActionCommand();
		}

		if ( !actionName.equals("") )
		{
			if ( actionName.equals("OpenDevice") )
			{
				// If there exists no group dialog
				if ( PrimeMain.groupsDialog == null )
				{
					if ( widget.getObject() instanceof Rack )
					{
						Rack rackObj = (Rack) widget.getObject();

						// Gets the view, if there exist any, with the given
						// Rack
						RackOverview view = PrimeMain.getRackView(rackObj);

						// There exist no view with the given Rack.
						// Which means that there exist no open view for the
						// given Rack.
						if ( view == null )
						{
							// Creates a new RackOverview with the
							// WidgetObject that has been cast.
							RackOverview rackView = new RackOverview(widget);

							// Adds the view to the arraylist of object
							// views.
							PrimeMain.addRackView(rackView);
						}
						else
						{
							// Brings the pre-existing ObjectView to the
							// front.
							view.toFront();
							// JOptionPane.showMessageDialog(null,
							// "Only one object can be edited at a time.");
						}
					}
					else
					{
						// Gets the view, if there exist any, with the given
						// object
						ObjectView view = PrimeMain.getObjectView(widget
								.getObject());

						// There exist no view with the given object.
						// Which means that there exist no open view for the
						// given
						// object.
						if ( view == null )
						{
							// Creates a new ObjectView object with the
							// WidgetObject
							// that has been cast.
							ObjectView objView = new ObjectView(widget);

							// Adds the view to the arraylist of object views.
							PrimeMain.addObjectView(objView);
						}
						else
						{
							// Brings the pre-existing ObjectView to the front.
							view.toFront();
						}
					}
				}
				else
				{
					PrimeMain.groupsDialog.toFront();
				}
			}
			else if ( actionName.equals("CopyObject") )
			{
				// Sets the cutWidget pointer to null
				PrimeMain.cutWidget = null;

				// Sets the widget as the widget to be copied
				PrimeMain.copyWidget = widget;
			}
			else if ( actionName.equals("CutObject") )
			{
				// Sets the copyWidget pointer to null
				PrimeMain.copyWidget = null;

				// Sets the widget as the widget to be cut(copied and deleted)
				PrimeMain.cutWidget = widget;
			}
			else if ( actionName.equals("DeleteConnectionsObject") )
			{
				// WorkareaCanvasActions.removeAllConnectionsToFromObject(canvas,
				// widget.getObject());
				ActionDeleteAllConnectionsToAndFrom actionDeleteConnections = new ActionDeleteAllConnectionsToAndFrom(
						PrimeMain.texts
								.getString("actionDeleteAllConnectionName"));
				actionDeleteConnections.performAction(true);
			}
		}


		DesktopCanvasManagment.canvasCleanUp(canvas);

		PrimeMain.updatePropertiesCanvasArea(false);
	}
}
