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
package widgetManipulation.Providers;


import java.awt.Point;

import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * This class controls the creation of graphical connections on a {@link WorkareaCanvas}.
 * 
 * @author Bahram Malaekeh
 */
public class SceneConnectProvider implements ConnectProvider
{
	/**
	 * The WorkareaCanvas this ConnectProvider belongs to.
	 */
	private WorkareaCanvas canvas;


	/**
	 * A constructor for the class that sets the {@link WorkareaCanvas} that an
	 * instance of this class will be applied to.
	 * 
	 * @param newObject
	 */
	public SceneConnectProvider(WorkareaCanvas canvas)
	{
		this.setCanvas(canvas);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.netbeans.api.visual.action.ConnectProvider#createConnection(org.netbeans
	 * .api.visual.widget.Widget, org.netbeans.api.visual.widget.Widget)
	 */
	public void createConnection(Widget sourceWidget, Widget targetWidget)
	{
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.netbeans.api.visual.action.ConnectProvider#hasCustomTargetWidgetResolver
	 * (org.netbeans.api.visual.widget.Scene)
	 */
	public boolean hasCustomTargetWidgetResolver(Scene scene)
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.netbeans.api.visual.action.ConnectProvider#isSourceWidget(org.netbeans
	 * .api.visual.widget.Widget)
	 */
	public boolean isSourceWidget(Widget sourceWidget)
	{
		if ( sourceWidget instanceof WidgetObject
				|| isChildOfThisWidget(sourceWidget) )
		{
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.netbeans.api.visual.action.ConnectProvider#isTargetWidget(org.netbeans
	 * .api.visual.widget.Widget, org.netbeans.api.visual.widget.Widget)
	 */
	public ConnectorState isTargetWidget(Widget sourceWidget,
			Widget targetWidget)
	{
		if ( sourceWidget != targetWidget
				&& !(targetWidget instanceof LabelWidget)
				&& !(targetWidget instanceof LayerWidget)
				&& (targetWidget instanceof WidgetObject || isChildOfThisWidget(targetWidget))
				&& !(targetWidget.getParentWidget() instanceof LayerWidget) )
		{
			return ConnectorState.ACCEPT;
		}
		else
		{
			return ConnectorState.REJECT_AND_STOP;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.netbeans.api.visual.action.ConnectProvider#resolveTargetWidget(org
	 * .netbeans.api.visual.widget.Scene, java.awt.Point)
	 */
	public Widget resolveTargetWidget(Scene sourceWidget, Point sceneLocation)
	{
		return null;
	}


	/**
	 * Determines whether the given {@link Widget} is a child of a {@link WidgetObject}.
	 */
	public boolean isChildOfThisWidget(Widget widget)
	{
		if ( widget != null && widget.getParentWidget() instanceof WidgetObject )
		{
			return true;
		}

		return false;
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param canvas
	 *            the canvas to set
	 */
	public void setCanvas(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @return the canvas
	 */
	public WorkareaCanvas getCanvas()
	{
		return canvas;
	}
}