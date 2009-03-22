/**
 * 
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuWidget;

import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import widgetManipulation.WidgetObject;
import actions.graphicalActions.WorkareaCanvasActions;

/**
 * Javadoc-TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class WorkareaWidgetActionListener implements ActionListener
{
	/**
	 * 
	 */
	private WorkareaCanvas canvas;
	
	
	
	/**
	 * Javadoc-TODO - Description NEEDED!
	 *
	 * @param canvas
	 */
	public WorkareaWidgetActionListener(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
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
			boolean set = false;
			Class<?> objectType = null;
			ImageIcon objectIcon = null;
			objects.Object newObject = null;
			WidgetObject newWidgetObject = null;

			if ( actionName.equals("DeleteConnectionsObject") )
			{
				WorkareaCanvasActions.removeAllConnectionsToFromObject(canvas,
						canvas.getCurrentWidgetObject().getObject());
			}
			else if ( actionName.equals("DeleteThisObject") )
			{
				WorkareaCanvasActions.deleteCurrentObject(canvas);
			}
		}
	}

}
