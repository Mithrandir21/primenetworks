/**
 * 
 */
package graphics.GUI.workareaCanvas.providers;


import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Iterator;

import org.netbeans.api.visual.action.RectangularSelectProvider;
import org.netbeans.api.visual.model.ObjectScene;

import widgetManipulation.WidgetObject;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PrimeObjectSceneRectangularSelectProvider implements RectangularSelectProvider
{
	private WorkareaCanvas canvas;
	
	public PrimeObjectSceneRectangularSelectProvider(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.RectangularSelectProvider#performSelection(java.awt.Rectangle)
	 */
	@Override
	public void performSelection(Rectangle sceneSelection)
	{
		boolean entirely = sceneSelection.width > 0;
		int w = sceneSelection.width;
		int h = sceneSelection.height;
		Rectangle rect = new Rectangle(w >= 0 ? 0 : w, h >= 0 ? 0 : h, w >= 0 ? w : -w, h >= 0 ? h : -h);
		rect.translate(sceneSelection.x, sceneSelection.y);

		
		WidgetObject[] objArray = canvas.getWidgetObjectsOnTheScene();
		ObjectScene scene = canvas.getObjectScene();
		HashSet<WidgetObject> set = new HashSet<WidgetObject> ();
		
		for ( int i = 0; i < objArray.length; i++ )
		{
			if ( objArray[i] instanceof WidgetObject )
			{
				WidgetObject object = (WidgetObject) objArray[i];
				
				if ( object == null )
				{
					continue;
				}
				if ( entirely )
				{
//					System.out.println(object.getObject().getObjectName());
					Rectangle widgetRect = object.convertLocalToScene(object.getBounds());
					if ( rect.contains(widgetRect) )
					{
//						System.out.println("is inside.");
						set.add(object);
					}
				}
				else
				{
//					if ( object instanceof ConnectionWidget )
//					{
//						System.out.println("testing....2");
//						ConnectionWidget conn = (ConnectionWidget) object;
//						java.util.List<Point> points = conn.getControlPoints();
//						for ( int j = points.size() - 2; j >= 0; j-- )
//						{
//							Point p1 = widget.convertLocalToScene(points.get(j));
//							Point p2 = widget.convertLocalToScene(points.get(j + 1));
//							if ( new Line2D.Float(p1, p2).intersects(rect) )
//								set.add(object);
//						}
//					}
//					else
//					{
//						System.out.println("testing....3");
//						Rectangle widgetRect = widget.convertLocalToScene(widget.getBounds());
//						if ( rect.intersects(widgetRect) )
//							set.add(object);
//					}
				}
			}
		}
		Iterator<WidgetObject> iterator = set.iterator();
//		scene.setFocusedObject(iterator.hasNext() ? iterator.next() : null);
//		scene.userSelectionSuggested(set, false);
	}

}
