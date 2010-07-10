/**
 * 
 */
package widgetManipulation.wdigetAlign;


import java.awt.BasicStroke;
import java.awt.Color;

import org.netbeans.api.visual.action.AlignWithMoveDecorator;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PrimeAlignWithMoveDecorator implements AlignWithMoveDecorator
{

	/*
	 * (non-Javadoc)
	 * @see org.netbeans.api.visual.action.AlignWithMoveDecorator#createLineWidget(org.netbeans.api.visual.widget.Scene)
	 */
	@Override
	public ConnectionWidget createLineWidget(Scene scene)
	{
		ConnectionWidget widget = new ConnectionWidget(scene);
		widget.setStroke(new BasicStroke(1.0f, BasicStroke.JOIN_BEVEL,
				BasicStroke.CAP_BUTT, 5.0f, new float[] { 6.0f, 3.0f }, 0.0f));
		widget.setForeground(Color.BLUE);
		return widget;
	}

}
