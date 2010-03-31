/**
 * 
 */
package graphics.GUI.workareaCanvas.providers.workareaProviders.jMenuConnection;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import widgets.WorkareaCanvas;
import connections.WidgetExtendedConnection;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class WorkareaConnectionActionListener implements ActionListener
{
	/**
	 * 
	 */
	private WorkareaCanvas canvas;


	private WidgetExtendedConnection widCon;



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param canvas
	 */
	public WorkareaConnectionActionListener(WorkareaCanvas canvas)
	{
		this.canvas = canvas;
	}



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param canvas
	 * @param widget
	 */
	public WorkareaConnectionActionListener(WorkareaCanvas canvas,
			WidgetExtendedConnection widget)
	{
		this.canvas = canvas;
		widCon = widget;
	}



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
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
			if ( actionName.equals("DeleteThisConnection") )
			{
				// WorkareaCanvasActions.removeWidgetConnection(canvas, widCon);
			}
		}
	}
}
