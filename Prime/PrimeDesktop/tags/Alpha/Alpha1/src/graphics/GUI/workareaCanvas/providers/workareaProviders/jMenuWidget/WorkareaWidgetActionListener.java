/**
 * 
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuWidget;


import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import actions.canvasActions.ActionDeleteAllConnections;



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
				// Gets the view, if there exist any, with the given object
				ObjectView view = PrimeMain1.getObjectView(widget.getObject());

				// There exist no view with the given object.
				// Which means that there exist no open view for the given
				// object.
				if ( view == null )
				{
					// Creates a new ObjectView object with the WidgetObject
					// that has been cast.
					ObjectView objView = new ObjectView(widget);

					// Adds the view to the arraylist of object views.
					PrimeMain1.addObjectView(objView);
				}
				else
				{
					// Brings the pre-existing ObjectView to the front.
					view.toFront();
				}
			}
			else if ( actionName.equals("CopyObject") )
			{
				// Sets the cutWidget pointer to null
				PrimeMain1.cutWidget = null;

				// Sets the widget as the widget to be copied
				PrimeMain1.copyWidget = widget;
			}
			else if ( actionName.equals("CutObject") )
			{
				// Sets the copyWidget pointer to null
				PrimeMain1.copyWidget = null;

				// Sets the widget as the widget to be cut(copied and deleted)
				PrimeMain1.cutWidget = widget;
			}
			else if ( actionName.equals("DeleteConnectionsObject") )
			{
				// WorkareaCanvasActions.removeAllConnectionsToFromObject(canvas,
				// widget.getObject());
				ActionDeleteAllConnections actionDeleteConnections = new ActionDeleteAllConnections(
						PrimeMain1.texts
								.getString("actionDeleteAllConnectionName"));
				actionDeleteConnections.performAction(true);
			}
		}


		canvas.cleanUp();

		PrimeMain1.updatePropertiesCanvasArea(false);
	}
}
