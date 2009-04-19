/**
 * 
 */
package widgetManipulation;


import graphics.PrimeMain1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.AlignWithMoveDecorator;
import org.netbeans.api.visual.action.AlignWithWidgetCollector;
import org.netbeans.api.visual.action.MoveProvider;
import org.netbeans.api.visual.action.MoveStrategy;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.modules.visual.action.AlignWithSupport;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class MoveWidgetObjectProvider extends AlignWithSupport implements MoveStrategy, MoveProvider
{
	
	
	private static final AlignWithMoveDecorator ALIGN_WITH_MOVE_DECORATOR_DEFAULT = new AlignWithMoveDecorator()
	{
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.netbeans.api.visual.action.AlignWithMoveDecorator#createLineWidget(org.netbeans.api.visual.widget.Scene)
		 */
		public ConnectionWidget createLineWidget(Scene scene)
		{
			ConnectionWidget widget = new ConnectionWidget(scene);
			widget.setStroke(new BasicStroke(1.0f, BasicStroke.JOIN_BEVEL, BasicStroke.CAP_BUTT, 5.0f, new float[] {
					6.0f, 3.0f }, 0.0f));
			widget.setForeground(Color.BLUE);
			return widget;
		}
	};

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param collector
	 * @param interractionLayer
	 * @param decorator
	 */
	public MoveWidgetObjectProvider(AlignWithWidgetCollector collector, LayerWidget interractionLayer)
	{
		super(collector, interractionLayer, ALIGN_WITH_MOVE_DECORATOR_DEFAULT);
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.MoveStrategy#locationSuggested(org.netbeans.api.visual.widget.Widget,
	 * java.awt.Point, java.awt.Point)
	 */
	public Point locationSuggested(Widget widget, Point originalLocation, Point suggestedLocation)
	{
		Point widgetLocation = widget.getLocation();
		Rectangle widgetBounds = widget.getBounds();
		Rectangle bounds = widget.convertLocalToScene(widgetBounds);
		bounds.translate(suggestedLocation.x - widgetLocation.x, suggestedLocation.y - widgetLocation.y);
		Point point = super.locationSuggested(widget, bounds, suggestedLocation, true, true, true, true);
		return widget.getParentWidget().convertSceneToLocal(point);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.MoveProvider#movementStarted(org.netbeans.api.visual.widget.Widget)
	 */
	public void movementStarted(Widget widget)
	{
		show();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.MoveProvider#movementFinished(org.netbeans.api.visual.widget.Widget)
	 */
	public void movementFinished(Widget widget)
	{
		hide();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.MoveProvider#getOriginalLocation(org.netbeans.api.visual.widget.Widget)
	 */
	public Point getOriginalLocation(Widget widget)
	{
		return ActionFactory.createDefaultMoveProvider().getOriginalLocation(widget);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.MoveProvider#setNewLocation(org.netbeans.api.visual.widget.Widget,
	 * java.awt.Point)
	 */
	public void setNewLocation(Widget widget, Point location)
	{
		ActionFactory.createDefaultMoveProvider().setNewLocation(widget, location);
	}

}
