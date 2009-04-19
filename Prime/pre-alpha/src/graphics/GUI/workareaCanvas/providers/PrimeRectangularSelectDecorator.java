/**
 * 
 */
package graphics.GUI.workareaCanvas.providers;


import java.awt.Color;

import javax.swing.BorderFactory;

import org.netbeans.api.visual.action.RectangularSelectDecorator;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PrimeRectangularSelectDecorator implements RectangularSelectDecorator
{
	private Scene scene;

	public PrimeRectangularSelectDecorator(Scene scene)
	{
		this.scene = scene;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.RectangularSelectDecorator#createSelectionWidget()
	 */
	@Override
	public Widget createSelectionWidget()
	{
		Widget widget = new Widget (scene);
		widget.setBorder(BorderFactory.createLineBorder(Color.black));
//        widget.setBorder (scene.getLookFeel().getMiniBorder(ObjectState.createNormal().deriveSelected (true)));
		return widget;
	}

}
