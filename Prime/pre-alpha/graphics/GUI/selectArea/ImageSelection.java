/**
 * 
 */
package graphics.GUI.selectArea;



import graphics.PrimeMain1;
import graphics.WidgetIcon;

import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import objects.Object;
import widgetManipulation.WidgetObject;


/**
 * A transferHandler extention class which contains a createTransferable
 * methode which returns a newly created object from a dragged 
 * {@link graphics.WidgetIcon WidgetIcon}.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ImageSelection extends TransferHandler
{

	/* (non-Javadoc)
	 * @see javax.swing.TransferHandler#getSourceActions(javax.swing.JComponent)
	 */
	public int getSourceActions(JComponent c)
	{
		return TransferHandler.COPY;
	}



	/* (non-Javadoc)
	 * @see javax.swing.TransferHandler#createTransferable(javax.swing.JComponent)
	 */
	public Transferable createTransferable(JComponent comp)
	{
		if ( comp instanceof WidgetIcon )
		{
			WidgetIcon icon = (WidgetIcon) comp;

			Object newObject = new CreateObjectDragged().CreateObject(icon);

			return new WidgetObject(PrimeMain1.scene, newObject, icon.getIconImage());
		}
		return null;
	}

}