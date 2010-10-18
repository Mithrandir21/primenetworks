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
package widgetManipulation.Actions;


import java.awt.Point;
import java.awt.event.MouseEvent;

import managment.Settings;

import org.netbeans.api.visual.action.ConnectDecorator;
import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.anchor.Anchor;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

import connections.WidgetExtendedConnection;


/**
 * This extension of the {@link WidgetAction.LockedAdapter} is used to setup the
 * mouse interaction for a {@link WidgetExtendedConnection}.
 */
public class ExtendedWidgetConnectAction extends WidgetAction.LockedAdapter
{
	private static final int MIN_DIFFERENCE = 5;

	private ConnectDecorator decorator;

	private Widget interractionLayer;

	private ConnectProvider provider;

	private ConnectionWidget connectionWidget = null;

	private Widget sourceWidget = null;

	private Widget targetWidget = null;

	private Point startingPoint = null;


	public ExtendedWidgetConnectAction(ConnectDecorator decorator,
			Widget interractionLayer, ConnectProvider provider)
	{
		this.decorator = decorator;
		this.interractionLayer = interractionLayer;
		this.provider = provider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.WidgetAction.LockedAdapter#isLocked()
	 */
	protected boolean isLocked()
	{
		return sourceWidget != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.WidgetAction.LockedAdapter#mousePressed
	 * (org.netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	public WidgetAction.State mousePressed(Widget widget,
			WidgetAction.WidgetMouseEvent event)
	{
		// If the event button has been clicked once and either the connection
		// button is toggled or the CTRL button is held down.
		if ( (event.getButton() == MouseEvent.BUTTON1 && event.getClickCount() == 1)
				&& ((Settings.connectionToggle == true) || (event
						.getModifiers() & MouseEvent.CTRL_MASK) != 0) )
		{
			if ( provider.isSourceWidget(widget) )
			{
				Settings.connecting = true;
				sourceWidget = widget;
				targetWidget = null;
				startingPoint = new Point(event.getPoint());
				connectionWidget = decorator
						.createConnectionWidget(interractionLayer.getScene());
				assert connectionWidget != null;
				connectionWidget.setSourceAnchor(decorator
						.createSourceAnchor(widget));
				interractionLayer.addChild(connectionWidget);
				return WidgetAction.State.createLocked(widget, this);
			}
		}
		return State.REJECTED;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.WidgetAction.LockedAdapter#mouseReleased
	 * (org.netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	public WidgetAction.State mouseReleased(Widget widget,
			WidgetAction.WidgetMouseEvent event)
	{
		Point point = event.getPoint();
		boolean state = move(widget, point);
		if ( state )
		{
			if ( Math.abs(startingPoint.x - point.x) >= MIN_DIFFERENCE
					|| Math.abs(startingPoint.y - point.y) >= MIN_DIFFERENCE )
			{
				provider.createConnection(sourceWidget, targetWidget);
				sourceWidget = null;
				targetWidget = null;
				startingPoint = null;
				connectionWidget.setSourceAnchor(null);
				connectionWidget.setTargetAnchor(null);
				interractionLayer.removeChild(connectionWidget);
				connectionWidget = null;
			}
			else
			{
				sourceWidget = null;
				targetWidget = null;
				startingPoint = null;
				connectionWidget.setSourceAnchor(null);
				connectionWidget.setTargetAnchor(null);
				interractionLayer.removeChild(connectionWidget);
				connectionWidget = null;
			}
		}

		Settings.connecting = false;

		return state ? State.CONSUMED : State.REJECTED;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.action.WidgetAction.LockedAdapter#mouseDragged
	 * (org.netbeans.api.visual.widget.Widget,
	 * org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent)
	 */
	public WidgetAction.State mouseDragged(Widget widget,
			WidgetAction.WidgetMouseEvent event)
	{
		return move(widget, event.getPoint()) ? State
				.createLocked(widget, this) : State.REJECTED;
	}

	private boolean move(Widget widget, Point point)
	{
		if ( sourceWidget != widget )
			return false;

		Point targetSceneLocation = widget.convertSceneToLocal(point);
		targetWidget = resolveTargetWidgetCore(interractionLayer.getScene(),
				targetSceneLocation);
		Anchor targetAnchor = null;
		if ( targetWidget != null )
			targetAnchor = decorator.createTargetAnchor(targetWidget);
		if ( targetAnchor == null )
			targetAnchor = decorator.createFloatAnchor(targetSceneLocation);
		connectionWidget.setTargetAnchor(targetAnchor);

		return true;
	}

	private Widget resolveTargetWidgetCore(Scene scene, Point sceneLocation)
	{
		if ( provider != null )
			if ( provider.hasCustomTargetWidgetResolver(scene) )
				return provider.resolveTargetWidget(scene, sceneLocation);
		Point sceneOrigin = scene.getLocation();
		sceneLocation = new Point(sceneLocation.x + sceneOrigin.x,
				sceneLocation.y + sceneOrigin.y);
		Widget[] result = new Widget[] { null };
		resolveTargetWidgetCoreDive(result, scene, sceneLocation);
		return result[0];
	}

	private boolean resolveTargetWidgetCoreDive(Widget[] result, Widget widget,
			Point parentLocation)
	{
		if ( interractionLayer.equals(widget) )
			return false;
		Point widgetLocation = widget.getLocation();
		Point location = new Point(parentLocation.x - widgetLocation.x,
				parentLocation.y - widgetLocation.y);

		if ( !widget.getBounds().contains(location) )
			return false;

		java.util.List<Widget> children = widget.getChildren();
		for ( int i = children.size() - 1; i >= 0; i-- )
		{
			if ( resolveTargetWidgetCoreDive(result, children.get(i), location) )
				return true;
		}

		if ( !widget.isHitAt(location) )
			return false;

		ConnectorState state = provider.isTargetWidget(sourceWidget, widget);
		if ( state == ConnectorState.REJECT )
			return false;
		if ( state == ConnectorState.ACCEPT )
			result[0] = widget;
		return true;
	}
}
