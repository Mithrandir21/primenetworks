/**
 * 
 */
package graphics.GUI.selectArea;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.TransferHandler;


/**
 * This is the classes mouseListener. It gets the source of the mouse event, gets the transferhandler for the source and
 * sets the action for the handler to exportAsDrag. Contains only a "mousePressed" method.
 * 
 * @author Bahram Malaekeh
 */
public class TransferWidgetIconListener extends MouseAdapter
{
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		JLabel comp = (JLabel) e.getSource();
		TransferHandler handler = comp.getTransferHandler();
		handler.exportAsDrag(comp, e, TransferHandler.COPY);
	}
}
