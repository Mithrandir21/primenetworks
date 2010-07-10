/**
 * 
 */
package graphics.GUI.workareaCanvas.providers;


import graphics.PrimeMain;

import org.netbeans.api.visual.widget.LabelWidget;
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
