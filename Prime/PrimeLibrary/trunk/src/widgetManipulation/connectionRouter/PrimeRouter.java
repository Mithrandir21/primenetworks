/**
 * 
 */
package widgetManipulation.connectionRouter;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.netbeans.api.visual.anchor.Anchor;
import org.netbeans.api.visual.router.Router;
import org.netbeans.api.visual.widget.ConnectionWidget;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PrimeRouter implements Router
{
	/**
	 * TODO - Description NEEDED!
	 * 
	 */
	public PrimeRouter()
	{
		// TODO Auto-generated constructor stub
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netbeans.api.visual.router.Router#routeConnection(org.netbeans.api
	 * .visual.widget.ConnectionWidget)
	 */
	@Override
	public List<Point> routeConnection(ConnectionWidget widget)
	{
		// An arrayList of points
		ArrayList<Point> list = new ArrayList<Point>();


		Anchor sourceAnchor = widget.getSourceAnchor();
		Anchor targetAnchor = widget.getTargetAnchor();
		if ( sourceAnchor == null || targetAnchor == null )
			return Collections.emptyList();

		list.add(sourceAnchor.compute(widget.getSourceAnchorEntry())
				.getAnchorSceneLocation());

		List<Point> oldControlPoints = widget.getControlPoints();
		if ( oldControlPoints != null )
		{
			ArrayList<Point> oldList = new ArrayList<Point>(oldControlPoints);
			oldList.remove(widget.getFirstControlPoint());
			oldList.remove(widget.getLastControlPoint());
			list.addAll(oldList);
		}

		list.add(targetAnchor.compute(widget.getTargetAnchorEntry())
				.getAnchorSceneLocation());

		return list;
	}

}
