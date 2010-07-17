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
package graphics.GUI.workareaCanvas.providers;


import graphics.PrimeMain;

import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.Providers.SceneConnectProvider;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import actions.canvasActions.ActionCreateConnection;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class GUIsceneConnectProvider extends SceneConnectProvider
{

	/**
	 * A constructor for the class that sets the {@link WorkareaCanvas} that an
	 * instance of this class will be applied to.
	 * 
	 * @param newObject
	 */
	public GUIsceneConnectProvider(WorkareaCanvas canvas)
	{
		super(canvas);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.netbeans.api.visual.action.ConnectProvider#createConnection(org.netbeans
	 * .api.visual.widget.Widget, org.netbeans.api.visual.widget.Widget)
	 */
	@Override
	public void createConnection(Widget sourceWidget, Widget targetWidget)
	{
		if ( sourceWidget != targetWidget
				&& !(targetWidget instanceof LabelWidget)
				&& !(targetWidget instanceof LayerWidget)
				&& (targetWidget instanceof WidgetObject || isChildOfThisWidget(targetWidget)) )
		{
			ActionCreateConnection action = new ActionCreateConnection(
					PrimeMain.texts
							.getString("actionCreateConnectionDescriptionText"),
					this.getCanvas(), (WidgetObject) sourceWidget,
					(WidgetObject) targetWidget.getParentWidget());
			action.performAction(true);
		}
	}
}
